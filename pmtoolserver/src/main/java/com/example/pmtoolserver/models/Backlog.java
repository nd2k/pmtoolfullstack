package com.example.pmtoolserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Backlog {

    private String backlogIdentifier;
    private Integer projectTaskSequence = 0;
    private List<ProjectTask> projectTasksList;

//    @CreatedDate
//    private LocalDateTime createAt;
//    @LastModifiedDate
//    private LocalDateTime modifiedAt;

}
