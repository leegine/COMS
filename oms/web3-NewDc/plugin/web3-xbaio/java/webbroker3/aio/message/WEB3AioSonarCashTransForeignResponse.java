head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransForeignResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金（外貨）レスポンス(WEB3AioSonarCashTransForeignResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 車進 (中訊) 新規作成               
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;


/**
 * (SONAR入出金（外貨）レスポンス)<BR>
 * SONAR入出金（外貨）レスポンスクラス<BR>
 * 
 * @@author 車進(中訊)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignResponse extends WEB3AioSonarCashTransResponse 
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
     * @@roseuid 44EFFBD70251
     */
    public WEB3AioSonarCashTransForeignResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioSonarCashTransForeignResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }  
}
@
