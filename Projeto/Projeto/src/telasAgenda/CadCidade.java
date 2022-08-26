package telasAgenda;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conectaBanco.ConectaBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class CadCidade extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtCidade;
	private JTextField txtUf;
	private JButton btnNewButton_1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					CadCidade frame = new CadCidade();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadCidade() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 31, 77, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNomeDaCidade = new JLabel("Cidade");
		lblNomeDaCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDaCidade.setBounds(10, 71, 77, 29);
		contentPane.add(lblNomeDaCidade);
		
		JLabel lblUf = new JLabel("UF");
		lblUf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUf.setBounds(10, 111, 77, 29);
		contentPane.add(lblUf);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(97, 37, 188, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(97, 77, 188, 20);
		contentPane.add(txtCidade);
		
		txtUf = new JTextField();
		txtUf.setColumns(10);
		txtUf.setBounds(97, 117, 188, 20);
		contentPane.add(txtUf);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conlogin = ConectaBD.createConnectionToMySQL();
					String sql = "insert into cidade (id_cidade, cidade, uf) values (?, ?, ?)";
					PreparedStatement stmt = conlogin.prepareStatement(sql);
					stmt.setString(1, txtCodigo.getText());
					stmt.setString(2, txtCidade.getText());
					stmt.setString(3, txtUf.getText());
					stmt.execute();
					stmt.close();
					conlogin.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			JOptionPane.showMessageDialog(null, "Cidade cadastrada com sucesso");
			}
		});
		btnNewButton.setBounds(97, 174, 188, 29);
		contentPane.add(btnNewButton);
		
		
		btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login tela = new Login();
				tela.setLocationRelativeTo(null);
				tela.setVisible(true);
				//new Login().setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(97, 214, 188, 29);
		contentPane.add(btnNewButton_1);
	}

}
