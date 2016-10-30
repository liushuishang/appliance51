package appliance51.admin.dialects.yay;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yuananyun on 2016/10/26.
 */
public class YayDialect extends AbstractProcessorDialect {
    private static final String DIALECT_NAME = "YayDialect";
    private static final String PREFIX = "yay";
    public static final int PROCESSOR_PRECEDENCE = 1001;

    public YayDialect() {
        super(DIALECT_NAME, PREFIX, PROCESSOR_PRECEDENCE);
    }

    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new YayFormAttributeProcessor(dialectPrefix));
        processors.add(new YaySelectCtrlProcessor(dialectPrefix));
        processors.add(new YayTextInputCtrlProcessor(dialectPrefix));
        processors.add(new YayTextAreaCtrlProcessor(dialectPrefix));
        processors.add(new YaySubmitBtnCtrlProcessor(dialectPrefix));

        // This will remove the xmlns:yay attributes we might add for IDE validation
        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));

        return processors;

    }
}
