package lk.epic.restfulAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MovieDTO {
    @NotEmpty(message = "Imdb Shouldn't be Empty")
    @NotBlank(message = "Imdb Shouldn't be Blank")
    @Size(min = 9, max = 9, message = "Imdb should be fixed with 9 characters")
    @Pattern(regexp = "^(tt)[0-9]{7}$", message = "Imdb should be in this pattern eg:-tt0371743, tt0371769")
    private String imdb;
    @NotEmpty(message = "Title Shouldn't be Empty")
    @NotBlank(message = "Title Shouldn't be Blank")
    private String title;
    @NotEmpty(message = "Description Shouldn't be Empty")
    @NotBlank(message = "Description Shouldn't be Blank")
    private String description;
    @Min(value = 0, message = "Rating should not be less than 0")
    @Max(value = 10, message = "Rating should not be greater than 10")
//    @Digits(integer = 2, fraction = 1, message = "Rating should be in this pattern eg:-7.9, 10")
    private double rating;
    @NotEmpty(message = "Category Shouldn't be Empty")
    @NotBlank(message = "Category Shouldn't be Blank")
    private String category;
    // @Digits(integer = 4, fraction = 0, message = "Year should be in this pattern eg:-1998, 2009")
    @Min(value = 1900, message = "Year should be in this pattern eg:-1998, 2009")
    @Max(value = 2023, message = "Year should be in this pattern eg:-1998, 2009")
    private int year;
    @NotEmpty(message = "ImageUrl Shouldn't be Empty")
    @NotBlank(message = "ImageUrl Shouldn't be Blank")
    private String imageUrl;
}
