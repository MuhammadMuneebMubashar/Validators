package validaters;

public class validateField {

    // names only — no digits, no weird symbols, just letters with occasional space/hyphen/apostrophe
    public static boolean isNameParamValid(String str) {
        if (str == null || str.isBlank()) {
            return false;
        }

        int len = str.length();

        // must start and end with a letter, "123" or "-john" won't cut it
        if (!(Character.isLetter(str.charAt(0)) && Character.isLetter(str.charAt(len - 1)))) {
            return false;
        }

        // sanity bounds — single char names and novels both rejected
        if (len < 2 || len > 50) {
            return false;
        }

        char ch;
        boolean isSpecCharRep = false;

        for (int i = 1; i < len - 1; i++) {
            ch = str.charAt(i);

            if (Character.isLetter(ch)) {
                isSpecCharRep = false;
            } else {
                // digits have no place in a name field
                if (Character.isDigit(ch)) {
                    return false;
                }

                // only space, apostrophe, hyphen allowed — for names like O'Brien or Mary-Jane
                if (" '-".indexOf(ch) == -1) {
                    return false;
                }

                // two specials in a row e.g. "O''Brien" — nope
                if (isSpecCharRep) {
                    return false;
                }

                isSpecCharRep = true;
            }
        }
        return true;
    }


    // handles most real-world formats: +92 300 1234567, (021) 111-111-111, etc.
    public static boolean isContactValid(String contact) {
        if (contact == null || contact.isBlank()) {
            return false;
        }

        int len = contact.length();
        boolean isSpecCharRep = false;
        int digits = 0;

        // first char can be a digit, '+' for country code, or '(' for area code
        char ch = contact.charAt(0);
        if (!Character.isDigit(ch)) {
            if (ch != '+' && ch != '(') {
                return false;
            }
            isSpecCharRep = true;
        } else {
            digits = 1;
        }

        // parentheses must be paired and in the right order — ')' can't come before '('
        int openIdx = contact.indexOf('(');
        int closeIdx = contact.indexOf(')');
        if (openIdx > closeIdx || (openIdx == -1 && closeIdx != -1)) {
            return false;
        }

        // only one pair of parentheses allowed
        if (openIdx != contact.lastIndexOf('(') || closeIdx != contact.lastIndexOf(')')) {
            return false;
        }

        for (int i = 1; i < len; i++) {
            // skip the parentheses themselves, already validated above
            if (i == openIdx || i == closeIdx) {
                continue;
            }

            ch = contact.charAt(i);

            if (Character.isDigit(ch)) {
                isSpecCharRep = false;
                digits++;
            } else {
                // two separators back to back e.g. "123--456"
                if (isSpecCharRep) {
                    return false;
                }

                // only hyphen and space allowed as separators
                if (ch != '-' && ch != ' ') {
                    return false;
                }

                isSpecCharRep = true;
            }
        }

        // ITU-T standard: min 7 digits (local), max 15 (international with country code)
        return digits >= 7 && digits <= 15;
    }


    // RFC 5321 compliant-ish — covers everything you'd actually encounter in the real world
    public static boolean isEmailValid(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }

        // 254 is the hard RFC limit for total email length
        if (email.length() > 254) {
            return false;
        }

        // must have exactly one '@', and it can't be the first or last character
        int idx = email.indexOf("@");
        if (idx == -1 || idx == 0 || idx == email.length() - 1 || idx != email.lastIndexOf("@")) {
            return false;
        }

        String[] parts = email.split("@");
        String local  = parts[0];
        String domain = parts[1];

        // local part maxes out at 64 chars per RFC, domain needs at least one dot with 2+ char TLD
        if (local.length() > 64 || domain.indexOf(".") == -1 || domain.lastIndexOf(".") >= domain.length() - 2) {
            return false;
        }

        // all four boundary characters must be alphanumeric — no leading/trailing dots or dashes
        if (!(Character.isLetterOrDigit(local.charAt(0)) &&
                Character.isLetterOrDigit(local.charAt(local.length() - 1)) &&
                Character.isLetterOrDigit(domain.charAt(0)) &&
                Character.isLetterOrDigit(domain.charAt(domain.length() - 1)))) {
            return false;
        }

        // local part: letters/digits freely, special chars (. _ + -) only one at a time
        boolean consect = false;
        char ch;
        for (int j = 1; j < local.length(); j++) {
            ch = local.charAt(j);
            if (Character.isLetterOrDigit(ch)) {
                consect = false;
            } else if (consect) {
                return false;  // two specials in a row e.g. "user..name"
            } else if ("._+-".indexOf(ch) != -1) {
                consect = true;
            } else {
                return false;  // character not allowed in local part
            }
        }

        // domain: same idea but only dot and hyphen are valid separators
        consect = false;
        for (int j = 1; j < domain.length() - 1; j++) {
            ch = domain.charAt(j);
            if (Character.isLetterOrDigit(ch)) {
                consect = false;
            } else if (consect) {
                return false;  // "my..domain.com" or "my-.domain.com"
            } else if (".-".indexOf(ch) != -1) {
                consect = true;
            } else {
                return false;  // anything else is invalid in a domain
            }
        }

        return true;
    }
}