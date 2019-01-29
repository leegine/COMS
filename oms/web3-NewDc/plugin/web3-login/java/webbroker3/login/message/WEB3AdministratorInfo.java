head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AdministratorInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者情報をxTradeクライアントに渡す為のメッセージクラス(WEB3AdministratorInfo.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 菊地(SRA)
Revesion History    : 2006/09/1 栄イ (中訊)仕様変更・モデル031
 */

package webbroker3.login.message;


import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * （管理者情報）
 * 管理者情報をxTradeクライアントに渡す為のメッセージクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3AdministratorInfo extends Message 
{
   
   /**
    * （管理者ID）
    */
   public long administratorID;
   
   /**
    * （管理者コード）
    */
   public String administratorCode;
   
   /**
    * （xTradeユーザ名）
    */
   public String xTradeUsername;
   
   /**
    * （管理者名）
    */
   public String administratorName;
   
   /**
    * （権限レベル）
    */
   public String permissionLevel;
   
   /**
    * （最終ログイン時刻）
    */
   public Date lastLoginTime;
   
   /**
    * （全部店許可フラグ）
    *  全部店許可=true／自部店のみ許可=false
    */
   public boolean allBranchPermissionFlag;
   
   /**
    * （DIR管理者フラグ）
    *  0 ：　@DIR管理者 
    *  1 ：　@通常管理者 
    *  2 ：　@通常管理者(申請者) 
    *  3 ：　@通常管理者(承認者)
    */
   public String dirAdminFlag;
   
   /**
    * @@roseuid 408F4C1F006A
    */
   public WEB3AdministratorInfo() 
   {
    
   }
}
@
