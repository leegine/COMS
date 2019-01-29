head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOnPaymentCooperationResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金連携レスポンス (WEB3AioOnPaymentCooperationReponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 李俊 (中訊) 新規作成
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (出金連携レスポンス)<BR>
 * 出金連携レスポンスクラス
 * @@author 李俊
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationResponse  extends WEB3BackResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_on_payment_cooperation";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511161604L;
    
    /**
     * @@roseuid 41EC84F8031C
     */
    public WEB3AioOnPaymentCooperationResponse() 
    {
     
    }   

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */    
    public WEB3AioOnPaymentCooperationResponse(WEB3BackRequest l_request) 
    {
        super(l_request); 
    } 

}
@
