package com.example.pmtoolserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Document(collection = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    private ObjectId id;
    @NotBlank(message = "Project name is required")
    private String projectName;
    @NotBlank(message = "Project identifier is required")
    @Size(min=4, max = 5, message = "Please use 4 to 5 characters")
    @Indexed(unique = true)
    private String projectIdentifier;
    @NotBlank(message = "Project description is required")
    private String description;
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    private LocalDateTime startDate;
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    private LocalDateTime endDate;

    private Backlog backlog;

//    @CreatedDate
//    private DateTime createAt;
//    @LastModifiedDate
//    private DateTime modifiedAt;

}
