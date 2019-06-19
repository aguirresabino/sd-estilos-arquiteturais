import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Logger;

public class Node2 {

    private static final Logger log = Logger.getLogger(Node2.class.getName());
    private static final int PORTA = 12345;
    private static final int PORTA_NODE3 = 4321;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket servidor = new ServerSocket(PORTA);
        log.info("[NODE2] Escutando na porta " + PORTA);
        for(;;) {
            Socket cliente = servidor.accept();
            log.info("[NODE2] Cliente conectado: " + cliente.getLocalAddress());
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

            List<Integer> numeros = (List<Integer>) ois.readObject();
            log.info("[NODE2] Números enviados pelo cliente: " + numeros);

            if(numereosDiferentes(numeros.get(0), numeros.get(1))) {
                log.info("[NODE2] Enviando números para o NODE3");
                enviarRespostaParaNode3(numeros);
                retornarParaONode1(cliente, 1);
            } else {
                log.info("[NODE2] Retornando valor 0 (zero) para NODE1");
                retornarParaONode1(cliente, 0);
            }

            ois.close();
            cliente.close();
//            servidor.close();
        }
    }

    public static boolean numereosDiferentes(int num1, int num2) {
        return num1 != num2;
    }

    public static void enviarRespostaParaNode3(List<Integer> numeros) throws IOException {
       Socket socket = new Socket("127.0.0.1", PORTA_NODE3);
       ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
       oos.writeObject(numeros);
       oos.flush();
    }

    public static void retornarParaONode1(Socket node1, int num) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(node1.getOutputStream());
        oos.writeInt(num);
        oos.flush();
    }
}
