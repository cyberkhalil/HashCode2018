package hashcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Waleed Mortaja, contact Email :
 * <a href="mailto:waleedmortaja@gmail.com">waleedmortaja@gmail.com</a>
 * @author Mahmoud Khalil, contact Email :
 * <a herf="mailto:khalil2535@yahoo.com">khalil2535@yahoo.com</a>
 */
public class Hashcode {

    public static ArrayList<Ride> AvailableRides;

//    public static ArrayList<Car> AvailableCars;
    public static Car vehicles[];

    public static long numberOfSteps; //added this
//    private static int currentStep;

    public static void main(String[] args) throws FileNotFoundException {
        File dir = new File("src/hashcode/input");
//        final File[] listFiles = dir.listFiles();
        final File[] files = dir.listFiles();
//        File currentFile;
//        for (File listFile : listFiles) {
//        for (File currentFile : listFiles) {
        for (File currentFile : files) {
//            currentFile = currentFile;
            Scanner in = new Scanner(currentFile);
//            int rows = in.nextInt();
            in.nextInt(); //just skip rows
//            int columns = in.nextInt();
            in.nextInt(); //just skip columns
            int numberOfVehicles = in.nextInt();
            long numberOfRides = in.nextLong();
//            int perRideBonus = in.nextInt();
            in.nextInt(); // just skip perRideBonus

//            int numberOfSteps = in.nextInt();
            numberOfSteps = in.nextInt();
//            AvailableRides = new ArrayList<>(numberOfRides);
            AvailableRides = new ArrayList<>();
//            AvailableCars = new ArrayList<>(numberOfVehicles); no need for this
            vehicles = new Car[numberOfVehicles];
            for (int i = 0; i < numberOfVehicles; i++) {
                Car newVehicle = new Car(i);// I think that we dont need id for the car
//                vehicles[i] =  new Car(i); 
                vehicles[i] = newVehicle; // I think that we dont need id for the car

//                AvailableCars.add(vehicles[i]);
//                AvailableCars.add(newVehicle); no need for this
            }
            for (int i = 0; i < numberOfRides; i++) {
                AvailableRides.add(new Ride(i, in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()));
            }

//            while (numberOfSteps-- != 0) {
//            for (currentStep = 0; currentStep < numberOfSteps; currentStep++) { //changed my mind, this is not needed too
//                for (int k = AvailableRides.size(); k > 0; k--) {
//                    getColsetVehecile(AvailableRides.get(0));
//                }
//                for (int j = 0; j < numberOfVehicles; j++) {
//                    vehicles[j].goStep();
//                }
//            }
//            for (int i = 0; i < numberOfRides; i++) {
//                final Ride CurrentRide = AvailableRides.get(0);
//                getBestCarForRide(CurrentRide).assignRide(CurrentRide);
//            }
            while (AvailableRides.size() > 0) {
                ArrayList<Car> badCars = new ArrayList<>();
                for (int i = 0; i < numberOfVehicles; i++) {

                    final Car currentVehicle = vehicles[i];

                    Ride bestRideForTheCurrentVehicle = currentVehicle.getBestRideForCar();
                    if (bestRideForTheCurrentVehicle == null) {
                        badCars.add(currentVehicle);
                        break;
                    }
                    Car bestCarForBestRideForTheCurrentVehicle = bestRideForTheCurrentVehicle.getBestCarForRide();

                    bestCarForBestRideForTheCurrentVehicle.assignRide(bestRideForTheCurrentVehicle);

                    if (currentVehicle != bestCarForBestRideForTheCurrentVehicle) {
                        i--; // do the loop for the same car. You have to know that the ride is removed from the available and that the best car for that ride is now working and wont be the best for the same ride even if the ride is still exists (which is not happening) 
                    }
                }
            }

            try (PrintWriter pw = new PrintWriter(new File("src/hashcode/output/" + currentFile.getName()))) {
                for (long i = 0; i < numberOfVehicles; i++) {
                    pw.println(vehicles[(int) i].getResult());
                }
            }
        }
    }

//    public static void getColsetVehecile(Ride r) {
////        if (!r.isIsTaken()) { not required nymore
//        Car bestCar = null;
//        int bestDistance = -1;
//        boolean canGetBonus = false;
//
//        for (int i = 0; i < vehicles.length; i++) {
//            Car v1 = vehicles[i];
//            if (!canGetBonus && r.stepsToStart(v1.getxDestination(), v1.getyDestination()) > r.getEarliestStart()) { // seems that the first condition is not necessary
//                canGetBonus = true;
//            }
//            if (!canGetBonus) {
//                if (bestDistance > r.stepsToStart(v1.getxDestination(), v1.getyDestination())) {
//                    bestDistance = r.stepsToStart(v1.getxDestination(), v1.getyDestination());
//                    bestCar = v1;
//                }
//            } else if (bestDistance < r.stepsToStart(v1.getxDestination(), v1.getyDestination())) {
//                bestDistance = r.stepsToStart(v1.getxDestination(), v1.getyDestination());
//                bestCar = v1;
//            }
//        }
//        if (bestCar == null) {
//            if (vehicles.length > 0) {
//                vehicles[0].assignRide(r);
//            }
//        } else {
//            bestCar.assignRide(r);
//        }
////        } delete this because of deleting if statement
//    } //added this
}
