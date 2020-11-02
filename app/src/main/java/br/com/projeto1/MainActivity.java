package br.com.projeto1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.projeto1.database.ProdutoDAO;
import br.com.projeto1.modelo.Produto;
import br.com.projeto1.ListaCategoriaActivity;

public class MainActivity extends AppCompatActivity {

    private ListView listViewProdutos;
    private ArrayAdapter<Produto> adapterProdutos;

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Produtos");
        listViewProdutos = findViewById(R.id.ListView);
        definirOnClickListenerListView();
        definirOnLongClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
        adapterProdutos = new ArrayAdapter<Produto>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                produtoDAO.listar());
        listViewProdutos.setAdapter(adapterProdutos);

    }

    private void definirOnClickListenerListView() {
        listViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtoClicado = adapterProdutos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
                intent.putExtra("produtoEditado", produtoClicado);
                startActivity(intent);
            }
        });
    }

    private void definirOnLongClick() {
        listViewProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Produto produtoClicado = adapterProdutos.getItem(position);

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Excluir item?")
                        .setMessage("Excluir permanentemente este item?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ProdutoDAO produtoDao = new ProdutoDAO(getBaseContext());
                                boolean excluiu = produtoDao.excluir(produtoClicado);
                                if (excluiu) {
                                    onResume();
                                    Toast.makeText(MainActivity.this, "Produto exluido com Sucesso.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Não", null).show();
                return true;
            }
        });
    }

    public void onClickNovoProduto(View v) {
        Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
        startActivity(intent);
    }

    public void onClickCategoria(View v) {
        Intent intent = new Intent(MainActivity.this, ListaCategoriaActivity.class);
        startActivity(intent);
        finish();
    }
}