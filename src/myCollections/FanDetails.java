package myCollections;

public class FanDetails {
    private String name;
    private String country;
    private String state;
    private String pincode;
    private String phone ;


    public FanDetails (String name, String country, String state ,String pincode ,String phone){
        this.name = name;
        this.country = country;
        this.state = state;
        this.pincode = pincode;
        this.phone = phone;
    }
    public String getName(){
        return name;
    }
    public String toString(){
        return "Name: " + name + ", Country: " + country + ", State: " + state + ", Pincode: " + pincode + ", Phone: " + phone;
    }
}
