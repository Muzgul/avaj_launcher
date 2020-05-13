package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.Aircraft.Coordinates;
import ro.academyplus.avaj.simulator.Tower;
import ro.academyplus.avaj.simulator.WeatherProvider.WeatherProvider;

public class WeatherTower extends Tower {
    // Attributes

    // Methods
        /* package */ void changeWeather(){
            this.conditionsChanged();
        }
        public String getWeather(Coordinates coordinates){
            return WeatherProvider.getProvider().getCurrentWeather(coordinates);
        }
}