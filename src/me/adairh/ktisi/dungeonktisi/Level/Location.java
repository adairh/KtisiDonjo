package me.adairh.ktisi.dungeonktisi.Level;

import java.util.Objects;

public class Location<K1, K2>
{
    public K1 key1;
    public K2 key2;

    public Location(K1 key1, K2 key2)
    {
        this.key1 = key1;
        this.key2 = key2;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Location key = (Location) o;
        if (!Objects.equals(key1, key.key1)) {
            return false;
        }

        if (!Objects.equals(key2, key.key2)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = key1 != null ? key1.hashCode() : 0;
        result = 31 * result + (key2 != null ? key2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "[" + key1 + ", " + key2 + "]";
    }
}
