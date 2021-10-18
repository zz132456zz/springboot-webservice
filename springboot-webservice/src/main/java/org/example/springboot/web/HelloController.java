package org.example.springboot.web;

import org.example.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController  // 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 준다. (@ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해준다.)
public class HelloController {

    @GetMapping("/hello")  // HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어 준다.
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {  // RequestParam : 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션이다.
        return new HelloResponseDto(name, amount);
    }
}
