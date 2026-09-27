package com.tech.ayugram.messenger;

public interface GenericProvider<F, T> {
    T provide(F obj);
}
