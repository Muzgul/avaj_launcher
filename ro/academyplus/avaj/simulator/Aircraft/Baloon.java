package ro.academyplus.avaj.simulator.Aircraft;

import ro.academyplus.avaj.simulator.Flyable.Flyable;
import ro.academyplus.avaj.simulator.Simulator;
import ro.academyplus.avaj.simulator.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    // Flyable Implementation
        // Attributes

        // Methods
            public void updateConditions(){
                switch (this.weatherTower.getWeather(this.coordinates)) {
                    case "SUN":
                        this.coordinates.longitude += 2;
                        this.coordinates.height += 4;
                        Simulator.outputWriter.printf("%s#%s(%d): Up, up and away.\n", this.getClass().getSimpleName(), this.name, this.id);
                        break;
                    case "RAIN":
                        this.coordinates.height -= 5;
                        Simulator.outputWriter.printf("%s#%s(%d): The passengers are cold and want to go home.\n", this.getClass().getSimpleName(), this.name, this.id);
                        break;
                    case "FOG":
                        this.coordinates.height -= 3;
                        Simulator.outputWriter.printf("%s#%s(%d): Is it a bird? Is it a plane? I can't tell.\n", this.getClass().getSimpleName(), this.name, this.id);
                        break;
                    case "SNOW":
                        this.coordinates.height -= 15;
                        Simulator.outputWriter.printf("%s#%s(%d): We didn't sign up for this.\n", this.getClass().getSimpleName(), this.name, this.id);
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
            /* package */ Baloon(String name, Coordinates coordinates){
                super(name, coordinates);                
            }
}