package br.com.projetoviacep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import br.com.projetoviacep.api.ViaCep;
import br.com.projetoviacep.common.Utility;
import br.com.projetoviacep.controller.ViaCepController;
import br.com.projetoviacep.dao.ViaCepCacheDAO;
import br.com.projetoviacep.model.CepModel;

public class MainActivity extends AppCompatActivity {

    //Declarando componentes da interface
    EditText editTextCep;
    EditText editTextLogradouro;
    EditText editTextComplemento;
    EditText editTextBairro;
    EditText editTextLocalidade;
    EditText editTextUf;
    EditText editTextDdd;
    Button buttonConsultar;

    ViaCepController viaCepController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializando variaveis
        editTextCep = findViewById(R.id.editText_cep);
        editTextLogradouro = findViewById(R.id.editText_logradouro);
        editTextComplemento = findViewById(R.id.editText_complemento);
        editTextBairro = findViewById(R.id.editText_bairro);
        editTextLocalidade = findViewById(R.id.editText_localidade);
        editTextUf = findViewById(R.id.editText_uf);
        editTextDdd = findViewById(R.id.editText_ddd);
        buttonConsultar = findViewById(R.id.button_consultar);

        viaCepController = new ViaCepController(getApplicationContext());

        buttonConsultar.setOnClickListener(v -> {
            preencherCampos(viaCepController.buscarCEP(editTextCep.getText().toString()));
        });

        editTextCep.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    preencherCampos(viaCepController.buscarCEP(editTextCep.getText().toString()));
                }
                return false;
            }
        });

    }

    private void preencherCampos(CepModel cepModel){
        if(cepModel != null) {
            editTextLogradouro.setText(cepModel.getLogradouro());
            editTextComplemento.setText(cepModel.getComplemento());
            editTextBairro.setText(cepModel.getBairro());
            editTextLocalidade.setText(cepModel.getLocalidade());
            editTextUf.setText(cepModel.getUf());
            editTextDdd.setText(cepModel.getDdd());
        }
    }

}