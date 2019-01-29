head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用顧客データアップロードUnitServiceImpl(WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 鄭海良(中訊) 新規作成
Revesion History : 2007/06/05 趙林鵬(中訊) モデル240,ＤＢ更新仕様 036
Revesion History : 2007/06/28 崔遠鵬(中訊) モデル276
Revesion History : 2007/07/11 孟亜南(中訊) モデル279 モデル283
Revesion History : 2007/07/27 金傑(中訊)　@モデル293 モデル296 モデル297,ＤＢ更新仕様 039
Revesion History : 2008/02/26 武波 仕様変更 モデル324
Revesion History : 2008/03/03 武波 仕様変更 モデル334
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3SrvAppliAttributeProcDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.srvregi.WEB3AdminSrvRegiAccountDataUploadCsv;
import webbroker3.srvregi.WEB3SrvRegiChangeAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiNewAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.data.SrvAppliAttributeDao;
import webbroker3.srvregi.data.SrvAppliAttributePK;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUploadUnitService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用顧客データアップロードUnitServiceImpl)<BR>
 * サービス利用顧客データアップロードUnitService　@実装クラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl 
    implements WEB3AdminSrvRegiAccountDataUploadUnitService 
{
    
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl.class);
    
    /**
     * @@roseuid 416F392C0148
     */
    public WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl() 
    {
     
    }
    
    /**
     * (update申込登録)<BR>
     * update申込登録処理を行う。<BR>
     * <BR>
     * シーケンス図「(サービス利用)顧客データアップロード・update申込登録」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strUploadDiv - (アップロード区分)<BR>
     * 登録／変更／抽選結果アップロード<BR>
     * @@param l_registId 申込登録ID<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * @@param l_tsAppliDate - (申込日)<BR>
     * @@param l_strAppliLotDiv - (申込抽選区分)<BR>
     * 試用／申込／当選（本申込）／落選／取消<BR>
     * @@param l_strPaymentDiv - (登録区分)<BR>
     * 無料／有料<BR>
     * @@param l_dblUseAmt - (利用料金)<BR>
     * @@param l_tsPaymentDate - (出金日)<BR>
     * @@param l_strPassword - (暗証番号)<BR>
     * @@roseuid 41109351035E
     */
    public void updateAppliRegist(
        WEB3GentradeSubAccount l_subAccount,
        String l_strUploadDiv, 
        Long   l_registId, 
        String l_strInstitutionCode, 
        String l_strSrvDiv, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        Timestamp l_tsAppliStartDate, 
        Timestamp l_tsAppliEndDate, 
        Timestamp l_tsAppliDate, 
        String l_strAppliLotDiv, 
        String l_strPaymentDiv, 
		Double l_dblUseAmt, 
        Timestamp l_tsPaymentDate, 
        String l_strPassword) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " updateAppliRegist(" +
            "WEB3GentradeSubAccount , " +
            "String , " +
            "Long   , " +           
            "String , " +
            "String , " +
            "String , " +
            "String , " +
            "Timestamp , " +
            "Timestamp , " +
            "Timestamp , " +
            "String , " +
            "String , " +
            "Double , " +
            "Timestamp , " +
            "String )";
        log.entering(STR_METHOD_NAME );
        
        try
        {
            //1.1 getInstitution(arg0 : 論理ビュー::java::lang::String)
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //拡張アカウントマネージャ取得する
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
        
            //1.2 getMainAccount(arg0 : long, arg1 : 論理ビュー::java::lang::String, arg2 : 論理ビュー::java::lang::String)
            MainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_institution.getInstitutionId(),
                l_strBranchCode, 
                l_strAccountCode);
                
			//口座をロックする。 
			//－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。  
			WEB3GentradeAccountManager l_gentradeAccMgr = 
				(WEB3GentradeAccountManager) l_finApp.getAccountManager();
				
			l_gentradeAccMgr.lockAccount(
				l_strInstitutionCode,
				l_strBranchCode,
				l_strAccountCode);

            //getサービスマスター(String, String, boolean)
            //　@証券会社コード = 引数.証券会社コード
			//サービス区分 = 引数.サービス区分
			//is行ロック = false
            WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement =
                new WEB3SrvRegiServiceInfoManagement();
            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
                l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

            WEB3SrvRegiRegistService l_registService = 
                (WEB3SrvRegiRegistService) Services.getService(
                    WEB3SrvRegiRegistService.class);
            //1.4 ＜分岐処理＞新規登録の場合に、余力の拘束とサービス申込登録へのデータ挿入を行う
            if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv))
            {
                Long l_lngOrderId = null;
                //1.4.1 ＜分岐処理＞申込抽選区分が申込あるいは当選、かつ、登録区分が有料の場合に、出金余力を拘束する。
                if ((WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ELECTION_LABEL.equals(l_strAppliLotDiv)
                        || WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_APPLI_LABEL.equals(l_strAppliLotDiv))
                    && WEB3AdminSrvRegiAccountDataUploadCsv.PAYMENT_DIV_CHARGE_LABEL.equals(l_strPaymentDiv))
                {
                    //get取引パスワード（補助口座, 引数.暗証番号）
                    String l_strTradingPassword = l_registService.getTradingPassword(l_subAccount, l_strPassword);
                    
                    //1.4.1.1 submit余力拘束(補助口座, Trader, double, Timestamp, String, String, String)
                    long l_lngReturn = l_registService.submitRemainingPowerRestraint(
                        l_subAccount, 
                        null, 
                        l_dblUseAmt.doubleValue(), 
                        l_tsPaymentDate, 
                        l_strSrvDiv, 
                        null, 
                        l_strTradingPassword);       
                    l_lngOrderId = new Long(l_lngReturn);
                }
                //1.4.2 createサービス利用新規申込内容(String, String, String, String, Timestamp, Timestamp, Timestamp, String, Double, Timestamp)
                WEB3SrvRegiNewAppliSpec l_spec = WEB3SrvRegiNewAppliSpec.createSrvRegiNewAppliSpec(
                    l_strInstitutionCode, 
                    l_strSrvDiv, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    l_tsAppliStartDate, 
                    l_tsAppliEndDate, 
                    l_tsAppliDate, 
                    l_strPaymentDiv, 
                    l_dblUseAmt, 
                    l_tsPaymentDate,
                    l_strAppliLotDiv);

                //1.4.3 submitサービス申込登録(サービス利用新規申込内容, Long)
                l_registService.submitServiceRegist(l_spec, l_lngOrderId);

                //＜分岐処理＞サービスマスターオブジェクト.特殊処理区分 = 1
                SrvRegiMasterParams l_srvRegiMasterParams =
                    (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
                String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
                if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
                {
                    //submit外部連携情報(String, String, String, String, Timestamp, Timestamp, boolean)
                    //証券会社コード = 引数.証券会社コード
                    //部店コード = 引数.部店コード
                    //口座コード = 引数.口座コード
                    //サービス区分 = 引数.データ.サービス区分
                    //適用開始日 = 引数.適用開始日
                    //適用終了日 = 引数.適用終了日
                    //新規申込区分 = true
                    WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                        (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
                    l_srvRegiOtherOrgService.submitOtherOrgInfo(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode,
                        l_strSrvDiv,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        true);
                }
            }
            
			//1.5 ＜分岐処理＞引数.アップロード区分!=”登録”の場合、以下の手続きを実施する。
			if (!WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_NEW_REGIST_LABEL.equals(l_strUploadDiv))
			{
				// サービス利用申込登録サービス取得
				WEB3SrvRegiRegistService l_appliRegiService = 
					(WEB3SrvRegiRegistService) Services.getService(WEB3SrvRegiRegistService.class);

				// サービス申込登録取得（既存レコード）
				WEB3GentradeSrvRegiApplication l_srvRegiApp = l_appliRegiService.getServiceRegist(
					l_strInstitutionCode,
					l_strBranchCode,
					l_strSrvDiv,
					l_strAccountCode,
					l_registId.longValue(),
					false);
				
				//1.5.1 get注文ID( )
				Long l_lngOrderId = l_srvRegiApp.getOrderId();

				// get申込抽選区分
				String l_strBeforeAppliLotDiv = l_srvRegiApp.getAppliLotDiv();
				
				//障害対応 NO_2060
				// ＜分岐処理＞既存レコードの申込抽選区分＝“申込”または、”当選” && 引数.申込抽選区分＝“落選” 
				// && 既存レコード.注文ID!=null の場合
				if(((WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_APPLI_LABEL.equals(l_strBeforeAppliLotDiv)) || 
					WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_ELECTION_LABEL.equals(l_strBeforeAppliLotDiv)) &&
				   (WEB3AdminSrvRegiAccountDataUploadCsv.APPLI_LOT_DIV_DEFEAT_LABEL.equals(l_strAppliLotDiv)) &&
				   (l_lngOrderId != null))
				{			
                    //get取引パスワード（補助口座, 引数.暗証番号）
                    String l_strTradingPassword = l_registService.getTradingPassword(l_subAccount, l_strPassword);

					//1.5.2 submit余力解放(補助口座, long, String)
					l_registService.submitRemainingPowerRelease(
						l_subAccount, 
						l_lngOrderId.longValue(), 
                        l_strTradingPassword);
				}
			}

            //1.6 ＜分岐処理＞変更登録、抽選結果アップロードの場合に、サービス申込登録のデータ変更を行う
            if (WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_CHANGE_REGIST_LABEL.equals(l_strUploadDiv)
                || WEB3AdminSrvRegiAccountDataUploadCsv.UPLOAD_DIV_LOT_RESULT_UPLOAD_LABEL.equals(l_strUploadDiv))
            {
                //1.6.1 createサービス利用変更申込内容(long, String, String, String, String, Timestamp, Timestamp, String, Timestamp, String, Double)
                WEB3SrvRegiChangeAppliSpec l_spec = WEB3SrvRegiChangeAppliSpec.createSrvRegiChangeAppliSpec(
                    l_registId.longValue(),
                    l_strInstitutionCode, 
                    l_strSrvDiv, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    l_tsAppliStartDate, 
                    l_tsAppliEndDate, 
                    l_strAppliLotDiv, 
                    l_tsAppliDate, 
                    l_strPaymentDiv, 
					l_dblUseAmt);

                //1.6.2 submitサービス申込変更(サービス利用変更申込内容)
                l_registService.submitServiceRegistChange(l_spec);

                //＜分岐処理＞サービスマスターオブジェクト.特殊処理区分 = 1
                SrvRegiMasterParams l_srvRegiMasterParams =
                    (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
                String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
                if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
                {
                    //submit外部連携情報(String, String, String, String, Timestamp, Timestamp, boolean)
                    //証券会社コード = 引数.証券会社コード
                    //部店コード = 引数.部店コード
                    //口座コード = 引数.口座コード
                    //サービス区分 = 引数.データ.サービス区分
                    //適用開始日 = 引数.適用開始日
                    //適用終了日 = 引数.適用終了日
                    //新規申込区分 = false
                    WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                        (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
                    l_srvRegiOtherOrgService.submitOtherOrgInfo(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode,
                        l_strSrvDiv,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        false);
                }
            }
        }
        catch (NotFoundException l_e)
        {
            WEB3BaseException l_webe = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01026,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "未登録顧客エラー.",
                    l_e);
            log.error("未登録顧客エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_webe;
        }


        log.exiting(STR_METHOD_NAME);     
    }

    /**
     * (insertサービス申込属性)<BR>
     * １）サービス申込属性テーブルにinsert処理を行う。<BR>
     * <BR>
     * １-１） 拡張アカウントマネージャを利用して顧客行を取得する。<BR>
     * 　@　@　@　@[get顧客()にセットするパラメータ]<BR>
     * 　@　@　@　@　@arg0：　@引数.証券会社コード<BR>
     * 　@　@　@　@　@arg1：　@引数.部店コード<BR>
     * 　@　@　@　@　@arg2：　@引数.顧客コード<BR>
     * <BR>
     * 　@１-１-１） １-１）の戻り値から７桁の顧客コードを取得する。<BR>
     * <BR>
     * １-２） サービス申込属性行オブジェクトを作成し、以下を設定する。<BR>
     * <BR>
     * 　@　@サービス申込属性行.証券会社コード = 引数.証券会社コード<BR>
     * 　@　@サービス申込属性行.部店コード = 引数.登録情報.部店コード<BR>
     * 　@　@サービス申込属性行.口座コード = １-１-１）で取得した顧客コード<BR>
     * 　@　@サービス申込属性行.サービス区分 = 引数.サービス区分<BR>
     * 　@　@サービス申込属性行.申込属性区分 = 引数.申込抽選区分<BR>
     * 　@　@サービス申込属性行.適用期間From = 引数.適用開始日<BR>
     * 　@　@サービス申込属性行.適用期間To = 引数.適用終了日<BR>
     * 　@　@サービス申込属性行.更新者コード = セッションから取得した管理者コード<BR>
     * 　@　@サービス申込属性行.作成日時 = 現在日時<BR>
     * 　@　@サービス申込属性行.更新日時 = 現在日時<BR>
     * 　@　@サービス申込属性行.処理区分 = '0'<BR>
     * <BR>
     * 　@(登録内容についてはDB更新仕様を参照願います。)<BR>
     * <BR>
     * １-３） QueryProcessor#doInsertQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@[doInsertQuery()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@１-３）で作成した行オブジェクト<BR>
     * <BR>
     * １-４） QueryProcessor#doInsertQuery()の戻り値を取得する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strMainAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_strAppliLotDiv - (申込抽選区分)<BR>
     * 試用／申込／当選（本申込）／落選／取消<BR>
     * <BR>
     * ※アップロード区分 = 3：申込属性 の場合<BR>
     * 無料／利用不可<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * 適用開始日<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * 適用終了日<BR>
     * @@throws WEB3BaseException
     */
    public void insertSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv,
        String l_strAppliLotDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertSrvApplyAttribute(String, String, String, String, "
            + "String, Timestamp, Timestamp)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //拡張アカウントマネージャを利用して顧客行を取得する。
        //[get顧客()にセットするパラメータ]
        //arg0：　@引数.証券会社コード
        //arg1：　@引数.部店コード
        //arg2：　@引数.顧客コード
        WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
            l_strInstitutionCode, l_strBranchCode, l_strMainAccountCode);

        //１-２）の戻り値から７桁の顧客コードを取得する。
        String l_strAccountCode = l_mainAccount.getAccountCode();

        //サービス申込属性行オブジェクトを作成し、以下を設定する
        SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();

        //サービス申込属性行.証券会社コード = 引数.証券会社コード
        l_srvAppliAttributeParams.setInstitutionCode(l_strInstitutionCode);

        //サービス申込属性行.部店コード = 引数.登録情報.部店コード
        l_srvAppliAttributeParams.setBranchCode(l_strBranchCode);

        //サービス申込属性行.口座コード = １-２-１）で取得した顧客コード
        l_srvAppliAttributeParams.setAccountCode(l_strAccountCode);

        //サービス申込属性行.サービス区分 = 引数.サービス区分
        l_srvAppliAttributeParams.setSrvDiv(l_strServiceDiv);

        //サービス申込属性行.申込属性区分 = 引数.申込抽選区分
        l_srvAppliAttributeParams.setAppliAttribute(l_strAppliLotDiv);

        //サービス申込属性行.適用期間From = 引数.適用開始日
        l_srvAppliAttributeParams.setAppliStartDate(l_tsAppliStartDate);

        //サービス申込属性行.適用期間To = 引数.適用終了日
        l_srvAppliAttributeParams.setAppliEndDate(l_tsAppliEndDate);

        //サービス申込属性行.更新者コード = セッションから取得した管理者コード
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        l_srvAppliAttributeParams.setLastUpdater(l_strAdministratorCode);

        //サービス申込属性行.作成日時 = 現在日時
        l_srvAppliAttributeParams.setCreatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        //サービス申込属性行.更新日時 = 現在日時
        l_srvAppliAttributeParams.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        //サービス申込属性行.処理区分 = '0'
        l_srvAppliAttributeParams.setProcDiv(WEB3SrvAppliAttributeProcDivDef.NOT_PROCESSED);

        //１-４） QueryProcessor#doInsertQuery()メソッドをコールする。
        //[doInsertQuery()にセットするパラメータ]
        //arg0：　@１-３）で作成した行オブジェクト
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_srvAppliAttributeParams);

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
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updateサービス申込属性)<BR>
     * サービス申込属性テーブルの更新を行う。<BR>
     * <BR>
     * １）Mapオブジェクトを作成し、以下を設定する。<BR>
     * <BR>
     * 　@　@コラム名： "appli_attribute" / 値： 引数.申込属性区分<BR>
     * 　@　@コラム名： "appli_start_date" / 値：引数.適用開始日<BR>
     * 　@　@コラム名： "appli_end_date" / 値： 引数.適用終了日<BR>
     * 　@　@コラム名： "last_updater" / 値： セッションから取得した管理者コード<BR>
     * 　@　@コラム名： "last_updated_timestamp" / 値： 現在日時<BR>
     *     コラム名： "proc_div" / 値： '0'<BR>
     * <BR>
     * ２） QueryProcessor#doUpdateQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@[doUpdateQuery()にセットするパラメータ]<BR>
     * 　@　@　@　@arg0：（引数）証券会社コード、部店コード、顧客コード、サービス区分を基にした<BR>
     * 　@　@　@　@サービス申込属性テーブルのPrimaryKeyオブジェクト<BR>
     * 　@　@　@　@arg1： １）で作成したMapオブジェクト<BR>
     * <BR>
     * (登録内容についてはDB更新仕様を参照願います。)<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strMainAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@param l_strAppliLotDiv - (申込抽選区分)<BR>
     * 試用／申込／当選（本申込）／落選／取消<BR>
     * <BR>
     * ※アップロード区分 = 3：申込属性 の場合<BR>
     * 無料／利用不可<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * 適用開始日<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * 適用終了日<BR>
     * @@throws WEB3BaseException
     */
    public void updateSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv,
        String l_strAppliLotDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateSrvApplyAttribute(String, String, String, String, "
            + "String, Timestamp, Timestamp)";
        log.entering(STR_METHOD_NAME);

        //１）Mapオブジェクトを作成し、以下を設定する。
        Map l_mapChanges = new HashMap();

        //コラム名： "appli_attribute" / 値： 引数.申込属性区分
        l_mapChanges.put("appli_attribute", l_strAppliLotDiv);

        //コラム名： "appli_start_date" / 値：引数.適用開始日
        l_mapChanges.put("appli_start_date", l_tsAppliStartDate);

        //コラム名： "appli_end_date" / 値： 引数.適用終了日
        l_mapChanges.put("appli_end_date", l_tsAppliEndDate);

        //コラム名： "last_updater" / 値： セッションから取得した管理者コード
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        l_mapChanges.put("last_updater", l_strAdministratorCode);

        //コラム名： "last_updated_timestamp" / 値： 現在日時
        l_mapChanges.put("last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());

        //コラム名： "proc_div" / 値： '0'
        l_mapChanges.put("proc_div", WEB3SrvAppliAttributeProcDivDef.NOT_PROCESSED);

        //２）引数.顧客コードから７桁の顧客コードを取得する。                           
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);                    
        WEB3GentradeAccountManager l_gentradeAccountManager =                           
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();                   
                                                                                        
        //拡張アカウントマネージャを利用して顧客行を取得する。                          
        //[get顧客()にセットするパラメータ]                                             
        //arg0：　@引数.証券会社コード                                                   
        //arg1：　@引数.部店コード                                                       
        //arg2：　@引数.顧客コード                                                       
        WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
            l_strInstitutionCode, l_strBranchCode, l_strMainAccountCode);               
                                                                                        
        String l_strAccountCode = l_mainAccount.getAccountCode();                       
                                                                                        
        //３）QueryProcessor#doUpdateQuery()メソッドをコールする。                      
        //[doUpdateQuery()にセットするパラメータ]                                       
        //arg0：（引数）証券会社コード、部店コード、２）で取得した7桁の顧客コード、      
        //サービス区分を基にしたサービス申込属性テーブルのPrimaryKeyオブジェクト
        //arg1： １）で作成したMapオブジェクト

        SrvAppliAttributeRow l_srvAppliAttributeRow = null;
        try
        {
        	l_srvAppliAttributeRow = SrvAppliAttributeDao.findRowByPk(
        			l_strInstitutionCode, l_strBranchCode, l_strAccountCode, l_strServiceDiv);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_srvAppliAttributeRow.getPrimaryKey(), l_mapChanges);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
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
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
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
     * (deleteサービス申込属性)<BR>
     * １）サービス申込属性テーブルのdelete処理を行う。<BR>
     * <BR>
     * １-１） 拡張アカウントマネージャを利用して顧客行を取得する。<BR>
     * [get顧客()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@引数.証券会社コード<BR>
     * 　@　@　@arg1：　@引数.部店コード<BR>
     * 　@　@　@arg2：　@引数.顧客コード<BR>
     * <BR>
     * １-２） １-１）の戻り値から７桁の顧客コードを取得する。<BR>
     * <BR>
     * １-３）QueryProcessor.dodeleteQuery()メソッドをコールする。<BR>
     * 　@　@　@arg0：　@引数で受け取った会社コード、部店コード、<BR>
     * 　@　@　@　@　@　@　@１－２）で取得した顧客コード、サービス区分を元に<BR>
     * 　@　@　@　@　@　@　@生成したプライマリーキーオブジェクト <BR>
     * <BR>
     * １-４） QueryProcessor.dodeleteQuery()の戻り値を取得する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strMainAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@throws WEB3BaseException
     */
    public int deleteSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteSrvApplyAttribute(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //拡張アカウントマネージャを利用して顧客行を取得する。
        //[get顧客()にセットするパラメータ]
        //arg0：　@引数.証券会社コード
        //arg1：　@引数.部店コード
        //arg2：　@引数.顧客コード
        WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
            l_strInstitutionCode, l_strBranchCode, l_strMainAccountCode);

        //１-２）の戻り値から７桁の顧客コードを取得する。
        String l_strAccountCode = l_mainAccount.getAccountCode();

        int l_intProcessor = 0;
        try
        {
            //QueryProcessor.dodeleteQuery()メソッドをコールする
            //　@　@ arg0：　@引数で受け取った会社コード、部店コード、１－２）
            //     で取得した顧客コード、サービス区分を元に生成したプライマリーキーオブジェクト
            SrvAppliAttributePK l_srvAppliAttributePK =
                new SrvAppliAttributePK(l_strInstitutionCode,l_strBranchCode,l_strAccountCode,l_strServiceDiv);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_intProcessor = l_queryProcessor.doDeleteQuery(
                l_srvAppliAttributePK);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_intProcessor;
    }
}
@
