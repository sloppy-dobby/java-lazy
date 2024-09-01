package com.sloppydobby.java.lazy;

import org.junit.Assert;
import org.junit.Test;

public final class LazyTests {

    @Test(expected = IllegalArgumentException.class)
    public void requireLazy_nullLazy_throwsException() {
        try {
            Lazy.requireLazy(null);
        } catch (final IllegalArgumentException exception) {
            Assert.assertEquals(Lazy.REQUIRED_LAZY_IS_NULL_EXCEPTION_MESSAGE, exception.getMessage());
            throw exception;
        }
    }

    @Test
    public void requireLazy_nonNullLazy_returnsGivenLazy() {
        final Lazy<Object> sourceLazy = newLazy();
        final Lazy<Object> resultLazy = Lazy.requireLazy(sourceLazy);

        Assert.assertEquals(sourceLazy, resultLazy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void requireLazy_nullLazy_nullExceptionMessage_throwsException() {
        try {
            Lazy.requireLazy(null, null);
        } catch (final IllegalArgumentException exception) {
            Assert.assertNull(exception.getMessage());
            throw exception;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void requireLazy_nullLazy_nonNullExceptionMessage_throwsException() {
        final String exceptionMessage = "Test message";

        try {
            Lazy.requireLazy(null, exceptionMessage);
        } catch (final IllegalArgumentException exception) {
            Assert.assertEquals(exceptionMessage, exception.getMessage());
            throw exception;
        }
    }

    @Test
    public void requireLazy_nonNullLazy_nullExceptionMessage_returnsGivenLazy() {
        final Lazy<Object> sourceLazy = newLazy();
        final Lazy<Object> resultLazy = Lazy.requireLazy(sourceLazy, null);

        Assert.assertEquals(sourceLazy, resultLazy);
    }

    @Test
    public void requireLazy_nonNullLazy_nonNullExceptionMessage_returnsGivenLazy() {
        final Lazy<Object> sourceLazy = newLazy();
        final Lazy<Object> resultLazy = Lazy.requireLazy(sourceLazy, "Test message");

        Assert.assertEquals(sourceLazy, resultLazy);
    }

    private <T> Lazy<T> newLazy() {
        return new Lazy<T>() {

            @Override
            public void setCachingEnabled(boolean isCachingEnabled) {}

            @Override
            public boolean isCachingEnabled() {
                return false;
            }

            @Override
            public boolean isCached() {
                return false;
            }

            @Override
            public void clearCache() {}

            @Override
            public T get() {
                return null;
            }

        };
    }

}
