head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransForeignRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金（外貨）リクエスト(WEB3AioSonarCashTransForeignRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 車進 (中訊) 新規作成                  
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * (SONAR入出金（外貨）リクエスト)<BR>
 * SONAR入出金（外貨）リクエストクラス<BR>
 * 
 * @@author 車進(中訊)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignRequest extends WEB3AioSonarCashTransRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_sonar_cash_trans_foreign";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608281448L;
    
    /**
     * @@roseuid 44EFFBD7029F
     */
    public WEB3AioSonarCashTransForeignRequest() 
    {
     
    }
    
    /**
     *（createResponseの実装）<BR>
     * <BR>
     * SONAR入出金（外貨）レスポンスオブジェクトを返却する。<BR>
     * @@return WEB3BackResponse
     * @@roseuid 4158E9B40347
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioSonarCashTransForeignResponse(this);
    }
}
@
