package org.rscdaemon.ls.packethandler.loginserver;

import org.rscdaemon.ls.packethandler.PacketHandler;
import org.rscdaemon.ls.Server;
import org.rscdaemon.ls.model.World;
import org.rscdaemon.ls.net.LSPacket;
import org.rscdaemon.ls.net.Packet;
import org.rscdaemon.ls.util.DataConversions;
import org.rscdaemon.ls.packetbuilder.loginserver.ReplyPacketBuilder;

import org.apache.mina.common.IoSession;

import java.sql.ResultSet;

public class BanHandler implements PacketHandler {
	private ReplyPacketBuilder builder = new ReplyPacketBuilder();

	public void handlePacket(Packet p, IoSession session) throws Exception {
		final long uID = ((LSPacket)p).getUID();
		boolean banned = ((LSPacket)p).getID() == 4;
		long user = p.readLong();
		
		ResultSet result = Server.db.getQuery("SELECT u.group_id, p.playermod FROM `users` AS u INNER JOIN `rscd_players` AS p ON p.owner=u.id WHERE p.user=" + user);
		if(!result.next()) {
      			builder.setSuccess(false);
      			builder.setReply("There is not an account by that username");
		}
		else if(banned && (result.getInt("group_id") < 3 || result.getInt("playermod") == 1)) {
      			builder.setSuccess(false);
      			builder.setReply("You cannot ban a (p)mod or admin!");
		}
      		else if(Server.db.updateQuery("UPDATE `rscd_players` SET `banned`='" + (banned ? "1" : "0") + "' WHERE `user` LIKE '" + user + "'") == 0) {
      			builder.setSuccess(false);
      			builder.setReply("There is not an account by that username");
      		}
      		else {
      			World w = Server.getServer().findWorld(user);
      			if(w != null) {
      				w.getActionSender().logoutUser(user);
      			}
      			builder.setSuccess(true);
      			builder.setReply(DataConversions.hashToUsername(user) + " has been " + (banned ? "banned" : "unbanned"));
      		}
      		builder.setUID(uID);
      		
      		LSPacket temp = builder.getPacket();
      		if(temp != null) {
      			session.write(temp);
      		}
      		
	}
	
}