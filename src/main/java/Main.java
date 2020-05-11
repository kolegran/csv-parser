import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = loadFromCsv("users.csv");
        System.out.println(users);
    }

    public static List<User> loadFromCsv(String csvFile) {
        try {
            final CSVReader reader = new CSVReader(new FileReader(csvFile), ',', '"', 1);
            final List<User> users = new ArrayList<>();

            String[] line;
            while ((line = reader.readNext()) != null) {
                final User user = new User();
                user.setId(Integer.valueOf(line[0]));
                user.setName(line[1]);
                user.setAge(Integer.parseInt(line[2]));
                user.setSex(Integer.parseInt(line[3]) != 0);

                users.add(user);
            }
            return users;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}

