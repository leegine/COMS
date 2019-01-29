head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.27.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptPasswordCheckResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客パスワードチェック結果を返すレスポンスクラス(WEB3AcceptPasswordCheckResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/10/28 齋藤　@栄三(FLJ) 新規作成
*/
 
package webbroker3.login.message;


import webbroker3.common.message.WEB3GenResponse;


/**
 * (顧客パスワードチェックレスポンス)<BR>
 * 顧客パスワードチェック結果を返すレスポンスクラス<BR>
 *<BR> 
 * @@author      Eizo Saito(FLJ)
 * @@version     1.00
 */
public class WEB3AcceptPasswordCheckResponse extends WEB3GenResponse 
{
   /**
    * TAGNAME
    */
   public static final String TAGNAME = "response";
   
   /**
    * PTYPE
    */
   public static final String PTYPE   = "web3_password_check";
   
   /**
    * SerialVersionUID
    */
   public final static long serialVersionUID = 200510281830L;

   /**
    * (パスワードチェック結果)<BR>
    */
   public boolean certifiedPasswordFlg;
   
   /**
    * コンストラクタ
    */
   public WEB3AcceptPasswordCheckResponse() 
   {
    
   }
   
   /**
    * コンストラクタ。<BR>
    * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<BR>
    * @@param l_request
    */
   public WEB3AcceptPasswordCheckResponse(WEB3AcceptPasswordCheckRequest l_request)
   {
       super(l_request);
   }
}
@
