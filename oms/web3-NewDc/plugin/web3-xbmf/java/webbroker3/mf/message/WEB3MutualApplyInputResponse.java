head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualApplyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  投信募集注文入力レスポンスクラス(WEB3MutualApplyInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 黄建 (中訊) 新規作成
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;

/**
 * 投信募集注文入力レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0   
 */

public class WEB3MutualApplyInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_apply_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200509261532L;
    
    /**
     * (買付可能額)<BR>  
     *  買付可能額<BR>  
     */
    public String tradingPower;
    
    /**
     * (銘柄コード)<BR>  
     * 投信銘柄コード <BR>  
     *（画面では非表示） <BR>     
     */
    public String mutualProductCode;
    
    /**
     * (銘柄名)<BR>  
     *  銘柄名<BR>  
     */
    public String mutualProductName;
    
    /**
     * (募集価額通貨コード)<BR>  
     *  集価額通貨コード <BR>  
     *  A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$ <BR>
     *  A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr <BR>
     *  F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS <BR>
     *  M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     *  T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR <BR> 
     */
    public String constantValueCurrencyCode;
    
    /**
     * (募集価額)<BR>  
     *  募集価額<BR>  
     */
    public String applyConstantValue;
    
    /**
     * (口座区分一覧)<BR>  
     *  口座区分一覧<BR>
     *  0:一般　@1:特定<BR> 
     */
    public String[] taxTypeList;
    
    /**
     * (指定方法@一覧)<BR>  
     *  指定方法@一覧<BR>  
     *  3:金額　@4:口数<BR>
     */
    public String[] specifyDivList;
    
    /**
     * (募集時単位口数)<BR>  
     *  募集時単位口数<BR>  
     */
    public String applyUnitQty;
    
    /**
     * (募集時最低口数)<BR>  
     *  募集時最低口数<BR>  
     */
    public String applyMinQty;
    
    /**
     * (募集時単位金額)<BR>  
     *  募集時単位金額<BR>  
     */
    public String applyUnitAmt;
    
    /**
     * (募集時最低金額)<BR>  
     *  募集時最低金額<BR>  
     */
    public String applyMinAmt;
    
    /**
     * (決済方法@一覧)<BR>  
     *  決済方法@一覧<BR> 
     *  1:円貨　@2:外貨<BR>  
     */
    public String[] settleDivList;
    
    /**
     * (発注日)<BR>  
     *  発注日<BR>  
     */
    public Date orderBizDate;
    
    /**
     * (約定日)<BR>  
     *  約定日<BR>  
     */
    public Date executionTimestamp;
    
    /**
     * (受渡日)<BR>  
     *  受渡日<BR>  
     */
    public Date deliveryDate;
    
    /**
     * (目論見書閲覧チェック結果)<BR>  
     *  目論見書閲覧チェック結果<BR>  
     */
    public WEB3GentradeProspectusResult prospectusResult;
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualApplyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
