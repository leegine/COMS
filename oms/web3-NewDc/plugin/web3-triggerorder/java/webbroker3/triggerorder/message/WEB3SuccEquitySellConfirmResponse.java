head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.45.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquitySellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式売付注文確認レスポンス(WEB3SuccEquitySellConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 鄭海良(中訊) 新規作成
                 : 2007/01/10 徐宏偉(中訊) モデル214
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3EquitySellConfirmResponse;


/**
 * (（連続）現物株式売付注文確認レスポンス)<BR>
 * （連続）現物株式売付注文確認レスポンス。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3SuccEquitySellConfirmResponse extends WEB3EquitySellConfirmResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equitySellConfirm";

    /**
     * (調整後単価)<BR>
     * 調整後単価。<BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 4348960501E4
     */
    public WEB3SuccEquitySellConfirmResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccEquitySellConfirmResponse(WEB3SuccEquitySellConfirmRequest l_request)
    {
        super(l_request);
    }     
}
@
