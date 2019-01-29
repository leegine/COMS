head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBuyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式買付注文完了レスポンス(WEB3EquityBuyCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 洪華 (中訊) 新規作成
Revesion History : 2004/12/13 桑原 (SRA) 残案件対応
Revesion History : 2006/12/25 柴双紅(中訊) モデルNo.1085
Revesion History : 2007/06/13 武波 (中訊) モデルNo.1168
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （現物株式買付注文完了レスポンス）。<BR>
 * <BR>
 * 現物株式買付注文完了応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquityBuyCompleteResponse extends WEB3GenResponse
{

    /**
     * (更新時間) <BR>
     * 注文受付日時 <BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * (識別コード) <BR>
     * 注文ID <BR>
     */
    public String orderActionId;
    
	/**
	 * (インサイダー警告表示フラグ)<BR>
	 * true：警告表示要　@　@　@false：警告表示不要
	 */
	public boolean insiderWarningFlag;

    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;

    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "equity_buy_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200402041600L;

    /**
     * (注文有効期限)<BR>
     * 注文有効期限
     */
    public Date expirationDate;

    /**
     * @@roseuid 40A091DE0196
     */
    public WEB3EquityBuyCompleteResponse()
    {

    }

    public WEB3EquityBuyCompleteResponse(WEB3EquityBuyCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
