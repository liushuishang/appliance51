package appliance51.admin.service;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuananyun on 2016/10/29.
 */
@Service
public class CommonService {

    private static Logger logger = LoggerFactory.getLogger(CommonService.class);

    public Object getEntityInstance(String entityName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class entityClass = Class.forName("appliance51.dao.domain." + entityName.replace(entityName.substring(0, 1), entityName.substring(0, 1).toUpperCase()));
        return entityClass.newInstance();
    }

    public PagingAndSortingRepository getCrudRepository(HttpServletRequest request, String entityName) {
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
       return getCrudRepository(applicationContext, entityName);
    }

    public PagingAndSortingRepository getCrudRepository(ApplicationContext context, String entityName) {
        String repositoryName = entityName + "Respository";
        return context.getBean(repositoryName, PagingAndSortingRepository.class);
    }


    /**
     * 通过实体名称和字段名称返回该实体的所有数据
     *
     * @param entityName
     * @param fields
     * @return
     */
    public List<Map> fetchAllEntityWithFields(ApplicationContext context, String entityName, String[] fields) {
        try {
            List<Map> result = new ArrayList<>();
            CrudRepository repository = getCrudRepository(context, entityName);
            Iterable rowList = repository.findAll();
            for (Object row : rowList) {
                Map m = new HashMap();
                for (String field : fields) {
                    m.put(field, BeanUtils.getProperty(row, field));
                }
                result.add(m);
            }
            return result;
        } catch (Exception ex) {
            logger.error("【fetchAllEntityWithFields】" + ex.getMessage());
            return null;
        }
    }
}
