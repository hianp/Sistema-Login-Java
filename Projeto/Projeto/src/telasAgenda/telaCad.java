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
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class telaCad extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaCad frame = new telaCad();
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
	public telaCad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 99, 72, 28);
		contentPane.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(102, 66, 332, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblIdusurio = new JLabel("Id Usu\u00E1rio");
		lblIdusurio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdusurio.setBounds(10, 60, 72, 28);
		contentPane.add(lblIdusurio);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(102, 105, 332, 20);
		contentPane.add(txtNome);
		
		JLabel lblLogin = new JLabel("Login Usu\u00E1rio");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogin.setBounds(10, 138, 82, 28);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(10, 177, 72, 28);
		contentPane.add(lblSenha);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(10, 216, 72, 28);
		contentPane.add(lblEmail);
		
		JLabel lblNewLabel_4_1 = new JLabel("Endere\u00E7o");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4_1.setBounds(10, 255, 72, 28);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Sexo");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4_2.setBounds(10, 294, 72, 28);
		contentPane.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("Foto");
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4_3.setBounds(10, 333, 72, 28);
		contentPane.add(lblNewLabel_4_3);
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(102, 144, 332, 20);
		contentPane.add(txtLogin);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(102, 222, 332, 20);
		contentPane.add(txtEmail);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(102, 261, 332, 20);
		contentPane.add(txtEndereco);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Masculino");
		rdbtnNewRadioButton.setBounds(146, 299, 109, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Feminino");
		rdbtnNewRadioButton_1.setBounds(267, 299, 109, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(102, 338, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conlogin = ConectaBD.createConnectionToMySQL();
					String sql = "insert into user (id_user, nome, login, senha, email, endereco) values (?, ?, ?, ?, ?, ?)";
					PreparedStatement stmt = conlogin.prepareStatement(sql);
					stmt.setString(1, txtId.getText());
					stmt.setString(2, txtNome.getText());
					stmt.setString(3, txtLogin.getText());
					stmt.setString(4, new String(txtSenha.getText()));
					stmt.setString(5, txtEmail.getText());
					stmt.setString(6, txtEndereco.getText());
					stmt.execute();
					stmt.close();
					conlogin.close();
					JOptionPane.showMessageDialog(null, "Cadastrado realizado com sucesso");

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(102, 411, 332, 28);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Limpar");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpar();
			}
		});
		btnNewButton_1_1.setBounds(102, 372, 332, 28);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Excluir");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conlogin = ConectaBD.createConnectionToMySQL();
					String sql = "delete from user where id_user=?";
					PreparedStatement stmt = conlogin.prepareStatement(sql);
					stmt.setString(1, txtId.getText());
					stmt.execute();
					stmt.close();
					conlogin.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso");	
			Limpar();
			}			
		});
		
		btnNewButton_1_1_1.setBounds(102, 450, 332, 28);
		contentPane.add(btnNewButton_1_1_1);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(102, 183, 332, 20);
		contentPane.add(txtSenha);
		
		JButton btnNewButton_2 = new JButton("Buscar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection buscar = ConectaBD.createConnectionToMySQL();
					String sql = "select *from user where id_user=?";
					PreparedStatement stmt = buscar.prepareStatement(sql);
					stmt.setString(1, txtId.getText());

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						txtId.setText(rs.getString("id_user"));
						txtNome.setText(rs.getString("nome"));
						txtLogin.setText(rs.getString("login"));
						txtSenha.setText(rs.getString("senha"));
						txtEmail.setText(rs.getString("email"));
						txtEndereco.setText(rs.getString("endereco"));
					}										
					rs.close();
					buscar.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton_2.setBounds(439, 65, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Alterar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection atualizar = ConectaBD.createConnectionToMySQL();
					String sql = "update user set nome=?, login=?, senha=? where id_user=?";
					PreparedStatement stmt = atualizar.prepareStatement(sql);
					stmt.setString(1, txtNome.getText());
					stmt.setString(2, txtLogin.getText());
					stmt.setString(3, new String(txtSenha.getPassword()));
					stmt.setString(4, txtId.getText());

				stmt.execute();
				stmt.close();
				atualizar.close();
				JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso");
				} catch (Exception e1) {
					e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERRO");
				}

			}
		});
		btnNewButton_3.setBounds(345, 338, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Voltar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login Login = new Login();
				Login.setLocationRelativeTo(null);
				Login.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_4.setBounds(102, 489, 332, 28);
		contentPane.add(btnNewButton_4);
	}
	private void Limpar() {
		txtId.setText("");
		txtNome.setText("");
		txtLogin.setText("");
		txtSenha.setText("");
		txtEmail.setText("");
		txtEndereco.setText("");
		txtId.requestFocus();
	}
}
