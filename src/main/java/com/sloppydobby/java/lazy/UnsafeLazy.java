package com.sloppydobby.java.lazy;

import java.util.function.Supplier;

public class UnsafeLazy<T> extends AbstractLazy<T> {

    public UnsafeLazy(final Supplier<T> supplier) throws IllegalArgumentException {
        super(supplier);
    }

    public UnsafeLazy(final Supplier<T> supplier, final boolean isCachingEnabled) throws IllegalArgumentException {
        super(supplier, isCachingEnabled);
    }

}
