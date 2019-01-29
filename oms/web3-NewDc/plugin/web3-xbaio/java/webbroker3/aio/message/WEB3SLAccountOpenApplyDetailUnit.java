head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLAccountOpenApplyDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保ローン申込顧客明細一覧行(WEB3SLAccountOpenApplyDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 何文敏 (中訊) 新規作成 仕様変更・モデルNo.776
*/
package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (担保ローン申込顧客明細一覧行)
 * 担保ローン申込顧客明細一覧行
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLAccountOpenApplyDetailUnit extends Message
{
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (ストックローン口座番号)<BR>
     * ストックローン口座番号<BR>
     */
    public String stockLoanAccount;

    /**
     * (申込状況)<BR>
     * 申込状況<BR>
     */
    public String applyStatus;

    /**
     * (申込日)<BR>
     * 申込日<BR>
     */
    public Date applyDateFrom;

    /**
     * (開設日)<BR>
     * 開設日<BR>
     */
    public Date accountOpenDate;

    /**
     * (成約日)<BR>
     * 成約日<BR>
     */
    public Date executeDate;

    /**
     * (解約日)<BR>
     * 解約日<BR>
     */
    public Date cancelDate;

    /**
     * (閉鎖日)<BR>
     * 閉鎖日<BR>
     */
    public Date closeDate;

    /**
     * (Y客ロック)<BR>
     * Y客ロック<BR>
     */
    public String yellowAccountLockDiv;

    /**
     * (考査ロック)<BR>
     * 考査ロック<BR>
     */
    public String examinationLockDiv;

    /**
     * (支店ロック)<BR>
     * 支店ロック<BR>
     */
    public String branchLockDiv;

    /**
     * (管理ロック)<BR>
     * 管理ロック<BR>
     */
    public String mngLockDiv;

    /**
     * (管理ロック理由(立替金))<BR>
     * 管理ロック理由(立替金)<BR>
     */
    public String mngExpenseLockReasonFlag;

    /**
     * (管理ロック理由(保証金未入))<BR>
     * 管理ロック理由(保証金未入)<BR>
     */
    public String mngDepositLockReasonFlag;

    /**
     * (管理ロック理由(適格担保不足))<BR>
     * 管理ロック理由(適格担保不足)<BR>
     */
    public String mngCollateralLockReasonFlag;

    /**
     * (管理ロック理由(預り証長期未差換))<BR>
     * 管理ロック理由(預り証長期未差換)<BR>
     */
    public String mngReceiptLockReasonFlag;
}
@
