import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class receptionistTest {

    hotelGuests hotelGuest1 = new hotelGuests("Erdogan Sevilgen","65421587421",5, hotel.Type.Standard_Single,"12.05.2018");
    hotelGuests hotelGuest2 = new hotelGuests("Yusuf Sinan Akgul","54128632014",3, hotel.Type.Deluxe_Single,"13.04.2018");

    receptionist receptionistTest;

    @BeforeEach
    void setup() throws IOException {
        FileWriter fw = new FileWriter("hotelRecords.csv",false);
        fw.close();
        receptionistTest = null;
        System.gc();
    }

    @Test
    void testBook() throws IOException {
        boolean expected=true;
        boolean actual = false;

        receptionistTest = new receptionist(hotelGuest1,"RoyalHotel34");

        //case 1 true
        actual = receptionistTest.book();
        assertEquals(expected,actual);

        //false döndürebilmesi için seçilen oda türünde müsait hiç odanın bulmaması gerekir
        //deluxe oda türü için 30, standard oda türü içinse 120 oda bulunmaktadir

        //case 2 false
        expected = false;
        receptionistTest = null;
        receptionistTest = new receptionist(hotelGuest2,"RoyalHotel34");
        int i = 0;
        while (i < 30){                 //30 odayıda doldurup book yapmaya çalıştığımda false döndürcek
            receptionistTest.book();
            ++i;
        }
        actual = receptionistTest.book();
        assertEquals(expected,actual);
    }

    @Test
    void testCancel() throws IOException {
        boolean expected=true;
        boolean actual = true;

        receptionistTest = new receptionist(hotelGuest1,"RoyalHotel34");

        //case 1 true
        receptionistTest.book();
        actual = receptionistTest.cancel(hotelGuest1.getGuestRoomNo());
        assertEquals(expected,actual);

        //case 2 false
        expected = false;
        actual = receptionistTest.cancel(hotelGuest1.getGuestRoomNo());
        assertEquals(expected,actual);
    }

    @Test
    void checkIn() throws IOException {
        boolean expected=true;
        boolean actual = false;

        receptionistTest = new receptionist(hotelGuest2,"RoyalHotel34");

        //case 1 true
        receptionistTest.book();
        actual = receptionistTest.checkIn(hotelGuest2.getGuestRoomNo());
        assertEquals(expected,actual);

        //case 2 false
        expected = false;
        actual = receptionistTest.checkIn(hotelGuest2.getGuestRoomNo());
        assertEquals(expected,actual);
    }

    @Test
    void checkOut() throws IOException {
        int expected = 1200;    //single deluxe room price is $400 per day, 3 days $1200 expected bill
        int actual = 0;

        //case 1 true
        receptionistTest = new receptionist(hotelGuest2,"RoyalHotel34");

        receptionistTest.book();
        receptionistTest.checkIn(hotelGuest2.getGuestRoomNo());
        actual = receptionistTest.checkOut(hotelGuest2.getGuestRoomNo());
        assertEquals(expected,actual);
    }
}