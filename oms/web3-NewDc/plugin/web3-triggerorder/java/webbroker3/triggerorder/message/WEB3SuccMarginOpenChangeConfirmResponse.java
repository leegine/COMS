head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.44.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginOpenChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引注文訂正新規建確認レスポンスクラス(WEB3SuccMarginOpenChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭徳懇(中訊) 新規作成
                 : 2007/01/10 徐宏偉(中訊) モデル214
*/
package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3MarginOpenMarginChangeConfirmResponse;

/**
 * (（連続）信用取引注文訂正新規建確認レスポンスクラス)<BR>
 * （連続）信用取引注文訂正新規建確認レスポンスクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3SuccMarginOpenChangeConfirmResponse extends WEB3MarginOpenMarginChangeConfirmResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginOpenChangeConfirm";

    /**
     * (調整後単価)<BR>
     * 調整後単価。<BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 4369CC3E02DE
     */
    public WEB3SuccMarginOpenChangeConfirmResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccMarginOpenChangeConfirmResponse(WEB3SuccMarginOpenChangeConfirmRequest l_request)
    {
        super(l_request);
    }     
}
@
