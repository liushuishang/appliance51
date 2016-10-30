package appliance51.admin.dialects.yay;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

/**
 * Created by yuananyun on 2016/10/29.
 */
public class YaySubmitBtnCtrlProcessor extends YayBaseCtrlProcessor {
    private static final String TAG_NAME = "formbutton";
    private static final String EDIT_TEMPLATE =
            "        <div class=\"layui-form-item\">\n" +
                    "            <div class=\"layui-input-block btn-submit\">\n" +
                    "                <button class=\"layui-btn\" lay-submit=\"\">立即提交</button>\n" +
                    "                <button type=\"reset\" class=\"layui-btn layui-btn-primary\">重置</button>\n" +
                    "            </div>\n" +
                    "        </div>";


    public YaySubmitBtnCtrlProcessor(String dialectPrefix) {
        super(dialectPrefix, TAG_NAME);
    }

    @Override
    void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler structureHandler, PageActionStatus actionStatus) {

        if (actionStatus == PageActionStatus.CREATE || actionStatus == PageActionStatus.EDIT) {
            structureHandler.replaceWith(EDIT_TEMPLATE, false);
        }
    }
}
