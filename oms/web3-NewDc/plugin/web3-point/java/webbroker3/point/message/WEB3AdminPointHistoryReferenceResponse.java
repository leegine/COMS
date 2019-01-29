head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointHistoryReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント履歴照会レスポンス(WEB3AdminPointHistoryReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ポイント履歴照会レスポンス)<BR>
 * ポイント履歴照会レスポンスクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointHistoryReferenceResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_historyReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290088L;
    
    /**
     * (利用可能ポイント)<BR>
     * 該当顧客の利用可能ポイント<BR>
     */
    public String availablePoint;
    
    /**
     * (ポイント履歴一覧)<BR>
     * ポイント履歴明細の配列<BR>
     */
    public WEB3AdminPointHistoryDetail[] pointHistoryList;
    
    /**
     * @@roseuid 41D1254E0119
     */
    public WEB3AdminPointHistoryReferenceResponse() 
    {
     
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminPointHistoryReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
