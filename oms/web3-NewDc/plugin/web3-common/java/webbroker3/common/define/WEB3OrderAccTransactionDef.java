head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderAccTransactionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3OrderAccTransactionDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
Revesion History : 2007/09/26 栄イ(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo541
Revesion History : 2008/07/31 趙林鵬(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo638
*/
package webbroker3.common.define;

/**
 * 注文受付ステイタステーブル.注文受付トランザクション　@定数定義インタフェイス<BR>                                                             
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3OrderAccTransactionDef
{
    /**
     * 00 : DEFAULT（すべて）
     */
    public static final String DEFAULT = "00";

    /**
     * 01 : 買付 <BR>    
     *   （新規建買）<BR>
     *    (サービス利用申込)<BR>
     *    (為替保証金口座開設)<BR>
     *    (外国株式口座開設)<BR>
     *    (シングルサインオン岩井為替証拠金)<BR>
     *    (証券担保ローン口座開設)<BR>
     */
    public static final String OPEN_LONG_MARGIN = "01";

    /**
     * 02 : 売付（新規建売）
     */
    public static final String OPEN_SHORT_MARGIN = "02";

    /**
     * 03 : 返済
     */
    public static final String CLOSE_MARGIN = "03";

    /**
     * 04 : 現引・現渡
     */
    public static final String SWAP_MARGIN = "04";

    /**
     * 05 : 訂正
     */
    public static final String CHANGE = "05";

    /**
     * 06 : 取消
     */
    public static final String CANCEL = "06";

    /**
     * 07 : 照会
     */
    public static final String REFERENCE = "07";

    /**
     * 08 : 出金
     */
    public static final String PAYMENT = "08";

    /**
     * 09 : 振替
     */
    public static final String TRANSFER = "09";

    /**
     * 10 : 入金
     */
    public static final String CASH_IN = "10";
    
    /**
     * 11 : 募集
     */
    public static final String RECRUIT = "11";

    /**
     * 12：定時定額買付
     */
    public static final String FIXED_BUYING = "12";
}
@
