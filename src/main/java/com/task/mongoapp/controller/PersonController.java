package com.task.mongoapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.mongoapp.data.PersonData;
import com.task.mongoapp.dto.PersonFindDto;
import com.task.mongoapp.dto.RestResponse;
import com.task.mongoapp.jsonmodel.Person;
import com.task.mongoapp.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@RestController
@RequestMapping("/api/people/")
@RequiredArgsConstructor
public class PersonController {

    private final PeopleService peopleService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse savePeople(@RequestPart("zip") MultipartFile file) throws IOException {
        List<Person> personList;
        File temp = File.createTempFile("geeks", "");
        try (OutputStream os = new FileOutputStream(temp)) {
            os.write(file.getBytes());
        }
        ZipFile zipFile = new ZipFile(temp);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        ZipEntry entry = entries.nextElement();
        try (InputStream input = zipFile.getInputStream(entry)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String line = reader.readLine();
                ObjectMapper mapper = new ObjectMapper();
//               JsonNode node=mapper.readTree(line);
//               for(JsonNode jsonNode :node){
//                   System.out.printf(jsonNode.asText());
//                   break;
//               }
                 personList = mapper.readValue(line, new TypeReference<>() {
                });
                List<PersonData> personData = new ArrayList<>();
                //  net.sf.json.JSONArray array = net.sf.json.JSONArray.fromObject(json);

                //   coll.drop();
                int count = 0;
                int batch = 100;
                //  List<InsertOneModel<Document>> docs = new ArrayList<>();

                String json = null;
                for(int i=0;i<personList.size();i++){
                    personList.get(i).setId(String.valueOf(i+1));
                }
                //  String json=mapper.writeValueAsString(personList.get(0));



            }
        }
        peopleService.createPeople(personList);
        return new RestResponse("OK");
    }

    @GetMapping("get/{fullname}")
    public PersonFindDto getPeople(@PathVariable String fullname){
      PersonFindDto person= peopleService.findByFIO(fullname);
      return person;
    }

    @PostMapping("_search")
    public List<String> getPOY(){
        return null;
    }
}
