package org.example.springboot.domain.posts;

import javafx.geometry.Pos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.swing.text.StringContent;

@Getter  // 클래스 내 모든 필드의 Getter 메소드 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 추가
@Entity  // 테이블과 링크될 클래스임을 나타낸다
public class Posts {

    @Id  // 해당 테이블의 PK 필드를 나타낸다
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // PK의 생성 규칙을 나타낸다
    private long id;

    @Column(length = 500, nullable = false)  // 테이블의 칼럼을 나타낸다 굳이 선언하지 않아도 되지만 옵션을 변경하고 싶으면 사용한다
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder  // 해당 클래스의 빌더 패턴 클래스를 생성 (생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함)
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }



}
