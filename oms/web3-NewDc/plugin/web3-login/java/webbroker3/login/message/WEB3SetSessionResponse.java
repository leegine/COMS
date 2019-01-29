head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SetSessionResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : セッション属性設定結果を返すレスポンスクラス(WEB3SetSessionResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/26 菊地(SRA)
 */
 
package webbroker3.login.message;


import webbroker3.common.message.WEB3GenResponse;


/**
 * (セッション属性設定レスポンス)<BR>
 * セッション属性設定結果を返すレスポンスクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3SetSessionResponse extends WEB3GenResponse 
{
   /**
    * TAGNAME
    */
   public static final String TAGNAME = "response";
   
   /**
    * PTYPE
    */
   public static final String PTYPE   = "web3_set_session";
   
   /**
    * SerialVersionUID
    */
   public final static long serialVersionUID = 200402251830L;
   
   /**
    * デフォルトコンストラクタ。
    */
   public WEB3SetSessionResponse() 
   {
    
   }
   
   /**
    * コンストラクタ。<BR>
    * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<BR>
    *<BR>
    * @@param l_request
    */
   public WEB3SetSessionResponse(WEB3SetSessionRequest l_request)
   {
       super(l_request);
   }
}
@
