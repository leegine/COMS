head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.56.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyProductListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付銘柄一覧リクエスト(WEB3BondApplyBuyProductListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.define.WEB3BondReferenceTypeDef;

/**
 * (債券応募/買付銘柄一覧リクエスト)<BR>
 * 債券応募/買付銘柄一覧リクエスト<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondApplyBuyProductListRequest extends WEB3GenRequest 
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondApplyBuyProductListRequest.class);
    
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_applyBuyProductList";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L;        
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;
    
    /**
     * (照会区分)<BR>
     * 照会区分<BR>
     * <BR>
     * 1：応募一覧<BR>
     * 2：買付一覧<BR>
     * 3：応募/買付一覧<BR>
     */
    public String referenceType;
    
    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;
    
    /**
     * @@roseuid 44FBFD390203
     */
    public WEB3BondApplyBuyProductListRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性をチェックする。<BR> 
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR> 
     * <BR>
     * １）　@照会区分チェック<BR>
     * 　@１－１）　@照会区分==nullの場合、例外をスローする。<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_00081<BR>
     * 　@１－２）　@照会区分が以下の値でない場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@1： 応募一覧<BR>
     * 　@　@　@　@　@　@　@　@2： 買付一覧<BR>
     * 　@　@　@　@　@　@　@　@3： 応募/買付一覧<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_00082<BR>
     * <BR>
     * ２）　@要求ページ番号チェック<BR> 
     * 　@２－１）　@要求ページ番号==nullの場合、例外をスローする。<BR> 
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_00089<BR>
     * 　@２－２）　@要求ページ番号が数字以外の場合、例外をスローする。<BR> 
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_00090<BR>
     * <BR>
     * ３）　@ページ内表示行数チェック<BR> 
     * 　@３－１）　@ページ内表示行数==nullの場合、例外をスローする。<BR> 
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_02224<BR>
     * 　@３－２）　@ページ内表示行数が数字以外の場合、例外をスローする。<BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_00092<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44B6E1B3029F
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@照会区分チェック 
        //　@１－１）　@照会区分==nullの場合、例外をスローする。 
        if (this.referenceType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "照会区分が未指定です。");
        }
        
        //　@１－２）　@照会区分が以下の値でない場合、例外をスローする。 
        //　@　@　@　@　@　@　@　@1： 応募一覧 
        //　@　@　@　@　@　@　@　@2： 買付一覧 
        //　@　@　@　@　@　@　@　@3： 応募/買付一覧 
        else if (!(WEB3BondReferenceTypeDef.RECRUIT_LIST.equals(this.referenceType)
            || WEB3BondReferenceTypeDef.BUY_LIST.equals(this.referenceType)
            || WEB3BondReferenceTypeDef.RECRUIT_BUY_LIST.equals(this.referenceType)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "照会区分の値が存在しないコード値です。");
        }
        
        //２）　@要求ページ番号チェック  
        //　@２－１）　@要求ページ番号==nullの場合、例外をスローする。
        if (this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        
        //　@２－２）　@要求ページ番号が数字以外の場合、例外をスローする。
        else if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        
        //３）　@ページ内表示行数チェック  
        //　@３－１）　@ページ内表示行数==nullの場合、例外をスローする。
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }
        
        //　@３－２）　@ページ内表示行数が数字以外の場合、例外をスローする。
        else if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
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
        return new WEB3BondApplyBuyProductListResponse(this);
    }
}
@
