import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    // Constants
    static final String FILEPATH = "info.txt";
    static final String LATITUDE = "LATITUDE";
    static final String LONGITUDE = "LONGITUDE";
    static final String BATTERY = "BATTERY";


    // Main method serves as the "CPU"
    public static void main(String[] args) throws java.io.IOException {
        Memory ram = new Memory(FILEPATH);
        ram.bufferMemory();

        MarsRover rover = new MarsRover(ram.readData(LATITUDE), ram.readData(LONGITUDE), ram.readData(BATTERY));
        Scanner scanInput = new Scanner(System.in);

        System.out.println("...Mars Rover OS...\n1. Check Rover Status\n2. Scan Area\n3. Move Rover\n4. Recharge Solar Panels\nType -1 to exit");
        System.out.print("> ");
        int userInput;

        //If user types text, prompt again
        while (true) {
            try {
                userInput = scanInput.nextInt();
                break;
            }
            catch (Exception InputMismatchException) {
                System.out.println("Type an integer");
                System.out.print("> ");
                scanInput.nextLine();
            }
        }

        while (userInput != -1) {
            switch (userInput) {
                case 1:
                    rover.printStatus();
                    break;
                case 2:
                    if (rover.scanArea()) {
                        System.out.println("Obstacle detected.");
                    }
                    else {
                        System.out.println("No obstacles detected.");
                    }
                    break;
                case 3:
                    System.out.println("Type (without \"\") \"forward\" ,\"back\" ,\"left\" , or \"right\". \nType anything else to cancel");
                    Scanner dirInput = new Scanner(System.in);
                    String direction = dirInput.next();

                    // If user types incorrectly, cancel
                    if (!(direction.equalsIgnoreCase("forward") || direction.equalsIgnoreCase("back") || direction.equalsIgnoreCase("left") || direction.equalsIgnoreCase("right"))) {
                        break;
                    }
                    rover.move(direction);
                    break;
                case 4:
                    rover.rechargeSolarPanel();
                    break;
                default:
                    System.out.println("Unknown command. Try Again.");
                    break;
            }
            ram.writeData(LATITUDE, rover.getLatitude());
            ram.writeData(LONGITUDE, rover.getLongitude());
            ram.writeData(BATTERY, rover.getBattery());
            scanInput.nextLine();

            //If user types text, prompt again
            System.out.print("> ");
            while (true) {
                try {
                    userInput = scanInput.nextInt();
                    break;
                }
                catch (Exception InputMismatchException) {
                    System.out.println("Unknown command. Try Again.");
                    System.out.print("> ");
                    scanInput.nextLine();
                }
            }
        }

        // When done, write rover's memory into file
        ram.bufferFile();
    }
}
