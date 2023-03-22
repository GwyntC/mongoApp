package com.task.mongoapp.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.task.mongoapp.jsonmodel.Person;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipJsonReader {
    public static List<Person> readZip(MultipartFile file) throws IOException {
        if (!Objects.requireNonNull(file.getOriginalFilename()).contains(".zip")) {
            throw new LayerInstantiationException("Not correct input;provide zip archive");
        }
        //int count = 0;
        //int batch = 100;
        List<Person> personList = new ArrayList<>();
        File temp = File.createTempFile("geeks", "");
        try (OutputStream os = new FileOutputStream(temp)) {
            os.write(file.getBytes());
        }
        try (ZipFile zipFile = new ZipFile(temp)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            ZipEntry entry = entries.nextElement();
            try (InputStream input = zipFile.getInputStream(entry)) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                try (JsonParser jsonParser = mapper.getFactory().createParser(input)) {
                    if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
                        throw new IllegalStateException("Expected content to be an array");
                    }
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        Person person = mapper.readValue(jsonParser, Person.class);

                        personList.add(person);
                    }
                }
            }
        }
        return personList;
    }
}
