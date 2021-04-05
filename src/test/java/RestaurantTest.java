import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setup(){
        // added common code as a setup method and added @BeforeEach annotation
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime currTime = LocalTime.parse("21:00:00"); // setting current time in between the range of open/closing time
        Restaurant spyRestaurant=Mockito.spy(restaurant); // created spy object of Restaurant class
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(currTime); // returning currTime value when getCurrentTime() is called

        assertEquals(true,spyRestaurant.isRestaurantOpen());
        //asserting the result to be true as currTime is valid time
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime currTime = LocalTime.parse("23:00:00");// setting current time outside the range of open/closing time
        Restaurant spyRestaurant=Mockito.spy(restaurant);// created spy object of Restaurant class
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(currTime);// returning currTime value when getCurrentTime() is called

        assertEquals(false,spyRestaurant.isRestaurantOpen());
        //asserting the result to be false as currTime is invalid time
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void on_select_items_from_the_menu_system_should_show_the_total_cost_of_the_order() {
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Chandra's Item", 3);
       // added 3 items in the menu and calling costOfSelectedItems method to check the order cost
        assertEquals(391,restaurant.costOfSelectedItems("Sweet corn soup", "Vegetable lasagne","Chandra's Item"));
        //total items cost 119+269+3 =391
    }
}