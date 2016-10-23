package br.com.seartv.utils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static String getGoogleUrlToSearchPerson(String name) {
        String url = "https://www.google.com.br/#q=";
        url = url + name.toLowerCase().replace(" ", "+");
        return url;
    }
}
