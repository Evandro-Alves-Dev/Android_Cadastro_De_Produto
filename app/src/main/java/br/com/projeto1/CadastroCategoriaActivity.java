package br.com.projeto1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.projeto1.database.CategoriaDAO;
import br.com.projeto1.modelo.Categoria;

public class CadastroCategoriaActivity extends AppCompatActivity {

    private int id = 0;
    private EditText editTextDescricao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categoria);
        editTextDescricao = findViewById(R.id.edt_edicaoCategoria);
        carregarCategoria();
    }

    public void carregarCategoria() {
        Intent intent = new Intent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("categoriaEdicao") != null) {
            Categoria categoria = (Categoria) intent.getExtras().get("categoriaEdicao");
            editTextDescricao.setText(categoria.getDescricao());
            id = categoria.getId();
        }
    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        String descricao = editTextDescricao.getText().toString();
        Categoria categoria = new Categoria(id, descricao);
        CategoriaDAO categoriaDAO = new CategoriaDAO(getBaseContext());
        boolean salvou = categoriaDAO.salvar(categoria);
        if (salvou) {
            finish();
        } else {
            Toast.makeText(CadastroCategoriaActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }
    }
}
