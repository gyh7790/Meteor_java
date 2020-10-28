package com.gyh.system.sys.dto;

import com.gyh.modules.test.dto.TestDto;

import java.util.List;

/**
 * @author gyh
 * @Date 2020/10/21 23:37
 */
public class UserDto {

    private String id;
    private String name;
    private TestDto dto;
    private List<TestDto> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestDto getDto() {
        return dto;
    }

    public void setDto(TestDto dto) {
        this.dto = dto;
    }

    public List<TestDto> getList() {
        return list;
    }

    public void setList(List<TestDto> list) {
        this.list = list;
    }
}
