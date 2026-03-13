package validaters;

public class validateField {

    public static boolean isNameParamValid(String str){
        if (str == null || str.isBlank()){
            return false;
        }

        int len = str.length();

        if (! (Character.isLetter(str.charAt(0)) && Character.isLetter(str.charAt(len-1)))) {
            return false;
        }

        if (len < 2 || len > 50) {
            return false;
        }

        char ch;
        boolean isSpecCharRep = false;

        for  (int i = 1; i < len - 1; i++) {
            ch = str.charAt(i);

            if (Character.isLetter(ch)) {
                isSpecCharRep = false;
            }else{
                if (Character.isDigit(ch)) {
                    return false;
                }
                if (" '-".indexOf(ch) == -1) {
                    return false;
                }
                if (isSpecCharRep){
                    return false;
                }
                isSpecCharRep = true;
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
        if (email != null){
            int atIdx = email.indexOf("@");
            int dotIdx = email.lastIndexOf(".");
            if (atIdx != -1 && dotIdx != -1 && dotIdx != email.length() - 1) {
                if (atIdx != email.lastIndexOf("@")){
                    return false;
                }
                if (atIdx > 0 && atIdx < dotIdx - 2 ){
                    return true;
                }
            }
        }
        return false;
    }
}
