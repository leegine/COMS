head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOREquityOrderExecutionRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式注文約定照会入力レスポンス (WEB3AdminOREquityOrderExecutionRefInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/09 何文敏(中訊) 仕様変更 モデルNo.106
*/
package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・株式注文約定照会入力レスポンス)<BR>
 * <BR>
 * 管理者・株式注文約定照会入力レスポンスクラス<BR>
 * <BR>
 * WEB3AdminOREquityOrderExecutionRefInputResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOREquityOrderExecutionRefInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_equity_order_execution_ref_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (発注日一覧)<BR>
     * <BR>
     * 発注日の配列<BR>
     * <BR>
     * An array of orderBizDate<BR>
     * <BR>
     */
    public Date[] orderBizDateList;

    /**
     * (市場コード一覧)<BR>
     * <BR>
     * 市場コードの配列<BR>
     * <BR>
     * An array of marketCode<BR>
     * <BR>
     */
    public String[] marketCodeList;

    /**
     * (弁済一覧)<BR>
     * <BR>
     * 弁済区分の配列<BR>
     * <BR>
     * An array of repayment<BR>
     * <BR>
     */
    public String[] repaymentList = null;

    /**
     * (値段条件一覧)<BR>
     * <BR>
     * 値段条件の配列<BR>
     * <BR>
     * An array of priceCond<BR>
     * <BR>
     */
    public String[] priceCondList = null;

    /**
     * (執行条件一覧)<BR>
     * <BR>
     * 執行条件の配列<BR>
     * <BR>
     * An array of execCondList<BR>
     * <BR>
     */
    public String[] execCondList = null;

    /**
     * (注文期限区分一覧)<BR>
     * <BR>
     * 注文期限区分の配列<BR>
     * <BR>
     * An array of expirationDateType<BR>
     * <BR>
     */
    public String[] expirationDateTypeList = null;

    /**
     * (発注条件一覧)<BR>
     * <BR>
     * 発注条件の配列<BR>
     * <BR>
     * An array of orderCondType<BR>
     * <BR>
     */
    public String[] orderCondTypeList = null;

    /**
     * (注文経路区分一覧)<BR>
     * <BR>
     * 注文経路区分の一覧<BR>
     */
    public String[] orderRootList = null;
    
    /**
     * （取扱商品一覧）<BR>
     * <BR>
     */
    public WEB3AdminORTradingProductUnit[] tradingProductList;

    /**
     * (強制決済実施フラグ)<BR>
     * <BR>
     * false:未実施<BR>
     * true:実施<BR>
     */
    public boolean forcedSettleEnforcementFlag;

    /**
     * @@roseuid 4212FB420315
     */
    public WEB3AdminOREquityOrderExecutionRefInputResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminOREquityOrderExecutionRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
