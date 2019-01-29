head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用申込登録サービスImpl(WEB3SrvRegiRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 李頴淵 新規作成
Revesion History : 2005/04/06 内田 亨(SRA) デバッグログ出力修正
Revesion History : 2007/06/06 金傑(中訊) 仕様変更モデルNo.247
Revesion History : 2007/06/19 崔遠鵬(中訊) 仕様変更モデルNo.249
Revesion History : 2007/07/16 孟亜南(中訊) 仕様変更モデルNo.284
Revesion History : 2007/07/24 金傑(中訊) 仕様変更モデルNo.292
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AppliAttributeDef;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3SrvUsePeriodDivDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.gentrade.data.SrvRegiApplicationRow;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiCancelUpdateInterceptor;
import webbroker3.srvregi.WEB3SrvRegiChangeAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiNewAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiNewOrderUpdateInterceptor;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiHistoryParams;
import webbroker3.srvregi.data.SrvRegiHistoryRow;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiFreeAttributeApplyDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiKeyItemDef;
import webbroker3.srvregi.define.WEB3SrvRegiRigistDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiSpecialDivDef;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

/**
 * (サービス利用申込登録サービスImpl)<BR>
 * サービス利用申込登録サービス実装クラス<BR>
 * @@author 李頴淵
 * @@version 1.0
 *
 * <BR>
 * 申込情報に関する処理を行うメソッドを集めたクラス<BR>
 */
public class WEB3SrvRegiRegistServiceImpl implements WEB3SrvRegiRegistService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3SrvRegiRegistServiceImpl.class);

    /**
     * @@roseuid 416F392903C8
     */
    public WEB3SrvRegiRegistServiceImpl()
    {

    }

    /**
     * (validate適用期間)<BR>
     * 指定された適用期間が正しいかどうかを判定する。<BR>
     * <BR>
     * 1) 以下の条件で「サービス申込登録」テーブルを検索する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@サービス区分=引数.サービス区分 and<BR>
     * 　@部店コード=引数.部店コード and<BR>
     * 　@口座コード=引数.顧客コード and<BR>
     * 　@適用終了日≧現在日時の日付部分 and<BR>
     * 　@有効区分="有効" and<BR>
     * 　@取消区分="通常" and<BR>
     * 　@申込登録ID!=引数.申込登録ID(*2) and<BR>
     * 　@((適用開始日≧引数.適用開始日 and 適用開始日≦引数.適用終了日) or<BR>
     * 　@(適用終了日≧引数.適用開始日 and 適用終了日≦引数.適用終了日) or<BR>
     * 　@(適用開始日≦引数.適用開始日 and <BR>適用終了日≧引数.適用終了日))<BR>
     * <BR>
     * (*2) 引数.申込登録IDがnullの場合、この条件を指定しない。<BR>
     * <BR>
     * 2) 検索結果＞0件の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00984<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strMainAccountCode - (顧客コード)<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * @@param l_registId - (申込登録ID)<BR>
     * @@roseuid 411047880125
     */
    public void validateAppliPeriod(String l_strInstitutionCode, String l_strSrvDiv, String l_strBranchCode,
        String l_strMainAccountCode, Timestamp l_tsAppliStartDate, Timestamp l_tsAppliEndDate, Long l_registId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAppliPeriod(String, String, String, String, Timestamp, Timestamp, Long)";
        log.entering(STR_METHOD_NAME);

        try
        {
        	//障害対応　@NO_U01711
        	//現在日の取得
			Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
			Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);

            if (l_registId == null)
            {
                log.debug("l_registId == null)");
                //「サービス申込登録」テーブルを検索する。
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();   //DataNetworkException, DataQueryException
                String l_strWhere =
                        "institution_code = ? and " +       //証券会社コード=引数.証券会社コード and
                        "srv_div = ? and " +                //サービス区分=引数.サービス区分 and
                        "branch_code = ? and " +            //部店コード=引数.部店コード and
                        "account_code = ? and " +           //口座コード=引数.顧客コード and
                        "appli_end_date >= ? and " +        //適用終了日≧現在日時(*1)の日付部分 and
                        "effective_div = ? and " +          //有効区分="有効" and
                        "cancel_div = ? and " +             //取消区分="通常" and
                        //((適用開始日≧引数.適用開始日 and 適用開始日≦引数.適用終了日) or
                        "((appli_start_date >= ? and appli_start_date <= ?) or " +
                        //(適用終了日≧引数.適用開始日 and 適用終了日≦引数.適用終了日) or
                        "(appli_end_date >= ? and appli_end_date <= ?) or " +
                        //(適用開始日≦引数.適用開始日 and <BR>適用終了日≧引数.適用終了日))
                        "(appli_start_date <= ? and appli_end_date >= ?))";

                Object l_bindVars[] =
                    {   l_strInstitutionCode,
                        l_strSrvDiv,
                        l_strBranchCode,
                        l_strMainAccountCode,
						l_datSystemDate,
                        WEB3EffectiveDivDef.EFFECTIVE,
                        WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate};

                List l_lisRows =
                    l_queryProcesser.doFindAllQuery(
                    SrvRegiApplicationRow.TYPE,
                    l_strWhere,
                    l_bindVars);

                if (l_lisRows.size() > 0)
                {
                    log.debug("適用期間チェックエラー。（サービス申込登録チェック）");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00984,
                        getClass().getName() + STR_METHOD_NAME);
                }
                log.exiting(STR_METHOD_NAME);
            }
            else
            {
                //「サービス申込登録」テーブルを検索する。
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                String l_strWhere =
                        "institution_code = ? and " +       //証券会社コード=引数.証券会社コード and
                        "srv_div = ? and " +                //サービス区分=引数.サービス区分 and
                        "branch_code = ? and " +            //部店コード=引数.部店コード and
                        "account_code = ? and " +           //口座コード=引数.顧客コード and
                        "appli_end_date >= ? and " +        //適用終了日≧現在日時(*1)の日付部分 and
                        "effective_div = ? and " +          //有効区分="有効" and
                        "cancel_div = ? and " +             //取消区分="通常" and
                        "regist_id <> ? and " +             //申込登録ID!=引数.申込登録ID(*2) and
                        //((適用開始日≧引数.適用開始日 and 適用開始日≦引数.適用終了日) or
                        "((appli_start_date >= ? and appli_start_date <= ?) or " +
                        //(適用終了日≧引数.適用開始日 and 適用終了日≦引数.適用終了日) or
                        "(appli_end_date >= ? and appli_end_date <= ?) or " +
                        //(適用開始日≦引数.適用開始日 and <BR>適用終了日≧引数.適用終了日))
                        "(appli_start_date <= ? and appli_end_date >= ?))";

                Object l_bindVars[] =
                    {   l_strInstitutionCode,
                        l_strSrvDiv,
                        l_strBranchCode,
                        l_strMainAccountCode,
						l_datSystemDate,
                        WEB3EffectiveDivDef.EFFECTIVE,
                        WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
                        l_registId,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate,
                        l_tsAppliStartDate,
                        l_tsAppliEndDate};

                List l_lisRows =
                    l_queryProcesser.doFindAllQuery(
                    SrvRegiApplicationRow.TYPE,
                    l_strWhere,
                    l_bindVars);

                if (l_lisRows.size() > 0)
                {
                    log.debug("適用期間チェックエラー。（サービス申込登録チェック）");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00984,
                        getClass().getName() + STR_METHOD_NAME);
                }
                log.exiting(STR_METHOD_NAME);
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }

    }

	/**
	 * (calc適用終了日)<BR>
	 * 指定された適用開始日から、適用終了日を算出して返却する。<BR>
	 * <BR>
	 * シーケンス図「（サービス利用）calc適用終了日」参照<BR>
	 * @@param l_strInstitutionCode - (証券会社コード)<BR>
	 * @@param l_strBranchCode - (部店コード)<BR>
	 * @@param l_strSrvDiv - (サービス区分)<BR>
	 * @@param l_strAccountCode - (口座コード)<BR>
	 * @@param l_tsAppliStartDate - (適用開始日)<BR>
	 * @@param l_lngSrvUsePeriodId - (利用期間ID)<BR>
	 * @@param l_strSpecialDiv - (特殊申込区分)<BR>
	 * @@param l_strFreeAttributeApplyDiv - (無料属性申込区分)<BR>* 
	 * @@return Timestamp
	 * @@roseuid 41295B860301
	 */
    public Timestamp calcAppliEndDate(String l_strInstitutionCode, String l_strBranchCode,
                          String l_strSrvDiv, String l_strAccountCode, Timestamp l_tsAppliStartDate,
                          long l_lngSrvUsePeriodId, String l_strSpecialDiv, String l_strFreeAttributeApplyDiv)
                                  throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " calcAppliEndDate(String, String, String, String, Timestamp, long)";
		log.entering(STR_METHOD_NAME);

		//1.1 getサービスマスター
		WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
		WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
			l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

		//1.2 getサービス申込登録
		WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication = null;
		l_gentradeSrvRegiApplication =
			this.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode,
			WEB3SrvRegiCancelDivDef.USUAL_DEFAULT, WEB3EffectiveDivDef.EFFECTIVE, false);

		//1.3 <分岐処理 *1>
		long TRIAL_OR_FREE = 0;
		Timestamp l_tsAppliEndDate = null;
		String l_strTrialPeriodDiv = null;
        String l_strSrvUsePeriodDiv = null;
        Integer l_intTrialPeriodObj = new Integer(0);
		int l_intTrialPeriod = 0;
        long l_lngSrvUsePeriod = 0;
        int l_intSrvUsePeriod = 0;

		if (l_gentradeSrvRegiApplication != null)
		{
			//1.3.1 get適用終了日( )
			log.debug("get適用終了日");
			l_tsAppliEndDate = l_gentradeSrvRegiApplication.getAppliEndDate();
		}
		//get申込要サービス
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        //getサービス申込属性情報
        List l_lisServiceApppliAttributeInfo = l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
            l_strInstitutionCode,
            l_strBranchCode,
            l_strAccountCode,
            l_strSrvDiv,
            null);

        //getサービス申込属性情報 != null　@の場合
        String l_strFreeTargetPeriod = null;
        if (l_lisServiceApppliAttributeInfo != null)
        {
            //get無料対象期間
            l_strFreeTargetPeriod = l_srvRegiApplicationRequiredService.getFreeTargetPeriod();
        }

		//引数.無料属性申込区分 == null 又、"0"　@の場合
		if (l_strFreeAttributeApplyDiv == null || 
			WEB3SrvRegiFreeAttributeApplyDivDef.NORMAL_APPLY.equals(l_strFreeAttributeApplyDiv))
        {
            //is試用申込可能
            boolean l_blnTrialAppliPossible = l_srvRegiServiceInfoManagement.isTrialAppliPossible(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strSrvDiv,
                l_strAccountCode);

            //<分岐処理 *3>
            if (l_blnTrialAppliPossible)
            {
                //get試用期間区分
                l_strTrialPeriodDiv = l_srvRegiApplicationRequiredService.getTrialPeriodDiv();
                //get試用期間
                l_intTrialPeriodObj = l_srvRegiApplicationRequiredService.getTrialPeriod();
                if (l_intTrialPeriodObj != null)
                {
                    l_intTrialPeriod = l_intTrialPeriodObj.intValue();    
                }
            }

            //利用期間ID != 0(試用期間)の場合
            if (l_lngSrvUsePeriodId != TRIAL_OR_FREE)
            {
                //getサービス利用期間料金
                WEB3SrvRegiServiceUsePeriodAmt l_srvRegiServiceUsePeriodAmt =
                    l_srvRegiServiceMaster.getSrvUseTermAmt(l_lngSrvUsePeriodId, false);
                
                //get利用期間区分
                l_strSrvUsePeriodDiv = l_srvRegiServiceUsePeriodAmt.getSrvUsePeriodDiv();
                //get利用期間
                l_lngSrvUsePeriod = l_srvRegiServiceUsePeriodAmt.getSrvUsePeriod();
                l_intSrvUsePeriod = Integer.parseInt(WEB3StringTypeUtility.formatNumber(l_lngSrvUsePeriod));
            }
        }

		//<適用終了日の算出>
		Calendar l_caleNewAppliEndDate = Calendar.getInstance();
		Calendar l_caleAppliStartDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		int l_maxDay = 0;
		boolean l_maxDayFlag = false;
		
		//サービス申込登録オブジェクト != null の場合
		if (l_gentradeSrvRegiApplication != null)
		{
			//<分岐処理 *1>で適用終了日が取得できた場合
			if (l_tsAppliEndDate != null)
			{
				log.debug("l_tsAppliEndDate != null");
				String l_stAppliEndDate = formatter.format(l_tsAppliEndDate);
				Date l_datAppliEndDate = WEB3DateUtility.getDate(l_stAppliEndDate, "yyyyMMdd");
				
				//引数.無料属性申込区分 == null 又、"0"　@の場合
				if (l_strFreeAttributeApplyDiv == null || 
					WEB3SrvRegiFreeAttributeApplyDivDef.NORMAL_APPLY.equals(l_strFreeAttributeApplyDiv))
				{
	                l_caleAppliStartDate.setTime(l_datAppliEndDate);
	                l_caleNewAppliEndDate.setTime(l_datAppliEndDate);

	                //試用期間の加算
	                if (l_strTrialPeriodDiv != null && l_intTrialPeriodObj != null)
	                {
	                    log.debug("試用期間の加算");
	                    if (WEB3SrvUsePeriodDivDef.YEAR.equals(l_strTrialPeriodDiv)) // 年
	                    {
	                        log.debug("年");
	                        l_caleNewAppliEndDate.add(Calendar.YEAR, l_intTrialPeriod);
	                        if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
	                        {
	                            l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
	                            l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
	                            l_maxDayFlag = true;
	                        }
	                    }
	                    else if (WEB3SrvUsePeriodDivDef.MONTH.equals(l_strTrialPeriodDiv)) // 月
	                    {
	                        log.debug("月");
	                        l_caleNewAppliEndDate.add(Calendar.MONTH, l_intTrialPeriod);
	                        if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
	                        {
	                            l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
	                            l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
	                            l_maxDayFlag = true;
	                        }
	                    }
	                    else if (WEB3SrvUsePeriodDivDef.DATE.equals(l_strTrialPeriodDiv)) // 日
	                    {
	                        log.debug("日");
	                        l_caleNewAppliEndDate.add(Calendar.DATE, l_intTrialPeriod);
	                    }

	                    
	                    if (!l_maxDayFlag && l_lngSrvUsePeriodId == TRIAL_OR_FREE)
	                    {
	                        l_caleNewAppliEndDate.add(Calendar.DATE, -1);
	                    }
	                    log.debug("l_datNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
	                    Date l_datNewAppliStartDate = l_caleNewAppliEndDate.getTime();
	                    l_caleAppliStartDate.setTime(l_datNewAppliStartDate);
	                }

	                //適用開始日の算出
	                l_caleAppliStartDate.add(Calendar.DATE, 1);
	                l_caleNewAppliEndDate.add(Calendar.DATE, 1);
	                
					//リクエスト.利用期間ID != '0'(試用申込)の場合
					if (l_lngSrvUsePeriodId != TRIAL_OR_FREE)
					{
		                //利用期間の加算
		                if (WEB3SrvUsePeriodDivDef.YEAR.equals(l_strSrvUsePeriodDiv)) // 年
		                {
		                    log.debug("年");
		                    l_caleNewAppliEndDate.add(Calendar.YEAR, l_intSrvUsePeriod);
		                    if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
		                    {
		                        l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
		                        l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
		                        l_maxDayFlag = true;
		                    }
		                }
		                else if (WEB3SrvUsePeriodDivDef.MONTH.equals(l_strSrvUsePeriodDiv)) // 月
		                {
		                    log.debug("月");
		                    l_caleNewAppliEndDate.add(Calendar.MONTH, l_intSrvUsePeriod);
		                    if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
		                    {
		                        l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
		                        l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
		                        l_maxDayFlag = true;
		                    }
		                }
		                else if (WEB3SrvUsePeriodDivDef.DATE.equals(l_strSrvUsePeriodDiv)) // 日
		                {
		                    log.debug("日");
		                    l_caleNewAppliEndDate.add(Calendar.DATE, l_intSrvUsePeriod);
		                }
					}
	            }

				else
				{
	                l_caleAppliStartDate.setTime(l_datAppliEndDate);
	                l_caleNewAppliEndDate.setTime(l_datAppliEndDate);

	                //適用開始日の算出
	                l_caleAppliStartDate.add(Calendar.DATE, 1);
	                l_caleNewAppliEndDate.add(Calendar.DATE, 1);

	                //算出日付＝現時点で有効な適用終了日＋get無料対象期間()で取得した利用期間
	                l_caleNewAppliEndDate.add(Calendar.MONTH, Integer.parseInt(l_strFreeTargetPeriod));
	                l_caleNewAppliEndDate.add(Calendar.DATE, -1);
				}
			}
		}

		//サービス申込登録オブジェクト == null の場合
		else
		{
			//（引数.無料属性申込区分 == null 又、"0"　@の場合）且つ
			//リクエスト.利用期間ID = 0 の場合
			if ((l_strFreeAttributeApplyDiv == null || 
				WEB3SrvRegiFreeAttributeApplyDivDef.NORMAL_APPLY.equals(l_strFreeAttributeApplyDiv)) &&
				(l_lngSrvUsePeriodId == TRIAL_OR_FREE))
			{
	            //get試用期間
				int l_trialPeriod = 0;
				if (l_srvRegiApplicationRequiredService.getTrialPeriod() != null)
				{
	            	l_trialPeriod = l_srvRegiApplicationRequiredService.getTrialPeriod().intValue();
				}

		        //＜現在日付の取得＞
		        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
	            l_tsAppliStartDate = l_tsSystemTimestamp;
	            
				String l_stAppliEndDate = formatter.format(l_tsSystemTimestamp);
				Date l_datAppliEndDate = WEB3DateUtility.getDate(l_stAppliEndDate, "yyyyMMdd");
				l_caleAppliStartDate.setTime(l_datAppliEndDate);
				l_caleNewAppliEndDate.setTime(l_datAppliEndDate);

	            if (WEB3SrvUsePeriodDivDef.YEAR.equals(l_strTrialPeriodDiv))                  //年
	            {
					log.debug("年");
					l_caleNewAppliEndDate.add(Calendar.YEAR, l_trialPeriod);
					if(l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
					{
						l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
						l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
						l_maxDayFlag = true;
					}
	            }
	            else if (WEB3SrvUsePeriodDivDef.MONTH.equals(l_strTrialPeriodDiv))            //月　@　@　@
	            {
					log.debug("月");
					l_caleNewAppliEndDate.add(Calendar.MONTH, l_trialPeriod);
					if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
					{
						l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
						l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
						l_maxDayFlag = true;
					}
	            }
	            else if (WEB3SrvUsePeriodDivDef.DATE.equals(l_strTrialPeriodDiv))            //日
	            {
					log.debug("日");
					l_caleNewAppliEndDate.add(Calendar.DATE, l_trialPeriod);
	            }

				if (!l_maxDayFlag)
				{
					l_caleNewAppliEndDate.add(Calendar.DATE, -1);
				}
				log.debug("l_datNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
				Date l_datNewAppliEndDate = l_caleNewAppliEndDate.getTime();

	            l_tsAppliEndDate = new Timestamp(l_datNewAppliEndDate.getTime());
			}
			
			//引数.無料属性申込区分 == null 又、"0"　@の場合
			else if (l_strFreeAttributeApplyDiv == null || 
			WEB3SrvRegiFreeAttributeApplyDivDef.NORMAL_APPLY.equals(l_strFreeAttributeApplyDiv))
			{
                //試用期間の加算
                if (l_strTrialPeriodDiv != null && l_intTrialPeriodObj != null)
                {
                    log.debug("試用期間の加算");
                    if (WEB3SrvUsePeriodDivDef.YEAR.equals(l_strTrialPeriodDiv)) // 年
                    {
                        log.debug("年");
                        l_caleNewAppliEndDate.add(Calendar.YEAR, l_intTrialPeriod);
                        if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
                        {
                            l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
                            l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
                            l_maxDayFlag = true;
                        }
                    }
                    else if (WEB3SrvUsePeriodDivDef.MONTH.equals(l_strTrialPeriodDiv)) // 月
                    {
                        log.debug("月");
                        l_caleNewAppliEndDate.add(Calendar.MONTH, l_intTrialPeriod);
                        if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
                        {
                            l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
                            l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
                            l_maxDayFlag = true;
                        }
                    }
                    else if (WEB3SrvUsePeriodDivDef.DATE.equals(l_strTrialPeriodDiv)) // 日
                    {
                        log.debug("日");
                        l_caleNewAppliEndDate.add(Calendar.DATE, l_intTrialPeriod);
                    }

                    if (!l_maxDayFlag)
                    {
                        l_caleNewAppliEndDate.add(Calendar.DATE, -1);
                    }
                    log.debug("l_datNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
                    Date l_datNewAppliStartDate = l_caleNewAppliEndDate.getTime();
                    l_caleAppliStartDate.setTime(l_datNewAppliStartDate);

                    //適用開始日の算出
                    if (l_strSrvUsePeriodDiv != null)
                    {
                        l_caleAppliStartDate.add(Calendar.DATE, 1);
                        l_caleNewAppliEndDate.add(Calendar.DATE, 1);
                    }
                }
                //利用期間の加算
                if (WEB3SrvUsePeriodDivDef.YEAR.equals(l_strSrvUsePeriodDiv)) // 年
                {
                    log.debug("年");
                    l_caleNewAppliEndDate.add(Calendar.YEAR, l_intSrvUsePeriod);
                    if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
                    {
                        l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
                        l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
                        l_maxDayFlag = true;
                    }
                }
                else if (WEB3SrvUsePeriodDivDef.MONTH.equals(l_strSrvUsePeriodDiv)) // 月
                {
                    log.debug("月");
                    l_caleNewAppliEndDate.add(Calendar.MONTH, l_intSrvUsePeriod);
                    if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
                    {
                        l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
                        l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
                        l_maxDayFlag = true;
                    }
                }
                else if (WEB3SrvUsePeriodDivDef.DATE.equals(l_strSrvUsePeriodDiv)) // 日
                {
                    log.debug("日");
                    l_caleNewAppliEndDate.add(Calendar.DATE, l_intSrvUsePeriod);
                }
			}
			
			else
			{
                //適用開始日に、get無料対象期間()で取得した利用期間を加算する。
                l_caleNewAppliEndDate.add(Calendar.MONTH, Integer.parseInt(l_strFreeTargetPeriod));
                l_caleNewAppliEndDate.add(Calendar.DATE, -1);
			}
		}
		
		//初回申込かつ特殊申込区分 = 1 の場合。
		if (l_tsAppliEndDate == null && l_strSpecialDiv != null
					&& l_strSpecialDiv.equals(WEB3SrvRegiSpecialDivDef.END_OF_MONTH_DIV))
		{
			l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
			l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
			l_maxDayFlag = true;
		}

		if (l_strSrvUsePeriodDiv != null && !l_maxDayFlag)
		{
			l_caleNewAppliEndDate.add(Calendar.DATE, -1);
		}
		log.debug("l_datNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
		log.exiting(STR_METHOD_NAME);
		Date l_datNewAppliEndDate = l_caleNewAppliEndDate.getTime();
		return new Timestamp(l_datNewAppliEndDate.getTime());
	}

    /**
     * (submitサービス申込登録)<BR>
     * サービス申込登録の更新処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）サービス申込登録」参照<BR>
     * @@param l_newAppliSpec - (申込内容)<BR>
     * サービス利用新規申込内容<BR>
     * @@param l_orderId - (注文ID)<BR>
     * @@roseuid 411228760343
     */
    public void submitServiceRegist(WEB3SrvRegiNewAppliSpec l_newAppliSpec, Long l_orderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitServiceRegist(WEB3SrvRegiNewAppliSpec, Long)";
        log.entering(STR_METHOD_NAME);

        //1.1 getサービスマスター
        String l_strInstitutionCode = l_newAppliSpec.getInstitutionCode();
        String l_strSrvDiv = l_newAppliSpec.getSrvDiv();

        //1.2 getサービス申込登録
        String l_strBranchCode = l_newAppliSpec.getBranchCode();
        String l_strAccountCode = l_newAppliSpec.getAccountCode();
        log.debug("getサービス申込登録");
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
            this.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode, null, WEB3EffectiveDivDef.EFFECTIVE, true);

        //1.3 <*1 分岐処理>
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        if (l_gentradeSrvRegiApplication != null)
        {
            log.debug("set有効区分");
            //1.3.1 set有効区分
            l_gentradeSrvRegiApplication.setEffectiveDiv(WEB3EffectiveDivDef.INEFFECTIVE);

            l_gentradeSrvRegiApplication.setLastUpdater(l_administrator.getAdministratorCode());

            //1.3.2 saveサービス申込登録
            l_gentradeSrvRegiApplication.saveSrvRegiApplication();
        }

        //1.4 createNewサービス申込登録
        WEB3GentradeSrvRegiApplication l_newGentradeSrvRegiApplication =
            WEB3GentradeSrvRegiApplication.createNewSrvRegiApplication(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode);

        //1.4.1 <パラメータ・セット>
        log.debug("<パラメータ・セット>");
        //○申込日=引数.注文内容.申込日
        if (l_newAppliSpec.getAppliDate() == null)
        {
            l_newGentradeSrvRegiApplication.setAppliDate(l_newAppliSpec.getAppliStartDate());
        }
        else
        {
            l_newGentradeSrvRegiApplication.setAppliDate(l_newAppliSpec.getAppliDate());
        }

        //○適用開始日=引数.注文内容.適用開始日
        l_newGentradeSrvRegiApplication.setAppliStartDate(l_newAppliSpec.getAppliStartDate());
        //○適用終了日=引数.注文内容.適用終了日
        l_newGentradeSrvRegiApplication.setAppliEndDate(l_newAppliSpec.getAppliEndDate());
        //○注文ID=引数.注文ID
        l_newGentradeSrvRegiApplication.setOrderId(l_orderId);
        //○登録区分==引数.注文内容.登録区分
        l_newGentradeSrvRegiApplication.setPaymentDiv(l_newAppliSpec.getPaymentDiv());
        //○申込抽選区分=引数.注文内容.申込抽選区分
        l_newGentradeSrvRegiApplication.setAppliLotDiv(l_newAppliSpec.getAppliLotDiv());
        //○利用料金=引数.注文内容.利用料金
        l_newGentradeSrvRegiApplication.setUseAmt(l_newAppliSpec.getUseAmt());
        //○出金日=引数.注文内容.出金日
        l_newGentradeSrvRegiApplication.setPaymentDate(l_newAppliSpec.getPaymentDate());
        //○自動当選取消期限日=null
        l_newGentradeSrvRegiApplication.setCancelLimitDate(null);

        l_newGentradeSrvRegiApplication.setLastUpdater(l_administrator.getAdministratorCode());

        //1.4.2 saveNewサービス申込登録
        l_newGentradeSrvRegiApplication.saveNewSrvRegiApplication();

        //1.5 get初期申込区分
        String l_strInitializeAppliDiv =
            this.getInitializeAppliDiv(l_newAppliSpec.getInstitutionCode(), l_newAppliSpec.getBranchCode(),
            l_newAppliSpec.getSrvDiv(), l_newAppliSpec.getAccountCode());

        //1.6 get初期申込区分( )の戻り値＝"無"の場合
        try
        {
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strInitializeAppliDiv))
            {

                //1.10 「申込履歴管理テーブル」登録処理
                SrvRegiHistoryParams l_srvRegiHistoryParams = new SrvRegiHistoryParams();
                OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                AccountManager l_accountManager = l_finApp.getAccountManager();

                //証券会社コード
                String l_strInstitutionId = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.INSTITUTION_ID);
                String l_strInstitutionCodeSession =
                    l_accountManager.getInstitution(Long.parseLong(l_strInstitutionId)).getInstitutionCode();
                l_srvRegiHistoryParams.setInstitutionCode(l_strInstitutionCodeSession);

                //部店コード
                l_srvRegiHistoryParams.setBranchCode(l_newAppliSpec.getBranchCode());

                //サービス区分
                l_srvRegiHistoryParams.setSrvDiv(l_newAppliSpec.getSrvDiv());

                //口座コード
                l_srvRegiHistoryParams.setAccountCode(l_newAppliSpec.getAccountCode());

                //申込日
                l_srvRegiHistoryParams.setRegistDate(l_newAppliSpec.getAppliDate());

                //申込経路区分
                l_srvRegiHistoryParams.setOrderRootDiv(WEB3OrderRootDivDef.ADMIN);

                //更新者コード
                AdministratorRow l_administratorRow =
                    AdministratorDao.findRowByLoginId(l_opLoginSec.getLoginInfo().getLoginId());
                if (l_administratorRow == null)
                {
                    log.debug("データ不整合エラー");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        getClass().getName() + STR_METHOD_NAME
                    );
                }
                l_srvRegiHistoryParams.setLastUpdater(l_administratorRow.getAdministratorCode());

                Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
                l_srvRegiHistoryParams.setCreatedTimestamp(l_tsSystemTimestamp);
                l_srvRegiHistoryParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

                l_queryProcesser.doInsertQuery(l_srvRegiHistoryParams);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (submitサービス申込変更)<BR>
     * サービス申込登録の更新処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）サービス申込変更」参照<BR>
     * @@param l_changeAppliSpec - (申込内容)<BR>
     * サービス利用変更申込内容オブジェクト<BR>
     * @@roseuid 41119A2F01DA
     */
    public void submitServiceRegistChange(WEB3SrvRegiChangeAppliSpec l_changeAppliSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitServiceRegistChange(WEB3SrvRegiChangeAppliSpec)";
        log.entering(STR_METHOD_NAME);

        //1.1 getサービス申込登録
        String l_strInstitutionCode = l_changeAppliSpec.getInstitutionCode();
        String l_strBranchCode = l_changeAppliSpec.getBranchCode();
        String l_strSrvDiv = l_changeAppliSpec.getSrvDiv();
        String l_strAccountCode = l_changeAppliSpec.getAccountCode();
        String l_strRegistId = l_changeAppliSpec.getRegistId();
        log.debug("getサービス申込登録");
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
            this.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode, Long.parseLong(l_strRegistId), true);

        //1.2  <分岐処理 *1>
        if (WEB3SrvRegiAppliLotDivDef.CANCEL.equals(l_changeAppliSpec.getAppliLotDiv()))
        {
            //1.2.1 set取消区分
            log.debug("set取消区分");
            l_gentradeSrvRegiApplication.setCancelDiv(WEB3SrvRegiCancelDivDef.CANCEL);
            log.debug("l_gentradeSrvRegiApplication.getCancelDiv() = " + l_gentradeSrvRegiApplication.getCancelDiv());

            //set最終更新者
            WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
            l_gentradeSrvRegiApplication.setLastUpdater(l_administrator.getAdministratorCode());

            //saveサービス申込登録()
            l_gentradeSrvRegiApplication.saveSrvRegiApplication();
        }
        else
        // 1.3<分岐処理 *2>
        {
            //1.3.1 getサービスマスター
            WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
                l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

            //1.3.2 get申込要サービス
            WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
                l_srvRegiServiceMaster.getAppliRequiredSrv(false);
            if (l_srvRegiApplicationRequiredService == null)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //1.3.3  <分岐処理 *3>
            WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_srvRegiApplicationRequiredService.getLotDiv()))
            {
                log.debug("<分岐処理 *3>");
                //1.3.3.1 set有効区分
                l_gentradeSrvRegiApplication.setEffectiveDiv(WEB3EffectiveDivDef.INEFFECTIVE);

                //set最終更新者
                l_gentradeSrvRegiApplication.setLastUpdater(l_administrator.getAdministratorCode());

                //1.3.3.2 saveサービス申込登録
                l_gentradeSrvRegiApplication.saveSrvRegiApplication();

                //1.3.3.3 createNewサービス申込登録
                WEB3GentradeSrvRegiApplication l_newGentradeSrvRegiApplication =
                    WEB3GentradeSrvRegiApplication.createNewSrvRegiApplication(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode);

                //1.3.3.4  <パラメータ・セット>
                log.debug("<パラメータ・セット>");
                //申込日=≪既存行≫の「申込日」
                l_newGentradeSrvRegiApplication.setAppliDate(l_gentradeSrvRegiApplication.getAppliDate());
                //申込抽選区分=引数.注文内容.get申込抽選区分( )
                l_newGentradeSrvRegiApplication.setAppliLotDiv(l_changeAppliSpec.getAppliLotDiv());
                //適用開始日=引数.注文内容.get適用開始日( )
                l_newGentradeSrvRegiApplication.setAppliStartDate(l_changeAppliSpec.getAppliStartDate());
                //適用終了日=引数.注文内容.get適用終了日( )
                l_newGentradeSrvRegiApplication.setAppliEndDate(l_changeAppliSpec.getAppliEndDate());
                //登録区分=引数.注文内容.get登録区分( )
                l_newGentradeSrvRegiApplication.setPaymentDiv(l_changeAppliSpec.getPaymentDiv());
                //利用料金=引数.注文内容.get利用料金( )
                l_newGentradeSrvRegiApplication.setUseAmt(l_changeAppliSpec.getUseAmt());
                //自動当選取消期限日=null
                l_newGentradeSrvRegiApplication.setCancelLimitDate(null);

                //set最終更新者
                l_newGentradeSrvRegiApplication.setLastUpdater(l_administrator.getAdministratorCode());

                //1.3.3.5 saveNewサービス申込登録
                l_newGentradeSrvRegiApplication.saveNewSrvRegiApplication();
            }
            //1.3.4 <分岐処理 *4>
            if (WEB3ConditionsValueDivDef.HAVE.equals(l_srvRegiApplicationRequiredService.getLotDiv()))
            {
                log.debug("<分岐処理 *4>");
                //1.3.4.1 <パラメータ・セット>
                //申込日=引数.注文内容.get申込日( )
                l_gentradeSrvRegiApplication.setAppliDate(l_changeAppliSpec.getAppliDate());
                //申込抽選区分=引数.注文内容.get申込抽選区分( )
                l_gentradeSrvRegiApplication.setAppliLotDiv(l_changeAppliSpec.getAppliLotDiv());
                //適用開始日=引数.注文内容.get適用開始日( )
                l_gentradeSrvRegiApplication.setAppliStartDate(l_changeAppliSpec.getAppliStartDate());
                //適用終了日=引数.注文内容.get適用終了日( )
                l_gentradeSrvRegiApplication.setAppliEndDate(l_changeAppliSpec.getAppliEndDate());
                //登録区分=引数.注文内容.get登録区分( )
                l_gentradeSrvRegiApplication.setPaymentDiv(l_changeAppliSpec.getPaymentDiv());
                //利用料金=引数.注文内容.get利用料金( )
                l_gentradeSrvRegiApplication.setUseAmt(l_changeAppliSpec.getUseAmt());
                //自動当選取消期限日=null
                l_gentradeSrvRegiApplication.setCancelLimitDate(null);

                //set最終更新者
                l_gentradeSrvRegiApplication.setLastUpdater(l_administrator.getAdministratorCode());

                //1.3.4.2 saveサービス申込登録
                l_gentradeSrvRegiApplication.saveSrvRegiApplication();
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit余力拘束)<BR>
     * サービスの利用申込に伴う出金の為、入出金注文単位を作成し、<BR>
     * その際に作成した注文IDを返却する。<BR>
     * <BR>
     * シーケンス図「（サービス利用）submit余力拘束」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）submit余力拘束」): <BR>
     * 1.13<is判定フラグ( )=falseの場合、余力残高エラーとして例外をスローする  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01187<BR>
     * ==========================================================<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 代理入力者オブジェクト<BR>
     * @@param l_dblUseAmt 利用料金<BR>
     * @@param l_tsPaymentDate - (出金日)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strOrderChannel - (注文チャネル)<BR>
     * @@param l_strPassword - (暗証番号)<BR>
     * @@return long
     * @@roseuid 413E63CA026B
     */
    public long submitRemainingPowerRestraint(WEB3GentradeSubAccount l_subAccount, Trader l_trader, double l_dblUseAmt, Timestamp l_tsPaymentDate, String l_strSrvDiv, String l_strOrderChannel, String l_strPassword)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRemainingPowerRestraint(WEB3GentradeSubAccount, Trader, double, Timestamp, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //1.1 <拡張入出金注文マネージャ取得>
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();

        //1.2 get商品ID
        long l_lngProductId = l_aioOrderManager.getProductId(l_subAccount.getInstitution());

        //1.3 createNewOrderId
        log.debug("createNewOrderId");
        long l_lngOrderId = l_aioOrderManager.createNewOrderId();

        //1.4 入出金注文内容
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(l_trader, OrderTypeEnum.CASH_OUT, AssetTransferTypeEnum.CASH_OUT, l_lngProductId,
            l_dblUseAmt, null, l_tsPaymentDate, null, new Long(l_lngOrderId));

        //1.5 サービス利用新規注文更新インタセプタ
        WEB3SrvRegiNewOrderUpdateInterceptor l_srvRegiNewOrderUpdateInterceptor = new WEB3SrvRegiNewOrderUpdateInterceptor();

        //1.6 setサービス区分
        log.debug("setサービス区分");
        l_srvRegiNewOrderUpdateInterceptor.setSrvDiv(l_strSrvDiv);

        //1.7 set受渡日
        l_srvRegiNewOrderUpdateInterceptor.setDeliveryDate(l_tsPaymentDate);

        //1.8:営業日計算(Timestamp)
        WEB3GentradeBizDate l_datBizDate = new WEB3GentradeBizDate(l_tsPaymentDate);

        //1.9:roll(int)
        Timestamp l_ts = l_datBizDate.roll(-1);

        //1.10 set発注日
        l_srvRegiNewOrderUpdateInterceptor.setOrderBizDate(l_ts);

        //1.10 set注文経路区分
        // (*) 引数.注文チャネルがNullの場合、WEB3OrderRootDivDef.ADMINを設定する。
        // (*) 引数.注文チャネルの値がWEB3ChannelDef.コールセンターの場合、
        //     WEB3OrderRootDivDef.コールセンターを設定する。
        // (*) 引数.注文チャネルの値がWEB3ChannelDef.コールセンターでない場合、
        //     WEB3OrderRootDivDef.PCを設定する。
        if (l_strOrderChannel == null)
        {
            l_srvRegiNewOrderUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.ADMIN);
        }
        else if (WEB3ChannelDef.CALL_CENTER.equals(l_strOrderChannel))
        {
            l_srvRegiNewOrderUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.CALLCENTER);
        }
        else
        {
            l_srvRegiNewOrderUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.PC);
        }

        //1.11 setThreadLocalPersistenceEventInterceptor
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_srvRegiNewOrderUpdateInterceptor);

        //1.12 validate取引余力
        WEB3TPTradingPowerService l_web3TPTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        Object[] l_obj1 = new Object[1];
        Object[] l_obj2 = new Object[1];
        l_obj1[0] = l_srvRegiNewOrderUpdateInterceptor;
        l_obj2[0] = l_aioNewOrderSpec;

        WEB3TPTradingPowerResult l_web3TPTradingPowerResult =
                    l_web3TPTradingPowerService.validateTradingPower(l_subAccount, l_obj1, l_obj2, OrderTypeEnum.CASH_OUT, true);

        //1.13 is判定フラグ()
        boolean l_blnDecisionFlag = l_web3TPTradingPowerResult.isResultFlg();

        //1.14 <is判定フラグ( )=falseの場合、余力残高エラーとして例外をスローする。>
        if (!l_blnDecisionFlag)
        {
            log.debug("余力残高エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01187,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.15 submitNewOrder
        OrderSubmissionResult l_orderSubmissionResult =
            l_aioOrderManager.submitNewOrder(l_subAccount, ProductTypeEnum.CASH, l_aioNewOrderSpec,
            l_lngOrderId, l_strPassword, true);
        if(l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("throw new WEB3SystemLayerException");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lngOrderId;
    }

    /**
     * (submit余力解放)<BR>
     * サービスの利用申込の取消などに伴う余力解放の為、<BR>
     * 入出金注文単位に取り消し処理を実施する。<BR>
     * <BR>
     * シーケンス図「（サービス利用）submit余力解放」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * @@param l_strPassword - (暗証番号)<BR>
     * @@roseuid 413E63D302A9
     */
    public void submitRemainingPowerRelease(WEB3GentradeSubAccount l_subAccount, long l_lngOrderId, String l_strPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRemainingPowerRelease(WEB3GentradeSubAccount, long, String)";
        log.entering(STR_METHOD_NAME);

        //1.1  <拡張入出金注文マネージャ取得>
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();

        //1.2 CancelOrderSpec
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_lngOrderId);

        //1.3 サービス利用取消更新インタセプタ
        WEB3SrvRegiCancelUpdateInterceptor l_srvRegiCancelUpdateInterceptor = new WEB3SrvRegiCancelUpdateInterceptor();

        //1.4 setThreadLocalPersistenceEventInterceptor
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_srvRegiCancelUpdateInterceptor);
		
		//障害対応 余力解放
        //1.5 submitCancelOrder
        log.debug("submitCancelOrder");
        OrderSubmissionResult l_orderSubmissionResult =
            l_aioOrderManager.submitCancelOrder(l_subAccount, l_cancelOrderSpec, l_strPassword, true);
        if(l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("throw new WEB3SystemLayerException");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                getClass().getName() + STR_METHOD_NAME);
        }
        
		//1.6 余力再計算
		log.debug("余力再計算");
		WEB3TPTradingPowerService l_web3TPTradingPowerService =
			(WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
		l_web3TPTradingPowerService.reCalcTradingPower(l_subAccount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getサービス申込登録)<BR>
     * 指定されたサービス申込登録を取得し、それをサービス申込登録オブジェクトを
     * 作成し返却する。<BR>
     * <BR>
     * 1) 以下の条件で「サービス申込登録テーブル」を検索する。<BR>
     * （引数.is行ロックがtrueの場合、select for updateで検索を行う。)<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@部店コード=引数.部店コード and<BR>
     * 　@サービス区分=引数.サービス区分 and<BR>
     * 　@口座コード=引数.口座コード and<BR>
     * 　@申込登録ID=引数.申込登録ID and<BR>
     * 　@有効区分="有効"<BR>
     * <BR>
     *  1-1) 検索結果=0件の場合<BR>
     * 　@　@nullを返却する。<BR>
     *  1-2) 検索結果=1件の場合<BR>
     * 　@　@検索結果のサービス申込登録Paramsオブジェクトを引数にサービス申込登録クラスの<BR>
     * 　@コンストラクタをコールし、サービス申込登録オブジェクトを生成する。<BR>
     * <BR>
     * 2) 引数.is行ロック=trueの場合<BR>
     * 　@生成したサービス申込登録オブジェクト.createForUpdateParams( )をコールする。<BR>
     * <BR>
     * 3) サービス申込登録オブジェクトを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@param l_lngRegistId - (申込登録ID)<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * true:行ロックを行う　@false:行ロックを行わない<BR>
     * @@return WEB3GentradeSrvRegiApplication
     * @@roseuid 41203F150220
     */
    public WEB3GentradeSrvRegiApplication getServiceRegist(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, long l_lngRegistId, boolean l_blnIsRowLock)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getServiceRegist(String, String, String, String, long, boolean)";
        log.entering(STR_METHOD_NAME);

        //1) 以下の条件で「サービス申込登録テーブル」を検索する。
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            String l_strWhere =
                    "institution_code = ? and " +        //証券会社コード=引数.証券会社コード and
                    "branch_code = ? and " +             //部店コード=引数.部店コード and
                    "srv_div = ? and " +                 //サービス区分=引数.サービス区分 and
                    "account_code = ? and " +            //口座コード=引数.口座コード and
                    "regist_id = ? and " +               //申込登録ID=引数.申込登録ID and
                    "effective_div = ?";                 //有効区分="有効"

            Object l_bindVars[] =
                {   l_strInstitutionCode,
                    l_strBranchCode,
                    l_strSrvDiv,
                    l_strAccountCode,
                    new Long(l_lngRegistId),
                    WEB3EffectiveDivDef.EFFECTIVE};

            List l_lisRows = null;
            if (l_blnIsRowLock)
            {
                log.debug("行ロックを行う");
                l_lisRows =
                    l_queryProcesser.doFindAllQuery(
                    SrvRegiApplicationRow.TYPE,
                    l_strWhere,
                    " FOR UPDATE ",
                    l_bindVars);
            }
            else
            {
                log.debug("行ロックを行わない");
                l_lisRows =
                    l_queryProcesser.doFindAllQuery(
                    SrvRegiApplicationRow.TYPE,
                    l_strWhere,
                    l_bindVars);
            }

            WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication = null;
            //検索結果=0件の場合
            if (l_lisRows.size() == 0)
            {
                log.debug("検索結果=0件の場合");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            //検索結果=1件の場合
            else
            {
                log.debug("検索結果=1件の場合");
                SrvRegiApplicationParams l_srvRegiApplicationParams = (SrvRegiApplicationParams)l_lisRows.get(0);
                l_gentradeSrvRegiApplication =
                    new WEB3GentradeSrvRegiApplication(l_srvRegiApplicationParams);
            }
            if (l_blnIsRowLock)
            {
                log.debug("l_blnIsRowLock");
                l_gentradeSrvRegiApplication.createForUpdateParams();
            }

            log.exiting(STR_METHOD_NAME);
            return l_gentradeSrvRegiApplication;
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }

    /**
     * (getサービス申込登録)<BR>
     * 現在有効なサービス申込登録を取得し、それを元に<BR>
     * サービス申込登録オブジェクトを返却する。<BR>
     * <BR>
     * 1) 以下の条件で「サービス申込登録テーブル」を検索する。<BR>
     * （引数.is行ロックがtrueの場合、select for updateで検索を行う。)<BR>
     * [検索条件]<BR>
     * 　@証券会社コード＝引数.証券会社コード and<BR>
     * 　@部店コード＝引数.部店コード and<BR>
     * 　@サービス区分＝引数.サービス区分 and<BR>
     * 　@口座コード＝引数.口座コード and<BR>
     * 　@適用終了日≧現在日付(*1) and<BR>
     * 　@取消区分＝(*2) and<BR>
     * 　@有効区分＝(*3)<BR>
     * <BR>
     * [並び順]<BR>
     * 　@適用開始日<BR>
     * <BR>
     * (*1) 基準日 = GtlUtils.getTradingSystem( ).getSystemTimestamp( )の日付部分<BR>
     * <BR>
     * (*2) 引数.取消区分="指定無"の場合、条件に含めない。<BR>
     * 　@　@上記以外は、「取消区分=引数.取消区分」を条件に含める。<BR>
     * <BR>
     * (*3) 引数.有効区分="指定無"の場合、条件に含めない。<BR>
     * 　@　@上記以外は、「有効区分=引数.有効区分」を条件に含める。<BR>
     * <BR>
     * 2) 戻り値の生成<BR>
     *  2-1) 検索結果が0件の場合、nullを返却する。<BR>
     * <BR>
     *  2-2) 検索結果が1件の場合<BR>
     * 　@検索結果であるサービス申込登録Paramsオブジェクトを引数に、<BR>
     * 　@サービス申込登録クラスのコンストラクタをコールする。<BR>
     * <BR>
     *  2-3) 検索結果＞1件の場合<BR>
     * 　@検索結果の一番先頭のレコードをサービス申込登録Paramsオブジェクトを引数に、<BR>
     * 　@サービス申込登録クラスのコンストラクタをコールする。<BR>
     * <BR>
     * 3) 生成したサービス申込登録オブジェクトを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@param l_strCancelDiv - (取消区分)<BR>
     * 0:通常　@1:取消　@null:指定無<BR>
     * @@param l_strEffectiveDiv - (有効区分)<BR>
     * 0:有効　@1:無効　@null:指定無<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * true:行ロックを行う　@false:行ロックを行わない<BR>
     * @@return WEB3GentradeSrvRegiApplication
     * @@roseuid 4108ECAD00D5
     */
    public WEB3GentradeSrvRegiApplication getServiceRegist(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, String l_strCancelDiv, String l_strEffectiveDiv, boolean l_blnIsRowLock)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getServiceRegist(String, String, String, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        //1) 以下の条件で「サービス申込登録テーブル」を検索する。
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException

            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
            Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
            List l_lisRows = null;
            if (l_strCancelDiv == null && l_strEffectiveDiv == null)
            {
                log.debug("l_strCancelDiv == null && l_strEffectiveDiv == null");
                String l_strWhere =
                        "institution_code = ? and " +        //証券会社コード=引数.証券会社コード and
                        "branch_code = ? and " +             //部店コード=引数.部店コード and
                        "srv_div = ? and " +                 //サービス区分=引数.サービス区分 and
                        "account_code = ? and " +            //口座コード=引数.口座コード and
                        "appli_end_date >= ?";               //適用終了日≧現在日付(*1) and

                Object l_bindVars[] =
                    {   l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCode,
                        l_datSystemDate};

                String l_strOrderBy = " appli_start_date ";

                if (l_blnIsRowLock)
                {
                    log.debug("行ロックを行う");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        " FOR UPDATE ",
                        l_bindVars);
                }
                else
                {
                    log.debug("行ロックを行わない");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        null,
                        l_bindVars);
                }
            }
            else if(l_strCancelDiv == null && l_strEffectiveDiv != null)
            {
                log.debug("else if(l_strCancelDiv == null && l_strEffectiveDiv != null)");
                String l_strWhere =
                        "institution_code = ? and " +        //証券会社コード=引数.証券会社コード and
                        "branch_code = ? and " +             //部店コード=引数.部店コード and
                        "srv_div = ? and " +                 //サービス区分=引数.サービス区分 and
                        "account_code = ? and " +            //口座コード=引数.口座コード and
                        "appli_end_date >= ? and " +         //適用終了日≧現在日付(*1) and
                        "effective_div = ?";                 //有効区分＝(*3)

                Object l_bindVars[] =
                    {   l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCode,
                        l_datSystemDate,
                        l_strEffectiveDiv};

                String l_strOrderBy = " appli_start_date ";

                if (l_blnIsRowLock)
                {
                    log.debug("行ロックを行う");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        " FOR UPDATE ",
                        l_bindVars);
                }
                else
                {
                    log.debug("行ロックを行わない");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        null,
                        l_bindVars);
                }
            }
            else if(l_strCancelDiv != null && l_strEffectiveDiv == null)
            {
                log.debug("else if(l_strCancelDiv != null && l_strEffectiveDiv == null)");
                String l_strWhere =
                        "institution_code = ? and " +        //証券会社コード=引数.証券会社コード and
                        "branch_code = ? and " +             //部店コード=引数.部店コード and
                        "srv_div = ? and " +                 //サービス区分=引数.サービス区分 and
                        "account_code = ? and " +            //口座コード=引数.口座コード and
                        "appli_end_date >= ? and " +         //適用終了日≧現在日付(*1) and
                        "cancel_div = ?";                    //有効区分＝(*3)

                Object l_bindVars[] =
                    {   l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCode,
                        l_datSystemDate,
                        l_strCancelDiv};

                String l_strOrderBy = " appli_start_date ";

                if (l_blnIsRowLock)
                {
                    log.debug("行ロックを行う");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        " FOR UPDATE ",
                        l_bindVars);
                }
                else
                {
                    log.debug("行ロックを行わない");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        null,
                        l_bindVars);
                }
            }
            else
            {
                log.debug("else");
                String l_strWhere =
                        "institution_code = ? and " +        //証券会社コード=引数.証券会社コード and
                        "branch_code = ? and " +             //部店コード=引数.部店コード and
                        "srv_div = ? and " +                 //サービス区分=引数.サービス区分 and
                        "account_code = ? and " +            //口座コード=引数.口座コード and
                        "appli_end_date >= ? and " +         //適用終了日≧現在日付(*1) and
                        "cancel_div = ? and " +              //取消区分＝(*2) and
                        "effective_div = ?";                  //有効区分＝(*3)

                Object l_bindVars[] =
                    {   l_strInstitutionCode,
                        l_strBranchCode,
                        l_strSrvDiv,
                        l_strAccountCode,
                        l_datSystemDate,
                        l_strCancelDiv,
                        l_strEffectiveDiv};

                String l_strOrderBy = " appli_start_date ";

                if (l_blnIsRowLock)
                {
                    log.debug("行ロックを行う");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        " FOR UPDATE ",
                        l_bindVars);
                }
                else
                {
                    log.debug("行ロックを行わない");
                    l_lisRows =
                        l_queryProcesser.doFindAllQuery(
                        SrvRegiApplicationRow.TYPE,
                        l_strWhere,
                        l_strOrderBy,
                        null,
                        l_bindVars);
                }
            }

            WEB3GentradeSrvRegiApplication l_WEB3GentradeSrvRegiApplication = null;
            //検索結果=0件の場合
            if (l_lisRows.size() == 0)
            {
                log.debug("検索結果=0件の場合 ");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            //検索結果=1件の場合
            else if (l_lisRows.size() == 1)
            {
                log.debug("検索結果=1件の場合");
                SrvRegiApplicationParams l_SrvRegiApplicationParams = (SrvRegiApplicationParams)l_lisRows.get(0);
                l_WEB3GentradeSrvRegiApplication =
                    new WEB3GentradeSrvRegiApplication(l_SrvRegiApplicationParams);
            }
            //検索結果＞1件の場合
            else
            {
                log.debug("検索結果＞1件の場合");
                SrvRegiApplicationParams l_SrvRegiApplicationParams = (SrvRegiApplicationParams)l_lisRows.get(0);
                l_WEB3GentradeSrvRegiApplication =
                    new WEB3GentradeSrvRegiApplication(l_SrvRegiApplicationParams);
            }
            if (l_blnIsRowLock)
            {
                log.debug("l_blnIsRowLock");
                l_WEB3GentradeSrvRegiApplication.createForUpdateParams();
            }

            log.exiting(STR_METHOD_NAME);
            return l_WEB3GentradeSrvRegiApplication;
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

    }

    /**
     * (getサービス申込登録一覧)<BR>
     * 指定された条件に合致するサービス申込登録一覧を検索し、<BR>
     * その結果をサービス申込登録Paramsオブジェクトの配列にして返却する。<BR>
     * <BR>
     * 1) ソート条件の作成<BR>
     * 　@引数.ソート条件がnull以外であり、かつ要素数＞0の場合、<BR>
     * 　@引数.ソート条件の件数分、以下を繰り返す。<BR>
     *  1-1) 対応するテーブルの列物理名を昇順／降順指定を付加しセットする。 <BR>
     * <BR>
     * 　@　@○キー項目とテーブルの列名称との対応は以下の通り。 <BR>
     * 　@　@　@※キー項目文字列(シンボル名)は、メッセージ定義書を参照。 <BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照。 <BR>
     * <BR>
     * 　@　@　@　@・部店　@　@　@　@　@　@　@　@=サービス申込登録テーブル.部店コード<BR>
     * 　@　@　@　@・顧客　@　@　@　@　@　@　@　@=サービス申込登録テーブル.口座コード<BR>
     * 　@　@　@　@・申込抽選区分　@　@=サービス申込登録テーブル.申込抽選区分<BR>
     * 　@　@　@　@・申込日　@　@　@　@　@　@ =サービス申込登録テーブル.申込日<BR>
     * 　@　@　@　@・適用開始日　@　@　@ =サービス申込登録テーブル.適用開始日<BR>
     * 　@　@　@　@・適用終了日　@　@　@ =サービス申込登録テーブル.適用終了日<BR>
     * 　@　@　@　@・登録区分　@　@　@　@　@=サービス申込登録テーブル.登録区分<BR>
     * 　@　@　@　@・利用料金　@　@　@　@　@=サービス申込登録テーブル.利用料金<BR>
     * 　@　@　@　@・最終更新日　@　@　@ =サービス申込登録テーブル.更新日付<BR>
     * 　@　@　@　@・最終更新者　@　@　@ =サービス申込登録テーブル.更新者コード<BR>
     * <BR>
     * 　@　@○昇順／降順指定は、ソートキー.昇順／降順 指定に従い設定する。 <BR>
     * <BR>
     * 2) 以下の検索条件で、「サービス申込登録テーブル」を検索する。<BR>
     * 　@[検索条件]<BR>
     * 　@　@○証券会社コード=引数.証券会社コード<BR>
     * 　@　@○サービス区分=引数.サービス区分<BR>
     * <BR>
     * 　@　@○部店コード=引数.部店コード ---------（ただし、引数.部店コードがnull<BR>
     * では無い場合に限る）<BR>
     * 　@　@○口座コード=引数.口座コード ---------（ただし、引数.口座コードがnull<BR>
     * では無い場合に限る）<BR>
     * 　@　@○登録区分=引数.登録区分 ----------（ただし、引数.登録区分がnull<BR>
     * では無い場合に限る）<BR>
     * 　@　@○適用開始日≧引数.適用開始日（自） -（ただし、引数.適用開始日（自）<BR>
     * がnullでは無い場合に限る）<BR>
     * 　@　@○適用開始日≦引数.適用開始日（至） -（ただし、引数.適用開始日（至）<BR>
     * がnullでは無い場合に限る）<BR>
     * 　@　@○申込日≧引数.申込日（自） ---------（ただし、引数.申込日（自）がnull<BR>
     * では無い場合に限る）<BR>
     * 　@　@○申込日≦引数.申込日（至） ---------（ただし、引数.申込日（至）がnull<BR>
     * では無い場合に限る）<BR>
     * <BR>
     * 　@　@○申込抽選区分・取消区分・自動当選取消期限日＝(*1)<BR>
     * 　@　@○適用終了日≧現在日時の日付部分<BR>
     * 　@　@○有効区分＝"有効"<BR>
     * <BR>
     * 　@[並び順]<BR>
     * 　@　@1)で生成したソート条件(*3)<BR>
     * <BR>
     * (*1)<BR>
     * 　@○リクエストデータ.申込抽選区分="取消"の場合<BR>
     * 　@　@　@　@　@（申込抽選区分を条件に含めない）<BR>
     * 　@　@　@　@　@取消区分＝"取消"<BR>
     * 　@　@　@　@　@（自動当選取消期限日を条件に含めない）<BR>
     * <BR>
     * 　@○リクエストデータ.申込抽選区分="全て"の場合<BR>
     * 　@　@　@　@　@（申込抽選区分を条件に含めない）<BR>
     * 　@　@　@　@　@（取消区分を条件に含めない）<BR>
     * 　@　@　@　@　@（自動当選取消期限日を条件に含めない）<BR>
     * <BR>
     * 　@○リクエストデータ.申込抽選区分="申込"の場合<BR>
     * 　@　@　@　@ (（申込抽選区分＝リクエストデータ.申込抽選区分） or<BR>
     * 　@　@　@　@　@（申込抽選区分＝"自動当選" and<BR>
     * 　@　@　@　@　@　@自動当選取消期限日≧現在日時(*2)の日付部分）) and<BR>
     * 　@　@　@　@　@取消区分＝"通常"<BR>
     * <BR>
     * 　@○リクエストデータ.申込抽選区分="当選／本申込"の場合<BR>
     * 　@　@　@　@ (（申込抽選区分＝リクエストデータ.申込抽選区分） or<BR>
     * 　@　@　@　@　@（申込抽選区分＝"自動当選" and<BR>
     * 　@　@　@　@　@　@自動当選取消期限日＜現在日時(*2)の日付部分）) and<BR>
     * 　@　@　@　@　@取消区分＝"通常"<BR>
     * <BR>
     * 　@○それ以外の場合<BR>
     * 　@　@　@　@　@申込抽選区分＝リクエストデータ.申込抽選区分 and<BR>
     * 　@　@　@　@　@取消区分＝"通常"<BR>
     * 　@　@　@　@　@自動当選取消期限日=null<BR>
     * <BR>
     * (*3)引数.ソート条件=nullの場合、適用開始日の昇順を指定する。<BR>
     * <BR>
     * 3) 2)の検索結果を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード（必須）<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分（必須）<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@param l_strPaymentDiv - (登録区分)<BR>
     * 0:有料　@1:無料　@2:全て<BR>
     * @@param l_strAppliLotDiv - (申込抽選区分)<BR>
     * @@param l_tsAppliStartDateFrom - (適用開始日（自）)<BR>
     * @@param l_tsAppliStartDateTo - (適用開始日（至）)<BR>
     * @@param l_tsAppliDateFrom - (申込日（自）)<BR>
     * @@param l_tsAppliDateTo - (申込日（至）)<BR>
     * @@param l_sortConds - (ソート条件)<BR>
     * 対象項目:<BR>
     * ≪抽選無の場合≫<BR>
     * "部店","顧客","適用開始日","適用終了日","登録区分","利用料金"<BR>
     * ,"最終更新日","最終更新者"<BR>
     * ≪抽選有の場合≫<BR>
     * "部店","顧客","申込抽選区分","申込日","適用開始日","適用終了日"<BR>
     * ,"登録区分","利用料金","最終更新日","最終更新者"<BR>
     * @@return webbroker3.gentrade.data.SrvRegiApplicationParams[ ]
     * @@roseuid 41078D740138
     */
    public SrvRegiApplicationParams[] getServiceRegistLists(String l_strInstitutionCode, String[] l_strBranchCodes,
        String l_strSrvDiv, String l_strAccountCode, String l_strPaymentDiv, String l_strAppliLotDiv,
        Timestamp l_tsAppliStartDateFrom, Timestamp l_tsAppliStartDateTo, Timestamp l_tsAppliDateFrom,
        Timestamp l_tsAppliDateTo, WEB3SrvRegiSortKey[] l_sortConds)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getServiceRegistLists(String, String[], String, String, String, String, Timestamp, Timestamp, Timestamp, Timestamp, WEB3SrvRegiSortKey[])";
        log.entering(STR_METHOD_NAME);

        String l_strWhere = null;
        List l_lis = new ArrayList();
        String l_strOrderBy = "";

        if (l_sortConds != null && l_sortConds.length > 0)
        {
            log.debug("l_sortConds != null && l_sortConds.length > 0");
            int l_intLength = l_sortConds.length;
            for (int i = 0; i < l_intLength; i++)
            {
                log.debug("for " + i);
                if (WEB3SrvRegiKeyItemDef.BRANCH_CODE.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("branch_code asc");
                        l_strOrderBy = l_strOrderBy + " branch_code asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("branch_code desc");
                        l_strOrderBy = l_strOrderBy + " branch_code desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.ACCOUNT_CODE.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("account_code asc");
                        l_strOrderBy = l_strOrderBy + " account_code asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("account_code desc");
                        l_strOrderBy = l_strOrderBy + " account_code desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_LOT_DIV.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_lot_div asc");
                        l_strOrderBy = l_strOrderBy + " appli_lot_div asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_lot_div desc");
                        l_strOrderBy = l_strOrderBy + " appli_lot_div desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_DATE.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_START_DATE.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_start_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_start_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_start_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_start_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_END_DATE.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_end_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_end_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("appli_end_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_end_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.PAYMENT_DIV.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("payment_div asc");
                        l_strOrderBy = l_strOrderBy + " payment_div asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("payment_div desc");
                        l_strOrderBy = l_strOrderBy + " payment_div desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.USE_AMT.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("use_amt asc");
                        l_strOrderBy = l_strOrderBy + " use_amt asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("use_amt desc");
                        l_strOrderBy = l_strOrderBy + " use_amt desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.LAST_UPDATED_TIMESTAMP.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("last_updated_timestamp  asc");
                        l_strOrderBy = l_strOrderBy + " last_updated_timestamp  asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("last_updated_timestamp  desc");
                        l_strOrderBy = l_strOrderBy + " last_updated_timestamp  desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.LAST_UPDATER.equals(l_sortConds[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("last_updater  asc");
                        l_strOrderBy = l_strOrderBy + " last_updater  asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConds[i].ascDesc))
                    {
                        log.debug("last_updater  desc");
                        l_strOrderBy = l_strOrderBy + " last_updater  desc";
                    }
                }

                if(i != l_intLength - 1)
                {
                    l_strOrderBy = l_strOrderBy + ", ";
                }
            }
        }
        else
        {
            log.debug("appli_start_date");
            l_strOrderBy = " appli_start_date";

        }
		
		//障害対応 NO_U01711
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
		Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);

        l_strWhere =
                "institution_code = ? and " +        //○証券会社コード=引数.証券会社コード
                "srv_div = ? and ";                  //○サービス区分=引数.サービス区分
        l_lis.add(new String(l_strInstitutionCode));
        l_lis.add(new String(l_strSrvDiv));

        //○部店コード=引数.部店コード ---------（ただし、引数.部店コードがnullでは無い場合に限る）
        if (l_strBranchCodes != null)
        {
            if (l_strBranchCodes.length == 1)
            {
                log.debug("部店コード=引数.部店コード");
                l_strWhere = l_strWhere + "branch_code = ? and ";
                l_lis.add(l_strBranchCodes[0]);
            }
            else
            {
                log.debug("部店コード in 引数.部店コード");
                l_strWhere = l_strWhere + "branch_code in ( ";
                int l_int = l_strBranchCodes.length;
                for (int i = 0; i < l_int; i++)
                {
                    l_strWhere = l_strWhere + " ?";
                    l_lis.add(l_strBranchCodes[i]);

                    if (i != l_int - 1)
                    {
                        l_strWhere = l_strWhere + ", ";
                    }
                }
                l_strWhere = l_strWhere + " ) and ";
            }
        }

        //○口座コード=引数.口座コード ---------（ただし、引数.口座コードがnullでは無い場合に限る）
        if (l_strAccountCode != null)
        {
            log.debug("口座コード=引数.口座コード");
            l_strWhere = l_strWhere + "account_code = ? and ";
            l_lis.add(l_strAccountCode);
        }
        //○登録区分=引数.登録区分 ----------（ただし、引数.登録区分がnullでは無い場合に限る）
        if (l_strPaymentDiv != null && !WEB3SrvRegiRigistDivDef.EVERYTHING.equals(l_strPaymentDiv))
        {
            log.debug("登録区分=引数.登録区分");
            l_strWhere = l_strWhere + "payment_div = ? and ";
            l_lis.add(l_strPaymentDiv);
        }
        //○適用開始日>=引数.適用開始日（自） -（ただし、引数.適用開始日（自）がnullでは無い場合に限る）
        if (l_tsAppliStartDateFrom != null)
        {
            log.debug("適用開始日>=引数.適用開始日（自）");
            l_strWhere = l_strWhere + "appli_start_date >= ? and ";
            l_lis.add(l_tsAppliStartDateFrom);
        }
        //○適用開始日<=引数.適用開始日（至） -（ただし、引数.適用開始日（至）がnullでは無い場合に限る）
        if (l_tsAppliStartDateTo != null)
        {
            log.debug("適用開始日<=引数.適用開始日（至）");
            l_strWhere = l_strWhere + "appli_start_date <= ? and ";
            l_lis.add(l_tsAppliStartDateTo);
        }
        //○申込日>=引数.申込日（自） ---------（ただし、引数.申込日（自）がnullでは無い場合に限る）
        if (l_tsAppliDateFrom != null)
        {
            log.debug("申込日>=引数.申込日（自）");
            l_strWhere = l_strWhere + "appli_date >= ? and ";
            l_lis.add(l_tsAppliDateFrom);
        }
        //○申込日<=引数.申込日（至） ---------（ただし、引数.申込日（至）がnullでは無い場合に限る）
        if (l_tsAppliDateTo != null)
        {
            log.debug("申込日<=引数.申込日（至）");
            l_strWhere = l_strWhere + "appli_date <= ? and ";
            l_lis.add(l_tsAppliDateTo);
        }
        //○申込抽選区分・取消区分・自動当選取消期限日＝(*1)
        if (WEB3SrvRegiAppliLotDivDef.CANCEL.equals(l_strAppliLotDiv))
        {
            log.debug("取消");
            l_strWhere = l_strWhere + "cancel_div = ? and ";
            l_lis.add(WEB3SrvRegiCancelDivDef.CANCEL);
        }
        else if (WEB3SrvRegiAppliLotDivDef.EVERYTHING.equals(l_strAppliLotDiv))
        {
            log.debug("申込抽選区分=全ての場合");
            l_strWhere = l_strWhere + "";
        }
        else if (WEB3AppliLotDivDef.APPLI.equals(l_strAppliLotDiv))
        {
            log.debug("申込");
            l_strWhere = l_strWhere + "((appli_lot_div = ?) or " +
                "(appli_lot_div = ? and " +
                "cancel_limit_date >= ?)) and " +
                "cancel_div = ? and ";
            l_lis.add(l_strAppliLotDiv);
            l_lis.add(WEB3AppliLotDivDef.AUTO_ELECTION);
            l_lis.add(l_datSystemDate);
            l_lis.add(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);
        }
        else if (WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_strAppliLotDiv))
        {
            log.debug("当選／本申込");
            l_strWhere = l_strWhere + "((appli_lot_div = ?) or " +
                "(appli_lot_div = ? and " +
                "cancel_limit_date < ?)) and " +
                "cancel_div = ? and ";
            l_lis.add(l_strAppliLotDiv);
            l_lis.add(WEB3AppliLotDivDef.AUTO_ELECTION);
            l_lis.add(l_datSystemDate);
            l_lis.add(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);
        }
        else
        {
            log.debug("else");
            l_strWhere = l_strWhere + "appli_lot_div = ? and " +
                "cancel_div = ? and " +
                "cancel_limit_date is null and ";
            l_lis.add(l_strAppliLotDiv);
            l_lis.add(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);
        }
        //○適用終了日≧現在日時(*2)の日付部分
        l_strWhere = l_strWhere + "appli_end_date >= ? and ";
        l_lis.add(l_datSystemDate);
        //○有効区分＝"有効"
        l_strWhere = l_strWhere + "effective_div = ?";
        l_lis.add(new String(WEB3EffectiveDivDef.EFFECTIVE));

        Object[] l_bindVars = new Object[l_lis.size()];
        l_lis.toArray(l_bindVars);
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException

            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                SrvRegiApplicationRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_bindVars);

            List l_lisSrvRegiApplicationParams = new ArrayList();
            int l_intSize = l_lisRows.size();

            for(int i= 0; i < l_intSize; i++)
            {
                log.debug("i = " + i);
                SrvRegiApplicationParams l_applicationParams =
                    new SrvRegiApplicationParams((SrvRegiApplicationRow)l_lisRows.get(i));
                l_lisSrvRegiApplicationParams.add(l_applicationParams);
            }

            SrvRegiApplicationParams[] l_srvRegiApplicationParams = new SrvRegiApplicationParams[l_intSize];

            l_lisSrvRegiApplicationParams.toArray(l_srvRegiApplicationParams);

            log.exiting(STR_METHOD_NAME);
            return l_srvRegiApplicationParams;
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }

    /**
     * (getサービス申込登録一覧)<BR>
     * 現在有効なサービス申込登録を取得し、返却する。<BR>
     * <BR>
     * 1) 以下の条件で「サービス申込登録テーブル」を検索する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@部店コード＝引数.部店コード and<BR>
     * 　@サービス区分＝引数.サービス区分 and<BR>
     * 　@口座コード＝引数.口座コード and<BR>
     * 　@適用終了日≧現在日時(*1)の日付部分 and(*2)<BR>
     * 　@有効区分＝"有効" and<BR>
     * 　@取消区分＝"通常"<BR>
     * [並び順]<BR>
     * 　@適用開始日(*2)<BR>
     *   適用終了日(*3)<BR>
     * (*2)適用終了日区分がtrueの場合に条件に加える。<BR>
     * (*3)適用終了日区分がfalseの場合に条件に加える。<BR>
     * <BR>
     * 2) 戻り値の生成<BR>
     *  2-1) 検索結果が0件の場合、nullを返却する。<BR>
     * <BR>
     *  2-2) 検索結果＞0件の場合、検索結果を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@param l_blnIsAppliEndDateDiv - (適用終了日区分)<BR> 
     * @@return webbroker3.gentrade.data.SrvRegiApplicationParams[ ]
     * @@roseuid 41130761012E
     */
    public SrvRegiApplicationParams[] getServiceRegistLists(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, boolean l_blnIsAppliEndDateDiv) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getServiceRegistLists(String, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        //1) 以下の条件で「サービス申込登録テーブル」を検索する。
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            
			//障害対応 NO_2049
			List l_lisRows = null;
			
			//適用終了日を検索条件に含める場合            
            if(l_blnIsAppliEndDateDiv){            
	            String l_strWhere =
	                    "institution_code = ? and " +        //証券会社コード=引数.証券会社コード and
	                    "branch_code = ? and " +             //部店コード=引数.部店コード and
	                    "srv_div = ? and " +                 //サービス区分=引数.サービス区分 and
	                    "account_code = ? and " +            //口座コード=引数.口座コード and
	                    "appli_end_date >= ? and " +         //適用終了日≧現在日時(*1)の日付部分 and
	                    "effective_div = ? and " +           //有効区分＝"有効" and
	                    "cancel_div = ? ";                   //取消区分＝"通常"
	
				//障害対応 NO_U01711
	            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
				Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
	
	            Object l_bindVars[] = 
	            	{	l_strInstitutionCode,
	                    l_strBranchCode,
	                    l_strSrvDiv,
	                    l_strAccountCode,
						l_datSystemDate,
	                    WEB3EffectiveDivDef.EFFECTIVE,
	                    WEB3SrvRegiCancelDivDef.USUAL_DEFAULT};
	                    
				//ソートキー（適用開始日）
	            String l_strOrderBy = " appli_start_date asc";
	            
				l_lisRows =
					l_queryProcesser.doFindAllQuery(
					SrvRegiApplicationRow.TYPE,
					l_strWhere,
					l_strOrderBy,
					null,
					l_bindVars);
            }
            else
			//適用終了日を検索条件に含めない場合
            {
				String l_strWhere =
						"institution_code = ? and " +        //証券会社コード=引数.証券会社コード and
						"branch_code = ? and " +             //部店コード=引数.部店コード and
						"srv_div = ? and " +                 //サービス区分=引数.サービス区分 and
						"account_code = ? and " +            //口座コード=引数.口座コード and
						"effective_div = ? and " +           //有効区分＝"有効" and
						"cancel_div = ? ";                   //取消区分＝"通常"
	
				Object l_bindVars[] =
					{   l_strInstitutionCode,
						l_strBranchCode,
						l_strSrvDiv,
						l_strAccountCode,
						WEB3EffectiveDivDef.EFFECTIVE,
						WEB3SrvRegiCancelDivDef.USUAL_DEFAULT};
	
				//ソートキー（適用終了日）
				String l_strOrderBy = " appli_end_date desc";
				
				l_lisRows =
					l_queryProcesser.doFindAllQuery(
					SrvRegiApplicationRow.TYPE,
					l_strWhere,
					l_strOrderBy,
					null,
					l_bindVars);
            }

            //検索結果=0件の場合
            if (l_lisRows.size() == 0)
            {
                log.debug("検索結果=0件の場合");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            //検索結果＞0件の場合
            else
            {
                log.debug("検索結果＞0件の場合");
                SrvRegiApplicationParams[] l_srvRegiApplicationParams = null;
                List l_lisSrvRegiApplicationParams = new ArrayList();

                int l_intSize = l_lisRows.size();
                l_srvRegiApplicationParams = new SrvRegiApplicationParams[l_intSize];
                for (int i = 0; i < l_intSize; i++)
                {
                    log.debug("i = " + i);
                    SrvRegiApplicationParams l_applicationParams =
                        new SrvRegiApplicationParams((SrvRegiApplicationRow)l_lisRows.get(i));
                    l_lisSrvRegiApplicationParams.add(l_applicationParams);
                }

                l_lisSrvRegiApplicationParams.toArray(l_srvRegiApplicationParams);

                log.exiting(STR_METHOD_NAME);
                return l_srvRegiApplicationParams;
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }

    /**
     * (is利用可能)<BR>
     * 当該サービスが利用可能かどうか判定する。<BR>
     * <BR>
     * 1) this.getサービス申込登録( )をコールし、サービス申込登録オブジェクトを取得する。<BR>
     * [getサービス申込登録( )の引数]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * 　@部店コード=引数.部店コード<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     * 　@口座コード=引数.口座コード<BR>
     * 　@申込登録ID=引数.申込登録ID<BR>
     * 　@is行ロック=false<BR>
     * <BR>
     * 2) 以下の条件に合致する場合、trueを返却する。<BR>
     * 　@合致しない場合、falseを返却する。<BR>
     * <BR>
     * ○取得したサービス申込登録オブジェクト.get適用開始日( )の日付部分<BR>
     *   ≦現在日付(*1)の日付部分、かつ現在日付の日付部分<BR>
     *   ≦取得したサービス申込登録オブジェクト.get適用終了日( )の日付部分である<BR>
     * <BR>
     * ○サービス情報管理.getサービスマスター( )をコールし、サービスマスターオブジェクトを<BR>
     * 　@取得する。<BR>
     * [getサービスマスター( )の引数]<BR>
     * 　@証券会社コード=this.get証券会社コード( )<BR>
     * 　@サービス区分=this.getサービス区分( )<BR>
     * 　@－取得したサービスマスターオブジェクト.is提供中( )=trueである。
     * <BR>
     * ○取得したサービス申込登録オブジェクト.get申込抽選区分( )の値が<BR>
     * 　@以下のいずれかである。<BR>
     * 　@　@試用／当選（本申込）／自動当選<BR>
     * <BR>
     * (*1) GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@param l_lngRegistId - (申込登録ID)<BR>
     * @@return boolean
     * @@roseuid 416B72A802EB
     */
    public boolean isUsePossible(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, long l_lngRegistId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isUsePossible(String, String, String, String, long)";
        log.entering(STR_METHOD_NAME);

        //this.getサービス申込登録( )をコールし、サービス申込登録オブジェクトを取得する。
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
            this.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode, l_lngRegistId, false);

        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        //2)
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);
		
		//障害対応  NO_U01724
		if (WEB3DateUtility.compareToDay(l_gentradeSrvRegiApplication.getAppliStartDate(), l_tsSystemTimestamp) <= 0
            && WEB3DateUtility.compareToDay(l_tsSystemTimestamp, l_gentradeSrvRegiApplication.getAppliEndDate()) <= 0
            && l_srvRegiServiceMaster.isProviding()
            && (WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv())
            || WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv())
            || WEB3AppliLotDivDef.AUTO_ELECTION.equals(l_gentradeSrvRegiApplication.getAppliLotDiv())))
        {
            log.debug("return true");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("return false");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is取消可能)<BR>
     * 当該のサービス申込登録が取消可能なものかどうかを判定する。<BR>
     * <BR>
     * シーケンス図「（サービス利用）is取消可能」参照<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@param l_lngRegistId - (申込登録ID)<BR>
     * @@return boolean
     * @@roseuid 416B72A802FA
     */
    public boolean isCancelPossible(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, long l_lngRegistId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isCancelPossible(String, String, String, String, long)";
        log.entering(STR_METHOD_NAME);

        //1.1 <現在日時の取得>
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

        //1.2 getサービス申込登録
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
            this.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode, l_lngRegistId, false);

        //1.3 get取消区分
        String l_strCancelDiv = l_gentradeSrvRegiApplication.getCancelDiv();

        //1.4 <取消区分チェック>
        if (WEB3SrvRegiCancelDivDef.CANCEL.equals(l_strCancelDiv))
        {
            log.debug("取消区分チェック");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //1.5 get申込抽選区分
        String l_strAppliLotDiv = l_gentradeSrvRegiApplication.getAppliLotDiv();

        //1.6  <申込抽選区分チェック>
        if (WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_strAppliLotDiv)
         || WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_strAppliLotDiv)
         || WEB3AppliLotDivDef.DEFEAT.equals(l_strAppliLotDiv))
        {
            log.debug("申込抽選区分チェック");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //1.7 <get申込抽選区分( )="申込"の場合>
        if (WEB3AppliLotDivDef.APPLI.equals(l_strAppliLotDiv))
        {
            log.debug("get申込抽選区分( )=申込の場合");
            //1.7.1 getサービスマスター
            WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
//            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
//                l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

            //1.7.2 get申込日
            Timestamp l_tsAppliDate = l_gentradeSrvRegiApplication.getAppliDate();

            //1.7.3 getサービス抽選情報
            WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_strSrvDiv, l_tsAppliDate, 0);

            //1.7.4 get申込期間（自）
            //Timestamp l_tsAppliDateFrom = l_srvRegiServiceLotInfo.getAppliDateFrom();

            //1.7.5 get申込期間（至）
            Timestamp l_tsAppliDateTo = l_srvRegiServiceLotInfo.getAppliDateTo();

            //1.7.6 <申込期間のチェック>
            if (WEB3DateUtility.compareToSecond(l_tsSystemTimestamp, l_tsAppliDateTo) > 0)
            {
                log.debug("申込期間のチェック");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //1.8 <get申込抽選区分( )="自動当選"の場合>
        if (WEB3AppliLotDivDef.AUTO_ELECTION.equals(l_strAppliLotDiv))
        {
            log.debug("get申込抽選区分( )=自動当選の場合");
            //1.8.1 get自動当選取消期限日
            Timestamp l_tsCancelLimitDate = l_gentradeSrvRegiApplication.getCancelLimitDate();
			
			//障害対応  NO_U01712
            //1.8.2 <自動当選取消期限日のチェック>
            if (WEB3DateUtility.compareToDay(l_tsCancelLimitDate, l_tsSystemTimestamp) < 0)
            {
                log.debug("自動当選取消期限日のチェック");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //1.9 メッセージ true
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate取引余力)<BR>
     * 取引余力残高が十分あるかを判定する。 <BR>
     *<BR>
     * シーケンス図「（サービス利用）validate取引余力」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）validate取引余力」): <BR>
     * 1.10<is判定フラグ( )=falseの場合、余力残高エラーとして例外をスローする  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01187<BR>
     * ==========================================================<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_trader - (代理入力者)<BR>
     * @@param l_dblUseAmt - (利用料金)<BR>
     * @@param l_tsPaymentDate - (出金日)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strOrderChannel - (注文チャネル)<BR>
     * @@return boolean
     * @@roseuid 416B72A802FA
     */
    public void validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        Trader l_trader,
        double l_dblUseAmt,
        Timestamp l_tsPaymentDate,
        String l_strSrvDiv,
        String l_strOrderChannel)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateTradedPower(WEB3GentradeSubAccount, Trader, double, Timestamp, String, String, String)";
        log.entering(STR_METHOD_NAME);
        //1.1 拡張入出金注文マネージャ取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();

        //1.2 get商品ID
        Institution l_institution = l_subAccount.getInstitution();
        long l_lngProductId = l_aioOrderManager.getProductId(l_institution);

        //1.3 入出金注文内容
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(l_trader, OrderTypeEnum.CASH_OUT, AssetTransferTypeEnum.CASH_OUT, l_lngProductId,
            l_dblUseAmt, null, l_tsPaymentDate, null, null);

        //1.4 サービス利用新規注文更新インタセプタ
        WEB3SrvRegiNewOrderUpdateInterceptor l_srvRegiNewOrderUpdateInterceptor = new WEB3SrvRegiNewOrderUpdateInterceptor();

        //1.5 setサービス区分
        l_srvRegiNewOrderUpdateInterceptor.setSrvDiv(l_strSrvDiv);

        //1.6set受渡日
        l_srvRegiNewOrderUpdateInterceptor.setDeliveryDate(l_tsPaymentDate);

        //1.7 set注文経路区分
        if (l_strOrderChannel == null)
        {
            l_srvRegiNewOrderUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.ADMIN);
        }
        else if (WEB3ChannelDef.CALL_CENTER.equals(l_strOrderChannel))
        {
            l_srvRegiNewOrderUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.CALLCENTER);
        }
        else
        {
            l_srvRegiNewOrderUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.PC);
        }

        //1.8:営業日計算(Timestamp)
        WEB3GentradeBizDate l_datBizDate = new WEB3GentradeBizDate(l_tsPaymentDate);

        //1.9:roll(int)
        Timestamp l_ts = l_datBizDate.roll(-1);

        //1.10 set発注日
        l_srvRegiNewOrderUpdateInterceptor.setOrderBizDate(l_ts);

        //1.11 validate取引余力
        Object[] l_obj1 = new Object[1];
        Object[] l_obj2 = new Object[1];
        l_obj1[0] = l_srvRegiNewOrderUpdateInterceptor;
        l_obj2[0] = l_aioNewOrderSpec;
        WEB3TPTradingPowerService l_web3TPTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_web3TPTradingPowerResult =
            l_web3TPTradingPowerService.validateTradingPower(l_subAccount, l_obj1, l_obj2, OrderTypeEnum.CASH_OUT, false);

        //1.12is判定フラグ
        boolean l_blnDecisionFlag = l_web3TPTradingPowerResult.isResultFlg();

        //1.13<is判定フラグ( )=falseの場合、余力残高エラーとして例外をスローする。>
        if (!l_blnDecisionFlag)
        {
            log.debug("余力残高エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01187,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get初期申込区分)<BR>
     *  引数にて指定されたサービス、顧客の組み合わせで過去１度でも申込があったかを<BR>
     * 判定し、判定結果(*)を返却する。 <BR>
     * <BR>
     * 1) 以下の条件で「申込履歴管理テーブル」テーブルを検索する。<BR>
     * [検索条件] <BR>
　@   *  証券会社コード=引数.証券会社コード and<BR>
　@   *  部店コード=引数.部店コード and <BR>
　@   *  サービス区分=引数.サービス区分 and <BR>
　@   *  口座コード=引数.口座コード <BR>
     * <BR>
     * 2) 判定結果の返却。<BR>
     * 2-1) 1)の検索結果=0件の場合、"無"を返却する。<BR>
     * 2-2) 1)の検索結果>0件の場合、"有"を返却する。 <BR>
     * <BR>
     *  (*) [返却値内容]<BR>
     *  （サービス利用顧客サービス情報一覧共通情報.初期申込区分、<BR>
     *  サービス利用管理者アップロード確認レスポンス.初期申込区分のコード定義と同じ<BR>
     *  ただし、nullはありえない。） <BR>
     *  0:無 <BR>
     *  1:有<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * @@return String<BR>
     * @@roseuid 416B72A802FA
     */
    public String getInitializeAppliDiv(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInitializeAppliDiv(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

            String l_strWhere =
                    "institution_code = ? and " +
                    "branch_code = ? and " +
                    "srv_div = ? and " +
                    "account_code = ? ";

            Object l_bindVars[] =
                {   l_strInstitutionCode,
                    l_strBranchCode,
                    l_strSrvDiv,
                    l_strAccountCode};

            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    SrvRegiHistoryRow.TYPE,
                    l_strWhere,
                    l_bindVars);

            int l_intSize = l_lisRows.size();
            if (l_intSize == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3ConditionsValueDivDef.HAVE_NOT;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3ConditionsValueDivDef.HAVE;
            }

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

    }
    
	/**
	 * (getサービス申込登録取消対象)<BR>
	 * 取消可能なサービス申込登録を取得し、
	 * それを元にサービス申込登録オブジェクトを返却する。<BR>
	 * <BR>
	 * 1) 以下の条件で「サービス申込登録テーブル」を検索する。<BR>
	 * 　@　@（引数.is行ロックがtrueの場合、select for updateで検索を行う。）
	 * [検索条件]<BR>
	 * 　@証券会社コード=引数.証券会社コード and<BR>
	 * 　@部店コード＝引数.部店コード and<BR>
	 * 　@サービス区分＝引数.サービス区分 and<BR>
	 * 　@口座コード＝引数.口座コード and<BR>
	 * 	 適用開始日≧現在日時(*1)の日付部分 and<BR>
	 * 　@適用終了日≧現在日時(*1)の日付部分 and<BR>
	 * 　@有効区分＝"有効" and<BR>
	 * 　@取消区分＝"通常"<BR>
	 * <BR>
	 * 1)基準日 = GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
	 * <BR>
	 * 2) 戻り値の生成<BR>
	 *  2-1) 検索結果が0件の場合、nullを返却する。<BR>
	 * <BR>
	 *  2-2) 検索結果であるサービス申込登録Paramsオブジェクトを引数に、
	 *       サービス申込登録クラスのコンストラクタをコールする。
	 * <BR>
	 *  2-3) 検索結果＞1件の場合
	 *       検索結果の一番先頭のレコードをサービス申込登録Paramsオブジェクトを引数に、
	 *       サービス申込登録クラスのコンストラクタをコールする。<BR>
	 * <BR>
	 * 3) 生成したサービス申込登録オブジェクトを返却する。
	 * @@param l_strInstitutionCode - (証券会社コード)<BR>
	 * @@param l_strBranchCode - (部店コード)<BR>
	 * @@param l_strSrvDiv - (サービス区分)<BR>
	 * @@param l_strAccountCode - (口座コード)<BR>
	 * @@param l_blnIsRowLock - (is行ロック)<BR>
	 * true : 行ロックを行う   false : 行ロックを行わない<BR>
	 * @@return WEB3GentradeSrvRegiApplication
	 */
	public WEB3GentradeSrvRegiApplication getServiceRegistCancelUnit(String l_strInstitutionCode, String l_strBranchCode, String l_strSrvDiv, String l_strAccountCode, boolean l_blnIsRowLock)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getServiceRegistLists(String, String, String, String, boolean)";
		log.entering(STR_METHOD_NAME);

		//1) 以下の条件で「サービス申込登録テーブル」を検索する。
		try
		{
			QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
			String l_strWhere =
					"institution_code = ? and " +        //証券会社コード=引数.証券会社コード and
					"branch_code = ? and " +             //部店コード=引数.部店コード and
					"srv_div = ? and " +                 //サービス区分=引数.サービス区分 and
					"account_code = ? and " +            //口座コード=引数.口座コード and
					"appli_start_date >= ? and " +       //適用開始日≧現在日時(*1)の日付部分 and
					"appli_end_date >= ? and " +         //適用終了日≧現在日時(*1)の日付部分 and
					"effective_div = ? and " +           //有効区分＝"有効" and
					"cancel_div = ? ";                   //取消区分＝"通常"

			//障害対応  NO_U01711
			Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
			Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
			
			List l_lisRows = null;

			Object l_bindVars[] =
				{   l_strInstitutionCode,
					l_strBranchCode,
					l_strSrvDiv,
					l_strAccountCode,
					WEB3DateUtility.toDay(l_datSystemDate),
					WEB3DateUtility.toDay(l_datSystemDate),
					WEB3EffectiveDivDef.EFFECTIVE,
					WEB3SrvRegiCancelDivDef.USUAL_DEFAULT};
			
			if (l_blnIsRowLock)
			{
				l_lisRows =
					l_queryProcesser.doFindAllQuery(
					SrvRegiApplicationRow.TYPE,
					l_strWhere,
					" FOR UPDATE ",
					l_bindVars);
			}
			else
			{
				l_lisRows =
					l_queryProcesser.doFindAllQuery(
					SrvRegiApplicationRow.TYPE,
					l_strWhere,
					null,
					l_bindVars);
			}
			
			WEB3GentradeSrvRegiApplication l_WEB3GentradeSrvRegiApplication = null;
			SrvRegiApplicationParams l_SrvRegiApplicationParams = null;
			
			//検索結果=0件の場合
			if (l_lisRows.size() == 0)
			{
				return null;
			}
			//検索結果=1件の場合
			else if (l_lisRows.size() == 1)
			{
				l_SrvRegiApplicationParams = (SrvRegiApplicationParams)l_lisRows.get(0);
				l_WEB3GentradeSrvRegiApplication =
					new WEB3GentradeSrvRegiApplication(l_SrvRegiApplicationParams);
			}
			//検索結果＞1件の場合
			else
			{
				l_SrvRegiApplicationParams = (SrvRegiApplicationParams)l_lisRows.get(0);
				l_WEB3GentradeSrvRegiApplication =
					new WEB3GentradeSrvRegiApplication(l_SrvRegiApplicationParams);
			}
			//For Updateの場合
			if (l_blnIsRowLock)
			{
				l_WEB3GentradeSrvRegiApplication.createForUpdateParams();
			}

			log.exiting(STR_METHOD_NAME);
			return l_WEB3GentradeSrvRegiApplication;
		}
		catch (DataNetworkException l_ex)
		{
			log.error(l_ex.getMessage(), l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				getClass().getName() + STR_METHOD_NAME,
				l_ex.toString(),
				l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error(l_ex.getMessage(), l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				getClass().getName() + STR_METHOD_NAME,
				l_ex.toString(),
				l_ex);
		}
	}

    /**
     * (get取引パスワード)
     * 取引パスワードを取得する。
     * 
     * OpLoginSecurityServiceより、ログインタイプ属性を取得する。
     * －ログインタイプ属性.属性名 == 取引パスワード設定（：TRADING_PWD_ENV）の属性値が 
     *   ”0：DEFAULT（取引パスワード項目を使用しない）”の場合、引数.暗証番号を返却する 。
     * －ログインタイプ属性.属性名 == 取引パスワード設定（：TRADING_PWD_ENV）の属性値が 
     * 　@”1：取引パスワード使用”の場合、引数.補助口座より顧客オブジェクト.取引パスワードを取得し、 
     *　@返却する。※
     *
     * ※顧客.getTradingPassword()の戻り値をWEB3Crypt.decrypt()で復号したもの 
     * 
     * @@param l_subAccout (補助口座）
     * @@param l_strPassword (暗証番号）
     * @@return String
     * 
     * @@author sra518
     */
    public String getTradingPassword (SubAccount l_subAccount, String l_strPassword)
    {
        final String STR_METHOD_NAME = " getPassword(SubAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        //OpLoginSecurityServiceより、ログインタイプ属性を取得する      
        //サービスを取得
        OpLoginSecurityService l_securityService = (OpLoginSecurityService)
            Services.getService(OpLoginSecurityService.class);
        OpLoginAdminService l_admService = (OpLoginAdminService) Services.getService(
            OpLoginAdminService.class);
        
        //LoginInfo->LoginType->LoginTypeAttribute 
        LoginInfo l_loginInfo = l_securityService.getLoginInfo();
        Map l_mapAttributes = l_admService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
        
        //ログインタイプ属性.取引パスワード設定を取得する
        String l_strAttribute =
            (String) l_mapAttributes.get(
                WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);
                
        String l_strResultPassword = null;
        //取引パスワード設定＝”0：DEFAULT（取引パスワード項目を使用しない）”の場合、
        //引数.暗証番号を返却する 。
        if (WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
        {
            l_strResultPassword = l_strPassword;
        }
        //取引パスワード設定＝”1：取引パスワード使用”の場合、
        //引数.補助口座より顧客オブジェクト.取引パスワードを返却する。
        else if(WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
        {
            WEB3Crypt l_web3Crypt = new WEB3Crypt();
            l_strResultPassword = l_web3Crypt.decrypt(l_subAccount.getMainAccount().getTradingPassword());
        }
        
        log.debug("l_strResultPassword = " + l_strResultPassword);
        return l_strResultPassword;
    }

    /**
     * 入力した日付に指定した月数をプラスし、返却します。
     *
     * @@param l_dat      日付
     * @@param l_intMonth 月数
     * @@param l_strDiv   区分
     * @@return 計算後の結果を返却する。
     */
    private Date addMonth(Date l_dat, int l_intMonth, String l_strDiv)
    {
        final String STR_METHOD_NAME = " addMonth(Date, int, String)";
        log.entering(STR_METHOD_NAME);

        if (l_dat == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        if ("A".equals(l_strDiv))
        {
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_dat);

            l_cal.add(Calendar.MONTH, l_intMonth);

            log.exiting(STR_METHOD_NAME);
            return l_cal.getTime();
        }
        else
        {
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_dat);

            l_cal.add(Calendar.DATE, -1);
            l_cal.add(Calendar.MONTH, l_intMonth);

            log.exiting(STR_METHOD_NAME);
            return l_cal.getTime();
        }

    }

    /**
     * 入力した日付に指定した年数をプラスし、返却します。
     *
     * @@param l_dat      日付
     * @@param l_intYear 年数
     * @@param l_strDiv   区分
     * @@return 計算後の結果を返却する。
     */
    private Date addYear(Date l_dat, int l_intYear, String l_strDiv)
    {
        final String STR_METHOD_NAME = " addYear(Date, int, String)";
        log.entering(STR_METHOD_NAME);

        if (l_dat == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        if ("A".equals(l_strDiv))
        {
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_dat);

            l_cal.add(Calendar.YEAR, l_intYear);

            log.exiting(STR_METHOD_NAME);
            return l_cal.getTime();
        }
        else
        {
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_dat);

            l_cal.add(Calendar.DATE, -1);

            l_cal.add(Calendar.YEAR, l_intYear);

            log.exiting(STR_METHOD_NAME);
            return l_cal.getTime();
        }

    }

    /**
     * (getサービス申込属性一覧)<BR>
     * 指定された条件に合致するサービス申込属性一覧を検索し、<BR> 
     * その結果をサービス申込登録Paramsオブジェクトの配列にして返却する。<BR> 
     * <BR>
     * 1) ソート条件の作成<BR>
     * 　@引数.ソート条件がnull以外であり、かつ要素数＞0の場合、<BR>
     * 　@引数.ソート条件の件数分、以下を繰り返す。<BR>
     *  1-1) 対応するテーブルの列物理名を昇順／降順指定を付加しセットする。<BR>
     * <BR>
     * 　@　@○キー項目とテーブルの列名称との対応は以下の通り。<BR>
     * 　@　@　@※キー項目文字列(シンボル名)は、メッセージ定義書を参照。<BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照。<BR>
     * <BR>
     * 　@　@　@　@・部店     =サービス申込属性テーブル.部店コード<BR>
     * 　@　@　@　@・顧客     =サービス申込属性テーブル.口座コード<BR>
     * 　@　@　@　@・申込属性   =サービス申込属性テーブル.申込属性<BR>
     * 　@　@　@　@・適用開始日 =サービス申込属性テーブル.適用期間From<BR>
     * 　@　@　@　@・適用終了日 =サービス申込属性テーブル.適用期間To<BR>
     * 　@　@　@　@・処理区分   =サービス申込属性テーブル.処理区分<BR>
     * 　@　@　@　@・最終更新日 =サービス申込属性テーブル.更新日付<BR>
     * 　@　@　@　@・最終更新者 =サービス申込属性テーブル.更新者コード<BR>
     * <BR>
     * 　@　@○昇順／降順指定は、ソートキー.昇順／降順 指定に従い設定する。<BR>
     * <BR>
     * 2) 以下の検索条件で、「サービス申込属性テーブル」を検索する。<BR>
     * 　@[検索条件]<BR>
     * 　@　@○証券会社コード=引数.証券会社コード<BR>
     * 　@　@○部店コード=引数.部店コード ---------（ただし、引数.部店コードがnullでは無い場合に限る）<BR>
     *     ○サービス区分=引数.サービス区分<BR>
     * 　@　@○口座コード=引数.口座コード ---------（ただし、引数.口座コードがnullでは無い場合に限る）<BR>
     * 　@　@○申込属性区分= <BR>
     *       引数.申込抽選区分が'7'の場合、'1'(無料対象)を設定<BR>
     *       引数.申込抽選区分が'8'の場合、'2'(申込不可)を設定<BR>
     *       引数.申込抽選区分が'9'の場合、この検索条件は設定しない<BR>
     * <BR>
     * 　@　@○適用期間From、適用期間To(引数.適用日がnullではない場合、以下の検索条件を追加する。)<BR>
     *     (適用期間From <= 引数.適用日 or 適用期間From == null) and (適用期間To >= 引数.適用日 
     *     or 適用期間To == null)<BR>
     * <BR>
     * 　@[並び順]<BR>
     * 　@　@1)で生成したソート条件<BR>
     * <BR>
     * 3) 2)の検索結果を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード（必須）<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コード一覧<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分（必須）<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strAppliLotDiv - (申込抽選区分)<BR>
     * 7:無料対象　@8:申込不可　@9:全て<BR>
     * @@param l_tsAppDate - (適用日)<BR>
     * 適用日<BR>
     * @@param l_sortCondition - (ソート条件)<BR>
     * 対象項目:<BR> 
     * "部店","顧客","申込属性","適用開始日","適用終了日","最終更新日","最終更新者"<BR> 
     * <BR>
     * @@return 操作 サービス申込属性Params[]
     */
    public SrvAppliAttributeParams[] getServiceAttributeLists(String l_strInstitutionCode, String[] l_strBranchCodes,
        String l_strSrvDiv, String l_strAccountCode, String l_strAppliLotDiv, Timestamp l_tsAppDate,
        WEB3SrvRegiSortKey[] l_sortConditions) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getServiceAttributeLists(String, String[], String, String, String, " +
            "Timestamp, WEB3SrvRegiSortKey[])";
        log.entering(STR_METHOD_NAME);

        String l_strWhere = null;
        List l_lisWhere = new ArrayList();
        String l_strOrderBy = "";

        //1) ソート条件の作成
        if (l_sortConditions != null && l_sortConditions.length > 0)
        {
            //引数.ソート条件がnull以外であり、かつ要素数＞0の場合、 
            //引数.ソート条件の件数分、以下を繰り返す。
            //1-1) 対応するテーブルの列物理名を昇順／降順指定を付加しセットする。
            int l_intLength = l_sortConditions.length;
            for (int i = 0; i < l_intLength; i++)
            {
                if (WEB3SrvRegiKeyItemDef.BRANCH_CODE.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("branch_code asc");
                        l_strOrderBy = l_strOrderBy + " branch_code asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("branch_code desc");
                        l_strOrderBy = l_strOrderBy + " branch_code desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.ACCOUNT_CODE.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("account_code asc");
                        l_strOrderBy = l_strOrderBy + " account_code asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("account_code desc");
                        l_strOrderBy = l_strOrderBy + " account_code desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_ATTRIBUTE.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("appli_attribute asc");
                        l_strOrderBy = l_strOrderBy + " appli_attribute asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("appli_attribute desc");
                        l_strOrderBy = l_strOrderBy + " appli_attribute desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_START_DATE.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("appli_start_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_start_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("appli_start_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_start_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.APPLI_END_DATE.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("appli_end_date asc");
                        l_strOrderBy = l_strOrderBy + " appli_end_date asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("appli_end_date desc");
                        l_strOrderBy = l_strOrderBy + " appli_end_date desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.TRANSACTION_DIV.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("proc_div");
                        l_strOrderBy = l_strOrderBy + " proc_div asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("proc_div desc");
                        l_strOrderBy = l_strOrderBy + " proc_div desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.LAST_UPDATED_TIMESTAMP.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("last_updated_timestamp asc");
                        l_strOrderBy = l_strOrderBy + " last_updated_timestamp asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("last_updated_timestamp desc");
                        l_strOrderBy = l_strOrderBy + " last_updated_timestamp desc";
                    }
                }
                else if (WEB3SrvRegiKeyItemDef.LAST_UPDATER.equals(l_sortConditions[i].keyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("last_updater asc");
                        l_strOrderBy = l_strOrderBy + " last_updater asc";
                    }
                    else if (WEB3AscDescDef.DESC.equals(l_sortConditions[i].ascDesc))
                    {
                        log.debug("last_updater desc");
                        l_strOrderBy = l_strOrderBy + " last_updater desc";
                    }
                }
                if(i != l_intLength - 1)
                {
                    l_strOrderBy = l_strOrderBy + ", ";
                }
            }
        }

        //2) 以下の検索条件で、「サービス申込属性テーブル」を検索する。

        //○証券会社コード=引数.証券会社コード
        l_strWhere = "institution_code = ? ";
        l_lisWhere.add(new String(l_strInstitutionCode));

        //○部店コード=引数.部店コード ---------（ただし、引数.部店コードがnullでは無い場合に限る）
        if (l_strBranchCodes != null)
        {
            if (l_strBranchCodes.length == 1)
            {
                log.debug("部店コード=引数.部店コード");
                l_strWhere = l_strWhere + "and branch_code = ? ";
                l_lisWhere.add(l_strBranchCodes[0]);
            }
            else
            {
                log.debug("部店コード in 引数.部店コード");
                l_strWhere = l_strWhere + "and branch_code in ( ";
                int l_int = l_strBranchCodes.length;
                for (int i = 0; i < l_int; i++)
                {
                    l_strWhere = l_strWhere + " ?";
                    l_lisWhere.add(l_strBranchCodes[i]);

                    if (i != l_int - 1)
                    {
                        l_strWhere = l_strWhere + ", ";
                    }
                }
                l_strWhere = l_strWhere + " ) ";
            }
        }

        //○サービス区分=引数.サービス区分
        l_strWhere = l_strWhere + "and srv_div = ? ";
        l_lisWhere.add(new String(l_strSrvDiv));

        //○口座コード=引数.口座コード ---------（ただし、引数.口座コードがnullでは無い場合に限る）
        if (l_strAccountCode != null)
        {
            log.debug("口座コード=引数.口座コード");
            l_strWhere = l_strWhere + "and account_code = ? ";
            l_lisWhere.add(l_strAccountCode);
        }

        //○申込属性区分= 
        if (WEB3SrvRegiAppliLotDivDef.FREE_OBJECT.equals(l_strAppliLotDiv))
        {
            //引数.申込抽選区分が'7'の場合、'1'(無料対象)を設定
            log.debug("申込属性区分='1'(無料対象)");
            l_strWhere = l_strWhere + "and appli_attribute = ? ";
            l_lisWhere.add(WEB3AppliAttributeDef.FREE_OBJECT);
        }
        else if (WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI.equals(l_strAppliLotDiv))
        {
            //引数.申込抽選区分が'8'の場合、'2'(申込不可)を設定
            log.debug("申込属性区分='2'(申込不可)");
            l_strWhere = l_strWhere + "and appli_attribute = ? ";
            l_lisWhere.add(WEB3AppliAttributeDef.CANNOT_APPLI);
        }

        //○適用期間From、適用期間To(引数.適用日がnullではない場合、以下の検索条件を追加する。)
        if (l_tsAppDate != null)
        {
            //(適用期間From <= 引数.適用日 or 適用期間From == null)
            log.debug("(適用期間From <= 引数.適用日 or 適用期間From == null)");
            l_strWhere = l_strWhere + "and ((appli_start_date <= ? or appli_start_date is null) ";
            l_lisWhere.add(l_tsAppDate);

            //and (適用期間To >= 引数.適用日 or 適用期間To == null)
            log.debug(" and (適用期間To >= 引数.適用日 or 適用期間To == null) ");
            l_strWhere = l_strWhere + "and (appli_end_date >= ? or appli_end_date is null)) ";
            l_lisWhere.add(l_tsAppDate);
        }

        Object[] l_bindVars = new Object[l_lisWhere.size()];
        l_lisWhere.toArray(l_bindVars);
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                SrvAppliAttributeRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_bindVars);

            List l_lisSrvAppliAttributeParams = new ArrayList();
            int l_intSize = l_lisRows.size();
            for(int i= 0; i < l_intSize; i++)
            {
                log.debug("i = " + i);
                SrvAppliAttributeParams l_applicationParams =
                    new SrvAppliAttributeParams((SrvAppliAttributeRow)l_lisRows.get(i));
                l_lisSrvAppliAttributeParams.add(l_applicationParams);
            }

            SrvAppliAttributeParams[] l_srvAppliAttributeParams = new SrvAppliAttributeParams[l_intSize];
            l_lisSrvAppliAttributeParams.toArray(l_srvAppliAttributeParams);

            //2)の検索結果を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_srvAppliAttributeParams;
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }
}
@
