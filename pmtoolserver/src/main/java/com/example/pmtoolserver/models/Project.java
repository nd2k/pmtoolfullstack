package com.example.pmtoolserver.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "projects")
@Data
@NoArgsConstructor
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
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private String startDate;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private String endDate;
    @CreatedDate
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private DateTime createAt;
    @LastModifiedDate
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private DateTime modifiedAt;

}
