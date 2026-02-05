package com.dsv.repository;

import com.dsv.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repository class for managing Item entities in memory.
 * Uses ArrayList as the in-memory data store.
 * Thread-safe ID generation using AtomicLong.
 */
@Repository
public class ItemRepository {
    

    private final List<Item> items = new ArrayList<>();
    

    private final AtomicLong idGenerator = new AtomicLong(1);
    

    public ItemRepository() {
        initializeSampleData();
    }
    

    private void initializeSampleData() {
        save(new Item("Laptop", "High-performance laptop with 16GB RAM", 899.99, 15, "Electronics"));
        save(new Item("Smartphone", "Latest 5G smartphone with 128GB storage", 699.99, 25, "Electronics"));
        save(new Item("Headphones", "Wireless noise-cancelling headphones", 199.99, 50, "Accessories"));
        save(new Item("Coffee Maker", "Automatic drip coffee maker with timer", 79.99, 30, "Home Appliances"));
        save(new Item("Running Shoes", "Lightweight running shoes for all terrains", 89.99, 40, "Sports"));
    }
    
    /**
     * Save a new item to the repository.
     * Automatically generates and assigns a unique ID.
     * 
     * @param item the item to save
     * @return the saved item with generated ID
     */
    public Item save(Item item) {
        // Generate unique ID
        item.setId(idGenerator.getAndIncrement());
        items.add(item);
        return item;
    }
    
    /**
     * Find an item by its ID.
     * 
     * @param id the ID to search for
     * @return Optional containing the item if found, empty Optional otherwise
     */
    public Optional<Item> findById(Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }
    
    /**
     * Find all items in the repository.
     * 
     * @return list of all items
     */
    public List<Item> findAll() {
        return new ArrayList<>(items); // Return a copy to prevent external modifications
    }
    
    /**
     * Update an existing item.
     * 
     * @param id the ID of the item to update
     * @param updatedItem the item with updated information
     * @return Optional containing the updated item if found, empty Optional otherwise
     */
    public Optional<Item> update(Long id, Item updatedItem) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                updatedItem.setId(id); // Preserve the original ID
                items.set(i, updatedItem);
                return Optional.of(updatedItem);
            }
        }
        return Optional.empty();
    }
    
    /**
     * Delete an item by its ID.
     * 
     * @param id the ID of the item to delete
     * @return true if item was deleted, false if not found
     */
    public boolean deleteById(Long id) {
        return items.removeIf(item -> item.getId().equals(id));
    }
    
    /**
     * Check if an item exists by ID.
     * 
     * @param id the ID to check
     * @return true if exists, false otherwise
     */
    public boolean existsById(Long id) {
        return items.stream().anyMatch(item -> item.getId().equals(id));
    }
    
    /**
     * Get the total count of items.
     * 
     * @return total number of items
     */
    public long count() {
        return items.size();
    }
    
    /**
     * Find items by category.
     * 
     * @param category the category to search for
     * @return list of items in that category
     */
    public List<Item> findByCategory(String category) {
        return items.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .toList();
    }
}