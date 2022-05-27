package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.DAO.AlunoDAO;
import br.com.alura.agenda.R;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configFabNovoAluno();
    }

    private void configFabNovoAluno() {
        View botaoNovoAluno = findViewById(R.id.activity_fab_novo_aluno);
        abreFormularioAlunoActivity(botaoNovoAluno);
    }

    private void abreFormularioAlunoActivity(View botaoNovoAluno) {
        botaoNovoAluno.setOnClickListener(view -> startActivity(new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configLista(dao);
    }

    private void configLista(AlunoDAO dao) {
        ListView listadeAlunos = findViewById(R.id.activity_lista_de_alunos);
        listadeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.todos()));
    }
}