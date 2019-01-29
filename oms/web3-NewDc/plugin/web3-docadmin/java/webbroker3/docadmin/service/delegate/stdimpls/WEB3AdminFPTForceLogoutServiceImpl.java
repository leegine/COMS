head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���ʖ����� �������O�A�E�g�T�[�r�XImpl(WEB3AdminFPTForceLogoutServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 ��(FLJ) �V�K�쐬
*/
package webbroker3.docadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DaemonTriggerTypeDef;
import webbroker3.common.define.WEB3DocumentCheckDivDef;
import webbroker3.common.define.WEB3ServerUrlAccessorDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTDocDivManagement;
import webbroker3.docadmin.data.DocForceLogoutRunStatusRow;
import webbroker3.docadmin.define.WEB3DocForceLogoutRunStatusStatusDef;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutMainRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutProductCondition;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutReferenceResponse;
import webbroker3.docadmin.message.WEB3FPTForceLogoutInfoUnit;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutConfirmResponse;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.DocCategoryManagementRow;
import webbroker3.system.tune.affinity.message.WEB3AffinityDescendRequest;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.comm.client.CommunicationException;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.ServerConnector;
import com.fitechlabs.xtrade.kernel.comm.client.ServerException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.db.ServerConfigRow;
import com.fitechlabs.xtrade.kernel.message.Request;

/**
 * �Ǘ��� ���ʖ����� �������O�A�E�g�T�[�r�XImpl
 * <BR>
 * @@author Kyo
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutServiceImpl implements WEB3AdminFPTForceLogoutService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutServiceImpl.class);
    
    /**
     * @@roseuid 47DF467601FB
     */
    public WEB3AdminFPTForceLogoutServiceImpl() 
    {
     
    }
    
    /**
     * ���ʖ������������O�A�E�g���͉�ʕ\���������s���B 
     * 
     * �V�[�P���X�} 
     * �uget���͉�ʁv�Q��
     * @@param l_request - �Ǘ��� ���ʖ����� 
     * �������O�A�E�g���̓��N�G�X�g
     * @@return WEB3AdminFPTForceLogoutInputResponse
     * TForceLogoutInputResponse
     * @@roseuid 47CFA5C1019E
     */
    protected WEB3AdminFPTForceLogoutInputResponse getInputPage(WEB3AdminFPTForceLogoutInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getInputPage(WEB3AdminFPTForceLogoutInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTForceLogoutInputResponse l_response = null;
       
        // ���O�C�������Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // �Ǘ��Ҍ����`�F�b�N�������s��
        validateAdminPermission(l_web3Administrator, true);

        // ���X�R�[�h���擾����
        String l_strBranchCode = l_web3Administrator.getBranchCode();

        // �،���ЃR�[�h���擾����
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        // ���ʋ敪�Ǘ��𐶐�����
        WEB3AdminFPTDocDivManagement l_management = 
            new WEB3AdminFPTDocDivManagement(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    null,
                    null);

        // ���ʋ敪�Ǘ������擾����
        WEB3FPTDocumentDivAdminInfoUnit[] l_infoUnit = l_management.getDocDivManagementLists();

        TreeMap l_map = new TreeMap();
        for (int i=0;i<l_infoUnit.length;i++)
        {
            // ���ʃ`�F�b�N�敪�������@@�̏ꍇ
            if (WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW.equals(l_infoUnit[i].docuCheckDiv))
            {
                l_map.put(l_infoUnit[i].documentCategory, l_infoUnit[i]);
            }
        }

        // TreeMap��0�T�C�Y�̏ꍇ
        if (l_map.size() == 0)
        {
            log.debug("���ʋ敪�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02998,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʋ敪�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���B");
        }
        
        WEB3FPTDocumentDivAdminInfoUnit[] l_infoUnitFin = (WEB3FPTDocumentDivAdminInfoUnit[])l_map.values().toArray(new WEB3FPTDocumentDivAdminInfoUnit[0]);
        
        l_response = (WEB3AdminFPTForceLogoutInputResponse) l_request.createResponse();
        l_response.documentDivList = l_infoUnitFin;

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * ���ʖ������������O�A�E�g�m�F��ʕ\���������s���B 
     * 
     * �V�[�P���X�} 
     * �uvalidate�������O�A�E�g�v�Q��
     * @@param l_request - �Ǘ��� ���ʖ����� 
     * �������O�A�E�g�m�F���N�G�X�g
     * @@return WEB3AdminFPTForceLogoutconfirmResponse
     * TForceLogoutconfirmResponse
     * @@roseuid 47D0EEB2014D
     */
    protected WEB3AdminFPTForceLogoutConfirmResponse validateForceLogout(WEB3AdminFPTForceLogoutConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateForceLogout(WEB3AdminFPTForceLogoutConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTForceLogoutConfirmResponse l_response = null;
        
        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();
        
        // ���O�C�������Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // �Ǘ��Ҍ����`�F�b�N�������s��
        validateAdminPermission(l_web3Administrator, true);
        
        // �������O�A�E�g�������\���`�F�b�N���s��
        validateForceLogoutPossible(l_request.documentDivList);
        
        l_response = (WEB3AdminFPTForceLogoutConfirmResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * ���ʖ������������O�A�E�g������ʕ\���������s���B 
     * 
     * �V�[�P���X�} 
     * �usubmit�������O�A�E�g�v�Q��
     * @@param l_request - �Ǘ��� ���ʖ����� 
     * �������O�A�E�g�������N�G�X�g
     * @@return WEB3AdminFPTForceLogoutCompleteResponse
     * TForceLogoutCompleteResponse
     * @@roseuid 47D6223603A8
     */
    protected WEB3AdminFPTForceLogoutCompleteResponse submitForceLogout(WEB3AdminFPTForceLogoutCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitForceLogout(WEB3AdminFPTForceLogoutCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();
        
        // ���O�C�������Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_web3Admin = WEB3Administrator.getInstanceFromLoginInfo();

        // �Ǘ��Ҍ����`�F�b�N�������s��
        validateAdminPermission(l_web3Admin, true);
        
        // �،���ЃR�[�h���擾����
        String l_strInstitutionCode = l_web3Admin.getInstitutionCode();
        
        // �Ïؔԍ��̃`�F�b�N���s��
        l_web3Admin.validateTradingPassword(l_request.password);
        
        // �������O�A�E�g�������\���`�F�b�N���s��
        WEB3AdminFPTForceLogoutProductCondition l_forceLogoutProductCondition = this.validateForceLogoutPossible(l_request.documentDivList);
        
        // ���s���ʂ��폜����
        this.deleteExecuteResult(l_strInstitutionCode);
        
        // �f�[�����g���K�[�ꗗ���擾����
        List l_lisDaemonTriggerUnits = this.getDaemonTrigerList();
        
        // ServerAccessor�I�u�W�F�N�g���擾����
        ServerAccessor l_serverAccessor = this.getServerAccessor();
        
        // get�f�[�����g���K�[�ꗗ()�̖߂�l�̗v�f�����ALoop����
        int l_intSize = l_lisDaemonTriggerUnits.size();
        try
        {
            for (int i = 0; i < l_intSize; i++)
            {
                DaemonTriggerRow l_row = (DaemonTriggerRow)l_lisDaemonTriggerUnits.get(i);
                
                //updateAP�ďo��(long)
                this.updateAPCalling(l_row.getThreadNo());

                //�Ǘ��ҁE�������ω��������F�^�񏳔F���C�����N�G�X�g( )
                WEB3AdminFPTForceLogoutMainRequest l_mainRequest =
                    new WEB3AdminFPTForceLogoutMainRequest();

                //�،���ЃR�[�h   ���@@get�،���ЃR�[�h()�̖߂�l
                l_mainRequest.institutionCode = l_strInstitutionCode;

                //�X���b�hNo     ���@@�����Ώۂ̗v�f.�X���b�h�ԍ�
                l_mainRequest.threadNo = new Long(l_row.getThreadNo());

                //From����ID   ���@@�����Ώۂ̗v�f.�ڋq�R�[�h�i���j
                l_mainRequest.accountIdFrom = new Long(l_row.getRangeFrom());

                //To����ID     ���@@�����Ώۂ̗v�f.�ڋq�R�[�h�i���j
                l_mainRequest.accountIdTo = new Long(l_row.getRangeTo());

                //�Ǘ���ID     ���@@�Ǘ���ID
                l_mainRequest.adminId = new Long(l_web3Admin.getAdministratorId());

                //�������O�A�E�g�Ώۖ�������  ���@@validate�������O�A�E�g�\
                l_mainRequest.forceLogoutProductCondition = l_forceLogoutProductCondition;

                WEB3AffinityDescendRequest l_affinityDescendRequest = new WEB3AffinityDescendRequest();
                l_affinityDescendRequest.account_id_range = l_row.getRangeFrom() + "," + l_row.getRangeTo();
                l_affinityDescendRequest.request = new Request[1]; 
                l_affinityDescendRequest.request[0] = l_mainRequest;
                
                // �i�񓯊��j�������O�A�E�g�������s��
                l_serverAccessor.doRequest(l_affinityDescendRequest);
            }
        }
        catch (CommunicationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // createResponse( )
        WEB3AdminFPTForceLogoutCompleteResponse l_response =
            (WEB3AdminFPTForceLogoutCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * ���ʖ������������O�A�E�g�\�`�F�b�N�������s���B 
     * 
     * �V�[�P���X�} 
     * �uvalidate�������O�A�E�g�\�v�Q��
     * @@param l_docDivMngArr - ���ʋ敪�Ǘ����
     * @@return WEB3AdminFPTForceLogoutProductCondition
     * TForceLogoutProductCondition
     * @@roseuid 47D60DEA0356
     */
    protected WEB3AdminFPTForceLogoutProductCondition validateForceLogoutPossible(WEB3FPTDocumentDivAdminInfoUnit[] l_docDivMngArr) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateForceLogoutPossible()";
        log.entering(STR_METHOD_NAME);
        
        // DB�d�q������Map
        Map l_batoProductMngMap = new HashMap();
        // ���ʋ敪TreeSet
        TreeSet l_docDivSet = new TreeSet();
        
        
        // ���O�C�������Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_web3Admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // �f�[�����g���K�[�ꗗ���擾����
        List l_lisDaemonTriggerUnits = this.getDaemonTrigerList();
        
        // �،���ЃR�[�h���擾����
        String l_strInstitutionCode = l_web3Admin.getInstitutionCode();
        
        // �������O�A�E�g���s���ʈꗗ���擾����
        List l_runResultList = this.getForceLogoutExecuteResultList(l_strInstitutionCode);
        
        // ��d�N���`�F�b�N
        if (l_runResultList != null)
        {
            if (l_runResultList.size() == l_lisDaemonTriggerUnits.size())
            {
                DocForceLogoutRunStatusRow l_runStatusRow = null;
                DaemonTriggerRow l_daemonTriggerRow = null;
                for (int i = 0; i < l_runResultList.size(); i++)
                {
                    l_runStatusRow = (DocForceLogoutRunStatusRow)l_runResultList.get(i);
                    l_daemonTriggerRow = (DaemonTriggerRow)l_lisDaemonTriggerUnits.get(i);
                    if (WEB3DocForceLogoutRunStatusStatusDef.PROCESSING.equals(l_runStatusRow.getStatus())
                        || !WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus()))
                    {
                        log.debug("�w��AP�N�����i��d�N���G���[�j�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�w��AP�N�����i��d�N���G���[�j�B");
                    }
                }
            }
            else
            {
                log.debug("�w��AP�N�����i��d�N���G���[�j�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w��AP�N�����i��d�N���G���[�j�B");
            }
        }
        
        // ���X�R�[�h���擾����
        String l_strBranchCode = l_web3Admin.getBranchCode();
        
        try
        {
            //�@@���ʎ�ފǗ��e�[�u������������B
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("institution_code = ? ");
            l_sbWhere.append("and branch_code = ? ");
            l_sbWhere.append("and document_category in (");
            
            List l_objList = new ArrayList();
            l_objList.add(l_strInstitutionCode);
            l_objList.add(l_strBranchCode);
    
            for (int i = 0; i < l_docDivMngArr.length; i++) 
            {
                if(i > 0)
                {
                    l_sbWhere.append(",");
                }
                l_sbWhere.append("?");
                l_objList.add(l_docDivMngArr[i].documentCategory);
            }
            
            l_sbWhere.append(")");
            Object[] l_vars = l_objList.toArray();
       
            //���������s
            List l_docCateMngList = Processors.getDefaultProcessor().doFindAllQuery(DocCategoryManagementRow.TYPE,l_sbWhere.toString(),l_vars);
            
            //�@@���ʎ�ފǗ��e�[�u���̌������ʃT�C�Y�@@���@@���ʋ敪�Ǘ����̃T�C�Y�@@�̏ꍇ
            if (l_docCateMngList.size() < l_docDivMngArr.length)
            {
                log.debug("���ʎ�ފǗ����R�[�h�`�F�b�N�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03000,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʎ�ފǗ����R�[�h�`�F�b�N�G���[�B");
            }
            
            // ���ʋ敪TreeSet�ɏ��ʋ敪��ǉ�����
            for(int i = 0; i < l_docDivMngArr.length; i++)
            {
                l_docDivSet.add(l_docDivMngArr[i].documentDiv);
            }
            
            //�@@�d�q�������R�[�h�Ǘ��e�[�u������������
            l_objList.clear();
            l_sbWhere = new StringBuffer();
            l_sbWhere.append("institution_code = ? ");
            l_sbWhere.append("and branch_code = ? ");
            l_sbWhere.append("and document_div in (");
            
            l_objList.add(l_strInstitutionCode);
            l_objList.add(l_strBranchCode);
    
            Iterator it = l_docDivSet.iterator();
            while(it.hasNext())
            {
                l_objList.add(it.next());
                
                if(it.hasNext())
                {
                    l_sbWhere.append("?,");
                }
                else
                {
                    l_sbWhere.append("?");
                }
            }
            l_sbWhere.append(") ");
            
            l_sbWhere.append("and substr(bato_product_code,1,3) in (");
            for (int i = 0; i < l_docDivMngArr.length; i++) 
            {
                if(i > 0)
                {
                    l_sbWhere.append(",");
                }
                l_sbWhere.append("?");
                l_objList.add(l_docDivMngArr[i].documentCategory);
            }
            l_sbWhere.append(") ");
            
            l_sbWhere.append("and valid_flag = ?");
            l_objList.add(WEB3ValidFlagDef.VALID);
            
            l_vars = l_objList.toArray();
    
            List l_batoProductMngList = null;
    
            // ���������s
            l_batoProductMngList = 
                Processors.getDefaultProcessor().doFindAllQuery(
                        BatoProductManagementRow.TYPE,
                        l_sbWhere.toString(),
                        l_vars);
            
            // �d�q�������R�[�h�Ǘ��e�[�u���̌������ʂ�0�T�C�Y�̏ꍇ
            if (l_batoProductMngList == null || l_batoProductMngList.size() == 0)
            {
                log.debug("�d�q�������R�[�h�Ǘ����R�[�h�`�F�b�N�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02999,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�d�q�������R�[�h�Ǘ����R�[�h�`�F�b�N�G���[�B");
            }

            // DB�d�q������Map�ɃL�[���d�q�������R�[�h�̐擪�R���A�l��Row�ŃZ�b�g����
            for (int i = 0; i < l_batoProductMngList.size(); i++)
            {
                BatoProductManagementRow l_batoProductMngRow = (BatoProductManagementRow)l_batoProductMngList.get(i);
                l_batoProductMngMap.put(l_batoProductMngRow.getBatoProductCode().substring(0, 3), l_batoProductMngRow);
            }
            
            for (int i = 0; i < l_docDivMngArr.length; i++) 
            {
                // ���b�Z�[�W DB�d�q������Map.containsKey(���ʋ敪�Ǘ����.���ʎ�ރR�[�h)����false�̏ꍇ
                if (l_batoProductMngMap.containsKey(l_docDivMngArr[i].documentCategory) == false)
                {
                    log.debug("�d�q�������R�[�h�Ǘ����R�[�h�`�F�b�N�G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02999,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�d�q�������R�[�h�Ǘ����R�[�h�`�F�b�N�G���[�B");
                }
            }
            
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
               
        WEB3AdminFPTForceLogoutProductCondition l_forceLogoutProductCondition = new WEB3AdminFPTForceLogoutProductCondition();
        l_forceLogoutProductCondition.documentCatCodeArr = new String[l_docDivMngArr.length];       
        for (int i = 0; i < l_docDivMngArr.length; i++) 
        {
            l_forceLogoutProductCondition.documentCatCodeArr[i] = l_docDivMngArr[i].documentCategory;
        }
        l_forceLogoutProductCondition.documentDivArr = new String[l_docDivSet.size()];   
        l_docDivSet.toArray(l_forceLogoutProductCondition.documentDivArr);
        int l_index=0;
        l_forceLogoutProductCondition.batoProductCodeArr = new String[l_batoProductMngMap.values().size()]; 
        for (Iterator iter = l_batoProductMngMap.values().iterator(); iter.hasNext();)
        {
            BatoProductManagementRow l_row = (BatoProductManagementRow) iter.next();
            l_forceLogoutProductCondition.batoProductCodeArr[l_index] = l_row.getBatoProductCode();
            l_index++;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_forceLogoutProductCondition;
    }
    
    /**
     * �Ǘ��� ���ʖ����� �������O�A�E�g�̊Ǘ��Ҍ����`�F�b�N�������s���B 
     * 
     * 
     * �P�j����.is�S���X����==false�̏ꍇ�A
     * �@@�S���X���̊Ǘ��҂łȂ��ꍇ�G���[
     * �@@�iBUSINESS_ERROR_01380�j
     * 
     * �Q�j����.validate����
     * �@@[����]
     *   �@@�\�J�e�S���R�[�h�F"G0105"(���ʖ������������O�A�E�g)
     *   is�X�V�F����.�X�V�t���O
     * @@param l_admin - �Ǘ���
     * @@param l_updateFlg - �X�V�t���O
     * @@roseuid 47D60FDE039E
     */
    protected void validateAdminPermission(WEB3Administrator l_admin, boolean l_updateFlg) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = "validateAdminPermission()";
        log.entering(STR_METHOD_NAME);
        
        if (l_admin.isAllBranchsPermission() == false)
        {
            log.debug("�S���X���̊Ǘ��҂łȂ��ꍇ�́A����s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�S���X���̊Ǘ��҂łȂ��ꍇ�́A����s�B");
        }
        
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FPT_FORCE_LOGOUT,
            l_updateFlg);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get�f�[�����g���K�[�ꗗ
     * 
     * ���ʖ����� �������O�A�E�g
     * �f�[�����g���K�[�e�[�u���̃��R�[�h��ԋp����B 
     * 
     * �P�j�@@DB���� 
     * �@@�ȉ��̏����Ńf�[�����g���K�[�e�[�u������������B 
     * �@@[����] 
     * �@@�@@�����^�C�v = "���ʖ����� �������O�A�E�g" 
     * 
     * �@@[�\�[�g����] 
     * �@@�@@�X���b�h�ԍ��@@���� 
     * 
     * �@@�Y���f�[�^�Ȃ��̏ꍇ�A�u�Y���f�[�^�Ȃ��v�� 
     * �@@�V�X�e���G���[���X���[����B 
     * �@@�iSYSTEM_ERROR_80005�j
     * 
     * �Q�j�@@�������ʂ�ԋp����B
     * @@return java.util.List
     * @@roseuid 47D6115803CC
     */
    protected java.util.List getDaemonTrigerList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDaemonTrigerList()";
        log.entering(STR_METHOD_NAME);

        String l_strTriggerType = WEB3DaemonTriggerTypeDef.FPT_FORCE_LOGOUT;

        Object[] l_objValues = {l_strTriggerType};

        //�@@[�\�[�g����]
        String l_strCondition = "thread_no asc";

        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                DaemonTriggerRow.TYPE,
                " trigger_type = ?",
                l_strCondition,
                null,
                l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�@@�Y���f�[�^�Ȃ��̏ꍇ�A�u�Y���f�[�^�Ȃ��v��
        //�@@�V�X�e���G���[���X���[����B
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_lisRecords;
    }
    
    /**
     * get�������O�A�E�g���s���ʈꗗ
     * 
     * �����̏����ɊY�����鏑�ʖ����� �������O�A�E�g���s���ʃe�[�u���� 
     * ���R�[�h��ԋp����B 
     * 
     * �P�j�@@DB���� 
     * �@@�ȉ��̏����ŃI�����C�����s���ʃe�[�u������������B 
     * �@@[����] 
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h 
     * 
     * �@@�Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B 
     * 
     * �Q�j�@@�������ʂ�ԋp����B
     * @@param �،���ЃR�[�h - �،���ЃR�[�h
     * @@return java.util.List
     * @@roseuid 47D6131E03C1
     */
    protected java.util.List getForceLogoutExecuteResultList(String l_institutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForceLogoutExecuteResultList(String)";
        log.entering(STR_METHOD_NAME);

        String l_strWhere = " institution_code = ?";

        Object[] l_objValues = {l_institutionCode};

        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                DocForceLogoutRunStatusRow.TYPE,
                l_strWhere,
                l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�@@�Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_lisRecords;
    }
    
    /**
     * delete���s����
     * 
     * �����ɊY�����鏑�ʖ����� �������O�A�E�g���s���ʃe�[�u���� 
     * ���R�[�h�𕨗��폜����B 
     * 
     * �P�j�ȉ��̏����ɊY�����郌�R�[�h��delete����B 
     * 
     * �@@[����] 
     * �@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
     * 
     * �@@���폜�Ώۂ̃��R�[�h���Ȃ��Ă���O�Ƃ��Ȃ����ƁB 
     * �@@���{�����͐V�K�g�����U�N�V�����ŏ������s���A 
     * �@@�@@�����������ɍX�V�����f�����悤�ɂ��邱�ƁB
     * @@param �،���ЃR�[�h - �،���ЃR�[�h
     * @@roseuid 47D627B70236
     */
    protected void deleteExecuteResult(String l_institutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteExecuteResult(String)";
        log.entering(STR_METHOD_NAME);

        final String l_strWhere = " institution_code = ? ";
        final String l_strCondition = "for update";
        final Object[] l_objValues = {l_institutionCode};

        try
        {
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                new TransactionCallback()
                    {
                        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
                        {
                            List l_lisRows = l_queryProcesser.doFindAllQuery(
                                DocForceLogoutRunStatusRow.TYPE,
                                l_strWhere,
                                l_strCondition,
                                l_objValues);
                            int l_intSize =  l_lisRows.size();
                            for (int i = 0; i < l_intSize; i++)
                            {
                                DocForceLogoutRunStatusRow l_row = (DocForceLogoutRunStatusRow)l_lisRows.get(i);
                                WEB3DataAccessUtility.deleteRow(l_row);
                            }
                            log.exiting(STR_METHOD_NAME);
                            return null;
                        }
                    }
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * ���ו��U�������s���ׂ�ServerAccessor�I�u�W�F�N�g�� 
     * �ԋp����B 
     * 
     * �P�j�@@�N���X�^�����O��T�[�o�[URL���擾����B 
     * �@@�@@QueryProcessor.doFindAllQuery()���\�b�h�� 
     * �@@�@@�R�[������B 
     * 
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] 
     * �@@�@@�@@arg0�F�@@ServerConfigRow.TYPE 
     * �@@�@@�@@arg1�F�@@"config_categ = ?" 
     * �@@�@@�@@arg2�F�@@"cluster.urls"�݂̂�v�f�Ƃ���z�� 
     * 
     * �@@�@@��"cluster.urls"�͒萔��`�N���X�Q�Ƃ��邱�ƁB 
     * 
     * �@@�@@�������ʂ̊e�v�f.config_value���擾���A������z��� 
     * �@@�@@�쐬����B 
     * �@@�@@���������ʂ��擾�ł��Ȃ������ꍇ�A�u�Y���f�[�^�Ȃ��v�� 
     * �@@�@@�@@�V�X�e���G���[���X���[����B 
     * 
     * �Q�j�@@ServerAccessor�̍쐬 
     * �@@ServerConnector.createAccessor()���\�b�h���R�[�����A 
     * �@@�߂�l��this.accessor�ɃZ�b�g������A�߂�l��ԋp����B 
     * 
     * �@@[craeteAccessor()�ɃZ�b�g����p�����[�^] 
     * �@@�@@arg0�iACCESSOR_KEY�j�F�@@"web3-static-cluster" 
     * �@@�@@arg1�iURL�j�F�@@�P�j�ɂč쐬����������z�� 
     * 
     * �@@�@@��"web3-static-cluster"�͒萔��`�N���X�Q�Ƃ��邱�ƁB
     * @@return ServerAccessor
     * @@roseuid 47D6296000F0
     */
    protected ServerAccessor getServerAccessor() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getServerAccessor()";
        log.entering(STR_METHOD_NAME);

        String l_strQueryWhere = "config_categ = ?";
        Object[] l_bindValues = {WEB3ServerUrlAccessorDef.CLUSTER_URLS};
        List l_lisRecords = null;

        try
        {
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcesser.doFindAllQuery(
                ServerConfigRow.TYPE,
                l_strQueryWhere,
                l_bindValues);

            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�@@ServerAccessor�̍쐬
        int l_intLength = l_lisRecords.size();
        ArrayList l_lisConfigValues = new ArrayList();
        for (int i = 0; i < l_intLength; i++)
        {
            ServerConfigRow l_serverConfigRow = (ServerConfigRow)l_lisRecords.get(i);
            l_lisConfigValues.add(l_serverConfigRow.getConfigValue());
        }

        String[] l_strServerConfigList = new String[l_lisConfigValues.size()];
        l_lisConfigValues.toArray(l_strServerConfigList);

        ServerAccessor l_serverAccessor = null;
        try
        {
            l_serverAccessor = ServerConnector.createAccessor(
                WEB3ServerUrlAccessorDef.WEB3_STATIC_CLUSTER,
                l_strServerConfigList);
        }
        catch (CommunicationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_serverAccessor;
    }
    
    /**
     * �����̃X���b�hNo�ɊY������f�[�����g���K�[�� 
     * ���R�[�h���A"AP�ďo��"��update����B 
     * 
     * �P�j�@@�ȉ��̏����ɊY������f�[�����g���K�[�e�[�u���� 
     * �@@���R�[�h��update����B 
     * 
     * �@@[����] 
     * �@@�@@�X���b�h�ԍ� = �p�����[�^.�X���b�hNo 
     * 
     * �@@[�X�V�l] 
     * �@@�@@������� = "�g���K�[�iAP�ďo���j" 
     * 
     * �@@���{�����͐V�K�g�����U�N�V�����ŏ������s���A 
     * �@@�@@�����������ɍX�V�����f�����悤�ɂ��邱�ƁB
     * @@param �X���b�hNo - �X���b�hNo
     * @@roseuid 47D62D940191
     */
    protected void updateAPCalling(long l_threadNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateAPCalling(long)";
        log.entering(STR_METHOD_NAME);

        final int UPDATE_SUCCESS = 0; // �X�V����������I�������l
        final int UPDATE_FAIL = -1; // �X�V�ΏۃX���b�h���̍X�V�Ɏ��s�����ꍇ�̒l
        final int NO_ROWS = -2; // �X�V�ΏۃX���b�h�����擾�ł��Ȃ������ꍇ�̒l

        try
        {
            final HashMap l_changes = new HashMap();
            l_changes.put("trigger_status", WEB3DaemonTriggerStatusDef.THREAD_API_CALL);

            final String l_strWhere = "thread_no = ?";
            final String l_strCondition = "for update";
            final Object l_bindVars[] = {new Long(l_threadNo)};
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            Integer l_intResult = (Integer)
                l_queryProcesser.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    new TransactionCallback()
                    {
                        public Object process() throws DataNetworkException,
                            DataQueryException,
                            DataCallbackException
                        {
                            Integer l_intResult = null;
                            List l_lisRows = l_queryProcesser.doFindAllQuery(
                                DaemonTriggerRow.TYPE,
                                l_strWhere, l_strCondition, l_bindVars);
                            if (l_lisRows != null && l_lisRows.size() > 0)
                            {
                                DaemonTriggerRow l_row =
                                    (DaemonTriggerRow)l_lisRows.get(0);
                                WEB3DataAccessUtility.updateRow(l_row, l_changes);
                                l_intResult = new Integer(UPDATE_SUCCESS);
                            }
                            else
                            {
                                l_intResult = new Integer(NO_ROWS);
                            }
                            return l_intResult;
                        }
                    }
                );

            if (l_intResult.intValue() == UPDATE_FAIL)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            }
            else if (l_intResult.intValue() == NO_ROWS)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
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
     * ���ʖ����� �������O�A�E�g���ʏƉ��ʕ\���������s���B 
     * 
     * �V�[�P���X�} 
     * �u(�Ǘ��� ���ʖ����� �������O�A�E�g�T�[�r�X)get���ʏƉ�v�Q�ƁB
     * @@param l_request - �Ǘ��� ���ʖ����� 
     * �������O�A�E�g���ʏƉ�N�G�X�g
     * @@return WEB3AdminFPTForceLogoutReferenceResponse
     * TForceLogoutReferenceResponse
     * @@roseuid 47D7B8E101BE
     */
    protected WEB3AdminFPTForceLogoutReferenceResponse getResultRefrence(WEB3AdminFPTForceLogoutReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getResultRefrence(WEB3AdminFPTForceLogoutReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTForceLogoutReferenceResponse l_response = null;
        
               
        // ���O�C�������Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_web3Admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // �Ǘ��Ҍ����`�F�b�N�������s��
        validateAdminPermission(l_web3Admin, false);
        
        // ���X�R�[�h���擾����
        String l_strBranchCode = l_web3Admin.getBranchCode();

        // �،���ЃR�[�h���擾����
        String l_strInstitutionCode = l_web3Admin.getInstitutionCode();
        
        // �f�[�����g���K�[�ꗗ���擾����
        List l_lisDaemonTriggerUnits = this.getDaemonTrigerList();
        
        // �������O�A�E�g���s���ʈꗗ���擾����
        List l_runResultList = this.getForceLogoutExecuteResultList(l_strInstitutionCode);
               
        String l_executeResult = null;
        Date l_executeStartTime = null;
        Date l_executeStopTime = null;
        long l_executeResultCount = 0;
        String l_updaterCode = null;
        TreeSet l_documentDivSet = new TreeSet();
        Map l_documentDivMap = new HashMap();
        String[] l_docDivString = null;
        
        if (l_runResultList == null)
        {
            // �������O�A�E�g���s���ʂ��擾�ł��Ȃ������ꍇ
            l_response = (WEB3AdminFPTForceLogoutReferenceResponse)l_request.createResponse();
            return l_response;
        }
        else
        {
            //�����X�e�[�^�X   ���@@�ȉ��̕���ɂ��Z�b�g����B
            if (l_lisDaemonTriggerUnits.size() != l_runResultList.size())
            {
                l_executeResult = WEB3DocForceLogoutRunStatusStatusDef.PROCESSING;
            }
            else 
            {
                int l_intSize = l_lisDaemonTriggerUnits.size();
                DaemonTriggerRow l_daemonTriggerRow = null;
                DocForceLogoutRunStatusRow l_onlineRunStatusRow = null;
                int l_intFlag = 0;
                for(int i = 0; i < l_intSize; i++)
                {
                    l_daemonTriggerRow = (DaemonTriggerRow)l_lisDaemonTriggerUnits.get(i);
                    l_onlineRunStatusRow = (DocForceLogoutRunStatusRow)l_runResultList.get(i);
                    //�@@�E�擾�����f�[�����g���K�[���R�[�h.������Ԃ�"������"��1���ł����݂���ꍇ
                    //�@@�E�擾�����I�����C�����s���ʃ��R�[�h.���s�X�e�[�^�X�敪��"������"��1���ł����݂���ꍇ
                    if (WEB3DaemonTriggerStatusDef.THREAD_PROCESSING.equals(l_daemonTriggerRow.getTriggerStatus()) 
                        || WEB3DocForceLogoutRunStatusStatusDef.PROCESSING.equals(l_onlineRunStatusRow.getStatus()))
                    {
                        l_intFlag = 1;
                        break;
                    }
                    else if (!WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus())
                        || !WEB3DocForceLogoutRunStatusStatusDef.PROCESSED.equals(l_onlineRunStatusRow.getStatus()))
                    {
                        l_intFlag = 2;                 
                    }
                }
                //�@@"������"���Z�b�g�������
                if (l_intFlag == 1)
                {
                    l_executeResult = WEB3DocForceLogoutRunStatusStatusDef.PROCESSING;
                }
                //�@@�A"������"���Z�b�g�������
                //�@@�E�擾�����S�Ẵf�[�����g���K�[���R�[�h.������� == "���ғ�"�@@����
                //�@@�擾�����S�ẴI�����C�����s���ʃ��R�[�h.���s�X�e�[�^�X�敪 == "������"
                else if (l_intFlag == 0)
                {
                    l_executeResult = WEB3DocForceLogoutRunStatusStatusDef.PROCESSED;
                }
                //"�G���["���Z�b�g�������
                //�ȊO�̏ꍇ
                else if (l_intFlag == 2)
                {
                    l_executeResult = WEB3DocForceLogoutRunStatusStatusDef.PROCESS_ERROR;
                }
            }
            
            for (int i=0;i<l_runResultList.size();i++)
            {
                DocForceLogoutRunStatusRow l_runStatusRow = (DocForceLogoutRunStatusRow)l_runResultList.get(i);
                
                if (l_executeStartTime == null ||
                    WEB3DateUtility.compare(l_executeStartTime,l_runStatusRow.getStartTimestamp()) > 0)
                {
                    // �J�n�������Ώۃ��R�[�h.get�J�n����
                    l_executeStartTime = l_runStatusRow.getStartTimestamp();
                }
                
                if (l_executeStopTime == null ||
                    WEB3DateUtility.compare(l_executeStopTime,l_runStatusRow.getEndTimestamp()) < 0)
                {
                    // �I���������Ώۃ��R�[�h.get�I������
                    l_executeStopTime = l_runStatusRow.getEndTimestamp();
                }
                
                if (l_docDivString == null)
                {
                    l_docDivString = l_runStatusRow.getDocumentCategoryList().split(",");
                    for (int j=0;j<l_docDivString.length;j++)
                    {
                        // ���ʎ�ރR�[�h�ꗗTreeSet�ɏ��ʎ�ރR�[�h��add����
                        l_documentDivSet.add(l_docDivString[j]);
                    }
                }
                
                if (l_updaterCode == null)
                {
                    // �X�V�҃R�[�h���Ώۃ��R�[�h.get�X�V�҃R�[�h
                    l_updaterCode = l_runStatusRow.getLastUpdater();
                }
                
                // �������� += �Ώۃ��R�[�h.get��������
                l_executeResultCount += l_runStatusRow.getProcessCount();
                
            }
        }
        
        // ���ʎ�ރR�[�h�ꗗTreeSet�̃T�C�Y��0 �̏ꍇ
        if (l_documentDivSet.size() > 0)
        {
            // ���ʋ敪�Ǘ��𐶐�����
            WEB3AdminFPTDocDivManagement l_management = 
                new WEB3AdminFPTDocDivManagement(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        null,
                        null);
            
            // ���ʋ敪�Ǘ������擾����
            WEB3FPTDocumentDivAdminInfoUnit[] l_infoUnit = l_management.getDocDivManagementLists();
            
            for (int k=0;k<l_infoUnit.length;k++)
            {
                // ���ʃ`�F�b�N�敪�������@@�̏ꍇ
                if (WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW.equals(l_infoUnit[k].docuCheckDiv))
                {
                    l_documentDivMap.put(l_infoUnit[k].documentCategory, l_infoUnit[k]);
                }
            }
            
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivUnits = new WEB3FPTDocumentDivAdminInfoUnit[l_documentDivSet.size()];
            Iterator it = l_documentDivSet.iterator();
            int l_documentDivUnitNum = 0;
            while(it.hasNext())
            {
                // ���ʋ敪�Ǘ�Map.get(���ʎ�ރR�[�h)�ŏ��ʋ敪�Ǘ������擾����
                WEB3FPTDocumentDivAdminInfoUnit l_documentDivUnit = (WEB3FPTDocumentDivAdminInfoUnit)l_documentDivMap.get(it.next());
                
                if (l_documentDivUnit == null)
                {
                    log.debug("���ʋ敪�Ǘ����R�[�h�`�F�b�N�G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02998,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���ʋ敪�Ǘ����R�[�h�`�F�b�N�G���[�B");
                }
                
                l_documentDivUnits[l_documentDivUnitNum] = l_documentDivUnit;
                l_documentDivUnitNum++;
            }
            
            WEB3FPTForceLogoutInfoUnit[]  l_forceLogoutInfoList = null;
            WEB3FPTForceLogoutInfoUnit    l_forceLogoutInfoUnit = null;
            
            l_forceLogoutInfoUnit = new WEB3FPTForceLogoutInfoUnit();
            l_forceLogoutInfoUnit.transactionResult = l_executeResult;
            l_forceLogoutInfoUnit.transactionStartDate = l_executeStartTime;
            if (WEB3DocForceLogoutRunStatusStatusDef.PROCESSED.equals(l_executeResult) ||
                WEB3DocForceLogoutRunStatusStatusDef.PROCESS_ERROR.equals(l_executeResult))
            {
                l_forceLogoutInfoUnit.transactionEndDate = l_executeStopTime; 
            }
            l_forceLogoutInfoUnit.endCount = Long.toString(l_executeResultCount);
            l_forceLogoutInfoUnit.updaterCode = l_updaterCode;
            l_forceLogoutInfoUnit.documentDivList = l_documentDivUnits;
            
            l_forceLogoutInfoList = new WEB3FPTForceLogoutInfoUnit[1];
            l_forceLogoutInfoList[0] = l_forceLogoutInfoUnit;

            // createResponse() 
            l_response = (WEB3AdminFPTForceLogoutReferenceResponse) l_request.createResponse();
            l_response.forceLogoutInfoList = l_forceLogoutInfoList;
        }
               
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * ���ʖ����� �������O�A�E�g���������{����B
     * 
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B 
     * 
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��� ���ʖ����� 
     * �������O�A�E�g���̓��N�G�X�g�̏ꍇ 
     * �@@�|get���͉��()���R�[������B 
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��� ���ʖ����� 
     * �������O�A�E�g�m�F���N�G�X�g�̏ꍇ 
     * �@@�|validate�������O�A�E�g()���R�[������B 
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��� ���ʖ����� 
     * �������O�A�E�g�������N�G�X�g�̏ꍇ 
     * �@@�|submit�������O�A�E�g()���R�[������B 
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��� ���ʖ����� 
     * �������O�A�E�g���ʏƉ�N�G�X�g�̏ꍇ 
     * �@@�|get���ʏƉ�()���R�[������B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@roseuid 47DB254200B7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminFPTForceLogoutInputRequest)
        {
            l_response =
                this.getInputPage(
                    (WEB3AdminFPTForceLogoutInputRequest) l_request);
        } 
        else if (l_request instanceof WEB3AdminFPTForceLogoutConfirmRequest)
        {
            l_response =
                this.validateForceLogout(
                    (WEB3AdminFPTForceLogoutConfirmRequest) l_request);
        } 
        else if (l_request instanceof WEB3AdminFPTForceLogoutCompleteRequest)
        {
            l_response =
                this.submitForceLogout(
                    (WEB3AdminFPTForceLogoutCompleteRequest) l_request);
        } 
        else if (l_request instanceof WEB3AdminFPTForceLogoutReferenceRequest)
        {
            l_response =
                this.getResultRefrence(
                    (WEB3AdminFPTForceLogoutReferenceRequest) l_request);
        } 
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
