package mco;

import java.util.ArrayList;

/**
 * The Hotel class represents a hotel with rooms and reservations.
 */
public class Hotel {

    private String name;
    private int numRooms;
    private int stdCnt;
    private int delCnt;
    private int execCnt;
    private double basePrice = 1299.00;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList<Reservation> reservations = new ArrayList<Reservation>();

    /**
     * Constructs a Hotel object with the given name and number of rooms.
     *
     * @param name     the name of the hotel
     * @param numRooms the number of rooms in the hotel
     */
    public Hotel(String name, int stdCnt, int delCnt, int execCnt) {
        this.name = name;
        this.stdCnt = stdCnt;
        this.delCnt = delCnt;
        this.execCnt = execCnt;
        this.numRooms = stdCnt + delCnt + execCnt;
        createRooms(stdCnt, delCnt, execCnt);
    }

    /**
     * Updates the name of the hotel.
     *
     * @param newName the new name of the hotel
     */
    public void updName(String newName) {
        this.name = newName;
    }

    /**
     * Updates the number of rooms in the hotel.
     *
     * @param newNum the new number of rooms in the hotel
     */
    public void updNumRooms(int newNum) {
        this.numRooms = newNum;
    }

    /**
     * Updates the base price of the hotel.
     *
     * @param basePrice the new base price of the hotel
     */
    public void updBasePrice(double basePrice) {
        this.basePrice = basePrice;

        for (Room room : rooms) {
            if (!(room instanceof deluxeRm) && !(room instanceof execRm)) {
                room.updPrice(basePrice);
            } else if (room instanceof deluxeRm) {
                room.updPrice(basePrice * 1.2);
            } else if (room instanceof execRm) {
                room.updPrice(basePrice * 1.35);
            }
        }

    }

    /**
     * Creates the rooms in the hotel.
     */
    public void createRooms(int stdCnt, int delCnt, int execCnt) {

        int cnt = 0;

        for (int i = 1; i <= stdCnt; i++) {
            String roomID = String.format("%03d", ++cnt); // Increment cnt here for clarity
            Room room = new Room(roomID, basePrice);
            rooms.add(room);
        }
        
        for (int i = 1; i <= delCnt; i++) {
            String roomID = String.format("%03d", ++cnt); // Ensure cnt is incremented before use
            deluxeRm room = new deluxeRm(roomID, basePrice); // Correct class name with proper casing
            rooms.add(room);
        }
    
        for (int i = 1; i <= execCnt; i++) {
            String roomID = String.format("%03d", ++cnt); // Increment cnt before use
            execRm room = new execRm(roomID, basePrice); // Correct class name with proper casing
            rooms.add(room);
        }

    }

    /**
     * Adds a room to the hotel.
     */
    public void addRoom(int choice) {
        numRooms++;
        String roomID = String.format("%03d", numRooms);
        if (choice == 1){
            Room room = new Room(roomID, basePrice);
            rooms.add(room);
        } else if (choice == 2){
            deluxeRm room = new deluxeRm(roomID, basePrice);
            rooms.add(room);
        } else if (choice == 3){
            execRm room = new execRm(roomID, basePrice);
            rooms.add(room);
        }
    }

    /**
     * Removes a room from the hotel.
     *
     * @param roomName the name of the room to be removed
     */
    public void removeRoom(String roomName) {
        for (Room room : rooms) {
            if (room.getRoomID().equals(roomName)) {
                rooms.remove(room);
                numRooms--;
                break;
            }
        }
    }

    /**
     * Removes a reservation from the hotel.
     *
     * @param guestName the name of the guest for the reservation to be removed
     */
    public void removeReservation(String guestName) {
        for (Reservation res : reservations) {
            if (res.getGuestName().equals(guestName)) {
                reservations.remove(res);
                break;
            }
        }
    }

