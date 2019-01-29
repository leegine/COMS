head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.44.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付共通リクエスト(WEB3BondApplyBuyCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (債券応募/買付共通リクエスト)<BR>
 * 債券応募/買付共通リクエスト<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondApplyBuyCommonRequest extends WEB3GenRequest 
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondApplyBuyCommonRequest.class);
    
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_applyBuyCommon";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L; 
    
    /**
     * (取引区分)<BR>
     * 取引区分<BR>
     * <BR>
     * １：応募<BR>
     * ２：買付<BR>
     */
    public String tradeDiv;
    
    /**
     * (銘柄ID)<BR>
     * 銘柄ID<BR>
     */
    public String productId;
    
    /**
     * (決済区分)<BR>
     * 決済区分<BR>
     * <BR>
     * 1：円貨<BR>
     * 2：外貨<BR>
     */
    public String settleDiv;
    
    /**
     * (額面金額)<BR>
     * 額面金額<BR>
     */
    public String faceAmount;
    
    /**
     * (紹介区分)<BR>
     * 紹介区分<BR>
     * <BR>
     * 1：直接取引<BR>
     * 2：単純紹介<BR>
     * 3：商品紹介<BR>
     * 4：仲介取引<BR>
     */
    public String introduceStoreDiv;
    
    /**
     * (紹介店コード)<BR>
     * 紹介店コード<BR>
     */
    public String introduceStoreCode;
    
    /**
     * @@roseuid 44FBFD38038A
     */
    public WEB3BondApplyBuyCommonRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性をチェックする。<BR> 
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR> 
     * <BR>
     * １）　@取引区分チェック<BR>
     * 　@１－１）　@取引区分 == nullの場合、例外をスローする。<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_00601<BR>
     * 　@１－２）　@取引区分が以下の値でない場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@1： 応募<BR>
     * 　@　@　@　@　@　@　@　@2： 買付<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_00602<BR>
     * <BR>
     * ２）　@銘柄IDチェック <BR>
     * 　@　@　@銘柄ID == nullの場合、例外をスローする。<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_02229 <BR>
     * <BR>
     * ３）　@決済区分チェック<BR> 
     * 　@３－１）　@決済区分 == nullの場合、例外をスローする。<BR> 
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_02111<BR>
     * 　@３－２）　@決済区分が以下の値でない場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@1：円貨<BR>
     * 　@　@　@　@　@　@　@　@　@2：外貨<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_02112<BR>
     * <BR>
     * ４）　@額面金額チェック<BR>
     * 　@４－１）　@額面金額 == nullの場合、例外をスローする。<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_02634<BR>
     * 　@４－２）　@額面金額 が整数ではない場合、例外をスローする。<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_02641<BR>
     * 　@４－３）　@額面金額 ≦ 0の場合、例外をスローする。<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_02636<BR>
     * 　@４－４）　@額面金額 ＞ 11桁の場合、例外をスローする。<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_02635<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44C857C40297
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@取引区分チェック 
        //　@１－１）　@取引区分 == nullの場合、例外をスローする。 
        if (this.tradeDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00601,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引区分が未指定です。");
        }
        
        //　@１－２）　@取引区分が以下の値でない場合、例外をスローする。 
        //　@　@　@　@　@　@　@　@1： 応募 
        //　@　@　@　@　@　@　@　@2： 買付 
        else if (!(WEB3BondDealDivDef.RECRUIT.equals(this.tradeDiv)
            || WEB3BondDealDivDef.BUY.equals(this.tradeDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00602,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引区分が存在しないコード値です。");
        }

        //２）　@銘柄IDチェック  
        //　@　@　@銘柄ID == nullの場合、例外をスローする。
        if (this.productId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄IDが未指定です。");
        }

        //３）　@決済区分チェック  
        //　@３－１）　@決済区分 == nullの場合、例外をスローする。  
        if (this.settleDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02111,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "決済区分がnullです。");
        }
        
        //　@３－２）　@決済区分が以下の値でない場合、例外をスローする。 
        //　@　@　@　@　@　@　@　@　@1：円貨 
        //　@　@　@　@　@　@　@　@　@2：外貨 
        else if (!(WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(this.settleDiv)
            || WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(this.settleDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02112,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "決済区分が未定義の値です。");
        }        

        //４）　@額面金額チェック 
        //　@４－１）　@額面金額 == nullの場合、例外をスローする。 
        if (this.faceAmount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02634,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額が未指定です。");
        }
        
        //　@４－２）　@額面金額 が整数ではない場合、例外をスローする。
        else if (!WEB3StringTypeUtility.isInteger(this.faceAmount))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02641,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額が整数値ではありません。");
        }
        
        //　@４－３）　@額面金額 ≦ 0の場合、例外をスローする。 
        else if (Double.parseDouble(this.faceAmount) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02636,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額が0以下の値です。");
        }
        
        //　@４－４）　@額面金額 ＞ 11桁の場合、例外をスローする。
        else if (WEB3StringTypeUtility.getByteLength(this.faceAmount) > 11)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02635,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額のサイズが不正です。");
        }      
                
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>  
     *<BR>  
     * @@return WEB3GenResponse  
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
