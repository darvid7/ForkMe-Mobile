package dlei.forkme.model;

/**
 * Data model class for location data to be posted.
 */
public class LocationData {

    private Double latitude;
    private Double longitude;
    private Integer id;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getlongitude() {
        return longitude;
    }

    public void setlongitude(Double _longitude) {
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

     public void setId(Integer id) {
        this.id = id;
    }
}