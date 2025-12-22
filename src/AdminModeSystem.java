import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//관리자 모드
//1. 관리자 인증 기능 메서드
//2. 상품 추가 기능 메서드
//3. 상품 수정 기능 메서드
//4. 상품 삭제 기능 메서드
public class AdminModeSystem {
    //CommerceSystem에서 비밀번호 입력 받고 내용 출력
    CommerceSystem commerceSystem;
    public AdminModeSystem(CommerceSystem commerceSystem){
        this.commerceSystem = commerceSystem;
    }

    //관리자용 비밀번호 설정
    private String adminPw = null;
    private Boolean AdminMode = false; //기본값: 관리자모드
    private Integer authCount = 0;

    //관리자 비밀번호가 설정되지 않았다면, 비밀번호 설정하기
    private void setAdminPw(String adminPw){
        this.adminPw = adminPw;
    }

    //관리자 모드로 설정하는 메서드
    private void setAdminMode(boolean isAdminMode){
        this.AdminMode = isAdminMode;
    }

    //관리자 비밀번호가 설정되어있다면, 비밀번호 입력 시 count 3번 실패하면 메인메뉴로 돌아가기
    public Boolean checkAdminPw(){
        Scanner scanner = new Scanner(System.in);

        //adminPw이 null이면
        if(adminPw == null){
            System.out.println("관리자 비밀번호가 설정되어있지 않습니다! 비밀번호 설정이 필요합니다.");
            System.out.println("설정할 비밀번호를 입력해주세요: ");
            setAdminPw(scanner.nextLine());
            //System.out.println("현재 비밀번호: " + adminPw);
        }

        while (authCount < 3){

            System.out.println("\n관리자 비밀번호를 입력해주세요: ");
            String inputPw = scanner.nextLine();
            //System.out.println("입력한 비밀번호: " + inputPw);

            if(adminPw.equals(inputPw)){
                //관리자 모드로 체크
                setAdminMode(true);
                return AdminMode; //true 리턴
            }
            else {
                authCount++;
                System.out.println("비밀번호가 일치하지 않습니다! 다시 입력해주세요.");
            }
        }

        System.out.println("비밀번호를 3회 틀렸습니다. 메인으로 돌아갑니다.\n");
        authCount = 0; //인증 횟수 0으로 초기화
        setAdminMode(false);
        return AdminMode; //false 리턴
    }

    //관리자 모드 시작
    public void adminMode(){
        Scanner scanner = new Scanner(System.in);

        //while문으로 관리자모드 메인 메뉴 출력
        while (AdminMode) {
            printAdminMenu();  //관리자 모드 메뉴 출력
            int adminSelect = scanner.nextInt();

            //adminSelect 1~4번 선택
            switch (adminSelect){
                case 0:
                    //메인으로 돌아가기
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    setAdminMode(false);
                    break;
                case 1:
                    //상품 추가 기능 메서드
                    adminAddProduct(commerceSystem.getCategories());
                    break;
                case 2:
                    //상품 수정 기능 메서드
                    break;
                case 3:
                    //상품 삭제 기능 메서드
                    break;
                case 4:
                    //전체 상품 현황(모든 카테고리, 모든 상품 출력)
                    break;
                default:
                    System.out.println("default 잘못된 입력입니다. 다시 입력해주세요.");
                    break;
            }

        }

    }

    //관리자 모드 메뉴 출력
    private void printAdminMenu(){
        System.out.println("\n[ 관리자 모드 ]");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
        System.out.println("4. 전체 상품 현황");
        System.out.println("0. 메인으로 돌아가기");
    }

    //1. 상품 추가 기능
    //새로운 상품의 정보(상품명, 가격, 설명, 재고수량)를 입력받아 기존 카테고리에 추가
    private void adminAddProduct(List<Category> categories){

        System.out.println("\n어느 카테고리에 상품을 추가하시겠습니까?");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
        }

        Scanner scanner = new Scanner(System.in);
        int select = scanner.nextInt();
        scanner.nextLine(); //개행 제거

        Category selectedCategory = categories.get(select - 1);

        System.out.println("\n[ " + selectedCategory.getCategoryName() +" 카테고리에 상품 추가" +" ]");

        //상품명 입력 + 전체 카테고리 중복 검사
        String productName;
        while (true){
            System.out.printf("상품명을 입력해주세요: ");
            productName = scanner.nextLine();

            if (productName.isEmpty()){
                System.out.println("상품명은 필수로 입력해야합니다. 다시 입력해주세요.");
                continue;
            }
            //중복 여부
            boolean isduplicated = false;

            //모든 카테고리의 모든 상품 순회하며 확인
            for (int i = 0; i < categories.size(); i++) {
                for (int j = 0; j < categories.get(i).getProducts().size(); j++) {
                    if(categories.get(i).getProducts().get(j).getName().equals(productName)){
                        isduplicated = true;
                        break;
                    }
                }
            }
            //만약 중복이면 경고메세지 출력
            if(isduplicated){
                System.out.println("같은 상품명이 이미 존재합니다. 다른 상품명으로 입력해주세요.");
                continue;
            }

            //중복이 아니면 상품명 확정
            break;
        }//while()


        System.out.printf("가격을 입력해주세요: ");
        int productPrice = scanner.nextInt();
        scanner.nextLine(); // 개행 제거

        System.out.printf("상품 설명을 입력해주세요: ");
        String productDescription = scanner.nextLine();

        System.out.printf("재고수량을 입력해주세요: ");
        int productStock = scanner.nextInt();
        scanner.nextLine(); // 개행 제거

        System.out.printf("\"%-10s | %,10d원 | %s | 재고: %d개\"%n",
                productName, productPrice, productDescription, productStock);


        while (true){
            try {
                System.out.println("위 정보로 상품을 추가하시겠습니까?");
                System.out.println("1. 확인       2. 취소");
                int confirm = scanner.nextInt();
                if (confirm == 1) break;
                else if (confirm == 2) {
                    System.out.println("관리자 메뉴로 이동합니다.");
                    return;
                }
                else {
                    System.out.println("잘못된 입력입니다. 다시 입력하세요");
                }
            }
            catch (InputMismatchException e){
                System.out.println("숫자만 입력해야합니다. 다시 입력하세요.");
            } finally {
                scanner.nextLine(); // 입력 버퍼 정리
            }
        }

        Product newProduct = new Product(productName, productPrice, productDescription, productStock);
        selectedCategory.getProducts().add(newProduct); //선택한 카테고리에 상품 추가

        System.out.println("상품이 성공적으로 추가되었습니다!");

        //선택한 카테고리의 상품 목록 출력 (확인용)
        List<Product> products = selectedCategory.getProducts();

        for (int i = 0; i < products.size(); i++) {
            System.out.print((i + 1) + ". ");
            products.get(i).printSelectedInfo();
        }
    }

}
