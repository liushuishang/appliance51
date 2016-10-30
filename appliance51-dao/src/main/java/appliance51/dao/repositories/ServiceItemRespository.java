package appliance51.dao.repositories;

import appliance51.dao.domain.CityRegion;
import appliance51.dao.domain.ServiceCategory;
import appliance51.dao.domain.ServiceItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuananyun on 2016/9/11.
 */
@Repository
public interface ServiceItemRespository extends PagingAndSortingRepository<ServiceItem, String> {


    Page<ServiceItem> findAll( Pageable pageable);


//https://www.tianmaying.com/tutorial/spring-jpa-page-sort
//    @Override
//    public List<Course> findByField(Map<String, ?> params) {
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<XXX> query = builder.createQuery(XXX.class);
//        Root<Course> root = query.from(XXX.class);
//        Predicate[] predicates = params.entrySet()
//                .stream()
//                .filter(p -> p.getValue() != null)
//                .map(p -> builder.equal(root.get(p.getKey()),
//                        isBoolean(p.getValue()) ? booleanValue(p.getValue()) : p.getValue()))
//                .toArray(Predicate[]::new);
//        query.where(builder.and(predicates));
//        return em.createQuery(query.select(root)).getResultList();
//    }
}
