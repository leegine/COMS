head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.24.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3UpdateAccessTimeRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : セッションアクセス時刻更新を要求するリクエストクラス(WEB3UpdateAccessTimeRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/26 菊地(SRA)
 */
 
package webbroker3.login.message;


import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (セッションアクセス時刻更新リクエスト)<BR>
 * セッションアクセス時刻更新を要求するリクエストクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3UpdateAccessTimeRequest extends WEB3GenRequest 
{
   /**
    * TAGNAME<BR>
    */
   public static final String TAGNAME = "request";
   
   /**
    * PTYPE<BR>
    */
   public static final String PTYPE   = "web3_update_access_time";
   
   /**
    * SerialVersionUID<BR>
    */
   public final static long serialVersionUID = 200402261800L;
   
   /**
    * デフォルトコンストラクタ。<BR>
    * @@roseuid 403EF0E80267
    */
   public WEB3UpdateAccessTimeRequest() 
   {
    
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    * <BR>
    * @@return レスポンスオブジェクト
    * @@roseuid 403EF0440277
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3UpdateAccessTimeResponse(this);
   }
}
@
