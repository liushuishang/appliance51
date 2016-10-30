package appliance51.admin.dialects.yay;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

/**
 * 表单中的文本控件
 * Created by yuananyun on 2016/10/26.
 */
public class YayTextInputCtrlProcessor extends YayBaseCtrlProcessor {

    private static final String TAG_NAME = "text";
    private static final String CREATE_TEMPLATE =
            "<div class=\"layui-form-item\">\n" +
                    "            <label class=\"layui-form-label\">%s</label>\n" +
                    "            <div class=\"layui-input-block\">\n" +
                    "                <input id=\"%s\" name=\"%s\" class=\"layui-input\" type=\"text\" lay-verify=\"%s\"\n" +
                    "                       placeholder=\"请输入%s\"/>\n" +
                    "            </div>\n" +
                    " </div>";
    private static final String EDIT_TEMPLATE =
            "<div class=\"layui-form-item\">\n" +
                    "            <label class=\"layui-form-label\">%s</label>\n" +
                    "            <div class=\"layui-input-block\">\n" +
                    "                <input id=\"%s\" name=\"%s\" class=\"layui-input\" type=\"text\" lay-verify=\"%s\"\n" +
                    "                       placeholder=\"请输入%s\" value=\"%s\" />\n" +
                    "            </div>\n" +
                    " </div>";

    private static final String REVIEW_TEMPLATE =
            "<div class=\"layui-form-item\">\n" +
                    "            <label class=\"layui-form-label\">%s</label>\n" +
                    "            <div class=\"layui-input-block\">\n" +
                    "                <input class=\"layui-input\" type=\"text\" readonly=\"readonly\" value=\"%s\" />\n" +
                    "            </div>\n" +
                    " </div>";


    public YayTextInputCtrlProcessor(String dialectPrefix) {
        super(dialectPrefix, TAG_NAME);

    }

    protected void doProcess(ITemplateContext context,
                             IProcessableElementTag tag,
                             IElementTagStructureHandler structureHandler, PageActionStatus actionStatus) {

//        final ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);

        String idAttributeValue = tag.getAttributeValue("id");
        String labelAttributeValue = tag.getAttributeValue("data-label");
        String verifyAtributeValue = tag.getAttributeValue("data-verify");

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

        structureHandler.replaceWith(dom, true);
//        structureHandler.replaceWith(HtmlEscape.escapeHtml5(dom), true);
    }


}
