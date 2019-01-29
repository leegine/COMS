head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqRcvdQueueReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������RCVD�L���[�Ɖ�T�[�r�XImpl(WEB3AdminFeqRcvdQueueReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ����� (���u) �V�K�쐬
                   2006/10/18 �����(���u) ���f�� No.293�Ή�
                   2006/11/20 ����� (���u) ���f�� No.302�Ή�
                   2006/11/22 ����� (���u) ���f�� No.305�Ή�
                   2006/12/25 ���@@�r (���u) ���f�� No.325�Ή�
                   2007/01/15 ����� (���u) ���f�� No.331�Ή�
Revesion History : 2008/10/02 ����(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.472 �i�����jNo.026
Revesion History : 2009/08/03 ���@@�g(���u) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.503
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceInputRequest;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceInputResponse;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceRequest;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceResponse;
import webbroker3.feq.message.WEB3FeqRcvdQueueInfo;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3AdminFeqRcvdQueueReferenceService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.data.SleRcvdQRow;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҊO������RCVD�L���[�Ɖ�T�[�r�XImpl)<BR>
 * �Ǘ��ҊO������RCVD�L���[�Ɖ�T�[�r�X�����N���X<BR>
 * 
 * @@author �����
 * @@version 1.0
 */
public class WEB3AdminFeqRcvdQueueReferenceServiceImpl implements WEB3AdminFeqRcvdQueueReferenceService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqRcvdQueueReferenceServiceImpl.class);
        
    /**
     * @@roseuid 42D0CED203D8
     */
    public WEB3AdminFeqRcvdQueueReferenceServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��ҊO������RCVD�L���[�Ɖ�������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɑΉ����郁�\�b�h���R�[������B<BR>
     * <BR>
     * �|get���͉��()<BR>
     * �|get�ꗗ���()<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqRcvdQueueReferenceInputRequest)
        {
            //get���͉��()
            l_response = 
                this.getInputScreen((WEB3AdminFeqRcvdQueueReferenceInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqRcvdQueueReferenceRequest)
        {
            //get�ꗗ���
            l_response = 
                this.getListScreen((WEB3AdminFeqRcvdQueueReferenceRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;   
    }
    
    /**
     * (get���͉��)<BR>
     * �Ǘ��ҊO������RCVD�L���[�Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҊO������RCVD�L���[�Ɖ�T�[�r�X)get���͉�ʁv�Q��<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFeqRcvdQueueReferenceInputResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4214980A032E
     */
    protected WEB3AdminFeqRcvdQueueReferenceInputResponse getInputScreen(
        WEB3AdminFeqRcvdQueueReferenceInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqRcvdQueueReferenceInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 getInstanceFrom���O�C�����( )
        //���O�C�������Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��Ҍ����`�F�b�N���s���B 
        //[validate����()�Ɏw�肷�����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�O���i�������Ǘ��j 
        //is�X�V�F�@@false(�X�V�Ȃ�) 
        //���@@�\�J�e�S���R�[�h�́ADB���C�A�E�g�u�Ǘ��Ҍ����e�[�u��.xls#�i�⑫�����j�@@�\�J�e�S���R�[�h�ꗗ�v�Q�ƁB
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);
        
        //1.3 createResponse( )
        WEB3AdminFeqRcvdQueueReferenceInputResponse l_response = 
            (WEB3AdminFeqRcvdQueueReferenceInputResponse)l_request.createResponse();
        
        //1.4 �v���p�e�B�Z�b�g
        //�X�V�҃R�[�h: �Ǘ���.get�Ǘ��҃R�[�h()�̖߂�l
        l_response.updaterCode = l_admin.getAdministratorCode();
        
        //�����敪�ꗗ
        l_response.transactionDivList = new String[] {
            SleRcvdqProcStatusEnum.TODO.intValue() + "",
            SleRcvdqProcStatusEnum.PROCESSED.intValue() + "",
            SleRcvdqProcStatusEnum.EXEC_PROCESSING.intValue() + "",
            SleRcvdqProcStatusEnum.SKIP_PROCESSING_IGNORE.intValue() + "",
            SleRcvdqProcStatusEnum.SKIP_PROCESSING_ERROR.intValue() + ""};
        
        //�o�H�敪�ꗗ
        l_response.orderRootDivList = new String[] {
            WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY,
            WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD,
            WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT,
            WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_RESULT_UPLOAD};
        
        //��t�敪�ꗗ
        l_response.acceptDivList = new String[] {
            WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE,
            WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR,
            WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL,
            WEB3FeqAcceptTypeDef.CHANGED,
            WEB3FeqAcceptTypeDef.CHANGE_ERROR,
            WEB3FeqAcceptTypeDef.CANCEL,
            WEB3FeqAcceptTypeDef.CANCEL_ERROR,
            WEB3FeqAcceptTypeDef.NOT_EXECUTED};
        
        log.exiting(STR_METHOD_NAME);
        return l_response;  
    }
    
    /**
     * (get�ꗗ���)<BR>
     * �Ǘ��ҊO������RCVD�L���[�Ɖ�ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҊO������RCVD�L���[�Ɖ�T�[�r�X)get�ꗗ��ʁv�Q��<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFeqRcvdQueueReferenceResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4214980A032E
     */
    protected WEB3AdminFeqRcvdQueueReferenceResponse getListScreen(
        WEB3AdminFeqRcvdQueueReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminFeqRcvdQueueReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();  

        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��Ҍ����`�F�b�N���s���B 
        //[validate����()�Ɏw�肷�����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�O���i�������Ǘ��j 
        //is�X�V�F�@@false(�X�V�Ȃ�) 
        //���@@�\�J�e�S���R�[�h�́ADB���C�A�E�g�u�Ǘ��Ҍ����e�[�u��.xls#�i�⑫�����j�@@�\�J�e�S���R�[�h�ꗗ�v�Q�ƁB
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);
        
        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get�^�p�R�[�h(�،���ЃR�[�h : String, �^�p�R�[�h : String)
        //�،���ЃR�[�h�F get�،���ЃR�[�h�i�j�̖߂�l
        //�^�p�R�[�h�F���N�G�X�g�f�[�^.�^�p�R�[�h
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService)Services.getService(
                WEB3FeqOrderEmpCodeGettingService.class);
        String l_strEmpCode =
            l_feqOrderEmpCodeGettingService.getEmpCode(l_strInstitutionCode, l_request.managementCode);

        //1.5 create��������������
        //����������������쐬����B 
        //[create��������������()�Ɏw�肷�����] 
        //�،���ЃR�[�h�F �擾�����،���ЃR�[�h 
        //�X�V�҃R�[�h�F���N�G�X�g.�X�V�҃R�[�h 
        //�����敪�F ���N�G�X�g.�����敪 
        //�^�p�R�[�h�F ���N�G�X�g.�^�p�R�[�h 
        //���X�R�[�h�F ���N�G�X�g.���X�R�[�h 
        //�ڋq�R�[�h�F ���N�G�X�g.�ڋq�R�[�h 
        //�o�H�敪�F ���N�G�X�g.�o�H�敪 
        //��t�敪�F ���N�G�X�g.��t�敪
        String l_strQueryString = this.createQueryString(
            l_request.updaterCode,
            l_request.transactionDiv,
            l_request.managementCode,
            l_request.branchCode,
            l_request.accountCode,
            l_request.orderRootDiv,
            l_request.acceptDiv);
        
        //1.6 create���������f�[�^�R���e�i
        //���������f�[�^�R���e�i���쐬����B 
        //[create���������f�[�^�R���e�i()�Ɏw�肷�����] 
        //�،���ЃR�[�h�F �擾�����،���ЃR�[�h 
        //�X�V�҃R�[�h�F���N�G�X�g.�X�V�҃R�[�h 
        //�����敪�F ���N�G�X�g.�����敪 
        //�^�p�R�[�h�F get�^�p�R�[�h�i�j�̖߂�l 
        //���X�R�[�h�F ���N�G�X�g.���X�R�[�h 
        //�ڋq�R�[�h�F ���N�G�X�g.�ڋq�R�[�h 
        //�o�H�敪�F���N�G�X�g.�o�H�敪 
        //��t�敪�F���N�G�X�g.��t�敪

        Object[] l_objQueryDataContainer = this.createQueryDataContainer(
            l_strInstitutionCode,
            l_request.updaterCode,
            l_request.transactionDiv,
            l_strEmpCode,
            l_request.branchCode,
            l_request.accountCode,
            l_request.orderRootDiv,
            l_request.acceptDiv);
        
        //1.7 create�\�[�g����
        //�\�[�g�������쐬����B 
        //[create�\�[�g����()�Ɏw�肷�����] 
        //�\�[�g�L�[�F ���N�G�X�g.�\�[�g�L�[
        String l_strSortCond = this.createSortCond(l_request.sortKeys);
        
        //1.8 doFindAllQuery
        //�����ɊY������(�O�������)RCVD_Q�e�[�u���̃��X�g���擾����B 
        //[doFindAllQuery��()�Ɏw�肷�����] 
        //�s�^�C�v�F (�O�������)RCVD_Q�e�[�u��Row.TYPE 
        //��������������F ���������������������� 
        //�\�[�g�����F ���������\�[�g���� 
        //�R���f�B�V�����F null 
        //���������f�[�^�R���e�i�F �����������������f�[�^�R���e�i
        List l_lisRecord = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecord = l_queryProcessor.doFindAllQuery(
                SleRcvdQRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_objQueryDataContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }     
        
        //1.9 createResponse()
        WEB3AdminFeqRcvdQueueReferenceResponse l_response = 
            (WEB3AdminFeqRcvdQueueReferenceResponse)l_request.createResponse();
        
        //1.10 ���R�[�h���擾�ł����ꍇ(*1)
        if (l_lisRecord != null && !l_lisRecord.isEmpty())
        {
            //1.10.1 WEB3PageIndexInfo(List, int, int)
            //WEB3PageIndexInfo�I�u�W�F�N�g�𐶐�����B 
            //[�R���X�g���N�^�Ɏw�肷�����] 
            //���׃��X�g�F �擾����(�O�������)RCVD_Q�e�[�u���̃��X�g 
            //�v���y�[�W�ԍ��F ���N�G�X�g.�v���y�[�W�ԍ� 
            //�v���y�[�W���\���s���F ���N�G�X�g.�y�[�W���\���s��
            WEB3PageIndexInfo l_pageIndexInfo = 
                new WEB3PageIndexInfo(
                    l_lisRecord, 
                    Integer.parseInt(l_request.pageIndex), 
                    Integer.parseInt(l_request.pageSize));
                       
            //1.10.2 getArrayReturned
            //�\���Ώۂ�(�O�������)RCVD_Q�e�[�u��Params�̔z����擾����B  
            //[getArrayReturned()�Ɏw�肷�����]  
            //class�F�@@(�O�������)RCVD_Q�e�[�u��Params.class
            SleRcvdQParams[] l_sleRcvdQParams = 
                (SleRcvdQParams[])l_pageIndexInfo.getArrayReturned(SleRcvdQParams.class);
            
            //1.10.3 �擾�����\���Ώۂ�(�O�������)RCVD_Q�e�[�u��Params�̔z��̌������A�������J��Ԃ��B
            int l_intRcvdQParamsLength = 0;
            if (l_sleRcvdQParams != null && l_sleRcvdQParams.length > 0)
            {
                l_intRcvdQParamsLength = l_sleRcvdQParams.length;
            }
            List l_lisRcvdQueueInfo = new ArrayList();
            for (int i = 0; i < l_intRcvdQParamsLength; i++)
            {
                //1.10.3.1 �O������RCVD�L���[���()
                WEB3FeqRcvdQueueInfo l_rcvdQueueInfo = new WEB3FeqRcvdQueueInfo();
                
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accMgr = 
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                if (l_accMgr == null)
                {
                    log.debug("�g���A�J�E���g�}�l�[�W�������݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�g���A�J�E���g�}�l�[�W�������݂��Ȃ��B");
                }
                WEB3GentradeMainAccount l_mainAccount = null;
                try
                {
                    l_mainAccount = (WEB3GentradeMainAccount)
                        l_accMgr.getMainAccount(l_sleRcvdQParams[i].getAccountId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                } 

                //1.10.3.2
                //���������O������RCVD�L���[���I�u�W�F�N�g��(�O�������)RCVD_Q�e�[�u��Params�̍��ڒl��ݒ肷��B
                FeqOrderUnitRow l_feqOrderUnitRow = null;
                FeqProductRow l_feqProductRow = null;
                try
                {
                    List l_lstFeqOrderUnit =
                        FeqOrderUnitDao.findRowsByOrderId(Long.parseLong(l_sleRcvdQParams[i].getInternalRef()));
                    l_feqOrderUnitRow = (FeqOrderUnitRow)l_lstFeqOrderUnit.get(0);
                    l_feqProductRow = FeqProductDao.findRowByPk(l_feqOrderUnitRow.getProductId());
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //�ڋq�R�[�h�FRCVD_Q�e�[�u��Params�����ځ@@��U����ݒ�i���P���̓`�F�b�N�f�B�W�b�g�̈׍폜�j�B
                l_rcvdQueueInfo.accountCode = l_mainAccount.getAccountCode().substring(0, 6);

                //RCVD_Q�e�[�u��Params�̌o�H�敪��������t����F�ؖ��͏o�����͖��͒�����t���ʈꊇ���͖��͖�茋�ʈꊇ���͂̎�
                if (WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_CANCEL_AUTHENTICATE.equals(l_sleRcvdQParams[i].getRouteDiv())
                    || WEB3FeqOrderExecRouteDivDef.EXEC_INPUT.equals(l_sleRcvdQParams[i].getRouteDiv())
                    || WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_RESULT_UPLOAD.equals(l_sleRcvdQParams[i].getRouteDiv())
                    || WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD.equals(l_sleRcvdQParams[i].getRouteDiv()))
                {
                    //�����敪�F�����P��.�������==701�̎��@@�P
                    if (OrderTypeEnum.FEQ_BUY.equals(l_feqOrderUnitRow.getOrderType()))
                    {
                        l_rcvdQueueInfo.dealingType = WEB3BuySellTypeDef.BUY;
                    }
                    //�����P��.�������==702�̎��@@�Q
                    else if (OrderTypeEnum.FEQ_SELL.equals(l_feqOrderUnitRow.getOrderType()))
                    {
                        l_rcvdQueueInfo.dealingType = WEB3BuySellTypeDef.SELL;
                    }
                }
                //��L�ȊO
                else
                {
                    //�����敪�FRCVD_Q�e�[�u��Params�����ڂ�ݒ�B
                    if (!l_sleRcvdQParams[i].getSideIsNull())
                    {
                        l_rcvdQueueInfo.dealingType = l_sleRcvdQParams[i].getSide() + "";
                    }
                }
                //�����R�[�h�F�����P��.����ID�ɊY���������.�����R�[�h��ݒ�B
                l_rcvdQueueInfo.productCode = l_feqProductRow.getProductCode();
                //��L�ȊO��RCVD_Q�e�[�u��Params�̓�����
                l_rcvdQueueInfo.acceptDiv = l_sleRcvdQParams[i].getAcceptDiv();
                l_rcvdQueueInfo.branchCode = l_sleRcvdQParams[i].getBranchCode();
                l_rcvdQueueInfo.createTimeStamp = l_sleRcvdQParams[i].getCreatedTimestamp();
                if (!l_sleRcvdQParams[i].getFxRateIsNull())
                {
                    l_rcvdQueueInfo.exchangeRate =
                        WEB3StringTypeUtility.formatNumber(l_sleRcvdQParams[i].getFxRate());
                }
                if (!l_sleRcvdQParams[i].getExecPriceIsNull())
                {
                    l_rcvdQueueInfo.execPrice =
                        WEB3StringTypeUtility.formatNumber(l_sleRcvdQParams[i].getExecPrice());
                }
                if (!l_sleRcvdQParams[i].getExecQtyIsNull())
                {
                    l_rcvdQueueInfo.execQuantity =
                        WEB3StringTypeUtility.formatNumber(l_sleRcvdQParams[i].getExecQty());
                }
                l_rcvdQueueInfo.executeNo = l_sleRcvdQParams[i].getExecSerialNo();
                l_rcvdQueueInfo.executionDate = l_sleRcvdQParams[i].getExecutionDate();
                l_rcvdQueueInfo.executionTimestamp = l_sleRcvdQParams[i].getExecTimestamp();
                l_rcvdQueueInfo.localDeliveryDate = l_sleRcvdQParams[i].getFDeliveryDate();
                l_rcvdQueueInfo.managementCode = l_sleRcvdQParams[i].getOrderEmpCode();
                l_rcvdQueueInfo.orderId = l_sleRcvdQParams[i].getInternalRef();
                l_rcvdQueueInfo.orderRootDiv = l_sleRcvdQParams[i].getRouteDiv();
                l_rcvdQueueInfo.transactionDiv = l_sleRcvdQParams[i].getStatus().intValue() + "";
                l_rcvdQueueInfo.updaterCode = l_sleRcvdQParams[i].getLastUpdater();
                l_rcvdQueueInfo.updateTimeStamp = l_sleRcvdQParams[i].getLastUpdatedTimestamp();

                //���ʃR�[�h
                l_rcvdQueueInfo.requestNumber = l_sleRcvdQParams[i].getOrderRequestNumber();

                //���ی����R�[�h
                l_rcvdQueueInfo.rejectCauseCode = l_sleRcvdQParams[i].getRejectCode();

                l_lisRcvdQueueInfo.add(l_rcvdQueueInfo);
            }
     
            WEB3FeqRcvdQueueInfo[] l_rcvdQueueInfos = new WEB3FeqRcvdQueueInfo[l_lisRcvdQueueInfo.size()];
            l_lisRcvdQueueInfo.toArray(l_rcvdQueueInfos);
            //1.10.4 getTotalPages()
            int l_intTotalPages = l_pageIndexInfo.getTotalPages();
            
            //1.10.5 getTotalRecords()
            int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();            
            
            //1.10.6 getPageIndex()
            int l_intPageIndex = l_pageIndexInfo.getPageIndex();
            
            //1.10.7 �v���p�e�B�Z�b�g
            l_response.feqRcvdQueueInfoList = l_rcvdQueueInfos;
            l_response.totalPages = l_intTotalPages + "";
            l_response.totalRecords = l_intTotalRecords + "";
            l_response.pageIndex = l_intPageIndex + "";      
        }
        else
        {
            //1.11 ���R�[�h���擾�ł��Ȃ������ꍇ(*2)
            l_response.feqRcvdQueueInfoList = null;
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";  
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j�\�[�g����������(�FString)���쐬����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     * �@@�@@�쐬�����\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�E�u�X�V�҃R�[�h�v �� (�O�������)RCVD_Q�e�[�u��.�X�V�҃R�[�h<BR>
     * �@@�@@�E�u�^�p�R�[�h�v �� (�O�������)RCVD_Q�e�[�u��.�^�p�R�[�h<BR>
     * �@@�@@�E�u���X�R�[�h�v �� (�O�������)RCVD_Q�e�[�u��.���X�R�[�h <BR>
     * �@@�@@�E�u�ڋq�R�[�h�v �� substr((�O�������)RCVD_Q�e�[�u��.�A�J�E���gID, 9, 6)<BR>
     * �@@�@@�E�u�o�H�敪�v�@@���@@(�O�������)RCVD_Q�e�[�u��.�o�H�敪<BR>
     * �@@�@@�E�u��t�敪�v�@@���@@(�O�������)RCVD_Q�e�[�u��.��t�敪<BR>
     * �@@�@@�E�u�����敪�v �� (�O�������)RCVD_Q�e�[�u��.�����敪<BR>
     * �@@�@@�E�u�쐬���t�v �� (�O�������)RCVD_Q�e�[�u��.�쐬���t<BR>
     * �@@�@@�E�u�X�V���t�v �� (�O�������)RCVD_Q�e�[�u��.�X�V���t<BR>
     * <BR>
     * �@@�Q�|�Q�j�\�[�g�L�[.�����^�~���ɑΉ�����擾����<BR>
     * �@@�@@(asc or desc)���\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �R�j�\�[�g���������ɁA(�O�������)RCVD_Q�e�[�u��.�X�V���t�������w��ŕt��<BR>
     * �@@���L�[���ڂɁu�X�V���t�v���w�肳��Ă���ꍇ�͕t�����Ȃ��B<BR>
     * <BR>
     * �S�j�쐬�����\�[�g�����������ԋp����B<BR>
     * <BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    protected String createSortCond(WEB3ForeignSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3ForeignSortKey[]) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKeys == null || l_sortKeys.length == 0)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        //�P�j�\�[�g����������(�FString)���쐬����B
        StringBuffer l_sortCond = new StringBuffer();
        
        //�Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        int l_intSortKeysLength = l_sortKeys.length;
        boolean l_blnDateStatus = true;
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            //�X�V�҃R�[�h
            if (WEB3FeqSortKeyItemNameDef.UPDATER_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" last_updater ASC ");
                }
                else
                {
                    l_sortCond.append(" last_updater DESC ");
                }
            }
            //�^�p�R�[�h
            else if (WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" order_emp_code ASC ");
                }
                else
                {
                    l_sortCond.append(" order_emp_code DESC ");
                }
            }
            //���X�R�[�h
            else if (WEB3FeqSortKeyItemNameDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" branch_code ASC ");
                }
                else
                {
                    l_sortCond.append(" branch_code DESC ");
                }
            }
            //�ڋq�R�[�h
            else if (WEB3FeqSortKeyItemNameDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" substr(account_id, 9, 6) ASC ");
                }
                else
                {
                    l_sortCond.append(" substr(account_id, 9, 6) DESC ");
                }
            }
            //�o�H�敪
            else if (WEB3FeqSortKeyItemNameDef.ORDER_ROOT_DIV.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" route_div ASC ");
                }
                else
                {
                    l_sortCond.append(" route_div DESC ");
                }
            }
            //��t�敪
            else if (WEB3FeqSortKeyItemNameDef.ACCEPT_DIV.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" accept_div ASC ");
                }
                else
                {
                    l_sortCond.append(" accept_div DESC ");
                }
            }
            //�����敪
            else if (WEB3FeqSortKeyItemNameDef.TRANSACTION_DIV.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" status ASC ");
                }
                else
                {
                    l_sortCond.append(" status DESC ");
                }
            }
            //�쐬���t
            else if (WEB3FeqSortKeyItemNameDef.CREATED_TIMESTAMP.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" created_timestamp ASC ");
                }
                else
                {
                    l_sortCond.append(" created_timestamp DESC ");
                }
            }
            //�X�V���t
            else if (WEB3FeqSortKeyItemNameDef.LAST_UPDATED_TIMESTAMP.equals(l_sortKeys[i].keyItem))
            {
                l_blnDateStatus = false;
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" last_updated_timestamp ASC ");
                }
                else
                {
                    l_sortCond.append(" last_updated_timestamp DESC ");
                }
            }
            if (i < l_sortKeys.length - 1)
            {
                l_sortCond.append(" , ");
            }
        }
        if (l_blnDateStatus)
        {
            if (l_sortCond.length() == 0)
            {
                l_sortCond.append(" last_updated_timestamp ASC ");
            }
            else
            {           
                l_sortCond.append(" , last_updated_timestamp ASC ");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_sortCond.toString();
    }
    
    /**
     * (create��������������)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j��������������C���X�^���X(�FString)�𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "institution_code = ? "<BR>
     * <BR>
     * �R�j�p�����[�^.�X�V�҃R�[�h != null�̏ꍇ�A<BR>
     * �@@�X�V�҃R�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and last_updater = ? "<BR>
     * <BR>
     * �S�j�p�����[�^.�����敪 != null�̏ꍇ�A<BR>
     * �@@�����敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and status = ? "<BR>
     * <BR>
     * �T�j�p�����[�^.�^�p�R�[�h != null�̏ꍇ�A<BR>
     * �@@�^�p�R�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and order_emp_code = ? "<BR>
     * <BR>
     * �U�j�p�����[�^.���X�R�[�h != null�̏ꍇ�A<BR>
     * �@@���X�R�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and branch_code = ? "<BR>
     * <BR>
     * �V�j�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * �@@�ڋq�R�[�h��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and substr(account_id, 9, 6) = ? "<BR>
     * <BR>
     * �W�j�p�����[�^.�o�H�敪 != null�̏ꍇ�A<BR>
     * �@@�o�H�敪����������������ɒǉ�����B<BR>  
     * <BR>
     * �@@�������������� += "and route_div = ? "<BR>
     * <BR>
     * �X�j�p�����[�^.��t�敪 != null�̏ꍇ�A<BR>
     * �@@��t�敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and accept_div = ? "<BR>
     * <BR>
     * �P�O�j�p�����[�^.�o�H�敪 == null�̏ꍇ�A <BR>
     * �@@�o�H�敪����������������ɒǉ�����B <BR>
     * <BR>
     * �@@�������������� += "and route_div is not null " <BR>
     * <BR>
     * <BR>
     * �P�P�j�쐬�������������������ԋp����B<BR>
     * <BR> 
     * @@param l_StrUpdaterCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@param l_StrStatus - (�����敪)<BR>
     * �����敪<BR>
     * @@param l_StrOrderEmpCode - (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     * @@param l_StrBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_StrCustomerCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_StrOrderRootDiv - (�o�H�敪)<BR>
     * �o�H�敪<BR>
     * @@param l_StrAcceptDiv - (��t�敪)<BR>
     * ��t�敪<BR>
     * @@return String
     * @@roseuid 4214980A032E
     */
    protected String createQueryString(
        String l_StrUpdaterCode, 
        String l_StrStatus, 
        String l_StrOrderEmpCode,
        String l_StrBranchCode,
        String l_StrCustomerCode,
        String l_StrOrderRootDiv,
        String l_StrAcceptDiv) 
    {
        final String STR_METHOD_NAME = 
            "createQueryString(String, String, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j��������������C���X�^���X(�FString)�𐶐�����B 
        String l_strQueryString = "";
        
        //�Q�j�،���ЃR�[�h����������������ɒǉ�����B 
        //�������������� += "institution_code = ? " 
        l_strQueryString = l_strQueryString + " institution_code = ? ";
        
        //�R�j�p�����[�^.�X�V�҃R�[�h != null�̏ꍇ�A 
        //�X�V�҃R�[�h����������������ɒǉ�����B 
        //�������������� += "and last_updater = ? "
        if (l_StrUpdaterCode != null)
        {
            l_strQueryString = l_strQueryString + " and last_updater = ? ";
        }
        
        //�S�j�p�����[�^.�����敪 != null�̏ꍇ�A 
        //�����敪����������������ɒǉ�����B 
        //�������������� += "and status = ? "
        if (l_StrStatus != null)
        {
            l_strQueryString = l_strQueryString + " and status = ? ";
        }
        
        //�T�j�p�����[�^.�^�p�R�[�h != null�̏ꍇ�A 
        //�^�p�R�[�h����������������ɒǉ�����B 
        //�������������� += "and order_emp_code = ? "
        if (l_StrOrderEmpCode != null)
        {
            l_strQueryString = l_strQueryString + " and order_emp_code = ? ";
        }
        
        //�U�j�p�����[�^.���X�R�[�h != null�̏ꍇ�A 
        //���X�R�[�h����������������ɒǉ�����B 
        //�������������� += "and branch_code = ? " 
        if (l_StrBranchCode != null)
        {
            l_strQueryString = l_strQueryString + " and branch_code = ? ";
        }
        
        //�V�j�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A 
        //�ڋq�R�[�h��������������������ɒǉ�����B 
        //�������������� += "and substr(account_id, 9, 6) = ? " 
        if (l_StrCustomerCode != null)
        {
            l_strQueryString = l_strQueryString + " and substr(account_id, 9, 6) = ? ";
        }
        
        //�W�j�p�����[�^.�o�H�敪 != null�̏ꍇ�A 
        //�o�H�敪����������������ɒǉ�����B 
        //�������������� += "and route_div = ? " 
        if (l_StrOrderRootDiv != null)
        {
            l_strQueryString = l_strQueryString + " and route_div = ? ";
        }
        
        //�X�j�p�����[�^.��t�敪 != null�̏ꍇ�A 
        //��t�敪����������������ɒǉ�����B 
        //�������������� += "and accept_div = ? " 
        if (l_StrAcceptDiv != null)
        {
            l_strQueryString = l_strQueryString + " and accept_div = ? ";
        }
        
        //�P�O�j�p�����[�^.�o�H�敪 == null�̏ꍇ�A 
        //�o�H�敪����������������ɒǉ�����B 
        //�������������� += "and route_div is not null " 
        if (l_StrOrderRootDiv == null)
        {
            l_strQueryString = l_strQueryString + " and route_div is not null ";
        }

        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����^.�،���ЃR�[�h�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�p�����[�^.�X�V�҃R�[�h != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�X�V�҃R�[�h�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j�p�����[�^.�����敪 != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�����敪�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�j�p�����[�^.�^�p�R�[�h != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�^�p�R�[�h�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �U�j�p�����[�^.���X�R�[�h != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.���X�R�[�h�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �V�j�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�ڋq�R�[�h�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �W�j�p�����[�^.�o�H�敪 != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�o�H�敪�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �X�j�p�����[�^.��t�敪 != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.��t�敪�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �P�O�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_StrInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_StrUpdaterCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@param l_StrStatus - (�����敪)<BR>
     * �����敪<BR>
     * @@param l_StrOrderEmpCode - (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     * @@param l_StrBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_StrCustomerCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@param l_StrOrderRootDiv - (�o�H�敪)<BR>
     * �o�H�敪<BR>
     * @@param l_StrAcceptDiv - (��t�敪)<BR>
     * ��t�敪<BR>
     * @@return Object[]
     * @@roseuid 4214980A032E
     */
    protected Object[] createQueryDataContainer(
        String l_StrInstitutionCode, 
        String l_StrUpdaterCode, 
        String l_StrStatus, 
        String l_StrOrderEmpCode,
        String l_StrBranchCode,
        String l_StrCustomerCode,
        String l_StrOrderRootDiv,
        String l_StrAcceptDiv)
    {
        final String STR_METHOD_NAME = 
            "createQueryDataContainer(String, String, String, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jArrayList�𐶐�����B 
        ArrayList l_arrayList = new ArrayList();
        
        //�Q�j�p�����^.�،���ЃR�[�h�𐶐�����ArrayList�ɒǉ�����B 
        l_arrayList.add(l_StrInstitutionCode);
        
        //�R�j�p�����[�^.�X�V�҃R�[�h != null�̏ꍇ�A 
        //�p�����[�^.�X�V�҃R�[�h�𐶐�����ArrayList�ɒǉ�����B 
        if (l_StrUpdaterCode != null)
        {
            l_arrayList.add(l_StrUpdaterCode);
        }
        
        //�S�j�p�����[�^.�����敪 != null�̏ꍇ�A 
        //�p�����[�^.�����敪�𐶐�����ArrayList�ɒǉ�����B 
        if (l_StrStatus != null)
        {
            l_arrayList.add(l_StrStatus);
        }
        
        //�T�j�p�����[�^.�^�p�R�[�h != null�̏ꍇ�A 
        //�p�����[�^.�^�p�R�[�h�𐶐�����ArrayList�ɒǉ�����B 
        if (l_StrOrderEmpCode != null)
        {
            l_arrayList.add(l_StrOrderEmpCode);
        }
        
        //�U�j�p�����[�^.���X�R�[�h != null�̏ꍇ�A 
        //�p�����[�^.���X�R�[�h�𐶐�����ArrayList�ɒǉ�����B 
        if (l_StrBranchCode != null)
        {
            l_arrayList.add(l_StrBranchCode);
        }
        
        //�V�j�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A 
        //�p�����[�^.�ڋq�R�[�h�𐶐�����ArrayList�ɒǉ�����B 
        if (l_StrCustomerCode != null)
        {
            l_arrayList.add(l_StrCustomerCode);
        }
        
        //�W�j�p�����[�^.�o�H�敪 != null�̏ꍇ�A 
        //�p�����[�^.�o�H�敪�𐶐�����ArrayList�ɒǉ�����B 
        if (l_StrOrderRootDiv != null)
        {
            l_arrayList.add(l_StrOrderRootDiv);
        }
        
        //�X�j�p�����[�^.��t�敪 != null�̏ꍇ�A 
        //�p�����[�^.��t�敪�𐶐�����ArrayList�ɒǉ�����B 
        if (l_StrAcceptDiv != null)
        {
            l_arrayList.add(l_StrAcceptDiv);
        }
    
        //�P�O�j��������ArrayList.toArray()�̖߂�l��ԋp����
        Object[] l_objQueryDataContainer = new Object[l_arrayList.size()];
        l_arrayList.toArray(l_objQueryDataContainer);
        
        log.exiting(STR_METHOD_NAME);
        return l_objQueryDataContainer;
    }
}
@
