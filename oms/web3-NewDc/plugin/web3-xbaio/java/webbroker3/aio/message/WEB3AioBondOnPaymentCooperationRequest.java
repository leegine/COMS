head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioBondOnPaymentCooperationRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券出金連携リクエスト(WEB3AioBondOnPaymentCooperationRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 徐宏偉 (中訊) 新規作成
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * (債券出金連携リクエスト)<BR>
 * 債券出金連携リクエストクラス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioBondOnPaymentCooperationRequest
    extends WEB3AioOnPaymentCooperationRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_bond_on_payment_cooperation";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610170915L;

    /**
     * コンストラクタ
     */
    public WEB3AioBondOnPaymentCooperationRequest()
    {

    }

    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 債券出金連携レスポンスオブジェクトを返却する。<BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3AioBondOnPaymentCooperationResponse(this);
    }
}
@
