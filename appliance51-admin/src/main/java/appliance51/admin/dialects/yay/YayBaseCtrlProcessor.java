package appliance51.admin.dialects.yay;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Created by yuananyun on 2016/10/26.
 */
public abstract class YayBaseCtrlProcessor extends AbstractElementTagProcessor {
    private static final int PRECEDENCE = 10000;

    public YayBaseCtrlProcessor(String dialectPrefix, String tagName) {
        super(TemplateMode.HTML,
                dialectPrefix,
                tagName,
                true,
                null,
                false,
                PRECEDENCE);
    }


    protected void doProcess(ITemplateContext context,
                             IProcessableElementTag tag,
                             IElementTagStructureHandler structureHandler) {
        PageActionStatus actionStatus = YayDialectUtil.getPageActionStatus(context);
        if (actionStatus == null) return;
        doProcess(context, tag, structureHandler, actionStatus);
    }


    abstract  void doProcess(ITemplateContext context,
                             IProcessableElementTag tag,
                             IElementTagStructureHandler structureHandler, PageActionStatus actionStatus);



}
