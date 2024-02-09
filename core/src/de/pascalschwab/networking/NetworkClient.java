package de.pascalschwab.networking;

import com.badlogic.gdx.utils.Disposable;
import de.pascalschwab.networking.messages.ClientConnectedMessage;
import de.pascalschwab.networking.messages.ClientDisconnectedMessage;
import de.pascalschwab.networking.messages.NetworkMessage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public abstract class NetworkClient implements NetworkObject, Disposable {
    private ClientSocket socket;

    public NetworkClient() {
    }

    public void connect(String address, int port) {
        try{
            socket = new ClientSocket(new Socket(InetAddress.getByName(address), port));
        }
        catch (IOException e){
            throw new RuntimeException("Can't connect to the server " + address);
        }

        HandleClientThread handleThread = new HandleClientThread(this, socket);
        handleThread.start();
    }

    @Override
    public void send(ClientSocket sender, NetworkMessage message) {
        if(!socket.isClosed()){
            try{
                socket.getOut().writeObject(message);
                socket.getOut().flush();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        else{
            System.err.println("Can't send message! Connection is closed!");
        }
    }

    protected abstract void onMessageArrived(ClientSocket sender, NetworkMessage message);
    @Override
    public final void handleMessageArrived(ClientSocket sender, NetworkMessage message){
        switch (message.getRequestType()){
            case CLIENT_DISCONNECTED:
                handleClientDisconnected((ClientDisconnectedMessage) message);
                break;
            case CLIENT_CONNECTED:
                handleClientConnected((ClientConnectedMessage) message);
                break;
            default:
                onMessageArrived(sender, message);
                break;
        }
    }
    @Override
    public void dispose() {
        socket.dispose();
    }

    public ClientSocket getSocket() {
        return socket;
    }

    protected abstract void onClientDisconnected(ClientDisconnectedMessage message);

    private void handleClientDisconnected(ClientDisconnectedMessage message){
        onClientDisconnected(message);
        this.dispose();
    }

    protected abstract void onClientConnected(ClientConnectedMessage message);

    private void handleClientConnected(ClientConnectedMessage message){
        onClientConnected(message);
    }
}
