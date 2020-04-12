package backend.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backend.exception.ReviewNotFoundException;
import backend.dto.ReviewDTO;
import backend.model.Review;
import backend.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public Long create(ReviewDTO reviewDTO) {
		Review review = new Review();
		BeanUtils.copyProperties(reviewDTO, review);
		review = reviewRepository.save(review);
		return review.getId();
	}

	@Override
	public void update(ReviewDTO reviewDTO) {
		Review review = new Review();
		BeanUtils.copyProperties(reviewDTO, review);
		reviewRepository.save(review);
	}

	@Override
	public ReviewDTO getById(Long id) {
		Optional<Review> optional = reviewRepository.findById(id);
		
		if (optional.isEmpty()) {
			throw new ReviewNotFoundException("Review deleted.");
		}
		
		Review review = optional.get();
		ReviewDTO reviewDTO = new ReviewDTO();
		BeanUtils.copyProperties(review, reviewDTO);
		
		return reviewDTO;
	}

	@Override
	public List<ReviewDTO> findAll() {
		Iterable<Review> iterable = reviewRepository.findAll();

		List<ReviewDTO> result = StreamSupport.stream(iterable.spliterator(), false).map(new Function<Review, ReviewDTO>() {
			@Override
			public ReviewDTO apply(Review review) {
				ReviewDTO reviewDTO = new ReviewDTO();
				BeanUtils.copyProperties(review, reviewDTO);
				
				return reviewDTO;
			}
		}).collect(Collectors.toList());

		return result;
	}

	@Override
	public void deleteByUserIdAndId(Long userId, Long id) {
		Optional<Review> reviewOpt = reviewRepository.findById(id);
		if(reviewOpt.isPresent()) {
			Review review = reviewOpt.get();
			if(review.getUserId() == userId) {
				reviewRepository.deleteById(id);
			}
		}		
	}	
}
