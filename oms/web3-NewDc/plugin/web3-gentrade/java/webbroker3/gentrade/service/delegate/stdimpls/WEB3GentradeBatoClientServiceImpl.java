head	1.6;
access;
symbols;
locks; strict;
comment	@// @;


1.6
date	2011.03.25.04.28.38;	author che-jin;	state Exp;
branches;
next	1.5;
deltatype	text;
kopt	kv;
permissions	666;
commitid	73c4d8c19f61c49;
filename	WEB3GentradeBatoClientServiceImpl.java;

1.5
date	2011.03.23.07.10.26;	author liu-lei;	state Exp;
branches;
next	1.4;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d899ce17bd8;
filename	WEB3GentradeBatoClientServiceImpl.java;

1.4
date	2011.03.23.04.52.52;	author liu-lei;	state Exp;
branches;
next	1.3;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7144d897ca3128e;
filename	WEB3GentradeBatoClientServiceImpl.java;

1.3
date	2011.03.17.02.31.20;	author liu-lei;	state Exp;
branches;
next	1.2;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a84d8172785172;
filename	WEB3GentradeBatoClientServiceImpl.java;

1.2
date	2011.03.17.02.24.04;	author liu-lei;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8784d8170c34bdf;
filename	WEB3GentradeBatoClientServiceImpl.java;

1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBatoClientServiceImpl.java;


desc
@@


