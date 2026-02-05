package com.dsv.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dsv.entity.Item;
import com.dsv.repository.ItemRepository;
import com.dsv.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	private final ItemRepository itemRepository;
	
	public ItemServiceImpl(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	
	@Override
	public Item createItem(Item item) {
		
        item.setId(null);
        
        return itemRepository.save(item);
		
	}


	@Override
	public Optional<Item> getItemById(Long id) {
		// TODO Auto-generated method stub
		return itemRepository.findById(id);
	}


	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		return itemRepository.findAll();
	}


	@Override
	public Optional<Item> updateItem(Long id, Item item) {
		// TODO Auto-generated method stub
		if(!itemRepository.existsById(id)) {
			return Optional.empty();
		}
		return itemRepository.update(id, item);
	}


	@Override
	public boolean deleteItem(Long id) {
		// TODO Auto-generated method stub
		if(!itemRepository.existsById(id)) {
			return false;
		}
		boolean deleted = itemRepository.deleteById(id);
		return deleted;
	}


    @Override
    public boolean itemExists(Long id) {
        return itemRepository.existsById(id);
    }
    
    @Override
    public long getTotalItemCount() {
        return itemRepository.count();
    }
    
    @Override
    public List<Item> getItemsByCategory(String category) {
        return itemRepository.findByCategory(category);
    }
    
    @Override
    public boolean isInStock(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.isPresent() && item.get().getStock() > 0;
    }
    
    @Override
    public Optional<Item> updateStock(Long id, Integer quantity) {

        Optional<Item> itemOpt = itemRepository.findById(id);
        
        if (itemOpt.isEmpty()) {
            return Optional.empty();  
        }
        
        Item item = itemOpt.get();
        item.setStock(quantity);
        
        return itemRepository.update(id, item);
    }

}
