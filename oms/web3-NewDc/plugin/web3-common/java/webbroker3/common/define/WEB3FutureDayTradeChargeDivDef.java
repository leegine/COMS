head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.05.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FutureDayTradeChargeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3FutureDayTradeChargeDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/08 鄒 政(sinocom)　@新規作成
*/

package webbroker3.common.define;

/**
 * 先物日計り手数料徴収区分　@定数定義インタフェイス
 *
 * @@author zou-zheng
 * @@version 1.0
 */
public interface WEB3FutureDayTradeChargeDivDef
{
    
    /**
     * 0 : 徴収しない 
     */
    public static final String NOCHARGE = "0";

    /**
     * 1 : 徴収する 
     */
    public static final String CHARGE = "1";

}
@
