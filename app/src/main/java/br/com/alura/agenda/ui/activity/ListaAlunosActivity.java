package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.alura.agenda.DAO.AlunoDAO;
import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

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
        inserirAluno(botaoNovoAluno);
    }

    private void inserirAluno(View botaoNovoAluno) {
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
        List<Aluno> alunos = dao.todos();
        configAdapter(listadeAlunos, alunos);
        itemClick(listadeAlunos);
    }

    private void itemClick(ListView listadeAlunos) {
        listadeAlunos.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
            editarAluno(alunoEscolhido);
        });
    }

    private void editarAluno(Aluno aluno) {
        Intent toFormulario = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        toFormulario.putExtra(CHAVE_ALUNO, aluno);
        startActivity(toFormulario);
    }

    private void configAdapter(ListView listadeAlunos, List<Aluno> alunos) {
        listadeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos));
    }
}