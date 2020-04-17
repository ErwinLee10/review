package backend.service;

import java.util.List;

import backend.dto.ReviewDTO;


public interface ReviewService {

	public Long create(ReviewDTO reviewDTO);

	public void update(ReviewDTO reviewDTO, Long userId, Long id);


	public List<ReviewDTO> getBySellerId(Long sellerId);



	public List<ReviewDTO> findAll();

	public void deleteByUserIdAndId(Long userId, Long id);
}
