package com.dsv.controller;

import com.dsv.dto.ApiResponse;
import com.dsv.exception.ItemNotFoundException;
import com.dsv.entity.Item;
import com.dsv.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Item management.
 * Provides RESTful API endpoints for CRUD operations on items.
 * 
 * Base URL: /api/items
 */
@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*") 
public class ItemController {
    
    private final ItemService itemService;
    
    /**
     * Constructor injection for ItemService
     * 
     * @param itemService the service to inject
     */
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    
    /**
     * Create a new item
     * 
     * POST /api/items
     * 
     * @param item the item to create (validated)
     * @return ResponseEntity with created item and 201 CREATED status
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Item>> createItem(@Valid @RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        ApiResponse<Item> response = ApiResponse.success(
                "Item created successfully", 
                createdItem
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    /**
     * Get a single item by ID
     * 
     * GET /api/items/{id}
     * 
     * @param id the item ID
     * @return ResponseEntity with item data and 200 OK status
     * @throws ItemNotFoundException if item not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Item>> getItemById(@PathVariable Long id) {
        Optional<Item> item = itemService.getItemById(id);
        
        if (item.isEmpty()) {
            throw new ItemNotFoundException(id);
        }
        
        ApiResponse<Item> response = ApiResponse.success(
                "Item retrieved successfully", 
                item.get()
        );
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get all items
     * 
     * GET /api/items
     * 
     * @return ResponseEntity with list of all items and 200 OK status
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Item>>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        
        ApiResponse<List<Item>> response = ApiResponse.success(
                "Items retrieved successfully. Total: " + items.size(), 
                items
        );
        return ResponseEntity.ok(response);
    }
    
    /**
     * Update an existing item
     * 
     * PUT /api/items/{id}
     * 
     * @param id the item ID to update
     * @param item the updated item data (validated)
     * @return ResponseEntity with updated item and 200 OK status
     * @throws ItemNotFoundException if item not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Item>> updateItem(
            @PathVariable Long id,
            @Valid @RequestBody Item item) {
        
        Optional<Item> updatedItem = itemService.updateItem(id, item);
        
        if (updatedItem.isEmpty()) {
            throw new ItemNotFoundException(id);
        }
        
        ApiResponse<Item> response = ApiResponse.success(
                "Item updated successfully", 
                updatedItem.get()
        );
        return ResponseEntity.ok(response);
    }
    
    /**
     * Delete an item by ID
     * 
     * DELETE /api/items/{id}
     * 
     * @param id the item ID to delete
     * @return ResponseEntity with success message and 200 OK status
     * @throws ItemNotFoundException if item not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteItem(@PathVariable Long id) {
        boolean deleted = itemService.deleteItem(id);
        
        if (!deleted) {
            throw new ItemNotFoundException(id);
        }
        
        ApiResponse<Object> response = ApiResponse.success(
                "Item deleted successfully with id: " + id
        );
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get items by category
     * 
     * GET /api/items/category/{category}
     * 
     * @param category the category to filter by
     * @return ResponseEntity with filtered items and 200 OK status
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<Item>>> getItemsByCategory(
            @PathVariable String category) {
        
        List<Item> items = itemService.getItemsByCategory(category);
        
        ApiResponse<List<Item>> response = ApiResponse.success(
                "Items in category '" + category + "' retrieved successfully. Total: " + items.size(), 
                items
        );
        return ResponseEntity.ok(response);
    }
    
    /**
     * Check if an item is in stock
     * 
     * GET /api/items/{id}/in-stock
     * 
     * @param id the item ID
     * @return ResponseEntity with stock status and 200 OK status
     * @throws ItemNotFoundException if item not found
     */
    @GetMapping("/{id}/in-stock")
    public ResponseEntity<ApiResponse<Boolean>> checkInStock(@PathVariable Long id) {
        if (!itemService.itemExists(id)) {
            throw new ItemNotFoundException(id);
        }
        
        boolean inStock = itemService.isInStock(id);
        
        ApiResponse<Boolean> response = ApiResponse.success(
                inStock ? "Item is in stock" : "Item is out of stock", 
                inStock
        );
        return ResponseEntity.ok(response);
    }
    
    /**
     * Update stock quantity for an item
     * 
     * PATCH /api/items/{id}/stock
     * 
     * @param id the item ID
     * @param stock the new stock quantity (from request body)
     * @return ResponseEntity with updated item and 200 OK status
     * @throws ItemNotFoundException if item not found
     */
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ApiResponse<Item>> updateStock(
            @PathVariable Long id,
            @RequestBody Integer stock) {
        
        if (stock < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        
        Optional<Item> updatedItem = itemService.updateStock(id, stock);
        
        if (updatedItem.isEmpty()) {
            throw new ItemNotFoundException(id);
        }
        
        ApiResponse<Item> response = ApiResponse.success(
                "Stock updated successfully", 
                updatedItem.get()
        );
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get total count of items
     * 
     * GET /api/items/count
     * 
     * @return ResponseEntity with total count and 200 OK status
     */
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> getTotalCount() {
        long count = itemService.getTotalItemCount();
        
        ApiResponse<Long> response = ApiResponse.success(
                "Total item count retrieved successfully", 
                count
        );
        return ResponseEntity.ok(response);
    }
    
    /**
     * Health check endpoint
     * 
     * GET /api/items/health
     * 
     * @return ResponseEntity with health status
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> healthCheck() {
        ApiResponse<String> response = ApiResponse.success(
                "Item API is running", 
                "OK"
        );
        return ResponseEntity.ok(response);
    }
}