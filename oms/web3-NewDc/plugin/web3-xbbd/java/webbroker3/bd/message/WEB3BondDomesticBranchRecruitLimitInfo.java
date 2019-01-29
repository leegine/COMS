head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.40.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticBranchRecruitLimitInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券部店別応募枠情報(WEB3BondDomesticBranchRecruitLimitInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.215
*/
package webbroker3.bd.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (国内債券部店別応募枠情報)<BR>
 * 国内債券部店別応募枠情報<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticBranchRecruitLimitInfo extends Message
{

    /**
     * (注文金額合計)<BR>
     * 注文金額合計<BR>
     */
    public String orderAmountTotal;

    /**
     * (WEB3応募枠)<BR>
     * WEB3応募枠<BR>
     */
    public String web3RecruitLimit;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (国内債券部店別応募枠情報)<BR>
     * コンストラクタ
     * @@roseuid 468D91690360
     */
    public WEB3BondDomesticBranchRecruitLimitInfo()
    {

    }
}
@
