package com.vector.app.individual;

public class Ids {
    private static long idCounter = 0;

    public static synchronized String createID()
    {
        return String.valueOf(idCounter++);
    }
}
