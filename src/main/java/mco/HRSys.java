package mco;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The HRSys class represents a hotel reservation system that manages hotels, rooms, and reservations.
 */
public class HRSys {

    private ArrayList<Hotel> hotels;

    /**
     * Constructs an HRSys object with an empty list of hotels.
     */
    public HRSys(){
        hotels = new ArrayList<Hotel>();
    }

    /**
     * Checks if a hotel with the specified name already exists.
     *
     * @param name the name of the hotel
     * @return true if a hotel with the specified name exists, false otherwise
     */
    public boolean checkIfNameExists(String name){
        name = name.toLowerCase();
        for(Hotel hotel : hotels){
            if(hotel.getName().toLowerCase().equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * Prints a line of dashes to separate sections in the menu.
     */
    public void blank(){
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Lets you press any key to continue.
     */
    public void pressAnyKeyToContinue() {
        System.out.println("Press any key to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println("An error occurred while trying to read input.");
            e.printStackTrace();
        }
}

    /**
     * Displays a confirmation message and returns true if the user confirms, false otherwise.
     *
     * @return true if the user confirms, false otherwise
     */
    public boolean confirmation() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Are you sure? (Y/N): ");
        String confirmation = scan.nextLine();

        return confirmation.equalsIgnoreCase("Y");
    }

    /**
     * main function to create a new instance of a Hotel.
     */
    public void createHotel(){
        Scanner scan = new Scanner(System.in);
        String name;
        int numRooms;
        int stdCnt;
        int delCnt;
        int execCnt;

        System.out.println("Enter the name of the hotel: ");
        name = scan.nextLine();
        
        boolean nameExists = checkIfNameExists(name);
        while (nameExists) {
            System.out.println("Hotel name already exists. Please enter a different name: ");
            name = scan.nextLine();
            nameExists = checkIfNameExists(name);
        }

        
        System.out.println("Enter the number of standard rooms: ");
        stdCnt = scan.nextInt();

        while (stdCnt < 1 || stdCnt > 48) {
            System.out.println("Invalid number of standard rooms. Please enter a number between 1 and 48: ");
            stdCnt = scan.nextInt();
        }

        System.out.println("Enter the number of deluxe rooms: ");
        delCnt = scan.nextInt();

        while (delCnt < 1 || delCnt > 49 || stdCnt + delCnt > 49) {
            System.out.println("Invalid number of deluxe rooms. Please enter a number between 1 and " + (49 - stdCnt) + ": ");
            delCnt = scan.nextInt();
        }

        System.out.println("Enter the number of executive rooms: ");
        execCnt = scan.nextInt();

        while (execCnt < 1 || execCnt > 50 || stdCnt + delCnt + execCnt > 50) {
            System.out.println("Invalid number of executive rooms. Please enter a number between 1 and " + (50 - stdCnt - delCnt) + ": ");
            execCnt = scan.nextInt();
        }
        
        Hotel hotel = new Hotel(name, stdCnt, delCnt, execCnt);
        hotels.add(hotel);

        blank();
        pressAnyKeyToContinue();

    }

    /**
     * Displays all hotels and prompts the user to select a hotel.
     * @return hotelNumber the number of the hotel selected by the user
     */
    public int displayAllHotels(){
        Scanner scan = new Scanner(System.in);

        int count = 1;
        blank();
        System.out.println("Hotels:");
        blank();
        for(Hotel hotel : hotels){
            System.out.println(count + ": " + hotel.getName());
            count++;
        }
        blank();
        
        System.out.println("Enter the number of the hotel: ");
        int hotelNumber = scan.nextInt();
        scan.nextLine(); 

        while (hotelNumber < 1 || hotelNumber > hotels.size()) {
            System.out.println("Invalid hotel number. Please enter a valid number: ");
            hotelNumber = scan.nextInt();
            scan.nextLine();
        }

        return hotelNumber;
    }

    /**
     * Displays all rooms in a hotel and prompts the user to select a room.
     * @param hotelNumber the number of the hotel
     * @param check the type of room to display (1: all rooms, 2: available rooms, 3: booked rooms)
     * @return roomNumber the number of the room selected by the user
     */
    public int displayAllRooms(int hotelNumber, int check){
        Scanner scan = new Scanner(System.in);

        String hotelName = hotels.get(hotelNumber - 1).getName();
        int count = 1;

        if (check == 1){
            blank();
            System.out.println("Standard Rooms in " + hotelName + ":");
            blank();
            for(Room room : hotels.get(hotelNumber - 1).getRooms(1)){
                System.out.println(count + ": " + room.getRoomID());
                count++;
            }
            blank();

            blank();
            System.out.println("Deluxe Rooms in " + hotelName + ":");
            blank();
            for(Room room : hotels.get(hotelNumber - 1).getRooms(2)){
                System.out.println(count + ": " + room.getRoomID());
                count++;
            }
            blank();

            blank();
            System.out.println("Executive Rooms in " + hotelName + ":");
            blank();
            for(Room room : hotels.get(hotelNumber - 1).getRooms(3)){
                System.out.println(count + ": " + room.getRoomID());
                count++;
            }
            blank();
        }
        if (check == 2) {
            blank();
            System.out.println("Available Rooms in " + hotelName + ":");
            blank();
            for (Room room : hotels.get(hotelNumber - 1).getRooms(4)) {
                if (!room.isBook()) {
                    System.out.println(count + ": " + room.getRoomID());
                    count++;
                }
            }
            blank();
        } else if (check == 3) {
            blank();
            System.out.println("Booked Rooms in " + hotelName + ":");
            blank();
            for (Room room : hotels.get(hotelNumber - 1).getRooms(4)) {
                if (room.isBook()) {
                    System.out.println(count + ": " + room.getRoomID());
                    count++;
                }
            }
            blank();
        }

        

        System.out.println("Enter the RoomID of the room: ");
        int roomNumber = scan.nextInt();
        scan.nextLine();

        while (roomNumber < 1 || roomNumber > count - 1) {
            System.out.println("Invalid room number. Please enter a valid number: ");
            roomNumber = scan.nextInt();
            scan.nextLine();
        }

        return roomNumber;
    }

    /**
     * Displays the availability of a room for the month.
     * @param room the room to display the availability for
     */
    public void displayRoomMonth(Room room){
        blank();
        System.out.println("Room ID: " + room.getRoomID());
        blank();
        System.out.println("Room Availability for the Month:");
        blank();
        for (int i = 1; i <= 31; i++) {
            System.out.printf("%-3d", i);
            if (i % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        blank();
    }

    /**
     * Displays the availability of a room for a specific day.
     * @param hotelNumber the number of the hotel
     * @param date the date to display the availability for
     * @param check the type of room to display (1: available rooms, 2: booked rooms)
     */
    public void displayRoomDay(int hotelNumber, int date, int check){
        
        int count = 1;
        String hotelName = hotels.get(hotelNumber - 1).getName();
        // FOR CHECK IN
        if (check == 1) {
            blank();
            System.out.println("Available Rooms in " + hotelName + " on Day " + date + ":");
            blank();
            for (Room room : hotels.get(hotelNumber - 1).getRooms(4)) {
                if (room.getState(date) != 1) {
                    System.out.println(count + ": " + room.getRoomID());
                    count++;
                }
            }
            blank();
        // FOR CHECK OUT
        } else if (check == 2) {
            blank();
            System.out.println("Booked Rooms in " + hotelName + " on Day " + date + ":");
            blank();
            for (Room room : hotels.get(hotelNumber - 1).getRooms(4)) {
                if (room.getState(date) != 2) {
                    System.out.println(count + ": " + room.getRoomID());
                    count++;
                }
            }
            blank();
        } 
        
    }

    /**
     * Select the reservation to view.
     * @param hotelNumber the number of the hotel
     * @return reservationName the name of the reservation selected by the user
     */
    public String selectReservation(int hotelNumber){
        Scanner scan = new Scanner(System.in);

        String hotelName = hotels.get(hotelNumber - 1).getName();
        int count = 1;

        blank();
        System.out.println("Reservations in " + hotelName + ":");
        blank();
        for(Reservation reservation : hotels.get(hotelNumber - 1).getReservations()){
            System.out.println(count + ": " + reservation.getGuestName());
            count++;
        }
        blank();

        System.out.println("Enter the name of the reservation: ");
        String reservationName = scan.nextLine();

        while(hotels.get(hotelNumber - 1).crossReservation(reservationName) == null){
            System.out.println("Invalid reservation name.");
            System.out.println("Enter the name of the reservation: ");
            reservationName = scan.nextLine();
        }

        return reservationName;
    }

    /**
     * Display all reservations in a hotel.
     * @param hotelNumber the number of the hotel
     * @return reservationNumber the number of the reservation selected by the user
     */
    public int displayAllReservations(int hotelNumber){
        Scanner scan = new Scanner(System.in);

        String hotelName = hotels.get(hotelNumber - 1).getName();
        int count = 1;

        if (hotels.get(hotelNumber - 1).getReservations().size() == 0) {
            blank();
            System.out.println("No reservations in " + hotelName + ".");
            blank();
            pressAnyKeyToContinue();
            return 0;
        }


        blank();
        System.out.println("Reservations in " + hotelName + ":");
        blank();
        for(Reservation reservation : hotels.get(hotelNumber - 1).getReservations()){
            System.out.println(count + ": " + reservation.getGuestName());
            count++;
        }
        blank();

        System.out.println("Enter the number of the reservation: ");
        int reservationNumber = scan.nextInt();
        scan.nextLine();

        while (reservationNumber < 1 || reservationNumber > count - 1) {
            System.out.println("Invalid number of reservation. Please enter a valid number: ");
            reservationNumber = scan.nextInt();
            scan.nextLine();
        }

        return reservationNumber;
    }


    /**
     * Main function to view all information about the hotel.
     */
    public void viewHotel(){
        if(hotels.size() == 0){
            blank();
            System.out.println("No Hotels to View. Please Create a Hotel First.");
        }
        else{
            Scanner scan = new Scanner(System.in);

            int hotelNumber = displayAllHotels();
            String hotelName = hotels.get(hotelNumber - 1).getName();
        

            blank();
            System.out.println("Hotel Name: " + hotelName);
            blank();
            System.out.println("Accessible Information:");
            System.out.println("    1: High-level Information");
            System.out.println("    2: Low-level Information");
            blank();
            System.out.println("Enter the number of your choice: ");
            int choice = scan.nextInt();

            blank();
            if(choice == 1){
                System.out.println("High-level Information:");
            
                System.out.println("    Hotel Name: " + hotelName);
                System.out.println("    Number of Rooms: " + hotels.get(hotelNumber - 1).getNumRooms());
                System.out.println("    Estimated Earnings of the Month: " + hotels.get(hotelNumber - 1).estEarn()); 
            }
            else if(choice == 2){
                System.out.println("Low-level Information Menu:");

                System.out.println("    1: Check Availability of Rooms");
                System.out.println("    2: View Rooms Informations");
                System.out.println("    3: View Reservations");

                blank();

                System.out.println("Enter the number of your choice: ");
                int choice2 = scan.nextInt();
                blank();
            
                if(choice2 == 1){
                    calendar();
                    System.out.println("Enter the date: ");
                    int date = scan.nextInt();
                        
                    System.out.println("    Available Rooms: " + hotels.get(hotelNumber - 1).countAvail(date));
                    System.out.println("    Booked Rooms: " + hotels.get(hotelNumber - 1).countBooked(date)); 
                }
                else if (choice2 == 2){
                    int room = displayAllRooms(hotelNumber, 1);

                    System.out.println("    Room ID: " + hotels.get(hotelNumber - 1).getRooms(4).get(room - 1).getRoomID());
                    System.out.println("    Room Price: " + hotels.get(hotelNumber - 1).getRooms(4).get(room - 1).getPrice()); 
                    System.out.println("    Room Status for the Days: "); 
                    System.out.println(hotels.get(hotelNumber - 1).getRooms(4).get(room - 1).getAllAvail());
                }
                else if (choice2 == 3){
                    //Information about Reservation
                    // FUNC CHOOSE RESERVATION
                    int reservation = displayAllReservations(hotelNumber);

                    if (reservation != 0) {
                        System.out.println("    Guest Name: " + hotels.get(hotelNumber - 1).getReservations().get(reservation - 1).getGuestName()); 
                        System.out.println("    Room Name: " + hotels.get(hotelNumber - 1).getReservations().get(reservation - 1).getRoom().getRoomID());
                        System.out.println("    Check-in Date: " + hotels.get(hotelNumber - 1).getReservations().get(reservation - 1).getCheckInDate()); 
                        System.out.println("    Check-out Date:  " + hotels.get(hotelNumber - 1).getReservations().get(reservation - 1).getCheckOutDate()); 
                        System.out.println("    Price per Night: " + hotels.get(hotelNumber - 1).getReservations().get(reservation - 1).getCostPerNight()); 
                        System.out.println("    Total Price: " + hotels.get(hotelNumber - 1).getReservations().get(reservation - 1).getTotalCost()); 
                    }

                    
                }
                else{
                    System.out.println("Invalid choice");
                    viewHotel();
                }
            }   
            else{
                System.out.println("Invalid choice");
                viewHotel();
            }
        }

        blank();
        pressAnyKeyToContinue();
    }

    /**
     * Update the name of the hotel.
     * @param hotelNumber the number of the hotel
     */
    public void updHotelName(int hotelNumber){
        Scanner scan = new Scanner(System.in);

        String hotelName;
        String tempName = hotels.get(hotelNumber - 1).getName();

        System.out.println("Enter the new name of the hotel: ");
        hotelName = scan.nextLine();
        boolean nameExists = checkIfNameExists(hotelName);
        while (nameExists) {
            System.out.println("Hotel name already exists. Please enter a different name: ");
            hotelName = scan.nextLine();
            nameExists = checkIfNameExists(hotelName);
        }
        hotels.get(hotelNumber - 1).updName(hotelName);



        if(confirmation()){
            System.out.println("Hotel name updated.");
        }
        else{
            System.out.println("Hotel name not updated.");
            hotels.get(hotelNumber - 1).updName(tempName);
        }
    }

    /**
     * Add rooms to the hotel.
     * @param hotelNumber the number of the hotel
     */
    public void addRooms(int hotelNumber){
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of rooms to add: ");
        int numRooms = scan.nextInt();
        int total = hotels.get(hotelNumber - 1).getNumRooms() + numRooms;

        while (total < 1 || total > 50) {
            System.out.println("Invalid number of rooms. Please enter a number between 1 and : " + (50 - hotels.get(hotelNumber - 1).getNumRooms()));
            numRooms = scan.nextInt();
            total = hotels.get(hotelNumber - 1).getNumRooms() + numRooms;
        }

        int choice;

        blank();
        System.out.println("Select the type of room to add:");
        System.out.println("1: Standard Room");
        System.out.println("2: Deluxe Room");
        System.out.println("3: Executive Room");
        blank();

        System.out.println("Enter the number of your choice: ");
        choice = scan.nextInt();

        while (choice < 1 || choice > 3) {
            System.out.println("Invalid choice. Please enter a number between 1 and 3: ");
            choice = scan.nextInt();
        }

        if(confirmation()){
            System.out.println("Rooms added.");
            
            for (int i = 0; i < numRooms; i++) {
                hotels.get(hotelNumber - 1).addRoom(choice);
            }
        }
        else{
            System.out.println("Rooms not added.");
        }
    }

    /**
     * Remove rooms from the hotel.
     * @param hotelNumber the number of the hotel
     */
    public void removeRoom(int hotelNumber){

        Scanner scan = new Scanner(System.in);
        int count = 1;

        System.out.println("Available Rooms to be Removed:");
        for(Room room : hotels.get(hotelNumber - 1).getRooms(4)){
            if(!room.isBook()){
                System.out.println(count + ": " + room.getRoomID());
                count++;
            }
        }

        System.out.println("Enter the Name of the Room to remove: ");
        String roomName = scan.nextLine();

        while(hotels.get(hotelNumber - 1).crossRoom(roomName) == null){
            System.out.println("Invalid room name.");
            System.out.println("Enter the name of the room: ");
            roomName = scan.nextLine();
        }

        if(confirmation()){
            hotels.get(hotelNumber - 1).removeRoom(roomName);
            System.out.println("Room removed.");
        }
        else{
            System.out.println("Room not removed.");
        }
    }

    /**
     * Update the base price of the hotel.
     * @param hotelNumber the number of the hotel
     */
    public void updBasePrice(int hotelNumber){
        Scanner scan = new Scanner(System.in);

        if (hotels.get(hotelNumber - 1).estEarn() != 0) {
            System.out.println("Cannot update base price. There are reservations in the hotel.");
            return;
        }


        double tempPrice = hotels.get(hotelNumber - 1).getBasePrice();
        double basePrice;

        System.out.println("Enter the new base price of the hotel: ");
        basePrice = scan.nextDouble();

        while (basePrice <= 100.00) {
            System.out.println("Invalid base price. Please enter a value greater than or equal to 100: ");
            basePrice = scan.nextDouble();
        }

        hotels.get(hotelNumber - 1).updBasePrice(basePrice);


        if(confirmation()){
            System.out.println("Base price updated.");
        }
        else{
            System.out.println("Base price not updated.");
            hotels.get(hotelNumber - 1).updBasePrice(tempPrice);
        }
    }

    /**
     * Remove a reservation from the hotel.
     * @param hotelNumber the number of the hotel
     */
    public void removeReservation(int hotelNumber){

        String name = selectReservation(hotelNumber);

        if(confirmation()){
            System.out.println("Reservation removed.");
            hotels.get(hotelNumber - 1).removeReservation(name);
        }
        else{
            System.out.println("Reservation not removed.");
        }
    }

    /**
     * Remove a hotel from the system.
     * @param hotelNumber the number of the hotel
     */
    public void removeHotel(int hotelNumber){

        if(confirmation()){
            System.out.println("Hotel removed.");
            hotels.remove(hotelNumber - 1);

        }
        else{
            System.out.println("Hotel not removed.");
        }
    }

    /**
     * Main function to manage the hotel.
     */
    public void manageHotel(){
        if(hotels.size() == 0){
            blank();
            System.out.println("No Hotels to Manage. Please Create a Hotel First.");
        }
        else{
            Scanner scan = new Scanner(System.in);

            blank();
            int hotelNumber = displayAllHotels();
            String hotelName = hotels.get(hotelNumber - 1).getName();
            blank();

            System.out.println("Manage Hotel: " + hotelName);
            blank();
            System.out.println("1: Update Hotel Name");
            System.out.println("2: Add Rooms");
            System.out.println("3: Remove Rooms");
            System.out.println("4: Update Base Price");
            System.out.println("5: Remove Reservation");
            System.out.println("6: Remove Hotel");
            System.out.println("7: Exit");
            blank();

            boolean validChoice = false;
            while (!validChoice) {
                System.out.println("Enter the numbere of your choice: ");
                int choice = scan.nextInt();
                scan.nextLine();

                if (choice >= 1 && choice <= 7) {
                    validChoice = true;
                    switch (choice) {
                        case 1:
                            updHotelName(hotelNumber);
                            break;
                        case 2:
                            addRooms(hotelNumber);
                            break;
                        case 3:
                            removeRoom(hotelNumber);
                            break;
                        case 4:
                            updBasePrice(hotelNumber);
                            break;
                        case 5:
                            removeReservation(hotelNumber);
                            break;
                        case 6:
                            removeHotel(hotelNumber);
                            break;
                        case 7:
                            break;
                    }
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            }
        }
        
        blank();
        pressAnyKeyToContinue();
    }

    /**
     * Display the calendar for the month.
     */
    public void calendar(){
        blank();
        System.out.println("Calendar:");
        blank();
        for (int i = 1; i <= 31; i++) {
            System.out.printf("%-3d", i);
            if (i % 7 == 0) {
            System.out.println();
            }
        }
        System.out.println();
        blank();
    }

    /**
     * Main function to book a room.
     */
    public void booking(){
        Scanner scan = new Scanner(System.in);
        if(hotels.size() == 0){
            blank();
            System.out.println("No Rooms to Book. Please Create a Hotel First.");
        }
        else{
            int hotelNumber = displayAllHotels();

            System.out.println("Enter the name of the guest: ");
            String guestName = scan.nextLine();


            calendar();
            System.out.println("Enter the check-in date: ");
            int checkInDate = scan.nextInt();

            boolean validCheckInDate = false;

            while (!validCheckInDate) {
                if (checkInDate < 1 || checkInDate >= 31) {
                    System.out.println("Invalid check-in date. Please enter 1 - 30: ");
                    checkInDate = scan.nextInt();
                } 
                else {
                    if (!hotels.get(hotelNumber - 1).getAllState(checkInDate)) {
                        System.out.println("No available rooms on the check-in date. Please enter again: ");
                        checkInDate = scan.nextInt();
                    } 
                    else {
                        validCheckInDate = true;
                    }
                }
            }

            calendar();
            System.out.println("Enter the check-out date: ");
            int checkOutDate = scan.nextInt();

            boolean validCheckOutDate = false;

            while (!validCheckOutDate) {
                if (checkOutDate == 1) {
                    System.out.println("You cannot check out at 1. Please enter again: ");
                    checkOutDate = scan.nextInt();
                } else if (checkOutDate < checkInDate) {
                    System.out.println("Invalid check-out date. Please enter date after Check In: ");
                    checkOutDate = scan.nextInt();
                } else {
                    validCheckOutDate = true;
                }
            }

            displayRoomDay(hotelNumber, checkInDate, 1);

            System.out.println("Enter the name of the room: ");
            
            scan.nextLine();
            String roomName = scan.nextLine();

            while(hotels.get(hotelNumber - 1).crossRoom(roomName) == null){
                System.out.println("Invalid room name.");
                System.out.println("Enter the name of the room: ");
                roomName = scan.nextLine();
            }

            Room room = hotels.get(hotelNumber - 1).crossRoom(roomName);

            if(confirmation()){
                hotels.get(hotelNumber - 1).addReservation(guestName, checkInDate, checkOutDate, room);
                room.bookRoom(checkInDate, checkOutDate);
                System.out.println("Room booked.");
            }
            else{
                System.out.println("Room not booked.");
            }


        }

        blank();
        pressAnyKeyToContinue();
    }
}
