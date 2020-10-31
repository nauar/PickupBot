package info.nauar.irc.pickupBot;

import info.nauar.irc.pickupBot.channel.ChannelService;
import info.nauar.irc.pickupBot.event.EventService;
import info.nauar.irc.pickupBot.message.Message;
import info.nauar.irc.pickupBot.message.MessageService;
import info.nauar.irc.pickupBot.message.command.CommandException;
import info.nauar.irc.pickupBot.nick.NickService;
import lombok.NoArgsConstructor;
import org.jibble.pircbot.DccChat;
import org.jibble.pircbot.DccFileTransfer;
import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;

@NoArgsConstructor
public class PickupBot extends PircBot {

    private String bridgeBotName;

    public PickupBot(String name) {
        setName(name);
    }

    public String getBridgeBotName() {
        return bridgeBotName;
    }

    public void setBridgeBotName(String bridgeBotName) {
        this.bridgeBotName = bridgeBotName;
    }

    @Autowired
    private NickService nickService;

    @Autowired
    private EventService eventService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChannelService channelService;

    @Override
    public void setAutoNickChange(boolean autoNickChange) {
        super.setAutoNickChange(autoNickChange);
    }

    @Override
    public void log(String line) {
        super.log(line);
    }

    @Override
    protected void handleLine(String line) {
        super.handleLine(line);
    }

    @Override
    protected void onConnect() {
        super.onConnect();
    }

    @Override
    protected void onDisconnect() {
        super.onDisconnect();
    }

    @Override
    protected void onServerResponse(int code, String response) {
        super.onServerResponse(code, response);
    }

    @Override
    protected void onUserList(String channel, User[] users) {
        super.onUserList(channel, users);
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
        super.onMessage(channel, sender, login, hostname, message);
        try {
            Message message1 = new Message();
            message1.setChannel(channel);
            message1.setSender(sender);
            message1.setLogin(login);
            message1.setHostname(hostname);
            message1.setMessage(message);
            messageService.processMessage(message1);
        } catch (CommandException e) {
            sendNotice(sender, e.getMessage());
        }
    }

    @Override
    protected void onPrivateMessage(String sender, String login, String hostname, String message) {
        super.onPrivateMessage(sender, login, hostname, message);
        try {
            Message message1 = new Message();
            message1.setSender(sender);
            message1.setLogin(login);
            message1.setHostname(hostname);
            message1.setMessage(message);
            messageService.processMessage(message1);
        } catch (CommandException e) {
            sendNotice(sender, e.getMessage());
        }
    }

    @Override
    protected void onAction(String sender, String login, String hostname, String target, String action) {
        super.onAction(sender, login, hostname, target, action);
    }

