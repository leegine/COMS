head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoTodayOpenOrder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 先物OP当日新規建注文情報クラス(WEB3IfoTodayOpenOrder.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/20 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.ifodeposit;

import java.text.ParseException;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.util.WEB3DateUtility;

/**
 * (先物OP当日新規建注文情報)<BR>
 * 当日の新規建注文の情報を表すクラス。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoTodayOpenOrder
{

    /**
     * 注文単位ID。
     */
    public long orderUnitid;

    /**
     * 銘柄ID。
     */
    public long productId;

    /**
     * 市場ID。
     */
    public long marketId;

    /**
     * (建区分)<BR>
     * 
     * 1：買建<BR>
     * 2：売建<BR>
     */
    public ContractTypeEnum contractType;

    /**
     * 発注日。
     */
    public Date orderBizDate;

    /**
     * 受渡日。
     */
    public Date deliveryDate;

    /**
     * (先物／オプション区分)<BR>
     * 
     * 1：先物<BR>
     * 2：オプション<BR>
     */
    public String ifoProductType;

    /**
     * 注文数量。
     */
    public double quantity = 0;

    /**
     * (オプション概算受渡代金。)<BR>
     * ※オプション買建の場合はマイナス値がセットされる。<BR>
     * ※先物の場合、0（初期値のまま）<BR>
     */
    public double optionEstimatedNetAmount = 0;

    /**
     * @@roseuid 4158CAEB03E5
     */
    public WEB3IfoTodayOpenOrder()
    {

    }

    /**
     * (create先物OP当日新規建注文情報)<BR>
     * 
     * 先物OP当日新規建注文情報を生成する。<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoTodayOpenOrder
     * @@roseuid 4112EC1500DB
     */
    public static WEB3IfoTodayOpenOrder create()
    {
        return new WEB3IfoTodayOpenOrder();
    }

    /**
     * (get先物OP当日新規建注文情報)<BR>
     * 
     * （staticメソッド）<BR>
     * 引数の注文単位Paramsより、先物OP当日新規建注文情報を作成する。<BR>
     * 
     * １）　@オブジェクトの生成<BR>
     * 　@先物OP当日新規建注文情報オブジェクトを生成する。<BR>
     * 
     * ２）　@プロパティのセット<BR>
     * 　@生成した先物OP当日新規建注文情報に下記プロパティセットを行う。<BR>
     * 　@
     * 　@・注文単位ID：　@引数.注文単位Params.注文単位ID<BR>
     * 　@・銘柄ID：　@引数.注文単位Params.銘柄ID<BR>
     * 　@・市場ID：　@引数.注文単位Params.市場ID<BR>
     * 　@・建区分：　@<BR>
     * 　@　@　@- 引数.注文単位Params.注文種別 == ”先物新規買建注文”、または、”　@OP新規買建注文”の場合、”買建”。<BR>
     * 
     *       - 以外、”売建”。<BR>
     * 
     * 　@・先物／オプション区分：　@引数.注文単位Params.先物／オプション区分<BR>
     * 　@・発注日：　@引数.注文単位Params.発注日<BR>
     * 　@・受渡日：　@引数.注文単位Params.受渡日<BR>
     * 　@・注文数量：<BR>
     * 　@　@　@- 未約定の場合<BR>
     * 　@　@　@　@(引数.注文単位Params.getExecutedQuantityIsNull == true)、<BR>
     * 　@　@　@　@　@　@引数.注文単位Params.注文数量<BR>
     * 
     * 　@　@　@- 一部約定の場合<BR>
     * 　@　@　@　@(引数.注文単位Params.getExecutedQuantityIsNull == false)、<BR>
     * 　@　@　@　@　@　@引数.注文単位.注文Params数量 - 注文単位Params.約定数量<BR>
     * 
     * 　@・オプション概算受渡代金：　@オプションの場合(引数.注文単位Params.注文カテゴリ == ”OP新規建注文”)のみ設定する<BR>
     * 　@　@　@　@- 買建の場合(建区分 == ”買建”)、引数.注文単位Params.概算受渡代金のマイナス値<BR>
     * 
     * 　@　@　@　@- 売建の場合(建区分 == ”売建”)、引数.注文単位Params.概算受渡代金<BR>
     * 
     * ３）　@プロパティセットした先物OP当日新規建注文情報を返却する。<BR>
     * 
     * @@param l_orderUnitParams - 注文単位Params。
     * @@return webbroker3.ifodeposit.WEB3IfoTodayOpenOrder
     * @@roseuid 413D68610215
     */
    public static WEB3IfoTodayOpenOrder getIfoTodayOpenOrder(IfoOrderUnitParams l_orderUnitParams)
    {

        // 建区分
        ContractTypeEnum l_contractType = ContractTypeEnum.SHORT;
        if (OrderTypeEnum
            .IDX_FUTURES_BUY_TO_OPEN
            .equals(l_orderUnitParams.getOrderType())
            || OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(
                l_orderUnitParams.getOrderType()))
        {
            l_contractType = ContractTypeEnum.LONG;
        }

        // 発注日
        Date l_orderBizDate = null;
        try
        {
            l_orderBizDate =
                GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd").parse(
                    l_orderUnitParams.getBizDate());
        } 
        catch(ParseException pe)
        {
            pe.printStackTrace();
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    "WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(IfoOrderUnitParams)",
                    pe.getMessage(), pe);
        }

        // 数量
        double l_quantity = l_orderUnitParams.getQuantity();
        if (!l_orderUnitParams.getExecutedQuantityIsNull())
        {
            l_quantity -= l_orderUnitParams.getExecutedQuantity();
        }

        // オプション概算受渡代金
        double l_estimatedNetAmount = 0.0;
        if (OrderCategEnum
            .IDX_OPTIONS_OPEN
            .equals(l_orderUnitParams.getOrderCateg()))
        {
            if (ContractTypeEnum.LONG.equals(l_contractType))
            {
                l_estimatedNetAmount = l_orderUnitParams.getEstimatedPrice() * -1;
            } 
            else if (ContractTypeEnum.SHORT.equals(l_contractType))
            {
                l_estimatedNetAmount = l_orderUnitParams.getEstimatedPrice();
            }
        }

        WEB3IfoTodayOpenOrder l_order = WEB3IfoTodayOpenOrder.create();
        l_order.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
        l_order.setProductId(l_orderUnitParams.getProductId());
        l_order.setMarketId(l_orderUnitParams.getMarketId());
        l_order.setContractType(l_contractType);
        l_order.setIfoProductType(l_orderUnitParams.getFutureOptionDiv());
        l_order.setOrderBizDate(l_orderBizDate);
        l_order.setDeliveryDate(l_orderUnitParams.getDeliveryDate());
        l_order.setQuantity(l_quantity);
        l_order.setOptionEstimatedNetAmount(l_estimatedNetAmount);

        return l_order;
    }

    /**
     * (get先物OP当日新規建注文情報)<BR>
     * 
     * （staticメソッド）<BR>
     * 引数の先物OP現注文内容より、先物OP当日新規建注文情報オブジェクトを作成する。<BR>
     * 
     * １）　@オブジェクトの生成<BR>
     * 　@先物OP当日新規建注文情報オブジェクトを生成する。<BR>
     * 
     * ２）　@プロパティのセット<BR>
     * 　@生成した先物OP当日新規建注文情報に下記プロパティセットを行う。<BR>
     * 　@
     * 　@・注文単位ID：　@引数.先物OP現注文内容.注文単位ID<BR>
     * 　@・銘柄ID：　@引数.先物OP現注文内容.銘柄ID<BR>
     * 　@・市場ID：　@引数.先物OP現注文内容.市場ID<BR>
     * 　@・建区分：　@引数.先物OP現注文内容.建区分<BR>
     * 　@・先物／オプション区分：　@<BR>
     * 　@　@　@- 引数.先物OP現注文内容.先物オプション商品 == ”先物”の場合、”先物”。<BR>
     * 　@　@　@- 以外、”オプション”。<BR>
     * 　@・発注日：　@引数.先物OP現注文内容.発注日<BR>
     * 　@・受渡日：　@引数.先物OP現注文内容.受渡日<BR>
     * 　@・注文数量：　@引数.先物OP現注文内容.注文数量<BR>
     * 　@・オプション概算受渡代金：　@オプションの場合(引数.先物OP現注文内容.先物オプション商品 != ”先物”)のみ設定する<BR>
     * 　@　@　@　@- 買建の場合(建区分 == ”買建”)、引数.先物OP現注文内容.概算受渡代金のマイナス<BR>
     * 　@　@　@　@- 売建の場合(建区分 == ”売建”)、引数.先物OP現注文内容.概算受渡代金<BR>
     * 
     * ３）　@プロパティセットした先物OP当日新規建注文情報を返却する。<BR>
     * 　@
     * @@param l_ifoNewOrderSpec - 先物OP現注文内容。
     * @@return webbroker3.ifodeposit.WEB3IfoTodayOpenOrder
     * @@roseuid 413D468C0021
     */
    public static WEB3IfoTodayOpenOrder getIfoTodayOpenOrder(WEB3IfoNewOrderSpec l_ifoNewOrderSpec)
    {
        ContractTypeEnum l_contractType = l_ifoNewOrderSpec.contractType;
        String l_ifoProductType = WEB3FuturesOptionDivDef.OPTION;
        if (IfoDerivativeTypeEnum
            .FUTURES
            .equals(l_ifoNewOrderSpec.ifoDerivativeType))
        {
            l_ifoProductType = WEB3FuturesOptionDivDef.FUTURES;
        }
        double l_dblEstimatedNetAmount = 0.0;
        if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoProductType))
        {
            if (ContractTypeEnum.LONG.equals(l_contractType))
            {
                l_dblEstimatedNetAmount = l_ifoNewOrderSpec.estimatedNetAmount * -1;
            } 
            else if (ContractTypeEnum.SHORT.equals(l_contractType))
            {
                l_dblEstimatedNetAmount = l_ifoNewOrderSpec.estimatedNetAmount;
            }
        }

        WEB3IfoTodayOpenOrder l_order = WEB3IfoTodayOpenOrder.create();
        l_order.setOrderUnitId(l_ifoNewOrderSpec.orderUnitId);
        l_order.setProductId(l_ifoNewOrderSpec.productId);
        l_order.setMarketId(l_ifoNewOrderSpec.marketId);
        l_order.setContractType(l_contractType);
        l_order.setIfoProductType(l_ifoProductType);
        l_order.setOrderBizDate(l_ifoNewOrderSpec.orderBizDate);
        l_order.setDeliveryDate(l_ifoNewOrderSpec.deliveryDate);
        l_order.setQuantity(l_ifoNewOrderSpec.quantity);
        l_order.setOptionEstimatedNetAmount(l_dblEstimatedNetAmount);

        return l_order;
    }

    /**
     * (getオプション買建概算受渡代金)<BR>
     * 
     * (this.is買建 == true && this.受渡日 
     * ==　@引数.受渡日)の場合のみ、this.オプション概算受渡代金を返却する。<BR>
     * 以外、0を返却する。<BR>
     * @@param l_datDeliveryDate - 受渡日。
     * @@return double
     * @@roseuid 4124A5A1039F
     */
    public double getOptionBuyEstimatedNetAmount(Date l_datDeliveryDate)
    {
        if (isBuy()
            && (WEB3DateUtility.compareToDay(getDeliveryDate(), l_datDeliveryDate)
                == 0))
        {
            return getOptionEstimatedNetAmount();
        } else
        {
            return 0.0;
        }
    }

    /**
     * (subtractオプション概算受渡代金)<BR>
     * 
     * this.オプション概算受渡代金から引数の受渡代金を減算する。 <BR>
     * 
     * this.オプション概算受渡代金 = this.オプション概算受渡代金 - 引数.受渡代金<BR>
     * @@param l_dblNetAmount - 受渡代金
     * @@roseuid 4129D4D3037C
     */
    public void subtractOptionEstimatedNetAmount(double l_dblNetAmount)
    {
        double l_dblSubtracted = getOptionEstimatedNetAmount() - l_dblNetAmount;
        setOptionEstimatedNetAmount(l_dblSubtracted);
    }

    /**
     * (is買建)<BR>
     * 
     * 該当注文が買建であるかを判定する。<BR>
     * 
     * this.建区分==”買建”の場合、trueを返却する。以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 4117094701B0
     */
    public boolean isBuy()
    {
        if (ContractTypeEnum.LONG.equals(getContractType()))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * (is先物)<BR>
     * 
     * 該当注文が先物であるかを判定する。<BR>
     * 
     * this.先物／オプション区分 == 
     * ”先物”の場合、trueを返却する。以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 4129DB850298
     */
    public boolean isFutures()
    {
        if (WEB3FuturesOptionDivDef.FUTURES.equals(getIfoProductType()))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * 建区分を取得する。
     * 
     * @@return　@建区分
     */
    public ContractTypeEnum getContractType()
    {
        return contractType;
    }

    /**
     * 受渡日を取得する。
     * 
     * @@return　@受渡日
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * 先物オプション区分を取得する。
     * 
     * @@return　@先物オプション区分
     */
    public String getIfoProductType()
    {
        return ifoProductType;
    }

    /**
     * 市場IDを取得する。
     * 
     * @@return　@市場ID
     */
    public long getMarketId()
    {
        return marketId;
    }

    /**
     * オプション概算受渡代金を取得する。
     * 
     * @@return　@オプション概算受渡代金
     */
    public double getOptionEstimatedNetAmount()
    {
        return optionEstimatedNetAmount;
    }

    /**
     * 発注日を取得する。
     * 
     * @@return　@発注日
     */
    public Date getOrderBizDate()
    {
        return orderBizDate;
    }

    /**
     * 注文単位IDを取得する。
     * 
     * @@return　@注文単位ID
     */
    public long getOrderUnitId()
    {
        return orderUnitid;
    }

    /**
     * 銘柄IDを取得する。
     * 
     * @@return　@銘柄ID
     */
    public long getProductId()
    {
        return productId;
    }

    /**
     * 数量を取得する。
     * 
     * @@return　@数量
     */
    public double getQuantity()
    {
        return quantity;
    }

    /**
     * 建区分を設定する。
     * 
     * @@param l_contractType　@建区分
     */
    public void setContractType(ContractTypeEnum l_contractType)
    {
        contractType = l_contractType;
    }

    /**
     * 受渡日を設定する。
     * 
     * @@param l_datDeliveryDate　@受渡日
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        deliveryDate = l_datDeliveryDate;
    }

    /**
     * 先物オプション区分を設定する。
     * 
     * @@param l_strIfoProductType　@先物オプション区分
     */
    public void setIfoProductType(String l_strIfoProductType)
    {
        ifoProductType = l_strIfoProductType;
    }

    /**
     * 市場IDを設定する。
     * 
     * @@param l_lngMarketId　@市場ID
     */
    public void setMarketId(long l_lngMarketId)
    {
        marketId = l_lngMarketId;
    }

    /**
     * オプション概算受渡代金を設定する。
     * 
     * @@param l_dblOptionEstimatedNetAmount　@オプション概算受渡代金
     */
    public void setOptionEstimatedNetAmount(double l_dblOptionEstimatedNetAmount)
    {
        optionEstimatedNetAmount = l_dblOptionEstimatedNetAmount;
    }

    /**
     * 発注日を設定する。
     * 
     * @@param l_datOrderBizDate　@発注日
     */
    public void setOrderBizDate(Date l_datOrderBizDate)
    {
        orderBizDate = l_datOrderBizDate;
    }

    /**
     * 注文単位IDを設定する。
     * 
     * @@param l_lngOrderUnitId　@注文単位ID
     */
    public void setOrderUnitId(long l_lngOrderUnitId)
    {
        orderUnitid = l_lngOrderUnitId;
    }

    /**
     * 銘柄IDを設定する。
     * 
     * @@param l_lngProductId　@銘柄ID
     */
    public void setProductId(long l_lngProductId)
    {
        productId = l_lngProductId;
    }

    /**
     * 数量を設定する。
     * 
     * @@param l_dblQuantity　@数量
     */
    public void setQuantity(double l_dblQuantity)
    {
        quantity = l_dblQuantity;
    }

    /**
     * WEB3IfoTodayOpenOrderの文字列表現を返す。
     * 
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3IfoTodayOpenOrder={");
        l_sb.append("orderUnitId=").append(getOrderUnitId());
        l_sb.append(",productId=").append(getProductId());
        l_sb.append(",marketId=").append(getMarketId());
        l_sb.append(",contractType=").append(getContractType());
        l_sb.append(",orderBizDate=").append(getOrderBizDate());
        l_sb.append(",deliveryDate=").append(getDeliveryDate());
        l_sb.append(",ifoProductType=").append(getIfoProductType());
        l_sb.append(",quantity=").append(getQuantity());
        l_sb.append(",optionEstimatedNetAmount=").append(
            getOptionEstimatedNetAmount());
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
