head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション返済入力画面レスポンスクラス(WEB3OptionsCloseMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 呉艶飛 新規作成
*/
package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;

/**
 * (株価指数オプション返済入力画面レスポンス)<BR>
 * 株価指数オプション返済入力画面レスポンスクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginInputResponse extends WEB3OptionsCommonResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406112110L;
        
    /**
     * (取引区分)<BR>
     * 5：買建返済注文（売返済）　@6：売建返済注文（買返済）<BR>
     */
    public String tradingType;
    
    /**
     * (取引市場)<BR>
     * 1：東京　@2：大阪<BR>
     */
    public String marketCode;
    
    /**
    * (指数種別)<BR>
    * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225<BR>
    */
    public String targetProductCode;
    
    /**
    * (限月)<BR>
    * YYYYMM形式<BR>
    */
    public String delivaryMonth;
    
    /**
    * (オプション商品区分)<BR>
    * P：プットオプション C：コールオプション<BR>
    */
    public String opProductType;
    
    /**
    * 行使価格
    */
    public String strikePrice;
    
    /**
    * (現在値)<BR>
    * 値がついていないときは基準値を設定。<BR>
    */
    public String currentPrice;
    
    /**
    * (前日比)<BR>
    * 値がついていないときは未設定。<BR>
    */
    public String comparedPreviousDay;
    
    /**
    * (取引時間)<BR>
    * 値がついていないときは未設定。<BR>
    */
    public Date currentPriceTime;
    
    /**
    * 建玉明細
    */
    public WEB3FuturesOptionsContractUnit[] contractUnits;
    
    /**
    * (取引終了警告文言)<BR>
    * 閉局間近の市場があれば、そのコードを格納<BR>
    */
    public String[] messageSuspension;
    
    /**
     * デフォルトコンストラクタ
     */
    public WEB3OptionsCloseMarginInputResponse()
    {
        
    }
    
    /**
    * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
    * レスポンスオブジェクトを生成する。<BR>
    *<BR>
    * @@param l_request リクエストオブジェクト
    */
    protected WEB3OptionsCloseMarginInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
