import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    //커머스 플랫폼의 상품을 관리하고 사용자 입력을 처리하는 클래스

    //Product를 관리하는 리스트 -> 카테고리를 통해 products에 접근하게 해야함
    private List<Category> categories;
    private ShoppingCart shoppingCart = new ShoppingCart();
    AdminModeSystem adminModeSystem = new AdminModeSystem(this);

    //main에서 categories를 전달받음
    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
    }

    //관리자 모드에서 카테고리 목록 접근용 getter
    public List<Category> getCategories() {
        return categories;
    }

    //관리자 모드에서 장바구니 접근용 getter
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //printCategoryList(categories);

            Integer select;
            Category selectedCategory;

            //입력받은 select에 따라 카테고리 내 제품 목록 출력
            //잘못된 입력일 경우 반복, try-catch문으로 예외처리
            while (true) {
                try {
                    printCategoryList(categories);

                    select = scanner.nextInt();

                    //0. 종료
                    if (select == 0) {
                        exit(scanner);  //프로그램 종료 메서드
                    }

                    //4,5번 주문 관리
                    if (select == (categories.size()+1) || select == (categories.size()+2)) {
                        if(shoppingCart.isEmpty()){
                            throw new IllegalStateException("장바구니가 비어있습니다.");
                        }
                        orderManage(select, scanner);
                        continue;
                    }

                    //6. 관리자 모드
                    if (select == (categories.size()+3)) {
                        boolean isAdmin = adminModeSystem.checkAdminPw(); //true or false 리턴

                        //isAdmin이 false이면 select선택 while문으로 돌아감
                        if(!isAdmin){
                            continue;
                        }
                        //관리자 모드 시작
                        adminModeSystem.adminMode();
                        continue;
                    }

                    selectedCategory = categories.get(select - 1);

                    //카테고리에 속한 제품 목록 출력
                    //printProductList(categories.get(select - 1));
                    printProductList(selectedCategory);

                    break;
                } catch (IndexOutOfBoundsException e){
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                } catch (InputMismatchException e){
                    System.out.println("숫자만 입력해야합니다. 다시 입력하세요.");
                } finally {
                    scanner.nextLine(); // 입력 버퍼 정리
                }

            }//while()

            Integer productSelect;
            Product selectedProduct;
            //잘못된 입력일 경우 반복
            while (true) {
                try {
                    productSelect = scanner.nextInt(); //제품 선택

                    if(productSelect == 0){
                        start();  //뒤로가기 - 시작 지점으로 돌아감
                    }
                    //selectedProduct = categories.get(select - 1).getProducts().get(productSelect - 1);
                    selectedProduct = selectedCategory.getProducts().get(productSelect - 1);

                    //선택한 카테고리 -> 선택한 제품의 상품 정보 출력
                    System.out.printf("선택한 상품: ");

                    //categories.get(select-1).getProducts().get(productSelect-1).printSelectedInfo();
                    selectedProduct.printSelectedInfo();

                    System.out.printf("\"%-10s | %,10d원 | %s\"%n",
                            selectedProduct.getName(), selectedProduct.getPrice(), selectedProduct.getDescription());
                    break;
                } catch (IndexOutOfBoundsException e){
                    System.out.println("잘못된 입력입니다. 다시 입력하세요");
                } catch (InputMismatchException e){
                    System.out.println("숫자만 입력해야합니다. 다시 입력하세요.");
                } finally {
                    scanner.nextLine(); // 입력 버퍼 정리
                }
            }//while()

            while (true){
                try {
                    System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
                    System.out.println("1. 확인       2. 취소");

                    Integer confirm = scanner.nextInt();

                    if(confirm == 1) {
                        while (true){
                            System.out.println("장바구니에 담을 상품 수량을 입력해주세요.");
                            Integer quantity = scanner.nextInt();
                            if(quantity <= selectedProduct.getStock()){

                                addToCart(selectedProduct, quantity);
                                break;
                            }
                            else System.out.println("상품 재고가 부족합니다. 현재 상품 재고 수: " + selectedProduct.getStock());
                        }
                        break;
                    }
                    else if (confirm == 2) {
                        System.out.println("장바구니 담기를 취소합니다. 메인으로 돌아갑니다.\n");
                        break;
                    }
                    else{
                        System.out.println("잘못된 입력입니다. 다시 입력하세요");
                    }
                } catch (InputMismatchException e){
                    System.out.println("숫자만 입력해야합니다. 다시 입력하세요.");
                } finally {
                    scanner.nextLine(); // 입력 버퍼 정리
                }
            }//while()

        }//while()
    }//start()

    //main 실행 시 바로 출력되는 메인 메뉴
    private void printCategoryList(List<Category> categories) {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }
        System.out.println("0. 종료             | 프로그램 종료");
        System.out.println((categories.size()+3) + ". 관리자 모드");

        //장바구니에 이미 상품이 들어 있으면 출력
        if (!shoppingCart.isEmpty()) {
            System.out.println("\n[ 주문 관리 ]");
            System.out.println((categories.size()+1) + ". 장바구니 확인    | 장바구니를 확인 후 주문합니다.");
            System.out.println((categories.size()+2) + ". 주문 취소       | 진행중인 주문을 취소합니다.");
        }
    }

    //카테고리별 상품 목록 출력
    private void printProductList(Category category) {

        System.out.println("[ " + category.getCategoryName() + " 카테고리 ]");
        List<Product> products = category.getProducts();

        for (int i = 0; i < products.size(); i++) {
            System.out.print((i + 1) + ". ");
            products.get(i).printInfo();
        }
        System.out.println("0. 뒤로가기");
    }

    //장바구니 담기 메서드
    private void addToCart(Product selectedProduct, Integer quantity) {
        shoppingCart.addProduct(selectedProduct, quantity);
        System.out.println(selectedProduct.getName() + "(이)가 장바구니에 추가되었습니다.");
        shoppingCart.printCartInfo(); //장바구니에 담은 품목 출력
        System.out.println();
    }

    //주문 관리 출력 - 장바구니에 이미 상품이 들어 있으면 출력
    private void orderManage(Integer select, Scanner scanner){
        while (true){
            try {
                //4. 장바구니 확인
                if (select == (categories.size()+1)){
                    System.out.println("아래와 같이 주문 하시겠습니까?\n");

                    System.out.println("[ 장바구니 내역 ]");
                    shoppingCart.printCartInfo();

                    System.out.println("\n[ 총 주문 금액 ]");

                    //장바구니 내 모든 상품의 금액을 더한 주문 금액
                    shoppingCart.printOrderTotalPrice();

                    while (true){
                        try {
                            System.out.println("\n1. 주문 확정        2. 메인으로 돌아가기");

                            Integer finalConfirm = scanner.nextInt();

                            if ( finalConfirm == 1) {
                                System.out.printf("주문이 완료되었습니다! ");
                                shoppingCart.printOrderTotalPrice();
                                shoppingCart.confirmOrder(); //재고 차감 + 장바구니 초기화

                                return;
                            }
                            else if (finalConfirm == 2){
                                System.out.println("메인으로 돌아갑니다.");
                                start();
                                return;
                            }
                            else{
                                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                            }
                        }
                        catch (InputMismatchException e) {
                            System.out.println("숫자만 입력해야합니다. 다시 입력하세요.");
                        }
                        finally {
                            scanner.nextLine();
                        }
                    }//while()

                }

                //5. 주문 취소
                if (select == (categories.size()+2)) {
                    System.out.println("진행중인 주문을 취소합니다. 메인으로 돌아갑니다.\n");
                    start();
                    return;
                }
                else{
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("숫자만 입력해야합니다. 다시 입력하세요.");
            }
            finally {
                scanner.nextLine();
            }
        }//while()
    }

    //프로그램 종료 메서드
    private void exit(Scanner scanner) {
        System.out.println("\n커머스 플랫폼을 종료합니다.");
        scanner.close();
        System.exit(0);
    }
}
