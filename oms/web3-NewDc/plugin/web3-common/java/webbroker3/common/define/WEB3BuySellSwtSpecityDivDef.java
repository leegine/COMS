head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BuySellSwtSpecityDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 指定方法@定数定義インタフェイス(WEB3BuySellSwtSpecityDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 張威(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 指定方法@定数定義インタフェイス
 *
 * @@author 張威
 * @@version 1.0
 */
public interface WEB3BuySellSwtSpecityDivDef
{

    /**
     * 0：選択指定　@　@　@　@  　@　@
     */
    public final static String SELECT_DESIGNATE = "0";

    /**
     * 3：金額指定
     */
    public final static String PRICE_DESIGNATE = "3";
    
    /**
     * 4：口数指定
     */
    public final static String QUANTITY_DESIGNATE = "4";

}
@
