package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;



import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello") //http://localhost:9090/api/get/{}
    public String getHello(){
        return "get Hello";
    }

    @RequestMapping(path="/hi", method= RequestMethod.GET) //get, post, put, delete 모든 메소드를 다 받을 수 있음, 방지를 위해 method인자를 받음
    public String hi(){
        return "hi";
    }

    //http://localhost:9090/api/get/path-variable/{name}
    //주소엔 대문자 안 씀 X pathVariable => path-variable
    //변화하는 값을 넣을 땐 pathVariable로 받아야 함
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable(name = "name") String pathName){
        //public String pathVariable(@PathVariable String name){
        //{name}과 pathvariable의 파라미터 명은 동일해야한다
        //단 필요 시, @PathVariable(name = "name") String pathName 와 같은 방식으로 파라미터 명을 변경해줄 수 있다
        System.out.println("PathVariable : "+pathName);

        return "name is "+pathName;
    }

    //쿼리 파라미터란?
    //?키-밸류 & 키 - 밸류 로 구성된 pathvariable
    //http://localhost:9090/api/get/query-param? user = kmoh42 & email = mole4020@gmail.com & age = 30

    //지정되지 않은 쿼리 파라미터를 넣을 수 있음
    @GetMapping(path="/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){
        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+" = "+entry.getValue()+"\n");
        });
        return sb.toString();
    }

    //받는 쿼리파람이 직관적으로 명시되어있어서 좋긴 하다만, 만약 받아야 하는 파람이 많을 경우 오히려 가독성과 관리 용이성이 떨어짐,,
    @GetMapping("/query-param2")
    public String queryParam2(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String age
    ){
        System.out.println("name : "+name);
        System.out.println("email : "+email);
        System.out.println("age : "+age);

        return name+", "+email+", "+age;
    }

    //현업에서 가장 자주 사용하는 방법이라고 한다.
    //주의 : 쿼리파람 인자로 들어오는 것들에는 리퀘스트 파람 어노테이션을 안붙인다
    //되게 신기함,,,, http://localhost:9090/api/get/query-param3?id=ozzie&email=kkkk@gmail.com&age=30
    //?뒤의 인자값들을 &단위로 쪼개서 알아서 매핑시켜주나봄,,
    @GetMapping("/query-param3")
    public String queryParam3(UserRequest userRequest){
        System.out.println(userRequest.getName());
        //if()
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }

}
