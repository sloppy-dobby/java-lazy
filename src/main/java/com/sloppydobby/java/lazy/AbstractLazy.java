package com.sloppydobby.java.lazy;

import java.util.function.Supplier;

/**
 * This is an abstract class containing non-thread-safe default implementations for Lazy.
 *
 * @param <T> The calculating value type
 *
 * @author Sergey Grigorov
 *
 * @since Added in 0.0.1
 */
public abstract class AbstractLazy<T> implements Lazy<T> {

    /**
     * This constant contains an error message that will be used in the exception thrown if <code>null</code> is
     * passed as a <code>Supplier</code> to constructors.
     *
     * @since Added in 0.0.1
     */
    public static final String REQUIRED_SUPPLIER_IS_NULL_EXCEPTION_MESSAGE = "Supplier is required but null given";

    /**
     * The value supplier.
     *
     * @since Added in 0.0.1
     */
    protected final Supplier<T> supplier;

    /**
     * The calculated value caching flag.
     *
     * @since Added in 0.0.1
     */
    protected boolean isCachingEnabled = true;

    /**
     * The calculated value is cached flag.
     *
     * @since Added in 0.0.1
     */
    protected boolean isCached = false;

    /**
     * The last cached calculated value.
     */
    protected T value = null;

    /**
     * The constructor that accepts only the value supplier. When using this constructor, caching of the value will be
     * active by default.
     *
     * @param supplier The value supplier
     *
     * @throws IllegalArgumentException This exception can be thrown if the passed <code>supplier</code> is <code>null</code>
     *
     * @since Added in 0.0.1
     */
    protected AbstractLazy(final Supplier<T> supplier) throws IllegalArgumentException {
        if (supplier == null) {
            throw new IllegalArgumentException(REQUIRED_SUPPLIER_IS_NULL_EXCEPTION_MESSAGE);
        }

        this.supplier = supplier;
    }

    /**
     * The constructor that accepts the value supplier and the calculated value caching flag.
     *
     * @param supplier The <code>Supplier</code> implementation in which the value is calculated
     * @param isCachingEnabled The flag of caching of the calculated value
     *
     * @throws IllegalArgumentException This exception can be thrown if the passed <code>supplier</code> is <code>null</code>
     *
     * @since Added in 0.0.1
     */
    protected AbstractLazy(final Supplier<T> supplier, final boolean isCachingEnabled) throws IllegalArgumentException {
        this(supplier);
        setCachingEnabled(isCachingEnabled);
    }

    /**
     * Implementation of the method for changing the calculated value caching flag.
     *
     * @param isCachingEnabled The flag of caching of the calculated value
     *
     * @since Added in 0.0.1
     */
    @Override
    public void setCachingEnabled(boolean isCachingEnabled) {
        this.isCachingEnabled = isCachingEnabled;
    }

    /**
     * Implementation of the caching calculated value flag getter.
     *
     * @return The flag of caching of the calculated value
     *
     * @since Added in 0.0.1
     */
    @Override
    public boolean isCachingEnabled() {
        return isCachingEnabled;
    }

    /**
     * Implementation of getter for the calculated value is cached flag.
     *
     * @return The calculated value is cached flag
     *
     * @since Added in 0.0.1
     */
    @Override
    public boolean isCached() {
        return isCached;
    }

    /**
     * Implementation of clear cache method.
     *
     * @since Added in 0.0.1
     */
    @Override
    public void clearCache() {
        value = null;
        isCached = false;
    }

    /**
     * Implementation of the value getter.
     * If caching is enabled, either the already calculated value will be returned, or the value will be calculated,
     * stored in the cache and returned.
     *
     * @return The calculated value
     *
     * @since Added in 0.0.1
     */
    @Override
    public T get() {
        if (isCachingEnabled) {
            if (!isCached) {
                value = supplier.get();
                isCached = true;
            }

            return value;
        }

        return supplier.get();
    }

}
