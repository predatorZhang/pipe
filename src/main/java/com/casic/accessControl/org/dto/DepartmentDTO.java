package com.casic.accessControl.org.dto;

import com.casic.accessControl.org.domain.Department;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/6/16.
 */
public class DepartmentDTO
{
    private Long id;

    private String name;

    private String code;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public DepartmentDTO()
    {
    }

    public static DepartmentDTO ConvertDTO(Department department)
    {
        if(null!=department){
            DepartmentDTO dto = new DepartmentDTO();
            dto.setCode(department.getCode());
            dto.setId(department.getId());
            dto.setName(department.getName());
            return dto;
        }
        return new DepartmentDTO();
    }

    public static List<DepartmentDTO> ConvertDTOs(List<Department> departments)
    {
        List<DepartmentDTO> dtos = new ArrayList<DepartmentDTO>();
        for (Department department : departments)
        {
            dtos.add(ConvertDTO(department));
        }
        return dtos;
    }
}
