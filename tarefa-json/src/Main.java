import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import java.net.URL;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        String response = HttpRequest("GET", "https://jsonplaceholder.typicode.com/users");
        List<User> usuarios = Arrays.asList(new Gson().fromJson(response, User[].class));
        System.out.println("Usuários:\n");
        for(User user : usuarios){
            System.out.println("Id: " + user.getId() + ", Nome: " + user.getName() + ", Email: " + user.getEmail());
            String postJson = HttpRequest("GET", "https://jsonplaceholder.typicode.com/users/" + user.getId() + "/posts");
            List<Post> posts = Arrays.asList(new Gson().fromJson(postJson, Post[].class));
        }
        System.out.println("Criando arquivo .json\n");
        String json = new Gson().toJson(usuarios);
        FileWriter fileWriter = new FileWriter("result.json");
        fileWriter.write(json.toString());
        fileWriter.flush();
        fileWriter.close();
    }

    public static String HttpRequest(String method, String urlRequest) {
        StringBuilder stringBuilder = new StringBuilder();
        URL url = new URL(urlRequest);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod(method);
        conexao.setRequestProperty("Accept", "application/json");
        InputStreamReader entrada = new InputStreamReader(conexao.getInputStream());
        BufferedReader br = new BufferedReader(entrada);
        String saida;

        while ((saida = br.readLine()) != null) {
            stringBuilder.append(saida);
        }
        return stringBuilder.toString();
    }

}
