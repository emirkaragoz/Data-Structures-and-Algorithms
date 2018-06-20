import java.io.*;
import java.security.InvalidParameterException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * System Users Abstract Class
 */
public abstract class SystemUsers implements HotelManagementSystem {
    /**
     * User ID
     */
    protected String id;
    /**
     * User Name
     */
    protected String name;

    /**
     * Constructor
     * @param userName  Input User Name
     * @param userId    Input User ID
     */
    public SystemUsers(String userName,String userId){
        for (int i = 0 ; i< userName.length() ; ++i){
            if(Character.isDigit(userName.charAt(i))){
                throw new InvalidParameterException("Name can't include number.");
            }
        }
        name = userName;

        if(userId.length() != 11)
            throw new InvalidParameterException("Please enter a correct ID number(11 Digit)");
        try{
            Long tmp = Long.parseLong(userId);
            id = tmp.toString();
        }catch (NumberFormatException nfe) {    //to specified error message
            throw new InvalidParameterException("ID must include only integers.");
        }
    }

    /**
     * Name Getter
     * @return Name
     */
    public String getName(){
        return name;
    }

    /**
     *  ID Getter
     * @return ID
     */
    public String getId(){
        return id;
    }

    /**
     * Check whether the selected room is full
     * @param roomNo Input Room No
     * @return  True or False
     * @throws IOException  read/write file process possible crush cases
     */
    protected boolean checkBusyRoom(int roomNo) throws IOException {
        String oneLine = "";
        BufferedReader br = new BufferedReader(new FileReader("hotelRecords.csv"));
        while ((oneLine = br.readLine())!=null && oneLine.length()!=0){
            int fileRoomNo = (Integer.parseInt(Character.toString(oneLine.charAt(0)))*100)+
                    (Integer.parseInt(Character.toString(oneLine.charAt(1)))*10)+
                    (Integer.parseInt(Character.toString(oneLine.charAt(2))));
            if (fileRoomNo == roomNo)
                return false;
        }
        return true;
    }

    /**
     * writes to csv file
     * @param toRecord  Room Information
     * @throws IOException  read/write file process possible crush cases
     */
    protected void writeCSV(hotel.rooms toRecord) throws IOException {
        FileWriter fw = new FileWriter("hotelRecords.csv",true);

        String record = "";
        record+= new Integer(toRecord.getGuest().getGuestRoomNo()).toString()+","+
                toRecord.getGuest().getId()+","+
                toRecord.getGuest().getName()+","+
                (toRecord.getGuest().getGuestsRoomType()).toString()+","+
                (toRecord.getGuest().getGuestsRoomStatus()).toString()+","+
                new Integer(toRecord.getGuest().getTime()).toString()+"\n";

        fw.append(record);
        fw.flush();
        fw.close();
    }

    /**
     * deletes from csv file
     * @param no    ID info
     * @param caller    who called this function (cancel or check out)
     * @return  1-full 2-reserved 0-empty
     * @throws IOException  read/write file process possible crush cases
     */
    protected int deleteFromCSV(String no, String caller) throws IOException {
        int status=0;//empty
        File oldRecords= new File("hotelRecords.csv");
        File newRecords= new File("tempHotelRecords.csv");

        BufferedReader br = new BufferedReader(new FileReader(oldRecords));
        BufferedWriter bw = new BufferedWriter(new FileWriter(newRecords));

        String oneLine = "";
        String roomNo = "";
        while ((oneLine = br.readLine()) != null && oneLine.length() != 0){
            int i=0;
            while (i<3){
                roomNo += oneLine.charAt(i);
                ++i;
            }
            if (!roomNo.equals(no))
                bw.write(oneLine+"\n");
            else if(roomNo.equals(no)  &&  (determineRoomStatus(oneLine) == hotel.Status.Full) && caller.equals("checkout")) {
                status = 1;//full
            }
            else if(roomNo.equals(no)  &&  determineRoomStatus(oneLine) == hotel.Status.Full && caller.equals("cancel")) {
                bw.write(oneLine+"\n");
                status = 1;//full
            }
            else if(roomNo.equals(no)  &&  determineRoomStatus(oneLine) == hotel.Status.Reserved && caller.equals("cancel")) {
                status = 2;//reserved
            }
            else if(roomNo.equals(no)  &&  determineRoomStatus(oneLine) == hotel.Status.Reserved && caller.equals("checkout")) {
                bw.write(oneLine+"\n");
                status = 2;//reserved
            }
            roomNo="";
        }
        bw.close();
        br.close();
        System.gc();

        oldRecords.delete();
        newRecords.renameTo(oldRecords);

        return status;
    }

    /**
     * check whether there is room available for the selected room type
     * @return 1-deluxe full 2-standard full 0-available
     * @throws IOException  read/write file process possible crush cases
     */
    protected int checkCapacity() throws IOException {
        int deluxe=0,standard=0;
        String oneLine="";

        File records= new File("hotelRecords.csv");
        BufferedReader br = new BufferedReader(new FileReader(records));

        while ((oneLine = br.readLine()) != null && oneLine.length() != 0){
            if (oneLine.charAt(0) == '7' || oneLine.charAt(0) == '8' || oneLine.charAt(0) == '9')
                ++deluxe;
            else
                ++standard;
        }
        br.close();

        if(deluxe == 30)
            return 1;//deluxe rooms full

        else if (standard == 120)
            return 2;//standard rooms full

        return 0;//hotel rooms available
    }

