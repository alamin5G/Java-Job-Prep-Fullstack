public class SubString {
    public static void main(String[] args) {
        String s = "welcometojava";
        int k = 3;
        System.out.println(getSmallestAndLargest2(s, k));
    }

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = s.substring(0, k);
        String largest = s.substring(0, k);

        for (int i = 1; i <= s.length() - k; i++) {
            String substr = s.substring(i, i + k);
            if (substr.compareTo(smallest) < 0) {
                smallest = substr;
            }
            if (substr.compareTo(largest) > 0) {
                largest = substr;
            }
        }

        return smallest + "\n" + largest;
    }

    public static String getSmallestAndLargest2(String s, int k) {

        String smallest = s.substring(0, k);
        String largest = s.substring(0, k);
        String[] arr = new String[s.length() - (k - 1)];
        int c = 0;
        for(int i = 3; i <= s.length(); i++){
            arr[c] = s.substring(c, i);
            c++;
        }

        for(int i = 1; i < arr.length; i++){
            if(arr[i].compareTo(largest) > 0){
                largest = arr[i];
            }

            if(arr[i].compareTo(smallest) < 0){
                smallest = arr[i];
            }
        }


        return smallest + "\n" + largest;
    }
}
