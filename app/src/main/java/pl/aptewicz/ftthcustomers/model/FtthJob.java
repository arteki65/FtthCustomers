package pl.aptewicz.ftthcustomers.model;

import java.io.Serializable;

public class FtthJob implements Serializable {

	private Long id;

	private String description;

	private FtthJobStatus jobStatus;

	private String servicemanUsername;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FtthJobStatus getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(FtthJobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getServicemanUsername() {
		return servicemanUsername;
	}

	public void setServicemanUsername(String servicemanUsername) {
		this.servicemanUsername = servicemanUsername;
	}
}
