head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPAdjustConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者余力調整確認リクエストクラス(WEB3AdminMutualTPAdjustConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 韋念瓊 (中訊) 新規作成
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * 投信管理者余力調整確認リクエストクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminMutualTPAdjustConfirmRequest extends WEB3AdminMutualTPAdjustCommonRequest
{   
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_tp_adjust_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512191015L;

    /**
     * (投信管理者余力調整確認リクエスト)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A8A1C4018D
     */
    public WEB3AdminMutualTPAdjustConfirmRequest()
    {

    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信管理者余力調整確認レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A8A1CE00E1
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualTPAdjustConfirmResponse(this);
    }
}
@
