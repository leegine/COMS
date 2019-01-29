head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorLoginRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータログインを要求するリクエストクラス(WEB3CCOperatorLoginRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 菊地(SRA)
 */

package webbroker3.login.message;


import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (CCオペレータログインリクエスト)<BR>
 * CCオペレータログインを要求するリクエストクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3CCOperatorLoginRequest extends WEB3GenRequest
{
   public static  String TAGNAME = "request";
   public static  String PTYPE = "web3_ccoperator_login";
   
   /**
    * (会社コード)
    */
   public String institutionCode;
   
   /**
    * (部店コード)
    */
   public String branchCode;
   
   /**
    * (CCオペレータコード)
    */
   public String ccOperatorCode;
   
   /**
    * (xTradeユーザ名)<BR>
    * 入力されたCCオペレータコードをxTradeログインユーザ名に変換した値。
    */
   public String xTradeUsername;
   
   /**
    * (CCオペレータパスワード)
    */
   public String ccOperatorPassword;
   
   /**
    * （ログインタイプ）<BR>
    * "0"：通常ログイン、"1"：パスワード変更の為のログイン。<BR>
    * xTradeのログインタイプとは違うもの。<BR>
    */
   public String loginType;
   
   /**
    * (IPアドレス)
    */
   public String ipAddress;
   
   /**
    * @@roseuid 4045C220029B
    */
   public WEB3CCOperatorLoginRequest()
   {
    
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    * @@return WEB3GenResponse
    * @@roseuid 4045C67B0097
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3CCOperatorLoginResponse(this);
   }
}
@
