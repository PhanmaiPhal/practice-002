package org.example.ide2markingapi.init;

public class MediaUtil {
    public static String extractExtension(String mediaName){
        int lastDotIndex= mediaName.lastIndexOf(".");
        return mediaName.substring(lastDotIndex+1);
    }
}
