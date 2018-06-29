package bot;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Weather {

    String command = "http://api.openweathermap.org/data/2.5/forecast?id=702550&appid=ca60ad41611cedc86730177a122132b4";
    private final static double KELVIN = 273.15;
    private Gson gson;
    private HttpRequest httpRequest;



    public String getWeather() {
            httpRequest = new HttpRequest();
            gson = new Gson();

            String response = httpRequest.request(command,"POST");

            Map<String , Object> map = jsonPars(response.toString());
            ArrayList ls = (ArrayList) map.get("list");
            map = (Map) ls.get(0);
            map = (Map) map.get("main");

            return "" + ((double)map.get("temp") - KELVIN);

    }

    public Map jsonPars(String json) {
        Map<String , Object> map = new HashMap<String, Object>();
        map = (Map<String, Object>) gson.fromJson(json , map.getClass());
        return map;
    }
}
