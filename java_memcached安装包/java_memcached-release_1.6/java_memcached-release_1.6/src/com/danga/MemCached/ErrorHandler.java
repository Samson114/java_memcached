package com.danga.MemCached;

/**
 * This is an interface implemented by classes that want to receive callbacks
 * in the event of an error in {@link MemCachedClient}. The implementor can do
 * things like flush caches or perform additioonal logging.
 *
 * @author Dan Zivkovic <zivkovic@apple.com>
 * @version 1.5
 */
public interface ErrorHandler {

    /**
     * Called for errors thrown during initialization.
     */
    public void handleErrorOnInit( final MemCachedClient client ,
                                   final Throwable error );

    /**
     * Called for errors thrown during {@link MemCachedClient#get(String)} and related methods.
     */
    public void handleErrorOnGet( final MemCachedClient client ,
                                  final Throwable error ,
                                  final String cacheKey );

    /**
     * Called for errors thrown during {@link MemCachedClient#getMulti(String)} and related methods.
     */
    public void handleErrorOnGet( final MemCachedClient client ,
                                  final Throwable error ,
                                  final String[] cacheKeys );

    /**
     * Called for errors thrown during {@link MemCachedClient#set(String,Object)} and related methods.
     */
    public void handleErrorOnSet( final MemCachedClient client ,
                                  final Throwable error ,
                                  final String cacheKey );

    /**
     * Called for errors thrown during {@link MemCachedClient#delete(String)} and related methods.
     */
    public void handleErrorOnDelete( final MemCachedClient client ,
                                     final Throwable error ,
                                     final String cacheKey );

    /**
     * Called for errors thrown during {@link MemCachedClient#flushAll()} and related methods.
     */
    public void handleErrorOnFlush( final MemCachedClient client ,
                                    final Throwable error );

    /**
     * Called for errors thrown during {@link MemCachedClient#stats()} and related methods.
     */
    public void handleErrorOnStats( final MemCachedClient client ,
                                    final Throwable error );

} // interface
