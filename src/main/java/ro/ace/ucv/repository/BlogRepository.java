package ro.ace.ucv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.ace.ucv.entity.Blog;
import ro.ace.ucv.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByUser(User user);
}
