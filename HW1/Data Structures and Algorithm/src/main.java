import java.io.IOException;
import java.security.InvalidParameterException;

/**
 * Main Class
 */
public class main {
    /**
     * Main Method (General Scenario)
     * @param args command line arguments
     */
    public static void main(String ... args){
        try {
            System.out.println("---------------------------------------------------");
            System.out.println("Book - Check In - Cancel(Try but can't) - Check out\n");
            {
                SystemUsers user1 = new hotelGuests("Emir Karagoz", "37102751028", 5, hotel.Type.Deluxe_Single,"25.12.2018");
                user1.book();     //polymorphic call

                hotelGuests hotelGuest1 = (hotelGuests) user1;    //downcast

                receptionist receptionist1 = new receptionist(hotelGuest1,"RoyalHotel34");

                receptionist1.checkIn(hotelGuest1.getGuestRoomNo());
                hotelGuest1.cancel(hotelGuest1.getGuestRoomNo());      //can't leave from hotel without paying

                receptionist1.checkOut(hotelGuest1.getGuestRoomNo());
            }
            System.out.println("---------------------------------------------------");
            System.out.println("Book - Check Out(Try but can't) - Check In - Check out\n");
            {
                hotelGuests hotelGuest2 = new hotelGuests("Fatma Nur Esirci","54215632547",3, hotel.Type.Standard_Single,"01.04.2018");
                SystemUsers user2 = new receptionist(hotelGuest2,"RoyalHotel34");

                user2.book(); //polymorphic call

                receptionist receptionist2 = (receptionist) user2;  //downcast

                receptionist2.checkOut(hotelGuest2.getGuestRoomNo());  //can't check out from the unchecked in room.
                receptionist2.checkIn(hotelGuest2.getGuestRoomNo());
                receptionist2.checkOut(hotelGuest2.getGuestRoomNo());
            }
            System.out.println("----------------------------------------");
            System.out.println("Book - Check In\n");
            {
                SystemUsers user3 = new hotelGuests("Mustafa Fatih Tunali", "97468921430", 10, hotel.Type.Deluxe_Double,"30.12.2018");

                user3.book();

                hotelGuests hotelGuest3 = (hotelGuests) user3;  //downcast

                receptionist receptionist3 = new receptionist(hotelGuest3,"RoyalHotel34");

                receptionist3.checkIn(hotelGuest3.getGuestRoomNo());
            }
        }

        catch (IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        catch (InvalidParameterException ipe){
            System.out.println(ipe.getMessage());
            System.exit(1);
        }
    }
}
