package biz.neustar.fp;

public class Data {

	// Data is a bounded mutable object to
	// store category and sub-category pair.

	public enum Category {
		// List of possible categories.
		PERSON, PLACE, ANIMAL, COMPUTER, OTHER;
	}

	private Category categoryType;
	private String subCategory;

	public Data(String categoryTypeStr, String subCategory)
			throws IllegalArgumentException {
		this.categoryType = Category.valueOf(categoryTypeStr);
		this.subCategory = subCategory;
	}

	public Data(Category categoryType, String subCategory)
			throws IllegalArgumentException {
		this.categoryType = categoryType;
		this.subCategory = subCategory;
	}

	// Accessors and Mutators.
	
	public String getCategoryTypeStr() {
		return categoryType.toString();
	}

	public Category getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryTypeStr) {
		setCategoryType(Category.valueOf(categoryTypeStr));
	}

	public void setCategoryType(Category categoryType)
			throws IllegalArgumentException {
		this.categoryType = categoryType;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	@Override
	public boolean equals(Object o) {
		// EFFECTS: Returns true if the category and sub-category
		// are same for this object and o, else false.
		
		if (!(o instanceof Data)) {
			return false;
		}

		Data d = (Data) o;

		if (this.categoryType.equals(d.categoryType)
				&& this.subCategory.equals(d.subCategory)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 17 * this.categoryType.hashCode() + this.subCategory.hashCode();
	}

	@Override
	public String toString() {
		// EFFECTS: Returns the string representation of this object
		
		StringBuilder sb = new StringBuilder();
		sb.append(this.getCategoryType());
		sb.append(" ");
		sb.append(this.getSubCategory());
		return sb.toString();
	}

}
