package backend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import backend.dto.ReviewDTO;
import backend.model.Review;
import backend.repository.ReviewRepository;

@SpringBootTest(classes = ReviewServiceImpl.class)
class ReviewServiceTest {
	
	@Autowired
	private ReviewService reviewService;
	
	@MockBean
	private ReviewRepository reviewRepository;
	
	
	@Test
	void addReviewTest() {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setUserId(1l);
		reviewDTO.setSellerId(1l);
		reviewDTO.setProductId(1l);
		reviewDTO.setStarRating(5);
		reviewDTO.setRemarks("some remarks");
		reviewDTO.setCreatedBy("some Name");
        reviewDTO.setLastUpdatedBy("some other Name");

		Review review = new Review();
		review.setId(1l);

		when(reviewRepository.save(any())).thenReturn(review);

		reviewService.create(reviewDTO);
		verify(reviewRepository, times(1)).save(any(Review.class));

	}
	
	@Test
	void updateReviewTest() {
		ReviewDTO reviewDTO = new ReviewDTO();

		reviewDTO.setRemarks("some remarks");
		reviewDTO.setCreatedBy("some Name");

		reviewDTO.setLastUpdatedBy("some other Name");

		reviewDTO.setStarRating(5);
		reviewDTO.setUserId(1l);
		reviewDTO.setSellerId(2l);
		reviewDTO.setProductId(1l);
		reviewService.update(reviewDTO);
		verify(reviewRepository, times(1)).save(any(Review.class));
	}
	

	
	@Test
	void getAllReviewTest() {
		reviewService.findAll();
		verify(reviewRepository, times(1)).findAll();
	}
	
	@Test
	void deleteByUserIdAndIdTest() {

		Long userId = 1l;
		Long id=1l;
		Review review = new Review();
		review.setUserId(userId);
		review.setId(id);
		review = reviewRepository.save(review);
		reviewService.deleteByUserIdAndId(1L,1L);
		verify(reviewRepository, times(1)).save(any(Review.class));
	}

	@Test
	void getBySellerIdTest() {
		Long sellerId = 8l;
		Review review = new Review();
		review.setSellerId(sellerId);

		reviewService.getBySellerId(sellerId);

		verify(reviewRepository, times(1)).findAll();
	}
	

}