1.6
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 電子鳩システム接続サービス実装クラス(WEB3GentradeBatoClientServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
Revesion History : 2008/05/20 趙林鵬 (中訊)モデルNo.328
Revesion History : 2008/06/18 趙林鵬 (中訊)モデルNo.330
*/
package webbroker3.gentrade.service.delegate.stdimpls;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties.ProxyProperties;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DocumentCheckDivDef;
import webbroker3.common.define.WEB3ServiceDivDef;
import webbroker3.common.define.WEB3WeekDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.gentrade.message.WEB3DocumentDeliverRequest;
import webbroker3.gentrade.message.WEB3DocumentDeliverResponse;
import webbroker3.gentrade.message.WEB3GentradeBatoDisplayCommonResponse;
import webbroker3.gentrade.message.WEB3GentradeMultiCheckResults;
import webbroker3.gentrade.message.WEB3GentradeMultiDocCheckResultUnit;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.data.BatoBranchProductPrefDao;
import webbroker3.gentrade.data.BatoBranchProductPrefPK;
import webbroker3.gentrade.data.BatoDoctypeManagementDao;
import webbroker3.gentrade.data.BatoDoctypeManagementPK;
import webbroker3.gentrade.data.BatoDoctypeManagementParams;
import webbroker3.gentrade.data.BatoFunctionPrefDao;
import webbroker3.gentrade.data.BatoFunctionPrefPK;
import webbroker3.gentrade.data.BatoInstBranchPrefDao;
import webbroker3.gentrade.data.BatoInstBranchPrefPK;
import webbroker3.gentrade.data.BatoInstBranchPrefParams;
import webbroker3.gentrade.data.BatoBranchProductPrefParams;
import webbroker3.gentrade.data.BatoFunctionPrefParams;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.gentrade.data.OtherOrgOutOfSrvDateRow;
import webbroker3.gentrade.data.OtherOrgOutOfSrvWeekRow;
import webbroker3.gentrade.data.OtherOrgSrvTimeParams;
import webbroker3.gentrade.data.OtherOrgSrvTimeRow;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.define.WEB3GentradeBatoFunctionDivDef;
import webbroker3.gentrade.define.WEB3GentradeBatoOperatorInputFlagDef;
import webbroker3.gentrade.define.WEB3GentradeBatoOrderAtSystemFailureFlagDef;
import webbroker3.gentrade.define.WEB3GentradeBatoProspectusServiceResultDef;
import webbroker3.gentrade.define.WEB3GentradeBatoServiceRegServiceResultDef;
import webbroker3.gentrade.define.WEB3GentradeBatoSystemFailureFlagDef;
import webbroker3.gentrade.define.WEB3GentradeBatoTranHistServiceResultDef;

/**
 * 電子鳩システム接続サービス実装クラス
 */
public class WEB3GentradeBatoClientServiceImpl 
    extends WEB3ClientRequestService 
    implements WEB3GentradeBatoClientService
{
    
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBatoClientServiceImpl.class);

    /**
     * 外部機@関コード．電子鳩：03
     */
    private static final String BATO_OTHER_ORG_CODE = "03";
   
    /**
     * 電子鳩ハッシュ計算アルゴリズム：SHA-1
     */
    private static final String BATO_HASH_ALGORITHM = "SHA-1";

    /**
     * 文字列ネームスペース名
     */
    private final QName XSD_STRING = 
        new QName("http://www.w3.org/2001/XMLSchema", "string");

    /**
     * パラメータリストセパレータ
     */
    private final String PARAMETER_LIST_DEL = ":"; 

    /**
     * デフォルトコンストラクタ
     * @@roseuid 423698F6008A
     */
    public WEB3GentradeBatoClientServiceImpl() 
    {
    }
   
    /**
     * 電子鳩システム画面の表示に必要なデータを取得し返却する。<br />
     * <br />
     * シーケンス図<br />
     * 「（電子鳩）get画面表示データ」 参照<br />
     * <br />
     * @@param l_request - リクエストデータ<br />
     * @@exception  BUSINESS_ERROR_00013:　@受付時間外
     * @@exception  SYSTEM_ERROR_80003:　@DBエラー
     * @@exception  SYSTEM_ERROR_80005:　@該当データなし
     * @@return WEB3GenResponse<br />
     * @@roseuid 421033C20302<br />
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBatoClientServiceImpl.execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        MainAccount l_account = this.getMainAccount();

        String l_url = null;
        String l_hashValue = null;
        boolean isWorking = false;
        int l_intSize;
        Trader l_trader = null;
        BatoBranchProductPrefParams l_batoBranchProductPrefParams = null;
            
        BatoInstBranchPrefParams l_insBrParams =     
            this.getBatoInstBranchPrefParams(
                l_account.getInstitution().getInstitutionCode(), 
                l_account.getBranch().getBranchCode()
                );
        if (WEB3GentradeBatoSystemFailureFlagDef.WORKING.equals(
            l_insBrParams.getSystemFailureFlag()))
        {
            try
            {
                this.validateConnectionTime();

                isWorking = true;
                l_hashValue = this.getHashValue(
                    l_account,
                    l_insBrParams.hash_field1,
                    l_insBrParams.hash_field2
                    );
                l_url = l_insBrParams.getUrl();
            }
            catch (WEB3BusinessLayerException e)
            {
                log.debug("validate接続可能時間()で例外発生：" + e.getErrorMessage());
            }
            
        }
        
        if (l_request instanceof WEB3DocumentDeliverRequest)
        {
            l_trader = this.getTrader();
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and branch_code = ? ");
            l_sbWhere.append(" and document_check_div = ? ");
            l_sbWhere.append(" and document_number = ? ");
        
            Object[] l_objWhere = {
                l_account.getInstitution().getInstitutionCode(),
                l_account.getBranch().getBranchCode(),
                WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW,
                "0"
            };
        
            List l_lisRecords = null;
            QueryProcessor l_queryProcessor;
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords = 
                    l_queryProcessor.doFindAllQuery(
                        DocDivManagementRow.TYPE, 
                        l_sbWhere.toString(), 
                        l_objWhere); 

            } 
            catch (DataException e) 
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    e.getMessage(),
                    e);

            } 
        
            l_intSize = l_lisRecords.size();
        
            if (l_intSize < 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    getClass().getName() + "." + STR_METHOD_NAME);
            }

            DocDivManagementRow l_docDivManagementRow = 
                (DocDivManagementRow) l_lisRecords.get(0);
            
            l_batoBranchProductPrefParams = 
                this.getBatoBranchProductPrefParams(
                    l_account.getInstitution().getInstitutionCode(), 
                    l_account.getBranch().getBranchCode(), 
                    l_docDivManagementRow.getDocumentDiv());
        }
                
        WEB3GentradeBatoDisplayCommonResponse l_response = 
            (WEB3GentradeBatoDisplayCommonResponse)l_request.createResponse();
        
        if (l_request instanceof WEB3DocumentDeliverRequest)
        {
            if (WEB3GentradeBatoOrderAtSystemFailureFlagDef.DISABLED.equals(
                l_batoBranchProductPrefParams.getOrderAtSystemFailureFlag()) ||
                (l_trader != null &&
                WEB3GentradeBatoOperatorInputFlagDef.DISABLED.equals(
                l_batoBranchProductPrefParams.getOperatorInputFlag())))
            {
                ((WEB3DocumentDeliverResponse)l_response).tradingStopFlag = false;
            }
            else
            {
                ((WEB3DocumentDeliverResponse)l_response).tradingStopFlag = true;
            }
        }
        
        l_response.isWorking = isWorking;
        l_response.url = l_url;
        l_response.hashValue = l_hashValue;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
   
    /**
     * （validate目論見書閲覧）<br />
     * <br />
     * 目論見書閲覧済かを判定する。<br />
     * <br />
     * シーケンス図<br />
     * 「（電子鳩）validate目論見書閲覧」 参照<br />
     * <br />
     * @@param l_typeCode 種別コード(ＰＲ層より取得)<br />
     * @@param l_productCode 銘柄コード<br />
     * @@return webbroker3.gentrade.message.WEB3GentradeProspectusResult<br />
     * @@see WEB3GentradeProspectusResult
     * @@see WEB3GentradeBatoProspectusServiceResultDef
     * @@exception  BUSINESS_ERROR_00013:　@受付時間外
     * @@exception  BUSINESS_ERROR_01959:　@電子鳩エラー
     * @@exception  BUSINESS_ERROR_01984:　@[電子鳩システム障害中]障害中注文不可
     * @@exception  BUSINESS_ERROR_01985:　@[電子鳩システム障害中]障害中代理入力不可
     * @@exception  SYSTEM_ERROR_80003:　@DBエラー
     * @@exception  SYSTEM_ERROR_80005:　@該当データなし
     * @@exception  SYSTEM_ERROR_80006:  引数エラー
     * @@roseuid 4210370D0208<br />
     */
    public WEB3GentradeProspectusResult validateProspectus(
        String l_typeCode, String l_productCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBatoClientServiceImpl.validateProspectus(String,String)";
        log.entering(STR_METHOD_NAME);

        if (l_typeCode == null) { 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "引数.種別コードがnullです。");
        }
        if (l_productCode == null) { 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "引数.銘柄コードがnullです。");
        }

        MainAccount l_account = this.getMainAccount();

        BatoInstBranchPrefParams l_insBrParams =     
            this.getBatoInstBranchPrefParams(
                l_account.getInstitution().getInstitutionCode(), 
                l_account.getBranch().getBranchCode()
                );

        BatoBranchProductPrefParams l_brProductParams =     
            this.getBatoBranchProductPrefParams(
                l_account.getInstitution().getInstitutionCode(), 
                l_account.getBranch().getBranchCode(),
                l_typeCode
                );

        WEB3GentradeProspectusResult l_result = 
            new WEB3GentradeProspectusResult();

        if (WEB3GentradeBatoSystemFailureFlagDef.NOT_WORKING.equals(
            l_insBrParams.getSystemFailureFlag()))
        {
            if (WEB3GentradeBatoOrderAtSystemFailureFlagDef.DISABLED.equals(
                l_brProductParams.order_at_system_failure_flag))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01984,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                    );
            }
            
            Trader l_trader = this.getTrader();
            if (l_trader != null) {
                if (WEB3GentradeBatoOperatorInputFlagDef.DISABLED.equals(
                    l_brProductParams.operator_input_flag))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01985,
                        this.getClass().getName() + "." + STR_METHOD_NAME
                        );
                }
            }
            
            l_result.checkResult = 
                WEB3GentradeBatoCheckResultDef.UNINSPECTION_TROUBLE;
            l_result.url = null; 
            l_result.hashValue = null;

            return l_result; 
        }

        this.validateConnectionTime();
        
        String l_hashValue = this.getHashValue( 
            l_account,
            l_insBrParams.hash_field1,
            l_insBrParams.hash_field2
            );
            
        ArrayList l_paramList = new ArrayList();
        l_paramList.add(l_account.getInstitution().getInstitutionCode());
        l_paramList.add(l_account.getBranch().getBranchCode());
        l_paramList.add(l_account.getAccountCode());
        l_paramList.add(l_typeCode);
        l_paramList.add(l_productCode);
        l_paramList.add(l_hashValue);

        String l_resValue = this.invokeBatoSystem(
            l_paramList.toArray(),
            l_insBrParams.getSoapUrl(),
            this.getBatoFunctionPrefParams(
                WEB3GentradeBatoFunctionDivDef.BATO_PROSPECTUS_SERVICE
                )
            );

        if (WEB3GentradeBatoProspectusServiceResultDef.HISTORY.
            equals(l_resValue) ||
            WEB3GentradeBatoProspectusServiceResultDef.CHECK_OFF.
            equals(l_resValue))
        {
            l_result.checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
            l_result.url = null; 
            l_result.hashValue = null;
        } else if (WEB3GentradeBatoProspectusServiceResultDef.NO_HISTORY.
            equals(l_resValue))
        {
            Trader l_trader = this.getTrader();
            if (l_trader != null) {
                if (WEB3GentradeBatoOperatorInputFlagDef.DISABLED.equals(
                    l_brProductParams.operator_input_flag))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01988,
                        this.getClass().getName() + "." + STR_METHOD_NAME
                        );
                }
            }
            
            l_result.checkResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION;
            l_result.url = l_insBrParams.getUrl();
            l_result.hashValue = l_hashValue;
        } else {
            String l_errorMsg = null;
            l_errorMsg = "[目論見書閲覧チェックエラー] " +
                "電子鳩返却値(" + l_resValue + ") " + 
                this.getParamListString(l_paramList.toArray());

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01959,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_errorMsg
                );
        }

        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
   
    /**
     * （validate電子鳩実施）<br />
     * <br />
     * 電子鳩システムでパラメータの機@能が実施されているかを判定する。<br />
     * <br />
     * シーケンス図<br />
     * 「（電子鳩）validate電子鳩実施」 参照<br />
     * <br />
     * <br />
     * [戻り値]<br />
     * ○引数.機@能区分が”電子鳩承諾チェック”の場合<br />
     *   0： 未同意顧客<br />
     *   1： 同意顧客<br />
     * ※　@WEB3GentradeBatoServiceRegServiceResultDefにて定数定義<br />
     * <br />
     * ○引数.機@能区分が”取引報告書実施チェック”の場合<br />
     *   0： 未同意顧客<br />
     *   1： 同意顧客<br />
     *   2： 未実施会社<br />
     * ※　@WEB3GentradeBatoTranHistServiceResultDefにて定数定義<br />
     * <br />
     * @@param l_functionDiv  機@能区分(WEB3GentradeBatoFunctionDivDef)
     * @@return 電子鳩システムの戻り値(WEB3GentradeBatoTranHistServiceResultDef,WEB3GentradeBatoServiceRegServiceResultDef)<br />
     * @@see WEB3GentradeBatoFunctionDivDef
     * @@see WEB3GentradeBatoTranHistServiceResultDef
     * @@see WEB3GentradeBatoServiceRegServiceResultDef
     * @@exception  BUSINESS_ERROR_00013:　@受付時間外
     * @@exception  BUSINESS_ERROR_01984:　@[電子鳩システム障害中]障害中注文不可
     * @@exception  BUSINESS_ERROR_01959:　@電子鳩エラー　@ 
     * @@exception  SYSTEM_ERROR_80005:　@該当データなし
     * @@exception  SYSTEM_ERROR_80003:　@DBエラー
     * @@exception  SYSTEM_ERROR_80006:  引数エラー
     * @@roseuid 421178B40170<br />
     */
    public String validateBato(String l_functionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBatoClientServiceImpl.validateBato(String)";
        log.entering(STR_METHOD_NAME);

        if (l_functionDiv == null) { 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "引数.機@能区分がnullです。");
        } else if (WEB3GentradeBatoFunctionDivDef.BATO_SERVICE_REG_SERVICE.equals(
            l_functionDiv)) 
        {
            log.debug("[電子鳩承諾チェック]");
        } else if (WEB3GentradeBatoFunctionDivDef.BATO_TRAN_HIST_SERVICE.equals(
            l_functionDiv)) 
        {
            log.debug("[取引報告書実施チェック]");
        } else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "引数.機@能区分には、" +
                "[00：電子鳩承諾チェック]または、[02：取引報告書実施チェック]" +
                "を指定してください。" + "指定値(" + l_functionDiv + ")");
        }

        MainAccount l_account = this.getMainAccount();

        BatoInstBranchPrefParams l_insBrParams =     
            this.getBatoInstBranchPrefParams(
                l_account.getInstitution().getInstitutionCode(), 
                l_account.getBranch().getBranchCode()
                );
        if (WEB3GentradeBatoSystemFailureFlagDef.NOT_WORKING.equals(
            l_insBrParams.getSystemFailureFlag()))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01984,
                this.getClass().getName() + "." + STR_METHOD_NAME
                );
        }

        this.validateConnectionTime();
        
        ArrayList l_paramList = new ArrayList();
        l_paramList.add(l_account.getInstitution().getInstitutionCode());
        l_paramList.add(l_account.getBranch().getBranchCode());
        l_paramList.add(l_account.getAccountCode());
        l_paramList.add(
            this.getHashValue(
            l_account,
            l_insBrParams.hash_field1,
            l_insBrParams.hash_field2)
        );

        String l_resValue = this.invokeBatoSystem(
            l_paramList.toArray(),
            l_insBrParams.getSoapUrl(),
            this.getBatoFunctionPrefParams(l_functionDiv)
            );
        
        String l_errorMsg = null;
        if (WEB3GentradeBatoFunctionDivDef.BATO_SERVICE_REG_SERVICE.equals(
            l_functionDiv)) 
        {
            if (WEB3GentradeBatoServiceRegServiceResultDef.AGREEMENT.equals(l_resValue) ||
                WEB3GentradeBatoServiceRegServiceResultDef.NOT_AGREEMENT.equals(l_resValue))
            { 
                log.exiting(STR_METHOD_NAME);
                return l_resValue;
            } 
            l_errorMsg = "[電子鳩承諾チェックエラー] " +
                "電子鳩返却値(" + l_resValue + ") " + 
                this.getParamListString(l_paramList.toArray());
            
        } else if (WEB3GentradeBatoFunctionDivDef.BATO_TRAN_HIST_SERVICE.equals(
            l_functionDiv)) 
        {
            if (WEB3GentradeBatoTranHistServiceResultDef.AGREEMENT.equals(l_resValue) ||
                WEB3GentradeBatoTranHistServiceResultDef.NOT_AGREEMENT.equals(l_resValue) ||
                WEB3GentradeBatoTranHistServiceResultDef.CHECK_OFF.equals(l_resValue))
            { 
                log.exiting(STR_METHOD_NAME);
                return l_resValue;
            } 
            l_errorMsg = "[取引報告書実施チェックエラー] " +
                "電子鳩返却値(" + l_resValue + ") " + 
                this.getParamListString(l_paramList.toArray());
        }

        log.exiting(STR_METHOD_NAME);
        throw new WEB3BusinessLayerException(
            WEB3ErrorCatalog.BUSINESS_ERROR_01959,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            l_errorMsg
            );
    }
   
    /**
     * （validate接続可能時間）<br />
     * <br />
     * 電子鳩システムに接続可能な時間帯かどうかのチェックを行う。<br />
     * <br />
     * １）現在時刻のカレンダを取得する。<br />
     * Calendar.getInstance()<br />
     * Calendar.setTime(GtlUtils.getSystemTimestamp())<br />
     * <br />
     * ２）外部機@関受付時間外（曜日）テーブルチェック<br />
     * 外部機@関受付時間外（曜日）テーブルから以下の条件のレコードを取得する。<br />
     * <br />
     * [検索条件]<br />
     * 外部機@関コード = ”電子鳩システム” and<br />
     * (月 = Calendar.get("MONTH")の戻り値+1 or 月 = "0" ) and<br />
     * 曜日 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and<br />
     * (週番号 = Calendar.get("WEEK_OF_MONTH")の戻り値 or 曜日番号 = "0") and<br />
     * 停止時間（FROM) <= カレンダの時刻（HHMMSS）部分 and<br />
     * 停止時間（TO) >= カレンダの時刻（HHMMSS）部分<br />
     * <br />
     * 取得できた場合、例外（BUSINESS_ERROR_00013）をthrowする。<br />
     * <br />
     * ３）外部機@関受付時間外（日付）テーブルチェック<br />
     * 外部機@関受付時間外（日付）テーブルから以下の条件のレコードを取得する。<br />
     * <br />
     * [検索条件]<br />
     * 外部機@関コード = ”電子鳩システム” and<br />
     * 日付 = カレンダの日付(MMDD)部分 and<br />
     * 停止時間（FROM) <= カレンダの時刻（HHMMSS）部分 and<br />
     * 停止時間（TO) >= カレンダの時刻（HHMMSS）部分<br />
     * <br />
     * 取得できた場合、例外（BUSINESS_ERROR_00013）をthrowする。<br />
     * <br />
     * ４）外部機@関受付時間テーブルチェック<br />
     * <br />
     * ４－１）外部機@関受付時間テーブルから以下の条件のレコードを取得する。<br />
     * <br />
     * [検索条件]<br />
     * 外部機@関コード = ”電子鳩システム” and<br />
     * 曜日区分 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and<br />
     * 受付時間（FROM) <= カレンダの時刻（HHMMSS）部分 and <br />
     * 受付時間（TO) >= カレンダの時刻（HHMMSS）部分<br />
     * <br />
     * 取得できなかった場合、例外（BUSINESS_ERROR_00013）をthrowする。<br />
     * <br />
     * ４－２）取得したレコードのサービス区分が0（停止中）の場合、例外（BUSINESS_ERROR_00013）をthrowする。<br />
     * <br />
     * 
     * <br />
     * @@exception  BUSINESS_ERROR_00013:受付時間外
     * @@exception  SYSTEM_ERROR_80003:DBエラー
     * @@roseuid 4211D91A0038<br />
     */
    protected void validateConnectionTime() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBatoClientServiceImpl.validateConnectionTime(void)";
        log.entering(STR_METHOD_NAME);

        Calendar l_cal = Calendar.getInstance();
        l_cal.setTime(GtlUtils.getSystemTimestamp());

        String l_time = WEB3DateUtility.formatDate(l_cal.getTime(), "HHmmss");
        int l_weekOfMonth = l_cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        int l_dayOfWeek = l_cal.get(Calendar.DAY_OF_WEEK);
        String l_weekDiv = null;
        if (l_dayOfWeek == Calendar.SUNDAY) 
        {
            l_weekDiv = WEB3WeekDivDef.SUNDAY;
        } else if (l_dayOfWeek == Calendar.MONDAY) 
        {
            l_weekDiv = WEB3WeekDivDef.MONDAY;
        } else if (l_dayOfWeek == Calendar.TUESDAY) 
        {
            l_weekDiv = WEB3WeekDivDef.TUESDAY;
        } else if (l_dayOfWeek == Calendar.WEDNESDAY) 
        {
            l_weekDiv = WEB3WeekDivDef.WEDNESDAY;
        } else if (l_dayOfWeek == Calendar.THURSDAY) 
        {
            l_weekDiv = WEB3WeekDivDef.THURSDAY;
        } else if (l_dayOfWeek == Calendar.FRIDAY) 
        {
            l_weekDiv = WEB3WeekDivDef.FRIDAY;
        } else if (l_dayOfWeek == Calendar.SATURDAY) 
        {
            l_weekDiv = WEB3WeekDivDef.SATURDAY;
        }

        
        /*
         * 【外部機@関受付時間外（曜日）チェック】
         *  
         * [検索条件] 
         * 外部機@関コード = ”電子鳩システム” and 
         * (月 = Calendar.get("MONTH")の戻り値+1 or 月 = "0" ) and 
         * 曜日 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and 
         * (週番号 = Calendar.get("WEEK_OF_MONTH")の戻り値 or 曜日番号 = "0") and 
         * 停止時間（FROM) <= カレンダの時刻（HHMMSS）部分 and 
         * 停止時間（TO) >= カレンダの時刻（HHMMSS）部分 
         */
        String l_condition = 
            "other_org_code = ? And " +
            "(month = ? Or month = ? Or month = ?) And " +
            "week_div = ? And " +
            "(week_no = ? Or week_no = ?) And " +
            "suspension_start_time <= ? And " +
            "suspension_end_time >= ?"
            ;

        String l_mm = WEB3DateUtility.formatDate(l_cal.getTime(), "MM");
        int l_m = Integer.parseInt(l_mm);
        ArrayList l_paramList = new ArrayList();
        l_paramList.add(BATO_OTHER_ORG_CODE);
        l_paramList.add(l_mm);
        l_paramList.add(String.valueOf(l_m));
        l_paramList.add("0");
        l_paramList.add(l_weekDiv);
        l_paramList.add(Integer.toString(l_weekOfMonth));
        l_paramList.add("0");
        l_paramList.add(l_time);
        l_paramList.add(l_time);
        
        List l_results = null;
        boolean isAccept = false;
		try
		{
			l_results = Processors.getDefaultProcessor().doFindAllQuery(
			        OtherOrgOutOfSrvWeekRow.TYPE, 
			        l_condition, 
			        l_paramList.toArray()
			        );
            if (l_results.size() == 0) { isAccept = true; }            
		} catch (DataFindException e)
		{
            isAccept = true;
        } catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "SQLエラー",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ネットワークエラー",
                e);
        }
        
        if (!isAccept) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[外部機@関受付時間外（曜日）テーブル] " +
                "外部機@関コード.電子鳩システム(" + BATO_OTHER_ORG_CODE + "), " +
                "月(" + l_mm + "), " +
                "曜日区分(" + l_weekDiv + "), " +
                "週番号(" + Integer.toString(l_weekOfMonth) + "), " +
                "チェック時間(" + l_time + ")"
                );
        }

        
        /*
         * 【外部機@関受付時間外（日付）チェック】
         * 
         * [検索条件]
         * 外部機@関コード = ”電子鳩システム” and
         * 日付 = カレンダの日付(MMDD)部分 and
         * 停止時間（FROM) <= カレンダの時刻（HHMMSS）部分 and
         * 停止時間（TO) >= カレンダの時刻（HHMMSS）部分
         */
        String l_mmdd = WEB3DateUtility.formatDate(l_cal.getTime(), "MMdd");
        l_condition = 
            "other_org_code = ? And " +
            "suspension_date = ? And " +
            "suspension_start_time <= ? And " +
            "suspension_end_time >= ?"
            ;
        l_paramList.clear();
        l_paramList.add(BATO_OTHER_ORG_CODE);
        l_paramList.add(l_mmdd);
        l_paramList.add(l_time);
        l_paramList.add(l_time);

        isAccept = false;
        try
        {
            l_results = Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgOutOfSrvDateRow.TYPE, 
                    l_condition, 
                    l_paramList.toArray()
                    );
            if (l_results.size() == 0) { isAccept = true; }            
        } catch (DataFindException e)
        {
            isAccept = true;
        } catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "SQLエラー",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ネットワークエラー",
                e);
        }
        
        if (!isAccept) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[外部機@関受付時間外（日付）テーブル] " +
                "外部機@関コード.電子鳩システム(" + BATO_OTHER_ORG_CODE + "), " +
                "日付(" + l_mmdd + "), " +
                "チェック時間(" + l_time + ")"
                );
        }

        
        /*
         * 【外部機@関受付時間チェック】
         * 
         * [検索条件] 
         * 外部機@関コード = ”電子鳩システム” and 
         * 曜日区分 = Calendar.get("DAY_OF_WEEK")の戻り値-1 and 
         * 受付時間（FROM) <= カレンダの時刻（HHMMSS）部分 and 
         * 受付時間（TO) >= カレンダの時刻（HHMMSS）部分          *  
         */
        l_condition = 
            "other_org_code = ? And " +
            "week_div = ? And " +
            "service_start_time <= ? And " +
            "service_end_time >= ?"
            ;
        l_paramList.clear();
        l_paramList.add(BATO_OTHER_ORG_CODE);
        l_paramList.add(l_weekDiv);
        l_paramList.add(l_time);
        l_paramList.add(l_time);

        isAccept = false;
        try
        {
            l_results = Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrgSrvTimeRow.TYPE, 
                    l_condition, 
                    l_paramList.toArray()
                    );
        } catch (DataFindException e)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[外部機@関受付時間テーブル．該当データなし] " +
                "外部機@関コード.電子鳩システム(" + BATO_OTHER_ORG_CODE + "), " +
                "曜日区分(" + l_weekDiv + "), " +
                "チェック時間(" + l_time + ")"
                );
        } catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "SQLエラー",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ネットワークエラー",
                e);
        }
                
        if (l_results.size() > 0) {            
            OtherOrgSrvTimeParams l_params = 
                (OtherOrgSrvTimeParams)l_results.get(0);
            if (!WEB3ServiceDivDef.INT_ACCEPT.equals(l_params.getServiceDiv()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "[外部機@関受付時間テーブル．停止中] " +
                    "外部機@関コード.電子鳩システム(" + BATO_OTHER_ORG_CODE + "), " +
                    "曜日区分(" + l_weekDiv + "), " +
                    "チェック時間(" + l_time + ")"
                    );
            }
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[外部機@関受付時間テーブル．該当データなし] " +
                "外部機@関コード.電子鳩システム(" + BATO_OTHER_ORG_CODE + "), " +
                "曜日区分(" + l_weekDiv + "), " +
                "チェック時間(" + l_time + ")"
                );
        }
         
        log.exiting(STR_METHOD_NAME);
    }
   
    /**
     * （get会社部店別プリファ@レンス）<br />
     * <br />
     * 電子鳩システム会社部店別プリファ@レンスを取得する。<br />
     * <br />
     * １）以下の条件で、電子鳩システム会社部店別プリファ@レンスからレコードを取得する。<br />
     * <br />
     *    [条件]<br />
     *    証券会社コード： 引数.証券会社コード<br />
     *    部店コード： 引数.部店コード<br />
     * <br />
     * ２）取得した電子鳩システム会社部店別プリファ@レンスの行オブジェクトを返却する。<br />
     * <br />
     * @@param l_institutionCode - 証券会社コード
     * @@param l_branchCode - 部店コード
     * @@exception  SYSTEM_ERROR_80005:　@該当データなし
     * @@exception  SYSTEM_ERROR_80003:　@DBエラー
     * @@return webbroker3.gentrade.data.BatoInstBranchPrefParams
     * @@roseuid 4211769F022C
     */
    protected BatoInstBranchPrefParams getBatoInstBranchPrefParams(
        String l_institutionCode, String l_branchCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBatoClientServiceImpl.getBatoInstBranchPrefParams(String,String)";
        log.entering(STR_METHOD_NAME);

        BatoInstBranchPrefPK l_pk = new BatoInstBranchPrefPK( 
            l_institutionCode, l_branchCode
             );
        BatoInstBranchPrefParams l_params = null;

        try
        {
            l_params = 
                (BatoInstBranchPrefParams)BatoInstBranchPrefDao.findRowByPk(l_pk);
        } catch (DataFindException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[会社部店別プリファ@レンステーブル．該当データなし] " +
                "証券会社(" + l_institutionCode + "), " +
                "部店(" + l_branchCode + ")"
                );
        } catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "SQLエラー",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ネットワークエラー",
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
   
    /**
     * （get部店商品別プリファ@レンス）<br />
     * <br />
     * 電子鳩システム部店商品別プリファ@レンスを取得する。<br />
     * <br />
     * １）以下の条件で、電子鳩システムドキュメント種別管理テーブルからレコードを取得す
     * る。<br />
     * <br />
     *    [条件]<br />
     *    証券会社コード： 引数.証券会社コード<br />
     *    種別コード： 引数.種別コード<br />
     * <br />
     * ２）以下の条件で、電子鳩システム部店商品別プリファ@レンスからレコードを取得する。<br />
     * <br />
     *    [条件]<br />
     *    証券会社コード： 引数.証券会社コード<br />
     *    部店コード： 引数.部店コード<br />
     *    商品コード： 
     * １）で取得した電子鳩システムドキュメント種別管理テーブル.商品コード<br />
     * <br />
     * ３）取得した電子鳩システム部店商品別プリファ@レンスの行オブジェクトを返却する。<br />
     * 
     * @@param l_institutionCode - 証券会社コード
     * @@param l_branchCode - 部店コード
     * @@param l_typeCode - 種別コード
     * @@exception  SYSTEM_ERROR_80005:　@該当データなし
     * @@exception  SYSTEM_ERROR_80003:　@DBエラー
     * @@return webbroker3.gentrade.data.BatoBranchProductPrefParams<br />
     * @@roseuid 421313A001CE<br />
     */
    protected BatoBranchProductPrefParams getBatoBranchProductPrefParams(
        String l_institutionCode, String l_branchCode, String l_typeCode)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBatoClientServiceImpl.getBatoBranchProductPrefParams(String,String,String)";
        log.entering(STR_METHOD_NAME);
        
        String l_prodCode = null;
        try
        {
            BatoDoctypeManagementParams l_docTypeParams = 
                (BatoDoctypeManagementParams)BatoDoctypeManagementDao.findRowByPk( 
                new BatoDoctypeManagementPK(l_institutionCode, l_typeCode)
                );
            l_prodCode = l_docTypeParams.getProductCode();
        } catch (DataFindException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[電子鳩システムドキュメント種別管理テーブル．該当データなし] " +
                "証券会社(" + l_institutionCode + "), " +
                "種別コード(" + l_typeCode + ")"
                );
        } catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "SQLエラー",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ネットワークエラー",
                e);
        }

        BatoBranchProductPrefPK l_pk = new BatoBranchProductPrefPK( 
            l_institutionCode, l_branchCode, l_prodCode
             );
        BatoBranchProductPrefParams l_params = null;

        try
        {
            l_params = 
                (BatoBranchProductPrefParams)BatoBranchProductPrefDao.findRowByPk(l_pk);
        } catch (DataFindException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[部店商品別プリファ@レンステーブル．該当データなし] " +
                "証券会社(" + l_institutionCode + "), " +
                "部店(" + l_branchCode + "), " +
                "商品(" + l_prodCode + ")"
                );
        } catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "SQLエラー",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ネットワークエラー",
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
   
    /**
     * （get機@能別プリファ@レンス）<br />
     * <br />
     * 電子鳩システム機@能別プリファ@レンスを取得する。<br />
     * <br />
     * １）以下の条件で、電子鳩システム機@能別プリファ@レンスからレコードを取得する。<br />
     * <br />
     *    [条件]<br />
     *    機@能区分： 引数.機@能区分<br />
     * <br />
     * ２）取得した電子鳩システム機@能別プリファ@レンスの行オブジェクトを返却する。<br />
     * 
     * @@exception  SYSTEM_ERROR_80005:　@該当データなし
     * @@exception  SYSTEM_ERROR_80003:　@DBエラー
     * @@param l_functionDiv - 機@能区分
     * @@return webbroker3.gentrade.data.BatoFunctionPrefParams
     * @@roseuid 4213141C02B8
     */
    protected BatoFunctionPrefParams getBatoFunctionPrefParams(
        String l_functionDiv) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBatoClientServiceImpl.getBatoFunctionPrefParams(String)";
        log.entering(STR_METHOD_NAME);

        BatoFunctionPrefPK l_pk = new BatoFunctionPrefPK( 
            l_functionDiv
            );
        BatoFunctionPrefParams l_params = null;

        try
        {
            l_params = 
                (BatoFunctionPrefParams)BatoFunctionPrefDao.findRowByPk(l_pk);
        } catch (DataFindException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[機@能別プリファ@レンステーブル．該当データなし] " +
                "機@能コード(" + l_functionDiv + ")"
                );
        } catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "SQLエラー",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ネットワークエラー",
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
   
    /**
     * （invoke電子鳩システム）<br />
     * <br />
     * 電子鳩システムの処理をコールする。<br />
     * <br />
     * シーケンス図<br />
     * 「（電子鳩）invoke電子鳩システム」 参照<br />
     * 
     * @@param l_paramList - パラメータの配列
     * @@param l_url - URL
     * @@param l_BatoFunctionPrefParams - 
     * 電子鳩システム機@能別プリファ@レンス行オブジェクト
     * @@exception  BUSINESS_ERROR_01959:　@電子鳩エラー　@ 
     * @@return String
     * @@roseuid 421322FD0122
     */
    protected String invokeBatoSystem(
        Object[] l_paramList, String l_url, 
        BatoFunctionPrefParams l_BatoFunctionPrefParams) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBatoClientServiceImpl.invokeBatoSystem(Object[],String,BatoFunctionPrefParams)";
        log.entering(STR_METHOD_NAME);

        String l_resValue = null;
        String l_logMessage = "";

        RPCServiceClient serviceClient = null;
        ProxyProperties proxyProperties = null;
        EndpointReference targetEPR = null;

        String l_urlArr[] = l_url.split(";");
        String l_soapUrl = "";

        try
        {
            serviceClient = new RPCServiceClient();

            Options options = serviceClient.getOptions();

            if (l_urlArr.length == 3)
            {
                l_soapUrl = l_urlArr[2];

                if (l_soapUrl.indexOf("https://") >= 0)
                {
                    System.setProperty("https.proxyHost", l_urlArr[0]);
                    System.setProperty("https.proxyPort", l_urlArr[1]);

                    proxyProperties = new ProxyProperties();
                    proxyProperties.setProxyName(l_urlArr[0]);
                    proxyProperties.setProxyPort(Integer.parseInt(l_urlArr[1]));
                    options.setProperty(HTTPConstants.PROXY, proxyProperties);

                    l_logMessage = 
                        "\nhttps.proxyHost (" + l_urlArr[0] + ")" +
                        "\nhttps.proxyPort (" + l_urlArr[1] + ")";
                } else
                {
                    System.setProperty("http.proxyHost", l_urlArr[0]);
                    System.setProperty("http.proxyPort", l_urlArr[1]);

                    proxyProperties = new ProxyProperties();
                    proxyProperties.setProxyName(l_urlArr[0]);
                    proxyProperties.setProxyPort(Integer.parseInt(l_urlArr[1]));
                    options.setProperty(HTTPConstants.PROXY, proxyProperties);

                    l_logMessage = 
                        "\nhttp.proxyHost (" + l_urlArr[0] + ")" +
                        "\nhttp.proxyPort (" + l_urlArr[1] + ")";
                }

                log.debug(l_logMessage);
            } else if (l_urlArr.length == 1)
            {
                l_soapUrl = l_url;
            } else
            {
                l_logMessage = 
                    "電子鳩システム会社部店別プリファ@レンス.URL（SOAP接続用）" + 
                    "のセクション数が、異なっています。\n" +
                    "「[proxy-host;proxy-port;]soap-url」でセットしてください。";
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01959,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_logMessage
                    );
            }

			String l_targetNamespace = 
                l_soapUrl + l_BatoFunctionPrefParams.getTargetNamespaceName();
			String l_serviceName = l_BatoFunctionPrefParams.getServiceName();
			String l_portTypeName = l_BatoFunctionPrefParams.getPortTypeName();
			String l_operationName = l_BatoFunctionPrefParams.getOperationName();

			QName l_operationQName = new QName(
			    l_serviceName,
			    l_operationName 
			    );

            targetEPR = new EndpointReference(l_targetNamespace);
            options.setTo(targetEPR);

            String l_paramNameList = l_BatoFunctionPrefParams.getParameterList();
            l_logMessage = "\n" +
                "TaegetNameSpace (" + l_targetNamespace + ")\n" +
                "ServiceName (" + l_serviceName + ")\n" +  
                "PortTypeName (" + l_portTypeName + ")\n" +  
                "OperationName (" + l_operationName + ")";
            log.debug(l_logMessage);

            if (l_paramNameList != null) 
            {
                String[] l_paramNames = l_paramNameList.split(PARAMETER_LIST_DEL);
                if (l_paramNames.length != l_paramList.length)
                {
                    l_logMessage = 
                        "電子鳩システム機@能別プリファ@レンス.パラメータリスト" + 
                        "のセクション数が、引数の数と異なっています。\n" +
                        this.getParamListString(l_paramList) + "\n" +
                        l_logMessage;
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01959,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_logMessage
                        );
                }
                
                l_logMessage = l_logMessage + "ParameterList ";
                for (int i = 0; i < l_paramNames.length; i ++) 
                {
                    l_logMessage = l_logMessage + 
                        "(" + 
                        l_paramNames[i] + "=" + l_paramList[i].toString() +
                        ")";
                }
            }

            Class[] returnTypes = new Class[] { String.class };

            Object[] response =
                serviceClient.invokeBlocking(
                    l_operationQName,
                    l_paramList,
                    returnTypes);

            l_resValue = response[0].toString();
            serviceClient.cleanupTransport();
		}
        catch (RemoteException e)
		{
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01959,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[通信エラー]\n" + l_logMessage, 
                e);
		}

        log.exiting(STR_METHOD_NAME + " 電子鳩返却値（" + l_resValue + "）");
        return l_resValue;
    }
    
     /**
      * （getハッシュ値）<br />
      * <br />
      * WEB3StringTypeUtility#createHashValue()にて、ハッシュ値を計算する。<br />
      * <br />
      * [createHashValue()に指定する引数]<br />
      * 　@l_strAlgorithm：SHA-1<br />
      *   l_algorithmObj[]：<br />
      *     l_algorithmObj[0] = 現在日付（YYYYMMDD）<br />
      *     l_algorithmObj[1] = 引数.ハッシュ定数１<br />
      *     l_algorithmObj[2] = 証券会社コード<br />
      *     l_algorithmObj[3] = 部店コード<br />
      *     l_algorithmObj[4] = 顧客コード<br />
      *     l_algorithmObj[5] = 引数.ハッシュ定数２<br />
      * <br />
      * @@param l_mainAccount - 顧客オブジェクト
      * @@param l_hashConst1 - ハッシュ定数１
      * @@param l_hashConst2 - ハッシュ定数２
      * @@return String
      * @@roseuid 4210483D01F8
      */
    protected String getHashValue(
        MainAccount l_account, 
        String l_hashConst1, 
        String l_hashConst2) 
    {
        String l_timestamp = WEB3DateUtility.formatDate( 
                new Date(), "yyyyMMdd"
                );
        String l_institutionCode = 
            l_account.getInstitution().getInstitutionCode();
        String l_branchCode = 
            l_account.getBranch().getBranchCode();
        String l_accountCode = 
            l_account.getAccountCode();
        
        ArrayList l_list = new ArrayList();
        l_list.add(l_timestamp);
        l_list.add(l_hashConst1);
        l_list.add(l_institutionCode);
        l_list.add(l_branchCode);
        l_list.add(l_accountCode);
        l_list.add(l_hashConst2);

        log.debug(this.getParamListString(l_list.toArray()));
        
        String hashValue = WEB3StringTypeUtility.createHashValue(   
            BATO_HASH_ALGORITHM, 
            (String[])(l_list.toArray(new String[0]))
            );
        
        log.debug("計算後Hash値 (" + hashValue + ")");

        return hashValue;
    }
    
    private String getParamListString(Object[] l_paramList) {
        
        if (l_paramList == null || l_paramList.length == 0) {
            return null;
        }
        
        String l_resValue = "パラメータ (";
        int l_size = l_paramList.length;
        for (int i = 0; i < l_size; i ++) 
        {
            String item = (String)l_paramList[i];
            if (item == null) { item = "null"; }
            if (i == 0) {
                l_resValue = l_resValue + item;
            } else {
                l_resValue = l_resValue + ", " + item;
            }
        }
        l_resValue = l_resValue + ")";
        return l_resValue;
    }

    /**
     * (is電子鳩停止中)<BR>
     * <BR>
     * 電子鳩システムが停止中かどうかを判定する。<BR>
     * <BR>
     * １）顧客オブジェクトを取得する。<BR>
     * <BR>
     * this.get口座()をコールする。<BR>
     * <BR>
     * ２）電子鳩システム会社部店別プリファ@レンスを取得する。<BR>
     * <BR>
     * this.get会社部店別プリファ@レンス()をコールする。<BR>
     * <BR>
     * [引数]<BR>
     * 証券会社コード： 顧客.getInstitution().getInstitutionCode()の戻り値<BR>
     * 部店コード： 顧客.getBranch().getBranchCode()の戻り値 <BR>
     * <BR>
     * ３）現在時刻が電子鳩システムの受付時間帯かを調べる。<BR>
     * <BR>
     * 　@　@this.validate接続可能時間()をコールする。<BR>
     * <BR>
     * ４）以下の条件に合致する場合はtrueを、そうでない場合はfalseを返却する。<BR>
     * <BR>
     * ・２）で取得したプリファ@レンス.システム障害フラグ == ”障害中”<BR> 
     * <BR>
     * ・２）で取得したプリファ@レンス.システム障害フラグ == ”稼動中” and<BR> 
     * 　@　@３）の処理が例外をスロー（受付時間外）<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
	public boolean isBatoStopping() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "isBatoStopping()";
        
        log.entering(STR_METHOD_NAME);
        
        // １）顧客オブジェクトを取得する。
        // this.get口座()をコールする。
        WEB3GentradeMainAccount l_account = 
            (WEB3GentradeMainAccount)this.getMainAccount();

        // ２）電子鳩システム会社部店別プリファ@レンスを取得する。
        //  this.get会社部店別プリファ@レンス()をコールする。
        // [引数]
        // 証券会社コード： 顧客.getInstitution().getInstitutionCode()の戻り値
        // 部店コード： 顧客.getBranch().getBranchCode()の戻り値 
        BatoInstBranchPrefParams l_batoInstBranchPrefParams = 
            this.getBatoInstBranchPrefParams(
                l_account.getInstitution().getInstitutionCode(), 
                l_account.getBranch().getBranchCode());
        
        try
        {
            // ３）現在時刻が電子鳩システムの受付時間帯かを調べる。
            // this.validate接続可能時間()をコールする。
            this.validateConnectionTime();
        
            // ４）以下の条件に合致する場合はtrueを、そうでない場合はfalseを返却する。 
            // ・２）で取得したプリファ@レンス.システム障害フラグ == ”障害中”
            if (WEB3GentradeBatoSystemFailureFlagDef.NOT_WORKING.equals(
                l_batoInstBranchPrefParams.getSystemFailureFlag()))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        catch (WEB3BusinessLayerException e)
        {
            log.debug("validate接続可能時間()で例外発生：" + e.getErrorMessage());
            
            // ・２）で取得したプリファ@レンス.システム障害フラグ == ”稼動中” and
            // 　@　@３）の処理が例外をスロー（受付時間外）
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
        
	}

    /**
     * (get電子鳩接続情報)<BR>
     * 電子鳩の接続情報(URL)を取得し、返却する。 <BR>
     * <BR>
     * 1）電子鳩システム接続サービス.get会社部店別プリファ@レンス()を呼び出し、<BR>
     * 　@電子鳩システム会社別プリファ@レンスを取得する。  <BR>
     * 　@[get会社部店別プリファ@レンス()に渡す引数]  <BR>
     * 　@証券会社コード = 引数.顧客.getInstitution().getInstitutionCode()の戻り値<BR>
     * 　@部店コード = 引数.顧客.getBranch().getBranchCode()の戻り値 <BR>
     * <BR>
     * 2）電子鳩システム接続サービス.getハッシュ値()を呼び出し、ハッシュ値を取得する。<BR>
     * 　@[getハッシュ値()に渡す引数] <BR>
     * 　@顧客： 引数.顧客  <BR>
     * 　@ハッシュ定数１： 電子鳩システム会社部店別プリファ@レンスParams.ハッシュ定数１<BR>
     * 　@ハッシュ定数２： 電子鳩システム会社部店別プリファ@レンスParams.ハッシュ定数２<BR>
     * <BR>
     * 3）電子鳩URLの作成 <BR>
     * 　@3-1）パラメータの設定 <BR>
     * 　@　@　@ＵＲＬ　@　@ : 電子鳩システム会社部店別プリファ@レンスParams.URL（一般接続用）<BR>
     * 　@　@　@会社コード : COMPCODE=引数.顧客.getInstitution().getInstitutionCode()の戻り値<BR>
     * 　@　@　@部店コード : BRANCODE=引数.顧客.getBranch().getBranchCode()の戻り値<BR>
     * 　@　@　@顧客コード : USERID=引数.顧客.getAccountCode()の戻り値<BR>
     * 　@　@　@ハッシュ値 : HASHSTRING=電子鳩システム接続サービス.getハッシュ値()の戻り値<BR>
     * 　@3-2）上記のパラメータを以下のように結合する。 <BR>
     * <BR>
     * 　@　@　@ＵＲＬ?会社コード＆部店コード＆顧客コード＆ハッシュ値 <BR>
     * <BR>
     * 　@　@　@例） https//xxx.xxx.jp/denshibato?COMPCODE=99& <BR>
     * BRANCODE=999&USERID=9999999&HASHSTRING=a1fg597s42f64sdf6as8fda <BR>
     * <BR>
     * 4）3）にて作成した電子鳩URLを返却。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getBatoConnectionInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBatoConnectionInfo()";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        //引数.顧客.getInstitution().getInstitutionCode()
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();

        //引数.顧客.getBranch().getBranchCode()
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();

        //1）電子鳩システム接続サービス.get会社部店別プリファ@レンス()を呼び出し、
        //電子鳩システム会社別プリファ@レンスを取得する。
        //[get会社部店別プリファ@レンス()に渡す引数]
        //証券会社コード = 引数.顧客.getInstitution().getInstitutionCode()の戻り値
        //部店コード = 引数.顧客.getBranch().getBranchCode()の戻り値
        BatoInstBranchPrefParams l_batoInstBranchPrefParams =
            this.getBatoInstBranchPrefParams(
                l_strInstitutionCode,
                l_strBranchCode);

        //2）電子鳩システム接続サービス.getハッシュ値()を呼び出し、ハッシュ値を取得する。
        //[getハッシュ値()に渡す引数]
        //顧客： 引数.顧客
        //ハッシュ定数１： 電子鳩システム会社部店別プリファ@レンスParams.ハッシュ定数１
        //ハッシュ定数２： 電子鳩システム会社部店別プリファ@レンスParams.ハッシュ定数２
        String l_strHashValue = this.getHashValue(
            l_mainAccount,
            l_batoInstBranchPrefParams.getHashField1(),
            l_batoInstBranchPrefParams.getHashField2());

        //3）電子鳩URLの作成
        //3-1）パラメータの設定
        //ＵＲＬ　@　@ : 電子鳩システム会社部店別プリファ@レンスParams.URL（一般接続用）
        String l_strURL = l_batoInstBranchPrefParams.getUrl();

        //会社コード : COMPCODE=引数.顧客.getInstitution().getInstitutionCode()の戻り値
        String l_strUrlInstitutionCode = "COMPCODE=" + l_strInstitutionCode;

        //部店コード : BRANCODE=引数.顧客.getBranch().getBranchCode()の戻り値
        String l_strUrlBranchCode = "BRANCODE=" + l_strBranchCode;

        //顧客コード : USERID=引数.顧客.getAccountCode()の戻り値
        String l_strUrlAccountCode = "USERID=" + l_mainAccount.getAccountCode();

        //ハッシュ値 : HASHSTRING=電子鳩システム接続サービス.getハッシュ値()の戻り値
        String l_strUrlHashValue = "HASHSTRING=" + l_strHashValue;

        //3-2）上記のパラメータを以下のように結合する。
        //ＵＲＬ?会社コード＆部店コード＆顧客コード＆ハッシュ値
        String l_strBatoConnectionInfo =
            l_strURL + "?" + l_strUrlInstitutionCode + "&" +
            l_strUrlBranchCode + "&" + l_strUrlAccountCode + "&" + l_strUrlHashValue;

        //4）3）にて作成した電子鳩URLを返却。
        log.exiting(STR_METHOD_NAME);
        return l_strBatoConnectionInfo;
    }

    /**
     * (validate複数銘柄目論見書閲覧)<BR>
     * 複数銘柄の目論見書閲覧済かを判定する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（電子鳩）validate複数銘柄目論見書閲覧」 参照<BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図 ：(電子鳩システム接続サービス / （電子鳩）validate複数銘柄目論見書閲覧) <BR>
     * 具体位置：(部店商品別プリファ@レンス.障害時注文可否フラグ == ”注文不可”の場合<BR>
     * 　@　@　@　@　@例外をスローする。)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_01984 <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図 ：(電子鳩システム接続サービス / （電子鳩）validate複数銘柄目論見書閲覧) <BR>
     * 具体位置：(部店商品別プリファ@レンス.代理入力可否フラグ == ”代理入力不可”の場合<BR>
     * 　@　@　@　@　@例外をスローする。)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_01985 <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図 ：(電子鳩システム接続サービス / （電子鳩）validate複数銘柄目論見書閲覧) <BR>
     * 具体位置：(get代理入力者()の戻り値 != null and <BR>
     * 　@　@　@　@　@取得した代理入力可否フラグ == ”代理入力不可” and <BR>
     * 　@　@　@　@　@引数.代理入力不可時チェックフラグ==trueの場合、例外をスローする。)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_01985 <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図 ：(電子鳩システム接続サービス / （電子鳩）validate複数銘柄目論見書閲覧) <BR>
     * 具体位置：(カンマ区切りで区切られた値が”履歴あり”、”履歴無し”、<BR>
     * 　@　@　@　@　@”履歴管理無し”以外なら、例外をスローする。)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_01959 <BR>
     * ========================================================== <BR>
     * <BR>
     * @@param l_multiDocCheckResultUnit - (複数銘柄目論見書閲覧チェックリスト)<BR>
     * 複数銘柄目論見書閲覧チェックリスト<BR>
     * @@param l_blnIsCheckFlag - (代理入力不可時チェックフラグ)<BR>
     * 代理入力不可時に業務エラーにするかしないかのフラグ<BR>
     * <BR>
     * 代理入力不可時チェックフラグ<BR>
     * true：チェックする<BR>
     * false：チェックしない<BR>
     * @@return WEB3GentradeMultiCheckResults
     * @@throws WEB3BaseException
     */
    public WEB3GentradeMultiCheckResults validateMultiProspectus(
        WEB3GentradeMultiDocCheckResultUnit[] l_multiDocCheckResultUnit,
        boolean l_blnIsCheckFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateMultiProspectus(WEB3GentradeMultiDocCheckResultUnit[], boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_multiDocCheckResultUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        //get補助口座(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //getMainAccount( )
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        //get代理入力者( )
        Trader l_trader = this.getTrader();

        //get会社部店別プリファ@レンス(String, String)
        //[引数]
        // 証券会社コード： 顧客.getInstitution().getInstitutionCode()の戻り値
        // 部店コード： 顧客.getBranch().getBranchCode()の戻り値
        BatoInstBranchPrefParams l_batoInstBranchPrefParams =
            this.getBatoInstBranchPrefParams(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode());

        HashMap l_hmOperatorInputFlag = new HashMap();
        List l_lisTypeCodes = new ArrayList();
        //(*)引数.複数銘柄目論見書閲覧チェックリストの件数分、ループ。
        int l_intMultiDocCheckResultUnitLength = l_multiDocCheckResultUnit.length;
        for (int i = 0; i < l_intMultiDocCheckResultUnitLength; i++)
        {
            // get部店商品別プリファ@レンス(String, String, String)
            //証券会社コード： 顧客.getInstitution().getInstitutionCode()の戻り値
            //部店コード： 顧客.getBranch().getBranchCode()の戻り値
            //種別コード： 引数.複数銘柄目論見書閲覧チェックリスト.種別コード
            BatoBranchProductPrefParams l_batoBranchProductPrefParams =
                this.getBatoBranchProductPrefParams(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    l_multiDocCheckResultUnit[i].typeCode);

            //会社部店別プリファ@レンス.システム障害フラグ == ”障害中” の場合
            if (WEB3GentradeBatoSystemFailureFlagDef.NOT_WORKING.equals(
                l_batoInstBranchPrefParams.getSystemFailureFlag()))
            {
                //）部店商品別プリファ@レンス.障害時注文可否フラグ == ”注文不可”の場合例外をスローする。
                if (WEB3GentradeBatoOrderAtSystemFailureFlagDef.DISABLED.equals(
                    l_batoBranchProductPrefParams.getOrderAtSystemFailureFlag()))
                {
                    log.debug("[電子鳩システム障害中]障害中注文不可。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01984,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "[電子鳩システム障害中]障害中注文不可。");
                }

                //get代理入力者()の戻り値 != null and
                //部店商品別プリファ@レンス.代理入力可否フラグ == ”代理入力不可”
                //例外をスローする。
                if (l_trader != null
                    && WEB3GentradeBatoOperatorInputFlagDef.DISABLED.equals(
                        l_batoBranchProductPrefParams.getOperatorInputFlag()))
                {
                    log.debug("[電子鳩システム障害中]障害中代理入力不可。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01985,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "[電子鳩システム障害中]障害中代理入力不可。");
                }

                //複数銘柄目論見書閲覧チェック結果
                WEB3GentradeMultiCheckResults l_multiCheckResults =
                    new WEB3GentradeMultiCheckResults();

                //チェック結果： null
                //URL： null
                //ハッシュ値： null
                //障害中フラグ:true
                l_multiCheckResults.checkResult = null;
                l_multiCheckResults.url = null;
                l_multiCheckResults.hashValue = null;
                l_multiCheckResults.batoFailureFlag = true;

                log.exiting(STR_METHOD_NAME);
                return l_multiCheckResults;
            }

            //種別コードに対する、代理入力可否フラグのハッシュ化
            //キー ： 引数.複数銘柄目論見書閲覧チェックリスト.種別コード
            //値 ： 電子鳩システム部店商品別プリファ@レンスParams.get代理入力可否フラグ
            //キーが重複する場合は、ハッシュに追加しない
            if (!l_lisTypeCodes.contains(l_multiDocCheckResultUnit[i].typeCode))
            {
                l_hmOperatorInputFlag.put(l_multiDocCheckResultUnit[i].typeCode,
                    l_batoBranchProductPrefParams.getOperatorInputFlag());

                l_lisTypeCodes.add(l_multiDocCheckResultUnit[i].typeCode);
            }
        }

        //validate接続可能時間( )
        this.validateConnectionTime();

        //getハッシュ値(顧客, String, String)
        String l_strHashValue = this.getHashValue(
            l_mainAccount,
            l_batoInstBranchPrefParams.getHashField1(),
            l_batoInstBranchPrefParams.getHashField2());

        //get機@能別プリファ@レンス(String)
        //機@能区分： ”複数銘柄目論見書閲覧チェック”
        BatoFunctionPrefParams l_batoFunctionPrefParams =
            this.getBatoFunctionPrefParams(WEB3GentradeBatoFunctionDivDef.BATO_MULTI_PROSPECTUS_SERVICE);

        //種別コードと銘柄コードのカンマ区切り文字生成
        //引数.複数銘柄目論見書閲覧チェックリストの件数分、ループ
        StringBuffer l_sbTypeCode = new StringBuffer();
        StringBuffer l_sbProductCode = new StringBuffer();
        for (int i = 0; i < l_intMultiDocCheckResultUnitLength; i++)
        {
            //複数銘柄目論見書閲覧チェックリスト.種別コードをカンマ区切りにして、種別コード一覧(カンマ区切り)に追加
            l_sbTypeCode.append(l_multiDocCheckResultUnit[i].typeCode);
            l_sbTypeCode.append(",");

            //複数銘柄目論見書閲覧チェックリスト.銘柄コードをカンマ区切りにして、銘柄コード一覧(カンマ区切り)に追加
            l_sbProductCode.append(l_multiDocCheckResultUnit[i].productCode);
            l_sbProductCode.append(",");
        }

        //銘柄コード一覧(カンマ区切り)と種別コード一覧(カンマ区切り)の最後の文字にはカンマを追加しないように制御する。
        l_sbTypeCode.deleteCharAt(l_sbTypeCode.length() - 1);
        l_sbProductCode.deleteCharAt(l_sbProductCode.length() - 1);

        // invoke電子鳩システム
        ArrayList l_paramList = new ArrayList();
        l_paramList.add(l_mainAccount.getInstitution().getInstitutionCode());
        l_paramList.add(l_mainAccount.getBranch().getBranchCode());
        l_paramList.add(l_mainAccount.getAccountCode());
        l_paramList.add(l_sbTypeCode.toString());
        l_paramList.add(l_sbProductCode.toString());
        l_paramList.add(l_strHashValue);

        String l_strInvokeBatoSystem = this.invokeBatoSystem(
            l_paramList.toArray(),
            l_batoInstBranchPrefParams.getSoapUrl(),
            l_batoFunctionPrefParams);

        //invoke電子鳩システムの戻り値の文字列をカンマ区切りでループする。
        String[] l_strInvokeBatoSystems = l_strInvokeBatoSystem.split(",");

        for(int i = 0; i < l_intMultiDocCheckResultUnitLength; i++)
        {
            // カンマ区切りで区切られた値が”履歴無し”の場合
            if (WEB3GentradeBatoProspectusServiceResultDef.NO_HISTORY.equals(l_strInvokeBatoSystems[i]))
            {
                //代理入力可否フラグのハッシュから引数.複数銘柄目論見書閲覧チェックリスト[index].種別コードをキーとして、
                //代理入力可否フラグを取得する。
                String l_strTypeCode = (String)l_hmOperatorInputFlag.get(l_multiDocCheckResultUnit[i].typeCode);

                //get代理入力者()の戻り値 != null and
                //取得した代理入力可否フラグ == ”代理入力不可” and
                //引数.代理入力不可時チェックフラグ==trueの場合、例外をスローする。
                if (l_trader != null
                    && WEB3GentradeBatoOperatorInputFlagDef.DISABLED.equals(l_strTypeCode)
                    && l_blnIsCheckFlag)
                {
                    log.debug("[電子鳩システム稼動中]目論見書閲覧未済顧客の代理入力不可。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01988,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "[電子鳩システム稼動中]目論見書閲覧未済顧客の代理入力不可。");
                }

                //引数.複数銘柄目論見書閲覧チェックリスト[index].チェック結果に以下の値をセット
                //カンマ区切りで区切られた値が”履歴なし”の場合、”1： 閲覧未済”
                l_multiDocCheckResultUnit[i].checkResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION;
            }
            else if (WEB3GentradeBatoProspectusServiceResultDef.HISTORY.equals(l_strInvokeBatoSystems[i])
                || WEB3GentradeBatoProspectusServiceResultDef.CHECK_OFF.equals(l_strInvokeBatoSystems[i]))
            {
                //カンマ区切りで区切られた値が”履歴あり”or”履歴管理なし”の場合、”0： 閲覧済”
                l_multiDocCheckResultUnit[i].checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
            }
            // カンマ区切りで区切られた値が”履歴あり”、”履歴無し”、”履歴管理無し”以外なら、例外をスローする。
            else
            {
                log.debug("電子鳩システム呼出でエラーが発生しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01959,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "電子鳩システム呼出でエラーが発生しました。");
            }
        }

        //複数銘柄目論見書閲覧チェック結果
        WEB3GentradeMultiCheckResults l_checkResults = new WEB3GentradeMultiCheckResults();

        //プロパティセット
        //チェック結果：引数.複数銘柄目論見書閲覧チェックリスト
        l_checkResults.checkResult = l_multiDocCheckResultUnit;

        //URL： 会社部店別プリファ@レンス.URL（一般接続用）
        l_checkResults.url = l_batoInstBranchPrefParams.getUrl();

        //ハッシュ値：getハッシュ値()の戻り値（ハッシュ値①@）
        l_checkResults.hashValue = l_strHashValue;

        //障害中フラグ:false
        l_checkResults.batoFailureFlag = false;

        log.exiting(STR_METHOD_NAME);
        return l_checkResults;
    }
}
@


1.5
log
@*** empty log message ***
@
text
@d1261 1
a1261 1

@


1.4
log
@*** empty log message ***
@
text
@d1170 1
a1170 3
                        "\nhttps.proxyPort (" + l_urlArr[1] + ")" +
                        "\nweblogic.webservice.transport.https.proxy.host (" + l_urlArr[0] + ")" +
                        "\nweblogic.webservice.transport.https.proxy.port (" + l_urlArr[1] + ")";
d1183 1
a1183 3
                        "\nhttp.proxyPort (" + l_urlArr[1] + ")" +
                        "\nweblogic.webservice.transport.http.proxy.host (" + l_urlArr[0] + ")" +
                        "\nweblogic.webservice.transport.http.proxy.port (" + l_urlArr[1] + ")";
@


1.3
log
@*** empty log message ***
@
text
@a18 5
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;
a19 1
import org.apache.axis2.AxisFault;
@


1.2
log
@*** empty log message ***
@
text
@d1168 6
a1173 2
                    System.setProperty("weblogic.webservice.transport.https.proxy.host", l_urlArr[0]);
                    System.setProperty("weblogic.webservice.transport.https.proxy.port", l_urlArr[1]);
@


1.1
log
@*** empty log message ***
@
text
@d25 7
d1143 1
a1143 1
        
d1146 5
a1150 4
        
        System.setProperty("javax.xml.rpc.ServiceFactory",
            "weblogic.webservice.core.rpc.ServiceFactoryImpl");
        
d1153 2
a1154 1
        if (l_urlArr.length == 3)
d1156 3
a1158 1
            l_soapUrl = l_urlArr[2];
d1160 1
a1160 1
            if (l_soapUrl.indexOf("https://") >= 0)
d1162 34
a1195 9
                System.setProperty("https.proxyHost", l_urlArr[0]);
                System.setProperty("https.proxyPort", l_urlArr[1]);
                System.setProperty("weblogic.webservice.transport.https.proxy.host", l_urlArr[0]);
                System.setProperty("weblogic.webservice.transport.https.proxy.port", l_urlArr[1]);
                l_logMessage = 
                    "\nhttps.proxyHost (" + l_urlArr[0] + ")" +
                    "\nhttps.proxyPort (" + l_urlArr[1] + ")" +
                    "\nweblogic.webservice.transport.https.proxy.host (" + l_urlArr[0] + ")" +
                    "\nweblogic.webservice.transport.https.proxy.port (" + l_urlArr[1] + ")";
a1197 4
                System.setProperty("http.proxyHost", l_urlArr[0]);
                System.setProperty("http.proxyPort", l_urlArr[1]);
                System.setProperty("weblogic.webservice.transport.http.proxy.host", l_urlArr[0]);
                System.setProperty("weblogic.webservice.transport.http.proxy.port", l_urlArr[1]);
d1199 8
a1206 5
                    "\nhttp.proxyHost (" + l_urlArr[0] + ")" +
                    "\nhttp.proxyPort (" + l_urlArr[1] + ")" +
                    "\nweblogic.webservice.transport.http.proxy.host (" + l_urlArr[0] + ")" +
                    "\nweblogic.webservice.transport.http.proxy.port (" + l_urlArr[1] + ")";

a1208 20
            log.debug(l_logMessage);
        } else if (l_urlArr.length == 1)
        {
            l_soapUrl = l_url;
        } else
        {
            l_logMessage = 
                "電子鳩システム会社部店別プリファ@レンス.URL（SOAP接続用）" + 
                "のセクション数が、異なっています。\n" +
                "「[proxy-host;proxy-port;]soap-url」でセットしてください。";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01959,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_logMessage
                );
        }
        
		try
		{
			ServiceFactory factory = ServiceFactory.newInstance();
d1214 1
a1214 9
			
			QName l_serviceQName = new QName(
			    l_targetNamespace,
			    l_serviceName
			    );
			QName l_portTypeQName = new QName(
			    l_targetNamespace,
			    l_portTypeName 
			    );
d1219 4
a1222 7
			
			Service service = factory.createService(l_serviceQName);
			
			Call call = service.createCall();
			call.setPortTypeName(l_portTypeQName);
			call.setOperationName(l_operationQName);
			
d1230 1
a1230 1
            
a1254 5
                    
                    call.addParameter(
                        l_paramNames[i], 
                        XSD_STRING, 
                        ParameterMode.IN);
d1257 13
a1269 6
            
			call.setReturnType(XSD_STRING);
			call.setTargetEndpointAddress(l_targetNamespace);
			
			l_resValue = (String)call.invoke(l_paramList);
		} catch (RemoteException e)
a1275 7
		} catch (ServiceException e)
		{
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01959,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[SOAPサービスエラー]\n" + l_logMessage, 
                e);
d1277 1
a1277 1
        
@

