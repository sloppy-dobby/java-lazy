package com.sloppydobby.java.lazy;

import java.util.function.Supplier;

/**
 * The interface of a lazy value calculator.
 *
 * @param <T> The calculating value type
 *
 * @author Sergey Grigorov
 *
 * @since Added in 0.0.1
 */
public interface Lazy<T> extends Supplier<T> {

    /**
     * This constant contains an error message, which will be the default in the null check method exception for Lazy.
     *
     * @since Added in 0.0.1
     */
    String REQUIRED_LAZY_IS_NULL_EXCEPTION_MESSAGE = "Lazy is required but null given";

    /**
     * This method is used to null-check for the of the <code>Lazy</code> object.
     *
     * @param <T> The calculating value
     * @param <LAZY> The lazy implementation type
     * @param lazy The lazy implementation instance
     *
     * @return Passed <code>lazy</code> or throws an <code>IllegalArgumentException</code> if <code>lazy</code> is null
     *
     * @throws IllegalArgumentException This exception can be thrown if the passed <code>lazy</code> is <code>null</code>
     *
     * @since Added in 0.0.1
     */
    static <T, LAZY extends Lazy<T>> LAZY requireLazy(final LAZY lazy) throws IllegalArgumentException {
        return requireLazy(lazy, REQUIRED_LAZY_IS_NULL_EXCEPTION_MESSAGE);
    }

    /**
     * This method is used to null-check for the of the <code>Lazy</code> object.
     *
     * @param <T> The calculating value
     * @param <LAZY> The lazy implementation type
     * @param lazy The lazy implementation instance
     * @param exceptionMessage The exception message for an exception that will be thrown when passed <code>lazy</code> is null
     *
     * @return Passed <code>lazy</code> or throws an <code>IllegalArgumentException</code> if <code>lazy</code> is null
     *
     * @throws IllegalArgumentException This exception can be thrown if the passed <code>lazy</code> is <code>null</code>
     *
     * @since Added in 0.0.1
     */
    static <T, LAZY extends Lazy<T>> LAZY requireLazy(
            final LAZY lazy,
            final String exceptionMessage
    ) throws IllegalArgumentException {
        if (lazy == null) {
            throw new IllegalArgumentException(exceptionMessage);
        }

        return lazy;
    }

    /**
     * This is a factory method for creating a non-thread-safe <code>Lazy</code> instance.
     *
     * @param <T> The calculating value type
     * @param supplier The <code>Supplier</code> implementation in which the value is calculated
     *
     * @return Non-thread-safe <code>Lazy</code> instance or throws an <code>IllegalArgumentException</code> if <code>supplier</code> is null
     *
     * @throws IllegalArgumentException This exception can be thrown if the passed <code>supplier</code> is <code>null</code>
     *
     * @see UnsafeLazy
     *
     * @since Added in 0.0.1
     */
    static <T> Lazy<T> unsafe(final Supplier<T> supplier) throws IllegalArgumentException {
        return new UnsafeLazy<>(supplier);
    }

    /**
     * This is a factory method for creating a non-thread-safe <code>Lazy</code> instance.
     *
     * @param <T> The calculating value type
     * @param supplier The <code>Supplier</code> implementation in which the value is calculated
     * @param isCachingEnabled The flag of caching of the calculated value
     *
     * @return Non-thread-safe <code>Lazy</code> instance or throws an <code>IllegalArgumentException</code> if <code>supplier</code> is null
     *
     * @throws IllegalArgumentException This exception can be thrown if the passed <code>supplier</code> is <code>null</code>
     *
     * @see UnsafeLazy
     *
     * @since Added in 0.0.1
     */
    static <T> Lazy<T> unsafe(
            final Supplier<T> supplier,
            final boolean isCachingEnabled
    ) throws IllegalArgumentException {
        return new UnsafeLazy<>(supplier, isCachingEnabled);
    }

    /**
     * This is a factory method for creating a thread-safe <code>Lazy</code> instance.
     *
     * @param <T> The calculating value type
     * @param supplier The <code>Supplier</code> implementation in which the value is calculated
     *
     * @return Thread-safe <code>Lazy</code> instance or throws an <code>IllegalArgumentException</code> if <code>supplier</code> is null
     *
     * @throws IllegalArgumentException This exception can be thrown if the passed <code>supplier</code> is <code>null</code>
     *
     * @see SafeLazy
     *
     * @since Added in 0.0.1
     */
    static <T> Lazy<T> safe(final Supplier<T> supplier) throws IllegalArgumentException {
        return new SafeLazy<>(supplier);
    }

    /**
     * This is a factory method for creating a thread-safe <code>Lazy</code> instance.
     *
     * @param <T> The calculating value type
     * @param supplier The <code>Supplier</code> implementation in which the value is calculated
     * @param isCachingEnabled The flag of caching of the calculated value
     *
     * @return Thread-safe <code>Lazy</code> instance or throws an <code>IllegalArgumentException</code> if <code>supplier</code> is null
     *
     * @throws IllegalArgumentException This exception can be thrown if the passed <code>supplier</code> is <code>null</code>
     *
     * @see SafeLazy
     *
     * @since Added in 0.0.1
     */
    static <T> Lazy<T> safe(
            final Supplier<T> supplier,
            final boolean isCachingEnabled
    ) throws IllegalArgumentException {
        return new SafeLazy<>(supplier, isCachingEnabled);
    }

    /**
     * Method for setting the calculated value caching flag
     *
     * @param isCachingEnabled The flag of caching of the calculated value
     *
     * @since Added in 0.0.1
     */
    void setCachingEnabled(final boolean isCachingEnabled);

    /**
     * Method for getting the calculated value caching flag
     *
     * @return The calculated value caching flag
     *
     * @since Added in 0.0.1
     */
    boolean isCachingEnabled();

    /**
     * Method for getting the calculated value is cached flag
     *
     * @return The calculated value is cached flag
     *
     * @since Added in 0.0.1
     */
    boolean isCached();

    /**
     * Method for clear cached calculated value.
     *
     * @since Added in 0.0.1
     */
    void clearCache();

}
