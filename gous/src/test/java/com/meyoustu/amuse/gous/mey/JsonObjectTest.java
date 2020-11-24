package com.meyoustu.amuse.gous.mey;

import org.junit.jupiter.api.Test;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/17 13:54
 */
class JsonObjectTest {

    @Test
    void addProperty() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "Liangcheng Juves");
        System.out.println(jsonObject);
    }
}