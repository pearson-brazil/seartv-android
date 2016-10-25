package br.com.seartv.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.com.seartv.config.ConstantsGeneral;

public class Utils {

    public static String getGoogleUrlToSearchPerson(String name) {
        String url = "https://www.google.com.br/#q=";
        url = url + name.toLowerCase().replace(" ", "+");
        return url;
    }

    public static Object parseJsonToObject(JSONObject jsonObject, Class definedClass) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(ConstantsGeneral.TIME_ZONE_DATE_FORMAT);
        Gson gson = gsonBuilder.create();

        return gson.fromJson(jsonObject.toString(), definedClass);
    }

    public static Map<String, String> generalHeader(Context ctx) {
        Map<String, String> headers = new HashMap<>();
        headers.put(ConstantsGeneral.ACCEPT, ConstantsGeneral.JSON);

        return headers;
    }

    public static String trimMessage(String json, String key) {
        String trimmedString = null;

        try {
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return trimmedString;
    }
}
