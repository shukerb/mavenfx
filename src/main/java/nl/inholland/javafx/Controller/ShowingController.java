package nl.inholland.javafx.Controller;

import nl.inholland.javafx.Model.Room;
import nl.inholland.javafx.Model.Showing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShowingController {

    private final List<Showing> showings;

    //extra time needed to leave and join a showing
    final int EXTRA_TIME = 15;

    public ShowingController(DataBase db) {
        this.showings = db.getShowings();
    }

    public void addShowing(Showing showing) {
        this.showings.add(showing);
    }

    public void removeShowing(Showing showing) {
        this.showings.remove(showing);
    }

    public List<Showing> showingsByRoom(Room room) {
        List<Showing> roomShowingsList = new ArrayList<>();
        for (Showing showing : this.showings) {
            if (showing.getRoom().equals(room))
                roomShowingsList.add(showing);
        }
        return roomShowingsList;
    }

    public void editShowing(Showing editedShowing) {
        for (Showing showing : this.showings) {
            if (showing.getId().equals(editedShowing.getId())) {
                showing.setAvailableTickets(editedShowing.getAvailableTickets());
                showing.setMovie(editedShowing.getMovie());
                showing.setRoom(editedShowing.getRoom());
                showing.setStartTime(editedShowing.getStartTime());
            }
        }
    }

    public boolean checkIfShowingsOverlap(Showing showing) {
        Room room = showing.getRoom();
        LocalDateTime enterShowingTime = showing.getStartTime().minusMinutes(EXTRA_TIME);
        LocalDateTime leaveShowingTime = showing.getEndTime().plusMinutes(EXTRA_TIME);
        for (Showing s : this.showings) {
            if (s.getRoom().equals(room)) {
                return enterShowingTime.isBefore(s.getEndTime().plusMinutes(EXTRA_TIME)) && s.getStartTime().plusMinutes(EXTRA_TIME).isBefore(leaveShowingTime);
            }
        }
        return false;
    }

    public void sellShowingTickets(UUID showingID, int numberOfTickets) {
        Showing showing = getShowingById(showingID);
        if (showing != null) {
            showing.setAvailableTickets(showing.getAvailableTickets() - numberOfTickets);
        }
    }

    private Showing getShowingById(UUID showingID) {
        for (Showing showing : showings) {
            if (showing.getId().equals(showingID)) {
                return showing;
            }
        }
        return null;
    }


}
