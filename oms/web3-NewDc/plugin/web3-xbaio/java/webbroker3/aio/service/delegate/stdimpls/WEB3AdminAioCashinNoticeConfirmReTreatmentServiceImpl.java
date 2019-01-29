head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 入金通知確認再処理サービスImplクラス(WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImpl)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/1/20 黄建 (中訊) 新規作成
 Revesion History : 2006/8/24 車進 (中訊) 仕様変更 モデル 617、633、639、640
 Revesion History : 2006/11/09 徐宏偉(中訊) 仕様変更 モデル 681
 Revesion History : 2006/11/14 徐宏偉 (中訊)  ＤＢ更新仕様　@No.133
 Revesion History : 2007/06/19 柴双紅 (中訊) 仕様変更 モデルNo.726
 Revesion History : 2009/02/05 柴双紅 (中訊) モデルNo.1095、No.1104
 */

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.data.BankDepositNotifyPK;
import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.aio.data.BankDepositNotifyRow;
import webbroker3.aio.define.WEB3AioBankDepositNotifyCashTransferDivDef;
import webbroker3.aio.define.WEB3AioCurrencyCodeDef;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeConfirmResponse;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeInputRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeInputResponse;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeSearchRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeSearchResponse;
import webbroker3.aio.message.WEB3AioCashinNoticeUnit2;
import webbroker3.aio.message.WEB3ForeignSummaryInfo;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinNoticeConfirmReTreatmentService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BankDepositNotifyStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.ExclusiveUseAccountDao;
import webbroker3.gentrade.data.ExclusiveUseAccountRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (入金通知確認再処理サービスImpl)<BR>
 * 入金通知確認再処理サービスImpl クラス<BR>
 *
 * @@author 黄建(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImpl
implements WEB3AdminAioCashinNoticeConfirmReTreatmentService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImpl.class);
    
    /**
     * 入金連絡確認サービス処理を行う。<BR>
     * <BR>
     * 引数.リクエストの型によって <BR>
     * 以下のようにメソッドを呼ぶ。 <BR>
     * <BR>
     * １）リクエストが入金通知検索リクエストの場合 <BR>
     * this.get入金通知一覧 <BR>
     * <BR>
     * ２）リクエストが入金通知訂正入力画面リクエストの場合 <BR>
     * this.get入金通知訂正入力画面 <BR>
     * <BR>
     * ３）リクエストが入金通知訂正確認リクエストの場合 <BR>
     * this.validate入金通知訂正 <BR>
     * <BR>
     * ４）リクエストが入金通知訂正完了リクエストの場合 <BR>
     * this.submit入金通知訂正 <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410755600136
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）リクエストが入金通知検索リクエストの場合
        //this.get入金通知一覧
        
        if (l_request instanceof WEB3AdminAioCashinNoticeSearchRequest)
        {
            WEB3AdminAioCashinNoticeSearchResponse l_searchResponse =
                this.getCashinNoticeList(
                        (WEB3AdminAioCashinNoticeSearchRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_searchResponse;
        }
        //２）リクエストが入金通知訂正入力画面リクエストの場合
        //this.get入金通知訂正入力画面
        else if (l_request instanceof WEB3AdminAioCashinNoticeChangeInputRequest)
        {
            WEB3AdminAioCashinNoticeChangeInputResponse l_changeInputResponse =
                this.getCashinNoticeChangeInputScreen(
                        (WEB3AdminAioCashinNoticeChangeInputRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_changeInputResponse;
        }
        //３）リクエストが入金通知訂正確認リクエストの場合
        //this.validate入金通知訂正
        else if (l_request instanceof WEB3AdminAioCashinNoticeChangeConfirmRequest)
        {
            WEB3AdminAioCashinNoticeChangeConfirmResponse l_changeConfirmResponse =
                this.validateCashinNoticeChange(
                        (WEB3AdminAioCashinNoticeChangeConfirmRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_changeConfirmResponse;
        }
        //４）リクエストが入金通知訂正完了リクエストの場合
        //this.submit入金通知訂正
        else if (l_request instanceof WEB3AdminAioCashinNoticeChangeCompleteRequest)
        {
            WEB3AdminAioCashinNoticeChangeCompleteResponse l_changeCompleteResponse =
                this.submitCashinNoticeChange(
                        (WEB3AdminAioCashinNoticeChangeCompleteRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_changeCompleteResponse;
        }
        else
        {
            log.debug("パラメータタイプ不正");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get入金通知一覧)<BR>
     * 入金通知検索処理を行い一覧を返却する。 <BR>
     * <BR>
     * ※シーケンス図「（入金通知確認再処理）get入金通知一覧」 <BR>
     * 参照<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminAioCashinNoticeSearchResponse
     * @@roseuid 4108B52800FA
     */
    protected WEB3AdminAioCashinNoticeSearchResponse getCashinNoticeList(
            WEB3AdminAioCashinNoticeSearchRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCashinNoticeList(" +
        "WEB3AdminAioCashinNoticeSearchRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリーコード = 入金連携
        //is更新 = false
        l_administrator.validateAuthority(
                WEB3TransactionCategoryDef.BANK_COOPERATION,
                false);
        
        //1.4 リクエスト.部店コードの配列件数分LOOPする。
        //部店コード　@= リクエスト.部店コード
        for (int i = 0; i < l_request.branchCode.length; i++)
        {
            String l_strBranchCode = l_request.branchCode[i];
            
            //1.4.1 validate部店権限(部店コード : String[])
            l_administrator.validateBranchPermission(l_strBranchCode);
        }
        
        //1.5 create検索条件(入金通知検索リクエスト)
        String l_strSearchCond = this.createQueryCondition(l_request);
        
        //1.6 createソートキー(入金通知検索リクエスト)
        String l_strSortKey = this.createSortKey(l_request);
        
        //1.7 入金通知テーブルを検索。
        
        //検索条件 = create検索条件()の戻り値
        //ソートキー = createソートキー()の戻り値
        //ページ内表示行数 = リクエスト.ページ表示数
        //ページナンバー = リクエスト.ページ要求番号 - 1
        List l_lisBankDepositNotifyRows = null;
        
        Object[] l_objVars = this.createQueryContainer(l_request, l_administrator.getInstitutionCode());
        
        
        try
        {
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();
            
            l_lisBankDepositNotifyRows =
                l_queryProcessor.doFindAllQuery(
                        BankDepositNotifyRow.TYPE,
                        l_strSearchCond,
                        l_strSortKey,
                        null,
                        l_objVars);
            
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
        
        int l_intSize = 0;
        
        if (l_lisBankDepositNotifyRows != null || l_lisBankDepositNotifyRows.size() != 0)
        {
            l_intSize = l_lisBankDepositNotifyRows.size();
            log.debug("入金通知件数 = " + l_intSize);
            
            for(int i = 0; i < l_lisBankDepositNotifyRows.size(); i++)
            {
                log.debug("検索された入金通知レコード" + (BankDepositNotifyRow)l_lisBankDepositNotifyRows.get(i));
                
            }
        }
        
        List l_lisCashinNoticeUnit2 = new ArrayList();
        
        try
        {
            //1.8 検索された入金通知件数分LOOP
            for (int i = 0; i < l_intSize; i++)
            {
                BankDepositNotifyRow l_bankDepositNotifyRow =
                    (BankDepositNotifyRow)l_lisBankDepositNotifyRows.get(i);
                
                BankDepositNotifyParams l_bankDepositNotifyParams =
                    new BankDepositNotifyParams(l_bankDepositNotifyRow);
                
                //1.8.1 (リクエスト.処理区分 == null)　@OR
                //(リクエスト.処理区分 != null AND リクエスト.処理区分 = 入金通知レコード.処理区分)
                //の場合
                //create入金通知明細()の戻り値をArrayListに追加。
                if (l_request.transactionDiv == null ||
                        (l_request.transactionDiv != null &&
                                l_request.transactionDiv.equals(
                                        l_bankDepositNotifyParams.getStatus())))
                {
                    WEB3AioCashinNoticeUnit2 l_cashinNoticeUnit2 = null;
                    WEB3GentradeMainAccount l_mainAccount = null;
                    
                    
                    if(l_bankDepositNotifyParams.getBranchCode() != null &&
                            l_bankDepositNotifyParams.getAccountCode() != null)
                    {
                        
                        try
                        {
                            //1.8.1.1 get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
                            
                            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                            WEB3GentradeAccountManager l_accountManager =
                                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                            
                            l_mainAccount =
                                l_accountManager.getMainAccount(
                                        l_bankDepositNotifyParams.getInstitutionCode(),
                                        l_bankDepositNotifyParams.getBranchCode(),
                                        l_bankDepositNotifyParams.getAccountCode());
                        }
                        catch (WEB3BaseException l_ex)
                        {
                            //1.8.1.2 例外発生時
                            //顧客未存在エラー例外発生しても
                            //捕捉するだけでなにもしない。
                            log.debug("Not found mainAccount " +
                                    "証券会社コード = " + l_bankDepositNotifyParams.getInstitutionCode() +
                                    "部店コード = " + l_bankDepositNotifyParams.getBranchCode() +
                                    "口座コード = " + l_bankDepositNotifyParams.getAccountCode());
                            
                            log.debug("顧客未存在エラー例外発生", l_ex);
                        }
                    }
                    
                    //1.8.1.3 create入金通知明細(入金通知Params, 顧客)
                    l_cashinNoticeUnit2 =
                        this.createCashinNoticeUnit(
                                l_bankDepositNotifyParams,
                                l_mainAccount);
                    
                    l_lisCashinNoticeUnit2.add(l_cashinNoticeUnit2);
                    
                }
            }
            
            WEB3AioCashinNoticeUnit2[] l_cashinNoticeUnits2 =
                new WEB3AioCashinNoticeUnit2[l_lisCashinNoticeUnit2.size()];
            l_lisCashinNoticeUnit2.toArray(l_cashinNoticeUnits2);
            
            //ページ
            int l_intPageSize = Integer.parseInt(l_request.pageSize);
            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            
            WEB3PageIndexInfo l_pageIndexInfo =
                new WEB3PageIndexInfo(
                        l_cashinNoticeUnits2,
                        l_intPageIndex,
                        l_intPageSize);
            
            l_cashinNoticeUnits2 =
                (WEB3AioCashinNoticeUnit2[])l_pageIndexInfo.getArrayReturned(
                        WEB3AioCashinNoticeUnit2.class);
            
            // 1.9 レスポンスデータを生成する。
            WEB3AdminAioCashinNoticeSearchResponse l_response =
                (WEB3AdminAioCashinNoticeSearchResponse) l_request.createResponse();
            
            //1.10指定した証券会社が登録している通貨の通貨コードをすべて取得する。 
            String[] l_strCurrencyCodes = WEB3GentradeCurrency.getCurrencyCodeList(
                l_administrator.getInstitutionCode());
            
            //1.11サマリ情報を生成する。 
            this.createSummaryInfo(l_strCurrencyCodes, 
                l_lisBankDepositNotifyRows,
                l_response);
            
            
            // 1.12 プロパティセット
            //レスポンス.入金通知明細一覧 = ArrayList.toArray()の戻り値
            l_response.cashinNoticeList = l_cashinNoticeUnits2;   
            
            //レスポンス.表示ページ番号 = リクエスト.表示ページ番号
            l_response.pageIndex = l_request.pageIndex;
            
            //レスポンス.総ページ数 = ListPage(*).getTotalPages()
            l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
            
            //レスポンス.総レコード数 = ListPage(*).getTotalSize()
            l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
            
            //レスポンス.選択通貨コード = get通貨コード一覧（）の戻り値
            l_response.selectCurrencyCode = l_strCurrencyCodes;
            
            //(*)doFindAllQuery()の戻り値
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
            
        }
        catch(NumberFormatException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
    }
    
    /**
     * (get入金通知訂正入力画面)<BR>
     * 入金通知訂正画面表示処理を行う。 <BR>
     * <BR>
     * ※シーケンス図「（入金通知確認再処理）get入金通知訂正入力画面」参照<BR>
     * =============================================== <BR>
     * 　@　@具体位置 : 入金通知Params.処理区分 != "9：エラー"の場合、<BR>
     * 　@　@　@　@　@　@　@　@例外をthrowする<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02839<BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminAioCashinNoticeChangeInputResponse
     * @@roseuid 4108BA730187
     */
    protected WEB3AdminAioCashinNoticeChangeInputResponse getCashinNoticeChangeInputScreen(
            WEB3AdminAioCashinNoticeChangeInputRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCashinNoticeChangeInputScreen(" +
        "WEB3AdminAioCashinNoticeChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード = 入金通知確認再処理
        //is更新 = true
        
        l_administrator.validateAuthority(
                WEB3TransactionCategoryDef.BANK_COOPERATION,
                true);
        
        List l_lisBankDepositNotifyRows = null;
        List l_lisCashinNoticeUnit2 = new ArrayList();

        //入金通知テーブルから
        //リクエスト.入金通知ID(配列)の値を
        //以下のとおりに、or 条件に展開して検索。
        //[条件]
        //(銀行入金通知テーブルID = リクエスト.入金通知ID[index]の2桁目～最後
        //　@AND 証券会社コード = 管理者.get証券会社コード()
        //　@AND データ取込区分 = リクエスト.入金通知ID[index]の1桁目)
        //OR …
        List l_lisBindVars = new ArrayList();
        StringBuffer l_sbWhere = new StringBuffer();
        int l_intLength = 0;
        if (l_request.cashinNoticeTableId != null)
        {
            l_intLength = l_request.cashinNoticeTableId.length;
        }
        int l_intLengthShort = l_intLength - 1;
        for (int i = l_intLengthShort; i >= 0; i--)
        {
            // 証券会社コードを取得する。
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            //(銀行入金通知テーブルID = リクエスト.入金通知ID[index]の2桁目～最後
            long l_lngBankDepositNotifyId = Long.parseLong(l_request.cashinNoticeTableId[i].substring(1));

            //　@AND データ取込区分 = リクエスト.入金通知ID[index]の1桁目)
            String l_strDataLoadDiv = l_request.cashinNoticeTableId[i].substring(0 ,1);

            l_sbWhere.append(" (bank_deposit_notify_id = ? ");
            l_lisBindVars.add(new Long(l_lngBankDepositNotifyId));
            log.debug(" (bank_deposit_notify_id = ? " + new Long(l_lngBankDepositNotifyId));

            l_sbWhere.append(" and institution_code = ? ");
            l_lisBindVars.add(l_strInstitutionCode);
            log.debug(" and institution_code = ? " + l_strInstitutionCode);

            l_sbWhere.append(" and data_load_div = ?) ");
            l_lisBindVars.add(l_strDataLoadDiv);
            log.debug(" and data_load_div = ?) " + l_strDataLoadDiv);

            if (i != 0)
            {
                l_sbWhere.append("or ");
                log.debug("or ");
            }
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBankDepositNotifyRows =
                l_queryProcessor.doFindAllQuery(
                    BankDepositNotifyRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_lisBindVars.toArray());
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        //1.5 検索された入金通知レコード件数分LOOP
        for (Iterator l_iter = l_lisBankDepositNotifyRows.iterator(); l_iter.hasNext(); )
        {
            BankDepositNotifyRow l_bankDepositNotifyRow =
                (BankDepositNotifyRow)l_iter.next();
            BankDepositNotifyParams l_bankDepositNotifyParams =
                new BankDepositNotifyParams(l_bankDepositNotifyRow);

            //入金通知Params.処理区分 != "9：エラー"の場合、例外をthrowする
            if (!WEB3BankDepositNotifyStatusDef.ERROR.equals(l_bankDepositNotifyParams.getStatus()))
            {
                log.debug("処理済みのレコードが存在します。もう一度検索して下さい。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02839,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "処理済みのレコードが存在します。もう一度検索して下さい。");
            }

            WEB3GentradeMainAccount l_mainAccount = null;
            try
            {
                //1.5.1.1 get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)

                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                if (l_bankDepositNotifyParams.getAccountCode() != null)
                {
                    l_mainAccount =
                        l_accountManager.getMainAccount(
                            l_bankDepositNotifyParams.getInstitutionCode(),
                            l_bankDepositNotifyParams.getBranchCode(),
                            l_bankDepositNotifyParams.getAccountCode());
                }
            }
            catch (WEB3BaseException l_ex)
            {
                //1.5.1.2 例外発生時
                //顧客未存在エラー例外発生しても
                //捕捉するだけでなにもしない。
                log.debug("顧客未存在エラー例外発生", l_ex);
            }

            //1.5.1.3 create入金通知明細(入金通知Params, 顧客)
            WEB3AioCashinNoticeUnit2 l_cashinNoticeUnit2 =
                this.createCashinNoticeUnit(
                    l_bankDepositNotifyParams,
                    l_mainAccount);

            l_lisCashinNoticeUnit2.add(l_cashinNoticeUnit2);
        }

        WEB3AioCashinNoticeUnit2[] l_cashinNoticeUnits2 =
            new WEB3AioCashinNoticeUnit2[l_lisCashinNoticeUnit2.size()];
        l_lisCashinNoticeUnit2.toArray(l_cashinNoticeUnits2);

        // 1.6 レスポンスデータを生成する。
        WEB3AdminAioCashinNoticeChangeInputResponse l_response =
            (WEB3AdminAioCashinNoticeChangeInputResponse)l_request.createResponse();

        //1.7 (*)プロパティセット
        //入金通知明細一覧 = ArrayList.toArray()
        l_response.cashinNoticeList = l_cashinNoticeUnits2;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate入金通知訂正)<BR>
     * 入金通知訂正確認処理を行う。 <BR>
     * <BR>
     * ※シーケンス図「（入金通知確認再処理）validate入金通知訂正」参照<BR>
     * <BR>
     * =============================================== <BR>
     * 　@　@具体位置 : 入金通知Params.処理区分 != "9：エラー"の場合、<BR>
     * 　@　@　@　@　@　@　@　@例外をthrowする<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02839<BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3AdminAioCashinNoticeChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4109C8B50240
     */
    protected WEB3AdminAioCashinNoticeChangeConfirmResponse validateCashinNoticeChange(
            WEB3AdminAioCashinNoticeChangeConfirmRequest l_request) throws WEB3BaseException
            {
        final String STR_METHOD_NAME = "validateCashinNoticeChange(" +
        "WEB3AdminAioCashinNoticeChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード = "入金通知再処理"
        //is更新 = true
        
        l_administrator.validateAuthority(
                WEB3TransactionCategoryDef.BANK_COOPERATION,
                true);
        
        //1.4 StringBuffer( )
        //エラー文字列用に
        //StringBufferを生成する。
        StringBuffer l_strErrorWhere = new StringBuffer();
        
        //1.5 リクエスト.訂正入金通知明細の件数分LOOP
        int l_intCcashinNoticeList = l_request.cashinNoticeList.length;
        for (int i = 0; i < l_intCcashinNoticeList; i++)
        {
            List l_lisSqlValues = new ArrayList();
            StringBuffer l_sbSql = new StringBuffer();

            String l_strCashinNoticeTableId = l_request.cashinNoticeList[i].cashinNoticeTableId;
            boolean l_blnIsEmpty = WEB3StringTypeUtility.isEmpty(l_strCashinNoticeTableId);
            long l_lngBankDepositNotifyId = 0;
            String l_strDataLoadDiv = null;
            if (!l_blnIsEmpty)
            {
                l_lngBankDepositNotifyId =
                    Long.parseLong(l_strCashinNoticeTableId.substring(1));
                l_strDataLoadDiv =
                    l_strCashinNoticeTableId.substring(0, 1);
            }
            l_sbSql.append(" bank_deposit_notify_id = ? ");
            l_lisSqlValues.add(new Long(l_lngBankDepositNotifyId));

            //管理者.get証券会社コード()
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            l_sbSql.append(" and institution_code = ? ");
            l_lisSqlValues.add(l_strInstitutionCode);

            l_sbSql.append(" and data_load_div = ? ");
            l_lisSqlValues.add(l_strDataLoadDiv);

            List l_lisResults = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisResults =
                    l_queryProcessor.doFindAllQuery(
                        BankDepositNotifyRow.TYPE,
                        l_sbSql.toString(),
                        l_lisSqlValues.toArray());
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //入金通知Params.処理区分 != "9：エラー"の場合、例外をthrowする
            if (l_lisResults != null && !l_lisResults.isEmpty())
            {
                BankDepositNotifyRow l_bankDepositNotifyRow =
                    (BankDepositNotifyRow)l_lisResults.get(0);
                if (!WEB3BankDepositNotifyStatusDef.ERROR.equals(l_bankDepositNotifyRow.getStatus()))
                {
                    log.debug("処理済みのレコードが存在します。もう一度検索して下さい。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02839,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "処理済みのレコードが存在します。もう一度検索して下さい。");
                }
            }

            //リクエスト.訂正入金通知明細.部店コード
            String l_strBranchCode = l_request.cashinNoticeList[i].branchCode;
            //リクエスト.訂正入金通知明細.顧客コード
            String l_strAccountCode = l_request.cashinNoticeList[i].accountCode;
            
            try
            {
                //1.5.1 validate部店権限(部店コード : String[])
                //引数：
                //リクエスト.訂正入金通知明細.部店コード
                l_administrator.validateBranchPermission(l_strBranchCode);
                
                //1.5.2 get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
                //顧客を取得する。
                //引数：
                //証券会社コード = 管理者.get証券会社コード()
                //部店コード = リクエスト.訂正入金通知明細.部店コード
                //口座コード = リクエスト.訂正入金通知明細.顧客コード
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                l_accountManager.getMainAccount(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
            }
            catch (WEB3BaseException l_ex)
            {
                //1.5.3 例外発生時
                //エラー顧客文字列を追加する。
                //"["リクエスト.訂正入金通知明細.部店コード, リクエスト.訂正入金通知明細.顧客コード"]　@"
                //データ例
                //[307 111111][307 222222][308 111111]
                //1.5.3.1 append(arg0 : String)
                l_strErrorWhere.append("[");
                l_strErrorWhere.append(l_strBranchCode);
                l_strErrorWhere.append(" ");
                l_strErrorWhere.append(l_strAccountCode);
                l_strErrorWhere.append("]");
            }
        }
        
        //1.6 StringBuffer.length() > 0の場合
        //顧客未存在エラー例外
        //を生成しスローする。
        //引数：
        //エラー情報 = 顧客未存在エラー
        //エラーメソッド = "validate入金通知訂正"
        //エラーメッセージ = エラー顧客文字列
        if (l_strErrorWhere.length() > 0)
        {
            //1.6.1 WEB3BusinessLayerException(
            //  l_errorInfo : ErrorInfo, l_errorMethod : String, l_errorMessage : String)
            log.debug("顧客未存在エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorWhere.toString());
        }
        
        //1.7 createResponse( )
        WEB3AdminAioCashinNoticeChangeConfirmResponse l_response =
            (WEB3AdminAioCashinNoticeChangeConfirmResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
            }
    
    /**
     * (submit入金通知訂正)<BR>
     * 入金通知訂正完了処理を行う。 <BR>
     * <BR>
     * ※シーケンス図「（入金通知確認再処理）submit入金通知訂正」 参照 <BR>
     * <BR>
     * =============================================== <BR>
     * 　@　@具体位置 : 入金通知Params.処理区分 != "9：エラー"の場合、<BR>
     * 　@　@　@　@　@　@　@　@例外をthrowする<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02839<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3AdminAioCashinNoticeChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4109C8B50240
     */
    protected WEB3AdminAioCashinNoticeChangeCompleteResponse submitCashinNoticeChange(
            WEB3AdminAioCashinNoticeChangeCompleteRequest l_request) throws WEB3BaseException
            {
        final String STR_METHOD_NAME = "submitCashinNoticeChange(" +
        "WEB3AdminAioCashinNoticeChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード = "入金通知再処理"
        //is更新 = true
        l_administrator.validateAuthority(
                WEB3TransactionCategoryDef.BANK_COOPERATION,
                true);
        
        //1.4 ArrayList( )
        ArrayList l_lisCcashinNoticeList = new ArrayList();
        
        //1.5 HashTable( )
        Hashtable l_tblMainAccount = new Hashtable();
        
        //1.6 StringBuffer( )
        StringBuffer l_strErrorWhere = new StringBuffer();
        
        //1.7 リクエスト.訂正入金通知明細の件数分LOOP
        int l_intCcashinNoticeList = l_request.cashinNoticeList.length;
        
        MainAccount  l_mainAccount =  null;
        for (int i = 0; i < l_intCcashinNoticeList; i++)
        {
            List l_lisSqlValues = new ArrayList();
            StringBuffer l_sbSql = new StringBuffer();

            String l_strCashinNoticeTableId = l_request.cashinNoticeList[i].cashinNoticeTableId;
            boolean l_blnIsEmpty = WEB3StringTypeUtility.isEmpty(l_strCashinNoticeTableId);
            long l_lngBankDepositNotifyId = 0;
            String l_strDataLoadDiv = null;
            if (!l_blnIsEmpty)
            {
                l_lngBankDepositNotifyId =
                    Long.parseLong(l_strCashinNoticeTableId.substring(1));
                l_strDataLoadDiv =
                    l_strCashinNoticeTableId.substring(0, 1);
            }
            l_sbSql.append(" bank_deposit_notify_id = ? ");
            l_lisSqlValues.add(new Long(l_lngBankDepositNotifyId));

            //管理者.get証券会社コード()
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            l_sbSql.append(" and institution_code = ? ");
            l_lisSqlValues.add(l_strInstitutionCode);

            l_sbSql.append(" and data_load_div = ? ");
            l_lisSqlValues.add(l_strDataLoadDiv);

            List l_lisResults = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisResults =
                    l_queryProcessor.doFindAllQuery(
                        BankDepositNotifyRow.TYPE,
                        l_sbSql.toString(),
                        l_lisSqlValues.toArray());
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //入金通知Params.処理区分 != "9：エラー"の場合、例外をthrowする
            if (l_lisResults != null && !l_lisResults.isEmpty())
            {
                BankDepositNotifyRow l_bankDepositNotifyRow =
                    (BankDepositNotifyRow)l_lisResults.get(0);
                if (!WEB3BankDepositNotifyStatusDef.ERROR.equals(l_bankDepositNotifyRow.getStatus()))
                {
                    log.debug("処理済みのレコードが存在します。もう一度検索して下さい。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02839,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "処理済みのレコードが存在します。もう一度検索して下さい。");
                }
            }

            //リクエスト.訂正入金通知明細.部店コード
            String l_strBranchCode = l_request.cashinNoticeList[i].branchCode;
            //リクエスト.訂正入金通知明細.顧客コード
            String l_strAccountCode = l_request.cashinNoticeList[i].accountCode;
            try
            {
                //1.7.1 validate部店権限(部店コード : String[])
                //引数：
                //リクエスト.訂正入金通知明細.部店コード
                l_administrator.validateBranchPermission(l_strBranchCode);
                
                //1.7.2 get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                l_mainAccount = l_accountManager.getMainAccount(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
                
                //1.7.3 add(arg0 : Object)
                //ArrayListに
                //訂正入金通知明細をaddする。
                l_lisCcashinNoticeList.add(l_request.cashinNoticeList[i]);
                
                //1.7.4 put(arg0 : Object, arg1 : Object)
                l_tblMainAccount.put(l_request.cashinNoticeList[i].cashinNoticeTableId, l_mainAccount);
            }
            catch (WEB3BaseException l_ex)
            {
                //1.7.5 例外発生時
                //エラー顧客文字列を追加する。
                //"["リクエスト.訂正入金通知明細.部店コード,リクエスト.訂正入金通知明細.顧客コード"]　@"
                //データ例
                //[307 111111][307 222222][308 111111]
                //1.7.5.1 append(arg0 : String)
                l_strErrorWhere.append("[");
                l_strErrorWhere.append(l_strBranchCode);
                l_strErrorWhere.append(" ");
                l_strErrorWhere.append(l_strAccountCode);
                l_strErrorWhere.append("]");
            }
        }
        
        //1.7.5.2 StringBuffer.length() > 0の場合
        //顧客未存在エラー例外
        //を生成しスローする。
        //引数：
        //エラー情報 = 顧客未存在エラー
        //エラーメソッド = "submit入金通知訂正"
        //エラーメッセージ = エラー顧客文字列
        if (l_strErrorWhere.length() > 0)
        {
            //1.8.1 WEB3BusinessLayerException(
            //  l_errorInfo : ErrorInfo, l_errorMethod : String, l_errorMessage : String)
            log.debug("顧客未存在エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorWhere.toString());
        }
        
        //1.8 validate取引パスワード(パスワード : String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //1.9 ArrayListの件数分LOOP
        WEB3AioCashinNoticeUnit2[] l_cashinNoticeList =
            new WEB3AioCashinNoticeUnit2[l_lisCcashinNoticeList.size()];
        l_lisCcashinNoticeList.toArray(l_cashinNoticeList);
        int l_intSize = l_cashinNoticeList.length;
        for (int i = 0; i < l_intSize; i++)
        {
            //管理者.get管理者コード()
            String l_strAdministratorCode = l_administrator.getAdministratorCode();
            //訂正入金通知明細.入金通知ID
            String l_strCashinNoticeTableId = l_cashinNoticeList[i].cashinNoticeTableId;

            // 証券会社コードを取得する。
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            //(銀行入金通知テーブルID = リクエスト.入金通知ID[index]の2桁目～最後
            long l_lngBankDepositNotifyId = Long.parseLong(l_strCashinNoticeTableId.substring(1));

            //　@AND データ取込区分 = リクエスト.入金通知ID[index]の1桁目)
            String l_strDataLoadDiv = l_strCashinNoticeTableId.substring(0 ,1);

            //リクエスト.備考
            String l_strRemark = l_cashinNoticeList[i].remark;
            
            //1.9.1 get(arg0 : Object)
            //Hashtableから顧客オブジェクトを取得。
            //引数 ArrayListから取得した訂正入金通知明細.入金通知ID
            l_mainAccount = (MainAccount)l_tblMainAccount.get(l_cashinNoticeList[i].cashinNoticeTableId);
            
            //1.9.2 doUpdateQuery(arg0 : String, arg1 : PrimaryKey, arg2 : Map)
            //入金通知テーブルを
            //ArrayListから取得した
            //訂正入金通知明細.入金通知テーブルIDで
            //以下のとおりに指定し更新。
            //[条件]
            //銀行入金通知テーブルID = 訂正入金通知明細.入金通知テーブルIDの2桁目～最後
            //証券会社コード = 管理者.get証券会社コード()
            //データ取込区分 = 訂正入金通知明細.入金通知テーブルIDの1桁目
            //[変更内容]
            //部店コード = 顧客.部店コード
            //顧客コード = 顧客.顧客コード
            //備考 = リクエスト.備考
            //処理区分 = 未処理
            //更新者 = 管理者.get管理者コード()
            //更新日時 = システム日付
            Map l_mapSpac = new HashMap();
            l_mapSpac.put("branch_code", l_mainAccount.getBranch().getBranchCode());
            l_mapSpac.put("account_code", l_mainAccount.getAccountCode());
            l_mapSpac.put("remark", l_strRemark);
            l_mapSpac.put("status", WEB3StatusDef.NOT_DEAL);
            l_mapSpac.put("update_person", l_strAdministratorCode);
            l_mapSpac.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                BankDepositNotifyPK l_bankDepositNotifypk =
                    new BankDepositNotifyPK(
                        l_lngBankDepositNotifyId,
                        l_strInstitutionCode,
                        l_strDataLoadDiv);
                l_queryProcessor.doUpdateQuery(
                    l_bankDepositNotifypk,
                    l_mapSpac);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
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
        
        //1.10 createResponse( )
        WEB3AdminAioCashinNoticeChangeCompleteResponse l_response =
            (WEB3AdminAioCashinNoticeChangeCompleteResponse) l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
            }
    
    /**
     * (create検索条件)<BR>
     * 入金通知テーブルの検索条件文字列を作成し返却する。<BR>
     * <BR>
     * １）証券会社コードを条件に追加する<BR>
     * <BR>
     * ２）以下を部店コードの条件とする<BR>
     * ２－１）リクエスト.部店コード<BR>
     * ２－２）部店コード  is nullを２－１）のor 条件で追加する。<BR>
     * <BR>
     * ３）以下の条件がnullでない場合<BR>
     * リクエスト.顧客コードand条件にて追加する。<BR>
     * リクエスト.振込依頼人コード<BR>
     * リクエスト.勘定日<BR>
     * <BR>
     * ４）処理日時(FROM)がnullでない<BR>
     * 処理日時 <= 更新日時　@場合<BR>
     * をand条件にて追加する。<BR>
     * <BR>
     * ５）処理日時(TO)がnullでない場<BR>
     * 更新日時 <= 処理日時(TO)合<BR>
     * をand条件にて追加する。<BR>
     * <BR>
     * ６）通貨コードが ”全て” 以外の場合、<BR> 
     * リクエスト.通貨コードがnullの場合、通貨コード is null <BR>
     * リクエスト.通貨コード != nullの場合、通貨コード = リクエスト.通貨コード <BR>
     * をand条件にて追加する。 <BR>
     * <BR>
     * ７）作成した文字列を返却する。<BR>
     * <BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4109C8B50240
     */
    protected String createQueryCondition(WEB3AdminAioCashinNoticeSearchRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryCondition(" +
            "WEB3AdminAioCashinNoticeSearchRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        StringBuffer l_strSearchCondition = new StringBuffer();
        
        //１）証券会社コードを条件に追加する
        l_strSearchCondition.append("institution_code = ? ");
        
        //２）以下を部店コードの条件とする
        //２－１）リクエスト.部店コードを配列件数分in条件に展開する。
        //２－２）部店コード  is nullを  ２－１）のor 条件で追加する。
        l_strSearchCondition.append( " and (branch_code is null or branch_code in ( ? ");
        
        for (int i = 1; i < l_request.branchCode.length; i++)
        {
            l_strSearchCondition.append(" , ? ");
        }
        
        l_strSearchCondition.append( " )) ");
        
        
        // ３）以下の条件がnullでない場合and条件にて追加する。
        //リクエスト.顧客コード
        //リクエスト.振込依頼人コード
        //リクエスト.勘定日
        if (l_request.accountCode != null)
        {
            l_strSearchCondition.append(" and account_code like ? ");
        }
        if (l_request.clientCode != null)
        {
            l_strSearchCondition.append(" and deposit_data_trans_person_code = ? ");
        }
        if (l_request.settlementDate != null)
        {
            l_strSearchCondition.append(" and deposit_data_account_date = ? ");
        }
        
        // ４）処理日時(FROM)がnullでない場合
        // 処理日時 <= 更新日時
        // をand条件にて追加する。
        if (l_request.transactionDateFrom != null)
        {
            l_strSearchCondition.append(" and last_updated_timestamp >= ? ");
        }
        
        // ５）処理日時(TO)がnullでない場合
        // 更新日時 <= 処理日時(TO)
        // をand条件にて追加する。
        if (l_request.transactionDateTo != null)
        {
            l_strSearchCondition.append(" and last_updated_timestamp <= ? ");
        }
        
        // ６）通貨コードが ”全て” 以外の場合
        // リクエスト.通貨コードがnullの場合、通貨コード is null
        // リクエスト.通貨コード != nullの場合、通貨コード = リクエスト.通貨コード 
        if (!WEB3AioCurrencyCodeDef.ALL.equals(l_request.currencyCode))
        {
            //リクエスト.通貨コードがnullの場合、通貨コード is null
            if (l_request.currencyCode == null)
            {
                l_strSearchCondition.append(" and currency_code is null ");
                
            //リクエスト.通貨コード != nullの場合、通貨コード = リクエスト.通貨コード 
            }
            else
            {
                l_strSearchCondition.append(" and currency_code = ? ");
            }
        }
        log.exiting(STR_METHOD_NAME);
        
        //７）作成した文字列を返却する。
        return l_strSearchCondition.toString();
    }
    
    /**
     * (createソートキー)<BR>
     * 入金通知テーブルのソート順文字列を作成し返却する。<BR>
     * <BR>
     * １）リクエスト.入金通知ソートキーの配列件数分LOOPする。<BR>
     * <BR>
     * １－１）入金通知ソートキー.createSortKeySpec()がnullでない場合<BR>
     * <BR>
     * ソートキー文字列 = 入金通知明細ソート.createSortKeySpec()の戻り値<BR>
     * <BR>
     * １－２）件数が0件以上の場合　@<BR>
     * ソートキー文字列をカンマでつなぐ。<BR>
     * <BR>
     * ２）文字列を返却する。<BR>
     * <BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4109C8B50240
     */
    protected String createSortKey(WEB3AdminAioCashinNoticeSearchRequest l_request)
    {
        final String STR_METHOD_NAME = "createSortKey(" +
        "WEB3AdminAioCashinNoticeSearchRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strSortKey = "";
        
        if (l_request.sortKeys.length > 0)
        {
            //１）リクエスト.入金通知ソートキーの配列件数分LOOPする。
            for (int i = 0; i < l_request.sortKeys.length; i++)
            {
                // １－１）入金通知ソートキー.createSortKeySpec()がnullでない場合
                //ソートキー文字列 = 入金通知明細ソート.createSortKeySpec()の戻り値
                if (l_request.sortKeys[i].createSortKeySpec() != null)
                {
                    l_strSortKey = l_strSortKey +
                    l_request.sortKeys[i].createSortKeySpec() + " ,";
                }
            }
            // １－２）件数が0件以上の場合
            //　@ソートキー文字列を" ,"でつなぐ。
            l_strSortKey = l_strSortKey.substring(0, l_strSortKey.length() - 1);
        }
        log.exiting(STR_METHOD_NAME);
        return l_strSortKey;
    }
    
    /**
     * (create入金通知明細)<BR>
     * 入金通知明細を作成し<BR>
     * 以下のようにセットする。<BR>
     * <BR>
     * 入金通知ID = 入金通知Params.データ取込区分 + 入金通知Params.入金通知ID<BR>
     * <BR>
     * (*)引数.顧客 != nullの場合のみ、顧客がnullの場合はnull<BR>
     * 部店コード = 顧客.get部店コード()の戻り値(*)<BR>
     * 顧客コード = 顧客.get表示顧客コード()の戻り値(*)<BR>
     * 銀行名 = 専用振込先口座Params.銀行名(*)<BR>
     * 支店名 = 専用振込先口座Params.支店名(*)<BR>
     * 顧客名 = 顧客.名前（苗字1） (*)<BR>
     * <BR>
     * 振込依頼人コード = 入金通知Params.振込依頼人コード<BR>
     * 振込依頼人名 = 入金通知Params.振込依頼人名<BR>
     * 金額 = 入金通知Params.金額<BR>
     * 勘定日 = 入金通知Params.勘定日を西暦日付に変換した値<BR> 
     * (①@年号.getJapEraDiv(入金通知Params.勘定日)にて年号を取得する<BR>
     * ②年号.toDate(年号(=取得した年号)，<BR>
     * 　@　@和暦文字列(=入金通知Params.勘定日))の戻り値)<BR>
     * <BR>
     * 振込銀行名 = 入金通知Params.振込銀行名<BR>
     * 振込支店名 = 入金通知Params.振込支店名<BR>
     * <BR>
     * 処理日時 = 入金通知Params.更新日時<BR>
     * 処理区分 = 入金通知Params.処理区分<BR>
     * <BR>
     * 備考 = 入金通知Params.備考(nullの場合,入金通知Params.エラーコメントをセット）<BR>
     * <BR>
     * 入払区分 = 入金通知Params.入払区分<BR>
     * 取引区分 = 入金通知Params.取引区分<BR>
     * <BR>
     * 通貨コード. = 入金通知Params.通貨コード<BR>
     * @@param l_bankDepositNotifyParams - (入金通知Params)
     * @@param l_mainAccount -(顧客)
     * @@return WEB3AioCashinNoticeUnit2
     * @@roseuid 4109C8B50240
     */
    protected WEB3AioCashinNoticeUnit2 createCashinNoticeUnit(
        BankDepositNotifyParams l_bankDepositNotifyParams,
        WEB3GentradeMainAccount l_mainAccount)
    {
        final String STR_METHOD_NAME = "createCashinNoticeUnit(" +
        "BankDepositNotifyParams, WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bankDepositNotifyParams == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AioCashinNoticeUnit2 l_aioCashinNoticeUnit2 =
            new WEB3AioCashinNoticeUnit2();

        //入金通知ID = 入金通知Params.データ取込区分 + 入金通知Params.入金通知ID
        l_aioCashinNoticeUnit2.cashinNoticeTableId =
            l_bankDepositNotifyParams.getDataLoadDiv()
            + l_bankDepositNotifyParams.getBankDepositNotifyId() + "";

        //部店コード = 顧客.get部店コード()の戻り値(*)
        //顧客コード = 顧客.get表示顧客コード()の戻り値(*)
        //顧客名 = 顧客.名前（苗字1） (*)
        //銀行名 = 専用振込先口座.銀行名 (*)
        //支店名 = 専用振込先口座.支店名 (*)
        // (*)引数.顧客 != nullの場合のみ、顧客がnullの場合はnull
        if (l_mainAccount != null)
        {
            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_mainAccount.getDataSourceObject();
            //部店コード = 顧客.get部店コード()の戻り値
            l_aioCashinNoticeUnit2.branchCode =  l_mainAccountRow.getBranchCode();
            // 顧客コード = 顧客.get表示顧客コード()の戻り値
            l_aioCashinNoticeUnit2.accountCode =  l_mainAccount.getDisplayAccountCode();
            // 顧客名 = 顧客.名前（苗字1）
            l_aioCashinNoticeUnit2.accountName =
                l_mainAccountRow.getFamilyNameAlt1();
            
            try
            {
                //専用振込先口座テーブルより検索。
                ExclusiveUseAccountRow l_exUseAccountRow = ExclusiveUseAccountDao.findRowByPk(l_mainAccountRow.getAccountId());
                
                //銀行名 = 専用振込先口座.銀行名 (*)
                l_aioCashinNoticeUnit2.financialInstitutionName =
                    l_exUseAccountRow.getFinInstitutionName();
                
                //支店名 = 専用振込先口座.支店名 (*)
                l_aioCashinNoticeUnit2.financialBranchName =
                    l_exUseAccountRow.getFinBranchName();
            }   
            //例外発生時はcatchし振込口座銀行名、振込先口座支店名の値はnullのままとする。
            catch (DataQueryException l_ex)
            {                
                log.debug("__DataQueryException__(専用振込口座):::::エラー発生した入金通知レコード=" 
                        + l_bankDepositNotifyParams, l_ex);                
            }
            catch (DataNetworkException l_ex)
            {
                log.debug("__DataNetworkException__(専用振込口座):::::エラー発生した入金通知レコード=" 
                        + l_bankDepositNotifyParams, l_ex);                
            }            
        }
        else
        {
            // 部店コード = 入金通知Params.部店コード(null)
            l_aioCashinNoticeUnit2.branchCode =  l_bankDepositNotifyParams.getBranchCode();
            // 顧客コード= 入金通知Params.顧客コード(null)
            String l_strAccountCode = l_bankDepositNotifyParams.getAccountCode();
            if(l_strAccountCode != null)
            {
                l_aioCashinNoticeUnit2.accountCode = l_strAccountCode.substring(0,6);                
            }
            
        }
        
        try
        {
            // 振込依頼人コード = 入金通知Params.振込依頼人コード
            l_aioCashinNoticeUnit2.clientCode = l_bankDepositNotifyParams.getDepositDataTransPersonCode();
            
            // 金額 =  入金通知Params.金額            
            double l_dblPrice = Double.parseDouble(l_bankDepositNotifyParams.getDepositDataDepositAmount());
            l_aioCashinNoticeUnit2.price = WEB3StringTypeUtility.formatNumber(l_dblPrice);
            
        }
        catch(NumberFormatException l_nfe)
        {
            log.debug("__NumberFormatException__(専用振込口座):::::エラー発生した入金通知レコード="
                    + l_bankDepositNotifyParams, l_nfe);                                      
        }
        
        // 振込依頼人名 = 入金通知Params.振込依頼人名
        l_aioCashinNoticeUnit2.clientName =
            l_bankDepositNotifyParams.getDepositDataTransPersonName();
        
        //勘定日 = 入金通知Params.勘定日を西暦日付に変換した値
        //(①@年号.getJapEraDiv(入金通知Params.勘定日)にて年号を取得する
        //②年号.toDate(年号(=取得した年号)，
        //  和暦文字列(=入金通知Params.勘定日))の戻り値)
        String l_strDepositDataAccountDate =
            l_bankDepositNotifyParams.getDepositDataAccountDate();
        l_aioCashinNoticeUnit2.settlementDate =
            WEB3GentradeEra.toDate(
                WEB3GentradeEra.getJapEraDiv(l_strDepositDataAccountDate),
                l_strDepositDataAccountDate);
                
        // 銀行コード = 入金通知Params.銀行コード
        l_aioCashinNoticeUnit2.financialInstitutionCode =
            l_bankDepositNotifyParams.getBankCode();
        
        // 支店コード = 入金通知Params.支店コード
        l_aioCashinNoticeUnit2.financialBranchCode =
            l_bankDepositNotifyParams.getBankBranchCode();
        
        // 振込銀行名 = 入金通知Params.仕向銀行名
        l_aioCashinNoticeUnit2.transferFinancialInstitutionName =
            l_bankDepositNotifyParams.getDeliveredBankName();
        
        // 振込支店名 = 入金通知Params.銀行支店名
        l_aioCashinNoticeUnit2.transferFinancialBranchName =
            l_bankDepositNotifyParams.getDeliveredBankBranchName();
        
        // 処理日時 = 入金通知Params.更新日時
        l_aioCashinNoticeUnit2.transactionDate =
            l_bankDepositNotifyParams.getLastUpdatedTimestamp();
        
        // 処理区分 = 入金通知Params.処理区分
        l_aioCashinNoticeUnit2.transactionDiv = l_bankDepositNotifyParams.getStatus();
        
        // 備考 = 入金通知Params.備考
        //備考がnullの場合エラーコメントを詰める
        String l_strRemark = l_bankDepositNotifyParams.getRemark();
        if(l_strRemark == null)
        {
            l_aioCashinNoticeUnit2.remark = l_bankDepositNotifyParams.getDepositErrorComment();
        }
        else
        {
            l_aioCashinNoticeUnit2.remark = l_strRemark;
        }
        
        // 入払区分 = 入金通知Params入払区分
        l_aioCashinNoticeUnit2.cashinoutDiv = l_bankDepositNotifyParams.getCashTransferDiv();
        
        // 取引区分 = 入金通知Params取引区分
        l_aioCashinNoticeUnit2.ioTradingType = l_bankDepositNotifyParams.getTradeDiv();
           
        // 通貨コード. = 入金通知Params.通貨コード 
        l_aioCashinNoticeUnit2.currencyCode = l_bankDepositNotifyParams.getCurrencyCode();
        
        log.exiting(STR_METHOD_NAME);
        return l_aioCashinNoticeUnit2;
        
    }
    
    /**
     * (create検索値)<BR>
     * 検索条件で追加する列に対応するようにArrayListに順番に追加して作成してください。　@<BR>
     * <BR>
     * １）引数:証券会社コード <BR>
     * ２）リクエスト.部店コードの配列件数分　@リクエスト.部店コード[]　@ <BR>
     * ３）リクエスト.顧客コードがnull出ない場合、リクエスト.顧客コード<BR>
     * ４）リクエスト.振込依頼人コードがnull出ない場合、リクエスト.振込依頼人コード<BR>
     * ５）リクエスト.勘定日がnullでない場合、<BR>
     * 　@　@年号.toJapDate(リクエスト.勘定日)にて変換した和暦文字列 <BR>
     * ※toJapDate()の戻り値がnullの場合、例外「勘定日の指定が正しくありません」をthrowする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@: BUSINESS_ERROR_03153<BR>
     * ６）リクエスト.処理日時FROMがnull出ない場合、リクエスト.処理日時FROM<BR>
     * ７）リクエスト.処理日時TOがnull出ない場合、リクエスト.処理日時TO<BR>
     * ８）リクエスト.通貨コードが”全て”以外の場合、 <BR>
     * リクエスト.通貨コードがnullの場合、通貨コード is null <BR>
     * リクエスト.通貨コード != nullの場合、リクエスト.通貨コード <BR>
     * <BR>
     * ArrayList.toArray()の戻り値を検索値として返却する。<BR>
     * @@param l_request - (リクエスト)
     * @@param l_strInstitutionCode - (証券会社コード) 
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 4109C8B50240
     */
    protected Object[] createQueryContainer(
        WEB3AdminAioCashinNoticeSearchRequest l_request, 
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryContainer( " +
            "WEB3AdminAioCashinNoticeSearchRequest )";
        log.entering(STR_METHOD_NAME);
        
        List l_lisValue = new ArrayList();
        
        //１）引数:証券会社コード
        l_lisValue.add(l_strInstitutionCode); 
        
        //２）リクエスト.部店コードの配列件数分　@リクエスト.部店コード[]
        for (int i = 0; i < l_request.branchCode.length; i++)
        {
            l_lisValue.add(l_request.branchCode[i]);
        }
        //３）リクエスト.顧客コードがnull出ない場合、リクエスト.顧客コード
        if (l_request.accountCode != null)
        {
            l_lisValue.add( l_request.accountCode + "%");
        }
        //４）リクエスト.振込依頼人コードがnull出ない場合、リクエスト.振込依頼人コード
        if (l_request.clientCode != null)
        {
            l_lisValue.add(l_request.clientCode);
        }
        //５）リクエスト.勘定日がnull出ない場合、リクエスト.勘定日
        if (l_request.settlementDate != null)
        {
            //年号.toJapDate(リクエスト.勘定日)にて変換した和暦文字列
            String[] l_strSettlementDates =
                WEB3GentradeEra.toJapDate(l_request.settlementDate);
            //※toJapDate()の戻り値がnullの場合、例外「勘定日の指定が正しくありません」をthrowする。
            if (l_strSettlementDates == null)
            {
                log.debug("勘定日の指定が正しくありません");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03153,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "勘定日の指定が正しくありません");
            }

            l_lisValue.add(l_strSettlementDates[1]);
        }
        //６）リクエスト.処理日時FROMがnull出ない場合、リクエスト.処理日時FROM
        if (l_request.transactionDateFrom != null)
        {
            l_lisValue.add( l_request.transactionDateFrom);
        }
        //７）リクエスト.処理日時TOがnull出ない場合、リクエスト.処理日時TO
        if (l_request.transactionDateTo != null)
        {
            l_lisValue.add(l_request.transactionDateTo);
        }
        
        //８）リクエスト.通貨コードが”全て”以外の場合、 
        // リクエスト.通貨コードがnullの場合、通貨コード is null 
        // リクエスト.通貨コード != nullの場合、リクエスト.通貨コード 
        if (!WEB3AioCurrencyCodeDef.ALL.equals(l_request.currencyCode))
        {
            //リクエスト.通貨コードがnullの場合、通貨コード is null 
            //クエスト.通貨コード != nullの場合、リクエスト.通貨コード
            if (l_request.currencyCode != null)
            {
                l_lisValue.add(l_request.currencyCode);             
            } 
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisValue.toArray();
    }

    /**
     * (createサマリ情報)<BR>
     * サマリ情報を生成する。<BR>
     * <BR>
     * 詳細は、シーケンス「（入金通知確認再処理）createサマリ情報」参照<BR>
     * @@param l_strCurrencyCodes - (通貨コード)<BR>
     * 通貨コード<BR>
     * @@param l_lisCashinNoticeRecord - (入金通知レコードList)<BR>
     * 入金通知レコードList<BR>
     * @@param l_response - (レスポンスデータ)<BR>
     * レスポンスデータ<BR>
     * @@return WEB3AdminAioCashinNoticeSearchResponse
     * @@roseuid 44D6C275009B
     */
    protected WEB3AdminAioCashinNoticeSearchResponse createSummaryInfo(
        String[] l_strCurrencyCodes, 
        List l_lisCashinNoticeRecord, 
        WEB3AdminAioCashinNoticeSearchResponse l_response) 
    {
        final String STR_METHOD_NAME = "createSummaryInfo" +
            "(String[], List, WEB3AdminAioCashinNoticeSearchResponse)";
        log.entering(STR_METHOD_NAME);
        
        if (l_response == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 HashMapオブジェクトの生成
        HashMap l_map = new HashMap();
        
        int l_intCurrencyCodeSize = 0;
        if (l_strCurrencyCodes != null)
        {
            l_intCurrencyCodeSize = l_strCurrencyCodes.length;
        }

        //1.2通貨コード配列の要素分、Loop処理を行う。
        for (int i = 0; i < l_intCurrencyCodeSize; i++)
        {
            //1.2.1外貨サマリ情報(String)(
            WEB3ForeignSummaryInfo l_foreignSummaryInfo = new WEB3ForeignSummaryInfo(
                l_strCurrencyCodes[i]);
            
            //1.2.2HashMapオブジェクトに値をセットする。
            //[セットする内容]
            //key = 通貨コード配列の要素
            //value = 外貨サマリ情報オブジェクト
            l_map.put(l_strCurrencyCodes[i], l_foreignSummaryInfo);

        }

        int l_listSize = 0;
        if (l_lisCashinNoticeRecord != null)
        {
            l_listSize = l_lisCashinNoticeRecord.size();
        }
        //1.3入金通知レコードの要素分、Loop処理を行う。
        for (int i = 0; i < l_listSize; i++)
        {   
            BankDepositNotifyRow l_bankDepositNotifyRow =
                (BankDepositNotifyRow)l_lisCashinNoticeRecord.get(i);

            BankDepositNotifyParams l_bankDepositNotifyParams =
                new BankDepositNotifyParams(l_bankDepositNotifyRow);
            //1.3.1入金通知レコードの要素.通貨コードがnullの場合、以下の処理を実行
            if (l_bankDepositNotifyParams.getCurrencyCode() == null)
            {
                //円貨のサマリ計算処理を行う。
                //1.3.1.1[引数] 
                //入金通知Params： 入金通知レコードの要素 
                //レスポンスデータ：　@入金通知検索レスポンス
                this.calcJapaneseCurrencyInfo(l_bankDepositNotifyParams,
                    l_response);

            //1.3.2入金通知レコードの要素.通貨コード  != nullの場合、以下の処理を実行
            } 
            else
            {
                //1.3.2.1HashMapオブジェクト.get(入金通知レコードの要素.通貨コード)で、
                //外貨サマリ情報オブジェクトを取得
                String l_strCurrencyCode = l_bankDepositNotifyParams.getCurrencyCode();
                WEB3ForeignSummaryInfo l_forSumInfo = (WEB3ForeignSummaryInfo)
                    l_map.get(l_strCurrencyCode);
                
                //1.3.2.2取得した外貨サマリ情報オブジェクト != null の場合、以下の処理を実行
                if (l_forSumInfo != null)
                {
                    //1.3.2.2.1外貨のサマリ情報を計算する。 
                    this.calcForeignCurrencyInfo(l_forSumInfo, l_bankDepositNotifyParams);
                }
                
            }
            
        }
        //1.4レスポンス.外貨サマリ情報一覧 =
        //HashMapオブジェクト.values().toArray()
        WEB3ForeignSummaryInfo[] l_foreignSum = new WEB3ForeignSummaryInfo[l_map.size()];
        l_map.values().toArray(l_foreignSum);
        l_response.foreignSummaryList = l_foreignSum;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * (calc円貨情報)<BR>
     * 円貨のサマリ計算処理を行う。<BR>
     * <BR>
     * <BR>
     * １）　@引数:入金通知Params.処理区分 == "処理済"の場合、<BR>
     * 　@　@　@・　@レスポンスデータ.正常明細件数をカウント<BR>
     * 　@　@　@・　@レスポンスデータ.正常明細合計金額に引数:入金通知Params.取引金額を加算<BR>
     * <BR>
     * ２）　@引数:入金通知Params.処理区分 == "エラー"の場合、<BR>
     * 　@　@　@・　@レスポンスデータ.エラー明細件数をカウント<BR>
     * 　@　@　@・　@レスポンスデータ.エラー明細合計金額に引数:入金通知Params.取引金額を加算<BR>
     * <BR>
     * ３）　@引数:入金通知Params.処理区分 == "未処理"の場合、<BR>
     * 　@　@　@・　@レスポンスデータ.未処理明細件数をカウント<BR>
     * 　@　@　@・　@レスポンスデータ.未処理明細合計金額に引数:入金通知Params.取引金額を加算<BR>
     * <BR>
     * ４）　@引数:入金通知Params.入払区分 == "入金"の場合、<BR>
     * 　@　@　@・　@レスポンスデータ.入金明細件数をカウント<BR>
     * 　@　@　@・レスポンスデータ.入金明細合計金額に引数:入金通知Params.取引金額を加算<BR>
     * <BR>
     * ５）　@引数:入金通知Params.入払区分 == "出金"の場合、<BR>
     * 　@　@　@・　@レスポンスデータ.出金明細件数をカウント<BR>
     * 　@　@　@・　@レスポンスデータ.出金明細合計金額に引数:入金通知Params.取引金額を加算<BR>
     * <BR>
     * ６）　@レスポンスデータ.総明細合計金額に引数:入金通知Params.取引金額を加算<BR>
     * <BR>
     * ７）　@レスポンスデータ.総明細件数をカウント<BR>
     * @@param l_bankDepositNotifyParams - (入金通知Params)<BR>
     * 入金通知Paramsオブジェクト<BR>
     * @@param l_response - (レスポンスデータ)<BR>
     * レスポンスデータ<BR>
     * @@roseuid 44D6D2300280
     */
    public void calcJapaneseCurrencyInfo(BankDepositNotifyParams l_bankDepositNotifyParams, 
        WEB3AdminAioCashinNoticeSearchResponse l_response) 
    {
        final String STR_METHOD_NAME = "calcJapaneseCurrencyInfo" +
            "(BankDepositNotifyParams, WEB3AdminAioCashinNoticeSearchResponse)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bankDepositNotifyParams == null || l_response == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        double l_dblDepositAmount = 0;
        if (l_bankDepositNotifyParams.getDepositDataDepositAmount() != null)
        {
            l_dblDepositAmount = Double.parseDouble(
                l_bankDepositNotifyParams.getDepositDataDepositAmount()); //入金通知Params.取引金額を加算
        }
            
        
        //１）　@引数:入金通知Params.処理区分 == "処理済"の場合
        //レスポンスデータ.正常明細件数をカウント 
        //レスポンスデータ.正常明細合計金額に引数:入金通知Params.取引金額を加算
        if (WEB3StatusDef.DEALT.equals(l_bankDepositNotifyParams.getStatus()))
        {
            //レスポンスデータ.正常明細件数をカウント 
            int l_intNormalNumber = 0;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.normalNumber))
            {
                l_intNormalNumber = Integer.parseInt(l_response.normalNumber);
            }
            
            l_intNormalNumber = l_intNormalNumber + 1;
            l_response.normalNumber = l_intNormalNumber + "";
      
            //レスポンスデータ.正常明細合計金額に引数:入金通知Params.取引金額を加算
            double l_dblNormalTotalPrice = 0;
            if (l_response.normalTotalPrice != null)
            {
                l_dblNormalTotalPrice = Double.parseDouble(l_response.normalTotalPrice);
            }

            l_dblNormalTotalPrice = l_dblNormalTotalPrice + l_dblDepositAmount;
            l_response.normalTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblNormalTotalPrice);
          
        }
        
        // ２）引数:入金通知Params.処理区分 == "エラー"の場合
        // レスポンスデータ.エラー明細件数をカウント 
        // レスポンスデータ.エラー明細合計金額に引数:入金通知Params.取引金額を加算 
        if (WEB3StatusDef.DATA_ERROR.equals(l_bankDepositNotifyParams.getStatus()))
        {
            //レスポンスデータ.エラー明細件数をカウント
            int l_intErrorNumber = 0;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.errorNumber))
            {
                l_intErrorNumber = Integer.parseInt(l_response.errorNumber);
            }
            l_intErrorNumber = l_intErrorNumber + 1;
            l_response.errorNumber = l_intErrorNumber + "";
      
            //レスポンスデータ.エラー明細合計金額に引数:入金通知Params.取引金額を加算
            double l_dblErrorTotalPrice = 0;
            if (l_response.errorTotalPrice != null)
            {
                l_dblErrorTotalPrice = Double.parseDouble(l_response.errorTotalPrice); 
            }
     
            l_dblErrorTotalPrice = l_dblErrorTotalPrice + l_dblDepositAmount;
            l_response.errorTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblErrorTotalPrice);
          
        }
        
        //３）　@引数:入金通知Params.処理区分 == "未処理"の場合
        //レスポンスデータ.未処理明細件数をカウント 
        //レスポンスデータ.未処理明細合計金額に引数:入金通知Params.取引金額を加算
        if (WEB3StatusDef.NOT_DEAL.equals(l_bankDepositNotifyParams.getStatus()))
        {
            //レスポンスデータ.未処理明細件数をカウント 
            int l_intNonTransactionNumber = 0;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.nonTransactionNumber))
            {
                l_intNonTransactionNumber = Integer.parseInt(l_response.nonTransactionNumber);
            }
            l_intNonTransactionNumber = l_intNonTransactionNumber + 1;
            l_response.nonTransactionNumber = l_intNonTransactionNumber + "";
      
            //レスポンスデータ.未処理明細合計金額に引数:入金通知Params.取引金額を加算
            double l_dblNonTransactionTotalPrice = 0;
            if (l_response.nonTransactionTotalPrice != null)
            {
                l_dblNonTransactionTotalPrice = Double.parseDouble(l_response.nonTransactionTotalPrice);
            }

            l_dblNonTransactionTotalPrice = l_dblNonTransactionTotalPrice + l_dblDepositAmount;
            l_response.nonTransactionTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblNonTransactionTotalPrice);
          
        }
        
        //４）引数:入金通知Params.入払区分 == "入金"の場合
        //レスポンスデータ.入金明細件数をカウント 
        //レスポンスデータ.入金明細合計金額に引数:入金通知Params.取引金額を加算
        if (WEB3AioBankDepositNotifyCashTransferDivDef.CASH_IN.equals(
            l_bankDepositNotifyParams.getCashTransferDiv()))
        {
            //レスポンスデータ.入金明細件数をカウント 
            int l_intCashinNumber = 0;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.cashinNumber))
            {
                l_intCashinNumber = Integer.parseInt(l_response.cashinNumber);
            }
            l_intCashinNumber = l_intCashinNumber + 1;
            l_response.cashinNumber = l_intCashinNumber + "";
      
            //レスポンスデータ.入金明細合計金額に引数:入金通知Params.取引金額を加算
            double l_dblCashinTotalPrice = 0;
            if (l_response.cashinTotalPrice != null)
            {
                l_dblCashinTotalPrice = Double.parseDouble(l_response.cashinTotalPrice); 
            }
   
            l_dblCashinTotalPrice = l_dblCashinTotalPrice + l_dblDepositAmount;
            l_response.cashinTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblCashinTotalPrice);
          
        }
        
        // ５）引数:入金通知Params.入払区分 == "出金"の場合
        //レスポンスデータ.出金明細件数をカウント 
        //レスポンスデータ.出金明細合計金額に引数:入金通知Params.取引金額を加算 
        if (WEB3AioBankDepositNotifyCashTransferDivDef.CASH_OUT.equals(
            l_bankDepositNotifyParams.getCashTransferDiv()))
        {
            //レスポンスデータ.出金明細件数をカウント 
            int l_intCashoutNumber = 0;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.cashoutNumber))
            {
                l_intCashoutNumber = Integer.parseInt(l_response.cashoutNumber);
            }
            l_intCashoutNumber = l_intCashoutNumber + 1;
            l_response.cashoutNumber = l_intCashoutNumber + "";
      
            //レスポンスデータ.出金明細合計金額に引数:入金通知Params.取引金額を加算 
            double l_dblCashoutTotalPrice = 0;
            if (l_response.cashoutTotalPrice != null)
            {
                l_dblCashoutTotalPrice = Double.parseDouble(l_response.cashoutTotalPrice); 
            }
            l_dblCashoutTotalPrice = l_dblCashoutTotalPrice + l_dblDepositAmount;
            l_response.cashoutTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblCashoutTotalPrice);
          
        }
        
        //６）　@レスポンスデータ.総明細合計金額に引数:入金通知Params.取引金額を加算 
        double l_dblTotalAmout = 0;
        if (l_response.totalPrice != null)
        {
            l_dblTotalAmout = Double.parseDouble(l_response.totalPrice);
        }
        l_dblTotalAmout = l_dblTotalAmout + l_dblDepositAmount;
        
        l_response.totalPrice = WEB3StringTypeUtility.formatNumber(l_dblTotalAmout);
        
        // ７）　@レスポンスデータ.総明細件数をカウント 
        int l_intTotleNumber = 0;
        if (WEB3StringTypeUtility.isNotEmpty(l_response.totalNumber))
        {
            l_intTotleNumber = Integer.parseInt(l_response.totalNumber);
        }
        l_intTotleNumber = l_intTotleNumber + 1;
        l_response.totalNumber = l_intTotleNumber + "";
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc外貨情報)<BR>
     * 外貨のサマリ情報を計算する。<BR>
     * <BR>
     * １）　@引数:入金通知Params.入払区分 = <BR>
     * "入金"の場合、引数:外貨サマリ情報.入金件数をカウント。<BR>
     * <BR>
     * ２）　@引数:入金通知Params.入払区分 = <BR>
     * "入金"の場合、引数:外貨サマリ情報.入金合計額に入金通知Params.取引金額を加算<BR>
     * @@param l_foreignSummaryInfo - (外貨サマリ情報)<BR>
     * 外貨サマリ情報オブジェクト<BR>
     * @@param l_bankDepositNotifyParams - (入金通知Params)<BR>
     * 入金通知Paramsオブジェクト<BR>
     * @@roseuid 44E447AE00EB
     */
    private void calcForeignCurrencyInfo(WEB3ForeignSummaryInfo l_foreignSummaryInfo, 
        BankDepositNotifyParams l_bankDepositNotifyParams) 
    {
        final String STR_METHOD_NAME = "calcForeignCurrencyInfo" +
            "(WEB3ForeignSummaryInfo, BankDepositNotifyParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bankDepositNotifyParams == null || l_foreignSummaryInfo == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        double l_dblDepositAmountTemp = 0 ;
        if (l_bankDepositNotifyParams.getDepositDataDepositAmount() != null)
        {
            l_dblDepositAmountTemp = Double.parseDouble
                (l_bankDepositNotifyParams.getDepositDataDepositAmount()); //入金通知Params.取引金額
        }        
        
        //１）引数:入金通知Params.入払区分 = "入金"の場合
        //    引数:外貨サマリ情報.入金件数をカウント。
        //２）引数:入金通知Params.入払区分 = "入金"の場合
        //    引数:外貨サマリ情報.入金合計額に加算
        if (WEB3AioBankDepositNotifyCashTransferDivDef.CASH_IN.equals(
            l_bankDepositNotifyParams.getCashTransferDiv()))
        {
            //引数:外貨サマリ情報.入金件数をカウント。
            int l_intCashinNumber = 0;
            if (WEB3StringTypeUtility.isNotEmpty(l_foreignSummaryInfo.cashinNumber))
            {
                l_intCashinNumber = Integer.parseInt(l_foreignSummaryInfo.cashinNumber);
            }
            l_intCashinNumber = l_intCashinNumber + 1;       
            l_foreignSummaryInfo.cashinNumber = l_intCashinNumber + "";

            //引数:外貨サマリ情報.入金合計額に入金通知Params.取引金額を加算
            double l_dblCashinTotalPrice = 0;
            if (l_foreignSummaryInfo.cashinTotalPrice != null)
            {
                l_dblCashinTotalPrice = Double.parseDouble(l_foreignSummaryInfo.cashinTotalPrice);
            }
            l_dblCashinTotalPrice = l_dblCashinTotalPrice + l_dblDepositAmountTemp;

            l_foreignSummaryInfo.cashinTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblCashinTotalPrice);

        }

        log.exiting(STR_METHOD_NAME);   
    }    
}
@
