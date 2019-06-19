import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;

public class Cliente {
    private static final Logger log = Logger.getLogger(Cliente.class.getName());

    public static void main(String args[]) {

        Map<String, Integer> data = Map.of("op", 2, "num1", 10, "num2", 5);

        try  {
            Socket cliente = new Socket("127.0.0.1", 1234);

            ObjectOutputStream outputStream = new ObjectOutputStream(cliente.getOutputStream());
            outputStream.writeObject(data);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
