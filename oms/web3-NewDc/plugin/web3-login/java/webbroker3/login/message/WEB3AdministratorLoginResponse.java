head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AdministratorLoginResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者ログイン結果を返すレスポンスクラス(WEB3AdministratorLoginResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 菊地(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （管理者ログインレスポンス）
 * 管理者ログイン結果を返すレスポンスクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3AdministratorLoginResponse extends WEB3GenResponse 
{
   public static  String TAGNAME = "response";
   public static  String PTYPE = "web3_administrator_login";
   
   /**
    * （xTradeセッションID）
    */
   public String xTradeSessionID;
   
   /**
    * （管理者情報）
    */
   public WEB3AdministratorInfo administratorInfo;
   
   /**
    * （会社情報）
    */
   public WEB3InstitutionInfo institutionInfo;
   
   /**
    * （部店情報）
    */
   public WEB3BranchInfo branchInfo;
   
   /**
    * （パスワード変更フラグ）<BR>
    * ログイン時パスワード変更の有無を表すフラグ<BR>
    * 　@"0"：変更なし<BR>
    * 　@"1"：変更必要<BR>
    */
   public String passwordChangeFlag;
   
   /**
    * (パスワード最小長)<BR>
    */
   public String passwordMinLength;
   
   /**
    * (パスワード最大長)<BR>
    */
   public String passwordMaxLength;
   
   /**
    * (パスワードチェック方式)<BR>
    *  <BR>
    * 1：　@数字のみ <BR>
    * 2：　@英数字 <BR>
    * 3：　@英数字混在 <BR>
    * <BR>
    * ※ WEB3CodeCheckModeDefにて定義済<BR> 
    */
   public String passwordCheckMethod;
   
   /**
    * @@roseuid 408F4D0300C8
    */
   public WEB3AdministratorLoginResponse() 
   {
    
   }
   
   /**
    * コンストラクタ。<BR>
    * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<B
    * R>
    * @@param l_request
    * @@roseuid 4084F9A202B9
    */
   public WEB3AdministratorLoginResponse(WEB3AdministratorLoginRequest l_request) 
   {
       super(l_request);
   }
}
@
