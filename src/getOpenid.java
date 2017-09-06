import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class getOpenid {
  public static void main(String[] args) {
    System.out.println("===== HTTP POST Start =====");
    try {
      URL url = new URL("https://api.weixin.qq.com/sns/oauth2/access_token?" 
          + "appid=wx9e5c8fd9758aaf35" 
          + "&secret=bd83e7a84cbbbaa7c9f8f1f08edf5157" 
          + "&code=001k6y7V0STYtY1wgj7V0WOA7V0k6y7m" 
          + "&grant_type=authorization_code");
      HttpURLConnection connection = null;
      try {
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        PrintStream ps = new PrintStream(connection.getOutputStream());
        ps.close();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
          try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
              StandardCharsets.UTF_8);
              BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
              System.out.println(line);
            }
          }
        }
      } finally {
        if (connection != null) {
          connection.disconnect();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("===== HTTP POST End =====");
  }
}