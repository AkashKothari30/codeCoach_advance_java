package myCollections;

    import java.util.ArrayList;
public class TestStarWarDemo {



    public static void main(String[] args) {

        ArrayList<AddressMaster> list = new ArrayList<>();
        setupDummyData(list);

        System.out.println("All Addresses:");
        for (AddressMaster addr : list) {
            System.out.println(addr);
        }

        System.out.println("Unique Countries:");
        System.out.println(AddressUtil.listUniqueCountries(list));

        System.out.println("Unique States:");
        System.out.println(AddressUtil.listUniqueStates(list));

    
        System.out.println("Deleting first address...");
        list.remove(0);

        System.out.println("Unique Countries After Delete:");
        System.out.println(AddressUtil.listUniqueCountries(list));
    }

    public static void setupDummyData(ArrayList<AddressMaster> list) {
        list.add(new AddressMaster("India", "Uttarakhand", "248001", "Rajpur Road"));
        list.add(new AddressMaster("India", "Delhi", "110001", "CP Area"));
        list.add(new AddressMaster("USA", "California", "90001", "LA Street"));
        list.add(new AddressMaster("USA", "Texas", "73301", "Austin Road"));
        list.add(new AddressMaster("India", "Uttarakhand", "248002", "ISBT Road")); 
    }
}


