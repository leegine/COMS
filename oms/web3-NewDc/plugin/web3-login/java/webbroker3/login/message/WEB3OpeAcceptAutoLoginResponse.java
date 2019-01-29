head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3OpeAcceptAutoLoginResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客成りすまし自動ログイン結果を返すレスポンスクラス(WEB3OpeAcceptAutoLoginResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/13 菊地(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ServiceImpState;

/**
 * （顧客成りすまし自動ログインレスポンス）
 * 顧客成りすまし自動ログイン結果を返すレスポンスクラス<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3OpeAcceptAutoLoginResponse extends WEB3GenResponse 
{
   public static  String TAGNAME = "response";
   public static  String PTYPE = "web3_ope_accept_autologin";
   
   /**
    * （xTradeセッションID）
    */
   public String xTradeSessionID;
   
   /**
    * （CCオペレータ情報）
    */
   public WEB3TraderInfo ccOperatorInfo;
   
   /**
    * （顧客情報）
    */
   public WEB3AcceptInfo acceptInfo;
   
   /**
    * （会社情報）
    */
   public WEB3InstitutionInfo institutionInfo;
   
   /**
    * （部店情報）
    */
   public WEB3BranchInfo branchInfo;
   
   /**
    * （サービス実施状態）
    */
   public WEB3ServiceImpState serviceImpState;
   
   /**
    * @@roseuid 406D318601C5
    */
   public WEB3OpeAcceptAutoLoginResponse() 
   {
    
   }
   
   /**
    * コンストラクタ。<BR>
    * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<B
    * R>
    * @@param l_request - リクエストオブジェクト
    * @@roseuid 406BE17C03B8
    */
   public WEB3OpeAcceptAutoLoginResponse(WEB3OpeAcceptAutoLoginRequest l_request) 
   {
       super(l_request);    
   }
}
@
