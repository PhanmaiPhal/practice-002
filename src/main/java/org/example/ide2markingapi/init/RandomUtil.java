package org.example.ide2markingapi.init;

import java.util.Random;

public class RandomUtil {

    public static String generate9Digits() {
        Random random=new Random();
        return String.format("%09d",random.nextInt(10000000));
    }
}
