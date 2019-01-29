head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionListUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求顧客検索一覧ユニット(WEB3AdminTPPaymentRequisitionListUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 孟亞南 (中訊) 新規作成 モデルNo.027
Revision History : 2008/10/28 張騰宇 (中訊) 仕様変更モデルNo.042
*/
package webbroker3.tradingpoweradmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (入金請求顧客検索一覧ユニット)<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionListUnit extends Message
{

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
    public String traderCode;

    /**
     * (属性)<BR>
     */
    public String attribute;

    /**
     * (立替金)<BR>
     */
    public String debitAmount;

    /**
     * (特別立替金)<BR>
     */
    public String specialDebitAmount;

    /**
     * (必要入金額)<BR>
     */
    public String requiredPayAmt;

    /**
     * (第一水準追証金額)<BR>
     */
    public String firstDepositAmount;

    /**
     * (第一水準追証経過日数)<BR>
     */
    public String firstDepositPassDay;

    /**
     * (第二水準追証請求(1))<BR>
     */
    public String secondDeposit1;

    /**
     * (第二水準追証請求(2))<BR>
     */
    public String secondDeposit2;

    /**
     * (第二水準追証請求未入金)<BR>
     */
    public String secondDepositNonPay;

    /**
     * @@roseuid 48EC703302F7
     */
    public WEB3AdminTPPaymentRequisitionListUnit()
    {

    }
}
@
