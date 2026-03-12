package validaters;

public class validateField {

    public static boolean isNameParamValid(String str){

        if (str == null || str.isBlank() || str.length() <= 2 ||
        str.startsWith("-") || str.endsWith("-")) {
            return false;
        }

        boolean consecutiveHyphen = false;
        char ch ;

        for (int i = 0; i < str.length(); i++) {

            ch = str.charAt(i);
            if (ch == '-') {
                if (consecutiveHyphen) {
                    return false;
                }
                consecutiveHyphen = true;
            }else if (! Character.isLetter(ch)) {
                return false;
            }else{
                consecutiveHyphen = false;
            }
        }
        return true;
    }

    public static boolean isContactValid(String contact){
        if (contact == null || contact.isBlank()) {
            return false;
        }
        int len = contact.length();

        if (len < 7 || len > 15) {
            return false;
        }
        if (!Character.isDigit(contact.charAt(0)) && contact.charAt(0) != '+'){
            return false;
        }
        for (int i = 1; i < len; i++) {
            if (!Character.isDigit(contact.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmailValid(String email){
        return email != null && email.contains("@") &&  email.contains(".");
    }
}
