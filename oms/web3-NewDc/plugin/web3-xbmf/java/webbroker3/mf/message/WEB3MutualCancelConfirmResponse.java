head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託取消確認レスポンスクラス(WEB3MutualCancelConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
Revesion History : 2004/08/24 黄建 (中訊) レビュー
Revesion History : 2006/10/16 周捷 仕様変更・モデル506
Revesion History : 2007/02/09 張騰宇 (中訊) 仕様変更・モデル540
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 投資信託取消確認レスポンスクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualCancelConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_cancel_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L; 
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
    
    /**
     * 銘柄名
     */
    public String mutualProductName;
    
    /**
     * 計算基準価額通貨コード<BR>
     * <BR>
     * ブランク:円　@ブランク×２:円<BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     * <BR>
     */
    public String constantValueCurrencyCode;
    
    /**
     * (計算基準価額)<BR>
     * 計算に使用した基準価額<BR>
     */
    public String constantValue;
    
    /**
     * 基準価額適用日
     */
    public Date constantValueAppDate;
    
    /**
     * 口座区分<BR>
     * <BR>
     * 0:一般　@1:特定  2:その他<BR>
     */
    public String taxType;
    
    /**
     * 売買区分(投信)<BR>
     * <BR>
     * 1:買付　@2:解約　@3:乗換　@5:募集<BR>
     */
    public String mutualDealingType;
    
    /**
     * 請求方法@<BR>
     * <BR>
     * 0:解約請求　@1:買取請求<BR>
     * <BR>
     * (買付注文の場合、null)<BR>
     */
    public String sellBuyDiv;
    
    /**
     * 指定方法@<BR>
     * <BR>
     * 2:全部　@3:金額　@4:口数<BR>
     */
    public String specifyDiv;
    
    /**
     * 注文数量区分<BR>
     * <BR>
     * 0:口数<BR>
     * ブランク:円　@ブランク×２:円<BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     * <BR>
     */
    public String mutualOrderQuantityType;
    
    /**
     * 注文数量
     */
    public String mutualOrderQuantity;
    
    /**
     * 概算受渡代金通貨コード<BR>
     * <BR>
     * ブランク:円　@ブランク×２:円<BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     * <BR>
     * (取消の対象となる注文が"乗換"の場合、nullが設定される)<BR>
     */
    public String estimatedPriceCurrencyCode;
    
    /**
     * 概算受渡代金<BR>
     * <BR>
     * (取消の対象となる注文が"乗換"の場合、nullが設定される)<BR>
     */
    public String estimatedPrice;
    
    /**
     * 概算売買口数<BR>
     * <BR>
     * (取消の対象となる注文が"乗換"の場合、nullが設定される)<BR>
     */
    public String estimatedQty;
    
    /**
     * 銘柄名（乗換先）<BR>
     * <BR>
     * (取消の対象となる注文が"乗換"以外の場合、nullが設定される)<BR>
     */
    public String switchingProductName;
    
    /**
     * 計算基準価額通貨コード（乗換先）<BR>
     * <BR>
     * ブランク:円　@ブランク×２:円<BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     * <BR>
     * (取消の対象となる注文が"乗換"以外の場合、nullが設定される)<BR>
     */
    public String switchingConstantValueCurrencyCode;
    
    /**
     * 計算基準価額（乗換先）<BR>
     * <BR>
     * (取消の対象となる注文が"乗換"以外の場合、nullが設定される)<BR>
     */
    public String switchingConstantValue;
    
    /**
     * 口座区分（乗換先）<BR>
     * <BR>
     * 0:一般　@1:特定<BR>
     * <BR>
     * (取消の対象となる注文が"乗換"以外の場合、nullが設定される)<BR>
     */
    public String switchingTaxType;
    
    /**
     * 概算買付口数（乗換先）<BR>
     * <BR>
     * (取消の対象となる注文が"乗換"以外の場合、nullが設定される)<BR>
     */
    public String switchingEstimatedQty;
    
    /**
     * 決済方法@<BR>
     * <BR>
     * 1:円貨　@2:外貨<BR>
     * <BR>
     * (取消の対象となる注文が"乗換"の場合、nullが設定される)<BR>
     */
    public String settleDiv;
    
    /**
     * 受渡方法@<BR>
     * <BR>
     * 1:銀行振込み　@2:証券口座入金<BR>
     * <BR>
     * (取消の対象となる注文が"乗換"の場合、nullが設定される)<BR>
     */
    public String deliveryDiv;
    
    /**
     * 発注日
     */
    public Date orderBizDate;
    
    /**
     * 約定日
     */
    public Date executionTimestamp;
    
    /**
     * 受渡日
     */
    public Date deliveryDate;
    
    /**
     * 確認時発注日<BR>
     * <BR>
     * 完了リクエストで送信する値を格納する。<BR>
     * （画面では非表示）<BR>
     */
    public Date checkDate;
    
    /**
     * (投信取消確認レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40A9A8DF03D4
     */
    public WEB3MutualCancelConfirmResponse() 
    {
     
    }
}
@
