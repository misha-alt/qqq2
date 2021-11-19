

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class F1 {
    private static HttpURLConnection connection;
    public static void main(String[] args) {

        BufferedReader reader;
        String line;
        StringBuilder responseContrnt = new StringBuilder();

        try {
            URL url = new URL("http://jsonplaceholder.typicode.com/posts/");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(500);
            connection.setReadTimeout(500);

            int status = connection.getResponseCode();
            //System.out.println(status);

            if (status>299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContrnt.append(line);
                }
                reader.close();
            }else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContrnt.append(line);

                }
                reader.close();
            }
            // System.out.println(responseContrnt.toString());
            parse(responseContrnt.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
    public static String parse(String responseBody){
        JSONArray album = new JSONArray(responseBody);
        for(int i =0; i<album.length(); i++){
            JSONObject album1 = album.getJSONObject(i);
            int id = album1.getInt("id");
            int userId = album1.getInt("userId");
            String title = album1.getString("title");
            System.out.println("1233"+id+","+ userId +", "+title);
        }
        return null;
    }

}