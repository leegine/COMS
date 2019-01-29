head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPAdjustCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者余力調整共通リクエストクラス(WEB3AdminMutualTPAdjustCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 韋念瓊 (中訊) 新規作成 
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信管理者余力調整共通リクエストクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualTPAdjustCommonRequest extends WEB3GenRequest 
{
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512191010L;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualTPAdjustCommonRequest.class);
    
    /**
     * (顧客コード)
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (銘柄コード)
     * 銘柄コード<BR>
     */
    public String mutualProductCode;
    
    /**
     * (精算金額)
     * 精算金額<BR>
     */
    public String settlePrice;
    
    /**
     * (発注日)
     * 発注日<BR>
     */
    public Date orderBizDate;
    
    /**
     * (約定日)
     * 約定日<BR>
     */
    public Date executionTimestamp;
    
    /**
     * (受渡日)
     * 受渡日<BR>
     */
    public Date deliveryDate;
     
    /**
     * (validate)
     * 当リクエストデータの整合性チェックを行う。  <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@顧客コードチェック <BR>
     *      this.顧客コード==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_00835 <BR>
     * <BR>
     * ２）　@銘柄コードチェック <BR>
     *      this.銘柄コード==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_01252 <BR>
     * <BR>
     * ３）　@精算金額チェック <BR>
     * 　@３－１) this.精算金額==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_02334 <BR>
     *   ３－２) this.精算金額の桁数＞11桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_02335 <BR>
     * <BR>
     * ４）　@発注日チェック <BR>
     *      this.発注日==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_00406 <BR>
     * <BR>
     * ５）　@約定日チェック <BR>
     *      this.約定日==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_02184 <BR>
     * <BR>
     * ６）　@受渡日チェック <BR>
     *      this.受渡日==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_01079 <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4153B64902D6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@顧客コードチェック 
        //this.顧客コード==nullの場合、例外をスローする。 

        if (this.accountCode == null)
        {
            log.debug("顧客コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードが未指定です。");
        }
        
        //２）　@銘柄コードチェック 
        //this.銘柄コード==nullの場合、例外をスローする。 
        if (this.mutualProductCode == null)
        {
            log.debug("銘柄コードの指定がありません。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01252,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードの指定がありません。");
        }
        
        //３）　@精算金額チェック 
        //　@３－１) this.精算金額==nullの場合、例外をスローする。 
        if (this.settlePrice == null)
        {
            log.debug("精算金額が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02334,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "精算金額が未指定です。");
        }
        //  ３－２) this.精算金額の桁数＞11桁の場合、例外をスローする。   
        if (this.settlePrice.length() > 11)
        {
            log.debug("精算金額が11桁を超えました。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02335,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "精算金額が11桁を超えました。");
        }
        
        //４）　@発注日チェック 
        //this.発注日==nullの場合、例外をスローする。 
        if (this.orderBizDate == null)
        {
            log.debug("発注日が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注日が未指定です。");
        }
        
        //５）　@約定日チェック 
        //this.約定日==nullの場合、例外をスローする。 
        if (this.executionTimestamp == null)
        {
            log.debug("約定日時が未入力です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02184,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定日時が未入力です。");
        }
        
        //６）　@受渡日チェック 
        //this.受渡日==nullの場合、例外をスローする。 
        if (this.deliveryDate == null)
        {
            log.debug("受渡日が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "受渡日が未指定です。");
        }        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF81440100
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
