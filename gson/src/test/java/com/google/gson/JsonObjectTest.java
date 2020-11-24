package com.google.gson;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * @author Liangcheng Juves
 * Created at 2020/04/30 14:20
 */
class JsonObjectTest {

    @Test
    void add() {
//        JsonObject jsonObject=new JsonObject();
//        jsonObject.addProperty("name","Liangcheng Juves");
//        System.out.println(jsonObject);


//        JsonObject jsonObject=new Gson().fromJson("{\"name\":\"Liangcheng Juves\"}",JsonObject.class);
//        System.out.println(jsonObject.get("name"));



//        Bean bean=new Bean();
//        bean.setName("Liangcheng Juves");
//        bean.setAge("20");
//        JsonObject jsonObject=new Gson().toJsonTree(bean);
//        System.out.println(jsonObject.get("name"));
    }
}

@Data
class Bean{
    private String name;
    private String age;

}