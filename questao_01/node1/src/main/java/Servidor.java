import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket servidor = new ServerSocket(12345);

        Socket cliente = servidor.accept();
        ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());

        List<Integer> numeros = (List<Integer>) ois.readObject();

        if(numeros.get(0) != numeros.get(1)) {
            oos.writeInt(1);
        } else {
            oos.writeInt(0);
        }

        oos.flush();
    }
}
