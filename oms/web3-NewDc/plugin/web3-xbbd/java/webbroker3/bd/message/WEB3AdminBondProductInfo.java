head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.49.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄情報(WEB3AdminBondProductInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
                     2006/10/08 周捷 (中訊) 仕様変更・モデル106
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (銘柄情報)<BR>
 * 銘柄情報クラス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductInfo extends Message
{

    /**
     * (銘柄コード（WEB3）)<BR>
     * 銘柄コード（WEB3）
     */
    public String productCode;

    /**
     * (銘柄名)<BR>
     * 取扱銘柄名
     */
    public String productName;

    /**
     * (買付単価)<BR>
     * 買付単価
     */
    public String buyPrice;

    /**
     * (売却単価)<BR>
     * 売却単価
     */
    public String sellPrice;

    /**
     * (利率)<BR>
     * 利率
     */
    public String coupon;

    /**
     * (通貨コード)<BR>
     * 通貨
     */
    public String currencyCode;

    /**
     * (発行日)<BR>
     * 発行日
     */
    public Date issueDate;

    /**
     * (年間利払回数)<BR>
     * 年間利払回数
     */
    public String yearlyInterestPayments;

    /**
     * (利払日1)<BR>
     * 利払日1
     */
    public String interestPaymentDay1;

    /**
     * (利払日2)<BR>
     * 利払日2
     */
    public String interestPaymentDay2;

    /**
     * (償還日)<BR>
     * 償還日
     */
    public Date maturityDate;

    /**
     *(仕入時の為替レート)<BR>
     *仕入時の為替レート
     */
    public String fxRateAtStock;

    /**
     * (カストディアン)<BR>
     * カストディアン
     */
    public WEB3AdminBondCustodianUnit custodianInfo;

    /**
     * (為替レート)<BR>
     * 為替レート
     */
    public WEB3AdminBondFxRateInfo fxRateInfo;

    /**
     * (銘柄情報)<BR>
     * コンストラクタ
     * @@roseuid 44DB2ED10264
     */
    public WEB3AdminBondProductInfo()
    {

    }
}
@
