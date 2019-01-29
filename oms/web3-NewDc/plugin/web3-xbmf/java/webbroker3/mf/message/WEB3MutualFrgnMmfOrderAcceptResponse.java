head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgnMmfOrderAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨MMF注文受付レスポンス(WEB3MutualFrgnMmfOrderAcceptResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 張騰宇 (中訊) 新規作成 (モデル536)
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (外貨MMF注文受付レスポンス)<BR>
 * 外貨MMF注文受付レスポンスクラス<BR>
 * 
 * @@author 張騰宇(中訊)
 * @@version 1.0
 */
public class WEB3MutualFrgnMmfOrderAcceptResponse extends WEB3BackResponse
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
     * (外貨MMF注文受付レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 45B46D4B005F
     */
    public WEB3MutualFrgnMmfOrderAcceptResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualFrgnMmfOrderAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
