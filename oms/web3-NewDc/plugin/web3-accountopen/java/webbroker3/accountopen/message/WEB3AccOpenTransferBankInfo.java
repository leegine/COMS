head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenTransferBankInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振込先銀行情報(WEB3AccOpenTransferBankInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張学剛 新規作成
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (振込先銀行情報)<BR>
 * 振込先銀行情報<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AccOpenTransferBankInfo extends Message 
{
    
    /**
     * (銀行コード)<BR>
     * 銀行コード<BR>
     */
    public String financialInstitutionCode;
    
    /**
     * (銀行名)<BR>
     * 銀行名<BR>
     */
    public String financialInstitutionName;
    
    /**
     * (支店コード)<BR>
     * 支店コード<BR>
     */
    public String financialBranchCode;
    
    /**
     * (支店名)<BR>
     * 支店名<BR>
     */
    public String financialBranchName;
    
    /**
     * (預金区分)<BR>
     * 預金区分<BR>
     */
    public String financialAccountDiv;
    
    /**
     * (口座番号)<BR>
     * 口座番号<BR>
     */
    public String financialAccountCode;
    
    /**
     * (振替手数料区分)<BR>
     * 振替手数料区分<BR>
     * <BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String transferCommissionDiv;
    
    /**
     * @@roseuid 41B45E790222
     */
    public WEB3AccOpenTransferBankInfo() 
    {
     
    }
}
@
