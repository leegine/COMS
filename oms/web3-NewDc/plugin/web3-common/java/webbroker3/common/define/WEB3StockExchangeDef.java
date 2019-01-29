head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.58.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3StockExchangeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3StockExchangeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/02 jia-yuanchun(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 取引市場　@定数定義インタフェイス
 *                                                                      
 * @@author jia-yuanchun
 * @@version 1.0
 */
public interface WEB3StockExchangeDef
{
    /**
     * 0:入出庫
     */
    public static final String STORAGE = "0";

    /**
     * 1:東京(JASDAQ含)
     */
    public static final String TOKYO = "1";

    /**
     * 2:大阪
     */
    public static final String OSAKA = "2";

    /**
     * 3:名古屋
     */
    public static final String NAGOYA = "3";

    /**
     * 5:NNM
     */
    public static final String NNM = "5";
}
@
