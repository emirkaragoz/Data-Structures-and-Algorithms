import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class hotelGuestsTest {

    hotelGuests hotelGuest1 = new hotelGuests("Mustafa Fatih Tunalı","65421832421",15, hotel.Type.Deluxe_Single,"21.06.2018");

    receptionist receptionistTest;

    @BeforeEach
    void setup() throws IOException {
        FileWriter fw = new FileWriter("hotelRecords.csv",false);
        fw.close();
        receptionistTest = null;
        System.gc();
    }

    @Test
    void book() throws IOException {
        boolean expected=true;
        boolean actual = false;

        receptionistTest = new receptionist(hotelGuest1,"RoyalHotel34");

        actual = receptionistTest.book();
        assertEquals(expected,actual);
    }

    @Test
    void cancel() throws IOException {
        boolean expected=true;
        boolean actual = true;

        receptionistTest = new receptionist(hotelGuest1,"RoyalHotel34");

        receptionistTest.book();
        actual = receptionistTest.cancel(hotelGuest1.getGuestRoomNo());
        assertEquals(expected,actual);
    }
}