    /**
     * check whether hotel has a specified type room
     * @param t  room type
     * @return  true or false
     * @throws IOException  read/write file process possible crush cases
     */
    protected boolean checkUsabilityViaRoomType(hotel.Type t) throws IOException {
        switch (t) {
            case Deluxe_Single:
                if(checkCapacity() == 1) {
                    System.out.println("All our deluxe rooms are full.");
                    return false;
                }
                break;
            case Standard_Single:
                if(checkCapacity() == 2) {
                    System.out.println("All our standard rooms are full.");
                    return false;
                }
                break;
            case Deluxe_Double:
                if(checkCapacity() == 1) {
                    System.out.println("All our deluxe rooms are full.");
                    return false;
                }
                break;
            case Standard_Double:
                if(checkCapacity() == 2) {
                    System.out.println("All our standard rooms are full.");
                    return false;
                }
                break;
        }
        return true;
    }

    /**
     * sets a random number for the room
     * @param t room type
     * @return  room no
     * @throws IOException  read/write file process possible crush cases
     */
    protected int randomRoomNo(hotel.Type t) throws IOException {
        int no;
        while (true) {
            int floorSign, orderSign;
            if (t == hotel.Type.Deluxe_Double || t == hotel.Type.Deluxe_Single) {
                floorSign = ThreadLocalRandom.current().nextInt(7, 10);
                orderSign = ThreadLocalRandom.current().nextInt(1, 11);
            } else {
                floorSign = ThreadLocalRandom.current().nextInt(1, 7);
                orderSign = ThreadLocalRandom.current().nextInt(1, 21);
            }

            no = (floorSign * 100) + orderSign;
            if (checkBusyRoom(no))
                break;
        }
        return no;
    }

    /**
     * Determines selected room's type
     * @param oneLine   one line from csv record file
     * @return  Status(Empty-Full-Reserved)
     */
    protected hotel.Status determineRoomStatus(String oneLine){
        hotel.Status result;
        String type="";
        int i=0,j=0;
        while (i != 4){
            if (oneLine.charAt(j) == ',')
                ++i;
            ++j;
        }

        while (true){
            if(oneLine.charAt(j) != ',') {
                type += Character.toString(oneLine.charAt(j));
                ++j;
            }
            else
                break;
        }

        switch (type){
            case "Full":
                result = hotel.Status.Full;
                break;
            case "Reserved":
                result = hotel.Status.Reserved;
                break;
            case "Empty":
                result = hotel.Status.Empty;
                break;
            default:
                result = hotel.Status.Empty;
                break;
        }
        return result;
    }

    /**
     * Calculates exit data
     * @param guestEntryData    entry data
     * @param time      duration of stay
     * @return  exit data
     */
    protected String calculateExitData(String guestEntryData,int time){
        Integer day,month,year;
        day = (Character.getNumericValue(guestEntryData.charAt(0))*10)+Character.getNumericValue(guestEntryData.charAt(1));
        month = (Character.getNumericValue(guestEntryData.charAt(3))*10)+Character.getNumericValue(guestEntryData.charAt(4));
        year = (Character.getNumericValue(guestEntryData.charAt(6))*1000)+(Character.getNumericValue(guestEntryData.charAt(7))*100)+
                (Character.getNumericValue(guestEntryData.charAt(8))*10)+(Character.getNumericValue(guestEntryData.charAt(9)));

        day+=time;
        month+=day/30;
        day%=30;
        if (day.equals(0))
            day=1;
        year+=month/12;
        month%=12;
        if (month.equals(0))
            month = 1;
        String result = day.toString()+"."+month.toString()+"."+year.toString();
        return result;
    }

    /**
     * checks whether the room with the specified number belongs to that user.
     * @param h     user
     * @param no    room no
     * @return  true or false
     * @throws IOException  read/write file process possible crush cases
     */
    protected boolean userInfoCheck(hotelGuests h,String no) throws IOException{
        File records= new File("hotelRecords.csv");
        BufferedReader br = new BufferedReader(new FileReader(records));

        String oneLine="";
        String tmpId = "",tmpName = "";
        while ((oneLine = br.readLine()) != null && oneLine.length() != 0) {
            if (oneLine.charAt(0) == no.charAt(0) && oneLine.charAt(1) == no.charAt(1) && oneLine.charAt(2) == no.charAt(2)) {
                int i = 4;
                while (i < 15) {
                    tmpId += oneLine.charAt(i);
                    ++i;
                }
                i = 16;
                int j = h.getName().length();
                while (j > 0) {
                    tmpName += oneLine.charAt(i);
                    ++i;
                    --j;
                }
                determineRoomStatus(oneLine);
                if (h.getName().equals(tmpName) && h.getId().equals(tmpId)) {
                    h.setGuestsRoomStatus(determineRoomStatus(oneLine));
                    h.setGuestRoomNo(Integer.parseInt(no));
                    return true;
                }
            }
        }
        return false;
    }

}
