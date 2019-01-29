head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託乗換入力レスポンスクラス(WEB3MutualSwitchingInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/25 黄建 (中訊) レビュー
                   2004/12/07 于美麗 (中訊) 残対応
                   2005/10/18 韋念瓊 (中訊) フィデリティ対応
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;


/**
 * 投資信託乗換入力レスポンスクラス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualSwitchingInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_switching_input";
    
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
    protected WEB3MutualSwitchingInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 

    /**
     * 乗換可能口数<BR>
     */
    public String switchingAbleQty;
    
    /**
     * 評価額<BR>
     */
    public String marketValue;
    
    /**
     * 投信銘柄コード<BR>
     * <BR>
     * (画面では非表示)<BR>
     */
    public String mutualProductCode;
    
    /**
     * 銘柄名<BR>
     */
    public String mutualProductName;
    
    /**
     * 解約価額通貨コード<BR>
     * <BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR
     */
    public String sellValueCurrencyCode;
    
    /**
     * 解約価額<BR>
     */
    public String sellValue;
    
    /**
     * 基準価額適用日<BR>
     */
    public Date constantValueAppDate;
    
    /**
     * 解約口座区分<BR>
     * <BR>
     * 0:一般　@1:特定<BR>
     */
    public String taxType;
    
    /**
     * 指定方法@一覧<BR>
     * <BR>
     * 2:全部　@3:金額指定　@4:口数指定<BR>
     */
    public String[ ] specifyDivList;
    
    /**
     * 請求方法@一覧<BR>
     * <BR>
     * 0:解約請求　@1:買取請求<BR>
     */
    public String[ ] sellBuyDivList;
    
    /**
     * 乗換時単位口数<BR>
     */
    public String switchingUnitQty;
    
    /**
     * 乗換時最低口数<BR>
     */
    public String switchingMinQty;
    
    /**
     * 乗換時単位金額<BR>
     */
    public String switchingUnitAmt;
    
    /**
     * 乗換時最低金額<BR>
     */
    public String switchingMinAmt;
    
    /**
     * 発注日<BR>
     */
    public Date orderBizDate;
    
    /**
     * 約定日<BR>
     */
    public Date executionTimestamp;
    
    /**
     * 受渡日<BR>
     */
    public Date deliveryDate;
    
    /**
     * 銘柄コード（乗換先）<BR>
     *  <BR>
     * (画面では非表示) <BR>
     */
    public String switchingProductCode;
    
    /**
     * 銘柄名（乗換先）<BR>
     */
    public String switchingProductName;
    
    /**
     * 買付基準価額通貨コード <BR>
     * <BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$ <BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr <BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS <BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円 <BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR <BR>
     */
    public String buyConstantValueCurrencyCode;
    
    /**
     * 買付基準価額 <BR>
     */
    public String buyConstantValue;
    
    /**
     * 買付基準価額適用日 <BR>
     */
    public Date buyConstantValueAppDate;
    
    /**
     * 買付口座区分一覧 <BR>
     * 0:一般　@1:特定 <BR>
     */
    public String[] taxTypeList;
    
    /**
     * 目論見書閲覧チェック結果 <BR>
     */
    public WEB3GentradeProspectusResult prospectusResult;
    
    /**
     * (投信乗換入力レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40A8A31F0072
     */
    public WEB3MutualSwitchingInputResponse() 
    {
     
    }
}
@
