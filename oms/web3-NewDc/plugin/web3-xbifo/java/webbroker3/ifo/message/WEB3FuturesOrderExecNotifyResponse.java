head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderExecNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式指数先物出来通知レスポンスクラス(WEB3FuturesOrderExecNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 艾興 (中訊) 新規作成
*/
package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (株式指数先物出来通知レスポンス)<BR>
 * 株式指数先物出来通知レスポンスクラス<BR>
 */
public class WEB3FuturesOrderExecNotifyResponse extends WEB3BackResponse 
{
    /**
      * PTYPE<BR>
      */
     public static final String PTYPE = "futures_orderExecNotify";

     /**
      * SerialVersionUID<BR>
      */
     public static final long serialVersionUID = 200407221304L;
    
    /**
       * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
       * レスポンスオブジェクトを生成する。<BR>
       *<BR>
       * @@param l_request リクエストオブジェクト
       */
      protected WEB3FuturesOrderExecNotifyResponse(WEB3BackRequest l_request)
      {
          super(l_request);
      }
}
@
