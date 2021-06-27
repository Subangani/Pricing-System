package com.example.pricingservice.dao;

import com.example.pricingservice.model.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl {

    NamedParameterJdbcTemplate template;

    public ProductRepositoryImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }



//
//    @Override
//    public Product findById(Integer id) {
//        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
//
//        CriteriaQuery<Product> productCriteriaQuery = criteriaBuilder.createQuery(Product.class);
//        Root<Product> productRoot = productCriteriaQuery.from(Product.class);
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(criteriaBuilder.equal(productRoot.get("id"), id));
//
//        List<Product> resultList = getSession().createQuery(productCriteriaQuery).getResultList();
//        if (!resultList.isEmpty()){
//            return resultList.get(0);
//        }
//        return null;
//    }
}
