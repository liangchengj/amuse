package com.google.gson;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/17 13:31
 */
interface Json<E> {

    JsonElement getJsonElement(E sign);

    default short getShort(E sign) {
        return getJsonElement(sign).getAsShort();
    }

    default int getInt(E sign) {
        return getJsonElement(sign).getAsInt();
    }

    default long getLong(E sign) {
        return getJsonElement(sign).getAsLong();
    }

    default float getFloat(E sign) {
        return getJsonElement(sign).getAsFloat();
    }

    default double getDouble(E sign) {
        return getJsonElement(sign).getAsDouble();
    }

    default boolean getBoolean(E sign) {
        return getJsonElement(sign).getAsBoolean();
    }

    default String getString(E sign) {
        return getJsonElement(sign).getAsString();
    }

    default JsonNull getJsonNull(E sign) {
        return getJsonElement(sign).getAsJsonNull();
    }

    default JsonObject getJsonObject(E sign) {
        return getJsonElement(sign).getAsJsonObject();
    }


    default JsonArray getJsonArray(E sign) {
        return getJsonElement(sign).getAsJsonArray();
    }
}
