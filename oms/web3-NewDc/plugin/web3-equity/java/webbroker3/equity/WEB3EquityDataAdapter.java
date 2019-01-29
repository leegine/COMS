head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityDataAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式データアダプタ(WEB3EquityDataAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/03/02 景山 (SRA)
Revesion History : 2006/07/04 肖志偉 (中訊) 仕様変更モデル943
Revesion History : 2006/08/29 柴雙紅 (中訊) 仕様変更モデル970
Revesion History : 2006/11/01 肖志偉 (中訊) 仕様変更モデル951
Revesion History : 2006/11/07 柴双紅 (中訊) 仕様変更モデルNo.990,No.994,No.996,No.1007,No.1015,No.1047,No.1049
Revesion History : 2006/11/07 柴双紅 (中訊) 仕様変更モデルNo.1055,No.1059
Revesion History : 2006/11/28 唐性峰 (中訊) 仕様変更モデルNo.1076
Revesion History : 2007/06/05 何文敏 (中訊) 仕様変更モデルNo.1164
Revesion History : 2007/07/24 何文敏 (中訊) 仕様変更モデルNo.1188
Revesion History : 2007/10/10 何文敏 (中訊) 仕様変更モデルNo.1194
*/

package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3TriggerOrderStatusDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.equity.define.WEB3EquityAcceptTypeDef;
import webbroker3.equity.define.WEB3EquityDelayDivDef;
import webbroker3.equity.define.WEB3EquityExecStatusTypeDef;
import webbroker3.equity.define.WEB3EquityExpirationStatusDef;
import webbroker3.equity.define.WEB3EquityOrderSpecTypeDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （株式データアダプタ）。<BR>
 * <BR>
 * 株式注文のデータ変換を行うクラス。 <BR>
 * <BR>
 * @@version 1.0
 */

public class WEB3EquityDataAdapter
{
    
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityDataAdapter.class);

    /**
     * (get取引区分)<BR>
     * <BR>
     * 引数の注文種別より、PR層で使用する取引区分を返却する。<BR>
     *<BR>
     *１）パラメータ.注文種別により分岐し、対応する値を返却する。<BR>  
     *<BR>
     *　@パラメータ.注文種別が、<BR>  
     *　@[OrderTypeEnum.現物買付注文の場合]<BR>  
     *　@　@"現物買付注文"を返却する。<BR>  
     *　@[OrderTypeEnum.現物売付注文の場合]<BR>  
     *　@　@"現物売付注文"を返却する。<BR>  
     *　@[OrderTypeEnum.新規買建注文の場合]<BR>  
     *　@　@"新規買建注文"を返却する。  <BR>
     *　@[OrderTypeEnum.新規売建注文の場合]<BR>  
     *　@　@"新規売建注文"を返却する。  <BR>
     *　@[OrderTypeEnum.買建返済注文の場合]<BR>  
     *　@　@"買建返済注文"を返却する。  <BR>
     *　@[OrderTypeEnum.売建返済注文の場合]<BR>  
     *　@　@"売建返済注文"を返却する。<BR>  
     *　@[OrderTypeEnum.現引注文の場合] <BR> 
     *　@　@"現引注文"を返却する。  <BR>
     *　@[OrderTypeEnum.現渡注文の場合]<BR>  
     *　@　@"現渡注文"を返却する。<BR>  
     *　@[上記以外の場合]  <BR>
     *　@　@nullを返却する。  <BR>
     *<BR>
     * @@param l_orderUnit
     * @@return
     * @@throws WEB3BaseException
     */
    public static String getTradingType(OrderTypeEnum l_orderType)
    {
        final String STR_METHOD_NAME =
            " getTradingType(OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTradingType = null;

        //[OrderTypeEnum.現物買付注文の場合]  
        //　@"現物買付注文"を返却する。
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.EQUITY_BUY);

        //[OrderTypeEnum.現物売付注文の場合]  
        //　@"現物売付注文"を返却する。
        } else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.EQUITY_SELL);

        //[OrderTypeEnum.新規買建注文の場合]  
        //　@"新規買建注文"を返却する。
        } else if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MARGIN_LONG);

        //[OrderTypeEnum.新規売建注文の場合]  
        //　@"新規売建注文"を返却する。
        } else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MARGIN_SHORT);
        //　@[OrderTypeEnum.買建返済注文の場合]  
        //　@　@"買建返済注文"を返却する。
        } else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.CLOSE_MARGIN_LONG);
        //[OrderTypeEnum.売建返済注文の場合]  
        //　@"売建返済注文"を返却する。
        } else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.CLOSE_MARGIN_SHORT);

        //[OrderTypeEnum.現引注文の場合] 
        //　@"現引注文"を返却する。
        } else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.SWAP_MARGIN_LONG);

        //[OrderTypeEnum.現渡注文の場合]  
        //　@"現渡注文"を返却する。
        } else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            l_strTradingType =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.SWAP_MARGIN_SHORT);

        //[上記以外の場合]
        // nullを返却する。
        } else
        {
            l_strTradingType = null;
        }

        log.exiting(STR_METHOD_NAME);

        return l_strTradingType;
    }
    
    /**
     * (get注文状態区分)<BR>
     * 指定された注文単位のPR層で使用する注文状態区分を返却する。  <BR>
     * <BR>
     * (1)引数の.注文単位を株式注文単位型にキャストする。<BR>
     * <BR>
     * (2)拡張株式注文マネージャ.is出来るまで注文単位(引数の注文単位)の戻り値 == true、<BR>
     * 　@　@　@かつ　@引数の注文単位.注文失効日付≧業務日付（GtlUtils.getTradingSystem().getBizDate()）、<BR>
     * 　@　@　@かつ　@注文単位.注文有効状態＝CLOSED（クローズ）、<BR>
     * 　@　@　@かつ　@注文単位.失効区分＝EXPIRED（失効済）、<BR>
     * 　@　@　@かつ　@注文単位.注文エラー理由コード≠"0000：正常"の場合、"繰越失敗"を返す。<BR>
     * <BR>
     * (3)引数の注文単位.isUnexecuted( ) == true、 <BR>
     * 　@　@　@かつ　@注文単位.注文有効状態＝CLOSED（クローズ）、 <BR>
     * 　@　@　@かつ　@注文単位.失効区分＝INVALIDATED_BY_MKT（マーケット拒否）の場合、<BR>
     * 　@　@　@"全部失効"を返す。  <BR>
     * <BR>
     * (4)引数の注文単位.isPartiallyExecuted( ) == true、 <BR>
     * 　@　@　@かつ　@注文単位.注文有効状態＝CLOSED（クローズ）、  <BR>
     * 　@　@　@かつ　@注文単位.失効区分 ==＝INVALIDATED_BY_MKT（マーケット拒否）の場合、<BR>
     * 　@　@　@"一部失効"を返す。  <BR>
     * <BR>
     * (5)引数の注文単位.注文有効状態＝CLOSED（クローズ）、  <BR>
     * 　@　@　@かつ　@注文単位.失効区分＝EXPIRED（失効済）の場合、"無効"を返す。 <BR>
     * 　@　@　@※出来終了通知で注文が失効した場合 <BR>
     * <BR>
     * (6)拡張株式注文マネージャ.is繰越注文単位(引数の注文単位)の戻り値 == true、<BR>
     * 　@　@　@かつ　@注文単位.注文状態＝ACCEPTED（受付済（新規注文））の場合、"繰越済"を返す。<BR>
     * <BR>
     * (7)注文単位.注文状態＝MODIFY_ACCEPTED（受付済(変更注文)）<BR>
     * 　@　@　@かつ　@注文単位.注文訂正・取消区分＝"W指値注文切替中"の場合、"切替注文"を返す。<BR>
     * <BR>
     * (8)注文単位.注文状態＝MODIFYING（発注中(変更注文)）<BR>
     * 　@　@　@かつ　@注文単位.注文訂正・取消区分＝"W指値注文切替中"の場合、"切替受付"を返す。<BR>
     * <BR>
     * (9)注文単位.注文状態＝MODIFIED（発注済み(変更注文)）<BR>
     * 　@　@　@かつ　@注文単位.注文訂正・取消区分＝"W指値注文一部切替完了"、"W指値注文全部切替完了"の場合、<BR>
     * 　@　@　@"切替完了"を返す。<BR>
     * <BR>
     * (10)注文単位.注文状態＝NOT_MODIFIED（発注失敗(変更注文)）<BR>
     * 　@　@　@かつ　@注文単位.注文訂正・取消区分＝"W指値注文切替失敗"の場合、"切替注文(失敗)"を返す。<BR>
     * <BR>
     * (11)上記以外の場合は、注文単位.注文状態.intValueを文字列で返す。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 株式注文単位<BR>
     * @@return String<BR>
     */
    public static String getOrderState(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderState(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_orderStatus = null;
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        //注文単位.注文状態
        OrderStatusEnum l_orderStatusEnum = l_orderUnitRow.getOrderStatus();
        //注文単位.注文訂正・取消区分
        String l_strModifyCancelType = l_orderUnitRow.getModifyCancelType();

        //(2)拡張株式注文マネージャ.is出来るまで注文単位(引数の注文単位)の戻り値 == true、
        //  かつ　@引数の注文単位.注文失効日付≧業務日付（GtlUtils.getTradingSystem().getBizDate()）、
        //　@かつ　@注文単位.注文有効状態＝CLOSED（クローズ）、
        //　@かつ　@注文単位.失効区分＝EXPIRED（失効済）、
        //　@かつ　@注文単位.注文エラー理由コード≠"0000：正常"の場合、"繰越失敗"を返す。
        if (l_orderManager.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit) &&
             (WEB3DateUtility.compareToDay(
             l_orderUnit.getExpirationTimestamp(), GtlUtils.getTradingSystem().getBizDate()) >= 0) &&
             OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()) &&
             OrderExpirationStatusEnum.EXPIRED.equals(l_orderUnit.getExpirationStatus()) &&
            !WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED.equals(l_orderUnitRow.getErrorReasonCode()) &&
            !WEB3ErrorReasonCodeDef.NORMAL.equals(l_orderUnitRow.getErrorReasonCode()))
        {
            l_orderStatus = WEB3OrderStatusDef.NOT_TRANSFERED;
        }
        
        //引数の注文単位.isUnexecuted( ) == true、
        //かつ　@注文単位.注文有効状態＝CLOSED（クローズ）、
        //かつ　@注文単位.失効区分＝INVALIDATED_BY_MKT（マーケット拒否）の場合、
        //"全部失効"を返す。
        else if (l_orderUnit.isUnexecuted() &&
                  OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()) &&
                  OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
        {
            l_orderStatus = WEB3OrderStatusDef.FULL_INAFFECTED;
        }
        
        //引数の注文単位.isPartiallyExecuted( ) == true、
        //かつ　@注文単位.注文有効状態＝CLOSED（クローズ） 
        //かつ　@注文単位.失効区分 ==＝INVALIDATED_BY_MKT（マーケット拒否）の場合、
        //"一部失効"を返す。
        else if (l_orderUnit.isPartiallyExecuted() &&
                  OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()) &&
                  OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
        {
            l_orderStatus = WEB3OrderStatusDef.PART_INAFFECTED;
        }
        
        //引数の注文単位.注文有効状態＝CLOSED（クローズ）、
        //かつ　@注文単位.失効区分＝EXPIRED（失効済）の場合、"無効"を返す。
        //※出来終了通知で注文が失効した場合 
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()) &&
                  OrderExpirationStatusEnum.EXPIRED.equals(l_orderUnit.getExpirationStatus()))
        {
            l_orderStatus = WEB3OrderStatusDef.CLOSED;
        }

        //拡張株式注文マネージャ.is繰越注文単位(引数の注文単位)の戻り値 == true、
        //かつ　@注文単位.注文状態＝ACCEPTED（受付済（新規注文））の場合、"繰越済"を返す。
        else if (l_orderManager.isCarryOverOrderUnit((EqTypeOrderUnit)l_orderUnit) && 
                  OrderStatusEnum.ACCEPTED.equals(l_orderStatusEnum))
        {            
            l_orderStatus = WEB3OrderStatusDef.TRANSFERED;            
        }

        //(7)注文単位.注文状態＝MODIFY_ACCEPTED（受付済(変更注文)）
        //　@　@かつ　@注文単位.注文訂正・取消区分＝"W指値注文切替中"の場合、"切替注文"を返す。
        else if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatusEnum) &&
            WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(l_strModifyCancelType))
        {
            l_orderStatus = WEB3OrderStatusDef.TRANSFER_ORDER;
        }

        //(8)注文単位.注文状態＝MODIFYING（発注中(変更注文)）
        //  かつ　@注文単位.注文訂正・取消区分＝"W指値注文切替中"の場合、"切替受付"を返す。
        else if (OrderStatusEnum.MODIFYING.equals(l_orderStatusEnum) &&
            WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(l_strModifyCancelType))
        {
            l_orderStatus = WEB3OrderStatusDef.TRANSFER_ACCEPT;
        }

        //(9)注文単位.注文状態＝MODIFIED（発注済み(変更注文)）
        //かつ　@注文単位.注文訂正・取消区分＝"W指値注文一部切替完了"、"W指値注文全部切替完了"の場合、
        //"切替完了"を返す。
        else if (OrderStatusEnum.MODIFIED.equals(l_orderStatusEnum) &&
            (WEB3ModifyCancelTypeDef.W_LIMIT_PARTIALLY_TRANSFERED.equals(l_strModifyCancelType)
            || WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERED.equals(l_strModifyCancelType)))
        {
            l_orderStatus = WEB3OrderStatusDef.TRANSFERRED;
        }

        //(10)注文単位.注文状態＝NOT_MODIFIED（発注失敗(変更注文)）
        // かつ　@注文単位.注文訂正・取消区分＝"W指値注文切替失敗"の場合、"切替注文(失敗)"を返す。
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderStatusEnum) &&
            WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR.equals(l_strModifyCancelType))
        {
            l_orderStatus = WEB3OrderStatusDef.TRANSFER_ORDER_FAIL;
        }
        //(11)上記以外の場合は、注文単位.注文状態.intValueを文字列で返す。
        else
        {
            l_orderStatus = Integer.toString(l_orderUnit.getOrderStatus().intValue());
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderStatus;
    }


    /**
     * (get約定状態区分)<BR>
     * パラメータ.注文単位より、PR層で使用する約定状態区分を返却する。<BR>
     * <BR>
     * １）パラメータ.注文単位の保持するデータにより分岐し<BR>
     * 　@　@対応する約定状態区分を返却する。<BR>
     * <BR>
     * 　@１－１）パラメータ.注文単位.isPartiallyExecuted( ) == trueの場合は、<BR>
     * 　@　@　@　@　@"一部成立"を返す。<BR>
     * <BR>
     * 　@１－２）パラメータ.注文単位.isFullyExecuted( ) == trueの場合は、<BR>
     * 　@　@　@　@　@"全部成立"を返す。<BR>
     * <BR>
     * 　@１－３）上記以外の場合は、"未約定"を返す。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * @@return String
     */
    public static String getExecType(EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "getExecType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_result;

        //パラメータ.注文単位.isPartiallyExecuted( ) == trueの場合は、
        //"一部成立"を返す。
        if (l_orderUnit.isPartiallyExecuted())
        {
            l_result = WEB3EquityExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE;
        }
        
        //パラメータ.注文単位.isFullyExecuted( ) == trueの場合は、
        //"全部成立"を返す。
        else if (l_orderUnit.isFullyExecuted())
        {
            l_result = WEB3EquityExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE;
        }
        
        //上記以外の場合は、"未約定"を返す。
        else
        {
            l_result = WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * (get逆指値発注状況区分)<BR>
     * パラメータ.注文単位より<BR>
     * PR層で使用する逆指値注文の発注状況区分を返却する。<BR>
     * <BR>
     * <BR>
     * １）　@発注状況区分の判定<BR>
     * <BR>
     * 　@１－１）　@発注遅延エラーの判定<BR>
     * 　@　@拡張株式注文マネージャ.is未発注遅延注文(注文単位) == true の場合、<BR>
     * 　@　@"発注遅延エラー"を返却する。<BR>
     * <BR>
     * 　@１－2）　@待機@中の判定<BR>
     * 　@　@注文単位.リクエストタイプ＝"DEFAULT"の場合、"待機@中"を返却する。<BR>
     * <BR>
     * 　@１－３）　@発注中、発注完了の判定<BR>
     * 　@　@注文単位.リクエストタイプ＝"時価サーバ"の場合、<BR>
     * 　@　@－注文単位.市場から確認済みの数量＝null の場合、"発注中"を返却する。<BR>
     * 　@　@－以外、"発注完了"を返却する。<BR>
     * <BR>
     * 　@１－４）　@発注審査エラーの判定<BR>
     * 　@　@注文単位.リクエストタイプ＝"発注失敗"の場合、"発注審査エラー"を返却する。<BR>
     * <BR>
     * 　@１－５）　@上記以外の場合、"その他"を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 株式注文単位<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getStopTriggerOrderStatusType(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getStopTriggerOrderStatusType(EquityOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTriggerOrderStatus = null;
        EqtypeOrderUnitRow l_orderUnitRow =
             (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //拡張株式注文マネージャを取得
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        //　@１－１）　@発注遅延エラーの判定 
        //　@拡張株式注文マネージャ.is未発注遅延注文(注文単位) == true の場合、 
        //　@"発注遅延エラー"を返却する。
        boolean l_blnNotOrderedDelay =
            l_equityOrderManager.isNotOrderedDelayOrder(l_orderUnit);
        if (l_blnNotOrderedDelay)
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR;
        }

        //１－２）　@待機@中の判定
        else if (WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType()))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_WAITING;
        }
        //１－３）　@発注中、発注完了の判定
        else if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_orderUnitRow.getRequestType()))
        {
            if (l_orderUnitRow.getConfirmedQuantityIsNull())
            {
                l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDERING;
            }
            else
            {
                l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_COMPLETE;
            }
        }
        //１－４）　@発注審査エラーの判定
        else if (WEB3RequestTypeDef.ORDER_FAILURE.equals(l_orderUnitRow.getRequestType()))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_VALIDATE_ERROR;
        }
        //１－５）　@上記以外の場合、"その他"を返却する。
        else
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.OTHER;
        }
        log.debug("逆指値発注状況区分 = " + l_strTriggerOrderStatus);

        log.exiting(STR_METHOD_NAME);
        
        return l_strTriggerOrderStatus;
    }

    /**
     * (getＷ指値用執行条件一覧)<BR>
     * 引数の執行条件一覧より、  <BR>
     * Ｗ指値用の執行条件一覧を作成し返却する。<BR>
     * <BR>
     * [引数の発注条件一覧に"Ｗ指値"が含まれていない場合]  <BR>
     * 　@　@nullを返却する。 <BR>
     * <BR>
     * [引数の執行条件一覧に"不出来引け成行"が含まれる場合]<BR>
     * 　@　@以下の執行条件を要素とする配列を返却する。 <BR>
     * 　@　@・"無条件"  <BR>
     * 　@　@・"不出来引け成行"  <BR>
     * [上記以外の場合]  <BR>
     * 　@"無条件"のみを要素とする配列を返却する。<BR>
     * @@param l_strExecutionConditionTypeList - (執行条件一覧)<BR>
     * 執行条件一覧  <BR>
     * ※以下の値から構成される配列  <BR>
     *  <BR>
     * 1：無条件  <BR>
     * 3：寄付  <BR>
     * 4：引け  <BR>
     * 7：不出来引け成行<BR>
     * @@param l_strOrderConditionList - (発注条件一覧)<BR>
     * 発注条件一覧 <BR>
     * ※以下の値から構成される配列 <BR>
     * <BR>
     * 0：DEFAULT（条件指定なし）<BR>
     * 1：逆指値 <BR>
     * 2：W指値<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public static String[] getWLimitExecutionConditionTypeList(
        String[] l_strExecutionConditionTypeList,
        String[] l_strOrderConditionList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitExecutionConditionTypeList(String[], String[])";
        log.entering(STR_METHOD_NAME);

        if (l_strExecutionConditionTypeList == null
            || l_strExecutionConditionTypeList.length == 0
            || l_strOrderConditionList == null
            || l_strOrderConditionList.length == 0)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        int l_intTypeListLength = l_strExecutionConditionTypeList.length;
        int l_intConditionLisLength = l_strOrderConditionList.length;
        boolean l_blnReturn = true;
        for (int i = 0; i < l_intConditionLisLength; i++)
        {
            //[引数の発注条件一覧に"Ｗ指値"が含まれていない場合]
            //　@nullを返却する。
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionList[i]))
            {
                l_blnReturn = false;
                break;
            }
        }
        if (l_blnReturn)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        for (int i = 0; i < l_intTypeListLength; i++)
        {
            //[引数の執行条件一覧に"不出来引け成行"が含まれる場合]
            //　@以下の執行条件を要素とする配列を返却する。
            //　@・"無条件"
            //　@・"不出来引け成行"
            if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(
                l_strExecutionConditionTypeList[i]))
            {
                String[] l_strWLimitExecutionConditionTypeList =
                    {WEB3ExecutionConditionDef.NO_CONDITION,
                     WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED};
                log.exiting(STR_METHOD_NAME);
                return l_strWLimitExecutionConditionTypeList;
            }
        }

        //[上記以外の場合]
        //"無条件"のみを要素とする配列を返却する。
        String[] l_strWLimitExecutionConditionTypeList =
            {WEB3ExecutionConditionDef.NO_CONDITION};
        log.exiting(STR_METHOD_NAME);
        return l_strWLimitExecutionConditionTypeList;
    }

    /**
     * (getＷ指値用執行条件一覧)<BR>
     * （getW指値用執行条件一覧のオーバーロードメソッド） <BR>
     * 引数の注文単位、執行条件一覧より、  <BR>
     * Ｗ指値用の執行条件一覧を作成し返却する。  <BR>
     * <BR>
     * １）　@引数の注文単位.発注条件が"Ｗ指値"でない場合、<BR>
     * 　@　@　@nullを返却する。 <BR>
     * <BR>
     * ２）　@引数の注文単位が出来るまで注文  <BR>
     * 　@（拡張株式注文マネージャ.is出来るまで注文単位(引数の注文単位) == true）の場合、<BR>
     * 　@"無条件"のみを要素とする配列を返却する。  <BR>
     * <BR>
     * ３）　@上記以外の場合、  <BR>
     * 　@this.getW指値用執行条件一覧(引数の執行条件一覧,引数の注文単位.発注条件)メソッドを<BR>
     * 　@コールし、戻り値を返却する。<BR>
     * @@param l_strExecutionConditionTypeList - (執行条件一覧)<BR>
     * 執行条件一覧  <BR>
     * ※以下の値から構成される配列  <BR>
     *  <BR>
     * 1：無条件  <BR>
     * 3：寄付  <BR>
     * 4：引け  <BR>
     * 7：不出来引け成行<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public static String[] getWLimitExecutionConditionTypeList(
        String[] l_strExecutionConditionTypeList,
        EqTypeOrderUnit l_eqTypeOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitExecutionConditionTypeList(String[], EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //１）　@引数の注文単位.発注条件が"Ｗ指値"でない場合、
        //　@nullを返却する。
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strOrderCondetionType =
            l_orderUnitRow.getOrderConditionType();
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@引数の注文単位が出来るまで注文
        //　@（拡張株式注文マネージャ.is出来るまで注文単位(引数の注文単位) == true）の場合、
        //　@"無条件"のみを要素とする配列を返却する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager.isCarriedOrderUnit(l_eqTypeOrderUnit))
        {
            String[] l_strWLimitExecutionConditionTypeList =
                {WEB3ExecutionConditionDef.NO_CONDITION};
            log.exiting(STR_METHOD_NAME);
            return l_strWLimitExecutionConditionTypeList;
        }

        //３）　@上記以外の場合、
        //　@this.getW指値用執行条件一覧(引数の執行条件一覧,引数の注文単位.発注条件)メソッドを
        //　@コールし、戻り値を返却する。
        String[] l_strWLimitExecutionConditionTypeList =
            WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
                l_strExecutionConditionTypeList,
                new String[]{l_strOrderCondetionType});

        log.exiting(STR_METHOD_NAME);
        return l_strWLimitExecutionConditionTypeList;
    }

    /**
     * （get商品区分）<BR>
     * <BR>
     * 引数の注文単位よりPR層で使用する商品区分を返却する。<BR>  
     *<BR>
     *１）以下の条件により分岐し、対応する商品区分を返却する。<BR>  
     *<BR>
     *　@[パラメータ.注文単位.注文カテゴリ == "現物注文"　@かつ<BR>  
     *　@  パラメータ.注文単位.注文種別 ==<BR>  
     *　@　@　@("現物買注文" or "現物売注文")の場合]<BR>  
     *　@　@　@"現物株式"を返却する。  <BR>
     *<BR>
     *　@[パラメータ.注文単位.注文カテゴリ ==<BR>  
     *　@　@　@("新規建注文" or "返済注文" or "現引現渡注文")の場合]<BR>  
     *　@　@　@"信用取引"を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return String
     */
    public static String getProductType(EqTypeOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME = "getProductType()";
        log.entering(STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = null;
        String l_strWEB3ProductTypeDef = null;
        l_eqtypeOrderUnitParams = (EqtypeOrderUnitParams) l_orderUnit.getDataSourceObject();

        // [パラメータ.注文単位.注文カテゴリ == "現物注文"の場合]
        if (l_eqtypeOrderUnitParams.order_categ.equals(OrderCategEnum.ASSET))
        {
            //[パラメータ.注文単位.注文種別 ==
            //    ("現物買注文" or "現物売注文")の場合]
            //    "現物株式"を返却する。
            if ((l_eqtypeOrderUnitParams.order_type.equals(OrderTypeEnum.
                            EQUITY_BUY))
             || (l_eqtypeOrderUnitParams.order_type.equals(OrderTypeEnum.
                            EQUITY_SELL)))
            {
                l_strWEB3ProductTypeDef = WEB3CommodityDivDef.EQUITY;
            }
        }
        
        //[パラメータ.注文単位.注文カテゴリ ==
        //        ("新規建注文" or "返済注文" or "現引現渡注文")の場合]
        //        "信用取引"を返却する。
        if ((l_eqtypeOrderUnitParams.order_categ.equals(OrderCategEnum.OPEN_MARGIN))
         || (l_eqtypeOrderUnitParams.order_categ.equals(OrderCategEnum.CLOSE_MARGIN))
         || (l_eqtypeOrderUnitParams.order_categ.equals(OrderCategEnum.SWAP_MARGIN)))
        {
            l_strWEB3ProductTypeDef = WEB3CommodityDivDef.MARGIN;
        }
        log.exiting(STR_METHOD_NAME);
        return l_strWEB3ProductTypeDef;
    }
    
    /**
     * (get処理状況区分)<BR>
     * 指定された注文単位の処理状況区分を返す。<BR> 
     * <BR>
     * 戻り値の処理状況区分：<BR> 
     * ※コード値はメッセージ定義フォルダ以下の<BR> 
     * 「ﾒｯｾｰｼﾞ定義書_信用取引(共通).xls」の処理状況区分定義を参照。<BR> 
     * <BR>
     * (1)受付区分を判定する。<BR> 
     * 　@this.get受付区分()をコールする。<BR> 
     *<BR> 
     * (2)this.get約定状態区分(パラメータ.注文単位)をコールし、約定区分を取得する。<BR> 
     *<BR> 
     * (3) 失効区分を判定する。<BR> 
     * 　@　@this.get注文状態区分(パラメータ.注文単位)をコールする。<BR> 
     * 　@　@　@・this.get注文状態区分()＝”一部失効”の場合は、”一部失効”。<BR> 
     * 　@　@　@・this.get注文状態区分()＝”全部失効”の場合は、”全部失効”。<BR> 
     * 　@　@　@・上記以外の場合は、”失効なし”。<BR> 
     *<BR> 
     * (4) (1)で取得した受付区分=="受付エラー"の場合は、"受付エラー"を処理区分として返す。<BR> 
     * 　@　@上記以外の場合は、(1)、(2)、(3)の取得値、及び注文単位.注文訂正・取消区分より、<BR> 
     * 　@　@該当する処理状況区分を返却する。<BR> 
     * 　@　@組み合わせ表に記載のない組み合わせの場合も、その文字列をそのまま返却する。<BR> 
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return String
     */
    public static String getTransactionStatusType(EqTypeOrderUnit l_orderUnit)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTransactionStatusType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        log.debug("処理対象注文ID:[" + l_orderUnit.getOrderId() + "]");

        //受付区分
        String l_strAcceptType = getAcceptType(l_orderUnit);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //約定区分
        String l_strExecType = getExecType(l_orderUnit);
        
        //失効区分
        String l_strExpirationType;        
        String l_strOrderStatusType = getOrderState(l_orderUnit);
        if (WEB3OrderStatusDef.PART_INAFFECTED.equals(l_strOrderStatusType))
        {
            l_strExpirationType = WEB3EquityExpirationStatusDef.EXPIRATION_TYPE_ONE_COMPLETE;
        }
        else if (WEB3OrderStatusDef.FULL_INAFFECTED.equals(l_strOrderStatusType))
        {
            l_strExpirationType = WEB3EquityExpirationStatusDef.EXPIRATION_TYPE_ALL_COMPLETE;
        }
        else
        {
            l_strExpirationType = WEB3EquityExpirationStatusDef.EXPIRATION_TYPE_NOT_PROMISE;
        }
        
        //注文単位.注文訂正・取消区分
        String l_strModifyCancelType = l_orderUnitRow.getModifyCancelType();
        
        //処理状況区分
        String l_strReturn = null;
        
        //受付区分が受付エラーの場合                         
        if (WEB3EquityAcceptTypeDef.EXEC_TYPE_ERROR.equals(l_strAcceptType))                           
        {                           
            l_strReturn = l_strAcceptType 
            + WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE 
            + WEB3EquityExpirationStatusDef.EXPIRATION_TYPE_NOT_PROMISE
            + WEB3ModifyCancelTypeDef.INITIAL_VALUE; 
        }
        else
        {                                                   
            l_strReturn = l_strAcceptType
            + l_strExecType 
            + l_strExpirationType 
            + l_strModifyCancelType;
        }
        
        log.debug("処理状況区分 = " + l_strReturn);
        
        log.exiting(STR_METHOD_NAME);                           
        
        return l_strReturn;
    }
    
    /**
     * (get受付区分)<BR>
     * 指定された注文単位の受付区分を返す。<BR>
     * ※取引カレンダコンテキストを設定の上、呼び出すこと。<BR>
     * <BR>
     * 戻り値の受付区分：<BR>
     * 0:受付未済　@1:受付済　@2:受付エラー  4:発注待ち　@6:翌営業日注文　@7:発注未処理<BR>
     * <BR>
     * １）　@以下条件全てに該当する場合のみ処理する。<BR>
     * 　@　@　@　@・注文単位.発注日 ≧ 取引時間管理.get発注日()<BR>
     * 　@　@　@　@・市場閉局中　@（取引時間管理.is市場開局時間帯()が"false"の場合）<BR>
     * <BR>
     *　@１－１）　@注文単位.発注日 ＞ 業務日付の場合<BR>
     *　@　@　@　@　@　@　@"翌営業日注文"を返却する。<BR>
     * <BR>
     *　@１－２）　@取引時間管理.get営業日区分(現在日時)が"非営業日"の場合<BR>
     *　@　@　@　@　@　@　@"翌営業日注文"を返却する。<BR>
     * <BR>
     *　@１－３）　@注文単位.発注日 ＝ 業務日付の場合<BR>
     *　@　@　@　@　@　@ - 取引時間管理.isオンラインサービス開始後()＝falseの場合<BR>
     *　@　@　@　@　@　@　@　@　@　@"翌営業日注文"を返却する。<BR>
     *　@　@　@　@　@　@ - 取引時間管理.isオンラインサービス開始後()＝trueかつ、<BR>
     *　@　@　@　@　@　@　@  現在日時の時間部分 ＜ 市場閉局時間（※）の場合<BR>
     *　@　@　@　@　@　@　@　@　@　@"発注未処理"を返却する。<BR>
     * <BR>
     * 　@　@　@　@　@　@ （※）市場閉局時間<BR>
     * 　@　@　@　@　@　@　@　@①@ 拡張金融オブジェクトマネージャ.get市場()をコールする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@[get市場()に設定する引数]<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@注文単位Row.getMarketId()<BR>
     * <BR>
     *　@　@ 　@　@　@　@　@　@② 取引時間管理.get市場閉局時間()をコールする。<BR>
     *　@　@　@ 　@　@　@　@　@　@　@　@[get市場閉局時間()に設定する引数]<BR>
     *　@　@　@　@ 　@　@　@　@　@　@　@市場コード：　@get市場()の戻り値.get市場コード()<BR>
     *　@　@　@　@　@ 　@　@　@　@　@　@商品コード：　@"0：DEFAULT"<BR>
     * <BR>
     * ２）　@未発注の有効な逆指値注文で市場開局時間帯の場合<BR>
     * <BR>
     * 　@注文単位.発注条件＝"逆指値"、かつ、<BR>
     * 　@注文単位.リクエストタイプ＝"DEFAULT"、かつ、<BR>
     * 　@注文単位.注文有効状態＝"オープン"、かつ、<BR>
     * 　@取引時間管理.is市場開局時間帯＝trueの場合、"発注待ち"を返却する。<BR>
     * <BR>
     * 3 ）　@未発注注文の場合<BR>
     * <BR>
     * 　@注文単位.市場から確認済の数量＝NaNの場合、<BR>
     * 　@ - 注文単位.注文状態＝"発注失敗(新規注文)"の場合は、"受付エラー"、<BR>
     * 　@ - 注文単位.注文状態≠"発注失敗(新規注文)"の場合は、"受付未済"を返却する。<BR>
     * <BR>
     * 4 ）　@上記以外の場合は、"受付済"を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getAcceptType(EqTypeOrderUnit l_orderUnit)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAcceptType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_strAcceptType = null;

        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        try
        {
            //取引時間管理.get発注日()
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();        
            Date l_datOrderUnitBizDate = WEB3DateUtility.getDate(
                                            l_orderUnitRow.getBizDate(), 
                                            WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                                            
            //以下条件全てに該当する場合のみ処理する。
            //　@・注文単位.発注日 ≧ 取引時間管理.get発注日()
            //　@・市場閉局中　@（取引時間管理.is市場開局時間帯()が"false"の場合）
            if ((WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datOrderBizDate) >= 0) &&
                !(WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone()))
            {
                //業務日付取得
                Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
                
                //現在日時取得
                Timestamp l_tsSysTimestamp = GtlUtils.getSystemTimestamp();
                String l_strBizDateType = 
                    WEB3GentradeTradingTimeManagement.getBizDateType(l_tsSysTimestamp);
                
                //注文単位.発注日 ＞ 業務日付の場合
                if (WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datBizDate) > 0)
                {
                    l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_NEXT_ORDER;
                    return l_strAcceptType;
                }                
                //取引時間管理.get営業日区分(現在日時)が"非営業日"の場合    
                else if (l_strBizDateType.compareTo(WEB3BizDateTypeDef.NO_BIZ_DATE) == 0)
                {
                    l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_NEXT_ORDER;
                    return l_strAcceptType;
                }            
                //注文単位.発注日 ＝ 業務日付の場合
                else if (WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datBizDate) == 0)
                {
                    //取引時間管理.isオンラインサービス開始後()＝falseの場合
                    if (!(WEB3GentradeTradingTimeManagement.isOnlineServiceStartAfter()))
                    {
                        l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_NEXT_ORDER;
                        return l_strAcceptType;
                    }
                    
                    //市場を取得する
                    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                    WEB3GentradeFinObjectManager l_finObjManager =
                        (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                    WEB3GentradeMarket l_market = null;                

                    l_market = 
                        (WEB3GentradeMarket)l_finObjManager.getMarket(
                                                l_orderUnitRow.getMarketId());
                    
                    //市場閉局時間を取得する
                    String l_strTradeCloseTime = 
                        WEB3GentradeTradingTimeManagement.getTradeCloseTime(
                            l_market.getMarketCode(), 
                            WEB3MarketCodeDef.DEFAULT);
                    
                    String l_strBizDate = 
                        GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datBizDate);
                    
                    Date l_datTradeCloseTime = WEB3DateUtility.getDate(
                            l_strBizDate + l_strTradeCloseTime,
                            "yyyyMMddHHmmss");
    
                    //取引時間管理.isオンラインサービス開始後()＝trueかつ
                    //現在日時の時間部分 ＜ 取引時間管理.get市場閉局時間()の場合
                    if (WEB3GentradeTradingTimeManagement.isOnlineServiceStartAfter() &&
                        WEB3DateUtility.compareToSecond(l_tsSysTimestamp, l_datTradeCloseTime) <= 0)
                    {
                        l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_UNORDER;
                        return l_strAcceptType;                      
                    }                
                }
            }
    
            //未発注の有効な逆指値注文で市場開局時間帯の場合        
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()) &&
                WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType()) &&
                OrderOpenStatusEnum.OPEN.equals(l_orderUnitRow.getOrderOpenStatus()) &&
                WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
            {
                l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_WAITING;
            }

            //未発注注文の場合
            else if (l_orderUnitRow.getConfirmedQuantityIsNull())
            {
                if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_ERROR;
                }
                else
                {
                    l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_NAN;
                }
            }
            else
            {
                l_strAcceptType = WEB3EquityAcceptTypeDef.EXEC_TYPE_NOT_NAN;
            }
            
            return l_strAcceptType;
            
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3EquityDataAdapter.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        finally
        {
            log.debug("受付区分 = " + l_strAcceptType);
            
            log.exiting(STR_METHOD_NAME);
            
            return l_strAcceptType;
        }                
    }
    
    /**
     * (get注文内容区分)<BR>
     * 指定された注文履歴の注文内容区分を返す。<BR>
     * <BR>
     * ※参考までに、注文履歴の項目とPR層に返す「注文内容区分」との対応は、<BR>
     * 「信用取引・注文データ更新仕様.xls」の「注文履歴登録仕様」シートを参照。<BR>
     * <BR>
     * 戻り値の注文内容区分：<BR>
     * 00：新規注文 01：注文受付 02：新規注文(失敗) 03：訂正注文<BR>
     * 04：訂正受付 05：訂正完了 06：訂正注文(失敗) 07：取消注文<BR>
     * 08：取消受付 09：取消完了 10：取消注文(失敗) 11：一部約定<BR>
     * 12：全部約定 13：約定取消 14：失効 15：失効取消 16：無効<BR>
     * 17：注文繰越 18：注文繰越(失敗) 19：約定取消(現引現渡)<BR>
     * 20：発注中 21：発注遅延<BR>
     * 22：切替遅延 23：切替注文 24：切替受付 25：切替完了<BR>
     * 26：切替注文(失敗) 27：ストップ注文失効 29：強制失効 30：承認済 99：その他<BR>
     * --------------------------------------------------------------------------<BR>
     * (1)約定<BR>
     * 　@注文履歴.注文イベントタイプ＝EXECUTE(約定)の場合、<BR>
     * 　@・注文履歴.注文数量＝注文履歴.約定数量ならば、"全部約定"を返す。<BR>
     * 　@・上記以外ならば、"一部約定"を返す。<BR>
     * <BR>
     * (2)強制失効<BR>
     * 　@注文単位.強制失効区分 == "強制失効済"かつ<BR>
     * 　@注文履歴.注文イベントタイプ＝EXPIRE(失効済み) かつ<BR>
     * 　@注文履歴.注文失効ステータス＝INVALIDATED_BY_MKT(マーケット拒否)の場合、<BR>
     * "強制失効"を返す。<BR>
     * <BR>
     * (3)失効<BR>
     * 　@注文履歴.注文イベントタイプ＝EXPIRE(失効済み) かつ<BR>
     * 　@注文履歴.注文失効ステータス＝INVALIDATED_BY_MKT(マーケット拒否)の場合、<BR>
     * "失効"を返す。<BR>
     * <BR>
     * (4)無効、注文繰越(失敗)<BR>
     * 　@注文履歴.注文イベントタイプ＝EXPIRE(失効済み) かつ<BR>
     * 　@注文履歴.注文失効ステータス＝EXPIRED(終了)の場合、<BR>
     * 　@・注文履歴.注文エラー理由コード≠"0000：正常"ならば、"注文繰越(失敗)"を返す。<BR>
     * 　@・上記以外であれば、"無効"を返す。<BR>
     * <BR>
     * (5)ストップ注文失効<BR>
     * 　@注文履歴.注文イベントタイプ＝EXPIRE(失効済み) かつ<BR>
     * 　@注文履歴.リクエストタイプ＝"失効"の場合、"ストップ注文失効"を返す。<BR>
     * <BR>
     * (6)失効取消<BR>
     * 　@注文履歴.注文イベントタイプ＝UNDO_INVALIDATION_BY_MKT(失効取消)の場合、<BR>
     * "失効取消"を返す。<BR>
     * <BR>
     * (7)約定取消、約定取消(現引現渡)<BR>
     * 　@注文履歴.注文イベントタイプ＝UNDO_EXECUTION(約定取消)の場合、<BR>
     * 　@・注文単位.注文カテゴリ＝SWAP_MARGIN(現引現渡注文)ならば、"約定取消(現引現渡)"を返す。<BR>
     * 　@・上記以外ならば、"約定取消"を返す。<BR>
     * <BR>
     * (8)発注遅延<BR>
     * 　@注文履歴.注文イベントタイプ＝ORDER_DELAY（発注遅延）の場合、<BR>
     * 　@"発注遅延"を返す。<BR>
     * <BR>
     * (9)切替遅延<BR>
     * 　@注文履歴.注文イベントタイプ＝SWITCH_DELAY（切替遅延）の場合、<BR>
     * 　@"切替遅延"を返す。<BR>
     * <BR>
     * (10)承認済<BR>
     * 　@注文履歴.注文イベントタイプ＝APPROVED（承認済）の場合、<BR>
     * 　@"承認済"を返す。<BR>
     * <BR>
     * (11)新規注文、注文繰越<BR>
     * 　@注文履歴.注文状態＝ACCEPTED(受付済(新規注文))の場合、<BR>
     * 　@・拡張株式注文マネージャ.is繰越注文単位(注文単位.注文単位ID) == false（＝初回注文）ならば、<BR>
     * 　@　@"新規注文"を返す。<BR>
     * 　@・上記以外ならば、"注文繰越"を返す。<BR>
     * <BR>
     * (12)注文受付<BR>
     * 　@注文履歴.注文状態＝ORDERED(発注済み（新規注文))の場合、"注文受付"を返す。<BR>
     * <BR>
     * (13)新規注文(失敗)<BR>
     * 　@注文履歴.注文状態＝NOT_ORDERED(発注失敗(新規注文))の場合、<BR>
     * 　@　@"新規注文(失敗)"を返す。<BR>
     * <BR>
     * (14)訂正注文、切替注文<BR>
     * 　@注文履歴.注文状態＝MODIFY_ACCEPTED(受付済(変更注文))の場合、<BR>
     * 　@　@・注文履歴.訂正・取消区分＝"W指値注文切替中"の場合、"切替注文"を返す。<BR>
     * 　@　@・上記以外の場合、"訂正注文"を返す。<BR>
     * <BR>
     * (15)訂正受付、切替受付<BR>
     * 　@注文履歴.注文状態＝MODIFYING(発注中(変更注文))の場合、<BR>
     * 　@　@・注文履歴.訂正・取消区分＝"W指値注文切替中"の場合、"切替受付"を返す。<BR>
     * 　@　@・上記以外の場合、"訂正受付"を返す。<BR>
     * <BR>
     * (16)訂正完了、切替完了<BR>
     * 　@注文履歴.注文状態＝MODIFIED(発注済み(変更注文))の場合、<BR>
     * 　@　@・注文履歴.訂正・取消区分＝（"W指値注文一部切替完了"または"W指値注文全部切替完了"）の場合、<BR>
     * 　@　@　@"切替完了"を返す。<BR>
     * 　@　@・上記以外の場合、"訂正完了"を返す。<BR>
     * <BR>
     * (17)訂正注文(失敗)、切替注文(失敗)<BR>
     * 　@注文履歴.注文状態＝NOT_MODIFIED(発注失敗(変更注文))の場合、<BR>
     * 　@　@・注文履歴.訂正・取消区分＝"W指値注文切替失敗"の場合、"切替注文(失敗)"を返す。<BR>
     * 　@　@・上記以外の場合、"訂正注文(失敗)"を返す。<BR>
     * <BR>
     * (18)取消注文<BR>
     * 　@注文履歴.注文状態＝CANCEL_ACCEPTED(受付済(取消注文))の場合、"取消注文"を返す。<BR>
     * <BR>
     * (19)取消受付<BR>
     * 　@注文履歴.注文状態＝CANCELLING(発注中(取消注文))の場合、"取消受付"を返す。<BR>
     * <BR>
     * (20)取消完了<BR>
     * 　@注文履歴.注文状態＝CANCELLED(発注済み(取消注文))の場合、"取消完了"を返す。<BR>
     * <BR>
     * (21)取消注文(失敗)<BR>
     * 　@注文履歴.注文状態＝NOT_CANCELLED(発注失敗(取消注文))の場合、"取消注文(失敗)"を返す。<BR>
     * <BR>
     * (22)発注中<BR>
     * 　@注文履歴.注文イベントタイプ＝SEND_TO_MKT（マーケット送信済（新規注文））の場合、<BR>
     * 　@"発注中"を返す。<BR>
     * <BR>
     * (23)上記以外の場合、"その他"を返す。 <BR>
     * --------------------------------------------------------------------------<BR>
     * <BR>
     * @@param l_orderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return java.lang.String
     * @@throws WEB3BaseException
     */
    public static String getOrderType(
        EqTypeOrderAction l_orderAction,
        EqTypeOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOrderType(EqTypeOrderAction, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_orderSpecType = null;
        // 注文単位.強制失効区分
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strForcedExpireType = l_orderUnitRow.getForcedExpireType();
        OrderEventTypeEnum l_orderEventType = l_orderAction.getOrderEventType();
        OrderStatusEnum l_orderStatus = l_orderAction.getOrderStatus();
        OrderExpirationStatusEnum l_orderExpirationStatus = l_orderAction.getExpirationStatus();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqtypeOrderActionRow l_orderActionRow =
            (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
        //注文履歴.訂正・取消区分
        String l_strModifyCancelType = l_orderActionRow.getModifyCancelType();
        
        if (OrderEventTypeEnum.EXECUTE.equals(l_orderEventType))
        {
            if (l_orderAction.getQuantity() == l_orderAction.getExecutionQuantity())
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.FULL_EXECUTE;
            }
            else if (l_orderAction.getExecutionQuantity() != 0.0D)
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.PART_EXECUTE;
            }
        }
        // (2)強制失効
        // 注文単位.強制失効区分 == "強制失効済"かつ
        // 注文履歴.注文イベントタイプ＝EXPIRE(失効済み) かつ
        // 注文履歴.注文失効ステータス＝INVALIDATED_BY_MKT(マーケット拒否)の場合
        else if (WEB3ForcedExpireType.FORCED_EXPIRED.equals(l_strForcedExpireType) &&
            OrderEventTypeEnum.EXPIRE.equals(l_orderEventType) &&
            OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderExpirationStatus))
        {
            // 強制失効
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.FORCED_EXPIRE;
        }
        else if (OrderEventTypeEnum.EXPIRE.equals(l_orderEventType) &&
                  OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderExpirationStatus))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.EXPIRE;
        }
        else if (OrderEventTypeEnum.EXPIRE.equals(l_orderEventType) &&
                  OrderExpirationStatusEnum.EXPIRED.equals(l_orderExpirationStatus))
        {
            if (!WEB3ErrorReasonCodeDef.NORMAL.equals(l_orderActionRow.getErrorReasonCode()))
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.ORDER_CARRY_OVER_REJECT;
            }
            else
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.INVALID;
            }
        }
        //(4)ストップ注文失効
        //注文履歴.注文イベントタイプ＝EXPIRE(失効済み) かつ
        //注文履歴.リクエストタイプ＝"失効"の場合、"ストップ注文失効"を返す。
        else if (OrderEventTypeEnum.EXPIRE.equals(l_orderEventType) &&
            WEB3RequestTypeDef.INVALIDATE.equals(l_orderActionRow.getRequestType()))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.STOP_ORDER_EXPIRE;
        }
        else if (OrderEventTypeEnum.UNDO_INVALIDATION_BY_MKT.equals(l_orderEventType))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.UNDO_EXPIRE;
        }
		else if (OrderEventTypeEnum.UNDO_EXECUTION.equals(l_orderEventType))
		{
			if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
			{
				l_orderSpecType = WEB3EquityOrderSpecTypeDef.UNDO_EXECUTE_SWAP;
			}
			else
			{
				l_orderSpecType = WEB3EquityOrderSpecTypeDef.UNDO_EXECUTE;
			}
		}
        else if (OrderEventTypeEnum.ORDER_DELAY.equals(l_orderEventType))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.ORDER_DELAY;
        }
        //(8)切替遅延
        //注文履歴.注文イベントタイプ＝SWITCH_DELAY（切替遅延）の場合、
        //"切替遅延"を返す。
        else if (OrderEventTypeEnum.SWITCH_DELAY.equals(l_orderEventType))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.SWITCH_DELAY;
        }
        // (10)承認済
        // 注文履歴.注文イベントタイプ＝APPROVED（承認済）の場合、
        // "承認済"を返す。
        else if (OrderEventTypeEnum.APPROVED.equals(l_orderEventType))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.APPROVED;
        }
        else if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus))
        {
            boolean isCarriyOverOrderUnit =
                l_orderManager.isCarryOverOrderUnit(l_orderUnit);
            if (!isCarriyOverOrderUnit)
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.NEW_ORDER;
            }
            else
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.ORDER_CARRY_OVER;
            }
        }
        else if (OrderStatusEnum.ORDERED.equals(l_orderStatus))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.NEW_ORDER_ACCEPT;
        }
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderStatus))
        {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.NEW_ORDER_REJECT;
        }
        //(12)訂正注文、切替注文
        //注文履歴.注文状態＝MODIFY_ACCEPTED(受付済(変更注文))の場合、
        //・注文履歴.訂正・取消区分＝"W指値注文切替中"の場合、"切替注文"を返す。
        //・上記以外の場合、"訂正注文"を返す。
        else if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(l_strModifyCancelType))
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.SWITCH_ORDER;
            }
            else
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.CHANGE;
            }
        }
        //(13)訂正受付、切替受付
        //注文履歴.注文状態＝MODIFYING(発注中(変更注文))の場合、
        //・注文履歴.訂正・取消区分＝"W指値注文切替中"の場合、"切替受付"を返す。
        //・上記以外の場合、"訂正受付"を返す。
        else if (OrderStatusEnum.MODIFYING.equals(l_orderStatus))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(l_strModifyCancelType))
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.SWITCH_ACCEPT;
            }
            else
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.CHANGE_ACCEPT;
            }
        }
        //(14)訂正完了、切替完了
        //注文履歴.注文状態＝MODIFIED(発注済み(変更注文))の場合、
        //・注文履歴.訂正・取消区分＝（"W指値注文一部切替完了"または"W指値注文全部切替完了"）の場合、
        //"切替完了"を返す。
        //・上記以外の場合、"訂正完了"を返す。
        else if (OrderStatusEnum.MODIFIED.equals(l_orderStatus))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_PARTIALLY_TRANSFERED.equals(l_strModifyCancelType)
                || WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERED.equals(l_strModifyCancelType))
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.SWITCH_OVER;
            }
            else
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.CHANGE_COMPLETE;
            }
        }
        //(15)訂正注文(失敗)、切替注文(失敗)
        //注文履歴.注文状態＝NOT_MODIFIED(発注失敗(変更注文))の場合、
        //・注文履歴.訂正・取消区分＝"W指値注文切替失敗"の場合、"切替注文(失敗)"を返す。
        //・上記以外の場合、"訂正注文(失敗)"を返す。
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderStatus))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR.equals(l_strModifyCancelType))
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.SWITCH_ORDER_FAIL;
            }
            else
            {
                l_orderSpecType = WEB3EquityOrderSpecTypeDef.CHANGE_REJECT;
            }
        }
        else if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.CANCEL;
        }
        else if (OrderStatusEnum.CANCELLING.equals(l_orderStatus))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.CANCEL_ACCEPT;
        }
        else if (OrderStatusEnum.CANCELLED.equals(l_orderStatus))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.CANCEL_COMPLETE;
        }
        else if (OrderStatusEnum.NOT_CANCELLED.equals(l_orderStatus))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.CANCEL_REJECT;
        }
        else if (OrderEventTypeEnum.SEND_TO_MKT.equals(l_orderEventType))
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.NEW_ORDER_ORDERING;
        }
        else
        {
            l_orderSpecType = WEB3EquityOrderSpecTypeDef.OTHER;
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderSpecType;
    }

    /**
     * (get受付結果区分)<BR>
     * 指定された注文履歴の受付結果区分を返す。<BR>
     * <BR>
     * 注文履歴の項目と、PR層に返す「受付結果区分」との対応は、<BR>
     * 「現物株式・注文データ更新仕様.xls」の「注文履歴登録仕様」シートを参照。<BR>
     * <BR>
     * 戻り値の受付結果区分：<BR>
     * 0000：正常 1001：受付エラー 1002：訂正エラー 1003：取消エラー 1004：切替エラー<BR>
     * 0001：値幅エラー 0002：預り金不足エラー 0003：株式残高不足エラー 0004：保証金不足エラー<BR>
     * 0005：建株残高不足エラー 0006：売買停止銘柄エラー 0007：市場変更銘柄エラー<BR>
     * 0008：買付余力エラー 0009：売付可能数量エラー 0010：特定口座エラー<BR>
     * 0011：注文繰越スキップ銘柄エラー<BR>
     * 9001：その他エラー<BR>
     * <BR>
     * --------------------------------------------------------------------------<BR>
     * ・注文履歴.注文訂正・取消区分＝"4"（取消失敗）の場合<BR>
     * <BR>
     * 　@"1003"（取消エラー）を返す。<BR>
     * <BR>
     * ・注文履歴.注文訂正・取消区分＝"8"（訂正失敗）の場合<BR>
     * <BR>
     * 　@"1002"（訂正エラー）を返す。<BR>
     * <BR>
     * ・注文履歴.注文訂正・取消区分＝"D"（W指値注文切替失敗）の場合<BR>
     * <BR>
     * 　@"1004"（切替エラー）を返す。<BR>
     * <BR>
     * ・注文履歴.注文状態＝NOT_ORDERED（発注失敗（新規注文））<BR>
     * 　@かつ　@注文履歴.注文イベントタイプ＝REJECTED_BY_MARKET（マーケット拒否（新規注文））<BR>
     * 　@の場合<BR>
     * <BR>
     * 　@"1001"（受付エラー）を返す。<BR>
     * <BR>
     * ・上記以外の場合<BR>
     * <BR>
     * 　@注文履歴.注文エラー理由コード を返す。<BR>
     * --------------------------------------------------------------------------<BR>
     * <BR>
     * @@param l_orderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト。<BR>
     * @@return java.lang.String
     */
    public static String getAcceptedResultDiv(EqTypeOrderAction l_orderAction)
    {
        final String STR_METHOD_NAME = "getAcceptedResultDiv(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderActionRow l_orderActionRow =
            (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
        String l_result = null;
        
        // ・注文履歴.注文訂正・取消区分＝"4"（取消失敗）の場合
        if (WEB3ModifyCancelTypeDef.CANCEL_ERROR.equals(l_orderActionRow.getModifyCancelType()))
        {
            l_result = WEB3ErrorReasonCodeDef.CANCEL_ERROR;
        }
        // ・注文履歴.注文訂正・取消区分＝"8"（訂正失敗）の場合
        else if (WEB3ModifyCancelTypeDef.CHANGE_ERROR.equals(l_orderActionRow.getModifyCancelType()))
        {
            l_result = WEB3ErrorReasonCodeDef.CHANGE_ERROR;
        }
        //・注文履歴.注文訂正・取消区分＝"D"（W指値注文切替失敗）の場合
        //"1004"（切替エラー）を返す。
        else if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR.equals(l_orderActionRow.getModifyCancelType()))
        {
            l_result = WEB3ErrorReasonCodeDef.TRANSFER_ERROR;
        }
        // ・注文履歴.注文状態＝NOT_ORDERED（発注失敗（新規注文））
        // 　@かつ　@注文履歴.注文イベントタイプ＝REJECTED_BY_MARKET（マーケット拒否（新規注文））
        // 　@の場合
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderAction.getOrderStatus()) &&
                  OrderEventTypeEnum.REJECTED_BY_MKT.equals(l_orderAction.getOrderEventType()))
        {
            l_result = WEB3ErrorReasonCodeDef.ACCEPT_ERROR;
        }
        // ・上記以外の場合
        else
        {
            l_result = l_orderActionRow.getErrorReasonCode();
        }

        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * （get口座区分）<BR>
     * <BR>
     * 引数の税区分よりPR層で使用する口座区分を返却する。<BR>  
     * <BR>
     * TaxTypeEnum.NORMAL == パラメータ.税区分の場合、<BR> 
     * 　@WEB3TaxTypeDef.NORMAL（"一般口座"）を返す。<BR> 
     * <BR>
     * TaxTypeEnum.STOCK_OPTION == パラメータ.税区分の場合、<BR>
     * 　@WEB3TaxTypeDef.STOCK_OPTION（"ストックオプション口座"）を返す。<BR>
     * <BR>
     * TaxTypeEnum.SPECIAL == パラメータ.税区分 <BR> 
     * 　@または、TaxTypeEnum.SPECIAL_WITHHOLD == パラメータ.税区分の場合、<BR> 
     * 　@　@WEB3TaxTypeDef.SPECIAL（"特定口座"）を返す。 <BR>
     * <BR>
     * @@param l_taxTypeEnum - (税区分)<BR>
     * 税区分<BR>
     * @@return String
     */
    public static String getTaxType(TaxTypeEnum l_taxTypeEnum)
    {
        final String STR_METHOD_NAME = " getTaxType(TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTaxType = null;
    	if(TaxTypeEnum.NORMAL.equals(l_taxTypeEnum))
    	{
    	    l_strTaxType = WEB3TaxTypeDef.NORMAL;
    	}
    	else if(TaxTypeEnum.STOCK_OPTION.equals(l_taxTypeEnum))
    	{
          l_strTaxType = WEB3TaxTypeDef.STOCK_OPTION;
    	}
    	else if(TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum) ||
            TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeEnum))
    	{    		
    	    l_strTaxType = WEB3TaxTypeDef.SPECIAL;
    	}
    	
    	log.exiting(STR_METHOD_NAME);
    	return l_strTaxType;
    }
    
    /**
     * (get発注条件)<BR>
     * 引数の発注条件、元発注条件の内、<BR> 
     * nullでない方の発注条件を返却する。<BR> 
     * <BR>
     * [パラメータ.元発注条件 != nullの場合] <BR>
     * 　@パラメータ.元発注条件を返却する。<BR> 
     * <BR>
     * [パラメータ.発注条件 != nullの場合] <BR>
     * 　@パラメータ.発注条件を返却する。<BR>
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_strOrgOrderConditionType - (元発注条件)<BR>
     * 元発注条件<BR>
     * @@return String
     * 
     */
    public static String getOrderConditionType(
        String l_strOrderConditionType, String l_strOrgOrderConditionType)
    {
        final String STR_METHOD_NAME = "getOrderConditionType(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ.元発注条件 != nullの場合
        if (l_strOrgOrderConditionType != null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_strOrgOrderConditionType;
        }
        
        //パラメータ.発注条件 != nullの場合
        if (l_strOrderConditionType != null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_strOrderConditionType;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
        
    }
    
    /**
     * (get発注条件演算子)<BR>
     * 引数の発注条件演算子、元発注条件演算子の内、 <BR>
     * 有効な発注条件演算子を返却する。 <BR>
     * <BR>
     * [パラメータ.元発注条件演算子 != nullの場合] <BR>
     * 　@パラメータ.元発注条件演算子を返却する。 <BR>
     * <BR>
     * [パラメータ.発注条件演算子 != nullの場合] <BR>
     * 　@パラメータ.発注条件演算子を返却する。<BR>
     * @@param l_strOrderCondOperator - (発注条件演算子)<BR>
     * 発注条件演算子<BR>
     * @@param l_strOrgOrderCondOperator - (元発注条件演算子)<BR>
     * 元発注条件演算子<BR>
     * @@return String
     */
    public static String getOrderCondOperator(
        String l_strOrderCondOperator, String l_strOrgOrderCondOperator)
    {
        final String STR_METHOD_NAME = "getOrderCondOperator(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ.元発注条件演算子 != nullの場合
        if (l_strOrgOrderCondOperator != null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_strOrgOrderCondOperator;
        }
        
        //パラメータ.発注条件演算子 != nullの場合
        if (l_strOrderCondOperator != null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_strOrderCondOperator;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get発注条件演算子)<BR>
     * 引数の注文単位より、有効な発注条件演算子を返却する。<BR> 
     * <BR>
     * １）　@発注条件指定なしの場合 <BR>
     * <BR>
     * this.get発注条件(）＝"DEFAULT（条件指定なし）"の場合、<BR> 
     * nullを返却する。 <BR>
     * <BR>
     * [get発注条件()に指定する引数] <BR>
     * 　@発注条件：　@注文単位.発注条件 <BR>
     * 　@元発注条件：　@注文単位.元発注条件 <BR>
     * <BR>
     * ２）　@以外の場合 <BR>
     * <BR>
     * this.get発注条件演算子()に処理を委譲する。<BR> 
     * <BR>
     * [get発注条件演算子()に指定する引数] <BR>
     * 　@発注条件演算子：　@注文単位.発注条件演算子 <BR>
     * 　@元発注条件演算子：　@注文単位.元発注条件演算子<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrderCondOperator(EqTypeOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderCondOperator(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //１）　@発注条件指定なしの場合
        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        String l_strOrderConditionType = 
            getOrderConditionType(
                l_row.getOrderConditionType(), 
                l_row.getOrgOrderConditionType());
        
        //this.get発注条件(）＝"DEFAULT（条件指定なし）"の場合
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //２）　@以外の場合
        String l_strOrderCondOperator = 
            getOrderCondOperator(
                l_row.getOrderCondOperator(), 
                l_row.getOrgOrderCondOperator());
        
        log.exiting(STR_METHOD_NAME);
        return l_strOrderCondOperator;
    }
    
    /**
     * (get逆指値基準値)<BR>
     * 引数の逆指値基準値、元逆指値基準値の内、<BR> 
     * 有効な逆指値基準値を返却する。 <BR>
     * <BR>
     * [パラメータ.元逆指値基準値 != nullの場合]<BR> 
     * 　@パラメータ.元逆指値基準値を返却する。<BR> 
     * <BR>
     * [パラメータ.逆指値基準値 != nullの場合] <BR>
     * 　@パラメータ.逆指値基準値を返却する。<BR>
     * @@param l_strStopOrderPrice - (逆指値基準値)<BR>
     * 逆指値基準値<BR>
     * @@param l_strOrgStopOrderPrice - (元逆指値基準値)<BR>
     * 元逆指値基準値<BR> 
     * @@return String
     */
    public static String getStopOrderPrice(
        String l_strStopOrderPrice, String l_strOrgStopOrderPrice)
    {
        final String STR_METHOD_NAME = "getStopOrderPrice(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ.元逆指値基準値 != nullの場合
        if (l_strOrgStopOrderPrice != null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_strOrgStopOrderPrice;
        }
        
        //パラメータ.逆指値基準値 != nullの場合
        if (l_strStopOrderPrice != null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_strStopOrderPrice;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get逆指値基準値)<BR>
     * 引数の注文単位より、有効な逆指値基準値を返却する。 <BR>
     * <BR>
     * １）　@発注条件指定なしの場合 <BR>
     * <BR>
     * this.get発注条件(）＝"DEFAULT（条件指定なし）"の場合、 <BR>
     * nullを返却する。 <BR>
     * <BR>
     * [get発注条件()に指定する引数] <BR>
     * 　@発注条件：　@注文単位.発注条件 <BR>
     * 　@元発注条件：　@注文単位.元発注条件 <BR>
     * <BR>
     * ２）　@以外の場合 <BR>
     * <BR>
     * this.get逆指値基準値()に処理を委譲する。 <BR>
     * <BR>
     * [get逆指値基準値()に指定する引数] <BR>
     * 　@逆指値基準値：　@注文単位.逆指値基準値 <BR>
     * 　@元逆指値基準値：　@注文単位.元逆指値基準値<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getStopOrderPrice(EqTypeOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderCondOperator(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //１）　@発注条件指定なしの場合
        EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        String l_strOrderConditionType = 
            getOrderConditionType(
                l_row.getOrderConditionType(), 
                l_row.getOrgOrderConditionType());
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //２）　@以外の場合
        String l_strStopOrderPrice = null;
        String l_strOrgStopOrderPrice = null;
        
        if (!l_row.getStopOrderPriceIsNull())
        {
            l_strStopOrderPrice = WEB3StringTypeUtility.formatNumber(l_row.getStopOrderPrice());
        }
        
        if (!l_row.getOrgStopOrderPriceIsNull())
        {
            l_strOrgStopOrderPrice = WEB3StringTypeUtility.formatNumber(l_row.getOrgStopOrderPrice());
        }
        
        String l_strRetStopOrderPrice = 
            getStopOrderPrice(l_strStopOrderPrice, l_strOrgStopOrderPrice);
        
        log.exiting(STR_METHOD_NAME);
        return l_strRetStopOrderPrice;
    }
    
    /**
     * (getAP口座区分)<BR>
     * <BR>
     * 引数の税区分よりAP層で使用する口座区分を返却する。<BR>
     * <BR>
     * WEB3TaxTypeDef.NORMAL == パラメータ.税区分の場合、<BR>
     * 　@TaxTypeEnum.NORMAL（"一般口座"）を返す。<BR>
     * <BR>
     * WEB3TaxTypeDef.STOCK_OPTION == パラメータ.税区分の場合、<BR>
     * 　@TaxTypeEnum.STOCK_OPTION（"ストックオプション口座"）を返す。 <BR>
     * <BR>
     * WEB3TaxTypeDef.SPECIAL == パラメータ.税区分の場合、<BR>
     * 　@TaxTypeEnum.SPECIAL（"特定口座"）を返す。<BR>
     * <BR>
     * @@param l_strTaxType - (税区分)<BR>
     * 税区分<BR>
     * @@return String
     */
    public static String getApTaxType(String l_strTaxType)
    {
        final String STR_METHOD_NAME = " getApTaxType(String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTaxTypeTemp = null;
        if(WEB3TaxTypeDef.NORMAL.equals(l_strTaxType))
        {
            l_strTaxTypeTemp = TaxTypeEnum.NORMAL.intValue() + "";
        }
        else if(WEB3TaxTypeDef.STOCK_OPTION.equals(l_strTaxType))
        {
            l_strTaxTypeTemp = TaxTypeEnum.STOCK_OPTION.intValue() + "";
        }
        else if(WEB3TaxTypeDef.SPECIAL.equals(l_strTaxType))
        {
            l_strTaxTypeTemp = TaxTypeEnum.SPECIAL.intValue() + "";
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strTaxTypeTemp;
    }
    
    /**
     * (getＷ指値用有効状態区分)<BR>
     * 引数の注文単位よりＷ指値用有効状態区分を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件<BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件<BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]<BR>
     * 　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]<BR>
     * 　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。<BR>
     * 　@[２）の戻り値 == trueの場合]<BR>
     * 　@　@"ストップ注文有効"を返却する。<BR>
     * 　@[３）の戻り値 == trueの場合]  <BR>
     * 　@　@"ストップ注文失効済"を返却する。<BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@"リミット注文有効"を返却する。<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitEnableStatusDiv(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitEnableStatusDiv(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        // nullを返却する。
        //[get発注条件()に指定する引数]
        // 発注条件：　@パラメータ.注文単位.発注条件
        // 元発注条件：　@パラメータ.注文単位.元発注条件
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strCondetionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。
        // [isストップ注文有効()に指定する引数]
        //  注文単位：　@パラメータ.注文単位
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。
        // [isストップ注文失効済()に指定する引数]
        // 注文単位：　@パラメータ.注文単位
        boolean l_blnIsStopOrderExpired =
            l_orderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //４）　@２）、３）の戻り値により、返却値を決定する。
        //　@[２）の戻り値 == trueの場合]
        // "ストップ注文有効"を返却する。
        if (l_blnIsStopOrderValid)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE;
        }

        //[３）の戻り値 == trueの場合]
        // "ストップ注文失効済"を返却する。
        if (l_blnIsStopOrderExpired)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityWlimitEnableStatusDivDef.STOP_UN_ENABLE;
        }

        //[上記以外の場合]
        // "リミット注文有効"を返却する。
        log.exiting(STR_METHOD_NAME);
        return WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE;
    }

    /**
     * (getＷ指値用有効状態区分)<BR>
     * 引数の注文履歴よりＷ指値用有効状態区分を返却する。  <BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]  <BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 <BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]  <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴  <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]  <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴  <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。<BR>
     * 　@[２）の戻り値 == trueの場合]  <BR>
     * 　@　@"ストップ注文有効"を返却する。<BR>
     * 　@[３）の戻り値 == trueの場合]  <BR>
     * 　@　@"ストップ注文失効済"を返却する。<BR>
     * 　@[上記以外の場合]  <BR>
     * 　@　@"リミット注文有効"を返却する。<BR>
     * @@param l_eqTypeOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitEnableStatusDiv(EqTypeOrderAction l_eqTypeOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitEnableStatusDiv(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        // nullを返却する。
        //[get発注条件()に指定する引数]
        // 発注条件：　@パラメータ.注文履歴.発注条件
        // 元発注条件：　@パラメータ.注文履歴.元発注条件
        EqtypeOrderActionRow l_orderActionRow =
            (EqtypeOrderActionRow)l_eqTypeOrderAction.getDataSourceObject();
        String l_strConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderActionRow.getOrderConditionType(),
                l_orderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。
        //[isストップ注文有効()に指定する引数]
        // 注文履歴：　@パラメータ.注文履歴
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderAction);

        //３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。
        // [isストップ注文失効済()に指定する引数]
        // 注文履歴：　@パラメータ.注文履歴
        boolean l_blnIsStopOrderExpired =
            l_orderManager.isStopOrderExpired(l_eqTypeOrderAction);

        //４）　@２）、３）の戻り値により、返却値を決定する。
        //[２）の戻り値 == trueの場合]
        //　@"ストップ注文有効"を返却する。
        if (l_blnIsStopOrderValid)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE;
        }
        //[３）の戻り値 == trueの場合]
        //　@"ストップ注文失効済"を返却する。
        if (l_blnIsStopOrderExpired)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityWlimitEnableStatusDivDef.STOP_UN_ENABLE;
        }
        //[上記以外の場合]
        //　@"リミット注文有効"を返却する。
        log.exiting(STR_METHOD_NAME);
        return WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE;
    }

    /**
     * (getＷ指値用切替前注文単価)<BR>
     * 引数の注文単位よりＷ指値用切替前注文単価を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。  <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]  <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件  <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 <BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]  <BR>
     * 　@　@注文単位：　@パラメータ.注文単位  <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文切替中()メソッドをコールする。<BR>
     * 　@[isストップ注文切替中()に指定する引数]  <BR>
     * 　@　@注文単位：　@パラメータ.注文単位  <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。 <BR>
     * 　@[２）の戻り値 == trueの場合]  <BR>
     * 　@　@パラメータ.注文単位.（W指値）切替前指値を返却する。<BR>
     * 　@[３）の戻り値 == trueの場合]  <BR>
     * 　@　@パラメータ.注文単位.市場から確認済みの指値を返却する。 <BR>
     * 　@[上記以外]  <BR>
     * 　@　@パラメータ.注文単位.指値を返却する。<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitBefSwitchPrice(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitBefSwitchPrice(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        //nullを返却する。
        //[get発注条件()に指定する引数]
        //　@発注条件：　@パラメータ.注文単位.発注条件
        //　@元発注条件：　@パラメータ.注文単位.元発注条件
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strCondetionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。
        // [isストップ注文有効()に指定する引数]
        //　@注文単位：　@パラメータ.注文単位
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //３）　@拡張株式注文マネージャ.isストップ注文切替中()メソッドをコールする。
        //[isストップ注文切替中()に指定する引数]
        //　@注文単位：　@パラメータ.注文単位
        boolean l_blnIsStopOrderExpired =
            l_orderManager.isStopOrderSwitching(l_eqTypeOrderUnit);

        //４）　@２）、３）の戻り値により、返却値を決定する。
        //[２）の戻り値 == trueの場合]
        //　@パラメータ.注文単位.（W指値）切替前指値を返却する。
        if (l_blnIsStopOrderValid)
        {
            String l_strWLimitBeforeLimitPrice = null;
            if (!l_orderUnitRow.getWLimitBeforeLimitPriceIsNull())
            {
                l_strWLimitBeforeLimitPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_orderUnitRow.getWLimitBeforeLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strWLimitBeforeLimitPrice;
        }

        //[３）の戻り値 == trueの場合]
        //　@パラメータ.注文単位.市場から確認済みの指値を返却する。
        if (l_blnIsStopOrderExpired)
        {
            String l_strConfirmedPrice = null;
            if (!l_orderUnitRow.getConfirmedPriceIsNull())
            {
                l_strConfirmedPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_orderUnitRow.getConfirmedPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strConfirmedPrice;
        }

        //[上記以外]
        //　@パラメータ.注文単位.指値を返却する。
        String l_strLimitPric = null;
        if (!l_orderUnitRow.getLimitPriceIsNull())
        {
            l_strLimitPric = WEB3StringTypeUtility.formatNumber(
                l_orderUnitRow.getLimitPrice());
        }

        log.exiting(STR_METHOD_NAME);
        return l_strLimitPric;
    }

    /**
     * (getＷ指値用切替前注文単価)<BR>
     * 引数の注文履歴よりＷ指値用切替前注文単価を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]<BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 <BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数] <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴  <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文切替中()メソッドをコールする。<BR>
     * 　@[isストップ注文切替中()に指定する引数]  <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴  <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。<BR>
     * 　@[２）の戻り値 == trueの場合]  <BR>
     * 　@　@パラメータ.注文履歴.（W指値）切替前指値を返却する。<BR>
     * 　@[３）の戻り値 == trueの場合]  <BR>
     * 　@　@パラメータ.注文履歴.市場から確認済みの指値を返却する。  <BR>
     * 　@[上記以外]  <BR>
     * 　@　@パラメータ.注文履歴.注文単価を返却する。 <BR>
     * @@param l_eqTypeOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitBefSwitchPrice(EqTypeOrderAction l_eqTypeOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitBefSwitchPrice(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        // nullを返却する。
        //[get発注条件()に指定する引数]
        // 発注条件：　@パラメータ.注文履歴.発注条件
        // 元発注条件：　@パラメータ.注文履歴.元発注条件
        EqtypeOrderActionRow l_orderActionRow =
            (EqtypeOrderActionRow)l_eqTypeOrderAction.getDataSourceObject();
        String l_strConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderActionRow.getOrderConditionType(),
                l_orderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。
        //[isストップ注文有効()に指定する引数]
        //　@注文履歴：　@パラメータ.注文履歴
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderAction);

        //３）　@拡張株式注文マネージャ.isストップ注文切替中()メソッドをコールする。
        //[isストップ注文切替中()に指定する引数]
        //　@注文履歴：　@パラメータ.注文履歴
        boolean l_blnIsStopOrderSwitching =
            l_orderManager.isStopOrderSwitching(l_eqTypeOrderAction);

        //４）　@２）、３）の戻り値により、返却値を決定する。
        //[２）の戻り値 == trueの場合]
        //　@パラメータ.注文履歴.（W指値）切替前指値を返却する。
        if (l_blnIsStopOrderValid)
        {
            String l_strWLimitBeforeLimitPrice = null;
            if (!l_orderActionRow.getWLimitBeforeLimitPriceIsNull())
            {
                l_strWLimitBeforeLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_orderActionRow.getWLimitBeforeLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strWLimitBeforeLimitPrice;
        }
        //[３）の戻り値 == trueの場合]
        //　@パラメータ.注文履歴.市場から確認済みの指値を返却する。
        if (l_blnIsStopOrderSwitching)
        {
            String l_strConfirmedPrice = null;
            if (!l_orderActionRow.getConfirmedPriceIsNull())
            {
                l_strConfirmedPrice = WEB3StringTypeUtility.formatNumber(
                    l_orderActionRow.getConfirmedPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strConfirmedPrice;
        }
        //[上記以外]
        //　@パラメータ.注文履歴.注文単価を返却する。
        String l_strPrice = null;
        if (!l_orderActionRow.getPriceIsNull())
        {
            l_strPrice = WEB3StringTypeUtility.formatNumber(l_orderActionRow.getPrice());
        }
        log.exiting(STR_METHOD_NAME);
        return l_strPrice;
    }

    /**
     * (getＷ指値用切替前執行条件)<BR>
     * 引数の注文単位よりＷ指値用切替前執行条件を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]<BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 <BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文切替中()メソッドをコールする。<BR>
     * 　@[isストップ注文切替中()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位  <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、執行条件を決定する。 <BR>
     * 　@[２）の戻り値 == trueの場合] <BR>
     * 　@　@執行条件 = パラメータ.注文単位.（W指値）切替前執行条件<BR>
     * 　@[３）の戻り値 == trueの場合] <BR>
     * 　@　@執行条件 = パラメータ.注文単位.市場から確認済みの執行条件 <BR>
     * 　@[上記以外]<BR>
     * 　@　@執行条件 = パラメータ.注文単位.執行条件<BR>
     * <BR>
     * ５）　@拡張株式注文マネージャ.get執行条件（SONAR）()メソッドをコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[get執行条件（SONAR）()に指定する引数] <BR>
     * 　@　@執行条件：　@４）にて決定した執行条件 <BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitBefSwitchExecCondType(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitBefSwitchExecCondType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        //nullを返却する。
        //[get発注条件()に指定する引数]
        //　@発注条件：　@パラメータ.注文単位.発注条件
        //　@元発注条件：　@パラメータ.注文単位.元発注条件
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strCondetionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。
        // [isストップ注文有効()に指定する引数]
        //　@注文単位：　@パラメータ.注文単位
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //３）　@拡張株式注文マネージャ.isストップ注文切替中()メソッドをコールする。
        //[isストップ注文切替中()に指定する引数]
        //　@注文単位：　@パラメータ.注文単位
        boolean l_blnIsStopOrderExpired =
            l_orderManager.isStopOrderSwitching(l_eqTypeOrderUnit);

        //４）　@２）、３）の戻り値により、執行条件を決定する。
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType = null;

        //[２）の戻り値 == trueの場合]
        //　@執行条件 = パラメータ.注文単位.（W指値）切替前執行条件
        if (l_blnIsStopOrderValid)
        {
            l_eqTypeExecutionConditionType = l_orderUnitRow.getWLimitBeforeExecCondType();
        }

        //[３）の戻り値 == trueの場合]
        //　@執行条件 = パラメータ.注文単位.市場から確認済みの執行条件
        else if (l_blnIsStopOrderExpired)
        {
            l_eqTypeExecutionConditionType = l_orderUnitRow.getConfirmedExecConditionType();
        }
        //[上記以外]
        //　@執行条件 = パラメータ.注文単位.執行条件
        else
        {
            l_eqTypeExecutionConditionType = l_orderUnitRow.getExecutionConditionType();
        }

        //５）　@拡張株式注文マネージャ.get執行条件（SONAR）()メソッドをコールし、
        // 戻り値を返却する。
        String l_strEqTypeExecutionConditionType =
            l_orderManager.getExecutionConditionTypeSonar(l_eqTypeExecutionConditionType);

        log.exiting(STR_METHOD_NAME);
        return l_strEqTypeExecutionConditionType;
    }

    /**
     * (getＷ指値用切替前執行条件)<BR>
     * 引数の注文履歴よりＷ指値用切替前執行条件を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 <BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]  <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴  <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文切替中()メソッドをコールする。<BR>
     * 　@[isストップ注文切替中()に指定する引数] <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴  <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、執行条件を決定する。<BR>
     * 　@[２）の戻り値 == trueの場合] <BR>
     * 　@　@執行条件 = パラメータ.注文履歴.（W指値）切替前執行条件<BR>
     * 　@[３）の戻り値 == trueの場合]<BR>
     * 　@　@執行条件 = パラメータ.注文履歴.市場から確認済みの執行条件<BR>
     * 　@[上記以外]<BR>
     * 　@　@執行条件 = パラメータ.注文履歴.執行条件<BR>
     * <BR>
     * ５）　@拡張株式注文マネージャ.get執行条件（SONAR）()メソッドをコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[get執行条件（SONAR）()に指定する引数]<BR>
     * 　@　@執行条件：　@４）にて決定した執行条件<BR>
     * @@param l_eqTypeOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitBefSwitchExecCondType(EqTypeOrderAction l_eqTypeOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitBefSwitchExecCondType(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        // nullを返却する。
        //[get発注条件()に指定する引数]
        // 発注条件：　@パラメータ.注文履歴.発注条件
        // 元発注条件：　@パラメータ.注文履歴.元発注条件
        EqtypeOrderActionRow l_orderActionRow =
            (EqtypeOrderActionRow)l_eqTypeOrderAction.getDataSourceObject();
        String l_strConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderActionRow.getOrderConditionType(),
                l_orderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。
        //[isストップ注文有効()に指定する引数]
        //　@注文履歴：　@パラメータ.注文履歴
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderAction);

        //３）　@拡張株式注文マネージャ.isストップ注文切替中()メソッドをコールする。
        //[isストップ注文切替中()に指定する引数]
        //　@注文履歴：　@パラメータ.注文履歴
        boolean l_blnIsStopOrderSwitching =
            l_orderManager.isStopOrderSwitching(l_eqTypeOrderAction);

        //４）　@２）、３）の戻り値により、執行条件を決定する。
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType = null;
        //[２）の戻り値 == trueの場合]
        //　@執行条件 = パラメータ.注文履歴.（W指値）切替前執行条件
        if (l_blnIsStopOrderValid)
        {
            l_eqTypeExecutionConditionType = l_orderActionRow.getWLimitBeforeExecCondType();
        }
        //[３）の戻り値 == trueの場合]
        //　@執行条件 = パラメータ.注文履歴.市場から確認済みの執行条件
        else if (l_blnIsStopOrderSwitching)
        {
            l_eqTypeExecutionConditionType = l_orderActionRow.getConfirmedExecConditionType();
        }
        //[上記以外]
        //　@執行条件 = パラメータ.注文履歴.執行条件
        else
        {
            l_eqTypeExecutionConditionType = l_orderActionRow.getExecutionConditionType();
        }

        //５）　@拡張株式注文マネージャ.get執行条件（SONAR）()メソッドをコールし、
        // 戻り値を返却する。
        String l_strExecutionConditionType =
            l_orderManager.getExecutionConditionTypeSonar(l_eqTypeExecutionConditionType);

        log.exiting(STR_METHOD_NAME);
        return l_strExecutionConditionType;
    }

    /**
     * (get遅延区分)<BR>
     * 引数の注文単位よりトリガー注文の遅延区分を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 == "DEFAULT(条件指定なし)"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]  <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 <BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.is遅延注文() == true の場合、<BR>
     * 　@"遅延"を返却する。<BR>
     * 　@以外、"正常"を返却する。<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getDelayDiv(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDelayDiv(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 == "DEFAULT(条件指定なし)"の場合、
        //nullを返却する。
        //[get発注条件()に指定する引数]
        //　@発注条件：　@パラメータ.注文単位.発注条件
        //　@元発注条件：　@パラメータ.注文単位.元発注条件
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strCondetionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@拡張株式注文マネージャ.is遅延注文() == true の場合、
        // "遅延"を返却する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsDelayOrder = l_orderManager.isDelayOrder(l_eqTypeOrderUnit);
        if (l_blnIsDelayOrder)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityDelayDivDef.DELAY;
        }

        //以外、"正常"を返却する。
        log.exiting(STR_METHOD_NAME);
        return WEB3EquityDelayDivDef.NORMAL;
    }

    /**
     * (getW指値発注状況区分)<BR>
     * パラメータ.注文単位より  <BR>
     * PR層で使用するW指値注文の発注状況区分を返却する。<BR>
     * <BR>
     * １）　@発注状況区分の判定 <BR>
     * <BR>
     * 　@１－１）　@発注遅延エラー（切替遅延エラー）の判定<BR>
     * 　@　@拡張株式注文マネージャ.is未発注遅延注文() == true の場合、<BR>
     * 　@　@"発注遅延エラー"を返却する。<BR>
     * <BR>
     * 　@１－２）　@待機@中（切替未済）の判定<BR>
     * 　@　@注文単位.リクエストタイプ＝"DEFAULT"の場合、"待機@中"を返却する。<BR>
     * <BR>
     * 　@１－３）　@発注中（切替中）の判定　@<BR>
     * 　@　@注文単位.リクエストタイプ＝"時価サーバ"の場合、"発注中"を返却する。<BR>
     * <BR>
     * 　@１－４）　@発注完了（切替完了）の判定 <BR>
     * 　@　@注文単位.リクエストタイプ＝"切替完了"の場合、"発注完了"を返却する。<BR>
     * <BR>
     * 　@１－５）　@ストップ注文失効の判定<BR>
     * 　@　@注文単位.リクエストタイプ＝"失効"の場合、"ストップ注文失効"を返却する。<BR>
     * <BR>
     * 　@１－６）　@上記以外の場合、"その他"を返却する。<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitOrderStatusType(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitOrderStatusType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //１）　@発注状況区分の判定
        //　@１－１）　@発注遅延エラー（切替遅延エラー）の判定
        //　@拡張株式注文マネージャ.is未発注遅延注文() == true の場合、
        //　@"発注遅延エラー"を返却する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsNotOrderedDelay =
            l_orderManager.isNotOrderedDelayOrder(l_eqTypeOrderUnit);
        if (l_blnIsNotOrderedDelay)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR;
        }

        //　@１－２）　@待機@中（切替未済）の判定
        //　@注文単位.リクエストタイプ＝"DEFAULT"の場合、"待機@中"を返却する。
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strRequestType = l_orderUnitRow.getRequestType();
        if (WEB3RequestTypeDef.DEFAULT.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3TriggerOrderStatusDef.ORDER_WAITING;
        }

        //　@１－３）　@発注中（切替中）の判定
        //　@注文単位.リクエストタイプ＝"時価サーバ"の場合、"発注中"を返却する。
        if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3TriggerOrderStatusDef.ORDERING;
        }

        //　@１－４）　@発注完了（切替完了）の判定
        //　@注文単位.リクエストタイプ＝"切替完了"の場合、"発注完了"を返却する。
        if (WEB3RequestTypeDef.TRANSFERED.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3TriggerOrderStatusDef.ORDER_COMPLETE;
        }

        //　@１－６）　@ストップ注文失効の判定
        //　@注文単位.リクエストタイプ＝"失効"の場合、"ストップ注文失効"を返却する。
        if (WEB3RequestTypeDef.INVALIDATE.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3TriggerOrderStatusDef.STOP_ORDER_INVALIDATION;
        }

        //　@１－７）　@上記以外の場合、"その他"を返却する。
        log.exiting(STR_METHOD_NAME);
        return WEB3TriggerOrderStatusDef.OTHER;
    }

    /**
     * (getＷ指値用執行条件)<BR>
     * 引数の注文単位よりＷ指値用執行条件を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件  <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件  <BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]  <BR>
     * 　@　@注文単位：　@パラメータ.注文単位  <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]  <BR>
     * 　@　@注文単位：　@パラメータ.注文単位  <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却する執行条件を決定する。<BR>
     * 　@[２）の戻り値 == trueの場合]  <BR>
     * 　@　@執行条件 = パラメータ.注文単位.執行条件<BR>
     * 　@[３）の戻り値 == trueの場合]  <BR>
     * 　@　@執行条件 = パラメータ.注文単位.元（W指値）執行条件<BR>
     * 　@[上記以外]<BR>
     * 　@　@執行条件 = パラメータ.注文単位.（W指値）執行条件<BR>
     * <BR>
     * ５）　@拡張株式注文マネージャ.get執行条件（SONAR）()メソッドをコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[get執行条件（SONAR）()に指定する引数]<BR>
     * 　@　@執行条件：　@４）にて決定した執行条件<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitExecCondType(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitExecCondType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        //nullを返却する。
        //[get発注条件()に指定する引数]
        //　@発注条件：　@パラメータ.注文単位.発注条件
        //　@元発注条件：　@パラメータ.注文単位.元発注条件
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strCondetionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。
        // [isストップ注文有効()に指定する引数]
        //　@注文単位：　@パラメータ.注文単位
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。
        // [isストップ注文失効済()に指定する引数]
        // 注文単位：　@パラメータ.注文単位
        boolean l_blnIsStopOrderExpired =
            l_orderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //４）　@２）、３）の戻り値により、返却する執行条件を決定する。
        EqTypeExecutionConditionType l_executionConditionType = null;
        //[２）の戻り値 == trueの場合]
        //　@執行条件 = パラメータ.注文単位.執行条件
        if (l_blnIsStopOrderValid)
        {
            l_executionConditionType = l_orderUnitRow.getExecutionConditionType();
        }
        //[３）の戻り値 == trueの場合]
        //　@執行条件 = パラメータ.注文単位.元（W指値）執行条件
        else if (l_blnIsStopOrderExpired)
        {
            l_executionConditionType = l_orderUnitRow.getOrgWLimitExecCondType();
        }
        //[上記以外]
        //　@執行条件 = パラメータ.注文単位.（W指値）執行条件
        else
        {
            l_executionConditionType = l_orderUnitRow.getWLimitExecCondType();
        }

        //５）　@拡張株式注文マネージャ.get執行条件（SONAR）()メソッドをコールし、
        //戻り値を返却する。
        String l_strExecutionConditionType =
            l_orderManager.getExecutionConditionTypeSonar(l_executionConditionType);

        log.exiting(STR_METHOD_NAME);
        return l_strExecutionConditionType;
    }

    /**
     * (getＷ指値用注文単価区分)<BR>
     * 引数の注文単位よりＷ指値用注文単価区分を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]<BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件<BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件<BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()をコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]  <BR>
     * 　@　@注文単位：　@パラメータ.注文単位  <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]  <BR>
     * 　@　@注文単位：　@パラメータ.注文単位  <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値の注文単価区分を決定する。<BR>
     * 　@[２）の戻り値 == trueの場合]<BR>
     * 　@　@パラメータ.注文単位.isMarketOrder == trueであれば、"成行"、<BR>
     * 　@　@以外は"指値"を返却する。<BR>
     * 　@[３）の戻り値 == trueの場合]  <BR>
     * 　@　@パラメータ.注文単位.元（W指値）訂正指値 == 0であれば、"成行"、<BR>
     * 　@　@以外は"指値"を返却する。<BR>
     * 　@[上記以外]<BR>
     * 　@　@パラメータ.注文単位.（W指値）訂正指値 == 0であれば、"成行"、<BR>
     * 　@　@以外は"指値"を返却する。<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitOrderPriceDiv(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitOrderPriceDiv(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3EquityDataAdapter." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        //nullを返却する。
        //[get発注条件()に指定する引数]
        //　@発注条件：　@パラメータ.注文単位.発注条件
        //　@元発注条件：　@パラメータ.注文単位.元発注条件
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strCondetionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。
        // [isストップ注文有効()に指定する引数]
        //　@注文単位：　@パラメータ.注文単位
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        boolean l_blnIsStopOrderValid =
            l_orderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。
        // [isストップ注文失効済()に指定する引数]
        // 注文単位：　@パラメータ.注文単位
        boolean l_blnIsStopOrderExpired =
            l_orderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //４）　@２）、３）の戻り値により、返却値の注文単価区分を決定する。
        //　@[２）の戻り値 == trueの場合]
        //　@　@パラメータ.注文単位.isMarketOrder == trueであれば、"成行"、
        //　@　@以外は"指値"を返却する。
        if (l_blnIsStopOrderValid)
        {
            if (l_eqTypeOrderUnit.isMarketOrder())
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
        }
        //　@[３）の戻り値 == trueの場合]
        //　@　@パラメータ.注文単位.元（W指値）訂正指値 == 0であれば、"成行"、
        //　@　@以外は"指値"を返却する。
        if (l_blnIsStopOrderExpired)
        {
            double l_dblOrgWLimitPrice = l_orderUnitRow.getOrgWLimitPrice();
            if (l_dblOrgWLimitPrice == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
        }
        //　@[上記以外]
        //　@　@パラメータ.注文単位.（W指値）訂正指値 == 0であれば、"成行"、
        //　@　@以外は"指値"を返却する。
        if (l_orderUnitRow.getWLimitPrice() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
    }

    /**
     * (getＷ指値用注文単価)<BR>
     * 引数の注文単位よりＷ指値用注文単価を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 <BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]  <BR>
     * 　@　@注文単位：　@パラメータ.注文単位  <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]  <BR>
     * 　@　@注文単位：　@パラメータ.注文単位  <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。<BR>
     * 　@[２）の戻り値 == trueの場合]  <BR>
     * 　@　@パラメータ.注文単位.指値を返却する。<BR>
     * 　@[３）の戻り値 == trueの場合]  <BR>
     * 　@　@パラメータ.注文単位.元（W指値）訂正指値を返却する。<BR>
     * 　@[上記以外]<BR>
     * 　@　@パラメータ.注文単位.（W指値）訂正指値を返却する。<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitOrderPrice(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitOrderPrice(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //パラメータ値がNULL
        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        //　@nullを返却する。
        //　@[get発注条件()に指定する引数]
        //　@　@発注条件：　@パラメータ.注文単位.発注条件
        //　@　@元発注条件：　@パラメータ.注文単位.元発注条件
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderUnitRow.getOrderConditionType(),
            l_eqtypeOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //拡張株式注文マネージャを取得
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。
        //　@[isストップ注文有効()に指定する引数]
        //　@　@注文単位：　@パラメータ.注文単位
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。
        //　@[isストップ注文失効済()に指定する引数]
        //　@　@注文単位：　@パラメータ.注文単位
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //４）　@２）、３）の戻り値により、返却値を決定する。
        //　@[２）の戻り値 == trueの場合]
        //　@　@パラメータ.注文単位.指値を返却する。
        if (l_blnStopOrderValid)
        {
            String l_strLimitPrice = null;
            if (!l_eqtypeOrderUnitRow.getLimitPriceIsNull())
            {
                l_strLimitPrice =
                    WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitRow.getLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strLimitPrice;
        }

        //　@[３）の戻り値 == trueの場合]
        //　@　@パラメータ.注文単位.元（W指値）訂正指値を返却する。
        else if (l_blnStopOrderExpired)
        {
            String l_strOrgWLimitPrice = null;
            if (!l_eqtypeOrderUnitRow.getOrgWLimitPriceIsNull())
            {
                l_strOrgWLimitPrice =
                    WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitRow.getOrgWLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strOrgWLimitPrice;
        }

        //　@[上記以外]
        //　@　@パラメータ.注文単位.（W指値）訂正指値を返却する。
        String l_strWLimitPrice = null;
        if (!l_eqtypeOrderUnitRow.getWLimitPriceIsNull())
        {
            l_strWLimitPrice =
                WEB3StringTypeUtility.formatNumber(l_eqtypeOrderUnitRow.getWLimitPrice());
        }
        log.exiting(STR_METHOD_NAME);
        return l_strWLimitPrice;
    }

    /**
     * (get元Ｗ指値用注文単価)<BR>
     * 引数の注文単位より元Ｗ指値用注文単価を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件<BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件<BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]  <BR>
     * 　@　@注文単位：　@パラメータ.注文単位  <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]<BR>
     * 　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。<BR>
     * 　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合] <BR>
     * 　@　@パラメータ.注文単位.元（W指値）訂正指値を返却する。<BR>
     * 　@[上記以外]<BR>
     * 　@　@nullを返却する。<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrgWLimitOrderPrice(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPrice(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //パラメータ値がNULL
        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        //　@nullを返却する。
        //　@[get発注条件()に指定する引数]
        //　@　@発注条件：　@パラメータ.注文単位.発注条件
        //　@　@元発注条件：　@パラメータ.注文単位.元発注条件
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderUnitRow.getOrderConditionType(),
            l_eqtypeOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //拡張株式注文マネージャを取得
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。
        //　@[isストップ注文有効()に指定する引数]
        //　@　@注文単位：　@パラメータ.注文単位
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。
        //　@[isストップ注文失効済()に指定する引数]
        //　@　@注文単位：　@パラメータ.注文単位
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //４）　@２）、３）の戻り値により、返却値を決定する。
        //　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]
        //　@　@パラメータ.注文単位.元（W指値）訂正指値を返却する。
        if (l_blnStopOrderValid || l_blnStopOrderExpired)
        {
            String l_strOrgWLimitPrice = null;
            if (!l_eqtypeOrderUnitRow.getOrgWLimitPriceIsNull())
            {
                l_strOrgWLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_eqtypeOrderUnitRow.getOrgWLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strOrgWLimitPrice;
        }

        //　@[上記以外]
        //　@　@nullを返却する。
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get元Ｗ指値用注文単価)<BR>
     * 引数の注文履歴より元Ｗ指値用注文単価を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]<BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件<BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件<BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]<BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴<BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]<BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴<BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。<BR>
     * 　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]<BR>
     * 　@　@パラメータ.注文履歴.元（W指値）訂正指値を返却する。<BR>
     * 　@[上記以外]<BR>
     * 　@　@nullを返却する。<BR>
     * @@param l_eqTypeOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrgWLimitOrderPrice(EqTypeOrderAction l_eqTypeOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPrice(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        //パラメータ値がNULL
        if (l_eqTypeOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        //　@nullを返却する。
        //　@[get発注条件()に指定する引数]
        //　@　@発注条件：　@パラメータ.注文履歴.発注条件
        //　@　@元発注条件：　@パラメータ.注文履歴.元発注条件
        EqtypeOrderActionRow l_eqtypeOrderActionRow =
            (EqtypeOrderActionRow)l_eqTypeOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderActionRow.getOrderConditionType(),
            l_eqtypeOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //拡張株式注文マネージャを取得
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。
        //　@[isストップ注文有効()に指定する引数]
        //　@　@注文履歴：　@パラメータ.注文履歴
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderAction);

        //３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。
        //　@[isストップ注文失効済()に指定する引数]
        //　@　@注文履歴：　@パラメータ.注文履歴
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderAction);

        //４）　@２）、３）の戻り値により、返却値を決定する。
        //　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]
        //　@　@パラメータ.注文履歴.元（W指値）訂正指値を返却する。
        if (l_blnStopOrderValid || l_blnStopOrderExpired)
        {
            String l_strOrgWLimitPrice = null;
            if (!l_eqtypeOrderActionRow.getOrgWLimitPriceIsNull())
            {
                l_strOrgWLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_eqtypeOrderActionRow.getOrgWLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strOrgWLimitPrice;
        }

        //　@[上記以外]
        //　@　@nullを返却する。
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get元Ｗ指値用注文単価区分)<BR>
     * 引数の注文単位より元Ｗ指値用注文単価区分を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件<BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()をコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]  <BR>
     * 　@　@注文単位：　@パラメータ.注文単位  <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位  <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。<BR>
     * 　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合] <BR>
     * 　@　@パラメータ.注文単位.元（W指値）訂正指値 == 0であれば、"成行"、<BR>
     * 　@　@以外は"指値"を返却する。  <BR>
     * 　@[上記以外]  <BR>
     * 　@　@nullを返却する。<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrgWLimitOrderPriceDiv(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPriceDiv(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //パラメータ値がNULL
        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        //　@nullを返却する。
        //　@[get発注条件()に指定する引数]
        //　@　@発注条件：　@パラメータ.注文単位.発注条件
        //　@　@元発注条件：　@パラメータ.注文単位.元発注条件
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderUnitRow.getOrderConditionType(),
            l_eqtypeOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //拡張株式注文マネージャを取得
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //２）　@拡張株式注文マネージャ.isストップ注文有効()をコールする。
        //　@[isストップ注文有効()に指定する引数]
        //　@　@注文単位：　@パラメータ.注文単位
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。
        //　@[isストップ注文失効済()に指定する引数]
        //　@　@注文単位：　@パラメータ.注文単位
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //４）　@２）、３）の戻り値により、返却値を決定する。
        //　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]
        if (l_blnStopOrderValid || l_blnStopOrderExpired)
        {
            double l_dblOrgWLimitPrice = l_eqtypeOrderUnitRow.getOrgWLimitPrice();
            //　@　@パラメータ.注文単位.元（W指値）訂正指値 == 0であれば、"成行"、
            if (l_dblOrgWLimitPrice == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            //　@　@以外は"指値"を返却する。
            else
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
        }

        //　@[上記以外]
        //　@　@nullを返却する。
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get元Ｗ指値用注文単価区分)<BR>
     * 引数の注文履歴より元Ｗ指値用注文単価区分を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]  <BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件<BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 <BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()をコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]<BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴 <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]  <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴  <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。<BR>
     * 　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合] <BR>
     * 　@　@パラメータ.注文履歴.元（W指値）訂正指値 == 0であれば、"成行"、<BR>
     * 　@　@以外は"指値"を返却する。<BR>
     * 　@[上記以外]  <BR>
     * 　@　@nullを返却する。<BR>
     * @@param l_eqTypeOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrgWLimitOrderPriceDiv(EqTypeOrderAction l_eqTypeOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPriceDiv(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        //パラメータ値がNULL
        if (l_eqTypeOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        //　@nullを返却する。
        //　@[get発注条件()に指定する引数]
        //　@　@発注条件：　@パラメータ.注文履歴.発注条件
        //　@　@元発注条件：　@パラメータ.注文履歴.元発注条件
        EqtypeOrderActionRow l_eqtypeOrderActionRow =
            (EqtypeOrderActionRow)l_eqTypeOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderActionRow.getOrderConditionType(),
            l_eqtypeOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //拡張株式注文マネージャを取得
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //２）　@拡張株式注文マネージャ.isストップ注文有効()をコールする。
        //　@[isストップ注文有効()に指定する引数]
        //　@　@注文履歴：　@パラメータ.注文履歴
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderAction);

        //３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。
        //　@[isストップ注文失効済()に指定する引数]
        //　@　@注文履歴：　@パラメータ.注文履歴
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderAction);

        //４）　@２）、３）の戻り値により、返却値を決定する。
        //　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]
        if (l_blnStopOrderValid || l_blnStopOrderExpired)
        {
            double l_dblOrgWLimitPrice = l_eqtypeOrderActionRow.getOrgWLimitPrice();
            //　@　@パラメータ.注文履歴.元（W指値）訂正指値 == 0であれば、"成行"、
            if (l_dblOrgWLimitPrice == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            //　@　@以外は"指値"を返却する。
            else
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
        }

        //　@[上記以外]
        //　@　@nullを返却する。
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get元Ｗ指値用執行条件)<BR>
     * 引数の注文単位より元Ｗ指値用執行条件を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]  <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件<BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 <BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]  <BR>
     * 　@　@注文単位：　@パラメータ.注文単位  <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却する執行条件を決定する。<BR>
     * 　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合] <BR>
     * 　@　@執行条件 = パラメータ.注文単位.元（W指値）執行条件<BR>
     * 　@[上記以外] <BR>
     * 　@　@nullを返却する。<BR>
     * <BR>
     * ５）　@拡張株式注文マネージャ.get執行条件（SONAR）()メソッドをコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[get執行条件（SONAR）()に指定する引数]<BR>
     * 　@　@執行条件：　@４）にて決定した執行条件<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrgWLimitExecCondType(EqTypeOrderUnit l_eqTypeOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitExecCondType(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //パラメータ値がNULL
        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        //　@nullを返却する。
        //　@[get発注条件()に指定する引数]
        //　@　@発注条件：　@パラメータ.注文単位.発注条件
        //　@　@元発注条件：　@パラメータ.注文単位.元発注条件
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderUnitRow.getOrderConditionType(),
            l_eqtypeOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //拡張株式注文マネージャを取得
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。
        //　@[isストップ注文有効()に指定する引数]
        //　@　@注文単位：　@パラメータ.注文単位
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderUnit);

        //３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。
        //　@[isストップ注文失効済()に指定する引数]
        //　@　@注文単位：　@パラメータ.注文単位
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderUnit);

        //４）　@２）、３）の戻り値により、返却する執行条件を決定する。
        //　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]
        //　@　@執行条件 = パラメータ.注文単位.元（W指値）執行条件
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType = null;
        if (l_blnStopOrderValid || l_blnStopOrderExpired)
        {
            l_eqTypeExecutionConditionType =
                l_eqtypeOrderUnitRow.getOrgWLimitExecCondType();
        }

        //　@[上記以外]
        //　@　@nullを返却する。
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //５）　@拡張株式注文マネージャ.get執行条件（SONAR）()メソッドをコールし、
        //　@戻り値を返却する。
        //　@[get執行条件（SONAR）()に指定する引数]
        //　@　@執行条件：　@４）にて決定した執行条件
        String l_strReturn =
            l_equityOrderManager.getExecutionConditionTypeSonar(l_eqTypeExecutionConditionType);

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (get元Ｗ指値用執行条件)<BR>
     * 引数の注文履歴より元Ｗ指値用執行条件を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。 <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件<BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件<BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]  <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴  <BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]  <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴  <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却する執行条件を決定する。<BR>
     * 　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]<BR>
     * 　@　@執行条件 = パラメータ.注文履歴.元（W指値）執行条件 <BR>
     * 　@[上記以外]  <BR>
     * 　@　@nullを返却する。  <BR>
     * <BR>
     * ５）　@拡張株式注文マネージャ.get執行条件（SONAR）()メソッドをコールし、<BR>
     * 　@戻り値を返却する。  <BR>
     * <BR>
     * 　@[get執行条件（SONAR）()に指定する引数]  <BR>
     * 　@　@執行条件：　@４）にて決定した執行条件<BR>
     * @@param l_eqTypeOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrgWLimitExecCondType(EqTypeOrderAction l_eqTypeOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitExecCondType(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        //パラメータ値がNULL
        if (l_eqTypeOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "パラメータ値不正。");
        }

        //１）　@this.get発注条件()の戻り値 != "W指値"の場合、
        //　@nullを返却する。
        //　@[get発注条件()に指定する引数]
        //　@　@発注条件：　@パラメータ.注文履歴.発注条件
        //　@　@元発注条件：　@パラメータ.注文履歴.元発注条件
        EqtypeOrderActionRow l_eqtypeOrderActionRow =
            (EqtypeOrderActionRow)l_eqTypeOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
            l_eqtypeOrderActionRow.getOrderConditionType(),
            l_eqtypeOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //拡張株式注文マネージャを取得
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        //２）　@拡張株式注文マネージャ.isストップ注文有効()メソッドをコールする。
        //　@[isストップ注文有効()に指定する引数]
        //　@　@注文履歴：　@パラメータ.注文履歴
        boolean l_blnStopOrderValid =
            l_equityOrderManager.isStopOrderValid(l_eqTypeOrderAction);

        //３）　@拡張株式注文マネージャ.isストップ注文失効済()メソッドをコールする。
        //　@[isストップ注文失効済()に指定する引数]
        //　@　@注文履歴：　@パラメータ.注文履歴
        boolean l_blnStopOrderExpired =
            l_equityOrderManager.isStopOrderExpired(l_eqTypeOrderAction);

        //４）　@２）、３）の戻り値により、返却する執行条件を決定する。
        //　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]
        //　@　@執行条件 = パラメータ.注文履歴.元（W指値）執行条件
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType = null;
        if (l_blnStopOrderValid || l_blnStopOrderExpired)
        {
            l_eqTypeExecutionConditionType =
                l_eqtypeOrderActionRow.getOrgWLimitExecCondType();
        }

        //　@[上記以外]
        //　@　@nullを返却する。
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //５）　@拡張株式注文マネージャ.get執行条件（SONAR）()メソッドをコールし、
        //　@戻り値を返却する。
        //　@[get執行条件（SONAR）()に指定する引数]
        //　@　@執行条件：　@４）にて決定した執行条件
        String l_strReturn =
            l_equityOrderManager.getExecutionConditionTypeSonar(l_eqTypeExecutionConditionType);

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (get発注状況区分)<BR>
     * PR層で使用する発注状況区分を返却する。<BR>
     * <BR>
     * １）　@パラメータ.条件注文種別＝"逆指値"の場合、<BR>
     * 　@this.get逆指値発注状況区分()をコールし、戻り値を返却する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ２）　@パラメータ.条件注文種別＝"W指値"の場合、<BR>
     * 　@this.getW指値発注状況区分()をコールし、戻り値を返却する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@注文単位：　@パラメータ.注文単位<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_strTriggerOrderType - (条件注文種別)<BR>
     * 条件注文種別<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getOrderStatusType(
        EqTypeOrderUnit l_orderUnit,
        String l_strTriggerOrderType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderStatusType(EqTypeOrderUnit, String)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityDataAdapter." + STR_METHOD_NAME ,
                "パラメータ値不正。");
        }

        //１）　@パラメータ.条件注文種別＝"逆指値"の場合、
        //this.get逆指値発注状況区分()をコールし、戻り値を返却する。
        if (WEB3TriggerOrderTypeDef.STOP.equals(l_strTriggerOrderType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityDataAdapter.getStopTriggerOrderStatusType(l_orderUnit);
        }

        //２）　@パラメータ.条件注文種別＝"W指値"の場合、
        //this.getW指値発注状況区分()をコールし、戻り値を返却する。
        if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_strTriggerOrderType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3EquityDataAdapter.getWLimitOrderStatusType(l_orderUnit);
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
