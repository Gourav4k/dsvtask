package com.dsv.exception;

/**
 * Custom exception thrown when an item is not found.
 * This exception is used to signal that a requested item does not exist in the system.
 */
public class ItemNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new ItemNotFoundException with a default message.
     * 
     * @param id the ID of the item that was not found
     */
    public ItemNotFoundException(Long id) {
        super("Item not found with id: " + id);
    }
    
    /**
     * Constructs a new ItemNotFoundException with a custom message.
     * 
     * @param message the custom error message
     */
    public ItemNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new ItemNotFoundException with a message and cause.
     * 
     * @param message the error message
     * @param cause the cause of the exception
     */
    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}