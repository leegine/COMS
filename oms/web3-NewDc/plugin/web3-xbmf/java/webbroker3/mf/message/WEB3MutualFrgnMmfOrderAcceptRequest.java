head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgnMmfOrderAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨MMF注文受付リクエスト(WEB3MutualFrgnMmfOrderAcceptRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 張騰宇 (中訊) 新規作成 (モデル536)
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (外貨MMF注文受付リクエスト)
 * 外貨MMF注文受付リクエストクラス
 * 
 * @@author 張騰宇(中訊)
 * @@version 1.0
 */
public class WEB3MutualFrgnMmfOrderAcceptRequest extends WEB3BackRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_frgn_mmf_order_accept";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200702061500L;

    /**
     * (外貨MMF注文受付リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 45B46BCA01A7
     */
    public WEB3MutualFrgnMmfOrderAcceptRequest() 
    {

    }

    /**
     * (createレスポンス)<BR>
     * （createResponseの実装）<BR>
     * <BR>
     * 外貨MMF注文受付レスポンスオブジェクトを生成して返す。 <BR>
     * @@return WEB3BackResponse
     * @@roseuid 45B46BE603BA
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3MutualFrgnMmfOrderAcceptResponse(this);
    }
}
@
