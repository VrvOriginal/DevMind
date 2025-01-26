package Teme;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;


class Vehicle {
    // TODO
    //Campuri / Fields
    private static final String producedBy = "Ford";
    private final int productionYear;
    private final String vin;
    private String plateNumber;
    private double kilometers;
    private int lastSoldOnYear;
    private double positionX, positionY;


    //Constructori
    public Vehicle(int productionYear, String vin) {
        this.productionYear = productionYear;
        this.vin = vin;
    }

    public Vehicle(int productionYear, String vin, String plateNumber, double kilometers, int lastSoldOnYear) {
        this(productionYear, vin);
        this.plateNumber = plateNumber;
        this.kilometers = kilometers;
        this.lastSoldOnYear = lastSoldOnYear;
    }

    public Vehicle(int productionYear, String vin, double positionX, double positionY) {
        this(productionYear, vin);
        this.positionX = positionX;
        this.positionY = positionY;
    }

    //Setters & getters
    public String getPlateNumber() {
        return plateNumber;
    }
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public double getKilometers() {
        return kilometers;
    }

    public int getLastSoldOnYear() {
        return lastSoldOnYear;
    }

    public double getPositionX() {
        return positionX;
    }
    public double getPositionY() {
        return positionY;
    }

    //Methods
    public void sellVehicle(String plateNumber, int lastSoldOnYear) {
        this.plateNumber = plateNumber;
        this.lastSoldOnYear = lastSoldOnYear;
    }

    public void moveVehicle(double positionX, double positionY) {
        double difPositionX = positionX - this.positionX;
        double difPositionY = positionY - this.positionY;

        this.kilometers = Math.sqrt( Math.pow( difPositionX, 2) + Math.pow(difPositionY, 2) ) ;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public boolean isVinValid(boolean isDrivingInNorthAmerica) {

        if (!isDrivingInNorthAmerica)
            return true;

        int[] weight = {8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2};
        if (vin.length() != weight.length)
            return false;

        int sum = 0;
        for (int i = 0; i < vin.length(); i++) {
            if (Character.isDigit(vin.charAt(i))) {
                sum += weight[i] * Character.getNumericValue(vin.charAt(i));
            } else if (Character.isLetter(vin.charAt(i))) {
                sum += weight[i] * ( (vin.charAt(i) - 'A') % 9 + 1 + (vin.charAt(i) >= 'S' ? 1 : 0) );
            }
        }

        int checkDigit = sum % 11;
        char checkChar = checkDigit == 10 ? 'X' : Integer.toString(checkDigit).charAt(0);

        if (checkChar == vin.charAt(8))
            return true;

        return false;

    }

    public void printVinDecomposed() {

        System.out.println("Identificatorul producatorului: " + vin.substring(0, 3));
        System.out.println("Atributele vehiculului: " + vin.substring(3, 8));
        System.out.println("Cifra de verificare: " + vin.substring(8, 9));
        System.out.println("Anul productiei modelului: " + vin.substring(9, 10));
        System.out.println("Seria fabricii: " + vin.substring(10, 11));
        System.out.print("Identificatorul numeric: " + vin.substring(11, 17));

    }

    public String display() {

        String x = String.format("Produced by: %s\n" +
                        "Production year: %s\n" +
                        "VIN: %s\n" +
                        "Plate number: %s\n" +
                        "Kilometers: %s\n" +
                        "Last sold on year: %s\n" +
                        "Position X: %s\n" +
                        "Position Y: %s\n",
                Vehicle.producedBy, productionYear, vin,
                plateNumber, kilometers,
                lastSoldOnYear, positionX, positionY);

        return x;
    }


}


public class OOP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testNumber = scanner.nextInt();
        switch (testNumber) {
            case 1:
                try {
                    int producedByModifs = Vehicle.class.getDeclaredField("producedBy").getModifiers();
                    if (Modifier.isStatic(producedByModifs) && Modifier.isFinal(producedByModifs)) {
                        System.out.println("producedBy field correctly declared");
                    } else {
                        System.out.println("producedBy field incorrectly declared!");
                    }
                } catch (NoSuchFieldException e) {
                    System.out.println("producedBy field not declared!");
                }
                break;
            case 2:
                try {
                    int vinModifs = Vehicle.class.getDeclaredField("vin").getModifiers();
                    if (Modifier.isFinal(vinModifs)) {
                        System.out.println("vin field correctly declared");
                    } else {
                        System.out.println("vin field incorrectly declared!");
                    }
                } catch (NoSuchFieldException e) {
                    System.out.println("vin field not declared!");
                }
                break;
            case 3:
                try {
                    int productionYearModifs =
                            Vehicle.class.getDeclaredField("productionYear").getModifiers();
                    if (Modifier.isFinal(productionYearModifs)) {
                        System.out.println("productionYear field correctly declared");
                    } else {
                        System.out.println("productionYear field incorrectly declared!");
                    }
                } catch (NoSuchFieldException e) {
                    System.out.println("productionYear field not declared!");
                }
                break;
            case 4:
                boolean setPlateNumberExists = false;
                boolean setKilometersExists = false;
                try {
                    Vehicle.class.getDeclaredMethod("setPlateNumber", String.class);
                    setPlateNumberExists = true;
                } catch (NoSuchMethodException e) {
                    System.out.println("plateNumber field should be modifiable!");
                }
                try {
                    Vehicle.class.getDeclaredMethod("setKilometers", double.class);
                    setPlateNumberExists = true;
                    System.out.println("kilometers field should be read only!");
                } catch (NoSuchMethodException e) {
                }
                if (setPlateNumberExists && !setKilometersExists) {
                    System.out.println("plateNumber and kilometers fields correctly declared");
                }
                break;
            case 5:
                int constructorCount = Vehicle.class.getDeclaredConstructors().length;
                if (constructorCount >= 3) {
                    System.out.println("Sufficient number of constructors");
                } else {
                    System.out.println("Insufficient number of constructors!");
                }
                break;
            case 6:
                Vehicle v1 = new Vehicle(1997, "ABCD");
                v1.sellVehicle("1111", 2010);
                System.out.println(v1.getPlateNumber() + " " + v1.getLastSoldOnYear());
                v1.sellVehicle("1234", 2012);
                System.out.println(v1.getPlateNumber() + " " + v1.getLastSoldOnYear());
                break;
            case 7:
                Vehicle v2 = new Vehicle(1997, "ABCD");
                System.out.println(v2.getPositionX() + " " + v2.getPositionY());
                v2.moveVehicle(99, 199);
                System.out.println(v2.getPositionX() + " " + v2.getPositionY());
                break;
            case 8:
                Vehicle v3 = new Vehicle(1997, "ABCD");
                System.out.println(v3.isVinValid(false));
                System.out.println(v3.isVinValid(true));
                Vehicle v4 = new Vehicle(1997, "1M8GDM9AXKP042788");
                System.out.println(v4.isVinValid(false));
                System.out.println(v4.isVinValid(true));
                break;
            case 9:
                Vehicle v5 = new Vehicle(1997, "1M8GDM9AXKP042788");
                v5.printVinDecomposed();
                break;
            case 10:
                try {
                    Method displayMethod = Vehicle.class.getDeclaredMethod("display");
                    if (displayMethod.getReturnType() == String.class) {
                        System.out.println("display method correctly declared");
                    } else {
                        System.out.println("display method incorrectly declared!");
                    }
                } catch (NoSuchMethodException e) {
                    System.out.println("display method not declared!");
                }
                break;
        }
    }
}
