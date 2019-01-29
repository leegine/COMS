head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GftMessageOperationDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3GftMessageOperationDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 屈陽 (中訊) 新規作成
Revesion History : 2006/04/27 凌建平 (中訊)【入出金】仕様変更・ＤＢレイアウトNo.091
Revesion History : 2006/07/12 栄イ (中訊)【入出金】仕様変更・ＤＢレイアウトNo.100
*/
package webbroker3.common.define;

/**
 * GFT電文処理区分　@定数定義インタフェイス
 *                                                                     
 * @@author 屈陽
 * @@version 1.0
 */
public interface WEB3GftMessageOperationDef
{
    /**
     * 01：口座開設　@　@
     */
    public static final String ACCOUNT_OPEN = "01";
    
    /**
     * 04：出金
     */
    public static final String CASH_OUT = "04";
    
    /**
     * 02：入金
     */
    public static final String CASH_IN = "02";

    /**
     * 04：出金（FX）
     */
    public static final String CASH_OUT_FX = "04";

    /**
     * 02：入金（FX）
     */
    public static final String CASH_IN_FX = "02";

    /**
     * 05：入金（先OP）
     */
    public static final String CASH_IN_FUOP = "05";

    /**
     * 06：出金（先OP）
     */
    public static final String CASH_OUT_FUOP = "06";
}
@
