head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.23.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物取消完了レスポンスクラス(WEB3FuturesCancelCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 盧法@旭 (中訊) 新規作成
              001: 2004/08/05 王暁傑 (中訊) Review修正
Revesion History : 2008/03/12 張騰宇　@仕様変更モデル829
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (株価指数先物取消完了レスポンス)<BR>
 * 株価指数先物取消完了レスポンスクラス
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3FuturesCancelCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="futures_cancelComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407220958L;        
    /**
     * (更新時間)<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (識別番号)<BR>
     * 注文履歴ＩＤ<BR>
     */
    public String orderActionId;

    /**
     * (連続注文設定フラグ)<BR>
     * true：設定あり　@　@　@false：設定なし<BR>
     */
    public boolean succSettingFlag;

    /**
     * @@roseuid 40F7AE1B0232
     */
    public WEB3FuturesCancelCompleteResponse() 
    {
     
    }
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FuturesCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
