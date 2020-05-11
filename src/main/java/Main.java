import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int START_WITH_LINE = 1;
    private static final char LINE_SEPARATOR = ',';
    private static final String CSV_FILENAME = "users.csv";

    public static void main(String[] args) {
        final List<User> parsedUsers = loadFromCsv(CSV_FILENAME);
        System.out.println(parsedUsers);
    }

    public static List<User> loadFromCsv(String csvFile) {
        try {
            final FileReader fileReader = new FileReader(csvFile);
            final CSVParser csvParser = new CSVParserBuilder().withSeparator(LINE_SEPARATOR).build();
            final CSVReader csvReader = new CSVReaderBuilder(fileReader)
                .withSkipLines(START_WITH_LINE)
                .withCSVParser(csvParser)
                .build();

            final List<User> users = new ArrayList<>();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                final Integer userId = Integer.valueOf(line[0]);
                final int userAge = Integer.parseInt(line[2]);
                final String userName = line[1];
                final boolean userSex = Integer.parseInt(line[3]) != 0;
                users.add(new User(userId, userName, userAge, userSex));
            }
            return users;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}

