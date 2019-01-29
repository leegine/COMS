head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAction.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文履歴(WEB3FeqOrderAction.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14  張玲(中訊) 新規作成
                   2006/10/30  徐大方(中訊) 式樣變更モデル295
Revesion History : 2008/10/02 大澤(SRA) 【外国株式】仕様変更管理台帳（モデル）No.466
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderActionImpl;

import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.feq.define.WEB3FeqActionStatusDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (外国株式注文履歴)<BR>
 * 外国株式注文履歴<BR>
 * 
 * @@ author 張玲 
 * @@ version 1.0 
 */
public class WEB3FeqOrderAction extends FeqOrderActionImpl 
{
    
    /**
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAction.class);

    /**
     * @@roseuid 42CE39EA01A5
     */
    public WEB3FeqOrderAction(long order_action_id) 
        throws DataQueryException, DataNetworkException
    {
        super(order_action_id);
    }
    
    /**
     * @@roseuid 42CE39EA01A5
     */
    public WEB3FeqOrderAction(FeqOrderActionRow row)
    {
       super(row);
    }
    
    /**
     * (get履歴状態区分)<BR>
     * 注文履歴の状態を返却する。<BR>
     * <BR>
     * 戻り値の履歴状態区分：<BR>
     * 00：新規注文 01：注文受付 02：新規注文(失敗) 03：訂正注文<BR>
     * 04：訂正受付 05：訂正完了 06：訂正注文(失敗) 07：取消注文<BR>
     * 08：取消受付 09：取消完了 10：取消注文(失敗) 11：一部約定<BR>
     * 12：全部約定 13：約定取消 14：失効 15：失効取消 16：無効<BR>
     * 17：注文繰越 18：注文繰越(失敗) 28：注文受付取消<BR>
     * 31：約定処理中（一部約定） 32：約定処理中（全部約定）<BR>
     * 99：その他<BR>
     * <BR>
     * -----------------------------------------------------<BR>
     * ---------------------<BR>
     * １）　@約定<BR>
     * 　@this.注文イベントタイプ == EXECUTE(約定)かつ<BR>
     * 　@パラメータ.注文単位.仮約定フラグ == "0：DEFAULT"の場合<BR>
     * 　@・一部約定（this.isPartiallyExecuted( ) == true）ならば<BR>
     * 　@　@"一部約定"を返す。<BR>
     * 　@・全部約定（this.isFullyExecuted( ) == true）ならば、<BR>
     * 　@　@"全部約定"を返す。<BR>
     * <BR>
     * 　@パラメータ.注文単位.仮約定フラグ == "1：仮約定"の場合<BR>
     * 　@・一部約定（this.isPartiallyExecuted( ) == true）ならば、<BR>
     * 　@　@"約定処理中（一部約定）"を返す。<BR>
     * 　@・全部約定（this.isFullyExecuted( ) == true）ならば、<BR>
     * 　@　@"約定処理中（全部約定）"を返す。<BR>
     * <BR>
     * ２）　@失効<BR>
     * 　@this.注文イベントタイプ == EXPIRE(失効済み) かつ<BR>
     * 　@this.注文失効ステータス == INVALIDATED_BY_MKT<BR>
     * 　@(マーケット拒否)の場合、"失効"を返す。<BR>
     * <BR>
     * ３）　@無効、注文繰越(失敗)<BR>
     * 　@this.注文イベントタイプ == EXPIRE(失効済み) かつ<BR>
     * 　@this.注文失効ステータス == EXPIRED(終了)の場合、<BR>
     * 　@・this.注文エラー理由コード != "0000：<BR>
     * 　@正常"ならば、"注文繰越(失敗)"を返す。<BR>
     * 　@・上記以外であれば、"無効"を返す。<BR>
     * <BR>
     * ４）　@失効取消<BR>
     * 　@this.注文イベントタイプ == UNDO_INVALIDATION_BY_MKT<BR>
     * 　@(失効取消)の場合、"失効取消"を返す。<BR>
     * <BR>
     * ５）　@約定取消<BR>
     * 　@this.注文イベントタイプ == UNDO_EXECUTION(約定取消)の場合、<BR>
     * 　@"約定取消"を返す。<BR>
     * <BR>
     * ６）　@新規注文、注文繰越<BR>
     * 　@this.注文状態 == ACCEPTED(受付済(新規注文))の場合、<BR>
     * 　@・外国株式注文マネージャ.is繰越注文単位(パラメータ.注文単位)<BR> 
     * 　@== false（ == 初回注文）ならば、<BR>
     * 　@　@"新規注文"を返す。<BR>
     * 　@・上記以外ならば、"注文繰越"を返す。<BR>
     * <BR>
     * ７）　@注文受付<BR>
     * 　@this.注文状態 == ORDERED(発注済み（新規注文))の場合、<BR>
     * 　@"注文受付"を返す。<BR>
     * <BR>
     * ８）　@新規注文(失敗)<BR>
     * 　@this.注文状態 == NOT_ORDERED(発注失敗(新規注文))の場合、<BR>
     * 　@　@"新規注文(失敗)"を返す。<BR>
     * <BR>
     * ９）　@訂正注文<BR>
     * 　@this.注文状態 == MODIFY_ACCEPTED(受付済(変更注文))<BR>
     * 　@の場合、"訂正注文"を返す。<BR>
     * <BR>
     * １０）　@訂正受付<BR>
     * 　@this.注文状態 == MODIFYING(発注中(変更注文))の場合、<BR>
     * 　@"訂正受付"を返す。<BR>
     * <BR>
     * １１）　@訂正完了<BR>
     * 　@this.注文状態 == MODIFIED(発注済み(変更注文))の場合、<BR>
     * 　@"訂正完了"を返す。<BR>
     * <BR>
     * １２）　@訂正注文(失敗)<BR>
     * 　@this.注文状態 == NOT_MODIFIED(発注失敗(変更注文))の場合、<BR>
     * 　@"訂正注文(失敗)"を返す。<BR>
     * <BR>
     * １３）　@取消注文<BR>
     * 　@this.注文状態 == CANCEL_ACCEPTED(受付済(取消注文))<BR>
     * 　@の場合、"取消注文"を返す。<BR>
     * <BR>
     * １４）　@取消受付<BR>
     * 　@this.注文状態 == CANCELLING(発注中(取消注文))<BR>
     * 　@の場合、"取消受付"を返す。<BR>
     * <BR>
     * １５）　@取消完了<BR>
     * 　@this.注文状態 == CANCELLED(発注済み(取消注文))の場合、<BR>
     * 　@"取消完了"を返す。<BR>
     * <BR>
     * １６）　@取消注文(失敗)<BR>
     * 　@this.注文状態 == NOT_CANCELLED(発注失敗(取消注文))の場合、<BR>
     * 　@"取消注文(失敗)"を返す。<BR>
     * <BR>
     * １７）　@注文受付取消<BR>
     * 　@this.注文状態 == ORDERING(発注中(新規注文))　@かつ<BR>
     * 　@this.注文イベントタイプ==SEND_TO_MKT（マーケット送信済み）の場合、"注文受付取消"を返す。<BR>
     * <BR>
     * １８）　@上記以外の場合、"その他"を返す。<BR>
     * ---------------------------------------------------<BR>
     * -----------------------<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 外国株式注文単位オブジェクト<BR>
     * @@return String
     * @@roseuid 42A55401007B
     */
    public String getActionStateDiv(WEB3FeqOrderUnit l_orderUnit) 
    {
        final String STR_METHOD_NAME = "getActionStateDiv()";
        log.entering(STR_METHOD_NAME);

        String l_strReturn; 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_orderManager =
            (WEB3FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        boolean l_blnIsCarryOverOrderUnit = l_orderManager.isCarryOverOrderUnit(l_orderUnit);               
        OrderEventTypeEnum l_oetEventType = this.getOrderEventType();
        OrderExpirationStatusEnum l_oetExpirationStatus = this.getExpirationStatus();
        OrderStatusEnum l_oetStatus = this.getOrderStatus();
        FeqOrderUnitRow l_feqOrderUnitRow = 
            (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
            
        //１）　@約定
        //　@this.注文イベントタイプ == EXECUTE(約定)かつ
        //　@パラメータ.注文単位.仮約定フラグ == "0：DEFAULT"の場合
        if (OrderEventTypeEnum.EXECUTE.equals(l_oetEventType))
        {
            if (WEB3TemporaryExecutionFlagDef.DEFAULT.equals(
                    l_feqOrderUnitRow.getTemporaryExecutionFlag()))
            {
                if (this.isPartiallyExecuted() == true)
                {
                    l_strReturn = WEB3FeqActionStatusDivDef.PART_EXECUTE;
                }
                else
                {
                    l_strReturn = WEB3FeqActionStatusDivDef.FULL_EXECUTE;
                }
            }
            else
            {
                if (this.isPartiallyExecuted() == true)
                {
                    l_strReturn = WEB3FeqActionStatusDivDef.PROCESSING_PART_EXECUTE;
                }
                else
                {
                    l_strReturn = WEB3FeqActionStatusDivDef.PROCESSING_FULL_EXECUTE;
                }
            }
        }
        //２）　@失効
        //this.注文イベントタイプ == EXPIRE(失効済み) かつ
        //this.注文失効ステータス == INVALIDATED_BY_MKT
        else if (OrderEventTypeEnum.EXPIRE.equals(l_oetEventType) 
            && OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_oetExpirationStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.EXPIRE;
        }
        //３）　@無効、注文繰越(失敗)   
        //this.注文イベントタイプ == EXPIRE(失効済み) かつ
        //this.注文失効ステータス == EXPIRED(終了)の場合 
        else if (OrderEventTypeEnum.EXPIRE.equals(l_oetEventType) 
            && OrderExpirationStatusEnum.EXPIRED.equals(l_oetExpirationStatus))
        {
            //this.注文エラー理由コード != "0000：正常"の場合
            if (!WEB3ErrorReasonCodeDef.NORMAL.equals(super.m_row.getErrorReasonCode()))
            {
                l_strReturn = WEB3FeqActionStatusDivDef.ORDER_CARRY_OVER_REJECT;   
            } 
            else 
            {
                l_strReturn = WEB3FeqActionStatusDivDef.INVALID;   
            }
        }
        //４）　@失効取消
        //this.注文イベントタイプ == UNDO_INVALIDATION_BY_MKT(失効取消)の場合
        else if (OrderEventTypeEnum.UNDO_INVALIDATION_BY_MKT.equals(l_oetEventType))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.UNDO_EXPIRE;
        }
        //５）　@約定取消
        //this.注文イベントタイプ == UNDO_EXECUTION(約定取消)の場合
        else if (OrderEventTypeEnum.UNDO_EXECUTION.equals(l_oetEventType))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.UNDO_EXECUTE; 
        }
        //６）　@新規注文、注文繰越
        //this.注文状態 == ACCEPTED(受付済(新規注文))の場合 
        else if (OrderStatusEnum.ACCEPTED.equals(l_oetStatus))
        {
            // 外国株式注文マネージャ.is繰越注文単位(パラメータ.注文単位) == false（ == 初回注文）
            if (!l_blnIsCarryOverOrderUnit )
            {
                l_strReturn = WEB3FeqActionStatusDivDef.NEW_ORDER;
            } 
            else 
            {
                l_strReturn = WEB3FeqActionStatusDivDef.ORDER_CARRY_OVER;         
            }
        }
        //７）　@注文受付
        //this.注文状態 == ORDERED(発注済み（新規注文))の場合
        else if (OrderStatusEnum.ORDERED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.NEW_ORDER_ACCEPT; 
        }
        //８）　@新規注文(失敗) 
        //this.注文状態 == NOT_ORDERED(発注失敗(新規注文))の場合
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.NEW_ORDER_REJECT;  
        }
        //９）　@訂正注文 
        //this.注文状態 == MODIFY_ACCEPTED(受付済(変更注文))の場合
        else if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CHANGE;    
        }    
        //１０）　@訂正受付
        //this.注文状態 == MODIFYING(発注中(変更注文))の場合
        else if (OrderStatusEnum.MODIFYING.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CHANGE_ACCEPT; 
        }   
        //１１）　@訂正完了 
        //this.注文状態 == MODIFIED(発注済み(変更注文))の場合
        else if (OrderStatusEnum.MODIFIED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CHANGE_COMPLETE;    
        }   
        //１２）　@訂正注文(失敗) 
        //this.注文状態 == NOT_MODIFIED(発注失敗(変更注文))の場合
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CHANGE_REJECT;     
        }   
        //１３）　@取消注文
        //this.注文状態 == CANCEL_ACCEPTED(受付済(取消注文))の場合
        else if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CANCEL;    
        }
        //１４）　@取消受付
        //this.注文状態 == CANCELLING(発注中(取消注文))の場合
        else if (OrderStatusEnum.CANCELLING.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CANCEL_ACCEPT;     
        }   
        //１５）　@取消完了
        //this.注文状態 == CANCELLED(発注済み(取消注文))の場合
        else if (OrderStatusEnum.CANCELLED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CANCEL_COMPLETE;   
        }
        //１６）　@取消注文(失敗) 
        //this.注文状態 == NOT_CANCELLED(発注失敗(取消注文))の場合
        else if (OrderStatusEnum.NOT_CANCELLED.equals(l_oetStatus))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.CANCEL_REJECT;  
        } 
        //１７）　@注文受付取消 
        //this.注文状態 == ORDERING(発注中(新規注文))　@かつ 
        //this.注文イベントタイプ==SEND_TO_MKT（マーケット送信済み）の場合、"注文受付取消"を返す。 
        else if (OrderStatusEnum.ORDERING.equals(l_oetStatus)
            && OrderEventTypeEnum.SEND_TO_MKT.equals(l_oetEventType))
        {
            l_strReturn = WEB3FeqActionStatusDivDef.ORDER_ACCEPT_CANCEL;  
        } 
        //上記以外の場合
        else 
        {
            l_strReturn = WEB3FeqActionStatusDivDef.OTHER;
        }

        log.debug(STR_METHOD_NAME + ".get履歴状態区分 : " + l_strReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }
    
    /**
     * (get受付結果区分)<BR>
     * 注文履歴の受付結果区分を返却する。<BR>
     * <BR>
     * 戻り値の受付結果区分：<BR>
     * 0000：正常 1001：受付エラー 1002：訂正エラー 1003：取消エラー<BR>
     * 0001：値幅エラー 0002：預り金不足エラー 0003：<BR>
     * 株式残高不足エラー 0004：保証金不足エラー<BR>
     * 0005：建株残高不足エラー 0006：売買停止銘柄エラー 0007：<BR>
     * 市場変更銘柄エラー<BR>
     * 0008：買付余力エラー 0009：売付可能数量エラー 0010：<BR>
     * 特定口座エラー<BR>
     * 0011：注文繰越スキップ銘柄エラー<BR>
     * 9001：その他エラー<BR>
     * <BR>
     * --------------------------------------------------<BR>
     * ------------------------<BR>
     * ・this.注文訂正・取消区分 == "取消失敗"の場合<BR>
     * <BR>
     * 　@"取消エラー"を返す。<BR>
     * <BR>
     * ・this.注文訂正・取消区分 == "訂正失敗"の場合<BR>
     * <BR>
     * 　@"訂正エラー"を返す。<BR>
     * <BR>
     * ・this.注文状態 == NOT_ORDERED（発注失敗（新規注文））<BR>
     * 　@かつ　@this.注文イベントタイプ == REJECTED_BY_MARKET<BR>
     * 　@（マーケット拒否（新規注文））<BR>
     * 　@の場合<BR>
     * <BR>
     * 　@"受付エラー"を返す。<BR>
     * <BR>
     * ・上記以外の場合<BR>
     * <BR>
     * 　@this.注文エラー理由コード を返す。<BR>
     * --------------------------------------------------<BR>
     * ------------------------<BR>
     * @@return String
     * @@roseuid 42A555C100D9
     */
    public String getAcceptStatusDiv() 
    {
        final String STR_METHOD_NAME = "getAcceptStatusDiv()";
        log.entering(STR_METHOD_NAME); 
        
        String l_strReturn;       
        String l_mdyCancelType = this.m_row.getModifyCancelType();
        OrderEventTypeEnum l_oetEventType = this.getOrderEventType();
        OrderStatusEnum l_oetStatus = this.getOrderStatus();
        
        //this.注文訂正・取消区分 == "取消失敗"の場合
        if (WEB3ModifyCancelTypeDef.CANCEL_ERROR.equals(l_mdyCancelType))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.CANCEL_ERROR;
        }
        //this.注文訂正・取消区分 == "訂正失敗"の場合
        else if (WEB3ModifyCancelTypeDef.CHANGE_ERROR.equals(l_mdyCancelType))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.CHANGE_ERROR;
        }
        //this.注文状態 == NOT_ORDERED（発注失敗（新規注文））
        //かつ　@this.注文イベントタイプ == REJECTED_BY_MARKET 
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_oetStatus)
            && OrderEventTypeEnum.REJECTED_BY_MKT.equals(l_oetEventType))
        {
            l_strReturn = WEB3ErrorReasonCodeDef.ACCEPT_ERROR;
        } 
        else 
        {
            l_strReturn = this.m_row.getErrorReasonCode();
        }

        log.debug(STR_METHOD_NAME + ".get受付結果区分 : " + l_strReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }
}
@
