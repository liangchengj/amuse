package com.meyoustu.amuse.gous.mey;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/17 13:36
 */
public final class JsonObject {

    private String[][] members;

    public JsonObject() {
        members = new String[1][2];
    }


    public void addProperty(String memberName, String value) {
        if (members.length == 1) {
            members[0][0] = memberName;
            members[0][1] = value;
        }
    }

    public JsonObject(String json) {

    }

    @Override
    public String toString() {
        String append = "";
        for (String[] member : members) {
            append += "\"" + member[0] + "\":" + member[1] + ",";
        }
        return "{" + Share.subEndBackOne(append) + "}";
    }
}
