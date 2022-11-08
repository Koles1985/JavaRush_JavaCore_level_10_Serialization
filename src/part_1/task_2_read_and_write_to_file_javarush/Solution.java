package part_1.task_2_read_and_write_to_file_javarush;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Solution {

    public Solution() {
        try{
            File yourFile = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            /*
            User petrov = new User();
            User petrova = new User();
            petrov.setFirstName("Petr");
            petrov.setLastName("Petrov");
            petrov.setBirthDate(new Date(-85, 4, 23));
            petrov.setMale(true);
            petrov.setCountry(User.Country.RUSSIA);
            petrova.setFirstName("Petra");
            petrova.setLastName("Petrova");
            petrova.setBirthDate(new Date(85, 4, 23));
            petrova.setMale(false);
            petrova.setCountry(User.Country.UKRAINE);
            javaRush.users.add(petrov);
            javaRush.users.add(petrova);
             */
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            outputStream.close();
            inputStream.close();
            for(User user : javaRush.users){
                System.out.println(user.getFirstName());
                System.out.println(user.getLastName());
                System.out.println(user.getBirthDate().toString());
                System.out.println(user.isMale());
                System.out.println(user.getCountry().getDisplayName());
            }
            System.out.println("----------------------------------");
            for(User user : loadedObject.users){
                System.out.println(user.getFirstName());
                System.out.println(user.getLastName());
                System.out.println(user.getBirthDate().toString());
                System.out.println(user.isMale());
                System.out.println(user.getCountry().getDisplayName());
            }
        }catch(IOException e) {
            e.printStackTrace();
            System.out.println("Oops something is wrong with my file");
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong width the save/load method");
        }
    }

    public class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save (OutputStream outputStream) throws Exception {
            PrintWriter printWriter = new PrintWriter(outputStream);
            if(!users.isEmpty())
                for(User user : users){
                    printWriter.println(user.getFirstName());
                    printWriter.println(user.getLastName());
                    printWriter.println(user.getBirthDate().getTime());
                    printWriter.println(Boolean.toString(user.isMale()));
                    printWriter.println(user.getCountry().getDisplayName());
                }
            printWriter.close();
        }

        public void load (InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            User user;
            while (reader.ready()) {
                user = new User();
                user.setFirstName(reader.readLine());
                user.setLastName(reader.readLine());
                user.setBirthDate(new Date(Long.parseLong(reader.readLine())));
                user.setMale(Boolean.parseBoolean(reader.readLine()));
                switch (reader.readLine()){
                    case "Украина" :
                        user.setCountry(User.Country.UKRAINE);
                        break;
                    case "Россия" :
                        user.setCountry(User.Country.RUSSIA);
                        break;
                    case "Другая" :
                        user.setCountry(User.Country.OTHER);
                        break;
                }
                this.users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if(this == o)
                return true;
            if(o == null || getClass() != o.getClass())
                return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush == null;
        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
