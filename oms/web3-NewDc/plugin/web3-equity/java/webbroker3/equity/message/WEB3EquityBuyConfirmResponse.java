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
filename	WEB3EquityBuyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式買付注文確認応答(WEB3EquityBuyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 洪華 (中訊) 新規作成
Revesion History : 2004/12/06 岡村和明(SRA) 残案件対応 Ｎｏ.１３５
Revesion History : 2006/12/25 柴双紅(中訊) モデルNo.1085
Revesion History : 2007/06/13 武波 (中訊) モデルNo.1168
*/

package webbroker3.equity.message;

import java.util.Date;

/**
 * （現物株式買付注文確認レスポンス）。<br>
 * <br>
 * 現物株式買付注文確認応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquityBuyConfirmResponse extends WEB3EquityConfirmCommonResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "equity_buy_confirm";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200402041600L;

    /**
     * （銘柄名）<BR>
     * 銘柄名<BR>
     */
    public String productName;

    /**
     * （手数料情報）<BR>
     * 手数料情報<BR>
     */
    public WEB3EquityCommissionInfoUnit commissionInfo;

    /**
     * （確認時単価）<BR>
     * 画面では非表示。完了リクエストで送信する値。<BR>
     */
    public String checkPrice;
    
    /**
     * （注文ＩＤ）<BR>
     * 画面では非表示。完了リクエストで送信する値。<BR>
     */
    public String orderId;
    
    /**
     * （インサイダー警告表示フラグ）<BR>
     * true：警告表示要　@　@　@false：警告表示不要<BR>
     */
    public boolean insiderWarningFlag;

    /**
     * （注意文言表示区分）<BR>
     * null：注意文言非表示　@　@1：現金不足注意文言表示　@　@3：預り金不足注意文言表示<BR>
     */
    public String attentionObjectionType;
    
    /**
     * （預り金不足額）<BR>
     * 余力の不足金額<BR>
     */
    public String accountBalanceInsufficiency;

    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;

    /**
     * (注文有効期限)<BR>
     * 注文有効期限
     */
    public Date expirationDate;

    /**
     * コンストラクタ。<BR>
     * 引数の無いコンストラクタ <BR>
     * <BR>
     * @@roseuid 405023760250
     */
    public WEB3EquityBuyConfirmResponse()
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
    public WEB3EquityBuyConfirmResponse(WEB3EquityBuyConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
