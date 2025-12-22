import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    //장바구니 클래스
    //상품명, 수량, 가격 정보를 저장하며, 항목을 동적으로 추가 및 조회할 수 있어야 합니다.

//    private String productName;
//    private Integer quantity;
//    private Integer price;

    //장바구니 전체
    //private List<Product> shoppingCart;

    //장바구니 속 아이템, 물건 한 종류에 대해 품목명 + 수량을 함께 저장
    private Map<Product, Integer> cartItems = new HashMap<>();

    //상품 추가(같은 상품이면 수량 누적)
    public void addProduct(Product product, Integer quantity) {
//        cartItems.put(product,
//                cartItems.getOrDefault(product, 0) + quantity);
        if(cartItems.containsKey(product)){
            cartItems.put(product, cartItems.get(product) + quantity);
        } else {
            cartItems.put(product, quantity);
        }
    }

    //장바구니 출력
    public void printCartInfo(){
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            System.out.println(String.format(
                    "%s | %,10d원 | 수량: %d개", product.getName(), product.getPrice(), quantity));
        }
    }

    //반복문으로 총 주문 금액 계산
    public int calculateOrderTotalPrice(){
        int orderTotalPrice = 0;

        //product.getPrice() -> 가격
        //cart.get(product) -> 수량
        for (Product product : cartItems.keySet()) {
            orderTotalPrice += product.getPrice() * cartItems.get(product);
        }

        return orderTotalPrice;
    }

    //총 주문 금액 출력
    public void printOrderTotalPrice() {
        System.out.println("총 금액: " + String.format("%,10d원", calculateOrderTotalPrice()));
    }

    //주문 시 재고 차감 + 장바구니 초기화
    public void confirmOrder() {
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            int beforeStock = product.getStock();  //차감 전 재고
            int afterStock = beforeStock - quantity; //차감 후 재고

            product.setStock(afterStock);
            //iPhone 15 재고가 30개 → 29개로 업데이트되었습니다.
            System.out.println(product.getName() + " 재고가 " + beforeStock + "개 → " + afterStock + "개로 업데이트 되었습니다.");
        }
        cartItems.clear(); //장바구니 초기화
        printCartInfo();
    }

    // 장바구니 비어있는지 체크 (주문 관리 출력용)
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}


















