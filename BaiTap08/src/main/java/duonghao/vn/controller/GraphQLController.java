package duonghao.vn.controller;


import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.math.BigDecimal;

import duonghao.vn.Entity.Category;
import duonghao.vn.Entity.CategoryInput;
import duonghao.vn.Entity.Product;
import duonghao.vn.Entity.ProductInput;
import duonghao.vn.Entity.User;
import duonghao.vn.Entity.UserInput;
import duonghao.vn.repository.CategoryRepository;
import duonghao.vn.repository.ProductRepository;
import duonghao.vn.repository.UserRepository;

@Controller
public class GraphQLController {
    private final ProductRepository productRepo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;

    public GraphQLController(ProductRepository productRepo, UserRepository userRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    // --- Queries ---
    @QueryMapping
    public List<Product> productsSortedByPrice() {
        return productRepo.findAllByOrderByPriceAsc();
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productRepo.findByCategories_Id(categoryId);
    }

    @QueryMapping
    public List<Product> products() { return productRepo.findAll(); }

    @QueryMapping
    public Product product(@Argument Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @QueryMapping
    public List<User> users() { return userRepo.findAll(); }

    @QueryMapping
    public User user(@Argument Long id) { return userRepo.findById(id).orElse(null); }

    @QueryMapping
    public List<Category> categories() { return categoryRepo.findAll(); }

    @QueryMapping
    public Category category(@Argument Long id) { return categoryRepo.findById(id).orElse(null); }

    // --- Mutations: users ---
    @MutationMapping
    public User createUser(@Argument("input") UserInput input) {
        User u = new User();
        u.setFullname(input.getFullname());
        u.setEmail(input.getEmail());
        u.setPassword(input.getPassword());
        u.setPhone(input.getPhone());
        // handle categories
        if (input.getCategoryIds() != null) {
            input.getCategoryIds().forEach(cid -> {
                categoryRepo.findById(cid).ifPresent(c -> u.getCategories().add(c));
            });
        }
        return userRepo.save(u);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument("input") UserInput input) {
        return userRepo.findById(id).map(u -> {
            u.setFullname(input.getFullname());
            u.setEmail(input.getEmail());
            u.setPhone(input.getPhone());
            if (input.getCategoryIds() != null) {
                u.getCategories().clear();
                input.getCategoryIds().forEach(cid -> categoryRepo.findById(cid).ifPresent(c -> u.getCategories().add(c)));
            }
            return userRepo.save(u);
        }).orElse(null);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        if (userRepo.existsById(id)) { userRepo.deleteById(id); return true; }
        return false;
    }

    // --- Mutations: categories ---
    @MutationMapping
    public Category createCategory(@Argument("input") CategoryInput input) {
        Category c = new Category();
        c.setName(input.getName());
        c.setImages(input.getImages());
        if (input.getUserIds() != null) {
            input.getUserIds().forEach(uid -> userRepo.findById(uid).ifPresent(u -> c.getUsers().add(u)));
        }
        return categoryRepo.save(c);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument("input") CategoryInput input) {
        return categoryRepo.findById(id).map(c -> {
            c.setName(input.getName());
            c.setImages(input.getImages());
            if (input.getUserIds() != null) {
                c.getUsers().clear();
                input.getUserIds().forEach(uid -> userRepo.findById(uid).ifPresent(u -> c.getUsers().add(u)));
            }
            return categoryRepo.save(c);
        }).orElse(null);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        if (categoryRepo.existsById(id)) { categoryRepo.deleteById(id); return true; }
        return false;
    }

    // --- Mutations: products ---
    @MutationMapping
    public Product createProduct(@Argument("input") ProductInput input) {
        Product p = new Product();
        p.setTitle(input.getTitle());
        p.setDesc(input.getDesc());
        p.setQuantity(input.getQuantity());
        p.setPrice(BigDecimal.valueOf(input.getPrice()));
        if (input.getOwnerId() != null) {
            userRepo.findById(input.getOwnerId()).ifPresent(p::setOwner);
        }
        if (input.getCategoryIds() != null) {
            input.getCategoryIds().forEach(cid -> categoryRepo.findById(cid).ifPresent(c -> p.getCategories().add(c)));
        }
        return productRepo.save(p);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument("input") ProductInput input) {
        return productRepo.findById(id).map(p -> {
            p.setTitle(input.getTitle());
            p.setDesc(input.getDesc());
            p.setQuantity(input.getQuantity());
            p.setPrice(BigDecimal.valueOf(input.getPrice()));
            if (input.getOwnerId() != null) userRepo.findById(input.getOwnerId()).ifPresent(p::setOwner);
            if (input.getCategoryIds() != null) {
                p.getCategories().clear();
                input.getCategoryIds().forEach(cid -> categoryRepo.findById(cid).ifPresent(c -> p.getCategories().add(c)));
            }
            return productRepo.save(p);
        }).orElse(null);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        if (productRepo.existsById(id)) { productRepo.deleteById(id); return true; }
        return false;
    }
}
