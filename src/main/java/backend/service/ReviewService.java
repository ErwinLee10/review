package backend.service;

import backend.dto.ReviewDTO;

import java.util.List;


public interface ReviewService {

	public Long create(ReviewDTO reviewDTO);

	public void update(ReviewDTO reviewDTO);


	public List<ReviewDTO> getBySellerId(Long sellerId);



	public List<ReviewDTO> findAll();

	public void deleteByUserIdAndId(Long userId, Long id);
}
