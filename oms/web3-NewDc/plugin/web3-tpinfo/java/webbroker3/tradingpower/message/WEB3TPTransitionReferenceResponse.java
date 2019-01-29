head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransitionReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 余力推移画面表示レスポンス(WEB3TPTransitionReferenceResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (余力推移画面表示レスポンス)<BR>
 * 余力推移画面表示レスポンスクラス。<BR>
 * 
 * @@author asano(SCS)
 */

public class WEB3TPTransitionReferenceResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "tradingpower_transition_reference";

    /**
     * 余力計算結果ID
     */
    public String calcResultId;

    /**
     * 預り証券顧客区分
     */
    public String depositDiv;

    /**
     * 余力推移明細一覧
     */
    public WEB3TPTransitionReferenceUnit[] transitionReferenceUnits;

    /**
     * 余力適用日<現物株買付余力>
     */
    public Date equityBuyApplyDate;

    /**
     * 余力適用日<信用新規建余力>
     */
    public Date marginApplyDate;

    /**
     * 余力適用日<信用現引余力>
     */
    public Date swapLongApplyDate;

    /**
     * 余力適用日<投信買付余力>
     */
    public Date mutualBuyApplyDate;

    /**
     * 余力適用日<その他商品買付余力>
     */
    public Date otherApplyDate;

    /**
     * 余力適用日<出金余力>
     */
    public Date paymentApplyDate;

    /**
     * 余力適用日<保証金預託率>
     */
    public Date marginCollateralRateApplyDate;

    /**
     * 値洗い状態区分
     */
    public String markToMarketStateDiv;

    /**
     * (証券担保ローン区分) 
     * 
     * 0 : 未実施 
     * 1 : 実施 
     * (顧客分類の判定に使用)
     */
    public String securedLoanSecAccOpenDiv;

    /**
     * (コンストラクタ)
     * @@param l_request
     * @@roseuid 41B6A87D008C
     */
    protected WEB3TPTransitionReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

    /**
     * (コンストラクタ)
     * @@roseuid 41B55B630046
     */
    public WEB3TPTransitionReferenceResponse()
    {

    }
}@
