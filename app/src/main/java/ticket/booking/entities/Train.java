package ticket.booking.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private int trainNo;
    private List<List<Integer>> seats;
    private Map<String, Date> stations;

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public int getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(int trainNo) {
        this.trainNo = trainNo;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public Map<String, Date> getStations() {
        return stations;
    }

    public void setStations(Map<String, Date> stations) {
        this.stations = stations;
    }

    public Train(String trainId, int trainNo, List<List<Integer>> seats, Map<String, Date> stations) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stations = stations;
    }
}
