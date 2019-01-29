head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptCancelCertificationRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式注文受付取消認証共通リクエスト(WEB3AdminFeqOrderAcceptCancelCertificationRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者外国株式注文受付取消認証共通リクエスト)<BR>
 * 管理者外国株式注文受付取消認証共通リクエストクラス
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptCancelCertificationRequest extends WEB3GenRequest 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptCancelCertificationRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAcceptCancelCertification";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (注文受付取消一覧)<BR>
     * 外国株式注文受付取消情報の配列
     */
    public WEB3FeqOrderAcceptCancelUnit[] orderAcceptCancelList;
    
    /**
     * @@roseuid 42CE39FC007D
     */
    public WEB3AdminFeqOrderAcceptCancelCertificationRequest() 
    {
     
    }
    
    /**
     * リクエストデータのチェックを行う<BR>
     * <BR>
     * １）　@注文受付取消一覧[]のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02043<BR>
     * 　@１－２）　@配列の各要素毎に、注文受付取消状況.validate()をコールする。
     * @@throws WEB3BaseException
     * @@roseuid 42A555D5021A
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@注文受付取消一覧[]のチェック
        //１－１）　@未入力の場合、例外をスローする。
        if (this.orderAcceptCancelList == null || this.orderAcceptCancelList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02043,
                this.getClass().getName() + STR_METHOD_NAME,
                " 注文受付取消一覧が未入力です。");         
        }

        //１－２）　@配列の各要素毎に、注文受付取消状況.validate()をコールする。
        int l_intCnt = this.orderAcceptCancelList.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3FeqOrderAcceptCancelUnit l_unit = orderAcceptCancelList[i];
            if (l_unit != null)
            {
                l_unit.validate();
            }
        }
        
        log.exiting(STR_METHOD_NAME); 
     
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
