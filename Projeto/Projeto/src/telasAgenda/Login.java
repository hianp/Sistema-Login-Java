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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel btnLimpar;
	private JTextField login;
	private JPasswordField txtsenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login tela = new Login();
					tela.setLocationRelativeTo(null);
					tela.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 369);
		btnLimpar = new JPanel();
		btnLimpar.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(btnLimpar);
		btnLimpar.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLogin.setBounds(32, 55, 69, 47);
		btnLimpar.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSenha.setBounds(32, 138, 69, 23);
		btnLimpar.add(lblSenha);
		
		login = new JTextField();
		login.setBounds(111, 71, 314, 23);
		btnLimpar.add(login);
		login.setColumns(10);
		
		txtsenha = new JPasswordField();
		txtsenha.setBounds(109, 142, 314, 23);
		btnLimpar.add(txtsenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conlogin = ConectaBD.createConnectionToMySQL();
					String sql = "select *from user where login=? and senha=?";
					PreparedStatement stmt = conlogin.prepareStatement(sql);
					stmt.setString(1, login.getText());
					stmt.setString(2, new String(txtsenha.getPassword()));
					ResultSet rs = stmt.executeQuery();
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Acesso Liberado");
						CadProd CadProd = new CadProd();
						CadProd.setLocationRelativeTo(null);
						CadProd.setVisible(true);
						setVisible(false);
										
					}else {
						JOptionPane.showMessageDialog(null, "Acesso negado");
						
					}
				stmt.close();
				conlogin.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		btnEntrar.setBounds(370, 288, 112, 31);
		btnLimpar.add(btnEntrar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaCad telaCad = new telaCad();
				telaCad.setLocationRelativeTo(null);
				telaCad.setVisible(true);
				setVisible(false);
			}
		});
		btnCadastrar.setBounds(248, 288, 112, 31);
		btnLimpar.add(btnCadastrar);
		
		JButton btnNewButton_2 = new JButton("Limpar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnNewButton_2.setBounds(109, 187, 112, 31);
		btnLimpar.add(btnNewButton_2);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		btnSair.setBounds(311, 187, 112, 31);
		btnLimpar.add(btnSair);
		
		JButton btnNewButton = new JButton("Cadastrar Cidade");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadCidade CadCidade = new CadCidade();
				CadCidade.setLocationRelativeTo(null);
				CadCidade.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 288, 152, 31);
		btnLimpar.add(btnNewButton);
	}
	private void limpar() {
		login.setText("");
		txtsenha.setText("");
		login.requestFocus();
	}
	
	private void sair() {
		System.exit(0);
	}
}
