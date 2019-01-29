head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.27.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SetAccountRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客成りすましを要求するリクエストクラス(WEB3SetAccountRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/17 菊地(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.*;

/**
 * (顧客成りすましリクエスト)<BR>
 * 顧客成りすましを要求するリクエストクラス<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3SetAccountRequest extends WEB3GenRequest 
{
   public static  String TAGNAME = "request";
   public static  String PTYPE = "web3_login_set_account";
   
   /**
    * (部店コード)<BR>
    * 成りすます顧客の部店コード
    */
   public String branchCode;
   
   /**
    * (顧客コード)<BR>
    * 顧客コード（ログイン時の口座番号）
    */
   public String acceptCode;
   
   /**
    * (xTradeユーザ名)<BR>
    * 入力された顧客コードをxTradeログインユーザ名に変換した値
    */
   public String xTradeUsername;

     /**
      * 顧客ID
      * Login時のなんちゃってaccountID
      */
     public String account_id;

   /**
    * (顧客パスワード)<BR>
    * 成りすまし時パスワードチェックなし部店が有り得るので、未設定を許可する。
    */
   public String acceptPassword;
   
   /**
    * (注文チャネル)
    */
   public String orderChannel;
   
   /**
    * (注文経路区分)<BR>
    * 固定的に「"1"：コールセンター」をセットし、サービスに渡す。
    */
   public String orderRootDiv;
   
   /**
    * （パスワードチェック有無フラグ）
    */
   public String passwordCheckFlag;
   
   /**
    * @@roseuid 4045C89302AB
    */
   public WEB3SetAccountRequest() 
   {
    
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。
    * @@return WEB3GenResponse
    * @@roseuid 4045C72001E0
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3SetAccountResponse(this);    
   }
}
@
