head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.30.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SwapTradeTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3SwapTradeTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/27 森川(SRA)　@新規作成
*/
package webbroker3.common.define;

/**
 * 売買区分　@定数定義インタフェイス
 * (現引現渡入力通知キューテーブル用)
 *                
 * @@author SRA森川
 * @@version 1.0
 */
public interface WEB3SwapTradeTypeDef
{
    /**
     * 1 : 現渡
     */
    public static final String DELIVERY = "1";

    /**
     * 2 : 現引
     */
    public static final String RECIPT = "2";

}
@
