head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.39.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付入力リクエスト(WEB3BondApplyBuyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券応募/買付入力リクエスト)<BR>
 * 債券応募/買付入力リクエスト<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondApplyBuyInputRequest extends WEB3GenRequest 
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondApplyBuyInputRequest.class);
    
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_applyBuyInput";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L; 
    
    /**
     * (取引区分)<BR>
     * 取引区分<BR>
     * <BR>
     * 1：応募<BR>
     * 2：買付<BR>
     */
    public String tradeDiv;
    
    /**
     * (銘柄ID)<BR>
     * 銘柄ID<BR>
     */
    public String productId;
    
    /**
     * @@roseuid 44FBFD3900CB
     */
    public WEB3BondApplyBuyInputRequest() 
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
     * ２）　@銘柄IDチェック<BR> 
     * 　@　@　@銘柄ID == nullの場合、例外をスローする。<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_02229 <BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44BF46E8031C
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
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>  
     *<BR>  
     * @@return WEB3GenResponse  
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3BondApplyBuyInputResponse(this);
    }
}
@
