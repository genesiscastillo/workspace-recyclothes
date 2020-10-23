package cl.ccastillo.recyclothes.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.http.MediaType;

import cl.ccastillo.recyclothes.model.Categoria;
import cl.ccastillo.recyclothes.model.Genero;
import cl.ccastillo.recyclothes.model.Recurso;
import cl.ccastillo.recyclothes.model.Talla;

public class RecursoRestClient {

	public static Recurso getRecurso() throws Exception {
//		Recurso recurso = ClientBuilder.newClient()
//				.target(URI.create(new URL("http://localhost:8081/api/recurso").toExternalForm())).request()
//				.get(Recurso.class);
//		return recurso;
		return null;
	}

//	public static void main(String[] args) throws Exception {
//		MultivaluedMap<String, Object> body = new MultivaluedMap<String, Object>();
////		body.add("files", new File("C:\\Users\\ccastillo\\Pictures\\CESAR_45.jpg"));
////		body.add("files", new File("C:\\Users\\ccastillo\\Pictures\\CESAR_46.jpg"));
////		body.add("files", new File("C:\\Users\\ccastillo\\Pictures\\CESAR_47.jpg"));
////		body.add("files", new File("C:\\Users\\ccastillo\\Pictures\\CESAR_48.jpg"));
////		body.add("files", new File("C:\\Users\\ccastillo\\Pictures\\CESAR_49.jpg"));
//		body.add("genero", Genero.NINO.name());
//		body.add("talla", Talla.TALLA_1.name());
//		body.add("categoria", Categoria.TODO.name());
//
////		File file1 = new File("C:/Users/ccastillo/Pictures/CESAR_45.jpeg");
////		File file2 = new File("C:/Users/ccastillo/Pictures/CESAR_46.jpeg");
////		File file3 = new File("C:/Users/ccastillo/Pictures/CESAR_47.jpeg");
////		File file4 = new File("C:/Users/ccastillo/Pictures/CESAR_48.jpeg");
////		File file5 = new File("C:/Users/ccastillo/Pictures/CESAR_49.jpeg");
//		
//
//		Client client = ClientBuilder.newClient();
//		WebTarget myResource = client.target("http://example.com/webapi/read");
//		String response = myResource.request(MediaType.MULTIPART_FORM_DATA_VALUE)
//				.post(Entity.form(body));
//				
//		        
//		        .get(String.class);
//	}

}
