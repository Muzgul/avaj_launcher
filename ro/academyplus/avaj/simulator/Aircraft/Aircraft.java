package ro.academyplus.avaj.simulator.Aircraft;

public class Aircraft {
    // Private
        private static long idCounter = 0;
        private long nextId(){
            return Aircraft.idCounter++;
        }

    // Protected
        protected long id;
        protected String name;
        protected Coordinates coordinates;

        protected Aircraft(String name, Coordinates coordinates){
            this.name = name;
            this.coordinates = coordinates;
            this.id = this.nextId(); // Placeholder
        }

    // Public
}