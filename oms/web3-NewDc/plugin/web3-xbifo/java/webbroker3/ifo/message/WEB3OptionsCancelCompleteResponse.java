head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.23.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション取消注文完了レスポンス(WEB3OptionsCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 鄒鋭 (中訊) 新規作成
Revesion History : 2008/03/12 金傑(中訊) モデル 830
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (株価指数オプション取消注文完了レスポンス)<BR>
 * 株価指数オプション取消注文完了レスポンスクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3OptionsCancelCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_cancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406102008L;

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
     * 連続注文設定フラグ<BR>
     * true：設定あり　@　@　@false：設定なし<BR>
     */
    public boolean succSettingFlag;

    /**
     * デフォルトコンストラクタ
     */
    public WEB3OptionsCancelCompleteResponse()
    {
        
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
