public class Customer {
    //고객 정보를 관리하는 클래스(고객 한 명의 정보)
    //고객명, 이메일, 등급 필드를 갖습니다.
    private String name;
    private String email;
    private String rank;

    //생성자
    public Customer(String name, String email, String rank) {
        this.name = name;
        this.email = email;
        this.rank = rank;
    }

}
