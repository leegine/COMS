head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.37.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderLockUnlockRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券注文ロック区分更新リクエスト(WEB3AdminBondOrderLockUnlockRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.define.WEB3BondOrderLockDivDef;


/**
 * (管理者債券注文ロック区分更新リクエスト)<BR>
 * 管理者債券注文ロック区分更新リクエストクラス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondOrderLockUnlockRequest extends WEB3GenRequest
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderLockUnlockRequest.class);
    
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_order_lock_unlock";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (注文ロック区分)<BR>
     * 注文ロック区分<BR>
     * 1：ロック解除　@2：ロック
     */
    public String orderLockDiv;
    
    /**
     * (注文ID)<BR>
     * 注文ID
     */
    public String id;
    
    /**
     * @@roseuid 44E336390242
     */
    public WEB3AdminBondOrderLockUnlockRequest() 
    {
     
    }   
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR> 
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * 注文ロック区分<BR>
     * ・ロック解除<BR>
     * ・ロック<BR>
     * 上記以外の場合は、エラーをスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02580<BR>
     * <BR>
     * 注文ID：<BR>
     * ・nullの場合は、エラーをスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00600
     * @@throws WEB3BaseException 
     * @@roseuid 44C426C10155
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //注文ロック区分
        //・ロック解除
        //・ロック
        //上記以外の場合は、エラーをスローする。
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_02580
        if ((!WEB3BondOrderLockDivDef.LOCK_RELEAS.equals(this.orderLockDiv)) && 
                (!WEB3BondOrderLockDivDef.LOCK.equals(this.orderLockDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02580,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文ロック区分が”ロック解除”、”ロック”以外の値です。");
        }
        
        //注文ID：
        //・nullの場合は、エラーをスローする。
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_00600
        if (this.id == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 管理者注文ロック区分更新レスポンスを生成し返す
     * @@return WEB3GenResponse
     * @@roseuid 44C426B700A9
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminBondOrderLockUnlockResponse(this);
    }
}
@
