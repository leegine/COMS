head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginTradingDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3MarginTradingDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 信用取引区分　@定数定義インタフェイス
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3MarginTradingDivDef
{
    /**
     * 0 ： DEFAULT（信用取引以外）
     */
    public static final String DEFAULT = "0";

    /**
     * 1 ： 制度信用
     */
    public static final String MARKET_MARGIN = "1";

    /**
     * 2 ： 一般信用
     */
    public static final String INSTITUTION_MARGIN = "2";
    
    /**
     * 3：制度／一般信用(両方)
     */
    public static final String MARKET_MARGIN_OR_INSTITUTION_MARGIN = "3";

}
@
