head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderLockUnlockResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券注文ロック区分更新レスポンス(WEB3AdminBondOrderLockUnlockResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者債券注文ロック区分更新レスポンス)<BR>
 * 管理者債券注文ロック区分更新レスポンスクラス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondOrderLockUnlockResponse extends WEB3GenResponse
{
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_order_lock_unlock";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (ロック解除ボタン区分)<BR>
     * ロック解除ボタン区分<BR>
     * <BR>
     * 0：非表示 1：解除ボタン 2：ロックボタン　@　@
     */
    public String lockDiv;
    
    /**
     * (約定変更ボタン区分)<BR>
     * 約定変更ボタン区分<BR>
     * <BR>
     * 0：非表示　@1：約定ボタン　@2：変更ボタン
     */
    public String execChgDiv;
    
    /**
     * (取消ボタン区分)<BR>
     * 取消ボタン区分<BR>
     * <BR>
     * 0：非表示　@1：取消ボタン
     */
    public String cancelDiv;
    
    /**
     * (更新時間)<BR>
     * 更新時間
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * @@roseuid 44E3363903A9
     */
    public WEB3AdminBondOrderLockUnlockResponse() 
    {
     
    }
    
    /**
     *　@コンストラクタ。<BR>
     *　@指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     *　@@@param l_request - リクエストオブジェクト
     */
    public WEB3AdminBondOrderLockUnlockResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }
}
@
