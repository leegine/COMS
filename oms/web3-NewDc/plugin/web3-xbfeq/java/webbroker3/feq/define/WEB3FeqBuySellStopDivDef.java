head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuySellStopDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 売買停止区分 定数定義インタフェイス(WEB3FeqBuySellStopDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/27 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 売買停止区分 定義インタフェイス
 * (管理者外国株式銘柄情報更新明細入力レスポンス用) 
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqBuySellStopDivDef
{
    /**
     * 0：可能
     */
    public static final String CAN = "0";
    
    /**
     * 1：停止（取引所規制）
     */
    public static final String STOP_MARKET = "1";
    /**
     * 2：停止（当社規制）
     */
    public static final String STOP_COMPANY = "2";
}
@
