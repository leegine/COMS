head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3OpeAcceptAutoLoginRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客成りすまし自動ログインを要求するリクエストクラス(WEB3OpeAcceptAutoLoginRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/08 菊地(SRA) 新規作成
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （顧客成りすまし自動ログインリクエスト）
 * 顧客成りすまし自動ログインを要求するリクエストクラス<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3OpeAcceptAutoLoginRequest extends WEB3GenRequest 
{
   public static  String TAGNAME = "request";
   public static  String PTYPE = "web3_ope_accept_autologin";
   
   /**
    * （会社コード）
    */
   public String institutionCode;
   
   /**
    * （CCオペレータの部店コード）
    */
   public String ccOpeBranchCode;
   
   /**
    * （CCオペレータコード）
    */
   public String ccOperatorCode;
   
   /**
    * （CCオペレータxTradeユーザ名）<BR>
    * 入力されたCCオペレータコードをxTradeログインユーザ名に変換した値。<BR>
    */
   public String xTradeCCOpeUsername;
   
   /**
    * （CCオペレータパスワード）
    */
   public String ccOperatorPassword;
   
   /**
    * （顧客の部店コード）
    */
   public String acceptBranchCode;
   
   /**
    * （顧客コード）<BR>
    * 顧客コード（ログイン時の口座番号）
    */
   public String acceptCode;
   
   /**
    * （顧客xTradeユーザ名）<BR>
    * 入力された顧客コードをxTradeログインユーザ名に変換した値<BR>
    */
   public String xTradeAcceptUsername;
   
   /**
    * （注文チャネル）
    */
   public String orderChannel;
   
   /**
    * （注文経路区分）<BR>
    * 固定的に「"1"：コールセンター」をセットし、サービスに渡す。<BR>
    */
   public String orderRootDiv;
   
   /**
    * （ハッシュ値）
    */
   public String hashValue;
   
   /**
    * @@roseuid 406D317E02FD
    */
   public WEB3OpeAcceptAutoLoginRequest() 
   {
    
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 406BDE660157
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3OpeAcceptAutoLoginResponse(this);    
   }
}
@
