package appliance51.rest.service;

import appliance51.dao.domain.SystemDictionary;
import appliance51.dao.repositories.SystemDictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by  yuananyun on 2016/12/7.
 */
@Service
public class SystemDictionaryService {
    public static String KEY_SPLASH_PROPRIETOR = "proprietor_splash";
    public static String KEY_CHARGE_ILLUSTRATE = "charge_illustrte";

    @Autowired
    private SystemDictionaryRepository dictionaryRepository;

    /**
     * 获取系统设定的业主端闪屏图片
     * @return
     */
    public String getProprietorSplash()
    {
        SystemDictionary entry = dictionaryRepository.findOneByItemKey(KEY_SPLASH_PROPRIETOR);
        if(entry!=null) return entry.getItemValue();
        return "暂无图片";
    }

    /**
     * 获取服务收费说明
     * @return
     */
    public String getChargeIllustrate() {
        SystemDictionary entry = dictionaryRepository.findOneByItemKey(KEY_CHARGE_ILLUSTRATE);
        if(entry!=null) return entry.getItemValue();
        return "暂无说明";
    }
}
