package com.meyoustu.amuse.gous.mey;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/17 13:31
 */
public abstract class JsonElement {

    private String element;

    JsonElement(String element) {
        this.element = element;
    }


    public short getAsShort() {
        try {
            return Short.parseShort(element);
        } catch (NumberFormatException e) {
            throw new JsonException(errorInfo(short.class));
        }
    }

    public int getAsInt() {
        try {
            return Integer.parseInt(element);
        } catch (NumberFormatException e) {
            throw new JsonException(errorInfo(int.class));
        }
    }

    public long getAsLong() {
        try {
            return Long.parseLong(element);
        } catch (NumberFormatException e) {
            throw new JsonException(errorInfo(long.class));
        }
    }

    public float getAsFloat() {
        try {
            return Float.parseFloat(element);
        } catch (NumberFormatException e) {
            throw new JsonException(errorInfo(float.class));
        }
    }

    public double getAsDouble() {
        try {
            return Double.parseDouble(element);
        } catch (NumberFormatException e) {
            throw new JsonException(errorInfo(double.class));
        }
    }

    public boolean getAsBoolean() {
        try {
            return Boolean.parseBoolean(element);
        } catch (NumberFormatException e) {
            throw new JsonException(errorInfo(boolean.class));
        }
    }

    public String getAsString() {
        return element.startsWith("\"") && element.endsWith("\"") ?
                element.replace("\"", "") :
                element;
    }

    public JsonNull getAsJsonNull() {
        if (isJsonNull()) {
            return JsonNull.INSTANCE;
        } else {
            throw new JsonException(errorInfo(JsonNull.class));
        }
    }

    public JsonObject getAsJsonObject() {
        if (isJsonObject()) {
            return new JsonObject(element);
        } else {
            throw new JsonException(errorInfo(JsonObject.class));
        }
    }

    public JsonArray getAsJsonArray() {
        if (isJsonArray()) {
            return new JsonArray(element);
        } else {
            throw new JsonException(errorInfo(JsonArray.class));
        }
    }


    public boolean isJsonObject() {
        return (element.startsWith("{") &&
                element.endsWith("}") &&
                element.contains("\"") &&
                element.contains(":")) ||
                "{}".equals(element);
    }

    public boolean isJsonArray() {
        return (element.startsWith("[") &&
                element.endsWith("]") &&
                element.contains(",")) ||
                "[]".equals(element) ||
                com.meyoustu.amuse.gous.util.Constants.bytesToString(
                        (byte) '[', (byte) '"', (byte) 'n', (byte) 'u', (byte) 'l',
                        (byte) 'l', (byte) '"', (byte) ']'
                ).equals(element);
    }

    public boolean isJsonNull() {
        return JsonNull.INSTANCE.equals(element);
    }


    private String errorInfo(Class clazz) {
        return com.meyoustu.amuse.gous.util.Constants.bytesToString(
                (byte) 'J', (byte) 's', (byte) 'o', (byte) 'n', (byte) 'E',
                (byte) 'l', (byte) 'e', (byte) 'm', (byte) 'e', (byte) 'n',
                (byte) 't', (byte) ' ', (byte) 'c', (byte) 'a', (byte) 'n',
                (byte) 'n', (byte) 'o', (byte) 't', (byte) ' ', (byte) 'b',
                (byte) 'e', (byte) ' ', (byte) 'c', (byte) 'o', (byte) 'n',
                (byte) 's', (byte) 'i', (byte) 'd', (byte) 'e', (byte) 'r',
                (byte) 'e', (byte) 'd', (byte) ' ', (byte) 'a', (byte) 's',
                (byte) ' '
        ) + clazz.getSimpleName() + com.meyoustu.amuse.gous.util.Constants.bytesToString(
                (byte) ' ', (byte) 'a', (byte) 'c', (byte) 'q', (byte) 'u',
                (byte) 'i', (byte) 's', (byte) 'i', (byte) 't', (byte) 'i',
                (byte) 'o', (byte) 'n', (byte) '.'
        );
    }

}
