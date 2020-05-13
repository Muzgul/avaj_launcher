package ro.academyplus.avaj.simulator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ro.academyplus.avaj.simulator.Flyable.Flyable;

// Observer pattern - This is the "Subject"
public class Tower {
    // Attributes
        private List<Flyable> observers = new CopyOnWriteArrayList<Flyable>();

    // Methods
        public void register(Flyable flyable){
            if (flyable != null){
                this.observers.add(flyable);
            }
        }
        public void unregister(Flyable flyable){
            if (this.observers.contains(flyable)){
                
                this.observers.remove(flyable);
            }
        }
        protected void conditionsChanged(){
            for (Flyable flyable : this.observers){
                flyable.updateConditions();
            }
        }
}