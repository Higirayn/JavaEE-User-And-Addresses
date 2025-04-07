package com.example.demo1.util;

import com.example.demo1.model.Address;
import com.example.demo1.model.Client;
import jakarta.validation.ValidationException;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final Pattern RUSSIAN_NAME_PATTERN = 
        Pattern.compile("^[А-Яа-я\\s\\-,.]+$");
    private static final Pattern IP_PATTERN = 
        Pattern.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
    private static final Pattern MAC_PATTERN = 
        Pattern.compile("^([0-9A-Fa-f]{2}[-]){5}([0-9A-Fa-f]{2})$");

    public static void validateClient(Client client) throws ValidationException {
        if (client == null || client.getClientName() == null || client.getClientName().trim().isEmpty()) {
            throw new ValidationException("Наименование клиента не может быть пустым");
        }
        
        if (client.getClientName().length() > 20) {
            throw new ValidationException("Наименование клиента слишком длинное");
        }

//        if (!RUSSIAN_NAME_PATTERN.matcher(client.getClientName()).matches()) {
//            throw new ValidationException("Наименование клиента содержит недопустимые символы");
//        }
        
//        if (!"Юридическое лицо".equals(client.getType()) && !"Физическое лицо".equals(client.getType())) {
//            throw new ValidationException("Недопустимый тип клиента");
//        }
    }

    public static void validateAddress(Address address) throws ValidationException {
        if (address == null || address.getIp() == null || address.getIp().trim().isEmpty()) {
            throw new ValidationException("IP-адрес не может быть пустым");
        }
        
        if (!IP_PATTERN.matcher(address.getIp()).matches()) {
            throw new ValidationException("Недопустимый формат IP-адреса");
        }
        
        if (address.getMac() == null || address.getMac().trim().isEmpty()) {
            throw new ValidationException("MAC-адрес не может быть пустым");
        }
        
        if (!MAC_PATTERN.matcher(address.getMac()).matches()) {
            throw new ValidationException("Недопустимый формат MAC-адреса");
        }
        
        if (address.getAddress() == null || address.getAddress().trim().isEmpty()) {
            throw new ValidationException("Адрес места нахождения не может быть пустым");
        }
        
        if (address.getAddress().length() > 200) {
            throw new ValidationException("Адрес места нахождения слишком длинный");
        }
    }
}