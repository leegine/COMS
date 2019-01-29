head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引返済注文完了リクエストクラス(WEB3MarginCloseMarginCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 李松峰 (中訊) 新規作成
Revesion History : 2007/06/04 何文敏 (中訊) 仕様変更モデル1153
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引返済注文完了リクエスト）。<br>
 * <br>
 * 信用取引返済注文完了リクエストクラス
 * @@version 1.0
 */
public class WEB3MarginCloseMarginCompleteRequest extends WEB3MarginCommonRequest 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginCloseMarginCompleteRequest.class);

    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginComplete";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101643L;        
    /**
     * (注文ID)<BR>
     */
    public String orderId;
    
    /**
     * (決済順序区分)<BR>
     * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順<BR>
     * <BR>
     * 一括決済の場合設定<BR>
     */
    public String closingOrder;
    
    /**
     * (信用取引決済建株の一覧)<BR>
     */
    public WEB3MarginCloseMarginContractUnit[] closeMarginContractUnits;
    
    /**
     * (確認時単価)<BR>
     * <BR>
     * 確認レスポンスで送信した値。<BR>
     */
    public String checkPrice;
    
    /**
     * (確認時発注日)<BR>
     * <BR>
     * 確認レスポンスで送信した値。<BR>
     */
    public Date checkDate;
    
    /**
     * (暗証番号)<BR>
     */
    public String password;

    /**
     * (手動強制決済フラグ)<BR>
     * 手動強制決済フラグ<BR>
     * <BR>
     * true：　@手動強制決済注文<BR>
     * false：　@手動強制決済注文でない<BR>
     * <BR>
     * @@return boolean
     */
    public boolean manualForcedSettleFlag = false;

    /**
     * @@roseuid 414047D8020E
     */
    public WEB3MarginCloseMarginCompleteRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す。<BR>
     * <BR>
     * ２）　@決済建株一覧チェック<BR>
     * 　@２－１）this.決済建株一覧＝nullの場合、<BR>
     * 　@　@　@　@　@「決済建株一覧がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00610<BR>     
     * <BR>
     * 　@２－２）this.決済建株一覧が要素数＝０の場合、<BR>
     * 　@　@　@　@　@「決済建株一覧.要素数が0」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00611<BR>     
     * <BR>
     * ３）　@決済順序区分チェック<BR>
     * 　@３－１）this.決済順序区分≠nullでかつ、<BR>
     * 　@　@　@　@　@this.決済順序区分が以下の値以外の場合、<BR>
     *　@　@　@　@ 　@「決済順序区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@　@・”0：ランダム”<BR>
     * 　@　@　@　@　@　@　@・”1：単価益順”<BR>
     * 　@　@　@　@　@　@　@・”2：単価損順”<BR>
     * 　@　@　@　@　@　@　@・”3：建日順”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00618<BR>     
     * <BR>
     * 　@３－２）this.決済順序区分がnullかつ、<BR> 
     * 　@　@　@　@　@this.決済建株一覧の要素数＞１の場合、<BR> 
     * 　@　@　@　@　@「一括返済時、決済順序が未指定」の例外をスローする。<BR> 
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02304<BR>
     * <BR> 
     * ４）　@注文株数チェック<BR>
     * 　@４－１）this.決済順序区分＝（null、”1：単価益順”、<BR>
     *   ”2：単価損順”、”3：建日順”のいずれかの値）、<BR>
     * 　@　@　@　@かつ　@this.注文株数＝nullの場合、<BR>
     * 　@　@　@　@「注文株数がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00624<BR>     
     * <BR>
     * ５）　@決済順位チェック<BR>
     * 　@５－１）this.決済順序区分＝”0：ランダム”の場合、<BR>
     * 　@　@　@　@決済建株一覧の要素(決済建株明細)数分<BR>
     * 　@　@　@　@下記のチェックを繰り返して行う。<BR>
     * 　@　@　@・決済建株明細のvalidate()メソッドを呼び出す。<BR>
     * <BR>
     * 　@５－２）this.決済順序区分＝”0：ランダム”の場合、<BR>
     * 　@　@　@　@　@以下のチェックを行う。<BR>
     * 　@　@５－２－１）決済建株一覧の要素数内の全ての決済順位＝nullまたは0の場合、<BR>
     * 　@　@　@　@「要素数内全ての決済順位がnullまたは0」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00619<BR>     
     * <BR>
     * 　@　@５－２－２）決済建株一覧の要素数内全ての注文株数、決済順位について、<BR>
     * 　@　@　@　@注文株数 > 0　@かつ　@決済順位 > 0となる組み合わせが存在しない場合、<BR>
     * 　@　@　@　@「決済対象なし」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00620<BR>     
     * <BR>
     * 　@５－３）this.決済順序区分＝nullまたは、<BR>
     * 　@　@　@　@”1：単価益順”、”2：単価損順”、”3：建日順”の値の場合、<BR>
     * 　@　@　@　@決済建株一覧の要素(決済建株明細)数分下記のチェックを繰り返して行う。<BR>
     * 　@　@　@・決済建株明細.決済順位≠nullの場合、<BR>
     * 　@　@　@　@「ランダム返済以外は注文株数、決済順位の指定不可」の<BR>
     * 　@　@　@　@例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00621<BR>
     * <BR>
     * 　@　@　@・決済建株明細のvalidate()メソッドを呼び出す。<BR>
     * <BR>
     * ６）　@確認時単価チェック　@（注文ID≠nullの場合のみ実施する）<BR>
     * 　@this.確認時単価＝nullであった場合、「確認時単価がnull」の<BR>
     * 　@例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00206<BR>
     * <BR>
     * ７）　@確認時発注日チェック　@（注文ID≠nullの場合のみ実施する）<BR>
     * 　@this.確認時発注日＝nullであった場合、「確認時発注日がnull」の<BR>
     * 　@例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * ８）　@手動強制決済フラグチェック<BR>
     * 　@８－１）this.手動強制決済フラグ＝true、<BR>
     * 　@　@　@　@　@かつ決済順序区分≠nullの場合、<BR>
     * 　@　@　@　@　@「手動強制決済は一括決済不可」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02812<BR>
     * <BR>
     * 　@８－２）this.手動強制決済フラグ＝true<BR>
     * 　@　@　@　@　@かつthis.注文期限区分≠"当日限り"の場合、<BR>
     * 　@　@　@　@　@「手動強制決済は出来るまで注文指定不可」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02813<BR>
     * <BR>
     * 　@８－３）this.手動強制決済フラグ＝true<BR>
     * 　@　@　@　@　@かつthis.発注条件区分≠"指定なし"の場合、<BR>
     * 　@　@　@　@　@「手動強制決済は発注条件指定不可」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02813<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4084B4D00358
     */
    public void validate() throws WEB3BaseException 
    { 
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        // １）　@スーパークラスのvalidateメソッドを呼び出す。<BR>
        //
        super.validate();
        // ２）　@決済建株一覧チェック<BR>
        //    * 　@２－１）this.決済建株一覧＝nullの場合、<BR>
        //    * 　@　@　@　@　@「決済建株一覧がnull」の例外をスローする。<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00610<BR>     
        //
        if (closeMarginContractUnits == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00610,STR_METHOD_NAME);
        }
        // 　@２－２）this.決済建株一覧が要素数＝０の場合、<BR>
        //    * 　@　@　@　@　@「決済建株一覧.要素数が0」の例外をスローする。<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00611<BR>     
        //
        if (closeMarginContractUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00611,STR_METHOD_NAME);
        }
        // ３）　@決済順序区分チェック<BR>
        //    * 　@３－１）this.決済順序区分≠nullでかつ、<BR>
        //    * 　@　@　@　@　@this.決済順序区分が以下の値以外の場合、<BR>
        //    * 　@　@　@　@　@「決済順序区分が未定義の値」の例外をスローする。<BR>
        //    * 　@　@　@　@　@　@　@・”0：ランダム”<BR>
        //    * 　@　@　@　@　@　@　@・”1：単価益順”<BR>
        //    * 　@　@　@　@　@　@　@・”2：単価損順”<BR>
        //    * 　@　@　@　@　@　@　@・”3：建日順”<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00618<BR>     
        //
        if (closingOrder != null && !WEB3ClosingOrderDef.RANDOM.equals(closingOrder) && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder)  && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) && !WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder))            
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00618,STR_METHOD_NAME);
        }
        //    * 　@３－２）this.決済順序区分がnullかつ、<BR> 
        //    * 　@　@　@　@　@this.決済建株一覧の要素数＞１の場合、<BR> 
        //    * 　@　@　@　@　@「一括返済時、決済順序が未指定」の例外をスローする。<BR> 
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_02304<BR>
        //
        if (closingOrder == null && closeMarginContractUnits.length > 1)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02304,STR_METHOD_NAME);
        }
        // ４）　@注文株数チェック<BR>
        //     * 　@４－１）this.決済順序区分＝（null、”1：単価益順”、<BR>
        //     *   ”2：単価損順”、”3：建日順”のいずれかの値）、<BR>
        //     * 　@　@　@　@かつ　@this.注文株数＝nullの場合、<BR>
        //     * 　@　@　@　@「注文株数がnull」の例外をスローする。<BR>
        //     *   class: WEB3BusinessLayerException<BR>
        //     *   tag:   BUSINESS_ERROR_00624<BR>     
        //
        if (closingOrder == null || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder) || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) || WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder))
        {
            if (orderQuantity == null )
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00624,STR_METHOD_NAME);
            }
        }
        // ５）　@決済順位チェック<BR>
        //    * 　@５－１）this.決済順序区分＝”0：ランダム”の場合、<BR>
        //    * 　@　@　@　@決済建株一覧の要素(決済建株明細)数分<BR>
        //    * 　@　@　@　@下記のチェックを繰り返して行う。<BR>
        //    * 　@　@　@・決済建株明細のvalidate()メソッドを呼び出す。<BR>
        //    * <BR>
        //    * 　@５－２）this.決済順序区分＝”0：ランダム”の場合、<BR>
        //    * 　@　@　@　@　@以下のチェックを行う。<BR>
        //    * 　@　@５－２－１）決済建株一覧の要素数内の全ての決済順位＝nullまたは0の場合、<BR>
        //    * 　@　@　@　@「要素数内全ての決済順位がnullまたは0」の例外をスローする。<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00619<BR>
        //    * 　@　@５－２－２）決済建株一覧の要素数内全ての注文株数、決済順位について、<BR>
        //    * 　@　@　@　@注文株数 > 0　@かつ　@決済順位 > 0となる組み合わせが存在しない場合、<BR>
        //    * 　@　@　@　@「決済対象なし」の例外をスローする。<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00620<BR>  
        // 
        long l_lngnullCnt=0;
        boolean l_boolFlag=false;
        if (WEB3ClosingOrderDef.RANDOM.equals(closingOrder))
        {
            for (int i = 0; i < closeMarginContractUnits.length;i++)
            {
                closeMarginContractUnits[i].validate();

                if (closeMarginContractUnits[i].settlePriority == null || WEB3ClosingOrderDef.RANDOM.equals(closeMarginContractUnits[i].settlePriority))
                {
                    l_lngnullCnt++;
                }
                if (closeMarginContractUnits[i].orderQuantity != null 
                && Long.parseLong(closeMarginContractUnits[i].orderQuantity) > 0 
                && closeMarginContractUnits[i].settlePriority != null
                && Long.parseLong(closeMarginContractUnits[i].settlePriority) > 0)
                {
                    l_boolFlag=true;
                }
            }
            if (l_lngnullCnt >= closeMarginContractUnits.length)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00619,STR_METHOD_NAME);
            }
            if (!l_boolFlag)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00620,STR_METHOD_NAME);   
            }
        }
        // 　@５－３）this.決済順序区分＝nullまたは、<BR>
        //    * 　@　@　@　@”1：単価益順”、”2：単価損順”、”3：建日順”の値の場合、<BR>
        //    * 　@　@　@　@決済建株一覧の要素(決済建株明細)数分下記のチェックを繰り返して行う。<BR>
        //    * 　@　@　@・決済建株明細.決済順位≠nullの場合、<BR>
        //    * 　@　@　@　@「ランダム返済以外は注文株数、決済順位の指定不可」の<BR>
        //    * 　@　@　@　@例外をスローする。<BR>
        //    *   class: WEB3BusinessLayerException<BR>
        //    *   tag:   BUSINESS_ERROR_00621<BR>
        //    * 　@　@　@・決済建株明細のvalidate()メソッドを呼び出す。<BR>                                           
        //
        l_boolFlag=false;
        if (closingOrder == null || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder) || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) || WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder)) 
        {
            for (int i = 0;i < closeMarginContractUnits.length;i++)
            {
                if (closeMarginContractUnits[i].settlePriority == null)
                {
                    l_boolFlag=true;
                }
                closeMarginContractUnits[i].validate();                 
            }
            if (!l_boolFlag)   
            {
               throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00621,STR_METHOD_NAME);
            }
        }
        if (this.orderId != null)
        {
            // ６）　@確認時単価チェック　@（注文ID≠nullの場合のみ実施する）<BR>
            //    * 　@this.確認時単価＝nullであった場合、「確認時単価がnull」の<BR>
            //    * 　@例外をスローする。<BR>
            //    *   class: WEB3BusinessLayerException<BR>
            //    *   tag:   BUSINESS_ERROR_00206<BR>
            //
            if (checkPrice == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00206,STR_METHOD_NAME);
            }
            // ７）　@確認時発注日チェック　@（注文ID≠nullの場合のみ実施する）<BR>
            //    * 　@this.確認時発注日＝nullであった場合、「確認時発注日がnull」の<BR>
            //    * 　@例外をスローする。<BR>
            //    *   class: WEB3BusinessLayerException<BR>
            //    *   tag:   BUSINESS_ERROR_00078<BR>
            //
            if (checkDate == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00078,STR_METHOD_NAME);
            }
        }

        // ８－１）this.手動強制決済フラグ＝true、
        // かつ決済順序区分≠nullの場合、
        // 「手動強制決済は一括決済不可」の例外をスローする。
        if (this.manualForcedSettleFlag && closingOrder != null)
        {
            log.debug("手動強制決済は一括決済不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02812,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "手動強制決済は一括決済不可。");
        }

        // ８－２）this.手動強制決済フラグ＝true
        // かつthis.注文期限区分≠"当日限り"の場合、
        if (this.manualForcedSettleFlag &&
            !WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType))
        {
            log.debug("手動強制決済は出来るまで注文指定不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02813,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "手動強制決済は出来るまで注文指定不可。");
        }

        // ８－３）this.手動強制決済フラグ＝true
        // かつthis.発注条件区分≠"指定なし"の場合、
        if (this.manualForcedSettleFlag &&
            !WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            log.debug("手動強制決済は発注条件指定不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02814,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "手動強制決済は発注条件指定不可。");
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
     * ２）　@注文IDチェック<BR>
     * 　@２－１）this.注文ID＝nullの場合、<BR>
     * 　@　@　@　@　@「注文IDがnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * ３）　@決済建株一覧チェック<BR>
     * 　@３－１）this.決済建株一覧＝nullの場合、<BR>
     * 　@　@　@　@　@「決済建株一覧がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00610<BR>
     * <BR>
     * 　@３－２）this.決済建株一覧が要素数＝０の場合、<BR>
     * 　@　@　@　@　@「決済建株一覧.要素数が0」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00611<BR>
     * <BR>
     * ４）　@決済順序区分チェック<BR>
     * 　@this.決済順序区分≠nullでかつ、<BR>
     * 　@this.決済順序区分が以下の値以外の場合、<BR>
     * 　@「決済順序区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・”0：ランダム”<BR>
     * 　@　@　@　@・”1：単価益順”<BR>
     * 　@　@　@　@・”2：単価損順”<BR>
     * 　@　@　@　@・”3：建日順”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00618<BR>
     * <BR>
     * ５）　@注文株数チェック<BR>
     * 　@５－１）this.決済順序区分＝（null、”1：単価益順”、”2：単価損順”、”3：建日順”のいずれかの値）、<BR>
     * 　@　@　@　@かつ　@this.注文株数＝nullの場合、<BR>
     * 　@　@　@　@「注文株数がnull」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00624<BR>
     * <BR>
     * ６）　@明細の注文株数チェック<BR>
     * 　@６－１）決済順序区分＝”0：ランダム”の場合のみ、<BR>
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
     * ７）　@確認時単価チェック<BR>
     * 　@this.確認時単価＝nullであった場合、「確認時単価がnull」の<BR>
     * 　@例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00206<BR>
     * <BR>
     * ８）　@確認時発注日チェック<BR>
     * 　@this.確認時発注日＝nullであった場合、「確認時発注日がnull」の<BR>
     * 　@例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00078<BR>
     * @@throws WEB3BaseException
     */
    public void validateAtReverseOrder() throws WEB3BaseException 
    { 
        final String STR_METHOD_NAME = "validateAtReverseOrder()";
        log.entering(STR_METHOD_NAME);
        
        super.validate();
        
        if (orderId == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
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
        
        if (closingOrder != null && !WEB3ClosingOrderDef.RANDOM.equals(closingOrder) && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder)  && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) && !WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder))            
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00618,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (closingOrder == null || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder) || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) || WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder))
        {
            if (orderQuantity == null )
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00624,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
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
        
        log.exiting(STR_METHOD_NAME); 
    }
    
   /*
    * @@return WEB3GenResponse
    */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginCloseMarginCompleteResponse(this);
    }
}
@
