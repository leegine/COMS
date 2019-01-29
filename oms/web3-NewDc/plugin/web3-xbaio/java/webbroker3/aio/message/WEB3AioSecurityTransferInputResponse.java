head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替入力レスポンス(WEB3AioSecurityTransferInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (証券振替入力レスポンス)<BR>
 * 証券振替入力レスポンスクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412070946L;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名
     */
    public String productName; 
    
    /**
     * (振替可能数量)<BR>
     * 選択された銘柄の振替可能数量（保有数量）
     */
    public String changePossQuantity;
    
    /**
     * (評価額)<BR>
     * 選択された銘柄の評価額
     */
    public String marketValue;
    
    /**
     * @@roseuid 41B031A102EE
     */
    public WEB3AioSecurityTransferInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioSecurityTransferInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
