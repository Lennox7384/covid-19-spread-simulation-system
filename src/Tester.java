
/**
 * Modifications we made to the tester class
 * 
 * Changed map to 40 by 40
 * Total time increased to 250
 * Added objects of subclasses and then statistical analysis towards the end of the main arguments.
 * 
 * Synopsis provide atthe very bottom
 */

import java.util.ArrayList;

/**
 * The purpose of this class is to define the walls of the campus,
 * create some Citizens to put on the campus, and then to turn them loose.
 * 
 * @author Nathan Axvig
 */

public class Tester {

        public static final int sleepTime = 1;
        public static final int TOTAL_TIME = 250;

        /**
         * The main method.
         */
        public static void main(String[] args) {
                boolean[][] map = {
                                { false, false, false, false, false, false, false, false, false, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { true, true, true, true, true, true, true, true, true, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false },
                                { false, false, false, false, false, false, false, false, true, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, true, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, true, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, true, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, true, false, false, false,
                                                false, false, true,
                                                false, false, true, false, false, false, false, false, false, false,
                                                false, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false },
                                { false, false, false, false, false, false, false, false, true, true, true, true, true,
                                                true, true,
                                                false, false, true, false, false, false, false, false, false, false,
                                                false, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false },
                                { false, false, false, false, false, false, false, false, false, false, false, false,
                                                false, false,
                                                false, false, false, true, false, false, false, false, false, false,
                                                false, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false },
                                { false, false, false, false, false, false, false, false, false, false, false, false,
                                                false, false,
                                                false, false, false, true, false, false, false, false, false, false,
                                                false, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false },
                                { false, false, false, false, false, false, false, false, false, false, false, false,
                                                false, false,
                                                false, false, false, true, false, false, false, false, false, false,
                                                false, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false },
                                { false, false, false, false, false, false, false, false, false, false, true, true,
                                                true, true, true,
                                                true, true, true, false, false, false, false, false, false, false,
                                                false, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false },
                                { false, false, false, false, false, false, false, false, false, false, true, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, true, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, true, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, true, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, true, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, true, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },

                                { false, false, false, false, false, false, false, false, false, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { true, true, true, true, true, true, true, true, true, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false },
                                { false, false, false, false, false, false, false, false, true, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, true, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, true, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, true, false, false, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, true, false, false, false,
                                                false, false, true,
                                                false, false, true, false, false, false, false, false, false, false,
                                                false, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false },
                                { false, false, false, false, false, false, false, false, true, true, true, true, true,
                                                true, true,
                                                false, false, true, false, false, false, false, false, false, false,
                                                false, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false },
                                { false, false, false, false, false, false, false, false, false, false, false, false,
                                                false, false,
                                                false, false, false, true, false, false, false, false, false, false,
                                                false, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false },
                                { false, false, false, false, false, false, false, false, false, false, false, false,
                                                false, false,
                                                false, false, false, true, false, false, false, false, false, false,
                                                false, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false },
                                { false, false, false, false, false, false, false, false, false, false, false, false,
                                                false, false,
                                                false, false, false, true, false, false, false, false, false, false,
                                                false, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false },
                                { false, false, false, false, false, false, false, false, false, false, true, true,
                                                true, true, true,
                                                true, true, true, false, false, false, false, false, false, false,
                                                false, false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false },
                                { false, false, false, false, false, false, false, false, false, false, true, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, true, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, true, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, true, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, true, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                                { false, false, false, false, false, false, false, false, false, false, true, false,
                                                false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false,
                                                false, false, false, false, false, false, false, false, false, false,
                                                false, false, false },
                };
                Campus campus = new Campus(map);
                campus.initializeCampus();
                campus.showCampus();
                ArrayList<Citizen> population = new ArrayList<Citizen>();

                boolean masked = true;
                boolean unmasked = false;

                int half_Of_Rows = Campus.NUMBER_OF_ROWS / 2;
                int half_Of_Columns = Campus.NUMBER_OF_COLUMNS / 2;

                // this below, adds unmasked susceptible citizens on the upper half of the
                // Campus.

                for (int ii = 0; ii < half_Of_Rows; ii += 3) {
                        for (int jj = 0; jj < Campus.NUMBER_OF_COLUMNS; jj += 2) {
                                if (map[ii][jj] == false)// then a cell is not a wall.
                                {
                                        population.add(new Citizen("Citizen", unmasked, Citizen.SUSCEPTIBLE, campus, ii,
                                                        jj));
                                }
                        }
                }

                // this below, adds masked susceptible citizens on the bottom half of the
                // Campus.

                for (int ii = half_Of_Rows; ii < Campus.NUMBER_OF_ROWS; ii += 3) {
                        for (int jj = 0; jj < Campus.NUMBER_OF_COLUMNS; jj += 2) {
                                if (map[ii][jj] == false)// then a cell is not a wall.
                                {
                                        population.add(new Citizen("Citizen", masked, Citizen.SUSCEPTIBLE, campus, ii,
                                                        jj));
                                }
                        }
                }

                // Anyone INFECTED is RED in Color.
                population.add(new Citizen("", masked, Citizen.INFECTED, campus, 1, 1)); // This guy is RED IN COLOR.

                // Below we have just scattered masked doctors all over campus. To make them
                // work, we ensured they are not sick.
                population.add(new Doctor("", masked, Doctor.SUSCEPTIBLE, campus, 1, 7)); // Color DEEP BLUE
                population.add(new Doctor("", masked, Doctor.SUSCEPTIBLE, campus, 20, 15)); // Color DEEP BLUE

                population.add(new Doctor("", masked, Doctor.RECOVERED, campus, 2, 11)); // Color DEEP BLUE
                population.add(new Doctor("", masked, Doctor.RECOVERED, campus, 38, 19)); // Color DEEP BLUE

                // Below we are going to scatter four citizens of type A. 2 of them Full Blown,
                // Two of them not fullBlown.
                // This is to ensure they perform both versions of their task.

                // these are intended to kill unmasked infected citizens.
                population.add(new CitizenType_A("", masked, CitizenType_A.INFECTED, campus, 24, 19,
                                CitizenType_A.FULL_BLOWN)); // RED
                population.add(new CitizenType_A("", masked, CitizenType_A.INFECTED, campus, 24, 17,
                                CitizenType_A.FULL_BLOWN)); // RED

                // These are intended to infect their unmasked susceptible neighbors
                population.add(new CitizenType_A("", masked, CitizenType_A.INFECTED, campus, 36, 14,
                                CitizenType_A.INCUBATION_STAGE)); // RED
                population.add(new CitizenType_A("", masked, CitizenType_A.INFECTED, campus, 36, 17,
                                CitizenType_A.WINDOW_STAGE)); // RED

                // Below, we are introducing Citizentype B. The trouble makers in the society.

                population.add(new CitizenType_B("", masked, CitizenType_B.SUSCEPTIBLE, campus, 30, 9)); //

                population.add(new CitizenType_B("", masked, CitizenType_B.SUSCEPTIBLE, campus, 28, 9));

                for (int time = 0; time < TOTAL_TIME; time++) {
                        for (Citizen cc : population) {
                                cc.move();
                        }
                }

                // Statistical analysis
                System.out.println("No. of people who died: " + Citizen.count_Dead);
                System.out.println("\n");
                System.out.println("No. of people who recovered: " + Citizen.countOfRecoveredCitizens);
                System.out.println("\n");
                System.out.println("No. of IfectionTransmissions: " + Citizen.infectionCount);
                System.out.println("\n");
                System.out.println("No. of people  murdered (non-covid related deaths): " + Citizen.murdered);
        }

        /**
         * This method pauses the execution of the program for pauseTime milliseconds.
         * It keeps things from running so fast our eyes can't see them. Large
         * pauseTimes
         * cause the animation to run slowly, small pauseTimes cause the animation to
         * run fast.
         */
        public static void pause(int pauseTime) {
                try {
                        Thread.sleep(pauseTime);
                } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                }
        }

        /**
         * How did the test go?
         * 
         * I coudn't get my eyes off the screen whenever I pressed the run button.
         * It was such a nice dance between people getting treated by the Blue colored
         * Doctors, Citizen type A spreading the disease from bottom right of the map,
         * and Citizen Type B killing people
         * 
         * One thing that was really fascinating is seeing doctors, the four of them
         * eradicate the disease completely at certain runs. If you are lucky you might
         * land such a run
         * 
         * 
         * Notice the effect of murder perpetrated by Citizen type B AND A. After
         * leaving the program to run for a while you will realise depopoulation on
         * campus.The number of people certainly reduced.
         * 
         * This reduction in population is backed up by our numbers. Once the program
         * stops, check the console and you see a relationship between the depopulation
         * on the map, and the number of deaths and muders reported.
         * 
         * General: I learnt a lot from reverse engineering your template to come up
         * with similar code. This project was one of a kind!
         * 
         * Worries: I have zero idea how to store and manipulate stored data in Java. My
         * final project could be a nightmare or consume moretime than allocated.
         * 
         * 
         * 
         * 
         */

}
