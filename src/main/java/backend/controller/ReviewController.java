package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.ReviewDTO;
import backend.service.ReviewService;

@RestController
public class ReviewController{

	@Autowired
	private ReviewService reviewService;

	/*@GetMapping("/reviews")
	@ResponseStatus(HttpStatus.OK)
	public List<ReviewDTO> findAll() {
		return reviewService.findAll();
	}*/
	@GetMapping("/reviews")
	@ResponseStatus(HttpStatus.OK)
	public List<ReviewDTO> findAll() {
		return reviewService.findAll();
	}

	@GetMapping("sellers/{sellerId}/reviews")
	@ResponseStatus(HttpStatus.OK)
	public List<ReviewDTO> getBySellerId(@PathVariable Long sellerId) {

		return reviewService.getBySellerId(sellerId);
	}
	@PostMapping("/users/{userId}/reviews")
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@RequestBody ReviewDTO reviewDTO) {
		return reviewService.create(reviewDTO);
	}

	@PutMapping("/users/{userId}/reviews/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody ReviewDTO reviewDTO, Long userId, Long id) {
		reviewService.update(reviewDTO, userId, id);
	}

	@DeleteMapping(value = "/users/{userId}/reviews/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long userId, @PathVariable("id") Long id) {
		reviewService.deleteByUserIdAndId(userId, id);
	}
	

	
}
