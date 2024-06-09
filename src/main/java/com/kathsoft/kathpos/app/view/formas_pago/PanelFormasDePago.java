package com.kathsoft.kathpos.app.view.formas_pago;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.view.Fr_principal;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;

public class PanelFormasDePago extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelEtiquetaFormasDePago;
	private JLabel lblNewLabel_9;
	private JComponent panelFormasDePagoCentral;
	private JScrollPane scrollPaneTablaFormasDePago;
	private DefaultTableModel modelTablaFormasDePago;
	private JTable tablaFormasDePago;
	private JPanel panelFormasDePagoCentralBotones;
	private JButton btnNuevaFormaDePago;
	private JButton btnActualizarFormaDePago;
	private JButton btnEliminarFormaPago;

	/**
	 * Create the panel.
	 */
	public PanelFormasDePago() {
		
		this.setForeground(new Color(255, 215, 0));
		this.setLayout(new BorderLayout(0, 0));
		
		panelEtiquetaFormasDePago = new JPanel();
		panelEtiquetaFormasDePago.setBackground(new Color(0, 0, 128));
		this.add(panelEtiquetaFormasDePago, BorderLayout.NORTH);

		lblNewLabel_9 = new JLabel("Formas De Pago");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelEtiquetaFormasDePago.add(lblNewLabel_9);

		panelFormasDePagoCentral = new JPanel();
		panelFormasDePagoCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelFormasDePagoCentral.setBackground(new Color(255, 215, 0));
		this.add(panelFormasDePagoCentral, BorderLayout.CENTER);
		panelFormasDePagoCentral.setLayout(new BorderLayout(0, 0));

		scrollPaneTablaFormasDePago = new JScrollPane();
		panelFormasDePagoCentral.add(scrollPaneTablaFormasDePago, BorderLayout.CENTER);

		modelTablaFormasDePago = new DefaultTableModel();

		modelTablaFormasDePago.addColumn("Id");
		modelTablaFormasDePago.addColumn("Forma de pago");
		modelTablaFormasDePago.addColumn("Activo");

		tablaFormasDePago = new JTable();
		tablaFormasDePago.setModel(modelTablaFormasDePago);
		scrollPaneTablaFormasDePago.setViewportView(tablaFormasDePago);
		
		DataTools.removerEditorDeTabla(tablaFormasDePago, modelTablaFormasDePago);
		
		panelFormasDePagoCentralBotones = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panelFormasDePagoCentralBotones.getLayout();
		flowLayout_9.setAlignment(FlowLayout.RIGHT);
		panelFormasDePagoCentralBotones.setBackground(new Color(255, 215, 0));
		panelFormasDePagoCentral.add(panelFormasDePagoCentralBotones, BorderLayout.NORTH);

		btnNuevaFormaDePago = new JButton("Agregar");
		btnNuevaFormaDePago.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnNuevaFormaDePago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormDatosFormasDePago(1, 0);
			}
		});
		this.btnNuevaFormaDePago.setBackground(new Color(152, 251, 152));
		panelFormasDePagoCentralBotones.add(btnNuevaFormaDePago);

		btnActualizarFormaDePago = new JButton("Actualizar");
		btnActualizarFormaDePago.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		btnActualizarFormaDePago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormDatosFormasDePago(2,
						DataTools.getIndiceElementoSeleccionado(tablaFormasDePago, modelTablaFormasDePago, 0));
			}
		});
		this.btnActualizarFormaDePago.setBackground(new Color(152, 251, 152));
		panelFormasDePagoCentralBotones.add(btnActualizarFormaDePago);

		btnEliminarFormaPago = new JButton("Eliminar");
		btnEliminarFormaPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarFormaDePago();
			}
		});
		btnEliminarFormaPago.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btnEliminarFormaPago.setBackground(new Color(255, 51, 0));
		panelFormasDePagoCentralBotones.add(btnEliminarFormaPago);
	}
	
	private void abrirFormDatosFormasDePago(int opcion, int idFormaDePago) {

		Component cm = null;
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					Fr_DatosFormaDePago frame = new Fr_DatosFormaDePago(opcion, idFormaDePago);
					frame.setLocationRelativeTo(cm);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception er) {
					er.printStackTrace();
				}

			}
		});

	}
	
	private void eliminarFormaDePago() {

		int input = MessageHandler.displayMessage(MessageHandler.DELETE_DATA_QUESTION_MESSAGE, this, "");

		if (input > 0) {
			return;
		}

		try {
			AppContext.formasDePagoController.eliminarFormaDepAgo(
					DataTools.getIndiceElementoSeleccionado(tablaFormasDePago, modelTablaFormasDePago, 0));

			MessageHandler.displayMessage(MessageHandler.DELETE_SUCCESS_MESSAGE, this, "");
		} catch (SQLException er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, " ", er.getMessage());
		}
	}
	
	public void llenarTablaFormasDePago() {
		this.modelTablaFormasDePago.getDataVector().removeAllElements();
		this.tablaFormasDePago.updateUI();
		AppContext.formasDePagoController.verFormasDePagoEnTabla(this.modelTablaFormasDePago);
	}

}
