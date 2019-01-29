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
filename	WEB3EquityChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文訂正完了応答レスポンスデータクラス(WEB3EquityChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/11 周玲玲 (中訊) 新規作成
Revesion History : 2004/12/14 桑原 (SRA) 残案件対応
Revesion History : 2007/06/13 武波 (中訊) モデルNo.1168
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （現物株式注文訂正完了レスポンス）。<br>
 * <br>
 * 現物株式注文訂正完了応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquityChangeCompleteResponse extends WEB3GenResponse
{

    /**
     * (更新時間)<BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * (注文ID)<BR>
     */
    public String orderActionId;
    
    /**
     * (インサイダー警告表示フラグ)<BR>
     * true：警告表示要　@　@　@false：警告表示不要<BR>
     */
	public boolean insiderWarningFlag;

    /**
     * (連続注文設定フラグ)<BR>
     * true：設定あり　@　@　@false：設定なし<BR>
     */
    public boolean succSettingFlag;

    /**
     * ポリモルフィックタイプ。<BR>
     */
    public static final String PTYPE = "equity_change_complete";

    /**
     * (注文有効期限)<BR>
     * 注文有効期限
     */
    public Date expirationDate;

    /**
     * シリアルバージョンUID。<BR>
     */
    public static final long serialVersionUID = 200402241355L;

    /**
     * @@roseuid 409EFF810229
     */
    public WEB3EquityChangeCompleteResponse()
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
    public WEB3EquityChangeCompleteResponse(WEB3EquityChangeCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
