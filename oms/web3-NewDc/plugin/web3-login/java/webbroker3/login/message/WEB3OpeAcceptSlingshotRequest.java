head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3OpeAcceptSlingshotRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客成りすましSS遷移を要求するリクエストクラス(WEB3OpeAcceptSlingshotRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/17 菊地(SRA) 新規作成
 */
package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （顧客成りすましSS遷移リクエスト）
 * 顧客成りすましSS遷移を要求するリクエストクラス<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3OpeAcceptSlingshotRequest extends WEB3GenRequest 
{
   public static  String TAGNAME = "request";
   public static  String PTYPE = "web3_ope_accept_slingshot";
   
   /**
    * (部店コード)<BR>
    * 成りすます顧客の部店コード
    */
   public String branchCode;
   
   /**
    * （顧客コード）<BR>
    * 顧客コード（ログイン時の口座番号）<BR>
    */
   public String acceptCode;
   
   /**
    * （xTradeユーザ名）<BR>
    * 入力された顧客コードをxTradeログインユーザ名に変換した値<BR>
    */
   public String xTradeUsername;
   
   /**
    * （顧客パスワード）<BR>
    * 成りすまし時パスワードチェックなし部店が有り得るので、未設定を許可する。<BR>
    */
   public String acceptPassword;
   
   /**
    * （投資情報サービスコード）
    */
   public String infoServiceCode;
   
   /**
    * （パスワードチェック有無フラグ）
    */
   public String passwordCheckFlag;
   
   /**
    * @@roseuid 406D2D13032C
    */
   public WEB3OpeAcceptSlingshotRequest() 
   {
    
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 406BBC540128
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3OpeAcceptSlingshotResponse(this);    
   }
}
@
