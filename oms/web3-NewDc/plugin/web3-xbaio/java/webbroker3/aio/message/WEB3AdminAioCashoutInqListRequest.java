head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込問合せ一覧リクエストクラス(WEB3AdminAioCashoutInqListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 韋念瓊 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
                   2004/12/10 周勇 (中訊) 残対応
                   2006/07/31 徐大方 (中訊) 式樣變更 モデル604
                   2006/09/04 車進 (中訊) 式樣變更 モデルNo.629                 
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3AioInputDivDef;
import webbroker3.aio.define.WEB3AioOrderAcceptedDivDef;
import webbroker3.aio.define.WEB3AioTransferDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (出金申込問合せ一覧リクエスト)<BR>
 * 出金申込問合せ一覧リクエストクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqListRequest extends WEB3GenRequest 
{
    /**
     * ログユーティリティ<BR>
     */
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashoutInqListRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_list";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410121248L;      
    /**
     * (部店コード)<BR>
     * (画面にて入力された部店コード)
     */
    public String[] branchCode;
    
    /**
     * (受渡日)<BR>
     * 画面にて入力された受渡日
     */
    public Date deliveryDate;
    
    /**
     * (注文受付区分)<BR>
     * 画面にて選択された注文受付区分<BR>
     * <BR>
     * 0： 受付未済<BR>
     * 1： 受付済<BR>
     * 2： 受付エラー<BR>
     * 3： 全て<BR>
     * <BR>
     */
    public String orderDiv;
    
    //===========remain zhou-yong NO.1 begin ========
    
    /**
     * (振込先区分)<BR>
     * 画面にて選択された振込先区分<BR>
     * <BR>
     * 0： ”全て”<BR> 
     * 1： ”郵貯”<BR> 
     * 2： ”その他”（郵貯以外） 
     */
    public String transferDiv;
    
    //===========remain zhou-yong NO.1 end ========   
    
    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数 
     */
    public String pageSize;   
    
    /**
     * (入力区分)<BR>
     * <BR>
     * 0：全て<BR>
     * 1：顧客<BR>
     * 2：SONAR<BR>
     */
    public String inputDiv;   
    
    /**
     * (通貨コード)<BR>
     * 通貨コード
     */
    public String currencyCode;
    
    /**
	 * リクエストデータの整合性をチェックする。<BR>
	 * <BR>
	 * １）部店コードチェック <BR>
	 * <BR>
	 * １－１）リクエストデータ.部店コードの要素数 = 0 or <BR>
	 * リクエストデータ.部店コード = nullの場合、例外をスローする。<BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00779<BR>
	 * <BR>
	 * １－２）配列の各要素について <BR>
	 * １－２－１）各要素.length() != 3 の場合、例外をスローする。<BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00834 <BR>
	 * <BR>
	 * １－２－２）各要素に数字以外の文字があるの場合、例外をスローする。<BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00834 <BR>
	 * <BR>
	 * ２）注文受付区分チェック<BR>
	 * リクエストデータ.注文受付区分 != 0（受付未済） and<BR>
	 * リクエストデータ.注文受付区分 != 1（受付済） and<BR>
	 * リクエストデータ.注文受付区分 != 2（受付エラー） and<BR>
	 * リクエストデータ.注文受付区分 != 3（全て） の場合、例外をスローする。<BR>
	 * <BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00790<BR>
	 * <BR>
	 * <BR>
	 * ３）振込先区分チェック <BR>
	 * <BR>
	 * リクエストデータ.振込先区分 != (0, 1, 2) <BR>
	 * の場合、例外をスローする。 <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_01363<BR>
	 * <BR>
	 * <BR>
	 * ４）要求ページ番号チェック<BR>
	 * ４－１）<BR>
	 * リクエストデータ.要求ページ番号 = null or<BR>
	 * リクエストデータ.要求ページ番号 <= 0 の場合、例外をスローする。<BR>
	 * <BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00786<BR>
	 * <BR>
	 * <BR>
	 * ４－２）<BR>
	 * リクエストデータ.要求ページ番号に数字以外の文字がある の場合、例外をスローする。<BR>
	 * <BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00090<BR>
	 * <BR>
	 * <BR>
	 * ５）ページ内表示行数チェック<BR>
	 * ５－１）<BR>
	 * リクエストデータ.ページ内表示行数 = null or<BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00091<BR>
	 * リクエストデータ.ページ内表示行数 <= 0 の場合、例外をスローする。<BR>
	 * <BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00617<BR>
	 * <BR>
	 * <BR>
	 * ５－２）<BR>
	 * リクエストデータ.ページ内表示行数に数字以外の文字があるの場合、例外をスローする。<BR>
	 * <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_00092<BR>
	 * ６）入力区分チェック<BR>
	 * <BR>
	 * リクエストデータ.入力区分 != 0（全て） and <BR>
	 * リクエストデータ.入力区分 != 1（顧客） and <BR>
	 * リクエストデータ.入力区分 != 2（SONAR）<BR>
	 * <BR>
	 * の場合、例外をスローする。 <BR>
	 * class: WEB3BusinessLayerException<BR>
	 * tag: BUSINESS_ERROR_02522<BR>
	 * <BR>
     * @@throws WEB3BaseException 
	 * @@roseuid 40E53E8A0254
	 */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コードチェック 

        //１－１）リクエストデータ.部店コードの要素数 = 0 or 
        //       リクエストデータ.部店コード = null の場合、例外をスローする。         
       
        if (this.branchCode.length == 0 ||
            this.branchCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.部店コードの要素数 = 0 or " +
                "リクエストデータ.部店コード = null");
        }      
        
        //１－２）配列の各要素について 

        //１－２－１）各要素.length() != 3 の場合、例外をスローする。 
        for (int i = 0; i < this.branchCode.length; i++)
        {
            if (this.branchCode[i].length() != 3)
            {                
                log.exiting(STR_METHOD_NAME);                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834 ,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードのサイズが不正です。");
            }
        }

        //１－２－２）各要素に数字以外の文字があるの場合、例外をスローする。 
        for (int i = 0; i < this.branchCode.length; i++)
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                log.exiting(STR_METHOD_NAME);                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードが数値以外の値です。");
            }
        }
        
        //２）注文受付区分チェック 

        //リクエストデータ.注文受付区分 != 0（受付未済） and 
        //リクエストデータ.注文受付区分 != 1（受付済） and 
        //リクエストデータ.注文受付区分 != 2（受付エラー） and 
        //リクエストデータ.注文受付区分 != 3（全て）         
        
        if (!WEB3AioOrderAcceptedDivDef.NOT_ACCEPTED.equals(this.orderDiv) &&
            !WEB3AioOrderAcceptedDivDef.ACCEPTED.equals(this.orderDiv) &&
            !WEB3AioOrderAcceptedDivDef.ACCEPT_ERROR.equals(this.orderDiv) &&
            !WEB3AioOrderAcceptedDivDef.ALL.equals(this.orderDiv))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00790,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.注文受付区分 != ( 0（受付未済）, 1（受付済）,2（受付エラー）,3（全て）)," +
                "リクエストデータ.注文受付区分 = " + this.orderDiv);
        }
        
        //=========remain zhou-yong NO.2 begin =============
        
        //３）振込先区分チェック
        //リクエストデータ.振込先区分 != (0, 1, 2)
        //の場合、例外をスローする。 
        if(!(WEB3AioTransferDivDef.ALL.equals(this.transferDiv) || 
            WEB3AioTransferDivDef.POSTAL_SAVINGS.equals(this.transferDiv) || 
            WEB3AioTransferDivDef.OTHERS.equals(this.transferDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.振込先区分 != (0, 1, 2), " +
                "リクエストデータ.振込先区分 = " + this.transferDiv);
            
        }
        
        //=========remain zhou-yong NO.2 end =============
        
        //４－１） 
        //リクエストデータ.要求ページ番号 = null 
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00786,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.要求ページ番号 = null");
        }

        //４－２）
        //リクエストデータ.要求ページ番号に数字以外の文字がある 
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.要求ページ番号に数字以外の文字がある, " +
                "リクエストデータ.要求ページ番号 = " + this.pageIndex);
        }
        
        //リクエストデータ.要求ページ番号 <= 0 
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00786,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.要求ページ番号 <= 0");
        }
        
        //５）ページ内表示行数チェック 

        //５－１）
        //リクエストデータ.ページ内表示行数 = null
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.ページ内表示行数 = null");
        }
        
        //５－２） 
        //リクエストデータ.ページ内表示行数に数字以外の文字がある 
        
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.ページ内表示行数に数字以外の文字がある," +
                "リクエストデータ.ページ内表示行数 = " + this.pageSize);
        }
        
        //リクエストデータ.ページ内表示行数 <= 0 
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.ページ内表示行数 <= 0," +
                "リクエストデータ.ページ内表示行数 = " + this.pageSize);
        }
        
        //６）入力区分チェック 
        //リクエストデータ.入力区分 != 0（全て） and 
        //リクエストデータ.入力区分 != 1（顧客） and 
        //リクエストデータ.入力区分 != 2（SONAR） 
        if (!WEB3AioInputDivDef.ALL.equals(this.inputDiv) &&
            !WEB3AioInputDivDef.CUSTOMER.equals(this.inputDiv) &&
            !WEB3AioInputDivDef.SONAR.equals(this.inputDiv))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02522,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入力区分が存在しないコード値です。 " +
                "リクエストデータ.入力区分 = " + this.inputDiv);
        }
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB660189
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashoutInqListResponse(this);
    }
}
@
