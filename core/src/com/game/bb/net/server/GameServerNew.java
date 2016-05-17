package com.game.bb.net.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.game.bb.net.packets.EntityPacket;
import com.game.bb.net.packets.TCPEventPacket;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by erik on 17/05/16.
 */
public class GameServerNew extends Listener {

    private int udpPort = 8080, tcpPort = 8081;
    private Server kryoServer;

    public GameServerNew(){
        kryoServer = new Server();
        kryoServer.getKryo().register(EntityPacket.class);
        kryoServer.getKryo().register(TCPEventPacket.class);
        try {
            kryoServer.bind(tcpPort, udpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        kryoServer.addListener(this);
        kryoServer.start();
        Gdx.app.log("NET_SERVER", "Game server started at " + TimeUtils.millis());
    }

    @Override
    public void connected(Connection c){
        Gdx.app.log("NET_CONNECTION", "Client @" + c.getRemoteAddressUDP().getAddress() + " connected.");
    }

    @Override
    public void received(Connection c, Object packet){
        Gdx.app.log("NET_SERVER_PACKET_RECEIVED", packet.getClass().toString());
        for (Connection connection : kryoServer.getConnections()){
            if (!c.equals(connection)){
                connection.sendUDP(packet);
            }
        }
    }

    @Override
    public void disconnected(Connection c){
        //Maybe add code here later
    }
}
