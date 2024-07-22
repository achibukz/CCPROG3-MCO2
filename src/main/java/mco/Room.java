package mco;

/**
 * The Room class represents a room in a hotel.
 */
public class Room {

    private String roomID;
    private boolean[] avail = new boolean[31]; // true: Available, false: Booked
    private int[] state = new int[31]; // 0: Neither CheckIn nor CheckOut, 1: CheckIn, 2: CheckOut
    protected double Price;

    /**
     * Constructs a Room object with the given room ID.
     *
     * @param roomID the ID of the room
     */
    public Room(String roomID, double basePrice) {
        this.roomID = roomID;
        this.Price = basePrice;
        setAvail();
        setState();
    }

    public Room(String roomID) {
        this.roomID = roomID;
        setAvail();
        setState();
    }

    /**
     * Sets all the availability of the room to true.
     */
    public void setAvail(){
        for (int i = 0; i < 31; i++){
            avail[i] = true;
        }
    }

    /**
     * Sets the state of all the dates to 0 (Neither CheckIn nor Check Out).
     */
    public void setState(){
        for (int i = 0; i < 31; i++){
            state[i] = 0;
        }
    }

    /**
     * Books the room for the specified check-in and check-out dates.
     *
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     */
    public void bookRoom(int checkInDate, int checkOutDate){
        for (int i = checkInDate - 1; i < checkOutDate; i++){
            avail[i] = false;
        }

        state[checkInDate - 1] = 1;
        state[checkOutDate - 1] = 2;
    }

    public void updPrice(double basePrice){
        this.Price = basePrice;
    }

    public double getPrice(){
        return Price;
    }

    /**
     * Returns the ID of the room.
     *
     * @return roomID - the room ID
     */
    public String getRoomID(){
        return roomID;
    }

    /**
     * Returns the availability status of the room for the specified date.
     *
     * @param date the date to check availability for
     * @return "Available" if the room is available, "Booked" otherwise
     */
    public String getAvail(int date){
        if (avail[date - 1]){
            return "Available";
        } else {
            return "Booked";
        }
    }

    /**
     * Checks if the room is available for the specified date.
     *
     * @param date the date to check availability for
     * @return true if the room is available, false otherwise
     */
    public boolean isAvail(int date){
        return avail[date - 1];
    }

    /**
     * Checks if the room is booked for any date.
     *
     * @return true if the room is booked, false otherwise
     */
    public boolean isBook(){
        for (int i = 0; i < 31; i++){
            if (!avail[i]){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the availability of the room for all dates.
     *
     * @return availStr - a string representation of the availability of the room
     */
    public String getAllAvail(){
        String availStr = "";
        for (int i = 0; i < 31; i++){
            if (avail[i]){
                availStr += "       Date " + (i + 1) + ": Available \n";
            } else {
                availStr += "       Date " + (i + 1) + ": Booked \n";
            }
        }

        return availStr;
    }

    /**
     * Returns the state of the room for the specified date.
     *
     * @param date the date to check the state for
     * @return 0 if the room is not booked, 1 if the room is booked for check-in, 2 if the room is booked for check-out
     */
    public int getState(int date){
        return state[date - 1];
    }


}
