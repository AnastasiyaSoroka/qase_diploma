package utils;

import java.util.Random;

public class RandomString {

    public String StringRandom(int length) {
        String strAllowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int OUTPUT_STRING_LENGTH = 0;
        StringBuilder sbRandomString = new StringBuilder(OUTPUT_STRING_LENGTH);
        Random random = new Random();
             for (int j = 0; j < length; j++) {
                 int randomInt = random.nextInt(strAllowedCharacters.length());
                 sbRandomString.append(strAllowedCharacters.charAt(randomInt));
        }
        return sbRandomString.toString();
    }
}
