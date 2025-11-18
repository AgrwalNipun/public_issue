package com.nip.public_issue.dtos;


import com.nip.public_issue.models.Category;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CreateDepartmentDTO {

private String  name;
private Category category;      

}
