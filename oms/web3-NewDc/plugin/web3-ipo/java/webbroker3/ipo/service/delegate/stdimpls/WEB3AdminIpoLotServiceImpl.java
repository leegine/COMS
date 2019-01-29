head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選割当サービスImpl(WEB3AdminIpoLotServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 呉　@鈞(中訊) 新規作成
                   2006/01/16 呉　@鈞(北京中訊) 仕様変更・モデル115
                   2006/01/18 沈　@迪(北京中訊) 仕様変更・モデル116、117
                   2006/01/31 佐藤(SCS) 仕様変更・モデル119、120                   
Revesion History : 2009/02/16 王志葵 (中訊) 仕様変更モデルNo.179
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3LotStatusDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ipo.WEB3IpoOrderValidator;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.WEB3IpoProductManagerImpl;
import webbroker3.ipo.data.IpoBookbuildingParams;
import webbroker3.ipo.data.IpoBookbuildingRow;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductDao;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3IPOBookBuldingProcDef;
import webbroker3.ipo.define.WEB3IPOBookBuldingStatusDef;
import webbroker3.ipo.define.WEB3IpoDisplayDivDef;
import webbroker3.ipo.define.WEB3IpoTransactionStatusDef;
import webbroker3.ipo.define.WEB3IpoTransactionStatusTypeDef;
import webbroker3.ipo.define.WEB3LotDivDef;
import webbroker3.ipo.define.WEB3LotResultDivDef;
import webbroker3.ipo.message.WEB3AdminIPOLotCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotInputResponse;
import webbroker3.ipo.message.WEB3IPOLotDetailUnit;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者IPO抽選割当サービスImpl)<BR>
 * 管理者IPO抽選割当サービス実装クラス。
 * 
 * @@author 呉　@鈞(中訊)
 * @@version 1.0
 */
