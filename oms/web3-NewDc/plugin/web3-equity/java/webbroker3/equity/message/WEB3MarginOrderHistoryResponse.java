head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文履歴照会レスポンスクラス(WEB3MarginOrderHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 李松峰 (中訊) 新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引注文履歴照会レスポンス）。<br>
 * <br>
 * 信用取引注文履歴照会レスポンスクラス
 * @@version 1.0
 */
public class WEB3MarginOrderHistoryResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "margin_orderHistory";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (注文履歴一覧)<BR>
     * 信用取引注文約定履歴明細<BR>
     */
    public WEB3MarginChangeCancelHistoryGroup[] changeCancelHistoryGroups;
    
    /**
     * @@roseuid 414048CB00AF
     */
    public WEB3MarginOrderHistoryResponse() 
    {
     
    }

    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginOrderHistoryResponse(WEB3MarginOrderHistoryRequest l_request)
    {
        super(l_request);
    }
}
@
