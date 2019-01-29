head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IfoDepShortageInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金不足状況情報(WEB3IfoDepShortageInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 李玉玲(中訊) 新規作成 モデルNo.004
*/
package webbroker3.ifoadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (証拠金不足状況情報)<BR>
 * 証拠金不足状況情報クラス<BR>
 * <BR>
 * @@author 李玉玲(中訊)
 * @@version 1.0
 */
public class WEB3IfoDepShortageInfo extends Message
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
     * (顧客名)<BR>
     * 顧客名<BR>
     */
    public String accountName;

    /**
     * (請求額)<BR>
     */
    public String claimAmount;

    /**
     * (現在未入金額)<BR>
     */
    public String curNonPayAmt = null;

    /**
     * (現在証拠金所要額)<BR>
     */
    public String curIfoDepositNecessaryAmt;

    /**
     * (建玉有無フラグ)<BR>
     * 建玉有無フラグ<BR>
     * （OP買建除く）<BR>
     * <BR>
     * true：　@有 <BR>
     * false：　@無<BR>
     * <BR>
     */
    public boolean contractExistFlag;

    /**
     * (注文有無フラグ)<BR>
     * 注文有無フラグ<BR>
     * （OP買建除く）<BR>
     * <BR>
     * true：　@有<BR>
     * false：　@無<BR>
     * <BR>
     * <BR>
     */
    public boolean orderExistFlag;

    /**
     * (証拠金不足状況情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 4998F2BB0071
     */
    public WEB3IfoDepShortageInfo()
    {

    }
}
@
