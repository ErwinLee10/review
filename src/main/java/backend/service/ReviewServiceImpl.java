package backend.service;

import backend.dto.ReviewDTO;
import backend.exception.ReviewNotFoundException;
import backend.model.Review;
import backend.repository.ReviewRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
	public void deleteByUserIdAndId(Long userId, Long id) {
		Optional<Review> reviewOpt = reviewRepository.findById(id);
		if(reviewOpt.isPresent()) {
			Review review = reviewOpt.get();
			if(review.getUserId().equals(userId)) {
				reviewRepository.deleteById(id);
			}
		}
	}

	@Override
	public void update(ReviewDTO reviewDTO) {
		Review review = new Review();
		BeanUtils.copyProperties(reviewDTO, review);
		reviewRepository.save(review);
	}

	@Override
	public List<ReviewDTO> getBySellerId(Long sellerId) {
		List<Review> list = reviewRepository.findBySellerId(sellerId);
		List<ReviewDTO> result = new ArrayList<ReviewDTO>();
		if (list != null && list.size() > 0) {
			for (Review review: list) {
				ReviewDTO reviewDTO = new ReviewDTO();
				BeanUtils.copyProperties(review, reviewDTO);
				result.add(reviewDTO);
			}
		}
		return result;
	}
	
	@Override
	public ReviewDTO getBySellerIdAndId(Long sellerId, Long reviewId) {
		Optional<Review> review = reviewRepository.findById(reviewId);
		if (review.isEmpty()) {
			throw new ReviewNotFoundException("Not review found");
		}
		ReviewDTO reviewDTO = new ReviewDTO();
		BeanUtils.copyProperties(review.get(), reviewDTO);
		return reviewDTO;
	}
	
	@Override
	public int getAverageRatingBySellerId(Long sellerId){
		List<Review> list = reviewRepository.findBySellerId(sellerId);
		int totalRating = 0;
		
		if (list != null && list.size() > 0) {
			for (Review review: list){
				totalRating = totalRating + review.getStarRating();
			}
			return totalRating / list.size();
		}
		//Default is 5 star
		return 5;
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
}