    /**
     * Retrieves a room object based on the room name.
     *
     * @param roomName the name of the room
     * @return the Room object with the specified room name, or null if not found
     */
    public Room crossRoom(String roomName) {
        roomName = roomName.toLowerCase();
        for (Room room : rooms) {
            if (room.getRoomID().toLowerCase().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    /**
     * Retrieves a reservation object based on the guest name.
     *
     * @param guestName the name of the guest
     * @return the Reservation object with the specified guest name, or null if not found
     */
    public Reservation crossReservation(String guestName) {
        guestName = guestName.toLowerCase();
        for (Reservation res : reservations) {
            if (res.getGuestName().toLowerCase().equals(guestName)) {
                return res;
            }
        }
        return null;
    }

    /**
     * Adds a reservation to the hotel.
     *
     * @param guestName     the name of the guest
     * @param checkInDate   the check-in date
     * @param checkOutDate  the check-out date
     * @param room          the room for the reservation
     */
    public void addReservation(String guestName, int checkInDate, int checkOutDate, Room room) {
        double costPerNight = basePrice;
        Reservation res = new Reservation(guestName, checkInDate, checkOutDate, costPerNight, room);
        reservations.add(res);
    }

    /**
     * Calculates the estimated earnings of the hotel.
     *
     * @return total - the total estimated earnings of the hotel
     */
    public double estEarn() {
        double total = 0;
        for (Reservation res : reservations) {
            total += res.getTotalCost();
        }
        return total;
    }

    /**
     * Retrieves the name of the hotel.
     *
     * @return name - the name of the hotel
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the number of rooms in the hotel.
     *
     * @return numRooms - the number of rooms in the hotel
     */
    public int getNumRooms() {
        return numRooms;
    }

    /**
     * Retrieves the base price of the hotel.
     *
     * @return basePrice - the base price of the hotel
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Retrieves the list of rooms in the hotel.
     *
     * @return rooms - the list of rooms in the hotel
     */
    public ArrayList<Room> getRooms(int i) {
        if (i == 1){
            ArrayList<Room> stdRooms = new ArrayList<Room>();
            for (Room room : rooms) {
                if (!(room instanceof deluxeRm) && !(room instanceof execRm)) {
                    stdRooms.add(room);
                }
            }
            return stdRooms;
        } else if (i == 2){
            ArrayList<Room> delRooms = new ArrayList<Room>();
            for (Room room : rooms) {
                if (room instanceof deluxeRm) {
                    delRooms.add(room);
                }
            }
            return delRooms;
        } else if (i == 3){
            ArrayList<Room> execRooms = new ArrayList<Room>();
            for (Room room : rooms) {
                if (room instanceof execRm) {
                    execRooms.add(room);
                }
            }
            return execRooms;
        }
        else{
            return rooms;
        }
    }

    /**
     * Retrieves the list of available rooms on a specific date.
     *
     * @param date the date to check for availability
     * @return availableRooms - the list of available rooms on the specified date
     */
    public ArrayList<Room> getAvailableRooms(int date) {
        ArrayList<Room> availableRooms = new ArrayList<Room>();

        for (Room room : rooms) {
            if (room.isAvail(date)) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    /**
     * Retrieves the list of booked rooms on a specific date.
     *
     * @param date the date to check for booked rooms
     * @return bookedRooms - the list of booked rooms on the specified date
     */
    public ArrayList<Room> getBookedRooms(int date) {
        ArrayList<Room> bookedRooms = new ArrayList<Room>();
        for (Room room : rooms) {
            if (!room.isAvail(date)) {
                bookedRooms.add(room);
            }
        }
        return bookedRooms;
    }

    /**
     * Counts the number of available rooms on a specific date.
     *
     * @param date the date to count available rooms
     * @return count - the number of available rooms on the specified date
     */
    public int countAvail(int date) {
        int count = 0;
        for (Room room : rooms) {
            if (room.isAvail(date)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of booked rooms on a specific date.
     *
     * @param date the date to count booked rooms
     * @return count - the number of booked rooms on the specified date
     */
    public int countBooked(int date) {
        int count = 0;
        for (Room room : rooms) {
            if (!room.isAvail(date)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Retrieves the list of reservations in the hotel.
     *
     * @return reservations - the list of reservations in the hotel
     */
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Checks if all rooms are booked.
     * @param date the date to check for booked rooms
     * @return check - false if all rooms are booked, true otherwise
     */
    public boolean getAllState(int date){
        boolean check = true; // there are rooms available
        int cnt = 0;

        for (Room room : rooms) {
            if (room.getState(date) == 1) {
                cnt++;
            }
        }
        
        if(cnt == rooms.size()){
            check = false; // all rooms are booked
        }

        return check;
    }
}
