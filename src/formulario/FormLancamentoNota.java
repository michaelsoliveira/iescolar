package formulario;

import modelo.Aluno;
import modelo.Turma;
import modelo.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormLancamentoNota extends JFrame {
    private JTextField txtNomeAluno;
    private JTextField txtNota;
    private JComboBox<Turma> comboTurmas;
    private JButton btnLancarNota;
    private DefaultComboBoxModel<Turma> modeloTurmas;

    public FormLancamentoNota(java.util.List<Turma> turmas) {
        setTitle("Lançamento de Nota");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel lblNomeAluno = new JLabel("Nome do Aluno:");
        txtNomeAluno = new JTextField(25);

        JLabel lblNota = new JLabel("Nota:");
        txtNota = new JTextField(5);

        JLabel lblTurma = new JLabel("Turma:");
        modeloTurmas = new DefaultComboBoxModel<>();
        for (Turma turma : turmas) {
            modeloTurmas.addElement(turma);
        }
        comboTurmas = new JComboBox<>(modeloTurmas);

        btnLancarNota = new JButton("Lançar Nota");

        btnLancarNota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeAluno = txtNomeAluno.getText();
                String notaStr = txtNota.getText();
                Turma turmaSelecionada = (Turma) comboTurmas.getSelectedItem();

                if (nomeAluno.isEmpty() || notaStr.isEmpty() || turmaSelecionada == null) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                } else {
                    try {
                        double nota = Double.parseDouble(notaStr);
                        Aluno aluno = new Aluno();
                        aluno.setNota(nota);

                        Pessoa pessoa = new Pessoa();
                        pessoa.setNome(nomeAluno);
                        aluno.setPessoa(pessoa);

                        turmaSelecionada.addAluno(aluno);
                        JOptionPane.showMessageDialog(null, "Nota lançada com sucesso para " + aluno.getPessoa().getNome() + " na turma " + turmaSelecionada.getNome());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira uma nota válida!");
                    }
                }
            }
        });

        add(lblNomeAluno);
        add(txtNomeAluno);
        add(lblNota);
        add(txtNota);
        add(lblTurma);
        add(comboTurmas);
        add(btnLancarNota);
    }

    public static void main(String[] args) {
        java.util.List<Turma> turmas = new java.util.ArrayList<>();
        turmas.add(new Turma("Matemática"));
        turmas.add(new Turma("História"));
        turmas.add(new Turma("Ciências"));

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FormLancamentoNota(turmas).setVisible(true);
            }
        });
    }
}
