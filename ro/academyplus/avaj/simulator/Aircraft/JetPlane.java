package ro.academyplus.avaj.simulator.Aircraft;

import ro.academyplus.avaj.simulator.Flyable.Flyable;
import ro.academyplus.avaj.simulator.Simulator;
import ro.academyplus.avaj.simulator.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

    // Flyable Implementation
        // Attributes

        // Methods
            public void updateConditions(){
                switch (this.weatherTower.getWeather(this.coordinates)) {
                    case "SUN":
                        this.coordinates.latitude += 10;
                        this.coordinates.height += 2;
                        Simulator.outputWriter.printf("%s#%s(%d): ♪ I can show you the world... ♪\n", this.getClass().getSimpleName(), this.name, this.id);

                        break;
                    case "RAIN":
                        this.coordinates.latitude += 5;
                        Simulator.outputWriter.printf("%s#%s(%d): You think some water can stop us... think again.\n", this.getClass().getSimpleName(), this.name, this.id);

                        break;
                    case "FOG":
                        this.coordinates.latitude += 1;
                        Simulator.outputWriter.printf("%s#%s(%d): I don't need to see anyway.\n", this.getClass().getSimpleName(), this.name, this.id);

                        break;
                    case "SNOW":
                        this.coordinates.height -= 7;
                        Simulator.outputWriter.printf("%s#%s(%d): Okay, water can't stop us. But if it's frozen...\n", this.getClass().getSimpleName(), this.name, this.id);

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
            /* package */ JetPlane(String name, Coordinates coordinates){
                super(name, coordinates);                
            }
}