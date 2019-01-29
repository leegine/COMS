head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquityBuyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続）現物株式買付注文確認レスポンス(WEB3SuccEquityBuyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 鄭海良(中訊) 新規作成
                 : 2007/01/10 徐宏偉(中訊) モデル214
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3EquityBuyConfirmResponse;


/**
 * (（連続）現物株式買付注文確認レスポンス)<BR>
 * （連続）現物株式買付注文確認レスポンス。<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3SuccEquityBuyConfirmResponse extends WEB3EquityBuyConfirmResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equityBuyConfirm";

    /**
     * (調整後単価)<BR>
     * 調整後単価。<BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 43489606004E
     */
    public WEB3SuccEquityBuyConfirmResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccEquityBuyConfirmResponse(WEB3SuccEquityBuyConfirmRequest l_request)
    {
        super(l_request);
    }     
}
@
