head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.29.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式約定情報(WEB3FeqExecuteUnit.java)
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
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (外国株式約定情報)<BR>
 * 外国株式約定情報クラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3FeqExecuteUnit extends Message 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqExecuteUnit.class);
        
    /**
     * (約定単価)<BR>
     * 約定単価
     */
    public String execPrice;
    
    /**
     * (約定数量)<BR>
     * 約定数量
     */
    public String execQuantity;
    
    /**
     * (約定日時)<BR>
     * 約定日時
     */
    public Date executionTimestamp;
    
    /**
     * (外国株式約定情報)<BR>
     * コンストラクタ
     * @@roseuid 4201C0F10303
     */
    public WEB3FeqExecuteUnit() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@約定単価のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02021<BR>
     * 　@１－２）　@数値でない場合、例外をスローする<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02022<BR>
     * 　@１－３）　@数値に変換した値 <= 0の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02023<BR>
     * 　@１－４）　@数値に変換した時の有効桁数が、整数部６桁，小数部５桁の範囲<BR>
     * 　@外であれば、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02024<BR>
     * <BR>
     * ２）　@約定数量のチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02025<BR>
     * 　@２－２）　@9桁以内の整数値でない場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02026<BR>
     * 　@２－３）　@数値に変換した値 <= 0の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02186<BR>
     * @@throws WEB3BaseException
     * @@roseuid 428D710F0270
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@約定単価のチェック
        //１－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.execPrice))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02021,
                this.getClass().getName() + STR_METHOD_NAME,
                " 約定単価が未入力です。"); 
        }
        
        //１－２）　@数値でない場合、例外をスローする
        if (!WEB3StringTypeUtility.isNumber(this.execPrice))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                this.getClass().getName() + STR_METHOD_NAME,
                " 約定単価が数値以外の値です:" + this.execPrice); 
        }
        
        //１－３）　@数値に変換した値 <= 0の場合、例外をスローする。
        if (Double.parseDouble(this.execPrice) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                this.getClass().getName() + STR_METHOD_NAME,
                " 約定単価が数値に変換した値 <= 0です:" + this.execPrice); 
        }
        
        //１－４）　@数値に変換した時の有効桁数が、整数部６桁，小数部５桁の範囲
        //外であれば、例外をスローする。
        int l_intIntCnt = WEB3StringTypeUtility.getIntegerDigits(this.execPrice);
        int l_intFacCnt = WEB3StringTypeUtility.getFractionDigits(this.execPrice);        
        if (l_intIntCnt > 6 || l_intFacCnt > 5)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02024,
                this.getClass().getName() + STR_METHOD_NAME,
                " 約定単価は整数部６桁，小数部５桁の範囲外です:" + this.execPrice); 
        }

        //２）　@約定数量のチェック
        //２－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.execQuantity))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02025,
                this.getClass().getName() + STR_METHOD_NAME,
                " 約定数量が未入力です。"); 
        }

        //２－２）　@9桁以内の整数値でない場合、例外をスローする。
        if (WEB3StringTypeUtility.isInteger(this.execQuantity))
        {
            if (WEB3StringTypeUtility.getIntegerDigits(this.execQuantity) > 9)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02026,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 約定数量が9桁以内の整数値ではありません:" + this.execQuantity); 
            }
            
            //２－３）　@数値に変換した値 <= 0の場合、例外をスローする。
            if (Double.parseDouble(this.execQuantity) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02186,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 約定数量が数値に変換した値 <= 0です:" + this.execQuantity); 
            }            
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02026,
                this.getClass().getName() + STR_METHOD_NAME,
                " 約定数量が整数値ではありません:" + this.execQuantity); 
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
