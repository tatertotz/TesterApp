package com.example.lia.testerapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lia on 2/28/17.
 */

public class SushiExpert {
    List<String> getTypes(String style) {
        List<String> types = new ArrayList<String>();
        if (style.equals("maki")) {
            types.add("Salmon");
            types.add("California Roll");
        } else {
            types.add("Eel");
            types.add("Tuna");
        }
        return types;
    }
}
