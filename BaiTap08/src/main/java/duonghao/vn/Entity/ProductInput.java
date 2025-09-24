package duonghao.vn.Entity;

import java.util.List;

public class ProductInput {
    private String title;
    private int quantity;
    private String desc;
    private double price;
    private Long userId;       
    private Long categoryId;   
    private Long ownerId; 
    private List<Long> categoryIds;
    public ProductInput() {
    }
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public List<Long> getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}
	public ProductInput(String title, int quantity, String desc, double price, Long userId, Long categoryId,
			Long ownerId, List<Long> categoryIds) {
		super();
		this.title = title;
		this.quantity = quantity;
		this.desc = desc;
		this.price = price;
		this.userId = userId;
		this.categoryId = categoryId;
		this.ownerId = ownerId;
		this.categoryIds = categoryIds;
	}

    
}
