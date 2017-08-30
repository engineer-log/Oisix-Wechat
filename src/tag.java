import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class tag {
  public static void main(String[] args) {
    System.out.println("===== HTTP POST Start =====");
    try {
      URL url = new URL("https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=0o3qoJevBULk9qdABLkK611gtNTReZaKuRkji4nwKJCwN8T1R14Im6yzERKy2A2W-0ys8jjYb4_GqKtHQiKULB1JdtT9sUCx5YlcpY8zCa0UFBeADAHSO");
      HttpURLConnection connection = null;
      try {
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        //JSON形式の文字列に変換する。
        String json = "{\"openid_list\":[\"owRRb0iMN1rLbEGQpEMBBPQnNIbQ\"],\"tagid\":100}";
        PrintStream ps = new PrintStream(connection.getOutputStream());
        ps.print(json);
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