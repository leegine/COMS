head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LoginPwdChangeResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : ログイン時パスワード変更結果を返すレスポンスクラス(WEB3LoginPwdChangeResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/03/31 菊地(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （ログイン時パスワード変更レスポンス）<BR>
 * ログイン時パスワード変更結果を返すレスポンスクラス<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3LoginPwdChangeResponse extends WEB3GenResponse 
{
   public static  final String TAGNAME = "response";
   public static  final String PTYPE = "web3_login_pwdchange";
   
   /**
    * （再ログイン実施フラグ）<BR>
    * パスワード変更が正常終了した後、再ログインを実施するかを指示するフラグ。<BR>
    * パスワード変更後再ログイン実施部店かを表すものであり、リクエスト・パラメータ<BR>
    * の指示によりログアウトした場合は「再ログインする」とはならない。<BR>
    * 　@　@再ログインする:"1"、再ログインしない:"0"<BR>
    */
   public String reLoginFlag;
   
   /**
    * @@roseuid 40692C570186
    */
   public WEB3LoginPwdChangeResponse() 
   {
    
   }
   
   /**
    * コンストラクタ。<BR>
    * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<B
    * R>
    * @@param l_request
    * @@roseuid 4063C00C0123
    */
   public WEB3LoginPwdChangeResponse(WEB3LoginPwdChangeRequest l_request) 
   {
       super(l_request);    
   }
}
@
