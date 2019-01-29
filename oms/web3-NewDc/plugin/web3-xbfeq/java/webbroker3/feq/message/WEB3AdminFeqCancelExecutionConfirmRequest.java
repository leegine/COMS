head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.34.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCancelExecutionConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式出来約定取消確認リクエスト(WEB3AdminFeqCancelExecutionConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
Revesion History : 2009/08/03 車進(中訊)   モデル　@No.504対応
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者外国株式出来約定取消確認リクエスト)<BR>
 * 管理者外国株式出来約定取消確認リクエストクラス
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqCancelExecutionConfirmRequest extends WEB3GenRequest 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqCancelExecutionConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_cancelExecutionConfirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * (運用コード)<BR>
     * 運用コード
     */
    public String managementCode;
    
    /**
     * (発注日)<BR>
     * 発注日
     */
    public Date orderBizDate;
    
    /**
     * (約定番号)<BR>
     * 約定番号
     */
    public String execNo;
    
    /**
     * @@roseuid 42CE39FD01F4
     */
    public WEB3AdminFeqCancelExecutionConfirmRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@運用コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02032<BR>
     * 　@１－２）　@5桁の半角数字でない場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_03163<BR>
     * <BR>
     * ２）　@約定番号のチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02039<BR>
     * 　@２－２）　@有効桁数3桁以内の正の整数値であること。<BR>
     * 　@以外の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02040<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4294319502C7
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １）　@運用コードのチェック
        // 　@１－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.managementCode))
        {
            log.debug("運用コードが未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02032,
                getClass().getName() + STR_METHOD_NAME,
                "運用コードが未入力です。");
        }
        
        // １－２）　@5桁の半角数字でない場合、例外をスローする。
        if (this.managementCode.length() != 5 || 
            !WEB3StringTypeUtility.isDigit(this.managementCode))
        {
            log.debug("運用コードが5桁の半角数字ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03163,
                getClass().getName() + STR_METHOD_NAME,
                "運用コードが5桁の半角数字ではありません。");
        }
        
        // ２）　@約定番号のチェック
        // 　@２－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.execNo))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02039,
                getClass().getName() + STR_METHOD_NAME,
                "約定番号が未入力です。");
        }

        // 　@２－２）　@有効桁数3桁以内の正の整数値であること。以外の場合、例外をスローする。
        final int EXECNO_LEN = 3;
        final int ZERO = 0;
        if (!WEB3StringTypeUtility.isDigit(this.execNo)
            || this.execNo.length() > EXECNO_LEN
            || ZERO == Integer.parseInt(this.execNo))
        {
            log.debug("約定番号 = " + this.execNo);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02040,
                getClass().getName() + STR_METHOD_NAME,
                "約定番号が有効桁数3桁以内の正の整数値ではありません。");
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
        return new WEB3AdminFeqCancelExecutionConfirmResponse(this);
    }
}
@
