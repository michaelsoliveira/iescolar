package modelo;

public class EstruturaCurricular {
    private int id;
    private Curso curso;
    private String semestre;
    private String disciplina;

    public EstruturaCurricular(int id, Curso curso, String semestre, String disciplina) {
        this.id = id;
        this.curso = curso;
        this.semestre = semestre;
        this.disciplina = disciplina;
    }

    public int getId() {
        return id;
    }

    public Curso getCurso() {
        return curso;
    }

    public String getSemestre() {
        return semestre;
    }

    public String getDisciplina() {
        return disciplina;
    }
}
