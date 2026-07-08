package RecommendationSystem.java;
import java.util.*;

public class RecommendationSystem {

    // Sample user-product ratings
    static Map<String, Map<String, Integer>> data = new HashMap<>();

    public static void main(String[] args) {

        // Sample data
        addRating("User1", "Laptop", 5);
        addRating("User1", "Mobile", 3);

        addRating("User2", "Laptop", 4);
        addRating("User2", "Headphones", 5);

        addRating("User3", "Mobile", 5);
        addRating("User3", "Headphones", 4);

        recommend("User1");
    }

    // Add rating
    static void addRating(String user, String product, int rating) {
        data.putIfAbsent(user, new HashMap<>());
        data.get(user).put(product, rating);
    }

    // Recommendation logic
    static void recommend(String user) {
        Map<String, Integer> userRatings = data.get(user);
        Map<String, Integer> scores = new HashMap<>();

        for (String otherUser : data.keySet()) {
            if (!otherUser.equals(user)) {

                for (String product : data.get(otherUser).keySet()) {

                    // Recommend only new products
                    if (!userRatings.containsKey(product)) {
                        scores.put(product,
                                scores.getOrDefault(product, 0)
                                        + data.get(otherUser).get(product));
                    }
                }
            }
        }

        System.out.println("Recommended products for " + user + ":");

        for (String product : scores.keySet()) {
            System.out.println(product);
        }
    }
}
