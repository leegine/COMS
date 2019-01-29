head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeStopInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投銘柄別売買停止入力画面レスポンス(WEB3AdminRuitoTradeStopInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import java.util.Date;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (累投銘柄別売買停止入力画面レスポンス)<BR>
 * 累投銘柄別売買停止入力画面レスポンスクラス
 */
public class WEB3AdminRuitoTradeStopInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_ruito_trade_stop_input";
    
    /**
     * SerialVersionUID
     */
    public final static long serialVersionUID = 200408031539L;
    
    /**
     * (累投銘柄別売買停止入力画面レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922CB302AF
     */
    public WEB3AdminRuitoTradeStopInputResponse()
    {
    }
    
    /**
     * (銘柄情報一覧)<BR>
     * 銘柄情報一覧
     */
    public WEB3AdminRuitoTradeInfo[] productInfoList;
    
    /**
     * (現在日からの発注日)<BR>
     * 現在日からの発注日
     */
    public Date curBizDate;
    
    /**
     * (現在日からの翌営業日)<BR>
     * 現在日からの翌営業日
     */
    public Date nextBizDate;
    
    /**
     * (オペレーション日付)<BR>
     * オペレーション日付<BR>
     * 入力画面取得時の現在日付
     */
    public Date operationDate;
    
    /**
     * (累投銘柄別売買停止入力画面レスポンス)<BR>
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminRuitoTradeStopInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}

@
