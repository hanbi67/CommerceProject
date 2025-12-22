import java.util.ArrayList;
import java.util.List;

public class Product {
    //설명 : 개별 상품을 관리하는 클래스입니다. 현재는 전자제품만 관리합니다.
    //상품명, 가격, 설명, 재고수량 필드를 갖습니다.

    private String name;
    private Integer price;
    private String description;
    private Integer stock;

    //생성자
    public Product(String name, Integer price, String description, Integer stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    //메뉴 출력 메서드
    public void printInfo() {
        System.out.printf("%-10s | %,10d원 | %s%n", name, price, description);
    }

    //선택한 제품 출력 메서드
    public void printSelectedInfo() {
        System.out.println(String.format("%s | %,10d원 | %s | 재고: %d개%n", name, price, description, stock));
    }

    public String getName() {
        return name;
    }
    public Integer getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public Integer getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
