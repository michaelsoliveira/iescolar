package modelo;

public class Disciplina {
    private String nome;
    private Professor professor;

    public Disciplina(String nome, Professor professor) {
        this.nome = nome;
        this.professor = professor;
    }

    public String getNome() {
        return this.nome;
    }

    public Professor getProfessor() {
        return this.professor;
    }
}