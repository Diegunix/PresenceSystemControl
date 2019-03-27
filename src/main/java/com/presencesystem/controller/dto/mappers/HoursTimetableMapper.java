package com.presencesystem.controller.dto.mappers;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.presencesystem.controller.dto.HoursTimetableDTO;
import com.presencesystem.service.bean.HoursTimetableBean;

@Mapper (uses = { UserMapper.class })
public interface HoursTimetableMapper {

    HoursTimetableMapper INSTANCE = Mappers.getMapper(HoursTimetableMapper.class);

    HoursTimetableDTO map(HoursTimetableBean entity);

    HoursTimetableBean map(HoursTimetableDTO dto);

    @IterableMapping(qualifiedByName = { "defaultMapper" })
    List<HoursTimetableDTO> map(List<HoursTimetableBean> content);
}