package mco;

/**
 * The Reservation class represents a reservation made by a guest for a room.
 */
public class Reservation {
    
    private String guestName;
    private int checkInDate;
    private int checkOutDate;
    private double costPerNight;
    private double totalCost;
    private Room room;

    /**
     * Constructs a Reservation object with the specified guest name, check-in date, check-out date, cost per night, and room.
     * 
     * @param guestName the name of the guest making the reservation
     * @param checkInDate the check-in date of the reservation
     * @param checkOutDate the check-out date of the reservation
     * @param costPerNight the cost per night of the room
     * @param room the room being reserved
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, double costPerNight, Room room){
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.costPerNight = costPerNight;
        this.room = room;
        room.bookRoom(checkInDate, checkOutDate);
    }

    /**
     * Returns the room associated with this reservation.
     * 
     * @return room - the room associated with this reservation
     */
    public Room getRoom(){
        return room;
    }

    /**
     * Returns the name of the guest making the reservation.
     * 
     * @return guestName - the name of the guest making the reservation
     */
    public String getGuestName(){
        return guestName;
    }

    /**
     * Returns the check-in date of the reservation.
     * 
     * @return checkInDate - the check-in date of the reservation
     */
    public int getCheckInDate(){
        return checkInDate;
    }

    /**
     * Returns the check-out date of the reservation.
     * 
     * @return checkOutDate - the check-out date of the reservation
     */
    public int getCheckOutDate(){
        return checkOutDate;
    }

    /**
     * Returns the cost per night of the room.
     * 
     * @return costPerNight - the cost per night of the room
     */
    public double getCostPerNight(){
        return costPerNight;
    }

    /**
     * Returns the total cost of the reservation.
     * 
     * @return totalCost - the total cost of the reservation
     */
    public double getTotalCost(){

        totalCost = (checkOutDate - checkInDate) * costPerNight;

        return totalCost;
    }
}
