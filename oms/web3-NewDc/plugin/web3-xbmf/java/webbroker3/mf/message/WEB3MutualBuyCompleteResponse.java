head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付注文完了レスポンスクラス(WEB3MutualBuyCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 周勇 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 投資信託買付注文完了レスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_buy_complete";
    
    /**
     * 更新時間
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * 識別番号
     */
    public String orderActionId;
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;
    
    /**
     * (投信買付注文完了レスポンス)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A883C701EC
     */
    public WEB3MutualBuyCompleteResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualBuyCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
