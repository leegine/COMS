head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.27.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptAutoLoginRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客自動ログインを要求するリクエストクラス(WEB3AcceptAutoLoginRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/08 菊地(SRA) 新規作成
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （顧客自動ログインリクエスト）
 * 顧客自動ログインを要求するリクエストクラス<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3AcceptAutoLoginRequest extends WEB3GenRequest 
{
   public static  final String TAGNAME = "request";
   public static  final String PTYPE = "web3_accept_autologin";
   
   /**
    * (会社コード)
    */
   public String institutionCode;
   
   /**
    * (部店コード)
    */
   public String branchCode;
   
   /**
    * （顧客コード）
    */
   public String acceptCode;
   
   /**
    * (xTradeユーザ名)<BR>
    * 入力された顧客コードをxTradeログインユーザ名に変換した値<BR>
    */
   public String xTradeUsername;
   
   /**
    * (顧客パスワード)
    */
   public String acceptPassword;
   
   /**
    * (注文チャネル)
    */
   public String orderChannel;
   
   /**
    * (注文経路区分)
    */
   public String orderRootDiv;
   
   /**
    * （ハッシュ値）
    */
   public String hashValue;
   
   /**
    * @@roseuid 40691A1A01C4
    */
   public WEB3AcceptAutoLoginRequest() 
   {
    
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 4062B2C00128
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3AcceptAutoLoginResponse(this);    
   }
}
@
