head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付注文入力レスポンスクラス(WEB3MutualBuyInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 周勇 (中訊) 新規作成
Revesion History : 2004/08/23 于美麗 (中訊) レビュー 
Revesion History : 2006/05/15 李小健 (中訊) 仕様変更・モデル411
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル535
Revesion History : 2007/04/09 唐性峰 (中訊) 仕様変更・モデル562
*/
package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;

/**
 * 投資信託買付注文入力レスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_buy_input";
    
    /**
     * 買付可能額
     */
    public String tradingPower;
    
    /**
     * 投信銘柄コード<BR>
     * <BR>
     * （画面では非表示）<BR>
     */
    public String mutualProductCode;
    
    /**
     * 銘柄名
     */
    public String mutualProductName;
    
    /**
     * 買付基準価額通貨コード<BR>
     * <BR>
     * ブランク:円　@ブランク×２:円<BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     */
    public String constantValueCurrencyCode;
    
    /**
     * 買付基準価額
     */
    public String constantValue;
    
    /**
     * 基準価額適用日
     */
    public Date constantValueAppDate;
    
    /**
     * 口座区分一覧<BR>
     * <BR>
     * 0:一般　@1:特定  2:その他<BR>
     */
    public String[ ] taxTypeList;
    
    /**
     * 指定方法@一覧<BR>
     * <BR>
     * 3:金額　@4:口数<BR>
     */
    public String[ ] specifyDivList;
    
    /**
     * 買付時(新規／追加)単位口数
     */
    public String buyUnitQty;
    
    /**
     * 買付時(新規／追加)最低口数
     */
    public String buyMinQty;
    
    /**
     * 買付時(新規／追加)単位金額
     */
    public String buyUnitAmt;
    
    /**
     * 買付時(新規／追加)最低金額
     */
    public String buyMinAmt;
    
    /**
     * 決済方法@一覧<BR>
     * <BR>
     * 1:円貨　@2:外貨<BR>
     */
    public String[ ] settleDivList;
    
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
     * (目論見書閲覧チェック結果)<BR>
     * 目論見書閲覧チェック結果
     */
    public WEB3GentradeProspectusResult prospectusResult;
 
    /**
     * (円転基準価額)<BR>
     * 円転基準価額<BR>
     * <BR>
     * 買付基準価額を円転したもの<BR>
     */
    public String yenConstantValue;
    
    /**
     * (参考レート)<BR>
     * 参考レート<BR>
     * <BR>
     * 為替レートテーブル.TTS<BR>
     * 円貨の場合null<BR>
     */
    public String referenceRate;
    
    /**
     * (参考レート確定日)<BR>
     * 参考レート確定日<BR>
     * <BR>
     * 為替レートテーブル.為替レート確定日<BR> 
     */
    public Date referenceRateFixedDay;
 
    /**
     * (買付時外貨単位金額)<BR>
     * 買付時(新規／追加)外貨単位金額<BR>
     */
    public String buyFrgnUnitAmt;

    /**
     * (買付時外貨最低金額)<BR>
     * 買付時(新規／追加)外貨最低金額<BR>
     */
    public String buyFrgnMinAmt;

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
    protected WEB3MutualBuyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
    /**
     * (投信買付注文入力レスポンス)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A87DAF00C3
     */
    public WEB3MutualBuyInputResponse() 
    {
     
    }
}
@
