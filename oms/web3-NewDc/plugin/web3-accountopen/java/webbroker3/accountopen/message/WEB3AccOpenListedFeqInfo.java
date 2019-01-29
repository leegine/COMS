head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenListedFeqInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 上場外株情報(WEB3AccOpenListedFeqInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/13 徐大方 新規作成
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (上場外株情報)<BR>
 * 上場外株情報<BR>
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3AccOpenListedFeqInfo extends Message 
{
    /**
     * (作成区分)<BR>
     * 作成区分<BR>
     */          
    public String createDiv;
    
    /**
     * (譲渡)<BR>
     * 譲渡<BR>
     */
    public String taxationTran;
    
    /**
     * (住所（カナ）)<BR>
     * 住所（カナ）<BR>
     */        
    public String addressKana;
    
    /**
     * (送金)<BR>
     * 送金<BR>
     */
    public String transferDiv;
    
    /**
     * (銀行コード)<BR>
     * 銀行コード<BR>
     */
    public String financialInstitutionCode;
    
    /**
     * (支店コード)<BR>
     * 支店コード<BR>
     */
    public String financialBranchCode;
    
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
     * @@roseuid 41B45E7A033C
     */
    public WEB3AccOpenListedFeqInfo() 
    {
     
    }

}
@
