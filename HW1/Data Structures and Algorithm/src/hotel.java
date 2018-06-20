/**
 * Hotel Class
 */
public class hotel {
    /**
     * Room Status Enum
     */
    enum Status {   //package-private
        Reserved,
        Full,
        Empty
    }

    /**
     * Room Type Enum
     */
    enum Type {     //package-private
        Deluxe_Single,
        Standard_Single,
        Deluxe_Double,
        Standard_Double
    }

    /**
     * Room Package-Private Inner Class
     */
    class rooms {   //package-private
        /**
         * Hotel Guests Object
         */
        private hotelGuests guest;

        /**
         * Constructor
         * @param hg    hotelGuests object
         */
        public rooms(hotelGuests hg){
            guest = hg;
        }

        /**
         * hotel guest getter
         * @return  hotelGuests object
         */
        public hotelGuests getGuest(){
            return guest;
        }

        /**
         * toString
         * @return formatted string
         */
        @Override
        public String toString() {
            return (getGuest().toString());
        }

        /**
         * equals
         * @param obj   object to be compared
         * @return true or false
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof rooms){
                rooms r = (rooms) obj;
                if (r.getGuest().equals(getGuest()))
                    return true;
                else
                    return false;
            }
            else
                return false;
        }
    }
}