public class WEB3AdminIpoLotServiceImpl implements WEB3AdminIpoLotService
{ 
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotServiceImpl.class);
    
    /**
     * @@roseuid 4112EEE30395
     */
    public WEB3AdminIpoLotServiceImpl() 
    {
     
    }
    
    /**
     * 管理者IPO抽選抽選割当処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者IPO抽選割当入力ﾘｸｴｽﾄの場合<BR>
     * 　@－get抽選割当入力画面()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者IPO抽選割当確認ﾘｸｴｽﾄの場合<BR>
     * 　@－get抽選割当確認()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者IPO抽選割当完了ﾘｸｴｽﾄの場合<BR>
     * 　@－get抽選割当完了()をコールする。<BR>
     * <BR>
     * ※サービスメソッドにて例外が発生した場合は、<BR>
     * 　@例外オブジェクトの追加文字列（WEB3BaseException.errorMessage）を<BR>
     * 　@レスポンスデータ.errorMessageにセットする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40C666B003B6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        if (l_request instanceof WEB3AdminIPOLotInputRequest)
        {
            WEB3AdminIPOLotInputResponse l_response = new WEB3AdminIPOLotInputResponse();
            try
            {
                l_response = this.getLotInput((WEB3AdminIPOLotInputRequest) l_request);
            }
            catch(WEB3BaseException l_ex)
            {
                log.error("get抽選割当入力画面()に例外が発生しました", l_ex);
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        else if (l_request instanceof WEB3AdminIPOLotConfirmRequest)
        {
            WEB3AdminIPOLotConfirmResponse l_response = new WEB3AdminIPOLotConfirmResponse();
            try
            {
                l_response = this.getLotConfirm((WEB3AdminIPOLotConfirmRequest) l_request);
            }
            catch(WEB3BaseException l_ex)
            {
                log.error("get抽選割当確認()に例外が発生しました", l_ex);
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        else if (l_request instanceof WEB3AdminIPOLotCompleteRequest)
        {
            WEB3AdminIPOLotCompleteResponse l_response = new WEB3AdminIPOLotCompleteResponse();
            try
            {
                l_response = this.getLotComplete((WEB3AdminIPOLotCompleteRequest) l_request);
            }
            catch(WEB3BaseException l_ex)
            {
                log.error("get抽選割当完了()に例外が発生しました", l_ex);
                l_response.errorInfo = l_ex.getErrorInfo();
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        else 
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
    }
    
    /**
     * (get抽選割当入力)<BR>
     * IPO管理者IPO抽選割当入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・IPO抽選割当）get抽選割当入力」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄管理リクエストデータオブジェクト
     * @@return WEB3AdminIPOLotInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 40C6677200B8
     */
    protected WEB3AdminIPOLotInputResponse getLotInput(WEB3AdminIPOLotInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLotInput(WEB3AdminIPOLotInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3 getInstanceFromログイン情報
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE, true);
        
        //1.5  IPO銘柄(long)
        WEB3IpoProductImpl l_ipoProduct = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3IpoProductManagerImpl l_ipoProductManager = 
            (WEB3IpoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        try
        {
            l_ipoProduct = (WEB3IpoProductImpl) (l_ipoProductManager.getProduct(Long.parseLong(l_request.id))); 
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }
        
        //1.6.1 validate抽選銘柄(IPO銘柄)
        this.validateLotProduct(l_ipoProduct);
        
        //1.6.2 get抽選区分(IPO銘柄)
        String l_strLotDiv = this.getLotDiv(l_ipoProduct);
        
        //1.7 get株式銘柄コード
//        String l_strIpoProductCode = l_ipoProduct.getIpoProductCode();
        String l_strIpoProductCode = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProductCode();
        
        //1.8  get銘柄名
        String l_strStandardName = l_ipoProduct.getStandardName();
        
        //1.9 get割当可能数量
        long l_lngAllotQuantity = this.getAllotAbleQuantity(l_request, l_ipoProduct, l_strLotDiv);
        
        //1.10 createResponse( )
        WEB3AdminIPOLotInputResponse l_response = (WEB3AdminIPOLotInputResponse) l_request.createResponse();
        
        //銘柄コード
        l_response.productCode = l_strIpoProductCode;
        
        //銘柄名
        l_response.productName = l_strStandardName;
        
        //抽選区分
        l_response.lotDiv = l_strLotDiv;
        
        //割当可能数量
        l_response.allotAbleQuantity = String.valueOf(l_lngAllotQuantity);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get抽選割当確認)<BR>
     * IPO管理者IPO抽選割当待ち or 確認画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・IPO抽選割当）get抽選割当確認」参照。<BR>
     *  ========================================================<BR>
     * シーケンス図IPO（管理者・IPO抽選割当）get抽選割当確認」<BR>
     *  1.10.1.リクエストオブジェクト.部店コード[] = null の場合、<BR>
     *          例外をスローする。<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_01429<BR> 
     *  1.10.3.1.is重複抽選処理の結果がtrueの場合、例外をスロー<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02318<BR> 
     *  1.10.5.抽選区分 = "1：新規抽選"の場合、以下の処理を実行し、<BR>
     *          結果がfalseの場合、例外をスロー。<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02309<BR> 
     *  1.11.1.1.get最新抽選レコード（）の戻り値.length　@=　@0　@の場合、<BR>
     *          例外をスロー<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02308<BR> 
     *  ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄詳細リクエストデータオブジェクト
     * @@return WEB3AdminIPOLotConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40C6677C01F0
     */
    protected WEB3AdminIPOLotConfirmResponse getLotConfirm(
        WEB3AdminIPOLotConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLotConfirm(WEB3AdminIPOLotConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE, true);
        
        //1.4 validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5 get証券会社コード
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.6 IPO銘柄(long)
        WEB3IpoProductImpl l_ipoProduct = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3IpoProductManagerImpl l_ipoProductManager = 
            (WEB3IpoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        try
        {
            l_ipoProduct = (WEB3IpoProductImpl) (l_ipoProductManager.getProduct(Long.parseLong(l_request.id))); 
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }
        
        //1.7.1 validate抽選銘柄(IPO銘柄)
        this.validateLotProduct(l_ipoProduct);
        
        //1.7.2 get抽選区分(IPO銘柄)
        String l_strLotDiv = this.getLotDiv(l_ipoProduct);
        
        //1.8 get株式銘柄コード
//      String l_strIpoProductCode = l_ipoProduct.getIpoProductCode();
        String l_strIpoProductCode = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProductCode();
        
        //1.9 get銘柄名
        String l_strStandardName = l_ipoProduct.getStandardName();
        
        IpoBookbuildingRow[] l_ipoBookbuildingRows = null;
        IpoBookbuildingRow[] l_newIpoBookbuildingRows = null;
        IpoBookbuildingRow[] l_ipoBookbuildings = null;
        String l_strTransactionStateType = null;
        //1.10 画面区分が"１：登録"の場合、以下の処理を実行する。
        if (WEB3IpoDisplayDivDef.LOGIN.equals(l_request.displayDiv))
        {
            //1.10.1 メッセージ リクエストオブジェクト.部店コード[] = null の場合、例外をスローする。
            if (l_request.branchCode == null || l_request.branchCode.length == 0)
            {
                log.debug("部店コード一覧が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01429, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コード一覧が未指定です。");
            }
            
            //1.10.2 リクエストオブジェクト.部店コード[]の要素分Loop処理をおこない、部店IDListを生成する。
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            int l_intBranchCodeLength = l_request.branchCode.length;
            List l_lisBranchIds = new ArrayList();
            for (int i = 0; i < l_intBranchCodeLength; i++)
            {
                //1.10.2.1 get部店(証券会社コード : String, 部店コード : String)
                Branch l_branch = null;
                try
                {
                    l_branch = l_accountManager.getWeb3GenBranch(l_strInstitutionCode, l_request.branchCode[i]);
                }
                catch (NotFoundException l_nfe)
                {
                    log.error("テーブルに該当するデータがありません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        l_nfe.getMessage(), 
                        l_nfe);
                }
                
                //1.10.2.2 getBranchId()
                long l_lngBranchId = l_branch.getBranchId();
                
                //1.10.2.3 部店IDリストに.addする。
                l_lisBranchIds.add(new Long(l_lngBranchId));
            }
            
            // validate異常終了(String, String)
            this.validateAbnormalEnd(l_request.id, l_strLotDiv);
            
            //1.10.3 is重複抽選処理(String, String)
            boolean l_blnIsDuplicateLotTransaction = this.isDuplicateLotTransaction(l_request.id, l_strLotDiv);
            
            //1.10.3.1  is重複抽選処理の結果がtrueの場合、例外をスロー。
            if (l_blnIsDuplicateLotTransaction)
            {
                log.debug("抽選処理実行中は、再抽選できません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02318, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "抽選処理実行中は、再抽選できません。");
            }
            
            //1.10.4 validate抽選妥当性(String, String, List)
            this.validateLotValidity(l_request.id, l_strLotDiv, l_lisBranchIds);
            
            //1.10.5 抽選区分 = "1：新規抽選"の場合、以下の処理を実行し、結果がfalseの場合、例外をスロー。
            if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
            {
                //1.10.5.1 is抽選確定(String, String, List)
                boolean l_blnIsLotConfirm = this.isLotConfirm(l_request.id, l_strLotDiv, l_lisBranchIds);
                
                if (!l_blnIsLotConfirm)
                {
                    log.debug("抽選処理は結果確定中、もしくは結果確定が終了しています。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02309, 
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "抽選処理は結果確定中、もしくは結果確定が終了しています。");
                }
            }
            
            //1.10.6 validate割当入力項目(管理者IPO抽選割当確認リクエスト, String, IPO銘柄)
            this.validateAllotInputItem(l_request, l_strLotDiv, l_ipoProduct);
                        
            //1.10.7 get抽選シーケンス(String, String)
            long l_lngBbSeq = this.getBbSeq(l_request.id, l_strLotDiv);
            
            //1.10.8 get入力項目Row(管理者IPO抽選割当確認リクエスト, String, long, List)
            l_ipoBookbuildingRows = this.getInputItemRow(
                l_request, l_strLotDiv, l_lngBbSeq, l_lisBranchIds);
            
            //1.10.9 getDefaultProcessor( )
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor(); 
                
                //1.10.10 get入力項目Row(管理者IPO抽選割当入力リクエスト, String, long, List)
                int l_intLength = l_ipoBookbuildingRows.length;
                for (int i = 0; i < l_intLength; i++)
                {
                    //1.10.10.1 doInsertQuery(arg0 : Row)
                    l_processor.doInsertQuery(l_ipoBookbuildingRows[i]);
                }
            }
            catch (DataQueryException l_dqe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dqe.getMessage(), 
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error("DBへのアクセスに失敗しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dne.getMessage(), 
                    l_dne);
            }
            l_ipoBookbuildings = l_ipoBookbuildingRows;
            
            l_strTransactionStateType = WEB3IpoTransactionStatusTypeDef.WAITING;
        }
        
        //1.11 画面区分が"2：参照"の場合、以下の処理を実行する。
        else if (WEB3IpoDisplayDivDef.REFERENCE.equals(l_request.displayDiv))
        {
            //1.11.1 get最新抽選レコード(String, String)
            l_newIpoBookbuildingRows = this.getNewLotRecord(l_request.id, l_strLotDiv);
            
            //1.11.1.1 get最新抽選レコード（）の戻り値.length　@=　@0　@の場合、例外をスロー
            if (l_newIpoBookbuildingRows.length == 0)
            {
                log.debug("抽選レコードが存在しません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02308, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "抽選レコードが存在しません。");
            }
            
            //1.11.2.1 get最新抽選レコード()の戻り値から1件目を取得する
            IpoBookbuildingRow l_ipoBookbuildingRow = l_newIpoBookbuildingRows[0];
            
            //1.11.3 validate抽選状況(IPO抽選Row)
            this.validateLotState(l_ipoBookbuildingRow);
            
            //1.11.4 get処理状況区分(IPO抽選Row)
            l_strTransactionStateType = this.getTransactionStateType(l_ipoBookbuildingRow);
            
            l_ipoBookbuildings = l_newIpoBookbuildingRows;
        }
        
        //1.12 create抽選割当詳細(IPO抽選Row[])
        WEB3IPOLotDetailUnit[] l_ipoLotDetailUnit = this.createLotDetail(l_ipoBookbuildings);
        
        //1.13 createResponse( )
        WEB3AdminIPOLotConfirmResponse l_response = (WEB3AdminIPOLotConfirmResponse) l_request.createResponse();
        
        //銘柄コード
        l_response.productCode = l_strIpoProductCode;
        
        //銘柄名 
        l_response.productName = l_strStandardName;
        
        //抽選区分
        l_response.lotDiv = l_strLotDiv;
        
        //処理状況区分
        l_response.transactionStateType = l_strTransactionStateType;

        //抽選割当詳細
        l_response.lotDetail = l_ipoLotDetailUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get抽選割当完了)<BR>
     * IPO管理者IPO抽選割当完了状況画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・IPO抽選割当）get抽選割当完了」参照。<BR>
     *  ========================================================<BR>
     * シーケンス図「IPO（管理者・IPO抽選割当）get抽選割当完了」<BR>
     *  1.10.4.1.is重複抽選処理の結果がtrueの場合、例外をスロー<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02318<BR> 
     *  1.10.6.抽選区分 = "1：新規抽選"の場合、以下の処理を実行し、<BR>
     *          結果がfalseの場合、例外をスロー。<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02309<BR> 
     *  1.11.1.1.get完了状況レコードの戻り値.length = 0 の場合、<BR>
     *          例外をスローする。<BR>
     *  class: WEB3BusinessLayerException<BR>                           
     *  tag:   BUSINESS_ERROR_02323<BR>
     *  ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO銘柄詳細リクエストデータオブジェクト
     * @@return WEB3AdminIPOLotCompleteResponse
     * @@roseuid 40C6677C01F0
     * @@throws WEB3BaseException
     */
    protected WEB3AdminIPOLotCompleteResponse getLotComplete(
        WEB3AdminIPOLotCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLotComplete(WEB3AdminIPOLotCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_IPO_PROD_OPE, true);
        
        //1.4 validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 IPO銘柄(long)
        WEB3IpoProductImpl l_ipoProduct = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3IpoProductManagerImpl l_ipoProductManager = 
            (WEB3IpoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager();
        try
        {
            l_ipoProduct = (WEB3IpoProductImpl) (l_ipoProductManager.getProduct(Long.parseLong(l_request.id))); 
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }
        
        //1.6.1 validate抽選銘柄(IPO銘柄)
        this.validateLotProduct(l_ipoProduct);
        
        //1.6.2 get抽選区分(IPO銘柄)
        String l_strLotDiv = this.getLotDiv(l_ipoProduct);
        
        //1.7 get株式銘柄コード
//        String l_strIpoProductCode = l_ipoProduct.getIpoProductCode();
        String l_strIpoProductCode = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProductCode();
        
        //1.8 get銘柄名
        String l_strStandardName = l_ipoProduct.getStandardName();
        
        IpoBookbuildingRow[] l_ipoBookbuildingRows = null;
        IpoBookbuildingRow[] l_ipoBookbuildingRowCompleteds = null;
        IpoBookbuildingRow[] l_ipoBookbuildings = null;
        String l_strTransactionStateType = null;
        //1.9 画面区分が"１：登録"の場合、以下の処理を実行する。
        if (WEB3IpoDisplayDivDef.LOGIN.equals(l_request.displayDiv))
        {
            //1.9.1 validate取引パスワード(パスワード : String)
            l_administrator.validateTradingPassword(l_request.password);
            
            //1.9.2 get抽選確定部店ID(String, String, long)
            if (l_request.lotSequence == null)
            {
                l_request.lotSequence = "0";
            }
            List l_lisBranchIds = this.getLotConfirmBranchId(
                l_request.id, l_strLotDiv, Long.parseLong(l_request.lotSequence));
            
            //1.9.3  GtlUtils.getSystemTimestamp();で、現在日付を取得する。
            Timestamp l_tsTimestamp = GtlUtils.getSystemTimestamp();
            
            // validate異常終了(String, String)
            this.validateAbnormalEnd(l_request.id, l_strLotDiv);
            
            //1.9.4 is重複抽選処理(String, String)
            boolean l_blnIsDuplicateLotTransaction = this.isDuplicateLotTransaction(l_request.id, l_strLotDiv);
            
            //1.9.4.1 is重複抽選処理の結果がtrueの場合、例外をスロー
            if (l_blnIsDuplicateLotTransaction)
            {
                log.debug("抽選処理実行中は、再抽選できません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02318, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "抽選処理実行中は、再抽選できません。");
            }
            
            //1.9.5 validate抽選妥当性(String, String, List)
            this.validateLotValidity(l_request.id, l_strLotDiv, l_lisBranchIds);
            
            //1.9.6 抽選区分 = "1：新規抽選"の場合、以下の処理を実行し、結果がfalseの場合、例外をスロー。
            if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
            {
                //1.9.6.1 is抽選確定(String, String, List)
                boolean l_blnIsLotConfirm = this.isLotConfirm(l_request.id, l_strLotDiv, l_lisBranchIds);
                
                if (!l_blnIsLotConfirm)
                {
                    log.debug("抽選処理は結果確定中、もしくは結果確定が終了しています。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02309, 
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "抽選処理は結果確定中、もしくは結果確定が終了しています。");
                }
            }
            
            //1.9.7 save抽選確定(String, String, long, List, Timestamp)
            this.saveLotConfirm(
                l_request.id, l_strLotDiv, Long.parseLong(l_request.lotSequence), l_lisBranchIds, l_tsTimestamp);
            
            //1.9.8 get完了状況レコード(String, String, String)
            l_ipoBookbuildingRows = this.getCompleteStateRecord(
                l_request.id, l_strLotDiv, l_request.lotSequence);
            
            //1.9.9 cloneIPO抽選Rows(IPO抽選Row［］)
            IpoBookbuildingRow[] l_cloneIpoBookBuildingRows = this.cloneIpoBookBuildingRows(l_ipoBookbuildingRows);
            
            //1.9.10 cloneIPO抽選Rowsで取得した値の要素分、
            //Loop処理をおこない、IPO抽選Rowの値を編集する。
            int l_intLength = l_cloneIpoBookBuildingRows.length;
            for (int i = 0; i < l_intLength; i++)
            {
                ((IpoBookbuildingParams) l_cloneIpoBookBuildingRows[i]).setProcess(WEB3IPOBookBuldingProcDef.CONFIRM_INPUT);
                ((IpoBookbuildingParams) l_cloneIpoBookBuildingRows[i]).setStatus(WEB3IPOBookBuldingStatusDef.DEALING);
                ((IpoBookbuildingParams) l_cloneIpoBookBuildingRows[i]).setLastUpdatedTimestamp(l_tsTimestamp);
            }
            l_ipoBookbuildings = l_cloneIpoBookBuildingRows;
            
            l_strTransactionStateType = WEB3IpoTransactionStatusTypeDef.WAITING;
        }
        //1.10 画面区分が"2：参照"の場合、以下の処理を実行する。
        else if (WEB3IpoDisplayDivDef.REFERENCE.equals(l_request.displayDiv))
        {
            //1.10.1 get完了状況レコード(String, String, String)
            l_ipoBookbuildingRowCompleteds = this.getCompleteStateRecord(
                l_request.id, l_strLotDiv, l_request.lotSequence);
            
            //1.10.1.1 get完了状況レコードの戻り値.length = 0 の場合、例外をスローする。
            if (l_ipoBookbuildingRowCompleteds.length == 0)
            {
                log.debug("確定処理をおこなう情報がありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02323, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "確定処理をおこなう情報がありません。");
            }
            
            //1.10.1.2 get完了状況レコード（）の戻り値から、最後尾の1件を取得する。
            IpoBookbuildingRow l_ipoBookbuildingRow = 
                l_ipoBookbuildingRowCompleteds[l_ipoBookbuildingRowCompleteds.length - 1];
            
            //1.10.2 validate確定状況(IPO抽選Row)
            this.validateConfirmState(l_ipoBookbuildingRow);
            
            //1.10.3 get処理状況区分(IPO抽選Row)
            l_strTransactionStateType = this.getTransactionStateType(l_ipoBookbuildingRow);
            
            l_ipoBookbuildings = l_ipoBookbuildingRowCompleteds;
        }
        
        //1.11 create抽選割当詳細(IPO抽選Row[])
        WEB3IPOLotDetailUnit[] l_ipoLotDetailUnit = this.createLotDetail(l_ipoBookbuildings);
        
        //1.12 createResponse( )
        WEB3AdminIPOLotCompleteResponse l_response = (WEB3AdminIPOLotCompleteResponse) l_request.createResponse();

        //銘柄コード
        l_response.productCode = l_strIpoProductCode;
        
        //銘柄名 
        l_response.productName = l_strStandardName;
        
        //抽選区分
        l_response.lotDiv = l_strLotDiv;
        
        //処理状況区分
        l_response.transactionStateType = l_strTransactionStateType;

        //抽選割当詳細
        l_response.lotDetail = l_ipoLotDetailUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
     
    /**
     * (create抽選割当詳細)
     * 抽選割当詳細の一覧を返却する。<BR>
     * <BR>
     * １）　@ArrayListオブジェクトの生成。<BR>
     * <BR>
     * ２）　@引数.IPO抽選Row[]の要素分、Loop処理をおこなう。<BR>
     * <BR>
     * 　@２－１）　@管理者IPO抽選割当詳細クラスのオブジェクトを生成する。<BR>
     * <BR>
     * 　@２－２）　@２－１）で生成したオブジェクトに以下の内容をセットする。<BR>
     * 　@　@　@・　@IPO抽選割当詳細オブジェクト.抽選シーケンス =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].抽選シーケンス<BR>
     * <BR>
     * 　@　@　@・　@IPO抽選割当詳細クラスオブジェクト.部店コード =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].部店IDから部店コードを取得。<BR>
     * <BR>
     * 　@　@　@・　@IPO抽選割当詳細クラスオブジェクト.抽選割当総枠数量 =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].抽選割当総枠数量<BR>
     * <BR>
     * 　@　@　@・　@IPO抽選割当詳細クラスオブジェクト.割当上限数量 =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].割当上限数量<BR>
     * <BR>
     * 　@　@　@・　@IPO抽選割当詳細クラスオブジェクト.割当済み数量 =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].割当済み数量<BR>
     * <BR>
     * 　@　@　@・　@IPO抽選割当詳細クラスオブジェクト.割当対象顧客数 =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].割当対象顧客数<BR>
     * <BR>
     * 　@　@　@・　@IPO抽選割当詳細クラスオブジェクト.割当最大値 =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].割当最大値<BR>
     * <BR>
     * 　@　@　@・　@IPO抽選割当詳細クラスオブジェクト.割当最小値 =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].割当最小値<BR>
     * <BR>
     * 　@　@　@・　@IPO抽選割当詳細クラスオブジェクト.補欠割当総枠数量 =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].補欠割当総枠数量<BR>
     * <BR>
     * 　@　@　@・　@IPO抽選割当詳細クラスオブジェクト.補欠割当上限数量 =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].補欠割当上限数量<BR>
     * <BR>
     * 　@　@　@・　@IPO抽選割当詳細クラスオブジェクト.補欠割当済み数量 =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].補欠割当済み数量<BR>
     * <BR>
     * 　@　@　@・　@IPO抽選割当詳細クラスオブジェクト.補欠割当対象顧客数 =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].補欠割当対象顧客数<BR>
     * <BR>
     * 　@　@　@・　@IPO抽選割当詳細クラスオブジェクト.補欠割当最大値 =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].補欠割当最大値<BR>
     * <BR>
     * 　@　@　@・　@IPO抽選割当詳細オブジェクト.補欠割当最小値 =<BR>
     * 　@　@　@　@　@　@　@引数:IPO抽選Row[ i ].補欠割当最小値<BR>
     * <BR>
     * <BR>
     * 　@２－２）　@IPO抽選詳細オブジェクト.処理日時 = IPO抽選Row.更新日時<BR>
     * <BR>
     * 　@２－３）　@以下の条件でIPO抽選詳細オブジェクト.処理状態をセットする。<BR>
     * 　@　@　@　@　@　@＊１　@ IPO抽選Row.処理状況　@=<BR>
     * 　@　@　@                 "11：確定入力" or "12：デーモン受付" or<BR>
     * 　@　@　@                 "13：確定開始"かつ<BR>
     * 　@　@　@                 IPO抽選Row.ステータス　@=　@"1：処理中"<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@IPO抽選詳細オブジェクト.処理状態 = "1：確定処理中"<BR>
     * <BR>
     *              ＊2　@ IPO抽選Row.処理状況　@=<BR>
     * 　@　@　@                 "11：確定入力" or "12：デーモン受付" or<BR>
     * 　@　@　@                 "13：確定開始"かつ<BR>
     * 　@　@　@                 IPO抽選Row.ステータス　@=　@"9：異常終了"<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@IPO抽選詳細オブジェクト.処理状態 = "9：異常終了"<BR>
     * <BR>
     *               ＊3　@ IPO抽選Row.処理状況　@=　@"14：確定終了"　@かつ<BR>
     * 　@　@　@                 IPO抽選Row.ステータス　@=　@"0：正常終了"<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@IPO抽選詳細オブジェクト.処理状態 = "2：確定完了"<BR>
     * <BR>
     *                ＊１、２、３以外は<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@IPO抽選詳細オブジェクト.処理状態 = null<BR>
     * <BR>
     * 　@２－４）　@ArrayListオブジェクト.add(IPO抽選割当詳細オブジェクト)<BR>
     * <BR>
     * ３）　@ArrayListオブジェクトをIPO抽選割当詳細[]に変換する。<BR>
     * <BR>
     * ４）　@３）で変換したIPO抽選割当詳細[]を返却する。<BR>
     * @@param  - (IPO抽選Row[])<BR>
     * IPO抽選Row[]オブジェクト
     * @@return WEB3IPOLotDetailUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3IPOLotDetailUnit[] createLotDetail(
        IpoBookbuildingRow[] l_ipoBookbuildingRows) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createLotDetail(IpoBookbuildingRow[])";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ArrayListオブジェクトの生成。
        List l_lisIPOLotDetailUnits = new ArrayList();
        
        //２）　@引数.IPO抽選Row[]の要素分、Loop処理をおこなう。
        int l_intLength = l_ipoBookbuildingRows.length;
        for (int i = 0; i < l_intLength; i++)
        {
            //２－１）　@管理者IPO抽選割当詳細クラスのオブジェクトを生成する。
            WEB3IPOLotDetailUnit l_iPOLotDetailUnit = new WEB3IPOLotDetailUnit();

            //IPO抽選割当詳細オブジェクト.抽選シーケンス =
            //引数:IPO抽選Row[ i ].抽選シーケンス
            l_iPOLotDetailUnit.lotSequence = String.valueOf(l_ipoBookbuildingRows[i].getBbSeq());
            
            //IPO抽選割当詳細クラスオブジェクト.部店コード =
            //引数:IPO抽選Row[ i ].部店IDから部店コードを取得。
            String l_strBranchCode = null;
            try
            {
                l_strBranchCode = 
                    BranchDao.findRowByPk(l_ipoBookbuildingRows[i].getBranchId()).getBranchCode();
            }
            catch (DataFindException l_dfe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dfe.getMessage(), 
                    l_dfe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dqe.getMessage(), 
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error("DBへのアクセスに失敗しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dne.getMessage(), 
                    l_dne);
            } 
            l_iPOLotDetailUnit.branchCode = l_strBranchCode;
            
            //IPO抽選割当詳細クラスオブジェクト.抽選割当総枠数量 =
            //引数:IPO抽選Row[ i ].抽選割当総枠数量
            l_iPOLotDetailUnit.lotAllotTotalQuantity = String.valueOf(l_ipoBookbuildingRows[i].getBbQuantityAll());
            
            //IPO抽選割当詳細クラスオブジェクト.割当上限数量 =
            //引数:IPO抽選Row[ i ].割当上限数量
            l_iPOLotDetailUnit.allotLimitQuantity = String.valueOf(l_ipoBookbuildingRows[i].getBbQuantityLoop());
            
            //IPO抽選割当詳細クラスオブジェクト.割当済み数量 =
            //引数:IPO抽選Row[ i ].割当済み数量
            if (l_ipoBookbuildingRows[i].getBbResultQuantitySumIsNull())
            {
                l_iPOLotDetailUnit.allotFinLimitQuantity = null;
            }
            else 
            {
                l_iPOLotDetailUnit.allotFinLimitQuantity = String.valueOf(
                    l_ipoBookbuildingRows[i].getBbResultQuantitySum());
            }
            
            //IPO抽選割当詳細クラスオブジェクト.割当対象顧客数 =
            //引数:IPO抽選Row[ i ].割当対象顧客数
            if (l_ipoBookbuildingRows[i].getBbResultAccCountIsNull())
            {
                l_iPOLotDetailUnit.allotTargetNumber = null;
            }
            else
            {
                l_iPOLotDetailUnit.allotTargetNumber = String.valueOf(l_ipoBookbuildingRows[i].getBbResultAccCount());
            }
            
            //IPO抽選割当詳細クラスオブジェクト.割当最大値 =
            //引数:IPO抽選Row[ i ].割当最大値
            if (l_ipoBookbuildingRows[i].getBbResultQuantityMaxIsNull())
            {
                l_iPOLotDetailUnit.allotMax = null;
            }
            else 
            {
                l_iPOLotDetailUnit.allotMax = String.valueOf(l_ipoBookbuildingRows[i].getBbResultQuantityMax());
            }
            
            //IPO抽選割当詳細クラスオブジェクト.割当最小値 =
            //引数:IPO抽選Row[ i ].割当最小値
            if (l_ipoBookbuildingRows[i].getBbResultQuantityMinIsNull())
            {
                l_iPOLotDetailUnit.allotMin = null;
            }
            else
            {
                l_iPOLotDetailUnit.allotMin = String.valueOf(l_ipoBookbuildingRows[i].getBbResultQuantityMin());
            }
            
            //IPO抽選割当詳細クラスオブジェクト.補欠割当総枠数量 =
            //引数:IPO抽選Row[ i ].補欠割当総枠数量
            if (l_ipoBookbuildingRows[i].getSubBbQuantityAllIsNull())
            {
                l_iPOLotDetailUnit.waitingAllotTotalQuantity = null;
            }
            else
            {
                l_iPOLotDetailUnit.waitingAllotTotalQuantity = 
                    String.valueOf(l_ipoBookbuildingRows[i].getSubBbQuantityAll());
            }
             
            //IPO抽選割当詳細クラスオブジェクト.補欠割当上限数量 =
            //引数:IPO抽選Row[ i ].補欠割当上限数量
            if (l_ipoBookbuildingRows[i].getSubBbQuantityLoopIsNull())
            {
                l_iPOLotDetailUnit.waitingAllotLimitQuantity = null;
            }
            else
            {
                l_iPOLotDetailUnit.waitingAllotLimitQuantity = 
                    String.valueOf(l_ipoBookbuildingRows[i].getSubBbQuantityLoop());
            }
                
            //IPO抽選割当詳細クラスオブジェクト.補欠割当済み数量 =
            //引数:IPO抽選Row[ i ].補欠割当済み数量
            if (l_ipoBookbuildingRows[i].getSubBbResultQuantitySumIsNull())
            {
                l_iPOLotDetailUnit.waitingAllotFinLimitQuantity = null;
            }
            else
            {
                l_iPOLotDetailUnit.waitingAllotFinLimitQuantity = 
                    String.valueOf(l_ipoBookbuildingRows[i].getSubBbResultQuantitySum());
            }
            
            //IPO抽選割当詳細クラスオブジェクト.補欠割当対象顧客数 =
            //引数:IPO抽選Row[ i ].補欠割当対象顧客数
            if (l_ipoBookbuildingRows[i].getSubBbResultAccCountIsNull())
            {
                l_iPOLotDetailUnit.waitingAllotTargetNumber = null;
            }
            else
            {
                l_iPOLotDetailUnit.waitingAllotTargetNumber = 
                    String.valueOf(l_ipoBookbuildingRows[i].getSubBbResultAccCount());
            }
            
            //IPO抽選割当詳細クラスオブジェクト.補欠割当最大値 =
            //引数:IPO抽選Row[ i ].補欠割当最大値
            if (l_ipoBookbuildingRows[i].getSubBbResultQuantityMaxIsNull())
            {
                l_iPOLotDetailUnit.waitingAllotMax = null;
            }
            else
            {
                l_iPOLotDetailUnit.waitingAllotMax = 
                    String.valueOf(l_ipoBookbuildingRows[i].getSubBbResultQuantityMax()); 
            }
            
            //IPO抽選割当詳細オブジェクト.補欠割当最小値 =
            //引数:IPO抽選Row[ i ].補欠割当最小値
            if (l_ipoBookbuildingRows[i].getSubBbResultQuantityMinIsNull())
            {
                l_iPOLotDetailUnit.waitingAllotMin = null;
            }
            else
            {
                l_iPOLotDetailUnit.waitingAllotMin = 
                    String.valueOf(l_ipoBookbuildingRows[i].getSubBbResultQuantityMin());
            }
            
            //２－２）　@IPO抽選詳細オブジェクト.処理日時 = IPO抽選Row.更新日時
            l_iPOLotDetailUnit.transactionDate = l_ipoBookbuildingRows[i].getLastUpdatedTimestamp();
            
            //２－３）　@以下の条件でIPO抽選詳細オブジェクト.処理状態をセットする。
            String l_strTransactionState = null;

            //IPO抽選Row.処理状況　@=
            //"11：確定入力" or "12：デーモン受付" or
            // "13：確定開始"かつ
            // IPO抽選Row.ステータス　@=　@"1：処理中"
            //IPO抽選詳細オブジェクト.処理状態 = "1：確定処理中"
            String l_strProcess = l_ipoBookbuildingRows[i].getProcess();
            String l_strStatus = l_ipoBookbuildingRows[i].getStatus();
            if ((WEB3IPOBookBuldingProcDef.CONFIRM_INPUT.equals(l_strProcess) || 
                WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT.equals(l_strProcess) ||
                WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN.equals(l_strProcess)) &&
                (WEB3IPOBookBuldingStatusDef.DEALING.equals(l_strStatus)))
            {
                l_strTransactionState = WEB3IpoTransactionStatusDef.DEALING;
            }
            
            //IPO抽選Row.処理状況　@=
            //"11：確定入力" or "12：デーモン受付" or
            //"13：確定開始"かつ
            //IPO抽選Row.ステータス　@=　@"9：異常終了"
            //IPO抽選詳細オブジェクト.処理状態 = "9：異常終了"
            if ((WEB3IPOBookBuldingProcDef.CONFIRM_INPUT.equals(l_strProcess) || 
                WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT.equals(l_strProcess) ||
                WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN.equals(l_strProcess)) &&
                (WEB3IPOBookBuldingStatusDef.ABNORMAL_END.equals(l_strStatus)))
            {
                l_strTransactionState = WEB3IpoTransactionStatusDef.ERROR;
            }
            
            //IPO抽選Row.処理状況　@=　@"14：確定終了"　@かつ
            //IPO抽選Row.ステータス　@=　@"0：正常終了"
            //IPO抽選詳細オブジェクト.処理状態 = "2：確定完了"
            if ((WEB3IPOBookBuldingProcDef.CONFIRM_END.equals(l_strProcess)) &&
                (WEB3IPOBookBuldingStatusDef.NORMAL_END.equals(l_strStatus)))
            {
                l_strTransactionState = WEB3IpoTransactionStatusDef.CONFIRM_COMPLETED;
            }
            
            l_iPOLotDetailUnit.transactionState = l_strTransactionState;
            
            //　@２－４）　@ArrayListオブジェクト.add(IPO抽選割当詳細オブジェクト)
            l_lisIPOLotDetailUnits.add(l_iPOLotDetailUnit);
        }
       
        //３）　@ArrayListオブジェクトをIPO抽選割当詳細[]に変換する。
        WEB3IPOLotDetailUnit[] l_iPOLotDetailUnits = new WEB3IPOLotDetailUnit[l_intLength];

        l_lisIPOLotDetailUnits.toArray(l_iPOLotDetailUnits);

        log.exiting(STR_METHOD_NAME);
        //４）　@３）で変換したIPO抽選割当詳細[]を返却する。
        return l_iPOLotDetailUnits;
    }
     
    /**
     * (is重複抽選処理)<BR>
     * 重複抽選処理を判定するメソッド。<BR>
     * <BR>
     * １）　@this.最新抽選レコード（引数:銘柄ID , 引数:抽選区分）で、<BR>
     * 　@　@　@IPO抽選テーブルのレコード行を取得する。<BR>
     * <BR>
     * ２）　@if １）の戻り値:IPO抽選Row[].size() = 0 の場合、falseを返却する。<BR>
     * <BR>
     * ３）　@else　@１）の戻り値:IPO抽選Row[]のサイズ分、Loop処理を行う。<BR>
     * <BR>
     * 　@３－１）　@IPO抽選Row[i]から、処理状況を取得<BR>
     * <BR>
     * 　@３－２）　@IPO抽選Row[i]から、ステータスを取得<BR>
     * <BR>
     * 　@３－３）　@if 処理状況が "04：抽選終了","14：確定終了" 以外で、かつ<BR>
     * 　@　@　@　@　@　@ステータスが"1：処理中"レコードが存在する場合、<BR>
     * <BR>
     * 　@　@　@　@　@　@trueを返却する。<BR>
     * <BR>
     * 　@３－４）　@else　@Loop処理を続ける。<BR>
     * <BR>
     * ４）　@３－３）の条件に1件も合致せずにLoop処理を終えた場合、falseを返却<BR>
     * @@param l_strProductId - 銘柄ID
     * @@param l_strLotDiv - (抽選区分)<BR>
     * 新規/繰上抽選区分。<BR>
     * <BR>
     * 　@１：新規抽選<BR>
     * 　@２：繰上抽選<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isDuplicateLotTransaction(String l_strProductId, String l_strLotDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isDuplicateLotTransaction(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@this.最新抽選レコード（引数:銘柄ID , 引数:抽選区分）で、
        // 　@　@　@IPO抽選テーブルのレコード行を取得する。
        IpoBookbuildingRow[] l_ipoBookbuildingRows = this.getNewLotRecord(l_strProductId, l_strLotDiv);
        
        int l_intLength = l_ipoBookbuildingRows.length;
        IpoBookbuildingRow ipoBookbuildingRow = null;
        boolean l_blnDuplicateLotTransaction = false;
        
        //２）　@if １）の戻り値:IPO抽選Row[].size() = 0 の場合、falseを返却する
        if (l_ipoBookbuildingRows.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //else　@１）の戻り値:IPO抽選Row[]のサイズ分、Loop処理を行う。
        else
        {
            for (int i = 0; i < l_intLength; i++)
            {
                ipoBookbuildingRow = l_ipoBookbuildingRows[i];
                
                //３－１）　@IPO抽選Row[i]から、処理状況を取得
                String l_strProcess = ipoBookbuildingRow.getProcess();
                //３－２）　@IPO抽選Row[i]から、ステータスを取得
                String l_strStatus = ipoBookbuildingRow.getStatus();
                
                //３－３）　@if 処理状況が "04：抽選終了","14：確定終了" 以外で、かつ
                // 　@　@　@　@　@　@ステータスが"1：処理中"レコードが存在する場合、
                // 　@　@　@　@　@　@trueを返却する。
                if ((!WEB3IPOBookBuldingProcDef.LOT_END.equals(l_strProcess) &&
                    !WEB3IPOBookBuldingProcDef.CONFIRM_END.equals(l_strProcess)) && 
                    (WEB3IPOBookBuldingStatusDef.DEALING.equals(l_strStatus)))
                {
                    l_blnDuplicateLotTransaction = true;
                    break;
                }
                else
                {
                    //３－４）else　@Loop処理を続ける。
                    continue;
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        //４）　@３－３）の条件に1件も合致せずにLoop処理を終えた場合、falseを返却
        return l_blnDuplicateLotTransaction;
    }
    
    /**
     * (is抽選確定)<BR>
     * 抽選確定中もしくは、抽選確定が終了しているか判定する。<BR>
     * <BR>
     * １）　@引数:部店IDリストのサイズ分、Loop処理をおこなう。<BR>
     * <BR>
     * 　@１－１）　@部店IDリストオブジェクト.get( i )で部店IDを取得する。<BR>
     * 　@　@　@　@　@　@<BR>
     * 　@１－２）　@doFindAllQuery()以下の検索条件で、<BR>
     * 　@　@　@　@　@　@IPO抽選テーブルのレコードを検索する。<BR>
     * <BR>
     * 　@　@[検索対象テーブル]<BR>
     * 　@　@IPO抽選テーブル<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@銘柄ID = 引数:銘柄ID and<BR>
     * 　@　@部店ID = １－１）で取得した部店ID and <BR>
     * 　@　@抽選区分 = "1：新規抽選" and<BR>
     * 　@　@処理状況 = "14：確定終了" and<BR>
     * 　@　@ステータス　@=　@"0：正常終了"<BR>
     * <BR>
     * 　@１－３）　@検索結果が１件以上存在する場合、falseを返却する。<BR>
     * 　@　@　@　@　@　@検索結果が存在しない場合は、Loop処理を続ける。<BR>
     * <BR>
     * ２）　@Loop処理を終えた場合、trueを返却する。<BR>
     * @@param l_strProductId - (銘柄ID)<BR>
     * @@param l_strLotDiv - (抽選区分)<BR>
     * 新規/繰上抽選区分。<BR>
     * <BR>
     * 　@１：新規抽選<BR>
     * 　@２：繰上抽選<BR>
     * @@param l_lisBranchId - (部店IDリスト)<BR>
     * 部店IDリストオブジェクト。
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isLotConfirm(
        String l_strProductId, String l_strLotDiv, List l_lisBranchIdLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isLotConfirm(String, String, List)";
        log.entering(STR_METHOD_NAME);
        
        //1) 引数:部店IDリストのサイズ分、Loop処理をおこなう。
        long l_lngBranchIdLength = l_lisBranchIdLists.size();
        
        List l_lisRecords = new ArrayList();
        for (int i = 0; i < l_lngBranchIdLength; i++)
        {
            //１－１）　@部店IDリストオブジェクト.get( i )で部店IDを取得する。
            Long l_lngBranchId = (Long) l_lisBranchIdLists.get(i);
            
            //１－２）　@doFindAllQuery()以下の検索条件で、IPO抽選テーブルのレコードを検索する。
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();  

                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" ipo_product_id = ? ");   //銘柄ID
                l_sbWhere.append(" and branch_id = ? ");    //部店ID
                l_sbWhere.append(" and bb_div = ? ");       //抽選区分
                l_sbWhere.append(" and process = ? ");      //処理状況
                l_sbWhere.append(" and status = ? ");       //ステータス

                Object[] l_objWhere = {
                    l_strProductId,
                    l_lngBranchId,
                    WEB3LotDivDef.OPEN_LOTTERY,
                    WEB3IPOBookBuldingProcDef.CONFIRM_END,
                    WEB3IPOBookBuldingStatusDef.NORMAL_END};

                l_lisRecords = l_processor.doFindAllQuery(
                    IpoBookbuildingRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objWhere);
            }
            catch (DataFindException l_dfe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dfe.getMessage(), 
                    l_dfe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dqe.getMessage(), 
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error("DBへのアクセスに失敗しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dne.getMessage(), 
                    l_dne);
            }
            
            //１－３）　@検索結果が１件以上存在する場合、falseを返却する。
            // 　@　@　@　@　@　@検索結果が存在しない場合は、Loop処理を続ける。
            if (l_lisRecords.size() > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        //２）　@Loop処理を終えた場合、trueを返却する。
        return true;
    }
    
    /**
     * (get抽選区分)<BR>
     * 現在日付によって、新規/繰上抽選区分を返却する。<BR>
     * <BR>
     * １）　@if　@IPO銘柄オブジェクト.isブックビルディング終了（）がtrue かつ　@<BR>
     * 　@　@　@　@IPO銘柄オブジェクト.is購入申込開始（当社設定）（）がfalseの場合、<BR>
     * <BR>
     * 　@　@　@　@return WEB3LotStatusDef.OPEN_LOTTERY_END（新規抽選）<BR>
     * <BR>
     * ２）　@else if 　@IPO銘柄オブジェクト.is購入申込開始（当社設定）（）<BR>
     * 　@　@　@　@がtrue かつ<BR>
     * 　@　@　@　@IPO銘柄オブジェクト.is購入申込終了（目論見書記載）がfalseの場合、<BR>
     * <BR>
     * 　@　@　@　@return WEB3LotStatusDef.ADVANCED_LOTTERY_END（繰上抽選）<BR>
     * <BR>
     * ３）　@else  例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02311<BR>
     * <BR>
     * 　@　@エラーメッセージ「抽選・割当期間ではありません。」<BR>
     * @@param l_ipoProduct - (IPO銘柄オブジェクト)<BR>
     * IPO銘柄クラスオブジェクト。
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getLotDiv(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLotDiv(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@if　@IPO銘柄オブジェクト.isブックビルディング終了（）がtrue かつ　@
        // 　@　@　@　@ IPO銘柄オブジェクト.is購入申込開始（当社設定）（）がfalseの場合、
        //         return WEB3LotStatusDef.OPEN_LOTTERY_END（新規抽選）;
        if (l_ipoProduct.isBookbuildingEnd() && !l_ipoProduct.isOfferStart())
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3LotStatusDef.OPEN_LOTTERY_END;
        }
        //２）　@else if 　@IPO銘柄オブジェクト.is購入申込開始（当社設定）（）がtrue かつ
        //　@　@　@　@　@　@　@　@IPO銘柄オブジェクト.is購入申込終了（目論見書記載）がfalseの場合、
        //               return WEB3LotStatusDef.ADVANCED_LOTTERY_END（繰上抽選）
        else if (l_ipoProduct.isOfferStart() && !l_ipoProduct.isOfferEndProspec())
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3LotStatusDef.ADVANCED_LOTTERY_END;
        }
        else 
        {
            log.debug("抽選・割当期間ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02311, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "抽選・割当期間ではありません。");
        }
    }
    
    /**
     * (get割当可能数量)<BR>
     * 抽選時に割当可能な数量を取得する。<BR>
     * <BR>
     * １）　@if 抽選区分=新規抽選の場合、doFindAllQuery（）を<BR>
     * 　@　@　@使用してIPO申告テーブルに<BR>
     * 　@　@　@以下の条件でレコードを取得する。<BR>
     * <BR>
     * [検索対象テーブル]<BR>
     * IPO申告テーブル<BR>
     * <BR>
     * [検索条件]<BR>
     * 銘柄ID = 引数.銘柄ID and<BR>
     * 抽選結果 = "1：当選"<BR>
     * <BR>
     * <BR>
     * 　@１－１）　@if　@検索結果 = 0件の場合、<BR>
     *            IPO銘柄オブジェクト.get当社取扱数量 ()の値を返却する。<BR>
     * <BR>
     * 　@１－２）　@else if 検索結果 != 0 の場合、取得結果の要素分、<BR>
     * 　@　@　@　@　@　@Loop処理を行い各レコードの当選数量を<BR>
     * 　@　@　@　@　@　@カウントし、当選数量合計を計算する。<BR>
     * <BR>
     * <BR>
     * 　@１－３）　@割当可能数量 = IPO銘柄オブジェクト.get当社取扱数量 ()<BR>
     * 　@　@　@　@　@　@－当選数量合計を計算する。<BR>
     * <BR>
     * 　@１－４）　@１－３）で計算した割当可能数量を返却する。<BR>
     * <BR>
     * ２）　@else if 抽選区分=繰上抽選の場合、<BR>
     * <BR>
     * 　@２－１）　@if IPO銘柄オブジェクト.is購入申込終了（当社設定）（）の<BR>
     * 　@　@　@　@　@　@戻り値がtrueの場合、<BR>
     * 　@　@　@　@　@<BR>
     * 　@　@２－１－１）　@doFindAllQuery（）を使用してIPO申告テーブルに以下の<BR>
     * 　@　@　@　@　@　@条件でレコードを取得する。<BR>
     * <BR>
     * 　@　@[検索対象テーブル]<BR>
     * 　@　@IPO申告テーブル<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@銘柄ID = 引数.銘柄ID and<BR>
     * <BR>
     * 　@　@(抽選結果 = "1：当選"　@and 購入申込区分 = "1：購入申込") <BR>
     * 　@　@or<BR>
     * 　@　@(抽選結果 = "2：補欠"　@and 抽選結果（繰上） = "1：当選") <BR>
     * <BR>
     * <BR>
     * 　@　@２－１－２）　@取得した結果の要素分、Loop処理をおこなう。<BR>
     * <BR>
     * 　@　@　@２－１－２．１）　@if　@抽選結果 = "1：当選"　@and 購入申込区分 =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@"1：購入申込"の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@検索結果レコードから購入申込数量を取得し、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@購入申込数量合計をカウントする。<BR>
     * <BR>
     * 　@　@　@２－１－２．２）　@else if 抽選結果 = "2：補欠"　@and<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@抽選結果（繰上） = "1：当選"の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@検索結果レコードから当選数量を取得し、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@当選数量合計をカウントする。<BR>
     * <BR>
     * 　@　@２－１－３）　@IPO銘柄オブジェクト.get当社取扱数量 ()　@－<BR>
     * 　@　@　@　@　@　@（購入申込数量合計 + 当選数量合計）　@を返却する。<BR>
     * <BR>
     * 　@２－２）　@if IPO銘柄オブジェクト.is購入申込終了（当社設定）（）の<BR>
     * 　@　@　@　@　@　@戻り値がfalseの場合、<BR>
     * 　@　@　@　@　@　@<BR>
     * 　@　@２－２－１）　@doFindAllQuery（）を使用してIPO申告テーブルに以下の<BR>
     * 　@　@　@　@　@　@条件でレコードを取得する。<BR>
     * <BR>
     * 　@　@[検索対象テーブル]<BR>
     * 　@　@IPO申告テーブル<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@銘柄ID = 引数.銘柄ID and<BR>
     * <BR>
     * 　@　@(抽選結果 = "1：当選"　@and 購入申込区分 = "1：購入申込") <BR>
     * 　@　@or<BR>
     * 　@　@(抽選結果 = "2：補欠"　@and 抽選結果（繰上） = "1：当選") <BR>
     * 　@　@or<BR>
     * 　@　@(抽選結果 = "1：当選"　@and 購入申込区分 = "0：デフォルト") <BR>
     * <BR>
     * <BR>
     * 　@２－２－２）　@取得した結果の要素分、Loop処理をおこなう。<BR>
     * <BR>
     * 　@　@２－２－２．１）　@if　@抽選結果 = "1：当選"　@and 購入申込区分 =<BR>
     * 　@　@　@　@　@　@　@　@　@　@"1：購入申込"の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@検索結果レコードから購入申込数量を取得し、<BR>
     * 　@　@　@　@　@　@　@　@　@　@購入申込数量合計をカウントする。<BR>
     * <BR>
     * 　@　@２－２－２．２）　@else if 抽選結果 = "2：補欠"　@and<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@抽選結果（繰上） = "1：当選"　@または<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@抽選結果 = "1：当選"　@and<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@購入申込区分 = "0：デフォルト"の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@検索結果レコードから当選数量を取得し、<BR>
     * 　@　@　@　@　@　@　@　@　@　@当選数量合計をカウントする。<BR>
     * <BR>
     * 　@２－２－３）　@IPO銘柄オブジェクト.get当社取扱数量 ()　@－<BR>
     * 　@　@　@　@　@　@（購入申込数量合計 + 当選数量合計）　@を返却する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@param l_ipoProduct - (IPO銘柄オブジェクト)
     * IPO銘柄クラスオブジェクト。
     * @@param l_strLotDiv - 抽選区分
     * 新規/繰上抽選区分。<BR>
     * <BR>
     * 　@１：新規抽選<BR>
     * 　@２：繰上抽選<BR>
     * @@return long
     * @@throws WEB3BaseException
     */
    protected long getAllotAbleQuantity(
        WEB3GenRequest l_request,
        WEB3IpoProductImpl l_ipoProduct,
        String l_strLotDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAllotAbleQuantity(WEB3GenRequest, WEB3IpoProductImpl, String)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngAllotAbleQuantity = 0;
        QueryProcessor l_processor = null;
        //１）　@if 抽選区分=新規抽選の場合、doFindAllQuery（）を
        //       使用してIPO申告テーブルに
        // 　@　@　@以下の条件でレコードを取得する。
        try
        {
            if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
            {
                List l_lisRecords = new ArrayList();
                
                l_processor = Processors.getDefaultProcessor();  

                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" ipo_product_id = ? ");   //銘柄ID
                l_sbWhere.append(" and lot_result = ? ");   //抽選結果

                Object[] l_objWhere = {
                    new Long(l_ipoProduct.getProductId()),
                    WEB3LotResultDivDef.ELECTION};

                l_lisRecords = l_processor.doFindAllQuery(
                    IpoOrderRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objWhere);
                     
                //１－１）　@if　@検索結果 = 0件の場合、
                //         IPO銘柄オブジェクト.get当社取扱数量 ()の値を返却する。
                int l_intLength = l_lisRecords.size();
                if (l_lisRecords == null || l_intLength == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return (long) l_ipoProduct.getDealingQuantity();
                }
                //１－２）　@else if 検索結果 != 0 の場合、取得結果の要素分、
                // 　@　@　@　@　@　@Loop処理を行い各レコードの当選数量を
                // 　@　@　@　@　@　@カウントし、当選数量合計を計算する。
                else if (l_intLength != 0)
                {
                    long l_lngElectedQuantity = 0;
                    for (int i = 0; i < l_intLength; i ++)
                    {
                        l_lngElectedQuantity +=  ((IpoOrderRow) l_lisRecords.get(i)).getElectedQuantity();  
                    }
                    // 割当可能数量 = IPO銘柄オブジェクト.get当社取扱数量 ()
                    //－当選数量合計を計算する。
                    l_lngAllotAbleQuantity = (long) l_ipoProduct.getDealingQuantity() - l_lngElectedQuantity;
                    
                    //１－４）　@１－３）で計算した割当可能数量を返却する
                    log.exiting(STR_METHOD_NAME);
                    return l_lngAllotAbleQuantity;  
                }   
            }
            else if (WEB3LotDivDef.ADVANCED_LOTTERY.equals(l_strLotDiv))
            {   
                if (l_ipoProduct.isOfferEnd())
                {
                    //doFindAllQuery（）を使用してIPO申告テーブルに以下の
                    //条件でレコードを取得する。
                    List l_lisRecords = new ArrayList();

                    l_processor = Processors.getDefaultProcessor();  

                    StringBuffer l_sbWhere = new StringBuffer();
                    l_sbWhere.append(" ipo_product_id = ? ");   //銘柄ID
                    l_sbWhere.append(" and ((lot_result = ? and offering_div = ?)");
                    l_sbWhere.append(" or (lot_result = ? and lot_result_retry = ?))");
                    Object[] l_objWhere = {
                        new Long(l_ipoProduct.getProductId()),
                        WEB3LotResultDivDef.ELECTION,
                        WEB3OfferingDivDef.PURCHASE_APPLICATION,
                        WEB3LotResultDivDef.SUPPLEMENT,
                        WEB3LotResultRetryDef.ELECTION};

                    l_lisRecords = l_processor.doFindAllQuery(
                        IpoOrderRow.TYPE,
                        l_sbWhere.toString(),
                        null,
                        l_objWhere);

                    long l_lngElectedQuantity = 0;
                    long l_lngApplicationQuantity = 0;
                    int l_intLength = l_lisRecords.size();
                    for (int i = 0; i < l_intLength; i ++)
                    {
                        IpoOrderRow l_ipoOrderRow = (IpoOrderRow) l_lisRecords.get(i);
                        if ((WEB3LotResultDivDef.ELECTION.equals(l_ipoOrderRow.getLotResult())) && 
                            WEB3OfferingDivDef.PURCHASE_APPLICATION.equals(l_ipoOrderRow.getOfferingDiv()))
                        {
                            //２－１－２．１）　@if　@抽選結果 = "1：当選"　@and 購入申込区分 =
                            // 　@　@　@　@　@　@　@　@　@　@　@　@　@"1：購入申込"の場合、
                            // 　@　@　@　@　@　@　@　@　@　@　@　@　@検索結果レコードから購入申込数量を取得し、
                            // 　@　@　@　@　@　@　@　@　@　@　@　@　@購入申込数量合計をカウントする。
                            l_lngApplicationQuantity +=  ((IpoOrderRow) l_lisRecords.get(i)).getApplicationQuantity(); 
                        }
                        //２－１－２．２）　@else if 抽選結果 = "2：補欠"　@and
                        // 　@　@　@　@　@　@　@　@　@　@　@　@　@抽選結果（繰上） = "1：当選"の場合、
                        // 　@　@　@　@　@　@　@　@　@　@　@　@　@検索結果レコードから当選数量を取得し、
                        // 　@　@　@　@　@　@　@　@　@　@　@　@　@当選数量合計をカウントする。
                        else if (WEB3LotResultDivDef.SUPPLEMENT.equals(l_ipoOrderRow.getLotResult()) &&
                            WEB3LotResultRetryDef.ELECTION.equals(l_ipoOrderRow.getLotResultRetry()))
                        {
                            l_lngElectedQuantity += ((IpoOrderRow) l_lisRecords.get(i)).getElectedQuantity(); 
                        }
                        
                    }
                    //２－１－３）　@IPO銘柄オブジェクト.get当社取扱数量 ()
                    //　@　@　@　@　@　@（購入申込数量合計 + 当選数量合計）　@を返却する。
                    l_lngAllotAbleQuantity = 
                        (long) l_ipoProduct.getDealingQuantity() - (l_lngApplicationQuantity + l_lngElectedQuantity);
                }
                //２－２）　@if IPO銘柄オブジェクト.is購入申込終了（当社設定）（）
                //　@　@　@　@　@　@戻り値がfalseの場合
                else
                {
                    //doFindAllQuery（）を使用してIPO申告テーブルに以下の
                    //条件でレコードを取得する。
                    List l_lisRecords = new ArrayList();
                   
                    l_processor = Processors.getDefaultProcessor();  

                    StringBuffer l_sbWhere = new StringBuffer();
                    l_sbWhere.append(" ipo_product_id = ? ");   //銘柄ID
                    l_sbWhere.append(" and ((lot_result = ? and offering_div = ?)");
                    l_sbWhere.append(" or (lot_result = ? and lot_result_retry = ?)");
                    l_sbWhere.append(" or (lot_result = ? and offering_div = ?))");
                    
                    Object[] l_objWhere = {
                        new Long(l_ipoProduct.getProductId()),
                        WEB3LotResultDivDef.ELECTION,
                        WEB3OfferingDivDef.PURCHASE_APPLICATION,
                        WEB3LotResultDivDef.SUPPLEMENT,
                        WEB3LotResultRetryDef.ELECTION,
                        WEB3LotResultDivDef.ELECTION,
                        WEB3OfferingDivDef.DEFAULT};

                    l_lisRecords = l_processor.doFindAllQuery(
                        IpoOrderRow.TYPE,
                        l_sbWhere.toString(),
                        null,
                        l_objWhere);
                   
                    long l_lngElectedQuantity = 0;
                    long l_lngApplicationQuantity = 0;
                    int l_intLength = l_lisRecords.size();
                    
                    for (int i = 0; i < l_intLength; i ++)
                    {
                        IpoOrderRow l_ipoOrderRow = (IpoOrderRow) l_lisRecords.get(i);
                        if ((WEB3LotResultDivDef.ELECTION.equals(l_ipoOrderRow.getLotResult())) && 
                            WEB3OfferingDivDef.PURCHASE_APPLICATION.equals(l_ipoOrderRow.getOfferingDiv()))
                        {
                            //２－２－２．１）　@if　@抽選結果 = "1：当選"　@and 購入申込区分 =
                            // 　@　@　@　@　@　@　@　@　@　@"1：購入申込"の場合、
                            // 　@　@　@　@　@　@　@　@　@　@検索結果レコードから購入申込数量を取得し、
                            // 　@　@　@　@　@　@　@　@　@　@購入申込数量合計をカウントする。
                            l_lngApplicationQuantity +=  ((IpoOrderRow) l_lisRecords.get(i)).getApplicationQuantity(); 
                        }
                        
                        else if ((WEB3LotResultDivDef.SUPPLEMENT.equals(l_ipoOrderRow.getLotResult()) &&
                            WEB3LotResultRetryDef.ELECTION.equals(l_ipoOrderRow.getLotResultRetry())) ||
                            (WEB3LotResultDivDef.ELECTION.equals(l_ipoOrderRow.getLotResult()) && 
                             WEB3OfferingDivDef.DEFAULT.equals(l_ipoOrderRow.getOfferingDiv())))
                        {
                            //２－２－２．２）　@else if 抽選結果 = "2：補欠"　@and
                            // 　@　@　@　@　@　@　@　@　@　@抽選結果（繰上） = "1：当選"　@または
                            // 　@　@　@　@　@　@　@　@　@　@抽選結果 = "1：当選"　@and
                            // 　@　@　@　@　@　@　@　@　@　@購入申込区分 = "0：デフォルト"の場合、
                            // 　@　@　@　@　@　@　@　@　@　@検索結果レコードから当選数量を取得し、
                            // 　@　@　@　@　@　@　@　@　@　@当選数量合計をカウントする。
                            l_lngElectedQuantity += ((IpoOrderRow) l_lisRecords.get(i)).getElectedQuantity();   
                        }
                    }
                    //２－２－３）　@IPO銘柄オブジェクト.get当社取扱数量 ()　@－
                    // 　@　@　@　@　@　@（購入申込数量合計 + 当選数量合計）　@を返却する。
                    l_lngAllotAbleQuantity = 
                        (long) l_ipoProduct.getDealingQuantity() - (l_lngApplicationQuantity + l_lngElectedQuantity);  
                }
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dfe.getMessage(), 
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dqe.getMessage(), 
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DBへのアクセスに失敗しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dne.getMessage(), 
                l_dne);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lngAllotAbleQuantity;
    }
    
    /**
     * (get抽選シーケンス)<BR>
     * IPO抽選テーブルのレコードで、抽選シーケンスがMax値のレコードの<BR>
     * 抽選シーケンス+1を返却する。<BR>
     * <BR>
     * １）　@this.get最新抽選レコード（<BR>
     * 　@　@リクエストオブジェクト.銘柄ID , 引数:抽選区分）で、<BR>
     * 　@　@レコードを検索する。<BR>
     * <BR>
     * ２）　@１）で取得した検索結果が0件の場合、<BR>
     * <BR>
     * 　@　@抽選シーケンス = 1 を返却する。<BR>
     * <BR>
     * ３）　@１）で取得した検索結果が１件以上の場合、　@<BR>
     * 　@３－１）　@検索したレコードの1件目を取り出し、抽選シーケンスを取得する。<BR>
     * <BR>
     * 　@３－２）　@取得した抽選シーケンス + 1 を返却する。<BR>
     * @@param l_strProductId - 銘柄ID
     * @@param l_strLotDiv - (抽選区分)
     * 新規/繰上抽選区分。<BR>
     * <BR>
     * 　@１：新規抽選<BR>
     * 　@２：繰上抽選<BR>
     * @@return long
     * @@throws WEB3BaseException
     */
    protected long getBbSeq(String l_strProductId, String l_strLotDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBbSeq(String, String)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngBbseq = 0;
        
        //１）　@this.get最新抽選レコード（
        // 　@　@リクエストオブジェクト.銘柄ID , 引数:抽選区分）で、レコードを検索する。
        IpoBookbuildingRow[] l_ipoBookbuildingRows = this.getNewLotRecord(l_strProductId, l_strLotDiv);
        
        //２）　@１）で取得した検索結果が0件の場合、抽選シーケンス = 1 を返却する。
        if (l_ipoBookbuildingRows.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return 1;
        }
        
        //３）　@１）で取得した検索結果が１件以上の場合、
        //３－１）　@検索したレコードの1件目を取り出し、抽選シーケンスを取得する。
        //３－２）　@取得した抽選シーケンス + 1 を返却する。
        l_lngBbseq = l_ipoBookbuildingRows[0].getBbSeq() + 1;

        log.exiting(STR_METHOD_NAME);
        return l_lngBbseq;
    }
    
    /**
     * (get処理状況区分)<BR>
     * 処理状況区分を返却する。<BR>
     * <BR>
     * 　@１）　@引数:IPO抽選Rowからステータスを取得する。<BR>
     * <BR>
     * 　@２）　@if ステータス = "1：処理中"の場合、<BR>
     * <BR>
     * 　@　@　@return "1：待ち状況"<BR>
     * <BR>
     * 　@３）　@else if ステータス = "9：異常終了"の場合、<BR>
     * <BR>
     * 　@　@　@return "9：異常終了"<BR>
     * <BR>
     * 　@４）　@else if ステータス = "0：正常終了"の場合、<BR>
     * <BR>
     * 　@　@　@return "2：完了状況"<BR>
     * @@param  - (IPO抽選Row)<BR>
     * IPO抽選Rowオブジェクト
     * @@return String
     */
    protected String getTransactionStateType(IpoBookbuildingRow l_ipoBookbuildingRow)
    {
        final String STR_METHOD_NAME = " getTransactionStateType(IpoBookbuildingRow)";
        log.entering(STR_METHOD_NAME);
        
        //　@１）　@引数:IPO抽選Rowからステータスを取得する。
        String l_strStatus = l_ipoBookbuildingRow.getStatus();
        
        //２）　@if ステータス = "1：処理中"の場合
        if (WEB3IPOBookBuldingStatusDef.DEALING.equals(l_strStatus))
        {
            //return 1：待ち状況
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoTransactionStatusTypeDef.WAITING;
        }
        //３）　@else if ステータス = 9：異常終了"の場合
        else if (WEB3IPOBookBuldingStatusDef.ABNORMAL_END.equals(l_strStatus))
        {
            //return 9：異常終了
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoTransactionStatusTypeDef.ERROR;
        }
        //　@４）　@else if ステータス = "0：正常終了"の場合
        else if (WEB3IPOBookBuldingStatusDef.NORMAL_END.equals(l_strStatus))
        {
            //return 2：完了状況
            log.exiting(STR_METHOD_NAME);
            return WEB3IpoTransactionStatusTypeDef.COMPLETED;
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get抽選確定部店ID)<BR>
     * 抽選確定対象の部店IDリストを返却する。<BR>
     * <BR>
     * １）　@ArrayListオブジェクトの生成。<BR>
     * <BR>
     * ２）　@以下の条件で、doFindAllQuery（）を使用してIPO抽選テーブルを検索する。<BR>
     * [検索対象テーブル]<BR>
     * IPO抽選テーブル<BR>
     * <BR>
     * [検索条件]<BR>
     * 銘柄ID = 引数:銘柄ID<BR>
     * 抽選区分 = 引数:抽選区分<BR>
     * 抽選シーケンス = 引数:抽選シーケンス<BR>
     * <BR>
     * ３）　@検索結果 != 0 の場合、取得したレコードの要素分Loop処理をおこなう。<BR>
     * <BR>
     * 　@３－１）　@レコードから部店IDを取得する。<BR>
     * <BR>
     * 　@３－２）　@ArrayListオブジェクト.add(取得した部店ID)<BR>
     * <BR>
     * ４）　@検索結果が0件の場合、例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02340<BR>
     * <BR>
     * ５）　@ArrayListオブジェクトを返却する。<BR>
     * @@param l_strProductId - 銘柄ID
     * @@param l_strLotDiv - (抽選区分)
     * 新規/繰上抽選区分。<BR>
     * <BR>
     * 　@１：新規抽選<BR>
     * 　@２：繰上抽選<BR>
     * @@param l_lngBbSeq - 抽選シーケンス
     * @@return List
     * @@throws WEB3BaseException
     */
    protected List getLotConfirmBranchId(
        String l_strProductId, 
        String l_strLotDiv,
        long l_lngBbSeq) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLotConfirmBranchId(String, String, long)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ArrayListオブジェクトの生成。
        List l_lisBranchIds = new ArrayList();
        List l_lisRecords = new ArrayList();
        try
        {
            //２）　@以下の条件で、doFindAllQuery（）を使用してIPO抽選テーブルを検索する。
            QueryProcessor l_processor = Processors.getDefaultProcessor();  

            StringBuffer l_sbWhere = new StringBuffer();
            //銘柄ID
            l_sbWhere.append(" ipo_product_id = ? ");   
            //抽選区分 
            l_sbWhere.append(" and bb_div = ? ");       
            //抽選シーケンス
            l_sbWhere.append(" and bb_seq = ? ");

            Object[] l_objWhere = {
                l_strProductId,
                l_strLotDiv,
                new Long(l_lngBbSeq)};

            l_lisRecords = l_processor.doFindAllQuery(
                IpoBookbuildingRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dfe.getMessage(), 
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dqe.getMessage(), 
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DBへのアクセスに失敗しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dne.getMessage(), 
                l_dne);
        }
        
        //３）　@検索結果 != 0 の場合、取得したレコードの要素分Loop処理をおこなう。
        int l_intLength = l_lisRecords.size();
        
        // 検索結果が0件の場合、例外をスローする。
        if (l_lisRecords == null || l_intLength == 0)
        {
            log.debug("抽選確定対象の部店IDリストが取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02340,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "抽選確定対象の部店IDリストが取得できません。");
        }
        
        for (int i = 0; i < l_intLength; i++)
        {
            IpoBookbuildingRow l_ipoBookbuildingRow = (IpoBookbuildingRow) l_lisRecords.get(i);
            //  ３－１）　@レコードから部店IDを取得する。
            //　@３－２）　@ArrayListオブジェクト.add(取得した部店ID)
            l_lisBranchIds.add(new Long(l_ipoBookbuildingRow.getBranchId()));
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisBranchIds;
    }
    
    /**
     * (get入力項目Row)<BR>
     * IPO抽選テーブルに更新処理（Insert）をおこなう為に、対象部店の要素分<BR>
     * IPO抽選Rowオブジェクトで生成し、IPO抽選Row[]を返却する。<BR>
     * <BR>
     * １）　@ArrayListオブジェクトの生成<BR>
     * <BR>
     * ２）　@引数:部店IDリストの要素分、Loop処理をおこなう。<BR>
     * <BR>
     * 　@２－１）　@部店IDリスト.get（ i ）で、部店IDを取得する。<BR>
     * <BR>
     * 　@２－２）　@IPO抽選paramsオブジェクトを生成する。<BR>
     * <BR>
     * 　@２－３）　@以下のパラメータを１－１）で<BR>
     * 　@　@生成したparamsオブジェクトにセットする。<BR>
     * 　@　@セットする内容は、 <BR>
     * <BR>
     * 　@　@DB更新仕様 <BR>
     * 　@　@「抽選割当_IPO抽選テーブル仕様.xls」参照。 <BR>
     * <BR>
     * 　@２－４）　@ArrayListオブジェクト.add(IPO抽選paramsオブジェクト)<BR>
     * <BR>
     * ３）　@ArrayListオブジェクトをIPO抽選Row[]に変換する。<BR>
     * <BR>
     * ４）　@３）で変換したIPO抽選Rowを返却する。<BR>
     * @@param l_request - (リクエストオブジェクト)
     * 管理者IPO抽選割当確認リクエストオブジェクト。
     * @@param l_strLotDiv - (抽選区分)
     * 新規/繰上抽選区分。<BR>
     * <BR>
     * 　@１：新規抽選<BR>
     * 　@２：繰上抽選<BR>
     * @@param l_lngBbSeq - 抽選シーケンス
     * @@param l_lisBranchIdLists - (部店IDリスト)
     * 部店IDリストオブジェクト
     * @@return - IPO抽選Row[]
     * @@throws WEB3BaseException
     */
    protected IpoBookbuildingRow[] getInputItemRow(
        WEB3AdminIPOLotConfirmRequest l_request,
        String l_strLotDiv,
        long l_lngBbSeq,
        List l_lisBranchIdLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputItemRow(WEB3AdminIPOLotInputRequest, String, long, List)";
        log.entering(STR_METHOD_NAME); 
        
        //１）　@ArrayListオブジェクトの生成
        List l_lisRecords = new ArrayList();
        
        int l_intLength = l_lisBranchIdLists.size();
        
        l_lngBbSeq = this.getBbSeq(l_request.id, l_strLotDiv);
        
        //２）　@引数:部店IDリストの要素分、Loop処理をおこなう。
        for (int i = 0; i < l_intLength; i++)
        {
            //２－１）　@部店IDリスト.get（ i ）で、部店IDを取得する。
            long l_lngBranchId = Long.parseLong(String.valueOf(l_lisBranchIdLists.get(i)));
            
            //２－２）　@IPO抽選paramsオブジェクトを生成する。
            IpoBookbuildingParams l_ipoBookbuildingParams = new IpoBookbuildingParams();
            
            //IPO銘柄ＩＤ
            l_ipoBookbuildingParams.setIpoProductId(Long.parseLong(l_request.id));
            
            //抽選区分
            l_ipoBookbuildingParams.setBbDiv(l_strLotDiv);
            
            //抽選シーケンス
            l_ipoBookbuildingParams.setBbSeq(l_lngBbSeq);
            
            //部店ＩＤ
            l_ipoBookbuildingParams.setBranchId(l_lngBranchId);
            
            String l_strProductCode = null;
            try
            {
                l_strProductCode = 
                    IpoProductDao.findRowByPk(Long.parseLong(l_request.id)).getProductCode();
            }
            catch (DataFindException l_dfe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dfe.getMessage(), 
                    l_dfe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dqe.getMessage(), 
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error("DBへのアクセスに失敗しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dne.getMessage(), 
                    l_dne);
            }
            
            //銘柄コード
            l_ipoBookbuildingParams.setProductCode(l_strProductCode);
            
            //抽選割当総枠数量 
            if (l_request.allotTotalQuantity == null)
            {
                l_request.allotTotalQuantity = "0";
            }
            l_ipoBookbuildingParams.setBbQuantityAll(Long.parseLong(l_request.allotTotalQuantity));
            
            //割当上限数量（１ループあたり）
            if (l_request.allotLimitQuantity == null)
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "パラメータ値不正。");
            }
            if (l_request.allotLimitQuantity == null)
            {
                l_request.allotLimitQuantity = "0";
            }
            l_ipoBookbuildingParams.setBbQuantityLoop(Long.parseLong(l_request.allotLimitQuantity));
            
            if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
            {
                //補欠割当総枠数量
                l_ipoBookbuildingParams.setSubBbQuantityAll(new Long(l_request.waitingAllotTotalQuantity));
                
                //補欠割当上限数量（１ループあたり）
                l_ipoBookbuildingParams.setSubBbQuantityLoop(new Long(l_request.waitingAllotLimitQuantity));
            }

            //処理状況
            l_ipoBookbuildingParams.setProcess(WEB3IPOBookBuldingProcDef.LOT_INPUT);
            
            //ステータス
            l_ipoBookbuildingParams.setStatus(WEB3IPOBookBuldingStatusDef.DEALING);
            
            //作成日時
            l_ipoBookbuildingParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //更新日時
            l_ipoBookbuildingParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //２－４）　@ArrayListオブジェクト.add(IPO抽選paramsオブジェクト)
            l_lisRecords.add(l_ipoBookbuildingParams);
            
        }
        IpoBookbuildingRow[] l_ipoBookbuildingRows = new IpoBookbuildingRow[l_intLength];

        //ArrayListオブジェクトをIPO抽選Row[]に変換する。
        l_lisRecords.toArray(l_ipoBookbuildingRows);

        log.exiting(STR_METHOD_NAME);
        //４）　@３）で変換したIPO抽選Rowを返却する。
        return l_ipoBookbuildingRows;
    }
    
    /**
     * (get最新抽選レコード)<BR>
     * IPO抽選テーブルから抽選シーケンスが最新のレコードを取得し、<BR>
     * 　@　@返却するメソッド。<BR>
     * <BR>
     * １）　@doFindAllQuery（）を使用して以下の条件で、<BR>
     * 　@　@IPO抽選テーブルにレコードを取得する。<BR>
     * [検索対象テーブル]<BR>
     * IPO抽選テーブル<BR>
     * <BR>
     * [検索条件]<BR>
     * 銘柄ID = 引数:銘柄ID<BR>
     * 抽選区分 = 引数:抽選区分<BR>
     * 抽選シーケンス = (select max(抽選シーケンス) from IPO抽選テーブル where <BR>
     *                         銘柄ID = 引数:銘柄ID and<BR>
     *                         抽選区分 = 引数:抽選区分)<BR>
     * <BR>
     * ２）　@１）の検索結果のListをIPO抽選Row[]に変換する。<BR>
     * <BR>
     * ３）　@２）で変換したIPO抽選Row[]を返却する。<BR>
     * @@param l_strProductId - 銘柄ID
     * @@param l_strLotDiv - (抽選区分)
     * 新規/繰上抽選区分。<BR>
     * <BR>
     * 　@１：新規抽選<BR>
     * 　@２：繰上抽選<BR>
     * @@return - IPO抽選Row[]
     * @@throws WEB3BaseException
     */
    protected IpoBookbuildingRow[] getNewLotRecord(String l_strProductId, String l_strLotDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNewLotRecord(String, String)";
        log.entering(STR_METHOD_NAME); 
        
        //doFindAllQuery（）を使用して以下の条件で、IPO抽選テーブルにレコードを取得する。
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();  

            StringBuffer l_sbWhere = new StringBuffer();
            //銘柄ID
            l_sbWhere.append(" ipo_product_id = ? ");   
            //抽選区分 
            l_sbWhere.append(" and bb_div = ? ");       
            //抽選シーケンス
            l_sbWhere.append(
                " and bb_seq = (select max(bb_seq) from ipo_bookbuilding where ipo_product_id = ? and bb_div = ? )");

            Object[] l_objWhere = {
                l_strProductId,
                l_strLotDiv,
                l_strProductId,
                l_strLotDiv};

            l_lisRecords = l_processor.doFindAllQuery(
                IpoBookbuildingRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dfe.getMessage(), 
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dqe.getMessage(), 
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DBへのアクセスに失敗しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dne.getMessage(), 
                l_dne);
        }
        
        //２）　@検索結果のListをIPO抽選Row配列に変換する。
        if (l_lisRecords == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");   
        }
        
        int l_intLength = l_lisRecords.size();
        IpoBookbuildingRow[] l_ipoBookbuildingRows = new IpoBookbuildingRow[l_intLength];

        l_lisRecords.toArray(l_ipoBookbuildingRows);

        log.exiting(STR_METHOD_NAME);
        //３）　@２）で変換したIPO抽選Row[]を返却する。
        return l_ipoBookbuildingRows;
    }
    
    /**
     * (get完了状況レコード)<BR>
     * 完了状況画面に表示するレコードを取得し、返却する。<BR>
     * <BR>
     * １）　@dofindAllQuery()を使用して、以下の検索条件でレコードを取得する。<BR>
     * <BR>
     * [検索対象テーブル]<BR>
     * IPO抽選テーブル<BR>
     * <BR>
     * [検索条件]<BR>
     * IPO銘柄ID = 引数:銘柄ID and<BR>
     * 抽選区分 = 引数:抽選区分 and<BR>
     * 抽選シーケンス = <BR>
     * <BR>
     * if 引数:抽選シーケンス != null　@の場合<BR>
     * 　@引数.抽選シーケンス<BR>
     * else<BR>
     * 　@(処理状況 = "14：確定終了" and "ステータス = "0：正常終了"<BR>
     * 　@or<BR>
     * 　@処理状況 in （"11：確定入力","12：デーモン受付","13:確定開始"）　@and　@<BR>
     * 　@　@ステータス in （"1:処理中","9:異常終了"））<BR>
     * <BR>
     * [ソート条件]<BR>
     * 抽選シーケンス.asc<BR>
     * <BR>
     * ２）　@検索結果のListをIPO抽選Row配列に変換する。<BR>
     * <BR>
     * ３）　@２）で変換したIPO抽選Row[]を返却する。<BR>
     * @@param l_strProductId - 銘柄ID
     * @@param l_strLotDiv - (抽選区分)
     * 新規/繰上抽選区分。<BR>
     * <BR>
     * 　@１：新規抽選<BR>
     * 　@２：繰上抽選<BR>
     * @@param l_strBbSeq - 抽選シーケンス
     * @@return - IPO抽選Row[]
     * @@throws WEB3BaseException
     */
    protected IpoBookbuildingRow[] getCompleteStateRecord(
        String l_strProductId,
        String l_strLotDiv,
        String l_strBbSeq) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCompleteStateRecord(String, String, String)";
        log.entering(STR_METHOD_NAME); 
        
        //dofindAllQuery()を使用して、以下の検索条件でレコードを取得する
        List l_lisRecords = new ArrayList();
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor(); 

            StringBuffer l_sbWhere = new StringBuffer();
            Object[] l_objWhere = null;
            l_sbWhere.append(" ipo_product_id = ? ");   //IPO銘柄ID
            l_sbWhere.append(" and bb_div = ? ");       //抽選区分 
            
            //if 引数:抽選シーケンス != null　@の場合
            if (l_strBbSeq != null)
            {
                l_sbWhere.append(" and bb_seq = ? ");       //抽選シーケンス
                
                l_objWhere = new Object[]{
                    l_strProductId,
                    l_strLotDiv,
                    l_strBbSeq};
            }
            //else
            //　@(処理状況 = "14：確定終了" and "ステータス = "0：正常終了"
            //　@or
            //　@処理状況 in （"11：確定入力","12：デーモン受付","13:確定開始"）　@and
            //　@　@ステータス in （"1:処理中","9:異常終了"））
            else
            {
                l_sbWhere.append(" and (process = ? and status = ?");
                l_sbWhere.append(" or process in (?, ?, ?) and status in (?, ?)) ");       //抽選シーケンス
                l_objWhere = new Object[]{
                    l_strProductId,
                    l_strLotDiv,
                    WEB3IPOBookBuldingProcDef.CONFIRM_END,
                    WEB3IPOBookBuldingStatusDef.NORMAL_END,
                    WEB3IPOBookBuldingProcDef.CONFIRM_INPUT,
                    WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT,
                    WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN,
                    WEB3IPOBookBuldingStatusDef.DEALING,
                    WEB3IPOBookBuldingStatusDef.ABNORMAL_END};
            }
            
            String l_strSortKey = " bb_seq asc";
            
            l_lisRecords = l_processor.doFindAllQuery(
                IpoBookbuildingRow.TYPE,
                l_sbWhere.toString(),
                l_strSortKey,
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dfe.getMessage(), 
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dqe.getMessage(), 
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DBへのアクセスに失敗しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dne.getMessage(), 
                l_dne);
        }
        
        //２）　@検索結果のListをIPO抽選Row配列に変換する。
        int l_intLength = l_lisRecords.size();
        IpoBookbuildingRow[] l_ipoBookbuildingRows = new IpoBookbuildingRow[l_intLength];

        l_lisRecords.toArray(l_ipoBookbuildingRows);

        log.exiting(STR_METHOD_NAME); 
        //３）　@２）で変換したIPO抽選Row[]を返却する。
        return l_ipoBookbuildingRows;
    }
    
    /**
     * (save抽選確定)<BR>
     * アイテムの定義<BR>
     * 抽選割当レコードのupdate処理を行う。 <BR>
     * <BR>
     * <BR>
     * １）　@引数.部店IDリストのサイズ分Loop処理をおこない、更新処理を実行する。<BR>
     * <BR>
     * 　@１－１）　@引数:部店IDリスト.get（ i ）で、部店IDを取得する。<BR>
     * <BR>
     * 　@１－２）　@doUpdateAllQuery（）を使用して以下の条件で、IPO抽選テーブルの<BR>
     * 　@　@　@　@　@　@レコードをUpdateする。<BR>
     * 　@[更新条件]<BR>
     * 　@・　@IPO銘柄ID = 引数:銘柄ID<BR>
     * 　@・　@抽選区分 = 引数.抽選区分<BR>
     * 　@・　@抽選シーケンス = 引数:抽選シーケンス<BR>
     * 　@・　@部店ID = １－１）で取得した部店ID<BR>
     * <BR>
     * 　@[更新内容]<BR>
     * 　@・　@処理状況 = "11：確定入力"<BR>
     * 　@・　@ステータス = "1：処理中"　@<BR>
     * 　@・　@更新日時 =  引数:更新日時<BR>
     * <BR>
     * 　@DB更新仕様 <BR>
     * 　@「抽選割当_IPO抽選テーブル仕様.xls」参照。 <BR>
     * @@param l_strProductId - 銘柄ID
     * @@param l_strLotDiv - (抽選区分)
     * 新規/繰上抽選区分。<BR>
     * <BR>
     * 　@１：新規抽選<BR>
     * 　@２：繰上抽選<BR>
     * @@param l_lngBbSeq - 抽選シーケンス
     * @@param l_lisBranchIdLists - (部店IDリスト)
     * 部店IDリストオブジェクト。
     * @@param l_tsLastUpdatedTimestamp- (更新日時)
     * GtlUtils.getSystemTimestamp()で取得した更新日時
     * @@throws WEB3BaseException
     */
    protected void saveLotConfirm(
        String l_strProductId,
        String l_strLotDiv,
        long l_lngBbSeq,
        List l_lisBranchIdLists,
        Timestamp l_tsLastUpdatedTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveLotConfirm(" +
            " String," +
            " String," +
            " long," +
            " List," +
            " Timestamp)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngBranchIdLength = l_lisBranchIdLists.size();
        //１）　@引数.部店IDリストのサイズ分Loop処理をおこない、更新処理を実行する。
        for (int i = 0; i < l_lngBranchIdLength; i++)
        {
            //１－１－１）　@部店IDリストから部店IDをget( i )する。
            Long l_lngBranchId = (Long) l_lisBranchIdLists.get(i);
            
            //doUpdateAllQuery()を使用して以下の条件で、IPO抽選テーブルのレコードをUpdateする。
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();  

                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" ipo_product_id = ? ");    //IPO銘柄ID
                l_sbWhere.append(" and bb_div = ? ");        //抽選区分 
                l_sbWhere.append(" and bb_seq = ? ");        //抽選シーケンス
                l_sbWhere.append(" and branch_id = ? ");     //部店ID

                Object[] l_objWhere = {
                    l_strProductId,
                    l_strLotDiv,
                    new Long(l_lngBbSeq),
                    l_lngBranchId};

                Map l_map = new HashMap();
                l_map.put("process", WEB3IPOBookBuldingProcDef.CONFIRM_INPUT);
                l_map.put("status", WEB3IPOBookBuldingStatusDef.DEALING);
                l_map.put("last_updated_timestamp", l_tsLastUpdatedTimestamp);
                l_processor.doUpdateAllQuery(
                    IpoBookbuildingRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere,
                    l_map);
            }
            catch (DataFindException l_dfe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dfe.getMessage(), 
                    l_dfe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dqe.getMessage(), 
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error("DBへのアクセスに失敗しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_dne.getMessage(), 
                    l_dne);
            }
        }
        log.exiting(STR_METHOD_NAME); 
    }
    
   /** 
    * (validate抽選銘柄)
    * IPO抽選銘柄の妥当性チェックを行う。<BR>
    * <BR>
    * １）　@IPO銘柄オブジェクト.is削除銘柄（）で、削除銘柄かを判定する。<BR>
    * 　@　@  結果がtrueの場合、例外をスローする。 <BR>
    * class: WEB3BusinessLayerExceptio<BR>
    * tag:  BUSINESS_ERROR_02326<BR>
    * <BR>
    * ２）　@IPO銘柄オブジェクト.is中止（）で、中止銘柄かを判定する。<BR>
    * 　@　@  結果がtrueの場合、例外をスローする。 <BR>
    * class: WEB3BusinessLayerExceptio<BR>
    * tag:  BUSINESS_ERROR_02327<BR>
    * <BR>
    * ３）　@IPO銘柄オブジェクト.isブックビルディング終了（）で、<BR>
    *       ブックビルディング期間が<BR>
    * 　@　@　@終了しているかを判定する。<BR>
    * 　@　@  結果がfalseの場合、例外をスローする。<BR>
    * class: WEB3BusinessLayerExceptio<BR>
    * tag:  BUSINESS_ERROR_02328<BR>
    * <BR>
    * ４）　@IPO銘柄オブジェクト.isスケジュール決定（）で、<BR>
    *       スケジュール項目が妥当かを判定する。<BR>
    * 　@　@   結果がfalseの場合、例外をスローする。<BR>
    * class: WEB3BusinessLayerException<BR>
    * tag:  BUSINESS_ERROR_02329<BR>
    * <BR>
    * ５）　@IPO銘柄オブジェクト.is公開価格決定（）で、<BR>
    *       公開価格が設定されているかを判定する。<BR>
    * 　@　@  結果がfalseの場合、例外をスローする。<BR>
    * class: WEB3BusinessLayerException<BR>
    * tag:  BUSINESS_ERROR_02330<BR>
    * <BR>
    * ６）　@IPO銘柄オブジェクト.get取扱数量（）で、取扱数量を取得する。<BR>
    * 　@６－１）　@取得した取扱数量が 0、<BR>
    * 　@　@　@またはDouble.isNaN(取得した取扱数量)の場合、例外をスローする。<BR>
    * class: WEB3BusinessLayerException<BR>
    * tag:  BUSINESS_ERROR_02331<BR>
    * <BR>
    * ７）　@IPO銘柄オブジェクト.get購入申込単位（）で、<BR>
    *       購入申込単位を取得する。<BR>
    * 　@７－１）　@取得した購入申込単位が 0、<BR>
    * 　@　@　@またはDouble.isNaN(取得した購入申込単位)の場合、例外をスローする。<BR>
    * class: WEB3BusinessLayerException<BR>
    * tag:  BUSINESS_ERROR_02332<BR>
    * @@param l_ipoProductImpl - (IPO銘柄オブジェクト)
    * @@throws WEB3BaseException
    */
    protected void validateLotProduct(WEB3IpoProductImpl l_ipoProductImpl) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateLotProduct(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        // １）　@IPO銘柄オブジェクト.is削除銘柄（）で、削除銘柄かを判定する。
        //結果がtrueの場合、例外をスローする。
        if (l_ipoProductImpl.isDeletedProduct())
        {
            log.debug("抽選銘柄が削除銘柄です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02326,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "抽選銘柄が削除銘柄です。");
        }
        
        //２）　@IPO銘柄オブジェクト.is中止（）で、中止銘柄かを判定する。
        //結果がtrueの場合、例外をスローする。
        if (l_ipoProductImpl.isDiscontinuation())
        {
            log.debug("抽選銘柄が中止銘柄です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02327,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "抽選銘柄が中止銘柄です。");
        }
        
        //３）　@IPO銘柄オブジェクト.isブックビルディング終了（）で、
        //ブックビルディング期間が終了しているかを判定する。
        //結果がfalseの場合、例外をスローする。
        if (!l_ipoProductImpl.isBookbuildingEnd())
        {
            log.debug("抽選・割当期間ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02311,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "抽選・割当期間ではありません。");
        }
        
        //４）　@IPO銘柄オブジェクト.isスケジュール決定（）で、
        //スケジュール項目が妥当かを判定する。結果がfalseの場合、例外をスローする。
        if (!l_ipoProductImpl.isScheduleDecision())
        {
            log.debug("抽選銘柄のスケジュール項目が不適切です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02329,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "抽選銘柄のスケジュール項目が不適切です。");
        }
        
        //５）　@IPO銘柄オブジェクト.is公開価格決定（）で、
        //公開価格が設定されているかを判定する。結果がfalseの場合、例外をスローする。
        if (!l_ipoProductImpl.isPublicPriceSettle())
        {
            log.debug("抽選銘柄の公開価格が設定されていません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02330,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "抽選銘柄の公開価格が設定されていません。");
        }
        
        //６）　@IPO銘柄オブジェクト.get取扱数量（）で、取扱数量を取得する。
        //　@６－１）　@取得した取扱数量が 0、
        //　@　@　@またはDouble.isNaN(取得した取扱数量)の場合、例外をスローする。
        double l_dblDealingQuantity = l_ipoProductImpl.getDealingQuantity();
        if (Double.isNaN(l_dblDealingQuantity) || l_dblDealingQuantity == 0)
        {
            log.debug("抽選銘柄の取扱数量が 0です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02331,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "抽選銘柄の取扱数量が 0です。"); 
        }
        
        //７）　@IPO銘柄オブジェクト.get購入申込単位（）で、購入申込単位を取得する。
        //　@７－１）　@取得した購入申込単位が 0、
        //　@　@　@またはDouble.isNaN(取得した購入申込単位)の場合、例外をスローする。
        double l_dblLotSize = l_ipoProductImpl.getLotSize();
        if (Double.isNaN(l_dblLotSize) || l_dblLotSize == 0)
        {
            log.debug("抽選銘柄の購入申込単位が 0です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02332,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "抽選銘柄の購入申込単位が 0です。"); 
        }
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (validate抽選妥当性)<BR>
     * IPO申告テーブルの抽選妥当性をチェックするメソッド。<BR>
     * <BR>
     * １）　@if　@抽選区分 = "新規抽選"　@の場合<BR>
     * 　@１－１）　@doFindAllQuery()を使用して以下の条件で、IPO申告テーブルを検索する。<BR>
     * <BR>
     * 　@　@[検索対象テーブル]<BR>
     * 　@　@IPO申告テーブル<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@銘柄ID = 引数:銘柄ID and<BR>
     * 　@　@部店ID in (引数:部店IDリスト[0],引数:部店IDリスト[1]･･･※) and<BR>
     * 　@　@ブックビルディング申告状態 != "取消" and<BR>
     * 　@　@抽選結果 = "未抽選"<BR>
     * <BR>
     * 　@１－２）　@if 検索結果が0件の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@エラーメッセージ「抽選対象顧客が存在しません。」<BR>
     * 　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@:　@　@BUSINESS_ERROR_02312<BR>
     * <BR>
     * ２）　@if　@抽選区分 = "繰上抽選"　@の場合<BR>
     * 　@２－１）　@doFindAllQuery()を使用して以下の条件で、IPO申告テーブルを検索する。<BR>
     * <BR>
     * 　@　@[検索対象テーブル]<BR>
     * 　@　@IPO申告テーブル<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@銘柄ID = 引数:銘柄ID and<BR>
     * 　@　@部店ID in (引数:部店IDリスト[0],引数:部店IDリスト[1]･･･※) and<BR>
     * 　@　@抽選結果 = "補欠" and<BR>
     * 　@　@購入申込区分 != "辞退" and<BR>
     * 　@　@抽選結果（繰上） = "DEFAULT(未抽選)"<BR>
     * <BR>
     * 　@２－２）　@if 検索結果が0件の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@エラーメッセージ「補欠データが存在しません。」<BR>
     * 　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@:　@　@BUSINESS_ERROR_02333<BR>
     * <BR>
     * ※引数:部店IDリストの要素分追加<BR>
     * <BR>
     * @@param l_strProductId - (銘柄ID)<BR>
     * IPO銘柄ID<BR>
     * @@param l_strLotDiv - (抽選区分)<BR>
     * 新規/繰上抽選区分<BR>
     * <BR>
     * １：新規抽選<BR>
     * ２：繰上抽選<BR>
     * @@param l_lisBranchIds - (部店IDリスト)<BR>
     * 部店IDリストオブジェクト<BR>
     * @@throws WEB3BaseException
     */
    protected void validateLotValidity(
        String l_strProductId, String l_strLotDiv, List l_lisBranchIds) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateLotValidity(" +
            " String," +
            " String," +
            " List)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@if　@抽選区分 = "新規抽選"　@の場合
        int l_intBranchIdLength = l_lisBranchIds.size();
        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisQueryContainers = new ArrayList();
        if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
        {
            //１－１）　@doFindAllQuery()を使用して以下の条件で、IPO申告テーブルを検索する。
            //[検索対象テーブル]
            //IPO申告テーブル
            //[検索条件]
            //銘柄ID = 引数:銘柄ID and
            //部店ID in (引数:部店IDリスト[0],引数:部店IDリスト[1]･･･※) and
            //ブックビルディング申告状態 != "取消" and
            //抽選結果 = "未抽選"
            l_sbWhere.append(" ipo_product_id = ? ");
            l_lisQueryContainers.add(l_strProductId);

            l_sbWhere.append(" and branch_id in (");
            for (int i = 0; i < l_intBranchIdLength; i++)
            {
                if (i == (l_intBranchIdLength - 1))
                {
                    l_sbWhere.append(" ? ");
                }
                else
                {
                    l_sbWhere.append(" ?, ");
                }
                l_lisQueryContainers.add((Long)l_lisBranchIds.get(i));
            }
            l_sbWhere.append(")");

            l_sbWhere.append(" and order_status <> ? ");
            l_sbWhere.append(" and lot_result = ? ");

            l_lisQueryContainers.add(new Long(OrderStatusEnum.CANCELLED.intValue()));
            l_lisQueryContainers.add(WEB3LotResultDivDef.DEFAULT);

            Object[] l_objWhere = new Object[l_lisQueryContainers.size()];
            l_lisQueryContainers.toArray(l_objWhere);

            List l_lisRecords = null;
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();

                l_lisRecords = l_processor.doFindAllQuery(
                    IpoOrderRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objWhere);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //　@１－２）　@if 検索結果が0件の場合、例外をスローする。
            //エラーメッセージ「抽選対象顧客が存在しません。」
            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                log.debug("抽選対象顧客が存在しません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02312,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "抽選対象顧客が存在しません。");
            }
        }
        else if (WEB3LotDivDef.ADVANCED_LOTTERY.equals(l_strLotDiv))
        {
            //２－１）　@doFindAllQuery()を使用して以下の条件で、IPO申告テーブルを検索する。
            //[検索対象テーブル]
            //IPO申告テーブル
            //[検索条件]
            //銘柄ID = 引数:銘柄ID and
            //部店ID in (引数:部店IDリスト[0],引数:部店IDリスト[1]･･･※) and
            //抽選結果 = "補欠" and
            //購入申込区分 != "辞退" and
            //抽選結果（繰上） = "DEFAULT(未抽選)"
            l_sbWhere.append(" ipo_product_id = ? ");
            l_lisQueryContainers.add(l_strProductId);

            l_sbWhere.append(" and branch_id in (");
            for (int i = 0; i < l_intBranchIdLength; i++)
            {
                if (i == (l_intBranchIdLength - 1))
                {
                    l_sbWhere.append(" ? ");
                }
                else
                {
                    l_sbWhere.append(" ?, ");
                }
                l_lisQueryContainers.add((Long)l_lisBranchIds.get(i));
            }
            l_sbWhere.append(")");

            l_sbWhere.append(" and lot_result = ? ");
            l_sbWhere.append(" and offering_div <> ? ");
            l_sbWhere.append(" and lot_result_retry = ? ");

            l_lisQueryContainers.add(WEB3LotResultDivDef.SUPPLEMENT);
            l_lisQueryContainers.add(WEB3OfferingDivDef.REFUSAL);
            l_lisQueryContainers.add(WEB3LotResultDivDef.DEFAULT);

            Object[] l_objWhere = new Object[l_lisQueryContainers.size()];
            l_lisQueryContainers.toArray(l_objWhere);

            List l_lisRecords = null;
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();

                l_lisRecords = l_processor.doFindAllQuery(
                    IpoOrderRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objWhere);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //２－２）　@if 検索結果が0件の場合、例外をスローする。
            //エラーメッセージ「補欠データが存在しません。」
            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                log.debug("補欠データが存在しません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02333,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "補欠データが存在しません。");
            }
        }
        log.exiting(STR_METHOD_NAME); 
    }
    
    /** 
     * (validate割当入力項目)
     * 入力項目の数値チェックをおこなう。<BR>
     * <BR>
     * １）　@nullチェック<BR>
     * 　@１－１）　@if リクエストオブジェクト.割当総枠数量　@= null の場合、<BR>
     * 　@　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@　@エラーメッセージ「割当総枠数量が数値以外です。」<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02314<BR>
     * <BR>
     * 　@１－２）　@if リクエストオブジェクト.割当上限数量 = null の場合、<BR>
     * 　@　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@　@エラーメッセージ「割当上限数量が数値以外です。」<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02315 <BR>
     * <BR>
     * 　@引数:抽選区分 = "新規抽選"の場合のみ、１－３）～１－４）の処理を実行<BR>
     * <BR>
     * 　@１－３）　@if　@リクエストオブジェクト.補欠割当総枠数量　@= null の場合、<BR>
     * 　@　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@　@エラーメッセージ「補欠割当総枠数量が数値以外です。」<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02316 <BR>
     * <BR>
     * 　@１－４）　@if リクエストオブジェクト.補欠割当上限数量　@= null の場合、<BR>
     * 　@　@　@　@　@　@　@例外をスロー<BR>
     * 　@　@　@　@　@　@　@エラーメッセージ「補欠割当上限数量が数値以外です。」<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02317 <BR>
     * <BR>
     * ２）　@割当可能数量超過チェック<BR>
     * 　@this.get割当可能数量（リクエストオブジェクト , <BR>
     * 　@IPO銘柄オブジェクト , 抽選区分） ＜<BR>
     * 　@リクエストオブジェクト.割当総枠数量の場合、例外をスローする。<BR>
     * 　@　@<BR>
     * 　@エラーメッセージ「割当総枠数量が割当可能数量を超過しています。」<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02313<BR>
     * <BR>
     * ３）　@単位数量チェック<BR>
     * <BR>
     * 　@３－１）　@IPO注文チェッククラスのオブジェクトを生成する。<BR>
     * <BR>
     * 　@３－２）　@割当総枠数量のチェック　@<BR>
     * <BR>
     * 　@　@３－２－１）　@if リクエストオブジェクト.割当総枠数量　@!= 0 の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@以下を実行<BR>
     * 　@　@　@　@　@　@　@　@　@　@IPO注文クラスオブジェクト.validate数量<BR>
     * 　@　@　@　@　@　@　@　@　@　@（銘柄クラスオブジェクト , 数量）で<BR>
     * エラーが発生した場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@　@　@　@エラーメッセージ「割当総枠数量は、<BR>
     * 　@　@　@　@　@　@　@　@　@　@購入申込単位の整数倍で入力してください。」<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02319<BR>
     * <BR>
     * 　@３－３）　@割当上限数量のチェック<BR>
     * <BR>
     * 　@　@３－３－１）　@if リクエストオブジェクト.割当上限数量　@!= 0 の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@以下を実行<BR>
     * 　@　@　@　@　@　@　@　@　@　@IPO注文クラスオブジェクト.validate数量<BR>
     * 　@　@　@　@　@　@　@　@　@　@（銘柄クラスオブジェクト , 数量）で<BR>
     * エラーが発生した場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@　@　@　@エラーメッセージ「割当上限数量は、<BR>
     * 　@　@　@　@　@　@　@　@　@　@購入申込単位の整数倍で入力してください。」<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02320 <BR>
     * <BR>
     * 　@３－４）　@引数:抽選区分 = "新規抽選"の場合、補欠割当総枠数量、<BR>
     * 　@　@　@　@　@　@　@　@　@　@補欠割当上限数量のチェック<BR>
     * <BR>
     * 　@　@３－４－１）　@if リクエストオブジェクト.補欠割当総枠数量　@!= 0 の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@以下を実行<BR>
     * 　@　@　@　@　@　@　@　@　@　@IPO注文クラスオブジェクト.validate数量<BR>
     * 　@　@　@　@　@　@　@　@　@　@（銘柄クラスオブジェクト , 数量）で<BR>
     * エラーが発生した場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@　@　@　@エラーメッセージ「補欠割当総枠数量は、<BR>
     * 　@　@　@　@　@　@　@　@　@　@購入申込単位の整数倍で入力してください。」<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02321 <BR>
     * <BR>
     * 　@　@３－４－２）　@if リクエストオブジェクト.補欠割当上限数量　@!= 0 の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@以下を実行<BR>
     * 　@　@　@　@　@　@　@　@　@　@IPO注文クラスオブジェクト.validate数量<BR>
     * 　@　@　@　@　@　@　@　@　@　@（銘柄クラスオブジェクト , 数量）で<BR>
     * エラーが発生した場合、例外をスロー<BR>
     * 　@　@　@　@　@　@　@　@　@　@エラーメッセージ「補欠割当上限数量は、<BR>
     * 　@　@　@　@　@　@　@　@　@　@購入申込単位の整数倍で入力してください。」<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:  BUSINESS_ERROR_02333<BR>
     * @@param l_request - (リクエストオブジェクト)
     * @@param l_strLotDiv - (抽選区分)
     * @@param l_ipoProductImpl - (IPO銘柄)
     * @@throws WEB3BaseException
     */
    protected void validateAllotInputItem(
        WEB3AdminIPOLotConfirmRequest l_request,
        String l_strLotDiv, 
        WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAllotInputItem(" +
            " WEB3AdminIPOLotConfirmRequest," +
            " String," +
            " WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);

        //１）　@nullチェック 
        //１－１）if リクエストオブジェクト.割当総枠数量　@= null の場合、例外をスロー 
        //　@　@　@　@エラーメッセージ「割当総枠数量が数値以外です。」 
        if (l_request.allotTotalQuantity == null)
        {
            log.debug("割当総枠数量が数値以外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02314,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "割当総枠数量が数値以外です。");
        }
        
        //１－２）if リクエストオブジェクト.割当上限数量 = null の場合、例外をスロー 
        //　@　@　@  エラーメッセージ「割当上限数量が数値以外です。」 
        if (l_request.allotLimitQuantity == null)
        {
            log.debug("割当上限数量が数値以外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02315,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "割当上限数量が数値以外です。");
        }
        
        //引数:抽選区分 = "新規抽選"の場合のみ、１－３）～１－４）の処理を実行 
        if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
        {
            //１－３）　@if　@リクエストオブジェクト.補欠割当総枠数量　@= null の場合、
            //          例外をスロー、エラーメッセージ「補欠割当総枠数量が数値以外です。」 
            if (l_request.waitingAllotTotalQuantity == null)
            {
                log.debug("補欠割当総枠数量が数値以外です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02316,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "補欠割当総枠数量が数値以外です。");
            }
            
            //１－４）　@if リクエストオブジェクト.補欠割当上限数量　@= null の場合、
            //    例外をスロー 、エラーメッセージ「補欠割当上限数量が数値以外です。」 
            if (l_request.waitingAllotLimitQuantity == null)
            {
                log.debug("補欠割当上限数量が数値以外です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02317,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "補欠割当上限数量が数値以外です。");
            }
        }
            
        //２）　@割当可能数量超過チェック 
        //this.get割当可能数量
        //（リクエストオブジェクト , IPO銘柄オブジェクト , 抽選区分） ＜ 
        //リクエストオブジェクト.割当総枠数量の場合、例外をスローする。 
        //エラーメッセージ「割当総枠数量が割当可能数量を超過しています。」 
        long l_lngAllotQuantity = this.getAllotAbleQuantity(l_request, l_ipoProduct, l_strLotDiv);
        
        if (l_lngAllotQuantity < Long.parseLong(l_request.allotTotalQuantity))
        {
            log.debug("割当総枠数量が割当可能数量を超過しています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02313,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "割当総枠数量が割当可能数量を超過しています。");
        }
        
        //３）　@単位数量チェック 
        //３－１）　@IPO注文チェッククラスのオブジェクトを生成する。
        WEB3IpoOrderValidator l_ipoOrderValidator = new WEB3IpoOrderValidator();
        
        //　@　@３－２－１）　@if リクエストオブジェクト.
        //割当総枠数量　@!= 0 の場合、以下を実行 
        //IPO注文クラスオブジェクト.
        //validate数量（銘柄クラスオブジェクト , 数量）で 
        //エラーが発生した場合、例外をスロー 
        //エラーメッセージ「割当総枠数量は、購入申込単位の整数倍で入力してください。」 
        double l_dblAllotTotalQuantity = Double.parseDouble(l_request.allotTotalQuantity);
        if (l_dblAllotTotalQuantity != 0)
        {
            try
            {
                l_ipoOrderValidator.validateQuantity(l_ipoProduct, l_dblAllotTotalQuantity);
            }
            catch (WEB3BusinessLayerException l_ble)
            {
                log.error("割当総枠数量は、購入申込単位の整数倍で入力してください。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02319, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "割当総枠数量は、購入申込単位の整数倍で入力してください。", 
                    l_ble);
            } 
        }
        
        //３－３）　@割当上限数量のチェック 
        //３－３－１）　@if リクエストオブジェクト.割当上限数量　@!= 0 の場合、
        //以下を実行 IPO注文クラスオブジェクト.validate数量（
        //銘柄クラスオブジェクト , 数量）で エラーが発生した場合、例外をスロー 
        //エラーメッセージ「割当上限数量は、購入申込単位の整数倍で入力してください。」 
        double l_dblAllotLimitQuantity = Double.parseDouble(l_request.allotLimitQuantity);
        if (l_dblAllotLimitQuantity != 0)
        {
            try
            {
                l_ipoOrderValidator.validateQuantity(l_ipoProduct, l_dblAllotLimitQuantity);
            }
            catch (WEB3BusinessLayerException l_ble)
            {
                log.error("割当上限数量は、購入申込単位の整数倍で入力してください。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02320, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "割当上限数量は、購入申込単位の整数倍で入力してください。", 
                    l_ble);
            }
        }
        
        //３－４）　@引数:抽選区分 = "新規抽選"の場合、補欠割当総枠数量、
        //          補欠割当上限数量のチェック 
        if (WEB3LotDivDef.OPEN_LOTTERY.equals(l_strLotDiv))
        {
            //３－４－１）　@if リクエストオブジェクト.補欠割当総枠数量　@!= 0 の場合、
            //以下を実行 IPO注文クラスオブジェクト.validate数量（
            //銘柄クラスオブジェクト , 数量）で エラーが発生した場合、例外をスロー 
            //エラーメッセージ「補欠割当総枠数量は、購入申込単位の整数倍で入力してください。」
            double l_dblWaitingAllotTotalQuantity = Double.parseDouble(l_request.waitingAllotTotalQuantity);
            if (l_dblWaitingAllotTotalQuantity != 0)
            {
                try
                {
                    l_ipoOrderValidator.validateQuantity(l_ipoProduct, l_dblWaitingAllotTotalQuantity);
                }
                catch (WEB3BusinessLayerException l_ble)
                {
                    log.error("補欠割当総枠数量は、購入申込単位の整数倍で入力してください。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02321, 
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "補欠割当総枠数量は、購入申込単位の整数倍で入力してください。", 
                        l_ble);
                }
            }
            
            //３－４－２）　@if リクエストオブジェクト.補欠割当上限数量　@!= 0 の場合、
            //以下を実行 IPO注文クラスオブジェクト.validate数量（
            //銘柄クラスオブジェクト , 数量）で エラーが発生した場合、例外をスロー 
            //エラーメッセージ「補欠割当上限数量は、購入申込単位の整数倍で入力してください。」
            double l_dblWaitingAllotLimitQuantity = Double.parseDouble(l_request.waitingAllotLimitQuantity);
            if (l_dblWaitingAllotLimitQuantity != 0)
            {
                try
                {
                    l_ipoOrderValidator.validateQuantity(l_ipoProduct, l_dblWaitingAllotLimitQuantity);
                }
                catch (WEB3BusinessLayerException l_ble)
                {
                    log.error("補欠割当上限数量は、購入申込単位の整数倍で入力してください。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02322, 
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "補欠割当上限数量は、購入申込単位の整数倍で入力してください。", 
                        l_ble);
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * (validate異常終了)<BR>
     * 抽選処理が異常終了か否かをチェックするメソッド。<BR>
     * <BR>
     * １）以下の条件で、doFindAllQuery（）を使用してIPO抽選テーブルを検索する。<BR>
     * <BR>
     * [検索対象テーブル]
     * IPO抽選テーブル
     * <BR>
     * [検索条件]<BR>
     * 銘柄ID = 引数:銘柄ID<BR>
     * 抽選区分 = 引数:抽選区分<BR>
     * 処理状況 in ("11：確定入力","12：デーモン受付","13：確定開始","14：確定終了")<BR>
     * ステータス = "9：異常終了"<BR>
     * <BR>
     * ２）　@１）で取得した検索結果が１件以上の場合、例外をスローする。<BR>
     * <BR>
     * 　@エラーメッセージ「抽選処理中にエラーが発生しました。」
     * @@param l_strProductId - 銘柄ID
     * @@param l_strLotDiv - (抽選区分)
     * 新規/繰上抽選区分。<BR>
     * <BR>
     * 　@１：新規抽選<BR>
     * 　@２：繰上抽選<BR>
     * @@return long
     * @@throws WEB3BaseException
     */
    protected void validateAbnormalEnd(String l_strProductId, String l_strLotDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAbnormalEnd(String , String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRecords = new ArrayList();

        try
        {
            //１）　@以下の条件で、doFindAllQuery（）を使用してIPO抽選テーブルを検索する。
            QueryProcessor l_processor = Processors.getDefaultProcessor();  

            StringBuffer l_sbWhere = new StringBuffer();

            // 銘柄ID
            l_sbWhere.append(" ipo_product_id = ? ");   
            // 抽選区分 
            l_sbWhere.append(" and bb_div = ? ");       
            // 処理状況
            l_sbWhere.append(" and process in (?, ?, ?, ?)");
            // ステータス
            l_sbWhere.append(" and status = ? ");

            Object[] l_objWhere = {
                l_strProductId,
                l_strLotDiv,
                WEB3IPOBookBuldingProcDef.CONFIRM_INPUT,
                WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT,
                WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN,
                WEB3IPOBookBuldingProcDef.CONFIRM_END,
                WEB3IPOBookBuldingStatusDef.ABNORMAL_END
                };

            l_lisRecords = l_processor.doFindAllQuery(
                IpoBookbuildingRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dfe.getMessage(), 
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dqe.getMessage(), 
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DBへのアクセスに失敗しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_dne.getMessage(), 
                l_dne);
        }

        //２）　@１）で取得した結果が1件以上の場合、例外をスローする。
        int l_intLength = l_lisRecords.size();

        if (l_intLength != 0)
        {
            log.debug("抽選処理中にエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02310,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "抽選処理中にエラーが発生しました。");
        }
    }
     
   /** 
    * (validate抽選状況)
    * 抽選状況の確認を行い、エラー画面を表示する場合は例外を<BR>
    *   スローする。<BR>
    * <BR>
    * 　@１）　@引数.IPO抽選Rowから処理状況を取得<BR>
    * <BR>
    * 　@２－２）　@引数.IPO抽選Rowからステータスを取得<BR>
    * <BR>
    * 　@２－３）　@if 処理状況 in （"01：抽選入力","02：デーモン受付",<BR>
    *   "03：抽選開始",<BR>
    * 　@"11：確定入力","12：デーモン受付","13：確定開始"）　@かつ<BR>
    * 　@ステータスが"9：異常終了"の場合、例外をスローする。<BR>
    *              class: WEB3BusinessLayerException<BR>
    *              tag:   BUSINESS_ERROR_02310<BR><BR>
    * <BR>
    * 　@エラーメッセージ「抽選処理中にエラーが発生しました。」<BR>
    * <BR>
    * 　@２－４）　@if 処理状況 in （"11：確定入力","12：デーモン受付",<BR>
    *    "13：確定開始","14：確定終了"）　@かつ<BR>
    * 　@ステータス in ("0：正常終了","1：処理中")の場合、例外をスローする。<BR>
    *       class: WEB3BusinessLayerException<BR>
    *       tag:   BUSINESS_ERROR_02309<BR>
    * <BR>
    * 　@エラーメッセージ「抽選処理は結果確定中、<BR>
    *   もしくは結果確定が終了しています。」<BR>
    * @@param l_ipoBookbuildingRow - (IPO抽選Row)
    * @@throws WEB3BaseException
    */
    protected void validateLotState(IpoBookbuildingRow l_ipoBookbuildingRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateLotState(IpoBookbuildingRow)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@引数:IPO抽選Rowから処理状況を取得
        String l_strProcess = l_ipoBookbuildingRow.getProcess();
        
        //２－２）　@引数.IPO抽選Rowからステータスを取得
        String l_strStatus = l_ipoBookbuildingRow.getStatus();
        
        //２－３）　@if 処理状況 in （"01：抽選入力","02：デーモン受付",
        //   "03：抽選開始",
        // 　@"11：確定入力","12：デーモン受付","13：確定開始"）　@かつ
        // 　@ステータスが"9：異常終了"の場合、例外をスローする。
        if ((WEB3IPOBookBuldingProcDef.LOT_INPUT.equals(l_strProcess) ||
            WEB3IPOBookBuldingProcDef.LOT_DAEMON_ACCEPT.equals(l_strProcess) ||
            WEB3IPOBookBuldingProcDef.LOT_BEGIN.equals(l_strProcess) || 
            WEB3IPOBookBuldingProcDef.CONFIRM_INPUT.equals(l_strProcess) ||
            WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT.equals(l_strProcess) || 
            WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN.equals(l_strProcess)) &&
            (WEB3IPOBookBuldingStatusDef.ABNORMAL_END.equals(l_strStatus)))
        {
            log.debug("抽選処理中にエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02310,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "抽選処理中にエラーが発生しました。");
        }
        
        //２－４）　@if 処理状況 in （"11：確定入力","12：デーモン受付",
        //   "13：確定開始","14：確定終了"）　@かつ
        // 　@ステータス in ("0：正常終了","1：処理中")の場合、
        //   例外をスローする。
        if ((WEB3IPOBookBuldingProcDef.CONFIRM_INPUT.equals(l_strProcess) || 
            WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT.equals(l_strProcess) ||
            WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN.equals(l_strProcess) || 
            WEB3IPOBookBuldingProcDef.CONFIRM_END.equals(l_strProcess)) &&
            (WEB3IPOBookBuldingStatusDef.NORMAL_END.equals(l_strStatus) || 
            WEB3IPOBookBuldingStatusDef.DEALING.equals(l_strStatus)))
        {
            log.debug("抽選処理は結果確定中、もしくは結果確定が終了しています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02309,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "抽選処理は結果確定中、もしくは結果確定が終了しています。");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
   /** 
    * (validate確定状況)
    * 確定状況の確認を行い、エラー画面を表示する場合は例外を<BR>
    *  スローする。<BR>
    * <BR>
    * 　@１）　@引数:IPO抽選Rowから処理状況を取得<BR>
    * <BR>
    * 　@２）　@if 処理状況 != （"11：確定入力","12：デーモン受付",<BR>
    *"13：確定開始", "14：確定終了")の場合、例外をスローする。<BR>
    *              class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02323<BR>
    * エラーメッセージ「確定処理をおこなう情報がありません。」<BR>
    * @@param l_ipoBookbuildingRow - (IPO抽選Row)
    * @@throws WEB3BaseException
    */
    protected void validateConfirmState(IpoBookbuildingRow l_ipoBookbuildingRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateConfirmState(IpoBookbuildingRow)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@引数:IPO抽選Rowから処理状況を取得
        String l_strProcess = l_ipoBookbuildingRow.getProcess();
        
        //２）　@if 処理状況 != （"11：確定入力","12：デーモン受付",
        //"13：確定開始", "14：確定終了")の場合、例外をスローする。
        // エラーメッセージ「確定処理をおこなう情報がありません。」
        if (!WEB3IPOBookBuldingProcDef.CONFIRM_INPUT.equals(l_strProcess) && 
            !WEB3IPOBookBuldingProcDef.CONFIRM_BEGIN.equals(l_strProcess) && 
            !WEB3IPOBookBuldingProcDef.CONFIRM_END.equals(l_strProcess) &&  
            !WEB3IPOBookBuldingProcDef.CONFIRM_DAEMON_ACCEPT.equals(l_strProcess))
        {
            log.debug("確定処理をおこなう情報がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02323,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "確定処理をおこなう情報がありません。");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (cloneIPO抽選Rows)<BR>
     * 引数のIPO抽選Row［］をコピーして、 <BR>
     * 同じ内容の別インスタンスを作成し、返却する。 <BR>
     * <BR>
     * １）　@ArrayListクラスオブジェクト生成<BR>
     * <BR>
     * ２）　@引数:IPO抽選Row[]の要素分、Loop処理を行う。<BR>
     * <BR>
     * 　@２－１）　@IPO抽選Row cloneRow = new IPO抽選Params(IPO抽選Row[i])を行い、<BR>
     * 　@　@　@　@　@　@Rowオブジェクトを生成する。<BR>
     * <BR>
     * 　@２－２）　@ArrayListクラスオブジェクト.add(cloneRow);<BR>
     * <BR>
     * ３）　@ArrayListクラスオブジェクトをIPO抽選Row[]に変換する。<BR>
     * <BR>
     * ４）　@３）で変換したIPO抽選Row[]を返却する。<BR>
     * @@param l_ipoBookbuildingRows - IPO抽選Rows<BR>
     * IPO抽選Row[]オブジェクト。
     * @@return IPO抽選Row[]
     * @@throws WEB3BaseException
     */
    protected IpoBookbuildingRow[] cloneIpoBookBuildingRows(IpoBookbuildingRow[] l_ipoBookbuildingRows)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " cloneIpoBookBuildingRows(IpoBookbuildingRow[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoBookbuildingRows == null || l_ipoBookbuildingRows.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
        //１）　@ArrayListクラスオブジェクト生成
        List l_lisIpoBookBuildingRows = new ArrayList();
        
        int l_intLength = l_ipoBookbuildingRows.length;
        //２）　@引数:IPO抽選Row[]の要素分、Loop処理を行う。
        for (int i = 0; i < l_intLength; i++)
        {
            //２－１）　@IPO抽選Row cloneRow = new IPO抽選Params(IPO抽選Row[i])を行い、
            //　@　@　@　@　@Rowオブジェクトを生成する。
            IpoBookbuildingRow l_cloneRow = new IpoBookbuildingParams(l_ipoBookbuildingRows[i]);
            
            //２－２）　@ArrayListクラスオブジェクト.add(cloneRow);
            l_lisIpoBookBuildingRows.add(l_cloneRow);
        }
        
        //３）　@ArrayListクラスオブジェクトをIPO抽選Row[]に変換する。
        IpoBookbuildingRow[] l_cloneIpoBookBuildingRows = new IpoBookbuildingRow[l_intLength];
        
        //４）　@３）で変換したIPO抽選Row[]を返却する。
        l_lisIpoBookBuildingRows.toArray(l_cloneIpoBookBuildingRows);
        
        log.exiting(STR_METHOD_NAME);
        return l_cloneIpoBookBuildingRows;
    }
}
@
