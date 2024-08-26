import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class Doctor holds a type of citizens who treat() citizens with covid.
 * However, when you get treated, it doesn't neccessarily mean that you will
 * heal()
 * Doctors treat() INFECTED people at 8 squares adjascent to them. This is the
 * DOYOURTHING() VERSION of in this subclass.
 * The treated people, depending on their recovery threshhold(crunched
 * combination of infected person's and doctor's recovery and boost
 * probabilities respectively), either DIE or RECOVER.
 * 
 * The dead people obviously cannnot react() in a meaningful way, so here we
 * might just do this, super.react()
 * Those who recovered, will react meaningfully by: writing a thank you note to
 * the doctor. We will just print something on the console
 * We could count the thank you notes as they get written to give us an idea of
 * how many people are recovering, or count the number of people dying as well.
 * 
 * Let's see how this rolls out.
 */

public class Doctor extends Citizen {

    /**
     * The fact that a doctor is masked or not,
     * and the fact that an infected patient is masked or not, determine the
     * patient's DEATH / RECOVERY CHANCES.
     */
    public static final double MASKED_PATIENT_RECOVERY_CHANCE = 0.8;
    public static final double UNMASKED_PATIENT_RECOVERY_CHANCE = 0.1;
    public static final double MASKED_DOCTOR_RECOVERY_BOOST = 0.5;
    public static final double UNMASKED_DOCTOR_RECOVERY_BOOST = 0.3; // Someone might argue that since the dotor is
                                                                     // unmasked, their recovery boost to the patient
                                                                     // should be 0
                                                                     // The argument is valid, however since this is a
                                                                     // "DOCTOR" expected to treat we let them have a
                                                                     // boost of atmost 0.3.

    public static final Color DOCTOR_HEALTHY_COLOR = new Color(40, 27, 250);// Will help us see Doctors roaming on the
                                                                            // map. We just guessed these color codes.
                                                                            // We might change.

    public static final Random randomnumbergenerator2 = new Random();// will create random number to help determine who
                                                                     // dies and who recovers. I wonder tho, the
                                                                     // possibility of inheriting such variables from
                                                                     // the superclass, cause the super class has almost
                                                                     // a similar declaration.

    /**
     * The constructor of a Doctor's class object.
     * 
     * @param myName                  name of the doctor
     * @param masked                  are they masked or not
     * @param startingInfectionStatus are they susceptible,infected or recovered.
     * @param newCampus               the campus class object
     * @param startRow                the row where their cell lies
     * @param startColumn             the column where the cell lies
     * 
     */

    public Doctor(String myName, boolean masked, int startingInfectionStatus, Campus newCampus, int startRow,
            int startColumn)

    {
        super(myName, masked, startingInfectionStatus, newCampus, startRow, startColumn);

    }

    // overriding the DOYOURTHING to make doctors treat.
    /**
     * The doctor must be valid i.e. not infected
     * And there must be infected citizens for him to treat
     */

    @Override
    public void doYourThing() {
        ArrayList<Citizen> potentialPatients = this.getListOfNeighbors(); // real people in 8 adjascent cells. Check
                                                                          // superclass.Not walls.Not empty cells.

        if (this.getInfectionStatus() != Citizen.INFECTED)// The doctor, atleast, must not know that they are infected.
                                                          // If they are, and they know, they dont get a chance to
                                                          // treat.
        {
            if (potentialPatients.size() > 0) {
                this.getCell().brieflyORANGE(); // Indicates that legal doctor is at work
                treat();
            }
        }

    }

    public void treat() {
        ArrayList<Citizen> potentialPatients = this.getListOfNeighbors();

        for (Citizen eachPatient : potentialPatients) {
            this.treatment_Done_To(eachPatient);
            eachPatient.react();
        }
    }

    // Here is where magic happens. How does this treatment work to result to either
    // Death or recovery.
    // Remember the recovery probabilities from the top of this file.

    public void treatment_Done_To(Citizen admittedPatient) {
        if (this.getInfectionStatus() != Citizen.INFECTED && admittedPatient.getInfectionStatus() == INFECTED)
            ; // Just to avoid malicious use of this method, we check again To ensure that the
              // interacting parties is an infected patient and a not SICK DOCTOR.

        double recoveryThreshold;

        if (admittedPatient.isWearingMask()) {
            recoveryThreshold = MASKED_PATIENT_RECOVERY_CHANCE;
        } else {
            recoveryThreshold = UNMASKED_PATIENT_RECOVERY_CHANCE;
        }

        if (this.isWearingMask()) {
            recoveryThreshold *= MASKED_DOCTOR_RECOVERY_BOOST;

        }

        else {
            recoveryThreshold *= UNMASKED_DOCTOR_RECOVERY_BOOST;
        }

        double doOrDieNumber = randomnumbergenerator2.nextDouble() * 0.5; // to restrict the range between 0 and 0.5.

        if (recoveryThreshold > doOrDieNumber)

        {
            admittedPatient.changeInfectionStatus(Citizen.RECOVERED);
        } else {
            admittedPatient.changeInfectionStatus(Citizen.DIED);
            // then send them out of the map i.e. non existent.
            admittedPatient.passed_away(); // by the way, we are secretly counting dying citizens. check the
                                           // implementation of this method in the superclass.
        }

    }

    @Override
    public void react() {
        /*
         * a citizen who passed away is non existent, the arraylist potentialPatients
         * shrinks
         * Therefore there is no need to explicitly mention them, those who get a chance
         * to use this method are the recovered ones.
         * 
         * Once you recover there is a great need to thank the doctors who worked had to
         * eradicate the virus out of you.
         * write a thank you note to them.
         * we keep count of recovered citizens as well.
         */

        super.gotWell();
    }

    // Help's us see doctor's roaming on the Map.
    @Override
    public Color getHealthyColor() {
        return DOCTOR_HEALTHY_COLOR;
    }

}
