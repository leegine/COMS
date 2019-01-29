head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SetAccountResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客成りすまし結果を返すレスポンスクラス(WEB3SetAccountResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/13 菊地(SRA)
 */

package webbroker3.login.message;


import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ServiceImpState;


/**
 * (顧客成りすましレスポンス)<BR>
 * 顧客成りすまし結果を返すレスポンスクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3SetAccountResponse extends WEB3GenResponse
{
   public static  String TAGNAME = "response";
   public static  String PTYPE = "web3_login_set_account";
   
   /**
    * (xTradeセッションID)
    */
   public String xTradeSessionID;
   
   /**
    * (顧客情報)
    */
   public WEB3AcceptInfo acceptInfo;
   
   /**
    * （部店情報）<BR>
    * 成りすました顧客の部店情報。
    */
   public WEB3BranchInfo branchInfo;
   
   /**
    * (サービス実施状態)
    */
   public WEB3ServiceImpState serviceImpState;
   
   /**
    * (先頭画面ID)
    * ユーザ指定の先頭画面を表すID<BR>
    */
   public String topPageID;
   
   /**
    * コンストラクタ。<BR>
    * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。
    * @@param l_request
    * @@roseuid 4045C9490078
    */
   public WEB3SetAccountResponse(WEB3SetAccountRequest l_request)
   {
       super(l_request);
   }
   
   /**
    * @@roseuid 4045C89F02DA
    */
   public WEB3SetAccountResponse()
   {
    
   }
}
@
