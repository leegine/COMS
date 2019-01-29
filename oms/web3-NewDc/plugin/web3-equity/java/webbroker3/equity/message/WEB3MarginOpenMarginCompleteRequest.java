head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引新規建注文完了リクエスト(WEB3MarginOpenMarginCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
                   2004/12/06 岡村和明(SRA) 残案件対応 Ｎｏ.１６８
                   2004/12/21 岡村和明(SRA) JavaDoc修正
                   2006/12/25 張騰宇 (中訊) モデル 1086
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;

import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引新規建注文完了リクエスト）。<br>
 * <br>
 * 信用取引新規建注文完了リクエストクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginOpenMarginCompleteRequest extends WEB3MarginCommonRequest 
{
    /**
     * <p>（ログ出力ユーティリティ）。</p>
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginOpenMarginCompleteRequest.class);
    
    /**
     * <p>（PTYPE）。</p>
     */
    public static final String PTYPE = "margin_openMarginComplete";

    /**
     * <p>（SerialVersionUID）。</p>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * <p>（注文ID）。</p>
     * <p>注文ID</p>
     */
    public String orderId;
    
    /**
     * <p>（銘柄コード）。</p>
     * <p>銘柄コード</p>
     */
    public String productCode;
    
    /**
     * <p>（市場コード）。</p>
     * <p>市場コード</p>
     */
    public String marketCode;
    
    /**
     * <p>（口座区分）。</p>
     * <p>0：一般　@1：特定</p>
     */
    public String taxType;
    
    /**
     * <p>（取引区分）。</p>
     * <p>取引区分<br>
     * <br>
     * 3：新規買建注文　@4：新規売建注文<br>
     * （OrderTypeEnumにて定義）</p>
     */
    public String tradingType;
    
    /**
     * <p>（弁済）。</p>
     * <p>弁済
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * <p>（確認時単価）。</p>
     * <p>確認時単価<br>
     * <br>
     * 確認レスポンスで送った値。</p>
     */
    public String checkPrice;
    
    /**
     * <p>（確認時発注日）。</p>
     * <p>確認時発注日<br>
     * <br>
     * 確認レスポンスで送った値。</p>
     */
    public Date checkDate;
    
    /**
     * <p>（暗証番号）。</p>
     * <p>暗証番号</p>
     */
    public String password;
    
    /**
     * <p>（信用取引新規建注文完了リクエスト）。</p>
     * <p>コンストラクタ。</p>
     */
    public WEB3MarginOpenMarginCompleteRequest() 
    {
     
    }
    
    /**
     * <p>（validate）。</p>
     * <p>１）　@スーパークラスのvalidateメソッドを呼び出す。<br>
     * <br>
     * ２）　@取引区分チェック<br>
     * 　@２－１）this.取引区分＝nullであれば「取引区分がnull」の例外をスローする。<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00601<br>
     * <br>
     * 　@２－２）this.取引区分が下記以外の値の場合、<br>
     * 　@　@　@　@　@「取引区分が未定義の値」の例外をスローする。<br>
     * 　@　@　@　@・”3：新規買建注文”<br>
     * 　@　@　@　@・”4：新規売建注文”<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00602<br>
     * <br>
     * ３）　@銘柄コードチェック<br>
     * 　@３－１）this.銘柄コード＝nullであれば「銘柄コードがnull」<br>
     *   の例外をスローする。<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00079<br>
     * ４）　@市場コードチェック<br>
     * 　@４－１）this.市場コード＝nullであれば「市場コードがnull」<br>
     *   の例外をスローする。<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00443<br>
     * <br>
     * 　@４－２)this.市場コードが下記以外の値の場合、<br>
     * 　@　@　@　@　@「市場コードが未定義の値」の例外をスローする。<br>
     *          ・”1：東京”<br>
     *          ・”2：大阪” <br>
     *          ・”3：名古屋” <br>
     *          ・”6：福岡” <br>
     *          ・”8：札幌” <br>
     *          ・”9：NNM” <br>
     *          ・”10：JASDAQ”<br>
     *          ・”99：優先市場”<BR>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00608<BR>
     * <br>
     * ５）　@弁済チェック<br>
     * 　@５－１）this.弁済＝nullであれば「弁済がnull」の例外をスローする。<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00603<br>
     * <br>
     * 　@５－２）this.弁済の信用取引弁済.validateメソッドを呼び出す。<br>
     * <br>
     * ６）　@口座区分チェック<br>
     * 　@６－１）this.口座区分＝nullであれば「口座区分がnull」の例外をスローする。<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00604<br>
     * <br>
     * 　@６－２）this.口座区分が以下の値以外の場合、<br>
     * 　@　@　@　@　@「口座区分が未定義の値」の例外をスローする。<br>
     * 　@　@　@　@・”0：一般”<br>
     * 　@　@　@　@・”1：特定”<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00605<br>
     * <br>
     * ７）　@確認時単価チェック　@（注文ID≠nullの場合のみ実施する） <br>
     * 　@７－１）this.確認時単価＝nullであれば「確認時単価がnull」の<br>
     * 　@　@　@　@例外をスローする。<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00206<br>
     * <br>
     * ８）　@確認時発注日チェック　@（注文ID≠nullの場合のみ実施する）<br>
     * 　@８－１）this.確認時発注日＝nullであれば「確認時発注日がnull」の<br>
     * 　@　@　@　@例外をスローする。<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00078<br>
     * <br>
     * ９）　@注文株数チェック<br>
     * 　@９－１）this.注文株数＝nullであれば、「注文株数が未指定」の例外をスローする。<br>
     *   class: WEB3BusinessLayerException<br>
     *   tag:    BUSINESS_ERROR_00126</p>
     * 
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "●●● WEB3MarginOpenMarginCompleteRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("スーパークラスのvalidateメソッドを呼び出す");
        // １）　@スーパークラスのvalidateメソッドを呼び出す。<br>
        super.validate();

        log.debug("取引区分チェック");
        // ２）　@取引区分チェック<br>
        // 　@２－１）this.取引区分＝nullであれば「取引区分がnull」の例外をスローする。<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00601<br>
        if (this.tradingType == null)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00601,
            this.getClass().getName() + "validate");
        }

        // 　@２－２）this.取引区分が下記以外の値の場合、<br>
        // 　@　@　@　@　@「取引区分が未定義の値」の例外をスローする。<br>
        // 　@　@　@　@・”3：新規買建注文”<br>
        // 　@　@　@　@・”4：新規売建注文”<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00602<br>
        if (!WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN.equals(this.tradingType)
                && !WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(this.tradingType))
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00602,
            this.getClass().getName() + "validate");
        }

        log.debug("銘柄コードチェック");
        // ３）　@銘柄コードチェック<br>
        // 　@３－１）this.銘柄コード＝nullであれば「銘柄コードがnull」<br>
        //   の例外をスローする。<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00079<br>
        if (this.productCode == null)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00079,
            this.getClass().getName() + "validate");
        }
        
        log.debug("市場コードチェック");
        // ４）　@市場コードチェック<br>
        // 　@４－１）this.市場コード＝nullであれば「市場コードがnull」<br>
        //   の例外をスローする。<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00443<br>
        if (this.marketCode == null)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00443,
            this.getClass().getName() + "validate");
        }

        // 　@４－２)this.市場コードが下記以外の値の場合、<br>
        // 　@　@　@　@　@「市場コードが未定義の値」の例外をスローする。<br>
        //          ・”1：東京”<br>
        //          ・”2：大阪” <br>
        //          ・”3：名古屋” <br>
        //          ・”6：福岡” <br>
        //          ・”8：札幌” <br>
        //          ・”9：NNM” <br>
        //          ・”10：JASDAQ”<br>
        //          ・”99：優先市場”<BR>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00608<br>
        if (!WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                && !WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                && !WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                && !WEB3MarketCodeDef.NNM.equals(this.marketCode)
                && !WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)
                && !WEB3MarketCodeDef.PRIORITY_MARKET.equals(this.marketCode))
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00608,
            this.getClass().getName() + "validate");
        }

        log.debug("弁済チェック");
        // ５）　@弁済チェック<br>
        // 　@５－１）this.弁済＝nullであれば「弁済がnull」の例外をスローする。<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00603<br>
        if (this.repayment == null)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00603,
            this.getClass().getName() + "validate");
        }

        // 　@５－２）this.弁済の信用取引弁済.validateメソッドを呼び出す。<br>
        this.repayment.validate();

        log.debug("口座区分チェック");
        // ６）　@口座区分チェック<br>
        // 　@６－１）this.口座区分＝nullであれば「口座区分がnull」の例外をスローする。<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00604<br>
        if (this.taxType == null)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00604,
            this.getClass().getName() + "validate");
        }
        
        // 　@６－２）this.口座区分が以下の値以外の場合、<br>
        // 　@　@　@　@　@「口座区分が未定義の値」の例外をスローする。<br>
        // 　@　@　@　@・”0：一般”<br>
        // 　@　@　@　@・”1：特定”<br>
        //   class: WEB3BusinessLayerException<br>
        //   tag:    BUSINESS_ERROR_00605<br>
        if (!WEB3TaxTypeSpecialDef.NORMAL.equals(this.taxType)
                && !WEB3TaxTypeSpecialDef.SPECIAL.equals(this.taxType))
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00605,
            this.getClass().getName() + "validate");
        }

        if (this.orderId != null)
        {
            log.debug("確認時単価チェック");
            // ７）　@確認時単価チェック<br>
            // 　@７－１）this.確認時単価＝nullであれば「確認時単価がnull」の<br>
            // 　@　@　@　@例外をスローする。<br>
            //   class: WEB3BusinessLayerException<br>
            //   tag:    BUSINESS_ERROR_00206<br>
            if (this.checkPrice == null)
            {
                //例外
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00206,
                this.getClass().getName() + "validate");
            }
    
            log.debug("確認時発注日チェック");
            // ８）　@確認時発注日チェック<br>
            // 　@８－１）this.確認時発注日＝nullであれば「確認時発注日がnull」の<br>
            // 　@　@　@　@例外をスローする。<br>
            //   class: WEB3BusinessLayerException<br>
            //   tag:    BUSINESS_ERROR_00078<br>
            if (this.checkDate == null)
            {
                //例外
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "validate");
            }
        }
        
		log.debug("注文株数チェックチェック");
		// ９）　@注文株数チェック<br>
		// 　@９－１）this.注文株数＝nullであれば、「注文株数が未指定」の例外をスローする。<br>
		//   class: WEB3BusinessLayerException<br>
		//   tag:    BUSINESS_ERROR_00126</p>
		if (this.orderQuantity == null)
		{
			//例外
			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00126,
			this.getClass().getName() + "validate");
		}
		
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * <p>（createレスポンス）。</p>
     * <p>信用取引新規建注文完了レスポンスを生成して返す。</p>
     * @@return 信用取引新規建注文完了レスポンス
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginOpenMarginCompleteResponse(this);
    }    
}
@
