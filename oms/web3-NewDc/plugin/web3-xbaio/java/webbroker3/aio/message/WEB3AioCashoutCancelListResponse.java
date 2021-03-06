head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金取消一覧レスポンス(WEB3AioCashoutCancelListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成     
                   2004/10/22 黄建 (中訊) レビュー                  
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (出金取消一覧レスポンス)<BR>
 * 出金取消一覧レスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashoutCancelListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_cancel_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L;    
    /**
     * (出金取消一覧)<BR>
     * 出金取消対象となる注文のリスト
     */
    public WEB3AioCashoutCancelUnit[] cashoutCancelUnits;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 4158EB6001A8
     */
    public WEB3AioCashoutCancelListResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashoutCancelListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
