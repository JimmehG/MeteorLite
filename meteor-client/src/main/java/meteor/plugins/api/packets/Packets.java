package meteor.plugins.api.packets;

import meteor.plugins.api.game.Game;
import net.runelite.api.Client;
import net.runelite.api.packets.ClientPacket;
import net.runelite.api.packets.PacketBufferNode;
import net.runelite.api.packets.PacketWriter;

import javax.inject.Inject;

public class Packets {
    
    public static class Dialog {
        public static void sendNumberInput(int number) {
            queuePacket(Game.getClient().getNumberInputPacket(), number);
        }

        public static void sendTextInput(String text) {
            queuePacket(Game.getClient().getTextInputPacket(), text);
        }

        public static void sendNameInput(String name) {
            queuePacket(Game.getClient().getNameInputPacket(), name);
        }
    }

    public static void queuePacket(ClientPacket clientPacket, Object... data) {
        PacketWriter writer = Game.getClient().getPacketWriter();
        PacketBufferNode packet = Game.getClient().preparePacket(clientPacket, writer.getIsaacCipher());

        for (Object o : data) {
            if (o instanceof Byte) {
                packet.getPacketBuffer().writeByte$api((int) o);
                continue;
            }

            if (o instanceof Short) {
                packet.getPacketBuffer().writeShort$api((int) o);
                continue;
            }

            if (o instanceof Integer) {
                packet.getPacketBuffer().writeInt$api((int) o);
                continue;
            }

            if (o instanceof Long) {
                packet.getPacketBuffer().writeLong$api((long) o);
                continue;
            }

            if (o instanceof String) {
                packet.getPacketBuffer().writeStringCp1252NullTerminated$api( (String) o);
                continue;
            }

            // invalid data
            return;
        }

        writer.queuePacket(packet);
    }
}
