head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorLoginResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータログイン結果を返すレスポンスクラス(WEB3CCOperatorLoginResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 菊地(SRA)
 */

package webbroker3.login.message;


import webbroker3.common.message.WEB3GenResponse;
import webbroker3.login.message.WEB3TraderInfo;
import webbroker3.login.message.WEB3InstitutionInfo;


/**
 * (CCオペレータログインレスポンス)<BR>
 * CCオペレータログイン結果を返すレスポンスクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3CCOperatorLoginResponse extends WEB3GenResponse 
{
   public static  String TAGNAME = "response";
   public static  String PTYPE = "web3_ccoperator_login";
   
   /**
    * (xTradeセッションID)<BR>
    */
   public String xTradeSessionID;
   
   /**
    * (CCオペレータ情報)<BR>
    */
   public WEB3TraderInfo ccOperatorInfo;
   
   /**
    * (会社情報)<BR>
    */
   public WEB3InstitutionInfo institutionInfo;
   
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
    * コンストラクタ。<BR>
    * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<BR>
    * @@param l_request
    * @@roseuid 4045C6C40347
    */
   public WEB3CCOperatorLoginResponse(WEB3CCOperatorLoginRequest l_request)
   {
       super(l_request);
   }
   
   /**
    * @@roseuid 4045C22803B4
    */
   public WEB3CCOperatorLoginResponse()
   {
    
   }
}
@
