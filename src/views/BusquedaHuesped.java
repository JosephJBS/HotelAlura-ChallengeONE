package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.HuespedDAO;
import dbConection.ConnectionDB;
import models.Huesped;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;

@SuppressWarnings("serial")
public class BusquedaHuesped extends JFrame {

	ConnectionDB factory = new ConnectionDB();
	Connection con = factory.recuperaConexion();
	private Integer idSeleccionado;
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private JTable tabla;
	private HuespedDAO huespedDao = new HuespedDAO(con);
	private DefaultTableModel modeloTabla = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusquedaHuesped frame = new BusquedaHuesped();
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
	public BusquedaHuesped() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(BusquedaHuesped.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Roboto", Font.PLAIN, 14));
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(BusquedaHuesped.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nroDocumento = txtBuscar.getText();
				modeloTabla.setRowCount(0);

				if (!nroDocumento.isEmpty()) {
					buscarHuespedPorDocumento(txtBuscar.getText());
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese el nro de documento");
				}

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnContinuar = new JPanel();
		btnContinuar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tabla.getSelectedRow() != -1) {
					idSeleccionado = (Integer) modeloTabla.getValueAt(tabla.getSelectedRow(), 0);
					System.out.println("Se envia Id seleccionado: " + idSeleccionado);

					ReservasView reservas = new ReservasView();
					reservas.obtenerCodigoHuesped(idSeleccionado);
					reservas.setVisible(true);
					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un registro", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnContinuar.setLayout(null);
		btnContinuar.setBackground(new Color(12, 138, 199));
		btnContinuar.setBounds(767, 508, 122, 35);
		btnContinuar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnContinuar);

		JLabel lblContinuar = new JLabel("Continuar");
		lblContinuar.setHorizontalAlignment(SwingConstants.CENTER);
		lblContinuar.setForeground(Color.WHITE);
		lblContinuar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblContinuar.setBounds(0, 0, 122, 35);
		btnContinuar.add(lblContinuar);
		setResizable(false);

		modeloTabla = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Devuelve false para que todas las celdas no sean editables
				return false;
			}
		};

		modeloTabla.addColumn("id");
		modeloTabla.addColumn("Nro Documento");
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Apellido");
		modeloTabla.addColumn("Nacionalidad");
		modeloTabla.addColumn("Telefono");

		// Crear la tabla con el modelo de datos
		tabla = new JTable(modeloTabla);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Agregar la tabla a un JScrollPane para que tenga barras de desplazamiento
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(20, 20, 800, 250);

		// Agregar el JScrollPane al panel del tabbedPane
		panel.addTab("Resultados", null, scrollPane, null);

		// Agregar un listener para manejar eventos de selección en la tabla
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

	}

	public void buscarHuespedPorDocumento(String nroDocumento) {
		List<Huesped> listaHuespedes = huespedDao.busquedaPorDocumento(nroDocumento);

		if (listaHuespedes.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se encontraron registros con el nro: " + nroDocumento, "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		System.out.println("Registros encontrados con el Nro Docmuento: " + nroDocumento + " -> \n" + listaHuespedes);

		for (Huesped huesped : listaHuespedes) {
			modeloTabla.addRow(
					new Object[] {
							huesped.getId(), 
							huesped.getDocIdentidad(), 
							huesped.getNombre(),
							huesped.getApellido(), 
							huesped.getNacionalidad(), 
							huesped.getTelefono() });
		}
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

}
