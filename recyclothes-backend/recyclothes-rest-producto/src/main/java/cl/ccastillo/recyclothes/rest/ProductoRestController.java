package cl.ccastillo.recyclothes.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cl.ccastillo.recyclothes.model.Categoria;
import cl.ccastillo.recyclothes.model.Estado;
import cl.ccastillo.recyclothes.model.Foto;
import cl.ccastillo.recyclothes.model.Genero;
import cl.ccastillo.recyclothes.model.Producto;
import cl.ccastillo.recyclothes.model.Talla;
import cl.ccastillo.recyclothes.model.UploadFileResponse;
import cl.ccastillo.recyclothes.service.impl.FileStorageService;
import cl.ccastillo.recyclothes.service.impl.ProductoServiceImpl;

@RestController
@CrossOrigin
public class ProductoRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductoRestController.class);

	@Autowired
	FileStorageService fileStorageService;

	@Autowired
	ProductoServiceImpl productoService;
	
	@DeleteMapping("/api/producto/{id}")
	public ResponseEntity<Long> deleteProducto( @PathVariable("id") Long id){
		productoService.eliminarProducto(id);
		return new ResponseEntity<>(id , HttpStatus.OK);
	}

	@PutMapping("/api/producto/{id}")
	public @ResponseBody Producto actualizarEstado(@PathVariable("id") Long id , @RequestBody Producto producto){
		productoService.actualizarProducto( producto );
		return producto; 
	}

	@GetMapping("/api/producto/{id}")
	public ResponseEntity<Producto> obtenerProducto(@PathVariable("id") Long id) {
		Optional<Producto> opt =  productoService.obtenerProducto(id);
		return opt.isPresent() ? ResponseEntity.ok().body(opt.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping("/api/producto/solicitarCatalogo")
	public @ResponseBody List<Producto> solicitarCatalogo(
			@RequestParam("genero") Genero genero , 
			@RequestParam("talla") Talla talla, 
			@RequestParam("categoria") Categoria categoria , 
			@RequestParam("estado") Estado estado) {
		List<Producto> productos = new ArrayList<Producto>();
		productos = productoService.solicitarCatalogo(genero, categoria, talla, estado);
		return productos;
	}
		
	@PostMapping("/api/producto/uploadFile")
	public @ResponseBody UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("genero") Genero genero, @RequestParam("categoria") Categoria categoria,
			@RequestParam("talla") Talla talla) {

		String fileName = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/producto/downloadFile/").path(fileName).toUriString();
		Foto foto = new Foto(fileName, fileDownloadUri);
		productoService.save(foto);
		
		Producto producto = new Producto(genero, talla, categoria, foto);
		productoService.save(producto);

		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize(), producto);
	}

	@PostMapping("/api/producto/uploadMultipleFiles")
	public @ResponseBody List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
			@RequestParam("genero") String sgenero, @RequestParam("categoria") String scategoria,
			@RequestParam("talla") String stalla) {

			Genero genero = Genero.valueOf(sgenero);
			Categoria categoria = Categoria.valueOf(scategoria);
			Talla talla = Talla.valueOf(stalla);
		
		return Arrays.asList(files).stream().map(file -> uploadFile(file, genero, categoria, talla))
				.collect(Collectors.toList());
	}

	@GetMapping("/api/producto/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		String contentType = null;
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());	
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		if (contentType == null) {
			System.out.println("ES NULLO");
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}