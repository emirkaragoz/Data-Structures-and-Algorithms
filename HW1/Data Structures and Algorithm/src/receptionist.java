import java.io.*;
import java.security.InvalidParameterException;

/**
 * Receptionist Class
 */
public class receptionist extends SystemUsers {
    /**
     * Hotel Guest Object
     */
    private hotelGuests hotelGuest;
    /**
     * fixed receptionist password
     */
    private String password = "RoyalHotel34";

    /**
     * Constructor
     * @param hg    Hotel Guest Object
     * @param receptionistPassword  password
     */
    public receptionist(hotelGuests hg,String receptionistPassword){
        super("Peter Thomson","12345678900");   //Hotel's fixed receptionist
        hotelGuest = hg;
        if (!receptionistPassword.equals(password)){
            throw new InvalidParameterException("Invalid Password");
        }
    }

    /**
     * books a room
     * @return  true or false
     * @throws IOException  read/write file process possible crush cases
     */
    @Override
    public boolean book() throws IOException {
        hotel.Type roomType = hotelGuest.getGuestsRoomType();

        if(checkUsabilityViaRoomType(roomType)) {
            int no = randomRoomNo(roomType);
            hotelGuest.setGuestRoomNo(no);

            hotelGuest.setGuestsRoomStatus(hotel.Status.Reserved);

            hotel h = new hotel();
            hotel.rooms room = h.new rooms(hotelGuest);

            writeCSV(room);

            System.out.printf("Registration is completed by receptionist. Your room number is %d\n", no);
            System.out.printf("Entry: %s  -  Exit: %s\n", hotelGuest.getEntryData(), calculateExitData(hotelGuest.getEntryData(), hotelGuest.getTime()));
            return true;
        }
        return false;
    }

    /**
     * cancels a reserved room
     * @param roomNoCancel    Room No to cancel
     * @return  true or false
     * @throws IOException  read/write file process possible crush cases
     */
    @Override
    public boolean cancel(int roomNoCancel) throws IOException {
        Integer no = new Integer(roomNoCancel);

        if (userInfoCheck(hotelGuest,no.toString())) {
            int alertMessage = deleteFromCSV(no.toString(), "cancel");
            if (alertMessage == 2) {
                System.out.println("Cancellation is completed by receptionist.");
                return true;
            } else if (alertMessage == 1) {
                System.out.println("You can't cancel a checked in room. You can check out.");
                return false;
            } else {
                System.out.println("You don't have a reserved room.");
                return false;
            }
        }
        else{
            System.out.printf("Room %d is not yours.\n",no);
            return false;
        }
    }

    /**
     * Checks In
     * @param roomNoCheckIn   Room Information to Check In
     * @throws IOException  read/write file process possible crush cases
     */
    boolean checkIn(int roomNoCheckIn) throws IOException{   //package-private
        Integer no = new Integer(roomNoCheckIn);

        if (userInfoCheck(hotelGuest,no.toString())) {
            if (hotelGuest.getGuestsRoomStatus() == hotel.Status.Reserved) {
                hotelGuest.setGuestsRoomStatus(hotel.Status.Full);
                File oldRecords = new File("hotelRecords.csv");
                File newRecords = new File("tempHotelRecords.csv");

                BufferedReader br = new BufferedReader(new FileReader(oldRecords));
                BufferedWriter bw = new BufferedWriter(new FileWriter(newRecords));

                String oneLine = "";
                String roomNo = "";
                String full = "";
                while ((oneLine = br.readLine()) != null && oneLine.length() != 0) {
                    int i = 0;
                    while (i < 3) {
                        roomNo += oneLine.charAt(i);
                        ++i;
                    }
                    if (!roomNo.equals(Integer.toString(hotelGuest.getGuestRoomNo())))
                        bw.write(oneLine + "\n");
                    else {
                        full += new Integer(hotelGuest.getGuestRoomNo()).toString() + "," +
                                hotelGuest.getId() + "," +
                                hotelGuest.getName() + "," +
                                (hotelGuest.getGuestsRoomType()).toString() + "," +
                                (hotelGuest.getGuestsRoomStatus()).toString() + "," +
                                new Integer(hotelGuest.getTime()).toString() + "\n";
                        bw.write(full);
                    }
                    roomNo = "";
                }
                bw.flush();
                bw.close();
                br.close();
                System.gc();

                oldRecords.delete();
                newRecords.renameTo(oldRecords);
                System.out.printf("Checked in. Your room number is %d\n", hotelGuest.getGuestRoomNo());
                return true;
            } else if (hotelGuest.getGuestsRoomStatus() == hotel.Status.Full)
                System.out.println("This room is Full");
            else
                System.out.println("You should book before check in.");
            return false;
        }
        else{
            System.out.printf("Room %d is not yours.\n",no);
            return false;
        }
    }

    /**
     * Checks Out
     * @param roomNoCheckOut  Room Information to Check out
     * @return bill amount
     * @throws IOException  read/write file process possible crush cases
     */
    int checkOut(int roomNoCheckOut) throws IOException {     //package-private
        Integer no = new Integer(roomNoCheckOut);
        int bill = 0;

        if (userInfoCheck(hotelGuest,no.toString())) {
            if (hotelGuest.getGuestsRoomStatus() == hotel.Status.Full) {
                if (hotelGuest.getGuestsRoomType() == hotel.Type.Deluxe_Double) {
                    bill = 500 * hotelGuest.getTime();
                } else if (hotelGuest.getGuestsRoomType() == hotel.Type.Deluxe_Single) {
                    bill = 400 * hotelGuest.getTime();
                } else if (hotelGuest.getGuestsRoomType() == hotel.Type.Standard_Single) {
                    bill = 200 * hotelGuest.getTime();
                } else if (hotelGuest.getGuestsRoomType() == hotel.Type.Standard_Double) {
                    bill = 300 * hotelGuest.getTime();
                }

                deleteFromCSV(no.toString(), "checkout");
                System.out.printf("Checked out. Your bill is $%d \n", bill);
            } else
                System.out.println("This room is not in use");
        }
        else {
            System.out.printf("Room %d is not yours.\n",no);
        }
        return bill;
    }

    /**
     * toString
     * @return formatted string
     */
    @Override
    public String toString() {
        String s=name+"\n"+id;
        return s;
    }

    /**
     * equals
     * @param obj   object to be compared
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof receptionist){
            receptionist r = (receptionist) obj;
            if ((r.toString()).equals(toString()))
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
