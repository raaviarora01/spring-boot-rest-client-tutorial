package io.github.raaviarora.RestClientTutorial.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Post {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
