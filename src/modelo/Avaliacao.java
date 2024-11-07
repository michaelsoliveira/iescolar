package modelo;

public class Avaliacao {
    private Disciplina disciplina;

    public Avaliacao(Disciplina disciplina, double nota) {
        this.disciplina = disciplina;
    }

    public Disciplina getDisciplina() {
        return this.disciplina;
    }
}
