package search;

import java.util.ArrayList;
import java.util.List;

public class SearchValidator {

    public static List<String> validate(String hometown) {

        List<String> errors = new ArrayList<String>();

//        地元のチェック
        String hometownError = validateHometown(hometown);
        if (!hometownError.equals("")) {
            errors.add(hometownError);
        }

        return errors;
    }


    private static String validateHometown(String hometown) {

        if (hometown == null || hometown.equals("")) {
            return "地元を入力してください。";
        }

        return "";
    }


}
