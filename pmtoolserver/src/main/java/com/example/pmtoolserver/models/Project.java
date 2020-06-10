package com.example.pmtoolserver.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
@Data
@NoArgsConstructor
public class Project {

    @Id
    private ObjectId id;
    private String projectName;
    private String projectIdentifier;
    private String description;
    private String startDate;
    private String endDate;
    @CreatedDate
    private DateTime createAt;
    @LastModifiedDate
    private DateTime modifiedAt;

}
