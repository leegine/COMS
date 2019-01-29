head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoDataAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OPデータアダプタ(WEB3IfoDataAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/24 有山 祥子 (SRA) 新規作成
                 : 2006/7/5 徐宏偉(中訊)仕様変更モデル499
                 : 2006/7/6 徐宏偉(中訊)仕様変更モデル513
                 : 2006/7/6 肖志偉(中訊)仕様変更モデル455,472,482,484,485,487,497,500,510,523,539
                 : 2006/10/9 唐性峰(中訊)　@仕様変更モデルNo.555　@
                   2006/11/29 徐大方(中訊)仕様変更モデル583
Revesion History : 2007/06/08 張騰宇(中訊) モデル654、663
Revesion History : 2007/06/14 肖志偉(中訊) モデル718
Revesion History : 2007/06/21 張騰宇(中訊) モデル745
Revesion History : 2007/06/27 張騰宇(中訊) モデル759、766
Revesion History : 2008/07/28 張少傑(中訊) モデル891
*/

package webbroker3.ifo;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderAction;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3ContractCheckDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.common.define.WEB3TriggerOrderStatusDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.define.WEB3DelayDivDef;
import webbroker3.ifo.define.WEB3IfoAcceptTypeDef;
import webbroker3.ifo.define.WEB3IfoExecStatusTypeDef;
import webbroker3.ifo.define.WEB3IfoExpirationStatusDef;
import webbroker3.ifo.define.WEB3IfoOrderSpecDivDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (先物OPデータアダプタ)<BR>
 * 先物OPのデータ変換を管理するクラス。<BR>
 * @@author  有山 祥子（SRA）
 * @@version 1.0
 */
public class WEB3IfoDataAdapter
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3IfoDataAdapter.class);

    /**
     * (get取引区分)<BR>
     * パラメータ.注文種別より、PR層で使用する取引区分を返却する。<BR>
     * <BR>
     * １）パラメータ.注文種別により分岐し、対応する値を返却する。<BR>
     * <BR>
     * パラメータ.注文種別が、<BR>
     * 　@[OrderTypeEnum.指数先物新規買建注文の場合]<BR>
     * 　@　@"指数先物新規買建注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数先物新規売建注文の場合]<BR>
     * 　@　@"指数先物新規売建注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数先物売建返済注文の場合]<BR>
     * 　@　@"指数先物売建返済注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数先物買建返済注文の場合]<BR>
     * 　@　@"指数先物買建返済注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数オプション新規買建注文の場合]<BR>
     * 　@　@"指数オプション新規買建注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数オプション新規売建注文の場合]<BR>
     * 　@　@"指数オプション新規売建注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数オプション売建返済注文の場合]<BR>
     * 　@　@"指数オプション売建返済注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数オプション買建返済注文の場合]<BR>
     * 　@　@"指数オプション買建返済注文"を返却する。<BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別<BR>
     * @@return String
     */
    public static String getTradingType(OrderTypeEnum l_orderType) 
    {
        final String STR_METHOD_NAME =
            " getTradingType(OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTradingType = null;
        //[OrderTypeEnum.指数先物新規買建注文の場合]
        //　@"指数先物新規買建注文"を返却する。
        if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue());
        }
        //[OrderTypeEnum.指数先物新規売建注文の場合]
        //　@"指数先物新規売建注文"を返却する。
        else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.intValue());
        }
        //[OrderTypeEnum.指数先物売建返済注文の場合]
        //　@"指数先物売建返済注文"を返却する。
        else if (OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.intValue());
        }
        //[OrderTypeEnum.指数先物買建返済注文の場合]
        //　@"指数先物買建返済注文"を返却する。
        else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.intValue());
        }
        //[OrderTypeEnum.指数オプション新規買建注文の場合]
        //　@"指数オプション新規買建注文"を返却する。
        else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.intValue());
        }
        //[OrderTypeEnum.指数オプション新規売建注文の場合]
        //　@"指数オプション新規売建注文"を返却する。
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.intValue());
        }
        //[OrderTypeEnum.指数オプション売建返済注文の場合]
        //　@"指数オプション売建返済注文"を返却する。
        else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.intValue());
        }
        //[OrderTypeEnum.指数オプション買建返済注文の場合]
        //　@"指数オプション買建返済注文"を返却する。
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType))
        {
            l_strTradingType = String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.intValue());
        }
        log.debug("取引区分 = " + l_strTradingType);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_strTradingType;
    }
    
    /**
     * (get注文状態区分)<BR>
     * パラメータ.注文単位より、PR層で使用する注文状態区分を返却する。<BR>
     * <BR>
     * １）　@パラメータ.注文単位の保持するデータにより分岐し、<BR>
     * 　@対応する注文状態区分を返却する。<BR>
     * <BR>
     * 　@１－１）　@一部失効の判定<BR>
     * 　@　@パラメータ.注文単位.isPartiallyExecuted()＝true かつ<BR>
     * 　@　@注文単位.注文有効状態＝CLOSED（クローズ） かつ<BR>
     * 　@　@注文単位.失効区分＝INVALIDATED_BY_MKT（マーケット拒否）の場合、<BR>
     * 　@　@　@"一部失効"を返す。<BR>
     * <BR>
     * 　@１－２）　@全部失効の判定<BR>
     * 　@　@パラメータ.注文単位.isUnexecuted()＝ true かつ<BR>
     * 　@　@注文単位.注文有効状態＝CLOSED（クローズ） かつ<BR>
     * 　@　@注文単位.失効区分＝INVALIDATED_BY_MKT（マーケット拒否）の場合、<BR>
     * 　@　@"全部失効"を返す。<BR>
     * <BR>
     * 　@１－３）　@繰越失敗の判定<BR>
     * <BR>
     * 　@　@先物OPデータアダプタ.get注文期限区分(注文単位)戻り値が"当日限り"以外 かつ<BR>
     * 　@　@注文単位.注文失効日付 >＝ 業務日付(*1) かつ<BR>
     * 　@　@注文単位.注文有効状態＝CLOSED（クローズ） かつ<BR>
     * 　@　@注文単位.失効区分＝EXPIRED（終了） かつ<BR>
     * 　@　@注文単位.注文エラー理由コード≠"トリガー注文管理者手動失効済" かつ<BR>
     * 　@　@注文単位.注文エラー理由コード≠"0000：正常"の場合、<BR>
     * 　@　@"繰越失敗"を返す。<BR>
     * <BR>
     * 　@１－４）　@無効の判定<BR>
     * 　@　@パラメータ.注文単位.注文有効状態＝CLOSED（クローズ） かつ<BR>
     * 　@　@注文単位.失効区分＝EXPIRED（終了）の場合、<BR>
     * 　@　@"無効"を返す。<BR>
     * 　@　@※出来終了通知で注文が失効した場合<BR>
     * <BR>
     * 　@１－５）　@繰越済の判定<BR>
     * <BR>
     * 　@　@注文単位.注文状態 ＝ACCEPTED（受付済（新規注文）） かつ、<BR>
     * 　@　@注文単位.初回注文の注文単位ID > 0（=繰越注文) の場合、<BR>
     * 　@　@"繰越済"を返す。<BR>
     * <BR>
     * 　@１－６）　@切替注文の判定 <BR>
     * 　@　@注文単位.注文状態＝MODIFY_ACCEPTED(受付済(変更注文)) かつ、 <BR>
     * 　@　@注文単位.注文訂正・取消区分＝"W指値注文切替中"の場合、"切替注文"を返す。<BR> 
     * <BR>
     * 　@１－７）　@切替受付の判定 <BR>
     * 　@　@注文単位.注文状態＝MODIFYING(発注中(変更注文)) かつ、<BR> 
     * 　@　@注文単位.注文訂正・取消区分＝"W指値注文切替中"の場合、"切替受付"を返す。<BR> 
     * <BR>
     * 　@１－８）　@切替完了の判定 <BR>
     * 　@　@注文単位.注文状態＝MODIFIED(発注済み(変更注文)) かつ、 <BR>
     * 　@　@注文単位.注文訂正・取消区分＝（"W指値注文一部切替完了"、"W指値注文全部切替完了"）<BR>
     * 　@　@の場合、"切替完了"を返す。 <BR>
     * <BR>
     * 　@１－９）　@切替失敗の判定 <BR>
     * 　@　@注文単位.注文状態＝NOT_MODIFIED(発注失敗(変更注文)) かつ、<BR> 
     * 　@　@注文単位.注文訂正・取消区分＝"W指値注文切替失敗"の場合、"切替注文(失敗)"を返す。<BR> 
     * <BR>
     * 　@１－１０）　@上記以外の場合は、注文単位.注文状態.intValueを文字列で返す。<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem( ).getBizDate( )<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrderStatusType(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getOrderStatusType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderUnitRow l_orderUnitRow =
             (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderStatus = null;

        //先物OPデータアダプタ.get注文期限区分(注文単位)
        String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnit);

        //１－１）　@一部失効の判定
        if (l_orderUnit.isPartiallyExecuted()
            && OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
            && OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
        {
            l_strOrderStatus = WEB3OrderStatusDef.PART_INAFFECTED;
        }
        //１－２）　@全部失効の判定
        else if (l_orderUnit.isUnexecuted()
            && OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
            && OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
        {
            l_strOrderStatus = WEB3OrderStatusDef.FULL_INAFFECTED;
        }
        //１－３）　@繰越失敗の判定
        else if ((!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_strExpirationDateType))
            && (WEB3DateUtility.compareToDay(
                l_orderUnit.getExpirationTimestamp(), GtlUtils.getTradingSystem().getBizDate()) >= 0)
            && OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()) 
            && OrderExpirationStatusEnum.EXPIRED.equals(l_orderUnit.getExpirationStatus()) 
            && (!WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED.equals(l_orderUnitRow.getErrorReasonCode()))
            && (!WEB3ErrorReasonCodeDef.NORMAL.equals(l_orderUnitRow.getErrorReasonCode())))
        {
            l_strOrderStatus = WEB3OrderStatusDef.NOT_TRANSFERED;
        }
        //１－４）　@無効の判定
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
            && OrderExpirationStatusEnum.EXPIRED.equals(l_orderUnit.getExpirationStatus()))
        {
            l_strOrderStatus = WEB3OrderStatusDef.CLOSED;
        }
        //１－５）　@繰越済の判定
        else if (OrderStatusEnum.ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
            && l_orderUnitRow.getFirstOrderUnitId() > 0)
        {
            l_strOrderStatus = WEB3OrderStatusDef.TRANSFERED;
        }
        // 　@１－６）　@切替注文の判定 
        // 　@　@注文単位.注文状態＝MODIFY_ACCEPTED(受付済(変更注文)) かつ、 
        // 　@　@注文単位.注文訂正・取消区分＝"W指値注文切替中"の場合、"切替注文"を返す。 
        else if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
    		&& WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(
				l_orderUnitRow.getModifyCancelType()))
        {
        	l_strOrderStatus = WEB3OrderStatusDef.TRANSFER_ORDER;
        }
        
        // 　@１－７）　@切替受付の判定 
        // 　@　@注文単位.注文状態＝MODIFYING(発注中(変更注文)) かつ、 
        // 　@　@注文単位.注文訂正・取消区分＝"W指値注文切替中"の場合、"切替受付"を返す。 
        else if (OrderStatusEnum.MODIFYING.equals(l_orderUnitRow.getOrderStatus())
    		&& WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(l_orderUnitRow.getModifyCancelType()))
        {
        	l_strOrderStatus = WEB3OrderStatusDef.TRANSFER_ACCEPT;
        }
        
        // 　@１－８）　@切替完了の判定 
        // 　@　@注文単位.注文状態＝MODIFIED(発注済み(変更注文)) かつ、 
        // 　@　@注文単位.注文訂正・取消区分＝（"W指値注文一部切替完了"、"W指値注文全部切替完了"）
        // 　@　@の場合、"切替完了"を返す。 
        else if (OrderStatusEnum.MODIFIED.equals(l_orderUnitRow.getOrderStatus())
    		&& (WEB3ModifyCancelTypeDef.W_LIMIT_PARTIALLY_TRANSFERED.equals(
				l_orderUnitRow.getModifyCancelType()) 
				|| WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERED.equals(l_orderUnitRow.getModifyCancelType())))
        {
        	l_strOrderStatus = WEB3OrderStatusDef.TRANSFERRED;
        }
        
        // 　@１－９）　@切替失敗の判定 
        // 　@　@注文単位.注文状態＝NOT_MODIFIED(発注失敗(変更注文)) かつ、 
        // 　@　@注文単位.注文訂正・取消区分＝"W指値注文切替失敗"の場合、"切替注文(失敗)"を返す。 
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderUnitRow.getOrderStatus())
    		&& WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR.equals(
				l_orderUnitRow.getModifyCancelType()))
        {
        	l_strOrderStatus = WEB3OrderStatusDef.TRANSFER_ORDER_FAIL;
        }
        
        // 　@１－１０）　@上記以外の場合は、注文単位.注文状態.intValueを文字列で返す。
        else
        {
            l_strOrderStatus = Integer.toString(l_orderUnit.getOrderStatus().intValue());
        }
        
        log.debug("注文状態区分 = " + l_strOrderStatus);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_strOrderStatus;
    }
    
    /**
     * (get約定状態区分)<BR>
     * パラメータ.注文単位より、PR層で使用する約定状態区分を返却する。<BR>
     * <BR>
     * １－１）パラメータ.注文単位.isPartiallyExecuted( ) == trueの場合は、<BR>
     * "一部成立"を返す。<BR>  
     * １－２）注文単位.isFullyExecuted( ) == trueの場合は、<BR>
     * "全部成立"を返す。<BR> 
     * １－３）上記以外の場合は、"未約定"を返す。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト。
     * @@return String
     */
    public static String getExecStatusType(IfoOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "getExecStatusType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //返還値の設定
        String l_strReturn = null;
        
        if (l_orderUnit.isPartiallyExecuted())
        {
            l_strReturn = WEB3IfoExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE;  
        }
        else if (l_orderUnit.isFullyExecuted())
        {
            l_strReturn = WEB3IfoExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE;
        }
        else
        {
            l_strReturn = WEB3IfoExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
        }

        log.debug("約定状態区分 = " + l_strReturn);
      
        log.exiting(STR_METHOD_NAME);
      
        return l_strReturn;
    }
    
    /**
     * (get執行条件)<BR>
     * 引数の執行条件（SONAR）より、WebⅢにおける執行条件を取得し返却する。<BR> 
     * <BR>
     * ○引数の執行条件（SONAR）＝nullの場合 <BR>
     * 　@　@nullを返す。 <BR>
     * <BR>
     * ○引数の執行条件（SONAR）＝"1"（無条件） の場合<BR> 
     * 　@　@IfoOrderExecutionConditionType.NONE（条件なし）を返す。<BR> 
     * <BR>
     * ○引数の執行条件（SONAR）＝"3"（寄付） の場合 <BR>
     * 　@　@IfoOrderExecutionConditionType.AT_MARKET_OPEN（寄り）を返す。 <BR>
     * <BR>
     * ○引数の執行条件（SONAR）＝"4"（引け） の場合 <BR>
     * 　@　@IfoOrderExecutionConditionType.AT_MARKET_CLOSE（引け）を返す。<BR> 
     * <BR>
     * ○引数の執行条件（SONAR）＝"7"（不出来引け成行)） の場合 <BR>
     * 　@　@IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED（不出来引け成行）<BR> 
     * 　@　@を返す。 <BR>
     * <BR>
     * ○引数の執行条件（SONAR）が上記以外の場合 <BR>
     * 　@　@例外をthrowする。 <BR>
     * 　@　@class:WEB3BusinessLayerException<BR>
     * 　@　@tag  :BUSINESS_ERROR_00127<BR>
     * @@param l_strExecutionConditionSONAR - (執行条件（SONAR）)<BR>
     * SONARの執行条件。 <BR>
     * <BR>
     * 1：　@無条件 <BR>
     * 3：　@寄付 <BR>
     * 4：　@引け <BR>
     * 7：　@不出来引け成行<BR>
     * @@return IfoOrderExecutionConditionType
     * @@throws WEB3BaseException 
     */
    public static IfoOrderExecutionConditionType getExecutionConditionType(
        String l_strExecutionConditionSONAR) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutionConditionType(String)";
        log.entering(STR_METHOD_NAME);
        
        //返還値の設定
        IfoOrderExecutionConditionType l_orderExecutionConditionTypeRen = null;
        
        // 引数の執行条件（SONAR）＝nullの場合 
        // 　@　@nullを返す。 
    	if (l_strExecutionConditionSONAR == null)
    	{
    		l_orderExecutionConditionTypeRen = null;
    	}
    	
        // 引数の執行条件（SONAR）＝"1"（無条件） の場合 
        // 　@　@IfoOrderExecutionConditionType.NONE（条件なし）を返す。 
    	else if (WEB3SonarExecutionConditionDef.UNCONDITIONDNESS.equals(l_strExecutionConditionSONAR))
    	{
    		l_orderExecutionConditionTypeRen = IfoOrderExecutionConditionType.NONE;
    	}
    	
        // 引数の執行条件（SONAR）＝"3"（寄付） の場合 
        // 　@　@IfoOrderExecutionConditionType.AT_MARKET_OPEN（寄り）を返す。
    	else if (WEB3SonarExecutionConditionDef.AT_MARKET_OPEN.equals(l_strExecutionConditionSONAR))
    	{
            l_orderExecutionConditionTypeRen = 
            	IfoOrderExecutionConditionType.AT_MARKET_OPEN;
    	}
    	
        // 引数の執行条件（SONAR）＝"4"（引け） の場合 
        // 　@　@IfoOrderExecutionConditionType.AT_MARKET_CLOSE（引け）を返す。
    	else if (WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE.equals(l_strExecutionConditionSONAR))
    	{
            l_orderExecutionConditionTypeRen = 
            	IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
    	}
    	
        // 引数の執行条件（SONAR）＝"7"（不出来引け成行)） の場合 
        // 　@　@IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED（不出来引け成行）
        // 　@　@を返す。 
    	else if (WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER.equals(
			l_strExecutionConditionSONAR))
    	{
    		l_orderExecutionConditionTypeRen = 
    			IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
    	}

        // 引数の執行条件（SONAR）が上記以外の場合 
        // 　@　@例外をthrowする。 
        else
        {
            log.debug("BUSINESS_ERROR_00127:執行条件の値が存在しないコード値です。");
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00127,
                WEB3IfoDataAdapter.class.getName() + "." + STR_METHOD_NAME,
                "執行条件の値が存在しないコード値です。");
        }
    	log.exiting(STR_METHOD_NAME);
		return l_orderExecutionConditionTypeRen;
    }
    
    /**
     * (get執行条件（PR層）)<BR>
     * パラメータ.執行条件より、PR層用の執行条件のコードを返却する。<BR>
     * <BR>
     * １）パラメータ.執行条件＝IfoOrderExecutionConditionType.NONE<BR>
     * （条件なし）の場合、"無条件"を返す。<BR>
     * <BR>
     * ２）パラメータ.執行条件＝IfoOrderExecutionConditionType.AT_MARKET_OPEN<BR>
     * （寄り）の場合、"寄付"を返す。<BR>
     * <BR>
     * ３）パラメータ.執行条件＝<BR>
     * IfoOrderExecutionConditionType.AT_MARKET_CLOSE（引け）の場合、<BR>
     * "引け"を返す。<BR>
     * <BR>
     * ４）パラメータ.執行条件＝<BR>
     * IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED
     * <BR>
     * （不出来引け成行）の場合、"不出来引け成行"を返す。<BR>
     * <BR>
     * ５）パラメータ.執行条件が上記以外の場合は、例外をthrowする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00127<BR>
     * @@param l_executionCondition - 執行条件。
     * @@throws WEB3BusinessLayerException
     * @@return String
     */
    public static String getExecutionCondByPr(IfoOrderExecutionConditionType l_executionCondition)
    throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME =
            "getExecutionCondByPr(IfoOrderExecutionConditionType)";

        log.entering(STR_METHOD_NAME);

        //返還値の設定
        String l_strReturn = null;

        if (IfoOrderExecutionConditionType.NONE.equals(l_executionCondition))
        {
            //引数.執行条件：条件なし
            l_strReturn = WEB3ExecutionConditionDef.NO_CONDITION;
        }
        else if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_executionCondition))
        {
            //引数.執行条件：寄り
            l_strReturn = WEB3ExecutionConditionDef.AT_MARKET_OPEN;
        }
        else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_executionCondition))
        {
            //引数.執行条件：引け
            l_strReturn = WEB3ExecutionConditionDef.AT_MARKET_CLOSE;
        }
        else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_executionCondition))
        {
            //引数.執行条件：不出来引け成行
            l_strReturn = WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED;
        }
        else
        {
            log.error("BUSINESS_ERROR_00127:執行条件の値が存在しないコード値です。");
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00127,
                WEB3IfoDataAdapter.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }
        log.debug("執行条件 = " + l_strReturn);

        log.exiting(STR_METHOD_NAME);

        return l_strReturn;
    }
    
    /**
     * (getＷ指値用執行条件一覧)<BR>
     * 引数の執行条件一覧より、<BR> 
     * Ｗ指値用の執行条件一覧を作成し返却する。 <BR>
     * <BR>
     * [引数の発注条件一覧に"W指値"が含まれていない場合]  <BR>
     * 　@　@nullを返却する。 <BR>
     * <BR>
     * [引数の執行条件一覧に"不出来引け成行"が含まれる場合] <BR>
     * 　@以下の執行条件を要素とする配列を返却する。<BR> 
     * 　@　@・"無条件" <BR>
     * 　@　@・"不出来引け成行" <BR>
     * [上記以外の場合] <BR>
     * 　@"無条件"のみを要素とする配列を返却する。<BR>
     * @@param l_strExecutionConditionTypeList - (執行条件一覧)<BR>
     * 執行条件一覧 <BR>
     * ※以下の値から構成される配列 <BR>
     * <BR>
     * 1：無条件 <BR>
     * 3：寄付 <BR>
     * 4：引け <BR>
     * 7：不出来引け成行<BR>
     * @@param l_strOrderConditionList - (発注条件一覧)<BR>
     * 発注条件一覧 <BR>
     * @@return String[]
     * @@throws WEB3BaseException 
     */
    public static String[] getWLimitExecutionConditionTypeList(
        String[] l_strExecutionConditionTypeList,
        String[] l_strOrderConditionList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getWLimitExecutionConditionTypeList(String[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_strExecutionConditionTypeList == null 
            || l_strExecutionConditionTypeList.length == 0)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3IfoDataAdapter." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        int l_intConditionLisLength = l_strOrderConditionList.length;
        boolean l_blnReturn = true;
        for (int i = 0; i < l_intConditionLisLength; i++)
        {
            //[引数の発注条件一覧に"W指値"が含まれていない場合]
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

        // [引数の執行条件一覧に"不出来引け成行"が含まれる場合] 
        // 　@以下の執行条件を要素とする配列を返却する。 
        // 　@　@・"無条件" 
        // 　@　@・"不出来引け成行"  
        int l_intListLength = 0;
        if (l_strExecutionConditionTypeList != null && 
            l_strExecutionConditionTypeList.length > 0)
        {
            l_intListLength = l_strExecutionConditionTypeList.length;
        }
        
        for (int i = 0; i < l_intListLength; i++)
        {
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
        
        // [上記以外の場合] 
        // 　@"無条件"のみを要素とする配列を返却する。
		String[] l_strWLimitExecutionConditionTypeList = 
            {WEB3ExecutionConditionDef.NO_CONDITION};
        log.exiting(STR_METHOD_NAME);
        return l_strWLimitExecutionConditionTypeList;
    }

	/**
	 * (getＷ指値用執行条件一覧)<BR>
	 * (getＷ指値用執行条件一覧のオーバーロードメソッド)<BR>
	 * <BR>
	 * 引数の注文単位、執行条件一覧より、<BR>
	 * Ｗ指値用の執行条件一覧を作成し返却する。<BR>
	 * <BR>
     * １）　@引数の注文単位.発注条件が"W指値"でない場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
	 * ２）　@引数の注文単位が出来るまで注文<BR>
	 * 　@（OP注文マネージャ.is出来るまで注文単位(引数の注文単位) == true）の場合、<BR>
	 * 　@"無条件"のみを要素とする配列を返却する。<BR>
	 * <BR>
     * ３）　@引数の注文単位が夕場まで注文 <BR>
     * 　@（OP注文マネージャ.is夕場まで注文(引数の注文単位) == true）の場合、 <BR>
     * 　@"無条件"のみを要素とする配列を返却する。 <BR>
     * <BR>
     * ４）　@上記以外の場合、 <BR>
     * 　@this.getW指値用執行条件一覧(引数の執行条件一覧, 引数の注文単位.発注条件)メソッドを <BR>
     * 　@コールし、戻り値を返却する。<BR>
	 * <BR>
	 * @@param l_strExecutionConditionTypeList - (執行条件一覧)<BR>
	 * 執行条件一覧 <BR>
	 * ※以下の値から構成される配列 <BR>
	 * <BR>
	 * 1：無条件<BR>
	 * 3：寄付<BR>
	 * 4：引け<BR>
	 * 7：不出来引け成行<BR>
	 * @@param l_ifoOrderUnit - (注文単位オブジェクト) <BR>
	 * 注文単位オブジェクト<BR>
     * @@return String[]
     * @@throws WEB3BaseException 
	 */
	public static String[] getWLimitExecutionConditionTypeList(
			String[] l_strExecutionConditionTypeList,
			IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getWLimitExecutionConditionTypeList(String[]. IfoOrderUnit)";
		log.entering(STR_METHOD_NAME);

        // 　@引数の注文単位.発注条件が"W指値"でない場合、<BR>
        // 　@nullを返却する。<BR>
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        String l_strOrderCondetionType =
            l_orderUnitRow.getOrderConditionType();
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondetionType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

		FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
		TradingModule l_tradingModule = 
			l_finApp.getTradingModule(ProductTypeEnum.IFO);
		WEB3OptionOrderManagerImpl l_orderManager = 
			(WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

		// 　@引数の注文単位が出来るまで注文
		// 　@（OP注文マネージャ.is出来るまで注文単位(引数の注文単位) == true）の場合、 
		// 　@"無条件"のみを要素とする配列を返却する。 
        //　@引数の注文単位が夕場まで注文
        //　@（OP注文マネージャ.is夕場まで注文(引数の注文単位) == true）の場合、
        //　@"無条件"のみを要素とする配列を返却する。
		if (l_orderManager.isCarriedOrderUnit(l_ifoOrderUnit)
            || l_orderManager.isEveningSessionOrder(l_ifoOrderUnit))
		{
			String[] l_strWLimitExecutionConditionTypeList =
				{WEB3ExecutionConditionDef.NO_CONDITION};
            
			log.exiting(STR_METHOD_NAME);
			return l_strWLimitExecutionConditionTypeList;
		}

		// 　@上記以外の場合、 
		// 　@this.getW指値用執行条件一覧(引数の執行条件一覧, 引数の注文単位.発注条件)メソッドを 
		// 　@コールし、戻り値を返却する。 
		log.exiting(STR_METHOD_NAME);
		return getWLimitExecutionConditionTypeList(
			l_strExecutionConditionTypeList,
            new String[] {l_strOrderCondetionType});
	}

    /**
     * (get発注状況区分)<BR>
     * パラメータ.注文単位、条件注文種別より<BR>
     * PR層で使用する発注状況区分を返却する。<BR>
     * <BR>
     * １）　@パラメータ.条件注文種別＝"逆指値"の場合、<BR>
     * 　@　@this.get逆指値発注状況区分()をコールし、戻り値を返却する。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ２）　@パラメータ.条件注文種別＝"W指値"の場合、<BR> 
     * 　@　@this.getW指値発注状況区分()をコールし、戻り値を返却する。 <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@注文単位：　@パラメータ.注文単位<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_strTriggerOrderType - (条件注文種別)<BR>
     * 条件注文種別<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getTriggerOrderStatusType(
		IfoOrderUnit l_orderUnit, 
		String l_strTriggerOrderType) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getTriggerOrderStatusType(IfoOrderUnit, String)";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3TriggerOrderTypeDef.STOP.equals(l_strTriggerOrderType))
        {
            String l_strManualOrder = getStopTriggerOrderStatusType(l_orderUnit);
            
            log.exiting(STR_METHOD_NAME);
            return l_strManualOrder;
        }
        // ２）　@パラメータ.条件注文種別＝"W指値"の場合、 
        // 　@　@this.getW指値発注状況区分()をコールし、戻り値を返却する。 
        if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_strTriggerOrderType))
        {
            String l_strOrderStatusType = getWLimitOrderStatusType(l_orderUnit);
             
            log.exiting(STR_METHOD_NAME);
            return l_strOrderStatusType;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get逆指値発注状況区分)<BR>
     * パラメータ.注文単位より<BR>
     * PR層で使用する逆指値注文の発注状況区分を返却する。<BR>
     * <BR>
     * １）　@発注状況区分の判定<BR>
     * <BR>
     * 　@１－１）　@発注遅延エラーの判定 
     *　@　@OP注文マネージャ.is未発注遅延注文(注文単位) == trueの場合、 
     *　@　@"発注遅延エラー"を返却する。
     * 　@１－２）　@待機@中の判定<BR>
     * 　@　@注文単位.リクエストタイプ＝"DEFAULT"の場合、"待機@中"を返却する。<BR>
     * <BR>
     * 　@１－３）　@発注中、発注完了の判定 <BR>
     * 　@　@注文単位.リクエストタイプ＝"時価サーバ"の場合、<BR> 
     * 　@　@－注文単位.市場から確認済みの数量＝nullの場合、"発注中"<BR>
     * 　@　@－以外、"発注完了" <BR>
     * 　@　@を返却する。<BR>
     * <BR>
     * 　@１－４）　@発注審査エラーの判定<BR>
     * 　@　@注文単位.リクエストタイプ＝"発注失敗"の場合、"発注審査エラー"を返却する。<BR>
     * <BR>
     * 　@１－５）　@上記以外の場合、"その他"を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     */
    public static String getStopTriggerOrderStatusType(IfoOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            " getStopTriggerOrderStatusType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        String l_strTriggerOrderStatus = null;
        IfoOrderUnitRow l_orderUnitRow =
             (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
        //１－1 ）　@発注遅延エラーの判定
        //　@　@OP注文マネージャ.is未発注遅延注文(注文単位) == trueの場合、
        //　@　@"発注遅延エラー"を返却する。
        if (l_orderManager.isNotOrderedDelay(l_orderUnit))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR;
        }
        //１－2 ）　@待機@中の判定
        else if (WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType()))
        {
            l_strTriggerOrderStatus = WEB3TriggerOrderStatusDef.ORDER_WAITING;
        }
        //１－3）　@発注中、発注完了の判定
        else if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_orderUnitRow.getRequestType()))
        {
        	//－注文単位.市場から確認済みの数量＝nullの場合、"発注中"
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
     * (get商品区分)<BR>
     * 引数の注文単位よりPR層で使用する商品区分を返却する。<BR>
     * <BR>
     * １）　@以下の条件により分岐し、対応する商品区分を返却する。<BR>
     * <BR>
     * 　@[パラメータ.注文単位.先物／オプション区分 == "オプション"の場合]<BR>
     * 　@　@"オプション"を返却する。<BR>
     * <BR>
     * 　@[パラメータ.注文単位.先物／オプション区分 == "先物"の場合]<BR>
     * 　@　@"先物"を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getCommodityDiv(IfoOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "getCommodityDiv(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderUnitRow l_orderUnitRow = 
            (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        String l_strFutureOptionDiv = l_orderUnitRow.getFutureOptionDiv();
        String l_strReturn = null;
        
        if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFutureOptionDiv))
        {
            //[パラメータ.注文単位.先物／オプション区分 == "オプション"の場合]
            //　@"オプション"を返却する。
            l_strReturn = WEB3CommodityDivDef.OPTION;
        }
        else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFutureOptionDiv))
        {
            //[パラメータ.注文単位.先物／オプション区分 == "先物"の場合]
            //　@"先物"を返却する。
            l_strReturn = WEB3CommodityDivDef.FUTURE;
        }
        
        log.debug("商品区分 = " + l_strReturn);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_strReturn;
    }
    
    /**
     * (get処理状況区分)<BR>
     * 指定された注文単位の処理状況区分を返す。<BR>
     * <BR>
     * 戻り値の処理状況区分：<BR>
     * ※コード値はメッセージ定義フォルダ以下の<BR>
     * 「ﾒｯｾｰｼﾞ定義書_株価指数先物(共通).xls」の処理状況区分定義を参照。<BR>
     * <BR>
     * １）受付区分を取得する。<BR>
     *   this.get受付区分()をコールする。<BR>
     * <BR>
     *  [引数]<BR>
     *      注文単位：　@パラメータ.注文単位<BR> 
     * <BR>
     * ２） 約定区分を取得する。<BR>
     * 　@this.get約定状態区分()をコールする。<BR>
     * <BR>
     *  [引数]<BR>
     *      注文単位：　@パラメータ.注文単位<BR> 
     * <BR>
     * ３） 失効区分を判定する。<BR>
     * 　@this.get注文状態区分()をコールする。<BR>
     * <BR>
     *  [引数]<BR>
     *      注文単位：　@パラメータ.注文単位<BR> 
     * <BR>
     * 　@戻り値＝”一部失効”の場合は、失効区分："一部失効"。<BR>
     * 　@戻り値＝”全部失効”の場合は、失効区分："全部失効"。<BR>
     * 　@上記以外の場合は、失効区分："失効なし"。<BR>
     * <BR>
     * ４） 処理状況区分を判定する。<BR>
     * 　@受付区分 ＝"受付エラー"の場合、 「受付エラー」を返却する。<BR>
     * 　@以外の場合、受付区分 + 約定区分 + 失効区分 + 注文単位.注文訂正・取消区分の文字列連結値を返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return String
     */
    public static String getTransactionStatusType(IfoOrderUnit l_orderUnit)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTransactionStatusType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //受付区分
        String l_strAcceptType = getAcceptType(l_orderUnit);
        
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //約定区分
        String l_strExecType = getExecStatusType(l_orderUnit);
        
        //失効区分
        String l_strExpirationType;        
        String l_strOrderStatusType = getOrderStatusType(l_orderUnit);
        if (WEB3OrderStatusDef.PART_INAFFECTED.equals(l_strOrderStatusType))
        {
            l_strExpirationType = WEB3IfoExpirationStatusDef.PARTIALLY_CLOSE;
        }
        else if (WEB3OrderStatusDef.FULL_INAFFECTED.equals(l_strOrderStatusType))
        {
            l_strExpirationType = WEB3IfoExpirationStatusDef.ALL_CLOSE;
        }
        else
        {
            l_strExpirationType = WEB3IfoExpirationStatusDef.DEFAULT;
        }
        
        //注文単位.注文訂正・取消区分
        String l_strModifyCancelType = l_orderUnitRow.getModifyCancelType();
        
        //処理状況区分
        String l_strReturn = null;
        
        //受付区分が受付エラーの場合                         
        if (WEB3IfoAcceptTypeDef.EXEC_TYPE_ERROR.equals(l_strAcceptType))                           
        {                           
            l_strReturn = l_strAcceptType 
            + WEB3IfoExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE 
            + WEB3IfoExpirationStatusDef.DEFAULT
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
     *　@　@　@　@　@　@　@  現在日時の時間部分 ≦ 市場閉局時間（※）の場合<BR>
     *　@　@　@　@　@　@　@　@　@　@"発注未処理"を返却する。<BR>
     * <BR>
     * 　@　@　@　@　@　@ （※）市場閉局時間<BR>
     * 　@　@　@　@　@　@　@　@①@ 注文単位.get銘柄().get原資産銘柄コード()をコールする。<BR>
     * <BR>
     *　@　@ 　@　@　@　@　@　@② 取引時間管理.get市場閉局時間()をコールする。<BR>
     *　@　@　@ 　@　@　@　@　@　@　@　@[get市場閉局時間()に設定する引数]<BR>
     *　@　@　@　@ 　@　@　@　@　@　@　@市場コード：　@"0：DEFAULT"<BR>
     *　@　@　@　@　@ 　@　@　@　@　@　@商品コード：　@①@処理の戻り値<BR>
     * <BR>
     * ２）　@以下条件全てに該当する場合のみ処理する。<BR>
     * 　@　@　@　@・市場開局中　@（取引時間管理.is市場開局時間帯()が"true"の場合）<BR>
     * 　@　@　@　@・立会時間帯ではない（取引時間管理.is立会時間帯()が"false"の場合） <BR>
     * <BR>
     * 　@２－１）　@注文単位.立会区分 ＝ 取引時間管理.立会区分の場合<BR>
     * 　@　@　@　@- 注文単位.発注日＝ 取引時間管理.get発注日()の場合<BR>
     * 　@　@　@　@　@　@　@"発注未処理"を返却する。<BR>
     * <BR>
     * 　@２－２）　@上記以外の場合<BR>
     * 　@　@　@　@　@　@　@３）以降の処理対象とする<BR>
     * <BR>
     * ３）　@未発注の有効な逆指値注文で市場開局時間帯の場合<BR>
     * <BR>
     * 　@注文単位.発注条件＝"逆指値"、かつ、<BR>
     * 　@注文単位.リクエストタイプ＝"DEFAULT"、かつ、<BR>
     * 　@注文単位.注文有効状態＝"オープン"、かつ、<BR>
     * 　@取引時間管理.is立会時間帯()＝trueの場合、"発注待ち"を返却する。<BR>
     * <BR>
     * ４）　@未発注注文の場合<BR>
     * <BR>
     *　@　@注文単位.市場から確認済の数量＝NaNの場合、<BR>
     *　@　@- 注文単位.注文状態＝"発注失敗(新規注文)"の場合は、"受付エラー"、<BR>
     *　@　@- 注文単位.注文状態≠"発注失敗(新規注文)"の場合は、"受付未済"を返却する。<BR>
     * <BR>
     * ５）　@上記以外の場合は、"受付済"を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getAcceptType(IfoOrderUnit l_orderUnit)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAcceptType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_strAcceptType = null;

        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        try
        {
            //取引時間管理.get発注日()
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //注文単位.発注日
            Date l_datOrderUnitBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),WEB3GentradeTimeDef.DATE_FORMAT_YMD);

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

                //営業日区分取得
                String l_strBizDateType = 
                    WEB3GentradeTradingTimeManagement.getBizDateType(l_tsSysTimestamp);

                //注文単位.発注日 ＞ 業務日付の場合
                if (WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datBizDate) > 0)
                {
                    l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_NEXT_ORDER;
                    return l_strAcceptType;
                }
                //取引時間管理.get営業日区分(現在日時)が"非営業日"の場合    
                else if (l_strBizDateType.compareTo(WEB3BizDateTypeDef.NO_BIZ_DATE) == 0)
                {
                    l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_NEXT_ORDER;
                    return l_strAcceptType;
                }
                //注文単位.発注日 ＝ 業務日付の場合
                else if (WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datBizDate) == 0)
                {
                    //取引時間管理.isオンラインサービス開始後()＝falseの場合
                    if (!(WEB3GentradeTradingTimeManagement.isOnlineServiceStartAfter()))
                    {
                        l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_NEXT_ORDER;
                        return l_strAcceptType;
                    }

                    //市場閉局時間を取得する
                    String l_strTradeCloseTime = 
                        WEB3GentradeTradingTimeManagement.getTradeCloseTime(
                            WEB3MarketCodeDef.DEFAULT, 
                            ((IfoProductImpl)l_orderUnit.getProduct()).getUnderlyingProductCode());

                    String l_strBizDate = 
                        GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datBizDate);

                    Date l_datTradeCloseTime = WEB3DateUtility.getDate(
                            l_strBizDate + l_strTradeCloseTime,
                            "yyyyMMddHHmmss");

                    //取引時間管理.isオンラインサービス開始後()＝trueかつ
                    //現在日時の時間部分 ≦ 取引時間管理.get市場閉局時間()の場合
                    if (WEB3GentradeTradingTimeManagement.isOnlineServiceStartAfter() &&
                        WEB3DateUtility.compareToSecond(l_tsSysTimestamp, l_datTradeCloseTime) <= 0)
                    {
                        l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_UNORDER;
                        return l_strAcceptType;
                    }
                }
            }

            //２）　@以下条件全てに該当する場合のみ処理する0。
            //　@　@　@　@・市場開局中　@（取引時間管理.is市場開局時間帯()が"true"の場合）
            //　@　@　@　@・立会時間帯ではない（取引時間管理.is立会時間帯()が"false"の場合）
            //           注文単位.立会区分 ＝ 取引時間管理.立会区分の場合
            //           - 注文単位.発注日＝ 取引時間管理.get発注日()の場合
            if (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone()
                && !WEB3GentradeTradingTimeManagement.isSessionTimeZone()
                && WEB3Toolkit.isEquals(l_orderUnitRow.getSessionType(),
                    WEB3GentradeTradingTimeManagement.getSessionType())
                && (WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datOrderBizDate) == 0))
            {
                //"発注未処理"を返却する。
                l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_UNORDER;
            }

            //未発注の有効な逆指値注文で市場開局時間帯の場合
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()) &&
                WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType()) &&
                OrderOpenStatusEnum.OPEN.equals(l_orderUnitRow.getOrderOpenStatus()) &&
                WEB3GentradeTradingTimeManagement.isSessionTimeZone())
            {
                l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_WAITING;
            }
            //未発注注文の場合
            else if (l_orderUnitRow.getConfirmedQuantityIsNull())
            {
                if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_ERROR;
                }
                else
                {
                    l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_NAN;
                }
            }
            else
            {
                l_strAcceptType = WEB3IfoAcceptTypeDef.EXEC_TYPE_NOT_NAN;
            }

            log.debug("受付区分 = " + l_strAcceptType);

            log.exiting(STR_METHOD_NAME);

            return l_strAcceptType;

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
     * 戻り値の注文内容区分：<BR>
     * 00：新規注文 01：注文受付 02：新規注文(失敗)<BR>
     * 03：訂正注文 04：訂正受付 05：訂正完了 06：訂正注文(失敗)<BR>
     * 07：取消注文 08：取消受付 09：取消完了 10：取消注文(失敗)<BR>
     * 11：一部約定 12：全部約定 13：約定取消<BR>
     * 14：失効 15：失効取消 16：無効<BR>
     * 17：注文繰越 18：注文繰越(失敗) 20：発注中 21：発注遅延 <BR>
     * 22：切替遅延 23：切替注文 24：切替受付 25：切替完了 26：切替注文(失敗)<BR>
     * 27：ストップ注文失効 99：その他<BR>
     * --------------------------------------------------------------------------<BR>
     * (1)約定<BR>
     * 　@注文履歴.注文イベントタイプ＝EXECUTE(約定)の場合、<BR>
     * 　@・注文履歴.注文数量＝注文履歴.約定数量ならば、"全部約定"を返す。<BR>
     * 　@・上記以外ならば、"一部約定"を返す。<BR>
     * <BR>
     * (2)失効<BR>
     * 　@注文履歴.注文イベントタイプ＝EXPIRE(失効済み) かつ<BR>
     * 　@注文履歴.注文失効ステータス＝INVALIDATED_BY_MKT(マーケット拒否)の場合、"失効"を返す。<BR>
     * <BR>
     * (3)注文繰越(失敗)、無効<BR>
     * 　@注文履歴.注文イベントタイプ＝EXPIRE(失効済み) かつ<BR>
     * 　@注文履歴.注文失効ステータス＝EXPIRED(終了)の場合、<BR>
     * 　@・注文履歴.注文エラー理由コード≠"0000：正常"ならば、"注文繰越(失敗)"を返す。<BR>
     * 　@・上記以外であれば、"無効"を返す。<BR>
     * <BR>
     * (4)ストップ注文失効 <BR>
     * 　@注文履歴.注文イベントタイプ＝EXPIRE(失効済み) かつ <BR>
     * 　@注文履歴.リクエストタイプ＝"失効"の場合、"ストップ注文失効"を返す。<BR> 
     * <BR>
     * (5)失効取消<BR>
     * 　@注文履歴.注文イベントタイプ＝UNDO_INVALIDATION_BY_MKT(失効取消)の場合、"失効取消"を返す。<BR>
     * <BR>
     * (6)約定取消<BR>
     * 　@注文履歴.注文イベントタイプ＝UNDO_EXECUTION(約定取消)の場合、"約定取消"を返す。<BR>
     * <BR>
     * (7)発注遅延<BR>
     * 　@注文履歴.注文イベントタイプ＝ORDER_DELAY（発注遅延）の場合、<BR>
     * 　@"発注遅延"を返す。<BR>
     *<BR>
     * (8)切替遅延 <BR>
     * 　@注文履歴.注文イベントタイプ＝SWITCH_DELAY（切替遅延）の場合、 <BR>
     * 　@"切替遅延"を返す。 <BR>
     * <BR>
     * (9)新規注文、注文繰越<BR>
     * 　@注文履歴.注文状態＝ACCEPTED(受付済(新規注文))の場合、<BR>
     * 　@・注文単位.初回注文の注文単位ID＝(null, 0)ならば、"新規注文"を返す。<BR>
     * 　@・上記以外ならば、"注文繰越"を返す。<BR>
     * <BR>
     * (10)注文受付<BR>
     * 　@注文履歴.注文状態＝ORDERD(発注済み（新規注文))の場合、"注文受付"を返す。<BR>
     * <BR>
     * (11)新規注文(失敗)<BR>
     * 　@注文履歴.注文状態＝NOT_ORDERD(発注失敗(新規注文))の場合、<BR>
     * 　@・"新規注文(失敗)"を返す。<BR>
     * <BR>
     * (12)訂正注文、切替注文 <BR>
     * 　@注文履歴.注文状態＝MODIFY_ACCEPTED(受付済(変更注文))の場合、 <BR>
     * 　@・注文履歴.注文訂正・取消区分＝"W指値注文切替中"ならば、"切替注文"を返す。<BR> 
     * 　@・上記以外ならば、"訂正注文"を返す。 <BR>
     * <BR>
     * (13)訂正受付、切替受付 <BR>
     * 　@注文履歴.注文状態＝MODIFYING(発注中(変更注文))の場合、 <BR>
     * 　@・注文履歴.注文訂正・取消区分＝"W指値注文切替中"ならば、"切替受付"を返す。<BR> 
     * 　@・上記以外ならば、"訂正受付"を返す。 <BR>
     * <BR>
     * (14)訂正完了、切替完了 <BR>
     * 　@注文履歴.注文状態＝MODIFIED(発注済み(変更注文))の場合、<BR> 
     * 　@・注文履歴.注文訂正・取消区分＝（"W指値注文一部切替完了"、<BR>
     * 　@　@"W指値注文全部切替完了"）ならば、"切替完了"を返す。 <BR>
     * 　@・上記以外ならば、"訂正完了"を返す。 <BR>
     * <BR>
     * (15)訂正注文(失敗)、切替注文(失敗) <BR>
     * 　@注文履歴.注文状態＝NOT_MODIFIED(発注失敗(変更注文))の場合、 <BR>
     * 　@・注文履歴.注文訂正・取消区分＝"W指値注文切替失敗"ならば、"切替注文(失敗)"を返す。<BR> 
     * 　@・上記以外ならば、"訂正注文(失敗)"を返す。 <BR>
     * <BR>
     * (16)取消注文<BR>
     * 　@注文履歴.注文状態＝CANCEL_ACCEPTED(受付済(取消注文))の場合、"取消注文"を返す。<BR>
     * <BR>
     * (17)取消受付<BR>
     * 　@注文履歴.注文状態＝CANCELLING(発注中(取消注文))の場合、"取消受付"を返す。<BR>
     * <BR>
     * (18)取消完了<BR>
     * 　@注文履歴.注文状態＝CANCELLED(発注済み(取消注文))の場合、"取消完了"を返す。<BR>
     * <BR>
     * (19)取消注文(失敗)<BR>
     * 　@注文履歴.注文状態＝NOT_CANCELLED(発注失敗(取消注文))の場合、"取消注文(失敗)"を返す。<BR>
     * <BR>
     * (20)発注中<BR>
     * 　@注文履歴.注文イベントタイプ＝SEND_TO_MKT（マーケット送信済（新規注文））の場合、<BR>
     * 　@"発注中"を返す。<BR>
     * <BR>
     * (21)上記以外の場合、"その他"を返す。<BR>
     * <BR>
     * @@param l_orderAction - 注文履歴オブジェクト。
     * @@param l_orderUnit - 注文単位オブジェクト。
     * @@return String
     */
    public static String getOrderSpecType(IfoOrderAction l_orderAction, IfoOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "getOrderSpecType(IfoOrderAction, IfoOrderUnit)";

        log.entering(STR_METHOD_NAME);
        //返却値の設定
        String l_strReturn = null;

        //注文履歴の取得
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_orderAction.getDataSourceObject();
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        OrderEventTypeEnum l_orderEventType = l_ifoOrderActionRow.getOrderEventType();
        String l_strRequestType = l_ifoOrderActionRow.getRequestType();
        
        log.debug("注文履歴.注文イベントタイプ＝" + l_orderEventType);
        OrderExpirationStatusEnum l_orderExpirationStatus = l_ifoOrderActionRow.getExpirationStatus();
        log.debug("注文履歴．注文失効ステータス＝" + l_orderExpirationStatus);

        //(1)約定
        if (OrderEventTypeEnum.EXECUTE.equals(l_orderEventType))
        {
            if (l_ifoOrderActionRow.getQuantity() == l_ifoOrderActionRow.getExecutedQuantity())
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.FULLY_EXECUTED;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.PARTIALLY_EXECUTED;
            }
        }
        //(2)失効 
        else if (OrderEventTypeEnum.EXPIRE.equals(l_orderEventType) &&
        OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderExpirationStatus))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.CLOSE;
        }
        //(3)注文繰越(失敗)、無効
        else if (OrderEventTypeEnum.EXPIRE.equals(l_orderEventType)
            && OrderExpirationStatusEnum.EXPIRED.equals(l_orderExpirationStatus))
        {                                                                               
            if (!WEB3ErrorReasonCodeDef.NORMAL.equals(l_ifoOrderActionRow.getErrorReasonCode()))
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.ORDER_CARRYOVER_FAIL;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.INEFFECTIVE;
            }
        }
        
        //(4)ストップ注文失効
        else if (OrderEventTypeEnum.EXPIRE.equals(l_orderEventType)
            && WEB3RequestTypeDef.INVALIDATE.equals(l_strRequestType))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.STOP_ORDER_EXPIRE;
        }
        //(5)失効取消 
        else if (OrderEventTypeEnum.UNDO_INVALIDATION_BY_MKT.equals(l_orderEventType))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.CLOSE_FAIL;
        }
        //(6)約定取消 
        else if (OrderEventTypeEnum.UNDO_EXECUTION.equals(l_orderEventType))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.EXCUTED_CANCEL;
        }
        //(7)発注遅延
        else if (OrderEventTypeEnum.ORDER_DELAY.equals(l_orderAction.getOrderEventType()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.ORDER_DELAY;
        }
        //(8)切替遅延
        else if (OrderEventTypeEnum.SWITCH_DELAY.equals(l_orderAction.getOrderEventType()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.SWITCH_DELAY;
        }
        //(9)新規注文、注文繰越
        else if (OrderStatusEnum.ACCEPTED.equals(l_orderAction.getOrderStatus()))
        {
            if (l_orderUnitRow.getFirstOrderUnitIdIsNull() || (l_orderUnitRow.getFirstOrderUnitId() == 0))
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.OPEN_ORDER;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.ORDER_CARRYOVER;
            }
        }
        //(10)注文受付 
        else if (OrderStatusEnum.ORDERED.equals(l_orderAction.getOrderStatus()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.ORDER_ACCEPT;
        }
        //(11)新規注文(失敗)
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderAction.getOrderStatus()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.OPEN_ORDER_FAIL;
        }
        //(12)訂正注文、切替注文
        else if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderAction.getOrderStatus()))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(
                l_ifoOrderActionRow.getModifyCancelType()))
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.SWITCH_ORDER;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.CHANGE_ORDER;
            }
        }
        //(13)訂正受付、切替受付
        else if (OrderStatusEnum.MODIFYING.equals(l_orderAction.getOrderStatus()))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERING.equals(
                l_ifoOrderActionRow.getModifyCancelType()))
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.SWITCH_ACCEPT;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.CHANGE_ACCEPT;
            }
        }
        //(14)訂正完了、切替完了
        else if (OrderStatusEnum.MODIFIED.equals(l_orderAction.getOrderStatus()))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_PARTIALLY_TRANSFERED.equals(
                l_ifoOrderActionRow.getModifyCancelType()) ||
                WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERED.equals(
                l_ifoOrderActionRow.getModifyCancelType()))
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.SWITCH_OVER;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.CHANGE_FINISH;
            }
        }
        //(15)訂正注文(失敗)、切替注文(失敗)
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderAction.getOrderStatus()))
        {
            if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR.equals(
                l_ifoOrderActionRow.getModifyCancelType()))
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.SWITCH_ORDER_FAIL;
            }
            else
            {
                l_strReturn = WEB3IfoOrderSpecDivDef.CHANGE_ORDER_FAIL;
            }
        }
        //(16)取消注文
        else if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderAction.getOrderStatus()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.CANCEL_ORDER;
        }
        //(17)取消受付
        else if (OrderStatusEnum.CANCELLING.equals(l_orderAction.getOrderStatus()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.CANCEL_ACCEPT;
        }
        //(18)取消完了
        else if (OrderStatusEnum.CANCELLED.equals(l_orderAction.getOrderStatus()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.CANCEL_FINISH;
        }
        //(19)取消注文(失敗)
        else if (OrderStatusEnum.NOT_CANCELLED.equals(l_orderAction.getOrderStatus()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.CANCEL_ORDER_FAIL;
        }
        //(20)発注中
        else if (OrderEventTypeEnum.SEND_TO_MKT.equals(l_orderAction.getOrderEventType()))
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.ORDERING;
        }
        //(21)上記以外の場合、"その他"を返す。
        else
        {
            l_strReturn = WEB3IfoOrderSpecDivDef.OTHER;
        }
        
        log.debug("注文内容区分 = " + l_strReturn);
        
        log.exiting(STR_METHOD_NAME);

        return l_strReturn;
    }
    
    /**
     * (get受付結果区分)<BR>
     * 指定された注文履歴の受付結果区分を返す。<BR>
     * <BR>
     * 戻り値の受付結果区分：<BR>
     * 0000：正常 1001：受付エラー 1002：訂正エラー <BR>
     * 1003：取消エラー 1004：切替エラー <BR>
     * 0001：値幅・刻み値エラー 0002：預り金不足エラー 0003：株価指数先物オプション残高不足エラー<BR>
     * 0004：保証金不足エラー 0005：建玉残高不足エラー 0006：売買停止銘柄エラー<BR>
     * 0007：市場変更銘柄エラー 0008：買付余力エラー 0009：売付可能数量エラー<BR>
     * 0010：特定口座エラー 0011：注文繰越スキップ銘柄エラー 0012：二階建チェックエラー<BR>
     * 0013：発注日チェックエラー 9001：その他エラー<BR>
     * <BR>
     * --------------------------------------------------------------------------<BR>
     * ・注文履歴.注文訂正・取消区分＝"取消失敗"の場合<BR>
     * <BR>
     * 　@"1003"（取消エラー）を返す。<BR>
     * <BR>
     * ・注文履歴.注文訂正・取消区分＝"訂正失敗"の場合<BR>
     * <BR>
     * 　@"1002"（訂正エラー）を返す。<BR>
     * <BR>
     * ・注文履歴.注文訂正・取消区分＝"W指値注文切替失敗"の場合、 <BR>
     * 　@"1004"（切替エラー）を返す。 <BR>
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
     * 注文履歴オブジェクト。
     * @@return String
     */
    public static String getAcceptResultType(IfoOrderAction l_orderAction)
    {
        final String STR_METHOD_NAME =
            "getAcceptResultType(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderActionRow l_orderActionRow =
            (IfoOrderActionRow)l_orderAction.getDataSourceObject();
        String l_strReturn = null;
        
        // ・注文履歴.注文訂正・取消区分＝（取消失敗）の場合
        if (WEB3ModifyCancelTypeDef.CANCEL_ERROR.equals(l_orderActionRow.getModifyCancelType()))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.CANCEL_ERROR;
        }
        // ・注文履歴.注文訂正・取消区分＝（訂正失敗）の場合
        else if (WEB3ModifyCancelTypeDef.CHANGE_ERROR.equals(l_orderActionRow.getModifyCancelType()))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.CHANGE_ERROR;
        }
        //注文履歴.注文訂正・取消区分＝"W指値注文切替失敗"の場合
        else if (WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR.equals(l_orderActionRow.getModifyCancelType()))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.TRANSFER_ERROR;
        }
        // ・注文履歴.注文状態＝NOT_ORDERED（発注失敗（新規注文））
        // 　@かつ　@注文履歴.注文イベントタイプ＝REJECTED_BY_MARKET（マーケット拒否（新規注文））
        // 　@の場合
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderAction.getOrderStatus()) &&
                OrderEventTypeEnum.REJECTED_BY_MKT.equals(l_orderAction.getOrderEventType()))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.ACCEPT_ERROR;
        }
        // ・上記以外の場合
        else
        {
            l_strReturn = l_orderActionRow.getErrorReasonCode();
        }

        log.debug("受付結果区分 = " + l_strReturn);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_strReturn;
    }
    
    /**
     * (getＷ指値用有効状態区分)<BR>
     * 引数の注文単位よりＷ指値用有効状態区分を返却する。<BR> 
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR> 
     * 　@nullを返却する。 <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 <BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。<BR> 
     * 　@[isストップ注文有効()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。 <BR>
     * 　@[isストップ注文失効済()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。 <BR>
     * 　@[３）の戻り値 == trueの場合] <BR>
     * 　@　@"ストップ注文失効済"を返却する。 <BR>
     * 　@[２）の戻り値 == trueの場合] <BR>
     * 　@　@"ストップ注文有効"を返却する。 <BR>
     * 　@[上記以外の場合] <BR>
     * 　@　@"リミット注文有効"を返却する。 <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitEnableStatusDiv(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitEnableStatusDiv(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文単位.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
    		l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
       
        // ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(l_orderUnit);
               
        // ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。 
        // 　@[isストップ注文失効済()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(l_orderUnit);
        
        // ４）　@２）、３）の戻り値により、返却値を決定する。 
        // 　@[３）の戻り値 == trueの場合] 
        // 　@　@"ストップ注文失効済"を返却する。 
        // 　@[２）の戻り値 == trueの場合] 
        // 　@　@"ストップ注文有効"を返却する。 
        // 　@[上記以外の場合] 
        // 　@　@"リミット注文有効"を返却する。 
        if (l_blnStopOrderExpire)
        {
        	String l_strStatusDiv = WEB3IfoWLimitEnableStatusDivDef.STOP_UN_ENABLE;
        	log.exiting(STR_METHOD_NAME);
        	return l_strStatusDiv;
        }       
        else if (l_blnStopOrderOpen)
        {
        	String l_strStatusDiv = WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE;
        	log.exiting(STR_METHOD_NAME);
        	return l_strStatusDiv;
        }
        
        String l_strStatusDiv = WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE;
    	log.exiting(STR_METHOD_NAME);       
        return l_strStatusDiv;
    }
    
    /**
     * (getＷ指値用有効状態区分)<BR>
     * 引数の注文履歴よりＷ指値用有効状態区分を返却する。<BR> 
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR> 
     * 　@nullを返却する。 <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 <BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。 <BR>
     * 　@[isストップ注文有効()に指定する引数] <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴 <BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR> 
     * 　@[isストップ注文失効済()に指定する引数] <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴 <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。 <BR>
     * 　@[３）の戻り値 == trueの場合] <BR>
     * 　@　@"ストップ注文失効済"を返却する。 <BR>
     * 　@[２）の戻り値 == trueの場合] <BR>
     * 　@　@"ストップ注文有効"を返却する。 <BR>
     * 　@[上記以外の場合] <BR>
     * 　@　@"リミット注文有効"を返却する。<BR>
     * @@param l_ifoOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitEnableStatusDiv(IfoOrderAction l_ifoOrderAction)
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitEnableStatusDiv(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文履歴.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderActionRow.getOrderConditionType(), 
    		l_ifoOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
       
        // ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文履歴：　@パラメータ.注文履歴 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_ifoOrderAction);
               
        // ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。 
        // 　@[isストップ注文失効済()に指定する引数] 
        // 　@　@注文履歴：　@パラメータ.注文履歴 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_ifoOrderAction);
        
        // ４）　@２）、３）の戻り値により、返却値を決定する。 
        // 　@[３）の戻り値 == trueの場合] 
        // 　@　@"ストップ注文失効済"を返却する。 
        // 　@[２）の戻り値 == trueの場合] 
        // 　@　@"ストップ注文有効"を返却する。 
        // 　@[上記以外の場合] 
        // 　@　@"リミット注文有効"を返却する。
        if (l_blnStopOrderExpire)
        {
        	String l_strStatusDiv = WEB3IfoWLimitEnableStatusDivDef.STOP_UN_ENABLE;
        	log.exiting(STR_METHOD_NAME);
        	return l_strStatusDiv;
        }       
        else if (l_blnStopOrderOpen)
        {
        	String l_strStatusDiv = WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE;
        	log.exiting(STR_METHOD_NAME);
        	return l_strStatusDiv;
       }
        
        String l_strStatusDiv = WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE;
    	log.exiting(STR_METHOD_NAME);       
        return l_strStatusDiv;
    }
    
    /**
     * (getＷ指値用切替前注文単価)<BR>
     * 引数の注文単位よりＷ指値用切替前注文単価を返却する。<BR> 
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR> 
     * 　@nullを返却する。 <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 <BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。<BR> 
     * 　@[isストップ注文有効()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文切替中()メソッドをコールする。 <BR>
     * 　@[isストップ注文切替中()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。 <BR>
     * 　@[３）の戻り値 == trueの場合] <BR>
     * 　@　@パラメータ.注文単位.市場から確認済みの指値を返却する。 <BR>
     * 　@[２）の戻り値 == trueの場合] <BR>
     * 　@　@パラメータ.注文単位.（W指値）切替前指値を返却する。 <BR>
     * 　@[上記以外] <BR>
     * 　@　@パラメータ.注文単位.指値を返却する。 <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitBefSwitchPrice(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitBefSwitchPrice(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文単位.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
    		l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        // ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnIsStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_orderUnit);
        
        // ３）　@OP注文マネージャ.isストップ注文切替中()メソッドをコールする。 
        // 　@[isストップ注文切替中()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnIsStopOrderChanging = l_optionOrderManagerImpl.isStopOrderSwitching(
    		l_orderUnit);
        
        // ４）　@２）、３）の戻り値により、返却値を決定する。 
        // 　@[３）の戻り値 == trueの場合] 
        // 　@　@パラメータ.注文単位.市場から確認済みの指値を返却する。 
        if (l_blnIsStopOrderChanging)
        {
            String l_strConfirmedPrice = null;
            if (!l_ifoOrderUnitRow.getConfirmedPriceIsNull())
            {
                l_strConfirmedPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getConfirmedPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strConfirmedPrice;
        }
        
        // 　@[２）の戻り値 == trueの場合] 
        // 　@　@パラメータ.注文単位.（W指値）切替前指値を返却する。
        else if (l_blnIsStopOrderOpen)
        {
            String l_strWLimitBeforeLimitPrice = null;
            if (!l_ifoOrderUnitRow.getWLimitBeforeLimitPriceIsNull())
            {
                l_strWLimitBeforeLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getWLimitBeforeLimitPrice());
            }
            log.exiting(STR_METHOD_NAME);
            return l_strWLimitBeforeLimitPrice;
        }
        
        // 　@[上記以外] 
        // 　@　@パラメータ.注文単位.指値を返却する。
        String l_strLimitPrice = null;
        if (!l_ifoOrderUnitRow.getLimitPriceIsNull())
        {
            l_strLimitPrice = WEB3StringTypeUtility.formatNumber(
                l_ifoOrderUnitRow.getLimitPrice());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strLimitPrice;
    }
    
    /**
     * (getＷ指値用切替前注文単価)<BR>
     * 引数の注文履歴よりＷ指値用切替前注文単価を返却する。<BR> 
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR> 
     * 　@nullを返却する。 <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 <BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。<BR> 
     * 　@[isストップ注文有効()に指定する引数] <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴 <BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文切替中()メソッドをコールする。<BR> 
     * 　@[isストップ注文切替中()に指定する引数] <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴<BR> 
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。 <BR>
     * 　@[３）の戻り値 == trueの場合] <BR>
     * 　@　@パラメータ.注文履歴.市場から確認済みの指値を返却する。<BR> 
     * 　@[２）の戻り値 == trueの場合] <BR>
     * 　@　@パラメータ.注文履歴.（W指値）切替前指値を返却する。 <BR>
     * 　@[上記以外] <BR>
     * 　@　@パラメータ.注文履歴.注文単価を返却する。<BR>
     * @@param l_ifoOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitBefSwitchPrice(IfoOrderAction l_ifoOrderAction) 
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWlimitBefSwitchPrice(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文履歴.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderActionRow.getOrderConditionType(), 
    		l_ifoOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        	
        // ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文履歴：　@パラメータ.注文履歴 
        boolean l_blnIsStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_ifoOrderAction);
        
        // ３）　@OP注文マネージャ.isストップ注文切替中()メソッドをコールする。 
        // 　@[isストップ注文切替中()に指定する引数] 
        // 　@　@注文履歴：　@パラメータ.注文履歴 
        boolean l_blnIsStopOrderChanging = l_optionOrderManagerImpl.isStopOrderSwitching(
    		l_ifoOrderAction);
        
        // ４）　@２）、３）の戻り値により、返却値を決定する。 
        // 　@[３）の戻り値 == trueの場合] 
        // 　@　@パラメータ.注文履歴.市場から確認済みの指値を返却する。 
        if (l_blnIsStopOrderChanging)
        {
            String l_strConfirmedPrice = null;
            if (!l_ifoOrderActionRow.getConfirmedPriceIsNull())
            {
                l_strConfirmedPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderActionRow.getConfirmedPrice());
            }
        	log.exiting(STR_METHOD_NAME);
        	return l_strConfirmedPrice;
        }
        
        // 　@[２）の戻り値 == trueの場合] 
        // 　@　@パラメータ.注文履歴.（W指値）切替前指値を返却する。 
        else if (l_blnIsStopOrderOpen)
        {
            String l_strWLimitBeforeLimitPrice = null;
            if (!l_ifoOrderActionRow.getWLimitBeforeLimitPriceIsNull())
            {
                l_strWLimitBeforeLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderActionRow.getWLimitBeforeLimitPrice());
            }
        	log.exiting(STR_METHOD_NAME);
        	return l_strWLimitBeforeLimitPrice;
        }
        
        // 　@[上記以外] 
        // 　@　@パラメータ.注文履歴.注文単価を返却する。
        String l_strPrice = null;
        if (!l_ifoOrderActionRow.getPriceIsNull())
        {
            l_strPrice = WEB3StringTypeUtility.formatNumber(
                l_ifoOrderActionRow.getPrice());
        }
        log.exiting(STR_METHOD_NAME);
        return l_strPrice;
    }
    
    /**
     * (getＷ指値用切替前執行条件)<BR>
     * 引数の注文単位よりＷ指値用切替前執行条件を返却する。<BR> 
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、 <BR>
     * 　@nullを返却する。 <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 <BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。<BR> 
     * 　@[isストップ注文有効()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文切替中()メソッドをコールする。 <BR>
     * 　@[isストップ注文切替中()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、執行条件を決定する。<BR> 
     * 　@[３）の戻り値 == trueの場合] <BR>
     * 　@　@執行条件 = パラメータ.注文単位.市場から確認済みの執行条件 <BR>
     * 　@[２）の戻り値 == trueの場合] <BR>
     * 　@　@執行条件 = パラメータ.注文単位.（W指値）切替前執行条件 <BR>
     * 　@[上記以外] <BR>
     * 　@　@執行条件 = パラメータ.注文単位.執行条件 <BR>
     * <BR>
     * ５）　@this.get執行条件（PR層）()メソッドをコールし、 <BR>
     * 　@戻り値を返却する。 <BR>
     * <BR>
     * 　@[get執行条件（PR層）()に指定する引数] <BR>
     * 　@　@執行条件：　@４）にて決定した執行条件 <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitBefSwitchExecCondType(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitBefSwitchExecCondType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文単位.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
    		l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        	
        // ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnIsStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_orderUnit);
        
        // ３）　@OP注文マネージャ.isストップ注文切替中()メソッドをコールする。 
        // 　@[isストップ注文切替中()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnIsStopOrderChanging = l_optionOrderManagerImpl.isStopOrderSwitching(
    		l_orderUnit);
        
        // ４）　@２）、３）の戻り値により、執行条件を決定する。 
        // 　@[３）の戻り値 == trueの場合] 
        // 　@　@執行条件 = パラメータ.注文単位.市場から確認済みの執行条件        
        IfoOrderExecutionConditionType l_execConditionType = null;
        if (l_blnIsStopOrderChanging)
        {
        	l_execConditionType = 
    			l_ifoOrderUnitRow.getConfirmedExecConditionType();
        }
        
        // 　@[２）の戻り値 == trueの場合] 
        // 　@　@執行条件 = パラメータ.注文単位.（W指値）切替前執行条件 
        else if (l_blnIsStopOrderOpen)
        {
        	l_execConditionType = 
    			l_ifoOrderUnitRow.getWLimitBeforeExecCondType();
        }
        else
        {
            // 　@[上記以外] 
            // 　@　@執行条件 = パラメータ.注文単位.執行条件 
        	l_execConditionType = l_ifoOrderUnitRow.getExecutionConditionType();
        }

        // ５）　@this.get執行条件（PR層）()メソッドをコールし、 
        // 　@戻り値を返却する。 
        // 　@[get執行条件（PR層）()に指定する引数] 
        // 　@　@執行条件：　@４）にて決定した執行条件
        String l_strWLimitBefChgExecCondType = getExecutionCondByPr(l_execConditionType);
        log.exiting(STR_METHOD_NAME);
        return l_strWLimitBefChgExecCondType;
    }
    
    /**
     * (getＷ指値用切替前執行条件)<BR>
     * 引数の注文履歴よりＷ指値用切替前執行条件を返却する。 <BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR> 
     * 　@nullを返却する。 <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 <BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。 <BR>
     * 　@[isストップ注文有効()に指定する引数] <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴 <BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文切替中()メソッドをコールする。<BR> 
     * 　@[isストップ注文切替中()に指定する引数] <BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴 <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、執行条件を決定する。 <BR>
     * 　@[３）の戻り値 == trueの場合] <BR>
     * 　@　@執行条件 = パラメータ.注文履歴.市場から確認済みの執行条件 <BR>
     * 　@[２）の戻り値 == trueの場合] <BR>
     * 　@　@執行条件 = パラメータ.注文履歴.（W指値）切替前執行条件 <BR>
     * 　@[上記以外] <BR>
     * 　@　@執行条件 = パラメータ.注文履歴.執行条件 <BR>
     * <BR>
     * ５）　@this.get執行条件（PR層）()メソッドをコールし、<BR> 
     * 　@戻り値を返却する。 <BR>
     * <BR>
     * 　@[get執行条件（PR層）()に指定する引数] <BR>
     * 　@　@執行条件：　@４）にて決定した執行条件 <BR>
     * @@param l_ifoOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitBefSwitchExecCondType(IfoOrderAction l_ifoOrderAction) 
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitBefSwitchExecCondType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文履歴.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderActionRow.getOrderConditionType(), 
    		l_ifoOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        	
        // ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文履歴：　@パラメータ.注文履歴 
        boolean l_blnIsStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_ifoOrderAction);
        
        // ３）　@OP注文マネージャ.isストップ注文切替中()メソッドをコールする。 
        // 　@[isストップ注文切替中()に指定する引数] 
        // 　@　@注文履歴：　@パラメータ.注文履歴 
       boolean l_blnIsStopOrderChanging = l_optionOrderManagerImpl.isStopOrderSwitching(
    		l_ifoOrderAction);
        
        // ４）　@２）、３）の戻り値により、執行条件を決定する。 
        // 　@[３）の戻り値 == trueの場合] 
        // 　@　@執行条件 = パラメータ.注文履歴.市場から確認済みの執行条件    
        IfoOrderExecutionConditionType l_execConditionType = null;
        if (l_blnIsStopOrderChanging)
        {
        	l_execConditionType = 
        		l_ifoOrderActionRow.getConfirmedExecConditionType();
        }
        
        // 　@[２）の戻り値 == trueの場合] 
        // 　@　@執行条件 = パラメータ.注文履歴.（W指値）切替前執行条件 
        else if (l_blnIsStopOrderOpen)
        {
        	l_execConditionType = 
        		l_ifoOrderActionRow.getWLimitBeforeExecCondType();
        }
        else
        {
            // 　@[上記以外] 
            // 　@　@執行条件 = パラメータ.注文履歴.執行条件 
        	l_execConditionType = l_ifoOrderActionRow.getExecutionConditionType();
        }

        // ５）　@this.get執行条件（PR層）()メソッドをコールし、 
        // 　@戻り値を返却する。 
        // 　@[get執行条件（PR層）()に指定する引数] 
        // 　@　@執行条件：　@４）にて決定した執行条件
        String l_strWLimitBefChgExecCondType = getExecutionCondByPr(l_execConditionType);
        log.exiting(STR_METHOD_NAME);
        return l_strWLimitBefChgExecCondType;
    }

    /**
     * (get遅延区分)<BR>
     * 引数の注文単位よりトリガー注文の遅延区分を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 == "DEFAULT(条件指定なし)"の場合、 <BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 <BR>
     * <BR>
     * ２）OP注文マネージャ.is遅延注文(パラメータ.注文単位) == trueの場合、<BR>
     *　@　@　@"遅延"を返却する。 <BR>
     *　@　@　@以外、"正常"を返却する<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getDelayDiv(IfoOrderUnit l_orderUnit)
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getDelayDiv(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }

        // １）　@this.get発注条件()の戻り値 == "DEFAULT(条件指定なし)"の場合、
        // 　@nullを返却する。
        // 　@[get発注条件()に指定する引数]
        // 　@　@発注条件：　@パラメータ.注文単位.発注条件
        // 　@　@元発注条件：　@パラメータ.注文単位.元発注条件
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(),
    		l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 == 'DEFAULT(条件指定なし)'の場。");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@OP注文マネージャ.is遅延注文(パラメータ.注文単位) == trueの場合、
        //　@"遅延"を返却する。
        //　@以外、"正常"を返却する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
        if (l_orderManager.isDelayOrder(l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
        	return WEB3DelayDivDef.DELAY;
        }

        log.exiting(STR_METHOD_NAME);
        return WEB3DelayDivDef.NORMAL;
    }

    /**
     * (getW指値発注状況区分)<BR>
     * パラメータ.注文単位より <BR>
     * PR層で使用するW指値注文の発注状況区分を返却する。<BR>
     * <BR>
     * １）　@発注状況区分の判定 <BR>
     * <BR>
     *　@ １－１）　@発注遅延エラー（切替遅延エラー）の判定
     *　@　@OP注文マネージャ.is未発注遅延注文(注文単位) == trueの場合、
     *　@　@"発注遅延エラー"を返却する。
     * 　@１－２）　@待機@中（切替未済）の判定 <BR>
     * 　@　@注文単位.リクエストタイプ＝"DEFAULT"の場合、"待機@中"を返却する。<BR>
     * <BR>
     * 　@１－３）　@発注中（切替中）の判定<BR>
     * 　@　@注文単位.リクエストタイプ＝"時価サーバ"の場合、"発注中"を返却する。<BR>
     * <BR>
     * 　@１－４）　@発注完了（切替完了）の判定 <BR>
     * 　@　@注文単位.リクエストタイプ＝"切替完了"の場合、"発注完了"を返却する。<BR>
     * <BR>
     * 　@１－５）　@ストップ注文失効の判定<BR>
     * 　@　@注文単位.リクエストタイプ＝"失効"の場合、"ストップ注文失効"を返却する。<BR>
     * <BR>
     * 　@１－６）　@上記以外の場合、"その他"を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitOrderStatusType(IfoOrderUnit l_orderUnit)
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitOrderStatusType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strRequestType = l_ifoOrderUnitRow.getRequestType();

        //１）　@発注状況区分の判定
        //　@１－１）　@発注遅延エラー（切替遅延エラー）の判定
        //　@　@OP注文マネージャ.is未発注遅延注文(注文単位) == trueの場合、
        //　@　@"発注遅延エラー"を返却する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
        if (l_orderManager.isNotOrderedDelay(l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR;
        }

        // １）　@発注状況区分の判定
        // 　@１－2 ）　@待機@中（切替未済）の判定
        // 　@　@注文単位.リクエストタイプ＝"DEFAULT"の場合、"待機@中"を返却する。
        if (WEB3RequestTypeDef.DEFAULT.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
        	return WEB3TriggerOrderStatusDef.ORDER_WAITING;
        }

        // 　@１－3）　@発注中（切替中）の判定
        // 　@　@注文単位.リクエストタイプ＝"時価サーバ"の場合、"発注中"を返却する。
        if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
        	return WEB3TriggerOrderStatusDef.ORDERING;
        }

        // 　@１－4）　@発注完了（切替完了）の判定
        // 　@　@注文単位.リクエストタイプ＝"切替完了"の場合、"発注完了"を返却する。
        if (WEB3RequestTypeDef.TRANSFERED.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
        	return WEB3TriggerOrderStatusDef.ORDER_COMPLETE;
        }

        // 　@１－６）　@ストップ注文失効の判定
        // 　@　@注文単位.リクエストタイプ＝"失効"の場合、"ストップ注文失効"を返却する。
        if (WEB3RequestTypeDef.INVALIDATE.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
        	return WEB3TriggerOrderStatusDef.STOP_ORDER_INVALIDATION;
        }

        // 　@１－７）　@上記以外の場合、"その他"を返却する。
        log.exiting(STR_METHOD_NAME);
        return WEB3TriggerOrderStatusDef.OTHER;

    }

    /**
     * (getＷ指値用執行条件)<BR>
     * 引数の注文単位よりＷ指値用執行条件を返却する。<BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。 <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 <BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却する執行条件を決定する。<BR>
     * 　@[３）の戻り値 == trueの場合] <BR>
     * 　@　@執行条件 = パラメータ.注文単位.元（W指値）執行条件 <BR>
     * 　@[２）の戻り値 == trueの場合] <BR>
     * 　@　@執行条件 = パラメータ.注文単位.執行条件 <BR>
     * 　@[上記以外] <BR>
     * 　@　@執行条件 = パラメータ.注文単位.（W指値）執行条件 <BR>
     * <BR>
     * ５）　@this.get執行条件（PR層）()メソッドをコールし、<BR>
     * 　@戻り値を返却する。 <BR>
     * <BR>
     * 　@[get執行条件（PR層）()に指定する引数] <BR>
     * 　@　@執行条件：　@４）にて決定した執行条件 <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitExecCondType(IfoOrderUnit l_orderUnit)
    	throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "getWLimitExecCondType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文単位.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
    		l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        // ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(l_orderUnit);
               
        // ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。 
        // 　@[isストップ注文失効済()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(l_orderUnit);
               
        // ４）　@２）、３）の戻り値により、返却する執行条件を決定する。 
        // 　@[３）の戻り値 == trueの場合] 
        // 　@　@執行条件 = パラメータ.注文単位.元（W指値）執行条件 
        IfoOrderExecutionConditionType l_executionConditionType = null;
        if (l_blnStopOrderExpire)
        {
            l_executionConditionType = 
    			l_ifoOrderUnitRow.getOrgWLimitExecCondType();        	
        }
        
        // 　@[２）の戻り値 == trueの場合] 
        // 　@　@執行条件 = パラメータ.注文単位.執行条件 
        else if (l_blnStopOrderOpen)
        {
            l_executionConditionType = 
        		l_ifoOrderUnitRow.getExecutionConditionType();
        }
        else
        {
            // 　@[上記以外] 
            // 　@　@執行条件 = パラメータ.注文単位.（W指値）執行条件 
            l_executionConditionType = 
    			l_ifoOrderUnitRow.getWLimitExecCondType();  
        }
        
        // ５）　@this.get執行条件（PR層）()メソッドをコールし、 
        // 　@戻り値を返却する。 
        // 　@[get執行条件（PR層）()に指定する引数] 
        // 　@　@執行条件：　@４）にて決定した執行条件                             
        String l_strWLimitExecCondType = getExecutionCondByPr(l_executionConditionType);
        log.exiting(STR_METHOD_NAME);
        return l_strWLimitExecCondType;
    }
    
    /**
     * (getＷ指値用注文単価区分)<BR>
     * 引数の注文単位よりＷ指値用注文単価区分を返却する。<BR> 
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、 <BR>
     * 　@nullを返却する。 <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 <BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()をコールする。 <BR>
     * 　@[isストップ注文有効()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR> 
     * 　@[isストップ注文失効済()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値の注文単価区分を決定する。<BR> 
     * 　@[３）の戻り値 == trueの場合] <BR>
     * 　@　@パラメータ.注文単位.元（W指値）訂正指値 == 0であれば、"成行"、 <BR>
     * 　@　@以外は"指値"を返却する。 <BR>
     * 　@[２）の戻り値 == trueの場合] <BR>
     * 　@　@パラメータ.注文単位.isMarketOrder == trueであれば、"成行"、 <BR>
     * 　@　@以外は"指値"を返却する。 <BR>
     * 　@[上記以外] <BR>
     * 　@　@パラメータ.注文単位.（W指値）訂正指値 == 0であれば、"成行"、 <BR>
     * 　@　@以外は"指値"を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitOrderPriceDiv(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPriceDiv(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ値がNULL
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文単位.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
      
        // ２）　@OP注文マネージャ.isストップ注文有効()をコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_orderUnit);
        
        // ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。 
        // 　@[isストップ注文失効済()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位    
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_orderUnit);
        
        // ４）　@２）、３）の戻り値により、返却値の注文単価区分を決定する。 
        // 　@[３）の戻り値 == trueの場合] 
        // 　@　@パラメータ.注文単位.元（W指値）訂正指値 == 0であれば、"成行"、 
        // 　@　@以外は"指値"を返却する。 
        if (l_blnStopOrderExpire)
        {
        	double l_dblOrgWLimitPrice = l_ifoOrderUnitRow.getOrgWLimitPrice();
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
        
        // 　@[２）の戻り値 == trueの場合] 
        // 　@　@パラメータ.注文単位.isMarketOrder == trueであれば、"成行"、 
        // 　@　@以外は"指値"を返却する。 
        if (l_blnStopOrderOpen)
        {
        	if (l_orderUnit.isMarketOrder())
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
        
        // 　@[上記以外] 
        // 　@　@パラメータ.注文単位.（W指値）訂正指値 == 0であれば、"成行"、 
        // 　@　@以外は"指値"を返却する。
        else if (l_ifoOrderUnitRow.getWLimitPrice() == 0)
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
     * 引数の注文単位よりＷ指値用注文単価を返却する。 <BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、 <BR>
     * 　@nullを返却する。 <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数] <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件 <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 <BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。<BR> 
     * 　@[isストップ注文有効()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。 <BR>
     * 　@[isストップ注文失効済()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。 <BR>
     * 　@[３）の戻り値 == trueの場合] <BR>
     * 　@　@パラメータ.注文単位.元（W指値）訂正指値を返却する。<BR> 
     * 　@[２）の戻り値 == trueの場合] <BR>
     * 　@　@パラメータ.注文単位.指値を返却する。 <BR>
     * 　@[上記以外] <BR>
     * 　@　@パラメータ.注文単位.（W指値）訂正指値を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getWLimitOrderPrice(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPrice(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ値がNULL
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文単位.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
        	new WEB3OptionOrderManagerImpl();
        
        // ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_orderUnit);
        
        // ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。 
        // 　@[isストップ注文失効済()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_orderUnit);
        
        // ４）　@２）、３）の戻り値により、返却値を決定する。 
        // 　@[３）の戻り値 == trueの場合] 
        // 　@　@パラメータ.注文単位.元（W指値）訂正指値を返却する。 
        if (l_blnStopOrderExpire)
        {
        	String l_strOrgWLimitPrice = null;
            if (!l_ifoOrderUnitRow.getOrgWLimitPriceIsNull()) 
            {
                l_strOrgWLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getOrgWLimitPrice()); 
            }
            log.exiting(STR_METHOD_NAME);
        	return l_strOrgWLimitPrice;
        }
        
        // 　@[２）の戻り値 == trueの場合] 
        // 　@　@パラメータ.注文単位.指値を返却する。 
        else if (l_blnStopOrderOpen)
        {
            String l_strLimitPrice = null;
            if (!l_ifoOrderUnitRow.getLimitPriceIsNull())
            {
                l_strLimitPrice = WEB3StringTypeUtility.formatNumber(
                        l_ifoOrderUnitRow.getLimitPrice());
            }
        	log.exiting(STR_METHOD_NAME);
        	return l_strLimitPrice;
        }
        
        // 　@[上記以外] 
        // 　@　@パラメータ.注文単位.（W指値）訂正指値を返却する。
        String l_strWLimitPrice = null;
        if (!l_ifoOrderUnitRow.getWLimitPriceIsNull())
        {
            l_strWLimitPrice = WEB3StringTypeUtility.formatNumber(
                l_ifoOrderUnitRow.getWLimitPrice());
        }
    	log.exiting(STR_METHOD_NAME);
        return l_strWLimitPrice;
    }

    /**
     * (get発注条件)<BR>
     * 引数の発注条件、元発注条件の内、<BR> 
     * nullでない方の発注条件を返却する。<BR>
     * <BR>                                 
     * [パラメータ.元発注条件 != nullの場合]<BR>
     * 　@パラメータ.元発注条件を返却する。<BR>
     * <BR>                                
     * [パラメータ.発注条件 != nullの場合]<BR>
     * 　@パラメータ.発注条件を返却する。<BR>
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_strOrgOrderConditionType - (元発注条件)<BR>
     * 元発注条件<BR>
     * @@return String
     */
    public static String getOrderConditionType(String l_strOrderConditionType, 
		String l_strOrgOrderConditionType)
    {
        final String STR_METHOD_NAME = "getOrderConditionType(String, String)";
        log.entering(STR_METHOD_NAME);
        
        // 引数の発注条件、元発注条件の内、 
        // nullでない方の発注条件を返却する。   
        // [パラメータ.元発注条件 != nullの場合]
        // 　@パラメータ.元発注条件を返却する。
        if (l_strOrgOrderConditionType != null)
        {
            log.exiting(STR_METHOD_NAME);
        	return l_strOrgOrderConditionType;
        }
                    
        // [パラメータ.発注条件 != nullの場合]
        // 　@パラメータ.発注条件を返却する。
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
     * 引数の発注条件演算子、元発注条件演算子の内、<BR>
     * 有効な発注条件演算子を返却する。<BR>            
     * <BR>                                            
     * [パラメータ.元発注条件演算子 != nullの場合] <BR>
     * 　@パラメータ.元発注条件演算子を返却する。<BR>   
     * <BR>                                            
     * [パラメータ.発注条件演算子 != nullの場合]<BR>   
     * 　@パラメータ.発注条件演算子を返却する。<BR>     
     * @@param l_strOrderCondOperator - (発注条件演算子)<BR>
     * 発注条件演算子<BR>
     * @@param l_strOrgOrderCondOperator - (元発注条件演算子)<BR>
     * 元発注条件演算子<BR>
     * @@return String
     */
    public static String getOrderCondOperator(String l_strOrderCondOperator, 
		String l_strOrgOrderCondOperator)
    {
        final String STR_METHOD_NAME = "getOrderCondOperator(String , String )";
        log.entering(STR_METHOD_NAME);    
        
        // 引数の発注条件演算子、元発注条件演算子の内、
        // 有効な発注条件演算子を返却する。                                                     
        // [パラメータ.元発注条件演算子 != nullの場合] 
        // 　@パラメータ.元発注条件演算子を返却する。  
        if (l_strOrgOrderCondOperator != null)
        {
            log.exiting(STR_METHOD_NAME);
        	return l_strOrgOrderCondOperator;
        }
                     
        // [パラメータ.発注条件演算子 != nullの場合]        
        // 　@パラメータ.発注条件演算子を返却する。    
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
     * １）　@発注条件指定なしの場合<BR>
     * <BR>
     * this.get発注条件(）＝"DEFAULT（条件指定なし）"の場合、<BR>
     * nullを返却する。<BR>
     * <BR>
     * [get発注条件()に指定する引数]<BR>
     * 　@発注条件：　@注文単位.発注条件<BR>
     * 　@元発注条件：　@注文単位.元発注条件<BR>
     * <BR>
     * ２）　@以外の場合<BR>
     * <BR>
     * this.get発注条件演算子()に処理を委譲する。<BR>
     * <BR>
     * [get発注条件演算子()に指定する引数]<BR>
     * 　@発注条件演算子：　@注文単位.発注条件演算子<BR>
     * 　@元発注条件演算子：　@注文単位.元発注条件演算子<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrderCondOperator(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderCondOperator(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        String l_strOrderCondOperatorReturn = null;
        
        //パラメータ値がNULL
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
       
        // 引数の注文単位より、有効な発注条件演算子を返却する。                                                                          
        // １）　@発注条件指定なしの場合<BR>                                                                                                   
        // this.get発注条件(）＝"DEFAULT（条件指定なし）"の場合、       
        // nullを返却する。      
        // [get発注条件()に指定する引数]                                   
        // 　@発注条件：　@注文単位.発注条件                                
        // 　@元発注条件：　@注文単位.元発注条件      
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))  
        {
            log.debug("this.get発注条件(）＝DEFAULT（条件指定なし）の場合。");   
        }
        else 
        {
            // ２）　@以外の場合                                                                                                                
            // this.get発注条件演算子()に処理を委譲する。                                                                                   
            // [get発注条件演算子()に指定する引数]                           
            // 　@発注条件演算子：　@注文単位.発注条件演算子                     
            // 　@元発注条件演算子：　@注文単位.元発注条件演算子 
        	l_strOrderCondOperatorReturn = getOrderCondOperator(
    			l_ifoOrderUnitRow.getOrderCondOperator(), 
    			l_ifoOrderUnitRow.getOrgOrderCondOperator());
            log.debug("this.get発注条件(）＝DEFAULT（条件指定なし）以外の場合。");   
        }          
        
        log.debug("発注条件演算子 = " + l_strOrderCondOperatorReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strOrderCondOperatorReturn;
    }
    
    /**
     * (get逆指値基準値タイプ)<BR>
     * 引数の逆指値基準値タイプ、元逆指値基準値タイプの内、<BR>
     * 有効な逆指値基準値タイプを返却する。<BR>
     * <BR>
     * [パラメータ.元逆指値基準値タイプ != nullの場合]<BR>
     * 　@パラメータ.元逆指値基準値タイプを返却する。<BR>
     * <BR>
     * [パラメータ.逆指値基準値タイプ != nullの場合]<BR>
     * 　@パラメータ.逆指値基準値タイプを返却する。<BR>
     * @@param l_strStopPriceType - (逆指値基準値タイプ)<BR>
     * 逆指値基準値タイプ<BR>
     * @@param l_strOrgStopPriceType - (元逆指値基準値タイプ)<BR>
     * 元逆指値基準値タイプ<BR>
     * @@return String
     */
    public static String getStopPriceType(String l_strStopPriceType, 
		String l_strOrgStopPriceType)
    {
        final String STR_METHOD_NAME = "getStopPriceType(String, String )";
        log.entering(STR_METHOD_NAME);
       
        // 引数の逆指値基準値タイプ、元逆指値基準値タイプの内、
        // 有効な逆指値基準値タイプを返却する。
        // [パラメータ.元逆指値基準値タイプ != nullの場合]
        // 　@パラメータ.元逆指値基準値タイプを返却する。
        if (l_strOrgStopPriceType != null)
        {
            log.exiting(STR_METHOD_NAME);
        	return l_strOrgStopPriceType;
        }
        
        // [パラメータ.逆指値基準値タイプ != nullの場合]
        // 　@パラメータ.逆指値基準値タイプを返却する。     
        if (l_strStopPriceType != null)
        {
            log.exiting(STR_METHOD_NAME);
        	return l_strStopPriceType;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get逆指値基準値タイプ)<BR>
     * 引数の注文単位より、有効な逆指値基準値タイプを返却する。<BR>
     * <BR>
     * １）　@発注条件指定なしの場合<BR>
     * <BR>
     * this.get発注条件(）＝"DEFAULT（条件指定なし）"の場合、<BR>
     * nullを返却する。<BR>
     * <BR>
     * [get発注条件()に指定する引数]<BR>
     * 　@発注条件：　@注文単位.発注条件<BR>
     * 　@元発注条件：　@注文単位.元発注条件<BR>
     * <BR>
     * ２）　@以外の場合<BR>
     * <BR>
     * this.get逆指値基準値タイプ()に処理を委譲する。<BR>
     * <BR>
     * [get逆指値基準値タイプ()に指定する引数]<BR>
     * 　@逆指値基準値タイプ：　@注文単位.逆指値基準値タイプ<BR>
     * 　@元逆指値基準値タイプ：　@注文単位.元逆指値基準値タイプ<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getStopPriceType(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getStopPriceType(IfoOrderUnit )";
        log.entering(STR_METHOD_NAME);
        String l_strStopPriceTypeReturn = null;
        
        //パラメータ値がNULL
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }  
       
        // 引数の注文単位より、有効な逆指値基準値タイプを返却する。
        // １）　@発注条件指定なしの場合
        // this.get発注条件(）＝"DEFAULT（条件指定なし）"の場合、
        // nullを返却する。
        // [get発注条件()に指定する引数]
        // 　@発注条件：　@注文単位.発注条件
        // 　@元発注条件：　@注文単位.元発注条件
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件(）＝DEFAULT（条件指定なし）の場合。");   
        }
        else 
        {
            // ２）　@以外の場合
            // this.get逆指値基準値タイプ()に処理を委譲する。
            // [get逆指値基準値タイプ()に指定する引数]
            // 　@逆指値基準値タイプ：　@注文単位.逆指値基準値タイプ
            // 　@元逆指値基準値タイプ：　@注文単位.元逆指値基準値タイプ 
        	l_strStopPriceTypeReturn = getStopPriceType(l_ifoOrderUnitRow.getStopPriceType(),
    			l_ifoOrderUnitRow.getOrgStopPriceType());
            log.debug("this.get発注条件(）＝DEFAULT（条件指定なし）以外の場合。");   
        }        
        
        log.debug("逆指値基準値タイプ = " + l_strStopPriceTypeReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strStopPriceTypeReturn;
    }
    
    /**
     * (get逆指値基準値)<BR>
     * 引数の逆指値基準値、元逆指値基準値の内、<BR>
     * 有効な逆指値基準値を返却する。<BR>
     * <BR>
     * [パラメータ.元逆指値基準値 != nullの場合]<BR>
     * 　@パラメータ.元逆指値基準値を返却する。<BR>
     * <BR>
     * [パラメータ.逆指値基準値 != nullの場合]<BR>
     * 　@パラメータ.逆指値基準値を返却する。<BR>
     * @@param l_strStopOrderPrice - (逆指値基準値)<BR>
     * 逆指値基準値<BR>
     * @@param l_strOrgStopOrderPrice - (元逆指値基準値)<BR>
     * 元逆指値基準値<BR>
     * @@return String
     */
    public static String getStopOrderPrice(String l_strStopOrderPrice, 
		String l_strOrgStopOrderPrice)
    {
        final String STR_METHOD_NAME = "getStopOrderPrice(String, String)";
        log.entering(STR_METHOD_NAME);
       
        // 引数の逆指値基準値、元逆指値基準値の内、
        // 有効な逆指値基準値を返却する。
        // [パラメータ.元逆指値基準値 != nullの場合]
        // 　@パラメータ.元逆指値基準値を返却する。
        if (l_strOrgStopOrderPrice != null)
        {
            log.exiting(STR_METHOD_NAME);
        	return l_strOrgStopOrderPrice;
        }

        // [パラメータ.逆指値基準値 != nullの場合]
        // 　@パラメータ.逆指値基準値を返却する。   
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
     * 引数の注文単位より、有効な逆指値基準値を返却する。<BR>
     * <BR>
     * １）　@発注条件指定なしの場合<BR>
     * <BR>
     * this.get発注条件(）＝"DEFAULT（条件指定なし）"の場合、<BR>
     * nullを返却する。<BR>
     * <BR>
     * [get発注条件()に指定する引数]<BR>
     * 　@発注条件：　@注文単位.発注条件<BR>
     * 　@元発注条件：　@注文単位.元発注条件<BR>
     * <BR>
     * ２）　@以外の場合<BR>
     * <BR>
     * this.get逆指値基準値()に処理を委譲する。<BR>
     * <BR>
     * [get逆指値基準値()に指定する引数]<BR>
     * 　@逆指値基準値：　@注文単位.逆指値基準値<BR>
     * 　@元逆指値基準値：　@注文単位.元逆指値基準値<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getStopOrderPrice(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getStopOrderPrice(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        String l_strStopOrderPriceReturn = null;
        
        //パラメータ値がNULL
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }

        // 引数の注文単位より、有効な逆指値基準値を返却する。
        // １）　@発注条件指定なしの場合
        // this.get発注条件(）＝"DEFAULT（条件指定なし）"の場合、
        // nullを返却する。
        // [get発注条件()に指定する引数]
        // 　@発注条件：　@注文単位.発注条件
        // 　@元発注条件：　@注文単位.元発注条件
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件(）＝DEFAULT（条件指定なし）の場合。");   
        }
        else 
        {
            // ２）　@以外の場合
            // this.get逆指値基準値()に処理を委譲する。
            // [get逆指値基準値()に指定する引数]
            // 　@逆指値基準値：　@注文単位.逆指値基準値
            // 　@元逆指値基準値：　@注文単位.元逆指値基準値 
            String l_strStopOrderPrice = null;
            String l_strOrgStopOrderPrice = null;
            if (!l_ifoOrderUnitRow.getStopOrderPriceIsNull()) 
            {
                l_strStopOrderPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getStopOrderPrice());
            }
            if (!l_ifoOrderUnitRow.getOrgStopOrderPriceIsNull()) 
            {
                l_strOrgStopOrderPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getOrgStopOrderPrice());
            }
        	l_strStopOrderPriceReturn = getStopOrderPrice(
    			l_strStopOrderPrice, l_strOrgStopOrderPrice);
            log.debug("this.get発注条件(）＝DEFAULT（条件指定なし）以外の場合。");   
        }        

        log.debug("逆指値基準値 = " + l_strStopOrderPriceReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strStopOrderPriceReturn;
    }
    
    /**
     * (get元Ｗ指値用注文単価)<BR>
     * 引数の注文単位より元Ｗ指値用注文単価を返却する。<BR> 
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]<BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件<BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件<BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]<BR>
     * 　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]<BR>
     * 　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。<BR>
     * 　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合] <BR>
     * 　@　@パラメータ.注文単位.元（W指値）訂正指値を返却する。 <BR>
     * 　@[上記以外] <BR>
     * 　@　@nullを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrgWLimitOrderPrice(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPrice(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ値がNULL
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文単位.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
        	new WEB3OptionOrderManagerImpl();
        // ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。
        // 　@[isストップ注文有効()に指定する引数]
        // 　@　@注文単位：　@パラメータ.注文単位
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_orderUnit);
        
        // ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。
        // 　@[isストップ注文失効済()に指定する引数]
        // 　@　@注文単位：　@パラメータ.注文単位       
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_orderUnit);
        
        // ４）　@２）、３）の戻り値により、返却値を決定する。
        //   [２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]  
        //     パラメータ.注文単位.元（W指値）訂正指値を返却する。  
        // 　@[上記以外]
        // 　@　@nullを返却する。
        if (l_blnStopOrderOpen || l_blnStopOrderExpire)
        {
            String l_strOrgWLimitPrice = null;
            if (!l_ifoOrderUnitRow.getOrgWLimitPriceIsNull()) 
            {
                l_strOrgWLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderUnitRow.getOrgWLimitPrice());
            }
        	log.exiting(STR_METHOD_NAME);
        	return l_strOrgWLimitPrice;
        }
        
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
     * ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]<BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴<BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]<BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴<BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。<BR>
     * 　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]<BR>
     * 　@　@パラメータ.注文履歴.元（W指値）訂正指値を返却する。<BR>
     * 　@[上記以外]<BR>
     * 　@　@nullを返却する。<BR>
     * @@param l_ifoOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrgWLimitOrderPrice(IfoOrderAction l_ifoOrderAction) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPrice(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ値がNULL
        if (l_ifoOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文履歴.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderActionRow.getOrderConditionType(), 
    		l_ifoOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        	
        // ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文履歴：　@パラメータ.注文履歴 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_ifoOrderAction);
        
        // ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。 
        // 　@[isストップ注文失効済()に指定する引数] 
        // 　@　@注文履歴：　@パラメータ.注文履歴 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_ifoOrderAction);
        
        // ４）　@２）、３）の戻り値により、返却値を決定する。 
        //   [２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]
        //     パラメータ.注文履歴.元（W指値）訂正指値を返却する。
        // 　@[上記以外] 
        // 　@　@nullを返却する。 
        if (l_blnStopOrderOpen || l_blnStopOrderExpire)
        {
            String l_strOrgWLimitPrice = null;
            if (!l_ifoOrderActionRow.getOrgWLimitPriceIsNull())
            {
                l_strOrgWLimitPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoOrderActionRow.getOrgWLimitPrice());
            }
        	log.exiting(STR_METHOD_NAME);
        	return l_strOrgWLimitPrice;
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get元Ｗ指値用注文単価区分)<BR>
     * 引数の注文単位より元Ｗ指値用注文単価区分を返却する。 <BR>
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]<BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件<BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件<BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()をコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]<BR>
     * 　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]<BR>
     * 　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。<BR>
     * 　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]<BR>
     * 　@　@パラメータ.注文単位.元（W指値）訂正指値 == 0であれば、"成行"、<BR>
     * 　@　@以外は"指値"を返却する。<BR>
     * 　@[上記以外]<BR>
     * 　@　@nullを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrgWLimitOrderPriceDiv(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPriceDiv(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ値がNULL
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文単位.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
			l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        	
        // ２）　@OP注文マネージャ.isストップ注文有効()をコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_orderUnit);
        
        // ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。 
        // 　@[isストップ注文失効済()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位     
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_orderUnit);
        
        // ４）　@２）、３）の戻り値により、返却値を決定する。 
        //   [２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]
        //     パラメータ.注文単位.元（W指値）訂正指値 == 0であれば、"成行"、
        //     以外は"指値"を返却する。
        // 　@[上記以外] 
        // 　@　@nullを返却する。 
        if (l_blnStopOrderOpen || l_blnStopOrderExpire)
        {
    		double 	l_dblOrgWLimitPrice = l_ifoOrderUnitRow.getOrgWLimitPrice();
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
     * 　@[get発注条件()に指定する引数]<BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件<BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件<BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()をコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]<BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴<BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]<BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴<BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却値を決定する。<BR>
     * 　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]<BR>
     * 　@　@パラメータ.注文履歴.元（W指値）訂正指値 == 0であれば、"成行"、<BR>
     * 　@　@以外は"指値"を返却する。<BR>
     * 　@[上記以外]<BR>
     * 　@　@nullを返却する。<BR>
     * @@param l_ifoOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrgWLimitOrderPriceDiv(IfoOrderAction l_ifoOrderAction) 
    	throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getOrgWLimitOrderPriceDiv(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ値がNULL
        if (l_ifoOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文履歴.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderActionRow.getOrderConditionType(), 
    		l_ifoOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
        	new WEB3OptionOrderManagerImpl();
        
        // ２）　@OP注文マネージャ.isストップ注文有効()をコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文履歴：　@パラメータ.注文履歴 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_ifoOrderAction);
        
        // ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。 
        // 　@[isストップ注文失効済()に指定する引数] 
        // 　@　@注文履歴：　@パラメータ.注文履歴 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_ifoOrderAction);
        
        // ４）　@２）、３）の戻り値により、返却値を決定する。 
        // [２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]
        //   パラメータ.注文履歴.元（W指値）訂正指値 == 0であれば、"成行"、
        //   以外は"指値"を返却する。
        // [上記以外]
        //   nullを返却する。
        if (l_blnStopOrderOpen || l_blnStopOrderExpire)
        {
    		double l_dblOrgWLimitPrice = l_ifoOrderActionRow.getOrgWLimitPrice();
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
        // 　@[上記以外] 
        // 　@　@nullを返却する。
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
     * 　@[get発注条件()に指定する引数]<BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件<BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件<BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]<BR>
     * 　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]<BR>
     * 　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却する執行条件を決定する。<BR>
     * 　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]<BR>
     * 　@　@執行条件 = パラメータ.注文単位.元（W指値）執行条件<BR>
     * 　@[上記以外]<BR>
     * 　@　@nullを返却する。<BR>
     * <BR>
     * ５）　@this.get執行条件（PR層）()メソッドをコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[get執行条件（PR層）()に指定する引数]<BR>
     * 　@　@執行条件：　@４）にて決定した執行条件<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrgWLimitExecCondType(IfoOrderUnit l_orderUnit) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitExecCondType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ値がNULL
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文単位.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderUnitRow.getOrderConditionType(), 
    		l_ifoOrderUnitRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        // ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(l_orderUnit);
        
        // ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。 
        // 　@[isストップ注文失効済()に指定する引数] 
        // 　@　@注文単位：　@パラメータ.注文単位 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(l_orderUnit);
        
        // ４）　@２）、３）の戻り値により、返却する執行条件を決定する。 
        // [２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]
        //   執行条件 = パラメータ.注文単位.元（W指値）執行条件
        IfoOrderExecutionConditionType l_orgexecutionConditionType = null;
        if (l_blnStopOrderOpen || l_blnStopOrderExpire)
        {
        	l_orgexecutionConditionType = 
    			l_ifoOrderUnitRow.getOrgWLimitExecCondType();        	
        }
        else
        {
            // 　@[上記以外] 
            // 　@　@nullを返却する。
            log.exiting(STR_METHOD_NAME);
        	return null;
        }
        
        // ５）　@this.get執行条件（PR層）()メソッドをコールし、 
        // 　@戻り値を返却する。 
        // 　@[get執行条件（PR層）()に指定する引数] 
        // 　@　@執行条件：　@４）にて決定した執行条件                               
        String l_strExecutionCondByPr = getExecutionCondByPr(l_orgexecutionConditionType);
        log.exiting(STR_METHOD_NAME);
        return l_strExecutionCondByPr;
    }
    
    /**
     * (get元Ｗ指値用執行条件)<BR>
     * 引数の注文履歴より元Ｗ指値用執行条件を返却する。<BR> 
     * <BR>
     * １）　@this.get発注条件()の戻り値 != "W指値"の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]<BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件<BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件<BR>
     * <BR>
     * ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。<BR>
     * 　@[isストップ注文有効()に指定する引数]<BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴<BR>
     * <BR>
     * ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。<BR>
     * 　@[isストップ注文失効済()に指定する引数]<BR>
     * 　@　@注文履歴：　@パラメータ.注文履歴<BR>
     * <BR>
     * ４）　@２）、３）の戻り値により、返却する執行条件を決定する。<BR>
     * 　@[２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]<BR>
     * 　@　@執行条件 = パラメータ.注文履歴.元（W指値）執行条件<BR>
     * 　@[上記以外]<BR>
     * 　@　@nullを返却する。<BR>
     * <BR>
     * ５）　@this.get執行条件（PR層）()メソッドをコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[get執行条件（PR層）()に指定する引数]<BR>
     * 　@　@執行条件：　@４）にて決定した執行条件<BR>
     * @@param l_ifoOrderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getOrgWLimitExecCondType(IfoOrderAction l_ifoOrderAction) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgWLimitExecCondType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ値がNULL
        if (l_ifoOrderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
			    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
			    "WEB3IfoDataAdapter." + STR_METHOD_NAME ,
			    "パラメータ値不正。");
        }
        
        // １）　@this.get発注条件()の戻り値 != "W指値"の場合、 
        // 　@nullを返却する。 
        // 　@[get発注条件()に指定する引数] 
        // 　@　@発注条件：　@パラメータ.注文履歴.発注条件 
        // 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 
        IfoOrderActionRow l_ifoOrderActionRow =
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        String l_strOrderConditionType = getOrderConditionType(
    		l_ifoOrderActionRow.getOrderConditionType(), 
    		l_ifoOrderActionRow.getOrgOrderConditionType());
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.debug("this.get発注条件()の戻り値 != W指値の場合。");   
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        // ２）　@OP注文マネージャ.isストップ注文有効()メソッドをコールする。 
        // 　@[isストップ注文有効()に指定する引数] 
        // 　@　@注文履歴：　@パラメータ.注文履歴 
        boolean l_blnStopOrderOpen = l_optionOrderManagerImpl.isStopOrderValid(
    		l_ifoOrderAction);
        
        // ３）　@OP注文マネージャ.isストップ注文失効済()メソッドをコールする。 
        // 　@[isストップ注文失効済()に指定する引数] 
        // 　@　@注文履歴：　@パラメータ.注文履歴 
        boolean l_blnStopOrderExpire = l_optionOrderManagerImpl.isStopOrderExpired(
    		l_ifoOrderAction);
        
        // ４）　@２）、３）の戻り値により、返却する執行条件を決定する。 
        // [２）の戻り値 == trueまたは、３）の戻り値 == trueの場合]
        //   執行条件 = パラメータ.注文履歴.元（W指値）執行条件
        IfoOrderExecutionConditionType l_orgexecutionConditionType = null;
        if (l_blnStopOrderOpen || l_blnStopOrderExpire)
        {
        	l_orgexecutionConditionType = 
    			l_ifoOrderActionRow.getOrgWLimitExecCondType();        	
        }
        else
        {
            // 　@[上記以外] 
            // 　@　@nullを返却する。 
            log.exiting(STR_METHOD_NAME);
        	return null;
        }
        
        // ５）　@this.get執行条件（PR層）()メソッドをコールし、 
        // 　@戻り値を返却する。 
        // 　@[get執行条件（PR層）()に指定する引数] 
        // 　@　@執行条件：　@４）にて決定した執行条件                           
        String l_strReturn = getExecutionCondByPr(l_orgexecutionConditionType);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (get初回注文の注文単位ID)<BR>
     * パラメータ.注文期限区分より、初回注文の注文単位IDを返却する。<BR>
     * <BR>
     * １）パラメータ.注文期限区分により分岐し、対応する値を返却する。 <BR>
     * <BR>
     * パラメータ.注文期限区分が、  <BR>
     * 　@["当日限り"の場合]  <BR>
     * 　@　@nullを返却する。  <BR>
     * 　@["出来るまで注文"の場合]  <BR>
     * 　@　@0を返却する。  <BR>
     * 　@["夕場まで注文"の場合]  <BR>
     * 　@　@nullを返却する。<BR>
     * @@param l_strExpirationDateType - (注文期限区分)<BR>
     * @@return Long
     */
    public static Long getFirstOrderUnitId(String l_strExpirationDateType)
    {
        final String STR_METHOD_NAME = "getFirstOrderUnitId(String)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.注文期限区分が、["当日限り"の場合]nullを返却する。
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_strExpirationDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //["出来るまで注文"の場合]0を返却する。
        else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(0);
        }
        //["夕場まで注文"の場合]nullを返却する。
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get夕場前繰越対象フラグ)<BR>
     * 夕場前繰越対象フラグを返却する。   <BR>
     * <BR>
     * 　@１）部店オブジェクトを取得する。 <BR>
     * <BR>
     * 　@　@　@パラメータ.部店IDにて取得する。 <BR>
     * <BR>
     * 　@２）部店オブジェクト.is夕場実施()をコールする。<BR>
     * <BR>
     * 　@　@　@[引数] <BR>
     * 　@　@　@銘柄タイプ： "先物・オプション" <BR>
     * <BR>
     * 　@３）パラメータ.注文期限区分と２）の戻り値により分岐し、対応する値を返却する。<BR>
     * <BR>
     * 　@　@　@３－１）パラメータ.注文期限区分が、"当日限り"の場合<BR>
     * 　@　@　@　@　@　@　@false（夕場前繰越なし）を返却する。<BR>
     * <BR>
     * 　@　@　@３－２）パラメータ.注文期限区分が、"出来るまで注文"　@かつ<BR>
     * 　@　@　@　@　@　@　@２）の戻り値 = trueの場合 <BR>
     * 　@　@　@　@　@　@　@true（夕場前繰越あり）を返却する。<BR>
     * <BR>
     * 　@　@　@３－３）パラメータ.注文期限区分が、"出来るまで注文"　@かつ<BR>
     * 　@　@　@　@　@　@　@２）の戻り値 = falseの場合<BR>
     * 　@　@　@　@　@　@　@false（夕場前繰越なし）を返却する。<BR>
     * <BR>
     * 　@　@　@３－４）パラメータ.注文期限区分が、"夕場まで注文"の場合<BR>
     * 　@　@　@　@　@　@　@true（夕場前繰越あり）を返却する。<BR>
     * @@param l_strExpirationDateType - (注文期限区分)<BR>
     * @@param l_branchId - (部店ID)<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public static boolean getEveningSessionCarryOverFlag(
        String l_strExpirationDateType,
        long l_branchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEveningSessionCarryOverFlag(String, long)";
        log.entering(STR_METHOD_NAME);

        //１）部店オブジェクトを取得する。
        //パラメータ.部店IDにて取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_branchId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3IfoDataAdapter." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //部店オブジェクト.is夕場実施()をコールする。
        //[引数] 銘柄タイプ： "先物・オプション"
        boolean l_blnIsEveningSession = l_branch.isEveningSessionEnforcemented(ProductTypeEnum.IFO);

        //３）パラメータ.注文期限区分と２）の戻り値により分岐し、対応する値を返却する。
        //３－１）パラメータ.注文期限区分が、"当日限り"の場合
        //false（夕場前繰越なし）を返却する。
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_strExpirationDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //３－２）パラメータ.注文期限区分が、"出来るまで注文"　@かつ２）の戻り値 = trueの場合
        //true（夕場前繰越あり）を返却する。
        else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType)
            && l_blnIsEveningSession)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //３－３）パラメータ.注文期限区分が、"出来るまで注文"　@かつ
        //　@２）の戻り値 = falseの場合
        //　@false（夕場前繰越なし）を返却する。
        else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType)
            && !l_blnIsEveningSession)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //３－４）パラメータ.注文期限区分が、"夕場まで注文"の場合
        //　@true（夕場前繰越あり）を返却する。
        else if (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(l_strExpirationDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get夕場前繰越対象フラグ（PR層）)<BR>
     * 引数の注文単位より、PR層で使用する夕場前繰越対象フラグを返却する。 <BR> 
     * <BR>
     * １）　@以下の条件により分岐し、夕場前繰越対象フラグを返却する。  <BR>
     * <BR>
     * 　@　@　@[パラメータ.注文単位.夕場前繰越対象フラグ == "夕場前繰越なし"の場合]  <BR>
     * <BR>
     * 　@　@　@　@　@false（夕場前繰越なし）を返却する。  <BR>
     * <BR>
     * 　@　@　@[パラメータ.注文単位.夕場前繰越対象フラグ == "夕場前繰越あり"の場合]  <BR>
     * <BR>
     * 　@　@　@　@　@true（夕場前繰越あり）を返却する。<BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     */
    public static boolean getEveningSessionCarryOverFlagPr(IfoOrderUnit l_ifoOrderUnit)
    {
        final String STR_METHOD_NAME = "getEveningSessionCarryOverFlagPr(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.注文単位.夕場前繰越対象フラグ == "夕場前繰越なし"の場合
        //false（夕場前繰越なし）を返却する。
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        if (BooleanEnum.FALSE.equals(l_ifoOrderUnitRow.getEveningSessionCarryoverFlag()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //パラメータ.注文単位.夕場前繰越対象フラグ == "夕場前繰越あり"の場合
        //true（夕場前繰越あり）を返却する。
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (get注文期限区分)<BR>
     * 引数の注文単位より、該当する注文期限区分を返却する。 <BR>
     * <BR>
     * １）　@OP注文マネージャ.is夕場まで注文()をコールする。<BR>
     * <BR>
     * 　@[is夕場まで注文()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * <BR>
     * ２）　@夕場まで注文（１）の戻り値 == true）の場合、 <BR>
     * 　@"夕場まで注文"を返却する。 <BR>
     * <BR>
     * ３）　@夕場まで注文でない（上記以外の）場合、 <BR>
     * 　@OP注文マネージャ.is出来るまで注文単位()をコールする。 <BR>
     * <BR>
     * 　@[is出来るまで注文単位()に指定する引数] <BR>
     * 　@　@注文単位：　@パラメータ.注文単位 <BR>
     * 　@ <BR>
     * 　@true（出来るまで注文）が返却された場合、"出来るまで注文"を返却する。 <BR>
     * 　@false（当日限り注文）が返却された場合、"当日限り"を返却する。<BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getExpirationDateType(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDateType(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //パラメータ値がNULL
        if (l_ifoOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3IfoDataAdapter." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        String l_strExpirationDateType = null;

        //１）　@OP注文マネージャ.is夕場まで注文()をコールする。
        //[is夕場まで注文()に指定する引数]
        //注文単位：　@パラメータ.注文単位
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
        boolean l_blnIsEveningSessionOrder = l_orderMgr.isEveningSessionOrder(l_ifoOrderUnit);

        //２）　@夕場まで注文（１）の戻り値 == true）の場合、
        //　@"夕場まで注文"を返却する。
        if (l_blnIsEveningSessionOrder)
        {
            l_strExpirationDateType = WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER;
        }
        //３）　@夕場まで注文でない（上記以外の）場合、
        //　@OP注文マネージャ.is出来るまで注文単位()をコールする。
        //[is出来るまで注文単位()に指定する引数]
        //　@　@注文単位：　@パラメータ.注文単位
        //　@true（出来るまで注文）が返却された場合、"出来るまで注文"を返却する。
        //　@false（当日限り注文）が返却された場合、"当日限り"を返却する。
        else
        {
            boolean l_blnIsCarriedOrderUnit = l_orderMgr.isCarriedOrderUnit(l_ifoOrderUnit);
            if (l_blnIsCarriedOrderUnit)
            {
                l_strExpirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
            }
            else
            {
                l_strExpirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_strExpirationDateType;
    }

    /**
     * (get日計り区分)<BR>
     * 引数の日計り区分を変換して返却する。<BR>
     * <BR>
     * [パラメータ.日計り区分 = nullの場合]<BR>
     * 　@"1"（日計り以外）を返却する。<BR>
     * [上記以外の場合]<BR>
     * 　@パラメータ.日計り区分を返却する。<BR>
     * @@param l_strDayTradeType - (日計り区分)<BR>
     * @@return String
     */
    public static String getDayTradeType(String l_strDayTradeType)
    {
        final String STR_METHOD_NAME = "getDayTradeType(String)";
        log.entering(STR_METHOD_NAME);
        if (l_strDayTradeType == null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ContractCheckDef.CONTRACT_CHECK;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_strDayTradeType;
        }
    }
}
@
