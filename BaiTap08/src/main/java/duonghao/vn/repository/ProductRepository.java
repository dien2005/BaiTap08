package duonghao.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duonghao.vn.Entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // tìm product theo category id
    List<Product> findByCategories_Id(Long categoryId);

    // sắp xếp product theo giá tăng dần
    List<Product> findAllByOrderByPriceAsc();
}