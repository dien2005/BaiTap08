package duonghao.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duonghao.vn.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // có thể thêm custom query nếu cần
}