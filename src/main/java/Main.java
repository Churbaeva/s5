import java.util.*;

import HomeWork5.PhonePerson;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;


public class Main {
    public static void main(String[] args) {
        List<PhonePerson> phonePeople = Arrays.asList(
                new PhonePerson("Ivanov", "88005553535"),
                new PhonePerson("Petrov", "88005555353"),
                new PhonePerson("Ivanov", "88005553536"),
                new PhonePerson("Ivanov", "88005553537"),
                new PhonePerson("Petrov", "88005555356"),
                new PhonePerson("Petrov", "88005555357"),
                new PhonePerson("Sidorov", "88005554555"),
                new PhonePerson("Sidorov", "88005554556"),
                new PhonePerson("Sidorov", "88005554557"));
        Map<String, List<String>> multimap = new HashMap<>();
        for (PhonePerson peoples : phonePeople) {
            multimap.computeIfAbsent(peoples.getPerson(), k -> new ArrayList<>()).add(peoples.getPhone());
        }
        System.out.println("Входные данные");
        System.out.println(multimap + "\n");
        Map<String, List<String>> phoneBook = multimap.entrySet().stream()
                .sorted(comparingInt(e -> e.getValue().size()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue, (a, b) -> {throw new AssertionError();}, LinkedHashMap:: new ));
        Set<String> set = phoneBook.keySet();
        Iterator<String> itr = set.iterator();
        List<String> alKeys = new ArrayList<String>(phoneBook.keySet());
        Collections.reverse(alKeys);
        System.out.println("Обратный порядок телефонной книжки");
        for (String key : alKeys) {
            System.out.println(key + "=" + phoneBook.get(key));
        }
    }
}