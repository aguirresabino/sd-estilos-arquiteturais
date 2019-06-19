import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;

public class Node1 {
    private static final Logger log = Logger.getLogger(Node1.class.getName());
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket;
        try {
            serverSocket = iniciarServidor(1234);
        } catch (IOException e) {
            serverSocket = iniciarServidor(1233);
            e.printStackTrace();
        }

        log.info("[NODE1] Servidor executando na porta: " + serverSocket.getLocalPort());

        for(;;) {
            Socket cliente = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            Map<String, Integer> op = (Map<String, Integer>) ois.readObject();

            if(op.get("op").equals(1)) {
                log.info("[NODE1] Executando operação 1: 2 * " + op.get("num1") + " * " + op.get("num2") + " = " + operacao(op.get("num1"), op.get("num2")));
            } else {
                log.info("[NODE1] Enviando requisição para NODE3");
                Socket node3 = new Socket("127.0.0.1",4444);
                ObjectOutputStream oos = new ObjectOutputStream(node3.getOutputStream());
                oos.writeObject(op);
            }
        }
    }

    private static double operacao(int num1, int num2) {
        return 2 * num1 * num2;
    }

    private static ServerSocket iniciarServidor(int porta) throws IOException {
        return new ServerSocket(porta);
    }
}
