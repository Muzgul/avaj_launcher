package ro.academyplus.avaj.simulator.Flyable;

import ro.academyplus.avaj.simulator.WeatherTower;

// Observer pattern - This is the Observer
public interface Flyable {

    // Public Methods
    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
}