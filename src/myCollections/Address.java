package myCollections;

public class Address {
    private String country;
    private String state ;
    private String pincode;
    private String street;

    public Address (String country, String state,String pincode,String street){
        this.country = country;
        this.state = state;
        this.pincode = pincode;
        this.street = street;

    }

    public String getFullAddress() {
        return street + ", " + state + ", " + pincode + ", " + country;
    }
}
