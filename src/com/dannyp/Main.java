package com.dannyp;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

public class Main {

    public static DbxClientV2 client;

    public static void main(String[] args) {
        String ACCESS_TOKEN = "<YOUR_TOKEN>";
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);

        NewThread thread = new NewThread();
        thread.start();
    }
}