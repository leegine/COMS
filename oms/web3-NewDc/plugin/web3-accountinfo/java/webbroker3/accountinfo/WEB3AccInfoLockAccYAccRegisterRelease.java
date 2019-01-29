head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLockAccYAccRegisterRelease.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  ロック客Y客登録解除(WEB3AccInfoLockAccYAccRegisterRelease)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.data.HostLockRegistParams;
import webbroker3.accountinfo.data.HostLockRegistRow;
import webbroker3.accountinfo.define.WEB3AccInfoAttributeDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoOrderPermitDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoRegiEraseDivDef;
import webbroker3.accountinfo.define.WEB3MngLockCancelDivDef;
import webbroker3.accountinfo.define.WEB3OrderPermitDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoStopInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3YellowCustomerDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ( ロック客Y客登録解除)<BR>
 *  ロック客Y客登録解除<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoLockAccYAccRegisterRelease implements BusinessObject
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoLockAccYAccRegisterRelease.class); 
    
    /**
     * (ロック客Y客登録解除行オブジェクト)
     *ロック客Y客登録解除行オブジェクト <BR>
     *<BR>
     *※ ロック客Y客登録解除ParamsクラスはDDLより自動生成する。<BR>
     */
    private HostLockRegistParams hostLockRegistParams;
    
    public WEB3AccInfoLockAccYAccRegisterRelease(HostLockRegistParams l_params)
    {
        this.hostLockRegistParams = l_params;
    }
    
    /**
     * （getDataSourceObjectの実装） <BR>
     * <BR>
     * this.ロック顧客登録行を返却する。  <BR>
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        return this.hostLockRegistParams;
    }
    
    /**
     * this.ロック顧客登録行をコピーして、<BR>
     * 同じ内容の別インスタンスを作成する（clone）。 <BR> 
     * 作成したコピーを自身のthis.ロック顧客登録行にセットする。<BR> 
     */
    public void createForUpdateParams()
    {
        HostLockRegistParams l_params = new HostLockRegistParams(this.hostLockRegistParams);
        this.hostLockRegistParams = l_params;
    }

    
    /**
     * (getロック客Y客登録解除)
     * 顧客に該当する最新のロック客Y客登録解除を取得する。 <BR>  
     * <BR> 
     * １）　@ロック客Y客登録解除キューテーブル検索  <BR> 
     * 　@ロック客Y客登録解除キューテーブルを以下の条件で検索し、<BR>  
     * 　@最新のロック客Y客登録解除行オブジェクトを取得する。 <BR> 
     * <BR> 
     * 　@[検索条件]  <BR> 
     * 　@データコード = データコード <BR> 
     * 　@証券会社コード = 顧客.getInstitution().getInstitutionCode() <BR> 
     * 　@部店コード = 顧客.getBranch().getBranchCode() <BR> 
     * 　@口座コード = 顧客.getAccountCode() <BR> 
     * 　@更新日付 =　@最新の更新日付 <BR> 
     * <BR> 
     * ２）　@ロック客Y客登録解除オブジェクト返却  <BR> 
     * 　@取得した行オブジェクトについて、ロック客Y客登録解除オブジェクトを生成し返却する。<BR>   
     * 　@行が取得できなかった場合は、nullを返却する。 <BR>  
     */
    public static WEB3AccInfoLockAccYAccRegisterRelease 
        getWEB3AccLockAccYAccRecordRelease(MainAccount l_mainAccount, String l_strDateCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWEB3AccLockAccYAccRecordRelease(MainAccount l_mainAccount, String l_strDateCode)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_strDateCode == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }
        
        List l_lisRecords = null;

        try
        {

            //１）　@ロック客Y客登録解除キューテーブル検索

            //[条件]
            //データコード = データコード 
            //証券会社コード = 顧客.getInstitution().getInstitutionCode() 
            //部店コード = 顧客.getBranch().getBranchCode()
            //口座コード = 顧客.getAccountCode()
            //更新日付 =　@最新の更新日付 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("request_code = ?");
            l_sbQueryString.append(" and institution_code = ?");
            l_sbQueryString.append(" and branch_code = ?");
            l_sbQueryString.append(" and account_code = ?");

            Object[] l_queryDataContainer = new Object[] {
                l_strDateCode,
                l_mainAccount.getInstitution().getInstitutionCode(),
                "" + l_mainAccount.getBranch().getBranchCode(),
                "" + l_mainAccount.getAccountCode()
                };

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                HostLockRegistRow.TYPE,
                l_sbQueryString.toString(),
                "last_updated_timestamp DESC",
                null,
                l_queryDataContainer);
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //行が取得できなかった場合は、nullを返却する。 
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            return null;
        }

        HostLockRegistRow l_hostLockRegistRow = (HostLockRegistRow)l_lisRecords.get(0);
        
        HostLockRegistParams l_params = new HostLockRegistParams(l_hostLockRegistRow);

        log.exiting(STR_METHOD_NAME);
        //）　@ロック客Y客登録解除オブジェクト返却
        return new WEB3AccInfoLockAccYAccRegisterRelease(l_params);
    }
    
    /**
     * (saveNewロック顧客登録解除 )<BR> 
     * ロック客Y客登録解除キューテーブルを更新(insert)する。 <BR>  
     * <BR> 
     * １） ロック客Y客登録解除オブジェクトを生成する。 <BR> 
     * <BR> 
     * ２） 更新情報をセット <BR> 
     * 　@１）のロック客Y客登録解除行の項目に値をセットする。  <BR> 
     * <BR> 
     * 　@DB更新仕様 <BR> 
     * 　@　@停止状況変更_ロック客Y客登録解除キューテーブル.xls <BR> 
     * 　@　@「ロック客Y客登録解除キューテーブルDB更新 （ロック客）」シート参照 <BR>  
     * 　@※ＤＢ更新仕様のロック客Y客登録解除オブジェクトは引数.ロック客Y客登録解除オブジェクトを指す。 <BR> 
     * <BR> 
     * ３） DB更新  <BR> 
     * 　@１）のロック客Y客登録解除行オブジェクトの内容で、ロック客Y客登録解除キューテーブルを更新（insert）する。 <BR> 
     * <BR> 
     * ４） HOSTへ送信 <BR> 
     * 　@１）のオブジェクト.ロック客Ｙ客登録解除送信()にて、ロック客Y客登録解除キューをHOSTへ送信する。<BR>   
     */
    public void saveNewLockAccRegisterRelease(MainAccount l_mainAccount, WEB3AccInfoLockAccYAccRegisterRelease l_release) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveNewLockAccRegisterRelease(MainAccount l_mainAccount, WEB3AccInfoLockAccYAccRegisterRelease l_release)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_release == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME);
        }
        
        HostLockRegistParams l_params = (HostLockRegistParams)l_release.getDataSourceObject();
        //１） ロック客Y客登録解除オブジェクトを生成する。
        HostLockRegistParams l_hostLokRegistParams = new HostLockRegistParams();
        
        //２） 更新情報をセット１）のロック客Y客登録解除行の項目に値をセットする。
        //データコード
        l_hostLokRegistParams.setRequestCode(WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL);
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        
        //証券会社コード = 顧客.証券会社コード
        l_hostLokRegistParams.setInstitutionCode(l_strInstitutionCode);
        //部店コード = 顧客.部店コード
        l_hostLokRegistParams.setBranchCode(l_strBranchCode);
        //口座コード = 顧客.口座コード
        l_hostLokRegistParams.setAccountCode(l_mainAccount.getAccountCode());
        //扱者コード = ブランク
        l_hostLokRegistParams.setTraderCode("     ");
        WEB3HostReqOrderNumberManageService l_orderNumberManagerService = 
            (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);
        //識別コード = 識別コード採番サービス.get新規識別コード（）
        String l_strNewNumber = 
            l_orderNumberManagerService.getNewNumber(l_strInstitutionCode, l_strBranchCode, ProductTypeEnum.OTHER);
        l_hostLokRegistParams.setOrderRequestNumber(l_strNewNumber);
        
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        //管理解除開始 = 顧客.管理ロック解除開始日
        l_hostLokRegistParams.setBeforeMngLockStartDate(l_mainAccountRow.getMngLockOffStartDate());
        //管理解除終了 = 顧客.管理ロック解除終了日
        l_hostLokRegistParams.setBeforeMngLockEndDate(l_mainAccountRow.getMngLockOffEndDate());
        
        //支店ロック = 顧客.支店ロック
        l_hostLokRegistParams.setBeforeBranchLock(l_mainAccountRow.getBranchLock());
        //注文認可 = 顧客.注文認可
        l_hostLokRegistParams.setBeforeOrderPermission(l_mainAccountRow.getOrderPermission());
        //登録理由 = 顧客.停止状況登録理由
        l_hostLokRegistParams.setBeforeLockRegistReason(l_mainAccountRow.getLockRegistrationReason());
        //Y客 = null;
        l_hostLokRegistParams.setBeforeYellowCustomer(null);
        //管理解除区分 = ロック客Y客登録解除オブジェクト.管理解除区分
        l_hostLokRegistParams.setMngLockCancelDiv(l_params.getMngLockCancelDiv());
        //管理解除開始 = ロック客Y客登録解除オブジェクト.管理解除開始
        l_hostLokRegistParams.setMngLockCancelStartDate(l_params.getMngLockCancelStartDate());
        //管理解除終了 = ロック客Y客登録解除オブジェクト.管理解除終了
        l_hostLokRegistParams.setMngLockCancelEndDate(l_params.getMngLockCancelEndDate());
        //支店ロック = ロック客Y客登録解除オブジェクト.支店ロック
        l_hostLokRegistParams.setBranchLock(l_params.getBranchLock());
        //注文認可 = ロック客Y客登録解除オブジェクト.注文認可
        l_hostLokRegistParams.setOrderPermission(l_params.getOrderPermission());
        //登録理由 = ロック客Y客登録解除オブジェクト.登録理由
        l_hostLokRegistParams.setLockRegistrationReason(l_params.getLockRegistrationReason());
        //属性 = null
        l_hostLokRegistParams.setAttribute(null);
        //登録抹消区分 = null
        l_hostLokRegistParams.setRegiEraseDiv(null);
        //処理区分 = 0：未処理
        l_hostLokRegistParams.setStatus(WEB3StatusDef.NOT_DEAL);
        //作成日付 = 処理日時
        l_hostLokRegistParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付 = 処理日時
        l_hostLokRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //３） DB更新  
        //１）のロック客Y客登録解除行オブジェクトの内容で、
        //ロック客Y客登録解除キューテーブルを更新（insert）する。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_hostLokRegistParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        /*
         * ４） HOSTへ送信 
         * 　@１）のオブジェクト.ロック客Ｙ客登録解除送信()にて、
         * ロック客Y客登録解除キューをHOSTへ送信する。
         */
		WEB3AccInfoLockAccYAccRegisterRelease l_lockAccYAccRegisterRelease = new WEB3AccInfoLockAccYAccRegisterRelease(l_hostLokRegistParams);
        l_lockAccYAccRegisterRelease.lockAccYAccRegisterSend();
    }
    
    /**
     * (saveNewY客登録解除)<BR> 
     * ロック客Y客登録解除キューテーブルを更新(insert)する。<BR>  
     * <BR> 
     * １） ロック客Y客登録解除オブジェクトを生成する。<BR> 
     * <BR> 
     * ２） 更新情報をセット<BR> 
     * 　@１）のロック客Y客登録解除行の項目に値をセットする。 <BR> 
     * <BR> 
     * 　@DB更新仕様<BR> 
     *    停止状況変更_ロック客Y客登録解除キューテーブル.xls<BR> 
     *    「ロック客Y客登録解除キューテーブルDB更新(Y客）」シート参照 <BR> 
     * 　@※ＤＢ更新仕様のロック客Y客登録解除オブジェクトは引数.ロック客Y客登録解除オブジェクトを指す。<BR> 
     * <BR> 
     * ３） DB更新 <BR> 
     * 　@１）のロック客Y客登録解除行オブジェクトの内容で、ロック客Y客登録解除キューテーブルを更新（insert）する。<BR> 
     * <BR> 
     * ４） HOSTへ送信<BR> 
     * 　@１）のオブジェクト.ロック客Ｙ客登録解除送信()にて、ロック客Y客登録解除キューをHOSTへ送信する。<BR> 
     */
    public void saveNewYAccRegisterRelease(MainAccount l_mainAccount, WEB3AccInfoLockAccYAccRegisterRelease l_release) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveNewYAccRegisterRelease(MainAccount l_mainAccount, WEB3AccInfoLockAccYAccRegisterRelease l_release)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_release == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME);
        }
        HostLockRegistParams l_params = (HostLockRegistParams)l_release.getDataSourceObject();
        //１） ロック客Y客登録解除オブジェクトを生成する。
        HostLockRegistParams l_hostLokRegistParams = new HostLockRegistParams();
        
        //２） 更新情報をセット１）のロック客Y客登録解除行の項目に値をセットする。
        //データコード
        l_hostLokRegistParams.setRequestCode(WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL);
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        
        //証券会社コード = 顧客.証券会社コード
        l_hostLokRegistParams.setInstitutionCode(l_strInstitutionCode);
        //部店コード = 顧客.部店コード
        l_hostLokRegistParams.setBranchCode(l_strBranchCode);
        //口座コード = 顧客.口座コード
        l_hostLokRegistParams.setAccountCode(l_mainAccount.getAccountCode());
        //扱者コード = ブランク
        l_hostLokRegistParams.setTraderCode("     ");
        WEB3HostReqOrderNumberManageService l_orderNumberManagerService = 
            (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);
        //識別コード = 識別コード採番サービス.get新規識別コード（）
        String l_strNewNumber = 
            l_orderNumberManagerService.getNewNumber(l_strInstitutionCode, l_strBranchCode, ProductTypeEnum.OTHER);
        l_hostLokRegistParams.setOrderRequestNumber(l_strNewNumber);
        
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        //管理解除開始 = null
        l_hostLokRegistParams.setBeforeMngLockStartDate(null);
        //管理解除終了 = null
        l_hostLokRegistParams.setBeforeMngLockEndDate(null);
        
        //支店ロック = null
        l_hostLokRegistParams.setBeforeBranchLock(null);
        //注文認可 = null
        l_hostLokRegistParams.setBeforeOrderPermission(null);
        //登録理由 = null
        l_hostLokRegistParams.setBeforeLockRegistReason(null);
        //Y客 = 顧客.Y客区分;
        l_hostLokRegistParams.setBeforeYellowCustomer(l_mainAccountRow.getYellowCustomer());
        //管理解除区分 = null
        l_hostLokRegistParams.setMngLockCancelDiv(null);
        //管理解除開始 = null
        l_hostLokRegistParams.setMngLockCancelStartDate(null);
        //管理解除終了 = null
        l_hostLokRegistParams.setMngLockCancelEndDate(null);
        //支店ロック = null
        l_hostLokRegistParams.setBranchLock(null);
        //注文認可 = null
        l_hostLokRegistParams.setOrderPermission(null);
        //登録理由 = null
        l_hostLokRegistParams.setLockRegistrationReason(null);
        //属性 = ロック客Y客登録解除オブジェクト.属性
        l_hostLokRegistParams.setAttribute(l_params.getAttribute());
        //登録抹消区分 = ロック客Y客登録解除オブジェクト.登録抹消区分
        l_hostLokRegistParams.setRegiEraseDiv(l_params.getRegiEraseDiv());
        //処理区分 = 0：未処理
        l_hostLokRegistParams.setStatus(WEB3StatusDef.NOT_DEAL);
        //作成日付 = 処理日時
        l_hostLokRegistParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付 = 処理日時
        l_hostLokRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //３） DB更新  <BR> 
        //１）のロック客Y客登録解除行オブジェクトの内容で、
        //ロック客Y客登録解除キューテーブルを更新（insert）する。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_hostLokRegistParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        /*
         * ４） HOSTへ送信 <BR> 
         * 　@１）のオブジェクト.ロック客Ｙ客登録解除送信()にて、
         * ロック客Y客登録解除キューをHOSTへ送信する。<BR> 
         */
		WEB3AccInfoLockAccYAccRegisterRelease l_lockAccYAccRegisterRelease = new WEB3AccInfoLockAccYAccRegisterRelease(l_hostLokRegistParams);
		l_lockAccYAccRegisterRelease.lockAccYAccRegisterSend();
    }    
    
    /**
     * (ロック客Y客登録解除送信 )<BR> 
     * ロック客Y客登録解除キューをHOSTへ送信する。<BR>  
     * <BR>
     * シーケンス図  <BR>
     * 「ロック客Y客登録解除送信」参照。  <BR>
     */
    public void lockAccYAccRegisterSend() throws WEB3BaseException
    {
        //市場開局時間帯であるかを判定する。 
        boolean l_blnIsMarketTrigger = 
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);
        //isトリガ発行（）の戻り値==trueの場合、MQトリガを実施する。
        String l_strInstitutionCode = null;
        String l_strRequestCode = null;
        String l_strUserData = null;
        if (l_blnIsMarketTrigger)
        {
            //証券会社コードを取得する。
            l_strInstitutionCode = this.getInstitutionCode();
            //データコードを取得する。
            l_strRequestCode = this.getRequestCode() + "T";
            //ユーザデータを取得する。
            l_strUserData = this.getUserData();

            /*
             * MQトリガパラメータを生成する。
             * 
             * [コンストラクタの引数] 
             * 証券会社コード：　@get証券会社コード() 
             * データコード：　@getデータコード() 
             * ユーザデータ：　@getユーザデータ() 
             */
            WEB3MQMessageSpec l_messageSpec =
                new WEB3MQMessageSpec(
                    l_strInstitutionCode,
                    l_strRequestCode,
                    l_strUserData);
            /*
             * MQトリガを実施する。 
             * [send()に指定する引数] 
             * MQメッセージ内容：　@（生成したオブジェクト）
             */
            WEB3MQGatewayService l_gateWayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);
            l_gateWayService.send(l_messageSpec);
        }
    }
    
    /**
     * (get処理区分)<BR> 
     * 処理区分を取得する。 <BR> 
     * <BR> 
     * this.ロック客Ｙ客登録解除行.処理区分を返却する。 <BR> 
     */
    public String getStatus()
    {
        return hostLockRegistParams.getStatus();
    }
    
    /**
     * (cｒeateChangeロック客Y客登録解除)<BR> 
     * １）行オブジェクトの生成  <BR> 
     * 　@　@ロック客Y客登録解除Paramsオブジェクトを生成する。<BR>   
     * <BR> 
     * 　@※ロック客Y客登録解除ParamsはDDLより自動生成する。  <BR> 
     * <BR> 
     * ２）プロパティセット <BR> 
     * 　@　@停止情報の以下の項目について、顧客行（顧客.getDataSourceObject()）の対応項目の値と比較し、 <BR> 
     * 　@　@変更があった項目は、１）で生成したロック客Y客登録解除Paramsオブジェクトにプロパティをセットする。 <BR> 
     * <BR> 
     * 　@ロック客Y客登録解除Params.（送信用）管理解除区分 = 停止情報.管理ロック解除開始日 != 顧客行.管理ロック解除開始日 And、<BR>  
     * 　@　@　@　@　@   停止情報.管理ロック解除開始日 == null　@　@And、 <BR> 
     * 　@　@　@　@　@　@ 停止情報.管理ロック解除終了日 == null　@の場合、 <BR> 
     * 　@　@　@　@　@　@ 0：未登録 をセットする。 <BR> 
     * <BR> 
     *             停止情報.管理ロック解除開始日 != 顧客行.管理ロック解除開始日 And、<BR>  
     * 　@　@　@　@　@　@ 停止情報.管理ロック解除開始日 != null　@　@And、 <BR> 
     * 　@　@　@　@　@　@ 停止情報.管理ロック解除終了日 != null　@の場合、<BR>  
     * 　@　@　@　@　@　@ 1：登録 をセットする。<BR>  
     * 　@ロック客Y客登録解除Params.（送信用）管理解除開始 = 停止情報.管理ロック解除開始日 != 顧客行.管理ロック解除開始日 の場合、 <BR> 
     * 　@　@　@　@　@    停止情報.管理ロック解除開始日をセットする。 <BR> 
     * 　@<BR> 
     * ロック客Y客登録解除Params.（送信用）管理解除終了 = 停止情報.管理ロック解除終了日 != 顧客行.管理ロック解除終了日 の場合、<BR>  
     * 　@　@　@　@　@   停止情報.管理ロック解除終了日をセットする。 <BR> 
     * <BR> 
     * 　@ロック客Y客登録解除Params.（送信用）支店ロック = 停止情報.支店ロック区分 != 顧客行.支店ロックの場合、 <BR> 
     * 　@　@　@　@　@   停止情報.支店ロック区分をセットする。<BR>  
     * <BR> 
     * 　@ロック客Y客登録解除Params.（送信用）注文認可 = 停止情報.注文認可区分 != 顧客行.注文認可 の場合、 <BR> 
     * 　@　@　@　@　@   停止情報.注文認可区分(*1)をセットする。<BR>  
     * <BR> 
     * 　@ロック客Y客登録解除Params.（送信用）登録理由 = 停止情報.停止状況登録理由 != 顧客行.停止状況登録理由 の場合、<BR>  
     * 　@　@　@　@　@   停止情報.停止状況登録理由をセットする。 <BR> 
     * <BR> 
     *   ロック客Y客登録解除Params.（送信用）属性 = 停止情報.Ｙ客区分 != 顧客行.Y客区分 の場合、<BR>  
     * 　@　@　@　@　@   8：Y客 をセットする。<BR>  
     *   ロック客Y客登録解除Params.（送信用）登録抹消区分 = 停止情報.Ｙ客区分 != 顧客行.Y客区分 の場合、<BR>  
     * 　@　@　@　@　@   停止情報.Ｙ客区分(*2)をセットする。 <BR> 
     * <BR> 
     *  (*1) 停止情報.注文認可区分==0:認可　@の場合、１：認可　@をセットする。 <BR> 
     *        停止情報.注文認可区分==1:非認可　@の場合、0：非認可　@をセットする。<BR>  
     * <BR> 
     *  (*2) 停止情報.Y客区分 == 1:Y客　@の場合、１：登録　@をセットする。 <BR> 
     *        停止情報.Y客区分 == 0:Y客でない　@の場合、9：抹消　@をセットする。 <BR> 
     * <BR> 
     * ３）ロック客Y客登録解除オブジェクト返却  <BR> 
     * 　@プロパティをセットした行オブジェクトを指定し、ロック客Y客登録解除オブジェクトを生成し返却する。  <BR> 

     */
    public static WEB3AccInfoLockAccYAccRegisterRelease createChangeLockAccYAccRegisterRelease(MainAccount l_mainAccount, WEB3AccInfoStopInfo l_stopInfo)
    {
        final String STR_METHOD_NAME = "cｒeateChangeLockAccYAccRegisterRelease(MainAccount l_mainAccount, WEB3AccInfoStopInfo l_stopInfo)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_stopInfo == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }
        
        /*
         * １）行オブジェクトの生成 
         * 　@　@ロック客Y客登録解除Paramsオブジェクトを生成する。 
         * 
         * 　@※ロック客Y客登録解除ParamsはDDLより自動生成する。 
         */
        HostLockRegistParams l_hostLokRegistParams = new HostLockRegistParams();
        
        /*２）プロパティセット 
         * 　@　@停止情報の以下の項目について、顧客行（顧客.getDataSourceObject()）の対応項目の値と比較し、 
         * 　@　@変更があった項目は、１）で生成したロック客Y客登録解除Paramsオブジェクトにプロパティをセットする。
         * 　@ロック客Y客登録解除Params.（送信用）管理解除区分 = 停止情報.管理ロック解除開始日 != 顧客行.管理ロック解除開始日 And、  
         * 　@　@　@　@　@   停止情報.管理ロック解除開始日 == null　@　@And、  
         * 　@　@　@　@　@　@ 停止情報.管理ロック解除終了日 == null　@の場合、  
         * 　@　@　@　@　@　@ 0：未登録 をセットする。 
         */
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        if (WEB3DateUtility.compare(l_stopInfo.mngLockCancelStartDate, l_mainAccountRow.getMngLockOffStartDate()) != 0
            && l_stopInfo.mngLockCancelStartDate == null 
            && l_stopInfo.mngLockCancelEndDate == null)
        {
            l_hostLokRegistParams.setMngLockCancelDiv(WEB3MngLockCancelDivDef.NOTREGISTER);
        }
        
        /*
         *停止情報.管理ロック解除開始日 != 顧客行.管理ロック解除開始日 And、
         * 　@　@　@　@　@　@ 停止情報.管理ロック解除開始日 != null　@　@And、  
         * 　@　@　@　@　@　@ 停止情報.管理ロック解除終了日 != null　@の場合、
         * 　@　@　@　@　@　@ 1：登録 をセットする。 
         */
        if (WEB3DateUtility.compare(l_stopInfo.mngLockCancelStartDate, l_mainAccountRow.getMngLockOffStartDate()) != 0
            && l_stopInfo.mngLockCancelStartDate != null 
            && l_stopInfo.mngLockCancelEndDate != null)
        {
            l_hostLokRegistParams.setMngLockCancelDiv(WEB3MngLockCancelDivDef.REGISTER);
        }
        
        /*
         * 　@ロック客Y客登録解除Params.（送信用）管理解除開始 = 
         *   停止情報.管理ロック解除開始日 != 顧客行.管理ロック解除開始日 の場合、 
         * 　@　@　@　@停止情報.管理ロック解除開始日をセットする。 
         */       
        if (WEB3DateUtility.compare(l_stopInfo.mngLockCancelStartDate, l_mainAccountRow.getMngLockOffStartDate()) != 0)
        {
            l_hostLokRegistParams.setMngLockCancelStartDate(l_stopInfo.mngLockCancelStartDate);
        }
        else
        {
            l_hostLokRegistParams.setMngLockCancelStartDate(null);
        }
        
        /*
         * ロック客Y客登録解除Params.（送信用）
         * 管理解除終了 = 停止情報.管理ロック解除終了日 != 顧客行.管理ロック解除終了日 の場合、
         * 　@　@　@停止情報.管理ロック解除終了日をセットする。
         */
        if (WEB3DateUtility.compare(l_stopInfo.mngLockCancelEndDate, l_mainAccountRow.getMngLockOffEndDate()) != 0)
        {
            l_hostLokRegistParams.setMngLockCancelEndDate(l_stopInfo.mngLockCancelEndDate);
        }
        else
        {
            l_hostLokRegistParams.setMngLockCancelEndDate(null);
        }
        
        /*
         * 　@ロック客Y客登録解除Params.（送信用）支店ロック = 
         *   停止情報.支店ロック区分 != 顧客行.支店ロックの場合、  
         * 　@　@　@停止情報.支店ロック区分をセットする。
         */
        if (!l_stopInfo.branchLockDiv.equals(l_mainAccountRow.getBranchLock()))
        {
            l_hostLokRegistParams.setBranchLock(l_stopInfo.branchLockDiv);
        }
        else
        {
            l_hostLokRegistParams.setBranchLock(null);
        }
        
        /*
         * ロック客Y客登録解除Params.（送信用）注文認可 = 
         * 停止情報.注文認可区分 != 顧客行.注文認可 の場合、 
         * 　@　@　@停止情報.注文認可区分(*1)をセットする。
         * (*1) 停止情報.注文認可区分==0:認可　@の場合、１：認可　@をセットする。 
         *      停止情報.注文認可区分==1:非認可　@の場合、0：非認可　@をセットする。
         */
        if (!l_stopInfo.orderPermitDiv.equals(l_mainAccountRow.getOrderPermission()))
        {
            if (WEB3OrderPermitDivDef.AUTHORIZATION.equals(l_stopInfo.orderPermitDiv))
            {
                l_hostLokRegistParams.setOrderPermission(WEB3AccInfoOrderPermitDivDef.AUTHORIZATION);
            }
            else
            {
                l_hostLokRegistParams.setOrderPermission(WEB3AccInfoOrderPermitDivDef.NOT_AUTHORIZATION);
            }

        }
        else
        {
            l_hostLokRegistParams.setOrderPermission(null);
        }
        
        /*
         * ロック客Y客登録解除Params.（送信用）登録理由 = 停止情報.停止状況登録理由 
         *  != 顧客行.停止状況登録理由 の場合、
         * 　@　@停止情報.停止状況登録理由をセットする。 
         */
        if (l_stopInfo.stopStateRegistReason != null)
        {
            if (!l_stopInfo.stopStateRegistReason.equals(l_mainAccountRow.getLockRegistrationReason()))
            {
                l_hostLokRegistParams.setLockRegistrationReason(l_stopInfo.stopStateRegistReason);
            }
            else
            {
                l_hostLokRegistParams.setLockRegistrationReason(null);
            }
        }
        else
        {
            l_hostLokRegistParams.setLockRegistrationReason(null);
        }

        /*
         * ロック客Y客登録解除Params.（送信用）属性 = 
         *  停止情報.Ｙ客区分 != 顧客行.Y客区分 の場合、 
         * 　@　@8：Y客 をセットする。
         */
        if (!l_stopInfo.yellowAccountDiv.equals(l_mainAccountRow.getYellowCustomer()))
        {
            l_hostLokRegistParams.setAttribute(WEB3AccInfoAttributeDivDef.YELLOW_CUSTOMER);
        }
        else
        {
            l_hostLokRegistParams.setAttribute(null);
        }
        
        /*
         * ロック客Y客登録解除Params.（送信用）登録抹消区分 = 
         *  停止情報.Ｙ客区分 != 顧客行.Y客区分 の場合、
         * 　@　@　@停止情報.Ｙ客区分(*2)をセットする。 
         * (*2) 停止情報.Y客区分 == 1:Y客　@の場合、１：登録　@をセットする。 
`        *       停止情報.Y客区分 == 0:Y客でない　@の場合、9：抹消　@をセットする。 
         */
        if (!l_stopInfo.yellowAccountDiv.equals(l_mainAccountRow.getYellowCustomer()))
        {
            if (WEB3YellowCustomerDef.YELLOW_CUSTOMER.equals(l_stopInfo.yellowAccountDiv))
            {
                l_hostLokRegistParams.setRegiEraseDiv(WEB3AccInfoRegiEraseDivDef.REGISTER);
            }
            else
            {
                l_hostLokRegistParams.setRegiEraseDiv(WEB3AccInfoRegiEraseDivDef.ERASION);
            }
            
        }
        else
        {
            l_hostLokRegistParams.setRegiEraseDiv(null);
        }
        return new WEB3AccInfoLockAccYAccRegisterRelease(l_hostLokRegistParams);
    }
    
    /**
     * (get証券会社コード)<BR> 
     * 証券会社コードを取得する。 <BR>  
     * <BR> 
     * this.ロック客Y客登録解除.証券会社コードを返却する。<BR>   
     */
    public String getInstitutionCode()
    {
        return hostLockRegistParams.getInstitutionCode();
    }
    
    /**
     * (getデータコード)<BR> 
     * データコードを取得する。 <BR> 
     * <BR> 
     * this.ロック客Y客登録解除.データコードを返却する。 <BR> 
     */
    public String getRequestCode()
    {
        return hostLockRegistParams.getRequestCode();
    }
    
    /**
     * (getユーザデータ)<BR> 
     * ユーザデータ領域にセットする項目を取得する。<BR>   
     * <BR> 
     * nullを返却する。  <BR> 
     */
    public String getUserData()
    {
        return null;
    }
}
@
