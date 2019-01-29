head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.20.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式予約注文更新サービスImpl(WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 郭英 (中訊) 新規作成 
Revesion History : 2007/4/25 劉立峰 (中訊) モデルNO.232 
*/

package webbroker3.triggerorder.base.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvEqOrderActionParams;
import webbroker3.triggerorder.base.data.RsvEqOrderActionRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitDao;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株式予約注文更新サービスImpl)<BR>
 * 株式予約注文更新サービスの実装クラス。
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl implements WEB3ToSuccReservationEqTypeOrderUpdateService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl.class);
        
    /**
     * @@roseuid 4348D9800280
     */
    public WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl() 
    {
     
    }
    
    /**
     * (insert予約注文履歴)<BR>
     * 最新の株式予約注文単位テーブルの内容より、<BR>
     * 株式予約注文履歴を１レコード作成し登録する。<BR>
     * <BR>
     * １）　@株式予約注文単位テーブルから該当レコードを取得する。<BR>
     * <BR>
     * 　@株式予約注文単位テーブルより、引数の注文IDに該当するレコードを取得する。<BR>
     * <BR>
     * ２）　@取得した株式予約注文単位オブジェクトより、株式予約注文履歴を<BR>
     * １レコード登録する。<BR>
     * <BR>
     * 　@DB更新仕様<BR>
     * 　@「（連続）現物株式注文登録_株式予約注文履歴テーブル.xls」<BR>
     * 　@を参照。
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。<BR>
     * （株式予約注文単位.注文IDをセット）
     * @@throws WEB3BaseException
     * @@roseuid 43378D0602A2
     */
    public void insertReserveOrderAction(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " insertReserveOrderAction(long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //１）　@株式予約注文単位テーブルから該当レコードを取得する。
            //株式予約注文単位テーブルより、引数の注文IDに該当するレコードを取得する。
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = RsvEqOrderUnitDao.findRowByPk(l_lngOrderId);
            //DataFindException,DataNetworkException,DataQueryException
            
            //２）　@取得した株式予約注文単位オブジェクトより、株式予約注文履歴を
            //１レコード登録する。
            RsvEqOrderActionParams l_rsvEqOrderActionParams = new RsvEqOrderActionParams();
            
            //口座ＩＤ: 株式予約注文単位テーブルの同項目
            l_rsvEqOrderActionParams.setAccountId(l_rsvEqOrderUnitRow.getAccountId());
                                                                                            
            //補助口座ＩＤ: 株式予約注文単位テーブルの同項目
            l_rsvEqOrderActionParams.setSubAccountId(l_rsvEqOrderUnitRow.getSubAccountId());
                                                                                                
            //取引者ＩＤ: 株式予約注文単位テーブルの同項目
            if (!l_rsvEqOrderUnitRow.getTraderIdIsNull())
            {
                l_rsvEqOrderActionParams.setTraderId(l_rsvEqOrderUnitRow.getTraderId());
            }
                                                                                            
            //注文ＩＤ: 株式予約注文単位テーブルの同項目                                                                                
            l_rsvEqOrderActionParams.setOrderId(l_rsvEqOrderUnitRow.getOrderId());
            
            //注文数量: 株式予約注文単位テーブルの同項目                                                                                
            l_rsvEqOrderActionParams.setQuantity(l_rsvEqOrderUnitRow.getQuantity());
            
            //指値: 株式予約注文単位テーブルの同項目      
            if (!l_rsvEqOrderUnitRow.getLimitPriceIsNull())   
            {
                l_rsvEqOrderActionParams.setLimitPrice(l_rsvEqOrderUnitRow.getLimitPrice());                                                   
            }            
            
            //単価調整値: 株式予約注文単位テーブルの同項目      
            if (!l_rsvEqOrderUnitRow.getPriceAdjustValueIsNull()) 
            {
                l_rsvEqOrderActionParams.setPriceAdjustValue(l_rsvEqOrderUnitRow.getPriceAdjustValue());
            }                                                                                  
            
            //注文失効日付: 株式予約注文単位テーブルの同項目                                                                                    
            l_rsvEqOrderActionParams.setExpirationDate(l_rsvEqOrderUnitRow.getExpirationDate());
            
            //注文状態: 株式予約注文単位テーブルの同項目                                                                                
            l_rsvEqOrderActionParams.setOrderStatus(l_rsvEqOrderUnitRow.getOrderStatus());
            
            //注文有効状態: 株式予約注文単位テーブルの同項目                                                                                    
            l_rsvEqOrderActionParams.setOrderOpenStatus(l_rsvEqOrderUnitRow.getOrderOpenStatus());
            
            //失効区分: 株式予約注文単位テーブルの同項目                                                                                
            l_rsvEqOrderActionParams.setExpirationStatus(l_rsvEqOrderUnitRow.getExpirationStatus());
            
            //注文履歴番号: 株式予約注文単位テーブル.注文履歴最終通番                                                                           
            l_rsvEqOrderActionParams.setOrderActionSerialNo(l_rsvEqOrderUnitRow.getLastOrderActionSerialNo());
            
            //概算受渡代金: 株式予約注文単位テーブルの同項目        
            if (!l_rsvEqOrderUnitRow.getEstimatedPriceIsNull())      
            {
                l_rsvEqOrderActionParams.setEstimatedPrice(l_rsvEqOrderUnitRow.getEstimatedPrice());                                                        
            }            
            
            //注文経路区分: 株式予約注文単位テーブルの同項目                                                                                    
            l_rsvEqOrderActionParams.setOrderRootDiv(l_rsvEqOrderUnitRow.getOrderRootDiv());
            
            //作成日付: 現在時刻                                                                                
            l_rsvEqOrderActionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //更新日付: 株式予約注文単位テーブルの同項目
            l_rsvEqOrderActionParams.setLastUpdatedTimestamp(l_rsvEqOrderUnitRow.getLastUpdatedTimestamp());
            
			//IPアドレス: 
			// ログインセキュリティサービスが取得可能な場合：　@セッションより取得した同項目の値
			// ログインセキュリティサービスが取得不可な場合：　@null
			OpLoginSecurityService l_securityService = 
				(OpLoginSecurityService) Services.getService(
					OpLoginSecurityService.class);
            
			try 
			{
				String l_strIpAddress = 
					l_securityService.getSessionProperty(
						WEB3SessionAttributeDef.IP_ADDRESS);
				l_rsvEqOrderActionParams.setIpAddress(l_strIpAddress);
			} 
			catch (IllegalSessionStateException e) 
			{
				l_rsvEqOrderActionParams.setIpAddress(null);
			}
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            
            l_queryProcessor.doInsertQuery(l_rsvEqOrderActionParams);
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataFindException l_ex)
        {
            log.error("株式予約注文単位テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                              
    }
    
    /**
     * (invalidate予約注文単位)<BR>
     * （invalidateOrderUnit）<BR>
     * <BR>
     * 株式予約注文単位行を失効させる。<BR>
     * <BR>
     * １）　@引数の株式予約注文単位行を失効させる。（updateする）<BR>
     * <BR>
     * 　@DB更新仕様<BR>
     * 　@「連続注文発注（NG）_株式予約注文単位テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ２）　@株式予約注文履歴を作成する。<BR>
     * <BR>
     * 　@this.insert株式予約注文履歴(引数の株式予約注文単位行.注文ID)を<BR>
     * 　@コールする。
     * @@param l_rsvEqOrderUnitRow - (株式予約注文単位行)<BR>
     * 株式予約注文単位行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成される。
     * @@param l_strErrorCode - (発注エラーコード)<BR>
     * 発注エラーコード。<BR>
     * （エラー原因の特定が可能なErrorInfo.error_codeをセット、<BR>
     * 発注エラー以外で失効する場合、nullをセット）
     * @@throws WEB3BaseException
     * @@roseuid 4337904800BE
     */
    public void invalidateOrderUnit(
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow, 
        String l_strErrorCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " invalidateOrderUnit(RsvEqOrderUnitRow, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_rsvEqOrderUnitRow == null)
        {
            log.debug("株式予約注文単位行オブジェクトが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "株式予約注文単位行オブジェクトが存在しない。");
        }
        
        try
        {
            //１）　@引数の株式予約注文単位行を失効させる。（updateする）
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = 
                new RsvEqOrderUnitParams(l_rsvEqOrderUnitRow);
            
            //注文履歴最終通番: （既存値）＋１
            int l_intLastOrderActionSerialNo = l_rsvEqOrderUnitParams.getLastOrderActionSerialNo() + 1;
            l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(l_intLastOrderActionSerialNo);
            
            //注文状態:
            //引数の発注エラーコードが指定されている場合：
            //6:発注失敗（新規注文）
            // （OrderStatusEnumにて定義）
            //引数の発注エラーコードが指定されていない場合：（既存値） 
            if (!WEB3StringTypeUtility.isEmpty(l_strErrorCode)) 
            {
                l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            }
            
            //注文有効状態:
            //2:クローズ（OrderOpenStatusEnumにて定義）
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            
            //失効区分:
            //3:マーケット拒否（OrderExpirationStatusEnumにて定義）
            l_rsvEqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            
            //発注エラーコード:
            //引数の発注エラーコードが指定されている場合：
            //発注エラーコードをセット
            //（* エラー原因の特定が可能なErrorInfo.error_codeをセット）
            // 引数の発注エラーコードが指定されていない場合：（既存値）
            if (!WEB3StringTypeUtility.isEmpty(l_strErrorCode)) 
            {
                l_rsvEqOrderUnitParams.setOrderErrorCode(l_strErrorCode);
            }
            
            //更新日付: 現在日時
            l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doUpdateQuery(l_rsvEqOrderUnitParams);
            
            //２）　@株式予約注文履歴を作成する。
            //this.insert株式予約注文履歴(引数の株式予約注文単位行.注文ID)をコールする。
            this.insertReserveOrderAction(l_rsvEqOrderUnitRow.getOrderId());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (invalidateAll予約注文単位)<BR>
     * （invalidateAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文単位を<BR>
     * 全て失効させる。<BR>
     * ※失効処理が行われなかった（有効な予約注文なし）場合、<BR>
     * 　@falseを返却する。以外、trueを返却する。<BR>
     * <BR>
     * １）　@有効な株式予約注文単位レコードを取得する。<BR>
     * 　@　@　@this.get有効予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。<BR>
     * 　@　@　@該当データなしの場合、falseを返却する。<BR>
     * <BR>
     * ２）　@１）の戻り値の要素数分、以下の処理を行う。<BR>
     * 　@２−１）　@this.invalidate予約注文単位(処理対象の要素, null)をコールする。<BR>
     * <BR>
     * ３）　@trueを返却する。<BR>
     * @@param l_lngParentOrderId - （親注文の注文ID）<BR>
     * 親注文の注文ID。<BR>
     * 
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4344AA59016F
     */
    public boolean invalidateAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " invalidateAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@有効な株式予約注文単位レコードを取得する。
        List l_lisOrderUnitRows = this.getOpenReserveEqtypeOrderUnits(l_lngParentOrderId);
        
        //該当データなしの場合、falseを返却する。
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())        
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）　@１）の戻り値の要素数分、以下の処理を行う。
        //２−１）　@this.invalidate予約注文単位(処理対象の要素, null)をコールする。
        int l_intCnt = l_lisOrderUnitRows.size();
        
        for (int i = 0; i < l_intCnt; i++)
        {
            this.invalidateOrderUnit((RsvEqOrderUnitRow)l_lisOrderUnitRows.get(i), null);
        }
        
        //３）　@trueを返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (cancel予約注文単位)<BR>
     * （cancelOrderUnit）<BR>
     * 引数の株式予約注文単位行を取消する。<BR>
     * <BR>
     * １）　@引数の株式予約注文単位行を取消する。（updateする）<BR>
     * <BR>
     * 　@DB更新仕様<BR>
     * 　@「（連続）現物株式注文取消_株式予約注文単位テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ２）　@株式予約注文履歴を作成する。<BR>
     * <BR>
     * 　@this.insert予約注文履歴(引数の株式予約注文単位行.注文ID)をコールする。<BR>
     * @@param l_rsvEqOrderUnitRow - (株式予約注文単位行)<BR>
     * 株式予約注文単位行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成される。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433A1D6802AF
     */
    public void cancelOrderUnit(RsvEqOrderUnitRow l_rsvEqOrderUnitRow) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " cancelOrderUnit(RsvEqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        if (l_rsvEqOrderUnitRow == null)
        {
            log.debug("株式予約注文単位行オブジェクトが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "株式予約注文単位行オブジェクトが存在しない。");
        }
        
        //１）　@引数の株式予約注文単位行を取消する。（updateする）
        RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams(l_rsvEqOrderUnitRow);
        
        try
        {
            //取引者ＩＤ(trader_id) :
            //ログインセキュリティサービスが取得可能な場合：
            //セッションから取得したログインIDに該当する扱者.取引者ID
            //※取得できなかった場合は、nullをセット        
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);        
            long l_loginId = l_opLoginSecurityService.getLoginInfo().getLoginId();//IllegalSessionStateException
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
            Trader l_trader = null;
            
            try
            {
                l_trader = l_finObjMgr.getTraderByLoginId(l_loginId);
                l_rsvEqOrderUnitParams.setTraderId(l_trader.getTraderId());
            }
            catch (NotFoundException l_ex)
            {
                l_rsvEqOrderUnitParams.setTraderId(null);
            }
                        
            //注文経路区分(order_root_div) :
            //ログインセキュリティサービスが取得可能な場合：セッションから取得した注文経路区分    
            l_rsvEqOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV)); 
        }
        catch (IllegalSessionStateException l_ex)
        {
            //取引者ＩＤ(trader_id) :
            //ログインセキュリティサービスが取得不可な場合：（既存値）
            //注文経路区分(order_root_div) :
            //ログインセキュリティサービスが取得不可な場合：（既存値）
        } 
        
        //注文履歴最終通番(last_order_action_serial_no) :（既存値） + 1
        l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(l_rsvEqOrderUnitParams.getLastOrderActionSerialNo() + 1);
        
        //注文状態(order_status) :14:発注済（取消注文）
        l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
        
        //注文有効状態(order_open_status) :2:クローズ
        l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);        
        
        //更新日付(last_updated_timestamp) :現在日時（GtlUtils.getSystemTimestamp()）
        l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doUpdateQuery(l_rsvEqOrderUnitParams);//DataNetworkException,DataQueryException
            
            //２）　@株式予約注文履歴を作成する。
            //this.insert予約注文履歴(引数の株式予約注文単位行.注文ID)をコールする。
            this.insertReserveOrderAction(l_rsvEqOrderUnitRow.getOrderId());
                
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
    }
    
    /**
     * (cancelAll予約注文単位)<BR>
     * （cancelAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文単位を<BR>
     * 全て取消する。<BR>
     * ※取消処理が行われなかった（有効な予約注文なし）場合、<BR>
     * 　@falseを返却する。以外、trueを返却する。<BR>
     * <BR>
     * １）　@有効な株式予約注文単位レコードを取得する。<BR>
     * 　@　@　@this.get有効予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。<BR>
     * 　@　@　@該当データなしの場合、falseを返却する。<BR>
     * <BR>
     * ２）　@１）の戻り値の要素数分、以下の処理を行う。<BR>
     * 　@２−１）　@this.cancel予約注文単位(処理対象の要素)をコールする。<BR>
     * <BR>
     * ３）　@trueを返却する。<BR>
     * @@param l_lngParentOrderId - （親注文の注文ID）<BR>
     * 親注文の注文ID。<BR>
     * 
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 433A18D20109
     */
    public boolean cancelAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " cancelAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@有効な株式予約注文単位レコードを取得する。
        //this.get有効予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。
        List l_lisOrderUnitRows = this.getOpenReserveEqtypeOrderUnits(l_lngParentOrderId);
        
        //該当データなしの場合、falseを返却する。
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())        
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）　@１）の戻り値の要素数分、以下の処理を行う。
        //２−１）　@this.cancel予約注文単位(処理対象の要素)をコールする。
        int l_intCnt = l_lisOrderUnitRows.size();
        
        for (int i = 0; i < l_intCnt; i++)
        {
            this.cancelOrderUnit((RsvEqOrderUnitRow)l_lisOrderUnitRows.get(i));
        }
        
        //３）　@trueを返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (expire予約注文単位)<BR>
     * （expireOrderUnit）<BR>
     * 引数の株式予約注文単位行を失効させる。<BR>
     * <BR>
     * １）　@引数の株式予約注文単位行を失効させる。（updateする）<BR>
     * <BR>
     * 　@　@DB更新仕様<BR>
     * 　@　@「現物株式出来終了通知_株式予約注文単位テーブル.xls」<BR>
     * 　@　@を参照。<BR>
     * <BR>
     * ２）　@株式予約注文履歴を作成する。<BR>
     * <BR>
     * 　@　@　@this.insert予約注文履歴(引数の株式予約注文単位行.注文ID)をコールする。<BR>
     * @@param l_rsvEqOrderUnitRow - (株式予約注文単位行)<BR>
     * 株式予約注文単位行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成される。<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 433A20720399
     */
    public void expireOrderUnit(RsvEqOrderUnitRow l_rsvEqOrderUnitRow) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " expireOrderUnit(RsvEqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);     
        
        if (l_rsvEqOrderUnitRow == null)
        {
            log.debug("株式予約注文単位行オブジェクトが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "株式予約注文単位行オブジェクトが存在しない。");
        }  
        
        try
        {
            //１）　@引数の株式予約注文単位行を失効させる。（updateする）
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams(l_rsvEqOrderUnitRow);
        
            //注文履歴最終通番(last_order_action_serial_no) :（既存値）＋１
            l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(
                l_rsvEqOrderUnitParams.getLastOrderActionSerialNo() + 1);
        
            //注文有効状態(order_open_status) : 2:クローズ（OrderOpenStatusEnumにて定義）
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        
            //失効区分(expiration_status) : 2:終了（OrderExpirationStatusEnumにて定義） 
            l_rsvEqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
             
            //更新日付(last_updated_timestamp) : 現在日時（GtlUtils.getSystemTimestamp()）
            l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doUpdateQuery(l_rsvEqOrderUnitParams);//DataNetworkException,DataQueryException
            
            //２）　@株式予約注文履歴を作成する。
            //this.insert予約注文履歴(引数の株式予約注文単位行.注文ID)をコールする。
            this.insertReserveOrderAction(l_rsvEqOrderUnitRow.getOrderId());
                
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
    }
    
    /**
     * (expireAll予約注文単位)<BR>
     * （expireAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文単位を<BR>
     * 全て失効させる。<BR>
     * ※失効処理が行われなかった（有効な予約注文なし）場合、<BR>
     * 　@falseを返却する。以外、trueを返却する。<BR>
     * <BR>
     * １）　@有効な株式予約注文単位レコードを取得する。<BR>
     * 　@　@　@this.get有効予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。<BR>
     * 　@　@　@該当データなしの場合、falseを返却する。<BR>
     * <BR>
     * ２）　@１）の戻り値の要素数分、以下の処理を行う。<BR>
     * 　@２−１）　@this.expire予約注文単位(処理対象の要素)をコールする。<BR>
     * <BR>
     * ３）　@trueを返却する。<BR>
     * @@param l_lngParentOrderId - （親注文の注文ID）<BR>
     * 親注文の注文ID。<BR>
     * 
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 433A2072039B
     */
    public boolean expireAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " expireAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@有効な株式予約注文単位レコードを取得する。
        //this.get有効予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。
        List l_lisOrderUnitRows = this.getOpenReserveEqtypeOrderUnits(l_lngParentOrderId);
        
        //該当データなしの場合、falseを返却する。
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())        
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）　@１）の戻り値の要素数分、以下の処理を行う。
        //２−１）　@this.expire予約注文単位(処理対象の要素)をコールする。
        int l_intCnt = l_lisOrderUnitRows.size();
        
        for (int i = 0; i < l_intCnt; i++)
        {
            this.expireOrderUnit((RsvEqOrderUnitRow)l_lisOrderUnitRows.get(i));
        }
        
        //３）　@trueを返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (get有効予約注文単位一覧)<BR>
     * （getOpenReserveEqtypeOrderUnits）<BR>
     * <BR>
     * 指定された親注文に紐付く、有効な株式予約注文単位行の配列を返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@　@以下の条件を指定して、【株式予約注文単位テーブル】を検索する。<BR>
     * <BR>
     * 　@　@------------------------------- <BR>
     * 　@　@＜検索条件＞<BR>
     * <BR>
     * 　@　@　@　@親注文の注文ID = 引数.親注文の注文ID <BR>
     * 　@　@かつ　@注文有効状態 = "オープン"<BR>
     * <BR>
     * 　@　@※「親注文内連番」で昇順ソート指定する。<BR>
     * 　@　@------------------------------- <BR>
     * <BR>
     * 　@　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ２）　@検索結果を返却する。
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 433A1C640157
     */
    public List getOpenReserveEqtypeOrderUnits(long l_lngParentOrderId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOpenReserveEqtypeOrderUnits(long)";
        log.entering(STR_METHOD_NAME);
        
        try 
        {
            //１）　@DB検索
            //以下の条件を指定して、【株式予約注文単位テーブル】を検索する。
            String l_strWhere = " parent_order_id = ? and order_open_status = ? ";                
            Object[] l_objs = {new Long(l_lngParentOrderId), OrderOpenStatusEnum.OPEN};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
              
            List l_lisRsvEqOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvEqOrderUnitRow.TYPE, 
                    l_strWhere, 
                    "serial_no_in_parent asc",
                    null,
                    l_objs);//DataNetworkException,DataQueryException
            
            if (l_lisRsvEqOrderUnitRows != null && !l_lisRsvEqOrderUnitRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                
                //２）　@検索結果を返却する。
                return l_lisRsvEqOrderUnitRows;
            }

            log.exiting(STR_METHOD_NAME);
            
            //検索結果が取得できなかった場合、nullを返却する。
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (deleteAll予約注文単位)<BR>
     * （deleteAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文データを<BR>
     * 全て削除する。<BR>
     * ※注文繰越スキップ銘柄通知サービスにて使用のため、<BR>
　@   *   現引現渡の場合の考慮はなし（現引現渡は当日限り注文のみ）<BR>
     * ※削除処理が行われなかった（予約注文なし）場合、<BR>
     * 　@falseを返却する。以外、trueを返却する。<BR>
     * ※データベースdeleteは、全てクエリプロセッサを使用し、<BR>
     * 　@SQL文を発行することで行う。<BR>
     * <BR>
     * １）　@株式予約注文単位レコードを取得する。<BR>
     * 　@　@　@this.get予約注文単位一覧(パラメータ.親注文の注文ID)<BR>
     * 　@　@　@をコールする。<BR>
     * 　@　@　@該当データなしの場合、falseを返却する。<BR>
     * <BR>
     * ２）　@１）の戻り値の要素数分、以下の処理を行う。<BR>
     * <BR>
     * 　@２−１）　@該当予約注文が既存建に対する返済注文の場合<BR>
     * 　@　@　@　@　@　@（処理対象の要素.連続注文取引区分=="信用返済（既存残）"）、<BR>
     * <BR>
     * 　@　@　@　@　@　@予約建株返済指定情報データを<BR>
     * 　@　@　@　@　@　@【株式予約建株返済指定情報テーブル】よりdeleteする。<BR>
     * 　@　@　@　@　@　@削除キーには、処理対象の要素.注文ID を指定する。<BR>
     * <BR>
     * 　@２−２）　@予約注文履歴データを<BR>
     * 　@　@　@　@　@　@【株式予約注文履歴テーブル】よりdeleteする。<BR>
     * 　@　@　@　@　@　@削除キーには、処理対象の要素.注文ID を指定する。<BR>
     * <BR>
     * 　@２−３）　@予約注文単位のデータを<BR>
     * 　@　@　@　@　@　@【株式予約注文単位テーブル】よりdeleteする。<BR>
     * 　@　@　@　@　@　@削除キーには、処理対象の要素.注文ID を指定する。<BR>
     * <BR>
     * ３）　@trueを返却する。<BR>
     * @@param l_lngParentOrderId - （親注文の注文ID）<BR>
     * 親注文の注文ID。<BR>
     * 
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4355E2000186
     */
    public boolean deleteAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //１）　@株式予約注文単位レコードを取得する。
            //this.get予約注文単位一覧(パラメータ.親注文の注文ID)
            List l_lisOrderUnitRows = this.getReserveEqtypeOrderUnits(l_lngParentOrderId);
            
            //該当データなしの場合、falseを返却する。
            if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())        
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
                 
            //２）　@１）の戻り値の要素数分、以下の処理を行う。   
            int l_intCnt = l_lisOrderUnitRows.size();
            
            for (int i = 0; i < l_intCnt; i++)
            {
                RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_lisOrderUnitRows.get(i);
                
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                //DataNetworkException,DataQueryException
                
                String l_strWhere = " order_id = ? ";                
                Object[] l_objs = {new Long(l_rsvEqOrderUnitRow.getOrderId())};  
                
                //２−１）　@該当予約注文が既存建に対する返済注文の場合
                //（処理対象の要素.連続注文取引区分=="信用返済（既存残）"）、
                if (WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_EXISTING_REMAINDER.equals(
                        l_rsvEqOrderUnitRow.getReserveOrderTradingType()))
                {
                    //予約建株返済指定情報データを
                    //【株式予約建株返済指定情報テーブル】よりdeleteする。
                    //削除キーには、処理対象の要素.注文ID を指定する。                    
                    l_queryProcessor.doDeleteAllQuery(
                        RsvEqClosingContractSpecRow.TYPE, 
                        l_strWhere, 
                        l_objs);//DataNetworkException,DataQueryException                    
                }    
                
                //２−２）　@予約注文履歴データを
                //【株式予約注文履歴テーブル】よりdeleteする。
                //削除キーには、処理対象の要素.注文ID を指定する。                     
                l_queryProcessor.doDeleteAllQuery(
                    RsvEqOrderActionRow.TYPE, 
                    l_strWhere, 
                    l_objs);//DataNetworkException,DataQueryException
                
                //２−３）　@予約注文単位のデータを
                //【株式予約注文単位テーブル】よりdeleteする。
                //削除キーには、処理対象の要素.注文ID を指定する。
                l_queryProcessor.doDeleteAllQuery(
                    RsvEqOrderUnitRow.TYPE, 
                    l_strWhere, 
                    l_objs);//DataNetworkException,DataQueryException
            }  
            
            //３）　@trueを返却する。    
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get予約注文単位一覧)<BR>
     * （getReserveEqtypeOrderUnits）<BR>
     * <BR>
     * 指定された親注文に紐付く、株式予約注文単位行の配列を返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@　@以下の条件を指定して、【株式予約注文単位テーブル】を検索する。<BR>
     * <BR>
     * 　@　@------------------------------- <BR>
     * 　@　@＜検索条件＞<BR>
     * <BR>
     * 　@　@　@　@親注文の注文ID = 引数.親注文の注文ID <BR>
     * <BR>
     * 　@　@※「親注文内連番」で昇順ソート指定する。<BR>
     * 　@　@------------------------------- <BR>
     * <BR>
     * 　@　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ２）　@検索結果を返却する。
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getReserveEqtypeOrderUnits(long l_lngParentOrderId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getReserveEqtypeOrderUnits(long)";
        log.entering(STR_METHOD_NAME);
        
        try 
        {
            //１）　@DB検索
            //以下の条件を指定して、【株式予約注文単位テーブル】を検索する。
            String l_strWhere = " parent_order_id = ?";                
            Object[] l_objs = {new Long(l_lngParentOrderId)};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
              
            List l_lisRsvEqOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvEqOrderUnitRow.TYPE, 
                    l_strWhere, 
                    "serial_no_in_parent asc",
                    null,
                    l_objs);
            
            if (l_lisRsvEqOrderUnitRows != null && !l_lisRsvEqOrderUnitRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                
                //２）　@検索結果を返却する。
                return l_lisRsvEqOrderUnitRows;
            }

            log.exiting(STR_METHOD_NAME);
            
            //検索結果が取得できなかった場合、nullを返却する。
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (update予約注文データ)<BR>
     * (updateReserveOrderData)<BR>
     * <BR>
     * 指定された注文オブジェクトを使用し、QueryProcessorにより予約注文データ類の更新を行う。<BR>
     * <BR>
     * １） update引数の株式予約注文単位
     *     引数の株式予約注文単位Rowオブジェクトの内容で 株式予約注文単位テーブルをupdateする。<BR>
     * ２） insert引数の株式予約注文履歴
     *     引数の株式予約注文履歴Rowが"null"でない場合のみ、  <BR>
　@   *     引数の株式予約注文履歴Rowオブジェクトの内容で <BR>
　@   *     株式予約注文履歴テーブルにinsertする。  <BR>
     * <BR>
     * @@param l_rsvEqOrderUnitRow - (株式予約注文単位行)<BR>
     * 株式予約注文単位行。
     * @@param l_rsvEqOrderActionRow - (株式予約注文履歴行)<BR>
     * 株式予約注文履歴行。
     * @@throws WEB3BaseException
     */
    public void updateReserveOrderData(RsvEqOrderUnitRow l_rsvEqOrderUnitRow, 
        RsvEqOrderActionRow l_rsvEqOrderActionRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateReserveOrderData(RsvEqOrderUnitRow, RsvEqOrderActionRow)";
        log.entering(STR_METHOD_NAME);
        try 
        {
            // データを更新する
            QueryProcessor processor = Processors.getDefaultProcessor();
            // １） update引数の株式予約注文単位
            //P1口座ＩＤ  P2親注文の注文ＩＤ P3親注文内連番
            String l_strWhere = "order_id = ? ";
            Object[] l_objUpdWhere = {
                new Long(l_rsvEqOrderUnitRow.getOrderId())
                };
            processor.doUpdateQuery(
                l_rsvEqOrderUnitRow, 
                l_strWhere, 
                l_objUpdWhere
                );
            // ２） insert引数の株式予約注文履歴
            if (l_rsvEqOrderActionRow != null)
            {
                processor.doInsertQuery(l_rsvEqOrderActionRow);                
            }
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex) 
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);            
        }
        catch (DataQueryException l_ex) 
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);            
        }
    }
}
@
