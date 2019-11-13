package com.example.pojo.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.example.pojo.DemoData;
import com.example.pojo.input.DemoDataInput;
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface DemoDataConverter {

        
    DemoDataConverter INSTANCE = Mappers.getMapper(DemoDataConverter.class);
        
    DemoDataInput po2vo(DemoData po);
    List<DemoDataInput> po2vo(List<DemoData> list);
    
}
