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
filename	WEB3MarginIndividualCloseMarginListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引個別決済一覧レスポンス(WEB3MarginIndividualCloseMarginListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
Revesion History : 2004/12/13 桑原 (SRA) 修正
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引個別決済一覧レスポンス）。<br>
 * <br>
 * 信用取引個別決済一覧レスポンスクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginIndividualCloseMarginListResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_individualCloseMarginList";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;    
    
    /**
     * (銘柄コード)<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)
     */
    public String productName;
    
    /**
     * (市場コード)
     */
    public String marketCode;
    
    /**
     * (口座区分)<BR>
     * 0：一般　@1：特定
     */
    public String taxType;
    
    /**
     * (建区分)<BR>
     * 1：買建　@2：売建<BR>
     * （ContractTypeEnumにて定義）<BR>
     */
    public String contractType;
    
    /**
     * (弁済)<BR>
     * 信用取引弁済
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (現引可能額)
     */
    public String swapLongTradingPower;
    
    /**
     * (現在値)<BR>
     *  現在値
     */
    public String currentPrice;
    
    /**
     * (建株明細一覧)<BR>
     * 信用取引建株明細の一覧
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * (取引終了警告文言を表示する市場コードの一覧)<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 4140474A00A1
     */
    public WEB3MarginIndividualCloseMarginListResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginIndividualCloseMarginListResponse(WEB3MarginIndividualCloseMarginListRequest l_request)
    {
        super(l_request);
    }
}
@
