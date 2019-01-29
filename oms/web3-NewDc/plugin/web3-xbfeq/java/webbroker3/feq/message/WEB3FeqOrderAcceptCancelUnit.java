head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.31.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptCancelUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文受付取消情報(WEB3FeqOrderAcceptCancelUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/

package webbroker3.feq.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (外国株式注文受付取消情報)<BR>
 * 外国株式注文受付取消明細クラス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptCancelUnit extends Message 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptCancelUnit.class);
        
    /**
     * (注文ID)<BR>
     * 注文ID
     */
    public String orderId;
    
    /**
     * (変更後受付区分)<BR>
     * 変更後受付区分<BR>
     * <BR>
     * 01：注文受付済<BR>
     * 02：注文受付エラー<BR>
     * 03：注文受付済取消<BR>
     * <BR>
     * 11：訂正済<BR>
     * 12：訂正エラー<BR>
     * <BR>
     * 21：取消済<BR>
     * 22：取消エラー<BR>
     * <BR>
     * 31：出来ず<BR>
     */
    public String aftChangeAcceptDiv;
    
    /**
     * (外国株式注文受付取消情報)<BR>
     * コンストラクタ
     * @@roseuid 4201F2E0017C
     */
    public WEB3FeqOrderAcceptCancelUnit() 
    {
     
    }
    
    /**
     * リクエストデータのチェックを行う。<BR>
     * <BR>
     * １）　@注文ＩＤのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * ２）　@変更後区分のチェック<BR>
     * 　@※入力がある場合のみ<BR>
     * 　@２－１）　@不正なコード値の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02020<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A55527015E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@注文ＩＤのチェック
        //１－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + STR_METHOD_NAME,
                " 注文IDが未指定です。"); 
        }
        
        //２）　@変更後区分のチェック
        //※入力がある場合のみ
        if (!WEB3StringTypeUtility.isEmpty(this.aftChangeAcceptDiv))
        {
            //２－１）　@不正なコード値の場合、例外をスローする。
            if (!(WEB3FeqAcceptTypeDef.CANCEL.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CANCEL_ERROR.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CHANGE_ERROR.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CHANGED.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(this.aftChangeAcceptDiv) || 
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR.equals(this.aftChangeAcceptDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02020,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 入力された変更後区分が不正なコード値です:" + this.aftChangeAcceptDiv); 
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
