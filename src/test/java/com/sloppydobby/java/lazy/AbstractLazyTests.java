package com.sloppydobby.java.lazy;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.function.Supplier;

public final class AbstractLazyTests {

    @Test(expected = IllegalArgumentException.class)
    public void constructor_nullSupplier_throwsException() {
        try {
            newLazy(null);
        } catch (final IllegalArgumentException exception) {
            Assert.assertEquals(AbstractLazy.REQUIRED_SUPPLIER_IS_NULL_EXCEPTION_MESSAGE, exception.getMessage());
            throw exception;
        }
    }

    @Test
    public void constructor_nonNullSupplier_supplierMatchesGiven_isCachingEnabledMatchesTrue() {
        final Random random = new Random();
        final Supplier<Integer> supplier = random::nextInt;;

        final AbstractLazy<Integer> lazy = newLazy(supplier);

        Assert.assertEquals(supplier, lazy.supplier);
        Assert.assertTrue(lazy.isCachingEnabled);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_nullSupplier_falseIsCachingEnabled_throwsException() {
        try {
            newLazy(null, false);
        } catch (final IllegalArgumentException exception) {
            Assert.assertEquals(AbstractLazy.REQUIRED_SUPPLIER_IS_NULL_EXCEPTION_MESSAGE, exception.getMessage());
            throw exception;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_nullSupplier_trueIsCachingEnabled_throwsException() {
        try {
            newLazy(null, true);
        } catch (IllegalArgumentException exception) {
            Assert.assertEquals(AbstractLazy.REQUIRED_SUPPLIER_IS_NULL_EXCEPTION_MESSAGE, exception.getMessage());
            throw exception;
        }
    }

    @Test
    public void constructor_nonNullSupplier_falseIsCachingEnabled_supplierMatchesGiven_isCachingEnabledMatchesFalse() {
        final Random random = new Random();
        final Supplier<Integer> supplier = random::nextInt;

        final AbstractLazy<Integer> lazy = newLazy(supplier, false);

        Assert.assertEquals(supplier, lazy.supplier);
        Assert.assertFalse(lazy.isCachingEnabled);
    }

    @Test
    public void constructor_nonNullSupplier_trueIsCachingEnabled_supplierMatchesGiven_isCachingEnabledMatchesTrue() {
        final Random random = new Random();
        final Supplier<Integer> supplier = random::nextInt;

        final AbstractLazy<Integer> lazy = newLazy(supplier, true);

        Assert.assertEquals(supplier, lazy.supplier);
        Assert.assertTrue(lazy.isCachingEnabled);
    }

    @Test
    public void setCachingEnabled_defaultFalseIsCachingEnabled_trueIsCachingEnabled_isCachingEnabledMatchesTrue() {
        final AbstractLazy<Object> lazy = newLazy(() -> null, false);
        lazy.setCachingEnabled(true);

        Assert.assertTrue(lazy.isCachingEnabled);
    }

    @Test
    public void setCachingEnabled_defaultTrueIsCachingEnabled_falseIsCachingEnabled_isCachingEnabledMatchesFalse() {
        final AbstractLazy<Object> lazy = newLazy(() -> null, true);
        lazy.setCachingEnabled(false);

        Assert.assertFalse(lazy.isCachingEnabled);
    }

    @Test
    public void isCachingEnabled_defaultFalseIsCachingEnabled_returnsFalse() {
        final AbstractLazy<Object> lazy = newLazy(() -> null, false);
        Assert.assertFalse(lazy.isCachingEnabled());
    }

    @Test
    public void isCachingEnabled_defaultTrueIsCachingEnabled_returnsTrue() {
        final AbstractLazy<Object> lazy = newLazy(() -> null, true);
        Assert.assertTrue(lazy.isCachingEnabled());
    }

    @Test
    public void clearCache_get() {
        final Random random = new Random();
        final Supplier<Integer> supplier = random::nextInt;

        final AbstractLazy<Integer> lazy = newLazy(supplier, true);

        final Integer value1 = lazy.get();
        final Integer value2 = lazy.get();

        Assert.assertEquals(value1, value2);
        Assert.assertTrue(lazy.isCached);
        Assert.assertNotNull(lazy.value);

        lazy.clearCache();
        Assert.assertFalse(lazy.isCached);
        Assert.assertNull(lazy.value);

        final Integer value3 = lazy.get();
        Assert.assertNotEquals(value2, value3);
    }

    private <T> AbstractLazy<T> newLazy(final Supplier<T> supplier) {
        return new AbstractLazy<T>(supplier) {};
    }

    private <T> AbstractLazy<T> newLazy(final Supplier<T> supplier, final boolean isCachingEnabled) {
        return new AbstractLazy<T>(supplier, isCachingEnabled) {};
    }

}
