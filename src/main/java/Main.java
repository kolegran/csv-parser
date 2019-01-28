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
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            List<User> users = new ArrayList<>();
            String[] line;

            reader.readNext();
            while ((line = reader.readNext()) != null) {
                User user = new User();

                user.setId(Integer.valueOf(line[0]));
                user.setName(line[1]);
                user.setAge(Integer.valueOf(line[2]));
                user.setSex((Integer.parseInt(line[3]) != 0) ? true : false);

                users.add(user);
            }

            return users;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}

