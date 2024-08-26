
/**
 * Introducing citizen type A.
 * The idea of this subclass was inspired by the undeniable real world fact that  the contemporary society exist in class categories.
 * There are the poor, the middle class and the upper class(the rich).Economics would go farther and pupport that there are upper middle- these are not yet really rich
 * to enter club upperclass, but they are somewhat above the normal middle class person.Here, we will acknowledge the existence of categories but not really focus on what they are.
 * These categories come with priveledges; the poor and the rich have constrasting lifestyles and thats just a fact we cant deny.(Hint: Health care and disease diagnosis differ depending on social ranks)
 * 
 * In our imaginary world hit by COVID, this group of Individuals, Citizen Type A, GET BETTER DIAGNOSIS WHEN INFECTED.
 * They are able to know the VIRAL LOAD of Covid in them. I suspect these people visit high tech hospitals and laboratories that a normal citizen can't visit for some reason.
 * The fact that they are able to know their VIRAL LOAD does not mean that they are infected already. Some of them are just SUSESTIPLE, RECOVERED or just not sick.
 * 
 * These people can passBy() you as they Move() around campus. // We are still debating whether to adopt 8 adjascent cells or 4 adjustcent cells for neighbourhood. We'll clarify later..
 * 
 * If you've been passedBy, then, depending on (their viral load && infection status), and (your manipulated infection data leading upto threshold), you can DIE, if you don't seeDoctor() immediately.
 * 
 * At this point we expect seeDoctor() to be a reaction. 
 * 
 * We need to give everyone (Every Citizen) a chance to DIE, therefore, we are going to add a static int in Citizen class (probably 3), that defines Death. 
 * 
 *  In summary, viral Load aspect Citizen type A, can cause death to some people if they come accross him this citizen. 
 * 
 */

import java.util.ArrayList;
import java.awt.Color;

public class CitizenType_A extends Citizen {
    private String viralLoad; // This Instance variable is specific for this subclass.

    public static final String NOTSICK = "undetectable";
    public static final String WINDOW_STAGE = "lessVirus";
    public static final String INCUBATION_STAGE = "mediumlevel"; // these define what the viral load could be.
    public static final String FULL_BLOWN = "highlevel";

    public static final Color CITIZEN_TYPE_A_HEALTHY_COLOR = new Color(90, 220, 10); // Just a place holder, we will
                                                                                     // get back to it.

    /**
     * Since we can't delete /manipulate the length of the Super class 2D
     * possibleMoves to fit want,
     * The only solution around this is to create a new 2D array of possibleMoves to
     * fit the intention.
     * We acknowledge that the inherited variable possibleMoves is going to lay
     * domant and unused.
     * It's work will be taken by the new 2D Array of moves. Let's call it
     * Citizen_Type_A_PossibleMoves
     * It will help us find neighbours that we want.
     */

    private static final int[][] citizen_Type_A_PossibleMoves = // will help us find 4 adjuscent neighbours
            {
                    { -1, 0 },
                    { 0, 1 },
                    { 1, 0 },
                    { 0, -1 }

            };

    /**
     * 
     * @param myName                  name of the citizen
     * @param masked                  are they masked or not
     * @param startingInfectionStatus are they susceptible,infected or recovered.
     * @param newCampus               the campus class object
     * @param startRow                the row where their cell lies
     * @param startColumn             the column where the cell lies
     * @param viral_load              the viral load.
     */

    /**
     * Constructor of the objects of the subclass
     * viral_load that the user will input, should be one of the following words
     * undetectable, lessVirus, mediumlevel or highlevel, without which this
     * subclass might not work as intended.
     */

    public CitizenType_A(String myName, boolean masked, int startingInfectionStatus, Campus newCampus, int startRow,
            int startColumn, String viral_load) {

        super(myName, masked, startingInfectionStatus, newCampus, startRow, startColumn);

        viralLoad = viral_load; // now we've got the viral load initialised.

    }

    @Override
    public void doYourThing() {
        ArrayList<Citizen> type_A_neighbours = getListOfNeighbors();// this is the newly defined neighbours

        if (!viralLoad.equals(NOTSICK))// Meaning all others loads of virus.
        {
            if (type_A_neighbours.size() > 0) // There must be someone in close proximity.
            {
                getCell().brieflyPink();
                passBy();

            }
        }

    }

