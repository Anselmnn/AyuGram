package com.tech.ayugram.tgnet;

import java.util.ArrayList;
import java.util.List;

public class TLRPC {
    // Error
    public static class TL_error extends TLObject {
        public int code = 0;
        public String text = "";
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // User
    public static class User extends TLObject {
        public long id;
        public long access_hash;
        public String first_name = "";
        public String last_name = "";
        public String username = "";
        public String phone = "";
        public String photo = "";
        public int status = 0;
        public boolean bot = false;
        public boolean verified = false;
        public boolean restricted = false;
        public boolean scam = false;
        public boolean fake = false;
        public boolean deleted = false;
        public int bot_inline_geo = 0;
        public int bot_info_version = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Chat
    public static class Chat extends TLObject {
        public long id;
        public long access_hash;
        public String title = "";
        public String username = "";
        public String photo = "";
        public int date = 0;
        public int version = 0;
        public boolean creator = false;
        public boolean deactivated = false;
        public boolean megagroup = false;
        public boolean broadcast = false;
        public boolean is_forum = false;
        public int participants_count = 0;
        public int admins_count = 0;
        public int kicked_count = 0;
        public int banned_count = 0;
        public int online_count = 0;
        public int read_inbox_max_id = 0;
        public int read_outbox_max_id = 0;
        public int unread_count = 0;
        public String about = "";
        public long linked_chat_id = 0;
        public int folder_id = 0;
        public int ttl_period = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Dialog
    public static class Dialog extends TLObject {
        public long id;
        public long top_message;
        public int read_inbox_max_id;
        public int read_outbox_max_id;
        public int unread_count;
        public int unread_mentions_count;
        public int unread_reactions_count;
        public long peer_id;
        public int folder_id = 0;
        public boolean archived = false;
        public boolean pinned = false;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Message
    public static class Message extends TLObject {
        public int id;
        public long random_id;
        public long dialog_id;
        public long from_id;
        public long peer_id;
        public int date;
        public String message = "";
        public String media = "";
        public String reply_to = "";
        public int reply_to_msg_id = 0;
        public int ttl = 0;
        public int views = 0;
        public int forwards = 0;
        public int edit_date = 0;
        public boolean out = false;
        public boolean mentioned = false;
        public boolean media_unread = false;
        public boolean silent = false;
        public boolean post = false;
        public boolean from_scheduled = false;
        public boolean legacy = false;
        public boolean edit_hide = false;
        public boolean pinned = false;
        public boolean noforwards = false;
        public boolean invert_media = false;
        public boolean offline = false;
        public long forward_from_chat_id = 0;
        public MessageReplies replies;
        public List<MessageEntity> entities = new ArrayList<>();
        public List<MessageEntity> caption_entities = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class MessageReplies extends TLObject {
        public int comments = 0;
        public int replies = 0;
        public int replies_pts = 0;
        public int recent_repliers = 0;
        public boolean isComments = false;
        public int max_id = 0;
        public int read_max_id = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class MessageEntity extends TLObject {
        public int type;
        public int offset;
        public int length;
        public String url = "";
        public long user_id = 0;
        public String language = "";
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Messages containers
    public static class messages_Dialogs extends TLObject {
        public List<Dialog> dialogs = new ArrayList<>();
        public List<User> users = new ArrayList<>();
        public List<Chat> chats = new ArrayList<>();
        public List<Message> messages = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class messages_Messages extends TLObject {
        public List<Message> messages = new ArrayList<>();
        public List<User> users = new ArrayList<>();
        public List<Chat> chats = new ArrayList<>();
        public int count = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class messages_SentMessage extends TLObject {
        public int id;
        public int date;
        public List<MessageEntity> entities = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Requests
    public static class TL_messages_getDialogs extends TLObject {
        public int offset_date = 0;
        public int offset_id = 0;
        public long offset_peer = 0;
        public int limit = 100;
        public int hash = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_messages_getHistory extends TLObject {
        public InputPeer peer;
        public int offset_id = 0;
        public int offset_date = 0;
        public int add_offset = 0;
        public int limit = 50;
        public int max_id = 0;
        public int min_id = 0;
        public int hash = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_messages_sendMessage extends TLObject {
        public boolean no_webpage = false;
        public boolean silent = false;
        public boolean background = false;
        public boolean clear_draft = false;
        public boolean noforwards = false;
        public InputPeer peer;
        public String message = "";
        public long random_id = 0;
        public String reply_to = "";
        public int reply_to_msg_id = 0;
        public int schedule_date = 0;
        public InputMedia media;
        public InputReplyMarkup reply_markup;
        public List<InputMessageEntity> entities = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_messages_getReplies extends TLObject {
        public InputPeer peer;
        public int msg_id = 0;
        public int offset_id = 0;
        public int offset_date = 0;
        public int add_offset = 0;
        public int limit = 20;
        public int max_id = 0;
        public int min_id = 0;
        public int hash = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_messages_forwardMessages extends TLObject {
        public InputPeer from_peer;
        public List<Integer> id = new ArrayList<>();
        public InputPeer to_peer;
        public boolean silent = false;
        public boolean background = false;
        public boolean with_my_score = false;
        public boolean drop_author = false;
        public boolean drop_media_captions = false;
        public List<Long> random_id = new ArrayList<>();
        public int schedule_date = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Search
    public static class TL_messages_search extends TLObject {
        public InputPeer peer;
        public String q = "";
        public InputMessageFilter filter;
        public int min_date = 0;
        public int max_date = 0;
        public int offset_id = 0;
        public int add_offset = 0;
        public int limit = 20;
        public int max_id = 0;
        public int min_id = 0;
        public long from_id = 0;
        public int hash = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_messages_searchGlobal extends TLObject {
        public String q = "";
        public InputMessageFilter filter;
        public int min_date = 0;
        public int max_date = 0;
        public int offset_rate = 0;
        public int offset_peer = 0;
        public int offset_id = 0;
        public int limit = 20;
        public long folder_id = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class InputMessageFilter extends TLObject {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_inputMessagesFilterEmpty extends InputMessageFilter {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Input types
    public static class InputPeer extends TLObject {
        public int type;
        public long user_id;
        public long chat_id;
        public long channel_id;
        public long access_hash;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_inputPeerUser extends InputPeer {
        public TL_inputPeerUser() { type = 0; }
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_inputPeerChat extends InputPeer {
        public TL_inputPeerChat() { type = 1; }
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_inputPeerChannel extends InputPeer {
        public TL_inputPeerChannel() { type = 2; }
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class InputMedia extends TLObject {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class InputReplyMarkup extends TLObject {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class InputMessageEntity extends TLObject {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Channels
    public static class Channel extends TLObject {
        public long id;
        public long access_hash;
        public String title = "";
        public String username = "";
        public String photo = "";
        public int date = 0;
        public boolean creator = false;
        public boolean megagroup = false;
        public boolean broadcast = false;
        public boolean is_forum = false;
        public int participants_count = 0;
        public int admins_count = 0;
        public int kicked_count = 0;
        public int banned_count = 0;
        public int online_count = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Updates
    public static class Updates extends TLObject {
        public List<Update> updates = new ArrayList<>();
        public List<User> users = new ArrayList<>();
        public List<Chat> chats = new ArrayList<>();
        public int date = 0;
        public int seq = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class Update extends TLObject {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_updateNewMessage extends Update {
        public Message message;
        public int pts = 0;
        public int pts_count = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_updateNewChannelMessage extends Update {
        public Message message;
        public int pts = 0;
        public int pts_count = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Auth classes
    public static class TL_codeSettings extends TLObject {
        public boolean allow_flashcall = false;
        public boolean current_number = false;
        public boolean allow_app_hash = false;
        public String app_hash = "";
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_auth_sendCode extends TLObject {
        public String phone_number = "";
        public int api_id = 0;
        public String api_hash = "";
        public TL_codeSettings settings;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_auth_sentCode extends TLObject {
        public String type = "";
        public String phone_code_hash = "";
        public String next_type = "";
        public int timeout = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_auth_signIn extends TLObject {
        public String phone_number = "";
        public String phone_code_hash = "";
        public String phone_code = "";
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Contacts
    public static class InputContact extends TLObject {
        public long client_id = 0;
        public String phone = "";
        public String first_name = "";
        public String last_name = "";
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_contacts_importContacts extends TLObject {
        public List<InputContact> contacts = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_contacts_importedContacts extends TLObject {
        public List<User> users = new ArrayList<>();
        public List<ImportedContact> imported = new ArrayList<>();
        public List<Long> popular_invites = new ArrayList<>();
        public List<Long> retry_contacts = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class ImportedContact extends TLObject {
        public long client_id = 0;
        public long user_id = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_contacts_deleteContacts extends TLObject {
        public List<Long> id = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_contacts_contactsNotModified extends TLObject {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_contacts_getContacts extends TLObject {
        public int hash = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Secret Chat
    public static class EncryptedChat extends TLObject {
        public int id = 0;
        public long access_hash = 0;
        public int date = 0;
        public int admin_id = 0;
        public int participant_id = 0;
        public String g_a = "";
        public String key_fingerprint = "";
        public String state = "";
        public int key_usage = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_messages_getDhConfig extends TLObject {
        public int version = 0;
        public int random_length = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_messages_requestEncryption extends TLObject {
        public int user_id = 0;
        public String g_a = "";
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_messages_acceptEncryption extends TLObject {
        public int chat_id = 0;
        public String g_b = "";
        public String key_fingerprint = "";
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_messages_discardEncryption extends TLObject {
        public int chat_id = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_messages_getEncryptedChat extends TLObject {
        public int chat_id = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Document and Photo for media
    public static class Document extends TLObject {
        public long id = 0;
        public long access_hash = 0;
        public String file_name = "";
        public String mime_type = "";
        public int size = 0;
        public int date = 0;
        public String dc_id = "";
        public String thumb = "";
        public String attributes = "";
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class Photo extends TLObject {
        public long id = 0;
        public long access_hash = 0;
        public int date = 0;
        public String sizes = "";
        public String dc_id = "";
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Update types for secret chats
    public static class TL_updateEncryptedChatTyping extends Update {
        public int chat_id = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_updateEncryption extends Update {
        public EncryptedChat chat;
        public int date = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_updateEncryptedMessagesRead extends Update {
        public int chat_id = 0;
        public int max_date = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Channels
    public static class TL_channels_getChannels extends TLObject {
        public List<InputChannel> id = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class InputChannel extends TLObject {
        public long channel_id = 0;
        public long access_hash = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_channels_createChannel extends TLObject {
        public String title = "";
        public String about = "";
        public boolean megagroup = false;
        public boolean broadcast = true;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Folders
    public static class TL_folders_editPeerFolders extends TLObject {
        public int folder_id = 0;
        public InputPeer peer;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_messages_getPinnedDialogs extends TLObject {
        public int folder_id = 0;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_messages_pinChatMessage extends TLObject {
        public InputPeer peer;
        public int id = 0;
        public boolean silent = false;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // §6.1 Stories (blocked - return empty)
    public static class TL_stories_getAllStories extends TLObject {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_stories_allStories extends TLObject {
        public List<Story> stories = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_stories_getPeerStories extends TLObject {
        public InputPeer peer;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_stories_peerStories extends TLObject {
        public List<Story> stories = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_stories_getStoriesArchive extends TLObject {
        public InputPeer peer;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_stories_storiesArchive extends TLObject {
        public List<Story> stories = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class Story extends TLObject {
        public long id = 0;
        public long peer_id = 0;
        public int date = 0;
        public String media = "";
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    // Account/Privacy
    public static class TL_account_setPrivacy extends TLObject {
        public InputPrivacyKey key;
        public List<PrivacyRule> rules = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_account_getPrivacy extends TLObject {
        public InputPrivacyKey key;
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class InputPrivacyKey extends TLObject {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_inputPrivacyKeyStatusTimestamp extends InputPrivacyKey {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_inputPrivacyKeyProfilePhoto extends InputPrivacyKey {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_inputPrivacyKeyPhoneCall extends InputPrivacyKey {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_inputPrivacyKeyForwards extends InputPrivacyKey {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_inputPrivacyKeyChatInvite extends InputPrivacyKey {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class PrivacyRule extends TLObject {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_privacyValueDisallowAll extends PrivacyRule {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_privacyValueAllowAll extends PrivacyRule {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_privacyValueAllowContacts extends PrivacyRule {
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_privacyValueAllowUsers extends PrivacyRule {
        public List<Long> users = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
    
    public static class TL_privacyValueDisallowUsers extends PrivacyRule {
        public List<Long> users = new ArrayList<>();
        
        @Override
        public void serializeToStream(java.io.OutputStream stream) throws java.io.IOException {}
    }
}