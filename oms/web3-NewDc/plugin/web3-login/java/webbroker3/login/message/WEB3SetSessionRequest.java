head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SetSessionRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : セッション属性設定を要求するリクエストクラス(WEB3SetSessionRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/26 菊地(SRA)
 */
 
package webbroker3.login.message;


import java.util.*;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (セッション属性設定リクエスト)<BR>
 * セッション属性設定を要求するリクエストクラス<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 */
public class WEB3SetSessionRequest extends WEB3GenRequest 
{
   /**
    * TAGNAME
    */
   public static final String TAGNAME = "request";
   
   /**
    * PTYPE
    */
   public static final String PTYPE   = "web3_set_session";
   
   /**
    * SerialVersionUID
    */
   public final static long serialVersionUID = 200402251830L;
   
   /**
    * 設定するセッション属性を格納する。<BR>
    */
   public Hashtable sessionAttributes = new Hashtable();
   
   /**
    * デフォルトコンストラクタ。
    */
   public WEB3SetSessionRequest() 
   {
    
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    *<BR>
    * @@return レスポンスオブジェクト
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3SetSessionResponse(this);
   }
}
@
