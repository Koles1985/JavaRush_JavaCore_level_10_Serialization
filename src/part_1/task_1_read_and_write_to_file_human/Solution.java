package part_1.task_1_read_and_write_to_file_human;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public Solution() {
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99),
                    new Asset("car", 2999.99));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);

            System.out.println(somePerson.name);
            System.out.println(somePerson.assets.get(0).getName());
            System.out.println(somePerson.assets.get(0).getPrice());
            System.out.println(somePerson.assets.get(1).getName());
            System.out.println(somePerson.assets.get(1).getPrice());
            inputStream.close();
        }catch (IOException e){
            System.out.println("Ops something wrong with my file");
            e.printStackTrace();
        }catch(Exception e){
            System.out.println("Something wring save/load method");
            e.printStackTrace();
        }
    }

    public class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human (String name, Asset... assets) {
            this.name = name;
            if(assets != null){
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if(this == o)
                return true;
            if(o == null || getClass() != o.getClass())
                return false;

            Human human = (Human) o;

            if(name != null ? !name.equals(human.name) : human.name != null)
                return false;

            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            //?????????????????????? ??????????
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            if(name == null)
                return;

            writer.write(name + "\n");

            if(!assets.isEmpty()) {
                for(Asset asset : assets){
                    writer.write(asset.getName() + "\n");
                    writer.write(String.valueOf(asset.getPrice()) + "\n");
                }
            }

            writer.close();

        }

        public void load(InputStream inputStream) throws Exception {
            //?????????????????????? ??????????
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(inputStream));
            this.name = reader.readLine();
            while(reader.ready()){
                String assetName = reader.readLine();
                double assetPrice = Double.parseDouble(reader.readLine());
                this.assets.add(new Asset(assetName, assetPrice));
            }

            reader.close();
        }
    }

}
