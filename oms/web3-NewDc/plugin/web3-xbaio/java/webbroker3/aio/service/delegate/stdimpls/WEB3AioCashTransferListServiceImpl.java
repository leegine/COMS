head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashTransferListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金一覧サービス実装クラス(WEB3AioCashTransferListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 周勇 (中訊) 新規作成
Revesion History : 2004/10/25 屈陽 (中訊) レビュー
Revesion History : 2005/11/17 李俊 (中訊) フィデリティ対応                        
Revesion History : 2006/06/05 鈴木（SCS） 仕様変更 No.589・DB更新仕様 091
Revesion History : 2006/07/12 丁昭奎 (中訊) 仕様変更 No.595,No.598
Revesion History : 2006/07/18 韋念瓊 (中訊) 仕様変更 No.605
Revesion History : 2006/08/01 鈴木(SCS)　@モデルNo.610対応
Revesion History : 2006/11/07 何文敏 (中訊) 仕様変更 No.679
Revesion History : 2007/05/09 何文敏 (中訊) 仕様変更 No.723 No.725
Revesion History : 2007/09/21 柴双紅 (中訊) 仕様変更 No.765
Revesion History : 2008/05/19 柴双紅 (中訊) 仕様変更 No.848、No.869、No.878、No.884
Revesion History : 2008/09/22 王志葵 (中訊) 仕様変更 No.1000,1045,1075
Revesion History : 2009/03/11 王志葵 (中訊) 仕様変更 No.1117,1146
Revesion History : 2009/04/16 柴双紅 (中訊) 仕様変更 No.1158
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioCashOutStatusUtility;
import webbroker3.aio.WEB3AioFEqConTransStatusUtility;
import webbroker3.aio.WEB3AioFXTransStatusUtility;
import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.BankCashTransferStatusParams;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.data.DepositInformRow;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.aio.data.HostPaymentAcceptRow;
import webbroker3.aio.data.UwgTransferStatusRow;
import webbroker3.aio.data.HostTransferAcceptDao;
import webbroker3.aio.data.HostTransferAcceptRow;
import webbroker3.aio.define.WEB3AioDescriptionDef;
import webbroker3.aio.define.WEB3AioTransUnitDef;
import webbroker3.aio.message.WEB3AioAcceptDateComparator;
import webbroker3.aio.message.WEB3AioCashTransUnit;
import webbroker3.aio.message.WEB3AioCashTransferListResponse;
import webbroker3.aio.message.WEB3AioDealComparator;
import webbroker3.aio.service.delegate.WEB3AioCashTransferListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3FxTransferMasterRemarkCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3RemarkCodeDef;
import webbroker3.common.define.WEB3ResultStatusFlagDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3TransactionStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (入出金一覧サービスImpl)<BR>
 * 入出金一覧サービス実装クラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashTransferListServiceImpl extends WEB3ClientRequestService
    implements WEB3AioCashTransferListService 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransferListServiceImpl.class);
    
    /**
     * 入出金一覧サービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金一覧）一覧画面表示データ取得」 参照<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4100F30A0261
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "execute(WEB3GenRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1)注文受付可能かどうかのチェックを行う。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // 1.2)補助口座を取得する。 
        //[引数] 
        //補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        // 1.3)注文単位テーブルから入出金明細を取得する。
        //[引数] 
        //補助口座： 補助口座オブジェクト
        List l_listCashTransferFromOrderUnit = this.getCashTransferFromOrderUnit(l_subAccount);
        
        // 1.4)入金連絡テーブルから入出金明細を取得する。
        //[引数] 
        //補助口座： 補助口座オブジェクト
        List l_LisCashTransferFromCashinNotice = 
            this.getCashTransferFromCashinNotice(l_subAccount);
        
        // 1.5)金融機@関連携入出金状況テーブルから入出金明細を取得する。 
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        List l_listCashTransferFromCashTransferStatus = 
            this.getCashTransferFromCashTransferStatus(l_subAccount);
        
        // 1.6) GFT振替状況テーブルから入出金明細を取得する。 
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        List l_listCashTransferFromGftTransferStatus = 
            this.getCashTransferFromGftTransferStatus(l_subAccount);
        
        // 1.7)get入出金明細From注文単位()で取得したArrayListの末尾に、リストを追加する。
        //[引数] 
        //arg0： get入出金明細From入金連絡()の戻り値
        l_listCashTransferFromOrderUnit.addAll(l_LisCashTransferFromCashinNotice);
        
        // 1.8)get入出金明細From注文単位()で取得したArrayListの末尾に、リストを追加する。
        //[引数] 
        //arg0： get入出金明細From入出金状況()の戻り値
        l_listCashTransferFromOrderUnit.addAll(l_listCashTransferFromCashTransferStatus);
        
        // 1.9) get入出金明細From注文単位()で取得したArrayListの末尾に、リストを追加する。 
        //[引数] 
        //arg0： get入出金明細FromGFT振替状況()の戻り値 
        l_listCashTransferFromOrderUnit.addAll(l_listCashTransferFromGftTransferStatus);
        
        // 1.10)入出金明細の配列を取得する
        WEB3AioCashTransUnit [] l_aioCashTransUnit = 
            new WEB3AioCashTransUnit[l_listCashTransferFromOrderUnit.size()];
        l_listCashTransferFromOrderUnit.toArray(l_aioCashTransUnit);
        
        // 1.11)ArrayList()
        List l_lisDate = new Vector();
        
        // 1.12)受付日時Comparatorインスタンスを生成する。 
        //[引数] 
        //orderBy： "D"（降順）
        WEB3AioAcceptDateComparator l_aioAcceptDateComparator =
            new WEB3AioAcceptDateComparator(WEB3AscDescDef.DESC);
        
        // 1.13)受付日時ComparatorオブジェクトをArrayListに追加する。
        //[引数] 
        //arb0： 受付日時Comparatorオブジェクト
        
        l_lisDate.add(l_aioAcceptDateComparator);
        
        // 1.14)取引Comparatorインスタンスを生成する。
        //[引数] 
        //orderBy： "A"（昇順） 
        WEB3AioDealComparator l_aioDealComparator =
            new WEB3AioDealComparator(WEB3AscDescDef.ASC);
        
        // 1.15)取引ComparatorオブジェクトをArrayListに追加する。
        //[引数] 
         //arb0： 取引Comparatorオブジェクト 
        l_lisDate.add(l_aioDealComparator);
        
        // 1.16)Comparatorの配列を取得する。
        Comparator[] l_comparators = new Comparator[l_lisDate.size()];
        l_lisDate.toArray(l_comparators);
        
        // 1.17)入出金明細［］をComparatorに従って、ソートする。
        //［引数］ 
        //obj： 入出金明細オブジェクトの配列 
        //com： Comparatorオブジェクトの配列 
        WEB3ArraysUtility.sort(l_aioCashTransUnit, l_comparators);
                
        // 1.18)レスポンスデータを生成する。 
        WEB3AioCashTransferListResponse l_aioCashTransferListResponse = 
            (WEB3AioCashTransferListResponse)l_request.createResponse();
        
        // 1.19)（*） プロパティセット
        //(*) 以下のように、プロパティをセットする。
        //レスポンス.入出金一覧 = 入出金明細［］
        l_aioCashTransferListResponse.cashTransUnits = l_aioCashTransUnit; 
                
        log.exiting(STR_METHOD_NAME);
        return l_aioCashTransferListResponse;
        
    }
    
    /**
     * (get注文状況)<BR>
     * 注文単位の注文状態、注文取消区分、注文単位に関連する<BR>
     * 受付キューテーブルの処理区分<BR>
     * から注文状況を判定し、返却する。<BR>
     * <BR>
     * １）受付キューテーブルを取得する。<BR>
     * <BR>
     * １－１）注文単位Params.注文種別 = 1001（出金注文） and<BR>
     *         注文単位Params.注文経路区分 != 9（HOST） の場合<BR>
     * <BR>
     *    [対象テーブル]<BR>
     *    出金請求受付キューテーブル <BR>
     * <BR>
     *    [検索条件]<BR>
     *    データコード = "GI80A"<BR>
     *    証券会社コード = <BR>
     * 引数.補助口座.getInstitution().getInstitutionCode()の戻り値<BR>
     *    部店コード = 引数.補助口座.get取引店().getBranchCode()の戻り値<BR>
     *    顧客コード = 引数.補助口座.getMainAccouｎt().getAccountCode()<BR>
     * の戻り値<BR>
     *    識別コード = 注文単位Params.識別コード<BR>
     * <BR>
     * １－２）（注文単位Params.注文カテゴリ = 13（振替） or <BR>
     * 注文単位Params.注文カテゴリ = 15（為替保証金振替）） and <BR>
     * 注文単位Params.注文経路区分 != 9（HOST） の場合 <BR>
     * <BR>
     * [対象テーブル] <BR>
     * 振替請求受付キューテーブル <BR>
     * <BR>
     * [検索条件] <BR>
     * データコード = "GI80F" <BR>
     * 証券会社コード = 引数.補助口座.getInstitution().getInstitutionCode()の戻り値 <BR>
     * 部店コード = 引数.補助口座.get取引店().getBranchCode()の戻り値 <BR>
     * 顧客コード = 引数.補助口座.getMainAccouｎt().getAccountCode()の戻り値 <BR>
     * 識別コード = 注文単位Params.識別コード <BR>
     * <BR>
     * ２）該当注文が為替保証金振替もしくは外国株式振替の場合、振替状況テーブルを取得する。<BR> 
     * <BR>
     * ２－１）注文単位Params.注文カテゴリ = 15（為替保証金振替） の場合 <BR>
     * <BR>
     * [対象テーブル] <BR>
     * GFT振替状況テーブル <BR>
     * <BR>
     * [検索条件] <BR>
     * 証券会社コード = 引数.補助口座.getInstitution().getInstitutionCode()の戻り値<BR> 
     * 部店コード = 引数.補助口座.get取引店().getBranchCode()の戻り値 <BR>
     * 顧客コード = 引数.補助口座.getMainAccouｎt().getAccountCode()の戻り値 <BR>
     * 識別コード = 注文単位Params.識別コード <BR>
     * <BR>
     * ２－２）注文単位Params.注文カテゴリ = 16（外国株式振替） の場合 <BR>
     * <BR>
     * [対象テーブル] <BR>
     * UWG振替状況テーブル <BR>
     * <BR>
     * [検索条件] <BR>
     * 証券会社コード = 引数.補助口座.getInstitution().getInstitutionCode()の戻り値<BR> 
     * 部店コード = 引数.補助口座.get取引店().getBranchCode()の戻り値 <BR>
     * 顧客コード = 引数.補助口座.getMainAccouｎt().getAccountCode()の戻り値 <BR>
     * 識別コード = 注文単位Params.識別コード <BR>
     * <BR>
     * ３）以下の項目から注文状況を判定し、返却する。 <BR>
     * <BR>
     * 注文単位Params.注文状態 <BR>
     * 注文単位Params.注文取消区分 <BR>
     * 受付キューテーブル.処理区分 （⇒ １）の処理で取得したもの） <BR>
     * <BR>
     * ※為替保証金振替or外国株式振替の場合は、以下の項目も考慮する。 <BR>
     * 振替状況テーブル.振替状況区分 <BR>
     * 振替状況テーブル.送受信区分 <BR>
     * 振替状況テーブル.受付結果コード <BR>
     * <BR>
     * 詳細は、「入出金ステータス構成表.xls」の各シート参照 <BR>
     * <BR>
     * ※2005/02/14現在、外国株式振替についての上記EXCELファ@イルの内容が未確定の <BR>
     * 箇所あり。その箇所については、内容が確定後に対応。 <BR>
     * <BR>
     * @@param l_subAcccount - (補助口座オブジェクト)
     * @@param l_orderUnitRow - (注文単位Row)
     * @@return String
     * @@throws WEB3SystemLayerException<BR>
     * @@roseuid 41060B51008C
     */
    protected String getOrderStatus(SubAccount l_subAccount, AioOrderUnitRow l_orderUnitRow) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "getOrderStatus(SubAccount l_subAcccount, AioOrderUnitParams l_orderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_orderUnitRow == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）受付キューテーブルを取得する。 
        //１－１）注文単位Params.注文種別 = 1001（出金注文） and  
        //注文単位Params.注文経路区分 != 9（HOST） の場合
        //[対象テーブル] 
        //出金請求受付キューテーブル
        //[検索条件] 
        //データコード = "GI80A" 
        //証券会社コード = 引数.補助口座.getInstitution().getInstitutionCode()の戻り値 
        //部店コード = 引数.補助口座.get取引店().getBranchCode()の戻り値 
        //顧客コード = 引数.補助口座.getMainAccouｎt().getAccountCode()の戻り値 
        //識別コード = 注文単位Params.識別コード
        List l_lisHostCashTransOrderAcceptRows = null;
        HostPaymentAcceptRow l_hostPaymentAcceptRow = null;
        
        if(!WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv())
            && OrderTypeEnum.CASH_OUT.equals(l_orderUnitRow.getOrderType()))
        {
            String l_strWhere = "request_code = ? and institution_code = ? " +
               " and branch_code = ? and account_code = ? and order_request_number = ?";
            
            Object[] l_objValues = {WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ACCEPT,
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_orderUnitRow.getOrderRequestNumber()};
                        
            QueryProcessor l_queryProcessor = null;
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
        
                l_lisHostCashTransOrderAcceptRows =
                    l_queryProcessor.doFindAllQuery(
                            HostPaymentAcceptRow.TYPE, 
                            l_strWhere, 
                            l_objValues);
                
                int l_intHostCashTransOrderAcceptRowsLength = 
                    l_lisHostCashTransOrderAcceptRows.size();
                
                if(l_intHostCashTransOrderAcceptRowsLength > 1)
                {
                    log.debug("l_lisHostCashTransOrderAcceptRows.size() > 1");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if(l_intHostCashTransOrderAcceptRowsLength == 1)
                {
                    l_hostPaymentAcceptRow = 
                        (HostPaymentAcceptRow)l_lisHostCashTransOrderAcceptRows.get(0);
                }
            }
            catch (DataException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
        }
        
        //１－２）（注文単位Params.注文カテゴリ = 13（振替） or 
        //注文単位Params.注文カテゴリ = 15（為替保証金振替）） and 
        //注文単位Params.注文経路区分 != 9（HOST） の場合 
        //[対象テーブル] 
        //振替請求受付キューテーブル 
        //[検索条件] 
        //データコード = "GI80F" 
        //証券会社コード = 引数.補助口座.getInstitution().getInstitutionCode()の戻り値 
        //部店コード = 引数.補助口座.get取引店().getBranchCode()の戻り値 
        //顧客コード = 引数.補助口座.getMainAccouｎt().getAccountCode()の戻り値 
        //識別コード = 注文単位Params.識別コード 
        List l_lisHostTransferAcceptRows = null;
        HostTransferAcceptRow l_hostTransferAcceptRow = null;
        
        if ((OrderCategEnum.CASH_TRANSFER.equals(l_orderUnitRow.getOrderCateg())
            || OrderCategEnum.FX.equals(l_orderUnitRow.getOrderCateg()))
            && !WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
        {
            String l_strWhere = "request_code = ? and institution_code = ? " +
               "and branch_code = ? and account_code = ? and order_request_number = ?";
            
            Object[] l_objValues = {
                WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ACCEPT,
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_orderUnitRow.getOrderRequestNumber()};
                        
            QueryProcessor l_queryProcessor = null;
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
        
                l_lisHostTransferAcceptRows =
                    l_queryProcessor.doFindAllQuery(
                            HostTransferAcceptRow.TYPE, 
                            l_strWhere, 
                            l_objValues);
                
                int l_intHostTransferAcceptRowsLength = 
                    l_lisHostTransferAcceptRows.size();
                
                if(l_intHostTransferAcceptRowsLength > 1)
                {
                    log.debug("l_lisHostTransferOrderRows.size() > 1");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if(l_intHostTransferAcceptRowsLength == 1)
                {
                    l_hostTransferAcceptRow = 
                        (HostTransferAcceptRow)l_lisHostTransferAcceptRows.get(0);
                }
            }
            catch (DataException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
        }
        //詳細は、「入出金ステータス構成表.xls」の各シート参照 
        
        //注文単位テーブル.注文状態
        OrderStatusEnum l_orderStatusEnum = l_orderUnitRow.getOrderStatus();  
        String l_strOrderStatus = l_orderStatusEnum.intValue() + "";  
        
        //注文単位テーブル.注文取消区分
        String l_strCancelType= l_orderUnitRow.getCancelType();
        
        //受付キューテーブル.処理区分 （⇒ １）の処理で取得したもの） 
        String l_strStatus = null;
		if(l_hostPaymentAcceptRow != null)
        {
            l_strStatus = l_hostPaymentAcceptRow.getStatus();
        }
        else if(l_hostTransferAcceptRow != null)
        {
            l_strStatus = l_hostTransferAcceptRow.getStatus();
        }
        log.debug("l_strStatus = " + l_strStatus);

        //２）該当注文が為替保証金振替もしくは外国株式振替の場合、振替状況テーブルを取得する。 
        //２－１）注文単位Params.注文カテゴリ = 15（為替保証金振替） の場合 
        //[対象テーブル] 
        //GFT振替状況テーブル 
        //[検索条件] 
        //証券会社コード = 引数.補助口座.getInstitution().getInstitutionCode()の戻り値 
        //部店コード = 引数.補助口座.get取引店().getBranchCode()の戻り値 
        //顧客コード = 引数.補助口座.getMainAccouｎt().getAccountCode()の戻り値 
        //識別コード = 注文単位Params.識別コード 
        
        if (OrderCategEnum.FX.equals(l_orderUnitRow.getOrderCateg()))            
        {
            List l_lisGftTransferStatusRows = null;
            GftTransferStatusRow l_gftTransferStatusRow = null;

            String l_strWhere = "institution_code = ? and branch_code = ? " +
               "and account_code = ? and order_request_number = ?";
            
            Object[] l_objValues = {
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_orderUnitRow.getOrderRequestNumber()};
                        
            QueryProcessor l_queryProcessor = null;
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
        
                l_lisGftTransferStatusRows =
                    l_queryProcessor.doFindAllQuery(
                            GftTransferStatusRow.TYPE, 
                            l_strWhere, 
                            l_objValues);
                if (l_lisGftTransferStatusRows == null || l_lisGftTransferStatusRows.size() == 0)
                {
                    log.debug("GFT振替状況テーブルから、レコードを取得しない ! order_request_number =  "
                            +  l_orderUnitRow.getOrderRequestNumber());
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                
                int l_intGftTransferStatusRowsLength = 
                    l_lisGftTransferStatusRows.size();
                
                if(l_intGftTransferStatusRowsLength > 1)
                {
                    log.debug("l_lisHostTransferOrderRows.size() > 1");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if(l_intGftTransferStatusRowsLength == 1)
                {
                    l_gftTransferStatusRow = 
                        (GftTransferStatusRow)l_lisGftTransferStatusRows.get(0);
                }
            }
            catch (DataException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
            //※為替保証金振替or外国株式振替の場合は、以下の項目も考慮する。 
            //振替状況テーブル.振替状況区分 
            //振替状況テーブル.送受信区分 
            //振替状況テーブル.受付結果コード 
            //詳細は、「入出金ステータス構成表.xls」の各シート参照
            String l_strTransferStatusDiv = l_gftTransferStatusRow.getTransferStatusDiv();
            String l_strSendRcvDiv = l_gftTransferStatusRow.getSendRcvDiv();
            String l_strResultCode = l_gftTransferStatusRow.getResultCode();
            
            WEB3AioFXTransStatusUtility l_aioFxStatusUtils = 
                    new WEB3AioFXTransStatusUtility();
            String l_result = l_aioFxStatusUtils.getResult(
                    l_strTransferStatusDiv, 
                    l_strSendRcvDiv, 
                    l_strResultCode, 
                    l_strOrderStatus, 
                    l_strCancelType, 
                    l_strStatus);

            return l_result;
        }
        //２－２）注文単位Params.注文カテゴリ = 16（外国株式振替） の場合 
        if (OrderCategEnum.FEQ_TRANSFER.equals(l_orderUnitRow.getOrderCateg()))            
        {
			List l_lisUwgTransferStatusRows = null;
			UwgTransferStatusRow l_uwgTransferStatusRow = null;

			String l_strWhere = "institution_code = ? and branch_code = ? " +
			   "and account_code = ? and order_request_number = ?";
            
			Object[] l_objValues = {
				l_subAccount.getInstitution().getInstitutionCode(),
				l_subAccount.getMainAccount().getBranch().getBranchCode(),
				l_subAccount.getMainAccount().getAccountCode(),
				l_orderUnitRow.getOrderRequestNumber()};
                        
			QueryProcessor l_queryProcessor = null;
			try
			{
				l_queryProcessor = Processors.getDefaultProcessor();
        
				l_lisUwgTransferStatusRows =
					l_queryProcessor.doFindAllQuery(
							UwgTransferStatusRow.TYPE, 
							l_strWhere, 
							l_objValues);
                
                if (l_lisUwgTransferStatusRows == null || l_lisUwgTransferStatusRows.size() == 0)
                {
                    log.debug("UWG振替状況テーブルから、レコードを取得しない !");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                
				int l_intUwgTransferStatusRowsLength = 
					l_lisUwgTransferStatusRows.size();
                
				if(l_intUwgTransferStatusRowsLength > 1)
				{
					log.debug("l_lisUwgTransferStatusRows.size() > 1");
					throw new WEB3BaseRuntimeException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80004,
						this.getClass().getName() + "." + STR_METHOD_NAME);
				}
				else if(l_intUwgTransferStatusRowsLength == 1)
				{
					l_uwgTransferStatusRow = 
						(UwgTransferStatusRow)l_lisUwgTransferStatusRows.get(0);
				}
			}
			catch (DataException l_ex)
			{
				log.error("DBへのアクセスに失敗しました: ", l_ex);
				log.exiting(STR_METHOD_NAME);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}            
			//※為替保証金振替or外国株式振替の場合は、以下の項目も考慮する。 
			//振替状況テーブル.振替状況区分 
			//振替状況テーブル.送受信区分 
			//振替状況テーブル.受付結果コード 
			//詳細は、「入出金ステータス構成表.xls」の各シート参照
			String l_strTransferStatusDiv = l_uwgTransferStatusRow.getTransferStatusDiv();
			String l_strSendRcvDiv = l_uwgTransferStatusRow.getSendRcvDiv();
			String l_strResultCode = l_uwgTransferStatusRow.getResultCode();
            
			WEB3AioFEqConTransStatusUtility l_aioFeqConStatusUtils = 
					new WEB3AioFEqConTransStatusUtility();
			String l_result = l_aioFeqConStatusUtils.getResult(
					l_strTransferStatusDiv, 
					l_strSendRcvDiv, 
					l_strResultCode, 
					l_strOrderStatus, 
					l_strCancelType);

			return l_result;
        }
        else
        {
            WEB3AioCashOutStatusUtility l_aioStatusUtils = 
                new WEB3AioCashOutStatusUtility();
            String l_result = l_aioStatusUtils.getStatus(
                    l_strOrderStatus, 
                    l_strCancelType, 
                    l_strStatus);
            return l_result;
        }
    }
    
    /**
     * (get入出金明細From注文単位)<BR>
     * 注文単位テーブルから、入出金明細のリストを取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金一覧）get入出金明細From注文単位」 参照<BR>
     * @@param l_subAccount - (補助口座オブジェクト)
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 410625CA03D2
     */
    protected List getCashTransferFromOrderUnit(SubAccount l_subAccount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "getCashTransferFromOrderUnit(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.3)ArrayListのインスタンスを生成する。
        List l_lisaioCashTransUnits = new Vector();
        
        try
        {
            // 1.1)クエリプロセッサを取得する
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            // 1.2)doFindAllQuery(Rowタイプ : RowType, Where : String, リスト : Object[])
            //注文単位テーブルから、以下の条件のレコードを取得する。 
            //[条件] 
            //口座ID = 補助口座.getAccountId()の戻り値 
            //補助口座ID = 補助口座.getSubAccountId()の戻り値 
            //注文カテゴリ != 14（証券振替）
            //注文タイプ != 1005（振替注文（預り金から信用保証金））
            //注文タイプ != 1006（振替注文（信用保証金から預り金））
			//注文経路区分 != 'B'(保証金自動振替バッチ)
			//出金申込区分 != 'A0'(バイキング)
            //振替記述 != 'cashout_gross'（出金連携Gross） and
            //摘要コード != '71'（株先証拠金：東証） and
            //摘要コード != '72'（株先証拠金：大証）
            //（発注日 >= システムタイムスタンプの日付部分 
            //or 
            //受付日時の日付部分 = システムタイムスタンプの日付部分
            //or
            //受渡日 >= システムプリファ@レンス.業務日付） 
            //[引数] 
            //Rowタイプ： AioOrderUnitRow.TYPE 
            //Where： "account_id = ? and sub_account_id = ? and order_categ <> ? and order_type<> ? and order_type <> ? "+
            //"and order_root_div <> ? and (payment_application_div is null or payment_application_div <> ?)  and "+ 
            //"( description is null or description <> ? ) and ( remark_code is null or ( remark_code <> ? and remark_code <> ? ) ) and "+
            //"(biz_date >= ? or to_char(received_date_time,'YYYYMMDD') = ? or to_char(delivery_date,'YYYYMMDD') >= ?)"; 
            //リスト： 以下の項目のリスト 
            //補助口座.getAccountId()の戻り値 
            //補助口座.getSubAccountId()の戻り値 
            //注文カテゴリ.証券振替
            //注文タイプ.振替注文(預り金から信用保証金)
            //注文タイプ.振替注文(信用保証金から預り金)
			//注文経路区分.保証金自動振替バッチ 
			//出金申込区分.バイキング 
            //振替記述.出金連携Gross
            //摘要コード.'71'（株先証拠金：東証)
            //摘要コード.'72'（株先証拠金：大証) 
            //システムタイムスタンプの日付部分 
            //システムタイムスタンプの日付部分
            //システムプリファ@レンス.業務日付 

			String l_strWhere = "account_id = ? and sub_account_id = ? and order_categ <> ? and order_type<>? and order_type<>? " +
				"and order_root_div<>? and (payment_application_div is null or payment_application_div<>?)  and "+
                "(description is null or description <> ?) and (remark_code is null or (remark_code <> ? and remark_code <> ?)) and " +
                "(biz_date >= ? or to_char(received_date_time,'YYYYMMDD') = ? or to_char(delivery_date,'YYYYMMDD') >= ?)";
            
            Object[] l_objValues = new Object[]{
                Long.toString(l_subAccount.getAccountId()),
                String.valueOf(l_subAccount.getSubAccountId()),
                OrderCategEnum.ASSET_TRANSFER, 
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
				WEB3OrderRootDivDef.DEPOSIT_AUTO_TRANSFER_BATCH,
				WEB3AioPaymentApplicationDivDef.BUY_KING,
                WEB3AioDescriptionDef.CASHOUT_GROSS,
                WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_TOKYO,
                WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_OSAKA,
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
				WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
				WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd")
                };            

            List l_lisRows =
                l_queryProcessor.doFindAllQuery(AioOrderUnitRow.TYPE, l_strWhere, l_objValues);
            
            int l_intOrderUnitLength = l_lisRows.size();
            
            // 1.4)取得した注文単位Params毎のLoop処理
            for(int i = 0; i < l_intOrderUnitLength; i++)
            {                
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow) l_lisRows.get(i); 
                AioOrderUnitParams l_aioOrderUnitParams = 
                    new AioOrderUnitParams(l_aioOrderUnitRow);

                // 1.4.1)入出金明細のインスタンスを生成する
                WEB3AioCashTransUnit l_aioCashTransUnit = new WEB3AioCashTransUnit();
                
                //1.4.2 取引区分を取得する。 
                //[引数] 
                //注文単位Params： 注文単位Paramsオブジェクト 
                String l_strTradingType = this.getTradingType(l_aioOrderUnitParams);
                
                //1.4.3 備考に設定する項目を取得する。 
                //[引数] 
                //注文単位Params： 注文単位Paramsオブジェクト
                String l_strRemark = this.getRemark(l_aioOrderUnitParams);

                // 1.4.4)プロパティセット
                //(*2) 以下のようにプロパティをセットする。
                //入出金明細.受付日付 = 注文単位Params.受付日時
                l_aioCashTransUnit.receptionDate = l_aioOrderUnitRow.getReceivedDateTime();
                
                //入出金明細.取引 = get取引区分()の戻り値
                l_aioCashTransUnit.tradingType = l_strTradingType;
                
                //入出金明細.金融機@関名 = null
                l_aioCashTransUnit.paySchemeName = null;
                
                //入出金明細.入出金金額 = 注文単位Params.注文数量
                l_aioCashTransUnit.cashTransAmt = 
                    WEB3StringTypeUtility.formatNumber(l_aioOrderUnitRow.getQuantity());
                
                //入出金明細.受渡予定日 = 注文単位Params.受渡日
                l_aioCashTransUnit.deliveryScheduledDate = l_aioOrderUnitRow.getDeliveryDate();
                
                //入出金明細..comデビット決済取引番号 = 注文単位Params..comデビット決済取引番号
                String l_strComDebitNumber = l_aioOrderUnitRow.getComDebitNumber();
                l_aioCashTransUnit.comDebitNumber = l_strComDebitNumber;
                
                //入出金明細.備考 = get備考()の戻り値
                l_aioCashTransUnit.ioRemark = l_strRemark;

                //入出金明細.通貨コード = 注文単位Params.通貨コード
                l_aioCashTransUnit.currencyCode = l_aioOrderUnitRow.getCurrencyCode();

                // 1.4.3)入出金明細..comデビット決済取引番号 != null （オンライン入金）の場合
                WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService =
                    (WEB3AioMultiBankSettleControlService) Services.getService(
                        WEB3AioMultiBankSettleControlService.class);
                
                if(l_strComDebitNumber != null)
                {
                    // 1.4.3.1)オンライン入金で使用した決済機@関の名称を取得する。 
                    //[引数] 
                    //補助口座： 補助口座オブジェクト 
                    //識別コード： 注文単位Params.識別コード   
                    String l_strOrderRequestNumber = l_aioOrderUnitRow.getOrderRequestNumber();
                    String l_strFinInstitutionName = 
                        l_aioMultiBankSettleControlService.getFinInstitutionName(
                            l_subAccount,
                            l_strOrderRequestNumber);

                    // 1.4.3.2)オンライン入出金の状況を取得する。
                    //[引数] 
                    //補助口座： 補助口座オブジェクト 
                    //識別コード： 注文単位Params.識別コード 
                    //注文状態： 注文単位Params.注文状態 
                    //注文取消区分： 注文単位Params.注文取消区分
                    String l_strCashTransSituation = 
                        l_aioMultiBankSettleControlService.getCashTransSituation(
                            l_subAccount,
                            l_strOrderRequestNumber,
                            l_aioOrderUnitRow.getOrderStatus(), 
                            l_aioOrderUnitRow.getCancelType());
                    
                    // 1.4.3.3)プロパティセット
                    //以下のようにプロパティをセットする。
                    //入出金明細.金融機@関名 = get金融機@関名()の戻り値
                    l_aioCashTransUnit.paySchemeName = l_strFinInstitutionName;
                    
                    //入出金明細.処理状況 = get入出金状況()の戻り値
                    l_aioCashTransUnit.payStatus = l_strCashTransSituation;
                    
                }
                
                // 1.4.4)入出金明細..comデビット決済取引番号 = null （オンライン入金以外）の場合
                if(l_strComDebitNumber == null)
                {
                    // 1.4.4.1)処理状況を判定する。
                    //[引数] 
                    //補助口座： 補助口座オブジェクト 
                    //注文単位Params： 注文単位Paramsオブジェクト
                    String l_strOrderStatus = this.getOrderStatus(l_subAccount, l_aioOrderUnitRow);
                    log.debug("l_strOrderStatus = " + l_strOrderStatus);
                    
                    // 1.4.4.1)プロパティセット
                    //以下のようにプロパティをセットする。
                    //入出金明細.処理状況 = get注文状況()の戻り値
                    l_aioCashTransUnit.payStatus = l_strOrderStatus;                   
                }
                
                // 1.4.4)ArrayListに要素を追加する。
                //[引数] 
                //arg0： 入出金明細オブジェクト
                l_lisaioCashTransUnits.add(l_aioCashTransUnit);
            }   
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisaioCashTransUnits;
    }
    
    /**
     * (get入出金明細From入金連絡)<BR>
     * 入金連絡テーブルから、入出金明細のリストを取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金一覧）get入出金明細From入金連絡」 参照<BR>
     * @@param l_subAccount - (補助口座オブジェクト)
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 4106FE4B0397
     */
    protected List getCashTransferFromCashinNotice(SubAccount l_subAccount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "getCashTransferFromCashinNotice(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.3)ArrayListのインスタンスを生成する。 
        List l_lisAioCashTransUnit = new Vector();
        
        try
        {
            // 1.1)クエリプロセッサを取得する。 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            // 1.2)入金連絡テーブルから、以下の条件のレコードを取得する。     
            //[条件] 
            //証券会社コード = 補助口座.getInstitution().getInstitutionCode()の戻り値 
            //部店コード = 補助口座.get取引店().getBranchCode()の戻り値 
            //顧客コード = 補助口座.getMainAccount().getAccountCode()の戻り値 
            //(振込日 >= システムタイムスタンプの日付部分 or 
            //作業日時の日付部分 = システムタイムスタンプの日付部分）
            
            //[引数] 
            //Rowタイプ： 入金連絡Row.TYPE 
            //Where： "institution_code=? and branch_code=? and account_code=? and (transfer_date>=? or to_char(worked_timestamp)=?)" 
            //リスト： 以下の項目のリスト 
            //補助口座.getInstitution().getInstitutionCode()の戻り値 
            //補助口座.get取引店().getBranchCode()の戻り値 
            //補助口座.getMainAccount().getAccountCode()の戻り値 
            //システムタイムスタンプの日付部分 
            //システムタイムスタンプの日付部分 
            String l_strWhere = "institution_code = ? and branch_code = ? and account_code = ? " +
            "and (transfer_date >= ? or to_char(worked_timestamp,'YYYYMMDD') = ? )";
            
            Object[] l_objValues = new Object[]{
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd")};
            
            List l_lisRows =
                l_queryProcessor.doFindAllQuery(DepositInformRow.TYPE, l_strWhere, l_objValues);
            int l_intDepositInform = l_lisRows.size();
            
            // 1.4)取得した入金連絡Params毎のLoop処理
            
            
            for(int i=0; i<l_intDepositInform; i++)
            {
                DepositInformRow l_depositInformRow = (DepositInformRow) l_lisRows.get(i); 
                
                WEB3AioCashTransUnit l_aioCashTransUnit = new WEB3AioCashTransUnit();
                
                //(*2)以下のようにプロパティをセットする
                //入出金明細.受付日付 = 入金連絡Params.作業日時
                l_aioCashTransUnit.receptionDate = l_depositInformRow.getWorkedTimestamp();
                
                //入出金明細.取引 = 4（入金連絡）
                l_aioCashTransUnit.tradingType = WEB3AioTransUnitDef.CASH_IN_CONTACT;
                
                //入出金明細.金融機@関名 = null
                l_aioCashTransUnit.paySchemeName = null;
                
                //入出金明細.入出金金額 = 入金連絡Params.入金額
                l_aioCashTransUnit.cashTransAmt = String.valueOf(l_depositInformRow.getAmount());
                
                //入出金明細.受渡予定日 = 入金連絡Params.振込日
                l_aioCashTransUnit.deliveryScheduledDate = l_depositInformRow.getTransferDate();
                
                //入出金明細..comデビット決済取引番号 = null
                l_aioCashTransUnit.comDebitNumber = null;
                
                //入出金明細.備考 = null
                l_aioCashTransUnit.ioRemark = null;
                
                //入出金明細.処理状況 = null
                l_aioCashTransUnit.payStatus = null;

                //入出金明細.通貨コード = null
                l_aioCashTransUnit.currencyCode = null;

                //ArrayListに要素を追加する。
                //[引数] 
                //arg0： 入出金明細オブジェクト
                l_lisAioCashTransUnit.add(l_aioCashTransUnit);
            }
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisAioCashTransUnit;
    }
    
    /**
     * (get入出金明細From入出金状況)<BR>
     * 金融機@関連携入出金状況テーブルから、入出金明細のリストを取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金一覧）get入出金明細From入出金状況」 参照<BR>
     * @@param l_subAccount - (補助口座オブジェクト)
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 410704B402BC
     */
    protected List getCashTransferFromCashTransferStatus(SubAccount l_subAccount)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "getCashTransferFromCashTransferStatus(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null)
        {
            log.debug("SubAccount is null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
       
        try
        {
            // 1.1)クエリプロセッサを取得する。 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //=======remian zhou-yong NO.2 障害票U00613 begin ========
            
            // 1.2) 金融機@関連携入出金状況テーブルから、以下の条件のレコードを取得する。 
            // 
            // [条件] 
            // 証券会社コード = 補助口座.getInstitution().getInstitutionCode()の戻り値 
            // 部店コード = 補助口座.get取引店().getBranchCode()の戻り値 
            // 顧客コード = 補助口座.getMainAccount().getAccountCode()の戻り値 
            // 受渡予定日 >= システムタイムスタンプの日付部分 
            // [引数] 
            // Rowタイプ： 金融機@関連携入出金状況Row.TYPE 
            // Where： "institution_code=? and branch_code=? and account_code=? and delivery_scheduled_date>=?" 
            // リスト： 以下の項目のリスト 
            // 補助口座.getInstitution().getInstitutionCode()の戻り値 
            // 補助口座.get取引店().getBranchCode()の戻り値 
            // 補助口座.getMainAccount().getAccountCode()の戻り値 
            // システムタイムスタンプの日付部分
            
            String l_strWhere = "institution_code = ? and branch_code = ? and account_code = ? " +
                "and delivery_scheduled_date >= ? ";
                

            Object[] l_objValues = {
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd")
                };
                
            List l_lisRows =
                l_queryProcessor.doFindAllQuery(BankCashTransferStatusRow.TYPE, l_strWhere, l_objValues);
            
            //1.3) ArrayList( )
            List l_lisAioCashTransUnit = new Vector();

            //1.4) (*1) 取得した金融機@関連携入出金状況Params毎のLoop処理
            for(int i = 0; i < l_lisRows.size(); i++)
            {
                BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                    (BankCashTransferStatusParams)l_lisRows.get(i);
                
                //1.4.1) 
                //(*2)金融機@関連携入出金状況Params.処理区分 = 1（OK） and
                //金融機@関連携入出金状況Params.処理FLAG（注文） = 2（応答送信） and
                //金融機@関連携入出金状況Params.処理FLAG（決済開始） = 2（応答送信） and
                //金融機@関連携入出金状況Params.処理FLAG（決済結果） in (1, 2, 8, 9, A)
                //の場合、実施
                if(WEB3TransactionStatusDef.OK.equals(l_bankCashTransferStatusParams.getTransactionStatus())
                    && WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(l_bankCashTransferStatusParams.getOrderStatusFlag())
                    && WEB3StartStatusFlgDef.RESPONSE_SEND.equals(l_bankCashTransferStatusParams.getStartStatusFlag())
                    && (WEB3ResultStatusFlagDef.NOTIFY_RECEIPT.equals(l_bankCashTransferStatusParams.getResultStatusFlag())
                        || WEB3ResultStatusFlagDef.RESPONSE_SEND.equals(l_bankCashTransferStatusParams.getResultStatusFlag())
                        || WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL.equals(l_bankCashTransferStatusParams.getResultStatusFlag())
                        || WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE.equals(l_bankCashTransferStatusParams.getResultStatusFlag())
                        || WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE.equals(l_bankCashTransferStatusParams.getResultStatusFlag())
                        )
                   )
                {
                    //1.4.1.1) get注文単位(String, String, String, String, SubAccountTypeEnum)
                    //アイテムの定義
                    //注文単位を取得する。
                    //[引数] 
                    //証券会社コード： 金融機@関連携入出金状況Params.証券会社コード 
                    //部店コード： 金融機@関連携入出金状況Params.部店コード 
                    //顧客コード： 金融機@関連携入出金状況Params.顧客コード 
                    //識別コード： 金融機@関連携入出金状況Params.識別コード 
                    //補助口座タイプ： 1（預り金口座） 
                    FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                    WEB3AioOrderManager l_aioOrderManager = 
                        (WEB3AioOrderManager) l_finApp.getTradingModule(
                            ProductTypeEnum.AIO).getOrderManager();
                    
                    AioOrderUnit l_aioOrderUnit = null;
                    try
                    {
                        l_aioOrderUnit = l_aioOrderManager.getOrderUnit(
                            l_bankCashTransferStatusParams.getInstitutionCode(),
                            l_bankCashTransferStatusParams.getBranchCode(),
                            l_bankCashTransferStatusParams.getAccountCode(),
                            l_bankCashTransferStatusParams.getOrderRequestNumber(),
                            SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.debug("注文単位を取得できない！", l_ex);
                    }
                    //注文単位が取得できた場合は、以降の処理はスキップする。
                    //（get入出金明細From注文単位()での処理対象となるため）
                    if(l_aioOrderUnit != null)
                    {
                        continue;
                    }
                }
                
                //1.4.2) 入出金明細()
                WEB3AioCashTransUnit l_aioCashTransUnit = new WEB3AioCashTransUnit();
                
                //1.4.3) get金融機@関名(SubAccount, String)
                //アイテムの定義
                //オンライン入金で使用した決済機@関の名称を取得する。
                //[引数] 
                //補助口座： 補助口座オブジェクト 
                //識別コード： 金融機@関連携入出金状況Params.識別コード
                WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService
                = (WEB3AioMultiBankSettleControlService)Services.getService(
                    WEB3AioMultiBankSettleControlService.class);
                
                String l_strFinInstitutionName = l_aioMultiBankSettleControlService.getFinInstitutionName(
                    l_subAccount, l_bankCashTransferStatusParams.getOrderRequestNumber());
                
                //1.4.4) get入出金状況(SubAccount, String, OrderStatusEnum, String)
                //アイテムの定義
                //オンライン入出金の状況を取得する。
                //[引数] 
                //補助口座： 補助口座オブジェクト 
                //識別コード： 金融機@関連携入出金状況Params.識別コード 
                //注文状態： null 
                //注文取消区分： null
                String l_strCashTransSituation = l_aioMultiBankSettleControlService.getCashTransSituation(
                    l_subAccount,
                    l_bankCashTransferStatusParams.getOrderRequestNumber(),
                    null,
                    null);

                //1.4.5)  (*3) プロパティセット
                //(*3) 以下のようにプロパティをセットする。
                //入出金明細.受付日付 = 金融機@関連携入出金状況Params.注文日時
                l_aioCashTransUnit.receptionDate = l_bankCashTransferStatusParams.getOrderDateTime();
                
                //入出金明細.取引 = 3（入金）
                l_aioCashTransUnit.tradingType = WEB3AioTransUnitDef.CASH_IN;
                
                //入出金明細.金融機@関名 = get金融機@関名()の戻り値
                l_aioCashTransUnit.paySchemeName = l_strFinInstitutionName;
                
                //入出金明細.入出金金額 = 金融機@関連携入出金状況Params.金額
                l_aioCashTransUnit.cashTransAmt = String.valueOf(l_bankCashTransferStatusParams.getAmount());
                
                //入出金明細.受渡予定日 = 金融機@関連携入出金状況Params.受渡予定日
                l_aioCashTransUnit.deliveryScheduledDate = l_bankCashTransferStatusParams.getDeliveryScheduledDate();
                
                //入出金明細..comデビット決済取引番号 = 金融機@関連携入出金状況Params..comデビット決済取引番号
                l_aioCashTransUnit.comDebitNumber  = l_bankCashTransferStatusParams.getCenterPayId();
                
                //入出金明細.備考 = null
                l_aioCashTransUnit.ioRemark = null;
                
                //入出金明細.処理状況 = get入出金状況()の戻り値
                l_aioCashTransUnit.payStatus  = l_strCashTransSituation;  

                //入出金明細.通貨コード = null
                l_aioCashTransUnit.currencyCode = null;

                // 1.4.6)ArrayListに要素を追加する。
                //[引数] 
                //arg0： 入出金明細オブジェクト
                l_lisAioCashTransUnit.add(l_aioCashTransUnit);
                
                //=======remian zhou-yong NO.2 障害票U00613 end ========
            }
            log.exiting(STR_METHOD_NAME);
            return l_lisAioCashTransUnit;
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    /**
     * (get取引区分)<BR>
     * 引数で渡された注文単位から取引区分を判定し、返却する。 <BR>
     * <BR>
     * １）注文単位Params.注文種別 == 1001（出金注文） の場合 <BR>
     * <BR>
     * １－１） <BR>
     *  注文単位Params.出金申込区分 == null or <BR>
     *  注文単位Params.出金申込区分 == "mf"（投信解約） <BR>
     *  の場合、0（出金） を返却する。 <BR>
     * <BR>
     * １－２）<BR> 
     * 注文単位Params.出金申込区分 == "33"<BR> 
     *  の場合、1（会費出金） を返却する。<BR>
     * <BR>
     * １－３）上記の１－１）、１－２）以外の場合、2（情報料出金） を返却する。<BR>
     * <BR>
     * ２）注文単位Params.注文種別 = 1002（入金注文） の場合、3（入金） を返却する。 <BR>
     * <BR>
     * ３）注文単位Params.注文種別 = 1007（振替注文（預り金から株先証拠金）） の場合、<BR>
     *      5（先物OP証拠金へ出金） を返却する。 <BR>
     * <BR>
     * ４）注文単位Params.注文種別 = 1008（振替注文（株先証拠金から預り金）） の場合、<BR>
     *      6（先物OP証拠金から入金） を返却する。 <BR>
     * <BR>
     * ５）注文単位Params.注文種別 = 1005（振替注文（預り金から信用保証金）） の場合、<BR>
     *      7（信用保証金へ出金） を返却する。 <BR>
     * <BR>
     * ６）注文単位Params.注文種別 = 1006（振替注文（信用保証金から預り金）） の場合、<BR>
     *      8（信用保証金から入金） を返却する。 <BR>
     * <BR>
     * ７）注文単位Params.注文種別 = 1011（振替注文（預り金から為替保証金））<BR>
     * または、注文単位Params.注文種別 = 1021（CFD振替注文（預り金からCFD口座））の場合<BR>
     * 　@　@　@　@　@　@顧客オブジェクトを取得する。<BR>
     * 　@　@　@　@　@　@拡張アカウントマネージャ.get顧客()をコールする。<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@口座ID： 注文単位Params.口座ID<BR>
     * <BR>
     * 　@　@　@　@　@　@GFT振替状況を取得する。<BR>
     * 　@　@　@　@　@　@FXデータ制御サービス.Impl.getGFT振替状況()をコールする。<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@証券会社コード： 顧客.getInstitution.getInstitutionCode()の戻り値<BR>
     * 　@　@　@　@　@　@部店コード： 顧客.getBranch.getBranchCode()の戻り値<BR>
     * 　@　@　@　@　@　@識別コード： 注文単位Params.識別コード<BR>
     * <BR>
     * 　@　@　@　@　@　@GFT振替状況Params.入出金一覧取引区分を返却する。<BR>
     * 　@　@　@　@　@　@※getGFT振替状況()の戻り値がnullの場合、9（FX保証金へ出金） を返却する。<BR>
     * <BR>
     * ８）注文単位Params.注文種別 = 1012（振替注文（為替保証金から預り金））<BR>
     * または、注文単位Params.注文種別 = 1022（CFD振替注文（CFD口座から預り金））の場合<BR>
     * 　@　@　@　@　@　@顧客オブジェクトを取得する。<BR>
     * 　@　@　@　@　@　@拡張アカウントマネージャ.get顧客()をコールする。<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@口座ID： 注文単位Params.口座ID<BR>
     * <BR>
     * 　@　@　@　@　@　@GFT振替状況を取得する。<BR>
     * 　@　@　@　@　@　@FXデータ制御サービス.Impl.getGFT振替状況()をコールする。<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@証券会社コード： 顧客.getInstitution.getInstitutionCode()の戻り値<BR>
     * 　@　@　@　@　@　@部店コード： 顧客.getBranch.getBranchCode()の戻り値<BR>
     * 　@　@　@　@　@　@識別コード： 注文単位Params.識別コード<BR>
     * <BR>
     * 　@　@　@　@　@　@GFT振替状況Params.入出金一覧取引区分を返却する。<BR>
     * 　@　@　@　@　@　@※getGFT振替状況()の戻り値がnullの場合、10（FX保証金から入金） を返却する。<BR>
     * <BR>
     * ９）注文単位Params.注文種別 = 1013（振替注文（預り金から外国株式口座）） の場合、<BR>
     *      11（中国株式口座へ出金） を返却する。 <BR>
     * <BR>
     * １０）注文単位Params.注文種別 = 1014（振替注文（外国株式口座から預り金）） の場合、<BR>
     *      12（中国株式口座から入金） を返却する。 <BR>
     * <BR>
     * １１）注文単位Params.注文種別 = 1017（その他振替注文（預り金からX）） の場合、<BR>
     *      13（その他出金） を返却する。 <BR>
     * <BR>
     * １２）注文単位Params.注文種別 = 1018（その他振替注文（Xから預り金）） の場合、<BR>
     *      14（その他入金） を返却する。 <BR>
     * <BR>
     * １３）注文単位Params.注文種別 = 1019（振替注文(預り金から大証金)） の場合、または<BR>
     * 　@　@　@注文単位Params.注文種別 = 1020（振替注文（預かり金からオリックスクレジット））<BR>
     * 　@　@　@の場合、15（担保ローン返済） を返却する。<BR>
     * <BR>
     * @@param l_aioOrderUnitParams - (注文単位Paramsオブジェクト)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 410704B402BC
     */
    public String getTradingType(AioOrderUnitParams l_aioOrderUnitParams)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "getTradingType(AioOrderUnitParams l_aioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if(l_aioOrderUnitParams == null)
        {
            log.debug("該当パラメータにNull値は設定できません。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strTradingType = null;

        //拡張アカウントマネージャ取得する
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //１）注文単位Params.注文種別 == 1001（出金注文） の場合         
        if (OrderTypeEnum.CASH_OUT.equals(l_aioOrderUnitParams.getOrderType()))
        {
            //１－１）   注文単位Params.出金申込区分 == null or 
            //注文単位Params.出金申込区分 == "mf"（投信解約） 
            //の場合、0（出金） を返却する。 
            if (l_aioOrderUnitParams.getPaymentApplicationDiv() == null ||
                WEB3AioPaymentApplicationDivDef.MF.equals(
                    l_aioOrderUnitParams.getPaymentApplicationDiv()))
            {
                l_strTradingType = WEB3AioTransUnitDef.CASH_OUT;
            }
            //１－２）  注文単位Params.出金申込区分 == "33" 
            //の場合、1（会費出金） を返却する。
            else if (WEB3AioPaymentApplicationDivDef.MEMBERSHIP_FEE.equals(
                    l_aioOrderUnitParams.getPaymentApplicationDiv()))
            {
                l_strTradingType = WEB3AioTransUnitDef.DUES_CASH_OUT;
            }
            //１－３）上記の１－１）、１－２）以外の場合、2（情報料出金） を返却する。
            else
            {
                l_strTradingType = WEB3AioTransUnitDef.INFO_CASN_OUT;
            }
        }        
        //２）注文単位Params.注文種別 = 1002（入金注文） の場合、3（入金） を返却する。 
        else if (OrderTypeEnum.CASH_IN.equals(l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.CASH_IN;
        }
        //３）注文単位Params.注文種別 = 1007（振替注文（預り金から株先証拠金）） の場合、
        //      5（先物OP証拠金へ出金） を返却する。 
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.FUTURE_OP_MARGIN_CASHOUT;
        }
        //４）注文単位Params.注文種別 = 1008（振替注文（株先証拠金から預り金）） の場合、
        //      6（先物OP証拠金から入金） を返却する。 
        else if (OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.FUTURE_OP_MARGIN_TO_CASHIN;
        }
        //５）注文単位Params.注文種別 = 1005（振替注文（預り金から信用保証金）） の場合、
        //      7（信用保証金へ出金） を返却する。 
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.MARGIN_GUARANTEE_CASHOUT;
        }
        //６）注文単位Params.注文種別 = 1006（振替注文（信用保証金から預り金）） の場合、
        //      8（信用保証金から入金） を返却する。 
        else if (OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.MARGIN_GUARANTEE_TO_CASHIN;
        }

        //７）注文単位Params.注文種別 = 1011（振替注文（預り金から為替保証金））
        //または、注文単位Params.注文種別 = 1021（CFD振替注文（預り金からCFD口座））の場合
        //８）注文単位Params.注文種別 = 1012（振替注文（為替保証金から預り金））
        //または、注文単位Params.注文種別 = 1022（CFD振替注文（CFD口座から預り金））の場合
        else if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
            l_aioOrderUnitParams.getOrderType())
            || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(
                l_aioOrderUnitParams.getOrderType())
            || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(
                l_aioOrderUnitParams.getOrderType())
            || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            //顧客オブジェクトを取得する。
            //拡張アカウントマネージャ.get顧客()をコールする。
            //[引数]
            //口座ID： 注文単位Params.口座ID
            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = l_gentradeAccountManager.getMainAccount(
                    l_aioOrderUnitParams.getAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //GFT振替状況を取得する。
            //FXデータ制御サービス.Impl.getGFT振替状況()をコールする。
            //[引数]
            //証券会社コード： 顧客.getInstitution.getInstitutionCode()の戻り値
            //部店コード： 顧客.getBranch.getBranchCode()の戻り値
            //識別コード： 注文単位Params.識別コード
            WEB3FXDataControlService l_fxDataControlService =
                (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
            GftTransferStatusParams l_gftTransferStatusParams =
                l_fxDataControlService.getGFTTransferStatus(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    l_aioOrderUnitParams.getOrderRequestNumber());

            //GFT振替状況Params.入出金一覧取引区分を返却する。
            if (l_gftTransferStatusParams != null)
            {
                l_strTradingType = l_gftTransferStatusParams.getIoListTradeDiv();
            }
            //※getGFT振替状況()の戻り値がnullの場合
            else
            {
                //注文単位Params.注文種別 = 1011（振替注文（預り金から為替保証金））
                //または、注文単位Params.注文種別 = 1021（CFD振替注文（預り金からCFD口座））の場合
                //9（FX保証金へ出金） を返却する。
                if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                    l_aioOrderUnitParams.getOrderType())
                    || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(
                        l_aioOrderUnitParams.getOrderType()))
                {
                    l_strTradingType = WEB3AioTransUnitDef.FX_CASHOUT;
                }
                else
                {
                    //注文単位Params.注文種別 = 1012（振替注文（為替保証金から預り金））
                    //または、注文単位Params.注文種別 = 1022（CFD振替注文（CFD口座から預り金））の場合
                    //10（FX保証金から入金） を返却する。
                    l_strTradingType = WEB3AioTransUnitDef.FX_TO_CASHIN;
                }
            }
        }

        //９）注文単位Params.注文種別 = 1013（振替注文（預り金から外国株式口座）） の場合、
        //      11（中国株式口座へ出金） を返却する。 
        else if (OrderTypeEnum.TRANSFER_TO_FEQ.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.MIDIUM_TERM_GOV_EQUITY_ACCOUNT_CASHOUT;
        }
        //１０）注文単位Params.注文種別 = 1014（振替注文（外国株式口座から預り金）） の場合、
        //      12（中国株式口座から入金） を返却する。 
        else if (OrderTypeEnum.TRANSFER_FROM_FEQ.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.MIDIUM_TERM_GOV_EQUITY_ACCOUNT_TO_CASHIN;
        }
        //１１）注文単位Params.注文種別 = 1017（その他振替注文（預り金からX）） の場合、
        //      13（その他出金） を返却する。 
        else if (OrderTypeEnum.TO_OTHER_TRANSFER.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.OTHER_CASHOUT;
        }
        //１２）注文単位Params.注文種別 = 1018（その他振替注文（Xから預り金）） の場合、
        //      14（その他入金） を返却する。
        else if (OrderTypeEnum.FROM_OTHER_TRANSFER.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.OTHER_CASHIN;
        }
        // １３）注文単位Params.注文種別 = 1019（振替注文(預り金から大証金)） の場合、または
        //　@注文単位Params.注文種別 = 1020（振替注文（預かり金からオリックスクレジット））  の場合
        //  15（担保ローン返済） を返却する。
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK.equals(
            l_aioOrderUnitParams.getOrderType())
            || OrderTypeEnum.TO_ORIX_CREDIT.equals(l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.SECURITY_LOAN_REPAY;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strTradingType;
    }
    
   /**
    * (get備考)
    * 引数で渡された注文単位から備考に設定する項目を判定し、返却する。 <BR>
    * <BR>
    * １）注文単位Params.注文種別 == 1001（出金注文） の場合 <BR>
    * <BR>
    * 注文単位Params.出金申込区分 を返却する。 <BR>
    * <BR>
    * ２）注文単位Params.注文カテゴリ = 15（為替保証金振替） の場合 <BR>
    * <BR>
    * ２－１）顧客オブジェクトを取得する。 <BR>
    * <BR>
    * 拡張アカウントマネージャ.get顧客()をコールする。 <BR>
    * <BR>
    * [引数] <BR>
    * 口座ID： 注文単位Params.口座ID <BR>
    * <BR>
    * ２－２）GFT振替状況を取得する。 <BR>
    * <BR>
    * FXデータ制御サービス.Impl.getGFT振替状況()をコールする。 <BR>
    * <BR>
    * [引数] <BR>
    * 証券会社コード： 顧客.getInstitution.getInstitutionCode()の戻り値 <BR>
    * 部店コード： 顧客.getBranch.getBranchCode()の戻り値 <BR>
    * 識別コード： 注文単位Params.識別コード <BR>
    * <BR>
    * GFT振替状況Params.コース区分を返却する。<BR>
    * ※getGFT振替状況()の戻り値がnullの場合は、nullを返却する。<BR>
    * <BR>
    * ３）注文単位Params.注文種別 == 1017 or 1018（その他振替注文） の場合 <BR>
    * <BR>
    * 注文単位Params.振替記述 を返却する。 <BR>
    * <BR>
    * ※注文単位Params.振替記述 == 98(摘要任意入力継続)の場合、99（その他）をセットする。 <BR>
    * <BR>
    * ４）上記の条件以外の場合 <BR>
    * <BR>
    * null を返却する。 <BR>
    * <BR>
    * @@param l_aioOrderUnitParams - (注文単位Paramsオブジェクト)
    * @@return String
    * @@throws WEB3BaseException
    * @@roseuid 410704B402BC
    */
   public String getRemark(AioOrderUnitParams l_aioOrderUnitParams)
       throws WEB3BaseException 
   {
       final String STR_METHOD_NAME =
           "getRemark(AioOrderUnitParams l_aioOrderUnitParams)";
       log.entering(STR_METHOD_NAME);
       
       if(l_aioOrderUnitParams == null)
       {
           log.debug("該当パラメータにNull値は設定できません。");
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       
       //１）注文単位Params.注文種別 == 1001（出金注文） の場合 
       //注文単位Params.出金申込区分 を返却する。 
       if (OrderTypeEnum.CASH_OUT.equals(l_aioOrderUnitParams.getOrderType()))
       {
           log.debug("注文単位Params.注文種別 == 1001（出金注文） の場合");
           return l_aioOrderUnitParams.getPaymentApplicationDiv();
       }
       
       //２）注文単位Params.注文カテゴリ = 15（為替保証金振替） の場合 
       //２－１）顧客オブジェクトを取得する。 
       //拡張アカウントマネージャ.get顧客()をコールする。 
       //[引数] 
       //口座ID： 注文単位Params.口座ID
       FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
       WEB3GentradeAccountManager l_web3GentradeAccountManager =
           (WEB3GentradeAccountManager) l_finApp.getAccountManager();
       if (OrderCategEnum.FX.equals(l_aioOrderUnitParams.getOrderCateg()))
       {
           log.debug("注文単位Params.注文カテゴリ = 15（為替保証金振替） の場合s");
           MainAccount l_mainAccount = null;
           try
           {
               l_mainAccount = 
                   l_web3GentradeAccountManager.getMainAccount(
                           l_aioOrderUnitParams.getAccountId());
               
           }
           catch (NotFoundException l_ex)
           {
               log.debug("__NotFoundException__", l_ex);
               //例外をスローする
               log.exiting(STR_METHOD_NAME);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.getMessage(),
                   l_ex);
           }        
           //２－２）GFT振替状況を取得する。 
           //FXデータ制御サービス.Impl.getGFT振替状況()をコールする。 
           //[引数] 
           //証券会社コード： 顧客.getInstitution.getInstitutionCode()の戻り値 
           //部店コード： 顧客.getBranch.getBranchCode()の戻り値 
           //識別コード： 注文単位Params.識別コード 
           
           // FXデータ制御サービスを取得
           WEB3FXDataControlService l_fxDataControlService = 
               (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
           GftTransferStatusParams l_gftTransferStatusParams = 
           l_fxDataControlService.getGFTTransferStatus(
                   l_mainAccount.getInstitution().getInstitutionCode(), 
                   l_mainAccount.getBranch().getBranchCode(),  
                   l_aioOrderUnitParams.getOrderRequestNumber());

           //GFT振替状況Params.コース区分を返却する。
           //※getGFT振替状況()の戻り値がnullの場合は、nullを返却する。
           if (l_gftTransferStatusParams != null)
           {
               log.exiting(STR_METHOD_NAME);
               return l_gftTransferStatusParams.getCourseDiv();
           }

           log.exiting(STR_METHOD_NAME);
           return null;
       }  
       //３）注文単位Params.注文種別 == 1017 or 1018（その他振替注文） の場合 
       //注文単位Params.振替記述 を返却する。 
       //※注文単位Params.振替記述 == 98(摘要任意入力継続)の場合、99（その他）をセットする。 
       if (OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_aioOrderUnitParams.getOrderType()) ||
           OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_aioOrderUnitParams.getOrderType()))
       {
           log.debug("注文単位Params.注文種別 == 1017 or 1018（その他振替注文） の場合");
           if (WEB3RemarkCodeDef.REMARK_INPUT_REQUEST_CONTINUE_THREE.equals(l_aioOrderUnitParams.getDescription()))
           {                   
               log.debug("注文単位Params.振替記述 == 98(摘要任意入力継続)の場合");
               return WEB3RemarkCodeDef.BLANK;
           }
           else
           {
               log.debug("注文単位Params.振替記述" + l_aioOrderUnitParams.getDescription());
               return l_aioOrderUnitParams.getDescription();
           }
       }
       log.exiting(STR_METHOD_NAME);
       return null;
   }
   /**
    * (get入出金明細FromGFT振替状況)
    * GFT振替状況テーブルから、入出金明細のリストを取得する。 <BR>
    * <BR>
    * シーケンス図 <BR>
    * 「（入出金一覧）get入出金明細FromGFT振替状況」 参照 <BR>
    * <BR>
    * @@param l_subAccount - (補助口座オブジェクト)
    * @@return List
    * @@throws WEB3BaseException
    * @@roseuid 410704B402BC
    */
    public List getCashTransferFromGftTransferStatus(SubAccount l_subAccount)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "getCashTransferFromGftTransferStatus(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
      
        if(l_subAccount == null)
        {
            log.debug("該当パラメータにNull値は設定できません。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }       

        // 1.3 ArrayListのインスタンスを生成する。 
        List l_lisAioCashTransUnit = new Vector();
        
        try
        {
            // 1.1 クエリプロセッサを取得する。 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            // 1.2 GFT振替状況テーブルから、以下の条件のレコードを取得する。 
            //[条件] 
            //証券会社コード = 補助口座.getInstitution().getInstitutionCode()の戻り値 
            //部店コード = 補助口座.get取引店().getBranchCode()の戻り値 
            //顧客コード = 補助口座.getMainAccount().getAccountCode()の戻り値 
            //受渡予定日 >= システムタイムスタンプの日付部分 
            //[引数] 
            //Rowタイプ： GFT振替状況Row.TYPE 
            //Where： "institution_code=? and branch_code=? and account_code=? and transfer_date>=?" 
            //リスト： 以下の項目のリスト 
            //補助口座.getInstitution().getInstitutionCode()の戻り値 
            //補助口座.get取引店().getBranchCode()の戻り値 
            //補助口座.getMainAccount().getAccountCode()の戻り値 
            //システムタイムスタンプの日付部分 

            String l_strWhere = "institution_code = ? and branch_code = ? " +
                    "and account_code = ? and transfer_date >= ? ";
            
            Object[] l_objValues = new Object[]{
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd")
                };
            
            List l_lisRows =
                l_queryProcessor.doFindAllQuery(
                        GftTransferStatusRow.TYPE, 
                        l_strWhere, 
                        l_objValues);
            
            int l_intGftTransferSize = l_lisRows.size();
            log.debug("l_intGftTransferSize = " + l_intGftTransferSize);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_web3AioOrderMgr =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            
            //1.4 取得したGFT振替状況Params毎のLoop処理
                        
            for(int i = 0; i < l_intGftTransferSize; i++)
            {
                GftTransferStatusRow l_gftTransferStatusRow = 
                    (GftTransferStatusRow) l_lisRows.get(i); 
                                
                log.debug("GFT振替状況Row = " + l_gftTransferStatusRow);
                //1.4.1 注文単位を取得する。 
                //[引数] 
                //証券会社コード： GFT振替状況Params.証券会社コード 
                //部店コード： GFT振替状況Params.部店コード 
                //顧客コード： GFT振替状況Params.顧客コード 
                //識別コード： GFT振替状況Params.識別コード 
                //補助口座タイプ： 1（預り金口座） 
                boolean l_blnNotFoundOrderUnit = false;
                AioOrderUnitRow l_aioOrderUnitRow = null;
                String l_strTransferStatus = null;
                WEB3AioFXTransStatusUtility l_aioFxStatusUtil = new WEB3AioFXTransStatusUtility();
                String l_Status = null;

                try
                {
                    l_aioOrderUnitRow = this.getOrderUnitRow(
                        l_gftTransferStatusRow.getInstitutionCode(), 
                        l_gftTransferStatusRow.getBranchCode(), 
                        l_gftTransferStatusRow.getAccountCode(), 
                        l_gftTransferStatusRow.getOrderRequestNumber(), 
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                }
                catch (NotFoundException l_ex)
                {
                    log.debug("__NotFoundException__" + l_ex);
                    l_blnNotFoundOrderUnit = true;

                } catch (WEB3BusinessLayerException be) {
                    throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00739,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
                }

                //注文単位.摘要コードが72（株先証拠金：大証)または71（株先証拠金：東証)の場合、
                //処理をスキップしない。
                if (l_aioOrderUnitRow != null
                    && !WEB3StringTypeUtility.isEmpty(
                        l_aioOrderUnitRow.getRemarkCode()))
                {

                    if (WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_OSAKA.equals(
                        l_aioOrderUnitRow.getRemarkCode())
                        || WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_TOKYO.equals(
                            l_aioOrderUnitRow.getRemarkCode()))
                    {
                        l_blnNotFoundOrderUnit = true;

                        //振替状況を取得する為、振替キューテーブルを取得する。
                        //(PK:証券会社コード、部店コード、顧客コード、識別コード）
                        HostTransferAcceptRow l_HostTransferAcceptRow = null;

                        try {

                            l_HostTransferAcceptRow =
                                HostTransferAcceptDao.findRowByPk(l_subAccount.getInstitution().getInstitutionCode(),
                                                                  l_subAccount.getMainAccount().getBranch().getBranchCode(),
                                                                  l_subAccount.getMainAccount().getAccountCode(),
                                                                  l_gftTransferStatusRow.getOrderRequestNumber());

                            //DBから取得できた場合、処理区分をチェックし、9：エラーの場合はセットする。（それ以外はDefaultStatus.OTHER）
                            if (l_HostTransferAcceptRow != null && !WEB3StringTypeUtility.isEmpty(l_HostTransferAcceptRow.getStatus())) {

                                l_Status = l_HostTransferAcceptRow.getStatus();

                            } else {
                                l_Status = null;
                            }

                        } catch (DataNetworkException l_dqex) {
                            log.error("DBへのアクセスに失敗しました:", l_dqex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_dqex.getMessage(),
                            l_dqex);

                        //DBに引っかからなかった場合、受付キューテーブルの値をDefaultStatus.OTHERをセットする。
                        } catch (DataQueryException l_ex) {
                            log.debug("受付キューTBLデータ無し。 " + l_ex);
                            l_Status = null;

                        }

                        //get処理状況メッセージを行う

                        l_strTransferStatus = l_aioFxStatusUtil.getResult(
                            l_gftTransferStatusRow.getTransferStatusDiv(),
                            l_gftTransferStatusRow.getSendRcvDiv(),
                            l_gftTransferStatusRow.getResultCode(),
                            l_aioOrderUnitRow.getOrderStatus().intValue() + "",
                            l_aioOrderUnitRow.getCancelType(),
                            l_Status);
                            log.debug("振替状況Result = " + l_strTransferStatus);

                    }

                }

                log.debug("l_blnNotFoundOrderUnit = " + l_blnNotFoundOrderUnit);
                if (!l_blnNotFoundOrderUnit)
                {
                    continue;
                }


                //1.4.2 入出金明細のインスタンスを生成する。 
                WEB3AioCashTransUnit l_aioCashTransUnit = new WEB3AioCashTransUnit();
                
                //1.4.3 振替状況を取得する。 
                //[引数] 
                //振替状況区分： GFT振替状況Params.振替状況区分 
                //送受信区分： GFT振替状況Params.送受信区分 
                //受付結果コード： GFT振替状況Params.受付結果コード 

                if (WEB3StringTypeUtility.isEmpty(l_strTransferStatus)) {
                    l_strTransferStatus = 
                        this.getTransferStatus(
                            l_gftTransferStatusRow.getTransferStatusDiv(), 
                            l_gftTransferStatusRow.getSendRcvDiv(), 
                            l_gftTransferStatusRow.getResultCode());

                    log.debug("GFT振替状況Params.振替状況区分 = " + l_gftTransferStatusRow.getTransferStatusDiv());
                    log.debug("GFT振替状況Params.送受信区分  = " + l_gftTransferStatusRow.getSendRcvDiv());
                    log.debug("GFT振替状況Params.受付結果コード = " + l_gftTransferStatusRow.getResultCode());

                    log.debug("振替状況 = " + l_strTransferStatus);

                }

                //1.4.4 (*2) プロパティセット
                //(*2) 以下のようにプロパティをセットする。

                //入出金明細.受付日付 = GFT振替状況Params.作成日付
                l_aioCashTransUnit.receptionDate = 
                    l_gftTransferStatusRow.getCreatedTimestamp();

                //入出金明細.取引 = GFT振替状況Params.入出金一覧取引区分
                l_aioCashTransUnit.tradingType =
                    l_gftTransferStatusRow.getIoListTradeDiv();
                
                //入出金明細.金融機@関名 = null
                l_aioCashTransUnit.paySchemeName = null;
                
                //入出金明細.入出金金額 = GFT振替状況Params.金額
                l_aioCashTransUnit.cashTransAmt = l_gftTransferStatusRow.getAmount() + "";
                
                //入出金明細.受渡予定日 = GFT振替状況Params.受渡予定日
                l_aioCashTransUnit.deliveryScheduledDate = 
                    WEB3DateUtility.getDate(
                            l_gftTransferStatusRow.getTransferDate(), "yyyyMMdd");
                
                //入出金明細..comデビット決済取引番号 = null
                l_aioCashTransUnit.comDebitNumber = null;
                
                //入出金明細.備考 = GFT振替状況Params.コース区分
                l_aioCashTransUnit.ioRemark =
                    l_gftTransferStatusRow.getCourseDiv();
                log.debug("入出金明細.備考 = " + l_aioCashTransUnit.ioRemark);
                
                //入出金明細.処理状況 = get振替状況()の戻り値
                l_aioCashTransUnit.payStatus = l_strTransferStatus;
                log.debug("入出金明細.処理状況 = " + l_aioCashTransUnit.payStatus);

                //入出金明細.通貨コード = null
                l_aioCashTransUnit.currencyCode = null;

                //ArrayListに要素を追加する。
                //[引数] 
                //arg0： 入出金明細オブジェクト
                l_lisAioCashTransUnit.add(l_aioCashTransUnit);
            }
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisAioCashTransUnit;
    }
    
    /**
     * (get振替状況)
     * 引数で渡されたGFT振替状況の項目から振替状況を判定し、判定結果を返却する。<BR> 
     * <BR>
     * 詳細は、「入出金ステータス構成表.xls」のシート「為替保証金連携(2)」参照 <BR>
     * <BR>
     * @@param l_strTransferStatusDiv - (振替状況区分)
     * @@param l_strSendRcvDiv - (送受信区分)
     * @@param l_strResultCode - (受付結果コード)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 410704B402BC
     */
     public String getTransferStatus(
             String l_strTransferStatusDiv, 
             String l_strSendRcvDiv, 
             String l_strResultCode)
         throws WEB3BaseException 
     {
         final String STR_METHOD_NAME =
             "getTransferStatus(String l_strTransferStatusDiv, " + 
             "String l_strSendRcvDiv, String l_strResultCode)";
         log.entering(STR_METHOD_NAME);
         
         WEB3AioFXTransStatusUtility l_aioFxStatusUtil = new WEB3AioFXTransStatusUtility();
         String l_strTransferStatus = l_aioFxStatusUtil.getResult(
                 l_strTransferStatusDiv, 
                 l_strSendRcvDiv, 
                 l_strResultCode, 
                 null, 
                 null, 
                 null);
         log.debug("振替状況Result = " + l_strTransferStatus);
         
         log.exiting(STR_METHOD_NAME);
         return l_strTransferStatus;
     }

     /**
      * (get注文単位)<BR>
      * 注文単位オブジェクトを取得する。<BR>
      * <BR>
      * １）以下のオブジェクトを取得する。<BR>
      * 
      * 証券会社オブジェクト = 拡張アカウントマネージャ.getInstitutiion(引数.証券会社コード)<BR>
      * 部店オブジェクト = 拡張アカウントマネージャ.get部店(引数.証券会社コード, 引数.部店コード)<BR>
      * 口座（顧客）オブジェクト = 拡張アカウントマネージャ.getMainAccount(証券会社オブジェクト, 部店オブジェクト, 
      * 引数.顧客コード)<BR>
      * 補助口座オブジェクト = 拡張アカウントマネージャ.getSubAccount(口座.口座ID, 引数.補助口座タイプ)<BR>
      * <BR>
      * ２）注文単位オブジェクトを取得する。<BR>
      * <BR>
      * [検索条件]<BR>
      * 口座ID = 口座.口座ID<BR>
      * 補助口座ID = 補助口座.補助口座ID<BR>
      * 部店ID = 部店.部店ID<BR>
      * 銘柄タイプ = ProductTypeEnum.CASH<BR>
      * 識別コード = 引数.識別コード<BR>
      * <BR>
      * 該当行が存在した場合、複数行一致した場合は例外をスローする。<BR>
      * <BR>
      *         class: WEB3BusinessLayerException<BR>
      *         tag:   BUSINESS_ERROR_00739<BR>
      * <BR>
      * @@param l_strInstitutionCode - (証券会社コード)
      * @@param l_strBranchCode - (部店コード)
      * @@param l_strAccountCode - (顧客コード)
      * @@param l_strOrderRequestNumber - (識別コード)
      * @@param l_subAccountType - (補助口座タイプ)
      * @@return AioOrderUnit
      * @@throws NotFoundException, WEB3BaseException
      * @@roseuid 
      */
     public AioOrderUnitRow getOrderUnitRow(
         String l_strInstitutionCode, 
         String l_strBranchCode, 
         String l_strAccountCode, 
         String l_strOrderRequestNumber, 
         SubAccountTypeEnum l_subAccountType)        
         throws WEB3BaseException, NotFoundException
     {
         String STR_METHOD_NAME = "getOrderUnit(" + 
             "String l_strBranchCode, String l_strAccountCode, " + 
             "String l_strOrderRequestNumber, SubAccountTypeEnum l_subAccountType)";
             
         log.entering(STR_METHOD_NAME);        
         
         //１）以下のオブジェクトを取得する。
         FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
         
         //拡張アカウントマネージャ取得する    
         WEB3GentradeAccountManager l_web3GentradeAccountManager =
             (WEB3GentradeAccountManager) l_finApp.getAccountManager();
         
         MainAccount l_mainAccount = null;
         SubAccount l_subAccount = null;
         Branch l_branch = null;
         AioOrderUnitRow l_aioOrderUnitRow = null;

         try
         {        
             //Throw NotFoundException
             //証券会社オブジェクト = 拡張アカウントマネージャ.getInstitutiion(引数.証券会社コード) 
             Institution l_institution = l_web3GentradeAccountManager.getInstitution(
                 l_strInstitutionCode);
             log.debug("証券会社.get証券会社Code() = " + l_institution.getInstitutionCode());
             
             //部店オブジェクト = 拡張アカウントマネージャ.get部店(証券会社オブジェクト, 引数.部店コード) 
             l_branch = l_web3GentradeAccountManager.getBranch(
                 l_institution, l_strBranchCode);
             log.debug("部店.get部店Code() = " + l_branch.getBranchCode());
             
             //口座（顧客）オブジェクト = 拡張アカウントマネージャ.getMainAccount
             //(証券会社オブジェクト, 部店オブジェクト, 引数.顧客コード) 
             l_mainAccount = l_web3GentradeAccountManager.getMainAccount(
                 l_institution, l_branch, l_strAccountCode);
                 
             log.debug("口座（顧客）オブジェクト.get口座Code = " + l_mainAccount.getAccountId());
             
             //補助口座オブジェクト = 拡張アカウントマネージャ.getSubAccount(口座.口座ID, 引数.補助口座タイプ) 
             l_subAccount = l_web3GentradeAccountManager.getSubAccount(
                 l_mainAccount.getAccountId(), l_subAccountType);
             
             log.debug("補助口座.get補助口座Id() = " + l_subAccount.getSubAccountId());
         }
         catch (NotFoundException l_ex)
         {
             log.error("__NotFoundException__", l_ex);
             //例外をスローする
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 getClass().getName() + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }
         try
         {
             //２）注文単位オブジェクトを取得する。
             List l_lisRows = null;
             String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                 "and branch_id = ? and product_type = ? and order_request_number = ?";
             
             //口座ID = 口座.口座ID
             long l_lngAccountId = l_mainAccount.getAccountId();
             
             //補助口座ID = 補助口座.補助口座ID
             long l_lngSubAccountId = l_subAccount.getSubAccountId();
             
             //部店ID = 部店.部店ID
             long l_lngBranchId = l_branch.getBranchId();           
             
             log.debug("口座ID  = " + l_lngAccountId);
             log.debug("補助口座ID  = " + l_lngSubAccountId);
             log.debug("部店ID  = " + l_lngBranchId);
             log.debug("銘柄タイプ  = " + ProductTypeEnum.CASH.intValue());
             log.debug("識別コード  = " + l_strOrderRequestNumber);
             
             Object l_bindVars[] = {
                 new Long(l_lngAccountId),
                 new Long(l_lngSubAccountId),
                 new Long(l_lngBranchId),
                 ProductTypeEnum.CASH, 
                 l_strOrderRequestNumber };
             
             l_lisRows =
                 Processors.getDefaultProcessor().doFindAllQuery(
                     AioOrderUnitRow.TYPE,
                     l_strWhereClause,
                     null,
                     l_bindVars);
             
             //該当行が存在した場合、複数行一致した場合は例外をスローする。
             if (l_lisRows.size() > 1)
             {                
                 log.debug("該当する注文情報が複数見つかりました。");
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00739,
                     this.getClass().getName() + "." + STR_METHOD_NAME,
                     "該当する注文情報が複数見つかりました。size = " + l_lisRows.size());
             }
             else if (l_lisRows.size() == 1)
             {
                 //注文単位オブジェクトを取得する。
                 l_aioOrderUnitRow = (AioOrderUnitRow)l_lisRows.get(0);            
                 log.debug("注文単位Row : " + l_aioOrderUnitRow);
     
                 log.exiting(STR_METHOD_NAME);
             }
             else 
             {
                 log.debug("データ不整合エラー");
                 //例外をスローする
                 throw new NotFoundException("注文単位オブジェクトを取得できない");
             }
             return l_aioOrderUnitRow;
         }
         catch (DataQueryException l_ex)
         {
             log.error("__DataQueryException__", l_ex);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }
         catch (DataNetworkException l_ex)
         {
             log.error("__DataNetworkException__", l_ex);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }
     }
}
@
