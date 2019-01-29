head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込問合せダウンロードリクエスト(WEB3AdminAioCashoutInqDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/31 徐大方 (中訊) 新規作成
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3AioInputDivDef;
import webbroker3.aio.define.WEB3AioOrderAcceptedDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (出金申込問合せダウンロードリクエスト)<BR>
 * 出金申込問合せダウンロードリクエストクラス
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public class WEB3AdminAioCashoutInqDownloadRequest extends WEB3GenRequest
{
    /**
     * ログユーティリティ<BR>
     */ 
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashoutInqDownloadRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_download";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200607311248L;   
    
    /**
     * (部店コード)<BR>
     * 画面にて入力された部店コード
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
     */
    public String orderDiv;
    
    /**
     * (入力区分)<BR>
     * 画面にて選択された入力区分<BR>
     * <BR>
     * 0：全て<BR>
     * 1：顧客<BR>
     * 2：SONAR<BR>
     */
    public String inputDiv;   
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）部店コードチェック<BR>
     * <BR>
     * １－１）<BR>
     * <BR>
     * リクエストデータ.部店コードの要素数 = 0 or <BR>
     * リクエストデータ.部店コード = null<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00833<BR>
     * <BR>   
     * １－２）配列の各要素について<BR>
     * <BR>
     * １－２－１）<BR>
     * <BR>
     * 各要素.length() != 3<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * １－２－２）<BR>
     * <BR>
     * 各要素に数字以外の文字がある<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01729<BR>
     * <BR>
     * ２）注文受付区分チェック<BR>
     * <BR>
     * リクエストデータ.注文受付区分 != 0（受付未済） and <BR>
     * リクエストデータ.注文受付区分 != 1（受付済） and<BR>
     * リクエストデータ.注文受付区分 != 2（受付エラー） and <BR>
     * リクエストデータ.注文受付区分 != 3（全て）<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00790<BR>
     * <BR>
     * ３）入力区分チェック<BR>
     * <BR>
     * リクエストデータ.入力区分 != 0（全て） and <BR>
     * リクエストデータ.入力区分 != 1（顧客） and <BR>
     * リクエストデータ.入力区分 != 2（SONAR）<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02522<BR>
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
        if (this.branchCode == null ||
            this.branchCode.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }      

        //１－２）配列の各要素について 
        for (int i = 0; i < this.branchCode.length; i++)
        {
            //１－２－１）各要素.length() != 3 の場合、例外をスローする。 
            if (this.branchCode[i].length() != 3)
            {                
                log.exiting(STR_METHOD_NAME);                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834 ,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードのサイズが不正です。");
            }
            
            //１－２－２）各要素に数字以外の文字があるの場合、例外をスローする。 
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
                "注文受付区分が存在しないコード値です。 " +
                "リクエストデータ.注文受付区分 = " + this.orderDiv);
        }
        
        //3）入力区分チェック 
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
        return new WEB3AdminAioCashoutInqDownloadResponse(this);
    }
}
@
