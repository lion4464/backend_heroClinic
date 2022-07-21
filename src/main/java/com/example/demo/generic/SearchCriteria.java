package com.example.demo.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchCriteria {
    private String key;
    @Pattern(regexp = "^(^(!=)?|^(<=)?|^(>=)?|^(=)?|^(:)?|^(<)?|^(>)?)$")
    private String operation;
    private Object value;

}
