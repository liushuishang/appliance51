package appliance51.admin.dialects.yay;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.annotation.ModelFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.model.*;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给form表单设定attribute，只需要在form表单上添加 yay:from 即可
 * Created by yuananyun on 2016/10/29.
 */
public class YayFormAttributeProcessor extends YayBaseAttributeProcessor {

    private static final String ATTR_NAME = "form";

    public YayFormAttributeProcessor(String dialectPrefix) {
        super(dialectPrefix, ATTR_NAME);
    }


    private static boolean checkPositionInMarkup(final ITemplateContext context) {
        final List<IProcessableElementTag> elementStack = context.getElementStack();
        if (elementStack.size() < 2) {
            return false;
        }

        final IProcessableElementTag container = elementStack.get(elementStack.size() - 1);
        if (!(container instanceof IOpenElementTag) || container.getAttribute("yay:form") == null) {
            return false;
        }

        return true;

    }

    @Override
    protected void doProcess(ITemplateContext context,
                             IProcessableElementTag tag,
                             AttributeName attributeName,
                             String attributeValue,
                             IElementTagStructureHandler structureHandler) {
        if (!checkPositionInMarkup(context)) {
            throw new TemplateProcessingException(
                    "The " + ATTR_NAME + " attribute can only be used inside a form element");
        }

        String id = "";
        IAttribute idAttribute = tag.getAttribute("id");
        if (idAttribute != null)
            id = idAttribute.getValue();

        String formAction = "";
        IAttribute actionAttribute = tag.getAttribute("action");
        if (actionAttribute != null) formAction = actionAttribute.getValue();
        PageActionStatus actionStatus = YayDialectUtil.getPageActionStatus(context);
        if (StringUtils.isBlank(formAction) && (actionStatus == PageActionStatus.CREATE || actionStatus == PageActionStatus.EDIT)) {
            String entityName = String.valueOf(context.getVariable("entityName"));
            formAction = String.format("%s/%s/save", ((WebEngineContext) context).getRequest().getContextPath(), entityName);
        }

        String enctype = "application/x-www-form-urlencoded";
        IAttribute enctypeAttribute = tag.getAttribute("enctype");
        if (enctypeAttribute != null)
            enctype = enctypeAttribute.getValue();


        String method = "post";
        IAttribute methodAttribute = tag.getAttribute("method");
        if (methodAttribute != null)
            method = methodAttribute.getValue();


        structureHandler.setAttribute("id", id);
        structureHandler.setAttribute("class", "layui-form layui-form-pane");
        structureHandler.setAttribute("method", method);
        structureHandler.setAttribute("enctype", enctype);
        structureHandler.setAttribute("action", formAction);

        if (actionStatus == PageActionStatus.EDIT) {
            //添加一个隐藏域
            String hiddenField = attributeValue;
            final IModelFactory modelFactory = context.getModelFactory();
            final IModel model = modelFactory.createModel();
            Map<String, String> attributeMap = new HashMap<>();
            attributeMap.put("type", "hidden");
            attributeMap.put("id", hiddenField);
            attributeMap.put("name", hiddenField);
            attributeMap.put("value", String.valueOf(YayDialectUtil.getFieldValue(context, hiddenField)));

            model.add(modelFactory.createOpenElementTag("input", attributeMap, AttributeValueQuotes.DOUBLE, false));
            model.add(modelFactory.createCloseElementTag("input"));

            structureHandler.insertImmediatelyAfter(model,false);
        }

    }
}
