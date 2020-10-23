package cl.ccastillo.recyclothes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import cl.ccastillo.recyclothes.model.Recurso;
import cl.ccastillo.recyclothes.model.Talla;
import cl.ccastillo.recyclothes.services.RecursoRestClient;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("Hello World Enterprise Application Client!");
		System.out.println(args.length);

		if (args.length > 0) {
			String dir = args[0];
			System.out.println("HOLA " + dir);
			File folderes = Paths.get(dir).toFile(); 
			if (folderes.exists()) {
				showFiles(folderes.listFiles());
			} else {
				createDirectorio(Paths.get(dir));
			}
		}

	}

	public static void showFiles(File[] files) {
	    for (File file : files) {
	        if (file.isDirectory()) {
	            System.out.println("Directory: " + file.getName());
	            System.out.println("Total    : "+file.listFiles().length); // Calls same method again.
	        } else {
	            System.out.println("File: " + file.getName());
	        }
	    }
	}
	public static void createDirectorio(Path dir) throws Exception {
		Recurso recurso = RecursoRestClient.getRecurso();

		for (Talla talla : recurso.talla) {
			System.out.println(talla);
			Path folder = Paths.get(dir.toString(), talla.name());
			Files.createDirectories(folder);
		}

	}
}
