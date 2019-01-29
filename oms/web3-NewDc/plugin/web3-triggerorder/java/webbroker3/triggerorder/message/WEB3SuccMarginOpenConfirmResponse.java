head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.40.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginOpenConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引新規建注文確認レスポンスクラス(WEB3SuccMarginOpenConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭徳懇(中訊) 新規作成
                 : 2007/01/10 徐宏偉(中訊) モデル214
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3MarginOpenMarginConfirmResponse;

/**
 * (（連続）信用取引新規建注文確認レスポンスクラス)<BR>
 * （連続）信用取引新規建注文確認レスポンスクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3SuccMarginOpenConfirmResponse extends WEB3MarginOpenMarginConfirmResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginOpenConfirm";

    /**
     * (調整後単価)<BR>
     * 調整後単価。<BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 4369CBF702FD
     */
    public WEB3SuccMarginOpenConfirmResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccMarginOpenConfirmResponse(WEB3SuccMarginOpenConfirmRequest l_request)
    {
        super(l_request);
    }     
}
@
