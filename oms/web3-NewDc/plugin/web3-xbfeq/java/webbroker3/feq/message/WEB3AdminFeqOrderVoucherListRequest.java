head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.24.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderVoucherListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式当日注文伝票一覧リクエスト(WEB3AdminFeqOrderVoucherListRequest.java)
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
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者外国株式当日注文伝票一覧リクエスト)<BR>
 * 管理者外国株式当日注文伝票一覧リクエストクラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqOrderVoucherListRequest extends WEB3GenRequest 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderVoucherListRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderVoucherList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (条件一覧)<BR>
     * 外国株式当日注文伝票一覧条件の配列
     */
    public WEB3AdminFeqOrderVoucherListCondUnit[] condList;
    
    /**
     * @@roseuid 42CE39FB0251
     */
    public WEB3AdminFeqOrderVoucherListRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）条件一覧<BR>
     * <BR>
     * １－１）<BR>
     *    this.条件一覧 == null or<BR>
     *    this.条件一覧.length() == 0<BR>
     * <BR>
     *    の場合、「市場未選択」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02045<BR>
     * 
     * <BR>
     * １－２）各要素毎に以下のチェックを行う。<BR>
     * <BR>
     * １－２－１）<BR>
     *    条件.市場コード == null<BR>
     * <BR>
     *    の場合、「市場コード未設定」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02046<BR>
     * <BR>
     * １－２－２）<BR>
     *    （条件.発注日（自） != null and 条件.発注日（至） == null ） or<BR>
     *    （条件.発注日（自） == null and 条件.発注日（至） != null ） or<BR>
     *    （条件.発注日（自） != null and 条件.発注日（至） != null and <BR> 
     *      条件.発注日（自） > 条件.発注日（至） ）<BR> 
     * <BR>
     *    の場合、「発注日条件設定エラー」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02047<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A0176803E7
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）条件一覧
        //this.条件一覧 == null or this.条件一覧.length() == 0
        //の場合、「市場未選択」の例外をスローする。
        if (this.condList == null || this.condList.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02045,
                this.getClass().getName() + STR_METHOD_NAME,
                " 市場未選択エラー。" ); 
        }
        
        //１－２）各要素毎に以下のチェックを行う。
        int l_intCnt = this.condList.length;
        for (int i =0; i < l_intCnt; i ++)
        {
            WEB3AdminFeqOrderVoucherListCondUnit l_unit = this.condList[i];
            if (l_unit != null)
            {
                //１－２－１）条件.市場コード == nullの場合、「市場コード未設定」の例外をスローする。
                if (WEB3StringTypeUtility.isEmpty(l_unit.marketCode))
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02046,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 市場コード未設定エラー。" ); 
                }
                //１－２－２）（条件.発注日（自） != null and 条件.発注日（至） == null ） or
                //（条件.発注日（自） == null and 条件.発注日（至） != null ） or
                //（条件.発注日（自） != null and 条件.発注日（至） != null and
                //条件.発注日（自） > 条件.発注日（至） ）
                // の場合、「発注日条件設定エラー」の例外をスローする。
                if ((l_unit.orderBizDateFrom != null && l_unit.orderBizDateTo == null) ||
                    (l_unit.orderBizDateFrom == null && l_unit.orderBizDateTo != null) ||
                    (l_unit.orderBizDateFrom != null && l_unit.orderBizDateTo != null &&                         
                        WEB3DateUtility.compareToDay(l_unit.orderBizDateFrom, l_unit.orderBizDateTo) > 0))
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02047,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 発注日条件設定エラー。" + 
                        l_unit.orderBizDateFrom + "(条件.発注日（自）)" + 
                        l_unit.orderBizDateTo + "(条件.発注日（至）)"); 
                }
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
        return new WEB3AdminFeqOrderVoucherListResponse(this);
    }
}
@
