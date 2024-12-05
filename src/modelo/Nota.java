package modelo;

public class Nota {
    private int id;
    private Aluno aluno;
    private Disciplina disciplina;
    private float valorNota;

    public Nota(int id, Aluno aluno, Disciplina disciplina, float valorNota) {
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.valorNota = valorNota;
    }

    public int getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public float getValorNota() {
        return valorNota;
    }

    public void setValorNota(float valorNota) {
        this.valorNota = valorNota;
    }
}
