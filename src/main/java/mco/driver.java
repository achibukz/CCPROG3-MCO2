package mco;

import java.io.IOException;
import java.util.Scanner;

/**
 * The driver class contains the main method.
 */
public class driver {

    /**
     * The main method creates an instance of the driver class and calls the displayMenu method.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args){
        driver d = new driver();
        d.displayMenu();
    }
        

    /**
     * Prints a line of dashes to separate sections in the menu.
     */
    public void blank(){
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Clears the console screen.
     */
    public static void clearScreen() {
    try {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
    }

    /**
     * Displays the main menu and handles user input.
     */
    public void displayMenu(){
        Scanner scan = new Scanner(System.in);
        HRSys hrs = new HRSys();
        int choice = 0;

        while(choice != 5){
            blank();
            System.out.println(" _   _ ____  ____            ");
            System.out.println("| | | |  _ \\/ ___| _   _ ___ ");
            System.out.println("| |_| | |_) \\___ \\| | | / __|");
            System.out.println("|  _  |  _ < ___) | |_| \\__ \\");
            System.out.println("|_| |_|_| \\_\\____/ \\__, |___/");
            System.out.println("                   |___/     ");
            blank();
            System.out.println("1: Create a hotel");
            System.out.println("2: View a hotel");
            System.out.println("3: Manage a hotel");
            System.out.println("4: Book a room");
            System.out.println("5: Exit");
            blank();
            
            while (true) {
                System.out.println("Enter the number of your choice: ");
                if (scan.hasNextInt()) {
                    choice = scan.nextInt();
                    break;
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    scan.next(); // discard the invalid input
                }
            }

            if(choice == 1){
                hrs.createHotel();
                clearScreen();
            } 
            else if(choice == 2){
                hrs.viewHotel();
                clearScreen();
            } 
            else if(choice == 3){
                hrs.manageHotel();
                clearScreen();
            }
            else if(choice == 4){
                hrs.booking();
                clearScreen();

            }
            else if(choice == 5){
                System.out.println("Exiting program...");
                scan.close();
                System.exit(0);

            }
            else{
                clearScreen();
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}