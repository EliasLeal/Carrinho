package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.DAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaCarrinho extends JFrame {
	private JPanel contentPane;
	private JLabel lblStatus;
	private JTextField textcodigo;
	private JTextField textnome;
	private JTextField textQuantidade;
	private JTextField textValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCarrinho frame = new TelaCarrinho();
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

	public TelaCarrinho() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				// Status da conexão
				status();
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblcodigo = new JLabel("Codigo:");
		lblcodigo.setBounds(10, 21, 65, 17);
		contentPane.add(lblcodigo);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 53, 65, 14);
		contentPane.add(lblNome);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(10, 94, 85, 14);
		contentPane.add(lblQuantidade);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(217, 97, 46, 14);
		contentPane.add(lblValor);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(TelaCarrinho.class.getResource("/icones/dberror.png")));
		lblStatus.setBounds(390, 217, 32, 32);
		contentPane.add(lblStatus);

		textcodigo = new JTextField();
		textcodigo.setBounds(81, 19, 46, 20);
		contentPane.add(textcodigo);
		textcodigo.setColumns(10);

		textnome = new JTextField();
		textnome.setBounds(83, 50, 293, 20);
		contentPane.add(textnome);
		textnome.setColumns(10);

		textQuantidade = new JTextField();
		textQuantidade.setBounds(83, 91, 116, 20);
		contentPane.add(textQuantidade);
		textQuantidade.setColumns(10);

		textValor = new JTextField();
		textValor.setBounds(261, 94, 105, 20);
		contentPane.add(textValor);
		textValor.setColumns(10);

		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setEnabled(false);
		btnPesquisar.setToolTipText("Pesquisar Produto");
		btnPesquisar.setIcon(new ImageIcon(TelaCarrinho.class.getResource("/icones/read.png")));
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setBorder(null);
		btnPesquisar.setBackground(SystemColor.control);
		btnPesquisar.setBounds(30, 141, 48, 48);
		contentPane.add(btnPesquisar);

		btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdd.setEnabled(false);
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setBorder(null);
		btnAdd.setBackground(SystemColor.control);
		btnAdd.setToolTipText("Adicionar contato");
		btnAdd.setIcon(new ImageIcon(TelaCarrinho.class.getResource("/icones/create.png")));
		btnAdd.setBounds(110, 180, 64, 64);
		contentPane.add(btnAdd);

		btnEdit = new JButton("");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();

			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setToolTipText("Editar contato");
		btnEdit.setIcon(new ImageIcon(TelaCarrinho.class.getResource("/icones/update.png")));
		btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEdit.setBorder(null);
		btnEdit.setBackground(SystemColor.control);
		btnEdit.setBounds(175, 180, 64, 64);
		contentPane.add(btnEdit);

		btnDel = new JButton("");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnDel.setEnabled(false);
		btnDel.setBorder(null);
		btnDel.setToolTipText("Excluir contato");
		btnDel.setIcon(new ImageIcon(TelaCarrinho.class.getResource("/icones/delete.png")));
		btnDel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDel.setBackground(SystemColor.control);
		btnDel.setBounds(241, 180, 64, 64);
		contentPane.add(btnDel);

	} // Fim do construtor

	DAO dao = new DAO();
	private JButton btnPesquisar;
	private JButton btnAdd;
	private JButton btnDel;
	private JButton btnEdit;

	/**
	 * Status da conexão
	 */
	private void status() {
		try {
			// estabelecer uma conexão
			Connection con = dao.conectar();
			// status
			// System.out.println(con);
			// trocando o ícone do banco(status de conexão)
			if (con != null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dbok.png")));
				btnPesquisar.setEnabled(true);
			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/dberror.png")));
			}
			// encerrar conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void pesquisar() {
		// instrução sql para pesquisar
		String read = "select * from carrinho where Id = ?";
		try {
			// estabelecer uma conexão
			Connection con = dao.conectar();
			// preparar a instrução sql
			PreparedStatement pst = con.prepareStatement(read);
		

			// resultado
			ResultSet rs = pst.executeQuery();
			// se existir um contato correspondente
			if (rs.next()) {
				textcodigo.setText(rs.getString(2));
				textnome.setText(rs.getString(3));
				textQuantidade.setText(rs.getString(4));
				textValor.setText(rs.getString(5));
				btnEdit.setEnabled(true);
				btnDel.setEnabled(true);
				btnPesquisar.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "Contato inexistente");
				btnAdd.setEnabled(true);
				btnPesquisar.setEnabled(false);
				con.close();
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void editar() {
		// validação dos campos
		if (textnome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do Produto");

		} else if (textnome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o quantidade do Produto");

		} else if (textnome.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo nome do Produto não pode ter mais que 50 caracteres");

		} else if (textQuantidade.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "O campo quantidade não pode ter mais que 15 caracteres");
		} else if (textValor.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo valor não pode ter mais que 50 caracteres");
		} else {
			String update = "update carrinho set Produto=?, Quantidade=?, Valor=? where Codigo=?";

			try {
				// estabelecer uma conexão
				Connection con = dao.conectar();
				// preparar a instrução sql
				PreparedStatement pst = con.prepareStatement(update);
				// subistituir os parametros (?,?,?) pelo conteudo das caixas de texto
				pst.setString(1, textnome.getText());
				pst.setString(2, textQuantidade.getText());
				pst.setString(3, textValor.getText());
				// executar a query (insert no banco de dados)
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Produto editado com sucesso");
				con.close();
				limpar();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * inserir um novo contato CRUD Creat
	 */
	private void adicionar() {
		// validação dos campos
		if (textnome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do Produto");

		} else if (textnome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o quantidade do Produto");

		} else if (textnome.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo nome do Produto não pode ter mais que 50 caracteres");

		} else if (textnome.getText().length() > 15) {
			JOptionPane.showMessageDialog(null, "O campo quantidade não pode ter mais que 15 caracteres");
		} else if (textValor.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "O campo valor não pode ter mais que 50 caracteres");
		} else {
			String create = "insert into carrinho (barcode, produto,quantidade) values (?,?,?)";

			try {
				// estabelecer uma conexão
				Connection con = dao.conectar();
				// preparar a instrução sql
				PreparedStatement pst = con.prepareStatement(create);
				// subistituir os parametros (?,?,?) pelo conteudo das caixas de texto
				pst.setString(2, textnome.getText());
				pst.setString(3, textQuantidade.getText());
				// executar a query (insert no banco de dados)
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
				con.close();
				limpar();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	/**
	 * Excluir contato CRUD Delete
	 */
	private void deletar() {
		String delete = "delete from carrinho where  codigo=?";
		// Confirmação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "confirma a exclusão desse produto?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, textcodigo.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Contato excluido");
				limpar();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			limpar();
		}
	}

	/**
	 * Limpar campos e configurar os botoes
	 */
	private void limpar() {
		textnome.setText(null);
		textQuantidade.setText(null);
		textValor.setText(null);
		btnAdd.setEnabled(false);
		btnEdit.setEnabled(false);
		btnDel.setEnabled(false);
		btnPesquisar.setEnabled(true);

	}
}
