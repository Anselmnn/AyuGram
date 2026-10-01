package com.tech.ayugram.tgnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class TLObject {
    public abstract void serializeToStream(OutputStream stream) throws IOException;
    
    public static TLObject deserialize(InputStream stream, int constructor) throws IOException {
        // Deserialize based on constructor
        return null;
    }
    
    public static TLObject deserialize(InputStream stream) throws IOException {
        int constructor = stream.read() | (stream.read() << 8) | (stream.read() << 16) | (stream.read() << 24);
        return deserialize(stream, constructor);
    }
}