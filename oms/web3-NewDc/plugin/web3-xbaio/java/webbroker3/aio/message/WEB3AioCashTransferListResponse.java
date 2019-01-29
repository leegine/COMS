head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashTransferListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金一覧レスポンスクラス(WEB3AioCashTransferListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 屈陽 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー  
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (入出金一覧レスポンス)<BR>
 * 入出金一覧レスポンスクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashTransferListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cash_transfer_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410111703L;    
    /**
     * (入出金一覧)<BR>
     * 入出金履歴の一覧
     */
    public WEB3AioCashTransUnit[] cashTransUnits;
        
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashTransferListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

    
}
@
