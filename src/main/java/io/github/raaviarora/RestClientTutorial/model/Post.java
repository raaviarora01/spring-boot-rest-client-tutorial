package io.github.raaviarora.RestClientTutorial.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWarDeployment;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Post {

    @NotNull(message = "User Id is required")
    private Integer userId;

    private Integer id;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Body cannot be blank")
    @Size(max = 500, message = "Body cannot exceed 500 characters")
    private String body;
}
