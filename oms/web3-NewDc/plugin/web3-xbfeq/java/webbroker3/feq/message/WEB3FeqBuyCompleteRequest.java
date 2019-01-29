head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.23.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuyCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式買付完了リクエスト(WEB3FeqBuyCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式買付完了リクエスト)<BR>
 * 外国株式買付完了リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqBuyCompleteRequest extends WEB3FeqBuyCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_buyComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     */
    public String orderId;
    
    /**
     * (確認時単価)<BR>
     * 確認時単価<BR>
     */
    public String checkPrice;
    
    /**
     * (確認時発注日)<BR>
     * 確認時発注日<BR>
     */
    public Date checkDate;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;
    
    /**
     * @@roseuid 42CE3A0501C5
     */
    public WEB3FeqBuyCompleteRequest() 
    {
     
    }
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBuyCompleteRequest.class);
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）スーパークラスのvalidate()メソッドを呼び出す。<BR>
     * <BR>
     * ２）注文IDチェック<BR>
     * <BR>
     *    this.注文ID == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * ３）確認時単価チェック<BR>
     * <BR>
     *    this.確認時単価 == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00206<BR>
     * <BR>
     * ４）確認時発注日チェック<BR>
     * <BR>
     *    this.確認時発注日 == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00078<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 428D6BCC02D5
     */
    public void validate() throws WEB3BaseException
    {       
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）スーパークラスのvalidate()メソッドを呼び出す。
        super.validate();
        
        //２）注文IDのチェック
        //２－１）　@this.注文ID == nullの場合、例外をスローする。
        if (this.orderId == null)
        {
            log.debug("注文IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文IDが未指定です。" + this.orderId);
        }
        
        //３）確認時単価チェック this.確認時単価 == null の場合、
        //例外をスローする。
        if (this.checkPrice == null)
        {
            log.debug("確認時単価が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206,
                this.getClass().getName() + STR_METHOD_NAME,
                "確認時単価が未指定です。" + this.checkPrice);
        }
        
        //４）確認時発注日チェック this.確認時発注日 == null の場合、
        //例外をスローする。
        if (this.checkDate == null)
        {
            log.debug("確認時発注日が入力されていません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + STR_METHOD_NAME,
                "確認時発注日が入力されていません。" + this.checkDate);
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
        return new WEB3FeqBuyCompleteResponse(this);
    }
}
@
