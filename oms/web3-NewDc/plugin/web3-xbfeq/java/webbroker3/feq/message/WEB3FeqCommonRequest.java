head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.24.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文共通リクエスト(WEB3FeqCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderConditionOperatorDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式注文共通リクエスト)<BR>
 * 外国株式注文共通リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqCommonRequest extends WEB3GenRequest 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (注文数量)<BR>
     * 注文数量<BR>
     */
    public String orderQuantity;
    
    /**
     * (注文単価区分)<BR>
     * 注文単価区分<BR>
     * <BR>
     * 0：成行<BR>
     * 1：指値<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     * 注文単価<BR>
     * <BR>
     * ※注文単価区分が”指値”の場合、設定。<BR>
     */
    public String limitPrice;
    
    /**
     * (執行条件)<BR>
     * 執行条件<BR>
     * <BR>
     * 1：条件なし<BR>
     * 3：寄付<BR>
     * 4：引け<BR>
     * 7：不出来引け成行<BR>
     */
    public String execCondType;
    
    /**
     * (注文期限区分)<BR>
     * 注文期限区分<BR>
     * <BR>
     * 1：当日限り<BR>
     * 2：出来るまで注文<BR>
     */
    public String expirationDateType;
    
    /**
     * (注文有効期限)<BR>
     * 注文有効期限<BR>
     * <BR>
     * ※注文期限区分が”出来るまで注文”の場合、設定<BR>
     */
    public Date expirationDate;
    
    /**
     * (発注条件)<BR>
     * 発注条件<BR>
     * <BR>
     * 0：指定なし<BR>
     * 1：逆指値<BR>
     * 2：W指値<BR>
     */
    public String orderCondType;
    
    /**
     * (逆指値用発注条件単価)<BR>
     * 逆指値用発注条件単価<BR>
     * <BR>
     * ※発注条件が”逆指値”の場合、設定<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (逆指値用発注条件演算子)<BR>
     * 逆指値用発注条件演算子<BR>
     * <BR>
     * 1：以上<BR>
     * 2：以下<BR>
     * <BR>
     * ※発注条件が”逆指値”の場合、設定<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (W指値用発注条件単価)<BR>
     * W指値用発注条件単価<BR>
     * <BR>
     * ※発注条件が”W指値”の場合、設定<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (W指値用発注条件演算子)<BR>
     * W指値用発注条件演算子<BR>
     * <BR>
     * 1：以上<BR>
     * 2：以下<BR>
     * <BR>
     * ※発注条件が”W指値”の場合、設定<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (W指値用注文単価区分)<BR>
     * W指値用注文単価区分<BR>
     * <BR>
     * 0：成行<BR>
     * 1：指値<BR>
     * <BR>
     * ※発注条件が”W指値”の場合、設定<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (W指値用注文単価)<BR>
     * W指値用注文単価<BR>
     * <BR>
     * ※W指値用注文単価区分が”指値”の場合、設定。<BR>
     */
    public String wLimitPrice;
    
    /**
     * @@roseuid 42CE3A0702AF
     */
    public WEB3FeqCommonRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）注文数量のチェック<BR>
     * <BR>
     *    this.注文数量 == null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00074<BR>
     *    this.注文数量 != 数字 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00075<BR>
     *    this.注文数量 <= 0 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00076<BR>
     *    this.注文数量.length > 9<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00077<BR>
     * <BR>
     *    の場合は、例外をスローする。<BR>
     * <BR>
     * ２）注文単価区分チェック<BR>
     * <BR>
     * ２－１）<BR>
     *    this.注文単価区分 == null<BR>
     * <BR>
     *    の場合、「注文単価区分がnull」の例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00184<BR>
     * <BR>
     * ２－２）<BR>
     *    this.注文単価区分 != （”成行” or ”指値”）<BR>
     * <BR>
     *    の場合、「注文単価区分が未定義の値」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00185<BR>
     * <BR>
     * ３）執行条件チェック<BR>
     * <BR>
     * ３－１）<BR>
     *    this.執行条件 == null<BR>
     * <BR>
     *    の場合、「執行条件がnull」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00197<BR>
     * <BR>
     * ３－２）<BR>
     *    this.執行条件 != （”条件なし” or ”寄付” or ”引け” or<BR>
     * ”不出来引け成行”）<BR>
     * <BR> 
     *    の場合、「執行条件が未定義の値」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02115<BR>
     * <BR>
     * ４）注文期限区分チェック<BR>
     * <BR>
     * ４－１）<BR>
     *    this.注文期限区分 == null<BR>
     * <BR>
     *    の場合、「注文期限区分がnull」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00208<BR>
     * <BR>
     * ４－２）<BR>
     *    this.注文期限区分 != （”当日限り” or ”出来るまで注文”）<BR>
     * <BR>
     *    の場合、「注文期限区分が未定義の値」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00209<BR>
     * <BR>
     * ５）発注条件チェック<BR>
     * <BR>
     * ５－１）<BR>
     *    this.発注条件 == null<BR>
     * <BR>
     *    の場合、「発注条件がnull」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02116<BR>
     * <BR>
     * ５－２）<BR>
     *    this.発注条件 != （”指定なし” or ”逆指値” or ”W指値”）<BR>
     * <BR>
     *    の場合、「発注条件が未定義の値」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02117<BR>
     * <BR>
     * ６）注文単価区分・単価 の整合性チェック<BR>
     * <BR>
     * ６－１）<BR>
     *    this.注文単価区分 == ”指値” and<BR>
     *    （<BR>
     *     this.注文単価 == null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02118<BR>
     *     this.注文単価 != 数字 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02119<BR>
     *     this.注文単価 <= 0 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02120<BR>
     *     this.注文単価.length > 8<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02093<BR>
     *    ）<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>　@　@ 
     * ６－２）<BR>
     *    this.注文単価区分 == ”成行” and<BR>
     *    this.注文単価 != （null or ”0”）<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00116<BR>
     * <BR>
     * ７）期限のチェック<BR>
     * <BR>
     * ７－１）<BR>
     *    this.注文期限区分 == ”当日限り” and<BR>
     *    this.注文有効期限 != null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00117<BR>
     * <BR>
     * ７－２）<BR>
     *    this.注文期限区分 == ”出来るまで注文” and<BR>
     *    this.注文有効期限 == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00210<BR>
     * <BR>
     * ８）発注条件のチェック１（”指定なし”）<BR>
     * <BR>
     *    this.発注条件 == ”指定なし” and<BR>
     *    (<BR>
     *     this.逆指値用発注条件単価 != （null or ”0”） or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01872<BR>
     *     this.逆指値用発注条件演算子 != null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01873<BR>
     *     this.W指値用発注条件単価 != （null or ”0”） or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01874<BR>
     *     this.W指値用発注条件演算子 != null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01875<BR>
     *     this.W指値用注文単価区分 != null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01876<BR>
     *     this.W指値用注文単価 != （null or ”0”）<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01877<BR>
     *    )<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * ９）発注条件のチェック２（”逆指値”） <BR>
     * <BR>
     * ９－１)<BR>
     *    this.発注条件 == ”逆指値” and<BR>
     *    (<BR>
     *     this.W指値用発注条件単価 != （null or ”0”） or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01878<BR>
     *     this.W指値用発注条件演算子 != null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01879<BR>
     *     this.W指値用注文単価区分 != null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01880<BR>
     *     this.W指値用注文単価 != （null or ”0”）<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01881<BR>
     *    )<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * ９－２)<BR>
     *    this.発注条件 == ”逆指値” and<BR>
     *    (<BR>
     *     this.逆指値用発注条件単価 == null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02121<BR>
     *     this.逆指値用発注条件単価 != 数字 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02123<BR>
     *     this.逆指値用発注条件単価 <= 0 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02122<BR>
     *     this.逆指値用発注条件単価,length > 8 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02124<BR>
     *     this.逆指値用発注条件演算子 == null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02125<BR>
     *     this.逆指値用発注条件演算子 != （”以上” or ”以下”）<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02126<BR>
     *    )<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * １０）発注条件のチェック３（”W指値”）<BR>
     * <BR>
     * １０－１）<BR>
     *    this.発注条件 == ”W指値” and<BR>
     *    (<BR>
     *     this.逆指値用発注条件単価 != （null or ”0”） or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01882<BR>
     *     this.逆指値用発注条件演算子 != null<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_01883<BR>
     *    )<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * １０－２）<BR>
     *    this.発注条件 == ”W指値” and<BR>
     *    (<BR>
     *     this.W指値用発注条件演算子 == null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02127<BR>
     *     this.W指値用発注条件演算子 != （”以上” or ”以下”） or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02128<BR>
     *     this.W指値用注文単価区分 == null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02129<BR>
     *     this.W指値用注文単価区分 != （”成行” or ”指値”） or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02130<BR>
     *     this.W指値用発注条件単価 == null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02131<BR>
     *     this.W指値用発注条件単価 != 数字 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02132<BR>
     *     this.W指値用発注条件単価 <= 0 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02133<BR>
     *     this.W指値用発注条件単価.length > 8<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02134<BR>
     *    )<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * １１）W指値用注文単価区分・W指値用注文単価 の整合性チェック<BR>
     * <BR>
     * １１－１）<BR>
     *    this.W指値用注文単価区分 == ”指値” and<BR>
     *    (<BR>
     *     this.W指値用注文単価 == null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02135<BR>
     *     this.W指値用注文単価 != 数字 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02136<BR>
     *     this.W指値用注文単価 <= 0 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02137<BR>
     *     this.W指値用注文単価.length > 8<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02138<BR>
     *    )<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * １１－２）<BR>
     *    this.W指値用注文単価区分 == ”成行” and<BR>
     *    this.W指値用注文単価 != （null or ”0”）<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02139<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * １２）注文期限・執行条件のチェック<BR>
     *    this.注文期限区分 == ”出来るまで注文” and<BR>
     *    this.執行条件 != ”条件なし”<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00125<BR>
     * <BR>
     * １３）注文単価・執行条件のチェック <BR>
     *   this.注文単価区分 != ”指値” and <BR>
     *   this.執行条件 == ”不出来引け成行”<BR> 
     *<BR>
     *  の場合、例外をスローする<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00114<BR>
     * @@throws WEB3BaseException
     * @@roseuid 428C33EC0361
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）注文数量のチェック 
        //this.注文数量 == null or 
        if (this.orderQuantity == null)
        {
            log.debug("注文数量が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文数量が未指定です。" + this.orderQuantity);
        }
        
        //this.注文数量 != 数字 or 
        if (!WEB3StringTypeUtility.isInteger(this.orderQuantity))
        {
            log.debug("注文数量が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文数量が数字以外の値です。" + this.orderQuantity);
        }
        
        //this.注文数量 <= 0 or 
        if (Integer.parseInt(this.orderQuantity) <= 0)
        {
            log.debug("注文数量が0以下の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文数量が0以下の値です。" + this.orderQuantity);
        }
        
        //this.注文数量.length > 9の場合は、例外をスローする。
        if (this.orderQuantity.length() > 9)
        {
            log.debug("注文数量の桁数が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文数量の桁数が不正です。" + this.orderQuantity.length());
        }
        
        //２）注文単価区分チェック 
        //２－１） 
        //this.注文単価区分 == null 
        //の場合、「注文単価区分がnull」の例外をスローする。  
        if (this.orderPriceDiv == null)
        {
            log.debug("注文単価区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00184,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文単価区分が未指定です。" + this.orderPriceDiv);
        }
        
        //２－２） 
        //this.注文単価区分 != （”成行” or ”指値”） 
        //の場合、「注文単価区分が未定義の値」の例外をスローする。 
        if (!(WEB3OrderPriceDivDef.MARKET_PRICE).equals(this.orderPriceDiv) &&
            !(WEB3OrderPriceDivDef.LIMIT_PRICE).equals(this.orderPriceDiv) )
        {
            log.debug("注文単価区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00185,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文単価区分の値が存在しないコード値です。" + 
                this.orderPriceDiv);
        }
    
        //３）執行条件チェック 
        //３－１） 
        //this.執行条件 == null 
        //の場合、「執行条件がnull」の例外をスローする。 
        if (this.execCondType == null)
        {
            log.debug("執行条件が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00197,
                this.getClass().getName() + STR_METHOD_NAME,
                "執行条件が未指定です。" + this.execCondType);
        }
        
        //３－２） 
        //this.執行条件 != （”条件なし” or ”寄付” or ”引け” or ”
        //来引け成行”） の場合、「執行条件が未定義の値」の例外をスローする。 
        if (!(WEB3ExecutionConditionDef.NO_CONDITION).equals(this.execCondType)
            && !(WEB3ExecutionConditionDef.AT_MARKET_OPEN).equals(this.execCondType)
            && !(WEB3ExecutionConditionDef.AT_MARKET_CLOSE).equals(this.execCondType)
            && !(WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED).equals(this.execCondType))
        {
            log.debug("執行条件が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02115,
                this.getClass().getName() + STR_METHOD_NAME,
                "執行条件が未定義の値です。" + this.execCondType);
        }
        
        //４）注文期限区分チェック 
        //４－１） 
        //this.注文期限区分 == null 
        //の場合、「注文期限区分がnull」の例外をスローする。 
        if (this.expirationDateType == null)
        {
            log.debug("注文期限区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00208,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文期限区分が未指定です。" + this.expirationDateType);
        }
        
        //４－２） 
        //   this.注文期限区分 != （”当日限り” or ”出来るまで注文”） 
        //   の場合、「注文期限区分が未定義の値」の例外をスローする。 
        if (!(WEB3OrderExpirationDateTypeDef.DAY_LIMIT).equals(this.expirationDateType)
            && !(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER).equals(this.expirationDateType))
        {
            log.debug("注文期限区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00209,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文期限区分の値が存在しないコード値です。" + 
                this.expirationDateType);
        }
        
        //５）発注条件チェック 
        //５－１） 
        //   this.発注条件 == null 
        //   の場合、「発注条件がnull」の例外をスローする。
        if (this.orderCondType == null)
        {
            log.debug("発注条件がnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02116,
                this.getClass().getName() + STR_METHOD_NAME,
                "発注条件がnullです。" + this.orderCondType);
        } 
        
        //５－２） 
        //   this.発注条件 != （”指定なし” or ”逆指値” or ”W指値”） 
        //   の場合、「発注条件が未定義の値」の例外をスローする。 
        if (!(WEB3OrderingConditionDef.DEFAULT).equals(this.orderCondType)
            && !(WEB3OrderingConditionDef.STOP_LIMIT_PRICE).equals(this.orderCondType)
            && !(WEB3OrderingConditionDef.W_LIMIT_PRICE).equals(this.orderCondType))
        {
            log.debug("発注条件が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02117,
                this.getClass().getName() + STR_METHOD_NAME,
                "発注条件が未定義の値です。" + this.orderCondType);
        }

        //６）注文単価区分・単価 の整合性チェック 
        //６－１） 
        //this.注文単価区分 == ”指値” and 
        //（ 
        
        if ((WEB3OrderPriceDivDef.LIMIT_PRICE).equals(this.orderPriceDiv))
        {
            //this.注文単価 == null or 
            if (this.limitPrice == null)
            {
                log.debug("注文単価が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02118,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "注文単価が未指定です。" + this.limitPrice);
            }
            
            //this.注文単価 != 数字 or 
            if (!WEB3StringTypeUtility.isNumber(this.limitPrice))
            {
                log.debug("注文単価が数値以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02119,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "注文単価が数値以外の値です。" + this.limitPrice);
            }
            
            //this.注文単価 < 0 or
            if (Double.parseDouble(this.limitPrice) <= 0)
            {
                log.debug("注文単価が0以下の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02120,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "注文単価が0以下の値です。" + this.limitPrice);
            }

            //this.注文単価-整数部 > 6桁 or this.注文単価-少数部 > 5桁 
            int l_limitPriceIntegerLength = WEB3StringTypeUtility.getIntegerDigits(this.limitPrice);   
            int l_limitPriceFractionLength = WEB3StringTypeUtility.getFractionDigits(this.limitPrice);
            if (l_limitPriceIntegerLength > 6 || l_limitPriceFractionLength > 5)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02093.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02093,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "注文単価 = "   
                        + this.limitPrice);
            }
			
        }

        //６－２） 
        //this.注文単価区分 == ”成行” and 
        if ((WEB3OrderPriceDivDef.MARKET_PRICE).equals(this.orderPriceDiv))
        {
            //this.注文単価 != （null or ”0”） 
            //の場合、例外をスローする。
            if (!WEB3StringTypeUtility.isEmpty(this.limitPrice) 
                && !("0").equals(this.limitPrice))
            {
                log.debug("注文単価区分が“0：成行”の場合は、注文単価指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00116,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "注文単価区分が“0：成行”の場合は、注文単価指定不可です。"
                        + "this.注文単価区分 = "
                        + this.orderPriceDiv
                        + "this.注文単価 = "
                        + this.limitPrice);
            }
        }
         
        //７）期限のチェック 
        //７－１） 
        //this.注文期限区分 == ”当日限り” and 
        //this.注文有効期限 != null 
        //の場合、例外をスローする。 
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType) 
            && this.expirationDate != null)
        {
            log.debug("注文期限区分が“1：当日限り”の場合は、注文有効期限指定不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00117,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文期限区分が“1：当日限り”の場合は、注文有効期限指定不可です。"
                    + "this.注文期限区分 = "
                    + this.expirationDateType
                    + "this.注文有効期限"
                    + this.expirationDate);
        }
        
        //７－２） 
        //this.注文期限区分 == ”出来るまで注文” and 
        //this.注文有効期限 == null 
        //の場合、例外をスローする。 
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
            && this.expirationDate == null)
        {
            log.debug("出来るまで注文の場合は、注文有効期限を指定してください。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00210,
                this.getClass().getName() + STR_METHOD_NAME,
                "出来るまで注文の場合は、注文有効期限を指定してください。"
                    + "this.注文期限区分 = "
                    + this.expirationDateType
                    + "this.注文有効期限 = "
                    + this.expirationDate);
        }
        
        //８）発注条件のチェック１（”指定なし”） 
        //this.発注条件 == ”指定なし” and 
        //( 
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            //this.逆指値用発注条件単価 != （null or ”0”） or 
            if ((this.stopOrderCondPrice != null) &&
                !"0".equals(stopOrderCondPrice))
            {
                log.debug("発注条件区分が“0：指定なし”の場合は、" +
                    "逆指値用発注条件単価が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01872,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“0：指定なし”の場合は、" +
                    "逆指値用発注条件単価が指定不可です。"
                        + "this.発注条件 = "
                        + this.orderCondType
                        + "this.逆指値用発注条件単価 = "
                        + this.stopOrderCondPrice);
            }
            
            //this.逆指値用発注条件演算子 != null or 
            if (this.stopOrderCondOperator != null)
            {
                log.debug("発注条件区分が“0：指定なし”の場合は、" +
                    "逆指値用発注条件演算子が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01873,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“0：指定なし”の場合は、" +
                    "逆指値用発注条件演算子が指定不可です。"
                        + "this.逆指値用発注条件演算子 = "
                        + this.stopOrderCondOperator);
            }
            
            //this.W指値用発注条件単価 != （null or ”0”） or 
            if ((this.wlimitOrderCondPrice != null) &&
                !("0".equals(this.wlimitOrderCondPrice)))
            {
                log.debug("発注条件区分が“0：指定なし”の場合は、" +
                    "W指値用発注条件単価が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01874,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“0：指定なし”の場合は、" +
                    "W指値用発注条件単価が指定不可です。"
                        + "this.W指値用発注条件単価 = "
                        + this.wlimitOrderCondPrice);
            }
       
            //this.W指値用発注条件演算子 != null or 
            if (this.wlimitOrderCondOperator != null)
            {
                log.debug("発注条件区分が“0：指定なし”の場合は、" +
                    "W指値用発注条件演算子が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01875,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“0：指定なし”の場合は、" +
                    "W指値用発注条件演算子が指定不可です。"
                        + "this.W指値用発注条件演算子 = "
                        + this.wlimitOrderCondOperator);
            }
        
            //this.W指値用注文単価区分 != null or 
            if (this.wLimitOrderPriceDiv != null)
            {
                log.debug("発注条件区分が“0：指定なし”の場合は、" +
                    "W指値用注文単価区分が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01876,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“0：指定なし”の場合は、" +
                    "W指値用注文単価区分が指定不可です。"
                        + "this.W指値用注文単価区分 = "
                        + this.wLimitOrderPriceDiv);
            }
        
            //this.W指値用注文単価 != （null or ”0”） 
            //) 
            //の場合、例外をスローする。 
            if ((this.wLimitPrice != null) &&
                !"0".equals(this.wLimitPrice))
            {
                log.debug("発注条件区分が“0：指定なし”の場合は、" +
                    "W指値用注文単価が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01877,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“0：指定なし”の場合は、" +
                    "W指値用注文単価が指定不可です。"
                        + "this.W指値用注文単価 = "
                        + this.wLimitPrice);
            }
        }

        //９）発注条件のチェック２（”逆指値”）  
        //９－１) 
        //this.発注条件 == ”逆指値” and 
        //( 
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType))
        {
            //this.W指値用発注条件単価 != （null or ”0”） or 
            if ((this.wlimitOrderCondPrice != null) 
                && !"0".equals(this.wlimitOrderCondPrice))
            {
                log.debug("発注条件区分が“1：逆指値”の場合は、" +
                    "W指値用発注条件単価が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01878,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“1：逆指値”の場合は、" +
                    "W指値用発注条件単価が指定不可です。"
                        + "this.W指値用発注条件単価 = "
                        + this.wlimitOrderCondPrice);
            }
            
            //this.W指値用発注条件演算子 != null or 
            if (this.wlimitOrderCondOperator != null)
            {
                log.debug("発注条件区分が“1：逆指値”の場合は、" +
                    "W指値用発注条件演算子が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01879,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“1：逆指値”の場合は、" +
                    "W指値用発注条件演算子が指定不可です。"
                        + "this.W指値用発注条件演算子 = "
                        + this.wlimitOrderCondOperator);
            }
        
            //this.W指値用注文単価区分 != null or 
            if (this.wLimitOrderPriceDiv != null)
            {
                log.debug("発注条件区分が“1：逆指値”の場合は、" +
                    "W指値用発注条件演算子が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01880,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“1：逆指値”の場合は、" +
                    "W指値用注文単価区分が指定不可です。"
                        + "this.W指値用注文単価区分 = "
                        + this.wLimitOrderPriceDiv);
            }
        
            //this.W指値用注文単価 != （null or ”0”） 
            //) 
            //の場合、例外をスローする。 
            if ((this.wLimitPrice != null) &&
                !"0".equals(this.wLimitPrice))
            {
                log.debug("発注条件区分が“1：逆指値”の場合は、" +
                    "W指値用注文単価が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01881,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“1：逆指値”の場合は、" +
                    "W指値用注文単価が指定不可です。"
                        + "this.W指値用注文単価 = "
                        + this.wLimitPrice);
            }
            
            //９－２) 
            //this.発注条件 == ”逆指値” and 
            //( 
            //this.逆指値用発注条件単価 == null or
            if (this.stopOrderCondPrice == null)
            {
                log.debug("発注条件区分が“逆指値”なのに、" +
                    "逆指値用発注条件単価が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02121,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“逆指値”なのに、" +
                    "逆指値用発注条件単価が未指定です。"
                        + "this.逆指値用発注条件単価 = "
                        + this.stopOrderCondPrice);
            }
             
            //this.逆指値用発注条件単価 != 数字 or 
            if (!WEB3StringTypeUtility.isNumber(this.stopOrderCondPrice))
            {
                log.debug("発注条件区分が“逆指値”で、" +
                    "逆指値用発注条件単価が数値以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02123,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“逆指値”で、" +
                    "逆指値用発注条件単価が数値以外の値です。"
                        + "this.逆指値用発注条件単価 = "
                        + this.stopOrderCondPrice);
            }
            
            //this.逆指値用発注条件単価 < 0 or 
            if (Double.parseDouble(this.stopOrderCondPrice) <= 0)
            {
                log.debug("発注条件区分が“逆指値”で、" +
                    "逆指値用発注条件単価が0以下の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02122,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“逆指値”で、" +
                    "逆指値用発注条件単価が0以下の値です。"
                        + "this.逆指値用発注条件単価 = "
                        + this.stopOrderCondPrice);
            }

            //this.逆指値用発注条件単価-整数部 > 6桁 or this.逆指値用発注条件単価-少数部 > 5桁 
            int l_stopOrderCondPriceIntegerLength = WEB3StringTypeUtility.getIntegerDigits(this.stopOrderCondPrice);   
            int l_stopOrderCondPriceFractionLength = WEB3StringTypeUtility.getFractionDigits(this.stopOrderCondPrice);
            if (l_stopOrderCondPriceIntegerLength > 6 || l_stopOrderCondPriceFractionLength > 5)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02124.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02124,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "逆指値用発注条件単価 = "   
                        + this.stopOrderCondPrice);
            }
            
            //this.逆指値用発注条件演算子 == null or 
            if (this.stopOrderCondOperator == null)
            {
                log.debug("発注条件区分が“逆指値”で、" +
                    "逆指値用発注条件演算子が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02125,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“逆指値”で、" +
                    "逆指値用発注条件演算子が未指定です。"
                        + "this.逆指値用発注条件演算子 = "
                        + this.stopOrderCondPrice);
            }

            //this.逆指値用発注条件演算子 != （”以上” or ”以下”） 
            //) 
            //の場合、例外をスローする。 
            if(!WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(
                this.stopOrderCondOperator)
                && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(
                    this.stopOrderCondOperator))
            {
                log.debug("発注条件区分が“逆指値”で、" +
                    "逆指値用発注条件演算子が未定義の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02126,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“逆指値”で、" +
                    "逆指値用発注条件演算子が未定義の値です。"
                        + "this.逆指値用発注条件演算子 = "
                        + this.stopOrderCondPrice);
            }    
        }

        //１０）発注条件のチェック３（”W指値”） 
        //１０－１） 
        //this.発注条件 == ”W指値” and 
        //( 
        if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType))
        {
            //this.逆指値用発注条件単価 != （null or ”0”） or 
            if((this.stopOrderCondPrice != null) 
                && !"0".equals(this.stopOrderCondPrice))
            {
                log.debug("発注条件区分が“2：W指値”の場合は、" +
                    "逆指値用発注条件単価が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01882,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“2：W指値”の場合は、" +
                    "逆指値用発注条件単価が指定不可です。"
                        + "this.逆指値用発注条件演算子 = "
                        + this.stopOrderCondPrice);
            }
            
            //this.逆指値用発注条件演算子 != null 
            //) の場合、例外をスローする。 
            if(this.stopOrderCondOperator != null)
            {
                log.debug("発注条件区分が“2：W指値”の場合は、" +
                    "逆指値用発注条件演算子が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01883,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“2：W指値”の場合は、" +
                    "逆指値用発注条件演算子が指定不可です。"
                        + "this.逆指値用発注条件演算子 = "
                        + this.stopOrderCondOperator);
            }
            
            //１０－２） 
            //this.発注条件 == ”W指値” and 
            //( 
            //this.W指値用発注条件演算子 == null or 
            if(this.wlimitOrderCondOperator == null)
            {
                log.debug("発注条件区分が“W指値”なのに、" +
                    "W指値用発注条件演算子が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02127,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“W指値”なのに、" +
                    "W指値用発注条件演算子が未指定です。" 
                    + "this.W指値用発注条件演算子 = "
                     + this.wlimitOrderCondOperator);
            }
            
            //this.W指値用発注条件演算子 != （”以上” or ”以下”） or 
            if(!WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.wlimitOrderCondOperator)
                && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.wlimitOrderCondOperator))
            {
                log.debug("発注条件区分が“W指値”で、" +
                    "W指値用発注条件演算子が未定義の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02128,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“W指値”で、" +
                    "W指値用発注条件演算子が未定義の値です。" 
                    + "this.W指値用発注条件演算子 = " 
                    + this.wlimitOrderCondOperator);
            }
            
            //this.W指値用注文単価区分 == null or 
            if(this.wLimitOrderPriceDiv == null)
            {
                log.debug("発注条件区分が“W指値”で、" +
                    "W指値用注文単価区分が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02129,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“W指値”なのに、" +
                    "W指値用注文単価区分が未指定です。" 
                    + "this.W指値用注文単価区分 = " 
                    + this.wLimitOrderPriceDiv);
            }
            
            //this.W指値用注文単価区分 != （”成行” or ”指値”） or 
            if (!WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv)
                && !WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv))
            {
                log.debug("発注条件区分が“W指値”で、" +
                    "W指値用注文単価区分が未定義の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02130,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“W指値”で、" +
                    "W指値用注文単価区分が未定義の値です。" 
                    + "this.W指値用注文単価区分 = " 
                    + this.wLimitOrderPriceDiv);
            }
            
            //this.W指値用発注条件単価 == null or 
            if(this.wlimitOrderCondPrice == null)
            {
                log.debug("発注条件区分が“W指値”で、" +
                    "W指値用発注条件単価が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02131,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“W指値”なのに、" +
                    "W指値用発注条件単価が未指定です。" 
                    + "this.W指値用発注条件単価 = " 
                    + this.wlimitOrderCondPrice);
            }
            
            //this.W指値用発注条件単価 != 数字 or 
            if(!WEB3StringTypeUtility.isNumber(this.wlimitOrderCondPrice))
            {
                log.debug("発注条件区分が“W指値”で、" +
                    "W指値用発注条件単価が数値以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02132,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“W指値”で、" +
                    "W指値用発注条件単価が数値以外の値です。" 
                    + "this.W指値用発注条件単価 = " 
                    + this.wlimitOrderCondPrice);
            }
            
            //this.W指値用発注条件単価 < 0 or 
            if(Double.parseDouble(this.wlimitOrderCondPrice) <= 0)
            {
                log.debug("発注条件区分が“W指値”で、" +
                    "W指値用発注条件単価が0以下の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02133,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注条件区分が“W指値”で、" +
                    "W指値用発注条件単価が0以下の値です。" 
                    + "this.W指値用発注条件単価 = " 
                    + this.wlimitOrderCondPrice);
            }

            //this.W指値用発注条件単価-整数部 > 6桁 or this.W指値用発注条件単価-少数部 > 5桁 
            int l_wlimitOrderCondPriceIntegerLength = WEB3StringTypeUtility.getIntegerDigits(this.wlimitOrderCondPrice);   
            int l_wlimitOrderCondPriceFractionLength = WEB3StringTypeUtility.getFractionDigits(this.wlimitOrderCondPrice);
            if (l_wlimitOrderCondPriceIntegerLength > 6 || l_wlimitOrderCondPriceFractionLength > 5)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02134.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02134,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "W指値用発注条件単価 = "   
                        + this.wlimitOrderCondPrice);
            }
            
        }

        //１１）W指値用注文単価区分・W指値用注文単価 の整合性チェック 
        //１１－１） 
        //this.W指値用注文単価区分 == ”指値” and 
        //( 
        if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
        {
            //this.W指値用注文単価 == null or 
            if (this.wLimitPrice == null)
            {
                log.debug("W指値用注文単価区分が”指値”なのに、" +
                    "W指値用注文単価が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02135,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "W指値用注文単価区分が”指値”なのに、" +
                    "W指値用注文単価が未指定です。" 
                    + "this.W指値用注文単価  = " 
                    + this.wLimitPrice);
            }
            
            //this.W指値用注文単価 != 数字 or 
            if (!WEB3StringTypeUtility.isNumber(this.wLimitPrice))
            {
                log.debug("W指値用注文単価区分が”指値”なのに、" +
                    "W指値用注文単価が数値以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02136,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "W指値用注文単価区分が”指値”で、" +
                    "W指値用注文単価が数値以外の値です。" 
                    + "this.W指値用注文単価  = " 
                    + this.wLimitPrice);
            }
            
            //this.W指値用注文単価 < 0 or 
            if (Double.parseDouble(this.wLimitPrice) <= 0)
            {
                log.debug("W指値用注文単価区分が”指値”なのに、" +
                    "W指値用注文単価が0以下の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02137,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "W指値用注文単価区分が”指値”で、" +
                    "W指値用注文単価が0以下の値です。" 
                    + "this.W指値用注文単価  = " 
                    + this.wLimitPrice);
            }            
            //this.W指値用注文単価-整数部 > 6桁 or this.W指値用注文単価-少数部 > 5桁 
            int l_wLimitPriceIntegerLength = WEB3StringTypeUtility.getIntegerDigits(this.wLimitPrice);   
            int l_wLimitPriceFractionLength = WEB3StringTypeUtility.getFractionDigits(this.wLimitPrice);
            if (l_wLimitPriceIntegerLength > 6 || l_wLimitPriceFractionLength > 5)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02138.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02138,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "W指値用注文単価 = "   
                        + this.wLimitPrice);
            }
           
        }

        //１１－２） 
        //this.W指値用注文単価区分 == ”成行” and 
        if ((WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv)))
        {
            //this.W指値用注文単価 != （null or ”0”） 
            //の場合、例外をスローする。
            if(this.wLimitPrice != null && !"0".equals(this.wLimitPrice))
            {
                log.debug("W指値用注文単価区分が”成行” なので、" +
                    "W指値用注文単価が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02139,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "W指値用注文単価区分が”成行” なので、" +
                    "W指値用注文単価が指定不可です。" 
                    + "this.W指値用注文単価  = " 
                    + this.wLimitPrice);
            }
        }
        
        //１２）注文期限・執行条件のチェック 
        //this.注文期限区分 == ”出来るまで注文” and 
        //this.執行条件 != ”条件なし” 
        //の場合、例外をスローする。 
        if(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType))
        {
            if(!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
            {
                log.debug("注文期限区分が“2：出来るまで注文”の場合は、執行条件に“" +
                    "1：無条件”を設定して下さい");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00125,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "注文期限区分が“2：出来るまで注文”の場合は、執行条件に“" +
                    "1：無条件”を設定して下さい。" 
                    + "this.執行条件  = " 
                    + this.execCondType);
            }
        }

        //１３）注文単価・執行条件のチェック 
        //this.注文単価区分 != ”指値” and 
        //this.執行条件 == ”不出来引け成行” 
        //の場合、例外をスローする。 
        if(!WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType))
            {
                log.debug("執行条件が“7：不出来引け成行”の場合は、注文単価区分が“" +
                    "1：指値”になれません（不出来引け成行は「指値」のみ指定可能）");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00114,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "執行条件が“7：不出来引け成行”の場合は、注文単価区分が“" +
                    "1：指値”になれません（不出来引け成行は「指値」のみ指定可能）" 
                    + "this.執行条件  = " 
                    + this.execCondType);
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
