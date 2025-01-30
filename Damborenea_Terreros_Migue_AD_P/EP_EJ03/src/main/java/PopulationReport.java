import java.sql.*;
import java.util.Scanner;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class PopulationReport {

		public static void main(String[] args) {
			HikariConfig ds = new HikariDataSource();
			String basedatos = "world";
			String host = "localhost";
			String port = "3306";
			String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDateTimeCode=false&serverTimezone=UTC";
			String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
			String user = "root";
			String pwd = "mdt212001";
			
			ds.setJdbcUrl(urlConnection);
			ds.setUsername(user);
			ds.setPassword(pwd);
			
			HikariDataSource dataSource = new HikariDataSource(ds);
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Introduce el nombre del país: ");
			String pais = scanner.nextLine();
					
			try (Connection connection = dataSource.getConnection()) {
				String consulta = "SELECT ci.Name, ci.Population FROM city ci JOIN country co ON ci.CountryCode = co.Code WHERE co.Name = ?";
				try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
					stmt.setString(1, pais);
					try (ResultSet resultset = stmt.executeQuery()) {
						boolean encontrado = false;
						System.out.println("Ciudades de " + pais + ":");
						while (resultset.next()) {
							String ciudad = resultset.getString("Name");
							int poblacion = resultset.getInt("Population");
							System.out.println("Ciudad: " + ciudad + " Población: " + poblacion);
							encontrado = true;
						}
						if (!encontrado) {
							System.out.println("No se ha encontrado el país o no tiene ciudades");
						}
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			scanner.close();
			dataSource.close();
		}
}
