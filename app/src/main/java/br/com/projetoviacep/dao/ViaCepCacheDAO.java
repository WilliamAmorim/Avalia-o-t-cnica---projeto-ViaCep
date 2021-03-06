package br.com.projetoviacep.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.projetoviacep.model.CepModel;
import br.com.projetoviacep.sqlLite.ViaCepCache;


/**
 * Classe responsavel por se comunicar com o banco SQLite
 */
public class ViaCepCacheDAO {
    private SQLiteDatabase db;
    private ViaCepCache banco;


    public ViaCepCacheDAO(Context context){
        banco = new ViaCepCache(context);
    }

    private ContentValues setValores(CepModel cepModel){

        ContentValues valores = new ContentValues();

        valores.put(ViaCepCache.CEP,cepModel.getCep() );
        valores.put(ViaCepCache.LOGRADOURO,cepModel.getLogradouro() );
        valores.put(ViaCepCache.COMPLEMENTO,cepModel.getComplemento() );
        valores.put(ViaCepCache.BAIRRO,cepModel.getBairro() );
        valores.put(ViaCepCache.UF,cepModel.getUf());
        valores.put(ViaCepCache.IBGE,cepModel.getIbge());
        valores.put(ViaCepCache.DDD,cepModel.getDdd());
        valores.put(ViaCepCache.LOCALIDADE,cepModel.getLocalidade());
        valores.put(ViaCepCache.SIAFI,cepModel.getLocalidade());
        valores.put(ViaCepCache.GIA,cepModel.getLocalidade());

        return valores;
    }

    private CepModel preencherObjeto(Cursor cursor){
        CepModel cepModel = new CepModel();

        for(int i = 0; i < cursor.getCount();i++){
            cursor.moveToPosition(i);
            cepModel.setCep(cursor.getString(cursor.getColumnIndexOrThrow(ViaCepCache.CEP)));
            cepModel.setLogradouro(cursor.getString(cursor.getColumnIndexOrThrow(ViaCepCache.LOGRADOURO)));
            cepModel.setComplemento(cursor.getString(cursor.getColumnIndexOrThrow(ViaCepCache.COMPLEMENTO)));
            cepModel.setBairro(cursor.getString(cursor.getColumnIndexOrThrow(ViaCepCache.BAIRRO)));
            cepModel.setUf(cursor.getString(cursor.getColumnIndexOrThrow(ViaCepCache.UF)));
            cepModel.setIbge(cursor.getString(cursor.getColumnIndexOrThrow(ViaCepCache.IBGE)));
            cepModel.setDdd(cursor.getString(cursor.getColumnIndexOrThrow(ViaCepCache.DDD)));
            cepModel.setLocalidade(cursor.getString(cursor.getColumnIndexOrThrow(ViaCepCache.LOCALIDADE)));
            cepModel.setSiafi(cursor.getString(cursor.getColumnIndexOrThrow(ViaCepCache.SIAFI)));
            cepModel.setGia(cursor.getString(cursor.getColumnIndexOrThrow(ViaCepCache.GIA)));
        }

        return cepModel;
    }

    public void novoCEP(CepModel cepModel){
        if(cepModel != null) {
            long resultado;
            db = banco.getWritableDatabase();
            ContentValues valores = setValores(cepModel);
            resultado = db.insert(ViaCepCache.TABELA_CEPS, null, valores);
            db.close();
        }
    }

    public CepModel buscarCep(String cep){
        String where = ViaCepCache.CEP + " = " + cep;
        Cursor cursor;
        String[] campos =  {ViaCepCache.CEP,ViaCepCache.LOGRADOURO,ViaCepCache.COMPLEMENTO,ViaCepCache.BAIRRO,ViaCepCache.UF,ViaCepCache.IBGE,ViaCepCache.DDD,ViaCepCache.LOCALIDADE,ViaCepCache.SIAFI,ViaCepCache.GIA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA_CEPS, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        db.close();

        return preencherObjeto(cursor);
    }

    public void updateCep(String cep,CepModel cepModel){
        String where;
        long resultado;

        db = banco.getWritableDatabase();

        where = banco.CEP + "=" + cep;

        ContentValues valores = setValores(cepModel);

        resultado = db.update(banco.TABELA_CEPS,valores,where,null);

        db.close();

    }

    public void removerCep(String cep){
        long resultado;
        String where = banco.CEP + "=" + cep;
        db = banco.getReadableDatabase();
        resultado = db.delete(banco.TABELA_CEPS,where,null);
        db.close();
    }

    /**
     * Verifica se um CEP esta cadastrado no banco
     * @param cep
     * @return
     */
    public boolean isCep(String cep){

        String where = ViaCepCache.CEP + " = " + cep;

        Cursor cursor;
        String[] campos =  {banco.CEP};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA_CEPS, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        db.close();

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
}
