head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeCancelAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP訂正取消受付レスポンス(WEB3IfoChangeCancelAcceptResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 李海波 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (先物OP訂正取消受付レスポンス)<BR>
 * 先物OP訂正取消受付レスポンスクラス<BR>
 * @@author 李海波
 * @@version 1.0 
 */
public class WEB3IfoChangeCancelAcceptResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="ifo_changeCancelAccept";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141540L;
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3IfoChangeCancelAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
