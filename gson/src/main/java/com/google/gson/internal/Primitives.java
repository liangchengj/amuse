/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal;

import java.lang.reflect.Type;

/**
 * Contains static utility methods pertaining to primitive types and their
 * corresponding wrapper types.
 *
 * @author Kevin Bourrillion
 */
public final class Primitives {
    private Primitives() {
    }

    /**
     * Returns true if this type is a primitive.
     */
    public static boolean isPrimitive(Type type) {
        return type instanceof Class<?> && ((Class<?>) type).isPrimitive();
    }

    /**
     * Returns {@code true} if {@code type} is one of the nine
     * primitive-wrapper types, such as {@link Integer}.
     *
     * @see Class#isPrimitive
     */
    public static boolean isWrapperType(Type type) {
        return type == Byte.class ||
                type == Short.class ||
                type == Integer.class ||
                type == Long.class ||
                type == Float.class ||
                type == Double.class ||
                type == Boolean.class ||
                type == Character.class ||
                type == Void.class;
    }

    /**
     * Returns the corresponding wrapper type of {@code type} if it is a primitive
     * type; otherwise returns {@code type} itself. Idempotent.
     * <pre>
     *     wrap(int.class) == Integer.class
     *     wrap(Integer.class) == Integer.class
     *     wrap(String.class) == String.class
     * </pre>
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> wrap(Class<T> type) {
        if (type == byte.class) {
            return (Class<T>) Byte.class;
        } else if (type == short.class) {
            return (Class<T>) Short.class;
        } else if (type == int.class) {
            return (Class<T>) Integer.class;
        } else if (type == long.class) {
            return (Class<T>) Long.class;
        } else if (type == float.class) {
            return (Class<T>) Float.class;
        } else if (type == double.class) {
            return (Class<T>) Double.class;
        } else if (type == boolean.class) {
            return (Class<T>) Boolean.class;
        } else if (type == char.class) {
            return (Class<T>) Character.class;
        } else if (type == void.class) {
            return (Class<T>) Void.class;
        }
        return type;
    }

    /**
     * Returns the corresponding primitive type of {@code type} if it is a
     * wrapper type; otherwise returns {@code type} itself. Idempotent.
     * <pre>
     *     unwrap(Integer.class) == int.class
     *     unwrap(int.class) == int.class
     *     unwrap(String.class) == String.class
     * </pre>
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> unwrap(Class<T> type) {
        if (type == Byte.class) {
            return (Class<T>) byte.class;
        } else if (type == Short.class) {
            return (Class<T>) short.class;
        } else if (type == Integer.class) {
            return (Class<T>) int.class;
        } else if (type == Long.class) {
            return (Class<T>) long.class;
        } else if (type == Float.class) {
            return (Class<T>) float.class;
        } else if (type == Double.class) {
            return (Class<T>) double.class;
        } else if (type == Boolean.class) {
            return (Class<T>) boolean.class;
        } else if (type == Character.class) {
            return (Class<T>) char.class;
        } else if (type == Void.class) {
            return (Class<T>) void.class;
        }
        return type;
    }
}
