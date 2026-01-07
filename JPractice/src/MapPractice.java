import java.util.HashMap;
import java.util.Map;

public class MapPractice {
    public static void main(String[] args) {
        Map<String, Double> subjectMarks = new HashMap<>();
        subjectMarks.put("Bangla", 88.0);
        subjectMarks.put("English", 75.5);
        subjectMarks.put("French", 85.0);
        subjectMarks.put("Math", 95.2);
        subjectMarks.put("Spanish", 85.0);

        //show the average grade
        System.out.println(getAverage(subjectMarks));
        //show the all subject marks
        subjectMarks.forEach((s, marks) -> System.out.println(s + " " + marks));
        //show only Bangla subject and it's grade
        subjectMarks.forEach((s, marks) ->{
            if (s.equals("Bangla")) System.out.println(s + " " + marks);
        });



    }

    public static double getAverage(Map<String, Double> map) {
        double sum = 0;
        for (double grade : map.values()) {
            sum += grade;
        }
        return sum / map.size();
    }


}
