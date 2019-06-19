import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;

public class Node3 {

    private static final Logger log = Logger.getLogger(Node3.class.getName());

    public static void main(String args[]) throws ClassNotFoundException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("[NODE3] Servidor executando na porta: " + serverSocket.getLocalPort());

        for(;;) {
            Map<String, Integer> op = null;
            try {
                Socket cliente = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
                op = (Map<String, Integer>) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(op.get("op").equals(2)) {
                log.info("[NODE3] Executando operação 2: 2 * " + op.get("num1") + " / " + op.get("num2") + " = " + operacao(op.get("num1"), op.get("num2")));
            } else {
                log.info("[NODE3] Enviando requisição para NODE1 ou NODE2");
                Socket node1 = null;
                try {
                    node1 = new Socket("127.0.0.1",1234);
                } catch (IOException e) {
                    try {
                        node1 = new Socket("127.0.0.1",1233);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                }

                try {
                    ObjectOutputStream oos = new ObjectOutputStream(node1.getOutputStream());
                    oos.writeObject(op);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static double operacao(int num1, int num2) {
        return 2 * num1 / num2;
    }
}
