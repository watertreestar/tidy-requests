package com.github.watertreestar.requests;

import java.io.Serializable;
import java.util.Map;

public class Parameter<T> implements Map.Entry<String, T>, Serializable {
    protected final String name;
    protected final T value;

    public Parameter(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public static <V> Parameter<V> of(String name, V value) {
        return new Parameter<>(name, value);
    }

    public String name() {
        return this.name;
    }

    public T value() {
        return this.value;
    }

    @Override
    public String getKey() {
        return name;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public T setValue(T value) {
        throw new UnsupportedOperationException("Parameter is read only");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parameter<?> parameter = (Parameter<?>) o;

        if (!name.equals(parameter.name)) return false;
        return value.equals(parameter.value);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "(" + name + " = " + value + ")";
    }
}
