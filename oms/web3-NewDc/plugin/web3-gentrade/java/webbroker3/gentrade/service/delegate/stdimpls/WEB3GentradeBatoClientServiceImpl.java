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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �d�q���V�X�e���ڑ��T�[�r�X�����N���X(WEB3GentradeBatoClientServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 ����(�r�q�`) �V�K�쐬
Revesion History : 2008/05/20 ��іQ (���u)���f��No.328
Revesion History : 2008/06/18 ��іQ (���u)���f��No.330
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
 * �d�q���V�X�e���ڑ��T�[�r�X�����N���X
 */
public class WEB3GentradeBatoClientServiceImpl 
    extends WEB3ClientRequestService 
    implements WEB3GentradeBatoClientService
{
    
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBatoClientServiceImpl.class);

    /**
     * �O���@@�փR�[�h�D�d�q���F03
     */
    private static final String BATO_OTHER_ORG_CODE = "03";
   
    /**
     * �d�q���n�b�V���v�Z�A���S���Y���FSHA-1
     */
    private static final String BATO_HASH_ALGORITHM = "SHA-1";

    /**
     * ������l�[���X�y�[�X��
     */
    private final QName XSD_STRING = 
        new QName("http://www.w3.org/2001/XMLSchema", "string");

    /**
     * �p�����[�^���X�g�Z�p���[�^
     */
    private final String PARAMETER_LIST_DEL = ":"; 

    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 423698F6008A
     */
    public WEB3GentradeBatoClientServiceImpl() 
    {
    }
   
    /**
     * �d�q���V�X�e����ʂ̕\���ɕK�v�ȃf�[�^���擾���ԋp����B<br />
     * <br />
     * �V�[�P���X�}<br />
     * �u�i�d�q���jget��ʕ\���f�[�^�v �Q��<br />
     * <br />
     * @@param l_request - ���N�G�X�g�f�[�^<br />
     * @@exception  BUSINESS_ERROR_00013:�@@��t���ԊO
     * @@exception  SYSTEM_ERROR_80003:�@@DB�G���[
     * @@exception  SYSTEM_ERROR_80005:�@@�Y���f�[�^�Ȃ�
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
                log.debug("validate�ڑ��\����()�ŗ�O�����F" + e.getErrorMessage());
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
     * �ivalidate�ژ_�����{���j<br />
     * <br />
     * �ژ_�����{���ς��𔻒肷��B<br />
     * <br />
     * �V�[�P���X�}<br />
     * �u�i�d�q���jvalidate�ژ_�����{���v �Q��<br />
     * <br />
     * @@param l_typeCode ��ʃR�[�h(�o�q�w���擾)<br />
     * @@param l_productCode �����R�[�h<br />
     * @@return webbroker3.gentrade.message.WEB3GentradeProspectusResult<br />
     * @@see WEB3GentradeProspectusResult
     * @@see WEB3GentradeBatoProspectusServiceResultDef
     * @@exception  BUSINESS_ERROR_00013:�@@��t���ԊO
     * @@exception  BUSINESS_ERROR_01959:�@@�d�q���G���[
     * @@exception  BUSINESS_ERROR_01984:�@@[�d�q���V�X�e����Q��]��Q�������s��
     * @@exception  BUSINESS_ERROR_01985:�@@[�d�q���V�X�e����Q��]��Q���㗝���͕s��
     * @@exception  SYSTEM_ERROR_80003:�@@DB�G���[
     * @@exception  SYSTEM_ERROR_80005:�@@�Y���f�[�^�Ȃ�
     * @@exception  SYSTEM_ERROR_80006:  �����G���[
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
                "����.��ʃR�[�h��null�ł��B");
        }
        if (l_productCode == null) { 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����.�����R�[�h��null�ł��B");
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
            l_errorMsg = "[�ژ_�����{���`�F�b�N�G���[] " +
                "�d�q���ԋp�l(" + l_resValue + ") " + 
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
     * �ivalidate�d�q�����{�j<br />
     * <br />
     * �d�q���V�X�e���Ńp�����[�^�̋@@�\�����{����Ă��邩�𔻒肷��B<br />
     * <br />
     * �V�[�P���X�}<br />
     * �u�i�d�q���jvalidate�d�q�����{�v �Q��<br />
     * <br />
     * <br />
     * [�߂�l]<br />
     * ������.�@@�\�敪���h�d�q�������`�F�b�N�h�̏ꍇ<br />
     *   0�F �����ӌڋq<br />
     *   1�F ���ӌڋq<br />
     * ���@@WEB3GentradeBatoServiceRegServiceResultDef�ɂĒ萔��`<br />
     * <br />
     * ������.�@@�\�敪���h����񍐏����{�`�F�b�N�h�̏ꍇ<br />
     *   0�F �����ӌڋq<br />
     *   1�F ���ӌڋq<br />
     *   2�F �����{���<br />
     * ���@@WEB3GentradeBatoTranHistServiceResultDef�ɂĒ萔��`<br />
     * <br />
     * @@param l_functionDiv  �@@�\�敪(WEB3GentradeBatoFunctionDivDef)
     * @@return �d�q���V�X�e���̖߂�l(WEB3GentradeBatoTranHistServiceResultDef,WEB3GentradeBatoServiceRegServiceResultDef)<br />
     * @@see WEB3GentradeBatoFunctionDivDef
     * @@see WEB3GentradeBatoTranHistServiceResultDef
     * @@see WEB3GentradeBatoServiceRegServiceResultDef
     * @@exception  BUSINESS_ERROR_00013:�@@��t���ԊO
     * @@exception  BUSINESS_ERROR_01984:�@@[�d�q���V�X�e����Q��]��Q�������s��
     * @@exception  BUSINESS_ERROR_01959:�@@�d�q���G���[�@@ 
     * @@exception  SYSTEM_ERROR_80005:�@@�Y���f�[�^�Ȃ�
     * @@exception  SYSTEM_ERROR_80003:�@@DB�G���[
     * @@exception  SYSTEM_ERROR_80006:  �����G���[
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
                "����.�@@�\�敪��null�ł��B");
        } else if (WEB3GentradeBatoFunctionDivDef.BATO_SERVICE_REG_SERVICE.equals(
            l_functionDiv)) 
        {
            log.debug("[�d�q�������`�F�b�N]");
        } else if (WEB3GentradeBatoFunctionDivDef.BATO_TRAN_HIST_SERVICE.equals(
            l_functionDiv)) 
        {
            log.debug("[����񍐏����{�`�F�b�N]");
        } else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����.�@@�\�敪�ɂ́A" +
                "[00�F�d�q�������`�F�b�N]�܂��́A[02�F����񍐏����{�`�F�b�N]" +
                "���w�肵�Ă��������B" + "�w��l(" + l_functionDiv + ")");
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
            l_errorMsg = "[�d�q�������`�F�b�N�G���[] " +
                "�d�q���ԋp�l(" + l_resValue + ") " + 
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
            l_errorMsg = "[����񍐏����{�`�F�b�N�G���[] " +
                "�d�q���ԋp�l(" + l_resValue + ") " + 
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
     * �ivalidate�ڑ��\���ԁj<br />
     * <br />
     * �d�q���V�X�e���ɐڑ��\�Ȏ��ԑт��ǂ����̃`�F�b�N���s���B<br />
     * <br />
     * �P�j���ݎ����̃J�����_���擾����B<br />
     * Calendar.getInstance()<br />
     * Calendar.setTime(GtlUtils.getSystemTimestamp())<br />
     * <br />
     * �Q�j�O���@@�֎�t���ԊO�i�j���j�e�[�u���`�F�b�N<br />
     * �O���@@�֎�t���ԊO�i�j���j�e�[�u������ȉ��̏����̃��R�[�h���擾����B<br />
     * <br />
     * [��������]<br />
     * �O���@@�փR�[�h = �h�d�q���V�X�e���h and<br />
     * (�� = Calendar.get("MONTH")�̖߂�l+1 or �� = "0" ) and<br />
     * �j�� = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and<br />
     * (�T�ԍ� = Calendar.get("WEEK_OF_MONTH")�̖߂�l or �j���ԍ� = "0") and<br />
     * ��~���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and<br />
     * ��~���ԁiTO) >= �J�����_�̎����iHHMMSS�j����<br />
     * <br />
     * �擾�ł����ꍇ�A��O�iBUSINESS_ERROR_00013�j��throw����B<br />
     * <br />
     * �R�j�O���@@�֎�t���ԊO�i���t�j�e�[�u���`�F�b�N<br />
     * �O���@@�֎�t���ԊO�i���t�j�e�[�u������ȉ��̏����̃��R�[�h���擾����B<br />
     * <br />
     * [��������]<br />
     * �O���@@�փR�[�h = �h�d�q���V�X�e���h and<br />
     * ���t = �J�����_�̓��t(MMDD)���� and<br />
     * ��~���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and<br />
     * ��~���ԁiTO) >= �J�����_�̎����iHHMMSS�j����<br />
     * <br />
     * �擾�ł����ꍇ�A��O�iBUSINESS_ERROR_00013�j��throw����B<br />
     * <br />
     * �S�j�O���@@�֎�t���ԃe�[�u���`�F�b�N<br />
     * <br />
     * �S�|�P�j�O���@@�֎�t���ԃe�[�u������ȉ��̏����̃��R�[�h���擾����B<br />
     * <br />
     * [��������]<br />
     * �O���@@�փR�[�h = �h�d�q���V�X�e���h and<br />
     * �j���敪 = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and<br />
     * ��t���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and <br />
     * ��t���ԁiTO) >= �J�����_�̎����iHHMMSS�j����<br />
     * <br />
     * �擾�ł��Ȃ������ꍇ�A��O�iBUSINESS_ERROR_00013�j��throw����B<br />
     * <br />
     * �S�|�Q�j�擾�������R�[�h�̃T�[�r�X�敪��0�i��~���j�̏ꍇ�A��O�iBUSINESS_ERROR_00013�j��throw����B<br />
     * <br />
     * 
     * <br />
     * @@exception  BUSINESS_ERROR_00013:��t���ԊO
     * @@exception  SYSTEM_ERROR_80003:DB�G���[
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
         * �y�O���@@�֎�t���ԊO�i�j���j�`�F�b�N�z
         *  
         * [��������] 
         * �O���@@�փR�[�h = �h�d�q���V�X�e���h and 
         * (�� = Calendar.get("MONTH")�̖߂�l+1 or �� = "0" ) and 
         * �j�� = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and 
         * (�T�ԍ� = Calendar.get("WEEK_OF_MONTH")�̖߂�l or �j���ԍ� = "0") and 
         * ��~���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and 
         * ��~���ԁiTO) >= �J�����_�̎����iHHMMSS�j���� 
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
                "SQL�G���[",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�l�b�g���[�N�G���[",
                e);
        }
        
        if (!isAccept) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[�O���@@�֎�t���ԊO�i�j���j�e�[�u��] " +
                "�O���@@�փR�[�h.�d�q���V�X�e��(" + BATO_OTHER_ORG_CODE + "), " +
                "��(" + l_mm + "), " +
                "�j���敪(" + l_weekDiv + "), " +
                "�T�ԍ�(" + Integer.toString(l_weekOfMonth) + "), " +
                "�`�F�b�N����(" + l_time + ")"
                );
        }

        
        /*
         * �y�O���@@�֎�t���ԊO�i���t�j�`�F�b�N�z
         * 
         * [��������]
         * �O���@@�փR�[�h = �h�d�q���V�X�e���h and
         * ���t = �J�����_�̓��t(MMDD)���� and
         * ��~���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and
         * ��~���ԁiTO) >= �J�����_�̎����iHHMMSS�j����
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
                "SQL�G���[",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�l�b�g���[�N�G���[",
                e);
        }
        
        if (!isAccept) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[�O���@@�֎�t���ԊO�i���t�j�e�[�u��] " +
                "�O���@@�փR�[�h.�d�q���V�X�e��(" + BATO_OTHER_ORG_CODE + "), " +
                "���t(" + l_mmdd + "), " +
                "�`�F�b�N����(" + l_time + ")"
                );
        }

        
        /*
         * �y�O���@@�֎�t���ԃ`�F�b�N�z
         * 
         * [��������] 
         * �O���@@�փR�[�h = �h�d�q���V�X�e���h and 
         * �j���敪 = Calendar.get("DAY_OF_WEEK")�̖߂�l-1 and 
         * ��t���ԁiFROM) <= �J�����_�̎����iHHMMSS�j���� and 
         * ��t���ԁiTO) >= �J�����_�̎����iHHMMSS�j����          *  
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
                "[�O���@@�֎�t���ԃe�[�u���D�Y���f�[�^�Ȃ�] " +
                "�O���@@�փR�[�h.�d�q���V�X�e��(" + BATO_OTHER_ORG_CODE + "), " +
                "�j���敪(" + l_weekDiv + "), " +
                "�`�F�b�N����(" + l_time + ")"
                );
        } catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "SQL�G���[",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�l�b�g���[�N�G���[",
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
                    "[�O���@@�֎�t���ԃe�[�u���D��~��] " +
                    "�O���@@�փR�[�h.�d�q���V�X�e��(" + BATO_OTHER_ORG_CODE + "), " +
                    "�j���敪(" + l_weekDiv + "), " +
                    "�`�F�b�N����(" + l_time + ")"
                    );
            }
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "[�O���@@�֎�t���ԃe�[�u���D�Y���f�[�^�Ȃ�] " +
                "�O���@@�փR�[�h.�d�q���V�X�e��(" + BATO_OTHER_ORG_CODE + "), " +
                "�j���敪(" + l_weekDiv + "), " +
                "�`�F�b�N����(" + l_time + ")"
                );
        }
         
        log.exiting(STR_METHOD_NAME);
    }
   
    /**
     * �iget��Е��X�ʃv���t�@@�����X�j<br />
     * <br />
     * �d�q���V�X�e����Е��X�ʃv���t�@@�����X���擾����B<br />
     * <br />
     * �P�j�ȉ��̏����ŁA�d�q���V�X�e����Е��X�ʃv���t�@@�����X���烌�R�[�h���擾����B<br />
     * <br />
     *    [����]<br />
     *    �،���ЃR�[�h�F ����.�،���ЃR�[�h<br />
     *    ���X�R�[�h�F ����.���X�R�[�h<br />
     * <br />
     * �Q�j�擾�����d�q���V�X�e����Е��X�ʃv���t�@@�����X�̍s�I�u�W�F�N�g��ԋp����B<br />
     * <br />
     * @@param l_institutionCode - �،���ЃR�[�h
     * @@param l_branchCode - ���X�R�[�h
     * @@exception  SYSTEM_ERROR_80005:�@@�Y���f�[�^�Ȃ�
     * @@exception  SYSTEM_ERROR_80003:�@@DB�G���[
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
                "[��Е��X�ʃv���t�@@�����X�e�[�u���D�Y���f�[�^�Ȃ�] " +
                "�،����(" + l_institutionCode + "), " +
                "���X(" + l_branchCode + ")"
                );
        } catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "SQL�G���[",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�l�b�g���[�N�G���[",
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
   
    /**
     * �iget���X���i�ʃv���t�@@�����X�j<br />
     * <br />
     * �d�q���V�X�e�����X���i�ʃv���t�@@�����X���擾����B<br />
     * <br />
     * �P�j�ȉ��̏����ŁA�d�q���V�X�e���h�L�������g��ʊǗ��e�[�u�����烌�R�[�h���擾��
     * ��B<br />
     * <br />
     *    [����]<br />
     *    �،���ЃR�[�h�F ����.�،���ЃR�[�h<br />
     *    ��ʃR�[�h�F ����.��ʃR�[�h<br />
     * <br />
     * �Q�j�ȉ��̏����ŁA�d�q���V�X�e�����X���i�ʃv���t�@@�����X���烌�R�[�h���擾����B<br />
     * <br />
     *    [����]<br />
     *    �،���ЃR�[�h�F ����.�،���ЃR�[�h<br />
     *    ���X�R�[�h�F ����.���X�R�[�h<br />
     *    ���i�R�[�h�F 
     * �P�j�Ŏ擾�����d�q���V�X�e���h�L�������g��ʊǗ��e�[�u��.���i�R�[�h<br />
     * <br />
     * �R�j�擾�����d�q���V�X�e�����X���i�ʃv���t�@@�����X�̍s�I�u�W�F�N�g��ԋp����B<br />
     * 
     * @@param l_institutionCode - �،���ЃR�[�h
     * @@param l_branchCode - ���X�R�[�h
     * @@param l_typeCode - ��ʃR�[�h
     * @@exception  SYSTEM_ERROR_80005:�@@�Y���f�[�^�Ȃ�
     * @@exception  SYSTEM_ERROR_80003:�@@DB�G���[
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
                "[�d�q���V�X�e���h�L�������g��ʊǗ��e�[�u���D�Y���f�[�^�Ȃ�] " +
                "�،����(" + l_institutionCode + "), " +
                "��ʃR�[�h(" + l_typeCode + ")"
                );
        } catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "SQL�G���[",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�l�b�g���[�N�G���[",
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
                "[���X���i�ʃv���t�@@�����X�e�[�u���D�Y���f�[�^�Ȃ�] " +
                "�،����(" + l_institutionCode + "), " +
                "���X(" + l_branchCode + "), " +
                "���i(" + l_prodCode + ")"
                );
        } catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "SQL�G���[",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�l�b�g���[�N�G���[",
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
   
    /**
     * �iget�@@�\�ʃv���t�@@�����X�j<br />
     * <br />
     * �d�q���V�X�e���@@�\�ʃv���t�@@�����X���擾����B<br />
     * <br />
     * �P�j�ȉ��̏����ŁA�d�q���V�X�e���@@�\�ʃv���t�@@�����X���烌�R�[�h���擾����B<br />
     * <br />
     *    [����]<br />
     *    �@@�\�敪�F ����.�@@�\�敪<br />
     * <br />
     * �Q�j�擾�����d�q���V�X�e���@@�\�ʃv���t�@@�����X�̍s�I�u�W�F�N�g��ԋp����B<br />
     * 
     * @@exception  SYSTEM_ERROR_80005:�@@�Y���f�[�^�Ȃ�
     * @@exception  SYSTEM_ERROR_80003:�@@DB�G���[
     * @@param l_functionDiv - �@@�\�敪
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
                "[�@@�\�ʃv���t�@@�����X�e�[�u���D�Y���f�[�^�Ȃ�] " +
                "�@@�\�R�[�h(" + l_functionDiv + ")"
                );
        } catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "SQL�G���[",
                e);
        } catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�l�b�g���[�N�G���[",
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
   
    /**
     * �iinvoke�d�q���V�X�e���j<br />
     * <br />
     * �d�q���V�X�e���̏������R�[������B<br />
     * <br />
     * �V�[�P���X�}<br />
     * �u�i�d�q���jinvoke�d�q���V�X�e���v �Q��<br />
     * 
     * @@param l_paramList - �p�����[�^�̔z��
     * @@param l_url - URL
     * @@param l_BatoFunctionPrefParams - 
     * �d�q���V�X�e���@@�\�ʃv���t�@@�����X�s�I�u�W�F�N�g
     * @@exception  BUSINESS_ERROR_01959:�@@�d�q���G���[�@@ 
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
                    "�d�q���V�X�e����Е��X�ʃv���t�@@�����X.URL�iSOAP�ڑ��p�j" + 
                    "�̃Z�N�V���������A�قȂ��Ă��܂��B\n" +
                    "�u[proxy-host;proxy-port;]soap-url�v�ŃZ�b�g���Ă��������B";
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
                        "�d�q���V�X�e���@@�\�ʃv���t�@@�����X.�p�����[�^���X�g" + 
                        "�̃Z�N�V���������A�����̐��ƈقȂ��Ă��܂��B\n" +
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
                "[�ʐM�G���[]\n" + l_logMessage, 
                e);
		}

        log.exiting(STR_METHOD_NAME + " �d�q���ԋp�l�i" + l_resValue + "�j");
        return l_resValue;
    }
    
     /**
      * �iget�n�b�V���l�j<br />
      * <br />
      * WEB3StringTypeUtility#createHashValue()�ɂāA�n�b�V���l���v�Z����B<br />
      * <br />
      * [createHashValue()�Ɏw�肷�����]<br />
      * �@@l_strAlgorithm�FSHA-1<br />
      *   l_algorithmObj[]�F<br />
      *     l_algorithmObj[0] = ���ݓ��t�iYYYYMMDD�j<br />
      *     l_algorithmObj[1] = ����.�n�b�V���萔�P<br />
      *     l_algorithmObj[2] = �،���ЃR�[�h<br />
      *     l_algorithmObj[3] = ���X�R�[�h<br />
      *     l_algorithmObj[4] = �ڋq�R�[�h<br />
      *     l_algorithmObj[5] = ����.�n�b�V���萔�Q<br />
      * <br />
      * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
      * @@param l_hashConst1 - �n�b�V���萔�P
      * @@param l_hashConst2 - �n�b�V���萔�Q
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
        
        log.debug("�v�Z��Hash�l (" + hashValue + ")");

        return hashValue;
    }
    
    private String getParamListString(Object[] l_paramList) {
        
        if (l_paramList == null || l_paramList.length == 0) {
            return null;
        }
        
        String l_resValue = "�p�����[�^ (";
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
     * (is�d�q����~��)<BR>
     * <BR>
     * �d�q���V�X�e������~�����ǂ����𔻒肷��B<BR>
     * <BR>
     * �P�j�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * this.get����()���R�[������B<BR>
     * <BR>
     * �Q�j�d�q���V�X�e����Е��X�ʃv���t�@@�����X���擾����B<BR>
     * <BR>
     * this.get��Е��X�ʃv���t�@@�����X()���R�[������B<BR>
     * <BR>
     * [����]<BR>
     * �،���ЃR�[�h�F �ڋq.getInstitution().getInstitutionCode()�̖߂�l<BR>
     * ���X�R�[�h�F �ڋq.getBranch().getBranchCode()�̖߂�l <BR>
     * <BR>
     * �R�j���ݎ������d�q���V�X�e���̎�t���ԑт��𒲂ׂ�B<BR>
     * <BR>
     * �@@�@@this.validate�ڑ��\����()���R�[������B<BR>
     * <BR>
     * �S�j�ȉ��̏����ɍ��v����ꍇ��true���A�����łȂ��ꍇ��false��ԋp����B<BR>
     * <BR>
     * �E�Q�j�Ŏ擾�����v���t�@@�����X.�V�X�e����Q�t���O == �h��Q���h<BR> 
     * <BR>
     * �E�Q�j�Ŏ擾�����v���t�@@�����X.�V�X�e����Q�t���O == �h�ғ����h and<BR> 
     * �@@�@@�R�j�̏�������O���X���[�i��t���ԊO�j<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
	public boolean isBatoStopping() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "isBatoStopping()";
        
        log.entering(STR_METHOD_NAME);
        
        // �P�j�ڋq�I�u�W�F�N�g���擾����B
        // this.get����()���R�[������B
        WEB3GentradeMainAccount l_account = 
            (WEB3GentradeMainAccount)this.getMainAccount();

        // �Q�j�d�q���V�X�e����Е��X�ʃv���t�@@�����X���擾����B
        //  this.get��Е��X�ʃv���t�@@�����X()���R�[������B
        // [����]
        // �،���ЃR�[�h�F �ڋq.getInstitution().getInstitutionCode()�̖߂�l
        // ���X�R�[�h�F �ڋq.getBranch().getBranchCode()�̖߂�l 
        BatoInstBranchPrefParams l_batoInstBranchPrefParams = 
            this.getBatoInstBranchPrefParams(
                l_account.getInstitution().getInstitutionCode(), 
                l_account.getBranch().getBranchCode());
        
        try
        {
            // �R�j���ݎ������d�q���V�X�e���̎�t���ԑт��𒲂ׂ�B
            // this.validate�ڑ��\����()���R�[������B
            this.validateConnectionTime();
        
            // �S�j�ȉ��̏����ɍ��v����ꍇ��true���A�����łȂ��ꍇ��false��ԋp����B 
            // �E�Q�j�Ŏ擾�����v���t�@@�����X.�V�X�e����Q�t���O == �h��Q���h
            if (WEB3GentradeBatoSystemFailureFlagDef.NOT_WORKING.equals(
                l_batoInstBranchPrefParams.getSystemFailureFlag()))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        catch (WEB3BusinessLayerException e)
        {
            log.debug("validate�ڑ��\����()�ŗ�O�����F" + e.getErrorMessage());
            
            // �E�Q�j�Ŏ擾�����v���t�@@�����X.�V�X�e����Q�t���O == �h�ғ����h and
            // �@@�@@�R�j�̏�������O���X���[�i��t���ԊO�j
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
        
	}

    /**
     * (get�d�q���ڑ����)<BR>
     * �d�q���̐ڑ����(URL)���擾���A�ԋp����B <BR>
     * <BR>
     * 1�j�d�q���V�X�e���ڑ��T�[�r�X.get��Е��X�ʃv���t�@@�����X()���Ăяo���A<BR>
     * �@@�d�q���V�X�e����Еʃv���t�@@�����X���擾����B  <BR>
     * �@@[get��Е��X�ʃv���t�@@�����X()�ɓn������]  <BR>
     * �@@�،���ЃR�[�h = ����.�ڋq.getInstitution().getInstitutionCode()�̖߂�l<BR>
     * �@@���X�R�[�h = ����.�ڋq.getBranch().getBranchCode()�̖߂�l <BR>
     * <BR>
     * 2�j�d�q���V�X�e���ڑ��T�[�r�X.get�n�b�V���l()���Ăяo���A�n�b�V���l���擾����B<BR>
     * �@@[get�n�b�V���l()�ɓn������] <BR>
     * �@@�ڋq�F ����.�ڋq  <BR>
     * �@@�n�b�V���萔�P�F �d�q���V�X�e����Е��X�ʃv���t�@@�����XParams.�n�b�V���萔�P<BR>
     * �@@�n�b�V���萔�Q�F �d�q���V�X�e����Е��X�ʃv���t�@@�����XParams.�n�b�V���萔�Q<BR>
     * <BR>
     * 3�j�d�q��URL�̍쐬 <BR>
     * �@@3-1�j�p�����[�^�̐ݒ� <BR>
     * �@@�@@�@@�t�q�k�@@�@@ : �d�q���V�X�e����Е��X�ʃv���t�@@�����XParams.URL�i��ʐڑ��p�j<BR>
     * �@@�@@�@@��ЃR�[�h : COMPCODE=����.�ڋq.getInstitution().getInstitutionCode()�̖߂�l<BR>
     * �@@�@@�@@���X�R�[�h : BRANCODE=����.�ڋq.getBranch().getBranchCode()�̖߂�l<BR>
     * �@@�@@�@@�ڋq�R�[�h : USERID=����.�ڋq.getAccountCode()�̖߂�l<BR>
     * �@@�@@�@@�n�b�V���l : HASHSTRING=�d�q���V�X�e���ڑ��T�[�r�X.get�n�b�V���l()�̖߂�l<BR>
     * �@@3-2�j��L�̃p�����[�^���ȉ��̂悤�Ɍ�������B <BR>
     * <BR>
     * �@@�@@�@@�t�q�k?��ЃR�[�h�����X�R�[�h���ڋq�R�[�h���n�b�V���l <BR>
     * <BR>
     * �@@�@@�@@��j https//xxx.xxx.jp/denshibato?COMPCODE=99& <BR>
     * BRANCODE=999&USERID=9999999&HASHSTRING=a1fg597s42f64sdf6as8fda <BR>
     * <BR>
     * 4�j3�j�ɂč쐬�����d�q��URL��ԋp�B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
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
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        //����.�ڋq.getInstitution().getInstitutionCode()
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();

        //����.�ڋq.getBranch().getBranchCode()
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();

        //1�j�d�q���V�X�e���ڑ��T�[�r�X.get��Е��X�ʃv���t�@@�����X()���Ăяo���A
        //�d�q���V�X�e����Еʃv���t�@@�����X���擾����B
        //[get��Е��X�ʃv���t�@@�����X()�ɓn������]
        //�،���ЃR�[�h = ����.�ڋq.getInstitution().getInstitutionCode()�̖߂�l
        //���X�R�[�h = ����.�ڋq.getBranch().getBranchCode()�̖߂�l
        BatoInstBranchPrefParams l_batoInstBranchPrefParams =
            this.getBatoInstBranchPrefParams(
                l_strInstitutionCode,
                l_strBranchCode);

        //2�j�d�q���V�X�e���ڑ��T�[�r�X.get�n�b�V���l()���Ăяo���A�n�b�V���l���擾����B
        //[get�n�b�V���l()�ɓn������]
        //�ڋq�F ����.�ڋq
        //�n�b�V���萔�P�F �d�q���V�X�e����Е��X�ʃv���t�@@�����XParams.�n�b�V���萔�P
        //�n�b�V���萔�Q�F �d�q���V�X�e����Е��X�ʃv���t�@@�����XParams.�n�b�V���萔�Q
        String l_strHashValue = this.getHashValue(
            l_mainAccount,
            l_batoInstBranchPrefParams.getHashField1(),
            l_batoInstBranchPrefParams.getHashField2());

        //3�j�d�q��URL�̍쐬
        //3-1�j�p�����[�^�̐ݒ�
        //�t�q�k�@@�@@ : �d�q���V�X�e����Е��X�ʃv���t�@@�����XParams.URL�i��ʐڑ��p�j
        String l_strURL = l_batoInstBranchPrefParams.getUrl();

        //��ЃR�[�h : COMPCODE=����.�ڋq.getInstitution().getInstitutionCode()�̖߂�l
        String l_strUrlInstitutionCode = "COMPCODE=" + l_strInstitutionCode;

        //���X�R�[�h : BRANCODE=����.�ڋq.getBranch().getBranchCode()�̖߂�l
        String l_strUrlBranchCode = "BRANCODE=" + l_strBranchCode;

        //�ڋq�R�[�h : USERID=����.�ڋq.getAccountCode()�̖߂�l
        String l_strUrlAccountCode = "USERID=" + l_mainAccount.getAccountCode();

        //�n�b�V���l : HASHSTRING=�d�q���V�X�e���ڑ��T�[�r�X.get�n�b�V���l()�̖߂�l
        String l_strUrlHashValue = "HASHSTRING=" + l_strHashValue;

        //3-2�j��L�̃p�����[�^���ȉ��̂悤�Ɍ�������B
        //�t�q�k?��ЃR�[�h�����X�R�[�h���ڋq�R�[�h���n�b�V���l
        String l_strBatoConnectionInfo =
            l_strURL + "?" + l_strUrlInstitutionCode + "&" +
            l_strUrlBranchCode + "&" + l_strUrlAccountCode + "&" + l_strUrlHashValue;

        //4�j3�j�ɂč쐬�����d�q��URL��ԋp�B
        log.exiting(STR_METHOD_NAME);
        return l_strBatoConnectionInfo;
    }

    /**
     * (validate���������ژ_�����{��)<BR>
     * ���������̖ژ_�����{���ς��𔻒肷��B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�d�q���jvalidate���������ژ_�����{���v �Q��<BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F(�d�q���V�X�e���ڑ��T�[�r�X / �i�d�q���jvalidate���������ژ_�����{��) <BR>
     * ��̈ʒu�F(���X���i�ʃv���t�@@�����X.��Q�������ۃt���O == �h�����s�h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_01984 <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F(�d�q���V�X�e���ڑ��T�[�r�X / �i�d�q���jvalidate���������ژ_�����{��) <BR>
     * ��̈ʒu�F(���X���i�ʃv���t�@@�����X.�㗝���͉ۃt���O == �h�㗝���͕s�h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_01985 <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F(�d�q���V�X�e���ڑ��T�[�r�X / �i�d�q���jvalidate���������ژ_�����{��) <BR>
     * ��̈ʒu�F(get�㗝���͎�()�̖߂�l != null and <BR>
     * �@@�@@�@@�@@�@@�擾�����㗝���͉ۃt���O == �h�㗝���͕s�h and <BR>
     * �@@�@@�@@�@@�@@����.�㗝���͕s���`�F�b�N�t���O==true�̏ꍇ�A��O���X���[����B)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_01985 <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F(�d�q���V�X�e���ڑ��T�[�r�X / �i�d�q���jvalidate���������ژ_�����{��) <BR>
     * ��̈ʒu�F(�J���}��؂�ŋ�؂�ꂽ�l���h��������h�A�h���𖳂��h�A<BR>
     * �@@�@@�@@�@@�@@�h�����Ǘ������h�ȊO�Ȃ�A��O���X���[����B)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_01959 <BR>
     * ========================================================== <BR>
     * <BR>
     * @@param l_multiDocCheckResultUnit - (���������ژ_�����{���`�F�b�N���X�g)<BR>
     * ���������ژ_�����{���`�F�b�N���X�g<BR>
     * @@param l_blnIsCheckFlag - (�㗝���͕s���`�F�b�N�t���O)<BR>
     * �㗝���͕s���ɋƖ��G���[�ɂ��邩���Ȃ����̃t���O<BR>
     * <BR>
     * �㗝���͕s���`�F�b�N�t���O<BR>
     * true�F�`�F�b�N����<BR>
     * false�F�`�F�b�N���Ȃ�<BR>
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
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        //get�⏕����(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //getMainAccount( )
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        //get�㗝���͎�( )
        Trader l_trader = this.getTrader();

        //get��Е��X�ʃv���t�@@�����X(String, String)
        //[����]
        // �،���ЃR�[�h�F �ڋq.getInstitution().getInstitutionCode()�̖߂�l
        // ���X�R�[�h�F �ڋq.getBranch().getBranchCode()�̖߂�l
        BatoInstBranchPrefParams l_batoInstBranchPrefParams =
            this.getBatoInstBranchPrefParams(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode());

        HashMap l_hmOperatorInputFlag = new HashMap();
        List l_lisTypeCodes = new ArrayList();
        //(*)����.���������ژ_�����{���`�F�b�N���X�g�̌������A���[�v�B
        int l_intMultiDocCheckResultUnitLength = l_multiDocCheckResultUnit.length;
        for (int i = 0; i < l_intMultiDocCheckResultUnitLength; i++)
        {
            // get���X���i�ʃv���t�@@�����X(String, String, String)
            //�،���ЃR�[�h�F �ڋq.getInstitution().getInstitutionCode()�̖߂�l
            //���X�R�[�h�F �ڋq.getBranch().getBranchCode()�̖߂�l
            //��ʃR�[�h�F ����.���������ژ_�����{���`�F�b�N���X�g.��ʃR�[�h
            BatoBranchProductPrefParams l_batoBranchProductPrefParams =
                this.getBatoBranchProductPrefParams(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    l_multiDocCheckResultUnit[i].typeCode);

            //��Е��X�ʃv���t�@@�����X.�V�X�e����Q�t���O == �h��Q���h �̏ꍇ
            if (WEB3GentradeBatoSystemFailureFlagDef.NOT_WORKING.equals(
                l_batoInstBranchPrefParams.getSystemFailureFlag()))
            {
                //�j���X���i�ʃv���t�@@�����X.��Q�������ۃt���O == �h�����s�h�̏ꍇ��O���X���[����B
                if (WEB3GentradeBatoOrderAtSystemFailureFlagDef.DISABLED.equals(
                    l_batoBranchProductPrefParams.getOrderAtSystemFailureFlag()))
                {
                    log.debug("[�d�q���V�X�e����Q��]��Q�������s�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01984,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "[�d�q���V�X�e����Q��]��Q�������s�B");
                }

                //get�㗝���͎�()�̖߂�l != null and
                //���X���i�ʃv���t�@@�����X.�㗝���͉ۃt���O == �h�㗝���͕s�h
                //��O���X���[����B
                if (l_trader != null
                    && WEB3GentradeBatoOperatorInputFlagDef.DISABLED.equals(
                        l_batoBranchProductPrefParams.getOperatorInputFlag()))
                {
                    log.debug("[�d�q���V�X�e����Q��]��Q���㗝���͕s�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01985,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "[�d�q���V�X�e����Q��]��Q���㗝���͕s�B");
                }

                //���������ژ_�����{���`�F�b�N����
                WEB3GentradeMultiCheckResults l_multiCheckResults =
                    new WEB3GentradeMultiCheckResults();

                //�`�F�b�N���ʁF null
                //URL�F null
                //�n�b�V���l�F null
                //��Q���t���O:true
                l_multiCheckResults.checkResult = null;
                l_multiCheckResults.url = null;
                l_multiCheckResults.hashValue = null;
                l_multiCheckResults.batoFailureFlag = true;

                log.exiting(STR_METHOD_NAME);
                return l_multiCheckResults;
            }

            //��ʃR�[�h�ɑ΂���A�㗝���͉ۃt���O�̃n�b�V����
            //�L�[ �F ����.���������ژ_�����{���`�F�b�N���X�g.��ʃR�[�h
            //�l �F �d�q���V�X�e�����X���i�ʃv���t�@@�����XParams.get�㗝���͉ۃt���O
            //�L�[���d������ꍇ�́A�n�b�V���ɒǉ����Ȃ�
            if (!l_lisTypeCodes.contains(l_multiDocCheckResultUnit[i].typeCode))
            {
                l_hmOperatorInputFlag.put(l_multiDocCheckResultUnit[i].typeCode,
                    l_batoBranchProductPrefParams.getOperatorInputFlag());

                l_lisTypeCodes.add(l_multiDocCheckResultUnit[i].typeCode);
            }
        }

        //validate�ڑ��\����( )
        this.validateConnectionTime();

        //get�n�b�V���l(�ڋq, String, String)
        String l_strHashValue = this.getHashValue(
            l_mainAccount,
            l_batoInstBranchPrefParams.getHashField1(),
            l_batoInstBranchPrefParams.getHashField2());

        //get�@@�\�ʃv���t�@@�����X(String)
        //�@@�\�敪�F �h���������ژ_�����{���`�F�b�N�h
        BatoFunctionPrefParams l_batoFunctionPrefParams =
            this.getBatoFunctionPrefParams(WEB3GentradeBatoFunctionDivDef.BATO_MULTI_PROSPECTUS_SERVICE);

        //��ʃR�[�h�Ɩ����R�[�h�̃J���}��؂蕶������
        //����.���������ژ_�����{���`�F�b�N���X�g�̌������A���[�v
        StringBuffer l_sbTypeCode = new StringBuffer();
        StringBuffer l_sbProductCode = new StringBuffer();
        for (int i = 0; i < l_intMultiDocCheckResultUnitLength; i++)
        {
            //���������ژ_�����{���`�F�b�N���X�g.��ʃR�[�h���J���}��؂�ɂ��āA��ʃR�[�h�ꗗ(�J���}��؂�)�ɒǉ�
            l_sbTypeCode.append(l_multiDocCheckResultUnit[i].typeCode);
            l_sbTypeCode.append(",");

            //���������ژ_�����{���`�F�b�N���X�g.�����R�[�h���J���}��؂�ɂ��āA�����R�[�h�ꗗ(�J���}��؂�)�ɒǉ�
            l_sbProductCode.append(l_multiDocCheckResultUnit[i].productCode);
            l_sbProductCode.append(",");
        }

        //�����R�[�h�ꗗ(�J���}��؂�)�Ǝ�ʃR�[�h�ꗗ(�J���}��؂�)�̍Ō�̕����ɂ̓J���}��ǉ����Ȃ��悤�ɐ��䂷��B
        l_sbTypeCode.deleteCharAt(l_sbTypeCode.length() - 1);
        l_sbProductCode.deleteCharAt(l_sbProductCode.length() - 1);

        // invoke�d�q���V�X�e��
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

        //invoke�d�q���V�X�e���̖߂�l�̕�������J���}��؂�Ń��[�v����B
        String[] l_strInvokeBatoSystems = l_strInvokeBatoSystem.split(",");

        for(int i = 0; i < l_intMultiDocCheckResultUnitLength; i++)
        {
            // �J���}��؂�ŋ�؂�ꂽ�l���h���𖳂��h�̏ꍇ
            if (WEB3GentradeBatoProspectusServiceResultDef.NO_HISTORY.equals(l_strInvokeBatoSystems[i]))
            {
                //�㗝���͉ۃt���O�̃n�b�V���������.���������ژ_�����{���`�F�b�N���X�g[index].��ʃR�[�h���L�[�Ƃ��āA
                //�㗝���͉ۃt���O���擾����B
                String l_strTypeCode = (String)l_hmOperatorInputFlag.get(l_multiDocCheckResultUnit[i].typeCode);

                //get�㗝���͎�()�̖߂�l != null and
                //�擾�����㗝���͉ۃt���O == �h�㗝���͕s�h and
                //����.�㗝���͕s���`�F�b�N�t���O==true�̏ꍇ�A��O���X���[����B
                if (l_trader != null
                    && WEB3GentradeBatoOperatorInputFlagDef.DISABLED.equals(l_strTypeCode)
                    && l_blnIsCheckFlag)
                {
                    log.debug("[�d�q���V�X�e���ғ���]�ژ_�����{�����όڋq�̑㗝���͕s�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01988,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "[�d�q���V�X�e���ғ���]�ژ_�����{�����όڋq�̑㗝���͕s�B");
                }

                //����.���������ژ_�����{���`�F�b�N���X�g[index].�`�F�b�N���ʂɈȉ��̒l���Z�b�g
                //�J���}��؂�ŋ�؂�ꂽ�l���h�����Ȃ��h�̏ꍇ�A�h1�F �{�����ρh
                l_multiDocCheckResultUnit[i].checkResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION;
            }
            else if (WEB3GentradeBatoProspectusServiceResultDef.HISTORY.equals(l_strInvokeBatoSystems[i])
                || WEB3GentradeBatoProspectusServiceResultDef.CHECK_OFF.equals(l_strInvokeBatoSystems[i]))
            {
                //�J���}��؂�ŋ�؂�ꂽ�l���h��������hor�h�����Ǘ��Ȃ��h�̏ꍇ�A�h0�F �{���ρh
                l_multiDocCheckResultUnit[i].checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
            }
            // �J���}��؂�ŋ�؂�ꂽ�l���h��������h�A�h���𖳂��h�A�h�����Ǘ������h�ȊO�Ȃ�A��O���X���[����B
            else
            {
                log.debug("�d�q���V�X�e���ďo�ŃG���[���������܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01959,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�d�q���V�X�e���ďo�ŃG���[���������܂����B");
            }
        }

        //���������ژ_�����{���`�F�b�N����
        WEB3GentradeMultiCheckResults l_checkResults = new WEB3GentradeMultiCheckResults();

        //�v���p�e�B�Z�b�g
        //�`�F�b�N���ʁF����.���������ژ_�����{���`�F�b�N���X�g
        l_checkResults.checkResult = l_multiDocCheckResultUnit;

        //URL�F ��Е��X�ʃv���t�@@�����X.URL�i��ʐڑ��p�j
        l_checkResults.url = l_batoInstBranchPrefParams.getUrl();

        //�n�b�V���l�Fget�n�b�V���l()�̖߂�l�i�n�b�V���l�@@�j
        l_checkResults.hashValue = l_strHashValue;

        //��Q���t���O:false
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
                "�d�q���V�X�e����Е��X�ʃv���t�@@�����X.URL�iSOAP�ڑ��p�j" + 
                "�̃Z�N�V���������A�قȂ��Ă��܂��B\n" +
                "�u[proxy-host;proxy-port;]soap-url�v�ŃZ�b�g���Ă��������B";
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
                "[SOAP�T�[�r�X�G���[]\n" + l_logMessage, 
                e);
d1277 1
a1277 1
        
@

