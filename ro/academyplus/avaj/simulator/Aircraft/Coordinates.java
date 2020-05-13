package ro.academyplus.avaj.simulator.Aircraft;

public class Coordinates {
    // Attributes
        Integer longitude;
        Integer latitude;
        Integer height;

    // Methods
        /* package */ Coordinates(Integer longitude, Integer latitude, Integer height){
            this.longitude = longitude;
            this.latitude = latitude;
            this.height = height;
        }
        public Integer getLongitude(){
            return this.longitude;
        }
        public Integer getLatitude(){
            return this.latitude;
        }
        public Integer getHeight(){
            return this.height;
        }
}