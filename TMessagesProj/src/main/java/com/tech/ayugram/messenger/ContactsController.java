package com.tech.ayugram.messenger;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.tgnet.ConnectionsManager;
import com.tech.ayugram.tgnet.RequestDelegate;
import com.tech.ayugram.tgnet.TLRPC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Keep
public class ContactsController {
    private static ContactsController[] instances = new ContactsController[3];
    
    private final Map<String, TLRPC.User> contactsDict = new ConcurrentHashMap<>();
    private boolean loadingContacts = false;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private ContactsController(int accountNum) {
    }

    public static ContactsController getInstance(int num) {
        if (instances[num] == null) {
            instances[num] = new ContactsController(num);
        }
        return instances[num];
    }

    public static ContactsController getInstance() {
        return getInstance(0);
    }

    public void loadContacts(RequestDelegate delegate) {
        if (loadingContacts) return;
        loadingContacts = true;
        
        // Load from device contacts
        List<TLRPC.InputContact> deviceContacts = getDeviceContacts();
        
        if (deviceContacts.isEmpty()) {
            loadingContacts = false;
            if (delegate != null) {
                TLRPC.TL_contacts_contactsNotModified result = new TLRPC.TL_contacts_contactsNotModified();
                delegate.run(result, null);
            }
            return;
        }
        
        // Import contacts via MTProto
        TLRPC.TL_contacts_importContacts req = new TLRPC.TL_contacts_importContacts();
        req.contacts = deviceContacts;
        
        ConnectionsManager.getInstance().sendRequest(req, (response, error) -> {
            loadingContacts = false;
            if (error == null && response instanceof TLRPC.TL_contacts_importedContacts) {
                TLRPC.TL_contacts_importedContacts imported = (TLRPC.TL_contacts_importedContacts) response;
                processImportedContacts(imported);
            }
            if (delegate != null) {
                delegate.run(response, error);
            }
        });
    }

    private List<TLRPC.InputContact> getDeviceContacts() {
        List<TLRPC.InputContact> contacts = new ArrayList<>();
        Context context = ApplicationLoader.getApplicationContext();
        ContentResolver resolver = context.getContentResolver();
        
        String[] projection = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
        };
        
        try (Cursor cursor = resolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            null, null,
            ContactsContract.Contacts.DISPLAY_NAME + " ASC")) {
            
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String contactId = cursor.getString(0);
                    String name = cursor.getString(1);
                    int hasPhone = cursor.getInt(2);
                    
                    if (hasPhone > 0) {
                        String[] phoneProjection = { ContactsContract.CommonDataKinds.Phone.NUMBER };
                        try (Cursor phoneCursor = resolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            phoneProjection,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{contactId}, null)) {
                            
                            if (phoneCursor != null) {
                                while (phoneCursor.moveToNext()) {
                                    String phone = phoneCursor.getString(0);
                                    TLRPC.InputContact inputContact = new TLRPC.InputContact();
                                    inputContact.client_id = System.currentTimeMillis();
                                    inputContact.phone = normalizePhone(phone);
                                    inputContact.first_name = name;
                                    inputContact.last_name = "";
                                    contacts.add(inputContact);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return contacts;
    }

    private String normalizePhone(String phone) {
        if (phone == null) return "";
        // Remove all non-digits except leading +
        String cleaned = phone.replaceAll("[^\\d+]", "");
        if (!cleaned.startsWith("+")) {
            cleaned = "+" + cleaned;
        }
        return cleaned;
    }

    private void processImportedContacts(TLRPC.TL_contacts_importedContacts imported) {
        for (TLRPC.User user : imported.users) {
            contactsDict.put(user.phone, user);
        }
    }

    public TLRPC.User getContactByPhone(String phone) {
        return contactsDict.get(normalizePhone(phone));
    }

    public void deleteContact(long userId, RequestDelegate delegate) {
        TLRPC.TL_contacts_deleteContacts req = new TLRPC.TL_contacts_deleteContacts();
        req.id = new ArrayList<>();
        req.id.add(userId);
        
        ConnectionsManager.getInstance().sendRequest(req, delegate);
    }

    public void cleanup() {
        contactsDict.clear();
    }
}