    @Override
    protected void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
        super.onNotice(sourceNick, sourceLogin, sourceHostname, target, notice);
    }

    @Override
    protected void onJoin(String channel, String sender, String login, String hostname) {
        super.onJoin(channel, sender, login, hostname);
        eventService.processJoin(channel, sender, login, hostname);
    }

    @Override
    protected void onPart(String channel, String sender, String login, String hostname) {
        super.onPart(channel, sender, login, hostname);
        eventService.processPart(channel, sender, login, hostname);
    }

    @Override
    protected void onNickChange(String oldNick, String login, String hostname, String newNick) {
        super.onNickChange(oldNick, login, hostname, newNick);
        nickService.processNickChange(oldNick, newNick);
    }

    @Override
    protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
        eventService.processKick(channel, kickerNick, kickerLogin, kickerHostname, recipientNick, reason);
    }

    @Override
    protected void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {
        eventService.processQuit(sourceNick, sourceLogin, sourceHostname, reason);
    }

    @Override
    protected void onTopic(String channel, String topic, String setBy, long date, boolean changed) {
        super.onTopic(channel, topic, setBy, date, changed);
        channelService.createChannel(channel, topic);
    }

    @Override
    protected void onChannelInfo(String channel, int userCount, String topic) {
        super.onChannelInfo(channel, userCount, topic);
    }

    @Override
    protected void onMode(String channel, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        super.onMode(channel, sourceNick, sourceLogin, sourceHostname, mode);
    }

    @Override
    protected void onUserMode(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        super.onUserMode(targetNick, sourceNick, sourceLogin, sourceHostname, mode);
    }

    @Override
    protected void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        super.onOp(channel, sourceNick, sourceLogin, sourceHostname, recipient);
    }

    @Override
    protected void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        super.onDeop(channel, sourceNick, sourceLogin, sourceHostname, recipient);
    }

    @Override
    protected void onVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        super.onVoice(channel, sourceNick, sourceLogin, sourceHostname, recipient);
    }

    @Override
    protected void onDeVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        super.onDeVoice(channel, sourceNick, sourceLogin, sourceHostname, recipient);
    }

    @Override
    protected void onSetChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {
        super.onSetChannelKey(channel, sourceNick, sourceLogin, sourceHostname, key);
    }

    @Override
    protected void onRemoveChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {
        super.onRemoveChannelKey(channel, sourceNick, sourceLogin, sourceHostname, key);
    }

    @Override
    protected void onSetChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname, int limit) {
        super.onSetChannelLimit(channel, sourceNick, sourceLogin, sourceHostname, limit);
    }

    @Override
    protected void onRemoveChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemoveChannelLimit(channel, sourceNick, sourceLogin, sourceHostname);
    }

    @Override
    protected void onSetChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {
        super.onSetChannelBan(channel, sourceNick, sourceLogin, sourceHostname, hostmask);
    }

    @Override
    protected void onRemoveChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {
        super.onRemoveChannelBan(channel, sourceNick, sourceLogin, sourceHostname, hostmask);
    }

    @Override
    protected void onSetTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onSetTopicProtection(channel, sourceNick, sourceLogin, sourceHostname);
    }

    @Override
    protected void onRemoveTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemoveTopicProtection(channel, sourceNick, sourceLogin, sourceHostname);
    }

    @Override
    protected void onSetNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onSetNoExternalMessages(channel, sourceNick, sourceLogin, sourceHostname);
    }

    @Override
    protected void onRemoveNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemoveNoExternalMessages(channel, sourceNick, sourceLogin, sourceHostname);
    }

    @Override
    protected void onSetInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onSetInviteOnly(channel, sourceNick, sourceLogin, sourceHostname);
    }

    @Override
    protected void onRemoveInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemoveInviteOnly(channel, sourceNick, sourceLogin, sourceHostname);
    }

    @Override
    protected void onSetModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onSetModerated(channel, sourceNick, sourceLogin, sourceHostname);
    }

    @Override
    protected void onRemoveModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemoveModerated(channel, sourceNick, sourceLogin, sourceHostname);
    }

    @Override
    protected void onSetPrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onSetPrivate(channel, sourceNick, sourceLogin, sourceHostname);
    }

    @Override
    protected void onRemovePrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemovePrivate(channel, sourceNick, sourceLogin, sourceHostname);
    }

    @Override
    protected void onSetSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onSetSecret(channel, sourceNick, sourceLogin, sourceHostname);
    }

    @Override
    protected void onRemoveSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemoveSecret(channel, sourceNick, sourceLogin, sourceHostname);
    }

    @Override
    protected void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
        super.onInvite(targetNick, sourceNick, sourceLogin, sourceHostname, channel);
    }

    @Override
    protected void onIncomingFileTransfer(DccFileTransfer transfer) {
        super.onIncomingFileTransfer(transfer);
    }

    @Override
    protected void onFileTransferFinished(DccFileTransfer transfer, Exception e) {
        super.onFileTransferFinished(transfer, e);
    }

    @Override
    protected void onIncomingChatRequest(DccChat chat) {
        super.onIncomingChatRequest(chat);
    }

    @Override
    protected void onVersion(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        super.onVersion(sourceNick, sourceLogin, sourceHostname, target);
    }

    @Override
    protected void onPing(String sourceNick, String sourceLogin, String sourceHostname, String target, String pingValue) {
        super.onPing(sourceNick, sourceLogin, sourceHostname, target, pingValue);
    }

    @Override
    protected void onServerPing(String response) {
        super.onServerPing(response);
    }

    @Override
    protected void onTime(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        super.onTime(sourceNick, sourceLogin, sourceHostname, target);
    }

    @Override
    protected void onFinger(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        super.onFinger(sourceNick, sourceLogin, sourceHostname, target);
    }

    @Override
    protected void onUnknown(String line) {
        super.onUnknown(line);
    }

    @Override
    public String getNick() {
        return super.getNick();
    }

    @Override
    public int[] longToIp(long address) {
        return super.longToIp(address);
    }

    @Override
    public long ipToLong(byte[] address) {
        return super.ipToLong(address);
    }

    @Override
    public void setEncoding(String charset) throws UnsupportedEncodingException {
        super.setEncoding(charset);
    }

    @Override
    public String getEncoding() {
        return super.getEncoding();
    }

    @Override
    public InetAddress getInetAddress() {
        return super.getInetAddress();
    }

    @Override
    public void setDccInetAddress(InetAddress dccInetAddress) {
        super.setDccInetAddress(dccInetAddress);
    }

    @Override
    public InetAddress getDccInetAddress() {
        return super.getDccInetAddress();
    }

    @Override
    public int[] getDccPorts() {
        return super.getDccPorts();
    }

    @Override
    public void setDccPorts(int[] ports) {
        super.setDccPorts(ports);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public synchronized void dispose() {
        super.dispose();
    }


}
