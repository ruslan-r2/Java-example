public class Palindrom {
    public static void main(String[] args) {
        String str1 = "I am, ma i.";
        System.out.println(isPalindrome(str1));
        System.out.println(isPalindrome1(str1));
    }

    public static Boolean isPalindrome(String s) {
        String str = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        String reverse = new StringBuilder(str).reverse().toString();
        return str.equals(reverse);
    }

    public static boolean isPalindrome1(String s) {
        String str = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        int lenght = str.length();
        for (int i = 0; i <= lenght / 2 - 1; i++) {
            if (!(str.charAt(i) == str.charAt(lenght - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}
