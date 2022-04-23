import org.apache.xml.utils.res.StringArrayWrapper;

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
        String scanDate = "Data";
        int twitchRank = 0;
        String following = "Create array based on number following";

        Scanner sc = new Scanner(System.in);

        System.out.println("Username: ");
        String username = sc.next();
        String[] usernameData = getData(username);
        System.out.println(usernameData[1] + ". " + username + " " + scanDate);
        System.out.println("Average viewers: " + usernameData[3]);
        System.out.println("Followers: " + usernameData[8]);
        System.out.println("Following save to file. Want see in console? Y/n (CAN BE LARGE)");
        sc.next();

    }

    public static String[] getData (String user) throws IOException {
        BufferedReader reader;
        String line;
        String responseContent = null;
        String[] responseArray;


        URL url = new URL("https://twitchtracker.com/api/channels/summary/" + user);
        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();

        if (status > 299) {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            while((line = reader.readLine()) != null){
                responseContent = line;
            }
            reader.close();
        } else {
            reader = new BufferedReader((new InputStreamReader(connection.getInputStream())));
            while ((line = reader.readLine()) != null){
                responseContent = line;
            }
            reader.close();
        }
        responseContent = responseContent.replaceAll("\\D+",",");
        responseArray = responseContent.split(",");
//        System.out.println(responseContent);

        connection.disconnect();

        return responseArray;
    }


}