package nl.inholland.javafx.Controller;

import nl.inholland.javafx.Model.Movie;
import nl.inholland.javafx.Model.Room;
import nl.inholland.javafx.Model.Showing;

import java.util.ArrayList;
import java.util.List;

public class ShowingController {

    private List<Showing> showings;

    public ShowingController(DataBase db) {
        this.showings= db.getShowings();
    }

    public void addShowing(Showing showing){
        showings.add(showing);
    }

    public List<Showing> showingsByRoom(Room room){
        List <Showing> roomShowingsList = new ArrayList<>();
        for (Showing showing:this.showings) {
            if (showing.getRoom().equals(room))
                roomShowingsList.add(showing);
        }
        return roomShowingsList;
    }
    public boolean checkIfShowingsOverlap(){
        return false;
    }


}
