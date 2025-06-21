import java.util.Scanner;

public class MCASUpdates {

    public static void main(String[] args) {
        boolean flapsUp;
        boolean manualFlight;
        boolean pilotInput;
        double aoa;
        double aoaThreshold;

        Scanner scanner = new Scanner(System.in);

        System.out.println("(true/false) Flaps are up:");
        flapsUp = scanner.nextBoolean();
        if (!flapsUp) {
            System.out.println("MCAS is not activated due to flaps being down.");
            scanner.close();
            return;
        }

        System.out.println("(true/false) Autopilot is disengaged:");
        manualFlight = scanner.nextBoolean();
        if (!manualFlight) {
            System.out.println("MCAS is not activated due to autopilot being engaged.");
            scanner.close();
            return;
        }

        System.out.println("Angle of Attack (AoA) in degrees:");
        aoa = scanner.nextDouble();
        if (aoa < 0 || aoa > 90) {
            System.out.println("Invalid AoA value. It must be between 0 and 90 degrees.");
            scanner.close();
            return;
        }

        System.out.println("AoA threshold in degrees:");
        aoaThreshold = scanner.nextDouble();
        if (aoaThreshold < 0 || aoaThreshold > 90) {
            System.out.println("Invalid AoA threshold. It must be between 0 and 90 degrees.");
            scanner.close();
            return;
        }

        System.out.println("(true/false) Pilot input is detected:");
        pilotInput = scanner.nextBoolean();

        // Run MCAS check
        System.out.println("Checking conditions for MCAS activation...");
        boolean mcasActivated = shouldMCASActivate(flapsUp, aoa, manualFlight, pilotInput, aoaThreshold);

        if (mcasActivated) {
            System.out.println("MCAS is activated.");
        } else {
            System.out.println("MCAS is not activated.");
        }

        scanner.close();
    }

    public static boolean shouldMCASActivate(boolean flapsUp, double aoa, boolean manualFlight, boolean pilotInput, double aoaThreshold) {
        return flapsUp && aoa > aoaThreshold && manualFlight && !pilotInput;
    }
}