package appliance51.common.utils;

import appliance51.common.exception.EngineException;
import appliance51.common.exception.EngineExceptionHelper;
import appliance51.common.exception.ExcepFactor;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * Created by yuananyun on 2016/9/21.
 */
public class ExceptionAssert {

    public static void isNull(Object object, ExcepFactor excepFactor) {
        if (object != null) {
            throw EngineExceptionHelper.localException(excepFactor);
        }
    }

    public static void notNull(Object object, ExcepFactor excepFactor) {
        if (object == null) {
            throw EngineExceptionHelper.localException(excepFactor);
        }
    }

    public static void notBlank(String str, ExcepFactor excepFactor) {
        if (StringUtils.isBlank(str))
            throw EngineExceptionHelper.localException(excepFactor);
    }

    public static void notEmpty(Collection<?> elements, ExcepFactor excepFactor) {
        if (elements == null || elements.size() == 0)
            throw EngineExceptionHelper.localException(excepFactor);
    }
}
