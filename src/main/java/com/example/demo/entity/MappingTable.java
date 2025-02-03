package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mappingtable", schema = "mappingdb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MappingTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "source_schema_name", length = 20, nullable = false)
    private String sourceSchemaName;

    @Column(name = "source_table_name", length = 20, nullable = false)
    private String sourceTableName;

    @Column(name = "source_column_name", length = 20, nullable = false)
    private String sourceColumnName;

    @Column(name = "is_secure", nullable = false)
    private Boolean isSecure;

    @Column(name = "destination_schema_name", length = 20, nullable = false)
    private String destinationSchemaName;

    @Column(name = "destination_table_name", length = 20, nullable = false)
    private String destinationTableName;

    @Column(name = "destination_column_name", length = 20, nullable = false)
    private String destinationColumnName;

    @Column(name = "pattern", length = 20)
    private String pattern;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceSchemaName() {
        return sourceSchemaName;
    }

    public void setSourceSchemaName(String sourceSchemaName) {
        this.sourceSchemaName = sourceSchemaName;
    }

    public String getSourceTableName() {
        return sourceTableName;
    }

    public void setSourceTableName(String sourceTableName) {
        this.sourceTableName = sourceTableName;
    }

    public String getSourceColumnName() {
        return sourceColumnName;
    }

    public void setSourceColumnName(String sourceColumnName) {
        this.sourceColumnName = sourceColumnName;
    }

    public Boolean getSecure() {
        return isSecure;
    }

    public void setSecure(Boolean secure) {
        isSecure = secure;
    }

    public String getDestinationSchemaName() {
        return destinationSchemaName;
    }

    public void setDestinationSchemaName(String destinationSchemaName) {
        this.destinationSchemaName = destinationSchemaName;
    }

    public String getDestinationTableName() {
        return destinationTableName;
    }

    public void setDestinationTableName(String destinationTableName) {
        this.destinationTableName = destinationTableName;
    }

    public String getDestinationColumnName() {
        return destinationColumnName;
    }

    public void setDestinationColumnName(String destinationColumnName) {
        this.destinationColumnName = destinationColumnName;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}

