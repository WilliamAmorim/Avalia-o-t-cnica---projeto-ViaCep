package br.com.projetoviacep.sqlLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Classe responsavel por criar o banco de dados SQLite
 */
public class ViaCepCache extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "viaCepCache.db";
    public static final String TABELA_CEPS = "ceps";
    public static final String CEP = "cep";
    public static final String LOGRADOURO = "logradouro";
    public static final String COMPLEMENTO = "complemento";
    public static final String BAIRRO = "bairro";
    public static final String LOCALIDADE = "localidade";
    public static final String UF = "uf";
    public static final String DDD = "ddd";
    public static final String IBGE = "ibge";
    public static final String GIA = "gia";
    public static final String SIAFI = "siafi";

    private static final int VERSAO = 1;

    public ViaCepCache(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlListas = "CREATE TABLE "+TABELA_CEPS+"("
                + CEP + " text ,"
                + LOGRADOURO + " text,"
                + COMPLEMENTO + " text,"
                + BAIRRO + " text,"
                + LOCALIDADE + " text,"
                + UF + " text,"
                + DDD + " text,"
                + IBGE + " text,"
                + GIA + " text,"
                + SIAFI + " text"
                +")";
        db.execSQL(sqlListas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CEPS);
        onCreate(db);
    }
}
