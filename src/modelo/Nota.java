package modelo;

public class Nota {
    private Aluno aluno;
    private Avaliacao avaliacao;
    private double valor;

    public Nota(Aluno aluno, Avaliacao avaliacao, double valor){
        this.aluno = aluno;
        this.avaliacao = avaliacao;
        this.valor = valor;
    }

    public Aluno getAluno() {
        return this.aluno;
    }

    public Avaliacao getAvaliacao() {
        return this.avaliacao;
    }

    public double getValor() {
        return this.valor;
    }
}
