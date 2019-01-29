head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託取消受付リクエストクラス(WEB3MutualCancelAcceptRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/25 黄建 (中訊) レビュー
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * 投資信託取消受付リクエストクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualCancelAcceptRequest extends WEB3BackRequest 
{
 
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_cancel_accept";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L; 
   
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40A9A10B02F9
     */
    public WEB3MutualCancelAcceptRequest() 
    {
    }
    
    /**
     * （createResponseの実装） <BR>
     * <BR>
     * 投信取消受付レスポンスオブジェクトを生成して返す。 <BR>
     * @@return WEB3BackResponse
     * @@roseuid 40A431EF004D
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3MutualCancelAcceptResponse(this);
    }
}
@
