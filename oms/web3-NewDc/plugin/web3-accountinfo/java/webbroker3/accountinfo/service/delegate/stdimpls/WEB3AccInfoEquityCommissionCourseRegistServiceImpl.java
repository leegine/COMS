head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報株式委託手数料コース変更申込サービスImpl(WEB3AccInfoEquityCommissionCourseRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 彭巍 (中訊) 新規作成
Revesion History : 2006/06/30 黄建（中訊）　@仕様変更・モデル113
Revesion History : 2006/07/29 山田(SCS) 仕様変更・モデル120
Revesion History : 2007/02/13 謝旋（中訊）　@仕様変更・モデル193
Revesion History : 2007/02/22 大木(SCS) 仕様変更・モデル199
Revesion History : 2007/03/02 吉麗ナ(中訊) FTBugを対応
Revesion History : 2008/08/18 楊夫志(中訊) 仕様変更・モデル240,244
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseMaster;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.data.CommAccountSendDao;
import webbroker3.accountinfo.data.CommAccountSendRow;
import webbroker3.accountinfo.data.CommissionCourseMasterRow;
import webbroker3.accountinfo.data.CommissionCourseRegistParams;
import webbroker3.accountinfo.data.CommissionCourseRegistRow;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommissionCourseRegistInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoEquityCommissionCourseRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccInfoCommissionDivDef;
import webbroker3.common.define.WEB3AccountinfoCommisionDivCheckDef;
import webbroker3.common.define.WEB3AppliStartDateDivDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CommCodeChgMstRow;
import webbroker3.gentrade.data.EquityCommAccountCondMstParams;
import webbroker3.gentrade.data.EquityCommAccountCondMstRow;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (お客様情報株式委託手数料コース変更申込サービスImpl)<BR>
 * お客様情報株式委託手数料コース変更申込サービス実装クラス<BR>
 * @@author　@彭巍
 * @@version 1.0
 */
public class WEB3AccInfoEquityCommissionCourseRegistServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AccInfoEquityCommissionCourseRegistService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class);
    
    /**
     * @@roseuid 418F39FF032C
     */
    public WEB3AccInfoEquityCommissionCourseRegistServiceImpl() 
    {
     
    }
    
    /**
     * 株式委託手数料コース変更申込処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、お客様情報株式委託手数料コース<BR>
     * 変更申込入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * ○ 引数のリクエストデータが、お客様情報株式委託手数料コース<BR>
     * 変更申込確認リクエストの場合 <BR>
     * 　@－validate変更申込()をコールする。<BR> 
     * ○ 引数のリクエストデータが、お客様情報株式委託手数料コース<BR>
     * 変更申込完了リクエストの場合 <BR>
     * 　@－submit変更申込()をコールする。 <BR>
     * ○ 引数のリクエストデータが、お客様情報株式委託手数料コース<BR>
     * 変更申込取消確認リクエストの場合 <BR>
     * 　@－validate申込取消()をコールする。 <BR>
     * ○ 引数のリクエストデータが、お客様情報株式委託手数料コース<BR>
     * 変更申込取消完了リクエストの場合 <BR>
     * 　@－submit申込取消()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413D5A83020D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //引数のリクエストデータが、お客様情報株式委託手数料コース変更申込入力リクエストデータの場合
        if(l_request instanceof WEB3AccInfoEquityCommissionCourseChangeInputRequest)
        {  
            l_response = this.getInputScreen((WEB3AccInfoEquityCommissionCourseChangeInputRequest)l_request);
        }
        //引数のリクエストデータが、お客様情報株式委託手数料コース変更申込確認リクエストの場合
        else if(l_request instanceof WEB3AccInfoEquityCommissionCourseChangeConfirmRequest)
        {
            l_response = this.validateRegist((WEB3AccInfoEquityCommissionCourseChangeConfirmRequest)l_request);
        }
        //引数のリクエストデータが、お客様情報株式委託手数料コース変更申込完了リクエストデータの場合
        else if(l_request instanceof WEB3AccInfoEquityCommissionCourseChangeCompleteRequest)
        {
            l_response = this.submitRegist((WEB3AccInfoEquityCommissionCourseChangeCompleteRequest)l_request);
        }
        //引数のリクエストデータが、お客様情報株式委託手数料コース変更申込取消確認リクエストデータの場合
        else if(l_request instanceof WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest)
        {
            l_response = this.validateRegistCancel((WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest)l_request);
        }
        
        //引数のリクエストデータが、お客様情報株式委託手数料コース変更申込取消完了リクエストデータの場合
        else if(l_request instanceof WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest)
        {
            l_response = this.submitRegistCancel((WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (get入力画面)<BR>
     * 株式委託手数料コース変更申込入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（株式委託手数料ｺｰｽ変更申込）get入力画面」参照。 <BR>
     * @@param l_request - お客様情報株式委託手数料コース変更申込入力リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputResponse
     * @@roseuid 413D5B3D00F3
     */
    protected WEB3AccInfoEquityCommissionCourseChangeInputResponse getInputScreen(WEB3AccInfoEquityCommissionCourseChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AccInfoEquityCommissionCourseChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //get顧客( )     
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) this.getMainAccount();
        
        //get委託手数料コース変更申込(顧客, String)
        WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegist =
            WEB3AccInfoCommissionCourseRegist.getCommissionCourseRegist(
                l_mainAccount, 
                WEB3CommisionProductCodeDef.LISTING_STOCK);
                                
        //(*1) 変更申込がある場合（get委託手数料コース() != null）
        WEB3AccInfoCommissionCourseChangeInfo[] l_commissionCourseRegistInfo = null;
        if (l_commissionCourseRegist != null)
        {
            //create手数料コース変更申込情報(委託手数料コース変更申込[])

            WEB3AccInfoCommissionCourseRegistInfoCreatedService 
                l_accInfoCommissionCourseRegistInfoCreatedService = 
                    (WEB3AccInfoCommissionCourseRegistInfoCreatedService) Services.getService(
                    WEB3AccInfoCommissionCourseRegistInfoCreatedService.class);
                    
            l_commissionCourseRegistInfo = 
                l_accInfoCommissionCourseRegistInfoCreatedService.createCommissionCourseRegistInfo(
                    l_commissionCourseRegist);
        }

        //（*2）以下の条件で証券プリファ@レンステーブルを検索する。
        //[条件]
        //証券会社ID：get顧客.getInstitution().getInstitutionId()
        //プリファ@レンス名：accountinfo.commision.div.check
        //プリファ@レンス値：1
        List l_lisRecords = new ArrayList();
        try
        {
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" institution_id = ? ");
            l_strWhere.append(" and name = ? ");
            l_strWhere.append(" and value = ? ");

            Object[] l_queryDataContainer = new Object[] {
                l_mainAccount.getInstitution().getInstitutionId() + "",
                WEB3InstitutionPreferencesNameDef.ACCOUNTINFO_COMMISION_DIV_CHECK,
                WEB3AccountinfoCommisionDivCheckDef.CHECK};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                InstitutionPreferencesRow.TYPE,
                l_strWhere.toString(),
                l_queryDataContainer
                );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intRecordsCnt = l_lisRecords.size();

        //（*2）でレコードが取得できた場合
        WEB3AccInfoCommissionCourseMaster[] l_accInfoCommissionCourseMaster = null;
        if (l_intRecordsCnt != 0)
        {
            //get取扱可能委託手数料コース(String, String, boolean)
            //証券会社が取り扱っている手数料コースマスタオブジェクトを取得する。
            //[引数]
            //証券会社コード = get顧客.getInstitution().getInstitutionCode()
            //手数料商品コード = "上場株式"
            //信用口座開設フラグ = get顧客.is信用口座開設()
            //※is信用口座開設()の引数
            //弁済区分 = "指定なし"
            l_accInfoCommissionCourseMaster = 
                WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    WEB3CommisionProductCodeDef.LISTING_STOCK,
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT));
        }
        //（*2）でレコードが取得できなかった場合
        else
        {
            //get取扱可能委託手数料コース(証券会社コード : String, 手数料商品コード : String)
            l_accInfoCommissionCourseMaster =
                WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    WEB3CommisionProductCodeDef.LISTING_STOCK);
        }

        int l_intSize = 0;
        if (l_accInfoCommissionCourseMaster != null)  
        {
            l_intSize = l_accInfoCommissionCourseMaster.length;
        }
               
        WEB3AccInfoEquityCommissionCourseChangeInputResponse l_response = 
            (WEB3AccInfoEquityCommissionCourseChangeInputResponse) l_request.createResponse();
                    
        String l_beforeCommCourse = "";
        if (l_commissionCourseRegistInfo != null)
        {
            l_beforeCommCourse =
            l_commissionCourseRegistInfo[l_commissionCourseRegistInfo.length - 1].commissionCourse;
        } else
        {
            l_beforeCommCourse = l_request.beforCommissionCourse;
        }

        List l_lisCommissionCourseMaster = new Vector();

        for (int i = 0; i < l_intSize; i++)
        {
            CommissionCourseMasterRow l_commissionCourseMasterRow = 
                (CommissionCourseMasterRow)l_accInfoCommissionCourseMaster[i].getDataSourceObject(); 
            if (!l_beforeCommCourse.equals(l_commissionCourseMasterRow.getCommissionCourseDiv()))
            {
                l_lisCommissionCourseMaster.add(l_accInfoCommissionCourseMaster[i]);
            }
        }        
        int l_intMasterRowsSize = l_lisCommissionCourseMaster.size();
        
        l_response.changeAbleCommissionCourseList = new String[l_intMasterRowsSize]; 
        l_response.applyStartDateList = new Date[l_intMasterRowsSize]; 
        l_response.changeOfferDeadlineTimeList = new String[l_intMasterRowsSize]; 
        l_response.changeOfferDeadlineDateList = new String[l_intMasterRowsSize];
        l_response.changeApplyStartDateDivList = new String[l_intMasterRowsSize];    
        for (int i = 0; i < l_intMasterRowsSize; i++)
        {   
            CommissionCourseMasterRow l_commissionCourseMasterRow = 
                (CommissionCourseMasterRow)((WEB3AccInfoCommissionCourseMaster)l_lisCommissionCourseMaster.get(i)).getDataSourceObject();      
            l_response.changeAbleCommissionCourseList[i] = l_commissionCourseMasterRow.getCommissionCourseDiv();
            l_response.applyStartDateList[i] = ((WEB3AccInfoCommissionCourseMaster)l_lisCommissionCourseMaster.get(i)).getAppliStartTimestamp();
            l_response.changeOfferDeadlineDateList[i] = l_commissionCourseMasterRow.getRegistEndDaySpec();
            l_response.changeOfferDeadlineTimeList[i] = l_commissionCourseMasterRow.getRegistEndTime();
            // ※注2）　@取扱可能委託手数料コースより編集する一覧データについて
            // データ値が”9：日数指定”の場合、”9：1”のように、データ値に続けて”：”＋日数を追加で編集する。
            // 日数は、get取扱可能委託手数料コース()戻り値の対象要素.委託手数料コースマスタ行.変更適用開始日数
            if (WEB3AppliStartDateDivDef.DAYS_DESIGNATED.equals(l_commissionCourseMasterRow.getAppliStartDateDiv()))
            {
                l_response.changeApplyStartDateDivList[i] = WEB3AppliStartDateDivDef.DAYS_DESIGNATED + ":" + l_commissionCourseMasterRow.getAppliStartDayCount();
            }
            else
            {
                l_response.changeApplyStartDateDivList[i] = l_commissionCourseMasterRow.getAppliStartDateDiv(); 
            }
           
        }      
        l_response.commissionCourceChangeList = l_commissionCourseRegistInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate変更申込)<BR>
     * 株式委託手数料コース変更申込確認処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（株式委託手数料ｺｰｽ変更申込）validate変更申込」参照。 <BR>
     * @@param l_request - お客様情報株式委託手数料コース変更申込確認リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmResponse
     * @@roseuid 413D5BA60132
     */
    protected WEB3AccInfoEquityCommissionCourseChangeConfirmResponse validateRegist(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateRegist(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate( )
        l_request.validate();
        
        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //get顧客( )     
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) this.getMainAccount();
         
        WEB3AccInfoCommissionCourseMaster l_accInfoCommissionCourseMasternew =
            new WEB3AccInfoCommissionCourseMaster(
                l_mainAccount.getInstitution().getInstitutionCode(),
                WEB3CommisionProductCodeDef.LISTING_STOCK,
                l_request.commissionCourse);

        //（*1）以下の条件で証券プリファ@レンステーブルを検索する。
        //[条件]
        //証券会社ID：get顧客.getInstitution().getInstitutionId()
        //プリファ@レンス名：accountinfo.commision.div.check
        //プリファ@レンス値：1
        List l_lisRecords = new ArrayList();
        try
        {
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" institution_id = ? ");
            l_strWhere.append(" and name = ? ");
            l_strWhere.append(" and value = ? ");

            Object[] l_queryDataContainer = new Object[] {
                l_mainAccount.getInstitution().getInstitutionId() + "",
                WEB3InstitutionPreferencesNameDef.ACCOUNTINFO_COMMISION_DIV_CHECK,
                WEB3AccountinfoCommisionDivCheckDef.CHECK};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                InstitutionPreferencesRow.TYPE,
                l_strWhere.toString(),
                l_queryDataContainer
                );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intRecordsCnt = l_lisRecords.size();
        //（*2）でレコードが取得できた場合
        if (l_intRecordsCnt != 0)
        {
            //validate取扱可能委託手数料コース(boolean, String)
            //当該顧客の口座開設状況により、取扱可能な委託手数料コースであるかをチェックする。
            //[引数]
            //手数料区分 = 委託手数料コースマスタ行.get手数料区分()
            //信用口座開設フラグ = get顧客.is信用口座開設()
            //※is信用口座開設()の引数
            //弁済区分 = "指定なし"
            this.validateHandlingPossibleCommCourse(
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT),
                l_accInfoCommissionCourseMasternew.getCommissionDiv());
        }

        WEB3AccInfoEquityCommissionCourseChangeConfirmResponse l_response = 
            (WEB3AccInfoEquityCommissionCourseChangeConfirmResponse) l_request.createResponse();
            
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit変更申込)<BR>
     * 株式委託手数料コース変更申込完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（株式委託手数料ｺｰｽ変更申込）submit変更申込」参照。 <BR>
     * @@param l_request - お客様情報株式委託手数料コース変更申込完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteResponse
     * @@roseuid 413D5BD80299
     */
    protected WEB3AccInfoEquityCommissionCourseChangeCompleteResponse submitRegist(WEB3AccInfoEquityCommissionCourseChangeCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitRegist(WEB3AccInfoEquityCommissionCourseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();
        
        //1.2 validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //get顧客( )     
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) this.getMainAccount();
        
        //getCommonOrderValidator( )
        //1.4getCommonOrderValidator( )
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //1.5validate取引パスワード()
        OrderValidationResult l_validationResult = l_gentradeOrderValidator.validateTradingPassword(
            getTrader(),
            getSubAccount(), 
            l_request.password
            );
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("チェックエラーの場合はを例外をスローする");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AccInfoCommissionCourseMaster l_accInfoCommissionCourseMaster = 
            new WEB3AccInfoCommissionCourseMaster(
                l_mainAccount.getInstitution().getInstitutionCode(),
                WEB3CommisionProductCodeDef.LISTING_STOCK,
                l_request.commissionCourse);

        //（*1）以下の条件で証券プリファ@レンステーブルを検索する。
        //[条件]
        //証券会社ID：get顧客.getInstitution().getInstitutionId()
        //プリファ@レンス名：accountinfo.commision.div.check
        //プリファ@レンス値：1
        List l_lisRecords = new ArrayList();
        try
        {
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" institution_id = ? ");
            l_strWhere.append(" and name = ? ");
            l_strWhere.append(" and value = ? ");

            Object[] l_queryDataContainer = new Object[] {
                l_mainAccount.getInstitution().getInstitutionId() + "",
                WEB3InstitutionPreferencesNameDef.ACCOUNTINFO_COMMISION_DIV_CHECK,
                WEB3AccountinfoCommisionDivCheckDef.CHECK};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                InstitutionPreferencesRow.TYPE,
                l_strWhere.toString(),
                l_queryDataContainer
                );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoEquityCommissionCourseRegistServiceImpl.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intRecordsCnt = l_lisRecords.size();
        //（*2）でレコードが取得できた場合
        if (l_intRecordsCnt != 0)
        {
            //validate取扱可能委託手数料コース(boolean, String)
            //当該顧客の口座開設状況により、取扱可能な委託手数料コースであるかをチェックする。
            //[引数]
            //手数料区分 = 委託手数料コースマスタ行.get手数料区分()
            //信用口座開設フラグ = get顧客.is信用口座開設()
            //※is信用口座開設()の引数
            //弁済区分 = "指定なし"
            this.validateHandlingPossibleCommCourse(
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT),
                l_accInfoCommissionCourseMaster.getCommissionDiv());
        }

        //1.8 createNew委託手数料コース変更申込
        WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist =
            WEB3AccInfoCommissionCourseRegist.createNewCommissionCourseRegist(
                l_mainAccount,
                l_accInfoCommissionCourseMaster);

        CommissionCourseRegistRow l_commissionCourseRegistRow = 
            (CommissionCourseRegistRow)l_commissionCourseRegist.getDataSourceObject();
     
       //1.10 doInsertQuery(Row)
        QueryProcessor l_processor;
        try
        {
            l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_commissionCourseRegistRow);
        } 

        catch (DataFindException l_ex) 
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
        catch (DataQueryException l_ex) 
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataNetworkException l_ex) 
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }     
            
    //1.11 is手数料No( )
    boolean l_blnIsCommissionNo = 
      this.isCommissionNo(l_commissionCourseRegist, l_mainAccount);

    //1.12 (*1)is手数料No()の戻り値 == trueの場合
    if (l_blnIsCommissionNo)
    {
          //1.13 get手数料No(委託手数料コース変更申込)
          //[get手数料No()に指定する引数] 
          // 委託手数料コース変更申込：　@生成した委託手数料コース変更申込
          String l_strCommissionNo = 
              this.getCommissionNo(l_commissionCourseRegist);
          
          //1.14 save手数料No(String, 委託手数料コース変更申込, 顧客)
          //[save手数料No()に指定する引数] 
          // 手数料No：　@get登録No()の戻り値 
          // 委託手数料コース変更申込：　@生成した委託手数料コース変更申込 
          // 顧客：　@get顧客()の戻り値
          this.saveCommissionNo(
              l_strCommissionNo, 
              l_commissionCourseRegist, 
              l_mainAccount);
    }
        
        //1.15 createResponse( )
        WEB3AccInfoEquityCommissionCourseChangeCompleteResponse l_response = 
            (WEB3AccInfoEquityCommissionCourseChangeCompleteResponse) l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (validate申込取消)<BR>
     * 株式委託手数料コース変更申込取消確認処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（株式委託手数料ｺｰｽ変更申込）validate申込取消」参照。 <BR>
     * <BR>
     *  ==========================================================    <BR>
     *          シーケンス図 :  お客様情報（株式委託手数料ｺｰｽ変更申込）validate申込取消                  <BR>
     *          具体位置     :  1.5  取消可能でない場合（is取消可能() == false）、   <BR>
     *                             例外をスローする。                                            <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_01010                    <BR> 
     *  ==========================================================    <BR>    <BR>
     * @@param l_request - お客様情報株式委託手数料コース変更申込取消確認リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413D5C0203B2
     */
    protected WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse validateRegistCancel(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateRegistCancel(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();
        
        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //委託手数料コース変更申込(long)
        long l_lngRegistId = Long.parseLong(l_request.id);
        WEB3AccInfoCommissionCourseRegist l_accInfoCommissionCourseRegist =
            new WEB3AccInfoCommissionCourseRegist(l_lngRegistId);
        boolean l_blnCancelPossible =l_accInfoCommissionCourseRegist.isCancelPossible();
        if (!l_blnCancelPossible)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01010,
                this.getClass().getName() + "." + STR_METHOD_NAME);           
        }
        
        //createResponse( )
        WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse l_response = 
            (WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse) l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (submit申込取消)<BR>
     * 株式委託手数料コース変更申込取消完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（株式委託手数料ｺｰｽ変更申込）submit申込取消」参照。 <BR>
     * <BR>
     *  ==========================================================    <BR>
     *          シーケンス図 :  お客様情報（株式委託手数料ｺｰｽ変更申込）submit申込取消                  <BR>
     *          具体位置     :  1.8  取消可能でない場合（is取消可能() == false）、   <BR>
     *                             例外をスローする。                                            <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_01010                    <BR> 
     *  ==========================================================    <BR>  
     * <BR>
     * @@param l_request - お客様情報株式委託手数料コース変更申込取消完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413D5C0203D2
     */
    protected WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse 
        submitRegistCancel(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitRegistCancel(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();
        
        //1.2 validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3 get補助口座( )     
        WEB3GentradeMainAccount l_mainAccout = (WEB3GentradeMainAccount) this.getMainAccount();
        
        //1.5 getCommonOrderValidator( )
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
        
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
            
        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateTradingPassword(
            getTrader(),
            getSubAccount(), 
            l_request.password
            );
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("チェックエラーの場合はを例外をスローする");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        
        //1.7 委託手数料コース変更申込(long)
        long l_lngRegistId = Long.parseLong(l_request.id);
        WEB3AccInfoCommissionCourseRegist l_accInfoCommissionCourseRegist =
            new WEB3AccInfoCommissionCourseRegist(l_lngRegistId);
        
        //1.9 (*1) 取消可能でない場合（is取消可能() == false）、例外をスローする。
        boolean l_blnCancelPossible =l_accInfoCommissionCourseRegist.isCancelPossible();
        if (!l_blnCancelPossible)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01010,
                this.getClass().getName() + "." + STR_METHOD_NAME);           
        }
        
        //1.10 getDataSourceObject( )     
        CommissionCourseRegistParams l_commissionCourseRegistParams = 
            new CommissionCourseRegistParams((CommissionCourseRegistRow)
                l_accInfoCommissionCourseRegist.getDataSourceObject()); 
                              
        l_commissionCourseRegistParams.setDeleteFlag(BooleanEnum.TRUE);
        l_commissionCourseRegistParams.setLastUpdater(l_mainAccout.getAccountCode());
        l_commissionCourseRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        try
        {
            //1.11 doUpdateQuery(arg0 : PrimaryKey, arg1(*2) : Map)
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doUpdateQuery(l_commissionCourseRegistParams);
        }
        catch (DataFindException l_ex) 
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
        catch (DataQueryException l_ex) 
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataNetworkException l_ex) 
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
    //1.12 is手数料No( )
    boolean l_blnIsCommissionNo = 
      this.isCommissionNo(l_accInfoCommissionCourseRegist, l_mainAccout);

    //1.13 (*1)is手数料No()の戻り値 == trueの場合
    if (l_blnIsCommissionNo)
    {
          //1.14 get手数料No(顧客, 委託手数料コース変更申込)
          //[get手数料No()に指定する引数] 
          //顧客：　@get顧客()の戻り値  
          //委託手数料コース変更申込：　@生成した委託手数料コース変更申込 
          String l_strCommissionNo = 
              this.getCommissionNo(
                  l_mainAccout,
                  l_accInfoCommissionCourseRegist);
          
          //1.15 save手数料No(String, 委託手数料コース変更申込, 顧客)
          //[save手数料No()に指定する引数] 
          // 手数料No：　@get手数料No()の戻り値 
          // 委託手数料コース変更申込：　@生成した委託手数料コース変更申込 
          // 顧客：　@get顧客()の戻り値
          this.saveCommissionNo(
              l_strCommissionNo, 
              l_accInfoCommissionCourseRegist, 
              l_mainAccout);
    }
    
        //createResponse( )
        WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse l_response = 
            (WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse) l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }

     /**
     *（is手数料No）<BR>
     * 更新対象のレコードが存在するか判定する。<BR>
     * <BR>
     * [戻り値] <BR>
     * true：　@更新対象のレコードが存在する<BR>
     * false：　@更新対象のレコードが存在しない<BR>
     * <BR>
     * １） 『委託手数料顧客条件登録マスター』を下記条件で検索する。<BR>
     * <BR>
     * 　@　@[条件] <BR>
     *　@　@　@・証券会社コード = 引数.委託手数料コース変更申込.get証券会社コード() <BR>
     *　@　@　@・部店ID = 引数.顧客.getBranch().getBranchId()  <BR>
     *　@　@　@・口座ID = 引数.顧客.getAccountId()   <BR>
     *　@　@　@・手数料商品コード in （10：上場株式、11：JASDAQ、30：債権、31：債権店頭）<BR>
     *　@　@　@・有効日 = 引数.委託手数料コース変更申込.get適用開始日() <BR>
     * <BR>
     * ２） 検索結果により戻り値を設定する。<BR>
     * <BR> 　@
     * 　@・取得レコードが0件の場合、falseを返却する。 <BR>
     * 　@・取得レコードが0件以外の場合、trueを返却する。<BR>
     * <BR>
     * @@param l_commissionCourseRegist - (委託手数料コース変更申込)<BR>
     * 委託手数料コース変更申込オブジェクト<BR>
     * @@param l_request - (顧客)<BR>
     * 顧客
     * @@return boolean
     * @@throws WEB3BaseException 
     * @@roseuid 
     */
  public boolean isCommissionNo(
    WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist,
    MainAccount l_mainAccount) throws WEB3BaseException
  {
        final String STR_METHOD_NAME = " isCommissionNo()";
        log.entering(STR_METHOD_NAME);

        if (l_commissionCourseRegist == null || l_mainAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //[条件] 
        StringBuffer l_strWhere = new StringBuffer();
             
        //証券会社コード = 引数.委託手数料コース変更申込.get証券会社コード() 
        l_strWhere.append(" institution_code = ? ");
        String l_strInstitutionCode = 
            l_commissionCourseRegist.getInstitutionCode();
        
        //部店ID = 引数.顧客.getBranch().getBranchId() 
        l_strWhere.append(" and branch_id = ? ");
        String l_strBranchId = 
            l_mainAccount.getBranch().getBranchId() + "";
             
        //座ID = 引数.顧客.getAccountId()  
        l_strWhere.append(" and account_id = ? ");
        String l_strAccountId = l_mainAccount.getAccountId() + "";
        
        //手数料商品コード in （10：上場株式、11：JASDAQ、30：債権、31：債権店頭） 
        l_strWhere.append(" and comm_product_code in(?, ?, ?, ?) ");

        //有効日 = 引数.委託手数料コース変更申込.get適用開始日() 
        l_strWhere.append(" and valid_until_biz_date = ? ");
        Date l_datAppliStartDate = 
            l_commissionCourseRegist.getAppliStartDate();
        String l_strAppliStartDate = 
            WEB3DateUtility.formatDate(l_datAppliStartDate, "yyyyMMdd");
        
        Object[] l_objEquityCommAccountCondMstbindVars = {
            l_strInstitutionCode,
            l_strBranchId,
            l_strAccountId,
            WEB3CommisionProductCodeDef.LISTING_STOCK,
            WEB3CommisionProductCodeDef.JASDAQ,
            WEB3CommisionProductCodeDef.CREDIT,
            WEB3CommisionProductCodeDef.CREDIT_STORE,
            l_strAppliStartDate};
             
        List l_lisEquityAccountCommCondMstRows = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisEquityAccountCommCondMstRows = l_queryProcessor.doFindAllQuery(
                EquityCommAccountCondMstRow.TYPE,
                l_strWhere.toString(),
                null,
                l_objEquityCommAccountCondMstbindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２） 検索結果により戻り値を設定する。
        if (l_lisEquityAccountCommCondMstRows == null || 
            l_lisEquityAccountCommCondMstRows.isEmpty())
        {
            return false;
        }
     
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (get手数料No)<BR>
     * 「手数料No」を取得する。<BR>
     * <BR>
     * １）レコードを取得 <BR>
     * 　@・ 『手数料顧客条件送信用』から以下の条件のレコードを取得する。 <BR>
     * 　@※該当行がない場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00398<BR>
     * <BR>
     * 　@　@[条件] <BR>
     *　@　@　@・証券会社コード = 引数.委託手数料コース変更申込.get証券会社コード() <BR>
     *　@　@　@・部店コード = 引数.顧客.getBranch().getBranchCode() <BR>
     *　@　@　@・口座コード = 引数.顧客.getAccountCode() <BR>
     *　@　@　@・手数料商品コード = 引数.委託手数料コース変更申込.get手数料商品コード() <BR>
     *　@　@　@・適用開始日 = 引数.委託手数料コース変更申込.get適用開始日()  <BR>
     * <BR>
     * ２） 「手数料No」を返却  <BR>
     * 　@・１）で取得した「手数料No」を返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_commissionCourseRegist - (委託手数料コース変更申込)<BR>
     * 委託手数料コース変更申込オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 413D5C0203D2
     */
    public String getCommissionNo(
        MainAccount l_mainAccount, 
        WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getCommissionNo(MainAccount, " +
            "WEB3AccInfoCommissionCourseRegist, Date)";
        log.entering(STR_METHOD_NAME);
         
        if (l_commissionCourseRegist == null || l_mainAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //１）レコードを取得 
            //『手数料顧客条件送信用』から以下の条件のレコードを取得する。 
            //[条件] 
            //証券会社コード = 引数.委託手数料コース変更申込.get証券会社コード() 
            String l_strInstitutionCode = 
                l_commissionCourseRegist.getInstitutionCode();
            //部店コード = 引数.顧客.getBranch().getBranchCode() 
            String l_strBranchCode = 
                l_mainAccount.getBranch().getBranchCode();
            //口座コード = 引数.顧客.getAccountCode() 
            String l_strAccountCode = l_mainAccount.getAccountCode(); 
            //手数料商品コード = 引数.委託手数料コース変更申込.get手数料商品コード()
            String l_strCommissionProductCode = 
                l_commissionCourseRegist.getCommissionProductCode();
            //適用開始日 = 引数.委託手数料コース変更申込.get適用開始日()
            Date l_datAppliStartDate = 
                l_commissionCourseRegist.getAppliStartDate();
            String l_strAppliStartDate = 
                WEB3DateUtility.formatDate(l_datAppliStartDate, "yyyyMMdd");
            
            CommAccountSendRow l_commAccountSendRow = 
                CommAccountSendDao.findRowByInstitutionCodeBranchCodeAccountCodeCommProductCodeAppliStartDate(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strCommissionProductCode,
                    l_strAppliStartDate);
            
            if (l_commAccountSendRow == null)
            {
                log.debug(" 該当するデータが存在しません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "該当するデータが存在しません。");
            }
            
            //２） 「手数料No」を返却 
            //１）で取得した「手数料No」を返却する。  
            String l_strCommissionNo = l_commAccountSendRow.getCommissionNo();
            log.debug("手数料No = " + l_strCommissionNo);
             
            log.exiting(STR_METHOD_NAME);
            return l_strCommissionNo;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
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
     * (get手数料No)<BR>
     * 「手数料No」を取得する。<BR>
     * <BR>
     * １） レコードを取得<BR>
     * 　@・『手数料コースコード変換マスタ』から以下の条件のレコードを取得する。<BR>
     * 　@※該当行がない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00398<BR>
     * <BR>
     *　@　@[条件]<BR>
     *　@　@　@・部店ID　@=　@引数.委託手数料コース変更申込.get部店<BR>
     *　@　@　@・手数料商品コード　@=　@引数.委託手数料コース変更申込.get手数料商品コード<BR>
     *　@　@　@・手数料コースコード　@=　@引数.委託手数料コース変更申込.get手数料コースコード<BR>
     *　@　@　@・適用開始日　@<=　@引数.委託手数料コース変更申込.get適用開始日<BR>
     * <BR>
     *　@　@[取得順]<BR>
     *　@　@　@・適用開始日 降順（DESC）<BR>
     * <BR>
     * ２） 「手数料No」を取得<BR>
     *　@・１）にて複数レコード取得されるので、<BR>
     *　@　@　@１番最初のレコードから「手数料No」を取得する。<BR>
     * <BR>
     * ３） 「手数料No」を返却<BR>
     *　@・２）で取得した「手数料No」を返却する。<BR>
     * <BR>
     * @@param l_commissionCourseRegist - (委託手数料コース変更申込)<BR>
     * 委託手数料コース変更申込オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 413D5C0203D2
     */
    public String getCommissionNo(
        WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getCommissionNo(WEB3AccInfoCommissionCourseRegist)";
        log.entering(STR_METHOD_NAME);
         
        if (l_commissionCourseRegist == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
         
        //１） レコードを取得
        //　@『手数料コースコード変換マスタ』から以下の条件のレコードを取得する。 
        //[条件] 
        StringBuffer l_strWhere = new StringBuffer();
         
        //部店ID = 引数.引数.委託手数料コース変更申込.get部店
        l_strWhere.append(" branch_id = ? ");
        long l_lngBranchId = 
            l_commissionCourseRegist.getBranch().getBranchId();
         
        //手数料商品コード = 引数.委託手数料コース変更申込.get手数料商品コード() 
        l_strWhere.append(" and comm_product_code = ? ");
        String l_strCommissionProductCode =
            l_commissionCourseRegist.getCommissionProductCode();
         
        //手数料コースコード = 引数.委託手数料コース変更申込.get手数料コースコード()
        l_strWhere.append(" and commission_course_div = ? ");
        String l_strCommissionCourseCode = 
            l_commissionCourseRegist.getCommissionCourseCode();
         
        //適用開始日　@<=　@引数.委託手数料コース変更申込.get適用開始日
        l_strWhere.append(" and appli_start_date <= ? ");
        Date l_datAppliStartDate = 
            l_commissionCourseRegist.getAppliStartDate();
        String l_strFomateDate = WEB3DateUtility.formatDate(l_datAppliStartDate, "yyyyMMdd");
         
        //[取得順] 
        //適用開始日 降順（DESC） 
        String l_strSortKey = "appli_start_date DESC";
        
        Object[] l_objCommCodeChgMstbindVars = {
            new Long(l_lngBranchId),
            l_strCommissionProductCode,
            l_strCommissionCourseCode,
            l_strFomateDate};
        
        List l_lisCommCodeChgMstRows = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCommCodeChgMstRows = l_queryProcessor.doFindAllQuery(
                CommCodeChgMstRow.TYPE,
                l_strWhere.toString(),
                l_strSortKey,
                null,
                l_objCommCodeChgMstbindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
    
         //※該当行がない場合、例外をスローする。 
        if (l_lisCommCodeChgMstRows == null || 
            l_lisCommCodeChgMstRows.isEmpty())
        {
            log.debug(" 該当するデータが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当するデータが存在しません。");
        }
        
        //２） 「手数料No」を取得
        //・１）にて複数レコード取得されるので、１番最初のレコードから「手数料No」を取得する。
        CommCodeChgMstRow l_commCodeChgMstRow = 
            (CommCodeChgMstRow) l_lisCommCodeChgMstRows.get(0);
        String l_strCommissionNo = l_commCodeChgMstRow.getCommissionNo();
        
        //４）「手数料No」を返却 
        log.exiting(STR_METHOD_NAME);
        return l_strCommissionNo;
    }
    
    /**
     * (save手数料No)<BR>
     * 『委託手数料顧客条件登録マスター.手数料No』をupdateする。<BR>
     * <BR>
     *　@[DB更新仕様] <BR>
     *　@　@・【ｘTrade】補足資料.DB更新 <BR>
     *　@　@　@　@20.お客様情報／暗証番号管理 <BR>
     *　@　@　@　@06.株式委託手数料コース変更申込 <BR>
     *　@　@　@　@「委託手数料顧客条件登録マスター更新仕様.xls」 <BR>
     * <BR>
     * 　@　@[条件] <BR>
     *　@　@　@・証券会社コード = 引数.委託手数料コース変更申込.get証券会社コード() <BR>
     *　@　@　@・部店ID = 引数.顧客.getBranch().getBranchId()  <BR>
     *　@　@　@・口座ID = 引数.顧客.getAccountId()   <BR>
     *　@　@　@・手数料商品コード in （10：上場株式、11：JASDAQ、30：債権、31：債権店頭）<BR>
     *　@　@　@・有効日 = 引数.委託手数料コース変更申込.get適用開始日() <BR>
     * <BR>
     * @@param l_strCommissionNo - (手数料No)<BR>
     * 手数料No<BR>
     * @@param l_commissionCourseRegist - (委託手数料コース変更申込)<BR>
     * 委託手数料コース変更申込オブジェクト<BR>
     * @@param l_request - (顧客)<BR>
     * 顧客
     * @@throws WEB3BaseException
     * @@roseuid 413D5C0203D2
     */
    public void saveCommissionNo(
        String l_strCommissionNo, 
        WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist,
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " saveCommissionNo(Date, String," +
            " WEB3AccInfoCommissionCourseRegist, MainAccount)";
        log.entering(STR_METHOD_NAME);
         
        if (l_commissionCourseRegist == null || l_mainAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
         
        //[条件] 
        StringBuffer l_strWhere = new StringBuffer();
         
        //証券会社コード = 引数.委託手数料コース変更申込.get証券会社コード() 
        l_strWhere.append(" institution_code = ? ");
        String l_strInstitutionCode = 
            l_commissionCourseRegist.getInstitutionCode();
         
        //部店ID = 引数.顧客.getBranch().getBranchId() 
        l_strWhere.append(" and branch_id = ? ");
        String l_strBranchId = 
            l_mainAccount.getBranch().getBranchId() + "";
         
        //座ID = 引数.顧客.getAccountId()  
        l_strWhere.append(" and account_id = ? ");
        String l_strAccountId = l_mainAccount.getAccountId() + "";
         
        //手数料商品コード in （10：上場株式、11：JASDAQ、30：債権、31：債権店頭） 
        l_strWhere.append(" and comm_product_code in(?, ?, ?, ?) ");

        //有効日 = 引数.委託手数料コース変更申込.get適用開始日() 
        l_strWhere.append(" and valid_until_biz_date = ? ");
        Date l_datAppliStartDate = 
            l_commissionCourseRegist.getAppliStartDate();
        String l_strAppliStartDate = 
            WEB3DateUtility.formatDate(l_datAppliStartDate, "yyyyMMdd");
        
        Object[] l_objEquityCommAccountCondMstbindVars = {
            l_strInstitutionCode,
            l_strBranchId,
            l_strAccountId,
            WEB3CommisionProductCodeDef.LISTING_STOCK,
            WEB3CommisionProductCodeDef.JASDAQ,
            WEB3CommisionProductCodeDef.CREDIT,
            WEB3CommisionProductCodeDef.CREDIT_STORE,
            l_strAppliStartDate};
         
        List l_lisEquityAccountCommCondMstRows = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisEquityAccountCommCondMstRows = l_queryProcessor.doFindAllQuery(
                EquityCommAccountCondMstRow.TYPE,
                l_strWhere.toString(),
                null,
                l_objEquityCommAccountCondMstbindVars);
        }
        catch (DataNetworkException l_ex)
        {
           log.error("DBへのアクセスに失敗しました", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }

        if (l_lisEquityAccountCommCondMstRows == null || 
            l_lisEquityAccountCommCondMstRows.isEmpty())
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        
        int l_intEquityAccountCommCondMstRows = 
            l_lisEquityAccountCommCondMstRows.size();
        for (int i = 0; i < l_intEquityAccountCommCondMstRows; i++)
        {
            //[DB更新仕様] 
            //【ｘTrade】補足資料.DB更新 
            //20.お客様情報／暗証番号管理 
            //06.株式委託手数料コース変更申込 
            //「委託手数料顧客条件登録マスター更新仕様.xls」
            EquityCommAccountCondMstRow l_accountCondMstRow = 
                (EquityCommAccountCondMstRow) l_lisEquityAccountCommCondMstRows.get(i);
            EquityCommAccountCondMstParams l_accountCondMstParams = 
                new EquityCommAccountCondMstParams(l_accountCondMstRow);
            
            l_accountCondMstParams.setCommissionNo(l_strCommissionNo);
            l_accountCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_accountCondMstParams);
            }
            catch (DataFindException l_ex)
            {
                log.error("DBへのアクセスに失敗しました", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);    
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);    
            }
            catch (DataQueryException l_ex)
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
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate取扱可能委託手数料コース)<BR>
     * 当該顧客の口座開設状況により、取扱可能な委託手数料コースであるかをチェックする。<BR>
     * <BR>
     * 引数.信用口座開設フラグ == false かつ 引数.手数料区分 == 1 （信用顧客）の場合、<BR>
     * 例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_03108<BR>
     * @@param l_strMarginOpenFlag - (信用口座開設フラグ)<BR>
     * 信用口座開設フラグ<BR>
     * @@param l_commissionDiv - (手数料区分)<BR>
     * 手数料区分<BR>
     * @@throws WEB3BaseException
     */
    public void validateHandlingPossibleCommCourse(boolean l_blnMarginOpenFlag, String l_strCommissionDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingPossibleCommCourse(boolean, String)";
        log.entering(STR_METHOD_NAME);

        if (!l_blnMarginOpenFlag
            && WEB3AccInfoCommissionDivDef.MARGIN_TRADE_COMMISSION.equals(l_strCommissionDiv))
        {
            log.debug("信用口座開設なし、かつ手数料区分は信用顧客です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03108,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "信用口座開設なし、かつ手数料区分は信用顧客です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
