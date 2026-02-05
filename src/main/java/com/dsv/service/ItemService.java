package com.dsv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dsv.entity.Item;

@Service
public interface ItemService {
	
	/**
	 * Create New Item
	 * @param item to create the item
	 * @retrun the created item with generated ID
	 */
	Item createItem(Item item);
	
	
	/**
	 * Get an item by ID
	 * @param id
	 * @return item with particular ID if found
	 * 
	 */
	
	Optional<Item> getItemById(Long id);
	
	/**
	 * get all items
	 * @return List of all Items
	 */
	
	List<Item> getAllItems();
	
    /**
     * Update an existing item.
     * 
     * @param id the ID of the item to update
     * @param item the updated item data
     * @return Optional containing the updated item if found
     */
    Optional<Item> updateItem(Long id, Item item);
    
    /**
     * Delete an item by ID.
     * 
     * @param id the ID of the item to delete
     * @return true if deleted successfully, false if not found
     */
    boolean deleteItem(Long id);
    
    /**
     * Check if an item exists.
     * 
     * @param id the ID to check
     * @return true if exists, false otherwise
     */
    boolean itemExists(Long id);
    
    /**
     * Get total count of items.
     * 
     * @return total number of items
     */
    long getTotalItemCount();
    
    /**
     * Get items by category.
     * 
     * @param category the category to filter by
     * @return list of items in the specified category
     */
    List<Item> getItemsByCategory(String category);
    
    /**
     * Check if an item is in stock.
     * 
     * @param id the item ID
     * @return true if in stock, false otherwise
     */
    boolean isInStock(Long id);
    
    /**
     * Update stock quantity for an item.
     * 
     * @param id the item ID
     * @param quantity the new stock quantity
     * @return Optional containing the updated item
     */
    Optional<Item> updateStock(Long id, Integer quantity);
	
	
}
