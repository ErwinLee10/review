package backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import backend.model.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>, QueryByExampleExecutor<Review> {
	List<Review> findBySellerId(long sellerId);

}
