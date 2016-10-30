package appliance51.admin.dialects.yay;

import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

/**
 * Created by yuananyun on 2016/10/29.
 */
public class YayTextAreaCtrlProcessor extends YayBaseCtrlProcessor {
    private static final String TAG_NAME = "textarea";
    private static final String CREATE_TEMPLATE =
            "<div class=\"layui-form-item layui-form-text\">\n" +
                    "            <label class=\"layui-form-label\">%s</label>\n" +
                    "            <div class=\"layui-input-block\">\n" +
                    "                <textarea class=\"layui-textarea\" id=\"%s\" name=\"%s\"  lay-verify=\"%s\"\n" +
                    "                       placeholder=\"请输入%s\"></textarea>\n" +
                    "            </div>\n" +
                    " </div>";
    private static final String EDIT_TEMPLATE =
            "<div class=\"layui-form-item layui-form-text\">\n" +
                    "            <label class=\"layui-form-label\">%s</label>\n" +
                    "            <div class=\"layui-input-block\">\n" +
                    "                <textarea class=\"layui-textarea\" id=\"%s\" name=\"%s\"  lay-verify=\"%s\"\n" +
                    "                       placeholder=\"请输入%s\" >%s</textarea>\n" +
                    "            </div>\n" +
                    " </div>";

    private static final String REVIEW_TEMPLATE =
            "<div class=\"layui-form-item layui-form-text\">\n" +
                    "            <label class=\"layui-form-label\">%s</label>\n" +
                    "            <div class=\"layui-input-block\">\n" +
                    "                <textarea class=\"layui-textarea\">%s</textarea>\n" +
                    "            </div>\n" +
                    " </div>";

    public YayTextAreaCtrlProcessor(String dialectPrefix) {
        super(dialectPrefix, TAG_NAME);
    }

    @Override
    void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler structureHandler, PageActionStatus actionStatus) {


        String idAttributeValue = tag.getAttributeValue("id");
        String labelAttributeValue = tag.getAttributeValue("data-label");
        String verifyAtributeValue = tag.getAttributeValue("data-verify");
        if (StringUtils.isBlank(verifyAtributeValue)) verifyAtributeValue = "";

        String ctlValue = "";
        if (actionStatus != PageActionStatus.CREATE)
            ctlValue = String.valueOf(YayDialectUtil.getFieldValue(context, idAttributeValue));

        String dom = "";
        if (actionStatus == PageActionStatus.CREATE) {
            dom = String.format(CREATE_TEMPLATE, labelAttributeValue, idAttributeValue, idAttributeValue,
                    verifyAtributeValue, labelAttributeValue);
        } else if (actionStatus == PageActionStatus.EDIT) {
            dom = String.format(EDIT_TEMPLATE, labelAttributeValue, idAttributeValue, idAttributeValue,
                    verifyAtributeValue, verifyAtributeValue, ctlValue);
        } else if (actionStatus == PageActionStatus.REVIEW) {
            dom = String.format(REVIEW_TEMPLATE, labelAttributeValue, ctlValue);
        }

        structureHandler.replaceWith(dom, false);
    }
}
