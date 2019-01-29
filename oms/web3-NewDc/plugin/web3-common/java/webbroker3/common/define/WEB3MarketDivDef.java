head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarketDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3MarketDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 jia-yuanchun(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 市場区分　@定数定義インタフェイス
 *                                                                      
 * @@author jia-yuanchun
 * @@version 1.0
 */
public interface WEB3MarketDivDef
{
    /**
     * 1:東証
     */
    public static final String TOKYO_STOCK_EXCHANGE = "1";

    /**
     * 2:大証
     */
    public static final String OSAKA_SECURITIES_EXCHANGE = "2";

    /**
     * 3:名証
     */
    public static final String NAGOYA_STOCK_EXCHANGE = "3";

    /**
     * 4:札証
     */
    public static final String SAPPORO_STOCK_EXCHANGE = "4";

    /**
     * 5:NNM
     */
    public static final String NNM_STOCK_EXCHANGE = "5";

    /**
     * 6:JASDAQ
     */
    public static final String JASDAQ_STOCK_EXCHANGE = "6";

    /**
     * 9:福証
     */
    public static final String FUKUOKA_STOCK_EXCHANGE = "9";
}
@
