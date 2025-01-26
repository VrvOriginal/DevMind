package Curs1;

public class Curs {

    public static void main(String[] args) {

//        String query = "ONE Simple sentence";
//        query.toLowerCase();
//        query = query.replace("one", "The");
//        System.out.println(query);

        String sent = "Never underestimate a TALeNTED person Who Is willing TO SUCCeed!";

        int counter = 0;
        int localCounter = 0;
        for (int i = 0; i < sent.length(); i++) {
            char ch = sent.charAt(i);
            if (!Character.isLetter(ch)) {
                counter += (localCounter > 0 ? 1 : 0);
                localCounter = 0;
                continue;
            }

            if(Character.isUpperCase(ch)) {
                localCounter++;
            } else {
                localCounter--;
            }
        }

        System.out.println(counter);


    }

}
