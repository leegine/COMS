head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連携通知一件サービスImpl(WEB3AioCashinCooperationNotifyUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/11 黄建(中訊) 新規作成
Revesion History : 2006/08/31 車進 (中訊) モデルNo.627
Revesion History : 2006/09/18 徐宏偉 (中訊)モデルNo.648, No.649
Revesion History : 2006/11/14 徐宏偉 (中訊)  ＤＢ更新仕様　@No.131
Revesion History : 2006/11/14 徐宏偉 (中訊)  ＤＢ更新仕様　@No.132
Revesion History : 2007/03/12 何文敏 (中訊)  モデルNo.713
Revesion History : 2007/07/12 孟亜南 (中訊)  モデルNo.735
Revesion History : 2007/07/28 孟亜南(中訊) 仕様変更モデル746
Revesion History : 2007/08/06 張騰宇(中訊) 仕様変更モデル750
Revesion History : 2009/02/06 柴双紅 (中訊) モデルNo.1096,No.1105,No.1106,No.1107
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioCashinCooperationOrderUpdateInterceptor;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.BankDepositErrorHistoryDao;
import webbroker3.aio.data.BankDepositErrorHistoryParams;
import webbroker3.aio.data.BankDepositNotifyDao;
import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.aio.data.BankDepositNotifyRow;
import webbroker3.aio.define.WEB3AioErrorCommentDef;
import webbroker3.aio.service.delegate.WEB3AioCashinCooperationNotifyUnitService;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3CompanyFormationDivDef;
import webbroker3.common.define.WEB3ErrorCommentDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3UpdatePersonDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 入金連携通知一件サービスImpl<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AioCashinCooperationNotifyUnitServiceImpl
    implements WEB3AioCashinCooperationNotifyUnitService
{
    /**
     *  ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashinCooperationNotifyUnitServiceImpl.class);
    
    /**
     * (notify入金連携)<BR>
     * 入金通知の注文登録する。<BR>
     * <BR>
     * シーケンス図「(入金連携通知一件サービスImpl).notify入金連携」 参照<BR>
     * <BR>
     * @@param l_bankDepositNotifyParams - 入金通知Params<BR>
     * @@param l_mainAccount  顧客 <BR>
     * @@throws WEB3BaseException
     * @@throws DataQueryException
     * @@throws DataNetworkException
     * @@throws DataFindException
     * @@roseuid 40BEFA0600BA
     */
    public void notifyCashinCooperation(
        BankDepositNotifyParams l_bankDepositNotifyParams, 
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCashinCooperation(BankDepositNotifyParams, WEB3GentradeMainAccount) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bankDepositNotifyParams == null || l_mainAccount == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータNull出来ない。");     
        }

        //toDate(japaneseEra : String, dateString : String)
        //入金起算日を和暦文字列から西暦日付に変換する。
        //引数：
        //年号 = 年号.getJapEraDiv(入金通知Params.入金起算日)の戻り値
        //日付 = 入金通知Params.入金起算日
        String l_strDepositDataBaseDate =
            l_bankDepositNotifyParams.getDepositDataBaseDate();
        Date l_datDepositDataBaseDate = WEB3GentradeEra.toDate(
            WEB3GentradeEra.getJapEraDiv(l_strDepositDataBaseDate),
            l_strDepositDataBaseDate);

        //結果がnullの場合
        if (l_datDepositDataBaseDate == null)
        {
            //当該入金通知レコードを更新
            Map l_mapSpac = new HashMap();
            //[更新内容]
            //処理区分 = "エラー"
            l_mapSpac.put("status", WEB3StatusDef.DATA_ERROR);

            //エラーコメント = "入金起算日エラー"
            l_mapSpac.put("deposit_error_comment",
               WEB3AioErrorCommentDef.DEPOSITDATA_BASEDATE_ERROR);

            //エラー履歴最終通番
            String l_strLastErrorHistorySerialNo =
                l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + "";
            l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);

            //update入金通知(入金通知Params, Map)
            this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);

            //入金通知処理エラー履歴テーブルに1件追加する。
            l_bankDepositNotifyParams.setLastErrorHistorySerialNo(
                l_bankDepositNotifyParams.getLastErrorHistorySerialNo());

            //処理区分 = "エラー"
            l_bankDepositNotifyParams.setStatus(WEB3StatusDef.DATA_ERROR);

            //エラーコメント = "入金起算日エラー"
            l_bankDepositNotifyParams.setDepositErrorComment(
                WEB3AioErrorCommentDef.DEPOSITDATA_BASEDATE_ERROR);

            //insert入金通知処理エラー履歴(入金通知Params)
            this.insertDepositErrorHistory(l_bankDepositNotifyParams);

            log.exiting(STR_METHOD_NAME);
            return;
        }

        //toDate(japaneseEra : String, dateString : String)
        //勘定日を和暦文字列から西暦日付に変換する。
        //引数：
        //年号 = 年号.getJapEraDiv(入金通知Params.勘定日)の戻り値
        //日付 = 入金通知Params.勘定日
        String l_strDepositDataAccountDate =
            l_bankDepositNotifyParams.getDepositDataAccountDate();
        Date l_datDepositDataAccountDate = WEB3GentradeEra.toDate(
            WEB3GentradeEra.getJapEraDiv(l_strDepositDataAccountDate),
            l_strDepositDataAccountDate);

        //結果がnullの場合
        if (l_datDepositDataAccountDate == null)
        {
            //当該入金通知レコードを更新
            Map l_mapSpac = new HashMap();
            //[更新内容]
            //処理区分 = "エラー"
            l_mapSpac.put("status", WEB3StatusDef.DATA_ERROR);

            //エラーコメント = "勘定日エラー"
            l_mapSpac.put("deposit_error_comment",
               WEB3AioErrorCommentDef.SETTLEMENTDATE_ERROR);

            //エラー履歴最終通番
            String l_strLastErrorHistorySerialNo =
                l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + "";
            l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);

            //update入金通知(入金通知Params, Map)
            this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);

            //入金通知処理エラー履歴テーブルに1件追加する。
            l_bankDepositNotifyParams.setLastErrorHistorySerialNo(
                l_bankDepositNotifyParams.getLastErrorHistorySerialNo());

            //処理区分 = "エラー"
            l_bankDepositNotifyParams.setStatus(WEB3StatusDef.DATA_ERROR);

            //エラーコメント = "勘定日エラー"
            l_bankDepositNotifyParams.setDepositErrorComment(
                WEB3AioErrorCommentDef.SETTLEMENTDATE_ERROR);

            //insert入金通知処理エラー履歴(入金通知Params)
            this.insertDepositErrorHistory(l_bankDepositNotifyParams);

            log.exiting(STR_METHOD_NAME);
            return;
        }

        //1.1 入金通知Params.部店コード != null かつ
        //入金通知Params.顧客コード != nullの場合
        //名寄せチェックを行わず注文を登録する。
        //１）submit注文
        //２）当該入金通知レコードを以下のように更新する。
        //[更新内容]
        //処理区分 = 処理済
        //部店コード = 顧客マスタ.部店コード
        //顧客コード = 顧客マスタ.顧客コード
        if (WEB3StringTypeUtility.isNotEmpty(l_bankDepositNotifyParams.getBranchCode()) &&
            WEB3StringTypeUtility.isNotEmpty(l_bankDepositNotifyParams.getAccountCode()))
        {    
            //入金通知Params.通貨コード != null の場合、、以下の処理を実行
            if (l_bankDepositNotifyParams.getCurrencyCode() != null)
            {
                //1.1.1.1 外貨コードの妥当性をチェックする。 
                //[引数] 
                //証券会社コード： 入金通知Params.get証券会社コード 
                //通貨コード： 入金通知Params.get通貨コード
                boolean l_blnForeignCode = this.isForeignCurrencyCode(
                    l_bankDepositNotifyParams.getInstitutionCode(), 
                    l_bankDepositNotifyParams.getCurrencyCode());
                
                //1.1.1.2 falseの場合
                if (!l_blnForeignCode)
                {
                    //1.1.1.2.1  update入金通知(入金通知Params, Map)
                    //[更新内容]
                    //処理区分 = "エラー"
                    //エラーコメント = "外貨コード不整合エラー"
                    Map l_mapSpac = new HashMap();

                    l_mapSpac.put("status", WEB3StatusDef.DATA_ERROR);
                    l_mapSpac.put("deposit_error_comment", WEB3AioErrorCommentDef.FOREIGN_ERROR);
                    
                    //エラー履歴最終通番
                    String l_strLastErrorHistorySerialNo = 
                        l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + "";
                    l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);

                    this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);
                    
                    l_bankDepositNotifyParams.setLastErrorHistorySerialNo(
                        l_bankDepositNotifyParams.getLastErrorHistorySerialNo());
                    
                    l_bankDepositNotifyParams.setStatus(WEB3StatusDef.DATA_ERROR);
                    
                    l_bankDepositNotifyParams.setDepositErrorComment(
                        WEB3AioErrorCommentDef.FOREIGN_ERROR);
                    
                    //1.1.1.2.2   insert入金通知処理エラー履歴(入金通知Params)
                    this.insertDepositErrorHistory(l_bankDepositNotifyParams);
                    
                    //1.1.1.2.3
                    return;
                }

                double l_dblSellExecRate = 0;
                // （共通）通貨(証券会社コード : String, 通貨コード : String)
                // [引数]
                // 証券会社コード：　@入金通知Params.証券会社コード
                // 通貨コード：　@入金通知Params.通貨コード
                WEB3GentradeCurrency l_genCurrency = WEB3GentradeCurrency.genCurrency(
                        l_bankDepositNotifyParams.getInstitutionCode(), 
                        l_bankDepositNotifyParams.getCurrencyCode());
                
                // get売付約定為替レート( )
                // 売付約定為替レートを取得する。
                l_dblSellExecRate = l_genCurrency.getSellExecRate();
                
                // is正常桁数
                // [引数]
                // 取引金額：入金通知Params.取引金額
                // 為替レート： get売付約定為替レート()の戻り値
                boolean l_blnIsNormalLength =
                    this.isNormalLength(
                        l_bankDepositNotifyParams.getDepositDataDepositAmount(), l_dblSellExecRate);
                if (!l_blnIsNormalLength)
                {
                    //update入金通知(入金通知Params, Map)
                    //[更新内容]
                    //処理区分 = "エラー"
                    //エラーコメント = "円換算額桁数エラー"
                    Map l_mapSpac = new HashMap();

                    l_mapSpac.put("status", WEB3StatusDef.DATA_ERROR);
                    l_mapSpac.put("deposit_error_comment", WEB3AioErrorCommentDef.CONVER_AMOUNT_LENGTH_ERROR);
                    
                    //エラー履歴最終通番
                    String l_strLastErrorHistorySerialNo = 
                        l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + "";
                    l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);
                    this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);

                    l_bankDepositNotifyParams.setLastErrorHistorySerialNo(
                        l_bankDepositNotifyParams.getLastErrorHistorySerialNo());
                    l_bankDepositNotifyParams.setStatus(WEB3StatusDef.DATA_ERROR);
                    l_bankDepositNotifyParams.setDepositErrorComment(
                        WEB3AioErrorCommentDef.CONVER_AMOUNT_LENGTH_ERROR);
                    
                    // insert入金通知処理エラー履歴(入金通知Params)
                    this.insertDepositErrorHistory(l_bankDepositNotifyParams);

                    return;
                }
                
                
            }

            //1.1.2 submit注文(入金通知Params, 顧客)
            this.submitOrder(l_bankDepositNotifyParams, l_mainAccount);
            
            //1.1.3 update入金通知(入金通知Params, Map)
            Map l_mapSpac = new HashMap();
            l_mapSpac.put("status", WEB3StatusDef.DEALT);
            l_mapSpac.put("branch_code", l_mainAccount.getBranch().getBranchCode());
            l_mapSpac.put("account_code", l_mainAccount.getAccountCode());
            //エラー履歴最終通番
            String l_strLastErrorHistorySerialNo = 
                l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + "";
            l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);

            this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);
        }
        
        //1.2 上記(1.1)以外
        else
        {
            //1.2.1 check顧客名(入金通知Params, 顧客)
            boolean l_blnIsAccountName = 
                this.checkAccountName(l_bankDepositNotifyParams, l_mainAccount);
            
            //1.2.2 trueの場合
            if (l_blnIsAccountName)
            {   
                //1.2.2.1 submit注文(入金通知Params, 顧客)
                //名寄せOKの場合
                //入金通知の注文登録する。
                //１）submit注文
                this.submitOrder(l_bankDepositNotifyParams, l_mainAccount);
                
                //1.2.2.2 update入金通知(入金通知Params, Map)
                //２）当該入金通知レコードを以下のように更新する。
                //[更新内容]
                //処理区分 = 処理済
                //部店コード = 顧客マスタ.部店コード
                //顧客コード = 顧客マスタ.顧客コード
                Map l_mapSpac = new HashMap();
                l_mapSpac.put("status", WEB3StatusDef.DEALT);
                l_mapSpac.put("branch_code", l_mainAccount.getBranch().getBranchCode());
                l_mapSpac.put("account_code", l_mainAccount.getAccountCode());
                //エラー履歴最終通番
                String l_strLastErrorHistorySerialNo = 
                    l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + "";
                l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);

                this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);
            }
            else
            {
                //1.2.3 falseの場合
                //名寄せエラーの場合
                //当該入金通知レコードを更新し、
                //入金通知処理エラー履歴テーブルに1件追加する。
                //[更新内容]
                //処理区分 = "エラー"
                //エラーコメント = "名寄せエラー"
                //部店コード = 顧客マスタ.部店コード
                //顧客コード = 顧客マスタ.顧客コード
                //1.2.3.1  update入金通知(入金通知Params, Map)
                Map l_mapSpac = new HashMap();
                l_mapSpac.put("status", WEB3StatusDef.DATA_ERROR);
                l_mapSpac.put("deposit_error_comment", WEB3ErrorCommentDef.DEPOSIT_ERROR_COMMENT);
                l_mapSpac.put("branch_code", l_mainAccount.getBranch().getBranchCode());
                l_mapSpac.put("account_code", l_mainAccount.getAccountCode());
                //エラー履歴最終通番
                String l_strLastErrorHistorySerialNo = 
                    l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + 1 + "";
                l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);
                this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);
                
                BankDepositNotifyRow l_bankDepositNotifyRow = null;
                try
                {
                    l_bankDepositNotifyRow =
                        BankDepositNotifyDao.findRowByPk(
                            l_bankDepositNotifyParams.getBankDepositNotifyId(),
                            l_bankDepositNotifyParams.getInstitutionCode(),
                            l_bankDepositNotifyParams.getDataLoadDiv());
                }
                catch (DataFindException l_ex)
                {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.debug("DBへのアクセスに失敗しました: ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.debug("DBへのアクセスに失敗しました: ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                } 
                l_bankDepositNotifyParams =  
                    new BankDepositNotifyParams(l_bankDepositNotifyRow);

                //1.2.3.2   insert入金通知処理エラー履歴(入金通知Params)
                this.insertDepositErrorHistory(l_bankDepositNotifyParams);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit注文)<BR>
     * 入金通知の注文登録する。<BR>
     * <BR>
     * シーケンス図「（入金連携通知一件サービスImpl）submit注文」 参照<BR>
     * <BR>
     * @@param l_bankDepositNotifyParams - 入金通知Params<BR>
     * @@param l_mainAccount  顧客 <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected void submitOrder(
        BankDepositNotifyParams l_bankDepositNotifyParams,
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitOrder(BankDepositNotifyParams, WEB3GentradeMainAccount) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 createNewOrderId( )
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        long l_lngNewOrderId = l_aioOrderManager.createNewOrderId();
        log.debug("l_AioOrderManager.createNewOrderId()====" + l_lngNewOrderId);
        
        //1.2 getInstitution( )
        Institution l_institution = l_mainAccount.getInstitution();
            
        //1.3 get商品ID(Institution)
        long l_lngProductId = l_aioOrderManager.getProductId(l_institution);
        
        //1.4 get発注日( )
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.5 toDate(japaneseEra : String, dateString : String)
        //入金通知起算日を和暦文字列から西暦日付に変換する。
        //引数：
        //年号 = 年号.getJapEraDiv(入金通知レコード.入金データ入金起算日)の戻り値
        //日付 = 入金通知レコード.入金データ入金起算日
        String l_strDepositDataBaseDate = l_bankDepositNotifyParams.getDepositDataBaseDate();
        Date l_datChangeDate = WEB3GentradeEra.toDate(
            WEB3GentradeEra.getJapEraDiv(l_strDepositDataBaseDate),
            l_strDepositDataBaseDate);
        
        //1.6 get受渡日(Date, Date)
        Date l_datDeliveryDate = 
            this.getDeliveryDate(l_datChangeDate, l_datBizDate);
        
        //1.7 入出金注文内容
        //扱者 = null
        //注文種別 = 入金
        //振替タイプ = 入金
        //商品ID = get商品ID()の戻り値
        //金額 = Double.parseDouble(入金通知レコード.入金金額)
        //記述 = null
        //振替予定日 = 勘定日（※1）　@※勘定日が非営業日の場合は、翌営業日
        //決済機@関ID = null
        //注文ID = get注文ID()の戻り値
        
        double l_dblDepositDataDepositAmount = 0.0D;
        if (l_bankDepositNotifyParams.getDepositDataDepositAmount() != null)
        {
            l_dblDepositDataDepositAmount = 
                Double.parseDouble(
                    l_bankDepositNotifyParams.getDepositDataDepositAmount());
        }
        
        //勘定日 = 入金通知Params.勘定日を西暦日付に変換した値
        //　@[年号.toDateの引数]
        //　@　@年号 = 年号.getJapEraDiv(入金通知Params.勘定日)の戻り値
        //　@　@和暦文字列 = 入金通知Params.勘定日
        String l_strDepositData =
            l_bankDepositNotifyParams.getDepositDataAccountDate();
        Date l_datDepositDataAccountDate = WEB3GentradeEra.toDate(
            WEB3GentradeEra.getJapEraDiv(l_strDepositData),
            l_strDepositData);
        String l_strDepositDataAccountDate = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_datDepositDataAccountDate.getTime()));
        
        //勘定日が非営業日の場合
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strDepositDataAccountDate))
        {
            //翌営業日
            l_datDepositDataAccountDate = new WEB3GentradeBizDate(new Timestamp(
                l_datDepositDataAccountDate.getTime())).roll(1);
        }
        
        WEB3AioNewOrderSpec l_aioNewOrderSpec = new WEB3AioNewOrderSpec(
            null,
            OrderTypeEnum.CASH_IN,
            AssetTransferTypeEnum.CASH_IN,
            l_lngProductId,
            l_dblDepositDataDepositAmount,
            null,
            l_datDepositDataAccountDate,
            null,
            new Long(l_lngNewOrderId));
        
        //1.8 get新規識別コード
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class); 
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        String l_strInstitutionCode = 
            l_mainAccount.getInstitution().getInstitutionCode();
        String l_strNewNumber = 
            l_hostReqOrderNumberManageService.getNewNumber(
                l_strInstitutionCode, 
                l_strBranchCode, 
                ProductTypeEnum.CASH);
        log.debug("新規の識別コード = " + l_strNewNumber);
        log.debug("InstitutionCode = " + l_strInstitutionCode);
        log.debug("BranchCode = " + l_strBranchCode);
        
        double l_dblSellExecRate = 0;
        //1.9 入金連携注文更新インタセプタ(入出金注文内容)
        WEB3AioCashinCooperationOrderUpdateInterceptor l_interceptor = 
            new WEB3AioCashinCooperationOrderUpdateInterceptor(l_aioNewOrderSpec);

        //1.10 入金通知Params.通貨コード != null の場合、以下の処理を実行
        if (l_bankDepositNotifyParams.getCurrencyCode() != null)
        {
            // 1.10.1（共通）通貨を生成する。 
            //[引数] 
            //証券会社コード：　@入金通知Params.証券会社コード 
            //通貨コード：　@入金通知Params.通貨コード
            WEB3GentradeCurrency l_genCurrency = WEB3GentradeCurrency.genCurrency(
                l_bankDepositNotifyParams.getInstitutionCode(), 
                l_bankDepositNotifyParams.getCurrencyCode());
            //1.10.2 get売付約定為替レート( )
            l_dblSellExecRate = l_genCurrency.getSellExecRate();
        }
        
        //1.11 (*)プロパティセット
        //以下のようにプロパティ値をセットする。
        //識別コード = get識別コード()の戻り値
        l_interceptor.setOrderRequestNumber(l_strNewNumber);
        //発注日 = get受渡日()
        l_interceptor.setOrderBizDate(l_datDeliveryDate);
        //受渡日 = get受渡日()
        l_interceptor.setDeliveryDate(l_datDeliveryDate);
        //注文経路 = 入金連携
        l_interceptor.setBizChannel(WEB3OrderRootDivDef.CASH_IN_COOPERATION);
        //振替記述 = 金融機@関コード
        String l_strBackSchemeCode = "";
        //(金融機@関コード = 入金通知レコード.銀行コード  + 
        l_strBackSchemeCode = 
            l_strBackSchemeCode + l_bankDepositNotifyParams.getBankCode();
        //入金通知レコード.銀行支店コード +
        l_strBackSchemeCode = 
            l_strBackSchemeCode + l_bankDepositNotifyParams.getBankBranchCode();  
        //入金通知レコード.入金データ口座種別 +
        l_strBackSchemeCode = 
            l_strBackSchemeCode + l_bankDepositNotifyParams.getDepositDataBankAccountType(); 
        //入金通知レコード.口座番号)
        l_strBackSchemeCode = 
            l_strBackSchemeCode + l_bankDepositNotifyParams.getBankAccountNo();
        l_interceptor.setDescription(l_strBackSchemeCode);
        
        //通貨コード = 入金通知Params.通貨コード
        l_interceptor.setCurrencyCode(l_bankDepositNotifyParams.getCurrencyCode());
        
        //入金通知Params.通貨コード がnullの場合、
        //入出金金額（円換算額） = null
        if (l_bankDepositNotifyParams.getCurrencyCode() == null)
        {
            l_interceptor.setConvertAmount(null);
        } 
        else
        {
            //入金通知Params.通貨コード  != nullの場合、
            //入出金金額（円換算額） = get売付約定為替レート()の戻り値 * 入金通知Params.取引金額の小数部分をカットした値
            BigDecimal l_bdlDepositDataAepositAmount = 
                new BigDecimal(l_bankDepositNotifyParams.getDepositDataDepositAmount());
            BigDecimal l_bddSellExeCRate = 
                new BigDecimal(String.valueOf(l_dblSellExecRate));
            l_bdlDepositDataAepositAmount = 
                l_bddSellExeCRate.multiply(l_bdlDepositDataAepositAmount);
            long l_lngDepositDataAepositAmount = l_bdlDepositDataAepositAmount.longValue();
            l_interceptor.setConvertAmount(
                new Double(l_lngDepositDataAepositAmount));
        }

        //1.12 setThreadLocalPersistenceEventInterceptor(arg0 : AioOrderManagerPersistenceEventInterceptor)
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //1.13 getSubAccount(arg0 : SubAccountTypeEnum)
        try
        {
            SubAccount l_subAccount = 
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            //1.14 decrypt(l_str : String)
            WEB3Crypt l_web3Crypt = new WEB3Crypt();
            String l_strDecrypt = l_web3Crypt.decrypt(l_mainAccount.getTradingPassword());

            //1.15 submitNewOrder
            //[引数]
            // 補助口座：　@補助口座オブジェクト
            // 商品タイプ：　@5（現金）
            // 入出金注文内容：　@入出金注文内容オブジェクト
            // 注文ＩＤ：　@createNewOrderId()の戻り値
            // パスワード：　@顧客.getTradingPassword()の戻り値をWEB3Cｒｙｐｔ.decrypt()で復号したもの
            // isSkip発注審査：　@true   
            OrderSubmissionResult l_submissionResult =
                l_aioOrderManager.submitNewOrder(
                    l_subAccount,
                    ProductTypeEnum.CASH,
                    l_aioNewOrderSpec,
                    l_lngNewOrderId,
                    l_strDecrypt,
                    true);
            if (l_submissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("注文登録処理失敗である");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_submissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // is信用口座開設(弁済区分 : String)
            boolean l_blnIsMarginAccountEstablished =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            if (l_blnIsMarginAccountEstablished)
            {
                // 顧客が信用口座を開設している（is信用口座開設()==TRUE）場合
                WEB3MarginTransferService l_service =
                    (WEB3MarginTransferService)Services.getService(
                    WEB3MarginTransferService.class);

                //submit保証金振替(顧客, Date, double, String,Trader)
                l_service.submitMarginTransfer(
                    l_mainAccount,
                    l_datDeliveryDate,
                    l_dblDepositDataDepositAmount,
                    l_strDecrypt,
                    null);
            }
            
            //1.16 余力再計算(補助口座 : 補助口座)
            WEB3TPTradingPowerService l_service =
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            WEB3GentradeSubAccount l_gentradeSubAccount =
                (WEB3GentradeSubAccount)l_subAccount;
            l_service.reCalcTradingPower(l_gentradeSubAccount);

            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);  
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
    }
    
    /**
     * (insert入金通知処理エラー履歴)<BR>
     * 入金通知処理エラー履歴テーブルに1件追加する。<BR>
     * <BR>
     * <DB更新仕様参照> <BR>
     * 銀行入金通知_入金通知処理エラー履歴テーブル.xls<BR>
     * <BR>
     * @@param l_bankDepositNotifyParams - 入金通知Params<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected void insertDepositErrorHistory(BankDepositNotifyParams l_bankDepositNotifyParams) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertDepositErrorHistory(BankDepositNotifyParams l_bankDepositNotifyParams) ";
        log.entering(STR_METHOD_NAME);

        try
        {
            //銀行入金通知処理エラー履歴Params
            BankDepositErrorHistoryParams l_bankDepositErrorHistoryParams = 
                new BankDepositErrorHistoryParams();
            
            //1)銀行入金通知処理エラー履歴テーブルID
            l_bankDepositErrorHistoryParams.setBankDepositErrorHistoryId(
                BankDepositErrorHistoryDao.newPkValue());
            
            //2)銀行入金通知テーブルID
            l_bankDepositErrorHistoryParams.setBankDepositNotifyId(
                l_bankDepositNotifyParams.getBankDepositNotifyId());
            
            //3)履歴番号
            l_bankDepositErrorHistoryParams.setSerialNo(
                l_bankDepositNotifyParams.getLastErrorHistorySerialNo());
            
            //4)入金エラーコメント
            l_bankDepositErrorHistoryParams.setDepositErrorComment(
                l_bankDepositNotifyParams.getDepositErrorComment());
            
            //5)備考
            l_bankDepositErrorHistoryParams.setRemark(
                l_bankDepositNotifyParams.getRemark());
            
            //6)更新担当者
            l_bankDepositErrorHistoryParams.setUpdatePerson(WEB3UpdatePersonDef.SYSTEM);
            
            //7)作成日付
            l_bankDepositErrorHistoryParams.setCreatedTimestamp(
                GtlUtils.getSystemTimestamp());
            
            //8)更新日付
            l_bankDepositErrorHistoryParams.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());
            
            //9)証券会社コード = 銀行入金通知テーブル.証券会社コード
            l_bankDepositErrorHistoryParams.setInstitutionCode(
                l_bankDepositNotifyParams.getInstitutionCode());

            //10)データ取込区分 = 銀行入金通知テーブル.データ取込区分
            l_bankDepositErrorHistoryParams.setDataLoadDiv(
                l_bankDepositNotifyParams.getDataLoadDiv());
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_bankDepositErrorHistoryParams);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update入金通知)<BR>
     * 当該入金通知レコードを更新する。<BR>
     * <BR>
     * <DB更新仕様参照> <BR>
     * 銀行入金通知_入金通知テーブル.xls<BR>
     * <BR>
     * @@param l_bankDepositNotifyParams - 入金通知Params<BR>
     * @@param l_mapSpac - Map 更新内容<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected void updateDepositNotify(
        BankDepositNotifyParams l_bankDepositNotifyParams, 
        Map l_mapSpac) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateDepositNotify(BankDepositNotifyParams, Map) ";
        log.entering(STR_METHOD_NAME);

        try
        {
            //更新担当者
            l_mapSpac.put("update_person", "system");
            //更新日付
            l_mapSpac.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(
                l_bankDepositNotifyParams.getPrimaryKey(),
                l_mapSpac);            
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
    }
    
    /**
     * (get受渡日)<BR>
     * 受渡日を取得する。<BR>
     * <BR>
     * １）引数.入金起算日 <= 引数.発注日 の場合 <BR>
     * 受渡日として、引数.発注日を返却する。 <BR>
     * ２）引数.入金起算日 > 引数.発注日 の場合 <BR>
     * 受渡日として、引数.入金起算日を返却する。<BR> 
     *  ※引数.入金起算日が非営業日の場合は、その翌営業日を返却する。<BR>
     * <BR>
     * @@param l_datDepositBaseDate - 入金起算日 <BR>
     * @@param l_datBizDate - 発注日  <BR>
     * @@return  Date 
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected Date getDeliveryDate(Date l_datDepositBaseDate, Date l_datBizDate) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDeliveryDate(Date l_datDepositBaseDate, Date l_datBizDate) ";
        log.entering(STR_METHOD_NAME);
        
        //１）引数.入金起算日 <= 引数.発注日 の場合 
        if(WEB3DateUtility.compareToDay(l_datDepositBaseDate, l_datBizDate) <= 0)
        {
            // 受渡日として、引数.発注日を返却する。 
            log.exiting(STR_METHOD_NAME);
            return l_datBizDate;
        }
        
        //２）引数.入金起算日 > 引数.発注日 の場合 
        else
        {
            String l_strDepositBaseDateType = 
                WEB3GentradeTradingTimeManagement.getBizDateType(
                    new Timestamp(l_datDepositBaseDate.getTime()));
            log.debug("入金起算日の営業日区分 = " + l_strDepositBaseDateType);
            
            //※引数.入金起算日が非営業日の場合は、その翌営業日を返却する。
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strDepositBaseDateType))
            {
                Date l_datRreturnDate = 
                    new WEB3GentradeBizDate(
                        new Timestamp(
                            l_datDepositBaseDate.getTime())).roll(1);
                log.exiting(STR_METHOD_NAME);
                return l_datRreturnDate;
            }
            else
            {
                //受渡日として、引数.入金起算日を返却する。 
                log.exiting(STR_METHOD_NAME);
                return l_datDepositBaseDate;
            }
        }
    }
    
    /**
     * (check顧客名)<BR>
     * 名寄せチェックを行う。<BR>
     * <BR>
     * 顧客マスタレコード.名前（苗字１） <BR>
     *  = 入金通知レコード.入金データ依頼人名 <BR>
     * <BR>
     * かどうかチェックする。<BR>
     * <BR>
     * １）不要文字を除外 <BR>
     * (*パターン) <BR>
     * 法@人設立形態区分 = {"（カ", "カ）", "（ユ", "ユ）", "（シ", "シ）"} <BR>
     * 全角スペース<BR>
     * 半角スペース <BR>
     * <BR>
     * ２）入金通知レコード.入金データ依頼人名が <BR>
     * 大文字小文字混在の場合は大文字変換する。<BR>
     * <BR>
     * <BR>
     * ３）上記処理後文字列が合致する場合true、合致しない場合falseを返す。<BR>
     * <BR>
     * @@param l_bankDepositNotifyParams - 入金通知Params <BR>
     * @@param l_mainAccount - 顧客<BR>
     * @@return  boolean  
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected boolean checkAccountName(
        BankDepositNotifyParams l_bankDepositNotifyParams, 
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "checkAccountName(BankDepositNotifyParams, WEB3GentradeMainAccount) ";
        log.entering(STR_METHOD_NAME);
        
        //１）不要文字を除外 
        //顧客マスタレコード.名前（苗字１）を取得する。  
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        String l_strFamilyNameAlt1 = 
            this.getCheckAccountName(l_mainAccountRow.getFamilyNameAlt1());
        
        //入金通知レコード.入金データ依頼人名を取得する。 
        if (l_bankDepositNotifyParams.getDepositDataTransPersonName() == null)
        {
            return false;
        }

        String l_strDepositDataTransPersonName = 
            this.getCheckAccountName(
                l_bankDepositNotifyParams.getDepositDataTransPersonName());
        
        //２）入金通知レコード.入金データ依頼人名が 
        //大文字小文字混在の場合は大文字変換する。
        l_strDepositDataTransPersonName =
            WEB3StringTypeUtility.toUpperWbyteKana(
                l_strDepositDataTransPersonName);

        //顧客マスタレコード.名前（苗字１） 
        //= 入金通知レコード.入金データ依頼人名 
        //３）上記処理後文字列が合致する場合true、合致しない場合falseを返す。
        if (l_strFamilyNameAlt1.compareTo(l_strDepositDataTransPersonName) == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
 
    /**
     * (is外貨コード)<BR>
     * 外貨コードの妥当性をチェックする。 <BR>
     * <BR>
     * １）　@（共通）通貨（引数:証券会社コード , 引数:外貨コード） で、レコードが <BR>
     *        検索できない場合、falseを返却。それ以外は、trueを返却 <BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード <BR>
     * @@param l_strForeignCurrencyCode - 外貨コード<BR>
     * @@return  boolean  
     * @@roseuid 40BEFA0600BA
     */
    protected boolean isForeignCurrencyCode(
        String l_strInstitutionCode, 
        String l_strForeignCurrencyCode)
    {
        final String STR_METHOD_NAME =
            "isForeignCurrencyCode(String, String) ";
        log.entering(STR_METHOD_NAME);
        
        //外貨コードの妥当性をチェックする。 
        //１）　@（共通）通貨（引数:証券会社コード , 引数:外貨コード） で、レコードが 
        //　@　@検索できない場合、falseを返却。それ以外は、trueを返却
        
        try
        {
            WEB3GentradeCurrency.genCurrency(
                l_strInstitutionCode, 
                l_strForeignCurrencyCode);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }    
    
    private String getCheckAccountName(String l_strCheckAccountName)
    {
        //全角スペース 
        //半角スペース 
        String l_strReturnValue = l_strCheckAccountName.trim();
        l_strReturnValue = l_strReturnValue.replaceAll(" ", "");
        l_strReturnValue = l_strReturnValue.replaceAll("　@", "");

        //(*パターン) 
        //法@人設立形態区分 = {"（カ", "カ）", "（ユ", "ユ）", "（シ", "シ）"} 
        l_strReturnValue =
            l_strReturnValue.replaceAll(
                WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_LIST[0], "");
        l_strReturnValue =
            l_strReturnValue.replaceAll(
                WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_LIST[1], "");
        l_strReturnValue =
            l_strReturnValue.replaceAll(
                WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_LIST[2], "");
        l_strReturnValue =
            l_strReturnValue.replaceAll(
                WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_LIST[3], "");
        l_strReturnValue =
            l_strReturnValue.replaceAll(
                WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_LIST[4], "");
        l_strReturnValue =
            l_strReturnValue.replaceAll(
                WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_LIST[5], "");
        l_strReturnValue = l_strReturnValue.trim();
        return l_strReturnValue;
    }
    
    /**
     * (is正常桁数)<BR>
     * 計算した入出金金額（円換算額）の桁数が13桁以上<BR>
     * の場合は、false、それ以外は、trueを返却する。<BR>
     * <BR>
     * １）　@引数:取引金額 * 引数:為替レートの計算処理<BR>
     * <BR>
     * ２）　@１）の計算結果の整数部分が１３桁以上の場合、false、それ以外true を返却<BR>
     * <BR>
     * @@return  boolean<BR>
     * @@param - (取引金額)<BR>
     * 取引金額
     * @@param - (為替レート)<BR>
     * 為替レート
     */
    public boolean isNormalLength(String l_strDepositDataDepositAmount, double l_dbSellExecRate)
    {
        final String STR_METHOD_NAME =
            "isNormalLength(String l_strDepositDataDepositAmount, double l_dbSellExecRate)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数:取引金額 * 引数:為替レートの計算処理
        BigDecimal l_bdDepositDataDepositAmount = new BigDecimal(l_strDepositDataDepositAmount);
        String l_strSellExecRate = WEB3StringTypeUtility.formatNumber(l_dbSellExecRate);
        BigDecimal l_bdSellExecRate = new BigDecimal(l_strSellExecRate);
        BigDecimal l_bdResult = l_bdDepositDataDepositAmount.multiply(l_bdSellExecRate);

        long l_lngResult = l_bdResult.longValue();
        String l_strIntValue = l_lngResult + "";
        int l_intLength = WEB3StringTypeUtility.getByteLength(l_strIntValue);
        // ２）　@１）の計算結果の整数部分が１３桁以上の場合、false、それ以外true を返却
        if (l_intLength >= 13)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }
}
@
