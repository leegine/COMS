head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.31.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqForeignCostUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現地手数料情報(WEB3AdminFeqForeignCostUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (現地手数料情報)<BR>
 * 現地手数料情報クラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqForeignCostUnit extends Message 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqForeignCostUnit.class);
        
    /**
     * (適用期間（自）)<BR>
     * 適用期間（自）
     */
    public Date applyStartDate;
    
    /**
     * (適用期間（至）)<BR>
     * 適用期間（至）
     */
    public Date applyEndDate;
    
    /**
     * (取引金額（自）)<BR>
     * 取引金額（自）
     */
    public String tradingAmtFrom;
    
    /**
     * (取引金額（至）)<BR>
     * 取引金額（至）
     */
    public String tradingAmtTo;
    
    /**
     * (徴収率)<BR>
     * 徴収率<BR>
     * 単位：%
     */
    public String collectRate;
    
    /**
     * (付加金額)<BR>
     * 付加金額
     */
    public String additionalAmt;
    
    /**
     * (現地手数料情報)<BR>
     * コンストラクタ
     * @@roseuid 4200B8FE0104
     */
    public WEB3AdminFeqForeignCostUnit() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）適用期間（自）<BR>
     * <BR>
     *    this.適用期間（自） == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02073<BR>
     * <BR>
     * ２）適用期間（至）<BR>
     * <BR>
     *    this.適用期間（至） != null and<BR>
     *    this.適用期間（自） > this.適用期間（至）<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_01446<BR>
     * <BR>
     * ３）取引金額（自）<BR>
     * <BR>
     * ３－１）<BR>
     * <BR>
     *    this.取引金額（自） == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02074<BR>
     * <BR>
     * ３－２）<BR>
     * <BR>
     *    this.取引金額（自） < 0<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02075<BR>
     * <BR>
     * ３－３）this.取引金額（自）に小数点が含まれる場合<BR>
     * （this.取引金額（自）.indexOf('.') >= 0 の場合）<BR>
     * <BR>
     *    this.取引金額（自）.split('.')[0].length() > 11 or<BR>
     *    this.取引金額（自）.split('.')[1].length() > 2<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02076<BR>
     * <BR>
     * ３－４）this.取引金額（自）に小数点が含まれない場合<BR>
     * （this.取引金額（自）.indexOf('.') = -1 の場合）<BR>
     *    this.取引金額（自）.length() > 11<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02076<BR>
     * <BR>
     * ４）取引金額（至）<BR>
     * <BR>
     *    this.取引金額（至） != null の場合、以下のチェックを行う。<BR>
     * <BR>
     * ４－１）<BR>
     * <BR>
     *    this.取引金額（至） < 0<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02077<BR>
     * <BR>
     * ４－２）this.取引金額（至）に小数点が含まれる場合<BR>
     * （this.取引金額（至）.indexOf('.') >= 0 の場合）<BR>
     * <BR>
     *    this.取引金額（至）.split('.')[0].length() > 11 or<BR>
     *    this.取引金額（至）.split('.')[1].length() > 2<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02078<BR>
     * <BR>
     * ４－３）this.取引金額（至）に小数点が含まれない場合<BR>
     * （this.取引金額（至）.indexOf('.') = -1 の場合）<BR>
     * <BR>
     *    this.取引金額（至）.length() > 11<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02078<BR>
     * <BR>
     * ４－４）<BR>
     * <BR>
     *    this.取引金額（自） > this.取引金額（至）<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02079<BR>
     * <BR>
     * ５）徴収率<BR>
     * <BR>
     * ５－１）<BR>
     * <BR>
     *    this.徴収率 == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02080<BR>
     * <BR>
     * ５－２）<BR>
     * <BR>
     *    this.徴収率 < 0<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02081<BR>
     * <BR>
     * ５－３）this.徴収率に小数点が含まれる場合<BR>
     * （this.徴収率.indexOf('.') >= 0 の場合）<BR>
     * <BR>
     *    this.徴収率.split('.')[0].length() > 3 or<BR>
     *    this.徴収率.split('.')[1].length() > 5<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02082<BR>
     * <BR>
     * ５－４）this.徴収率に小数点が含まれない場合<BR>
     * （this.徴収率.indexOf('.') = -1 の場合）<BR>
     * <BR>
     *    this.徴収率.length() > 3<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02082<BR>
     * <BR>
     * ６）付加金額<BR>
     * <BR>
     * ６－１）<BR>
     * <BR>
     *    this.付加金額 == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02083<BR>
     * <BR>
     * ６－２）<BR>
     * <BR>
     *    this.付加金額 < 0<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02084<BR>
     * <BR>
     * ６－３）this.付加金額に小数点が含まれる場合<BR>
     * （this.付加金額.indexOf('.') >= 0 の場合）<BR>
     * <BR>
     *    this.付加金額.split('.')[0].length() > 9 or<BR>
     *    this.付加金額.split('.')[1].length() > 5<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02085<BR>
     * <BR>
     * ６－４）this.付加金額に小数点が含まれない場合<BR>
     * （this.付加金額.indexOf('.') = -1 の場合）<BR>
     * <BR>
     *    this.付加金額.length() > 9<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02085<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B0E859024B
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）適用期間（自） 
        //this.適用期間（自） == null の場合、例外をスローする。 
        if (this.applyStartDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02073,
                this.getClass().getName() + STR_METHOD_NAME,
                " 適用期間（自）が未入力です。"); 
        }
        
        //２）適用期間（至） 
        // this.適用期間（至） != null and this.適用期間（自） > this.適用期間（至） 
        //の場合、例外をスローする。 
        if (this.applyEndDate != null)
        {
            int l_intFlag = WEB3DateUtility.compareToDay(this.applyStartDate, this.applyEndDate);
            if (l_intFlag > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01446,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 適用期間From/To整合性エラー:" + 
                    this.applyStartDate + "(適用期間（自）), " + 
                    this.applyEndDate + "(適用期間（至）) "); 
            }
        }

        //３）取引金額（自） 
        //３－１） this.取引金額（自） == null の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.tradingAmtFrom))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02074,
                this.getClass().getName() + STR_METHOD_NAME,
                " 取引金額（自）が未入力です。"); 
        }
        
        double l_dblTradingAmtFrom = Double.parseDouble(this.tradingAmtFrom);
        //３－２） this.取引金額（自） < 0 の場合、例外をスローする。 
        if (l_dblTradingAmtFrom < 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02075,
                this.getClass().getName() + STR_METHOD_NAME,
                " 取引金額（自）がマイナスの値です:" + this.tradingAmtFrom); 
        }
        
        //３－３）this.取引金額（自）に小数点が含まれる場合
        //（this.取引金額（自）.indexOf('.') >= 0 の場合）
        if (this.tradingAmtFrom.indexOf('.') >= 0)            
        {
            // this.取引金額（自）.split('.')[0].length() > 11 or 
            //this.取引金額（自）.split('.')[1].length() > 2 
            //の場合、例外をスローする。 
            String[] l_strs = this.tradingAmtFrom.trim().split("\\.");
            if (l_strs[0].length() > 11 || l_strs[1].length() > 2)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02076,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 取引金額（自）の値が不正です。:" + this.tradingAmtFrom); 
            }
        }
        
        //３－４）this.取引金額（自）に小数点が含まれない場合（this.取引金額（自）.indexOf('.') = -1 の場合）         
        if (this.tradingAmtFrom.indexOf('.') == -1)
        {
            //this.取引金額（自）.length() > 11 の場合、例外をスローする。 
            if (WEB3StringTypeUtility.getNubmerLength(this.tradingAmtFrom) > 11)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02076,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 取引金額（自）の値が不正です。:" + this.tradingAmtFrom); 
            }
        }
        
        //４）取引金額（至） 
        //this.取引金額（至） != null の場合、以下のチェックを行う。
        if (!WEB3StringTypeUtility.isEmpty(this.tradingAmtTo))
        {            
            double l_dblTradingAmtTo = Double.parseDouble(this.tradingAmtTo);
            //４－１） 
            //this.取引金額（至） < 0 の場合、例外をスローする。 
            if (l_dblTradingAmtTo < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02077,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 取引金額（至）がマイナスの値です。:" + this.tradingAmtTo);
            }

            //４－２）this.取引金額（至）に小数点が含まれる場合（this.取引金額（至）.indexOf('.') >= 0 の場合） 
            if (this.tradingAmtTo.indexOf('.') >= 0)
            {
                String[] l_strs = this.tradingAmtTo.trim().split("\\.");
                //this.取引金額（至）.split('.')[0].length() > 11 or 
                //this.取引金額（至）.split('.')[1].length() > 2 
                //の場合、例外をスローする。
                if (l_strs[0].length() > 11 || l_strs[1].length() > 2)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02078,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 取引金額（至）の値が不正です:" + this.tradingAmtTo);
                }
            }
 

            //４－３）this.取引金額（至）に小数点が含まれない場合（this.取引金額（至）.indexOf('.') = -1 の場合） 
            //this.取引金額（至）.length() > 11 の場合、例外をスローする。 
            if (this.tradingAmtTo.indexOf('.') == -1)
            {
                //this.取引金額（自）.length() > 11 の場合、例外をスローする。 
                if (WEB3StringTypeUtility.getNubmerLength(this.tradingAmtTo) > 11)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02078,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 取引金額（至）の値が不正です:" + this.tradingAmtTo); 
                }
            }
            
            //４－４）this.取引金額（自） > this.取引金額（至）の場合、例外をスローする。 
            if (l_dblTradingAmtFrom > l_dblTradingAmtTo)
            {                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02079,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 取引金額（自）が取引金額（至）を超えました:" + 
                    this.tradingAmtFrom + " > " + this.tradingAmtTo); 
            }

        }
         
        //５）徴収率 
        //５－１）this.徴収率 == nullの場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.collectRate))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02080,
                this.getClass().getName() + STR_METHOD_NAME,
                " 徴収率が未入力です。"); 
        }        
        
        //５－２）this.徴収率 < 0 の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isMinus(this.collectRate))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02081,
                this.getClass().getName() + STR_METHOD_NAME,
                " 徴収率がマイナスの値です:" + this.collectRate); 
        }
        //５－３）this.徴収率に小数点が含まれる場合（this.徴収率.indexOf('.') >= 0 の場合） 
        //this.徴収率.split('.')[0].length() > 3 or this.徴収率.split('.')[1].length() > 5 
        //の場合、例外をスローする。 
        if (this.collectRate.indexOf('.') >= 0)
        {
            String[] l_strs = this.collectRate.trim().split("\\.");

            if (l_strs[0].length() > 3 || l_strs[1].length() > 5)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 徴収率の値が不正です:" + this.collectRate);
            }
        }

        //５－４）this.徴収率に小数点が含まれない場合（this.徴収率.indexOf('.') = -1 の場合） 
        //this.徴収率.length() > 3 /の場合、例外をスローする。
        if (this.collectRate.indexOf('.') == -1)
        { 
            if (WEB3StringTypeUtility.getNubmerLength(this.collectRate) > 3)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 徴収率の値が不正です:" + this.collectRate); 
            }
        } 

        //６）付加金額 
        //６－１）this.付加金額 == null の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.additionalAmt))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02083,
                this.getClass().getName() + STR_METHOD_NAME,
                " 付加金額が未入力です。"); 
        } 
        
        //６－２）this.付加金額 < 0 の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isMinus(this.additionalAmt))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02084,
                this.getClass().getName() + STR_METHOD_NAME,
                " 付加金額がマイナスの値です:" + this.additionalAmt); 
        }
        
        //６－３）this.付加金額に小数点が含まれる場合（this.付加金額.indexOf('.') >= 0 の場合）         
        //this.付加金額.split('.')[0].length() > 9 orthis.付加金額.split('.')[1].length() > 5 
        //の場合、例外をスローする。
        if (this.additionalAmt.indexOf('.') >= 0)
        {
            String[] l_strs = this.additionalAmt.trim().split("\\.");

            if (l_strs[0].length() > 9 || l_strs[1].length() > 5)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02085,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 付加金額の値が不正です:" + this.additionalAmt);
            }
        }
         
        //６－４）this.付加金額に小数点が含まれない場合（this.付加金額.indexOf('.') = -1 の場合） 
        //this.付加金額.length() > 9 の場合、例外をスローする。
        if (this.additionalAmt.indexOf('.') == -1)
        {
            if (WEB3StringTypeUtility.getNubmerLength(this.additionalAmt) > 9)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02085,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 付加金額の値が不正です:" + this.additionalAmt);
            }
        } 

        log.exiting(STR_METHOD_NAME);       
    }
}
@
