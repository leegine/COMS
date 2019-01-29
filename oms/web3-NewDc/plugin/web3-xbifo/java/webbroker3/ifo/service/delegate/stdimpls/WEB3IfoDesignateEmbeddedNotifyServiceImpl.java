head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoDesignateEmbeddedNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP指定埋通知サービスImpl(WEB3IfoDesignateEmbeddedNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/17 張宝楠 (中訊) 新規作成
                          001: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Contract;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.data.HostFotypeClosingContSpecParams;
import webbroker3.ifo.data.HostFotypeClosingContSpecRow;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.service.delegate.WEB3IfoDesignateEmbeddedNotifyService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingTypesDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;

import webbroker3.gentrade.WEB3GentradeBizDate;


/**
 * (先物OP指定埋通知サービスImpl)<BR>
 * <BR>
 * 先物OP指定埋通知サービス実装クラス<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_JOIN_EXISTING）<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3IfoDesignateEmbeddedNotifyServiceImpl implements WEB3IfoDesignateEmbeddedNotifyService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDesignateEmbeddedNotifyServiceImpl.class);

    /**
     * (create指定埋通知)<BR>
     * <BR>
     * 指定埋通知キューテーブルに行を作成する。<BR>
     * <BR>
     * １）　@注文カテゴリ判定<BR>
     * 　@返済注文でない場合（注文単位.注文カテゴリ != OrderCategEnum.”返済注文”）、<BR>
     * 処理を終了する。<BR>
     * 　@返済注文の場合のみ、以降の処理を実施する。<BR>
     * <BR>
     *２）　@返済指定情報取得<BR>
　@   *以下の手順にて、注文単位に関連する返済指定情報をすべて取得する。<BR>
     *２－１）引数の注文単位を、IfoContractSettleOrderUnit型にキャストする。<BR>
     *２－２）注文単位.getContractsToClose()メソッドをコールする。<BR>
     * <BR>
     * ３）　@指定埋通知行作成<BR>
     * 　@取得した返済指定情報の要素毎に、指定埋通知行をinsertする。<BR>
     * 　@以下、返済指定情報の要素毎の繰り返し処理。<BR>
     *
     * 　@３－１）　@建玉オブジェクト取得<BR>
     * 　@先物OPポジションマネージャ.getContract()にて、返済指定情報.建玉ＩＤに<BR>
     * 該当する建玉オブジェクトを取得する。<BR>
     * <BR>
     * 　@[getContract()に指定する引数]<BR>
     * 　@建玉ＩＤ：　@返済指定情報.建玉ＩＤ<BR>
     * <BR>
     * 　@３－２）　@先物OP指定埋通知Paramsオブジェクト生成<BR>
     * 　@先物OP指定埋通知Paramsオブジェクトを生成し、<BR>
     * 　@注文単位、返済指定情報、建玉の内容より、プロパティをセットする。<BR>
     * <BR>
     * 　@※ 先物OP指定埋通知行編集内容については、<BR>
     * 　@「共通_先物OP指定埋通知キューテーブル.xls」<BR>
     *  　@[先物OP指定埋通知ｷｭｰ DB更新仕様]シートを参照。<BR>
     * <BR>
     * 　@３－３）　@行insert<BR>
     * 　@生成した行をDBにinsertする。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@roseuid 408CCB9B02C7
     */
    public void createDesignateEmbeddedNotify(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createDesignateEmbeddedNotify(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //返済注文でない場合（注文単位.注文カテゴリ != OrderCategEnum.”返済注文”）
        //処理を終了する。
        //if (!OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
        if (!(OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()) || 
                OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg()) ||
                OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg())
               ))
        {
            return;
        }
        

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3IfoPositionManagerImpl l_positionManager =
            (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();

        //返済指定情報取得
        IfoClosingContractSpec[] l_closingContractSpecs = ((IfoContractSettleOrderUnit)l_orderUnit).getContractsToClose();

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        try
        {

            AccountManager l_accountMgr = l_finApp.getAccountManager();
            Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());    //throw NotFoundException
            MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_orderUnit.getAccountId());    //throw NotFoundException

            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
            
            //扱者コード
            String l_strTraderCode = null;
            if (l_orderUnit.getTraderId() != 0)
            {
                Trader l_trader = l_finObjMgr.getTrader(l_orderUnit.getTraderId());     //throw NotFoundException
                l_strTraderCode = l_trader.getTraderCode();
            }
            
            Market l_market = l_finObjMgr.getMarket(l_orderUnitRow.getMarketId());  //throw NotFoundException

            Product l_product = l_orderUnit.getProduct();
            IfoProductRow l_productRow = (IfoProductRow)l_product.getDataSourceObject();

            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException
            Timestamp l_currentTime = l_finApp.getTradingSystem().getSystemTimestamp();

            for (int i = 0; i < l_closingContractSpecs.length; i++)
            {

                //建玉オブジェクト取得
                Contract l_contract = l_positionManager.getContract(l_closingContractSpecs[i].getContractId());   //throw NotFoundException
                IfoContractRow l_contractRow = (IfoContractRow)l_contract.getDataSourceObject();

                //先物OP指定埋通知Paramsオブジェクト生成
                HostFotypeClosingContSpecParams l_params = new HostFotypeClosingContSpecParams();
                if(WEB3FuturesOptionDivDef.OPTION.equals(l_orderUnitRow.getFutureOptionDiv()))
                {
                    l_params.setRequestCode(WEB3HostRequestCodeDef.OPTION_DESIGNATE_EMBEDDED_INDICATION);   //データコード:EI806(株価指数オプション指定埋指示)
                }
                if(WEB3FuturesOptionDivDef.FUTURES.equals(l_orderUnitRow.getFutureOptionDiv()))
                {
                    l_params.setRequestCode(WEB3HostRequestCodeDef.FUTURES_DESIGNATE_EMBEDDED_INDICATION);   //データコード:EI807(株価指数先物指定埋指示)
                }
                l_params.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());            //証券会社コード
                l_params.setBranchCode(l_branch.getBranchCode());                                       //部店コード
                l_params.setAccountCode(l_mainAccount.getAccountCode());                                //顧客コード
                l_params.setTraderCode(l_strTraderCode);                                       //扱者コード
                l_params.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());                 //識別コード
                l_params.setMarketCode(Long.parseLong(l_market.getMarketCode()));                       //市場コード
                l_params.setTargetProductCode(l_productRow.getUnderlyingProductCode());                 //原資産銘柄コード

                l_params.setDelivaryMonth(l_productRow.getMonthOfDelivery());                           //限月
                
                //先物オプション商品
                if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_productRow.getDerivativeType()))
                {
                    l_params.setFutureOptionProductType(WEB3IfoProductTypeDef.CALL_OPTIONS);    
                }
                else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_productRow.getDerivativeType()))
                {
                    l_params.setFutureOptionProductType(WEB3IfoProductTypeDef.PUT_OPTIONS);
                }
                else if (IfoDerivativeTypeEnum.FUTURES.equals(l_productRow.getDerivativeType()))
                {
                    l_params.setFutureOptionProductType(WEB3IfoProductTypeDef.FUTURES);
                }
                
                double l_dblStrkePrice = l_productRow.getStrikePrice();
                if (Double.isNaN(l_dblStrkePrice))
                {
                    l_dblStrkePrice = 0D;
                }
                l_params.setStrikePrice(l_dblStrkePrice);                                 //行使価格
                
                l_params.setSplitType(l_productRow.getSplitType());                                     //分割
                l_params.setContractType(l_contractRow.getContractType());                              //建区分
                l_params.setClosingType(WEB3ClosingTypesDef.OPEN_DATE_UNIT_PRICE);                      //指定区分: 4(建日・単価)

                //建日の前営業日の取得
                WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_contractRow.getOpenDate());
                Date l_prevBizDate = l_gentradeBizDate.roll(-1);
                l_params.setOpenDate(l_prevBizDate);  
                //建約定年月日
                double l_dblContractPrice = l_contractRow.getContractPrice();
                if (Double.isNaN(l_dblContractPrice))
                {
                    l_dblStrkePrice = 0D;
                }
                double l_dblQuantity = l_closingContractSpecs[i].getQuantity();
                if (Double.isNaN(l_dblQuantity))
                {
                    l_dblQuantity = 0D;
                }
                l_params.setStrikePrice(l_dblStrkePrice);   

                l_params.setContractPrice(l_dblContractPrice);                            //建単価
                
                l_params.setQuantity(l_dblQuantity);                          //建数量
                l_params.setCreateDatetime(l_currentTime);                                              //指示日時
                l_params.setLastUpdatedTimestamp(l_currentTime);                                        //更新日時
                l_params.setStatus(WEB3StatusDef.NOT_DEAL);                                             //処理区分
                //行insert
                l_processor.doInsertQuery(l_params);    //throw DataFindException, DataQueryException, DataNetworkException
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (undo指定埋通知)<BR>
     * <BR>
     * 指定埋通知キューテーブルの行を無効にする。<BR>
     * <BR>
     * 　@先物OP指定埋通知キューテーブルの以下の条件に当てはまる行を削除（delete）する。<BR>
     * <BR>
     * [条件]<BR>
     * 先物OP指定埋通知キューテーブル.証券会社コード = <BR>
     * 　@注文単位.部店ＩＤに該当する部店の証券会社コード<BR>
     * 先物OP指定埋通知キューテーブル.部店コード = <BR>
     * 　@注文単位.部店ＩＤに該当する部店の部店コード<BR>
     * 先物OP指定埋通知キューテーブル.口座コード = <BR>
     * 　@注文単位.口座ＩＤに該当する顧客の口座コード<BR>
     * 先物OP指定埋通知キューテーブル.識別コード = <BR>
     * 　@注文単位.識別コード<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@roseuid 408F1B35001F
     */
    public void undoDesignateEmbeddedNotify(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " undoDesignateEmbeddedNotify(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        try
        {
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());    //throw NotFoundException
            //MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_orderUnit.getAccountId());    //throw NotFoundException

            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            String l_strWhere = " institution_code = ? and branch_code = ? and request_code  = ? and order_request_number = ? ";
            Object[] l_objWheres = {
                l_branch.getInstitution().getInstitutionCode(),
                l_branch.getBranchCode(),
                WEB3HostRequestCodeDef.OPTION_DESIGNATE_EMBEDDED_INDICATION,
                l_orderUnitRow.getOrderRequestNumber()};
            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException
            
            l_processor.doDeleteAllQuery(HostFotypeClosingContSpecRow.TYPE, l_strWhere, l_objWheres);    //throw DataQueryException, DataNetworkException
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
