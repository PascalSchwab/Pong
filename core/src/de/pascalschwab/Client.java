package de.pascalschwab;

import com.badlogic.gdx.Gdx;
import de.pascalschwab.networking.ClientSocket;
import de.pascalschwab.networking.NetworkClient;
import de.pascalschwab.networking.messages.ClientConnectedMessage;
import de.pascalschwab.networking.messages.ClientDisconnectedMessage;
import de.pascalschwab.networking.messages.NetworkMessage;

public class Client extends NetworkClient {
    @Override
    protected void onMessageArrived(ClientSocket sender, NetworkMessage message) {
    }

    @Override
    protected void onClientDisconnected(ClientDisconnectedMessage message) {

    }

    @Override
    protected void onClientConnected(ClientConnectedMessage message) {
        Gdx.app.log("Client", "Connected");
    }
}
