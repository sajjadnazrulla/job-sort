import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws IOException {
        String fileName = "data.json";
        String jsonString = Files.readString(Paths.get("src", "main", "resources", fileName));

        List<Map<String, String>> jsonData = new ObjectMapper().readValue(jsonString, List.class);
        List<Model> models = jsonData.stream()
                .map(map -> new Model(map))
                .sorted(Comparator.comparing(Model::getAttributes, Comparator.reverseOrder())
                        .thenComparing(Model::getScore, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        System.out.println(models);
    }


}
