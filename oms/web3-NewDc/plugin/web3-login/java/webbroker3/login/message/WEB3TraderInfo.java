head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.27.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3TraderInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータ情報をxTradeクライアントに渡す為のメッセージクラス(WEB3TraderInfo.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 菊地(SRA)
Revesion History    : 2007/07/23 周墨洋 (中訊) 仕様変更・モデルNo.040
*/

package webbroker3.login.message;


import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (CCオペレータ情報)<BR>
 * CCオペレータ情報をxTradeクライアントに渡す為のメッセージクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3TraderInfo extends Message 
{
   /**
    * （オペレータ部店コード）<BR>
    * オペレータの部店コード。
    */
   public String branchCode;
   
   /**
    * （扱者ID）
    */
   public long traderID;
   
   /**
    * (扱者コード)
    */
   public String traderCode;
   
   /**
    * (xTradeユーザ名)
    */
   public String xTradeUsername;
   
   /**
    * (扱者名（漢字）)
    */
   public String nameKanji;
   
   /**
    * (扱者名（カナ）)
    */
   public String nameKana;
   
   /**
    * （代行注文可否フラグ）<BR>
    * 代行注文が可能なオペレータかどうか判断するフラグ。
    */
   public String accountOrderFlag;
   
   /**
    * (最終ログイン時刻)
    */
   public Date lastLoginTime;

   /**
    * （所属コード）<BR>
    * 所属コード
    */
   public String departmentCode;

   /**
    * @@roseuid 4021A20B00AB
    */
   public WEB3TraderInfo() 
   {
    
   }
}
@
