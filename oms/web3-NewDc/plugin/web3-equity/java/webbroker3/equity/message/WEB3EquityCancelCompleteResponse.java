head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文取消完了レスポンス(WEB3EquityCancelOrderCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 李雲峰 (中訊) 新規作成
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （現物株式注文取消完了レスポンス）。<BR>
 * <BR>
 * 現物株式注文取消完了応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquityCancelCompleteResponse extends WEB3GenResponse
{

    /**
     * (更新時間)<BR>
     * <BR>
     * 注文受付日時<BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * (識別番号)<BR>
     * <BR>
     * 注文ID<BR>
     */
    public String orderActionId;

    /**
     * (連続注文設定フラグ)<BR>
     * <BR>
     * true：設定あり　@　@　@false：設定なし<BR>
     */
    public boolean succSettingFlag;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_cancel_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405151153L;

    /**
     * @@roseuid 40AC536601B8
     */
    public WEB3EquityCancelCompleteResponse()
    {

    }
    
    /**
     * コンストラクタ。<BR>
     * 引数で与えられたリクエストオブジェクトを基に
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     * @@roseuid 405023760250
     */
    public WEB3EquityCancelCompleteResponse(WEB3EquityCancelCompleteRequest l_request)
    {
        super(l_request);
    }
        
}
@
