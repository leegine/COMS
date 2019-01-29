head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBuyCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資買付注文完了リクエスト(WEB3MstkBuyCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 劉江涛 (中訊) 新規作成
                   2004/12/13 岡村和明(SRA) 残案件対応 Ｎｏ.４０７
                   2005/01/05 岡村和明(SRA) JavaDoc修正
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
 * （株式ミニ投資買付注文完了リクエスト）。<BR>
 * <BR>
 * 株式ミニ投資買付注文完了リクエストクラス
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3MstkBuyCompleteRequest extends WEB3GenRequest 
{
    /**         
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBuyCompleteRequest.class);

    /**
     * （PTYPE）。
     */
    public static final String PTYPE = "mstk_buyComplete";

    /**
     * （SerialVersionUID）。
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * （銘柄コード）。<BR>
     * 株式銘柄コード
     */
    public String productCode;
    
    /**
     * （注文株数）。
     */
    public String orderQuantity;
    
    /**
     * （暗証番号）。
     */
    public String password;
    
    /**
     * （確認時単価）。
     */
    public String checkPrice;
    
    /**
     * （確認時発注日）。<BR>
     * ※注文執行日
     */
    public Date checkDate;
    
    /**
     * （注文ID）。
     */
    public String orderId;
    
    /**
     * （株式ミニ投資買付注文完了リクエスト）。<BR>
     * <BR>
     * コンストラクタ
     */
    public WEB3MstkBuyCompleteRequest() 
    {
     
    }
    
    /**
     * （validate）。<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@　@銘柄コードチェック<BR>
     * 　@　@　@　@this.銘柄コードが、以下のいずれかに該当する場合は、<BR>
     * 　@　@　@　@以下の例外をスローする。<BR>
     * <BR>
     * 　@　@　@・this.銘柄コード＝null　@　@　@　@　@(「銘柄コードがnull」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * ２）　@　@注文株数チェック<BR>
     * 　@　@　@　@this.注文株数が以下のいずれかに該当する場合は、<BR>
     * 　@　@　@　@以下の例外をスローする。<BR>
     * <BR>
     * 　@　@　@・this.注文株数＝null　@　@　@　@　@(「注文株数がnull」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * 　@　@　@・this.注文株数≠数字　@　@　@　@<BR>
     * (「注文株数が数字以外」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00901<BR>
     * <BR>
     * 　@　@　@・this.注文株数≦０　@　@ 　@　@　@ (「注文株数が0以下」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00902<BR>
     * <BR>
     * 　@　@　@・this.注文株数＞8byte　@　@　@ <BR>
     * (「注文株数が8byte以上」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00903<BR>
     * <BR>
     * ３）　@確認時単価チェック<BR>
     * 　@　@　@　@this.確認時単価＝nullであれば例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00206<BR>
     * <BR>
     * ４）　@確認時発注日チェック<BR>
     * 　@　@　@　@this.確認時発注日＝nullであれば例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * ５）　@注文IDチェック<BR>
     * 　@　@　@　@this.注文ID＝nullであれば例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * @@roseuid 4111BAA301C5
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        

        if(this.productCode == null)
        {
            log.error("「銘柄コードがnull」の例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00079, getClass().getName() + "validate");
        }

        if(this.orderQuantity == null)
        {
            log.error("「注文株数がnull」の例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00126, getClass().getName() + "validate");
        }
        if(!WEB3StringTypeUtility.isNumber(this.orderQuantity))
        {
            log.error("「注文株数が数字以外」の例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00901, getClass().getName() + "validate");
        }
        if(Double.parseDouble(this.orderQuantity) <= 0)
        {
            log.error("「注文株数が0以下」の例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00902, getClass().getName() + "validate");
        }
        if(WEB3StringTypeUtility.getByteLength(this.orderQuantity) > 8 )
        {
            log.error("「注文株数が8byte以上」の例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00903, getClass().getName() + "validate");
        }


        if(this.checkPrice == null)
        {
            log.error("this.確認時単価＝nullであれば例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00206, getClass().getName() + "validate");
        }

        if(this.checkDate == null)
        {
            log.error("this.確認時発注日＝nullであれば例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00078, getClass().getName() + "validate");
        }

        if(this.orderId == null)
        {
            log.error("this.注文ID＝nullであれば例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, getClass().getName() + "validate");
        }

        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createレスポンス）。<BR>
     * <BR>
     * @@return WEB3GenResponse 株式ミニ投資買付注文完了レスポンス
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkBuyCompleteResponse(this);
    }
}
@
