package in.cdac.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.model.OrderModel;
import in.cdac.repository.OrderModelRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderModelRepository orderModelRepository;

	
	@GetMapping("/query")
	public  List<OrderModel> readByOtherThanPrimaryKey() {
		
		// return orderModelRepository.findByProductName("WIND");
		return orderModelRepository.customFindAllNativeSQLQuery();
	}
	
	
	@GetMapping("/{id}")
	public Optional<OrderModel> readOrderById(@PathVariable Long id) {
		return orderModelRepository.findById(id);
	}
	
	
	@GetMapping("/")
	public List<OrderModel> readAllOrder(boolean sortingorder, String sortBy) {
		// return orderModelRepository.findAll();
		sortBy = sortBy != null ? sortBy : "id"; 
		
		Direction dir = sortingorder ? Direction.ASC : Direction.DESC; 
		return orderModelRepository.findAll(Sort.by(dir, sortBy));
	}
	
	
	
	@PostMapping("/")
	public OrderModel createOrder(@RequestBody @Valid OrderModel orderModel) {
		log.info("Creating order {}", orderModel);
		
		orderModelRepository.save(orderModel);
		return orderModel;
	}
	
	
	@DeleteMapping("/{id}")
	public boolean deleteOrderById(@PathVariable Long id) {
		orderModelRepository.deleteById(id);
		return true;
	}
	
	
	@DeleteMapping("/")
	public boolean deleteOrderByIdV1(@RequestBody OrderModel orderModel) {
		orderModelRepository.deleteById(orderModel.getId());
		return true;
	}
	
	
	
	@GetMapping("/health")
	public String healthCheck() {
		return "Order Api Is UP!";
	}
}
