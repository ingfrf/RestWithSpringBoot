package com.enmivida.rest.utils.mapper;

import com.enmivida.rest.data.model.Person;
import com.enmivida.rest.data.vo.PersonVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper {
    Person personVOToPerson(PersonVO personVO);
    PersonVO personToPersonVO(Person person);
    List<PersonVO> personListToPersonVOList(List<Person> personList);
}
