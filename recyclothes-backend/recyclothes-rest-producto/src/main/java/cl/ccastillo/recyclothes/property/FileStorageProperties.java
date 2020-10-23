package cl.ccastillo.recyclothes.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file.upload.dir")
public class FileStorageProperties {
	
	private String pendiente;
	
	public String getPendiente() {
		return pendiente;
	}
	public void setPendiente(String pendiente) {
		this.pendiente = pendiente;
	}
	
	

}
