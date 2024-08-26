
/**
 * Changes/Modifications done to the super class Citizen:
 * 
 * We added a static variable DIED help mark that the death of a person. This variable is not very useful however.
 * We added static integers to help us gather statics on death, recovery,murder and infection transmission. 
 * We added the following methods towards the end of this super class
 * 
 * passed_away()
 * gotWell()
 * infection_Count() 
 * murdered() 
 */

import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;

/**
 * This is a general superclass for people on the campus.
 * 
 * It governs how they move, what they do when they move,
 * and how they can infect one another.
 */

public class Citizen {

    private String name;
    public final int ID;
    private Cell currentCell; // Where is the citizen?
    private boolean isWearingMask;
    private int infectionStatus;
    private Campus campus;

    public static final int SUSCEPTIBLE = 0;
    public static final int INFECTED = 1;
    public static final int RECOVERED = 2;

    public static final int DIED = 3; // this variable is not very useful.

    public static final double MASKED_TRANSMISSION_PROBABILITY = 0.10;// this is a totally made up number.
    public static final double UNMASKED_TRANSMISSION_PROBABILITY = 0.80;// this is a totally made up number.
    public static final double MASKED_PROTECTION_FACTOR = 0.50;// this is a totally made up number.

    public static final Color CITIZEN_HEALTHY_COLOR = new Color(0, 220, 200);

    private static int idCount = 1;

    private static final int[][] possibleMoves = {
            { -1, 0 },
            { -1, 1 },
            { -1, -1 },
            { 1, -1 },
            { 1, 0 },
            { 1, 1 },
            { 0, 1 },
            { 0, -1 }
    };

    public static final Random randomNumberGenerator = new Random();

    /*
     * Constructor
     */

    public Citizen(String myName, boolean masked, int startingInfectionStatus, Campus newCampus, int startRow,
            int startColumn) {
        name = myName;
        currentCell = null;
        isWearingMask = masked;
        infectionStatus = startingInfectionStatus;
        campus = newCampus;
        campus.addCitizen(this, startRow, startColumn);

        ID = idCount;
        idCount++;

    }

    /*
     * These
     * counts happen anytime the described instances occurs.
     */
    static int count_Dead = 0;

    static int countOfRecoveredCitizens = 0;
    static int infectionCount = 0;

    static int murdered = 0;

    /**
     * Describes how a Citizen should choose their next cell, and moves them to it.
     * Such a move also initiates doYourThing()
     */

    public void move() {
        // get list of place you could go.
        ArrayList<Cell> availableCells = new ArrayList<Cell>();
        availableCells.add(currentCell);
        int row = currentCell.getRow();
        int col = currentCell.getCol();

        for (int[] possibleMove : possibleMoves) // For every Array, in this system of Arrays.Genious!!!
        {
            int newRow = row + possibleMove[0];
            int newCol = col + possibleMove[1];

            if (newRow >= 0 && newRow < Campus.NUMBER_OF_ROWS && newCol >= 0 && newCol < Campus.NUMBER_OF_COLUMNS)// If
                                                                                                                  // cells
                                                                                                                  // are
                                                                                                                  // within
                                                                                                                  // the
                                                                                                                  // Map,
            {
                Cell potentialCell = campus.getCell(newRow, newCol); // Notice an object from a different class
                                                                     // informing an object from another class!!
                if (potentialCell.getAvailability() && !potentialCell.isWallCell()) {
                    availableCells.add(potentialCell);
                }
            }

        }

        // now pick one and move there.
        int nn = availableCells.size(); // Specification of Upper bound for random object.
        int randomIndex = randomNumberGenerator.nextInt(nn);// Indices of cell elements in ArrayList Available cells.
        Cell oldCell = currentCell;
        Cell newCell = availableCells.get(randomIndex);
        setCell(newCell); // new cell becomes current cell. Check method at the bottom.
        oldCell.removeCitizen();
        newCell.addCitizen(this);
        doYourThing();

        Tester.pause(Tester.sleepTime);

    }

    /**
     * A general method of what to do when arriving at a cell.
     * Should be overloaded or overridden in subclasses
     * depending on the intended behavoir of instances of that
     * subclass.
     */
    public void doYourThing() {

        ArrayList<Citizen> neighbors = this.getListOfNeighbors();

        // if you're infected, cough to spread it
        if (infectionStatus == INFECTED) {
            if (neighbors.size() > 0) // Genius!
            {
                this.getCell().brieflyYellow();
                cough();// ??
            }
        }
    }

