head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyApplyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付新規申込サービスImpl(WEB3MutualFixedBuyApplyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 肖志偉 (中訊) 新規作成
                 : 2006/07/27 徐宏偉 (中訊) 仕様変更 モデルNo.466
                 : 2006/08/10 山下　@（SRA）受入テスト障害 No.U02875        
Revesion History : 2007/10/25 孫洪江 (中訊) 仕様変更 モデルNo.584
Revesion History : 2007/10/30 趙林鵬 (中訊) 仕様変更 モデルNo.586
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFixedBuyCommonService;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyConfirmRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyInputRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyInputResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionUnit;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyApplyService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信定時定額買付新規申込サービス実装クラス<BR>
 * <BR>
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3MutualFixedBuyApplyServiceImpl 
    extends WEB3MutualClientRequestService 
        implements WEB3MutualFixedBuyApplyService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyApplyServiceImpl.class);

    /**
     * 投信定時定額買付新規申込サービス処理を実施する。<BR> 
     * <BR>
     * リクエストデータの型により、 <BR>
     * input定時定額買付新規申込()、validate定時定額買付新規申込() <BR>
     * いずれかのメソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        //input定時定額買付新規申込()の場合
        if (l_request instanceof WEB3MutualFixedBuyApplyInputRequest)
        {
            l_response = this.inputFixedBuyApply((WEB3MutualFixedBuyApplyInputRequest)l_request);
        }
        
        //validate定時定額買付新規申込()の場合
        else if (l_request instanceof WEB3MutualFixedBuyApplyConfirmRequest)
        {
            l_response = this.validateFixedBuyApply((WEB3MutualFixedBuyApplyConfirmRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
       
        log.exiting(STR_METHOD_NAME);
        return l_response;
     }
    
    /**
     * (input定時定額買付新規申込)<BR>
     * 投資信託定時定額買付新規申込入力を行う。<BR> 
     * <BR>
     * シーケンス図 <BR>
     * 「(投信)定時定額買付新規申込」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3MutualFixedBuyApplyInputResponse
     * @@throws WEB3BaseException 
     */
    protected WEB3MutualFixedBuyApplyInputResponse inputFixedBuyApply(
        WEB3MutualFixedBuyApplyInputRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "inputFixedBuyApply(WEB3MutualFixedBuyApplyInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1)validate注文受付可能()
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        
        //1.2) get補助口座()
        SubAccount l_subAccount = this.getSubAccount();       
        
        //1.3.getCommonOrderValidator( )
        //注文チェックオブジェクトを取得する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //1.4.get発注日( )        
        //"投信定時定額買付"に対する発注日を取得する。
        Date l_datOrderBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("l_datOrderBizDate = " + l_datOrderBizDate);
        
        //1.5.validate取引可能顧客(顧客 : 顧客, 発注日 : Timestamp)
        //[validate取引可能顧客に渡す引数]  
        //　@顧客：取得した補助口座.getMainAccount()  
        //　@発注日：取引時間管理.get投信発注日()の戻り値
        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
	    OrderValidationResult l_validationResult =
	        l_orderValidator.validateAccountForTrading(
	            l_genMainAccount,  
	            new Timestamp(l_datOrderBizDate.getTime()));
	            
		if (l_validationResult.getProcessingResult().isFailedResult())
		{
			log.debug("定時定額買付取引停止顧客エラー");
			throw new WEB3BaseException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02524,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"定時定額買付取引停止顧客エラー");
		}	                
        
        //1.6.getMainAccount()
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //1.7) get投信銘柄カテゴリーリスト()
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        List l_lisProductCategory = 
            l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);
        
        //1.8)＜分岐処理＞get投信銘柄カテゴリーリスト()の戻り値 > 0件の場合
        WEB3MutualProductCategoryUnit[] l_productCategoryUnits = null;
        if (l_lisProductCategory != null && l_lisProductCategory.size() > 0)
        {
            //1.8.1) create投信銘柄カテゴリー一覧(List)
            l_productCategoryUnits = 
                l_mfProductManager.createMutualFundProductCategoryList(l_lisProductCategory);
        }
        
        //1.9.)get定時定額買付可能銘柄リスト()
        List lisBuyPosProduct = 
            l_mfProductManager.getFixedBuyPossibleProductList(l_strInstitutionCode);
        
        //1.10.)get定時定額買付可能銘柄リスト()の戻り値のList要素数分LOOP
        List l_lisUnits = new ArrayList();
        if (lisBuyPosProduct != null && lisBuyPosProduct.size() > 0)
        {
            int l_intLength = lisBuyPosProduct.size();
            for (int i = 0; i < l_intLength; i++)
            {
                //1.10.1)投信定時定額買付条件行
                WEB3MutualFixedBuyConditionUnit l_unit = 
                    new WEB3MutualFixedBuyConditionUnit();
                
                //銘柄コード = 拡張投信銘柄.getProductCode
                WEB3MutualFundProduct l_fundProduct = 
                    (WEB3MutualFundProduct)lisBuyPosProduct.get(i);
                MutualFundProductRow l_row = 
                    (MutualFundProductRow)l_fundProduct.getDataSourceObject();
                
                l_unit.mutualProductCode = l_row.getProductCode();
                
                //銘柄名 = 拡張投信銘柄.get銘柄名
                l_unit.mutualProductName = l_row.getStandardName();
                
                //投信銘柄カテゴリーコード = 拡張投信銘柄.getカテゴリーコード
                l_unit.categoryCode = l_row.getCategoryCode();
                
                //※上記以外の項目にはnullをセットする。
                l_unit.displayOrder = null;
                l_unit.increaseBuyAmount = null;
                l_unit.monthlyBuyAmount = null;
                l_unit.validStartDate = null;
                
                l_lisUnits.add(l_unit);
            }
        }
        
        WEB3MutualFixedBuyConditionUnit[] l_units = 
            new WEB3MutualFixedBuyConditionUnit[l_lisUnits.size()];
        l_lisUnits.toArray(l_units);
        
        //createレスポンス
        WEB3MutualFixedBuyApplyInputResponse l_response = 
            (WEB3MutualFixedBuyApplyInputResponse)l_request.createResponse();
        
        //プロパティセット
        //顧客コード = 取得した顧客.get表示顧客コード（）
		WEB3GentradeMainAccount l_gentradeMainAccount = null;                    
		try                                                                      
		{                                                                        
		 l_gentradeMainAccount =                                                 
		  (WEB3GentradeMainAccount) l_finApp.getAccountManager().getMainAccount(l_mainAccount.getAccountId());                                  
		}                                                                        
		catch (NotFoundException l_ex)                                           
		{                                                                        
		 log.error("getMainAccount not found");                                  
		 log.exiting(STR_METHOD_NAME);                                           
		 throw new WEB3SystemLayerException(                                     
		  WEB3ErrorCatalog.SYSTEM_ERROR_80005,                                   
		  this.getClass().getName() + "." + STR_METHOD_NAME,                     
		  "getMainAccount not found",l_ex);                                                                  
		}                                                                        
                                                                            
		l_response.accountCode = l_gentradeMainAccount.getDisplayAccountCode();                                                                       
                                                                                    
        //顧客名（カナ） = 取得した顧客.名前（苗字１）
        l_response.accountNameKana = l_mainAccountRow.getFamilyNameAlt1();
        
        //顧客名（漢字） = 取得した顧客.名前（苗字）
        l_response.accountName = l_mainAccountRow.getFamilyName();
        
        //郵便番号 = 取得した顧客.郵便番号
        l_response.zipCode = l_mainAccountRow.getZipCode();
        
        //住所１ = 取得した顧客.住所１
        l_response.address1 = l_mainAccountRow.getAddressLine1();
        
        //住所２ = 取得した顧客.住所２
        l_response.address2 = l_mainAccountRow.getAddressLine2();
        
        //住所３ = 取得した顧客.住所３
        l_response.address3 = l_mainAccountRow.getAddressLine3();
        
        //投信定時定額買付条件一覧 = 投信定時定額買付条件行の配列
        l_response.conditionList = l_units;
        
        //投信銘柄カテゴリー一覧 = create投信銘柄カテゴリー一覧()の戻り値
        l_response.categoryList = l_productCategoryUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate定時定額買付新規申込)<BR>
     * 投資信託定時定額買付新規申込確認を行う。<BR> 
     * <BR>
     * シーケンス図 <BR>
     * 「(投信)定時定額買付新規申込確認」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「(投信)定時定額買付新規申込審査」: <BR>
     * 　@　@　@　@1.4.3)＜分岐処理＞is定時定額買付可能()の戻り値 == false の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     *  　@　@　@　@tag:   BUSINESS_ERROR_02480<BR>
     *  ========================================================<BR>
     *  <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3MutualFixedBuyApplyConfirmResponse
     * @@throws WEB3BaseException 
     */
    protected WEB3MutualFixedBuyApplyConfirmResponse validateFixedBuyApply(
        WEB3MutualFixedBuyApplyConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateFixedBuyApply(WEB3MutualFixedBuyApplyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1)validate()
        l_request.validate();
        
        //1.2)validate注文受付可能()
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        
        //1.3) get補助口座()
        SubAccount l_subAccount = this.getSubAccount();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        
        WEB3MutualFixedBuyCommonService l_service = 
            (WEB3MutualFixedBuyCommonService) Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        
        //1.4.getCommonOrderValidator( )
        //注文チェックオブジェクトを取得する。
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //1.5.get発注日( )        
        //"投信定時定額買付"に対する発注日を取得する。
        Date l_datOrderBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("l_datOrderBizDate = " + l_datOrderBizDate);
        
        //1.6.validate取引可能顧客(顧客 : 顧客, 発注日 : Timestamp)
        //[validate取引可能顧客に渡す引数]  
        //　@顧客：取得した補助口座.getMainAccount()  
        //　@発注日：取引時間管理.get投信発注日()の戻り値
        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        OrderValidationResult l_validationResult =
            l_orderValidator.validateAccountForTrading(
                l_genMainAccount,  
                new Timestamp(l_datOrderBizDate.getTime()));
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引停止顧客エラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引停止顧客エラー");
        }   
        
        //値の初期化
        //work用の電子鳩障害中フラグにfalseをセット。
        boolean l_blnBatoTroubleFlag = false;

        boolean l_blnForeignSecAccOpenFlag = false;

        //投信定時定額買付共通情報一覧.電子鳩チェックフラグ
        boolean l_blnBatoCheckFlag = false;

        //1.7)リクエストデータ.投信定時定額買付共通情報一覧の要素数分LOOP
        int l_intLength = l_request.commonList.length;

        List l_lisNoReadProductCodes = new ArrayList();

        List l_lisCheckResults = new ArrayList();

        String[] l_strNoReadProductCodes = null;

        WEB3GentradeBatoClientService l_batoService =
            (WEB3GentradeBatoClientService)Services.getService(
                WEB3GentradeBatoClientService.class);

        WEB3GentradeProspectusResult l_validateBataResult = null;

        for (int i = 0; i < l_intLength; i++)
        {
            //1.7.1)get投信銘柄()
            WEB3MutualFundProduct l_mfProduct = null;
            try
            {
                l_mfProduct = 
                    (WEB3MutualFundProduct)l_mfProductManager.getMutualFundProduct(
                        l_subAccount.getInstitution(),
                        l_request.commonList[i].mutualProductCode);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("証券会社、銘柄コードに対応する投信銘柄データが存在しません");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "",
                    l_nfe);
            }
            
            //1.7.2)is定時定額買付可能()
            boolean l_blnFixedBuyPos = l_mfProduct.isFixedBuyPossible();
            
            //1.7.3)＜分岐処理＞is定時定額買付可能()の戻り値 == false の場合、
            if (!l_blnFixedBuyPos)
            {
                log.debug("定時定額買付不可銘柄エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02480,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.commonList[i].mutualProductCode);
            }
            
            //1.7.4) validate外国証券口座開設()
            if (!l_blnForeignSecAccOpenFlag)
            {
                l_blnForeignSecAccOpenFlag = 
                    l_service.validateForeignSecAccOpen(l_subAccount, l_mfProduct);
            }
            
            //1.7.5)validate定時定額買付金額()
            try 
            {
                l_service.validateFixedBuyAmount(
                    l_subAccount,
                    l_request.commonList[i].monthlyBuyAmount,
                    l_request.commonList[i].increaseBuyAmount);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("定時定額買付最低金額チェック、単位金額チェックを行なう。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.commonList[i].mutualProductCode);
            }

            //＜分岐処理＞リクエスト.投信定時定額買付共通情報一覧.電子鳩チェックフラグ ==
            //trueかつwork用の電子鳩障害中フラグがfalseの場合
            if (l_request.commonList[i].batoCheckFlag
                && !l_blnBatoTroubleFlag)
            {
                //validate目論見書閲覧(種別コード : String, 銘柄コード : String)
                //目論見書閲覧のチェックを行なう。
                //[validate目論見書閲覧に渡すパラメタ]
                //    種別コード：リクエスト.投信定時定額買付共通情報一覧.種別コード
                //    銘柄コード：リクエスト.投信定時定額買付共通情報一覧.銘柄コード
                l_validateBataResult = l_batoService.validateProspectus(
                    l_request.commonList[i].typeCode,
                    l_request.commonList[i].mutualProductCode);

                l_lisCheckResults.add(l_validateBataResult.checkResult);

                //＜分岐処理＞validate目論見書閲覧()の戻り値が「1:閲覧未済」の場合
                if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(l_validateBataResult.checkResult))
                {
                    //work用の目論見書閲覧未済銘柄コード一覧にリクエスト.
                    //投信定時定額買付共通情報一覧.銘柄コードを追加する。
                    l_lisNoReadProductCodes.add(l_request.commonList[i].mutualProductCode);
                }

                //＜分岐処理＞validate目論見書閲覧()の戻り値が「2:閲覧未済(障害中)」の場合
                if (WEB3GentradeBatoCheckResultDef.UNINSPECTION_TROUBLE.equals(l_validateBataResult.checkResult))
                {
                    //work用の電子鳩障害中フラグにtrueをセット。
                    l_blnBatoTroubleFlag = true;
                }

                //1件でもリクエスト.投信定時定額買付共通情報一覧.電子鳩チェックフラグがtrueの場合
                l_blnBatoCheckFlag = true;
            }
        }
        
        //1.8)createレスポンス()
        WEB3MutualFixedBuyApplyConfirmResponse l_response = 
            (WEB3MutualFixedBuyApplyConfirmResponse)l_request.createResponse();
        
        //1.9)プロパティセット
        //外国証券口座開設要フラグ 
        l_response.foreignSecAccOpenFlag = l_blnForeignSecAccOpenFlag;

        //電子鳩チェック結果 = 全てのリクエスト.投信定時定額買付共通情報一覧.電子鳩チェックフラグがfalseの場合、
        //｢0:閲覧済｣をセット
        //1件でもリクエスト.投信定時定額買付共通情報一覧.電子鳩チェックフラグがtrueの場合
        //work用の電子鳩障害中フラグがtrueの場合、｢2:閲覧未済(障害中)｣をセット。
        //                    work用の電子鳩障害中フラグがfalseの場合validate目論見書閲覧()
        //の戻り値が全て｢0:閲覧済｣の場合、｢0:閲覧済｣をセット。1件でも戻り値が｢1:閲覧未済｣があれば、｢1:閲覧未済｣をセット。
        if (!l_blnBatoCheckFlag)
        {
            l_response.batoCheckResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
        }
        else if (l_blnBatoTroubleFlag)
        {
            l_response.batoCheckResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION_TROUBLE;
        }
        else if (l_lisCheckResults.contains(WEB3GentradeBatoCheckResultDef.UNINSPECTION))
        {
            l_response.batoCheckResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION;
        }
        else
        {
            l_response.batoCheckResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
        }

        //work用の目論見書閲覧未済銘柄コード一覧が1件もない場合、nullをセット。
        if (l_lisNoReadProductCodes.size() == 0)
        {
            l_response.noReadProductCodeList = null;
        }
        //目論見書閲覧未済銘柄コード一覧 = work用の目論見書閲覧未済銘柄コード一覧をセット。
        else
        {
            l_strNoReadProductCodes = new String[l_lisNoReadProductCodes.size()];

            l_response.noReadProductCodeList =
                (String[])l_lisNoReadProductCodes.toArray(l_strNoReadProductCodes);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
