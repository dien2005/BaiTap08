package duonghao.vn.Entity;

import java.util.List;

public class CategoryInput {
    private String name;
    private String images;
    private List<Long> userIds;

    public CategoryInput() {
    }

	public CategoryInput(String name, String images, List<Long> userIds) {
		super();
		this.name = name;
		this.images = images;
		this.userIds = userIds;
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

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}

    
}
