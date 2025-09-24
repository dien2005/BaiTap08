package duonghao.vn.Entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String images; 

    @ManyToMany(mappedBy = "categories")
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "category_product",
        joinColumns = @JoinColumn(name="category_id"),
        inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private Set<Product> products = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Category(Long id, String name, String images, Set<User> users, Set<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.images = images;
		this.users = users;
		this.products = products;
	}

	public Category() {
		super();
	}

    // getters/setters
    
}