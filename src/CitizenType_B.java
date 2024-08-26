
/**
 * This subclass is inspired by the fact that in every society there are people who cause trouble
 * which unfortunately leads to deaths not related to Covid in anyway. 
 * In London, there is many cases of street stubbings
 * In the USA cities there are many cases of public shootings, and ofcourse all other societies have some form of crime leading to unexpected deaths
 * . These deaths in real life, occur anytime irrespective of whether it was covid erra.
 * 
 * For this reason, its meaningful to simulate a map with bad people in it. 
 * 
 * 
 * These individuals murder even neighbours of their type(other trouble makers) as they move().
 * Fortunately, they dont have many neighbours to affect. They can DOTHEIRTHING() to two cells adjuscent to them.
 * This means we are going to change possible moves. 
 * 
 * DSince people cant react in a meaningful way when they get mudered, we'll probably  use the react method to collect counts of deths unrelated to covid. 
 * 
 * Lets roll in. 
 * 
 */
import java.util.ArrayList;
import java.awt.Color;

public class CitizenType_B extends Citizen {

    private static final int[][] citizen_Type_B_PossibleMoves = // will help us find 2 adjuscent neighbours
            {
                    { -1, 0 },
                    { 0, 1 },

            };

    public static final Color CITIZEN_TYPE_B_HEALTHY_COLOR = new Color(190, 60, 250);// Helps us see healthy trouble
                                                                                     // makers moving
    // around and doing their thing.

    /**
     * Constructor:
     * 
     * @param myName                  name of the TROUBLE MAKER.
     * @param masked                  are they masked or not
     * @param startingInfectionStatus are they susceptible,infected or recovered.
     * @param newCampus               the campus class object
     * @param startRow                the row where their cell lies
     * @param startColumn             the column where the cell lies
     */

    public CitizenType_B(String myName, boolean masked, int startingInfectionStatus, Campus newCampus, int startRow,
            int startColumn) {
        super(myName, masked, startingInfectionStatus, newCampus, startRow, startColumn);

    }

    @Override
    public void doYourThing() {
        ArrayList<Citizen> type_B_neighbours = getListOfNeighbors();// newly defined TYPE_B neighbours

        if (type_B_neighbours.size() > 0) // Two targets.
        {
            getCell().brieflyMAGENTA();
            activateCrimeMode();

        }

    }

    public void activateCrimeMode() {
        ArrayList<Citizen> type_B_neighbours = getListOfNeighbors();
        for (Citizen cc : type_B_neighbours)// for each of the two neighbors
        {
            this.performCrimeOn(cc); // THIS method perform crime on, suggests action done (from its name). In
                                     // straight language, MURDER!

            cc.react();

        }
    }

    /**
     * 
     * @param victim the individual adjuscent to trouble maker
     */
    public void performCrimeOn(Citizen victim) {
        victim.murdered(); // Due to the crime. They kill litterally everyone so there is no need for (IFs)
                           // filters.
    }

    /**
     * Did you override react? Yes.
     * Is it meaningful? NO. Cause the person expected to react is none exixtent.
     */

    @Override
    public void react() {
        super.react(); // :)
    }

    /**
     * This will get us List of two neighbors
     */

    @Override
    public ArrayList<Citizen> getListOfNeighbors() {
        ArrayList<Citizen> neighbors = new ArrayList<Citizen>();
        Cell myCell = this.getCell();
        int row = myCell.getRow();
        int col = myCell.getCol();

        for (int[] type_A_Move : citizen_Type_B_PossibleMoves) { // Important pivot.
            int newRow = row + type_A_Move[0];
            int newCol = col + type_A_Move[1];

            if (newRow >= 0 && newRow < Campus.NUMBER_OF_ROWS && newCol >= 0 && newCol < Campus.NUMBER_OF_COLUMNS) // Ensures
                                                                                                                   // cell
                                                                                                                   // is
                                                                                                                   // usable.

            {

                Campus helper2 = super.getcampus();

                Cell potentialCell = helper2.getCell(newRow, newCol); // a neighbour could be in this cell.
                if (!potentialCell.getAvailability() && !potentialCell.isWallCell()) {
                    neighbors.add(potentialCell.getCitizen());
                }

            }
        }
        return neighbors;
    }

    // this method overrrides the getHealthyColor of the citizen class.

    @Override
    public Color getHealthyColor() {

        return CitizenType_B.CITIZEN_TYPE_B_HEALTHY_COLOR;

    }

}
