package ro.academyplus.avaj.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import ro.academyplus.avaj.simulator.Aircraft.AircraftFactory;
import ro.academyplus.avaj.simulator.Flyable.Flyable;

public class Simulator {
    // Private writer
    public static PrintWriter outputWriter = null;
    private static AircraftFactory aircraftFactory = new AircraftFactory();

    private static Flyable validateFlyableInput(String input) throws InvalidInputException{
        String type = null;
        String name = null;
        int longitude = 0;
        int latitude = 0;
        int height = 0;
        Flyable fly = null;

        String[] line = input.split(" ");

        if (line.length != 5){
            String message = "Invalid number of attributes for a flyable entity: ";
            for (int i = 0; i < line.length; i++)
                message += "[" + line[i] + "]";
            throw new InvalidInputException(message);
        }

        type = line[0];
        name = line[1];
        longitude = Integer.parseInt(line[2]);
        latitude = Integer.parseInt(line[3]);
        height = Integer.parseInt(line[4]);

        if (longitude < 0) throw new InvalidInputException("Longitude input is negative.");
        if (latitude < 0) throw new InvalidInputException("Latitude input is negative.");
        if (height < 0) throw new InvalidInputException("Height input is negative.");

        fly = aircraftFactory.newAircraft(type, name, longitude, latitude, height);

        if (fly == null) {
            String message = "Invalid Flyable type with input ";
            message += "[" + type + "]";
            message += "[" + name + "]";
            message += "[" + longitude + "]";
            message += "[" + latitude + "]";
            message += "[" + height + "]";
            throw new InvalidInputException(message);
        }   

        return fly;
    }
    public static void main(String[] args){
		int 		simulations = 0;
        File        inputFile = null;
        Scanner     inputScanner = null;
        File		outputFile = null;

        WeatherTower wTower;

        // No input file provided
        if (args.length <= 0){
            System.out.println("Please provide a simulation file.");
            return;
        }

        // Input file
        try {
            inputFile = new File(args[0]);
            inputScanner = new Scanner(inputFile);
        } catch ( FileNotFoundException e){
            System.out.println("File not found. Error: ");
            System.out.println(e.getMessage());
        }

        // Input file is valid -> Check output file
        try {
            outputFile = new File("simulation.txt");
            outputFile.createNewFile();
            Simulator.outputWriter = new PrintWriter(outputFile);
        } catch (IOException e) {
            System.out.println("Unable to create 'simulation.txt'. Error: ");
            System.out.println(e.getMessage());
            return ;
        }

        // Ouput file is valid -> begin simulation

        wTower = new WeatherTower();

        // Get simulation count + register new flyables
        int line_number = 0;
        try {
            if (inputScanner.hasNextLine())
                simulations = Integer.parseInt(inputScanner.nextLine());
            if (simulations < 1) throw new InvalidInputException("Invalid simulation count; cannot be less than 1.");
            // Loop through following lines to create Flyables
            while (inputScanner.hasNextLine()){
                line_number++;
                Flyable flyable = Simulator.validateFlyableInput(inputScanner.nextLine());
                flyable.registerTower(wTower);
            }
        } catch (InvalidInputException e) {
            System.out.printf("%s.. at line %d\n", e.getMessage(), line_number + 1);
            return ;
        } catch (NumberFormatException e){
            System.out.printf("%s.. at line %d\n", e.getMessage(), line_number + 1);
            return ;
        }

        // All Flyables created. Run
        
        for (int i = 0; i < simulations; i++){
            wTower.conditionsChanged();
        }

        inputScanner.close();
        Simulator.outputWriter.close();
    }
}