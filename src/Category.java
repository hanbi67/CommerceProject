import java.util.ArrayList;
import java.util.List;
//개별 카테고리 관리하는 클래스
public class Category {
    // Product 클래스를 List로 관리
    private List<Product> products;

    //카테고리 이름
    private String categoryName;

    //생성자
    public Category(String categoryName) {
        this.categoryName = categoryName;
        products = new ArrayList<>();
    }

    //세터
    public void addProduct(Product product) {
        products.add(product);
    }

    //게터
    public String getCategoryName() {
        return categoryName;
    }
    public List<Product> getProducts() {
        return products;
    }

}
