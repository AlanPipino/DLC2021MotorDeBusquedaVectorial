package ar.edu.utn.frc.dlc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootApplication
public class DlcApplication {

	public static void main(String[] args) {
		System.out.println("EMPIEZA LA APP");
		probarIndex();
		SpringApplication.run(DlcApplication.class, args);
	}

	private void probarDB(){
		DBManager dbm = new DBManager();
		try{
			dbm.init();

			System.out.println("TODO OK");
		} catch (SQLException ex){
			System.out.println("FALLO EL SQL");
		} catch (Exception ex){
			System.out.println("FALLO OTRA COSA");
		} finally {
			try{
				dbm.close();
			} catch (SQLException e){
				System.out.println("FALLO EL SQL AL CERRAR");
			}
		}
	}

	private static void probarIndex() {
		try{
			File file = new File("C:/pruebas/el_quijote.txt");
//			File file = new File("C:/pruebas/prueba.txt");
			Scanner sc = new Scanner(file);
			sc.useDelimiter("[ .,\\n\\[\\]'\\(\\)\\-\":;0-9!?@*]");

			HashSet<String> palabras = sc.tokens()
					.filter(p -> p.length() > 2)
					.collect(Collectors.toCollection(HashSet::new));

			System.out.println(palabras.size());
		} catch (IOException e){
			System.out.println("FALLO ALGO DEL ARCHIVO");
		} catch (Exception e){
			System.out.println("FALLO OTRA COSA");
		}
	}

}
