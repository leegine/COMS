head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptSlingshotResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客SS遷移結果を返すレスポンスクラス(WEB3AcceptSlingshotResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/21 菊地(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （顧客SS遷移レスポンス）
 * 顧客SS遷移結果を返すレスポンスクラス<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3AcceptSlingshotResponse extends WEB3GenResponse 
{
   public static  final String TAGNAME = "response";
   public static  final String PTYPE = "web3_accept_slingshot";
   
   /**
    * (xTradeセッションID)<BR>
    * パスワード変更が必要な場合のみ有効。<BR>
    * パスワード変更要求は、このセッションに対して発行する。<BR>
    */
   public String xTradeSessionID;
   
   /**
    * （ハッシュ値）<BR>
    * スリングショット側に受け渡す認証用のハッシュ値<BR>
    */
   public String hashValue;
   
   /**
    * （CD付顧客コード）
    * CD付７桁の顧客コード。
    */
   public String acceptCodeCD;
   
   /**
    * (パスワード変更フラグ)<BR>
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
    * @@roseuid 406906D20260
    */
   public WEB3AcceptSlingshotResponse() 
   {
    
   }
   
   /**
    * コンストラクタ。<BR>
    * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<B
    * R>
    * @@param l_request
    * @@roseuid 40627FFB0138
    */
   public WEB3AcceptSlingshotResponse(WEB3AcceptSlingshotRequest l_request) 
   {
       super(l_request);    
   }
}
@
