
/**
 * Modifications to Cell
 *  we added public static final colors: PINK, ORANGE AND MAGENTA, TO display Briefly as citizens move  from cell to cell.
 * 
 * We added the following methods towards the end of the class to help display the colors mentioned above whenever they are called;
 * brieflyPink()
 * brieflyORANGE()
 * brieflyMAGENTA() 
 */

import javax.swing.*;//The * is a lazy import:  I'm importing everything in the swing package.
import java.awt.*;//Yep, still being lazy.

/*
 *The purpose of class is to ensure the proper functionality of the cell and to make it interactive
 */

public class Cell {

    public static final Color WALL_COLOR = Color.BLACK;
    public static final Color EMPTY_COLOR = Color.GRAY;
    public static final Color INFECTED_COLOR = Color.RED;
    public static final Color DO_YOUR_THING_COLOR = Color.YELLOW;

    // Do your things colors that i have added.
    public static final Color PASSING_BY = Color.PINK;
    public static final Color DOCTOR_AT_WORK = Color.ORANGE;
    public static final Color TROUBLE_COLOR = Color.MAGENTA;

    private JButton button; // ???
    private boolean occupied;
    private boolean isWallCell;// indicating whether the cell is a wall or if free for people to be on.
    private final int row;
    private final int col;
    private Citizen currentCitizen; // This instance variable is of type citizen

    /**
     * Constructor
     */
    public Cell(int rr, int cc, boolean wallCell) {
        row = rr;
        col = cc;
        occupied = false; // We can manipulate these in our subclassses if we want.
        isWallCell = wallCell;
        button = new JButton();
        button.setPreferredSize(new Dimension(Campus.CELL_SIZE, Campus.CELL_SIZE));
        button.setMargin(new Insets(0, 0, 0, 0));
        if (isWallCell == true) {
            button.setBackground(WALL_COLOR);
        } else {
            button.setBackground(EMPTY_COLOR);
        }
        currentCitizen = null;
    }

    /**
     * Accessor method
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Changes button text
     */
    public void setText(String text) {
        button.setText(text); // further manipulation of the button object
    }

    /**
     * Sets the color of the button
     * 
     */

    public void setColor(Color newColor) // Enables you to set new color of a button. Could be of use.
    {
        button.setBackground(newColor);
    }

    /**
     * Checks to see if the cell is empty
     */

    public boolean getAvailability() {
        return !occupied;
    }

    /**
     * Accessor method
     */
    public boolean isWallCell() {
        return isWallCell;
    }

    /**
     * Accessor method
     */

    public int getRow() {
        return row;
    }

    /**
     * Accessor method
     */
    public int getCol() {
        return col;
    }

    /**
     * Adds a citizen to occupy this cell
     */

    public void addCitizen(Citizen citizen) {
        currentCitizen = citizen; // Instance variabled changed.
        occupied = true; // Instance variable changed
        button.setText(" " + citizen.getID()); // Ok!
        if (citizen.getInfectionStatus() == Citizen.INFECTED) {
            button.setBackground(INFECTED_COLOR);
        } else {
            button.setBackground(citizen.getHealthyColor());
        }
    }

    /**
     * Removes a Citizen from the cell.
     */

    public void removeCitizen() 
    {
        currentCitizen = null;
        occupied = false;
        button.setBackground(EMPTY_COLOR);
        button.setText("");
    }

    /**
     * Accessor method
     */

    public Citizen getCitizen() {
        return currentCitizen;
    }

    /**
     * Briefly turns a cell yellow, then back to its previous color.
     * Used to indicate some sort of action or happening.
     */

    public void brieflyYellow() {
        Color oldColor = button.getBackground();
        button.setBackground(DO_YOUR_THING_COLOR);
        Tester.pause(Tester.sleepTime);
        button.setBackground(oldColor);
    }

    /**
     * THis specifically helps with those citizens who know their viral loads
     */
    public void brieflyPink() {
        Color oldColor = button.getBackground();
        button.setBackground(PASSING_BY);
        Tester.pause(Tester.sleepTime);
        button.setBackground(oldColor);
    }

    /**
     * To help us see doctors at work
     */
    public void brieflyORANGE() {
        Color oldColor = button.getBackground();
        button.setBackground(DOCTOR_AT_WORK);
        Tester.pause(Tester.sleepTime);
        button.setBackground(oldColor);
    }

    /**
     * To help us seebad people at work
     */
    public void brieflyMAGENTA() {
        Color oldColor = button.getBackground();
        button.setBackground(TROUBLE_COLOR);
        Tester.pause(Tester.sleepTime);
        button.setBackground(oldColor);
    }

}
