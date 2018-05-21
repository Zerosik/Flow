package kr.hs.dgsw.ahnt3.Networks;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class NetworkHelper {

    public static String POST(String url, String json, String token){
        InputStream inputStream = null;
        String result = "";

        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            StringEntity se = new StringEntity(json);

            httpPost.setEntity(se);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("content-type", "application/json");
            if(!token.equals("")){
                httpPost.setHeader("x-access-token", token);
            }

            HttpResponse httpResponse = httpClient.execute(httpPost);

            inputStream = httpResponse.getEntity().getContent();


            if(inputStream != null)
                result = convertInputStreamToString(inputStream);

            else
                result = "Did not work!";



        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }


        return result;
    }



    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }
}