package appliance51.admin.dialects.yay;

import appliance51.admin.service.CommonService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.context.SpringContextUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by yuananyun on 2016/10/29.
 */
public class YaySelectCtrlProcessor extends YayBaseCtrlProcessor {
    private static final String TAG_NAME = "select";

    private static String TEMPLATE =
            "        <div class=\"layui-form-item\">\n" +
                    "            <label class=\"layui-form-label\">%s</label>\n" +
                    "            <div class=\"layui-input-block\">\n" +
                    "                <select id=\"%s\" name=\"%s\" placeholder=\"请选择\" lay-verify=\"%s\" lay-filter=\"%s\">\n" +
                    "%s" +
                    "                </select>\n" +
                    "            </div>\n" +
                    "</div>";

    private static String REVIEW_TEMPLATE =
            "        <div class=\"layui-form-item\">\n" +
                    "            <label class=\"layui-form-label\">%s</label>\n" +
                    "            <div class=\"layui-input-block\">\n" +
                    "                <input class=\"layui-input\" type=\"text\" readonly=\"readonly\" value=\"%s\" />\n" +
                    "            </div>\n" +
                    "</div>";


    public YaySelectCtrlProcessor(String dialectPrefix) {
        super(dialectPrefix, TAG_NAME);
    }

    @Override
    void doProcess(ITemplateContext context,
                   IProcessableElementTag tag,
                   IElementTagStructureHandler structureHandler,
                   PageActionStatus actionStatus) {


        String idAttributeValue = tag.getAttributeValue("id");
        String labelAttributeValue = tag.getAttributeValue("data-label");
        String verifyAtributeValue = tag.getAttributeValue("lay-verify");
        String filterAtributeValue = tag.getAttributeValue("lay-filter");

        //关联的实体名
        String joinEntityNameValue = tag.getAttributeValue("data-join-entity");
        //选择控件显示的关联实体的显示的属性字段名
        String displayProperty = tag.getAttributeValue("data-field-display");
        if (StringUtils.isBlank(displayProperty)) displayProperty = "name";
        //选择控件显示的关联实体的显示的值字段名
        String valueProperty = tag.getAttributeValue("data-field-value");
        if (StringUtils.isBlank(valueProperty)) valueProperty = "id";

        List<Map> selectCtlData = null;
        if (!StringUtils.isBlank(joinEntityNameValue)) {
            ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
            CommonService commonService = appCtx.getBean("commonService", CommonService.class);
            selectCtlData = commonService.fetchAllEntityWithFields(appCtx,
                    joinEntityNameValue, new String[]{displayProperty, valueProperty});
        }

        String ctlValue = "";
        if (actionStatus != PageActionStatus.CREATE)
            ctlValue = String.valueOf(YayDialectUtil.getFieldValue(context,idAttributeValue));

        String dom = "";
        if (actionStatus == PageActionStatus.CREATE || actionStatus == PageActionStatus.EDIT) {
            StringBuilder optionBuilder = new StringBuilder();
            optionBuilder.append(String.format("<option >%s</option>\n", "请选择"+labelAttributeValue));
            if (selectCtlData != null && selectCtlData.size() > 0) {
                for (Map map : selectCtlData) {
                    String value = MapUtils.getString(map, valueProperty);
                    if (ctlValue != null && ctlValue.equals(value))
                        optionBuilder.append(String.format("<option value=\"%s\" selected=\"selected\">%s</option>\n", value, MapUtils.getString(map, displayProperty)));
                    else
                        optionBuilder.append(String.format("<option value=\"%s\">%s</option>\n", value, MapUtils.getString(map, displayProperty)));
                }
            }
            dom = String.format(TEMPLATE, labelAttributeValue, idAttributeValue,
                    idAttributeValue, verifyAtributeValue,
                    filterAtributeValue, optionBuilder.toString());
        } else {
            String selectedDisplay = "";
            if (selectCtlData != null && selectCtlData.size() > 0) {
                for (Map map : selectCtlData) {
                    String value = MapUtils.getString(map, valueProperty);
                    if (ctlValue != null && ctlValue.equals(value)) {
                        selectedDisplay = MapUtils.getString(map, displayProperty);
                        break;
                    }
                }
            }
            dom = String.format(REVIEW_TEMPLATE, labelAttributeValue, selectedDisplay);
        }
        structureHandler.replaceWith(dom, true);

    }
}
