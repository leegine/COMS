head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSellCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式売付共通リクエスト(WEB3FeqSellCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式売付共通リクエスト)<BR>
 * 外国株式売付共通リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqSellCommonRequest extends WEB3FeqCommonRequest 
{    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_sellCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (保有資産ID)<BR>
     * 保有資産ID<BR>
     */
    public String assetId;
    
    /**
     * (決済区分)<BR>
     * 決済区分<BR>
     * <BR>
     * 0：円貨<BR>
     * 1：外貨<BR>
     */
    public String settleDiv;
    
    /**
     * @@roseuid 42CE3A0A0138
     */
    public WEB3FeqSellCommonRequest() 
    {
     
    }
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqSellCommonRequest.class);
        
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）スーパークラスのvalidate()メソッドを呼び出す。<BR>
     * <BR>
     * ２）保有資産IDチェック<BR>
     *    this.保有資産ID == null<BR>
     * <BR>
     *    の場合、「保有資産IDがnull」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_01919<BR>
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
     * ４－２）<BR>
     *    this.決済区分 != （”円貨” or ”外貨”）<BR>
     * <BR>
     *    の場合、「決済区分が未定義の値」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02112<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 4294783B009C
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）スーパークラスのvalidate()メソッドを呼び出す。
        super.validate();
        
        //２）保有資産IDチェック 
        //this.保有資産ID == null
        //の場合、「保有資産IDがnull」の例外をスローする。
        if (this.assetId == null)
        {
            log.debug("保有資産IDが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01919,
                this.getClass().getName() + STR_METHOD_NAME,
                "保有資産IDが未指定(null)です。" + this.assetId);
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
        
        //４－２）this.決済区分 != （”円貨” or ”外貨”）
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
        log.exiting(STR_METHOD_NAME);
    }
}
@
