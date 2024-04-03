package hello.hellospring.controller;

public class MemberForm {
    // 스프링이 name='name'이라는 속성을 가진 태그에서 값을 가져온 후 그 값을 setter를 통해서 name이라는 곳에 알아서 저장
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
