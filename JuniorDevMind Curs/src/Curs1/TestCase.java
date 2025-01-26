package Curs1;


public class TestCase {

    public static void main(String[] args) {

//        System.out.println(Character.getNumericValue ('9'));
//        Character.getNumericValue ('1');
//        int sum = 351 % 11;
//        System.out.println(sum);

//        isVinValid("JH4KA3171KC016159");
//        System.out.println(isVinValid("1M8GDM9AXKP042788"));
//        printVinDecomposed("1M8GDM9AXKP042788");

//        int x = 1;
//        char x1 = (char) x;
//        System.out.println(x1);
        String producedBy = "Ford";
        int productionYear = 1994;
        String vin = "1M8GDM9AXKP042788";
        String plateNumber = "Alabama";
        double kilometers = 100003.7;
        int lastSoldOnYear = 2025;
        double positionX = 100, positionY = 200;

        String x = String.format("Produced by: %s\n" +
                        "Production year: %s\n" +
                        "VIN: %s\n" +
                        "Plate number: %s\n" +
                        "Kilometers: %s\n" +
                        "Last sold on year: %s\n" +
                        "Position X: %s\n" +
                        "Position Y: %s",
                        producedBy, productionYear, vin,
                        plateNumber, kilometers,
                        lastSoldOnYear, positionX, positionY);

        System.out.println(x);


    }

    public static boolean isVinValid(String vin) {

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

    public static void printVinDecomposed(String vin) {

        System.out.println("Identificatorul producatorului: " + vin.substring(0, 3));
        System.out.println("Atributele vehiculului: " + vin.substring(3, 8));
        System.out.println("Cifra de verificare: " + vin.substring(8, 9));
        System.out.println("Anul productiei modelului: " + vin.substring(9, 10));
        System.out.println("Seria fabricii: " + vin.substring(10, 11));
        System.out.print("Identificatorul numeric: " + vin.substring(11, 17));

    }


}
