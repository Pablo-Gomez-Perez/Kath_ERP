package com.kathsoft.kathpos.app.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.model.compra.ArticuloPorCompra;
import com.kathsoft.kathpos.app.model.compra.Compra;
import com.kathsoft.kathpos.app.model.compra.CompraConDetalle;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;

class CompraControllerValidationTest {

	private static final int ID_SUCURSAL = 1;

	@Test
	void rechazaCompraSinArticulosAntesDePersistir() {
		CompraController controller = new CompraController();
		CompraConDetalle compra = new CompraConDetalle(crearCompra("FAC-001", 100.00, 16.00), List.of());

		SpResponseModel respuesta = controller.insertCompra(ID_SUCURSAL, compra);

		assertAll(() -> assertEquals(500, respuesta.id()),
				() -> assertEquals("La compra debe contener al menos un artículo", respuesta.message()));
	}

	@Test
	void rechazaCabeceraConCamposObligatoriosVaciosAntesDePersistir() {
		CompraController controller = new CompraController();
		Compra compraInvalida = new Compra.CompraBuilder().idEmpleado(1).idProveedor(1).folioFactura(" ")
				.fechaFactura(Date.valueOf("2026-08-26")).fechaCompra(Date.valueOf("2026-08-26")).tipoCompra(false)
				.subtotal(100.00).iva(16.00).build();

		SpResponseModel respuesta = controller.insertCompra(ID_SUCURSAL,
				new CompraConDetalle(compraInvalida, List.of(crearDetalle(100, 1, 100.00))));

		assertAll(() -> assertEquals(500, respuesta.id()),
				() -> assertEquals("El folio de factura es obligatorio", respuesta.message()));
	}

	@Test
	void rechazaArticuloDuplicadoAntesDePersistir() {
		CompraController controller = new CompraController();
		CompraConDetalle compra = new CompraConDetalle(crearCompra("FAC-002", 200.00, 32.00),
				List.of(crearDetalle(100, 1, 100.00), crearDetalle(100, 1, 100.00)));

		SpResponseModel respuesta = controller.insertCompra(ID_SUCURSAL, compra);

		assertAll(() -> assertEquals(500, respuesta.id()),
				() -> assertTrue(respuesta.message().contains("mismo artículo")));
	}

	@Test
	void rechazaSubtotalQueNoCoincideConDetalle() {
		CompraController controller = new CompraController();
		CompraConDetalle compra = new CompraConDetalle(crearCompra("FAC-003", 150.00, 24.00),
				List.of(crearDetalle(100, 1, 100.00)));

		SpResponseModel respuesta = controller.insertCompra(ID_SUCURSAL, compra);

		assertAll(() -> assertEquals(500, respuesta.id()),
				() -> assertEquals("El subtotal de la compra no coincide con la suma de los artículos",
						respuesta.message()));
	}

	private Compra crearCompra(String folio, double subtotal, double iva) {
		return new Compra.CompraBuilder().idEmpleado(1).idProveedor(1).folioFactura(folio)
				.fechaFactura(Date.valueOf("2026-08-26")).fechaCompra(Date.valueOf("2026-08-26")).tipoCompra(false)
				.subtotal(subtotal).iva(iva).build();
	}

	private ArticuloPorCompra crearDetalle(int idArticulo, int cantidad, double subtotal) {
		return new ArticuloPorCompra.ArticuloPorCompraBuilder().idArticulo(idArticulo).cantidad(cantidad)
				.subtotal(subtotal).build();
	}
}
