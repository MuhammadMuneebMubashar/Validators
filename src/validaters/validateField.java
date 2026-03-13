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
        if (contact == null || contact.isBlank()){
            return false;
        }

        int len = contact.length();
        boolean isSpecCharRep = false;
        int digits = 0;

        char ch = contact.charAt(0);
        if (! Character.isDigit(ch)){
            if (ch != '+' && ch != '('){
                return false;
            }
            isSpecCharRep = true;
        }else{
            digits = 1;
        }
        int openIdx = contact.indexOf('(');
        int closeIdx = contact.indexOf(')');
        if (openIdx > closeIdx || (openIdx == -1 && closeIdx != -1)){
            return false;
        }
        if (openIdx != contact.lastIndexOf('(') || closeIdx != contact.lastIndexOf(')')){
            return false;
        }

        for (int i = 1 ; i < len ; i ++){
            if (i == openIdx || i == closeIdx){
                continue;
            }
            ch = contact.charAt(i);
            if (Character.isDigit(ch)){
                isSpecCharRep = false;
                digits++;
            }else{
                if (isSpecCharRep){
                    return false;
                }
                if (ch != '-' && ch != ' '){
                    return false;
                }
                isSpecCharRep = true;
            }
        }if (digits >= 7 && digits <= 15){
            return true;
        }return false;
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
