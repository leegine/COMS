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
filename	WEB3MarginCloseMarginChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文訂正_返済完了リクエストクラス(WEB3MarginCloseMarginChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/15 李松峰 (中訊) 新規作成
                   2004/12/06 岡村和明(SRA) 残案件対応 Ｎｏ.２７０
                   2004/12/21 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引注文訂正_返済完了リクエスト）。<br>
 * <br>
 * 信用取引注文訂正_返済完了リクエストクラス
 * @@version 1.0
 */
public class WEB3MarginCloseMarginChangeCompleteRequest extends WEB3MarginCommonRequest 
{
    
    /**
     * <p>（ログ出力ユーティリティ）。</p>
     */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginCloseMarginChangeCompleteRequest.class);

    /**
     * <p>（PTYPE）。</p>
     */
    public static final String PTYPE = "margin_closeMarginChangeComplete";

    /**
     * <p>（SerialVersionUID）。</p>
     */
    public static final long serialVersionUID = 200409101636L; 
                 
    /**
     * <p>（ＩＤ）。</p>
     * <p>注文ＩＤ</p>
     */
    public String id;
    
    /**
     * <p>（決済建株一覧）。</p>
     * <p>信用取引決済建株の一覧</p>
     */
    public WEB3MarginCloseMarginContractUnit[] closeMarginContractUnits;
    
    /**
     * <p>（確認時単価）。</p>
     * <p>確認時単価<br>
     * <br>
     * 確認レスポンスで送信した値。</p>
     */
    public String checkPrice;
    
    /**
     * <p>（確認時発注日）。</p>
     * <p>確認時発注日<br>
     * <br>
     * 確認レスポンスで送信した値。</p>
     */
    public Date checkDate;
    
    /**
     * <p>（暗証番号）。</p>
     * <p>暗証番号</p>
     */
    public String password;
    
    /**
     * <p>（信用取引注文訂正_返済完了リクエスト）。</p>
     * <p>コンストラクタ。</p>
     */
    public WEB3MarginCloseMarginChangeCompleteRequest() 
    {
    }
    
    /**
     * <p>（validate）。</p>
     * <p>（当リクエストデータの整合性チェックを行う。<br>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<br>
     * <br>
     * １）　@スーパークラスのvalidateメソッドを呼び出す。<br>
     * <br>
     * ２）　@IDチェック<br>
     * 　@　@　@this.ID＝nullの場合、「IDがnull」の例外をスローする。<br>
     * 　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@tag:　@ BUSINESS_ERROR_00080<br>     
     * <br>
     * ３）　@決済建株一覧チェック<br>
     * 　@３－１）this.決済建株一覧＝nullの場合、<br>
     * 　@　@　@　@　@「決済建株一覧がnull」の例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:　@ BUSINESS_ERROR_00610<br>     
     * <br>
     * 　@３－２）this.決済建株一覧が要素数＝０の場合、<br>
     * 　@　@　@　@　@「決済建株一覧.要素数が0」の例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:　@ BUSINESS_ERROR_00611<br>
     * <br>
     * ４）　@決済建株一覧要素内チェック<br>
     * 　@　@　@this.決済建株一覧の要素(決済建株明細)数分、<br>
     * 　@　@　@下記のチェックを繰り返して行う。<br>
     * 　@　@　@・決済建株明細のvalidate( )メソッドをコールする。<br>
     * <br>
     * ５）　@決済建株一覧総株数チェック<br>
     * 　@５－１）（super.注文株数 == null）、かつ<br>
     *            this.決済建株一覧の要素数内の全ての注文株数がnullまたは0の場合、<br>
     * 　@　@　@　@　@「注文株数指定なし」の例外をスローする。<br>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<br>
     * 　@　@　@　@　@tag:　@ BUSINESS_ERROR_00612<br>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        // １）　@スーパークラスのvalidateメソッドを呼び出す。
        super.validate();
        
        // ２）　@IDチェック
        // 　@　@　@this.ID＝nullの場合、「IDがnull」の例外をスローする。
        // 　@　@　@class: WEB3BusinessLayerException
        // 　@　@　@tag:　@ BUSINESS_ERROR_00080
        if (id == null)
        {
           throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,STR_METHOD_NAME);
        }
        
        // ３）　@決済建株一覧チェック
        // 　@３－１）this.決済建株一覧＝nullの場合、
        // 　@　@　@　@　@「決済建株一覧がnull」の例外をスローする。
        // 　@　@　@　@　@class: WEB3BusinessLayerException
        // 　@　@　@　@　@tag:　@ BUSINESS_ERROR_00610
        // 　@３－２）this.決済建株一覧が要素数＝０の場合、
        // 　@　@　@　@　@「決済建株一覧.要素数が0」の例外をスローする。
        // 　@　@　@　@　@class: WEB3BusinessLayerException
        // 　@　@　@　@　@tag:　@ BUSINESS_ERROR_00611
        if (closeMarginContractUnits == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00610,STR_METHOD_NAME);
        }
        if (closeMarginContractUnits.length==0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00611,STR_METHOD_NAME);
        }
        
        // ４）　@決済建株一覧要素内チェック
        // 　@　@　@this.決済建株一覧の要素(決済建株明細)数分、
        // 　@　@　@下記のチェックを繰り返して行う。
        // 　@　@　@・決済建株明細のvalidate( )メソッドをコールする。
        for (int i=0;i < closeMarginContractUnits.length;i++)
        {
            closeMarginContractUnits[i].validate();
        }
        
        // ５）　@決済建株一覧総株数チェック
        // 　@５－１）（super.注文株数 == null）、かつ
        //            this.決済建株一覧の要素数内の全ての注文株数がnullまたは0の場合、
        // 　@　@　@　@　@「注文株数指定なし」の例外をスローする。
        // 　@　@　@　@　@class: WEB3BusinessLayerException
        // 　@　@　@　@　@tag:　@ BUSINESS_ERROR_00612
        if(super.orderQuantity == null)
        {
            boolean l_orderQuantityFlg = false;
            for(int i = 0 ; i < closeMarginContractUnits.length ; i++)
            {
                if(closeMarginContractUnits[i].orderQuantity != null 
                && Long.parseLong(closeMarginContractUnits[i].orderQuantity) > 0)
                {
                    l_orderQuantityFlg = true;
                }
            }
            
            if(!l_orderQuantityFlg) {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00612,STR_METHOD_NAME);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateAT反対取引)<BR>
     * 反対取引指定時の、当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （連続注文用のメソッド）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す。<BR>
     * <BR>
     * ２）　@IDチェック<BR>
     * 　@　@this.ID＝nullの場合、「IDがnull」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@ BUSINESS_ERROR_00080<BR>
     * <BR>
     * ３）　@決済建株一覧チェック<BR>
     * 　@３－１）this.決済建株一覧＝nullの場合、<BR>
     * 　@　@　@　@　@「決済建株一覧がnull」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@ BUSINESS_ERROR_00610<BR>
     * <BR>
     * 　@３－２）this.決済建株一覧が要素数＝０の場合、<BR>
     * 　@　@　@　@　@「決済建株一覧.要素数が0」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@ BUSINESS_ERROR_00611<BR>
     * <BR>
     * ４）　@確認時単価チェック<BR>
     * 　@this.確認時単価＝nullの場合、<BR>
     * 　@「確認時単価がnull」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@ BUSINESS_ERROR_00206<BR>
     * <BR>
     * ５）　@確認時発注日チェック<BR>
     * 　@this.確認時発注日＝nullの場合、<BR>
     * 　@「確認時発注日がnull」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@ BUSINESS_ERROR_00078<BR>
     * <BR>
     * ６）　@連続注文・注文条件チェック<BR>
     * 　@スーパークラスのvalidate連続注文メソッドをコールする。<BR>
     * @@throws WEB3BaseException
     */
    public void validateAtReverseOrder() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateAtReverseOrder()";
        log.entering(STR_METHOD_NAME);
        
        super.validate();
        
        if (id == null)
        {
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00080,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (closeMarginContractUnits == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00610,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (closeMarginContractUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00611,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (checkPrice == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (checkDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        super.validateSuccOrder();
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * <p>（createレスポンス）。</p>
     * <p>信用取引注文訂正_返済完了レスポンスを生成して返す。</p>
     * @@return 信用取引注文訂正_返済完了レスポンス
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginCloseMarginChangeCompleteResponse(this);
    }    
}
@
