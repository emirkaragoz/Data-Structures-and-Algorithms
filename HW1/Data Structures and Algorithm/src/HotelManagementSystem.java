import java.io.IOException;

/**
 * Hotel Management System Interface
 */
public interface HotelManagementSystem {
    /**
     *
     * @return  True or False
     * @throws IOException  read/write file process possible crush cases
     */
    boolean book() throws IOException;

    /**
     *
     * @param roomNo    Room No to cancel
     * @return  True or False
     * @throws IOException  read/write file process possible crush cases
     */
    boolean cancel(int roomNo) throws IOException;
}
