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
                case 1:
                    //상품 추가 기능 메서드
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
                    System.out.println("default잘못된 입력입니다. 다시 입력해주세요.");
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
}
