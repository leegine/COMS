head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.47.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券売却確認リクエスト(WEB3BondSellConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (債券売却確認リクエスト)<BR>
 * 債券売却確認リクエスト<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondSellConfirmRequest extends WEB3GenRequest 
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondSellConfirmRequest.class);
    
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_sellConfirm";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L;   
    
    /**
     * 保有資産ID<BR>
     */
    public String id;
    
    /**
     * (額面金額)<BR>
     * 額面金額<BR>
     */
    public String faceAmount;
    
    /**
     * (決済区分)<BR>
     * 決済区分<BR>
     * <BR>
     * 1：円貨<BR>
     * 2：外貨<BR>
     */
    public String settleDiv;
    
    /**
     * @@roseuid 44FBFD3902AF
     */
    public WEB3BondSellConfirmRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR> 
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR> 
     * <BR>
     * １）　@ＩＤチェック<BR> 
     * 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_01919<BR> 
     * <BR>
     * ２）　@額面金額チェック<BR>
     * 　@２－１）　@this.額面金額が整数ではない場合、例外をスローする。<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_02641<BR>
     * 　@２－２）　@this.額面金額が０以下の場合、例外をスローする。<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_02636<BR>
     * <BR>
     * ３）　@決済区分チェック<BR>
     * 　@this.決済区分が以下定義値以外の場合、例外をスローする。<BR>
     * 　@　@1：円貨<BR>
     * 　@　@2：外貨<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_02112<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D7FC3801CD
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ＩＤチェック  
        //　@this.ＩＤがnullの値であれば例外をスローする。  
        if (this.id == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01919,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有資産IDが未指定(null)です。");            
        }
        
        //２）　@額面金額チェック 
        //　@２－１）　@this.額面金額が整数ではない場合、例外をスローする。 
        if (!WEB3StringTypeUtility.isInteger(this.faceAmount))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02641,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額が整数値ではありません。"); 
        }
        
        //　@２－２）　@this.額面金額が０以下の場合、例外をスローする。 
        else if (Double.parseDouble(this.faceAmount) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02636,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額が0以下の値です。");
        }

        //３）　@決済区分チェック 
        //　@this.決済区分が以下定義値以外の場合、例外をスローする。 
        //　@　@1：円貨 
        //　@　@2：外貨
        if (!(WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(this.settleDiv)
            || WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(this.settleDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02112,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "決済区分が未定義の値です。");
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
        return new WEB3BondSellConfirmResponse(this);
    }
}
@
