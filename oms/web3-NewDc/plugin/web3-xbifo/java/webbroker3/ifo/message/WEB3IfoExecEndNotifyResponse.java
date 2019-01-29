head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecEndNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP出来終了通知レスポンスクラス(WEB3IfoExecEndNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 呉艶飛 新規作成
*/

package webbroker3.ifo.message;


import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;

/**
 * (先物OP出来終了通知レスポンス)<BR>
 * 先物OP出来終了通知レスポンスクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3IfoExecEndNotifyResponse extends WEB3BackResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "ifo_execEndNotify";
        
    /**
     * serialVersionUID<BR>
     */
     public final static long serialVersionUID = 200406112120L;
        
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3IfoExecEndNotifyResponse(WEB3BackRequest l_request) 
    {
        super(l_request);
    }
}
@
