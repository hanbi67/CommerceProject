import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //카테고리 리스트 생성
        List<Category> categories = new ArrayList<>();
        
        //카테고리 객체 생성
        Category electronics = new Category("전자제품");
        Category clothes = new Category("의류");
        Category food = new Category("음식");
        
        //카테고리 리스트에 추가
        categories.add(electronics);
        categories.add(clothes);
        categories.add(food);

        List <Product> products = new ArrayList<>();
        //전자제품 카테고리 제품 추가
        electronics.addProduct(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10));
        electronics.addProduct(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 5));
        electronics.addProduct(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 6));
        electronics.addProduct(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 7));

        //의류 카테고리 제품 추가
        clothes.addProduct(new Product("아디다스 패딩", 2500000, "이번 시즌 신상 패딩", 5));
        clothes.addProduct(new Product("나이키 운동화", 1300000, "이번 시즌 신상 운동화", 4));
        clothes.addProduct(new Product("뉴발란스 바람막이", 2200000, "이번 시즌 한정 바람막이", 3));

        //음식 카테고리 제품 추가
        food.addProduct(new Product("초코송이", 1200, "초코 과자", 8));
        food.addProduct(new Product("허니버터칩", 1800, "달콤한 감자칩", 7));
        food.addProduct(new Product("하리보 젤리", 2000, "곰돌이 모양 젤리", 6));
        food.addProduct(new Product("마이쮸", 800, "포도맛 마이쮸", 5));
        food.addProduct(new Product("새콤달콤", 500, "포도맛 새콤달콤", 4));
        
        CommerceSystem commerceSystem = new CommerceSystem(categories);
        //메뉴 출력
        commerceSystem.start();

    }
}