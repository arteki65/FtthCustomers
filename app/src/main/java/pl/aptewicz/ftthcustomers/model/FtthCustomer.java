package pl.aptewicz.ftthcustomers.model;

import java.io.Serializable;
import java.util.Collection;

public class FtthCustomer implements Serializable {

	public static final String FTTH_CUSTOMER = "pl.aptewicz.ftthcustomers.model.FtthCustomer";

	private Long id;

	private String username;

	private String password;

	private Collection<FtthIssue> ftthIssues;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<FtthIssue> getFtthIssues() {
		return ftthIssues;
	}

	public void setFtthIssues(Collection<FtthIssue> ftthIssues) {
		this.ftthIssues = ftthIssues;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
