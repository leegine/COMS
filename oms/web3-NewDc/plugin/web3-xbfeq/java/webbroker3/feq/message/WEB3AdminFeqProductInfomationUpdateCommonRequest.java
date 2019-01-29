head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.30.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductInfomationUpdateCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式銘柄情報更新共通リクエスト(WEB3AdminFeqProductInfomationUpdateCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/27 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqBuySellStopDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者外国株式銘柄情報更新共通リクエスト)<BR>
 * 管理者外国株式銘柄情報更新共通リクエストクラス
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqProductInfomationUpdateCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqProductInfomationUpdateCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_productInfomationUpdateCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * (銘柄名（漢字）)<BR>
     * 銘柄名（漢字）
     */
    public String productNameKanji;
    
    /**
     * (買付停止区分)<BR>
     * 買付停止区分<BR>
     * <BR>
     * 0：買付可能<BR>
     * 1：買付停止
     */
    public String buyStopDiv;
    
    /**
     * (売付停止区分)<BR>
     * 売付停止区分<BR>
     * <BR>
     * 0：売付可能<BR>
     * 1：売付停止
     */
    public String sellStopDiv;
    
    /**
     * (現地銘柄コード)<BR>
     * 画面にて入力された現地銘柄コード
     */
    public String localProductCode;
    
    /**
     * (買付単位)<BR>
     * 画面にて入力された買付単位
     */
    public String buyUnit;
    
    /**
     * (最低買付単位)<BR>
     * 画面にて入力された最低買付単位
     */
    public String minBuyUnit;
    
    /**
     * (売付単位)<BR>
     * 画面にて入力された売付単位
     */
    public String sellUnit;
    
    /**
     * (最低売付単位)<BR>
     * 画面にて入力された最低売付単位
     */
    public String minSellUnit;
    
    /**
     * @@roseuid 42CE39FA0290
     */
    public WEB3AdminFeqProductInfomationUpdateCommonRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）銘柄コード<BR>
     * <BR>
     * １－１）<BR>
     *    this.銘柄コード == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * １－２）<BR>
     *    this.銘柄コード.length() != 5<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00439<BR>
     * <BR>
     * ２）銘柄名（漢字）<BR>
     * <BR>
     *    this.銘柄名（漢字）のバイト長 > 50<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02050<BR>
     * <BR>
     * ３）買付停止区分<BR>
     * <BR>
     *    this.買付停止区分 != （”買付可能” or ”買付停止”）<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02051<BR>
     * <BR>
     * ４）売付停止区分<BR>
     * <BR>
     *    this.売付停止区分 != （”売付可能” or ”売付停止”）<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02052<BR>
     * <BR>
     * ５）現地銘柄コード<BR>
     * <BR>
     * ５－１）<BR>
     *    this.現地銘柄コード == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02053<BR>
     * <BR>
     * ５－２）<BR>
     *    this.現地銘柄コード.length() > 9<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02054<BR>
     * <BR>
     * ５－３）<BR>
     *    this.現地銘柄コード != 数字<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02055<BR>
     * <BR>
     * ６）買付単位<BR>
     * <BR>
     * ６－１）<BR>
     *    this.買付単位 == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02056<BR>
     * <BR>
     * ６－２）<BR>
     *    this.買付単位 != 数字<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02057<BR>
     * <BR>
     * ６－３）<BR>
     *    this.買付単位.length() > 7<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02058<BR>
     * <BR>
     * ６－４）<BR>
     *    this.買付単位 < 0<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02059<BR>
     * <BR>
     * ７）最低買付単位<BR>
     * <BR>
     * ７－１）<BR>
     *    this.最低買付単位 == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02060<BR>
     * <BR>
     * ７－２）<BR>
     *    this.最低買付単位 != 数字<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02061<BR>
     * <BR>
     * ７－３）<BR>
     *    this.最低買付単位.length() > 7<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02062<BR>
     * <BR>
     * ７－４）<BR>
     *    this.最低買付単位 < 0<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02063<BR>
     * <BR>
     * ８）売付単位<BR>
     * <BR>
     * ８－１）<BR>
     *    this.売付単位 == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02064<BR>
     * <BR>
     * ８－２）<BR>
     *    this.売付単位 != 数字<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02065<BR>
     * <BR>
     * ８－３）<BR>
     *    this.売付単位.length() > 7<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02066<BR>
     * <BR>
     * ８－４）<BR>
     *    this.売付単位 < 0<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02067<BR>
     * <BR>
     * ９）最低売付単位<BR>
     * <BR>
     * ９－１）<BR>
     *    this.最低売付単位 == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02068<BR>
     * <BR>
     * ９－２）<BR>
     *    this.最低売付単位 != 数字<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02069<BR>
     * <BR>
     * ９－３）<BR>
     *    this.最低売付単位.length() > 7<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02070<BR>
     * <BR>
     * ９－４）<BR>
     *    this.最低売付単位 < 0<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02071<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B2B96D01E3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１－１）
        //   this.銘柄コード == null
        //   の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.productCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                getClass().getName() + STR_METHOD_NAME,
                "銘柄コードが未入力です。");
        }
        
        //１－２）
        //   this.銘柄コード.length() != 5
        //   の場合、例外をスローする。
        if (this.productCode.length() != 5)
        {
            String l_strMessage = "銘柄コードのサイズが不正です。「"+ this.productCode + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //２）銘柄名（漢字）        
        //   this.銘柄名（漢字）のバイト長 > 50        
        //   の場合、例外をスローする。
        if (this.productNameKanji != null
            && WEB3StringTypeUtility.getByteLength(this.productNameKanji) > 50)
        {
            String l_strMessage = "銘柄名（漢字）のサイズが不正です。「"+ this.productNameKanji + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02050,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        //３）買付停止区分
        //   this.買付停止区分 != （”買付可能” or ”買付停止（取引所規制）　@or”買付停止（当社規制）”）
        //   の場合、例外をスローする。
        if (!WEB3FeqBuySellStopDivDef.CAN.equals(this.buyStopDiv) 
            && !WEB3FeqBuySellStopDivDef.STOP_MARKET.equals(this.buyStopDiv)
            && !WEB3FeqBuySellStopDivDef.STOP_COMPANY.equals(this.buyStopDiv))
        {
            String l_strMessage = "買付停止区分の値が不正です。「"+ this.buyStopDiv + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02051,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //４）売付停止区分
        //   this.売付停止区分 != （”売付可能” or ”売付停止（取引所規制）　@or”売付停止（当社規制）”）
        //   の場合、例外をスローする。
        if (!WEB3FeqBuySellStopDivDef.CAN.equals(this.sellStopDiv) 
            && !WEB3FeqBuySellStopDivDef.STOP_MARKET.equals(this.sellStopDiv)
            && !WEB3FeqBuySellStopDivDef.STOP_COMPANY.equals(this.sellStopDiv))
        {
            String l_strMessage = "売付停止区分の値が不正です。「"+ this.sellStopDiv + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02052,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //５）現地銘柄コード
        //５－１）
        //   this.現地銘柄コード == null
        //   の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.localProductCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02053,
                getClass().getName() + STR_METHOD_NAME,
                "現地銘柄コードが未入力です。");
        }
        
        //５－２）
        //   this.現地銘柄コード.length() > 9
        //   の場合、例外をスローする。
        if (this.localProductCode.length() > 9)
        {
            String l_strMessage = "現地銘柄コードのサイズが不正です。「"+ this.localProductCode + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02054,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //５－３）
        //   this.現地銘柄コード != 数字
        //   の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.localProductCode))
        {
            String l_strMessage = "現地銘柄コードが数値以外の値です。「"+ this.localProductCode + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02055,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //６）買付単位
        //６－１）
        //   this.買付単位 == null
        //   の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.buyUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02056,
                getClass().getName() + STR_METHOD_NAME,
                "買付単位が未入力です。");
        }
        
        //６－２）
        //   this.買付単位 != 数字
        //   の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.buyUnit))
        {
            String l_strMessage = "買付単位が数値以外の値です。「" + this.buyUnit + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02057,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //６－３）
        //   this.買付単位.length() > 7
        //   の場合、例外をスローする。
        //　@class: WEB3BusinessLayerException
        //　@tag:   BUSINESS_ERROR_02058
        if (this.buyUnit.length() > 7)
        {
            String l_strMessage = "買付単位のサイズが不正です。「"+ this.buyUnit + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02058,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //６－４）
        //   this.買付単位 < 0        
        //   の場合、例外をスローする。
        int l_intBuyUnit = Integer.parseInt(this.buyUnit);
        if (l_intBuyUnit < 0)
        {
            String l_strMessage = "買付単位がマイナスの値です。「"+ this.buyUnit + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02059,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //７）最低買付単位
        //７－１）
        //   this.最低買付単位 == null
        //   の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.minBuyUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02060,
                getClass().getName() + STR_METHOD_NAME,
                "最低買付単位が未入力です。");
        }
        
        //７－２）
        //   this.最低買付単位 != 数字        
        //   の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.minBuyUnit))
        {
            String l_strMessage = "最低買付単位が数値以外の値です。「" + this.minBuyUnit + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02061,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //７－３）
        //   this.最低買付単位.length() > 7
        //   の場合、例外をスローする。
        if (this.minBuyUnit.length() > 7)
        {
            String l_strMessage = "最低買付単位のサイズが不正です。「"+ this.minBuyUnit + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02062,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //７－４）
        //   this.最低買付単位 < 0
        //   の場合、例外をスローする。
        int l_intMinBuyUnit = Integer.parseInt(this.minBuyUnit);
        if (l_intMinBuyUnit < 0)
        {
            String l_strMessage = "最低買付単位がマイナスの値です。「"+ this.minBuyUnit + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02063,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //８）売付単位
        //８－１）
        //   this.売付単位 == null
        //   の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.sellUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02064,
                getClass().getName() + STR_METHOD_NAME,
                "売付単位が未入力です。");
        }
        
        //８－２）
        //   this.売付単位 != 数字
        //   の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.sellUnit))
        {
            String l_strMessage = "売付単位が数値以外の値です。「" + this.sellUnit + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02065,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //８－３）
        //   this.売付単位.length() > 7
        //   の場合、例外をスローする。
        if (this.sellUnit.length() > 7)
        {
            String l_strMessage = "売付単位のサイズが不正です。「"+ this.sellUnit + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02066,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //８－４）
        //   this.売付単位 < 0
        //   の場合、例外をスローする。
        int l_intSellUnit = Integer.parseInt(this.sellUnit);
        if (l_intSellUnit < 0)
        {
            String l_strMessage = "売付単位がマイナスの値です。「"+ this.sellUnit + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02067,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //９）最低売付単位
        //９－１）
        //   this.最低売付単位 == null
        //   の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.minSellUnit))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02068,
                getClass().getName() + STR_METHOD_NAME,
                "最低売付単位が未入力です。");
        }
        
        //９－２）
        //   this.最低売付単位 != 数字
        //   の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.minSellUnit))
        {
            String l_strMessage = "最低売付単位が数値以外の値です。「" + this.minSellUnit + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02069,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //９－３）
        //   this.最低売付単位.length() > 7
        //   の場合、例外をスローする。
        if (this.minSellUnit.length() > 7)
        {
            String l_strMessage = "最低売付単位のサイズが不正です。「"+ this.minSellUnit + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02070,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //９－４）
        //   this.最低売付単位 < 0
        //   の場合、例外をスローする。
        int l_intMinSellUnit = Integer.parseInt(this.minSellUnit);
        if (l_intMinSellUnit < 0)
        {
            String l_strMessage = "最低売付単位がマイナスの値です。「"+ this.minSellUnit + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02071,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
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
}@
