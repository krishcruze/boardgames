import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GitHubRepoCreator {
    public static void main(String[] args) {
        try {
            // Replace with your GitHub Personal Access Token (PAT)
            String token = "ghp_your_personal_access_token";

            // GitHub API endpoint for creating repos
            URL url = new URL("https://api.github.com/user/repos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Configure request
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "token " + token);
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
            conn.setDoOutput(true);

            // JSON payload for repo creation
            String jsonInputString = "{"
                    + "\"name\": \"java-created-repo\","
                    + "\"description\": \"Repository created via Java API\","
                    + "\"private\": false"
                    + "}";

            // Send request body
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Check response
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Repository created successfully!");
            } else {
                System.out.println("Failed to create repo. Response code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
