package es.Rest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import okhttp3.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@PropertySource("classpath:variables.properties")
@Component
public class ImagesRest {

    @Value( "${api.key.images}" )
    private String API_KEY;

    public JSONObject callapi(String url,String mode){
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body =
                RequestBody.create(mediaType,
                        "{\r\"url\": \""+url+"\"\r}");
        Request request = new Request.Builder()
                .url("https://microsoft-computer-vision3.p.rapidapi.com/analyze?language=es&visualFeatures="+mode+"&details=Celebrities")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("x-rapidapi-host", "microsoft-computer-vision3.p.rapidapi.com")
                .addHeader("x-rapidapi-key", API_KEY)
                .build();
        Response response = null;
        String jsonData= null;
        try{
            response = client.newCall(request).execute();
            jsonData = response.body().string();
        }catch (Exception e){
        }

        return new JSONObject(jsonData);
    }

}
