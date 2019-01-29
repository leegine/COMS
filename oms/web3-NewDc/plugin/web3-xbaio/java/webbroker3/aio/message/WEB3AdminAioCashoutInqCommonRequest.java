head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込問合せ共通リクエストクラス(WEB3AdminAioCashoutInqCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 韋念瓊 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
                   2004/12/10 周勇 (中訊) 残対応
*/

package webbroker3.aio.message;

import java.util.Date;
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
 * (出金申込問合せ共通リクエスト)<BR>
 * 出金申込問合せ共通リクエストクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqCommonRequest extends WEB3GenRequest 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashoutInqCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_common";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410131341L;
        
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
     * (指示リスト)<BR>
     * 指示を行う注文IDのリスト
     */
    public String[] directionsList;
    
    /**
     * @@roseuid 4158EB640294
     */
    public WEB3AdminAioCashoutInqCommonRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）部店コードチェック <BR>
     * <BR>
     * １－１）リクエストデータ.部店コードの要素数 = 0 or <BR>
     *        リクエストデータ.部店コード = nullの場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * １－２）配列の各要素について <BR>
     * １－２－１）各要素.length() != 3 の場合、例外をスローする。<BR> 
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834 <BR>
     * <BR>
     * １－２－２）各要素に数字以外の文字があるの場合、例外をスローする。<BR> 
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01729 <BR>
     * <BR>
     * ２）注文受付区分チェック<BR>
     *   リクエストデータ.注文受付区分 != 0（受付未済） and<BR>
     *   リクエストデータ.注文受付区分 != 1（受付済） and<BR>
     *   リクエストデータ.注文受付区分 != 2（受付エラー） and<BR>
     *   リクエストデータ.注文受付区分 != 3（全て）  の場合、例外をスローする。<BR><BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00790<BR>
     * <BR>
     * <BR>
     * ３）振込先区分チェック <BR>
     * リクエストデータ.振込先区分 != (0, 1, 2)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01363<BR>
     * <BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * <BR>
     * ４）指示リストチェック<BR>
     *   配列の要素数が0   の場合、例外をスローする。<BR><BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00793<BR>
     * <BR>
     * @@roseuid 4129B310019D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コードチェック 

        //１－１）リクエストデータ.部店コード = null の場合、例外をスローする。
        if (this.branchCode == null)
        {
            log.exiting(STR_METHOD_NAME);   
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.部店コード = null");
        }      
        
        //リクエストデータ.部店コードの要素数 = 0 
        if (this.branchCode.length == 0)
            {
                log.exiting(STR_METHOD_NAME);
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "リクエストデータ.部店コードの要素数 = 0");
            }      
        
        //１－２）配列の各要素について 

        //１－２－１）各要素.length() != 3 の場合、例外をスローする。 
        for (int i = 0; i < this.branchCode.length; i++)
        {
            if (this.branchCode[i].length() != 3)
            {           
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834, 
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
                "リクエストデータ.注文受付区分 != (0（受付未済）, 1（受付済）, " +
                "2（受付エラー）, 3（全て）), " +
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
        
        //４）指示リストチェック 
        //配列の要素数が0 
        if (directionsList == null || directionsList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00793,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "配列の要素数が0");
        }

    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return  null;        
    }
}
@
