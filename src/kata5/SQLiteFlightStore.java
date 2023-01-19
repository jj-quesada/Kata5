package kata5;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Iterator;

public class SQLiteFlightStore implements FlightStore{

    private final File file;
    private final Connection connection;

    public SQLiteFlightStore(File file) throws SQLException{
        this.file = file;
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
    }
    
    @Override
    public Iterable<Flight> getFlights() {
        return new Iterable<Flight>() {
            
            @Override
            public Iterator<Flight> iterator() {
                try{
                    return createIterator();
                }catch (SQLException ex){
                    return emptyIterator();
                }
            };

            private Iterator<Flight> emptyIterator() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

        
        };
    }
    
    private Iterator<Flight> createIterator() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM flights");
        return new Iterator<Flight>(){
            Flight nextFlight = nextFlight(rs);

            @Override
            public boolean hasNext() {
                return nextFlight != null;
            }

            @Override
            public Flight next() {
                Flight flight = nextFlight;
                nextFlight = nextFlight(rs);
                return flight;
            }

            
        };
    }
    
    private Flight nextFlight(ResultSet rs) {
        if(!next(rs)) return null;
        return new Flight(
            DayOfWeek.of(getIntField(rs,"DAY_OF_WEEK")),
            parseTime(getIntField(rs,"DEP_TIME")),
            getIntField(rs,"DEP_DELAY"),
            parseTime(getIntField(rs,"ARR_TIME")),
            getIntField(rs,"ARR_DELAY"),
            getIntField(rs,"AIR_TIME"),
            getIntField(rs,"DISTANCE"),
            getIntField(rs,"CANCELLED") == 1,
            getIntField(rs,"DIVERTED") == 1
        );
    }

    private boolean next(ResultSet rs) {
        try {
            boolean next = rs.next();
            if (next) return true;
            rs.close();            
        } catch (SQLException ex) {
        }
        return false;
    }
    
    private int getIntField(ResultSet rs, String field) {
        try {
            return rs.getInt(field);
        } catch (SQLException ex) {
            return 0;
        }
    }

    private LocalTime parseTime(int d) {
        return LocalTime.of(d / 100 % 24, d%100);
    }
}
