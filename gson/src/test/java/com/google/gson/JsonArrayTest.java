package com.google.gson;

import org.junit.jupiter.api.Test;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/17 12:16
 */
class JsonArrayTest {

    @Test
    void add() {
        JsonArray jsonArray = new JsonArray();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "Liangcheng Juves");

        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("sex", "boy");

        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject1);

        System.out.println(jsonArray);
    }
}