head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資売付注文完了リクエストクラス(WEB3MstkSellCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李海波 (中訊) 新規作成
                   2004/12/13 岡村和明(SRA) 残案件対応 Ｎｏ.４０７
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （株式ミニ投資売付注文完了リクエスト）。<BR>
 * <BR>
 * 株式ミニ投資売付注文完了リクエストクラス
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3MstkSellCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkSellCompleteRequest.class);
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mstk_sellComplete";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200410121732L;    
    /**
     * (銘柄コード)
     */
    public String productCode;
    
    /**
     * (注文株数)
     */
    public String orderQuantity;
    
    /**
     * (暗証番号)
     */
    public String password;
    
    /**
     * (確認時単価)
     */
    public String checkPrice;
    
    /**
     * (確認時発注日)<BR>
     * ※注文執行日
     */
    public Date checkDate;
    
    /**
     * (注文ID)
     */
    public String orderId;
    
    /**
     * @@roseuid 4167B050000F
     */
    public WEB3MstkSellCompleteRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@　@銘柄コードチェック<BR>
     * 　@　@　@　@this.銘柄コードが、以下のいずれかに該当する場合は、<BR>
     * 　@　@　@　@以下の例外をスローする。<BR>
     * <BR>
     * 　@　@　@・this.銘柄コード＝null(「銘柄コードがnull」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * ２）　@　@注文株数チェック<BR>
     * 　@　@　@　@this.注文株数が以下のいずれかに該当する場合は、<BR>
     * 　@　@　@　@以下の例外をスローする。<BR>
     * <BR>
     * 　@　@　@・this.注文株数＝null(「注文株数がnull」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * 　@　@　@・this.注文株数≠数字(「注文株数が数字以外」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00901<BR>
     * <BR>
     * 　@　@　@・this.注文株数≦０(「注文株数が0以下」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00902<BR>
     * <BR>
     * 　@　@　@・this.注文株数＞8byte(「注文株数が8byte以上」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00903<BR>
     * <BR>
     * ３）　@確認時単価チェック<BR>
     * 　@　@　@this.確認時単価＝nullであった場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00206<BR>
     * <BR>
     * ４）　@確認時発注日チェック<BR>
     * 　@　@　@this.確認時発注＝nullであった場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * ５）　@注文IDチェック<BR>
     * 　@　@　@this.注文ID＝nullであった場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * @@roseuid 4111BE7C01A5
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@　@銘柄コードチェック
        log.debug("銘柄コードチェック");
        if(productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079, 
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        //２）　@　@注文株数チェック
        log.debug("注文株数チェック");
        if(orderQuantity == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00126, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if(!WEB3StringTypeUtility.isNumber(orderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00901, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        double l_dblOrderQuantity = Double.parseDouble(orderQuantity);
        if(l_dblOrderQuantity <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00902, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if(WEB3StringTypeUtility.getNubmerLength(orderQuantity) > 8)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00903, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //３）　@確認時単価チェック
        log.debug("確認時単価チェック");
        if(checkPrice == null)
        {
            log.error("エラー：確認時単価がnullする！");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206, 
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        //４）　@確認時発注日チェック
        log.debug("確認時発注日チェック");
        if(checkDate == null)
        {
            log.error("エラー：確認時発注日がnullする！");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //５）　@注文IDチェック
        log.debug("注文IDチェック");
        if(orderId == null)
        {
            log.error("エラー：注文IDがnullする！");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4167B050002D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkSellCompleteResponse(this);
    }
}
@
