head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExchangeUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式為替情報(WEB3FeqExchangeUnit.java)
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
import webbroker3.feq.define.WEB3FeqRateDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (外国株式為替情報)<BR>
 * 外国株式為替情報クラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3FeqExchangeUnit extends Message 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqExchangeUnit.class);
    
    /**
     * (通貨コード)<BR>
     * 通貨コード
     */
    public String currencyCode;
    
    /**
     * (レート区分)<BR>
     * レート区分<BR>
     * <BR>
     * 0：基準為替<BR>
     * 1：約定為替
     */
    public String rateDiv;
    
    /**
     * (売付為替レート)<BR>
     * 売付為替レート<BR>
     * <BR>
     * ※登録入力レスポンスでセットされる値は、変更前の値。<BR>
     *    それ以外でセットされる値は、変更後の値。<BR>
     *    （入力されてない場合は、null）
     */
    public String sellExchangeRate;
    
    /**
     * (買付為替レート)<BR>
     * 買付為替レート<BR>
     * <BR>
     * ※登録入力レスポンスでセットされる値は、変更前の値。<BR>
     *    それ以外でセットされる値は、変更後の値。<BR>
     *    （入力されてない場合は、null）
     */
    public String buyExchangeRate;
    
    /**
     * (登録日時)<BR>
     * 登録日時
     */
    public Date registDatetime;
    
    /**
     * (外国株式為替情報)<BR>
     * コンストラクタ
     * @@roseuid 4200A6850060
     */
    public WEB3FeqExchangeUnit() 
    {
     
    }
    
    /**
     * 為替情報データの整合性をチェックする。<BR>
     * <BR>
     * １）　@売付為替レートのチェック<BR>
     * 　@入力がある場合（売付為替レート != null）のみ、以下の処理実施<BR>
     * <BR>
     * 　@１－１）　@数値でない場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02013<BR>
     * 　@１－２）　@数値に変換した時の有効桁数が、整数部３桁，<BR>
     * 　@小数部４桁の範囲外であれば、例外※をスローする。<BR>
     * 　@　@※レート区分によって例外メッセージを分ける<BR>
     * 　@　@（レート区分 == ”基準為替”）の場合、「売付基準為替エラー」<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02014<BR>　@
     * 　@　@（レート区分 == ”約定為替”）の場合、「売付約定為替エラー」<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02015<BR>　@
     * 　@１－３）　@売付為替レート <= 0 の場合、例外※をスローする。<BR>
     * 　@　@※レート区分によって例外メッセージを分ける<BR>
     * 　@　@（レート区分 == ”基準為替”）の場合、「売付基準為替エラー」<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02014<BR>　@
     * 　@　@（レート区分 == ”約定為替”）の場合、「売付約定為替エラー」<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02015<BR>　@
     * <BR>
     * ２）　@買付為替レートのチェック<BR>
     * 　@入力がある場合（買付為替レート != null）のみ、以下の処理実施<BR>
     * <BR>
     * 　@２－１）　@数値でない場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02016<BR>
     * 　@２－２）　@数値に変換した時の有効桁数が、整数部３桁，<BR>
     * 小数部４桁の範囲外であれば、例外※をスローする。<BR>
     * 　@　@※レート区分によって例外メッセージを分ける<BR>
     * 　@　@（レート区分 == ”基準為替”）の場合、「買付基準為替エラー」<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02017<BR>　@
     * 　@　@（レート区分 == ”約定為替”）の場合、「買付約定為替エラー」<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02018<BR>　@
     * 　@２－３）　@買付為替レート <= 0 の場合、例外※をスローする。<BR>
     * 　@　@※レート区分によって例外メッセージを分ける<BR>
     * 　@　@（レート区分 == ”基準為替”）の場合、「買付基準為替エラー」<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02017<BR>　@
     * 　@　@（レート区分 == ”約定為替”）の場合、「買付約定為替エラー」<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02018<BR>　@
     * @@throws WEB3BaseException
     * @@roseuid 42BA5C6E005D
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@売付為替レートのチェック
        //入力がある場合（売付為替レート != null）のみ、以下の処理実施
        if (!WEB3StringTypeUtility.isEmpty(this.sellExchangeRate))
        {
            //１－１）　@数値でない場合、例外をスローする。
            if (!WEB3StringTypeUtility.isNumber(this.sellExchangeRate))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02013,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 売付為替レートが数値以外の値です:" + this.sellExchangeRate); 
            }
            
            //１－２）　@数値に変換した時の有効桁数が、整数部３桁，
            //小数部４桁の範囲外であれば、例外※をスローする。
            int l_intIntCnt = WEB3StringTypeUtility.getIntegerDigits(this.sellExchangeRate);
            int l_intFacCnt = WEB3StringTypeUtility.getFractionDigits(this.sellExchangeRate);
                
            if (l_intIntCnt > 3 || l_intFacCnt > 4)
            {
                //※レート区分によって例外メッセージを分ける 
                //（レート区分 == ”基準為替”）の場合、「売付基準為替エラー」
                if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02014,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 売付為替レート(基準為替)が整数部３桁，小数部４桁の範囲外です:" + this.sellExchangeRate); 
                }                   
                //（レート区分 == ”約定為替”）の場合、「売付約定為替エラー」 
                else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02015,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 売付為替レート(約定為替)が整数部３桁，小数部４桁の範囲外です:" + this.sellExchangeRate); 
                }
            }

            double l_dblSellExecRate = Double.parseDouble(this.sellExchangeRate);
            //１－３）　@売付為替レート <= 0 の場合、例外※をスローする。
            if (l_dblSellExecRate <= 0)
            {
                //※レート区分によって例外メッセージを分ける 
                //（レート区分 == ”基準為替”）の場合、「売付基準為替エラー」
                if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02014,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 売付為替レート(基準為替)が<= 0です:" + this.sellExchangeRate); 
                }                   
                //（レート区分 == ”約定為替”）の場合、「売付約定為替エラー」 
                else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02015,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 売付為替レート(約定為替)が<= 0です:" + this.sellExchangeRate); 
                }
            }                
        }            
            
        //２）　@買付為替レートのチェック
        //入力がある場合（買付為替レート != null）のみ、以下の処理実施
        if (!WEB3StringTypeUtility.isEmpty(this.buyExchangeRate))
        {
            //２－１）　@数値でない場合、例外をスローする。
            if (!WEB3StringTypeUtility.isNumber(this.buyExchangeRate))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02016,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 買付為替レートが数値以外の値です:" + this.buyExchangeRate); 
            }
                
            //２－２）　@数値に変換した時の有効桁数が、整数部３桁，
            //小数部４桁の範囲外であれば、例外※をスローする。    
            int l_intIntCnt = WEB3StringTypeUtility.getIntegerDigits(this.buyExchangeRate);
            int l_intFacCnt = WEB3StringTypeUtility.getFractionDigits(this.buyExchangeRate);
                
            if (l_intIntCnt > 3 || l_intFacCnt > 4)
            {
                //※レート区分によって例外メッセージを分ける 
                //（レート区分 == ”基準為替”）の場合、「買付基準為替エラー」
                if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02017,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 買付為替レート(基準為替)が整数部３桁，小数部４桁の範囲外です:" + this.buyExchangeRate); 
                }                   
                //（レート区分 == ”約定為替”）の場合、「買付約定為替エラー」 
                else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02018,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 買付為替レート(約定為替)が整数部３桁，小数部４桁の範囲外です:" + this.buyExchangeRate); 
                }
            }
            
            double l_dblBuyExecRate = Double.parseDouble(this.buyExchangeRate);
            //２－３）　@買付為替レート <= 0 の場合、例外※をスローする。
            if (l_dblBuyExecRate <= 0)
            {
                //※レート区分によって例外メッセージを分ける 
                //（レート区分 == ”基準為替”）の場合、「買付基準為替エラー」
                if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02017,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 買付為替レート(基準為替)が<= 0です:" + this.buyExchangeRate); 
                }                   
                //（レート区分 == ”約定為替”）の場合、「買付約定為替エラー」 
                else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {                    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02018,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 買付為替レート(約定為替)が<= 0です:" + this.buyExchangeRate); 
                }
            }
        }                                    
        
        log.exiting(STR_METHOD_NAME);       
    }
}
@
