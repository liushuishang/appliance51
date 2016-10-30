package appliance51.admin.dialects.yay;

import org.thymeleaf.processor.element.AbstractAttributeModelProcessor;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Created by yuananyun on 2016/10/29.
 */
public abstract  class YayBaseAttributeProcessor extends AbstractAttributeTagProcessor {
    private static final int PRECEDENCE = 10000;


    public YayBaseAttributeProcessor(final String dialectPrefix,String attributeName) {
        super(
                TemplateMode.HTML, // This processor will apply only to HTML mode
                dialectPrefix,     // Prefix to be applied to name for matching
                null,              // No tag name: match any tag name
                false,             // No prefix to be applied to tag name
                attributeName,         // Name of the attribute that will be matched
                true,              // Apply dialect prefix to attribute name
                PRECEDENCE,        // Precedence (inside dialect's own precedence)
                true);             // Remove the matched attribute afterwards
    }


}
