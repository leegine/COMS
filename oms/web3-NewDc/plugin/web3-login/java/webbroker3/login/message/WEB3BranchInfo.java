head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.27.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3BranchInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 部店情報をxTradeクライアントに渡す為のメッセージクラス(WEB3BranchInfo.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 菊地(SRA)
 */

package webbroker3.login.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (部店情報)<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3BranchInfo extends Message 
{
   
   /**
    * (部店ID)
    */
   public long branchID;
   
   /**
    * (部店コード)
    */
   public String branchCode;
   
   /**
    * (部店名)
    */
   public String branchName;
   
   /**
    * @@roseuid 40762E2F01F6
    */
   public WEB3BranchInfo() 
   {
    
   }
}
@
