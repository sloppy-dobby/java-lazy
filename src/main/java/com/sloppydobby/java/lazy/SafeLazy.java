package com.sloppydobby.java.lazy;

import java.util.function.Supplier;

public class SafeLazy<T> extends AbstractLazy<T> {

    public SafeLazy(final Supplier<T> supplier) {
        super(supplier);
    }

    public SafeLazy(final Supplier<T> supplier, final boolean isCachingEnabled) {
        super(supplier, isCachingEnabled);
    }

    @Override
    public synchronized void setCachingEnabled(boolean isCachingEnabled) {
        super.setCachingEnabled(isCachingEnabled);
    }

    @Override
    public synchronized boolean isCachingEnabled() {
        return super.isCachingEnabled();
    }

    @Override
    public synchronized boolean isCached() {
        return super.isCached();
    }

    @Override
    public synchronized void clearCache() {
        super.clearCache();
    }

    @Override
    public synchronized T get() {
        return super.get();
    }

}
