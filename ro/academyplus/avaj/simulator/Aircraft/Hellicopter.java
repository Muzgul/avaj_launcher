package ro.academyplus.avaj.simulator.Aircraft;

import ro.academyplus.avaj.simulator.Flyable.Flyable;
import ro.academyplus.avaj.simulator.Simulator;
import ro.academyplus.avaj.simulator.WeatherTower;

public class Hellicopter extends Aircraft implements Flyable {

    // Flyable Implementation
        // Attributes

        // Methods
            public void updateConditions(){
                switch (this.weatherTower.getWeather(this.coordinates)) {
                    case "SUN":
                        this.coordinates.longitude += 10;
                        this.coordinates.height += 2;
                        Simulator.outputWriter.printf("%s#%s(%d): Go faster, I'm sweating here.\n", this.getClass().getSimpleName(), this.name, this.id);
                        break;
                    case "RAIN":
                        this.coordinates.longitude += 5;
                        Simulator.outputWriter.printf("%s#%s(%d): Someone get a mop.\n", this.getClass().getSimpleName(), this.name, this.id);
                        break;
                    case "FOG":
                        this.coordinates.longitude += 1;
                        Simulator.outputWriter.printf("%s#%s(%d): Straight ahead, whichever way that is.\n", this.getClass().getSimpleName(), this.name, this.id);
                        break;
                    case "SNOW":
                        this.coordinates.height -= 12;
                        Simulator.outputWriter.printf("%s#%s(%d): The rotor blades... They are freezing.\n", this.getClass().getSimpleName(), this.name, this.id);
                        break;
                    default:
                        break;
                }
                if (this.coordinates.height <= 0){
                    Simulator.outputWriter.printf("%s#%s(%d): Has landed. Coordinates: [lng = %d, lat = %d].\n", this.getClass().getSimpleName(), this.name, this.id, this.coordinates.longitude, this.coordinates.latitude);
                    Simulator.outputWriter.printf("Tower says: %s#%s(%d) Has unregistered with tower. Coordinates: [lng = %d, lat = %d, alt = %d].\n", this.getClass().getSimpleName(), this.name, this.id, this.coordinates.longitude, this.coordinates.latitude, this.coordinates.height);
                    this.weatherTower.unregister(this);
                }
            }
            public void registerTower(WeatherTower weatherTower){
                this.weatherTower = weatherTower;
                Simulator.outputWriter.printf("Tower says: %s#%s(%d) Has registered with tower. Coordinates: [lng = %d, lat = %d, alt = %d].\n", this.getClass().getSimpleName(), this.name, this.id, this.coordinates.longitude, this.coordinates.latitude, this.coordinates.height);
                this.weatherTower.register(this);
            }

    // Aircraft implementation
        // Attributes
            WeatherTower weatherTower;
        // Methods
            /* package */ Hellicopter(String name, Coordinates coordinates){
                super(name, coordinates);                
            }
}