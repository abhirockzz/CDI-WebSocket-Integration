package websocket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/wsep")
public class WSEndpoint {

    private Session client;

    @OnOpen
    public void onOpen(Session session) {
        this.client = session;
        try {
            client.getBasicRemote().sendText("you are connected. your ID is " + client.getId());
        } catch (IOException ex) {
            Logger.getLogger(WSEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("WS End point class ID -- " + this.hashCode());
    }

    //@OnMessage
    //public void onMsg(@Observes(notifyObserver = Reception.IF_EXISTS) String msg){
    public void onMsg(@Observes String msg) {

        //different WS enpoint instance - notice the hash code value in the server log
        System.out.println("WS End point class ID -- " + this.hashCode());
        try {

            client.getBasicRemote().sendText(msg);

        } catch (IOException ex) {
            Logger.getLogger(ServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnClose
    public void onClose() {
        System.out.println(client.getId() + " disconnected ");
    }
}
