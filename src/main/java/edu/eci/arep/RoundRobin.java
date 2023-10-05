package edu.eci.arep;

import java.util.ArrayList;

public class RoundRobin {
    
    private static ArrayList<String> apis = new ArrayList<String>() {{
        add("http://spark-app2:35000");
        add("http://spark-app3:35000");
        add("http://spark-app4:35000");
    }};
    private static int currentApi = 0;

    public static String getNextApi() {
        String api = apis.get(currentApi % apis.size());
        currentApi++;
        return api;
    }

}
