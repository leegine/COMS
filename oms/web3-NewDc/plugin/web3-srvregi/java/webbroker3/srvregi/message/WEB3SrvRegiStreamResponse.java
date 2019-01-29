head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStreamResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用債券連携レスポンス(WEB3SrvRegiStreamResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/19 馮海濤 新規作成 モデル370、モデル375
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (サービス利用債券連携レスポンス)<BR>
 * サービス利用債券連携レスポンス<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public class WEB3SrvRegiStreamResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_stream";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200805191659L;

   /**
    * (注文№)<BR>
    * 注文№<BR>
    */
   public String orderNo;

   /**
    * (サービス利用債券連携レスポンス)<BR>
    * デフォルトコンストラクタ<BR>
    * @@roseuid 48155548019C
    */
   public WEB3SrvRegiStreamResponse()
   {

   }

   /**
    * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
    * レスポンスオブジェクトを生成する。<BR>
    *<BR>
    * @@param l_request - (リクエストオブジェクト)<BR>
    * リクエストオブジェクト<BR>
    */
   protected WEB3SrvRegiStreamResponse(WEB3GenRequest l_request)
   {
       super(l_request);
   }
}@