    /**
     * Opens up options to spread disease to people
     * in neigboring cells.
     */
    public void cough() {
        ArrayList<Citizen> neighbors = getListOfNeighbors();
        for (Citizen cc : neighbors) {
            this.coughAt(cc);
            cc.react();
        }

    }

    /**
     * This is an empty method as of right now
     * It is intended to be overridden by subclasses
     * so that subclasses can do their own unique thing
     * as a reaction to someone around them coughing. *
     */

    public void react() {
        /*
         * for now, do nothing. This can, of course, be
         * overriden in a subclass
         */
    }

    /**
     * Uses random numbers and the current states of SIR for
     * both participants to determine whether infection spreads.
     */

    public void coughAt(Citizen potentialVictim) {
        if (this.infectionStatus == Citizen.INFECTED && potentialVictim.getInfectionStatus() == Citizen.SUSCEPTIBLE) {
            double threshold;
            // adjust for whether cougher is wearing mask
            if (this.isWearingMask) {
                threshold = MASKED_TRANSMISSION_PROBABILITY;
            } else {
                threshold = UNMASKED_TRANSMISSION_PROBABILITY;
            }
            // now adjust for whether potential victim is masked
            if (potentialVictim.isWearingMask) {
                threshold *= MASKED_PROTECTION_FACTOR;
            }

            double rand = randomNumberGenerator.nextDouble(); // Important Pivot!!

            if (rand < threshold) {
                potentialVictim.changeInfectionStatus(Citizen.INFECTED);
                potentialVictim.infection_Count();// updates data of infected people
            }
        }
    }

    /**
     * Obtains a list of all Citizens in the 8 neighboring cells.
     */

    public ArrayList<Citizen> getListOfNeighbors() {
        ArrayList<Citizen> neighbors = new ArrayList<Citizen>();
        int row = currentCell.getRow();
        int col = currentCell.getCol();

        for (int[] possibleMove : possibleMoves) {
            int newRow = row + possibleMove[0];
            int newCol = col + possibleMove[1];

            if (newRow >= 0 && newRow < Campus.NUMBER_OF_ROWS && newCol >= 0 && newCol < Campus.NUMBER_OF_COLUMNS) {
                Cell potentialCell = campus.getCell(newRow, newCol);
                if (!potentialCell.getAvailability() && !potentialCell.isWallCell())// meaning there is someone there
                {
                    neighbors.add(potentialCell.getCitizen());// Good!!
                }
            }

        }

        return neighbors;
    }

    /*
     * Accessor
     */
    public Color getHealthyColor() {
        return CITIZEN_HEALTHY_COLOR;
    }

    /*
     * Accessor
     */
    public int getID() {
        return ID;
    }

    /*
     * Accessor
     */
    public String getName() {
        return name;
    }

    /*
     * Accessor
     */
    public Cell getCell() {
        return currentCell;
    }

    /*
     * Accessor
     */
    public boolean isWearingMask() {
        return isWearingMask;
    }

    /*
     * Accessor
     */
    public int getInfectionStatus() {
        return infectionStatus;
    }

    /*
     * Changes from one cell to another.
     */
    public void setCell(Cell newCell) {
        currentCell = newCell;
    }

    /*
     * Changes infection status.
     */
    public void changeInfectionStatus(int newStatus) {
        infectionStatus = newStatus;
    }

    public Campus getcampus() {
        return campus;
    }

    /**
     * Some people unfortunately die when they have been passed by cetain strands of
     * citizen type_A
     */

    public void passed_away() {
        Cell evacuatingCell = getCell();
        evacuatingCell.removeCitizen();
        count_Dead = count_Dead + 1;

    }

    /**
     * Other people get well
     */

    public void gotWell() {
        System.out.println("thank you ");
        countOfRecoveredCitizens = countOfRecoveredCitizens + 1;
    }

    /**
     * updates data each time a person is infected.
     */
    public void infection_Count() {
        infectionCount = infectionCount + 1;
    }

    /**
     * Helps us adjust campus whenever murder occurs
     */
    public void murdered() {
        Cell evacuatingCell = getCell();
        evacuatingCell.removeCitizen();
        murdered = murdered + 1;

    }

}
