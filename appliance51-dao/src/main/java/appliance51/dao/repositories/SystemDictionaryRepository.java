package appliance51.dao.repositories;

import appliance51.dao.domain.SystemDictionary;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by  yuananyun on 2016/12/7.
 */
public interface SystemDictionaryRepository  extends PagingAndSortingRepository<SystemDictionary, String> {
    SystemDictionary findOneByItemKey(String key);
}
