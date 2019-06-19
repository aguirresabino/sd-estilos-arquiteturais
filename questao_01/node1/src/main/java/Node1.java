import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Node1 {

    private static final Logger log = Logger.getLogger(Node1.class.getName());
    private static final int PORTA_NODE2 = 12345;

    public static void main(String args[]) {
        Random random = new Random();
        for(;;) {
            List<Integer> numeros = Arrays.asList(random.nextInt(100), random.nextInt(100));
            try {
                Socket cliente = new Socket("127.0.0.1", PORTA_NODE2);
                log.info("[NODE1] O cliente se conectou ao servidor na porta " + PORTA_NODE2);
                log.info("[NODE1] Números enviados: " + numeros);
                // Enviando uma lista de Integer
                ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
                oos.writeObject(numeros);
                oos.flush();
                // Aguardando resposta do node2
                ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
                log.info("[NODE1] Servidor respondeu com o valor: " + ois.readInt());

                oos.close();
                ois.close();
                cliente.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
