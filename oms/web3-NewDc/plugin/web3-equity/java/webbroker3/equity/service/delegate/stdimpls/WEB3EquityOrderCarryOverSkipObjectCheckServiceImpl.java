head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文繰越スキップ銘柄通知繰越対象チェックサービスImpl
                       (WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/31 鄒政 (中訊) 新規作成
                   2004/11/03 法@旭　@修正 
                   2004/12/13 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;


import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipObjectCheckService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文繰越スキップ銘柄通知繰越対象チェックサービスImpl）。
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl
    implements WEB3EquityOrderCarryOverSkipObjectCheckService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl.class);

    /**
     * @@roseuid 40B2E8FD020F
     */
    public WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl()
    {

    }

    /**
     * (is繰越注文単位)<BR>
     * 引数の注文単位が繰越対象であるかどうかを判定する。<BR>
     * （当メソッドは市場閉局後にコールされる。）<BR>
     * <BR>
     * 引数の注文単位.発注日＝取引時間管理.get発注日( )の前営業日(*1)
     * <BR>
     * かつ　@引数の注文単位.注文失効日≧取引時間管理.get発注日( )(*2)　@の場合は、<BR>
     * 注文繰越対象であると判定しtrueを返す。<BR>
     * 上記以外の場合は、falseを返す。<BR>
     * <BR>
     * (*1)当日発注の注文<BR>
     * (*2)翌日以降まで有効な注文<BR>
     * @@param l_orderUnit - 注文単位オブジェクト。
     * @@throws WEB3SystemLayerException
     * @@return boolean
     * @@roseuid 406A51E501D0
     */
    public boolean isCarryOverOrderUnit(OrderUnit l_orderUnit)
    throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isCarryOverOrderUnit(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        EqtypeOrderUnitRow l_eqOrderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();

        //get注文単位.発注日
        String l_strOrderUnitBizDate = l_eqOrderUnitRow.getBizDate();

        //get注文単位.注文失効日
        Date l_orderUnitExpirationDate = l_eqOrderUnitRow.getExpirationDate();
        String l_strExpirationDate = WEB3DateUtility.formatDate(l_orderUnitExpirationDate, "yyyyMMdd");

        //get取引時間管理.get発注日
        Date l_orderBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        String l_strOrderBizDate = WEB3DateUtility.formatDate(l_orderBizDate, "yyyyMMdd");

        //get取引時間管理.get発注日( )の前営業日
        WEB3GentradeBizDate l_dateCalc =
             new WEB3GentradeBizDate(new Timestamp(l_orderBizDate.getTime()));
        Date l_prevBizDate = l_dateCalc.roll(-1);
        String l_strPrevBizDate = WEB3DateUtility.formatDate(l_prevBizDate, "yyyyMMdd");
        
        log.debug("get注文単位.発注日 = " + l_strOrderUnitBizDate);
        log.debug("get注文単位.注文失効日 = " + l_strExpirationDate);
        log.debug("get取引時間管理.get発注日 = " + l_strOrderBizDate);
        log.debug("get取引時間管理.get発注日( )の前営業日 = " + l_strPrevBizDate);
        
        boolean l_result;
        if ((l_strOrderUnitBizDate.compareTo(l_strPrevBizDate) == 0)
            && (l_strExpirationDate.compareTo(l_strOrderBizDate) >= 0))
        {
            l_result = true;
        }
        else
        {
            l_result = false;
        }
        log.debug("l_result = " + l_result);
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
}
@
