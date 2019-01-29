head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOrderCarryOverResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物注文繰越リクエストクラス(WEB3FuturesOrderCarryOverRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 艾興 (中訊) 新規作成
Revesion History : 2007/06/29 金傑  (中訊)  仕様変更 モデル 671
*/
package webbroker3.triggerorder.message;


/**
 * (株価指数先物注文繰越リクエスト)<BR>
 * 株価指数先物注文繰越リクエストクラス<BR>
 */
public class WEB3FuturesOrderCarryOverResponse extends WEB3IfoOrderCarryOverMainResponse
{
    /**
    * PTYPE<BR>
    */
    public static final String PTYPE = "futures_orderCarryOver";

     /**
      * SerialVersionUID<BR>
      */
     public static final long serialVersionUID = 200407221304L;
   
    /**
     * @@roseuid 40F7AE0D036B
     */
    public WEB3FuturesOrderCarryOverResponse()
    {

    }
    protected WEB3FuturesOrderCarryOverResponse(WEB3FuturesOrderCarryOverRequest l_request)
    {
        super(l_request);
    }
}
@
