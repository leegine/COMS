head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteDateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 債券約定日情報(WEB3BondExecuteDateInfo.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  齊珂 (中訊) 新規作成
                      2006/10/08 周捷 (中訊) 仕様変更・モデル099、110
 Revesion History : 2009/07/24 武波 (中訊) 仕様変更・モデル262
 */

package webbroker3.bd;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券約定日情報)<BR>
 * 債券約定日情報<BR>
 * <BR>
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3BondExecuteDateInfo
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3BondExecuteDateInfo.class);

    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    private Date bizDate;

    /**
     * (現地発注日)<BR>
     * 現地発注日<BR>
     */
    private Date foreignBizDate;

    /**
     * (約定日)<BR>
     * 約定日<BR>
     */
    private Date executeDate;

    /**
     * (現地約定日)<BR>
     * 現地約定日<BR>
     */
    private Date foreignExecuteDate;

    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    private Date deliveryDate;

    /**
     * (現地受渡日)<BR>
     * 現地受渡日<BR>
     */
    private Date foreignDeliveryDate;

    /**
     * (入金日)<BR>
     * 入金日<BR>
     */
    private Date paymentDate;

    /**
     * (債券約定日情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44C7562D0261
     */
    public WEB3BondExecuteDateInfo()
    {

    }

    /**
     * (get発注日)<BR>
     * get発注日<BR>
     * @@return java.util.Date
     * @@roseuid 44C6F79600C8
     */
    public Date getBizDate()
    {
        return this.bizDate;
    }

    /**
     * (get現地発注日)<BR>
     * get現地発注日<BR>
     * @@return java.util.Date
     * @@roseuid 44C6F7A3030A
     */
    public Date getForeignBizDate()
    {
        return this.foreignBizDate;
    }

    /**
     * (get約定日)<BR>
     * get約定日<BR>
     * @@return java.util.Date
     * @@roseuid 44C6F7AC005B
     */
    public Date getExecuteDate()
    {
        return executeDate;
    }

    /**
     * (get現地約定日)<BR>
     * get現地約定日<BR>
     * @@return java.util.Date
     * @@roseuid 44C6F7AC005C
     */
    public Date getForeignExecuteDate()
    {
        return this.foreignExecuteDate;
    }

    /**
     * (get受渡日)<BR>
     * get受渡日<BR>
     * @@return java.util.Date
     * @@roseuid 44C6F7AD004B
     */
    public Date getDeliveryDate()
    {
        return this.deliveryDate;
    }

    /**
     * (get現地受渡日)<BR>
     * get現地受渡日<BR>
     * @@return java.util.Date
     * @@roseuid 44C6F7AD004C
     */
    public Date getForeignDeliveryDate()
    {
        return this.foreignDeliveryDate;
    }

    /**
     * (get入金日)<BR>
     * get入金日<BR>
     * @@return Date
     */
    public Date getPaymentDate()
    {
        return this.paymentDate;
    }

    /**
     * (set発注日)<BR>
     * set発注日<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@roseuid 44C6F7D40125
     */
    public void setBizDate(Date l_datBizDate)
    {
        this.bizDate = l_datBizDate;
    }

    /**
     * (set現地発注日)<BR>
     * set現地発注日<BR>
     * @@param l_datForeignBizDate - (現地発注日)<BR>
     * 現地発注日<BR>
     * @@roseuid 44C6F7F0000C
     */
    public void setForeignBizDate(Date l_datForeignBizDate)
    {
        this.foreignBizDate = l_datForeignBizDate;
    }

    /**
     * (set約定日)<BR>
     * set約定日<BR>
     * @@param l_datExecuteDate - (約定日)<BR>
     * 約定日<BR>
     * @@roseuid 44C6F82601EF
     */
    public void setExecuteDate(Date l_datExecuteDate)
    {
        this.executeDate = l_datExecuteDate;
    }

    /**
     * (set現地約定日)<BR>
     * set現地約定日<BR>
     * @@param l_datForeignExecuteDate - (現地約定日)<BR>
     * 現地約定日<BR>
     * @@roseuid 44C6F82601F1
     */
    public void setForeignExecuteDate(Date l_datForeignExecuteDate)
    {
        this.foreignExecuteDate = l_datForeignExecuteDate;
    }

    /**
     * (set受渡日)<BR>
     * set受渡日<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@roseuid 44C6F82700F5
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        this.deliveryDate = l_datDeliveryDate;
    }

    /**
     * (set現地受渡日)<BR>
     * set現地受渡日<BR>
     * @@param l_datForeignDeliveryDate - (現地受渡日)<BR>
     * 現地受渡日<BR>
     * @@roseuid 44C6F82700F7
     */
    public void setForeignDeliveryDate(Date l_datForeignDeliveryDate)
    {
         this.foreignDeliveryDate = l_datForeignDeliveryDate;
    }

    /**
     * (set入金日)<BR>
     * set入金日<BR>
     * @@param l_datPaymentDate - (入金日)<BR>
     * 入金日
     */
    public void setPaymentDate(Date l_datPaymentDate)
    {
        this.paymentDate = l_datPaymentDate;
    }

    /**
     * (validate約定日)<BR>
     * validate約定日<BR>
     * <BR>
     * １）this.get約定日が営業日ではない場合、例外をスローする。 <BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02631<BR>
     * <BR>
     * ２）this.get受渡日が営業日ではない場合、例外をスローする。 <BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02336<BR>
     * <BR>
     * ３）this.get約定日＞this.get受渡日の場合、例外をスローする。 <BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02528<BR>
     * <BR>
     * ４）引数.債券銘柄.is外国債券 ＝＝ trueの場合、 <BR>
     * 　@　@４－１）this.get現地約定日 ＝＝ null の場合、例外をスローする。 <BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02529<BR>
     * <BR>
     * 　@  ４－２）this.get現地受渡日 ＝＝ null　@の場合、例外をスローする。 <BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02530<BR>
     * <BR>
     * 　@　@４－３）this.get現地約定日＞this.get現地受渡日の場合、例外をスローする。 <BR>
     * <BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02531<BR>
     *
     * ５）this.get入金日が営業日ではない場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02681<BR>
     *
     * ６）this.get約定日＞発注日の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02693<BR>
     *
     * ７）this.get受渡日＜発注日の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02689<BR>
     *
     * ８）this.get入金日＜営業日(T+0)の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02690<BR>
     *
     * ９）this.get入金日＜this.get約定日の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_02700<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44C6FF2703DC
     */
    public void validateExecuteDate(WEB3BondProduct l_bondProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExecuteDate(WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）this.get約定日が営業日ではない場合、例外をスローする。
        Timestamp l_tisExecuteDate = new Timestamp(this.getExecuteDate().getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tisExecuteDate)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02631,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定日が非営業日です。");
        }

        // ２）this.get受渡日が営業日ではない場合、例外をスローする。
        Timestamp l_tisDeliveryDate = new Timestamp(this.getDeliveryDate().getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tisDeliveryDate)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02336,
                this.getClass().getName() + STR_METHOD_NAME,
                "受渡日が非営業日です。");
        }

        //３）this.get約定日＞this.get受渡日の場合、例外をスローする。
        if (this.getExecuteDate().compareTo(this.getDeliveryDate()) > 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02528,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定日が受渡日を超えています。");
        }

        //４）引数.債券銘柄.is外国債券 ＝＝ trueの場合
        if (l_bondProduct.isForeignBond())
        {
            //    ４－１）this.get現地約定日 ＝＝ null の場合、例外をスローする。
            if (this.getForeignExecuteDate() == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02529,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "現地約定日が未指定です。");
            }

            //   4－２）this.get現地受渡日 ＝＝ null　@の場合、例外をスローする。
            if (this.getForeignDeliveryDate() == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02530,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "現地受渡日が未指定です。");
            }

            //　@４－３）this.get現地約定日＞this.get現地受渡日の場合、例外をスローする。
            if (this.getForeignExecuteDate().compareTo(this.getForeignDeliveryDate()) > 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02531,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "現地約定日が現地受渡日を超えています。");
            }
        }

        //５）this.get入金日が営業日ではない場合、例外をスローする。
        Timestamp l_tisPaymentDate = new Timestamp(this.getPaymentDate().getTime());
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tisPaymentDate)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02681,
                this.getClass().getName() + STR_METHOD_NAME,
                "入金日が非営業日です。");
        }

        //６）this.get約定日＞発注日の場合、例外をスローする。
        if (this.getExecuteDate().compareTo(this.getBizDate()) > 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02693,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定日が発注日を越えています。");
        }
        
        //７）this.get受渡日＜発注日の場合、例外をスローする。
        if (this.getDeliveryDate().compareTo(this.getBizDate()) < 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02689,
                this.getClass().getName() + STR_METHOD_NAME,
                "受渡日は発注日以降にして下さい。");
        }
        
        //８）this.get入金日＜営業日(T+0)の場合、例外をスローする。
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        if (this.getPaymentDate().compareTo(l_datBizDate) < 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02690,
                this.getClass().getName() + STR_METHOD_NAME,
                "入金日は当日以降にして下さい。");
        }
        
        //９）this.get入金日＜this.get約定日の場合、例外をスローする。
        if (this.getPaymentDate().compareTo(this.getExecuteDate()) < 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02700,
                this.getClass().getName() + STR_METHOD_NAME,
                "入金日は約定日以降にして下さい。");
        }
    }
}
@
