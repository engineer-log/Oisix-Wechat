import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class templateMessage {
  public static void main(String[] args) {
    System.out.println("===== HTTP POST Start =====");
    try {
      URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=UuBJLq_nfSUip_3A4yQv2mHf6hbCKlm2ctXM3-k9idqYGpl1sTg_lhoLU3ttjoYpfcspti2CFc5QTJe2t3jwGCUQTBLFrekKdcEKWyPMfijAMFsW2U-ftzxFmhzU_zoyVCDbAGAAHI");
      HttpURLConnection connection = null;
      try {
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        String json = "{\"data\":" 
            + "{\"first\": {\"color\": \"#173177\",\"value\": \"発送通知メッセージです\"}," 
            + "\"keyword1\": {\"color\": \"#173177\", \"value\": \"福田\"}," 
            + "\"keyword2\": {\"color\": \"#173177\", \"value\": \"2017年\"}," 
            + "\"remark\": {\"color\": \"#173177\",\"value\": \"ありがとうございました！\"}}," 
            + "\"template_id\": \"9kTt0nlO9G11X89nMMRlv9UgrwwNrgbdqUtAuFRiPXw\"," 
            + "\"touser\": \"owRRb0rFTbuWSZO-Dkpc4V-NtnSk\"," 
            + "\"url\": \"http://weixin.qq.com\"}";
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