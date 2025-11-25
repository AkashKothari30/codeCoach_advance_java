package myCollections;

import java.util.ArrayList;
import java.util.HashSet;

public class AddressUtil {
    static HashSet<String> listUniqueCountries(ArrayList<AddressMaster> list) {
    HashSet<String> set = new HashSet<>();

        for(AddressMaster address : list){
            set.add(address.getCountry());
        }
        return set;
    }
    public static HashSet<String> listUniqueStates(ArrayList<AddressMaster> list) {
        HashSet<String> set = new HashSet<>();
        for (AddressMaster address : list) {
            set.add(address.getState());
        }
        return set;
    }
}
