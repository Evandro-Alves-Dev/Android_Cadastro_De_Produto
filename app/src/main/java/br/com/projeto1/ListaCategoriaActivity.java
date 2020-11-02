package br.com.projeto1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.projeto1.database.CategoriaDAO;
import br.com.projeto1.modelo.Categoria;

public class ListaCategoriaActivity extends AppCompatActivity {

    private ListView listViewCategorias;
    private ArrayAdapter<Categoria> adapterCategorias;

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_categorias);
        setTitle("Categorias");
        listViewCategorias = findViewById(R.id.lv_categorias);
        definirOnclickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CategoriaDAO categoriaDAO = new CategoriaDAO(getBaseContext());
        adapterCategorias = new ArrayAdapter<Categoria>(ListaCategoriaActivity.this,
                android.R.layout.simple_list_item_1,
                categoriaDAO.listar());
        listViewCategorias.setAdapter(adapterCategorias);
    }

    private void definirOnclickListenerListView() {
        listViewCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Categoria categoriaClicada = adapterCategorias.getItem(position);
                Intent intent = new Intent(ListaCategoriaActivity.this, CadastroCategoriaActivity.class);
                intent.putExtra("categoriaEdicao", categoriaClicada);
                startActivity(intent);
            }
        });
    }

    public void onClickNovaCategoria(View v) {
        Intent intent = new Intent(ListaCategoriaActivity. this, CadastroCategoriaActivity.class);
        startActivity(intent);
    }

    public void onClickProdutos(View v) {
        Intent intent = new Intent(ListaCategoriaActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
