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
filename	WEB3MarginSwapMarginCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡注文完了リクエスト(WEB3MarginSwapMarginCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3TaxTypeDef;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引現引現渡注文完了リクエスト）。<br>
 * <br>
 * 信用取引現引現渡注文完了リクエストクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSwapMarginCompleteRequest extends WEB3GenRequest 
{
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginSwapMarginCompleteRequest.class);
    
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_swapMarginComplete";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (注文ID)
     */
    public String orderId;
    
    /**
     * (決済順序区分)<BR>
     * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順<BR>
     * <BR>
     * 一括現引現渡の場合設定<BR>
     */
    public String closingOrder;
    
    /**
     * (決済建株一覧)<BR>
     * 信用取引決済建株明細の一覧
     */
    public WEB3MarginCloseMarginContractUnit[] closeMarginContractUnits;
    
    /**
     * (注文株数)
     */
    public String orderQuantity;
    
    /**
     * (現引先現渡元口座区分)<BR>
     * 0：一般　@1：特定<BR>
     * <BR>
     * 現引の時は現引先口座区分<BR>
     * 現渡の時は現渡元口座区分<BR>
     */
    public String swapTaxType;
    
    /**
     * (確認時発注日)<BR>
     * <BR>
     * 確認レスポンスで送信した値。<BR>
     */
    public Date checkDate;
    
    /**
     * 暗証番号<BR>
     */
    public String password;
    
    /**
     * @@roseuid 4140411200A2
     */
    public WEB3MarginSwapMarginCompleteRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@決済建株一覧チェック<BR>
     * 　@１－１）this.決済建株一覧＝nullの場合、<BR>
     * 　@　@　@　@　@「決済建株一覧がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00610<BR>
     * <BR>
     * 　@１－２）this.決済建株一覧の要素数＝０の場合、<BR>
     * 　@　@　@　@　@「決済建株一覧.要素数が0」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00611<BR>
     * <BR>
     * ２）　@決済順序区分チェック<BR>
     * 　@２－１）this.決済順序区分がnull以外の値でかつ、<BR>
     * 　@　@　@　@　@this.決済順序区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@「決済順序区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@・”0：ランダム”<BR>
     * 　@　@　@　@　@　@　@・”1：単価益順”<BR>
     * 　@　@　@　@　@　@　@・”2：単価損順”<BR>
     * 　@　@　@　@　@　@　@・”3：建日順”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00618<BR>
     * <BR>
     * 　@２－２）this.決済順序区分がnullかつ、<BR>
     * 　@　@　@　@　@this.決済建株一覧の要素数＞１の場合、<BR>
     *　@　@　@　@　@「一括返済時、決済順序が未指定」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR> 
     *   tag:   BUSINESS_ERROR_02304<BR>
     * <BR>
     * ３）　@注文株数チェック<BR>
     * 　@３－１）this.決済順序区分＝（null、”1：単価益順”、”2：単価損順”、<BR>
     * ”3：建日順”のいずれかの値）、<BR>
     * 　@　@　@　@かつ　@this.注文株数＝nullであれば<BR>
     * 　@　@　@　@「注文株数がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * 　@３－２）this.注文株数≠null かつ、<BR>
     * 　@　@　@　@this.注文株数が数字以外の値であれば、<BR>
     * 　@　@　@　@「注文株数が数字以外」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * 　@３－３）this.注文株数≠null かつ、<BR>
     * 　@　@　@　@this.注文株数≦０であれば<BR>
     * 　@　@　@　@「注文株数が0以下」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * ４）　@決済順位チェック<BR>
     * 　@４－１）this.決済順序区分が”0：ランダム”の値の場合<BR>
     * 　@　@　@　@決済建株一覧の要素(決済建株明細)数分下記の<BR>
     *         チェックを繰り返して行う。<BR>
     * 　@　@　@　@・決済建株明細のvalidate()メソッドを呼び出す。<BR>
     * <BR>
     * 　@４－２）this.決済順序区分が”0：ランダム”の値の場合、<BR>
     * 　@　@　@　@　@以下のチェックを行う。<BR>
     * 　@　@４－２－１）決済建株一覧の要素数内の全ての決済順位＝nullまたは0<BR>
     * 　@　@　@　@であった場合、<BR>
     * 　@　@　@　@「要素数内全ての決済順位がnullまたは0」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00619<BR>
     * <BR>
     * 　@　@４－２－２）決済建株一覧の要素数内全ての注文株数、決済順位について、<BR>
     * 　@　@　@　@注文株数 > 0　@かつ　@決済順位 > 0となる組み合わせが存在しない場合、<BR>
     * 　@　@　@　@「決済対象なし」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00620<BR>
     * <BR>
     * 　@４－３）this.決済順序区分＝null または、<BR>
     * 　@　@　@　@”1：単価益順”、”2：単価損順”、”3：建日順”の値の場合<BR>
     * 　@　@　@　@決済建株一覧の要素数分下記のチェックを繰り返して行う。<BR>
     * 　@　@４－３－１）決済建株明細.注文株数、決済建株明細.決済順序≠null<BR>
     * 　@　@　@　@であれば「ランダム決済以外は注文株数、決済順位の指定不可」の<BR>
     * 　@　@　@　@例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00621<BR>
     * <BR>
     * 　@　@４－３－２）決済建株明細のvalidate()メソッドを呼び出す。<BR>
     * <BR>
     * ５）　@確認時発注日チェック　@（注文ID≠nuｌｌの場合のみ実施する）<BR>
     * 　@this.確認時発注日＝nullの場合、<BR>
     * 　@「確認時発注日がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * ６）　@現引先現渡元口座区分チェック<BR>
     * 　@６－１）this.現引先現渡元口座区分＝nullの場合、<BR>
     * 　@　@　@　@　@「現引先現渡元口座区分がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00622<BR>
     * 　@６－２）this.現引先現渡元口座区分≠null、<BR>
     * 　@　@　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@　@　@「現引先現渡元口座区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・”0：一般”<BR>
     * 　@　@　@　@・”1：特定”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00623<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4085DA6403D7
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "●●● WEB3MarginSwapMarginCompleteRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("決済建株一覧チェック");
        // １）　@決済建株一覧チェック<BR>
        // 　@１－１）this.決済建株一覧＝nullの場合、<BR>
        // 　@　@　@　@　@「決済建株一覧がnull」の例外をスローする。<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00610<BR>
        if (this.closeMarginContractUnits == null)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00610,
            this.getClass().getName() + "validate");
        }

        // 　@１－２）this.決済建株一覧の要素数＝０の場合、<BR>
        // 　@　@　@　@　@「決済建株一覧.要素数が0」の例外をスローする。<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00611<BR>
        int l_intCloseMarginContractUnitsLength = this.closeMarginContractUnits.length;
        if (l_intCloseMarginContractUnitsLength == 0)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00611,
            this.getClass().getName() + "validate");
        }
        
        log.debug("決済順序区分チェック");
        // ２）　@決済順序区分チェック<BR>
        // 　@２－１）this.決済順序区分がnull以外の値でかつ、<BR>
        // 　@　@　@　@　@this.決済順序区分が以下の値以外の場合、<BR>
        // 　@　@　@　@　@「決済順序区分が未定義の値」の例外をスローする。<BR>
        // 　@　@　@　@　@　@　@・”0：ランダム”<BR>
        // 　@　@　@　@　@　@　@・”1：単価益順”<BR>
        // 　@　@　@　@　@　@　@・”2：単価損順”<BR>
        // 　@　@　@　@　@　@　@・”3：建日順”<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00618<BR>
        if (this.closingOrder != null
                && !WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder)
                && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
                && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
                && !WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00618,
            this.getClass().getName() + "validate");
        }

        // 　@２－２）this.決済順序区分がnullかつ、<BR>
        // 　@　@　@　@　@this.決済建株一覧の要素数＞１の場合、<BR>
        // 　@　@　@　@　@「一括返済時、決済順序が未指定」の例外をスローする。<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_02304<BR>
        if (this.closingOrder == null
                &&l_intCloseMarginContractUnitsLength > 1)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02304,
            this.getClass().getName() + "validate");
        }

        log.debug("注文株数チェック");
        // ３）　@注文株数チェック<BR>
        // 　@３－１）this.決済順序区分＝（null、”1：単価益順”、”2：単価損順”、<BR>
        // ”3：建日順”のいずれかの値）、<BR>
        // 　@　@　@　@かつ　@this.注文株数＝nullであれば<BR>
        // 　@　@　@　@「注文株数がnull」の例外をスローする。<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00126<BR>
        if ((this.closingOrder == null
                || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
                || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
                || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
                && this.orderQuantity == null)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00126,
            this.getClass().getName() + "validate");
        }

        // 　@３－２）this.注文株数≠null かつ、<BR>
        // 　@　@　@　@this.注文株数が数字以外の値であれば、<BR>
        // 　@　@　@　@「注文株数が数字以外」の例外をスローする。<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00901<BR>
        if (this.orderQuantity != null && !WEB3StringTypeUtility.isNumber(this.orderQuantity))
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00901,
            this.getClass().getName() + "validate");
        }

        // 　@３－３）this.注文株数≠null かつ、<BR>
        // 　@　@　@　@this.注文株数≦０であれば<BR>
        // 　@　@　@　@「注文株数が0以下」の例外をスローする。<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00902<BR>
        if (this.orderQuantity != null && Long.parseLong(this.orderQuantity) <= 0)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00902,
            this.getClass().getName() + "validate");
        }
        
        log.debug("決済順位チェック");
        // ４）　@決済順位チェック<BR>
        if (WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder))
        {
            int l_intCnt1 = 0;
            int l_intCnt2 = 0;
            for (int i = 0; i < l_intCloseMarginContractUnitsLength; i++)
            {
                // 　@４－１）this.決済順序区分が”0：ランダム”の値の場合<BR>
                // 　@　@　@　@決済建株一覧の要素(決済建株明細)数分下記の<BR>
                //         チェックを繰り返して行う。<BR>
                // 　@　@　@　@・決済建株明細のvalidate()メソッドを呼び出す。<BR>
                closeMarginContractUnits[i].validate();


                // 　@４－２）this.決済順序区分が”0：ランダム”の値の場合、<BR>
                // 　@　@　@　@　@以下のチェックを行う。<BR>
                // 　@　@４－２－１）決済建株一覧の要素数内の全ての決済順位＝nullまたは0<BR>
                // 　@　@　@　@であった場合、<BR>
                // 　@　@　@　@「要素数内全ての決済順位がnullまたは0」の例外をスローする。<BR>
                //   class: WEB3BusinessLayerException<BR>
                //   tag:   BUSINESS_ERROR_00619<BR>
                if (closeMarginContractUnits[i].settlePriority == null 
                        || Long.parseLong(closeMarginContractUnits[i].settlePriority) == 0)
                {
                    l_intCnt1 += 1;
                }
            
                // 　@　@４－２－２）決済建株一覧の要素数内全ての注文株数、決済順位について、<BR>
                // 　@　@　@　@注文株数 > 0　@かつ　@決済順位 > 0となる組み合わせが存在しない場合、<BR>
                // 　@　@　@　@「決済対象なし」の例外をスローする。<BR>
                //   class: WEB3BusinessLayerException<BR>
                //   tag:   BUSINESS_ERROR_00620<BR>
                if (closeMarginContractUnits[i].settlePriority == null
                        || !(Long.parseLong(closeMarginContractUnits[i].orderQuantity) > 0 
                        && Long.parseLong(closeMarginContractUnits[i].settlePriority) > 0))
                {
                    l_intCnt2 += 1;
                }
            }
            
            if (l_intCnt1 == l_intCloseMarginContractUnitsLength)
            {
                //例外
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00619,
                this.getClass().getName() + "validate");
            }
            
            if (l_intCnt2 == l_intCloseMarginContractUnitsLength)
            {
                //例外
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00620,
                this.getClass().getName() + "validate");
            }
        }

        // 　@４－３）this.決済順序区分＝null または、<BR>
        // 　@　@　@　@”1：単価益順”、”2：単価損順”、”3：建日順”の値の場合<BR>
        // 　@　@　@　@決済建株一覧の要素数分下記のチェックを繰り返して行う。<BR>
        if ((this.closingOrder == null
                || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
                || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
                || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder)))
        {
            for (int i = 0; i < l_intCloseMarginContractUnitsLength; i++)
            {
                // 　@　@４－３－１）決済建株明細.注文株数、決済建株明細.決済順序≠null<BR>
                // 　@　@　@　@であれば「ランダム決済以外は注文株数、決済順位の指定不可」の<BR>
                // 　@　@　@　@例外をスローする。<BR>
                //   class: WEB3BusinessLayerException<BR>
                //   tag:   BUSINESS_ERROR_00621<BR>
                if (closeMarginContractUnits[i].orderQuantity != null
                        || closeMarginContractUnits[i].settlePriority != null)
                {
                    //例外
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00621,
                    this.getClass().getName() + "validate");
                }

                // 　@　@４－３－２）決済建株明細のvalidate()メソッドを呼び出す。<BR>
                closeMarginContractUnits[i].validate();
            }
        }

        if (this.orderId != null)
        {
            log.debug("確認時発注日チェック");
            // ５）　@確認時発注日チェック　@（注文ID≠nuｌｌの場合のみ実施する）<BR>
            // 　@this.確認時発注日＝nullの場合、<BR>
            // 　@「確認時発注日がnull」の例外をスローする。<BR>
            //   class: WEB3BusinessLayerException<BR>
            //   tag:   BUSINESS_ERROR_00078<BR>
            if (this.checkDate == null)
            {
                //例外
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "validate");
            }
        }

        log.debug("現引先現渡元口座区分チェック");
        // ６）　@現引先現渡元口座区分チェック<BR>
        // 　@６－１）this.現引先現渡元口座区分＝nullの場合、<BR>
        // 　@　@　@　@　@「現引先現渡元口座区分がnull」の例外をスローする。<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00622<BR>
        if (this.swapTaxType == null)
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00622,
            this.getClass().getName() + "validate");
        }
        
        // 　@６－２）this.現引先現渡元口座区分≠null、<BR>
        // 　@　@　@　@　@かつ下記の値以外の場合、<BR>
        // 　@　@　@　@　@「現引先現渡元口座区分が未定義の値」の例外をスローする。<BR>
        // 　@　@　@　@・”0：一般”<BR>
        // 　@　@　@　@・”1：特定”<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00623<BR>
        if (!WEB3TaxTypeDef.NORMAL.equals(this.swapTaxType)
                && !WEB3TaxTypeDef.SPECIAL.equals(this.swapTaxType))
        {
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00623,
            this.getClass().getName() + "validate");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateAT反対取引)<BR>
     * 反対取引指定時の、当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （連続注文用のメソッド）<BR>
     * <BR>
     * １）　@注文IDチェック<BR>
     * 　@１－１）this.注文ID＝nullの場合、<BR>
     * 　@　@　@　@　@「注文IDがnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * ２）　@決済建株一覧チェック<BR>
     * 　@２－１）this.決済建株一覧＝nullの場合、<BR>
     * 　@　@　@　@　@「決済建株一覧がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00610<BR>
     * <BR>
     * 　@２－２）this.決済建株一覧の要素数＝０の場合、<BR>
     * 　@　@　@　@　@「決済建株一覧.要素数が0」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00611<BR>
     * <BR>
     * ３）　@決済順序区分チェック<BR>
     * 　@this.決済順序区分がnull以外の値でかつ、<BR>
     * 　@this.決済順序区分が以下の値以外の場合、<BR>
     * 　@「決済順序区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・”0：ランダム”<BR>
     * 　@　@　@　@・”1：単価益順”<BR>
     * 　@　@　@　@・”2：単価損順”<BR>
     * 　@　@　@　@・”3：建日順”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00618<BR>
     * <BR>
     * ４）　@注文株数チェック<BR>
     * 　@４－１）this.決済順序区分＝（null、”1：単価益順”、”2：単価損順”、”3：建日順”のいずれかの値）、<BR>
     * 　@　@　@　@かつ　@this.注文株数＝nullであれば<BR>
     * 　@　@　@　@「注文株数がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * 　@４－２）this.注文株数≠null かつ、<BR>
     * 　@　@　@　@this.注文株数が数字以外の値であれば、<BR>
     * 　@　@　@　@「注文株数が数字以外」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00901<BR>
     * <BR>
     * 　@４－３）this.注文株数≠null かつ、<BR>
     * 　@　@　@　@this.注文株数≦０であれば<BR>
     * 　@　@　@　@「注文株数が0以下」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00902<BR>
     * <BR>
     * ５）　@明細の注文株数チェック<BR>
     * 　@５－１）決済順序区分＝”0：ランダム”の場合のみ、<BR>
     * 　@　@　@　@決済建株一覧の要素(決済建株明細)数分<BR>
     * 　@　@　@　@下記のチェックを繰り返して行う。<BR>
     * 　@　@　@　@※反対取引の場合は、実際には１明細のみが設定されてくる。<BR>
     * 　@　@　@　@※また、ランダム以外の場合は、リクエスト.注文株数を使用するので、<BR>
     * 　@　@　@　@※チェック不要。<BR>
     * 　@　@　@　@・決済建株明細.注文株数 が以下のいずれかの場合は、<BR>
     * 　@　@　@　@　@「決済明細の注文株数指定が不正」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・null<BR>
     * 　@　@　@　@　@　@・数字以外<BR>
     * 　@　@　@　@　@　@・０以下の数字<BR>
     * 　@　@　@　@　@　@・８桁を超える数字<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02285<BR>
     * <BR>
     * ６）　@確認時発注日チェック<BR>
     * 　@this.確認時発注日＝nullの場合、<BR>
     * 　@「確認時発注日がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * ７）　@現引先現渡元口座区分チェック<BR>
     * 　@７－１）this.現引先現渡元口座区分＝nullの場合、<BR>
     * 　@　@　@　@　@「現引先現渡元口座区分がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00622<BR>
     * 　@７－２）this.現引先現渡元口座区分≠null、<BR>
     * 　@　@　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@　@　@「現引先現渡元口座区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・”0：一般”<BR>
     * 　@　@　@　@・”1：特定”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00623<BR>
     * @@throws WEB3BaseException
     */
    public void validateAtReverseOrder() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateAtReverseOrder";
        log.entering(STR_METHOD_NAME);
        
        if (this.orderId == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (this.closeMarginContractUnits == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00610,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        int l_intCloseMarginContractUnitsLength = this.closeMarginContractUnits.length;
        if (l_intCloseMarginContractUnitsLength == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00611,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (this.closingOrder != null
                && !WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder)
                && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
                && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
                && !WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00618,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if ((this.closingOrder == null
                || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
                || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
                || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
                && this.orderQuantity == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00126,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (this.orderQuantity != null && !WEB3StringTypeUtility.isNumber(this.orderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00901,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (this.orderQuantity != null && Long.parseLong(this.orderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00902,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (WEB3ClosingOrderDef.RANDOM.equals(closingOrder))
        {
            for (int i = 0;i < closeMarginContractUnits.length;i++)
            {
                String l_strOrderQuantity = closeMarginContractUnits[i].orderQuantity;
                if (l_strOrderQuantity == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02285,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                if (!WEB3StringTypeUtility.isNumber(l_strOrderQuantity))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02285,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                long l_lngOrderQuantity =
                    Long.parseLong(closeMarginContractUnits[i].orderQuantity);
                if (l_lngOrderQuantity <= 0L ||
                    l_lngOrderQuantity > 99999999L)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02285,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        
        if (this.checkDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (this.swapTaxType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00622,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (!WEB3TaxTypeDef.NORMAL.equals(this.swapTaxType)
                && !WEB3TaxTypeDef.SPECIAL.equals(this.swapTaxType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00623,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4140411200C0
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginSwapMarginCompleteResponse(this);
    }
}
@
