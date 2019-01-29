head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioForeignCashTransAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨入出金受付リクエスト(WEB3AioForeignCashTransAcceptRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/03 徐宏偉 (中訊) 新規作成
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (外貨入出金受付リクエスト)<BR>
 * 外貨入出金受付リクエストクラス<BR>
 *<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioForeignCashTransAcceptRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "aio_foreign_cash_trans_accept";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609031518L;    
        
    public WEB3AioForeignCashTransAcceptRequest() 
    {
     
    }

    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 外貨入出金受付レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioForeignCashTransAcceptResponse(this);
    }
}
@
