head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BookValueBuySellDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3BookValueBuySellDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/02 jia-yuanchun(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 売買　@定数定義インタフェイス
 *                                                                      
 * @@author jia-yuanchun
 * @@version 1.0
 */
public interface WEB3BookValueBuySellDivDef
{
    /**
     * 1:売or現渡or出庫
     */
    public static final String SELL_SPOT_STORAGE_OUT = "1";

    /**
     * 2:買or現引or入庫
     */
    public static final String BUY_RECEIPT_STORAGE_IN = "2";

}
@
