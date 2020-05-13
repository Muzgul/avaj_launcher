package ro.academyplus.avaj.simulator.WeatherProvider;

import java.util.Random;

import ro.academyplus.avaj.simulator.Aircraft.Coordinates;

// Singleton design pattern
public class WeatherProvider {
    // Attributes
        /* Singleton instance */
        private static WeatherProvider weatherProvider;
        private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

        private final static int longitude_max = 360;
        private final static int latitude_max = 180;
        private final static int height_max = 100;

        private static int[][] noise_matrix = new int[36][18];

    // Methods
        private WeatherProvider(){
            Random rand = new Random();
            for (int row = 0; row < 36; row++){
                for (int col = 0; col < 18; col++)
                    noise_matrix[row][col] = rand.nextInt(WeatherProvider.height_max);
            }
        }

        private static double lerp(int low, int high, double t){
            return low * (1 - t) + high * t;
        }
        // lng, lat
        private static int noise(double nx, double ny){

            int x_low = (int)Math.floor(nx);
            int x_high = (int)Math.ceil(nx);
            int x_round = (int)Math.round(nx);
            
            int y_low = (int)Math.floor(ny);
            int y_high = (int)Math.ceil(ny);
            int y_round = (int)Math.round(ny);

            int x_h = (int)lerp(
                noise_matrix[x_low][y_round],
                noise_matrix[x_high][y_round],
                nx - x_low
            );
            int y_h = (int)lerp(
                noise_matrix[x_round][y_low],
                noise_matrix[x_round][y_high],
                ny - y_low
            );

            return Math.round((x_h + y_h) / 2);
        }

        public static WeatherProvider getProvider(){
            if (WeatherProvider.weatherProvider == null)
                WeatherProvider.weatherProvider = new WeatherProvider();
            return WeatherProvider.weatherProvider;
        }

        public String getCurrentWeather(Coordinates coordinates){
            /*
                *********

                Weather Simulation aglorithm goes here
                
                - Noise algorithm
                - Add octaves for more interesting landscapes
                - Add "steps" or "biomes" to indicate different weather (sun -> fog -> rain -> snow)

                *********
            */
            
            double lng = coordinates.getLongitude();
            double lat = coordinates.getLatitude();

            while (lng > longitude_max) lng -= longitude_max; // Normalise lng
            while (lat > latitude_max) lat -= latitude_max; // Nomralise lat

            if (lng < 1) lng = 1; // For division purposes
            if (lat < 1) lat = 1; // For division purposes

            // System.out.printf("lng = %f lat = %f", lng, lat);

            double n = WeatherProvider.noise(lng / 10, lat / 10) + coordinates.getHeight();    
            
            // System.out.print("NOISE: ");
            // System.out.println( n );

            if (n > 100) n = 100; // Max height of 100
            if (n < 0)  n = 1; // Cannot go below 0

            // System.out.print("WEATHER PROVIDER AT: ");
            // System.out.println( Math.round( (n / 100) * 4) );
            
            int weatherAt = (int)Math.round( ( (n / 100) * 4 ));

            if (weatherAt >= 4) weatherAt = 3; // Weather array limitations
            if (weatherAt < 0) weatherAt = 0; // Same
            
            return WeatherProvider.weather[ weatherAt ];
        }
        
}