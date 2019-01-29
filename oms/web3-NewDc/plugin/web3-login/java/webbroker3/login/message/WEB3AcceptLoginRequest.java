head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptLoginRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客ログインを要求するリクエストクラス(WEB3AcceptLoginRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/03/30 菊地(SRA) 新規作成
Revesion History    : 2007/06/13 謝旋 仕様変更モデルNo.033
 */
 
package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (顧客ログインリクエスト)<BR>
 * 顧客ログインを要求するリクエストクラス<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3AcceptLoginRequest extends WEB3GenRequest 
{
   
   /**
    * TAGNAME
    */
   public static  final String TAGNAME = "request";
   
   /**
    * PTYPE
    */
   public static  final String PTYPE = "web3_accept_login";
   
   /**
    * SerialVersionUID
    */
   public static  final long serialVersionUID = 200402141830L;
   
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
      * 顧客ID
      * Login時のなんちゃってaccountID
      */
     public String account_id;

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
    * (IPアドレス)
    */
   public String ipAddress;

   /**
    * (お客様識別番号)
    */
   public String discriminationCode;

   /**
    * @@roseuid 403EDB16018D
    */
   public WEB3AcceptLoginRequest() 
   {
    
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 403EECCE0390
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3AcceptLoginResponse(this);    
   }
}
@