    public void passBy() {
        ArrayList<Citizen> type_A_neighbours = getListOfNeighbors();
        for (Citizen cc : type_A_neighbours)// for each of the four neighbors
        {
            this.passed_By(cc); // method passed_By, suggests action done (from its name). This should elicite a
                                // reaction.

            cc.react(); // we will come back to check something here.

        }

    }

    /**
     * What happens if Citizen_Type A passes by you?
     * This is the pivital part.
     * Depending on their viral load and victims threshold, the victim either dies
     * instantly, therefore adopting super.react()
     */

    public void passed_By(Citizen potentialVictim) {
        if (potentialVictim instanceof Citizen) { // genius!

            if (this.viralLoad.equals(FULL_BLOWN) && potentialVictim.getInfectionStatus() == Citizen.INFECTED
                    && !potentialVictim.isWearingMask()) {
                potentialVictim.passed_away(); // unfortunately, a full blown viral load is so toxic that it instantly
                                               // kills an infected citizen not wearing mask.
                                               // for some reason tho, the carrier of this toxicity does not die of it.
                                               // We don't want to do ID COUNT -- because once you have an ID number, it
                                               // shopuld
            }
            if (!this.viralLoad.equals(FULL_BLOWN) && potentialVictim.getInfectionStatus() != Citizen.INFECTED
                    && !potentialVictim.isWearingMask()) {
                potentialVictim.changeInfectionStatus(Citizen.INFECTED);
                potentialVictim.infection_Count(); // updates data of infected people
                                                   // If you are not wearing mask and you come
                                                   // across type A person who is not at full
                                                   // blown but sick, be sure of getting infected.

            }

        }

    }

    /**
     * react() method happens after passed_By() method is tested and some people
     * eliminated due to death
     * The remaining Citizens are either masked or unmasked with the masked ones
     * certainly infected.
     * In the Implementation of react() below, we will give the living masked and
     * unmasked an opportunity to react to their encounter with the horrible
     * citizentype A.
     * 
     * think of reallife mass education on TV on how to handle covid when
     * infected/exposed etc..,and then some people just didn't care.
     * This reaction emulates real life human behavior. We have rebels and those who
     * conform to make positive change.
     */
    @Override

    public void react() {
        if (this.isWearingMask() && this.getInfectionStatus() == SUSCEPTIBLE) // these individuals are somehow modest
                                                                              // and well behaved, but vulnerable.
                                                                              // they have their mask on but are
                                                                              // susceptible. They choose to respecfully
                                                                              // tell Citizen type A
                                                                              // to stay home given their publicly known
                                                                              // viral load.
        {
            System.out.println("Stay home to protect yourself and others.Your viral load puts society in danger.");
        }
        if (!this.isWearingMask()) { // Doesn't wear mask, went ahead and hugged Citizen type A, a complete stranger.
                                     // according to passed_By() method, we know clearly that they are infected given
                                     // their absense of mask.
            System.out.println("Hugged Citizen type A");
        }
    }

    /**
     * citizen type A passes by people, however, they passBy four people adjuscent
     * to them.
     * This is slightly different from your code where citizen coughs at 8 people
     * adjuscent to them.,
     * therefore we are going to override the getListOfNeighbours method below.
     */

    @Override
    public ArrayList<Citizen> getListOfNeighbors() {
        ArrayList<Citizen> neighbors = new ArrayList<Citizen>();
        Cell myCell = this.getCell();
        int row = myCell.getRow();
        int col = myCell.getCol();

        for (int[] type_A_Move : citizen_Type_A_PossibleMoves) {
            int newRow = row + type_A_Move[0];
            int newCol = col + type_A_Move[1];

            if (newRow >= 0 && newRow < Campus.NUMBER_OF_ROWS && newCol >= 0 && newCol < Campus.NUMBER_OF_COLUMNS) // basically,
                                                                                                                   // it
                                                                                                                   // ensures
                                                                                                                   // that
                                                                                                                   // new
                                                                                                                   // row
                                                                                                                   // and
                                                                                                                   // new
                                                                                                                   // column
                                                                                                                   // actually
                                                                                                                   // exist
                                                                                                                   // on
                                                                                                                   // campus.
            {

                Campus helper = super.getcampus();// we added this method at the very end of the superclass to help us
                                                  // access that Instance variable of type Campus.

                Cell potentialCell = helper.getCell(newRow, newCol); // a neighbour could be in this cell.
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

        return CitizenType_A.CITIZEN_TYPE_A_HEALTHY_COLOR;

    }

}
