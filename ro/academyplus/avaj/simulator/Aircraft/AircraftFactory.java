package ro.academyplus.avaj.simulator.Aircraft;

import ro.academyplus.avaj.simulator.Flyable.Flyable;

public class AircraftFactory {
    // Flyable constructor
    public Flyable newAircraft(String type, String name, Integer longitude, Integer latitude, Integer height){
        
        Flyable flyable;
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        switch (type) {
            case "Helicopter":
                flyable = new Hellicopter(name, coordinates);
                break;
            case "JetPlane":
                flyable = new JetPlane(name, coordinates);
                break;
            case "Baloon":
                flyable = new Baloon(name, coordinates);
                break;
            default:
                flyable = null;
                break;
        }
        return flyable;
    }
}