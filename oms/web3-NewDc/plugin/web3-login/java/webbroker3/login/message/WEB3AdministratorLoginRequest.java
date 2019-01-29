head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.27.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AdministratorLoginRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者ログインを要求するリクエストクラス(WEB3AdministratorLoginRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 菊地(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （管理者ログインリクエスト）
 * 管理者ログインを要求するリクエストクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3AdministratorLoginRequest extends WEB3GenRequest 
{
   public static  String TAGNAME = "request";
   public static  String PTYPE = "web3_administrator_login";
   
   /**
    * （会社コード）
    */
   public String institutionCode;
   
   /**
    * （部店コード）<BR>
    * 2004.04.21時点ではダミー。部店は管理者．部店IDのものを使用する。<BR>
    */
   public String branchCode;
   
   /**
    * （管理者コード）
    */
   public String administratorCode;
   
   /**
    * （xTradeユーザ名）<BR>
    * 入力された管理者コードをxTradeログインユーザ名に変換した値<BR>
    */
   public String xTradeUsername;
   
   /**
    * （管理者パスワード）
    */
   public String administratorPassword;
   
   /**
    * （ログインタイプ）<BR>
    * "0"：通常ログイン、"1"：パスワード変更の為のログイン<BR>
    */
   public String loginType;
   
   /**
    * @@roseuid 408F4C930396
    */
   public WEB3AdministratorLoginRequest() 
   {
    
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 4084F8EC01BF
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3AdministratorLoginResponse(this);
   }
}
@
