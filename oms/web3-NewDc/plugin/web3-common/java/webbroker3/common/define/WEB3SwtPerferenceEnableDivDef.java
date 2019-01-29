head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.42.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SwtPerferenceEnableDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3SwtPerferenceEnableDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 li-yingyuan(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 乗換優遇可能区分　@定数定義インタフェイス
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3SwtPerferenceEnableDivDef
{
    /**
     * 0:優遇不可
     */
    public static final String NOT_PREFERENCE = "0";

    /**
     * 1:内枠優遇
     */
    public static final String LIMITED_PREFERENCE = "1";
    
    /**
     * 2:半額優遇
     */
    public static final String HALF_PRICE_PREFERENCE = "2";
    
    /**
     * 9:全額優遇
     */
    public static final String TOTAL_PRICE_PREFERENCE = "9";

}
@
