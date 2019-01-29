head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金リクエスト(WEB3AioSonarCashTransRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成 
                   2004/10/22 黄建 (中訊) レビュー                      
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (SONAR入出金リクエスト)<BR>
 * SONAR入出金リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_sonar_cash_trans";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L;    
    /**
     * デフォルトコンストラク
     * @@roseuid 4158E9B40333
     */
    public WEB3AioSonarCashTransRequest() 
    {
     
    }
    
    /**
     * @@return WEB3BackResponse
     * @@roseuid 4158E9B40347
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioSonarCashTransResponse(this);
    }
}
@
