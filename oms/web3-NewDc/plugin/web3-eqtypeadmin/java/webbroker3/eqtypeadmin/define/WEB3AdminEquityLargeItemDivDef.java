head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityLargeItemDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 大項目区分 定数定義インタフェイス(WEB3AdminEquityLargeItemDivDef.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.define;

/**
 * 大項目区分 定数定義インタフェイス
 * <BR>
 * @@author Babu Prasad
 * @@version 1.0
 */
public interface WEB3AdminEquityLargeItemDivDef
{
    /**
     * 取引規制
     */
    public final static String TRADING_REGULATION = "0";

    /**
     * 基本情報
     */
    public final static String BASIC_INFO = "1";

    /**
     * 信用銘柄情報
     */
    public final static String MARGIN_PRODUCT_INFO = "2";

    /**
     * 委託保証金率
     */
    public final static String DEPOSIT_RATE = "3";

    /**
     * 代用有価証券情報
     */
    public final static String SUBSTITUTE_SECURITY_INFO = "4";

    /**
     * 値段情報
     */
    public final static String PRICE_INFO = "5";
}
@
