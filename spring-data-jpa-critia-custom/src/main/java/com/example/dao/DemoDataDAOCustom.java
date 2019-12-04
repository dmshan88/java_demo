package com.example.dao;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.example.pojo.vo.SumVO;

@NoRepositoryBean
public interface DemoDataDAOCustom {
    
    List<SumVO> calcSum();

}
