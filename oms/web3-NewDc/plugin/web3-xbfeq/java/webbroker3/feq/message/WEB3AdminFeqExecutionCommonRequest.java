head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式出来入力共通リクエスト(WEB3AdminFeqExecutionCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー
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
 * (管理者外国株式出来入力共通リクエスト)<BR>
 * 管理者外国株式出来入力共通リクエストクラス
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionCommonRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executionCommon";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;   
    
    /**
     * (注文ID)<BR>
     * 注文ID
     */
    public String orderId;
    
    /**
     * (約定為替レート)<BR>
     * 約定為替レート
     */
    public String execExchangeRate;
    
    /**
     * (約定日)<BR>
     * 約定日
     */
    public Date executionDate;
    
    /**
     * (現地受渡日)<BR>
     * 現地受渡日
     */
    public Date localDeliveryDate;
    
    /**
     * (出来情報一覧)<BR>
     * 画面で入力された外国株式約定情報の配列
     */
    public WEB3FeqExecuteUnit[] executeList;
    
    /**
     * @@roseuid 42CE39FD0261
     */
    public WEB3AdminFeqExecutionCommonRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@注文ＩＤのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * ２）　@約定為替レートのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02036<BR>
     *   ２－２）　@数値でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02220<BR>
     * 　@２－３）　@数値に変換した時の有効桁数が、整数部３桁，<BR>
     *   小数部４桁の範囲外であれば、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02037<BR>
     *   ２－４）　@数値に変換した値 <= 0の場合、例外をスローする。
     *   class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02196<BR>
     * <BR>
     * ３）　@出来情報チェック<BR>
     * 　@３－１）　@出来情報一覧[] が未入力の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02038<BR>
     * 　@３－２）　@出来情報一覧[] の各要素.validate()をコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 428D8A8E03A9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);

        //１）　@注文ＩＤのチェック
        // １－１）　@未入力の場合、例外をスローする。
        // class: WEB3BusinessLayerException
        // tag:   BUSINESS_ERROR_00600
        if (WEB3StringTypeUtility.isEmpty(orderId)) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + STR_METHOD_NAME,
                " 注文ＩＤが未入力の場合チェック"); 
        } 

        //２）　@約定為替レートのチェック
        //２－１）　@未入力の場合、例外をスローする。
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_02036
        if (WEB3StringTypeUtility.isEmpty(execExchangeRate)) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02036,
                this.getClass().getName() + STR_METHOD_NAME,
                " 約定為替レートが未入力の場合チェック"); 
        } 

        //２－２）　@数値でない場合、例外をスローする。
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_02220
        if (!WEB3StringTypeUtility.isNumber(execExchangeRate))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02220,
                this.getClass().getName() + STR_METHOD_NAME,
                " 約定為替レートが数値でないの場合チェック"); 
        }
        
        //２－３）　@数値に変換した時の有効桁数が、整数部３桁，
        //小数部４桁の範囲外であれば、例外をスローする。
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_02037
        else if (!WEB3StringTypeUtility.isNumber(execExchangeRate) 
            || WEB3StringTypeUtility.getIntegerDigits(execExchangeRate) > 3
            || WEB3StringTypeUtility.getFractionDigits(execExchangeRate) > 4) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02037,
                this.getClass().getName() + STR_METHOD_NAME,
                " 数値に変換した時の有効桁数チェック"); 
        }         
        //２－４）　@数値に変換した値 <= 0の場合、例外をスローする。
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_02196
        else if (Double.parseDouble(execExchangeRate) <= 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02196,
                this.getClass().getName() + STR_METHOD_NAME,
                " 数値に変換した値 <= 0," + execExchangeRate); 
        }

        //３）　@出来情報チェック
        // ３－１）　@出来情報一覧[] が未入力の場合、例外をスローする。
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_02038
        if (executeList == null || executeList.length == 0) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02038,
                this.getClass().getName() + STR_METHOD_NAME,
                " 出来情報一覧[] が未入力の場合チェック"); 
        }
        
        //３－２）　@出来情報一覧[] の各要素.validate()をコールする。
        for (int i = 0; i < executeList.length; i++) 
        {
            executeList[i].validate();
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
