import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;

public class Node4 {
    private static final Logger log = Logger.getLogger(Node4.class.getName());

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(3232);

        for (; ; ) {
            Socket cliente = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            Map<String, Integer> operacao = (Map<String, Integer>) ois.readObject();

            Integer resultado = null;

            if(operacao.get("node") == 2) resultado = operacao.get("num1") + operacao.get("num2");
            if(operacao.get("node") == 3) resultado = operacao.get("num1") - operacao.get("num2");

            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            oos.writeObject(resultado);
            oos.flush();
            ois.close();
            oos.close();
            cliente.close();
        }
    }
}
