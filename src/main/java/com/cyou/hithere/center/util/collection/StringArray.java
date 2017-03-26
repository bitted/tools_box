/**
 * @title: StringArray.java
 * @package com.cyou.hithere.center.util.collection
 * @description: 简理财
 * @copyright: Copyright (c) 2016
 * @company:北京简便快乐信息技术有限公司
 * 
 * @author litaijun
 * @date 2017年3月15日 下午4:17:35
 */
package com.cyou.hithere.center.util.collection;

/**
 * @author litaijun
 * @create 2017年3月15日 下午4:17:35
 */
public class StringArray {

  /**
   * @description （用一句话描述该方法的适用条件、执行流程、适用方法、注意事项 - 可选）
   * @param args
   * @return void
   * @throws @author
   *           litaijun
   * @create 2017年3月15日 下午4:17:35
   */
  public static void main(String[] args) {
    String[][] CREDIT_BANK_CODE = { { "GDB", "广发银行" }, { "HXB", "华夏银行" }, { "CCB", "建设银行" },
        { "BOC", "中国银行" }, { "CITIC", "中信银行" }, { "PSBC", "中国邮政储蓄银行" }, { "CEB", "中国光大银行" },
        { "ABC", "中国农业银行" }, { "SHB", "上海银行" }, { "ICBC", "工商银行" }, { "CIB", "兴业银行" },
        { "CMBC", "民生银行" }, { "BCOM", "交通银行" }, { "CMB", "招商银行" } };

    for (int i = 0; i < CREDIT_BANK_CODE.length; i++) {
      for (int j = 0; j < CREDIT_BANK_CODE[i].length; j++) {
        System.out.println(CREDIT_BANK_CODE[i] + "----" + CREDIT_BANK_CODE[j]);
      }
    }
  }

}
