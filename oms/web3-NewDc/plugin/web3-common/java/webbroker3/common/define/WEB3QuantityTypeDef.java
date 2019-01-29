head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.48.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3QuantityTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3QuantityTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 jia-yuanchun(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 売買数量単位　@定数定義インタフェイス
 *                                                                      
 * @@author jia-yuanchun
 * @@version 1.0
 */
public interface WEB3QuantityTypeDef
{
    /**
     * 0:株
     */
    public static final String STOCK = "0";

    /**
     * 1:枚
     */
    public static final String PIECE = "1";

    /**
     * 2:口
     */
    public static final String ONE_SHARE = "2";

    /**
     * 3:万口
     */
    public static final String TEN_THOUSAND_SHARE = "3";

    /**
     * 4:千円
     */
    public static final String THOUSAND_SHARE = "4";

    /**
     * 5:g
     */
    public static final String G = "5";

}
@
