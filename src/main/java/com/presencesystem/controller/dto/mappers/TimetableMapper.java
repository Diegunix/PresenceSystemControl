package com.presencesystem.controller.dto.mappers;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.presencesystem.commons.enums.ActionEnum;
import com.presencesystem.controller.dto.TimetableDTO;
import com.presencesystem.dao.domain.Timetable;

@Mapper(uses = { UserMapper.class },imports = { ActionEnum.class })
public interface TimetableMapper {

    TimetableMapper INSTANCE = Mappers.getMapper(TimetableMapper.class);

    @Mapping(target = "action", expression = "java( ActionEnum.get(entity.getAction()))")
    TimetableDTO map(Timetable entity);

    @IterableMapping(qualifiedByName = { "defaultMapper" })
    List<TimetableDTO> map(List<Timetable> content);
}