head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsChangeCancelNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式指数オプション訂正取消通知レスポンス(WEB3OptionsChangeCancelNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 李海波 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;

/**
 * (株式指数オプション訂正取消通知レスポンス)<BR>
 * 株式指数オプション訂正取消通知レスポンスクラス<BR>
 * @@author 李海波
 * @@version 1.0  
 */
public class WEB3OptionsChangeCancelNotifyResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public static  final String PTYPE = "options_changeCancelNotify";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141526L;
        
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsChangeCancelNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
