head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualApplyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信募集注文完了レスポンスクラス(WEB3MutualApplyCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 黄建 (中訊) 新規作成
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 投信募集注文完了レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0   
 */

public class WEB3MutualApplyCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_apply_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200509261632L;
    
    /**
     * (更新時間)<BR>
     *  更新時間<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (識別番号)<BR>
     *  識別番号（注文ID）<BR>
     */
    public String orderActionId;
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualApplyCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
