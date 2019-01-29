head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約入力レスポンスクラス(WEB3MutualSellInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 黄建 (中訊) 新規作成
Revesion History : 2006/05/15 李小健 (中訊) 仕様変更・モデル411
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル536
Revesion History : 2007/04/09 唐性峰 (中訊) 仕様変更・モデル562
*/
package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 投資信託解約入力レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0   
 */

public class WEB3MutualSellInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408120950L;
    
    /**
     * 解約可能口数
     */
    public String sellAbleQty;
    
    /**
     * 評価額
     */
    public String marketValue;
    
    /**
     * 投信銘柄コード<BR>
     * <BR>
     * (画面では非表示)<BR>
     */
    public String mutualProductCode;
    
    /**
     * 銘柄名
     */
    public String mutualProductName;
    
    /**
     * 口座区分<BR>
     * <BR>
     * 0:一般　@1:特定　@2:その他<BR>
     */
    public String taxType;
    
    /**
     * 解約価額通貨コード<BR>
     * <BR>
     * ブランク:円　@ブランク×２:円<BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     * <BR>
     */
    public String sellValueCurrencyCode;
    
    /**
     * 解約価額
     */
    public String sellValue;
    
    /**
     * 基準価額適用日
     */
    public Date constantValueAppDate;
    
    /**
     * 請求方法@一覧<BR>
     * <BR>
     * 0:解約請求　@1:買取請求<BR>
     */
    public String[ ] sellBuyDivList;
    
    /**
     * 指定方法@一覧<BR>
     * <BR>
     * 2:全部　@3:金額指定　@4:口数指定<BR>
     */
    public String[ ] specifyDivList;
    
    /**
     * 解約時単位口数
     */
    public String sellUnitQty;
    
    /**
     * 解約時最低口数
     */
    public String sellMinQty;
    
    /**
     * 解約時単位金額
     */
    public String sellUnitAmt;
    
    /**
     * 解約時最低金額
     */
    public String sellMinAmt;
    
    /**
     * 決済方法@一覧<BR>
     * <BR>
     * 1:円貨　@2:外貨<BR>
     */
    public String[ ] settleDivList;
    
    /**
     * 受渡方法@一覧<BR>
     * <BR>
     * 1:銀行振込み　@2:証券口座入金<BR>
     */
    public String[ ] deliveryDivList;
    
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
     * (解約時外貨単位金額)<BR>
     * 解約時外貨単位金額<BR>
     */
    public String sellFrgnUnitAmt;

    /**
     * (解約時外貨最低金額)<BR>
     * 解約時外貨最低金額<BR>
     */
    public String sellFrgnMinAmt;

    /**
     * デフォルトコンストラクタ
     * @@roseuid 40A8907B0140
     */
    public WEB3MutualSellInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualSellInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
