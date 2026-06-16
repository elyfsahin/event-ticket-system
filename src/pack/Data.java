package pack;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Data {
    
    public static List<User> registeredUsers = new ArrayList<>();
    public static List<Event> allEvents = new ArrayList<>();


    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) 
            (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
        .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) 
            (json, typeOfT, context) -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
        .setPrettyPrinting()
        .create();


    public static void saveData() {
        try {
            
            FileWriter userWriter = new FileWriter("users.json");
            gson.toJson(registeredUsers, userWriter);
            userWriter.close();

       
            FileWriter eventWriter = new FileWriter("events.json");
            gson.toJson(allEvents, eventWriter);
            eventWriter.close();
            
            System.out.println("System: Succesfull");
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    
    public static void loadData() {
        try {
            
            File userFile = new File("users.json");
            if (userFile.exists()) {
                FileReader reader = new FileReader(userFile);
                Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
                List<User> loadedUsers = gson.fromJson(reader, userListType);
                if (loadedUsers != null) {
                    registeredUsers.clear();
                    registeredUsers.addAll(loadedUsers);
                }
                reader.close();
            }

           
            File eventFile = new File("events.json");
            if (eventFile.exists()) {
                FileReader reader = new FileReader(eventFile);
                Type eventListType = new TypeToken<ArrayList<Event>>(){}.getType();
                List<Event> loadedEvents = gson.fromJson(reader, eventListType);
                if (loadedEvents != null) {
                    allEvents.clear(); 
                    allEvents.addAll(loadedEvents); 
                    System.out.println("Sistem: " + allEvents.size() + " event loaded.");
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}