head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquitySellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式売付注文確認レスポンス(WEB3EquitySellConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 洪華 (中訊) 新規作成
Revesion History : 2004/12/14 桑原 (SRA) 残案件対応
Revesion History : 2006/12/25 柴双紅(中訊) モデルNo.1085
Revesion History : 2007/06/13 武波 (中訊) モデルNo.1168
*/
package webbroker3.equity.message;

import java.util.Date;

/**
 * （現物株式売付注文確認レスポンス）。<BR>
 * <BR>
 * 現物株式売付注文確認応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquitySellConfirmResponse extends WEB3EquityConfirmCommonResponse
{

    /**
     * 概算簿価単価<BR>
     */
    public String estimatedBookPrice;
    
    /**
     * (手数料情報)<BR>
     * 手数料情報<BR>
     */
    public WEB3EquityCommissionInfoUnit commissionInfo;
    
    /**
     * (確認時単価)<BR>
     * 確認時単価<BR>
     */
    public String checkPrice;
    
    /**
     * (注文ＩＤ)<BR>
     * 注文ＩＤ<BR>
     */
    public String orderId;
    
    /**
     * (インサイダー警告表示フラグ)<BR>
     * true：警告表示要　@　@　@false：警告表示不要<BR>
     */
    public boolean insiderWarningFlag;

    /**
     * (注意文言表示区分)<BR>
     * null：注意文言非表示　@　@1：現金不足注意文言表示　@　@3：預り金不足注意文言表示<BR>
     */
    public String attentionObjectionType;
    
    /**
     * (預り金不足額)<BR>
     * 余力の不足金額<BR>
     */
    public String accountBalanceInsufficiency;

    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;

    /**
    * PTYPE<BR>
    */
    public static final String PTYPE = "equity_sell_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200402041611L;

    /**
     * (注文有効期限)<BR>
     * 注文有効期限
     */
    public Date expirationDate;

    /**
     * @@roseuid 40A0949B001F
     */
    public WEB3EquitySellConfirmResponse()
    {

    }

    public WEB3EquitySellConfirmResponse(WEB3EquitySellConfirmRequest l_request)
    {
        super(l_request);
    }

}
@
