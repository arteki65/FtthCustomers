package pl.aptewicz.ftthcustomers.model;

import java.io.Serializable;

public class FtthIssue implements Serializable {

    private Long id;

    private String description;

    private double longitude;

    private double latitude;

    private FtthJob ftthJob;

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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public FtthJob getFtthJob() {
        return ftthJob;
    }

    public void setFtthJob(FtthJob ftthJob) {
        this.ftthJob = ftthJob;
    }

    @Override
    public String toString() {
        String toString = "Numer zlecenia : " + id + "\n" +
                "Opis zlecenia : " + description + "\n" +
                "Długość geograficzna : " + longitude + "\n" +
                "Szerokość geograficzna : " + latitude;
        if (ftthJob != null) {
            toString = toString + "\nStatus zlecenia : " + ftthJob.getJobStatus();
        }
        return toString;
    }
}
