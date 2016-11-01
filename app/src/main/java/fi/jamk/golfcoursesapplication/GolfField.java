package fi.jamk.golfcoursesapplication;

/**
 * Created by Pedo on 1.11.2016.
 */
public class GolfField {
    protected String type;
    protected String lat;
    protected String lng;
    protected String name;
    protected String phone;
    protected String email;
    protected String address;
    protected String imageUrl;
    protected String description;
    protected String url;

    public GolfField(String type, String lat, String lng, String name, String phone, String email, String address, String imageUrl, String description, String url) {
        this.type = type;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.imageUrl = imageUrl;
        this.description = description;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
