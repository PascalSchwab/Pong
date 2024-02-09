package de.pascalschwab;

import de.pascalschwab.networking.ClientSocket;
import de.pascalschwab.networking.NetworkServer;
import de.pascalschwab.networking.messages.NetworkMessage;

public class Server extends NetworkServer {
    public Server(int port) {
        super(port);
    }

    public static void main(String[] args){
        Server server = new Server(3000);
        server.start();
    }

    @Override
    protected void onMessageArrived(ClientSocket socket, NetworkMessage message) {
        System.out.println(message.getObject());
    }

    @Override
    protected void onClientDisconnected(ClientSocket client) {

    }
}
