package modelo;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String nome;
    private List<Disciplina> disciplinas;
    private List<Aluno> alunos;

    public Turma(String nome) {
        this.nome = nome;
        this.disciplinas = new ArrayList<>();
        this.alunos = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public List<Aluno> getAlunos() {
        return this.alunos;
    }

    public void addDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public List<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }
}
