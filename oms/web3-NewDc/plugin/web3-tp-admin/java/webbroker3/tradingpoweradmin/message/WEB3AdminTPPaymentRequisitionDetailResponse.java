head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求顧客検索詳細レスポンス(WEB3AdminTPPaymentRequisitionDetailResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 孟亞南 (中訊) 新規作成 モデルNo.027
Revision History : 2008/10/15 姜丹 (中訊) モデルNo.035
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (入金請求顧客検索詳細レスポンス)<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionDetailResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tp_payment_requisition_detail";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200810081730L;

    /**
     * (計算日)<BR>
     */
    public Date calcDate;

    /**
     * (部店コード)<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     */
    public String accountCode;

    /**
     * (顧客名)<BR>
     */
    public String accountName;

    /**
     * (扱者コード)<BR>
     */
    public String traderCode = null;

    /**
     * (属性)<BR>
     */
    public String attribute;

    /**
     * (取引停止区分)<BR>
     */
    public String tradeStopDiv;

    /**
     * (追証未入金区分)<BR>
     */
    public String additionalDepositStop;

    /**
     * (信用新規建余力区分)<BR>
     */
    public String marginOpenPositionStop;

    /**
     * (先物OP新規建余力区分)<BR>
     */
    public String ifoOpenPositionStop;

    /**
     * (出金余力区分)<BR>
     */
    public String paymentStop;

    /**
     * (その他商品買付余力区分)<BR>
     */
    public String otherTradingStop;

    /**
     * (立替金)<BR>
     */
    public String debitAmount = "0";

    /**
     * (特別立替金)<BR>
     */
    public String specialDebitAmount = "0";

    /**
     * (保証金自動振替後判定フラグ)<BR>
     */
    public boolean autoTransferAfterJudgeFlag;

    /**
     * (不足金発生状況)<BR>
     */
    public String shortfallGenerationStateDiv = "0";

    /**
     * (追証発生状況)<BR>
     */
    public String additionalGenerationStateDiv = "0";

    /**
     * (不足金発生情報)<BR>
     */
    public WEB3AdminTPShortfallGenerationInfo shortfallGenerationInfo;

    /**
     * (第一水準追証情報)<BR>
     */
    public WEB3AdminTPFirstAdditionalInfo firstAdditionalInfo = null;

    /**
     * (第二水準追証情報)<BR>
     */
    public WEB3AdminTPSecondAdditionalInfo secondAdditionalInfo = null;

    /**
     * @@roseuid 48EC70340047
     */
    public WEB3AdminTPPaymentRequisitionDetailResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminTPPaymentRequisitionDetailResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
