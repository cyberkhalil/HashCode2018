package hashcode;

import static hashcode.Hashcode.AvailableRides;

/**
 *
 * @author Waleed Mortaja, contact Email :
 * <a href="mailto:waleedmortaja@gmail.com">waleedmortaja@gmail.com</a>
 * @author Mahmoud Khalil, contact Email :
 * <a herf="mailto:khalil2535@yahoo.com">khalil2535@yahoo.com</a>
 */
public class Car {

    private long xDestination, yDestination, numberOfRides, steps;
    private long id, stepsToFinish, stepsToArriveTheIntersection;
    private String rides;
//    private boolean available;
//    private Ride currentRide;

    private long stepsToFinishAllAssignedRides; //added this

    public Car(long id) {
        this.id = id;
        xDestination = 0;
        yDestination = 0;
        rides = "";
        numberOfRides = 0;
//        available = true;
//        currentRide = null;
//        stepsToFinish = 0;
        steps = 0;
        stepsToFinishAllAssignedRides = 1;
    }

    public long[] getCordinates() {
        return new long[]{xDestination, yDestination};
    }

//    public long getx() {
    public long getxDestination() {
        return xDestination;
    }

//    public int gety() {
    public long getyDestination() {
        return yDestination;
    }

    public void assignRide(Ride r) {
        if (stepsToFinishAllAssignedRides <= Hashcode.numberOfSteps) {
//        currentRide = r;
//        currentRide.take(); delete from availble rides instead

//        stepsToStart = currentRide.stepsToStart(xDestination, yDestination);
//        stepsToFinish = currentRide.getDistance();
            this.stepsToArriveTheIntersection = r.stepsToStart(xDestination, yDestination);
//        stepsToFinish = currentRide.getDistance();

            numberOfRides++;
            rides += " " + r.getId();

            stepsToFinishAllAssignedRides += r.stepsToStart(this.xDestination, this.yDestination) + r.getDistance(); //added this

            this.xDestination = r.getRowOfTheFinishIntersection();
            this.yDestination = r.getColumnOfTheFinishIntersection();
//        available = false;
            Hashcode.AvailableRides.remove(r);
        } else {

        }
//        Hashcode.AvailableCars.remove(this);
    }

//    public boolean isAvailable() {
//        return available;
//    }
//    public void goStep() {
//        if (currentRide != null) {
//            if (stepsToStart > 0) {
//                stepsToStart--;
//            } else if (stepsToFinish > 0) {
//                stepsToFinish--;
//            } else {
//                currentRide = null;
////                available = true;
//            }
//        } else if (!Hashcode.AvailableCars.contains(this)) {
//            Hashcode.AvailableCars.add(this);
//        }
//    }
    public String getResult() {
        return this.numberOfRides + this.rides;

    }

    public long getStepsToFinishAllAssignedRides() {
        return this.stepsToFinishAllAssignedRides;
    }

    //new added method
    public Ride getBestRideForCar() {
        long sizeOfAvailabeRides = AvailableRides.size();
        Ride bestRide = null;
        long leastWastedSteps = Long.MAX_VALUE;
        for (int i = 0; i < sizeOfAvailabeRides; i++) {
            final Ride CurrentProccessedRide = AvailableRides.get(i);
            final long distanceBetweenCarAndRideStart = CurrentProccessedRide.stepsToStart(this.getxDestination(), this.getyDestination());

            final long remainigStepToStart = distanceBetweenCarAndRideStart + this.getStepsToFinishAllAssignedRides();
//            final long remainigStepToStart = CurrentProccessedRide.getEarliestStart() - this.getStepsToFinishAllAssignedRides();

            long wastedSteps = Math.abs(CurrentProccessedRide.getEarliestStart() - remainigStepToStart);

            if (wastedSteps < leastWastedSteps) {
                bestRide = CurrentProccessedRide;
                leastWastedSteps = wastedSteps;
            }
        }
        return bestRide; // can not be null
    }

}
