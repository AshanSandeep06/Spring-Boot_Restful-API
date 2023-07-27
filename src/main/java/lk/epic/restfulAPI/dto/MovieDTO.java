package lk.epic.restfulAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MovieDTO {
    private String imdb;
    private String title;
    private String description;
    private double rating;
    private String category;
    private int year;
    private String imageUrl;
}
