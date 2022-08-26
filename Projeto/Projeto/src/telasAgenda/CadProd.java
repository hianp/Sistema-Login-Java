package telasAgenda;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conectaBanco.ConectaBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadProd extends JFrame {
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtPreco;
	private JTable tbUsuarios;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadProd CadProd = new CadProd();
					CadProd.setLocationRelativeTo(null);
					CadProd.setVisible(true);
					//new Login().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadProd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCodigo.setBounds(74, 43, 80, 30);
		contentPane.add(lblCodigo);
		
		txtId = new JTextField();
		txtId.setBounds(164, 51, 326, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNomeDoProduto = new JLabel("Nome do Produto");
		lblNomeDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeDoProduto.setBounds(10, 84, 148, 30);
		contentPane.add(lblNomeDoProduto);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(164, 92, 326, 20);
		contentPane.add(txtNome);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o");
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPreco.setBounds(74, 125, 80, 30);
		contentPane.add(lblPreco);
		
		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(164, 133, 326, 20);
		contentPane.add(txtPreco);

		tbUsuarios = new JTable();
		tbUsuarios.setBorder(new LineBorder(new Color(0, 0, 0)));
		tbUsuarios.setBackground(Color.GRAY);
		tbUsuarios.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nome do Produto", "Pre\u00E7o"
			}
		));
		tbUsuarios.getColumnModel().getColumn(0).setPreferredWidth(185);
		tbUsuarios.getColumnModel().getColumn(1).setPreferredWidth(271);
		tbUsuarios.getColumnModel().getColumn(2).setPreferredWidth(135);
		tbUsuarios.setBounds(10, 309, 604, -136);
		contentPane.add(tbUsuarios);
		
		JButton btnNewButton = new JButton("Limpar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpar();
			}
		});
		btnNewButton.setBounds(164, 184, 326, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conlogin = ConectaBD.createConnectionToMySQL();
					String sql = "insert into produtos (codigoProduto, nomeProduto, precoUnitario) values (?, ?, ?)";
					PreparedStatement stmt = conlogin.prepareStatement(sql);
					stmt.setString(1, txtId.getText());
					stmt.setString(2, txtNome.getText());
					stmt.setString(3, txtPreco.getText());
					stmt.execute();
					stmt.close();
					conlogin.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			JOptionPane.showMessageDialog(null, "Cadastrado realizado com sucesso");
			
			
			}
		});
		btnNewButton_1.setBounds(164, 225, 326, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Excluir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conlogin = ConectaBD.createConnectionToMySQL();
					String sql = "delete from produtos where codigoProduto=?";
					PreparedStatement stmt = conlogin.prepareStatement(sql);
					stmt.setString(1, txtId.getText());
					stmt.execute();
					stmt.close();
					conlogin.close();
					JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso");	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			Limpar();			
			}
		});
		btnNewButton_2.setBounds(164, 266, 326, 30);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Voltar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login Login = new Login();
				Login.setLocationRelativeTo(null);
				Login.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(164, 309, 326, 30);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Buscar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection buscar = ConectaBD.createConnectionToMySQL();
					String sql = "select *from produtos where codigoProduto=?";
					PreparedStatement stmt = buscar.prepareStatement(sql);
					stmt.setString(1, txtId.getText());
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						txtId.setText(rs.getString("codigoProduto"));
						txtNome.setText(rs.getString("nomeProduto"));
						txtPreco.setText(rs.getString("precoUnitario"));
					}										
					rs.close();
					buscar.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(500, 50, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("Produtos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(271, 11, 103, 29);
		contentPane.add(lblNewLabel_1);
	}
	private void Limpar() {
		txtId.setText("");
		txtNome.setText("");
		txtPreco.setText("");
		txtId.requestFocus();
	}
}
