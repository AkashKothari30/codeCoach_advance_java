package myCollections;
public class AddressMaster {
    private String country;
    private String state ;
    private String pincode;
    private String street;

    public AddressMaster (String country, String state,String pincode,String street){
        this.country = country;
        this.state = state;
        this.pincode = pincode;
        this.street = street;

    }
    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }


    public String getFullAddress() {
        return street + ", " + state + ", " + pincode + ", " + country;
    }
    public String toString(){
        return street + ", " + state + ", " + pincode + ", " + country;
        
    }
}
