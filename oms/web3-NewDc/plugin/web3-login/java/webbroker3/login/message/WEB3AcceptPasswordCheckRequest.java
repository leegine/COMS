head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptPasswordCheckRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客パスワードチェックを要求するリクエストクラス(WEB3AcceptPasswordCheckRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/10/28 齋藤　@栄三(FLJ) 新規作成
*/
 
package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (顧客パスワードチェックリクエスト)<BR>
 * 顧客パスワードチェックを要求するリクエストクラス<BR>
 * <BR> 
 * @@author      Eizo Saito(FLJ)
 * @@version     1.00
 */
public class WEB3AcceptPasswordCheckRequest extends WEB3GenRequest 
{
   
   /**
    * TAGNAME
    */
   public static  final String TAGNAME = "request";
   
   /**
    * PTYPE
    */
   public static  final String PTYPE = "web3_password_check";
   
   /**
    * SerialVersionUID
    */
   public static  final long serialVersionUID = 200510281830L;
   
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
    * (顧客ID)<BR>
    * Affinity対応のaccountID
    */
   public String account_id;

   /**
    * (顧客パスワード)
    */
   public String acceptPassword;
   
   /**
    * コンストラクタ
    */
   public WEB3AcceptPasswordCheckRequest() 
   {
    
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3AcceptPasswordCheckResponse(this);    
   }
}
@
