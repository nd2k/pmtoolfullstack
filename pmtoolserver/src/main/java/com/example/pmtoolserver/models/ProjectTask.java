package com.example.pmtoolserver.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
public class ProjectTask {

    private String projectTaskIdentifier;
    private String projectSequence;
    @NotBlank(message = "Please include a project summary")
    private String  summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    private Date dueDate;

//    @CreatedDate
//    private LocalDateTime createAt;
//    @LastModifiedDate
//    private LocalDateTime modifiedAt;
}
