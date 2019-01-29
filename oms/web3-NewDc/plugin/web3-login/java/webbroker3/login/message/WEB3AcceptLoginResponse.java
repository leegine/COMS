head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptLoginResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客ログイン結果を返すレスポンスクラス(WEB3AcceptLoginResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/13 菊地(SRA)
 */
 
package webbroker3.login.message;


import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ServiceImpState;


/**
 * (顧客ログインレスポンス)<BR>
 * 顧客ログイン結果を返すレスポンスクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3AcceptLoginResponse extends WEB3GenResponse 
{
   /**
    * TAGNAME
    */
   public static final String TAGNAME = "response";
   
   /**
    * PTYPE
    */
   public static final String PTYPE   = "web3_accept_login";
   
   /**
    * SerialVersionUID
    */
   public final static long serialVersionUID = 200402141830L;
   
   /**
    * (xTradeセッションID)
    */
   public String xTradeSessionID;
   
   /**
    * (顧客情報)
    */
   public WEB3AcceptInfo acceptInfo;
   
   /**
    * (会社情報)
    */
   public WEB3InstitutionInfo institutionInfo;
   
   /**
    * (部店情報)
    */
   public WEB3BranchInfo branchInfo;
   
   /**
    * (サービス実施状態)
    */
   public WEB3ServiceImpState serviceImpState;
   
   /**
    * (パスワード変更フラグ)<BR>
    * ログイン時パスワード変更の有無を表すフラグ<BR>
    * 　@"0"：変更なし<BR>
    * 　@"1"：変更必要<BR>
    */
   public String passwordChangeFlag;
   
   /**
    * (先頭画面ID)<BR>
    * ユーザ指定の先頭画面を表すID<BR>
    */
   public String topPageID;
   
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
    * @@roseuid 4021A5190138
    */
   public WEB3AcceptLoginResponse() 
   {
    
   }
   
   /**
    * コンストラクタ。<BR>
    * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<BR>
    * @@param l_request
    * @@roseuid 403EF23602B6
    */
   public WEB3AcceptLoginResponse(WEB3AcceptLoginRequest l_request)
   {
       super(l_request);
   }
}
@
