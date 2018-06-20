import java.io.*;
import java.security.InvalidParameterException;

/**
 * Hotel Guest Class
 */
public class hotelGuests extends SystemUsers {
    /**
     * Guest's room type
     */
    private hotel.Type guestsRoomType;
    /**
     * Guest's room status
     */
    private hotel.Status guestsRoomStatus;
    /**
     * Guest's duration of stay on hotel
     */
    private int time;
    /**
     * Guest's room no
     */
    private int guestRoomNo;
    /**
     * Guest's entry data
     */
    private String entryData;

    /**
     * Constructor
     * @param guestName name
     * @param guestId   ID
     * @param guestTime duration of stay on hotel
     * @param rt        room type
     * @param guestEntryData    entry data
     */
    public hotelGuests(String guestName,String guestId,int guestTime,hotel.Type rt, String guestEntryData){
        super(guestName,guestId);
        time = guestTime;
        guestsRoomType = rt;
        if (guestEntryData.length() != 10)
            throw new InvalidParameterException("Please enter a correct date in this format (01.01.2018)");
        entryData = guestEntryData;
    }

    /**
     * Time Getter
     * @return  time
     */
    public int getTime(){
        return time;
    }

    /**
     * Entry data getter
     * @return  entry data
     */
    public String getEntryData() {
        return entryData;
    }

    /**
     * Guest's Room Type Getter
     * @return room type
     */
    public hotel.Type getGuestsRoomType(){
        return guestsRoomType;
    }

    /**
     * Guest's room status getter
     * @return  room status
     */
    public hotel.Status getGuestsRoomStatus(){
        return guestsRoomStatus;
    }

    /**
     * Guest's Room No getter
     * @return   room no
     */
    public int getGuestRoomNo(){
        return guestRoomNo;
    }

    /**
     * Guest's room no setter
     * @param no room no
     */
    void setGuestRoomNo(int no){    //package-private
        guestRoomNo = no;
    }

    /**
     * Guest's room status setter
     * @param s room status
     */
    void setGuestsRoomStatus(hotel.Status s){   //package-private
        guestsRoomStatus = s;
    }

    /**
     * books a room
     * @return  true or false
     * @throws IOException  read/write file process possible crush cases
     */
    @Override
    public boolean book() throws IOException {
        hotel.Type roomType = getGuestsRoomType();

        if(checkUsabilityViaRoomType(roomType)) {
            int no = randomRoomNo(roomType);
            guestRoomNo = no;

            guestsRoomStatus = hotel.Status.Reserved;

            hotel h = new hotel();
            hotel.rooms room = h.new rooms(this);

            writeCSV(room);

            System.out.printf("Registration is completed. Your room number is %d\n", no);
            System.out.printf("Entry: %s  -  Exit: %s\n", entryData, calculateExitData(entryData, time));
            return true;
        }
        return false;
    }

    /**
     * cancels a reserved room
     * @param roomNo    Room No to cancel
     * @return  true or false
     * @throws IOException  read/write file process possible crush cases
     */
    @Override
    public boolean cancel(int roomNo) throws IOException {
        Integer no = new Integer(roomNo);
        if (userInfoCheck(this,no.toString())) {
            int alertMessage = deleteFromCSV(no.toString(), "cancel");
            if (alertMessage == 2) {
                System.out.println("Cancellation is completed.");
                return true;
            } else if (alertMessage == 1) {
                System.out.println("You can't cancel a checked in room. You can check out.");
                return false;
            } else {
                System.out.println("You don't have a reserved room. ");
                return false;
            }
        }
        else{
            System.out.printf("Room %d is not yours.",no);
            return false;
        }
    }

    /**
     * toString
     * @return formatted string
     */
    @Override
    public String toString() {
        String s=id+" "+name+" "+Integer.toString(guestRoomNo)+"\n"+guestsRoomType.toString()+" "+entryData;
        return s;
    }

    /**
     * equals
     * @param obj   object to be compared
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof hotelGuests){
            hotelGuests hg = (hotelGuests) obj;
            if((hg.getName()).equals(name) && (hg.getId()).equals(id) && (hg.getGuestsRoomStatus()).equals(guestsRoomType)
                && (hg.getGuestsRoomType()).equals(guestsRoomType) && (hg.getTime() == time) && (hg.getGuestRoomNo()==guestRoomNo)
                && (hg.getEntryData()).equals(entryData))
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
