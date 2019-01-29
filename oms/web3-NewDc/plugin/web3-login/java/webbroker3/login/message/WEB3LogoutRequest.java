head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LogoutRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : ログアウトを要求するリクエストクラス(WEB3LogoutRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/26 菊地(SRA)
 */
 
package webbroker3.login.message;


import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ログアウトリクエスト)<BR>
 * ログアウトを要求するリクエストクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3LogoutRequest extends WEB3GenRequest 
{
   /**
    * TAGNAME<BR>
    */
   public static final String TAGNAME = "request";
   
   /**
    * PTYPE<BR>
    */
   public static final String PTYPE   = "web3_logout";
   
   /**
    * SerialVersionUID<BR>
    */
   public final static long serialVersionUID = 200402171348L;
   
   /**
    * デフォルトコンストラクタ。<BR>
    * @@roseuid 403EEBDA0035
    */
   public WEB3LogoutRequest() 
   {
    
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 403EEEB403A0
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3LogoutResponse(this);
   }
}
@
