head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLCashOutStopInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金停止情報(WEB3SLCashOutStopInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 金傑（中訊）新規作成 仕様変更モデル764
*/
package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (出金停止情報)<BR>
 * 出金停止情報クラス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3SLCashOutStopInfoUnit extends Message
{
    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709140917L;

    /**
     * (口座ID)<BR>
     */
    public long accountId;

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
     * (利用可能枠)<BR>
     */
    public String cashoutLimit;

    /**
     * (出金拘束金)<BR>
     */
    public String cashoutRestraint;

    /**
     * (出金可能額)<BR>
     */
    public String cashoutPossAmt;

    /**
     * (出金停止区分)<BR>
     * <BR>
     * 0：通常<BR>
     * 1：停止中<BR>
     */
    public String cashoutStopDiv;

}
@
