head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客データダウンロードサービスImpl(WEB3AdminSrvRegiAccountDataDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
Revesion History : 2007/04/06 齊珂  (中訊) 仕様変更モデル 235
Revesion History : 2007/04/12 CInomata(SCS) 仕様変更モデル 237
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.srvregi.WEB3AdminSrvRegiAccountDataDownloadCsv;
import webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataDownloadService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用管理者顧客データダウンロードサービスImpl)<BR>
 * サービス利用管理者顧客データダウンロードサービス実装クラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataDownloadServiceImpl implements WEB3AdminSrvRegiAccountDataDownloadService 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountDataDownloadServiceImpl.class);
    
    /**
     * @@roseuid 416F39290167
     */
    public WEB3AdminSrvRegiAccountDataDownloadServiceImpl() 
    {
     
    }
    
    /**
     * サービス利用管理者顧客データダウンロード処理を行う。<BR>
     * <BR>
     * ２）　@getダウンロードファ@イル()をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410700660159
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminSrvRegiDownloadRequest)
        {
            //２）　@getダウンロードファ@イル()をコールする
            l_response = this.getDownloadFile(
                (WEB3AdminSrvRegiDownloadRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = "パラメータの類型が不正。";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
    
    /**
     * (getダウンロードファ@イル)<BR>
     * サービス利用管理者顧客ダウンロードファ@イル処理を行う。<BR>
     * <BR>
     * シーケンス図「(サービス利用管理者)顧客ダウンロード・getダウンロードファ@イル」参照<BR>
     * ====================================================================
     * シーケンス図「(サービス利用管理者)顧客ダウンロード・getダウンロードファ@イル」
     * 1.11 getサービス申込登録一覧()の戻り値.size() > getプリファ@レンス()の戻り値の場合、
     *      例外（BUSINESS_ERROR_01957）をスローする。
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01957<BR>
     * ====================================================================
     * @@param l_request - (リクエストデータ)<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 410706B80157
     */
    protected WEB3AdminSrvRegiDownloadResponse getDownloadFile(WEB3AdminSrvRegiDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminSrvRegiDownloadRequest )";
        log.entering(STR_METHOD_NAME );
        
        //1.1 validate( )
        l_request.validate();
        
        //1.3 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, false);
        
        //1.5 validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.6 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7 顧客データCSV( )
        WEB3AdminSrvRegiAccountDataDownloadCsv l_accountDataDownloadCsv = 
            new WEB3AdminSrvRegiAccountDataDownloadCsv();
            
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        String l_strAccountCodeInDb = null;
        
        //障害対応 NO_2109
		if (l_request.accountCode != null && l_request.branchCode != null)
		{
			int l_cntAccount = 0;
			
			for (int i = 0; i < l_request.branchCode.length; i++)
			{
				try
				{
					//1.8 get顧客(String, String, String)
					WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode[i], l_request.accountCode);
        
					//1.9 getAccountCode( ) 顧客コード7桁取得
					l_strAccountCodeInDb = l_mainAccount.getAccountCode();
                    
					//顧客マスターに存在する数をcount
					l_cntAccount++;
                    
					log.debug("【顧客コード存在チェックOK】 : " + l_request.accountCode);
					break;
				}
				catch (WEB3BaseException l_e)
				{
					log.debug("【顧客コード存在チェック 部店 ："+l_request.branchCode[i]+"】該当なし");
					continue;
				}
			}
            
			//入力された顧客コードが顧客マスターに存在しない場合
			if(l_cntAccount == 0)
			{
				log.debug("【顧客コード存在チェックNG】 : " + l_request.accountCode);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01987,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"顧客マスタテーブルで顧客オブジェクトを取得できない場合");
			}
            
		}
        
            
        //1.10 getサービス申込登録一覧
        WEB3SrvRegiRegistService l_appliRegiService = 
            (WEB3SrvRegiRegistService) Services.getService(WEB3SrvRegiRegistService.class);
        
        Timestamp l_tsTrialStartFrom = null;
        Timestamp l_tsTrialStartTo = null;
        Timestamp l_tsApplyDateFrom = null;
        Timestamp l_tsApplyDateTo = null;
        if (l_request.trialStartFrom != null)
        {
            l_tsTrialStartFrom = new Timestamp(l_request.trialStartFrom.getTime());
        }
        if (l_request.trialStartTo != null)
        {
            l_tsTrialStartTo = new Timestamp(l_request.trialStartTo.getTime());
        }
        if (l_request.applyDateFrom != null)
        {
            l_tsApplyDateFrom = new Timestamp(l_request.applyDateFrom.getTime());
        }
        if (l_request.applyDateTo != null)
        {
            l_tsApplyDateTo = new Timestamp(l_request.applyDateTo.getTime());        
        }
        SrvRegiApplicationParams[] l_appParams =
            l_appliRegiService.getServiceRegistLists(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.serviceDiv,
                l_strAccountCodeInDb,
                l_request.registDiv,
                l_request.applyLotteryDiv,
                l_tsTrialStartFrom,
                l_tsTrialStartTo,
                l_tsApplyDateFrom,
                l_tsApplyDateTo,
                l_request.sortKeys);

        //getプリファ@レンス(String)
        //引数で指定された設定名称の設定値をシステムプリファ@レンステーブルから取得する。
        //[this.getプリファ@レンスに指定する引数]
        //設定名称：　@"DL_REC_COUNT_SERVICE_ACCOUNT_DATA"
        String l_strPreference =
            this.getPreference(WEB3SystemPreferencesNameDef.DL_REC_COUNT_SERVICE_ACCOUNT_DATA);

        //getサービス申込登録一覧()の戻り値.size() > getプリファ@レンス()の戻り値の場合、
        //例外（BUSINESS_ERROR_01957）をスローする。
        int l_intParamsLength = l_appParams.length;
        int l_intPreference = Integer.parseInt(l_strPreference);
        
        if (l_intParamsLength > l_intPreference)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01957,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当する件数がダウンロード件数を超えています。");
        }

        //1.11 (*)取得した顧客データの要素数分、Loop処理
        int l_intRowCount = l_appParams.length;
        for (int i = 0; i < l_intRowCount; i++)
        {
            SrvRegiApplicationParams l_params = l_appParams[i];
            
            //1.11.1 add明細行( )
            int l_intIndex = l_accountDataDownloadCsv.addRow();
            
            //1.11.2 set申込登録ID(int, String)
            l_accountDataDownloadCsv.setRegistId(l_intIndex, WEB3StringTypeUtility.formatNumber(l_params.getRegistId()));
            
            //1.11.3 set証券会社コード(int, String)
            l_accountDataDownloadCsv.setInstitutionCode(l_intIndex, l_params.getInstitutionCode());
            
            //1.11.4 set部店コード(int, String)
            l_accountDataDownloadCsv.setBranchCode(l_intIndex, l_params.getBranchCode());
            
            //1.11.5 setサービス区分(int, String)
            l_accountDataDownloadCsv.setSrvDiv(l_intIndex, l_params.getSrvDiv());
            
            //1.11.6 set顧客(int, String, String, String)
            try
            {
                //WEB3-SRVREGI-A-UT-0089
                l_accountDataDownloadCsv.setAccount(
                    l_intIndex, 
                    l_params.getInstitutionCode(),
                    l_params.getBranchCode(),
                    l_params.getAccountCode());//WEB3BaseException

            }
            catch (WEB3BaseException l_e)
            {
                //1.11.7 (*)顧客オブジェクト取得に失敗した場合
                //1.11.7.1 delete明細行(int)
                l_accountDataDownloadCsv.deleteRow(l_intIndex);
                
				log.debug("【顧客情報取得エラー（明細削除）】"+l_params.getAccountCode().substring(0,6));
                
                //1.11.7.2 continue
                continue;
            }
            
            //1.11.8 set登録区分(int, String)
            l_accountDataDownloadCsv.setPaymentDiv(l_intIndex, l_params.getPaymentDiv());
            
            //1.11.9 set申込抽選区分(int, String)
            l_accountDataDownloadCsv.setAppliLotDiv(
            		l_intIndex,
            		l_params.getAppliLotDiv(),
					l_params.getCancelDiv(),
					l_params.getCancelLimitDate());
            
            //1.11.10 set申込日(int, Date)
            l_accountDataDownloadCsv.setAppliDate(l_intIndex, l_params.getAppliDate());
            
            //1.11.11 set適用開始日(int, Date)
            l_accountDataDownloadCsv.setAppliStartDate(l_intIndex, l_params.getAppliStartDate());
            
            //1.11.12 set適用終了日(int, Date)
            l_accountDataDownloadCsv.setAppliEndDate(l_intIndex, l_params.getAppliEndDate());
            
            //1.11.13 set利用料金(int, double)
            l_accountDataDownloadCsv.setUseAmt(l_intIndex, l_params.getUseAmt());
            
            //1.11/14 set出金日(int, Date)
            l_accountDataDownloadCsv.setPaymentDate(l_intIndex, l_params.getPaymentDate());
            
            //仕様変更対応 NO_207
            try
            {
	            //1.11.15 set出金余力(int, String, String, String)
	            l_accountDataDownloadCsv.setPaymentPower(
	                l_intIndex,
	                l_params.getInstitutionCode(),
	                l_params.getBranchCode(),
                    l_params.getAccountCode());
            }
			catch (WEB3BaseException e)
			{
				//1.11.16 (*)出金余力の取得に失敗した場合
				//1.11.16.1 delete明細行(int)
				l_accountDataDownloadCsv.deleteRow(l_intIndex);
                
				log.debug("【set出金余力エラー（明細削除）】"+l_params.getAccountCode().substring(0,6));
				//1.11.16.2 continue
				continue;
			}
        }
        
        //1.12 getCSVファ@イル行( )
        String[] l_strCsvFileLines = l_accountDataDownloadCsv.getCsvFileLines();
        
        //1.13 サービス利用管理者ダウンロードレスポンス( )
        WEB3AdminSrvRegiDownloadResponse l_response = 
            (WEB3AdminSrvRegiDownloadResponse)l_request.createResponse();
        
        //1.14 (*)プロパティセット  
        l_response.lines = l_strCsvFileLines;
        l_response.currentDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getプリファ@レンス)<BR>
     * 引数で指定された設定名称の設定値をシステムプリファ@レンステーブルから取得する。<BR>
     * <BR>
     * １）システムプリファ@レンステーブルから以下の条件でレコードを取得する。<BR>
     * <BR>
     * 　@[取得条件]<BR>
     * 　@名称（環境変数名） = （引数）設定名称<BR>
     * <BR>
     * ２）取得したシステムプリファ@レンステーブルのレコードの設定値を返却する。<BR>
     * @@param l_strSetName - (設定名称)<BR>
     * 設定名称
     * @@return String
     * @@throws WEB3BaseException
     */
    private String getPreference(String l_strSetName) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPreference(String) ";
        log.entering(STR_METHOD_NAME);

        //引数で指定された設定名称の設定値をシステムプリファ@レンステーブルから取得する。
        //　@[取得条件]
        //　@名称（環境変数名） = （引数）設定名称
        SystemPreferencesRow l_sysPreferenceRow = null;
        try
        {
            l_sysPreferenceRow = SystemPreferencesDao.findRowByPk(l_strSetName);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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

        //２）取得したシステムプリファ@レンステーブルのレコードの設定値を返却する。
        String l_strValue = l_sysPreferenceRow.getValue();

        log.exiting(STR_METHOD_NAME);
        return l_strValue;
    }
}
@
