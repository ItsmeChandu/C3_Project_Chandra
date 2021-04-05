import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currenTime=getCurrentTime(); //setting currentTime
       // System.out.println(currenTime);
        //currentTime is after openingTime & checking if closingTime is after currentTime
        //i.e   openingtime>currentTime>closing Time
        if ((currenTime.isAfter(openingTime) || currenTime.equals(openingTime) ) && closingTime.isAfter(currenTime))
        {

            return true;
        }
        else{
            return false;
        }

    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return Collections.unmodifiableList(menu);
        //returning the menu
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public int costOfSelectedItems(String... itemNames){
        int cost=0;
        //System.out.println("length of list: "+itemNames.length);
        if(itemNames.length==0){  // if there is empty list of selected items then the cost should be 0
            return 0;
        }
        else {
            for (String itemName : itemNames) {
                //looping through the list of itemNames and checking
                // if the name is empty string " " the cost 0 is added to prev cost
                if (itemName.equals("")) cost+= 0;
                else {
                    Item item1 = findItemByName(itemName); // finding the item by itemName and getting its cost
                    cost += item1.getPrice(); // assigning the cost
                }
            }
        }
        return cost; // returning the final cost of selected items
    }


}
