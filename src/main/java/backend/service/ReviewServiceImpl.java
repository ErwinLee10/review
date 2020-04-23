package backend.service;

import backend.dto.ReviewDTO;
import backend.model.Review;
import backend.repository.ReviewRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		Iterable<Review> iterable = reviewRepository.findAll();

		List<ReviewDTO> result = StreamSupport.stream(iterable.spliterator(), false).map(new Function<Review, ReviewDTO>() {
			@Override
			public ReviewDTO apply(Review review) {
				ReviewDTO reviewDTO = new ReviewDTO();
				if (review.getSellerId().equals(sellerId)) {
					BeanUtils.copyProperties(review, reviewDTO);
					return reviewDTO;
				}


				return null;
			}
		}).collect(Collectors.toList());
		while(result.remove(null)){
			result=result;
		}
		return result;
	}
	@Override
	public int getAverageRatingBySellerId(Long sellerId){
		//ReviewService reviewService = new ReviewService();
		Iterable<Review> iterable = reviewRepository.findAll();

		List<ReviewDTO> result = StreamSupport.stream(iterable.spliterator(), false).map(new Function<Review, ReviewDTO>() {
			@Override
			public ReviewDTO apply(Review review) {
				ReviewDTO reviewDTO = new ReviewDTO();
				if (review.getSellerId().equals(sellerId)) {
					BeanUtils.copyProperties(review, reviewDTO);
					return reviewDTO;
				}


				return null;
			}
		}).collect(Collectors.toList());
		while(result.remove(null)){
			result=result;
		}
		int totalRating=0;
		int numOfReviews=result.size();

		for (int i=0; i< result.size();i++){
			Review review = new Review();
			BeanUtils.copyProperties(result.get(i), review);
			totalRating=totalRating+review.getStarRating();

		}
		int averageRating=totalRating/numOfReviews;
		return averageRating;
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
