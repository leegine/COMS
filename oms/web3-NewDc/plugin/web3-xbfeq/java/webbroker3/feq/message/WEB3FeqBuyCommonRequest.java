head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.37.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuyCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式買付共通リクエスト(WEB3FeqBuyCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式買付共通リクエスト)<BR>
 * 外国株式買付共通リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqBuyCommonRequest extends WEB3FeqCommonRequest 
{    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_buyCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (決済区分)<BR>
     * 決済区分<BR>
     * <BR>
     * 0：円貨<BR>
     * 1：外貨<BR>
     */
    public String settleDiv;
    
    /**
     * (特定口座区分)<BR>
     * 特定口座区分<BR>
     * <BR>
     * 0：一般<BR>
     * 1：特定<BR>
     */
    public String taxType;
    
    /**
     * @@roseuid 42CE3A050148
     */
    public WEB3FeqBuyCommonRequest() 
    {
     
    }
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBuyCommonRequest.class);
        
    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）スーパークラスのvalidate()メソッドを呼び出す。<BR>
     * <BR>
     * ２）銘柄コードチェック<BR>
     *    this.銘柄コード == null<BR>
     * <BR>
     *    の場合、「銘柄コードがnull」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * ３）決済区分チェック<BR>
     * <BR>
     * ３－１）<BR>
     *    this.決済区分 == null<BR>
     * <BR>
     *    の場合、「決済区分がnull」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02111<BR>
     * <BR>
     * ３－２）<BR>
     *    this.決済区分 != （”円貨” or ”外貨”）<BR>
     * <BR>
     *    の場合、「決済区分が未定義の値」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02112<BR>
     * <BR>
     * ４）特定口座区分チェック<BR>
     * <BR>
     * ４－１）<BR>
     *    this.特定口座区分 == null<BR>
     * <BR>
     *    の場合、「特定口座区分がnull」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02113<BR>
     * <BR>
     * ４－２）<BR>
     *    this.特定口座区分 != （”一般” or ”特定”）<BR>
     * <BR>
     *    の場合、「特定口座区分が未定義の値」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02114<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 428C426800A2
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）スーパークラスのvalidate()メソッドを呼び出す。
        super.validate();
        
        //２）銘柄コードチェック 
        //this.銘柄コード == null
        //の場合、「銘柄コードがnull」の例外をスローする。
        if (this.productCode == null)
        {
            log.debug("銘柄コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄コードが未指定です。" + this.productCode);
        }
        
        //３）決済区分チェック
        //３－１）this.決済区分 == null
        //の場合、「決済区分がnull」の例外をスローする。
        if (this.settleDiv == null)
        {
            log.debug("決済区分がnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02111,
                this.getClass().getName() + STR_METHOD_NAME,
                "決済区分がnullです。" + this.settleDiv);
        }
        
        //３－２）this.決済区分 != （”円貨” or ”外貨”）
        //の場合、「決済区分が未定義の値」の例外をスローする。
        if (!WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(this.settleDiv)
            && !WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(this.settleDiv))
        {
            log.debug("決済区分が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02112,
                this.getClass().getName() + STR_METHOD_NAME,
                "決済区分が未定義の値です。" + this.settleDiv);
        }
        
        //４）特定口座区分チェック
        //４－１）this.特定口座区分 == null
        //の場合、「特定口座区分がnull」の例外をスローする。
        if (this.taxType == null)
        {
            log.debug("特定口座区分がnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02113,
                this.getClass().getName() + STR_METHOD_NAME,
                "特定口座区分がnullです。" + this.taxType);
        }
        
        //４－２）
        //this.特定口座区分 != （”一般” or ”特定”）
        //の場合、「特定口座区分が未定義の値」の例外をスローする。
        if (!WEB3TaxTypeSpecialDef.NORMAL.equals(this.taxType) &&
            !WEB3TaxTypeSpecialDef.SPECIAL.equals(this.taxType))
        {
            log.debug("特定口座区分が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02114,
                this.getClass().getName() + STR_METHOD_NAME,
                "特定口座区分が未定義の値です。" + this.taxType);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
