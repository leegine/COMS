head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountBaseInfoCreatedServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報顧客基本情報作成サービスImpl(WEB3AccInfoAccountBaseInfoCreatedServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 張宝楠 (中訊) 新規作成
Revesion History : 2005/12/23 鄭徳懇 (中訊) 仕様変更No.071
Revesion History : 2006/02/08 呉艶飛 (中訊) ＤＢレイアウトNo.012
Revesion History : 2006/02/23 呉艶飛 (中訊) モデルNo.086
Revesion History : 2006/02/28 呉艶飛 (中訊) モデルNo.089
Revesion History : 2006/05/16 凌建平 (中訊)【入出金】仕様変更・ＤＢレイアウトNo.096の関連を修正
Revesion History : 2006/05/30 鈴木剛 (SCS)モデルNo.108
Revesion History : 2006/06/30 丁昭奎 (中訊) 仕様変更管理No.116
Revesion History : 2006/09/07 車進　@ (中訊) 仕様変更管理No.122
Revesion History : 2006/09/12 車進　@ (中訊) 仕様変更管理No.126
Revesion History : 2006/10/9  齊珂   (中訊) 仕様変更管理No.124
Revesion History : 2006/10/30 齊珂   (中訊) モデルNo.139
Revesion History : 2006/12/01 周捷 (中訊) 仕様変更管理No.150
Revesion History : 2007/02/26 吉麗ナ (中訊) 仕様変更管理No.200
Revesion History : 2007/02/26 吉麗ナ (中訊) 仕様変更・モデル206
Revesion History : 2007/03/13 吉麗ナ (中訊) 仕様変更管理No.211
Revesion History : 2007/03/16 吉麗ナ (中訊) 仕様変更管理No.212
Revesion History : 2007/03/13 吉麗ナ (中訊) 仕様変更管理No.208
Revesion History : 2007/04/03 謝旋 (中訊) 仕様変更管理No.213
Revesion History : 2007/04/19 謝旋 (中訊) 仕様変更管理No.214
Revesion History : 2007/06/21 佐藤 (SCS) 仕様変更管理No.215
Revesion History : 2007/07/13 武波 (中訊) 仕様変更管理No.219
Revesion History : 2007/09/07 武波 (中訊) 仕様変更管理No.223
Revesion History : 2007/09/10 武波 (中訊) 仕様変更管理No.224
Revesion History : 2007/09/10 武波 (中訊) 仕様変更管理No.225
Revesion History : 2007/08/27 武波 (中訊) 仕様変更管理No.216
Revesion History : 2007/08/30 武波 (中訊) 仕様変更管理No.222
Revesion History : 2007/09/12 武波 (中訊) 仕様変更管理No.226
Revesion History : 2007/11/01 武波 (中訊) 仕様変更・モデルNo.228
Revesion History : 2008/02/15 馮海涛 (中訊) 仕様変更・モデルNo.229
Revesion History : 2008/05/20 車進　@ (中訊) 仕様変更・モデルNo.230 231 232 233
Revesion History : 2008/06/18 末満正巳　@ (SCS) 仕様変更・モデルNo.235 236 237
Revesion History : 2008/08/01 張少傑 (中訊) 仕様変更・モデルNo.238
Revesion History : 2008/09/22 武波 (中訊) 仕様変更・モデルNo.250,252
Revesion History : 2010/02/21 武波 (中訊) 仕様変更・モデルNo.258,No.264
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultSortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FilterQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoAsset;

import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.WEB3AccInfoLockAccYAccRegisterRelease;
import webbroker3.accountinfo.WEB3AccInfoMaster;
import webbroker3.accountinfo.WEB3AccInfoMobileOfficeInfoRegist;
import webbroker3.accountinfo.data.CommCampaignAccHistoryRow;
import webbroker3.accountinfo.data.CommCampaignProductMstRow;
import webbroker3.accountinfo.define.WEB3AccInfoBankAccountRegiDef;
import webbroker3.accountinfo.define.WEB3AccInfoRegistTypeDef;
import webbroker3.accountinfo.define.WEB3AccInfoStateDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoStockLoanAccOpenDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoTaxTypeDef;
import webbroker3.accountinfo.define.WEB3CreateStateDivDef;
import webbroker3.accountinfo.define.WEB3IfoTradingRegistDef;
import webbroker3.accountinfo.define.WEB3MobileOfficeChangeStateDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo;
import webbroker3.accountinfo.message.WEB3AccInfoAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoBatoInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCfdAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCampaignInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCorporationInfo;
import webbroker3.accountinfo.message.WEB3AccInfoFxAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoInsiderInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressInfoUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressTypeUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMultiMailAddressInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMultiMailAddressList;
import webbroker3.accountinfo.message.WEB3AccInfoStockLoanAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoStockOptionInfo;
import webbroker3.accountinfo.message.WEB3AccInfoStopInfo;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoAccountBaseInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommissionCourseRegistInfoCreatedService;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FeqAccountRow;
import webbroker3.aio.data.FxAccountCodeDao;
import webbroker3.aio.data.FxAccountCodeRow;
import webbroker3.aio.data.FxAccountDao;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BankAccountRegiDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3CumulativeAccountDivDef;
import webbroker3.common.define.WEB3FinDelDivDef;
import webbroker3.common.define.WEB3ForeignSecAccOpenDiv;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3FxSystemDivDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MngLockDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.define.WEB3TransferDivDef;
import webbroker3.common.define.WEB3ValidDivDef;
import webbroker3.common.define.WEB3YellowCustomerDef;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeInsider;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.AccountInfoMstRow;
import webbroker3.gentrade.data.AccountMailAddressRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.ExclusiveUseAccountDao;
import webbroker3.gentrade.data.ExclusiveUseAccountRow;
import webbroker3.gentrade.data.FTransFinInstitutionRow;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.gentrade.data.MailAssortmentRow;
import webbroker3.gentrade.data.StockOptionProductRow;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.data.TransferedFinInstitutionDao;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.point.data.OrixTradeBonusPlanDao;
import webbroker3.point.data.OrixTradeBonusPlanRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.WEB3RuitoPositionManager;

/**
 * (お客様情報顧客基本情報作成サービスImpl)<BR>
 * お客様情報顧客基本情報作成サービス実装クラス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoAccountBaseInfoCreatedServiceImpl implements WEB3AccInfoAccountBaseInfoCreatedService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoAccountBaseInfoCreatedServiceImpl.class);  
        
    /**
     * @@roseuid 418F39FD037A
     */
    public WEB3AccInfoAccountBaseInfoCreatedServiceImpl() 
    {
     
    }
    
    /**
     * (create顧客基本情報)<BR>
     * 顧客オブジェクトより、顧客基本情報メッセージデータを作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「お客様情報（顧客基本情報）create顧客基本情報」参照。<BR>
     * @@param l_mainAccount - 顧客オブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo
     * @@roseuid 415D15F402AD
     */
    public WEB3AccInfoAccountBaseInfo createAccountBaseInfo(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createAccountBaseInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }
        
        //内部者情報を取得する。 
        WEB3AccInfoInsiderInfo[] l_insiderInfoes = createInsiderInfo(l_mainAccount);
        
        //株式委託手数料コース変更申込情報を取得する。
        WEB3AccInfoCommissionCourseChangeInfo[] l_commissionCourseChangeInfoes = createEquityCommissionCourseRegistInfo(l_mainAccount);
        
        //停止情報を取得する。
        WEB3AccInfoStopInfo l_stopInfo = createStopInfo(l_mainAccount);
        
        //携帯番号勤務先情報を取得する。 
        WEB3AccInfoMobileOfficeInfo l_mobileOfficeInfo = createMobileOfficeInfo(l_mainAccount);
        
        //専用振込先口座情報を取得する。 
        WEB3AccInfoAccountInfo l_exclusiveTransferAccountInfo = createExclusiveTransferAccountInfo(l_mainAccount);
        
        //振込先指定口座情報を取得する。 
        WEB3AccInfoAccountInfo l_transferAccountInfo = createTransferAccountInfo(l_mainAccount);

        //create外貨振込先指定口座情報(顧客)
        WEB3AccInfoAccountInfo[] l_accInfoAccountInfos = this.createForeignTransferAccountInfo(l_mainAccount);

        //電子鳩情報を取得する。
        WEB3AccInfoBatoInfo l_batoInfo = createBatoInfo(l_mainAccount);
        
        //1.8create為替保証金口座情報(顧客)
        WEB3AccInfoFxAccountInfo[] l_fxAccInformationUnits = this.createFXAccInformationUnit(l_mainAccount);
        
        //1.9getオリックス専用振込先口座(顧客)
        String l_strSubAccountCode = this.getOrixSubAccountCode(l_mainAccount);
        
        //1.10create法@人情報(顧客)
        WEB3AccInfoCorporationInfo l_corporationInfo = this.createCorporationInfo(l_mainAccount);
        
        //1.11createストックオプション銘柄情報(顧客)
        WEB3AccInfoStockOptionInfo[] l_stockOptionInfos  = this.createStockOptionInfo(l_mainAccount);
        
        //1.12create割引キャンペーン情報(顧客)
        WEB3AccInfoCommissionCampaignInfo[] l_accInfoCommissionCampaignInfos  = this.createAccInfoCampaignInfo(l_mainAccount);

        //create証券担保ローン口座開設情報(顧客)
        WEB3AccInfoStockLoanAccountInfo l_accInfoStockLoanAccountInfo =
            this.createStockLoanAccountInfo(l_mainAccount);

        //createCFD口座情報(顧客)
        WEB3AccInfoCfdAccountInfo[] l_accInfoCfdAccountInfos =
            this.createCFDAccountInfo(l_mainAccount);

        //createメールアドレス情報(顧客 : 顧客)
        WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoUnits =
            this.createMailAddressInfo(l_mainAccount);

        //createメール種別情報(顧客 : 顧客)
        WEB3AccInfoMailAddressTypeUnit[] l_mailAddressTypeUnits =
            this.createMailAddressTypeInfo(l_mainAccount);

        //ログインＩＤを取得する。
        long l_lngLoginId = l_mainAccount.getLoginId();
        log.debug("[ログインＩＤ] = " + l_lngLoginId);
        
        OpLoginAdminService l_opLoginAdminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        
        Map l_loginAttr = l_opLoginAdminService.getLoginAttributes(l_lngLoginId);
        
        LoginInfo l_loginInfo = l_opLoginAdminService.getLoginInfo(l_lngLoginId);
        
        Map l_loginTypeAttr = l_opLoginAdminService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        
        //パスワード更新日時
        Date l_loginPasswordUpdatedDate = null;
        String l_strDate = (String)l_loginAttr.get(WEB3LoginAttributeKeyDef.LAST_PWDCHANGE);
        if (l_strDate != null)
        {
            try
            {
                l_loginPasswordUpdatedDate = WEB3PasswordUtility.loginAttributeDateFormat.parse(l_strDate);
            }
            catch (ParseException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        log.debug("[パスワード更新日時] = " + l_loginPasswordUpdatedDate);
        
        //計算サービスを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //委託手数料コース（現在値）を取得する。    
        String l_strCommissionCourse = null;
        
        //1.13（get部店プリファ@レンス（顧客） != 1）の場合のみ処理実施
        if (getBranchPreferences(l_mainAccount) == null 
            || (getBranchPreferences(l_mainAccount) != null 
            && getBranchPreferences(l_mainAccount).intValue() != 1))
        {
            WEB3GentradeBizLogicProvider l_bizLogicProvider = 
                (WEB3GentradeBizLogicProvider) l_finApp.getGlobalBizLogicProvider();
            
            //有効日
            TradingSystem l_tradingSystem = GtlUtils.getTradingSystem();
            Date l_datBizDate = l_tradingSystem.getBizDate();
            log.debug("[有効日 = ]" + l_datBizDate);
            
            l_strCommissionCourse = l_bizLogicProvider.getCommissionCourseDiv(
                l_mainAccount,                              //顧客：　@顧客
                WEB3CommisionProductCodeDef.LISTING_STOCK,  //手数料商品コード：　@手数料商品コード.上場株式
                l_datBizDate                                //有効日
                );
        }
        log.debug("[委託手数料コース（現在値） = ]" + l_strCommissionCourse);
        //1.15
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //create複数アドレス情報(Row)
        //[create複数アドレス情報()に指定する引数]
        //顧客行：　@顧客.getDataSourceObject()の戻り値
        WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo =
            this.createMultiMailAddressInfo(l_mainAccountRow);

        //累投モジュールを取得する。 
        TradingModule l_ruitoTradingModule = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        
        //補助口座を取得する。 
        WEB3GentradeSubAccount l_subAccount;
        WEB3AccInfoAccountBaseInfo l_accountBaseInfo = new WEB3AccInfoAccountBaseInfo();
        if (WEB3CumulativeAccountDivDef.ESTABLISH.equals(l_mainAccountRow.getRuitoAccOpenDiv()))
        {
            try
            {
                l_subAccount = (WEB3GentradeSubAccount) l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

				Object[] l_bindVal = { ProductTypeEnum.RUITO };
				FilterQueryParamsSpec l_spec = new FilterQueryParamsSpec(" product_type = ? ", l_bindVal); 

                //累投残高を取得する。
                WEB3RuitoPositionManager l_ruitoPositionMgr = (WEB3RuitoPositionManager)l_ruitoTradingModule.getPositionManager();
                List l_lisRuitoAssets = l_ruitoPositionMgr.getAssets(
                	l_subAccount, 
					l_spec,
                	DefaultSortKeySpec.NULL_SORT_KEY_SPEC
                	);
            
                //たす累投残高（：Asset）の銘柄名（getProduct().getStandardName()）の配列を取得する。
                List l_lisProductName = new ArrayList();
            
                if (l_lisRuitoAssets != null)
                {
                    int l_intSize = l_lisRuitoAssets.size();
                    for (int i = 0; i < l_intSize; i++)
                    {
                        //　@以下の条件を満たす累投残高（：Asset）の銘柄名（getProduct().getStandardName()）の配列を取得する。
                        //－累投銘柄が存在する（getProduct()にてオブジェクトが取得できる）
                    
                        RuitoAsset l_ruitoAsset = (RuitoAsset)l_lisRuitoAssets.get(i);
                        Product l_ruitoProduct = l_ruitoAsset.getProduct();
                    
                        if (l_ruitoProduct != null)
                        {
                            l_lisProductName.add(l_ruitoProduct.getStandardName());
                        }
                    }
                }
            
                String[] l_strRuitoProductNames = new String[l_lisProductName.size()];
                l_lisProductName.toArray(l_strRuitoProductNames);
                log.debug("[銘柄名 = ]" + l_strRuitoProductNames);
            
                //中国Ｆ・ＭＭＦ登録：　@(*1) で生成した累投銘柄名の配列
                l_accountBaseInfo.ruitoRegist = l_strRuitoProductNames;
            

            }
            catch(NotFoundException l_ex)
            {
                l_accountBaseInfo.ruitoRegist = null;
            }

        }

        //携帯番号・勤務先情報変更申込情報を取得する。 
        WEB3AccInfoMobileOfficeInfoRegist l_mobileOfficeInfoRegist = 
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount); 
            

        
        //顧客基本情報メッセージオブジェクトを生成する。 
        
        
        //======================================================
        //顧客基本情報メッセージオブジェクトプロパティに、以下の通り値をセットする。
        //======================================================
        l_accountBaseInfo.fxAccountInfoList = l_fxAccInformationUnits;
        // 外国株式口座番号：　@
        // ※（顧客行.外国株式連携口座開設区分 == １：口座開設）の場合のみセット
        //     外国株式顧客テーブルを以下の条件で読み、取得した行.外国株式口座番号
        //
        //　@　@　@　@           [条件]
        //　@　@　@　@           外国株式顧客テーブル.証券会社コード = 顧客.getInstitution().getInstitutionCode() And
        //　@　@　@　@           外国株式顧客テーブル.部店コード = 顧客.getBranch().getBranchCode() And
        //　@　@　@　@           外国株式顧客テーブル.顧客コード = 顧客.getAccountCode()
        if (WEB3ForeignSecAccOpenDiv.OPEN.equals(l_mainAccountRow.getFeqConAccOpenDiv()))
        {
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException
        
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" institution_code = ? ");     
                l_sbWhere.append(" and branch_code = ? ");      
                l_sbWhere.append(" and account_code = ? ");     
        
                Object[] l_objWhere = { 
                    l_mainAccount.getInstitution().getInstitutionCode(),   
                    l_mainAccount.getBranch().getBranchCode(),        //部店コード
                    l_mainAccount.getAccountCode()
                    };
                  
                List l_lstRecords = l_processor.doFindAllQuery(
                    FeqAccountRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    null,
                    l_objWhere
                    );
                
                if (l_lstRecords == null || l_lstRecords.isEmpty())
                {
                    throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName()+ STR_METHOD_NAME);                       
                }
                
                FeqAccountRow l_feqAccountRow = (FeqAccountRow)l_lstRecords.get(0);
                l_accountBaseInfo.fstkAccountCode = l_feqAccountRow.getFeqAccountCode();
            }
            catch (DataFindException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);

                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName()+ STR_METHOD_NAME,
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

        }
        //部店コード：　@顧客.getBranch().getBranchCode()
        l_accountBaseInfo.branchCode = l_mainAccount.getBranch().getBranchCode();
        log.debug("[部店コード = ]" + l_accountBaseInfo.branchCode);
        //顧客コード：　@顧客.get表示顧客コード()
        l_accountBaseInfo.accountCode = l_mainAccount.getDisplayAccountCode();
        log.debug("[顧客コード = ]" + l_accountBaseInfo.accountCode);
        //顧客名（カナ）：　@顧客行.名前（苗字１）※顧客名（カナ）として使用する。
        l_accountBaseInfo.accountNameKana = l_mainAccountRow.getFamilyNameAlt1();
        log.debug("[顧客名（カナ） = ]" + l_accountBaseInfo.accountNameKana);
        //顧客名（漢字）：　@顧客行.名前（苗字）※顧客名（漢字）として使用する。
        l_accountBaseInfo.accountName = l_mainAccountRow.getFamilyName();
        log.debug("[顧客名（漢字） = ]" + l_accountBaseInfo.accountName);
        //生年月日年号：　@顧客行.生年月日年号
        l_accountBaseInfo.eraBorn = l_mainAccountRow.getEraBorn();
        log.debug("[生年月日年号 = ]" + l_accountBaseInfo.eraBorn);
        //生年月日：　@顧客行.生年月日
        l_accountBaseInfo.bornDate = l_mainAccountRow.getBornDate();
        log.debug("[生年月日 = ]" + l_accountBaseInfo.bornDate);
        //郵便番号：　@顧客行.郵便番号
        l_accountBaseInfo.zipCode = l_mainAccountRow.getZipCode();
        log.debug("[郵便番号 = ]" + l_accountBaseInfo.zipCode);
        //住所１（カナ）：　@顧客行.住所１（カナ）
        l_accountBaseInfo.addressKana1 = l_mainAccountRow.getAddressLine1Kana();
        log.debug("[住所１（カナ） = ]" + l_accountBaseInfo.addressKana1);
        //住所２（カナ）：　@顧客行.住所２（カナ）
        l_accountBaseInfo.addressKana2 = l_mainAccountRow.getAddressLine2Kana();
        log.debug("[住所２（カナ） = ]" + l_accountBaseInfo.addressKana2);
        //住所３（カナ）：　@顧客行.住所３（カナ）
        l_accountBaseInfo.addressKana3 = l_mainAccountRow.getAddressLine3Kana();
        log.debug("[住所３（カナ） = ]" + l_accountBaseInfo.addressKana3);
        //住所１（漢字）：　@顧客行住所１
        l_accountBaseInfo.address1 = l_mainAccountRow.getAddressLine1();
        log.debug("[住所１（漢字） = ]" + l_accountBaseInfo.address1);
        //住所２（漢字）：　@顧客行住所２
        l_accountBaseInfo.address2 = l_mainAccountRow.getAddressLine2();
        log.debug("[住所２（漢字） = ]" + l_accountBaseInfo.address2);
        //住所３（漢字）：　@顧客行住所３
        l_accountBaseInfo.address3 = l_mainAccountRow.getAddressLine3();
        log.debug("[住所３（漢字） = ]" + l_accountBaseInfo.address3);
        //電話番号：　@顧客行.電話番号
        l_accountBaseInfo.telephone = l_mainAccountRow.getTelephone();
        log.debug("[電話番号 = ]" + l_accountBaseInfo.telephone);
        //携帯番号・勤務先情報：　@create携帯番号・勤務先情報()の戻り値
        l_accountBaseInfo.mobileOfficeInfo = l_mobileOfficeInfo;
        log.debug("[携帯番号・勤務先情報 = ]" + l_accountBaseInfo.mobileOfficeInfo);
        //携帯番号・勤務先情報変更状態区分
        if (l_mobileOfficeInfoRegist == null)
        {
            //－（携帯番号・勤務先情報変更申込.get携帯番号・勤務先情報変更申込() == null）の場合、0：申込可
            l_accountBaseInfo.mobileOfficeChangeStateDiv = WEB3MobileOfficeChangeStateDivDef.REGIST_POSSIBLE;
        }
        else
        {
            if (!l_mobileOfficeInfoRegist.isDeciding())
            {
                //－（携帯番号・勤務先情報変更申込.get携帯番号・勤務先情報変更申込().is判定確認中() == false）の場合、1：申込中
                l_accountBaseInfo.mobileOfficeChangeStateDiv = WEB3MobileOfficeChangeStateDivDef.REGISTING;
            }
            else
            {
                //－（携帯番号・勤務先情報変更申込.get携帯番号・勤務先情報変更申込().is判定確認中() == true）の場合、2：確認中
                l_accountBaseInfo.mobileOfficeChangeStateDiv = WEB3MobileOfficeChangeStateDivDef.CONFIRMING;
            }
        }
        log.debug("[携帯番号・勤務先情報変更状態区分 = ]" + l_accountBaseInfo.mobileOfficeChangeStateDiv);
        //内部者情報：　@create内部者情報()の戻り値
        l_accountBaseInfo.insiderList = l_insiderInfoes;
        
        //口座開設日：　@顧客行.口座登録日
        l_accountBaseInfo.accountOpenDate = l_mainAccountRow.getAccountOpenDate();
        log.debug("[口座開設日 = ]" + l_accountBaseInfo.accountOpenDate);
        //扱者コード：　@顧客行.扱者コード（SONAR）
        l_accountBaseInfo.traderCode = l_mainAccountRow.getSonarTraderCode();
        log.debug("[扱者コード = ]" + l_accountBaseInfo.traderCode);
        //振込先指定口座：　@create振込先指定口座情報()の戻り値
        l_accountBaseInfo.transferAccount = l_transferAccountInfo;
        //外貨振込先指定口座一覧：　@create外貨振込先指定口座情報()の戻り値
        l_accountBaseInfo.foreignTransferAccountList = l_accInfoAccountInfos;
        
        //専用振込先口座：　@create専用振込先口座情報()の戻り値
        l_accountBaseInfo.exclusiveTransferAccount = l_exclusiveTransferAccountInfo;
                
        //メールアドレス：　@顧客行.emailアドレス
        l_accountBaseInfo.mailAddress = l_mainAccountRow.getEmailAddress();
        log.debug("[メールアドレス = ]" + l_accountBaseInfo.mailAddress);
        //メールアドレス更新日時：　@顧客行.emailアドレス更新日時
        l_accountBaseInfo.mailAddressUpdateDate = l_mainAccountRow.getEmailLastUpdatedTimestamp();
        log.debug("[メールアドレス更新日時 = ]" + l_accountBaseInfo.mailAddressUpdateDate);
        //株式約定メール送信フラグ：　@（顧客行.株式約定メール送信フラグ == BooleanEnum.TRUE）の場合true、以外false。
        l_accountBaseInfo.equityExecMailFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getEquityOrderExeMailFlag());
        log.debug("[株式約定メール送信フラグ = ]" + l_accountBaseInfo.equityExecMailFlag);
        //株式未約定メール送信フラグ：　@（顧客行.株式未約定メール送信フラグ == BooleanEnum.TRUE）の場合true、以外false。
        l_accountBaseInfo.equityUnExecMailFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getEquityOrderUnexecMailFlag());
        log.debug("[株式未約定メール送信フラグ = ]" + l_accountBaseInfo.equityUnExecMailFlag);      
        //先物OP約定メール送信フラグ：　@（顧客行.先物OP株式約定メール送信フラグ == BooleanEnum.TRUE）の場合true、以外false。
        l_accountBaseInfo.futOpExecMailFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getIfoOrderExecMailFlag());
        log.debug("[先物OP約定メール送信フラグ = ]" + l_accountBaseInfo.futOpExecMailFlag);
        //先物OP未約定メール送信フラグ：　@（顧客行.先物OP未約定メール送信フラグ == BooleanEnum.TRUE）の場合true、以外false。
        l_accountBaseInfo.futOpUnExecMailFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getIfoOrderUnexecMailFlag());
        log.debug("[先物OP未約定メール送信フラグ = ]" + l_accountBaseInfo.futOpUnExecMailFlag);
        //案内メール送信フラグ：　@（顧客行.案内メール送信フラグ == BooleanEnum.TRUE）の場合true、以外false。
        l_accountBaseInfo.guidanceMailFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getInformationMailFlag());
        log.debug("[案内メール送信フラグ = ]" + l_accountBaseInfo.guidanceMailFlag);
        //ログインエラー回数：　@OpLoginAdminService.getLoginInfo().getFailureCount()
        l_accountBaseInfo.loginErrorCount = "" + l_loginInfo.getFailureCount();
        log.debug("[ログインエラー回数 = ]" + l_accountBaseInfo.loginErrorCount);
        //パスワード更新日時：　@OpLoginAdminService.getLoginAttrubutes().get(ログイン属性名.前回パスワード更新日付）
        l_accountBaseInfo.lPasswordUpdateDate = l_loginPasswordUpdatedDate;
        log.debug("[パスワード更新日時 = ]" + l_accountBaseInfo.lPasswordUpdateDate);
        //暗証番号更新日時：　@顧客行.取引パスワード更新日時
        l_accountBaseInfo.passwordUpdateDate = l_mainAccountRow.getTrPwdLastUpdateTimestamp();
        log.debug("[暗証番号更新日時 = ]" + l_accountBaseInfo.passwordUpdateDate);
        //暗証番号変更可能フラグ
        String l_strTradingPwdEnv = (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);
        if (WEB3TradingPwdEnvDef.DEFAULT.equals(l_strTradingPwdEnv))
        {
            //－（OpLoginAdminService.getLoginTypeAttrubutes().get(属性名.取引パスワード設定） == 0：DEFAULT）の場合、false。
            l_accountBaseInfo.passwordUpdateAbleFlag = false;
        } 
        else if (WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strTradingPwdEnv))
        {
            //－（OpLoginAdminService.getLoginTypeAttrubutes().get(属性名.取引パスワード設定） == 1：取引パスワード使用）の場合、true。
            l_accountBaseInfo.passwordUpdateAbleFlag = true;
        }
        log.debug("[暗証番号変更可能フラグ = ]" + l_accountBaseInfo.passwordUpdateAbleFlag);
        //停止情報：　@create停止情報()の戻り値
        l_accountBaseInfo.stopInfo = l_stopInfo;
        
        //手数料コース：　@計算サービス.get委託手数料コース()の戻り値
        l_accountBaseInfo.commissionCourse = l_strCommissionCourse;
        log.debug("[手数料コース = ]" + l_accountBaseInfo.commissionCourse);
        //手数料コース変更申込情報一覧：　@create株式委託手数料コース変更申込情報()の戻り値
        l_accountBaseInfo.commissionCourseChangeInfoList = l_commissionCourseChangeInfoes;
        
        //有料情報フラグ：　@顧客.isリアル客()の戻り値
        l_accountBaseInfo.chargedInfoFlag = l_mainAccount.isRealCustomer();
        log.debug("[有料情報フラグ = ]" + l_accountBaseInfo.chargedInfoFlag);
        //振込先指定金融機@関登録フラグ：　@顧客.is円貨振込先（銀行口座）登録区分()の戻り値。
        l_accountBaseInfo.transferFinancialInstitutionFlag = l_mainAccount.isJapaneseCurrencyBankAccountRegi();
        log.debug("[振込先指定金融機@関登録フラグ = ]" + l_accountBaseInfo.transferFinancialInstitutionFlag);  
        //外貨振込先指定金融機@関登録フラグ：　@顧客.is外貨振込先（銀行口座）登録区分() の戻り値。
        l_accountBaseInfo.foreignTransferFinancialInstitutionFlag = l_mainAccount.isForeignCurrencyBankAccountRegi();
        log.debug("[外貨振込先指定金融機@関登録フラグ = ]" + l_accountBaseInfo.foreignTransferFinancialInstitutionFlag);
        //制度信用取引登録フラグ：　@（顧客行.制度信用取引口座開設区分 == 1：口座開設）の場合true、以外false。
        l_accountBaseInfo.marketMarginFlag = WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginSysAccOpenDiv());
        log.debug("[制度信用取引登録フラグ]" + l_accountBaseInfo.marketMarginFlag);
        //一般信用取引登録フラグ：　@（顧客行.一般信用取引口座開設区分 == 1：口座開設）の場合true、以外false。
        l_accountBaseInfo.institutionMarginFlag = WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(l_mainAccountRow.getMarginGenAccOpenDiv());
        log.debug("[一般信用取引登録フラグ = ]" + l_accountBaseInfo.institutionMarginFlag);
        //先物OP取引登録：
        boolean l_blnFuturesOpen = l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES);
        log.debug("[l_blnFuturesOpen= ]" + l_blnFuturesOpen);
        boolean l_blnOptionOpen = l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION);
        log.debug("[l_blnOptionOpen = ]" + l_blnOptionOpen);
        
        if (!l_blnFuturesOpen && l_blnOptionOpen)
        {
            //－（顧客is先物OP口座開設（先物／オプション区分.先物） == false && 
            //   顧客is先物OP口座開設（先物／オプション区分.オプション） == true）の場合、1：登録済み（OP買建取引）
            l_accountBaseInfo.futOpTradeRegist = WEB3IfoTradingRegistDef.REGISTED_OPTION_BUY_TRADING;
        }
        else if (l_blnFuturesOpen && !l_blnOptionOpen)
        {
            //－（顧客is先物OP口座開設（先物／オプション区分.先物） == true && 
            //    顧客is先物OP口座開設（先物／オプション区分.オプション） == false）の場合、2：登録済み（先物取引）
            l_accountBaseInfo.futOpTradeRegist = WEB3IfoTradingRegistDef.REGISTED_FUTURES_TRADING;
        }
        else if (l_blnFuturesOpen && l_blnOptionOpen)
        {
            //－（顧客is先物OP口座開設（先物／オプション区分.先物） == true && 
            //   顧客is先物OP口座開設（先物／オプション区分.オプション） == true）の場合、3：登録済み（先物／OP取引）
            l_accountBaseInfo.futOpTradeRegist = WEB3IfoTradingRegistDef.REGISTED_FUTURES_OPTION_TRADING;            
        }
        else
        {
            //－（顧客is先物OP口座開設（先物／オプション区分.先物） == false && 
            //   顧客is先物OP口座開設（先物／オプション区分.オプション） == false）の場合、null
            l_accountBaseInfo.futOpTradeRegist = null;
        }
        log.debug("[先物OP取引登録 = ]" + l_accountBaseInfo.futOpTradeRegist);


        //現物株式口座区分：
        l_accountBaseInfo.equityTaxType = getTaxType(l_mainAccountRow.getTaxType());
        log.debug("[現物株式口座区分 = ]" + getTaxType(l_mainAccountRow.getTaxType()));        
        //現物株式口座区分（次年）：
        l_accountBaseInfo.equityTaxTypeNext = getTaxType(l_mainAccountRow.getTaxTypeNext());
        log.debug("[現物株式口座区分（次年）= ]" + getTaxType(l_mainAccountRow.getTaxTypeNext()));
        //現物株式特定口座開設日：　@顧客行.現物株式特定口座開設日
        l_accountBaseInfo.equityCapitalGainTaxOpenDate = l_mainAccountRow.getEquitySpAccOpenDate();
        log.debug("[現物株式特定口座開設日 = ]" + l_mainAccountRow.getEquitySpAccOpenDate());
        //信用取引口座区分
        l_accountBaseInfo.marginTaxType = getTaxType(l_mainAccountRow.getMarginTaxType());
        log.debug("[信用取引口座区分 = ]" + getTaxType(l_mainAccountRow.getMarginTaxType()));
        //信用取引口座区分（次年）
        l_accountBaseInfo.marginTaxTypeNext = getTaxType(l_mainAccountRow.getMarginTaxTypeNext());
        log.debug("[信用取引口座区分（次年） = ]" + getTaxType(l_mainAccountRow.getMarginTaxTypeNext()));
        //信用取引特定口座開設日：　@顧客行.信用取引特定口座開設日
        l_accountBaseInfo.marginCapitalGainTaxOpenDate = l_mainAccountRow.getMarginSpAccOpenDate();
        log.debug("[信用取引特定口座開設日 = ]" + l_mainAccountRow.getMarginSpAccOpenDate());
        //電子鳩情報：　@create電子鳩情報()の戻り値
        l_accountBaseInfo.batoStatus = l_batoInfo;
        log.debug("[電子鳩情報 = ]" + l_batoInfo);
        log.exiting(STR_METHOD_NAME);
        log.debug("[l_accountBaseInfo = ]" + l_accountBaseInfo);
        //オリックス専用振込先口座：　@getオリックス専用振込先口座()の戻り値
        l_accountBaseInfo.orixSubAccCode = l_strSubAccountCode;
        //累投口座開設区分：　@顧客行.累投口座開設区分
        l_accountBaseInfo.ruitoAccountOpenDiv = l_mainAccountRow.getRuitoAccOpenDiv();
        //外国証券口座開設区分：　@顧客行.外国証券口座開設区分
        l_accountBaseInfo.foreignSecAccOpenDiv = l_mainAccountRow.getForeignSecAccOpenDiv();
        //特定管理口座開設区分：　@顧客行.特定管理口座開設区分
        l_accountBaseInfo.capitalGainTaxAccOpenDiv = l_mainAccountRow.getSpMngAccOpenDiv();
        //MRF口座開設区分：　@顧客行.MRF口座開設区分
        l_accountBaseInfo.mrfAccOpenDiv = l_mainAccountRow.getMrfAccOpenDiv();
        //口座タイプ：　@顧客行.口座タイプ
        l_accountBaseInfo.accountType = "" + l_mainAccountRow.getAccountType().intValue();
        if (l_mobileOfficeInfoRegist != null)
        {
            //顧客情報変更区分1：　@携帯番号・勤務先情報変更申込.get判定確認中フラグ()
            l_accountBaseInfo.accountChangeDiv1 = l_mobileOfficeInfoRegist.getDecisionFlag();
            //顧客情報変更区分2：　@携帯番号・勤務先情報変更申込.get判定結果()
            l_accountBaseInfo.accountChangeDiv2 = l_mobileOfficeInfoRegist.getDecision();
            //顧客情報変更区分3：　@携帯番号・勤務先情報変更申込.get削除フラグ()
            l_accountBaseInfo.accountChangeDiv3 = l_mobileOfficeInfoRegist.getDeleteFlag();
        }

        //口座情報マスタオブジェクトを取得する。 
        WEB3AccInfoMaster l_accInfoMaster = WEB3AccInfoMaster.getAccInfoMaster(l_mainAccount);
        if(l_accInfoMaster != null)
        {
            AccountInfoMstRow l_accountInfoMstRow = (AccountInfoMstRow)l_accInfoMaster.getDataSourceObject();
            //職業：　@口座情報マスタ.get口座情報マスタ().getDataSourceObject.職業区分
            l_accountBaseInfo.occupationDiv = l_accountInfoMstRow.getOccupationDiv();
            //国籍：　@口座情報マスタ.get口座情報マスタ().getDataSourceObject.国籍
            l_accountBaseInfo.nationality = l_accountInfoMstRow.getNationality();
            //国籍その他：　@口座情報マスタ.get口座情報マスタ().getDataSourceObject.国籍その他
            l_accountBaseInfo.nationalityOther = l_accountInfoMstRow.getNationalityOther();
            //顧客正式名称1：　@口座情報マスタ.get口座情報マスタ().getDataSourceObject.顧客正式名称1
            l_accountBaseInfo.accountRealName1 = l_accountInfoMstRow.getRealName1();
            //顧客正式名称2：　@口座情報マスタ.get口座情報マスタ().getDataSourceObject.顧客正式名称2
            l_accountBaseInfo.accountRealName2 = l_accountInfoMstRow.getRealName2();
        }
        
        //法@人情報：　@create法@人情報()の戻り値
        l_accountBaseInfo.corporationInfo =  l_corporationInfo;
        
        //ストックオプション口座開設区分：　@顧客行.ストックオプション口座開設区分
        l_accountBaseInfo.stockOptionAccOpenDiv = l_mainAccountRow.getStockOptionAccOpenDiv();
        
        //ストックオプション口座開設情報一覧：　@createストックオプション銘柄情報()の戻り値
        l_accountBaseInfo.stockOptionAccOpenList = l_stockOptionInfos;
        
        //証券担保ローン区分：　@顧客行.証券担保ローン区分
        l_accountBaseInfo.comStockLoanDiv = l_mainAccountRow.getSecuredLoanSecAccOpenDiv();

        //性別：　@顧客行.性別
        l_accountBaseInfo.sex = l_mainAccountRow.getSex();

        //手数料割引キャンペーン情報 = create割引キャンペーン情報（）の戻り値
        l_accountBaseInfo.commissionCampaignInfoList = l_accInfoCommissionCampaignInfos;

        //モバイル専用口座開設区分：　@顧客行.モバイル専用口座開設区分
        l_accountBaseInfo.mobileAccountDiv = l_mainAccountRow.getOnlyMobileOpenDiv();

        l_accountBaseInfo.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;

        //証券担保ローン口座開設情報：　@create証券担保ローン口座開設情報()の戻り値
        l_accountBaseInfo.stockLoanAccountInfo = l_accInfoStockLoanAccountInfo;

        //プロアマ区分：　@顧客行.プロアマ区分
        l_accountBaseInfo.proAmDiv = l_mainAccountRow.getProamDiv();

        //PTS口座開設区分：　@顧客行.PTS口座開設区分
        l_accountBaseInfo.ptsAccOpenDiv = l_mainAccountRow.getPtsAccOpenDiv();

        //放送法@： 顧客行.放送法@
        l_accountBaseInfo.broadcastLaw = l_mainAccountRow.getBroadcastLaw();

        //航空法@： 顧客行.航空法@
        l_accountBaseInfo.aviationLaw = l_mainAccountRow.getAviationLaw();

        //ＮＴＴ法@： 顧客行.NTT法@
        l_accountBaseInfo.nttLaw = l_mainAccountRow.getNttLaw();

        //配当金振込指定区分： 顧客行.配当金振込指定区分
        l_accountBaseInfo.dividendTransDesignate = l_mainAccountRow.getDividendTransDesignate();

        //常任代理人： 顧客行.常任代理人
        l_accountBaseInfo.standingProxy = l_mainAccountRow.getStandingProxy();

        //法@定代理人： 顧客行.法@定代理人
        l_accountBaseInfo.statutoryAgent = l_mainAccountRow.getStatutoryAgent();

        //加入者口座番号： 顧客行.加入者口座番号
        l_accountBaseInfo.affiliateAccountCode = l_mainAccountRow.getAffiliateAccountCode();

        //機@構通知完了区分： 顧客行.機@構通知完了区分
        l_accountBaseInfo.agencyNotifyCmpDiv = l_mainAccountRow.getAgencyNotifyCmpDiv();

        //CFD口座情報[]：　@createCFD口座情報()の戻り値
        l_accountBaseInfo.cfdAccountInfoList = l_accInfoCfdAccountInfos;

        WEB3AccInfoMultiMailAddressList l_multiMailAddressList =
            new WEB3AccInfoMultiMailAddressList();
        l_multiMailAddressList.mailAddressInfoList =l_mailAddressInfoUnits;
        l_multiMailAddressList.mailTypeInfoList = l_mailAddressTypeUnits;
        l_accountBaseInfo.multiMailAddressList = l_multiMailAddressList;
        return l_accountBaseInfo;

    }
    
    /**
     * (create内部者情報)<BR>
     * 顧客オブジェクトより、内部者情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@内部者オブジェクトを取得する。<BR>
     * 　@内部者.get内部者()にて、内部者オブジェクトの配列を取得する。<BR>
     * 　@取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * 　@[get内部者()に指定する引数]<BR>
     * 　@顧客：　@顧客<BR>
     * <BR>
     * ２）　@株式プロダクトマネージャ取得<BR>
     * 　@※FinApp.getTradingModule(ProductTypeEnum.株式<BR>)
     * .getProductManager()にて取得する。<BR>
     * <BR>
     * ３）　@内部者情報編集<BR>
     * 　@１）の戻り値の要素毎に３－１）～３－５）を繰り返す。<BR>
     * <BR>
     * 　@３－１）　@株式銘柄オブジェクトを取得する。<BR>
     * 　@　@株式プロダクトマネージャ.getProduct()にて株式銘柄オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@[getProduct()に指定する引数]<BR>
     * 　@　@銘柄ＩＤ：　@内部者.銘柄ＩＤ<BR>
     * <BR>
     * 　@３－２）　@内部者情報オブジェクトを生成する。<BR>
     * 　@３－３）　@以下の通り、プロパティをセットし返却する。<BR>
     * <BR>
     * 　@　@内部者情報.銘柄コード = 株式銘柄.getProductCode()<BR>
     * 　@　@内部者情報.銘柄名 = 株式銘柄.getStandardName()<BR>
     * 　@　@内部者情報.関係コード = 内部者.関係コード<BR>
     * 　@　@内部者情報.役員名 = 内部者マスタ行.役員名<BR>
     * 　@　@内部者情報.役職名 = 内部者マスタ行.役職名<BR>
     * 　@　@内部者情報,登録状況区分 = 内部者マスタ行.登録状況区分<BR>
     * <BR>
     * ４）　@生成した内部者情報を配列で返却する。<BR>
     * @@param l_mainAccount - 顧客オブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoInsiderInfo[]
     * @@roseuid 415D1B450145
     */
    protected WEB3AccInfoInsiderInfo[] createInsiderInfo(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createInsiderInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //内部者オブジェクトを取得する。
        WEB3GentradeInsider[] l_insiders = WEB3GentradeInsider.getInsider(l_mainAccount);
        
        //取得できなかった場合は、nullを返却する。 
        if (l_insiders == null || l_insiders.length == 0)
        {
            return null;
        }
        
        WEB3AccInfoInsiderInfo[] l_insiderInfoes = new WEB3AccInfoInsiderInfo[l_insiders.length];
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_equityTradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        //株式プロダクトマネージャ取得 
        WEB3EquityProductManager l_equityProductMgr = (WEB3EquityProductManager)l_equityTradingModule.getProductManager();
        
        //内部者情報編集
        for (int i = 0; i < l_insiders.length; i++)
        {
            WEB3GentradeInsider l_insider = l_insiders[i];
            InsiderRow l_insiderRow = (InsiderRow)l_insider.getDataSourceObject();
            
            //株式銘柄オブジェクトを取得する。
            WEB3EquityProduct l_equityProduct;
            try
            {
                l_equityProduct = (WEB3EquityProduct) l_equityProductMgr.getProduct(l_insiderRow.getProductId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            EqtypeProductRow l_productRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject();
            
            //内部者情報オブジェクトを生成する。 
            l_insiderInfoes[i] = new WEB3AccInfoInsiderInfo();
            
            //内部者情報.銘柄コード = 株式銘柄.getProductCode()
            l_insiderInfoes[i].productCode = l_equityProduct.getProductCode();
            
            //内部者情報.銘柄名 = 株式銘柄.getStandardName()
            l_insiderInfoes[i].productName = l_productRow.getStandardName();
            
            //内部者情報.関係コード = 内部者.関係コード
            l_insiderInfoes[i].relationCode = l_insider.getRelationDiv();
            
            //内部者情報.役員名 = 内部者マスタ行.役員名
            l_insiderInfoes[i].executive =  l_insiderRow.getOfficerName();
            
            //内部者情報.役職名 = 内部者マスタ行.役職名
            l_insiderInfoes[i].position =  l_insiderRow.getPostName();
             
            //内部者情報,登録状況区分 = 内部者マスタ行.登録状況区分
            l_insiderInfoes[i].registStateDiv = l_insiderRow.getRegistDiv();
            
        }
                
        log.exiting(STR_METHOD_NAME);
        return l_insiderInfoes;
    }
    
    /**
     * (create株式委託手数料コース変更申込情報)<BR>
     * 顧客オブジェクトより、手数料コース変更申込情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@委託手数料コース変更申込オブジェクトを取得する。<BR>
     * 　@委託手数料コース変更申込.get委託手数料コース変更申込()にて、<BR>
     * 委託手数料変更申込オブジェクトを取得する。<BR>
     * 　@取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * 　@[get委託手数料コース変更申込()に指定する引数]<BR>
     * 　@顧客：　@顧客<BR>
     * 　@手数料商品コード：　@手数料商品コード.”上場株式”<BR>
     * <BR>
     * ２）　@手数料コース変更申込情報作成<BR>
     * 　@お客様情報委託手数料コース変更申込情報作成サービスを取得し、<BR>
     * 　@create手数料コース変更申込情報()メソッドをコールし、戻り値を返却する。<BR>
     * <BR>　@
     * 　@[create手数料コース変更申込情報()に指定する引数]<BR>
     * 　@委託手数料コース変更申込[]：　@get委託手数料変更申込()の戻り値<BR>
     * @@param l_mainAccount - 顧客オブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo[]
     * @@roseuid 415D1B810145
     */
    protected WEB3AccInfoCommissionCourseChangeInfo[] createEquityCommissionCourseRegistInfo(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createEquityCommissionCourseRegistInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //委託手数料コース変更申込オブジェクトを取得する。 
        WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegists = 
            WEB3AccInfoCommissionCourseRegist.getCommissionCourseRegist(l_mainAccount, WEB3CommisionProductCodeDef.LISTING_STOCK);
        
        //取得できなかった場合は、nullを返却する。 
        if (l_commissionCourseRegists == null || l_commissionCourseRegists.length == 0)
        {
            return null;
        }
                
        //お客様情報委託手数料コース変更申込情報作成サービスを取得        
        WEB3AccInfoCommissionCourseRegistInfoCreatedService l_service = 
            (WEB3AccInfoCommissionCourseRegistInfoCreatedService)Services.getService(WEB3AccInfoCommissionCourseRegistInfoCreatedService.class);
        
        WEB3AccInfoCommissionCourseChangeInfo[] l_commissionCourseChangeInfoes = 
            l_service.createCommissionCourseRegistInfo(l_commissionCourseRegists);
        
        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseChangeInfoes;        
    }
    
    /**
     * (create停止情報)<BR>
     * 顧客オブジェクトより、停止情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@停止情報オブジェクトを生成する。<BR>
     * ２）　@顧客.getDataSourceObject()にて顧客行オブジェクトを取得する。<BR>
     * ３）　@以下の通り、プロパティをセットし、返却する。<BR>
     * <BR>
     * 　@停止情報.Ｙ客区分 = 顧客行.Ｙ客区分<BR>
     * 　@停止情報.管理ロック区分 = 顧客行.管理ロック区分<BR>
     * 　@停止情報.管理ロック理由フラグ（立替金） = <BR>
     * 　@　@－（顧客行.管理ロック理由フラグ（立替金） == <BR>
     * BooleanEnum.TRUE）の場合、true。<BR>
     * 　@　@－（顧客行.管理ロック理由フラグ（立替金） == <BR>
     * BooleanEnum.FALSE）の場合、false。<BR>
     * 　@停止情報.管理ロック理由フラグ（保証金未入） = <BR>
     * 　@　@－（顧客行.管理ロック理由フラグ（保証金未入） == <BR>
     * BooleanEnum.TRUE）の場合、true。<BR>
     * 　@　@－（顧客行.管理ロック理由フラグ（保証金未入） == <BR>
     * BooleanEnum.FALSE）の場合、false。<BR>
     * 　@停止情報.管理ロック理由フラグ（適格担保不足） = <BR>
     * 　@　@－（顧客行.管理ロック理由フラグ（適格担保不足） == <BR>
     * BooleanEnum.TRUE）の場合、true。<BR>
     * 　@　@－（顧客行.管理ロック理由フラグ（適格担保不足） == <BR>
     * BooleanEnum.FALSE）の場合、false。<BR>
     * 　@停止情報.管理ロック理由フラグ（預り証長期未差替） = <BR>
     * 　@　@－（顧客行.管理ロック理由フラグ（預り証長期未差替） == <BR>
     * BooleanEnum.TRUE）の場合、true。<BR>
     * 　@　@－（顧客行.管理ロック理由フラグ（預り証長期未差替） == <BR>
     * BooleanEnum.FALSE）の場合、false。<BR>
     * 　@停止情報.管理ロック解除開始日 = 顧客行.管理ロック解除開始日<BR>
     * 　@停止情報.管理ロック解除終了日 = 顧客行.管理ロック解除終了日<BR>
     * 　@停止情報.支店ロック区分 = 顧客行.支店ロック区分<BR>
     * 　@停止情報.注文認可区分 = 顧客行.注文認可区分<BR>
     * 　@停止情報.状況区分 = 取引停止中※の場合、1：禁止中。<BR>
     * 以外、0：DEFAULT（禁止中でない）。<BR>
     * <BR>
     * 　@停止情報.停止状況登録理由 = 顧客行.ロック登録理由。 <BR>
     * 　@停止情報.登録日時 =  <BR>
     * 　@　@－（顧客行.Y客区分==Y客 or 顧客行.管理ロック==ロック or 顧客行.支店ロック==ロック）の場合、顧客行.停止状況更新日時。<BR> 
     * 　@　@－以外、null。 <BR>
     * 　@停止情報.ロック客登録解除SONAR受付状況 = <BR>
     * 　@　@－（ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*1) == null）の場合、null。<BR> 
     * 　@　@－以外、ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*1).get処理区分()。<BR> 
     * 　@停止情報.Ｙ客登録解除SONAR受付状況 = <BR>
     * 　@　@－（ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*2) == null）の場合、null。 <BR>
     * 　@　@－以外、ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*2).get処理区分()。 <BR>
     * 　@　@[getロック客Ｙ客登録解除()に指定する引数] <BR> 
     * 　@　@顧客：　@顧客 <BR>
     * 　@　@データコード：　@(*1) ”GI846”:ロック客登録解除 ／ (*2) ”GI847”:Y客登録解除 <BR>
     * <BR>
     * 　@　@※　@取引停止中の判定<BR>
     * 　@　@１）　@FinApp.getCommonOrderValidator()にて、<BR>
     * 注文チェックインスタンスを取得する。
     * 　@　@２）　@注文チェック.validate取引可能顧客()にて、<BR>
     * エラーが返却された場合は取引停止中と判断する。
     * <BR>
     * 　@　@　@　@　@[validate取引可能顧客()に指定する引数]<BR>
     * 　@　@　@　@　@顧客：　@顧客<BR>
     * @@param l_mainAccount - 顧客オブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoStopInfo
     * @@roseuid 415D1BD00107
     */
    public WEB3AccInfoStopInfo createStopInfo(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createStopInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoStopInfo l_stopInfo = new WEB3AccInfoStopInfo();
        
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //停止情報.Ｙ客区分 = 顧客行.Ｙ客区分
        l_stopInfo.yellowAccountDiv = l_mainAccountRow.getYellowCustomer();
        
        //停止情報.管理ロック区分 = 顧客行.管理ロック区分
        l_stopInfo.mngLockDiv = l_mainAccountRow.getMngLockFlag();
     
        //停止情報.管理ロック理由フラグ（立替金） = 
        //  －（顧客行.管理ロック理由フラグ（立替金） == BooleanEnum.TRUE）の場合、true。 
        //  －（顧客行.管理ロック理由フラグ（立替金） == BooleanEnum.FALSE）の場合、false。
        l_stopInfo.mngExpenseLockReasonFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getMngLockFlagAdvance());

        //停止情報.管理ロック理由フラグ（保証金未入） = 
        //  －（顧客行.管理ロック理由フラグ（保証金未入） == BooleanEnum.TRUE）の場合、true。 
        //  －（顧客行.管理ロック理由フラグ（保証金未入） == BooleanEnum.FALSE）の場合、false。
        l_stopInfo.mngDepositLockReasonFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getMngLockFlagUnpayMargin());
        
        //停止情報.管理ロック理由フラグ（適格担保不足） = 
        //  －（顧客行.管理ロック理由フラグ（適格担保不足） == BooleanEnum.TRUE）の場合、true。 
        //  －（顧客行.管理ロック理由フラグ（適格担保不足） == BooleanEnum.FALSE）の場合、false。
        l_stopInfo.mngCollateralLockReasonFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getMngLockFlagShortSecurity());
                         
        //停止情報.管理ロック理由フラグ（預り証長期未差替） = 
        //  －（顧客行.管理ロック理由フラグ（預り証長期未差替） == BooleanEnum.TRUE）の場合、true。 
        //  －（顧客行.管理ロック理由フラグ（預り証長期未差替） == BooleanEnum.FALSE）の場合、false。
        l_stopInfo.mngReceiptLockReasonFlag = BooleanEnum.TRUE.equals(l_mainAccountRow.getMngLockFlagUnsubstitDepo());
                 
        //停止情報.管理ロック解除開始日 = 顧客行.管理ロック解除開始日
        l_stopInfo.mngLockCancelStartDate = l_mainAccountRow.getMngLockOffStartDate();
         
        //停止情報.管理ロック解除終了日 = 顧客行.管理ロック解除終了日
        l_stopInfo.mngLockCancelEndDate = l_mainAccountRow.getMngLockOffEndDate();
        
        //停止情報.支店ロック区分 = 顧客行.支店ロック区分
        l_stopInfo.branchLockDiv = l_mainAccountRow.getBranchLock();
        
        //停止情報.注文認可区分 = 顧客行.注文認可区分
        l_stopInfo.orderPermitDiv = l_mainAccountRow.getOrderPermission();
        
        //停止情報.停止状況登録理由 = 顧客行.ロック登録理由。
        l_stopInfo.stopStateRegistReason = l_mainAccountRow.getLockRegistrationReason();
        
        /*
         * 停止情報.登録日時 =  <BR>
         * 　@　@－（顧客行.Y客区分==Y客 or 顧客行.管理ロック==ロック or
         *     顧客行.支店ロック==ロック）の場合、顧客行.停止状況更新日時。<BR> 
         * 　@　@－以外、null。 <BR>
         */
        if (WEB3YellowCustomerDef.YELLOW_CUSTOMER.equals(l_mainAccountRow.getYellowCustomer()) 
                || WEB3MngLockDef.LOCK.equals(l_mainAccountRow.getMngLockFlag())
                || WEB3MngLockDef.LOCK.equals(l_mainAccountRow.getBranchLock()))
        {
            l_stopInfo.registDate = l_mainAccountRow.getEnableOrderUpdatedTimestamp();
        }
        else
        {
            l_stopInfo.registDate = null;
        }
        
        /*
         * 停止情報.ロック客登録解除SONAR受付状況 = <BR>
         * 　@　@－（ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*1) == null）の場合、null。<BR> 
         * 　@　@－以外、ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*1).get処理区分()。
         */
        WEB3AccInfoLockAccYAccRegisterRelease l_release = 
            WEB3AccInfoLockAccYAccRegisterRelease.getWEB3AccLockAccYAccRecordRelease(l_mainAccount, WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL);
        
        if (l_release == null)
        {
            l_stopInfo.lockAccountSonarState = null;
        }
        else
        {
            l_stopInfo.lockAccountSonarState = l_release.getStatus();
        }
        
        /*
         * 停止情報.Ｙ客登録解除SONAR受付状況 = <BR>
         * 　@　@－（ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*2) == null）の場合、null。 <BR>
         * 　@　@－以外、ロック客Ｙ客登録解除.getロック客Ｙ客登録解除()(*2).get処理区分()。 <BR>
         */
        l_release = 
            WEB3AccInfoLockAccYAccRegisterRelease.getWEB3AccLockAccYAccRecordRelease(l_mainAccount, WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL);

        if (l_release == null)
        {
            l_stopInfo.yAccountSonarState = null;
        }
        else
        {
            l_stopInfo.yAccountSonarState = l_release.getStatus();
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //注文チェック.validate取引可能顧客()にて、エラーが返却された場合は取引停止中と判断する。
        boolean l_blnTradingStop = false;
        OrderValidationResult l_validationResult = l_orderValidator.validateAccountForTrading(l_mainAccount);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            l_blnTradingStop = true;
        }
                 
        //停止情報.状況区分 = 取引停止中※の場合、1：禁止中。以外、0：DEFAULT（禁止中でない）。
        if (l_blnTradingStop)
        {
            l_stopInfo.stateDiv = WEB3AccInfoStateDivDef.PROHIBITING;
        }
        else
        {
            l_stopInfo.stateDiv = WEB3AccInfoStateDivDef.DEFAULT;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_stopInfo;
    }
    
    /**
     * (create携帯番号・勤務先情報)<BR>
     * 顧客オブジェクトより、携帯番号・勤務先情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@携帯番号・勤務先情報オブジェクトを生成する。<BR>
     * ２）　@顧客.getDataSourceObject()にて顧客行オブジェクトを取得する。<BR>
     * ３）　@口座情報マスタオブジェクトを取得する。<BR> 
     * 　@　@　@口座情報マスタ.get口座情報マスタ()にて、口座情報マスタオブジェクトを取得する。<BR> 
     * <BR> 
     * ４）　@以下の通り、プロパティをセットし返却する。<BR>
     * <BR>
     * 　@携帯番号・勤務先情報.携帯番号 = 顧客行.連絡先電話番号（携帯）<BR>
     * 　@携帯番号・勤務先情報.勤務先名称 = 顧客行.勤務先名称<BR>
     * 　@携帯番号・勤務先情報.勤務先郵便番号 = 顧客行.勤務先郵便番号<BR>
     * 　@携帯番号・勤務先情報.勤務先住所 = 顧客行.勤務先住所<BR>
     * 　@携帯番号・勤務先情報.勤務先電話番号 = 顧客行.勤務先電話番号<BR>
     * 　@携帯番号・勤務先情報.役職名 = 顧客行.役職<BR>
     * 　@携帯番号・勤務先情報.連絡先優先順位1位 = 口座情報マスタ.連絡先優先順位1位<BR> 
     * 　@携帯番号・勤務先情報.連絡先優先順位2位 = 口座情報マスタ.連絡先優先順位2位<BR> 
     * 　@携帯番号・勤務先情報.連絡先優先順位3位 = 口座情報マスタ.連絡先優先順位3位<BR>
     * 　@携帯番号・勤務先情報.所属 = 口座情報マスタ.所属<BR>
     * 　@携帯番号・勤務先情報.正式名称１ = 口座情報マスタ.顧客正式名称１ <BR>
     * 　@携帯番号・勤務先情報.正式名称２ = 口座情報マスタ.顧客正式名称２ <BR>
     * 　@携帯番号・勤務先情報.職業 = 口座情報マスタ.職業区分  <BR>
     * 　@携帯番号・勤務先情報.国籍 = 口座情報マスタ.国籍 <BR>
     * 　@携帯番号・勤務先情報.国籍その他 = 口座情報マスタ.国籍その他　@<BR>
     * 　@携帯番号・勤務先情報.代表者名（漢字）姓 = 口座情報マスタ.代表者名（漢字）姓<BR>
     * 　@携帯番号・勤務先情報.代表者名（漢字）名 = 口座情報マスタ.代表者名（漢字）名<BR>
     * 　@携帯番号・勤務先情報.代表者名（カナ）姓 = 口座情報マスタ.代表者名（カナ）姓<BR> 
     * 　@携帯番号・勤務先情報.代表者名（カナ）名 = 口座情報マスタ.代表者名（カナ）名<BR>
     * 　@携帯番号・勤務先情報.代表者権 = 口座情報マスタ.代表者権<BR>
     * 　@携帯番号・勤務先情報.取引責任者名（漢字）姓 = 口座情報マスタ.取引先責任者名（漢字）姓<BR>
     * 　@携帯番号・勤務先情報.取引責任者名（漢字）名 = 口座情報マスタ.取引先責任者名（漢字）名<BR>
     * 　@携帯番号・勤務先情報.取引責任者名（カナ）姓 = 口座情報マスタ.取引先責任者名（カナ）姓<BR>
     * 　@携帯番号・勤務先情報.取引責任者名（カナ）名 = 口座情報マスタ.取引先責任者名（カナ）名<BR>
     * 　@携帯番号・勤務先情報.取引責任者　@所属部署 = 口座情報マスタ.取引責任者所属部署<BR>
     * 　@携帯番号・勤務先情報.取引責任者　@役職 = 口座情報マスタ.取引責任者役職<BR>
     * 　@携帯番号・勤務先情報.取引責任者郵便番号 = 口座情報マスタ.取引先責任者郵便番号<BR>
     * 　@携帯番号・勤務先情報.取引責任者住所１ = 口座情報マスタ.取引先責任者住所１<BR>  
     * 　@携帯番号・勤務先情報.取引責任者住所２ = 口座情報マスタ.取引先責任者住所２<BR>
     * 　@携帯番号・勤務先情報.取引責任者住所３ = 口座情報マスタ.取引先責任者住所３<BR>
     * 　@携帯番号・勤務先情報.取引責任者生年月日　@年号 = 口座情報マスタ.取引先責任者生年月日年号<BR>
     * 　@携帯番号・勤務先情報.取引責任者生年月日 = 口座情報マスタ.取引先責任者生年月日<BR>
     * 　@携帯番号・勤務先情報.取引責任者会社直通番号 = 口座情報マスタ.取引先責任者会社直通番号<BR> 
     * 　@携帯番号・勤務先情報.その他連絡先（携帯、自宅等） = 口座情報マスタ.その他の連絡先（携帯、自宅等）<BR>
	 * 　@携帯番号・勤務先情報.FAX番号 = 口座情報マスタ.FAX番号 <BR>
	 * 　@携帯番号・勤務先情報.年収 = 口座情報マスタ.年収 <BR>
	 * 　@携帯番号・勤務先情報.金融資産額 = 口座情報マスタ.金融資産額 <BR>
	 * 　@携帯番号・勤務先情報.運用予定額 = 口座情報マスタ.運用予定額 <BR>
	 * 　@携帯番号・勤務先情報.投資目的 = 口座情報マスタ.投資目的 <BR>
	 * 　@携帯番号・勤務先情報.投資予定期間 = 口座情報マスタ.投資予定期間 <BR>
	 * 　@携帯番号・勤務先情報.投資経験の有無（１） = 口座情報マスタ.投資経験の有無（１） <BR>
	 * 　@携帯番号・勤務先情報.投資経験の有無（２） = 口座情報マスタ.投資経験の有無（２） <BR>
	 * 　@携帯番号・勤務先情報.投資経験の有無（３） = 口座情報マスタ.投資経験の有無（３） <BR>
	 * 　@携帯番号・勤務先情報.投資経験の有無（４） = 口座情報マスタ.投資経験の有無（４） <BR>
	 * 　@携帯番号・勤務先情報.投資経験の有無（５） = 口座情報マスタ.投資経験の有無（５） <BR>
	 * 　@携帯番号・勤務先情報.投資経験の有無（６） = 口座情報マスタ.投資経験の有無（６） <BR>
	 * 　@携帯番号・勤務先情報.投資経験の有無（７） = 口座情報マスタ.投資経験の有無（７） <BR>
	 * 　@携帯番号・勤務先情報.投資経験の有無（８） = 口座情報マスタ.投資経験の有無（８） <BR>
	 * 　@携帯番号・勤務先情報.投資経験の有無（９） = 口座情報マスタ.投資経験の有無（９） <BR>
	 * 　@携帯番号・勤務先情報.投資経験の有無（１０） = 口座情報マスタ.投資経験の有無（１０） <BR>
	 * 　@携帯番号・勤務先情報.投資経験（１） = 口座情報マスタ.投資経験（１） <BR>
	 * 　@携帯番号・勤務先情報.投資経験（２） = 口座情報マスタ.投資経験（２） <BR>
	 * 　@携帯番号・勤務先情報.投資経験（３） = 口座情報マスタ.投資経験（３） <BR>
	 * 　@携帯番号・勤務先情報.投資経験（４） = 口座情報マスタ.投資経験（４） <BR>
	 * 　@携帯番号・勤務先情報.投資経験（５） = 口座情報マスタ.投資経験（５） <BR>
	 * 　@携帯番号・勤務先情報.投資経験（６） = 口座情報マスタ.投資経験（６） <BR>
	 * 　@携帯番号・勤務先情報.投資経験（７） = 口座情報マスタ.投資経験（７） <BR>
	 * 　@携帯番号・勤務先情報.投資経験（８） = 口座情報マスタ.投資経験（８） <BR>
	 * 　@携帯番号・勤務先情報.投資経験（９） = 口座情報マスタ.投資経験（９） <BR>
	 * 　@携帯番号・勤務先情報.投資経験（１０） = 口座情報マスタ.投資経験（１０） <BR>
	 * 　@携帯番号・勤務先情報.取引の種類（１） = 口座情報マスタ.取引の種類（１） <BR>
	 * 　@携帯番号・勤務先情報.取引の種類（２） = 口座情報マスタ.取引の種類（２） <BR>
	 * 　@携帯番号・勤務先情報.取引の種類（３） = 口座情報マスタ.取引の種類（３） <BR>
	 * 　@携帯番号・勤務先情報.取引の種類（４） = 口座情報マスタ.取引の種類（４） <BR>
	 * 　@携帯番号・勤務先情報.取引の種類（５） = 口座情報マスタ.取引の種類（５） <BR>
	 * 　@携帯番号・勤務先情報.取引の種類（６） = 口座情報マスタ.取引の種類（６） <BR>
	 * 　@携帯番号・勤務先情報.取引の種類（７） = 口座情報マスタ.取引の種類（７） <BR>
	 * 　@携帯番号・勤務先情報.取引の種類（８） = 口座情報マスタ.取引の種類（８） <BR>
	 * 　@携帯番号・勤務先情報.取引の種類（９） = 口座情報マスタ.取引の種類（９） <BR>
	 * 　@携帯番号・勤務先情報.取引の種類（１０） = 口座情報マスタ.取引の種類（１０） <BR>
	 * 　@携帯番号・勤務先情報.口座開設の動機@ = 口座情報マスタ.口座開設の動機@ <BR>
	 * 　@携帯番号・勤務先情報.口座開設の動機@の詳細 = 口座情報マスタ.口座開設の動機@の詳細 <BR>
	 * 　@携帯番号・勤務先情報.現在利用している証券会社 = 口座情報マスタ.現在利用している証券会社 <BR>
	 * 　@携帯番号・勤務先情報.インターネット取引区分 = 口座情報マスタ.インターネット取引区分 <BR>
	 * 　@携帯番号・勤務先情報.紹介支店 = 口座情報マスタ.紹介支店<BR>
     * <BR> 　@
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo
     * @@throws WEB3BaseException
     * @@roseuid 415D1BEA0107
     */
    protected WEB3AccInfoMobileOfficeInfo createMobileOfficeInfo(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMobileOfficeInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AccInfoMobileOfficeInfo l_mobileOfficeInfo = new WEB3AccInfoMobileOfficeInfo();
        
        //口座情報マスタオブジェクトを取得する。 
        //　@口座情報マスタ.get口座情報マスタ()にて、口座情報マスタオブジェクトを取得する。 
        WEB3AccInfoMaster l_master = WEB3AccInfoMaster.getAccInfoMaster(l_mainAccount);
        
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //携帯番号・勤務先情報.携帯番号 = 顧客行.連絡先電話番号（携帯） 
        l_mobileOfficeInfo.mobileTelephone = l_mainAccountRow.getMobile();
        
        //携帯番号・勤務先情報.勤務先名称 = 顧客行.勤務先名称
        l_mobileOfficeInfo.officeName = l_mainAccountRow.getOffice();
        
        //携帯番号・勤務先情報.勤務先郵便番号 = 顧客行.勤務先郵便番号
        l_mobileOfficeInfo.officeZipCode = l_mainAccountRow.getOfficeZipCode();
         
        //携帯番号・勤務先情報.勤務先住所 = 顧客行.勤務先住所
        l_mobileOfficeInfo.officeAdress = l_mainAccountRow.getOfficeAddress();
         
        //携帯番号・勤務先情報.勤務先電話番号 = 顧客行.勤務先電話番号
        l_mobileOfficeInfo.officeTelephone = l_mainAccountRow.getOfficeTelephone();
        
        //携帯番号・勤務先情報.役職名 = 顧客行.役職
        l_mobileOfficeInfo.position = l_mainAccountRow.getPost();

        if (l_master != null)
        {
            AccountInfoMstParams l_params = (AccountInfoMstParams)l_master.getDataSourceObject();
            
            //携帯番号・勤務先情報.連絡先優先順位1位 = 口座情報マスタ.連絡先優先順位1位
            l_mobileOfficeInfo.contactPriority1 = l_params.getContactPriority1();  
            
            //携帯番号・勤務先情報.連絡先優先順位2位 = 口座情報マスタ.連絡先優先順位2位
            l_mobileOfficeInfo.contactPriority2 = l_params.getContactPriority2();         
            
            //携帯番号・勤務先情報.連絡先優先順位3位 = 口座情報マスタ.連絡先優先順位3位
            l_mobileOfficeInfo.contactPriority3 = l_params.getContactPriority3(); 
            
            //携帯番号・勤務先情報.所属 = 口座情報マスタ.所属
            l_mobileOfficeInfo.department = l_params.getDepartment();
            
            //携帯番号・勤務先情報.正式名称１ = 口座情報マスタ.顧客正式名称１  
            l_mobileOfficeInfo.accountRealName1 = l_params.getRealName1();
            
            //携帯番号・勤務先情報.正式名称２ = 口座情報マスタ.顧客正式名称２ 
            l_mobileOfficeInfo.accountRealName2 = l_params.getRealName2();
            
            //携帯番号・勤務先情報.職業 = 口座情報マスタ.職業区分
            l_mobileOfficeInfo.occupationDiv = l_params.getOccupationDiv();
            
            //携帯番号・勤務先情報.国籍 = 口座情報マスタ.国籍  
            l_mobileOfficeInfo.nationality = l_params.getNationality();
            
            //携帯番号・勤務先情報.国籍その他 = 口座情報マスタ.国籍その他 
            l_mobileOfficeInfo.nationalityOther = l_params.getNationalityOther();
            
            //携帯番号・勤務先情報.代表者名（漢字）姓 = 口座情報マスタ.代表者名（漢字）姓
            l_mobileOfficeInfo.representFamilyName = l_params.getRepresentFamilyName();
            
            //携帯番号・勤務先情報.代表者名（漢字）名 = 口座情報マスタ.代表者名（漢字）名
            l_mobileOfficeInfo.representName = l_params.getRepresentGivenName();
            
            //携帯番号・勤務先情報.代表者名（カナ）姓 = 口座情報マスタ.代表者名（カナ）姓
            l_mobileOfficeInfo.representFamilyNameKana = l_params.getRepresentFamilyNameAlt1();
            
            //携帯番号・勤務先情報.代表者名（カナ）名 = 口座情報マスタ.代表者名（カナ）名  
            l_mobileOfficeInfo.representNameKana = l_params.getRepresentGivenNameAlt1();
            
            //携帯番号・勤務先情報.代表者権 = 口座情報マスタ.代表者権
            l_mobileOfficeInfo.representPower = l_params.getRepresentPower();
            
            //携帯番号・勤務先情報.取引責任者名（漢字）姓 = 口座情報マスタ.取引先責任者名（漢字）姓
            l_mobileOfficeInfo.directorFamilyName = l_params.getDirectorFamilyName();
            
            //携帯番号・勤務先情報.取引責任者名（漢字）名 = 口座情報マスタ.取引先責任者名（漢字）名
            l_mobileOfficeInfo.directorName = l_params.getDirectorGivenName();
            
            //携帯番号・勤務先情報.取引責任者名（カナ）姓 = 口座情報マスタ.取引先責任者名（カナ）姓
            l_mobileOfficeInfo.directorFamilyNameKana = l_params.getDirectorFamilyNameAlt1();
            
            //携帯番号・勤務先情報.取引責任者名（カナ）名 = 口座情報マスタ.取引先責任者名（カナ）名
            l_mobileOfficeInfo.directorNameKana = l_params.getDirectorGivenNameAlt1();
            
            //携帯番号・勤務先情報.取引責任者　@所属部署 = 口座情報マスタ.取引責任者所属部署
            l_mobileOfficeInfo.directorDepartment = l_params.getDirectorDepartment();
            
            //携帯番号・勤務先情報.取引責任者　@役職 = 口座情報マスタ.取引責任者役職
            l_mobileOfficeInfo.directorPosition = l_params.getDirectorPost();
            
            //携帯番号・勤務先情報.取引責任者郵便番号 = 口座情報マスタ.取引先責任者郵便番号
            l_mobileOfficeInfo.directorZipCode = l_params.getDirectorZipCode();
            
            //携帯番号・勤務先情報.取引責任者住所１ = 口座情報マスタ.取引先責任者住所１
            l_mobileOfficeInfo.directorAddress1 = l_params.getDirectorAddress1();

            //携帯番号・勤務先情報.取引責任者住所２ = 口座情報マスタ.取引先責任者住所２
            l_mobileOfficeInfo.directorAddress2 = l_params.getDirectorAddress2();
            
            //携帯番号・勤務先情報.取引責任者住所３ = 口座情報マスタ.取引先責任者住所３
            l_mobileOfficeInfo.directorAddress3 = l_params.getDirectorAddress3();
            
            //携帯番号・勤務先情報.取引責任者生年月日　@年号 = 口座情報マスタ.取引先責任者生年月日年号
            l_mobileOfficeInfo.directorEraBorn = l_params.getDirectorEraBorn();
            
            //携帯番号・勤務先情報.取引責任者生年月日 = 口座情報マスタ.取引先責任者生年月日
            l_mobileOfficeInfo.directorBornDate = l_params.getDirectorBornDate();
            
            //携帯番号・勤務先情報.取引責任者会社直通番号 = 口座情報マスタ.取引先責任者会社直通番号
            l_mobileOfficeInfo.directorCorpDirect = l_params.getDirectorCorpTelephone();
            
            //携帯番号・勤務先情報.その他連絡先（携帯、自宅等） = 口座情報マスタ.その他の連絡先（携帯、自宅等）  
            l_mobileOfficeInfo.directorOtherContact = l_params.getOtherContact();
            
            //携帯番号・勤務先情報.FAX番号 = 口座情報マスタ.FAX番号 
            l_mobileOfficeInfo.faxTelephone = l_params.getFax();
            
            //携帯番号・勤務先情報.年収 = 口座情報マスタ.年収 
            l_mobileOfficeInfo.annualIncomeDiv = l_params.getAnnualIncomeDiv();
            
            //携帯番号・勤務先情報.金融資産額 = 口座情報マスタ.金融資産額  
            l_mobileOfficeInfo.assetValueDiv = l_params.getAssetValueDiv();
            
            //携帯番号・勤務先情報.運用予定額 = 口座情報マスタ.運用予定額  
            l_mobileOfficeInfo.fundBundgetAmountDiv = l_params.getFundBudgetAmountDiv();
            
            //携帯番号・勤務先情報.投資目的 = 口座情報マスタ.投資目的   
            l_mobileOfficeInfo.investPurposeDiv = l_params.getInvestPurposeDiv();
            
            //携帯番号・勤務先情報.投資予定期間 = 口座情報マスタ.投資予定期間  
            l_mobileOfficeInfo.investPlanPeriodDiv = l_params.getInvestPlanPeriodDiv();
            
            //携帯番号・勤務先情報.投資経験の有無（１） = 口座情報マスタ.投資経験の有無（１）  
            if (!l_params.getExperienceFlag1IsNull())
            {
                l_mobileOfficeInfo.experienceFlag1 = l_params.getExperienceFlag1() + "";
            }

            //携帯番号・勤務先情報.投資経験の有無（２） = 口座情報マスタ.投資経験の有無（２） 
            if (!l_params.getExperienceFlag2IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag2 = l_params.getExperienceFlag2() + "";
            }
            //携帯番号・勤務先情報.投資経験の有無（３） = 口座情報マスタ.投資経験の有無（３）
            if (!l_params.getExperienceFlag3IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag3 = l_params.getExperienceFlag3() + "";
            }
            //携帯番号・勤務先情報.投資経験の有無（４） = 口座情報マスタ.投資経験の有無（４）
            if (!l_params.getExperienceFlag4IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag4 = l_params.getExperienceFlag4() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（５） = 口座情報マスタ.投資経験の有無（５）   
            if (!l_params.getExperienceFlag5IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag5 = l_params.getExperienceFlag5() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（６） = 口座情報マスタ.投資経験の有無（６）   
            if (!l_params.getExperienceFlag6IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag6 = l_params.getExperienceFlag6() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（７） = 口座情報マスタ.投資経験の有無（７） 
            if (!l_params.getExperienceFlag7IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag7 = l_params.getExperienceFlag7() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（８） = 口座情報マスタ.投資経験の有無（８） 
            if (!l_params.getExperienceFlag8IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag8 = l_params.getExperienceFlag8() + "";
            }
            //携帯番号・勤務先情報.投資経験の有無（９） = 口座情報マスタ.投資経験の有無（９） 
            if (!l_params.getExperienceFlag9IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag9 = l_params.getExperienceFlag9() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（１０） = 口座情報マスタ.投資経験の有無（１０）  
            if (!l_params.getExperienceFlag10IsNull())
            {
            	l_mobileOfficeInfo.experienceFlag10 = l_params.getExperienceFlag10() + "";
            }
            
            //携帯番号・勤務先情報.投資経験（１） = 口座情報マスタ.投資経験（１） 
            l_mobileOfficeInfo.experienceDiv1 = l_params.getExperienceDiv1();
            
            //携帯番号・勤務先情報.投資経験（２） = 口座情報マスタ.投資経験（２）   
            l_mobileOfficeInfo.experienceDiv2 = l_params.getExperienceDiv2();
            
            //携帯番号・勤務先情報.投資経験（３） = 口座情報マスタ.投資経験（３）   
            l_mobileOfficeInfo.experienceDiv3 = l_params.getExperienceDiv3();
            
            //携帯番号・勤務先情報.投資経験（４） = 口座情報マスタ.投資経験（４）    
            l_mobileOfficeInfo.experienceDiv4 = l_params.getExperienceDiv4();
            
            //携帯番号・勤務先情報.投資経験（５） = 口座情報マスタ.投資経験（５）    
            l_mobileOfficeInfo.experienceDiv5 = l_params.getExperienceDiv5();
            
            //携帯番号・勤務先情報.投資経験（６） = 口座情報マスタ.投資経験（６）    
            l_mobileOfficeInfo.experienceDiv6 = l_params.getExperienceDiv6();
            
            //携帯番号・勤務先情報.投資経験（７） = 口座情報マスタ.投資経験（７）    
            l_mobileOfficeInfo.experienceDiv7 = l_params.getExperienceDiv7();
            
            //携帯番号・勤務先情報.投資経験（８） = 口座情報マスタ.投資経験（８） 
            l_mobileOfficeInfo.experienceDiv8 = l_params.getExperienceDiv8();
            
            //携帯番号・勤務先情報.投資経験（９） = 口座情報マスタ.投資経験（９）    
            l_mobileOfficeInfo.experienceDiv9 = l_params.getExperienceDiv9();
            
            //携帯番号・勤務先情報.投資経験（１０） = 口座情報マスタ.投資経験（１０）  
            l_mobileOfficeInfo.experienceDiv10 = l_params.getExperienceDiv10();
            
            //携帯番号・勤務先情報.取引の種類（１） = 口座情報マスタ.取引の種類（１） 
            if (!l_params.getInterestFlag1IsNull())
            {
            	l_mobileOfficeInfo.interest1 = l_params.getInterestFlag1() + "";
            }

            //携帯番号・勤務先情報.取引の種類（２） = 口座情報マスタ.取引の種類（２） 
            if (!l_params.getInterestFlag2IsNull())
            {
            	l_mobileOfficeInfo.interest2 = l_params.getInterestFlag2() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（３） = 口座情報マスタ.取引の種類（３） 
            if (!l_params.getInterestFlag3IsNull())
            {
            	l_mobileOfficeInfo.interest3 = l_params.getInterestFlag3() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（４） = 口座情報マスタ.取引の種類（４） 
            if (!l_params.getInterestFlag4IsNull())
            {
            	l_mobileOfficeInfo.interest4 = l_params.getInterestFlag4() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（５） = 口座情報マスタ.取引の種類（５）
            if (!l_params.getInterestFlag5IsNull())
            {
            	l_mobileOfficeInfo.interest5 = l_params.getInterestFlag5() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（６） = 口座情報マスタ.取引の種類（６） 
            if (!l_params.getInterestFlag6IsNull())
            {
            	l_mobileOfficeInfo.interest6 = l_params.getInterestFlag6() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（７） = 口座情報マスタ.取引の種類（７） 
            if (!l_params.getInterestFlag7IsNull())
            {
            	l_mobileOfficeInfo.interest7 = l_params.getInterestFlag7() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（８） = 口座情報マスタ.取引の種類（８）  
            if (!l_params.getInterestFlag8IsNull())
            {
            	l_mobileOfficeInfo.interest8 = l_params.getInterestFlag8() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（９） = 口座情報マスタ.取引の種類（９）   
            if (!l_params.getInterestFlag9IsNull())
            {
            	l_mobileOfficeInfo.interest9 = l_params.getInterestFlag9() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（１０） = 口座情報マスタ.取引の種類（１０）  
            if (!l_params.getInterestFlag10IsNull())
            {
            	l_mobileOfficeInfo.interest10 = l_params.getInterestFlag10() + "";
            }
            
            //携帯番号・勤務先情報.口座開設の動機@ = 口座情報マスタ.口座開設の動機@           
            l_mobileOfficeInfo.appliMotivatDiv = l_params.getAppliMotivatDiv();
            
            //携帯番号・勤務先情報.口座開設の動機@の詳細 = 口座情報マスタ.口座開設の動機@の詳細           
            l_mobileOfficeInfo.appliMotivatDetail = l_params.getAppliMotivatDivDetail();

            //携帯番号・勤務先情報.現在利用している証券会社 = 口座情報マスタ.現在利用している証券会社           
            l_mobileOfficeInfo.useInstitutionDiv = l_params.getUseInstitutionDiv();
            
            //携帯番号・勤務先情報.インターネット取引区分 = 口座情報マスタ.インターネット取引区分           
            l_mobileOfficeInfo.internetTradeDiv = l_params.getInternetTradeDiv();
            
            //携帯番号・勤務先情報.紹介支店 = 口座情報マスタ.紹介支店            
            l_mobileOfficeInfo.introduceBranch = l_params.getIntroduceBranchCode();
        }

        log.exiting(STR_METHOD_NAME);
        return l_mobileOfficeInfo;     
    }
    
    /**
     * (create専用振込先口座情報)<BR>
     * 顧客オブジェクトより、専用振込先口座に関して、<BR>
     * お客様情報口座情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@専用振込先口座テーブルを以下の条件で読む。<BR>
     * 　@行が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@専用振込先口座テーブル.口座ID = 顧客getAccountId()<BR>
     * 　@<BR>
     * ２）　@お客様情報口座情報オブジェクトを生成する。<BR>
     * ３）　@以下の通り、プロパティをセットし返却する。<BR>
     * <BR>
     *   お客様情報口座情報.金融機@関コード = 専用振込先口座.銀行コード<BR>
     * 　@お客様情報口座情報.金融機@関名称 = 専用振込先口座.銀行名<BR>
     * 　@お客様情報口座情報.支店コード = 専用振込先口座.支店コード<BR>
     * 　@お客様情報口座情報.支店名 = 専用振込先口座.支店名<BR>
     * 　@お客様情報口座情報.口座種類名 = 専用振込先口座.口座種類名<BR>
     * 　@お客様情報口座情報.口座番号 = 専用振込先口座.口座番号<BR>
     * 　@お客様情報口座情報.口座名義人 = 専用振込先口座.口座名義人<BR>
     * @@param l_mainAccount - 顧客オブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountInfo
     * @@roseuid 415D1C12003C
     */
    protected WEB3AccInfoAccountInfo createExclusiveTransferAccountInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException  
    {
        
        final String STR_METHOD_NAME = " createExclusiveTransferAccountInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //専用振込先口座を以下の条件で読む。
        ExclusiveUseAccountRow l_exclusiveUseAccountRow = null;
        try
        {
            l_exclusiveUseAccountRow = ExclusiveUseAccountDao.findRowByPk(l_mainAccount.getAccountId());
        }
        catch (DataFindException l_ex)
        {
            //行が取得できなかった場合は、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
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
        
        if (l_exclusiveUseAccountRow == null)
        {
            //行が取得できなかった場合は、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3AccInfoAccountInfo l_accountInfo = new WEB3AccInfoAccountInfo();
        
        //お客様情報口座情報.金融機@関コード = 専用振込先口座.銀行コード
        l_accountInfo.financialInstitutionCode = l_exclusiveUseAccountRow.getFinInstitutionCode();
        
        //お客様情報口座情報.金融機@関名称 = 専用振込先口座.銀行名
        l_accountInfo.financialInstitutionName = l_exclusiveUseAccountRow.getFinInstitutionName();
        
        //お客様情報口座情報.支店コード = 専用振込先口座.支店コード
        l_accountInfo.financialBranchCode = l_exclusiveUseAccountRow.getFinBranchCode();
         
        //お客様情報口座情報.支店名 = 専用振込先口座.支店名
        l_accountInfo.financialBranchName = l_exclusiveUseAccountRow.getFinBranchName();
         
        //お客様情報口座情報.口座種類名 = 専用振込先口座.口座種類名
        l_accountInfo.financialAccountTypeName = l_exclusiveUseAccountRow.getFinAccountTypeName();
         
        //お客様情報口座情報.口座番号 = 専用振込先口座.口座番号
        l_accountInfo.financialAccountCode = l_exclusiveUseAccountRow.getFinAccountNo();
         
        //お客様情報口座情報.口座名義人 = 専用振込先口座.口座名義人 
        l_accountInfo.financialAccountName = l_exclusiveUseAccountRow.getFinAccountName();
        
        log.exiting(STR_METHOD_NAME);
        return l_accountInfo; 
    }
    
    /**
     * (create振込先指定口座情報)<BR>
     * 顧客オブジェクトより、振込先指定口座に関して、<BR>
     * お客様情報口座情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@振込先金融機@関テーブルを以下の条件で読む。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@振込先金融機@関テーブル.証券会社コード = <BR>
     * 顧客.getInstitution().getInstitutionCode()<BR>
     * 　@振込先金融機@関テーブル.部店コード = <BR>
     * 顧客.getBranch().getBranchCode()<BR>
     * 　@振込先金融機@関テーブル.顧客コード = 顧客.getAccountCode()<BR>
     * 　@振込先金融機@関テーブル.指定区分 = 'A' <BR>
     * <BR>
     * ２）　@お客様情報口座情報オブジェクトを生成する。<BR>
     * ３）以下のプロパティをセットする。<BR>
     * 　@　@３－１）顧客.is円貨振込先（銀行口座）登録区分() == false の場合<BR>
     * 　@　@　@３－１－１）お客様情報口座情報.振込先登録区分 = 0<BR>
     * 　@　@３－２）顧客.is円貨振込先（銀行口座）登録区分() == true の場合<BR>
     * 　@　@　@３－２－１）お客様情報口座情報.振込先登録区分 = 1<BR>
     * <BR>
     * ４）顧客.is円貨振込先（銀行口座）登録区分() == true の場合<BR>
     * 　@４－１）振込先金融機@関テーブルで該当のデータが存在しなかった場合<BR>
     *   　@　@　@　@又は取得した振込先金融機@関データが<BR>
     * 　@　@　@　@　@（振替区分==1：銀行振込）且つ（銀行名==null）の場合<BR> 
     * 　@　@４－１－１）お客様情報口座情報.振込先登録区分 = 9<BR>
     *<BR> 
     * 　@４－２）上記以外の場合<BR>
     * 　@　@４－２－１）以下の通り、プロパティをセットし返却する。<BR> 
     * 　@　@　@お客様情報口座情報.金融機@関名称 = 振込先金融機@関.銀行名<BR> 
     * 　@　@　@お客様情報口座情報.支店コード = 振込先金融機@関.支店コード<BR> 
     * 　@　@　@お客様情報口座情報.支店名 = 振込先金融機@関.支店名<BR> 
     * 　@　@　@お客様情報口座情報.口座種類名 =<BR> 
     * 　@　@　@　@－（振込先金融機@関.預金区分 == 1：普通預金）の場合、”普通預金”<BR> 
     * 　@　@　@　@－（振込先金融機@関.預金区分 == 2：当座預金）の場合、”当座預金”<BR> 
     * 　@　@　@　@－（振込先金融機@関.預金区分 == 3：その他）の場合、”その他”<BR> 
     * 　@　@　@　@－（振込先金融機@関.預金区分 == 4：貯蓄預金）の場合、”貯蓄預金” <BR>
     * 　@　@　@お客様情報口座情報.口座番号 = 振込先金融機@関.口座番号<BR> 
     * 　@　@　@お客様情報口座情報.口座名義人 = 振込先金融機@関.口座名義人 <BR>
     * 　@　@　@お客様情報口座情報.振替区分 = 振込先金融機@関.振替区分<BR>
     * 　@　@　@お客様情報口座情報.振込先区分 = 振込先金融機@関.振込先区分<BR>
     * 　@　@　@お客様情報口座情報.取扱区分 = 振込先金融機@関.取扱区分<BR>
     *<BR> 
     * @@param l_mainAccount - 顧客オブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountInfo
     * @@roseuid 415D1C550174
     */
    protected WEB3AccInfoAccountInfo createTransferAccountInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createTransferAccountInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //振込先金融機@関テーブルを以下の条件で読む。     
        TransferedFinInstitutionRow l_finInstitutionRow = null;

        try
        {
            l_finInstitutionRow =
                TransferedFinInstitutionDao.findRowByPk(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    l_mainAccount.getAccountCode(),
                    "A");
        } 
        catch (DataFindException e)
        {
            log.debug("振込先金融機@関テーブル該当データなし。");
        } 
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        WEB3AccInfoAccountInfo l_accountInfo = new WEB3AccInfoAccountInfo();

        //顧客.is円貨振込先（銀行口座）登録区分() == false の場合
        if (!l_mainAccount.isJapaneseCurrencyBankAccountRegi())
        {
            l_accountInfo.bankAccountRegi = WEB3BankAccountRegiDef.NOT_REGISTER;
        }
        else
        {
            //顧客.is円貨振込先（銀行口座）登録区分() == trueの場合
            l_accountInfo.bankAccountRegi = WEB3BankAccountRegiDef.ALREADY_REGISTER;
            
            if (l_finInstitutionRow == null
                || (WEB3TransferDivDef.BANK_TRANSFER.equals(l_finInstitutionRow.getTransferDiv())
                    && (l_finInstitutionRow.getFinInstitutionName() == null)))
            {
                //  お客様情報口座情報.振込先登録区分 = 9(該当データなし)
                l_accountInfo.bankAccountRegi = WEB3AccInfoBankAccountRegiDef.NO_DATA;
            } 
            else
            {
                //お客様情報口座情報.金融機@関名称 = 振込先金融機@関.銀行名
                l_accountInfo.financialInstitutionName = l_finInstitutionRow.getFinInstitutionName();

                //お客様情報口座情報.支店コード = 振込先金融機@関.支店コード
                l_accountInfo.financialBranchCode = l_finInstitutionRow.getFinBranchCode();

                //お客様情報口座情報.支店名 = 振込先金融機@関.支店名
                l_accountInfo.financialBranchName = l_finInstitutionRow.getFinBranchName();

                //お客様情報口座情報.口座種類名
                l_accountInfo.setFinancialAccountTypeName(l_finInstitutionRow.getFinSaveDiv());

                //お客様情報口座情報.口座番号 = 専用振込先口座.口座番号
                l_accountInfo.financialAccountCode = l_finInstitutionRow.getFinAccountNo();

                //お客様情報口座情報.口座名義人 = 専用振込先口座.口座名義人 
                l_accountInfo.financialAccountName = l_finInstitutionRow.getFinAccountName();

                //お客様情報口座情報.振替区分 = 振込先金融機@関.振替区分
                l_accountInfo.transferDiv = l_finInstitutionRow.getTransferDiv();
                
                //2007.06.21 口座種類区分,金融機@関コードをセットするように修正　@SCS佐藤-------------start
                l_accountInfo.financialAccountType = l_finInstitutionRow.getFinSaveDiv();
                
                l_accountInfo.financialInstitutionCode = l_finInstitutionRow.getFinInstitutionCode();
                //----------------end

                //お客様情報口座情報.振込先区分 = 振込先金融機@関.振込先区分
                l_accountInfo.transferAccountDiv = l_finInstitutionRow.getTransCommission();

                //お客様情報口座情報.取扱区分 = 振込先金融機@関.取扱区分
                l_accountInfo.tradeHandleDiv = l_finInstitutionRow.getTransDealDiv();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_accountInfo;
    }
    
    /**
     * (create電子鳩情報)<BR>
     * 顧客オブジェクトより、電子鳩情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@電子鳩情報オブジェクトを生成する。<BR>
     * ２）　@顧客.getDataSourceObject()にて顧客行オブジェクトを取得する。<BR>
     * ３）　@以下の通り、プロパティをセットし、返却する。<BR>
     * <BR>
     * 　@電子鳩情報.取引報告書交付状態区分 = <BR>
     * 顧客行.取引報告書交付区分
     * 　@電子鳩情報.取引残高報告書交付状態区分 = <BR>
     * 顧客行.取引残高報告書交付区分
     * 　@電子鳩情報.取引残高報告書作成周期区分 = <BR>
     * 顧客行.取引残高報告書作成周期区分
     * 　@電子鳩情報.取引残高報告書預り証作成状態区分 = <BR>
     * 　@　@－（顧客行.取引残高報告書預り証作成フラグ == <BR>
     * BooleanEnum.TRUE）の場合、1：作成<BR>
     * 　@　@－（顧客行.取引残高報告書預り証作成フラグ == <BR>
     * BooleanEnum.FALSE）の場合、0：不作成<BR>
     * 　@電子鳩情報.取引残高報告書計算書作成状態区分 = <BR>
     * 　@　@－（顧客行.取引残高報告書計算書作成フラグ == <BR>
     * BooleanEnum.TRUE）の場合、1：作成<BR>
     * 　@　@－（顧客行.取引残高報告書計算書作成フラグ == <BR>
     * BooleanEnum.FALSE）の場合、0：不作成<BR>
     * @@param l_mainAccount - 顧客オブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoBatoInfo
     * @@roseuid 415D1C8700B9
     */
    protected WEB3AccInfoBatoInfo createBatoInfo(WEB3GentradeMainAccount l_mainAccount) 
    {
        final String STR_METHOD_NAME = " createBatoInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoBatoInfo l_batoInfo = new WEB3AccInfoBatoInfo();
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //電子鳩情報.取引報告書交付状態区分 = 顧客行.取引報告書交付区分
        l_batoInfo.tradingReportStateDiv = l_mainAccountRow.getTradingReportDiv();
        
        //電子鳩情報.取引残高報告書交付状態区分 = 顧客行.取引残高報告書交付区分
        l_batoInfo.positionReportStateDiv = l_mainAccountRow.getPositionReportDiv();
         
        //電子鳩情報.取引残高報告書作成周期区分 = 顧客行.取引残高報告書作成周期区分
        l_batoInfo.positionReportCycleDiv = l_mainAccountRow.getPositionReportCycleDiv();
         
        //電子鳩情報.取引残高報告書預り証作成状態区分 = 
        //  －（顧客行.取引残高報告書預り証作成フラグ == BooleanEnum.TRUE）の場合、1：作成 
        //  －（顧客行.取引残高報告書預り証作成フラグ == BooleanEnum.FALSE）の場合、0：不作成
        if (BooleanEnum.TRUE.equals(l_mainAccountRow.getCertificateDepositFlag()))
        {
            l_batoInfo.certificateDepositStateDiv = WEB3CreateStateDivDef.CREATE;
        }
        else
        {
            l_batoInfo.certificateDepositStateDiv = WEB3CreateStateDivDef.NOT_CREATE;
        }
         
        //電子鳩情報.取引残高報告書計算書作成状態区分 = . 
        //  －（顧客行.取引残高報告書計算書作成フラグ == BooleanEnum.TRUE）の場合、1：作成 
        //  －（顧客行.取引残高報告書計算書作成フラグ == BooleanEnum.FALSE）の場合、0：不作成 
        if (BooleanEnum.TRUE.equals(l_mainAccountRow.getAccountStatementFlag()))
        {
            l_batoInfo.accountStatementStateDiv = WEB3CreateStateDivDef.CREATE;
        }
        else
        {
            l_batoInfo.accountStatementStateDiv = WEB3CreateStateDivDef.NOT_CREATE;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_batoInfo; 
    }
    
    /**
     * 口座区分を取得する。
     * －（引数の税区分 == TaxTypeEnum.DEFAULT，またはnull）の場合、null。
　@　@ * －（引数の税区分 == TaxTypeEnum.一般）の場合、1：未開設（一般）
　@　@ * －（引数の税区分 == TaxTypeEnum.特定）の場合、2：開設済み源泉徴収なし（特定かつ源泉徴収なし）
　@　@ * －（引数の税区分 == TaxTypeEnum.特定口座かつ源泉徴収）の場合、3：開設済み源泉徴収あり（特定かつ源泉徴収）
     * 
     * @@param l_taxType 税区分
     * @@return String
     */
    private String getTaxType(TaxTypeEnum l_taxType)
    {
        String l_strTaxType = null;
        
        if (TaxTypeEnum.UNDEFINED.equals(l_taxType))
        {
            //－（引数の税区分 == TaxTypeEnum.DEFAULT，またはnull）の場合、null。
            l_strTaxType = null;
        }
        else if (TaxTypeEnum.NORMAL.equals(l_taxType))
        {
            //－（引数の税区分 == TaxTypeEnum.一般）の場合、1：未開設（一般）
            l_strTaxType = WEB3AccInfoTaxTypeDef.DEFAULT;
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_taxType))
        {
            //－（引数の税区分 == TaxTypeEnum.特定）の場合、2：開設済み源泉徴収なし（特定かつ源泉徴収なし）
            l_strTaxType = WEB3AccInfoTaxTypeDef.OPEN_WITHOUT_SPECIAL_WITHHOLD;
        }
        else if (TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
        {
            //－（引数の税区分 == TaxTypeEnum.特定口座かつ源泉徴収）の場合、3：開設済み源泉徴収あり（特定かつ源泉徴収）
            l_strTaxType = WEB3AccInfoTaxTypeDef.OPEN_SPECIAL_WITHHOLD;
        }
        
        return l_strTaxType;
    } 
    
    /**
     * (create為替保証金口座情報)<BR>
     * 指定顧客のＦＸ顧客，ＦＸ口座番号データより、<BR>
     * 　@為替保証金口座情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@ArrayListオブジェクトの生成。<BR>
     * 　@　@　@為替保証金口座情報オブジェクトを格納する為のオブジェクト生成。<BR>
     * <BR>
     * <BR>
     * ２）　@FXシステムコードの取得<BR>
     * 　@　@　@会社別FXシステム条件テーブルを以下の条件で検索する。<BR>
     * 　@　@　@※該当行がなかった場合は、
     *       nullを返却            
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@会社別FXシステム条件テーブル.証券会社コード =<BR>
     * 　@　@　@　@顧客.getInstitution().getInstitutionCode() And<BR>
     * 　@　@　@会社別FXシステム条件テーブル.部店コード =<BR>
     * 　@　@　@　@顧客.getBranch().getBranchCode() And<BR>
     * 　@　@　@FXシステム区分 = null<BR>
     * <BR>
     * ３）　@２）の処理で取得した会社別FXシステム条件テーブルの要素毎に、以下の処理を行う<BR>
     * <BR>
     * 　@＜２）でレコードが取得できた場合＞<BR>
     * 　@　@　@　@　@３－１）ＦＸログインＩＤ取得<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ顧客テーブルを以下の条件で検索する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@※該当行がなかった場合は、
     *　@　@　@　@　@　@　@       何もせずに次の要素へ                

     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@[条件]<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ顧客テーブル.証券会社コード =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@顧客.getInstitution().getInstitutionCode() And<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ顧客テーブル.部店コード =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@顧客.getBranch().getBranchCode() And<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ顧客テーブル.ＦＸシステムコード　@=<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@会社別FXシステム条件行.FXシステムコード And<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ顧客テーブル.顧客コード = 顧客.getAccountCode()<BR>
     * <BR>
     * 　@　@　@　@　@３－２）FX口座番号取得<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ口座番号テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@[条件]<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ口座番号テーブル.証券会社コード =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@顧客.getInstitution().getInstitutionCode() And<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ口座番号テーブル.部店コード =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@顧客.getBranch().getBranchCode() And<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ口座番号テーブル.ＦＸシステムコード　@=<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@会社別FXシステム条件行.FXシステムコード And<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ口座番号テーブル.ＦＸコース区分<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@in（ＦＸコース区分.１万通貨コース，<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@ＦＸコース区分.１０万通貨コース） And<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ口座番号テーブル.顧客コード = 顧客.getAccountCode()<BR>
     * <BR>
     * 　@　@　@　@　@３－３）　@為替保証金口座情報オブジェクトを生成し以下の通り、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@プロパティをセットする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@為替保証金口座情報.ＦＸログインＩＤ：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@ＦＸ顧客行.ＦＸログインＩＤ<BR>
     * 　@　@　@　@　@　@　@　@　@　@為替保証金口座情報.ＦＸ口座番号（１万通貨コース）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@ＦＸ口座番号行.ＦＸ口座番号<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@※（ＦＸコース区分 == １万通貨コース）の行<BR>
     * 　@　@　@　@　@　@　@　@　@　@為替保証金口座情報.ＦＸ口座番号（１０万通貨コース）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@ＦＸ口座番号行.ＦＸ口座番号<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@※（ＦＸコース区分 == １０万通貨コース）の行<BR>
     * 　@　@　@　@　@　@　@　@　@　@為替保証金口座情報.FXシステムコード：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@会社別FXシステム条件行.FXシステムコード<BR>
     * <BR>
     * 　@　@　@　@　@３－４）　@１）で生成したListオブジェクト<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@に為替保証金口座情報オブジェクトを格納する。<BR>
     * <BR>
     * ４）ArrayList.toArray()の戻り値を返却<BR>
     * <BR>
     * @@param l_account - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return WEB3FXAccInformationUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoFxAccountInfo[] createFXAccInformationUnit(WEB3GentradeMainAccount l_account)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createFXAccInformationUnit(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ArrayListオブジェクトの生成。
        //為替保証金口座情報オブジェクトを格納する為のオブジェクト生成。
        ArrayList l_lisFxAccountInfos = new ArrayList();
        WEB3AccInfoFxAccountInfo[] l_fxAccountInfos = null;

        //２FXシステムコードの取得
        //会社別FXシステム条件テーブルを以下の条件で検索する。
        //　@[条件]
        //　@会社別FXシステム条件テーブル.証券会社コード = 顧客.getInstitution().getInstitutionCode() And
        //　@会社別FXシステム条件テーブル.部店コード = 顧客.getBranch().getBranchCode() And
        //　@FXシステム区分 = null
        List l_lisCompFxComditions = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append(" institution_code = ? ");
            l_sbQueryString.append(" and branch_code = ? ");
            l_sbQueryString.append(" and fx_system_div is null ");

            Object[] l_queryDataContainers = new Object[]{
                l_account.getInstitution().getInstitutionCode(),
                l_account.getBranch().getBranchCode()};

            l_lisCompFxComditions = l_queryProcessor.doFindAllQuery(
        		CompFxConditionRow.TYPE,
                l_sbQueryString.toString(),
                l_queryDataContainers);

            //※該当行がなかった場合は、
            //nullを返却            
            
            if (l_lisCompFxComditions.isEmpty())
            {
            	return null;
            }
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

        //２）の処理で取得した会社別FXシステム条件テーブルの要素毎に、以下の処理を行う
        CompFxConditionRow l_compFxConditionRow = null;
        Iterator l_itCompFxCon = l_lisCompFxComditions.iterator();

        while (l_itCompFxCon.hasNext())
        {
        	l_compFxConditionRow = (CompFxConditionRow)l_itCompFxCon.next();

        	//ＦＸログインＩＤ取得
            FxAccountRow l_row = null;
            String l_strFxSystemCode = l_compFxConditionRow.getFxSystemCode();
            try
            {
                l_row = FxAccountDao.findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCode(
                            l_account.getInstitution().getInstitutionCode(),
                            l_account.getBranch().getBranchCode(),
                            l_strFxSystemCode,
                            l_account.getAccountCode());
                if (l_row == null)
                {
                   //※該当行がなかった場合は、
                   //何もせずに次の要素へ                
                    continue;
                }                    
            }
            catch (DataNetworkException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //３－２）FX口座番号取得
            //ＦＸ口座番号テーブルを以下の条件で検索する。
            //[条件]
            //ＦＸ口座番号テーブル.証券会社コード = 顧客.getInstitution().getInstitutionCode() And
            //ＦＸ口座番号テーブル.部店コード = 顧客.getBranch().getBranchCode() And
            //ＦＸ口座番号テーブル.ＦＸシステムコード　@= 会社別FXシステム条件行.FXシステムコード And
            //ＦＸ口座番号テーブル.ＦＸコース区分
            //  in （ＦＸコース区分.１万通貨コース，ＦＸコース区分.１０万通貨コース） And 
            //ＦＸ口座番号テーブル.顧客コード = 顧客.getAccountCode()
            //（ＦＸコース区分 == １万通貨コース）の行
            FxAccountCodeRow l_fxAccountCodeRowOneCose = null;
            //（ＦＸコース区分 == １０万通貨コース）の行
            FxAccountCodeRow l_fxAccountCodeRowTenCose = null;
            
            try
            {
                l_fxAccountCodeRowOneCose = (FxAccountCodeRow) FxAccountCodeDao.findRowByPk(
                        l_account.getInstitution().getInstitutionCode(),
                        l_account.getBranch().getBranchCode(),
                        l_strFxSystemCode,
                        l_account.getAccountCode(),
                        WEB3GftTransStatusCourseDivDef.ONE_COSE);

                log.debug("（ＦＸコース区分 == １万通貨コース）の行 = " + l_fxAccountCodeRowOneCose);

            }
            catch (DataNetworkException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            catch (DataFindException l_ex)
            {
                log.debug("予期しないシステムエラーが発生しました。" + l_ex);

            } 
            catch (DataQueryException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            try
            {
                l_fxAccountCodeRowTenCose = (FxAccountCodeRow) FxAccountCodeDao.findRowByPk(
                        l_account.getInstitution().getInstitutionCode(),
                        l_account.getBranch().getBranchCode(),
                        l_strFxSystemCode,
                        l_account.getAccountCode(),
                        WEB3GftTransStatusCourseDivDef.TEN_COSE);

                log.debug("（ＦＸコース区分 == １０万通貨コース）の行 = " + l_fxAccountCodeRowTenCose);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            catch (DataFindException l_ex)
            {
                log.debug("予期しないシステムエラーが発生しました。" + l_ex);

            } 
            catch (DataQueryException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //３－３）　@為替保証金口座情報オブジェクトを生成し以下の通り、プロパティをセットする。 
            //為替保証金口座情報.ＦＸログインＩＤ： ＦＸ顧客行.ＦＸログインＩＤ
            //為替保証金口座情報.ＦＸ口座番号（１万通貨コース）： ＦＸ口座番号行.ＦＸ口座番号　@※（ＦＸコース区分 == １万通貨コース）の行
            //為替保証金口座情報.ＦＸ口座番号（１０万通貨コース）： ＦＸ口座番号行.ＦＸ口座番号　@※（ＦＸコース区分 == １０万通貨コース）の行 
            //為替保証金口座情報.FXシステムコード：会社別FXシステム条件行.FXシステムコード
            WEB3AccInfoFxAccountInfo l_fxAccInformationUnit = new WEB3AccInfoFxAccountInfo();
            l_fxAccInformationUnit.fxLoginId = "" + l_row.getFxLoginId();

            if (l_fxAccountCodeRowOneCose != null)
            {
                l_fxAccInformationUnit.fxAccountCode1 = l_fxAccountCodeRowOneCose.getFxAccountCode();
            }

            if (l_fxAccountCodeRowTenCose != null)
            {
                l_fxAccInformationUnit.fxAccountCode2 = l_fxAccountCodeRowTenCose.getFxAccountCode();
            }
            l_fxAccInformationUnit.fxSystemCode = l_strFxSystemCode;
            //３－４）　@１）で生成したListオブジェクトに為替保証金口座情報オブジェクトを格納する。
            l_lisFxAccountInfos.add(l_fxAccInformationUnit);
        }

        log.exiting(STR_METHOD_NAME);
        //４）ArrayList.toArray()の戻り値を返却
        
        //取得したデータが0件場合、nullを返却
        if(l_lisFxAccountInfos.size() == 0){
			l_fxAccountInfos = null;
			return l_fxAccountInfos;
		}
        
        //それ以外の場合、取得した結果を返却
        else{
        
        	l_fxAccountInfos = new WEB3AccInfoFxAccountInfo[l_lisFxAccountInfos.size()];
        
        }
        
        l_lisFxAccountInfos.toArray(l_fxAccountInfos);
        return l_fxAccountInfos;
    }
    
    /**
     * getオリックス専用振込先口座。<BR>
     * オリックス専用振込先口座を取得する。<BR>
     *<BR>
     * 指定顧客のオリックス専用振込先口座を取得する。<BR>
     * <BR>
     * １）　@トレードボーナスプランテーブル検索<BR>
     *　@トレードボーナスプランテーブルを以下の条件で検索する。<BR>
     *<BR>
     *　@[条件]<BR>
     *　@トレードボーナスプランテーブル.証券会社コード = 顧客.getInstitution().getInstitutionCode() And<BR>
     *　@トレードボーナスプランテーブル.部店コード = 顧客.getBranch().getBranchCode() And<BR>
     *　@トレードボーナスプランテーブル.口座コード = 顧客.getAccountCode()<BR>
     *<BR>
     *　@該当行があれば、取得行.サブアカウントを返却する。<BR>
     *　@該当行がなかった場合は、nullを返却する。<BR>
     *<BR>
     * @@param l_mainAccount 顧客<BR>
     * @@return String
     */
    public String getOrixSubAccountCode(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTaxType(WEB3GentradeMainAccount l_MainAccount)";
        log.entering(STR_METHOD_NAME);
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        String l_strAccountCode = l_mainAccount.getAccountCode();
        OrixTradeBonusPlanRow l_row = null;
        try
        {
            l_row = OrixTradeBonusPlanDao.findRowByInstitutionCodeBranchCodeAccountCode(l_strInstitutionCode,
                l_strBranchCode,l_strAccountCode);
        }
        catch (DataFindException l_e)
        {
            log.debug(STR_METHOD_NAME,l_e);
            return null;
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }


        log.exiting(STR_METHOD_NAME);
        if (l_row != null)
        {
            return l_row.getSubAccCode();
        }
        else
        {
            return null;
        }
        
    }
    
    /**
     * (get部店プリファ@レンス)<BR>
     * 顧客オブジェクトより、部店用プリファ@レンステーブルから<BR>
     * 委託手数料コース区分を取得する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 　@　@部店プリファ@レンステーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@部店ID = 顧客.getBranch() And<BR>
     * 　@　@プリファ@レンス名 = プリファ@レンス名.委託手数料コース取得区分 And<BR>
     * 　@　@プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * 　@　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ２）　@検索結果.プリファ@レンスの値を返却する。<BR>
     * @@param l_mainAccount - 顧客<BR>
     * @@return Integer
     * @@throws WEB3BaseException
     */
    protected Integer getBranchPreferences(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchPreferences(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        try 
        {
            //１）DB検索 
            //  部店プリファ@レンステーブルを以下の条件で検索する。
            BranchPreferencesRow l_branchReferencesRow = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_mainAccount.getBranch().getBranchId(), 
                    WEB3BranchPreferencesNameDef.GENTRADE_BIZLOGICPROVIDER_DIV,
                    1);
            
            log.exiting(STR_METHOD_NAME);
            
            //２）　@検索結果.プリファ@レンスの値を返却する。
            if (l_branchReferencesRow == null)
            {
                return null;
            }
            else
            {
                return Integer.valueOf(l_branchReferencesRow.getValue());
            }
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
     * (create法@人情報)<BR>
     * 顧客オブジェクトより、法@人情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@法@人情報オブジェクトを生成する。<BR>
     * <BR>
     * ２）　@口座情報マスタオブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@口座情報マスタ.get口座情報マスタ(引数：顧客オブジェクト)にて、<BR>
     * 口座情報マスタオブジェクトを取得する。<BR>
     * <BR>
     * ３）　@以下の通り、プロパティをセットし返却する。<BR>
     * <BR>
     *　@法@人情報.代表者名（カナ）姓 = 口座情報マスタ.代表者名（カナ）姓<BR>
     *　@法@人情報.代表者名（カナ）名 = 口座情報マスタ.代表者名（カナ）名<BR>
     *　@法@人情報.代表者名（漢字）姓 = 口座情報マスタ.代表者名（漢字）姓<BR>
     *　@法@人情報.代表者名（漢字）名 = 口座情報マスタ.代表者名（漢字）名<BR>
     *　@法@人情報.代表者権 = 口座情報マスタ.代表者権<BR>
     *　@法@人情報.取引責任者名（カナ）姓 = 口座情報マスタ.取引責任者名（カナ）姓<BR>
     *　@法@人情報.取引責任者名（カナ）名 = 口座情報マスタ.取引責任者名（カナ）名<BR>
     *　@法@人情報.取引責任者名（漢字）姓 = 口座情報マスタ.取引責任者名（漢字）姓<BR>
     *　@法@人情報.取引責任者名（漢字）名 = 口座情報マスタ.取引責任者名（漢字）名<BR>
     *　@法@人情報.取引責任者・所属部署 = 口座情報マスタ.取引責任者 所属部署<BR>
     *　@法@人情報.取引責任者・役職 = 口座情報マスタ.取引責任者　@役職<BR>
     *　@法@人情報.取引責任者・生年月日年号 = 口座情報マスタ.取引責任者生年月日年号<BR>
     *　@法@人情報.取引責任者・生年月日 = 口座情報マスタ.取引責任者生年月日<BR>
     *　@法@人情報.取引責任者・郵便番号 = 口座情報マスタ.取引責任者郵便番号<BR>
     *　@法@人情報.取引責任者・住所1 = 口座情報マスタ.取引責任者住所1<BR>
     *　@法@人情報.取引責任者・住所2 = 口座情報マスタ.取引責任者住所2<BR>
     *　@法@人情報.取引責任者・住所3 = 口座情報マスタ.取引責任者住所3<BR>
     *　@法@人情報.取引責任者・その他連絡先 = 口座情報マスタ.その他連絡先（携帯、自宅等）<BR> 
     *　@法@人情報.取引責任者・会社直通番号 = 口座情報マスタ.取引責任者会社直通番号<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト
     * @@return WEB3AccInfoCorporateInfo
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoCorporationInfo createCorporationInfo(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createCorporationInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AccInfoCorporationInfo l_accInfoCorporationInfo = new WEB3AccInfoCorporationInfo();
        
        //口座情報マスタオブジェクトを取得する。 
        //口座情報マスタ.get口座情報マスタ()(引数：顧客オブジェクト)にて、
        //口座情報マスタオブジェクトを取得する。 
        WEB3AccInfoMaster l_accInfoMaster = WEB3AccInfoMaster.getAccInfoMaster(l_mainAccount);

        if (l_accInfoMaster != null)
        {
            AccountInfoMstRow l_accountInfoMstRow = (AccountInfoMstRow)l_accInfoMaster.getDataSourceObject();
            AccountInfoMstParams l_accountInfoMstParams = new AccountInfoMstParams(l_accountInfoMstRow);
            
            //法@人情報.代表者名（カナ）姓 = 口座情報マスタ.代表者名（カナ）姓        
            l_accInfoCorporationInfo.representFamilyNameKana = l_accountInfoMstParams.getRepresentFamilyNameAlt1();
            
            //法@人情報.代表者名（カナ）名 = 口座情報マスタ.代表者名（カナ）名
            l_accInfoCorporationInfo.representNameKana = l_accountInfoMstParams.getRepresentGivenNameAlt1();
            
            //法@人情報.代表者名（漢字）姓 = 口座情報マスタ.代表者名（漢字）姓
            l_accInfoCorporationInfo.representFamilyName = l_accountInfoMstParams.getRepresentFamilyName();
            
            //法@人情報.代表者名（漢字）名 = 口座情報マスタ.代表者名（漢字）名
            l_accInfoCorporationInfo.representName = l_accountInfoMstParams.getRepresentGivenName();
            
            //法@人情報.代表者権 = 口座情報マスタ.代表者権
            l_accInfoCorporationInfo.representPower = l_accountInfoMstParams.getRepresentPower();
            
            //法@人情報.取引責任者名（カナ）姓 = 口座情報マスタ.取引責任者名（カナ）姓
            l_accInfoCorporationInfo.directorFamilyNameKana = l_accountInfoMstParams.getDirectorFamilyNameAlt1();
            
            //法@人情報.取引責任者名（カナ）名 = 口座情報マスタ.取引責任者名（カナ）名
            l_accInfoCorporationInfo.directorNameKana = l_accountInfoMstParams.getDirectorGivenNameAlt1();
            
            //法@人情報.取引責任者名（漢字）姓 = 口座情報マスタ.取引責任者名（漢字）姓
            l_accInfoCorporationInfo.directorFamilyName = l_accountInfoMstParams.getDirectorFamilyName();
            
            //法@人情報.取引責任者名（漢字）名 = 口座情報マスタ.取引責任者名（漢字）名
            l_accInfoCorporationInfo.directorName = l_accountInfoMstParams.getDirectorGivenName();
            
            //法@人情報.取引責任者・所属部署 = 口座情報マスタ.取引責任者 所属部署
            l_accInfoCorporationInfo.directorDepartment = l_accountInfoMstParams.getDirectorDepartment();
            
            //法@人情報.取引責任者・役職 = 口座情報マスタ.取引責任者　@役職
            l_accInfoCorporationInfo.directorPosition = l_accountInfoMstParams.getDirectorPost();
            
            //法@人情報.取引責任者・生年月日年号 = 口座情報マスタ.取引責任者生年月日年号
            l_accInfoCorporationInfo.directorEraBorn = l_accountInfoMstParams.getDirectorEraBorn();
            
            //法@人情報.取引責任者・生年月日 = 口座情報マスタ.取引責任者生年月日
            l_accInfoCorporationInfo.directorBornDate = l_accountInfoMstParams.getDirectorBornDate();
            
            //法@人情報.取引責任者・郵便番号 = 口座情報マスタ.取引責任者郵便番号
            l_accInfoCorporationInfo.directorZipCode = l_accountInfoMstParams.getDirectorZipCode();
            
            //法@人情報.取引責任者・住所1 = 口座情報マスタ.取引責任者住所1
            l_accInfoCorporationInfo.directorAddress1 = l_accountInfoMstParams.getDirectorAddress1();
            
            //法@人情報.取引責任者・住所2 = 口座情報マスタ.取引責任者住所2
            l_accInfoCorporationInfo.directorAddress2 = l_accountInfoMstParams.getDirectorAddress2();
            
            //法@人情報.取引責任者・住所3 = 口座情報マスタ.取引責任者住所3
            l_accInfoCorporationInfo.directorAddress3 = l_accountInfoMstParams.getDirectorAddress3();
            
            //法@人情報.取引責任者・その他連絡先 = 口座情報マスタ.その他連絡先（携帯、自宅等)
            l_accInfoCorporationInfo.directorOtherContact = l_accountInfoMstParams.getOtherContact();
            
            //法@人情報.取引責任者・会社直通番号 = 口座情報マスタ.取引責任者会社直通番号    
            l_accInfoCorporationInfo.directorCorpDirect = l_accountInfoMstParams.getDirectorCorpTelephone();
        }  
                  
        log.exiting(STR_METHOD_NAME);        
        return l_accInfoCorporationInfo;
    }
    
    /**
     * (createストックオプション銘柄情報)<BR>
     * 顧客オブジェクトより、ストックオプション銘柄情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@ストックOP銘柄管理マスターテーブルより、下記条件に該当するレコードを取得する。 <BR>
     * 　@※取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 顧客.getInstitution().getInstitutionCode()<BR>
     * 　@部店ＩＤ = 顧客.getBranch().getBranchId()<BR>
     * 　@口座ＩＤ = 顧客.getAccountId()<BR>
     * <BR>
     * ２）　@株式プロダクトマネージャ取得<BR>
     * 　@※FinApp.getTradingModule(ProductTypeEnum.株式).getProductManager()にて取得する。<BR>
     * <BR>
     * ３）　@ArrayListオブジェクトの生成<BR>
     * <BR>
     * ４）　@１）の戻り値の要素分、Loop処理を行う。<BR>
     * <BR>
     * 　@４－１）　@株式銘柄オブジェクトを取得する。<BR>
     * 　@　@株式プロダクトマネージャ.getProduct()にて株式銘柄オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@[getProduct()に指定する引数]<BR>
     * 　@　@銘柄ＩＤ：　@ストックOP銘柄管理マスターRow.get銘柄ＩＤ<BR>
     * <BR>
     * 　@４－２）　@ストックオプション銘柄情報クラスのオブジェクト生成 <BR>
     * <BR>
     * 　@４－３）　@ストックオプション銘柄情報オブジェクト.銘柄コード = 株式銘柄.getProductCode()<BR>
     * <BR>
     * 　@４－４）　@ストックオプション銘柄情報オブジェクト.銘柄名 = 株式銘柄.getStandardName()<BR>
     * <BR>
     * 　@４－５）　@ArrayListオブジェクト.add(ストックオプション銘柄情報オブジェクト) <BR>
     * <BR>
     * ５）　@ArrayListオブジェクトをストックオプション銘柄情報[]に変換する。 <BR>
     * <BR>
     * ６）　@５）で変換したストックオプション銘柄情報[]を返却する。<BR>
     * @@param 顧客 - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return WEB3AccInfoStockOptionInfo[]
     * @@throws WEB3BaseException
     * @@roseuid 44EBACB203AD
     */
    protected WEB3AccInfoStockOptionInfo[] createStockOptionInfo(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createStockOptionInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        List l_lisRecords = null;
        int l_intSize = 0;
        //顧客オブジェクトより、ストックオプション銘柄情報メッセージデータを作成する。 
        //１）　@ストックOP銘柄管理マスターテーブルより、下記条件に該当するレコードを取得する。
        //　@※取得できなかった場合は、nullを返却する。
        //[条件] 
        //証券会社コード = 顧客.getInstitution().getInstitutionCode() 
        //部店ＩＤ = 顧客.getBranch().getBranchId() 
        //口座ＩＤ = 顧客.getAccountId()
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and branch_id = ? ");
            l_sbWhere.append(" and account_id = ? ");

            Object[] l_objWhere = {
                l_mainAccount.getInstitution().getInstitutionCode(),   
                new Long(l_mainAccount.getBranch().getBranchId()), 
                new Long(l_mainAccount.getAccountId())
                };

            l_lisRecords = l_processor.doFindAllQuery(
                StockOptionProductRow.TYPE, 
                l_sbWhere.toString(), 
                null, 
                null, 
                l_objWhere
                );

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
        
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@株式プロダクトマネージャ取得
        //※FinApp.getTradingModule(ProductTypeEnum.株式).getProductManager()にて取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_equityTradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //株式プロダクトマネージャ取得
        WEB3EquityProductManager l_equityProductMgr = (WEB3EquityProductManager)l_equityTradingModule.getProductManager();
 
        //３）　@ArrayListオブジェクトの生成
        List l_lisTemp = new ArrayList();

        //４）　@１）の戻り値の要素分、Loop処理を行う。
        if (l_lisRecords != null)
        {
            l_intSize = l_lisRecords.size();
        }

        for (int i = 0; i < l_intSize; i++)
        {
            //４－１）　@株式銘柄オブジェクトを取得する。
            //株式プロダクトマネージャ.getProduct()にて株式銘柄オブジェクトを取得する。 
            //[getProduct()に指定する引数] 
            //銘柄ＩＤ：　@ストックOP銘柄管理マスターRow.get銘柄ＩＤ
            //株式銘柄オブジェクトを取得する。
            StockOptionProductRow l_stockOptionProductRow = (StockOptionProductRow)l_lisRecords.get(i);
            WEB3EquityProduct l_equityProduct = null;
            try
            {   
                l_equityProduct = (WEB3EquityProduct) l_equityProductMgr.getProduct(l_stockOptionProductRow.getProductId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //４－２）　@ストックオプション銘柄情報クラスのオブジェクト生成
            WEB3AccInfoStockOptionInfo l_stockOptionInfo = new WEB3AccInfoStockOptionInfo();

            //４－３）　@ストックオプション銘柄情報オブジェクト.銘柄コード = 株式銘柄.getProductCode()
            l_stockOptionInfo.productCode = l_equityProduct.getProductCode();

            //４－４）　@ストックオプション銘柄情報オブジェクト.銘柄名 = 株式銘柄.getStandardName()
            l_stockOptionInfo.productName = l_equityProduct.getStandardName();

            //４－５）　@ArrayListオブジェクト.add(ストックオプション銘柄情報オブジェクト)
            l_lisTemp.add(l_stockOptionInfo);

        }

        //５）　@ArrayListオブジェクトをストックオプション銘柄情報[]に変換する。
        WEB3AccInfoStockOptionInfo[] l_stockOptionInfos = new WEB3AccInfoStockOptionInfo[l_lisTemp.size()];
        l_lisTemp.toArray(l_stockOptionInfos);

        //６）　@５）で変換したストックオプション銘柄情報[]を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_stockOptionInfos;
    }

    /**
     * (create割引キャンペーン情報)<BR>
     * 手数料割引キャンペーン情報を作成する。<BR>
     * <BR>
     * １）　@手数料割引キャンペーン顧客履歴テーブルを以下の条件で検索する。<BR> 
     * 　@・該当行がなかった場合は、nullを返却する。<BR>
     * 　@・取得したList中に【登録タイプ="2 ： 強制個別顧客指定"】の<BR>
     * 　@　@データが含まれる場合はnullを返却する。<BR>
     * 　@[検索条件] <BR>
     * 　@口座ID = 顧客.getAccountId() and <BR>
     * 　@対象期間From <= 翌々営業日 and<BR>
     * 　@対象期間To >= 当日営業日 and<BR>
     * 　@有効区分 = 1：有効<BR>
     * <BR>
     * 　@[ソート条件]<BR>
     * 　@対象期間From.asc<BR>
     * <BR>
     * ２）　@手数料割引キャンペーン商品マスタテーブルを以下の条件で検索する。<BR>
     * 　@[検索条件] <BR>
     * 　@キャンペーン手数料条件ID = １）で取得したList.get(0).キャンペーン手数料条件ID<BR>
     * <BR>
     * ３）　@取得したレコード分、Loop処理を行う。 <BR>
     *　@３－１）　@手数料割引キャンペーン情報オブジェクトを生成する。 <BR>
     * <BR>
     *　@３－２）　@手数料割引キャンペーン商品マスタテーブルを以下の条件で検索する。 <BR>
     *　@[検索条件]  <BR>
     *　@キャンペーン手数料条件ID = １）で取得したList.get( i ).キャンペーン手数料条件ID <BR>
     * <BR>
     *　@３－３)　@３－２）で取得したListからLoop処理にて商品コードのListを生成 <BR>
     * <BR>
     *　@３－４）　@以下の通り、プロパティをセットし、返却する。 <BR>
     *　@手数料割引キャンペーン情報.キャンペーン名称 = １）で取得したList.get( i ).手数料割引キャンペーン名称 <BR>
     *　@手数料割引キャンペーン情報.商品コード = ３－３）で生成した商品コードListをString型の配列に変換した値 <BR>
     *　@手数料割引キャンペーン情報.割引率 = (100 － 取得したList.get( i ).徴収率)をString型に変換 <BR>
     *　@手数料割引キャンペーン情報.適用期間from = １）で取得したList.get( i ).対象期間From <BR>
     *　@手数料割引キャンペーン情報.適用期間To = １）で取得したList.get( i ).対象期間To <BR>
     * <BR>
     *　@３－５）　@手数料割引キャンペーン情報オブジェクトをListに追加 <BR>
     * <BR>
     * ４）　@ArrayListオブジェクトを配列に変換し、返却
     * <BR>
     * @@param 顧客 - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return WEB3AccInfoCommissionCampaignInfo[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoCommissionCampaignInfo[] createAccInfoCampaignInfo(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAccInfoCampaignInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //検索条件
        StringBuffer l_sbSearchCondition = new StringBuffer();
        l_sbSearchCondition.append(" account_id = ? ");
        l_sbSearchCondition.append(" and appli_start_date <= ? ");
        l_sbSearchCondition.append(" and appli_end_date >= ? ");
        l_sbSearchCondition.append(" and valid_div = ? ");
        String l_strSearchCondition = l_sbSearchCondition.toString();
        
        Object[] l_strSearchConditionContainers = null;
        List l_lisSearchContainers = new ArrayList();

        if (l_mainAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //口座ID
        Long l_strAccountId = new Long(l_mainAccount.getAccountId());
        l_lisSearchContainers.add(l_strAccountId);
        //業務日付
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        WEB3GentradeBizDate l_bizDateCalcUtil = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        //翌々営業日
        Timestamp l_tsBizDate = l_bizDateCalcUtil.roll(2);
        l_lisSearchContainers.add(l_tsBizDate);
        //当日営業日
        l_lisSearchContainers.add(l_datBizDate);
        //有効区分
        l_lisSearchContainers.add(WEB3ValidDivDef.EFFECTIVE);
        l_strSearchConditionContainers = l_lisSearchContainers.toArray();
        //ソート条件
        String l_sortCondition = " appli_start_date ASC ";

        List l_lisCommCampaignAccHistory = new ArrayList();
        //１）　@手数料割引キャンペーン顧客履歴テーブルを以下の条件で検索する。
        //[検索条件]
        //口座ID = 顧客.getAccountId() and 
        //対象期間From <= 翌営業日 and
        //対象期間To >= 当日営業日 and
        //有効区分 = 1：有効 
        //[ソート条件]
        //対象期間From.asc
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCommCampaignAccHistory = l_queryProcessor.doFindAllQuery(
                    CommCampaignAccHistoryRow.TYPE,
                    l_strSearchCondition,
                    l_sortCondition,
                    null,
                    l_strSearchConditionContainers);
        }
        catch (DataFindException l_ex)
        {
            //該当行がなかった場合は、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
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

        if ((l_lisCommCampaignAccHistory == null) || (l_lisCommCampaignAccHistory.size() == 0))
        {
            //該当行がなかった場合は、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //取得したList中に【登録タイプ="2 ： 強制個別顧客指定"】の
        //データが含まれる場合はnullを返却する。
        for (int i = 0; i < l_lisCommCampaignAccHistory.size(); i++)
        {
            if (WEB3AccInfoRegistTypeDef.FORCE_INDIVIDUAL.equals((
                    (CommCampaignAccHistoryRow)l_lisCommCampaignAccHistory.get(i)).getRegistType()))
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }

        //２）　@ArrayListオブジェクト生成
        List l_lisAccInfoCommissionCampaignInfos = new ArrayList();
        //３）　@取得したレコード分、Loop処理を行う。
        for (int i = 0; i < l_lisCommCampaignAccHistory.size(); i++)
        {
            //３－１）　@手数料割引キャンペーン情報オブジェクトを生成する。
            WEB3AccInfoCommissionCampaignInfo l_accInfoCommissionCampaignInfo =
                new WEB3AccInfoCommissionCampaignInfo();
            //３－２）　@手数料割引キャンペーン商品マスタテーブルを以下の条件で検索する。
            //[検索条件] 
            //キャンペーン手数料条件ID = １）で取得したList.get( i ).キャンペーン手数料条件ID
            l_strSearchCondition = " campaign_id = ? ";
            CommCampaignAccHistoryRow l_commCampaignAccHistoryRow =
                (CommCampaignAccHistoryRow)l_lisCommCampaignAccHistory.get(i);
            Long l_lngCampaignId =
                new Long(l_commCampaignAccHistoryRow.getCampaignId());

            l_strSearchConditionContainers = new Object[1];;
            l_strSearchConditionContainers[0] = l_lngCampaignId;

            List l_lisCampaignConditionMasters = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisCampaignConditionMasters = l_queryProcessor.doFindAllQuery(
                        CommCampaignProductMstRow.TYPE,
                        l_strSearchCondition,
                        l_strSearchConditionContainers);
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
            if ((l_lisCampaignConditionMasters == null) || (l_lisCampaignConditionMasters.size() == 0))
            {
                log.debug("手数料割引キャンペーン商品マスタのデータ取得出来ない、例外をスローする。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "条件に該当するデータが存在しない。");
            }
            List l_lisCommProductCodes = new ArrayList();
            //３－３)　@３－２）で取得したListからLoop処理にて商品コードのListを生成
            for (int j = 0; j < l_lisCampaignConditionMasters.size(); j++)
            {
                l_lisCommProductCodes.add(((CommCampaignProductMstRow)l_lisCampaignConditionMasters.get(j)).getCommProductCode());
            }
            //３－４）　@以下の通り、プロパティをセットし、返却する。
            //手数料割引キャンペーン情報.キャンペーン名称 = １）で取得したList.get( i ).手数料割引キャンペーン名称
            //手数料割引キャンペーン情報.商品コード = ３－３）で生成した商品コードListをString型の配列に変換した値
            //手数料割引キャンペーン情報.割引率 = (100 － 取得したList.get( i ).徴収率)をString型に変換
            //手数料割引キャンペーン情報.適用期間from = １）で取得したList.get( i ).対象期間From
            //手数料割引キャンペーン情報.適用期間To = １）で取得したList.get( i ).対象期間To
            CommCampaignAccHistoryRow l_commCampaignAccHistory = (CommCampaignAccHistoryRow)l_lisCommCampaignAccHistory.get(i);
            l_accInfoCommissionCampaignInfo.campaignName = l_commCampaignAccHistory.getCommCampaignName();
            l_accInfoCommissionCampaignInfo.itemCode =
                (String[])l_lisCommProductCodes.toArray(new String[l_lisCommProductCodes.size()]);
            l_accInfoCommissionCampaignInfo.discountRate =
                WEB3StringTypeUtility.formatNumber(100 - l_commCampaignAccHistoryRow.getAccountChargeRatio());
            l_accInfoCommissionCampaignInfo.targetPeriodFrom = l_commCampaignAccHistoryRow.getAppliStartDate();
            l_accInfoCommissionCampaignInfo.targetPeriodTo = l_commCampaignAccHistoryRow.getAppliEndDate();
            //３－５）　@手数料割引キャンペーン情報オブジェクトをListに追加
            l_lisAccInfoCommissionCampaignInfos.add(l_accInfoCommissionCampaignInfo);
        }

        WEB3AccInfoCommissionCampaignInfo[] l_accInfoCommissionCampaignInfos =
            new WEB3AccInfoCommissionCampaignInfo[l_lisAccInfoCommissionCampaignInfos.size()];
        l_lisAccInfoCommissionCampaignInfos.toArray(l_accInfoCommissionCampaignInfos);
        
        log.exiting(STR_METHOD_NAME);
        return l_accInfoCommissionCampaignInfos;
    }

    /**
     * (create外貨振込先指定口座情報)<BR>
     * 顧客オブジェクトより、外貨振込先指定口座に関して、お客様情報口座情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@外貨振込先金融機@関テーブルを以下の条件で読む。 <BR>
     * 　@　@　@※該当データが取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@外貨振込先金融機@関テーブル.証券会社コード = 顧客.getInstitution().getInstitutionCode() <BR>
     * 　@外貨振込先金融機@関テーブル.部店コード = 顧客.getBranch().getBranchCode() <BR>
     * 　@外貨振込先金融機@関テーブル.顧客コード = 顧客.getAccountCode() <BR>
     * 　@外貨振込先金融機@関テーブル.登録区分 = '0'<BR>
     * <BR>
     * ２）　@ArrayListオブジェクトの生成<BR>
     * <BR>
     * ３）　@１）の戻り値の要素分、Loop処理を行う。<BR>
     * <BR>
     * 　@３－１）　@お客様情報口座情報オブジェクトを生成する。<BR>
     * 　@３－２）　@以下の通りプロパティをセット。<BR>
     * 　@　@お客様情報口座情報.通貨コード = 外貨振込先金融機@関.通貨コード <BR>
     * 　@　@お客様情報口座情報.金融機@関コード  = 外貨振込先金融機@関.銀行コード<BR>
     * 　@　@お客様情報口座情報.金融機@関名称 = 外貨振込先金融機@関.銀行名 <BR>
     * 　@　@お客様情報口座情報.支店コード = 外貨振込先金融機@関.支店コード <BR>
     * 　@　@お客様情報口座情報.支店名 = 外貨振込先金融機@関.支店名 <BR>
     * 　@　@お客様情報口座情報.口座種類区分 = 外貨振込先金融機@関.預金区分<BR>
     * 　@　@お客様情報口座情報.口座番号 = 外貨振込先金融機@関.口座番号 <BR>
     * 　@　@お客様情報口座情報.口座名義人 = 外貨振込先金融機@関.口座名義人<BR>
     * 　@３－３）　@ArrayListオブジェクト.add(お客様情報口座情報オブジェクト)<BR>
     * <BR>
     * ４）　@ArrayListオブジェクトをお客様情報口座情報[]に変換する。<BR>
     * <BR>
     * ５）　@４）で変換したお客様情報口座情報[]を返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return WEB3AccInfoAccountInfo[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoAccountInfo[] createForeignTransferAccountInfo(WEB3GentradeMainAccount l_mainAccount) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createForeignTransferAccountInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //検索条件
        StringBuffer l_sbSearchCondition = new StringBuffer();
        l_sbSearchCondition.append(" institution_code = ? ");
        l_sbSearchCondition.append(" and branch_code = ? ");
        l_sbSearchCondition.append(" and account_code = ? ");
        l_sbSearchCondition.append(" and fin_del_div = ? ");
        String l_strSearchCondition = l_sbSearchCondition.toString();
        
        Object[] l_strSearchConditionContainers = null;
        List l_lisSearchContainers = new ArrayList();

        if (l_mainAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //顧客.getInstitution().getInstitutionCode()
        l_lisSearchContainers.add(l_mainAccount.getInstitution().getInstitutionCode());
        //顧客.getBranch().getBranchCode()
        l_lisSearchContainers.add(l_mainAccount.getBranch().getBranchCode());
        //顧客.getAccountCode()
        l_lisSearchContainers.add(l_mainAccount.getAccountCode());
        //登録区分 = '0'
        l_lisSearchContainers.add(WEB3FinDelDivDef.EXCEPT_OBLITERATION);
        l_strSearchConditionContainers = l_lisSearchContainers.toArray();

        //１）　@外貨振込先金融機@関テーブルを以下の条件で読む。
        //　@　@　@※該当データが取得できなかった場合は、nullを返却する。
        //　@[条件]
        //　@外貨振込先金融機@関テーブル.証券会社コード = 顧客.getInstitution().getInstitutionCode()
        //　@外貨振込先金融機@関テーブル.部店コード = 顧客.getBranch().getBranchCode()
        //　@外貨振込先金融機@関テーブル.顧客コード = 顧客.getAccountCode()
        //　@外貨振込先金融機@関テーブル.登録区分 = '0'

        //２）　@ArrayListオブジェクトの生成
        List l_lisFTransFinInstitution = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisFTransFinInstitution = l_queryProcessor.doFindAllQuery(
                    FTransFinInstitutionRow.TYPE,
                    l_strSearchCondition,
                    l_strSearchConditionContainers);
        }
        catch (DataFindException l_ex)
        {
            //該当データが取得できなかった場合は、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
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

        //３）　@１）の戻り値の要素分、Loop処理を行う。
        if ((l_lisFTransFinInstitution == null) || (l_lisFTransFinInstitution.size() == 0))
        {
            //該当データが取得できなかった場合は、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //ArrayListオブジェクト
        List l_lisAccInfoAccountInfo = new ArrayList();
        for (int i = 0; i < l_lisFTransFinInstitution.size(); i++)
        {
            //３－１）　@お客様情報口座情報オブジェクトを生成する。
            WEB3AccInfoAccountInfo l_accountInfo = new WEB3AccInfoAccountInfo();
            //外貨振込先金融機@関クラスのオブジェクト生成
            FTransFinInstitutionRow l_fTransFinInstitutionRow = (FTransFinInstitutionRow)l_lisFTransFinInstitution.get(i);
            //３－２）　@以下の通りプロパティをセット。
            //　@　@お客様情報口座情報.通貨コード = 外貨振込先金融機@関.通貨コード
            //　@　@お客様情報口座情報.金融機@関コード  = 外貨振込先金融機@関.銀行コード
            //　@　@お客様情報口座情報.金融機@関名称 = 外貨振込先金融機@関.銀行名
            //　@　@お客様情報口座情報.支店コード = 外貨振込先金融機@関.支店コード
            //　@　@お客様情報口座情報.支店名 = 外貨振込先金融機@関.支店名
            //　@　@お客様情報口座情報.口座種類区分 = 外貨振込先金融機@関.預金区分
            //　@　@お客様情報口座情報.口座番号 = 外貨振込先金融機@関.口座番号
            //　@　@お客様情報口座情報.口座名義人 = 外貨振込先金融機@関.口座名義人
            l_accountInfo.currencyCode = l_fTransFinInstitutionRow.getCurrencyCode();
            l_accountInfo.financialInstitutionCode = l_fTransFinInstitutionRow.getFinInstitutionCode();
            l_accountInfo.financialInstitutionName = l_fTransFinInstitutionRow.getFinInstitutionName();
            l_accountInfo.financialBranchCode = l_fTransFinInstitutionRow.getFinBranchCode();
            l_accountInfo.financialBranchName = l_fTransFinInstitutionRow.getFinBranchName();
            l_accountInfo.financialAccountType = l_fTransFinInstitutionRow.getFinSaveDiv();
            l_accountInfo.financialAccountCode = l_fTransFinInstitutionRow.getFinAccountNo();
            l_accountInfo.financialAccountName = l_fTransFinInstitutionRow.getFinAccountName();
            //３－３）　@ArrayListオブジェクト.add(お客様情報口座情報オブジェクト)
            l_lisAccInfoAccountInfo.add(l_accountInfo);
        }
        //４）　@ArrayListオブジェクトをお客様情報口座情報[]に変換する。
        WEB3AccInfoAccountInfo[] l_accInfoAccountInfos = new WEB3AccInfoAccountInfo[l_lisAccInfoAccountInfo.size()];
        //５）　@４）で変換したお客様情報口座情報[]を返却する。
        l_lisAccInfoAccountInfo.toArray(l_accInfoAccountInfos);
        log.exiting(STR_METHOD_NAME);
        return l_accInfoAccountInfos;
        
    }

    /**
     * (create複数アドレス情報)<BR>
     * 顧客行オブジェクトより、複数アドレス情報メッセージデータを作成する。<BR>
     * 顧客行より複数アドレス情報が取得出来ない場合、nullを返却する。<BR>
     * <BR>
     * １）　@複数アドレス情報オブジェクトを生成する。<BR>
     * <BR>
     * ２）　@以下の通り、プロパティをセットする。<BR>
     * 　@２-１） 該当列値がnull以外の場合<BR>
     * <BR>
     * 　@　@複数アドレス情報.メールアドレス２ = （引数）顧客行.emailアドレス1<BR>
     * 　@　@複数アドレス情報.メールアドレス３ = （引数）顧客行.emailアドレス2<BR>
     * 　@　@複数アドレス情報.約定通知送信フラグ = （引数）顧客行.約定通知送信フラグ<BR>
     * 　@　@複数アドレス情報.未約定通知送信フラグ = （引数）顧客行.未約定通知送信フラグ<BR>
     * 　@　@複数アドレス情報.重要連絡メール送信フラグ = （引数）顧客行.重要連絡メール送信フラグ<BR>
     * 　@　@複数アドレス情報.案内メール２送信フラグ = （引数）顧客行.案内メール２送信フラグ<BR>
     * 　@　@複数アドレス情報.メールアドレス２削除フラグ = false<BR>
     * 　@　@複数アドレス情報.メールアドレス３削除フラグ = false<BR>
     * <BR>
     * 　@２-２） 該当列値がnullの場合<BR>
     * 　@　@複数アドレス情報.メールアドレス２ = null<BR>
     * 　@　@複数アドレス情報.メールアドレス３ = null<BR>
     * 　@　@複数アドレス情報.約定通知送信フラグ = null<BR>
     * 　@　@複数アドレス情報.未約定通知送信フラグ = null<BR>
     * 　@　@複数アドレス情報.重要連絡メール送信フラグ = null<BR>
     * 　@　@複数アドレス情報.案内メール２送信フラグ = null<BR>
     * 　@　@複数アドレス情報.メールアドレス２削除フラグ = false<BR>
     * 　@　@複数アドレス情報.メールアドレス３削除フラグ = false<BR>
     * <BR>
     * <BR>
     * ３） 複数アドレス情報オブジェクトを返却する。<BR>
     * @@param l_mainAccountRow - (顧客行)<BR>
     * 顧客行オブジェクト<BR>
     * @@return WEB3AccInfoMultiMailAddressInfo
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoMultiMailAddressInfo createMultiMailAddressInfo(
        MainAccountRow l_mainAccountRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMultiMailAddressInfo(MainAccountRow)";
        log.entering(STR_METHOD_NAME);

        //１）　@複数アドレス情報オブジェクトを生成する。
        WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo =
            new WEB3AccInfoMultiMailAddressInfo();

        if (l_mainAccountRow == null)
        {
            //２-２） 該当列値がnullの場合
            //複数アドレス情報.メールアドレス２ = null
            l_accInfoMultiMailAddressInfo.mailAddress2 = null;

            //複数アドレス情報.メールアドレス３ = null
            l_accInfoMultiMailAddressInfo.mailAddress3 = null;

            //複数アドレス情報.約定通知送信フラグ = null
            l_accInfoMultiMailAddressInfo.execMailFlag = null;

            //複数アドレス情報.未約定通知送信フラグ = null
            l_accInfoMultiMailAddressInfo.unExecMailFlag = null;

            //複数アドレス情報.重要連絡メール送信フラグ = null
            l_accInfoMultiMailAddressInfo.importantMailFlag = null;

            //複数アドレス情報.案内メール２送信フラグ = null
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = null;

            //複数アドレス情報.メールアドレス２削除フラグ = false
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = false;

            //複数アドレス情報.メールアドレス３削除フラグ = false
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag3 = false;
        }
        else
        {
            //２-１） 該当列値がnull以外の場合
            //複数アドレス情報.メールアドレス２ = （引数）顧客行.emailアドレス1
            l_accInfoMultiMailAddressInfo.mailAddress2 = l_mainAccountRow.getEmailAddressAlt1();

            //複数アドレス情報.メールアドレス３ = （引数）顧客行.emailアドレス2
            l_accInfoMultiMailAddressInfo.mailAddress3 = l_mainAccountRow.getEmailAddressAlt2();

            //複数アドレス情報.約定通知送信フラグ = （引数）顧客行.約定通知送信フラグ
            if (!l_mainAccountRow.getOrderExeFlagIsNull())
            {
                l_accInfoMultiMailAddressInfo.execMailFlag = "" + l_mainAccountRow.getOrderExeFlag();
            }

            //複数アドレス情報.未約定通知送信フラグ = （引数）顧客行.未約定通知送信フラグ
            if (!l_mainAccountRow.getOrderUnexeFlagIsNull())
            {
                l_accInfoMultiMailAddressInfo.unExecMailFlag = "" + l_mainAccountRow.getOrderUnexeFlag();
            }

            //複数アドレス情報.重要連絡メール送信フラグ = （引数）顧客行.重要連絡メール送信フラグ
            if (!l_mainAccountRow.getImportantConnectionMailFlagIsNull())
            {
                l_accInfoMultiMailAddressInfo.importantMailFlag =
                    "" + l_mainAccountRow.getImportantConnectionMailFlag();
            }

            //複数アドレス情報.案内メール２送信フラグ = （引数）顧客行.案内メール２送信フラグ
            if (!l_mainAccountRow.getInformationMail2FlagIsNull())
            {
                l_accInfoMultiMailAddressInfo.guidanceMailFlag2 =
                    "" + l_mainAccountRow.getInformationMail2Flag();
            }

            //複数アドレス情報.メールアドレス２削除フラグ = false
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = false;

            //複数アドレス情報.メールアドレス３削除フラグ = false
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag3 = false;
        }

        log.exiting(STR_METHOD_NAME);
        return l_accInfoMultiMailAddressInfo;
    }

    /**
     * (create証券担保ローン口座開設情報)<BR>
     * 証券担保ローン口座開設情報を作成する。<BR>
     * <BR>
     * １）　@証券担保ローン口座開設情報オブジェクトを生成する。<BR>
     * <BR>
     * ２）引数:顧客.is証券担保ローン口座開設()==trueの場合、以下の処理を行う。<BR>
     * <BR>
     * 　@　@２－１）　@株券担保ローン口座テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@　@[検索条件]<BR>
     * 　@　@　@　@株券担保ローン口座テーブル.口座ID = 顧客.getAccountId() and<BR>
     * 　@　@　@　@株券担保ローン口座テーブル.開設日 != null<BR>
     * <BR>
     * 　@　@　@[ソート条件]<BR>
     * 　@　@　@　@株券担保ローン口座テーブル.開設日.asc<BR>
     * <BR>
     * 　@　@２－２）　@以下の通り、プロパティをセットする。<BR>
     * 　@　@　@　@　@証券担保ローン口座開設情報オブジェクト.開設状況 = 1：開設<BR>
     * 　@　@　@　@　@証券担保ローン口座開設情報オブジェクト.開設日 =<BR>
     * 　@　@　@　@　@　@　@　@取得したList..get( 0 ).get開設日<BR>
     * 　@　@　@　@　@　@　@　@*該当行がなかった場合は、null<BR>
     * <BR>
     * <BR>
     * <BR>
     * ３）　@引数:顧客.is証券担保ローン口座開設()==falseの場合、以下の処理を行う。<BR>
     * <BR>
     * <BR>
     * 　@　@３－１）　@以下の通り、プロパティをセットする。<BR>
     * 　@　@　@　@　@　@　@　@　@証券担保ローン口座開設情報オブジェクト.開設状況 =<BR>
     * 　@　@　@　@　@　@　@　@　@　@0：未開設<BR>
     * 　@　@証券担保ローン口座開設情報オブジェクト.開設日 = null<BR>
     * <BR>
     * <BR>
     * ４）　@証券担保ローン口座開設情報オブジェクトを返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return WEB3AccInfoStockLoanAccountInfo
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoStockLoanAccountInfo createStockLoanAccountInfo(
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createStockLoanAccountInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）　@証券担保ローン口座開設情報オブジェクトを生成する。
        WEB3AccInfoStockLoanAccountInfo l_accInfoStockLoanAccountInfo =
            new WEB3AccInfoStockLoanAccountInfo();

        //２）引数:顧客.is証券担保ローン口座開設()==trueの場合、以下の処理を行う。
        if (l_mainAccount.isSecuredLoanAccountOpen())
        {
            //２－１）　@株券担保ローン口座テーブルを以下の条件で検索する。
            //[検索条件]
            //株券担保ローン口座テーブル.口座ID = 顧客.getAccountId() and
            //株券担保ローン口座テーブル.開設日 != null
            String l_strQuery = " account_id = ? and account_open_date is not null ";
            Long l_accountId = new Long(l_mainAccount.getAccountId());
            Object[] l_whereValues = {l_accountId};

            //[ソート条件]
            //株券担保ローン口座テーブル.開設日.asc
            List l_lisStockSecuredLoans = new ArrayList();
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisStockSecuredLoans = l_queryProcessor.doFindAllQuery(
                    StockSecuredLoanRow.TYPE,
                    l_strQuery,
                    " account_open_date asc ",
                    null,
                    l_whereValues);
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

            //２－２）　@以下の通り、プロパティをセットする。
            //証券担保ローン口座開設情報オブジェクト.開設状況 = 1：開設
            l_accInfoStockLoanAccountInfo.stockLoanAccOpenDiv =
                WEB3AccInfoStockLoanAccOpenDivDef.OPEN;

            //証券担保ローン口座開設情報オブジェクト.開設日 = 取得したList..get( 0 ).get開設日
            //*該当行がなかった場合は、null
            if (l_lisStockSecuredLoans.isEmpty())
            {
                l_accInfoStockLoanAccountInfo.stockLoanAccOpenDate = null;
            }
            else
            {
                StockSecuredLoanRow l_stockSecuredLoanRow =
                    (StockSecuredLoanRow)l_lisStockSecuredLoans.get(0);
                l_accInfoStockLoanAccountInfo.stockLoanAccOpenDate =
                    l_stockSecuredLoanRow.getAccountOpenDate();
            }
        }
        else
        {
            //３）　@引数:顧客.is証券担保ローン口座開設()==falseの場合、以下の処理を行う。
            //３－１）　@以下の通り、プロパティをセットする。
            //証券担保ローン口座開設情報オブジェクト.開設状況 = 0：未開設
            l_accInfoStockLoanAccountInfo.stockLoanAccOpenDiv =
                WEB3AccInfoStockLoanAccOpenDivDef.NOT_OPEN;

            //証券担保ローン口座開設情報オブジェクト.開設日 = null
            l_accInfoStockLoanAccountInfo.stockLoanAccOpenDate = null;
        }

        //４）　@証券担保ローン口座開設情報オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_accInfoStockLoanAccountInfo;
    }

    /**
     * (createCFD口座情報)<BR>
     * 指定顧客のＦＸ顧客，ＦＸ口座番号データより、<BR>
     * CFD口座情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@ArrayListオブジェクトの生成。<BR>
     * 　@　@　@CFD口座情報オブジェクトを格納する為のオブジェクト生成。<BR>
     * <BR>
     * <BR>
     * ２）　@FXシステムコードの取得<BR>
     * 　@　@　@会社別FXシステム条件テーブルを以下の条件で検索する。<BR>
     * 　@　@　@※該当行がなかった場合は、nullを返却<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@会社別FXシステム条件テーブル.証券会社コード =<BR>
     * 　@　@　@　@　@　@顧客.getInstitution().getInstitutionCode() And<BR>
     * 　@　@　@会社別FXシステム条件テーブル.部店コード =<BR>
     * 　@　@　@　@　@　@顧客.getBranch().getBranchCode() And<BR>
     * 　@　@　@FXシステム区分 = 2<BR>
     * <BR>
     * ３）　@２）の処理で取得した会社別FXシステム条件テーブルの要素毎に、<BR>
     * 　@　@　@　@　@　@以下の処理を行う<BR>
     * <BR>
     * 　@＜２）でレコードが取得できた場合＞<BR>
     * 　@　@　@　@　@３－１）ＦＸログインＩＤ取得<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ顧客テーブルを以下の条件で検索する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@※該当行がなかった場合は、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@何もせずに次の要素へ<BR>
     * 　@　@　@　@　@　@　@　@　@　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@[条件]<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ顧客テーブル.証券会社コード =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@顧客.getInstitution().getInstitutionCode() And<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ顧客テーブル.部店コード =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@顧客.getBranch().getBranchCode() And<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ顧客テーブル.ＦＸシステムコード　@=<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@会社別FXシステム条件行.FXシステムコード And<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ顧客テーブル.顧客コード = 顧客.getAccountCode()<BR>
     * 　@　@　@　@　@<BR>
     * 　@　@　@　@　@３－２）FX口座番号取得<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ口座番号テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@[条件]<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ口座番号テーブル.証券会社コード =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@顧客.getInstitution().getInstitutionCode() And<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ口座番号テーブル.部店コード =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@顧客.getBranch().getBranchCode() And<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ口座番号テーブル.ＦＸシステムコード　@=<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@会社別FXシステム条件行.FXシステムコード And<BR>
     * 　@　@　@　@　@　@　@　@　@　@ＦＸ口座番号テーブル.顧客コード = 顧客.getAccountCode()<BR>
     * <BR>
     * 　@　@　@　@　@３－３）　@CFD口座情報オブジェクトを生成し以下の通り、<BR>
     * 　@　@　@　@　@　@　@　@　@プロパティをセットする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@CFD口座情報.CFDログインＩＤ： ＦＸ顧客行.ＦＸログインＩＤ<BR>
     * 　@　@　@　@　@　@　@　@　@　@CFD口座情報.CFD口座番号： ＦＸ口座番号行.ＦＸ口座番号<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@※３－２）でレコードが取得できた場合のみCFD口座情報.CFD口座番号に値をセット<BR>
     * 　@　@　@　@　@　@　@　@　@　@CFD口座情報.FXシステムコード：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@会社別FXシステム条件行.FXシステムコード<BR>
     * 　@　@　@　@　@<BR>
     * 　@　@　@　@　@３－４）　@１）で生成したListオブジェクトにCFD口座情報オブジェクトを格納する。<BR>
     * <BR>
     * ４）ArrayList.toArray()の戻り値を返却<BR>
     * 　@　@　@　@　@取得したデータが0件場合、nullを返却<BR>
     * 　@　@　@　@　@それ以外の場合、取得した結果を返却<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@return WEB3AccInfoCfdAccountInfo[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoCfdAccountInfo[] createCFDAccountInfo(
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createCFDAccountInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //１）　@ArrayListオブジェクトの生成。
        //CFD口座情報オブジェクトを格
        List l_lisAccountInfos = new ArrayList();

        //顧客.getInstitution().getInstitutionCode()
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        //顧客.getBranch().getBranchCode()
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //顧客.getAccountCode()
        String l_strAccountCode = l_mainAccount.getAccountCode();

        //２）　@FXシステムコードの取得
        //会社別FXシステム条件テーブルを以下の条件で検索する。
        //※該当行がなかった場合は、nullを返却
        //[条件]
        //会社別FXシステム条件テーブル.証券会社コード = 顧客.getInstitution().getInstitutionCode() And
        //会社別FXシステム条件テーブル.部店コード = 顧客.getBranch().getBranchCode() And
        //FXシステム区分 = 2
        List l_lisCompFxConditions = null;
        try
        {
            String l_strCompFxConditionQuery =
                " institution_code = ? and branch_code = ? and fx_system_div = ? ";
            Object[] l_objCompFxConditionQuery =
                new Object[]{
                    l_strInstitutionCode,
                    l_strBranchCode,
                    WEB3FxSystemDivDef.CFD_SYSTEM};
            // １） QueryProcessor.doFindAllQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCompFxConditions = l_queryProcessor.doFindAllQuery(
                CompFxConditionRow.TYPE,
                l_strCompFxConditionQuery,
                l_objCompFxConditionQuery);

            if (l_lisCompFxConditions.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //３）　@２）の処理で取得した会社別FXシステム条件テーブルの要素毎に、以下の処理を行う
            for (int i = 0; i < l_lisCompFxConditions.size(); i++)
            {
                CompFxConditionRow l_compFxConditionRow =
                    (CompFxConditionRow)l_lisCompFxConditions.get(i);
                String l_strFxSystemCode = l_compFxConditionRow.getFxSystemCode();

                WEB3AccInfoCfdAccountInfo l_accInfoCfdAccountInfo =
                    new WEB3AccInfoCfdAccountInfo();
                //[条件]
                //ＦＸ顧客テーブル.証券会社コード = 顧客.getInstitution().getInstitutionCode() And
                //ＦＸ顧客テーブル.部店コード = 顧客.getBranch().getBranchCode() And
                //ＦＸ顧客テーブル.ＦＸシステムコード　@= 会社別FXシステム条件行.FXシステムコード And
                //ＦＸ顧客テーブル.顧客コード = 顧客.getAccountCode()
                String l_strQuery =
                    " institution_code = ?"
                    + " and branch_code = ?"
                    + " and fx_system_code = ?"
                    + " and account_code = ? ";

                Object[] l_objQuery =
                    new Object[]{
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strFxSystemCode,
                        l_strAccountCode};
                //＜２）でレコードが取得できた場合＞
                //３－１）ＦＸログインＩＤ取得
                //ＦＸ顧客テーブルを以下の条件で検索する。
                //※該当行がなかった場合は、
                //何もせずに次の要素へ
                FxAccountRow l_fxAccountRow =
                    FxAccountDao.findRowByInstitutionCodeBranchCodeFxSystemCodeAccountCode(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strFxSystemCode,
                        l_strAccountCode);

                if (l_fxAccountRow == null)
                {
                    continue;
                }

                //３－２）FX口座番号取得
                //ＦＸ口座番号テーブルを以下の条件で検索する。
                List l_lisFxAccountCodeRows = l_queryProcessor.doFindAllQuery(
                    FxAccountCodeRow.TYPE,
                    l_strQuery,
                    l_objQuery);

                //３－３）　@CFD口座情報オブジェクトを生成し以下の通り、プロパティをセットする。
                //CFD口座情報.CFDログインＩＤ： ＦＸ顧客行.ＦＸログインＩＤ
                l_accInfoCfdAccountInfo.cfdLoginId =
                    l_fxAccountRow.getFxLoginId() + "";

                //CFD口座情報.CFD口座番号： ＦＸ口座番号行.ＦＸ口座番号
                //※３－２）でレコードが取得できた場合のみCFD口座情報.CFD口座番号に値をセット
                if (l_lisFxAccountCodeRows.size() != 0)
                {
                    FxAccountCodeRow l_fxAccountCodeRow =
                        (FxAccountCodeRow)l_lisFxAccountCodeRows.get(0);
                    l_accInfoCfdAccountInfo.cfdAccountCode =
                        l_fxAccountCodeRow.getFxAccountCode();
                }

                //CFD口座情報.FXシステムコード：会社別FXシステム条件行.FXシステムコード
                l_accInfoCfdAccountInfo.fxSystemCode = l_strFxSystemCode;

                //３－４）　@１）で生成したListオブジェクトにCFD口座情報オブジェクトを格納する。
                l_lisAccountInfos.add(l_accInfoCfdAccountInfo);
            }
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

        //４）ArrayList.toArray()の戻り値を返却取得したデータが0件場合、nullを返却
        if (l_lisAccountInfos.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3AccInfoCfdAccountInfo[] l_accInfoCfdAccountInfo =
            new WEB3AccInfoCfdAccountInfo[l_lisAccountInfos.size()];
        l_lisAccountInfos.toArray(l_accInfoCfdAccountInfo);
        log.exiting(STR_METHOD_NAME);
        return l_accInfoCfdAccountInfo;
    }

    /**
     * (createメールアドレス情報)<BR>
     * メールアドレス情報を作成する。<BR>
     * <BR>
     * １）　@顧客メールアドレステーブルを以下の条件で検索する。<BR>
     * 　@　@　@・該当行がなかった場合は、nullを返却する。<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@　@証券会社コード：　@顧客.証券会社コード<BR>
     * 　@　@　@部店コード：　@顧客.部店コード<BR>
     * 　@　@　@口座コード：　@顧客.口座コード<BR>
     * 　@　@[ソート条件]<BR>
     * 　@　@　@メールアドレス番号<BR>
     * <BR>
     * ２)　@ArrayListオブジェクト生成<BR>
     * <BR>
     * ３）　@１）の戻り値の要素分、Loop処理を行う。<BR>
     * 　@３－１）メールアドレス情報オブジェクトを生成する。<BR>
     * 　@３－２）　@以下の通りプロパティをセット。<BR>
     * 　@　@メールアドレス情報.メールアドレス番号 = 顧客メールアドレス.メールアドレス番号<BR>
     * 　@　@メールアドレス情報.メールアドレス区分 = 顧客メールアドレス.アドレス区分<BR>
     * 　@　@メールアドレス情報.メールアドレス = 顧客メールアドレス.メールアドレス<BR>
     * 　@３－３）　@ArrayListオブジェクト.add(メールアドレス情報オブジェクト)<BR>
     * <BR>
     * ４）　@ArrayListオブジェクトをメールアドレス情報[]に変換する。<BR>
     * <BR>
     * ５）　@４）で変換したメールアドレス情報[]を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@return WEB3AccInfoMailAddressInfoUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoMailAddressInfoUnit[] createMailAddressInfo(
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMailAddressInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //１）　@ArrayListオブジェクトの生成。
        List l_lisMailAddressInfoUnits = new ArrayList();
        //顧客.getInstitution().getInstitutionCode()
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        //顧客.getBranch().getBranchCode()
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //顧客.getAccountCode()
        String l_strAccountCode = l_mainAccount.getAccountCode();
        //１）　@顧客メールアドレステーブルを以下の条件で検索する。
        String l_strQuery =
            " institution_code = ?"
            + " and branch_code = ?"
            + " and account_code = ? ";

        Object[] l_objQuery =
            new Object[]{
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisAccountMailAddressRows = l_queryProcessor.doFindAllQuery(
                AccountMailAddressRow.TYPE,
                l_strQuery,
                " email_address_number asc ",
                null,
                l_objQuery);

            if (l_lisAccountMailAddressRows.isEmpty())
            {
                //該当行がなかった場合は、nullを返却する。
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            WEB3AccInfoMailAddressInfoUnit l_mailAddressInfoUnit = null;
            for (int i = 0; i < l_lisAccountMailAddressRows.size(); i++)
            {
                AccountMailAddressRow l_accountMailAddressRow =
                    (AccountMailAddressRow)l_lisAccountMailAddressRows.get(i);
                l_mailAddressInfoUnit = new WEB3AccInfoMailAddressInfoUnit();
                //メールアドレス情報.メールアドレス = 顧客メールアドレス.メールアドレス
                l_mailAddressInfoUnit.mailAddress = l_accountMailAddressRow.getEmailAddress();
                //メールアドレス情報.メールアドレス番号 = 顧客メールアドレス.メールアドレス番号
                l_mailAddressInfoUnit.mailAddressNo = l_accountMailAddressRow.getEmailAddressNumber() + "";
                //メールアドレス情報.メールアドレス区分 = 顧客メールアドレス.アドレス区分
                l_mailAddressInfoUnit.mailAddressDiv = l_accountMailAddressRow.getAddressDiv();
                l_lisMailAddressInfoUnits.add(l_mailAddressInfoUnit);
            }
            WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoUnits =
                new WEB3AccInfoMailAddressInfoUnit[l_lisMailAddressInfoUnits.size()];
            l_lisMailAddressInfoUnits.toArray(l_mailAddressInfoUnits);

            log.exiting(STR_METHOD_NAME);
            return l_mailAddressInfoUnits;
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
    }

    /**
     * (createメール種別情報)<BR>
     * メール種別情報を作成する。<BR>
     * <BR>
     * １）　@メール種別テーブルを以下の条件で検索する。<BR>
     * 　@　@・該当行がなかった場合は、nullを返却する。<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@　@証券会社コード：　@顧客.証券会社コード<BR>
     * 　@　@　@部店コード：　@顧客.部店コード<BR>
     * 　@　@　@口座コード：　@顧客.口座コード<BR>
     * <BR>
     * 　@　@[ソート条件]<BR>
     * 　@　@　@メール種別区分<BR>
     * <BR>
     * ２）　@ArrayListオブジェクト生成<BR>
     * <BR>
     * ３）　@１）の戻り値の要素分、Loop処理を行う。<BR>
     * 　@３－１）メール種別情報オブジェクトを生成する。<BR>
     * 　@３－２）以下の通りプロパティをセット。<BR>
     * 　@　@メール種別情報.メールアドレス番号 = メール種別.メールアドレス番号<BR>
     * 　@　@メール種別情報.メール種別番号 = メール種別.メール種別番号<BR>
     * 　@３－３）ArrayListオブジェクト.add(お客様情報口座情報オブジェクト)<BR>
     * <BR>
     * ４）　@ArrayListオブジェクトをメール種別情報[]に変換する。<BR>
     * <BR>
     * ５）　@４）で変換したメール種別情報[]を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@return WEB3AccInfoMailAddressTypeUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoMailAddressTypeUnit[] createMailAddressTypeInfo(
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMailAddressInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //１）　@ArrayListオブジェクトの生成。
        List l_lisMailAddressTypeUnits = new ArrayList();
        //顧客.getInstitution().getInstitutionCode()
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        //顧客.getBranch().getBranchCode()
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //顧客.getAccountCode()
        String l_strAccountCode = l_mainAccount.getAccountCode();
        //１）　@顧客メールアドレステーブルを以下の条件で検索する。
        String l_strQuery =
            " institution_code = ?"
            + " and branch_code = ?"
            + " and account_code = ? ";

        Object[] l_objQuery =
            new Object[]{
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisMailAssortmentRows = l_queryProcessor.doFindAllQuery(
                MailAssortmentRow.TYPE,
                l_strQuery,
                " mail_assortment_div asc ",
                null,
                l_objQuery);

            if (l_lisMailAssortmentRows.isEmpty())
            {
                //該当行がなかった場合は、nullを返却する。
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            WEB3AccInfoMailAddressTypeUnit l_mailAddressTypeUnit = null;
            for (int i = 0; i < l_lisMailAssortmentRows.size(); i++)
            {
                MailAssortmentRow l_accountMailAddressRow =
                    (MailAssortmentRow)l_lisMailAssortmentRows.get(i);
                l_mailAddressTypeUnit = new WEB3AccInfoMailAddressTypeUnit();
                //メール種別情報.メールアドレス番号 = メール種別.メールアドレス番号
                l_mailAddressTypeUnit.mailAddressNo = l_accountMailAddressRow.getEmailAddressNumber() + "";
                //メール種別情報.メール種別番号 = メール種別.メール種別番号
                l_mailAddressTypeUnit.mailAddressTypeNo = l_accountMailAddressRow.getMailAssortmentDiv();
                l_lisMailAddressTypeUnits.add(l_mailAddressTypeUnit);
            }
            WEB3AccInfoMailAddressTypeUnit[] l_mailAddressTypeUnits =
                new WEB3AccInfoMailAddressTypeUnit[l_lisMailAddressTypeUnits.size()];
            l_lisMailAddressTypeUnits.toArray(l_mailAddressTypeUnits);

            log.exiting(STR_METHOD_NAME);
            return l_mailAddressTypeUnits;
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
    }
}
@
