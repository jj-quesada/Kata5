package kata5;

import histogram.Histogram;
import histogram.HistogramDisplay;
import java.io.File;
import java.sql.SQLException;


public class Kata5 {

    public static void main(String[] args) throws SQLException {
        FlightStore store = new SQLiteFlightStore(new File("flights.db"));
        Histogram<Integer> histogram = new Histogram<>();
        
        for (Flight flight : store.getFlights()) 
            histogram.increment(flight.getArrivalTime().getHour());;
   
        HistogramDisplay histogramDisplay = new HistogramDisplay("Histograma: Horarios de Vuelos", histogram);
        histogramDisplay.execute();
    }
    
}
