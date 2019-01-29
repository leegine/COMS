head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LoginPwdChangeRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : ログイン時パスワード変更を要求するリクエストクラス(WEB3LoginPwdChangeRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 菊地(SRA) 新規作成
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （ログイン時パスワード変更リクエスト）<BR>
 * ログイン時パスワード変更を要求するリクエストクラス<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3LoginPwdChangeRequest extends WEB3GenRequest 
{
   public static  final String TAGNAME = "request";
   public static  final String PTYPE = "web3_login_pwdchange";
   
   /**
    * （ログイン名）<BR>
    * ログイン名（顧客コード、CCオペレータコード、または管理者コード）
    */
   public String loginName;
   
   /**
    * （旧顧客パスワード）
    */
   public String oldPassword;
   
   /**
    * （新顧客パスワード１）
    */
   public String newPassword1;
   
   /**
    * （新顧客パスワード２）
    */
   public String newPassword2;
   
   /**
    * （ログアウトフラグ）<BR>
    * パスワード変更が正常終了した後、ログアウトするかどうかを指示するフラグ。<BR>
    * 　@　@ログアウトする:"1"、ログアウトしない:"0"<BR>
    * 顧客SS遷移前のパスワード変更の場合、正常終了後にログアウトする必要がある。<BR>
    */
   public String logoutFlag;
   
   /**
    * @@roseuid 40692C4F03A9
    */
   public WEB3LoginPwdChangeRequest() 
   {
    
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 4063C0380355
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3LoginPwdChangeResponse(this);    
   }
}
@
