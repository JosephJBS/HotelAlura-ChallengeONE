package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class ConsultaHuesped extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConsultaHuesped dialog = new ConsultaHuesped();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConsultaHuesped() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConsultaHuesped.class.getResource("/imagenes/aH-40px.png")));
		setBounds(100, 100, 394, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Abre la ventana que desees al cerrar la ConsultaHuesped
                MenuUsuario menuUsuario = new MenuUsuario();
                menuUsuario.setVisible(true);
            }
        });
		
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(ConsultaHuesped.class.getResource("/imagenes/Ha-100px.png")));
			lblNewLabel.setBounds(123, 11, 100, 100);
			contentPanel.add(lblNewLabel);
		}

		{
			JLabel lblNewLabel_1 = new JLabel("¿Es nuevo el huesped?");
			lblNewLabel_1.setForeground(new Color(12, 138, 199));
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
			lblNewLabel_1.setHorizontalAlignment(JLabel.CENTER);

			lblNewLabel_1.setBounds(27, 122, 322, 21);
			contentPanel.add(lblNewLabel_1);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton siButton = new JButton("SI");
				siButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();// sirve para cerrar la ventana actual
						RegistroHuesped registroHuesped = new RegistroHuesped();
						registroHuesped.setVisible(true);
					}
				});
				siButton.setActionCommand("SI");
				buttonPane.add(siButton);
				getRootPane().setDefaultButton(siButton);
			}
			{
				JButton noButton = new JButton("NO");
				noButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();// sirve para cerrar la ventana actual
						BusquedaHuesped registroHuesped = new BusquedaHuesped();
						registroHuesped.setVisible(true);
					}
				});
				noButton.setActionCommand("NO");
				buttonPane.add(noButton);
			}
		}
	}
}
