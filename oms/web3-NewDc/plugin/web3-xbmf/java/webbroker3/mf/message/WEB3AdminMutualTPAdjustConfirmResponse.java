head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPAdjustConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者余力調整確認レスポンスクラス(WEB3AdminMutualTPAdjustConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 韋念瓊 (中訊) 新規作成
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 投信管理者余力調整確認レスポンスクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminMutualTPAdjustConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_tp_adjust_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512191022L; 
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminMutualTPAdjustConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

    /**
     * (投信乗換確認レスポンス)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A8AABD0130
     */
    public WEB3AdminMutualTPAdjustConfirmResponse() 
    {
     
    }
}
@
