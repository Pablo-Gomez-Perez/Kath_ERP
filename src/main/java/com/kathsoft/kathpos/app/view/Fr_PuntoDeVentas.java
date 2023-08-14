package com.kathsoft.kathpos.app.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JTextField;

public class Fr_PuntoDeVentas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8197295139603781983L;
	/**
	 * 
	 * 
	 * 
	 */
	private JPanel contentPane;
	private JPanel panelSuperiorDatos;
	private JTextField txfFolioVenta;

	/**
	 * Create the frame.
	 */
	public Fr_PuntoDeVentas() {
		
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Fr_PuntoDeVentas.class.getResource("/com/kathsoft/kathpos/app/resources/ventagr.png")));
		setTitle("Punto de venta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelSuperiorDatos = new JPanel();
		panelSuperiorDatos.setBackground(new Color(204, 255, 255));
		panelSuperiorDatos.setBorder(
				new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder(new LineBorder(new Color(0, 0, 0)),
						"Datos de venta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		contentPane.add(panelSuperiorDatos, BorderLayout.NORTH);
		panelSuperiorDatos.setLayout(new BoxLayout(panelSuperiorDatos, BoxLayout.Y_AXIS));
		
		Box horizontalBox = Box.createHorizontalBox();
		panelSuperiorDatos.add(horizontalBox);
		
		JLabel lblNewLabel = new JLabel("Folio");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		horizontalBox.add(lblNewLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut);
		
		txfFolioVenta = new JTextField();
		horizontalBox.add(txfFolioVenta);
		txfFolioVenta.setColumns(10);
		this.txfFolioVenta.setMaximumSize(this.txfFolioVenta.getPreferredSize());
		
	}

}
