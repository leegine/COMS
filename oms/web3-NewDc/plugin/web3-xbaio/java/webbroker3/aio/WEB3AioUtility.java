head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金の機@能を実装するユーティリティ(WEB3AioUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 于美麗 (中訊) 新規作成
                   2004/12/09 王蘭芬(中訊)残対応
*/
package webbroker3.aio;

import java.util.List;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (入出金の機@能を実装するユーティリティ)<BR>
 * 入出金の機@能を実装するユーティリティクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AioUtility {

//    /**
//     * (get処理状況メッセージコード)
//     * 引数により、オンライン入金の場合、処理状況を取得し、返却する
//     * 「ＤＢ更新仕様\10.入出金\入出金ステータス構成表.xls」を参照する
//     *
//     * @@param l_strStatus - (金融機@関連携入出金状況テーブル.処理区分)
//     * @@param l_strOrderStatusFlag - (処理FLAG（注文）)
//     * @@param l_strStartStatusFlg - (処理FLAG（決済開始）)
//     * @@param l_strResultStatusFlag - (処理FLAG（決済結果）)
//     * @@param l_OrderStatus - (注文状態)
//     * @@param l_strCancelType - (注文取消区分)
//     * @@param l_strKeyTableStatus - (入出金伝票受付キューテーブル.処理区分)
//     * @@return String
//     */
//    public static String getResult(
//        String l_strStatus,
//        String l_strOrderStatusFlag,
//        String l_strStartStatusFlg,
//        String l_strResultStatusFlag,
//        OrderStatusEnum l_OrderStatus,
//        String l_strCancelType,
//        String l_strKeyTableStatus)
//    {
//        //>>>>>>*******金融機@関連携入出金状況テーブル.処理区分 = ０：未処理 Start
//        if (WEB3TransactionStatusDef.NOT_DEAL.equals(l_strStatus))
//        {
//            //処理FLAG（注文）= ０：未処理 || 処理FLAG（注文）= １：要求受信
//            if (WEB3OrderStatusFlagDef.NOT_DEAL.equals(l_strOrderStatusFlag)
//                || WEB3OrderStatusFlagDef.REPUEST_RECEIPT.equals(
//                    l_strOrderStatusFlag))
//            {
//               //処理FLAG（決済開始）= ０：未処理 &&  処理FLAG（決済結果）= ０：未処理
//                if (WEB3StartStatusFlgDef.NOT_DEAL.equals(l_strStartStatusFlg)
//                    && WEB3ResultStatusFlagDef.NOT_DEAL.equals(
//                        l_strResultStatusFlag))
//                {
//                    return WEB3AioJudgeResultDef.A;
////                    return "A";
//                }
//                else
//                {
//                    //その他....
//                    return WEB3AioJudgeResultDef.C;
////                    return "C";
//                }
//            }
//            else
//            {
//                //処理FLAG（注文）= ２：応答送信.......... 
//                if (WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(
//                    l_strOrderStatusFlag))
//                {
//                    //(処理FLAG（決済開始）= ０：未処理 || 処理FLAG（決済開始）= １：要求受信)
//                    // && 処理FLAG（決済結果）= ０：未処理 ....
//                    if ((WEB3StartStatusFlgDef.NOT_DEAL.equals(
//                        l_strStartStatusFlg)
//                            || WEB3StartStatusFlgDef.REPUEST_RECEIPT.equals(
//                                l_strStartStatusFlg))
//                            && WEB3ResultStatusFlagDef.NOT_DEAL.equals(
//                                l_strResultStatusFlag))
//                    {
//                        return WEB3AioJudgeResultDef.A;
////                        return "A";
//                    }
//                    else
//                    {
//                        //(処理FLAG（決済開始）= ２：応答送信........
//                        if (WEB3StartStatusFlgDef.RESPONSE_SEND.equals(
//                            l_strStartStatusFlg))
//                        {
//                            // 処理FLAG（決済結果）= ０：未処理 ....
//                            if (WEB3ResultStatusFlagDef.NOT_DEAL.equals(
//                                l_strResultStatusFlag))
//                            {
//                                return WEB3AioJudgeResultDef.P;
////                                return "P";
//                            }
//                            else
//                            {
//                                return WEB3AioJudgeResultDef.B;
////                                return "B";
//                            }
//                        }
//                        else
//                        {
//                            //その他....
//                            return WEB3AioJudgeResultDef.C;
////                            return "C";
//                        }
//                    }
//                }
//                else
//                {
//                    //その他....
//                    return WEB3AioJudgeResultDef.C;
////                    return "C";
//                }
//            }
//            //>>>>>>*******金融機@関連携入出金状況テーブル.処理区分 = ０：未処理 End    
//        }
//        
//        //>>>>>>*******金融機@関連携入出金状況テーブル.処理区分 = １：決済完了 Start 
//        else
//        {
//            if (WEB3TransactionStatusDef.OK.equals(l_strStatus))
//            {
//                //処理FLAG（注文）= ２：応答送信 && (処理FLAG（決済開始）= ２：応答送信
//                if (WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(
//                    l_strOrderStatusFlag)
//                        && WEB3StartStatusFlgDef.RESPONSE_SEND.equals(
//                            l_strStartStatusFlg))
//                {
//                    //処理FLAG（決済結果）=１：通知受信 ||
//                    //処理FLAG（決済結果）=２：応答送信 ||
//                    //処理FLAG（決済結果）= ９：余力再計算完了 ||
//                    //処理FLAG（決済結果）= A：決済再処理完了.......... 
//                   if (WEB3ResultStatusFlagDef.NOTIFY_RECEIPT.equals(
//                            l_strResultStatusFlag)
//                        || WEB3ResultStatusFlagDef.RESPONSE_SEND.equals(
//                           l_strResultStatusFlag)
//                        || WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE.equals(
//                            l_strResultStatusFlag)
//                        || WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE.equals(
//                            l_strResultStatusFlag))
//                    {
//                        //注文状態 =１：受付済（新規注文）&&  注文取消区分 = ０：初期値
//                        if (OrderStatusEnum.ACCEPTED.equals(l_OrderStatus)
//                            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
//                        {
//                            //入出金伝票受付キューテーブル.処理区分 = ９：エラー
//                            if (WEB3StatusDef.DATA_ERROR.equals(l_strKeyTableStatus))
//                            {
//                                return WEB3AioJudgeResultDef.J;
////                                return "J";
//                            }
//                            else
//                            {
//                                return WEB3AioJudgeResultDef.D;
////                                return "D";
//                            }
//                        }
//                        else
//                        {
//                            //注文状態 = ３：発注済（新規注文）&& 注文取消区分 = ０：初期値
//                            if (OrderStatusEnum.ORDERED.equals(l_OrderStatus)
//                                && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
//                            {
//                                return WEB3AioJudgeResultDef.E;
////                                return "E";
//                            }
//                            else
//                            {
//                                //注文状態 = ６：発注失敗（新規注文）&& 注文取消区分 = ０：初期値
//                                if (OrderStatusEnum.NOT_ORDERED.equals(l_OrderStatus)
//                                    && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
//                                {
//                                    return WEB3AioJudgeResultDef.F;
////                                    return "F";
//                                }
//                                else
//                                {
//                                    //注文状態 =１：受付済（新規注文）&&  注文取消区分 = ０：初期値
//                                    // && 入出金伝票受付キューテーブル.処理区分 = ９：エラー
//                                    if (OrderStatusEnum.ACCEPTED.equals(l_OrderStatus)
//                                        && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType)
//                                        && WEB3StatusDef.DATA_ERROR.equals(l_strKeyTableStatus))
//                                    {
//                                        return WEB3AioJudgeResultDef.J;
////                                        return "J";
//                                    }
//                                    else
//                                    {
//                                        //注文状態 = null && 注文取消区分 = null
//                                        // && 入出金伝票受付キューテーブル.処理区分 = null
//                                        if (l_OrderStatus == null
//                                            && l_strCancelType == null
//                                            && l_strKeyTableStatus == null)
//                                        {
//                                            return WEB3AioJudgeResultDef.E;
////                                            return "E";
//                                        }
//                                        else
//                                        {
//                                            //その他....
//                                            return WEB3AioJudgeResultDef.B;
////                                            return "B";
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    else
//                    {
//                        //処理FLAG（決済結果）= ８：余力計算失敗.....
//                        if (WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL.equals(
//                            l_strResultStatusFlag))
//                        {
//                            //注文状態 =１：受付済（新規注文）&&  注文取消区分 = ０：初期値
//                            if (OrderStatusEnum.ACCEPTED.equals(l_OrderStatus)
//                                && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
//                            {
//                                //入出金伝票受付キューテーブル.処理区分 = ９：エラー
//                                if (WEB3StatusDef.DATA_ERROR.equals(l_strKeyTableStatus))
//                                {
//                                    return WEB3AioJudgeResultDef.J;
////                                    return "J";
//                                }
//                                else
//                                {
//                                    return WEB3AioJudgeResultDef.K;
////                                    return "K";
//                                }
//                           }
//                            else
//                            {
//                                //注文状態 = ３：発注済（新規注文）&& 注文取消区分 = ０：初期値
//                                if (OrderStatusEnum.ORDERED.equals(l_OrderStatus)
//                                    && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
//                                {
//                                    return WEB3AioJudgeResultDef.L;
////                                    return "L";
//                                }
//                                else
//                                {
//                                    //注文状態 = ６：発注失敗（新規注文）&& 注文取消区分 = ０：初期値
//                                    if (OrderStatusEnum.NOT_ORDERED.equals(l_OrderStatus)
//                                        && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
//                                    {
//                                        return WEB3AioJudgeResultDef.M;
////                                        return "M";
//                                    }
//                                    else
//                                    {
//                                        //注文状態 =１：受付済（新規注文）&&  注文取消区分 = ０：初期値
//                                        // && 入出金伝票受付キューテーブル.処理区分 = ９：エラー                                       
//                                        if (OrderStatusEnum.ACCEPTED.equals(l_OrderStatus)
//                                            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType)
//                                            && WEB3StatusDef.DATA_ERROR.equals(l_strKeyTableStatus))
//                                        {
//                                            return WEB3AioJudgeResultDef.J;
////                                            return "J";
//                                        }
//                                        else
//                                        {
//                                            //注文状態 = null && 注文取消区分 = null
//                                            // && 入出金伝票受付キューテーブル.処理区分 = null
//                                            if (l_OrderStatus == null
//                                                && l_strCancelType == null
//                                                && l_strKeyTableStatus == null)
//                                            {
//                                                return WEB3AioJudgeResultDef.L;
////                                                return "L";
//                                            }
//                                            else
//                                            {
//                                                //その他....
//                                                return WEB3AioJudgeResultDef.B;
////                                                return "B";
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        else
//                        { 
//                            //その他....
//                            return WEB3AioJudgeResultDef.B;
////                            return "B";
//                        }
//                    }
//                }
//                else
//                {
//                    //その他....
//                    return WEB3AioJudgeResultDef.B;
////                    return "B";
//                }
//            //>>>>>>*******金融機@関連携入出金状況テーブル.処理区分 = １：決済完了 End
//            }
//            
//            
//            else
//            {
//                //>>>>>>*******金融機@関連携入出金状況テーブル.処理区分 = ２：決済中止 Start
//                if (WEB3TransactionStatusDef.NG.equals(l_strStatus))
//                {
//                    if (WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(
//                        l_strOrderStatusFlag)
//                            && WEB3StartStatusFlgDef.RESPONSE_SEND.equals(
//                                l_strStartStatusFlg)
//                            && WEB3ResultStatusFlagDef.NOTIFY_ERROR_FAIL.equals(
//                                l_strResultStatusFlag))
//                    {
//                        return WEB3AioJudgeResultDef.G;
////                        return "G";
//                    }
//                    else
//                    {
//                        return WEB3AioJudgeResultDef.B;
////                        return "B";
//                    }
//                //>>>>>>*******金融機@関連携入出金状況テーブル.処理区分 = ２：決済中止 End
//                }
//                else
//                {
//                    //>>>>>>*******金融機@関連携入出金状況テーブル.処理区分 = ３：エラー Start
//                    if (WEB3TransactionStatusDef.ERROR.equals(l_strStatus))
//                    {
//                        //処理FLAG（注文）= ０：未処理  || 処理FLAG（注文）= １：要求受信
//                        if (WEB3OrderStatusFlagDef.NOT_DEAL.equals(
//                            l_strOrderStatusFlag)
//                                || WEB3OrderStatusFlagDef.REPUEST_RECEIPT.equals(
//                                    l_strOrderStatusFlag))
//                        {
//                            return WEB3AioJudgeResultDef.C;
////                            return "C";
//                        }
//                        else
//                        {
//                            //処理FLAG（注文）= ２：応答送信....
//                            if (WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(
//                                l_strOrderStatusFlag))
//                            {
//                                //(処理FLAG（決済開始）= ０：未処理 || (処理FLAG（決済開始）= １：要求受信
//                                if (WEB3StartStatusFlgDef.NOT_DEAL.equals(
//                                    l_strStartStatusFlg)
//                                    || WEB3StartStatusFlgDef.REPUEST_RECEIPT.equals(
//                                        l_strStartStatusFlg))
//                                {
//                                    return WEB3AioJudgeResultDef.C;
////                                    return "C";
//                                }
//                                else
//                                {
//                                    //(処理FLAG（決済開始) = ２：応答送信
//                                    if (WEB3StartStatusFlgDef.RESPONSE_SEND.equals(
//                                        l_strStartStatusFlg))
//                                    {
//                                        //処理FLAG（決済結果）= ０：未処理
//                                        if (WEB3ResultStatusFlagDef.NOTIFY_ERROR_ERROR.equals(
//                                            l_strResultStatusFlag))
//                                        {
//                                            return WEB3AioJudgeResultDef.H;
////                                            return "H";
//                                        }
//                                        else
//                                        {
//                                            //処理FLAG（決済結果）= ６：セッションエラー（COMPLETE)
//                                            if (WEB3ResultStatusFlagDef.SESSION_ERROR_COMPLETE.equals(
//                                                l_strResultStatusFlag))
//                                            {
//                                                return WEB3AioJudgeResultDef.N;
////                                                return "N";
//                                            }
//                                            else
//                                            {
//                                                //処理FLAG（決済結果）= ７：セッションエラー（COMPLETE以外)
//                                                if (WEB3ResultStatusFlagDef.SESSION_ERROR_COMPLETE_EXCEPT.equals(
//                                                    l_strResultStatusFlag))
//                                                {
//                                                    return WEB3AioJudgeResultDef.O;
////                                                    return "O";
//                                                }
//                                                else
//                                                {
//                                                    //その他....
//                                                    return WEB3AioJudgeResultDef.B;
////                                                    return "B";
//                                                }
//                                            }
//                                        }
//                                    }
//                                    else
//                                    {
//                                        //(処理FLAG（決済開始) = ５：キャンセル
//                                        if (WEB3StartStatusFlgDef.CANCEL.equals(
//                                            l_strStartStatusFlg))
//                                        {
//                                            return WEB3AioJudgeResultDef.I;
////                                            return "I";
//                                        }
//                                        else
//                                        {
//                                            //その他....
//                                            return WEB3AioJudgeResultDef.C;
////                                            return "C";
//                                        }
//                                    }
//                                }
//                            }
//                            else
//                            {
//                                //その他....
//                                return WEB3AioJudgeResultDef.C;
////                                return "C";
//                            }
//                        }
//                        //>>>>>>*******金融機@関連携入出金状況テーブル.処理区分 = ３：エラー End
//                    }
//                    
//                    //>>>>>>*******その他：エラー Begin
//                    else
//                    {
//                        return WEB3AioJudgeResultDef.J;
////                        return "J";
//                    }
//                    //>>>>>>*******その他：エラー End
//                }
//            }
//        }
//        
//    }
    
    public static QueryProcessor getProcessor()
        throws WEB3BaseException
    {
        try
        {
            return Processors.getDefaultProcessor();
        }
        catch (DataException l_ex)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3AioUtility.class.getName() + ".getProcessor()",
                    l_ex.getMessage(),
                    l_ex);
        }

    }
    
    public static List doFindAllQuery(
            RowType l_rowType, String l_strWhere, 
            String l_strOrderBy, String l_strConditions, Object[] l_bindVars,
            ErrorInfo l_errorInfo, String l_strErrormessage)
        throws WEB3BaseException
    {
        try
        {
            return Processors.getDefaultProcessor().doFindAllQuery(
                    l_rowType, l_strWhere, l_strOrderBy, l_strConditions, l_bindVars);
        }
        catch (DataException l_ex)
        {
            log.error(l_strErrormessage);
            throw new WEB3SystemLayerException(
                    l_errorInfo,
                    WEB3AioUtility.class.getName() + ".doFindAllQuery()",
                    l_ex.getMessage(),
                    l_ex);
        }
    }
    
    public static List doFindAllQuery(
            RowType l_rowType, String l_strWhere, String l_strConditions, Object[] l_bindVars,
            ErrorInfo l_errorInfo, String l_strErrormessage)
        throws WEB3BaseException
    {
        try 
        {
            return Processors.getDefaultProcessor().doFindAllQuery(
                    l_rowType, l_strWhere, l_strConditions, l_bindVars);
        }
        catch (DataException l_ex)
        {
            log.error(l_strErrormessage);
            throw new WEB3SystemLayerException(
                    l_errorInfo,
                    WEB3AioUtility.class.getName() + ".doFindAllQuery()",
                    l_ex.getMessage(),
                    l_ex);
        }
    }
    
    public static TradingTimeRow doFindAllQuery(String l_institutionCode, String l_branchCode)
        throws WEB3BaseException
    {
        try
        {
            //processor
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            
            //where 
            StringBuffer l_strBuf = new StringBuffer();
            l_strBuf.append(" institution_code = ? and ");
            l_strBuf.append(" branch_code = ? and ");
            l_strBuf.append(" market_code = ? and ");
            l_strBuf.append(" trading_time_type = ? and ");
            l_strBuf.append(" product_code = ? and ");
            l_strBuf.append(" biz_date_type = ? and ");
            l_strBuf.append(" start_time <= ? and end_time >= ? ");
            
            //value
            //営業日区分
            String l_strBizDateType = 
                WEB3GentradeTradingTimeManagement.getBizDateType(GtlUtils.getSystemTimestamp());
            //date    
            String l_strDate = 
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "HHmmss");
            
            Object[] l_bindVars = 
                {l_institutionCode,
                    l_branchCode,
                    WEB3MarketCodeDef.DEFAULT,
                    WEB3TradingTimeTypeDef.MARGIN_TRANSFER,
                    WEB3ProductCodeDef.DEFAULT,
                    l_strBizDateType,
                    l_strDate};
                    
            List l_lis = l_processor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_strBuf.toString(),
                l_bindVars);
                
            TradingTimeRow l_tradingTimeRow = (TradingTimeRow)l_lis.get(0);
                
            return l_tradingTimeRow;
        }
        catch (DataException l_ex)
        {
            log.error("Error in find 取引時間テーブル");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AioUtility.class.getName() + ".doFindAllQuery()",
                l_ex.getMessage(),
                l_ex);
        }    
    }
     
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioUtility.class);
}
@
