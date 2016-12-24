package appliance51.common.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by  yuananyun on 2016/12/21.
 */
public class ChangShaAdCodes {
    private static Map<String, String> codeAndNameMap = new HashMap<>();

    static {
        codeAndNameMap.put("430102", "芙蓉区");
        codeAndNameMap.put("430103", "天心区");
        codeAndNameMap.put("430104", "岳麓区");
        codeAndNameMap.put("430105", "开福区");
        codeAndNameMap.put("430111", "雨花区");
        codeAndNameMap.put("430121", "长沙县");
        codeAndNameMap.put("430122", "望城县");
        codeAndNameMap.put("430124", "宁乡县");
        codeAndNameMap.put("430181", "浏阳市");
    }

}