package formulario;
import javax.swing.*;

import modelo.Disciplina;
import modelo.Professor;

import java.awt.*;
import java.awt.event.*;

public class FormCadastroDisciplina extends JFrame{
    private JTextField txtNomeDisciplina;
    private JComboBox<String> comboProfessores;
    private JButton btnCadastrar;

    public FormCadastroDisciplina() {
        setTitle("Cadastro de Disciplina");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel lblNomeDisciplina = new JLabel("Nome da Disciplina:");
        txtNomeDisciplina = new JTextField(25);
        btnCadastrar = new JButton("Cadastrar");

        JLabel lblProfessor = new JLabel("Professor:");
        comboProfessores = new JComboBox<>(new String[]{"Professor 1", "Professor 2"});
        // String [] colunas = { "Nome", " Telefone ", "Email" };
        // Object [][] dados = {
        //     { "Michael Olivera", "981341534", "michael.oliveira@unifap.br" },
        //     { "MIchael Olivera", "981341534", "michael.oliveira@unifap.br" },
        //     { "MIchael Olivera", "981341534", "michael.oliveira@unifap.br" }
        // };
        // JPanel painelFundo = new JPanel();
        // JTable tabela = new JTable(dados, colunas);
        // JScrollPane barraRolagem = new JScrollPane();
        // painelFundo.add(barraRolagem);
        // getContentPane().add(painelFundo);
        // add(tabela);

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeDisciplina = txtNomeDisciplina.getText();
                String nomeProfessor = (String) comboProfessores.getSelectedItem();
                Professor professor = new Professor(nomeProfessor);
                Disciplina disciplina = new Disciplina(nomeDisciplina, professor);
                JOptionPane.showMessageDialog(null, "A Disciplina " + 
                disciplina.getNome() + " vinculada ao professor "+ professor.getNome() + 
                " foi cadastrada com sucesso!");
            }
        });

        add(lblNomeDisciplina);
        add(txtNomeDisciplina);
        add(lblProfessor);
        add(comboProfessores);
        add(btnCadastrar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FormCadastroDisciplina().setVisible(true);
            }
        });
    }
}
