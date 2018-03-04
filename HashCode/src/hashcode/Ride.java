package hashcode;

import static hashcode.Hashcode.vehicles;

/**
 *
 * @author Waleed Mortaja, contact Email :
 * <a href="mailto:waleedmortaja@gmail.com">waleedmortaja@gmail.com</a>
 * @author Mahmoud Khalil, contact Email :
 * <a herf="mailto:khalil2535@yahoo.com">khalil2535@yahoo.com</a>
 */
public class Ride {

    private final long id, rowOfTheStartIntersection, columnOfTheStartIntersection, rowOfTheFinishIntersection, columnOfTheFinishIntersection, latestFinish;
//    private boolean isTaken ;
    private final long earliestStart;

    public Ride(long id, long rowOfTheStartIntersection, long columnOfTheStartIntersection, long rowOfTheFinishIntersection, long columnOfTheFinishIntersection, long earliestStart, long latestFinish) {
        this.id = id;
        this.rowOfTheStartIntersection = rowOfTheStartIntersection;
        this.columnOfTheStartIntersection = columnOfTheStartIntersection;
        this.rowOfTheFinishIntersection = rowOfTheFinishIntersection;
        this.columnOfTheFinishIntersection = columnOfTheFinishIntersection;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
//        isTaken = false;
    }

    public long stepsToStart(long x, long y) {
        return Math.abs(x - rowOfTheStartIntersection) + Math.abs(y - columnOfTheStartIntersection);
    }

    public long getDistance() {
        return (rowOfTheFinishIntersection - rowOfTheStartIntersection) + (columnOfTheFinishIntersection - columnOfTheStartIntersection);
    }

    public long getEarliestStart() {
        return earliestStart;
    }

//    public void take() {
//        this.isTaken = true;
//    }
    public long getId() {
        return id;
    }

//    public boolean isIsTaken() {
//        return isTaken;
//    }
    public long getRowOfTheFinishIntersection() {
        return rowOfTheFinishIntersection;
    }

    public long getColumnOfTheFinishIntersection() {
        return columnOfTheFinishIntersection;
    }

    public Car getBestCarForRide() {
        long sizeOfAvailabeCars = vehicles.length;
        Car bestCar = null;
        long leastWastedSteps = Integer.MAX_VALUE;
        for (int i = 0; i < sizeOfAvailabeCars; i++) {
            final Car CurrentProccessedCar = vehicles[i];
            final long stepsToArriveTheIntersection = this.stepsToStart(CurrentProccessedCar.getxDestination(), CurrentProccessedCar.getyDestination());
            final long remainigStepToStart = this.getEarliestStart() - CurrentProccessedCar.getStepsToFinishAllAssignedRides();
            long wastedSteps = stepsToArriveTheIntersection - remainigStepToStart;

            if (wastedSteps < leastWastedSteps) {
                bestCar = CurrentProccessedCar;
                leastWastedSteps = wastedSteps;
            }
        }
        return bestCar; // can not be null
    }
}
