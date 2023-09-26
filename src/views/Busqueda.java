package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import dao.HuespedDAO;
import dao.ReservaDAO;
import dbConection.ConnectionDB;
import models.Huesped;
import models.Reserva;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	ConnectionDB factory = new ConnectionDB();
	Connection con = factory.recuperaConexion();
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private int valorDiario = 120;
	private HuespedDAO huespedDao = new HuespedDAO(con);
	private ReservaDAO reservaDao = new ReservaDAO(con);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
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

		panel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int selectedIndex = panel.getSelectedIndex();
				if (selectedIndex == 0) {
					System.out.println(selectedIndex);
					listarReservasRegistradas();

				} else if (selectedIndex == 1) {
					System.out.println(selectedIndex);
					listarHuespedesRegistrados();

				}

			}
		});

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		modelo.addColumn("Id Cliente");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Documento");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		System.out.println(panel.getSelectedIndex());

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

				String nroBusqueda = txtBuscar.getText();
				int posicionActual = posicionTabActual(panel);

				if (!nroBusqueda.isEmpty()) {
					switch (posicionActual) {
					case 0:
						buscarReservaPorNroReserva(txtBuscar.getText());
						break;

					case 1:
						buscarHuespedPorDocumento(txtBuscar.getText());
						break;
					default:
						break;
					}
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

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int posicionActual = posicionTabActual(panel);

				if (posicionActual == 0) {
					int filaSeleccionada = tbReservas.getSelectedRow();
					if (filaSeleccionada >= 0) {
						int idReservaSeleccionado = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
						int idCliente = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 5).toString());
						String fechaEntradStr = modelo.getValueAt(filaSeleccionada, 1).toString();
						String fechaSalidaStr = modelo.getValueAt(filaSeleccionada, 2).toString();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate fechaEntrada = LocalDate.parse(fechaEntradStr, formatter);
						LocalDate fechaSalida = LocalDate.parse(fechaSalidaStr, formatter);

						double valorPagar = valorDiario * ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);

						/*
						 * Integer idCliente, LocalDate fechaEntrada, LocalDate fechaSalida, double
						 * valor, String formapago, Integer id
						 */

						reservaDao.modificar(idCliente, fechaEntrada, fechaSalida, valorPagar,
								modelo.getValueAt(filaSeleccionada, 4).toString(), idReservaSeleccionado);

						listarReservasRegistradas();
						JOptionPane.showMessageDialog(null, "ID de reserva a eliminado: " + idReservaSeleccionado);
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona una reserva para eliminar.");
					}
				} else if (posicionActual == 1) {
					int filaSeleccionada = tbHuespedes.getSelectedRow();
					if (filaSeleccionada >= 0) {

						int idHuespedSeleccionado = (Integer) modeloHuesped.getValueAt(filaSeleccionada, 0);

						String fechaStr = modeloHuesped.getValueAt(filaSeleccionada, 3).toString();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate fecha = LocalDate.parse(fechaStr, formatter);

						huespedDao.modificar((String) modeloHuesped.getValueAt(filaSeleccionada, 1),
								(String) modeloHuesped.getValueAt(filaSeleccionada, 2), fecha,
								(String) modeloHuesped.getValueAt(filaSeleccionada, 4),
								(String) modeloHuesped.getValueAt(filaSeleccionada, 5), idHuespedSeleccionado,
								(String) modeloHuesped.getValueAt(filaSeleccionada, 6));

						listarHuespedesRegistrados();
						JOptionPane.showMessageDialog(null, "ID de huésped editado: " + idHuespedSeleccionado);
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona un huésped para editar.");
					}
				}
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int posicionActual = posicionTabActual(panel);

				if (posicionActual == 0) {
					int filaSeleccionada = tbReservas.getSelectedRow();
					if (filaSeleccionada >= 0) {
						int idReservaSeleccionado = (Integer) modelo.getValueAt(filaSeleccionada, 0);
						reservaDao.eliminar(idReservaSeleccionado);
						listarReservasRegistradas();

						System.out.println("ID de reserva a eliminar: " + idReservaSeleccionado);
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona una reserva para eliminar.");
					}
				} else if (posicionActual == 1) {
					int filaSeleccionada = tbHuespedes.getSelectedRow();
					if (filaSeleccionada >= 0) {
						int idHuespedSeleccionado = (Integer) modeloHuesped.getValueAt(filaSeleccionada, 0);
						huespedDao.eliminar(idHuespedSeleccionado);
						listarHuespedesRegistrados();
						System.out.println("ID de huésped a eliminar: " + idHuespedSeleccionado);
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona un huésped para eliminar.");
					}
				}
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	// FUNCIONES

	public void listarHuespedesRegistrados() {
		List<Huesped> listaHuespedes = huespedDao.listar();

		if (listaHuespedes.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se encontraron huespedes registrados", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		modeloHuesped.setRowCount(0);
		for (Huesped huesped : listaHuespedes) {
			modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
					huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
					huesped.getDocIdentidad() });
		}
	}

	public void buscarHuespedPorDocumento(String nroDocumento) {

		List<Huesped> listaHuespedes = huespedDao.busquedaPorDocumento(nroDocumento);
		System.out.println(listaHuespedes);

		if (listaHuespedes.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se encontraron registros con el nro", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		modeloHuesped.setRowCount(0);

		for (Huesped huesped : listaHuespedes) {
			modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
					huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
					huesped.getDocIdentidad() });
		}
	}

	public void listarReservasRegistradas() {

		List<Reserva> listaReservas = reservaDao.listar();
		System.out.println(listaReservas);

		if (listaReservas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se encontraron reservas registrados", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		modelo.setRowCount(0);

		for (Reserva reserva : listaReservas) {
			modelo.addRow(new Object[] { reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(),
					reserva.getValor(), reserva.getFormaPago(), reserva.getIdCliente() });

		}

	}

	public void buscarReservaPorNroReserva(String nroReserva) {
		List<Reserva> listaReservas = reservaDao.busquedaPorNroReserva(nroReserva);

		if (listaReservas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se encontraron registros con el nro", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		modelo.setRowCount(0);

		for (Reserva reserva : listaReservas) {
			modelo.addRow(new Object[] { reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(),
					reserva.getValor(), reserva.getFormaPago(), reserva.getIdCliente() });

		}
	}

	public int posicionTabActual(JTabbedPane panel) {
		int nroTab = panel.getSelectedIndex();
		return nroTab;
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
