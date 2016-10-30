package appliance51.admin.dialects.yay;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;

/**
 * Created by yuananyun on 2016/10/29.
 */
public class YayDialectUtil {

    /**
     * 解析页面操作状态
     *
     * @param context
     * @return
     */
    static  PageActionStatus getPageActionStatus(ITemplateContext context) {
        try {
            String action = parseExpression(context, String.format("${%s}", "action"));
            PageActionStatus actionStatus = PageActionStatus.valueOf(action.toUpperCase());
            return actionStatus;
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            return null;
        }
    }
    static String getFieldExpression(String fieldName) {
        return String.format("${entity.%s}", fieldName);
    }

    static  Object getFieldValue(ITemplateContext context,String fieldName){
        return parseExpression(context, getFieldExpression(fieldName));
    }


    static String parseExpression(ITemplateContext context, String exp) {
        try {
            IEngineConfiguration configuration = context.getConfiguration();
            IStandardExpressionParser parser =
                    StandardExpressions.getExpressionParser(configuration);
            IStandardExpression expression =
                    parser.parseExpression(context, exp);
            return String.valueOf(expression.execute(context));
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            return null;
        }
    }
}
