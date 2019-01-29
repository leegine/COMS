head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioOtherCountReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : その他件数照会レスポンス(WEB3AdminAioOtherCountReferenceResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/11 韋念瓊(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (その他件数照会レスポンス)<BR>
 * その他件数照会レスポンスクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioOtherCountReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_aio_other_count_reference";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;

    /**
     * (その他件数照会情報一覧)<BR>
     * その他件数照会情報一覧
     */
    public WEB3AioOtherCountReferenceUnit otherCountReferenceUnits;
    
    /**
     * @@roseuid 423552AB00FA
     */
    public WEB3AdminAioOtherCountReferenceResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminAioOtherCountReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
