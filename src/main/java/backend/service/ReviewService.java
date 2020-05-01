package backend.service;

import backend.dto.ReviewDTO;

import java.util.List;


public interface ReviewService {

	public Long create(ReviewDTO reviewDTO);

	public void update(ReviewDTO reviewDTO);
	
	public int getAverageRatingBySellerId(Long sellerId);

	public List<ReviewDTO> getBySellerId(Long sellerId);

	public List<ReviewDTO> findAll();
	
	public ReviewDTO getBySellerIdAndId(Long sellerId, Long reviewId);

	public void deleteByUserIdAndId(Long userId, Long id);
}
