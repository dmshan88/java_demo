package com.example.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.dao.DemoDataDAOCustom;
import com.example.pojo.DemoData;
import com.example.pojo.DemoData_;
import com.example.pojo.vo.SumVO;

public class DemoDataDAOImpl implements DemoDataDAOCustom {

    interface SumVOAlias {
//        final String ID = "id";
        final String USER_ID = "userId";
        final String TOTAL_SCORE = "score";
    }
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<SumVO> calcSum() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();
        Root<DemoData> root = query.from(DemoData.class);
        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(cb.gt(root.get(DemoData_.id), 0));

        query.where(predicateList.toArray(new Predicate[predicateList.size()]));
        query.groupBy(root.get(DemoData_.userId));
        query.multiselect(
            cb.sum(root.get(DemoData_.score)).alias(SumVOAlias.TOTAL_SCORE),
            root.get(DemoData_.userId).alias(SumVOAlias.USER_ID));
        TypedQuery<Tuple> q = em.createQuery(query);
        List<SumVO> list = new ArrayList<>();
        for (Tuple t : q.getResultList()) {
            list.add(new SumVO(t.get(SumVOAlias.USER_ID, Integer.class),
                t.get(SumVOAlias.TOTAL_SCORE, Long.class).intValue()));
        }
        return list;
    }

}
