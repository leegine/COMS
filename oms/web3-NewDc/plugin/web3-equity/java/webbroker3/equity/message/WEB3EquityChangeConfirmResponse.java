head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文訂正確認応答　@レスポンスデータクラス(WEB3EquityChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/11 周玲玲 (中訊) 新規作成
Revesion History : 2004/12/14 桑原 (SRA) 残案件対応
Revesion History : 2007/06/13 武波 (中訊) モデルNo.1168
*/
package webbroker3.equity.message;

import java.util.Date;

/**
 * （現物株式注文訂正確認レスポンス）。<br>
 * <br>
 * 現物株式注文訂正確認応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquityChangeConfirmResponse extends WEB3EquityConfirmCommonResponse
{

    /**
     * (内出来株数)<BR>
     */
    public String partContQuantity;

    /**
     * (概算簿価単価)<BR>
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
     * (注文有効期限)<BR>
     * 注文有効期限
     */
    public Date expirationDate;

    /**
     * ポリモルフィックタイプ。<BR>
     */
    public static final String PTYPE = "equity_change_confirm";

    /**
     * シリアルバージョンUID。<BR>
     */
    public static final long serialVersionUID = 200402241455L;

    /**
     * @@roseuid 409EFF7700DA
     */
    public WEB3EquityChangeConfirmResponse()
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
    public WEB3EquityChangeConfirmResponse(WEB3EquityChangeConfirmRequest l_request)
    {
        super(l_request);
    }
        
}
@
