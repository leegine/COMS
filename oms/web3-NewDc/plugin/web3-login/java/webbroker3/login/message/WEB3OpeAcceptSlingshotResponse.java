head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3OpeAcceptSlingshotResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客成りすましSS遷移結果を返すレスポンスクラス(WEB3OpeAcceptSlingshotResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/21 菊地(SRA)
 */

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （顧客成りすましSS遷移レスポンス）
 * 顧客成りすましSS遷移結果を返すレスポンスクラス<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3OpeAcceptSlingshotResponse extends WEB3GenResponse 
{
   public static  String TAGNAME = "response";
   public static  String PTYPE = "web3_ope_accept_slingshot";
   
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
    * @@roseuid 406D2D1B0290
    */
   public WEB3OpeAcceptSlingshotResponse() 
   {
    
   }
   
   /**
    * コンストラクタ。<BR>
    * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<B
    * R>
    * @@param l_request
    * @@roseuid 406BBC6F0261
    */
   public WEB3OpeAcceptSlingshotResponse(WEB3OpeAcceptSlingshotRequest l_request) 
   {
       super(l_request);    
   }
}
@
