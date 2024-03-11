import java.io.*;
import java.util.*;

public class Memory {
    // The memory
    private Map<String, Integer> memory;
    final private String FILEPATH;

    //Constructor
    public Memory(String FILEPATH) throws FileNotFoundException {
        memory = new HashMap<>();
        this.FILEPATH = FILEPATH;
    }

    /**Buffer data from file to memory
     */
    public void bufferMemory() throws FileNotFoundException {
        File inputFile = new File(FILEPATH);
        Scanner fileReader = new Scanner(inputFile);

        //Check if text file exists
        if (!inputFile.exists()) {
            System.out.println("Buffer Error: Input file does not exist.");
            return;
        }

        //Check if text file is empty
        if (!fileReader.hasNextLine()) {
            System.out.println("Buffer Error: Input file is empty.");
            return;
        }

        // Read each key/value pair
        while (fileReader.hasNextLine()){
            String pair = fileReader.nextLine();
            Scanner pairReader = new Scanner(pair);
            try {
                //Write key/value pair into memory
                String key = pairReader.next();
                int value = pairReader.nextInt();
                memory.put(key, value);
            }
            //If line is empty or corrupted, skip to next line
            catch (Exception NoSuchElementException) {
                System.out.println("Buffer Warning: Unknown pair. Ignored.");
            }
        }
    }

    /**Buffer data from memory to file
     */
    public void bufferFile() {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILEPATH, false)))) {
            for (Map.Entry<String, Integer> entry : memory.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                writer.println(key + " " + value);
            }
        } catch (IOException e) {
            System.out.println("Buffer Error: Cannot write memory to file");
        }
    }

    /**Read data from memory
     *
     * @return int
     */
    public int readData(String key) {
        try {
            return memory.get(key);
        }
        catch (Exception NullPointerException) {
            System.out.println("\"" + key + "\" is not found in memory.");
            return 0;
        }
    }

    /**Write data to memory
     *
     */
    public void writeData(String key, int value) {
        memory.put(key, value);
    }
}