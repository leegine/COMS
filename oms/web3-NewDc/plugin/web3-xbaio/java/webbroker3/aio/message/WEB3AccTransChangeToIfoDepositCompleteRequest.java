head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeToIfoDepositCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金への振替完了リクエストクラス(WEB3AccTransChangeToIfoDepositCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 于美麗 (中訊) 新規作成
                   2004/10/22 屈陽 (中訊) レビュー
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (証拠金への振替完了リクエスト)<BR>
 * 証拠金への振替完了リクエストクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeToIfoDepositCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeToIfoDepositCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "acc_trans_change_to_ifo_deposit_complete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;    
    
    /**
     * (振替金額)<BR>
     * 画面から入力された振替金額
     */
    public String changeAmt;
    
    /**
     * (暗証番号)<BR>
     * 画面から入力された暗証番号
     */
    public String password;
    
    /**
     * (確認時発注日)
     */
    public Date checkDate;
    
    /**
     * (注文ID)<BR>
     * 確認処理で取得した注文ID
     */
    public String orderId;
    
    /**
     * @@roseuid 4158E8E40103
     */
    public WEB3AccTransChangeToIfoDepositCompleteRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * １）振替金額チェック<BR>
     * １－１）   リクエストデータ.振替金額 = null   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00759<BR>
     * <BR>
     * <BR>
     * １－２）   リクエストデータ.振替金額 <= 0   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00809<BR>
     * <BR>
     * <BR>
     * １－３）   リクエストデータ.振替金額length() > 9   の場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00810<BR>
     * <BR>
     * <BR>
     * １－４）   リクエストデータ.振替金額に数字以外の文字が含まれる   <BR>
     * の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00811<BR>
     * <BR>
     * <BR>
     * ２）注文IDチェック <BR>
     * リクエストデータ.注文ID = null   <BR>
     * の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * <BR>
     * ３）確認時発注日チェック <BR>
     * リクエストデータ.確認時発注日 = null   <BR>
     * の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * @@throws WEB3BaseException 
     * @@roseuid 41342E210321
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
       
        // １）振替金額チェック 
        // １－１）リクエストデータ.振替金額 = null   の場合、例外をスローする
        if(WEB3StringTypeUtility.isEmpty(this.changeAmt))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00759,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.振替金額 = null");
        }

        // １－３）リクエストデータ.振替金額length() > 9の場合、例外をスローする
        if (this.changeAmt.length() > 9)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00810,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.振替金額length() > 9, " +
                "リクエストデータ.振替金額length() = " + this.changeAmt.length());
        }

        // １－４）リクエストデータ.振替金額に数字以外の文字が含まれるの場合、例外をスローする
        if(!WEB3StringTypeUtility.isNumber(this.changeAmt))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00811,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.振替金額に数字以外の文字, " +
                "リクエストデータ.振替金額 = " + this.changeAmt);
        }
        
        // １－２）リクエストデータ.振替金額 <= 0   の場合、例外をスローする
        if (Double.parseDouble(this.changeAmt) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00809,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.振替金額 <= 0," +
                "リクエストデータ.振替金額 = " + this.changeAmt);
        }

        // ２）注文IDチェック
        //    リクエストデータ.注文ID = null   の場合、例外をスローする
        if(WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.注文ID = null");
        }

        // ３）確認時発注日チェック
        //    リクエストデータ.確認時発注日 = null   の場合、例外をスローする
        if(this.checkDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.確認時発注日 = null");
        }   
        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * （createResponseの実装） <BR>
     * <BR>
     * 証拠金への振替完了レスポンスオブジェクトを生成して返す。 <BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158E8E40121
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AccTransChangeToIfoDepositCompleteResponse(this);
    }
}
@
