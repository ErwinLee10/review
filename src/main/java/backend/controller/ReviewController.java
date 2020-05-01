package backend.controller;

import backend.dto.ReviewDTO;
import backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController{

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/reviews/")
	@ResponseStatus(HttpStatus.OK)
	public List<ReviewDTO> findAll() {
		return reviewService.findAll();
	}
	
	@GetMapping("/sellers/{sellerId}/reviews")
	@ResponseStatus(HttpStatus.OK)
	public List<ReviewDTO> getBySellerId(@PathVariable Long sellerId) {
		return reviewService.getBySellerId(sellerId);
	}
	
	@GetMapping("/reviews/{sellerId}/reviews/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ReviewDTO getBySellerIdAndId(@PathVariable Long sellerId, @PathVariable Long id) {
		return reviewService.getBySellerIdAndId(sellerId, id);
	}

	@GetMapping("/sellers/{sellerId}/avg-rating")
	@ResponseStatus(HttpStatus.OK)
	public int getAverageRatingBySellerId(@PathVariable Long sellerId) {
		return reviewService.getAverageRatingBySellerId(sellerId);
	}

	@PostMapping("/sellers/{sellerId}/reviews")
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@PathVariable Long sellerId, @RequestBody ReviewDTO reviewDTO) {
		reviewDTO.setUserId(sellerId);
		return reviewService.create(reviewDTO);
	}

	@PutMapping("/sellers/{sellerId}/reviews/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable Long sellerId, @RequestBody ReviewDTO reviewDTO) {
		reviewDTO.setUserId(sellerId);
		reviewService.update(reviewDTO);
	}


	@DeleteMapping(value = "/sellers/{sellerId}/reviews/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long sellerId, @PathVariable("id") Long id) {
		reviewService.deleteByUserIdAndId(sellerId, id);
	}


	
}
