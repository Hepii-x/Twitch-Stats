import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;



public class Main {

    private static HttpURLConnection connection;

    public static void main(String[] args) throws IOException {
        int followers = 0;
        int averageViewers = 0;
        String scanDate = " ";
        int activeSubs = 0; // Can be null
        int twitchRank = 0;
        String following = "Create array based on number following";

        Scanner sc = new Scanner(System.in);

        System.out.println("Username: ");
        String username = sc.next();
        getData(username);
        System.out.println(twitchRank + ". " + username + " " + scanDate);
        System.out.println("Average viewers: " + averageViewers);
        System.out.println("Followers: " + followers);
        System.out.println("Active subs: " + activeSubs);
        System.out.println("Following save to file. Want see in console? Y/n (CAN BE LARGE)");
        sc.next();

    }

    public static void getData (String user) throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        URL url = new URL("https://twitchtracker.com/api/channels/summary/" + user);
        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();

        if (status > 299) {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            while((line = reader.readLine()) != null){
                responseContent.append(line);
            }
            reader.close();
        } else {
            reader = new BufferedReader((new InputStreamReader(connection.getInputStream())));
            while ((line = reader.readLine()) != null){
                responseContent.append(line);
            }
            reader.close();
        }
        System.out.println(responseContent.toString());

        connection.disconnect();
    }


}