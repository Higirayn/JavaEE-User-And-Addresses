package com.example.demo1.utils;

public class ValidationUtils {

    public static boolean isValidClientName(String name) {
        return name != null && name.matches("^[А-Яа-яЁё\\-\\s,.]{1,100}$");
    }

    public static boolean isValidType(String type) {
        return "Юридическое лицо".equals(type) || "Физическое лицо".equals(type);
    }

    public static boolean isValidIp(String ip) {
        return ip.matches("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$");
    }

    public static boolean isValidMac(String mac) {
        return mac.matches("^([0-9A-Fa-f]{2}-){5}[0-9A-Fa-f]{2}$");
    }
}