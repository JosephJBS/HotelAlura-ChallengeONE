package Ejecutar;


import java.sql.SQLException;

import views.MenuPrincipal;


public class Main {

	public static void main(String[] args) throws SQLException {
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		menuPrincipal.setVisible(true);
	}

}
