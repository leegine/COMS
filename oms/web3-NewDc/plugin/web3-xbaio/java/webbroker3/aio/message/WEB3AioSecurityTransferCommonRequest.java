head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替共通リクエスト(WEB3AioSecurityTransferCommomRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 屈陽   (中訊) 新規作成 
                   2006/11/03 何文敏 (中訊) モデルNo.678 
*/

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioDepositTypeDivDef;
import webbroker3.aio.define.WEB3AioMessageCommodityDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (証券振替共通リクエスト)<BR>
 * 証券振替共通リクエストクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferCommonRequest extends WEB3GenRequest 
{
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412071537L;     
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferCommonRequest.class);
    
    /**
     * (商品タイプ)<BR>
     * 銘柄の商品タイプ<BR>
     * <BR>
     * 1： 株式<BR>
     * 2： 債券<BR>
     * 3： 投資信託<BR>
     */
    public String instrumentsType;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0： 一般<BR>
     * 1： 特定<BR>
     */
    public String taxType;
    
    /**
     * (預り区分)<BR>
     * 振替元預り区分<BR>
     * <BR>
     * 1： 保護（保護から代用への振替）<BR>
     * 2： 代用（代用から保護への振替）
     */
    public String depositDiv;
    
    /**
     * @@roseuid 41B0255E0232
     */
    public WEB3AioSecurityTransferCommonRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）商品タイプ<BR>
     * <BR>
     *   リクエストデータ.商品タイプ != (1, 2, 3)<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01295<BR> 
     * <BR>
     * ２）銘柄コード<BR>
     * <BR>
     *   リクエストデータ.銘柄コード = null or<BR>
     *   リクエストデータ.銘柄コード != 半角数字<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00079<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00815<BR> 
     * <BR>
     * ３）口座区分<BR>
     * <BR>
     *   リクエストデータ.口座区分 != (0, 1)<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01303<BR> 
     * <BR>
     * ４）預り区分<BR>
     * <BR>
     *   リクエストデータ.預り区分 != (1, 2)<BR>
     * <BR>
     *   の場合、例外をスローする。
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01297<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4153D8210102
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        //１）商品タイプ
        //  リクエストデータ.商品タイプ != (1, 2, 3)
        //  の場合、例外をスローする。
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01295 
        if (!(WEB3AioMessageCommodityDef.EQUITY.equals(this.instrumentsType) ||
            WEB3AioMessageCommodityDef.BOND.equals(this.instrumentsType) ||
            WEB3AioMessageCommodityDef.MUTUAL_FUND.equals(this.instrumentsType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01295,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.商品タイプ != (1, 2, 3), " +
                "リクエストデータ.商品タイプ = " + this.instrumentsType);               
        }

        //２）銘柄コード
        //  リクエストデータ.銘柄コード = null or
        //  リクエストデータ.銘柄コード != 半角数字
        //  の場合、例外をスローする。
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00079 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00815 
        if (WEB3StringTypeUtility.isEmpty(this.productCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.銘柄コード = null");              
        }
        else if(!WEB3StringTypeUtility.isNumber(this.productCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.銘柄コード != 半角数字, " +
                "リクエストデータ.銘柄コード = " + this.productCode);                
        }
        
        //３）口座区分
        //  リクエストデータ.口座区分 != (0, 1)
        //  の場合、例外をスローする。
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01303 
        if (!(WEB3AccountDivDef.NORMAL.equals(this.taxType) || 
            WEB3AccountDivDef.SPECIAL.equals(this.taxType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01303,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.口座区分 != (0, 1)," +
                "リクエストデータ.口座区分 = " + this.taxType);             
        }
        
        //４）預り区分
        //  リクエストデータ.預り区分 != (1, 2)
        //  の場合、例外をスローする。
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01297    
        if (!(WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(this.depositDiv) ||
            WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY.equals(this.depositDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01297,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.預り区分 != (1, 2), " +
                "リクエストデータ.預り区分 = " + this.depositDiv);                  
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 証券振替共通レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return null;    
    }
}
@
