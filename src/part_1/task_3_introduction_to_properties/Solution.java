package part_1.task_3_introduction_to_properties;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Solution {
    public Map<String, String> runtimeStorage = new HashMap<>();
    public Solution() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            FileInputStream fis = new FileInputStream(reader.readLine()); //Не понимает Русский
            FileReader fr = new FileReader(reader.readLine(), Charset.forName("UTF-8"));
            load(fr);

        }catch(IOException e) {
            e.printStackTrace();
        }

        System.out.println(runtimeStorage);

    }

    public void save(OutputStream outputStream) throws Exception {
        Properties properties = new Properties();
        for(Map.Entry<String, String> entry : runtimeStorage.entrySet()){
            properties.setProperty(entry.getKey(), entry.getValue());
        }

        properties.store(outputStream, null);
    }

    public void load (InputStream inputStream) throws IOException {
        Properties prop = new Properties();
        prop.load(inputStream);
        for (Map.Entry<Object, Object> entry : prop.entrySet()){
            runtimeStorage.put(entry.getKey().toString(), entry.getValue().toString());
        }
    }
    public void load (InputStreamReader inputStreamReader) throws IOException {
        Properties prop = new Properties();
        prop.load(inputStreamReader);
        for (Map.Entry<Object, Object> entry : prop.entrySet()){
            runtimeStorage.put(entry.getKey().toString(), entry.getValue().toString());
        }
    }
}
