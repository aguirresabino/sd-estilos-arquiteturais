import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Logger;

public class Node3 {

    private static final Logger log = Logger.getLogger(Node3.class.getName());
    private static final int PORTA = 4321;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket servidor = new ServerSocket(PORTA);
        log.info("[NODE3] Executando na porta " + PORTA);
        for(;;) {
            Socket cliente = servidor.accept();
            log.info("[NODE3] Conexão aceita para o cliente: " + cliente.getInetAddress().getHostAddress());
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

            List<Integer> numeros = (List<Integer>) ois.readObject();
            log.info("[NODE3] Números enviados pelo cliente: " + numeros);

            Double resposta = Math.pow(numeros.get(0), numeros.get(0)) + Math.pow(numeros.get(1), numeros.get(1));

            log.info("[NODE3] Valor encontrado após calculo: " + resposta);

            ois.close();
            oos.flush();
            oos.close();
            cliente.close();
//            servidor.close();
        }
    }
}
