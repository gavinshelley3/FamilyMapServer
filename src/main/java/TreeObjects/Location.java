package TreeObjects;

public class Location {
    private String country;
    private String city;
    private float latitude;
    private float longitude;

    public Location() {}

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String toString() {
        return "Location: " + country + ", " + city + ", " + latitude + ", " + longitude;
    }


}
