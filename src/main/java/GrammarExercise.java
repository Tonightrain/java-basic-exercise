import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String firstWordList = sc.nextLine();
        String secondWordList = sc.nextLine();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);

        System.out.println(result);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {

        String firstWord = firstWordList.toUpperCase();
        String secondWord = secondWordList.toUpperCase();
        String[] arr1 = firstWord.split(",");
        String[] arr2 = secondWord.split(",");

        Arrays.asList(arr1).stream().forEach(x->{if (Pattern.matches("^[a-zA-Z]+$",x) == false){
            throw new RuntimeException("input not valid");
        }});
        Arrays.asList(arr2).stream().forEach(x->{if (Pattern.matches("^[a-zA-Z]+$",x) == false){
            throw new RuntimeException("input not valid");
        }});

        String[] arr12 = new String[arr1.length + arr2.length];
        for (int i = 0; i < arr12.length; i++){
            if (i<arr1.length){
                arr12[i]=arr1[i];
            }else {
                arr12[i]=arr2[i-arr1.length];
            }
        }

        List<String> list = Arrays.asList(arr12);
        List<String> result2 = new ArrayList<String>();
        Map<String, Long> collect = list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        collect.forEach((k,v)->{
            if (v>1){
                result2.add(k);
            }
        });
        String[] result1 = new String[result2.size()];

        result2.toArray(result1);
        String split = "(.{1})";
        String[] array = new String[result1.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = result1[i].replaceAll(".(?=.)", "$0 ");
        }
        List<String> result = Arrays.asList(array);

        return result;
    }
}
