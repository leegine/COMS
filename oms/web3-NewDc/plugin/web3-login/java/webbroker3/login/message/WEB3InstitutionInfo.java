head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3InstitutionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 会社情報をxTradeクライアントに渡す為のメッセージクラス(WEB3InstitutionInfo.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/05 菊地(SRA)
 */

package webbroker3.login.message;


import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (会社情報)<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3InstitutionInfo extends Message 
{
   /**
    * (会社ID)
    */
   public long institutionID;
   
   /**
    * (会社コード)
    */
   public String institutionCode;
   
   /**
    * (会社名)
    */
   public String institutionName;
   
   /**
    * @@roseuid 4021A2F203D8
    */
   public WEB3InstitutionInfo() 
   {
    
   }
}
@
