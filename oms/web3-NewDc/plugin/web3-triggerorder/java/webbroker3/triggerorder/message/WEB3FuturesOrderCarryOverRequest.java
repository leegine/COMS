head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.44.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOrderCarryOverRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物注文繰越レスポンスクラス(WEB3FuturesOrderCarryOverRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 艾興 (中訊) 新規作成
Revesion History : 2007/06/29 金傑  (中訊)  仕様変更 モデル 671

*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (株価指数先物注文繰越レスポンス)<BR>
 * 株価指数先物注文繰越レスポンスクラス<BR>
 */
public class WEB3FuturesOrderCarryOverRequest extends WEB3IfoOrderCarryOverMainRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_orderCarryOver";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201304L;
    
    /**
     * @@roseuid 40F7AE0D02EE
     */
    public WEB3FuturesOrderCarryOverRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * super.validate()をコールする。 <BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        super.validate();
    }
    
    /**
     * @@return WEB3BackResponse
     * @@roseuid 40F7AE0D02FD
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3FuturesOrderCarryOverResponse(this);
    }
}
@
