package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.DAO.AlunoDAO;
import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configFabNovoAluno();
        configLista();
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
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configLista() {
        ListView listadeAlunos = findViewById(R.id.activity_lista_de_alunos);
        configAdapter(listadeAlunos);
        itemClick(listadeAlunos);
        itemLongClick(listadeAlunos);
    }

    private void itemLongClick(ListView listadeAlunos) {
        listadeAlunos.setOnItemLongClickListener((adapterView, view, posicao, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
            removeAluno(alunoEscolhido);
            return false;
        });
    }

    private void removeAluno(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
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

    private void configAdapter(ListView listadeAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listadeAlunos.setAdapter(adapter);
    }
}