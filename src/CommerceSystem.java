import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    //커머스 플랫폼의 상품을 관리하고 사용자 입력을 처리하는 클래스

    //Product를 관리하는 리스트 -> 카테고리를 통해 products에 접근하게 해야함
    private List<Category> categories;

    //생성자 - main에서 categories를 전달받음
    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printCategoryList(categories);
            Integer select;
            Category selectedCategory = null;

            //입력받은 select에 따라 카테고리 내 제품 목록 출력
            //잘못된 입력일 경우 반복, try-catch문으로 예외처리
            while (true) {
                try {
                    select = scanner.nextInt();

                    if (select == 0) {
                        exit(scanner);  //프로그램 종료 메서드
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

            }

            Integer productSelect;
            Product selectedProduct;
            //잘못된 입력일 경우 반복, try-catch문으로 예외처리
            while (true) {
                try {
                    productSelect = scanner.nextInt(); //제품 선택

                    if(productSelect == 0){
                        break;  //뒤로가기 - 카테고리 선택으로 돌아감
                    }
                    //selectedProduct = categories.get(select - 1).getProducts().get(productSelect - 1);
                    selectedProduct = selectedCategory.getProducts().get(productSelect - 1);
                    //선택한 카테고리->선택한 제품의 상품 정보 출력
                    System.out.printf("선택한 상품: ");
                    //categories.get(select-1).getProducts().get(productSelect-1).printSelectedInfo();
                    selectedProduct.printSelectedInfo();
                    break;
                } catch (IndexOutOfBoundsException e){
                    System.out.println("잘못된 입력입니다. 다시 입력하세요");
                } catch (InputMismatchException e){
                    System.out.println("숫자만 입력해야합니다. 다시 입력하세요.");
                } finally {
                    scanner.nextLine(); // 입력 버퍼 정리
                }
            }

        }
    }

    //main 실행 시 바로 출력되는 메뉴
    public void printCategoryList(List<Category> categories) {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }
        System.out.println("0. 종료             | 프로그램 종료");
    }

    public void printProductList(Category category) {

        System.out.println("[ " + category.getCategoryName() + " 카테고리 ]");
        List<Product> products = category.getProducts();

        for (int i = 0; i < products.size(); i++) {
            System.out.print((i + 1) + ". ");
            products.get(i).printInfo();
        }
        System.out.println("0. 뒤로가기");
    }

    //프로그램 종료 메서드
    private void exit(Scanner scanner) {
        System.out.println();
        System.out.println("커머스 플랫폼을 종료합니다.");
        scanner.close();
        System.exit(0);
    }

}
