package in.cdac;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SampleTest {

    @Test
    public void test1() {

        List<String> list = Arrays.asList("delhi", "calcutta");

        list.stream().forEach((item) -> System.out.println(item));
        list.stream().forEach(System.out::println);

        List<String> list1 = list.stream().map((input) -> input.toLowerCase()).collect(Collectors.toList());
        System.out.println(list1);

        List<String> list2 = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(list2);


        list.stream().filter((item) -> item.length() > 6).forEach(System.out::println);
    }
}
