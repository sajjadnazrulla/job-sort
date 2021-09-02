import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Model {
    private int attributes;
    private int score;
    private Map<String, String> data;
    private static List<String> scoringKeys;

    static {
        scoringKeys = Arrays.stream(Attribute.values()).map(attribute -> attribute.name()).collect(Collectors.toList());
    }

    public Model(Map<String, String> data){
        this.data = data;
        data.entrySet().forEach(entry ->{
            String key = entry.getKey();
            if(scoringKeys.indexOf(key.toUpperCase()) >= 0) {
                String value = String.valueOf(entry.getValue());
                if (value != null && value.trim().length() > 0) {
                    this.attributes++;
                    this.score += this.calculateScore(key);
                }
            }
        });
    }

    private int calculateScore(String key) {
        return Attribute.valueOf(key.toUpperCase()).getScore();
    }

    private enum Attribute {
        PACKAGINGTYPE(1),
        CONTENTTYPE(2),
        CHANNELID(3),
        PLATFORM(4),
        COUNTRY(5);

        int score;
        Attribute(int score) {
            this.score = score;
        }

        public int getScore() {
            return score;
        }
    }

    public int getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "Model{" +
                "attributes=" + attributes +
                ", score=" + score +
                ", data=" + data +
                "}\n";
    }

    public int getScore() {
        return score;
    }
}
