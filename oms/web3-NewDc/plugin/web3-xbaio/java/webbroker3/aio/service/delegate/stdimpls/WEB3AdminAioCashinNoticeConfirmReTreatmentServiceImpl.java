head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �����ʒm�m�F�ď����T�[�r�XImpl�N���X(WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImpl)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/1/20 ���� (���u) �V�K�쐬
 Revesion History : 2006/8/24 �Ԑi (���u) �d�l�ύX ���f�� 617�A633�A639�A640
 Revesion History : 2006/11/09 ���G��(���u) �d�l�ύX ���f�� 681
 Revesion History : 2006/11/14 ���G�� (���u)  �c�a�X�V�d�l�@@No.133
 Revesion History : 2007/06/19 �đo�g (���u) �d�l�ύX ���f��No.726
 Revesion History : 2009/02/05 �đo�g (���u) ���f��No.1095�ANo.1104
 */

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.data.BankDepositNotifyPK;
import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.aio.data.BankDepositNotifyRow;
import webbroker3.aio.define.WEB3AioBankDepositNotifyCashTransferDivDef;
import webbroker3.aio.define.WEB3AioCurrencyCodeDef;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeConfirmResponse;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeInputRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeInputResponse;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeSearchRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeSearchResponse;
import webbroker3.aio.message.WEB3AioCashinNoticeUnit2;
import webbroker3.aio.message.WEB3ForeignSummaryInfo;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinNoticeConfirmReTreatmentService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BankDepositNotifyStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.ExclusiveUseAccountDao;
import webbroker3.gentrade.data.ExclusiveUseAccountRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����ʒm�m�F�ď����T�[�r�XImpl)<BR>
 * �����ʒm�m�F�ď����T�[�r�XImpl �N���X<BR>
 *
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImpl
implements WEB3AdminAioCashinNoticeConfirmReTreatmentService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImpl.class);
    
    /**
     * �����A���m�F�T�[�r�X�������s���B<BR>
     * <BR>
     * ����.���N�G�X�g�̌^�ɂ���� <BR>
     * �ȉ��̂悤�Ƀ��\�b�h���ĂԁB <BR>
     * <BR>
     * �P�j���N�G�X�g�������ʒm�������N�G�X�g�̏ꍇ <BR>
     * this.get�����ʒm�ꗗ <BR>
     * <BR>
     * �Q�j���N�G�X�g�������ʒm�������͉�ʃ��N�G�X�g�̏ꍇ <BR>
     * this.get�����ʒm�������͉�� <BR>
     * <BR>
     * �R�j���N�G�X�g�������ʒm�����m�F���N�G�X�g�̏ꍇ <BR>
     * this.validate�����ʒm���� <BR>
     * <BR>
     * �S�j���N�G�X�g�������ʒm�����������N�G�X�g�̏ꍇ <BR>
     * this.submit�����ʒm���� <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410755600136
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j���N�G�X�g�������ʒm�������N�G�X�g�̏ꍇ
        //this.get�����ʒm�ꗗ
        
        if (l_request instanceof WEB3AdminAioCashinNoticeSearchRequest)
        {
            WEB3AdminAioCashinNoticeSearchResponse l_searchResponse =
                this.getCashinNoticeList(
                        (WEB3AdminAioCashinNoticeSearchRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_searchResponse;
        }
        //�Q�j���N�G�X�g�������ʒm�������͉�ʃ��N�G�X�g�̏ꍇ
        //this.get�����ʒm�������͉��
        else if (l_request instanceof WEB3AdminAioCashinNoticeChangeInputRequest)
        {
            WEB3AdminAioCashinNoticeChangeInputResponse l_changeInputResponse =
                this.getCashinNoticeChangeInputScreen(
                        (WEB3AdminAioCashinNoticeChangeInputRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_changeInputResponse;
        }
        //�R�j���N�G�X�g�������ʒm�����m�F���N�G�X�g�̏ꍇ
        //this.validate�����ʒm����
        else if (l_request instanceof WEB3AdminAioCashinNoticeChangeConfirmRequest)
        {
            WEB3AdminAioCashinNoticeChangeConfirmResponse l_changeConfirmResponse =
                this.validateCashinNoticeChange(
                        (WEB3AdminAioCashinNoticeChangeConfirmRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_changeConfirmResponse;
        }
        //�S�j���N�G�X�g�������ʒm�����������N�G�X�g�̏ꍇ
        //this.submit�����ʒm����
        else if (l_request instanceof WEB3AdminAioCashinNoticeChangeCompleteRequest)
        {
            WEB3AdminAioCashinNoticeChangeCompleteResponse l_changeCompleteResponse =
                this.submitCashinNoticeChange(
                        (WEB3AdminAioCashinNoticeChangeCompleteRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_changeCompleteResponse;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s��");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get�����ʒm�ꗗ)<BR>
     * �����ʒm�����������s���ꗗ��ԋp����B <BR>
     * <BR>
     * ���V�[�P���X�}�u�i�����ʒm�m�F�ď����jget�����ʒm�ꗗ�v <BR>
     * �Q��<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AdminAioCashinNoticeSearchResponse
     * @@roseuid 4108B52800FA
     */
    protected WEB3AdminAioCashinNoticeSearchResponse getCashinNoticeList(
            WEB3AdminAioCashinNoticeSearchRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCashinNoticeList(" +
        "WEB3AdminAioCashinNoticeSearchRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���[�R�[�h = �����A�g
        //is�X�V = false
        l_administrator.validateAuthority(
                WEB3TransactionCategoryDef.BANK_COOPERATION,
                false);
        
        //1.4 ���N�G�X�g.���X�R�[�h�̔z�񌏐���LOOP����B
        //���X�R�[�h�@@= ���N�G�X�g.���X�R�[�h
        for (int i = 0; i < l_request.branchCode.length; i++)
        {
            String l_strBranchCode = l_request.branchCode[i];
            
            //1.4.1 validate���X����(���X�R�[�h : String[])
            l_administrator.validateBranchPermission(l_strBranchCode);
        }
        
        //1.5 create��������(�����ʒm�������N�G�X�g)
        String l_strSearchCond = this.createQueryCondition(l_request);
        
        //1.6 create�\�[�g�L�[(�����ʒm�������N�G�X�g)
        String l_strSortKey = this.createSortKey(l_request);
        
        //1.7 �����ʒm�e�[�u���������B
        
        //�������� = create��������()�̖߂�l
        //�\�[�g�L�[ = create�\�[�g�L�[()�̖߂�l
        //�y�[�W���\���s�� = ���N�G�X�g.�y�[�W�\����
        //�y�[�W�i���o�[ = ���N�G�X�g.�y�[�W�v���ԍ� - 1
        List l_lisBankDepositNotifyRows = null;
        
        Object[] l_objVars = this.createQueryContainer(l_request, l_administrator.getInstitutionCode());
        
        
        try
        {
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();
            
            l_lisBankDepositNotifyRows =
                l_queryProcessor.doFindAllQuery(
                        BankDepositNotifyRow.TYPE,
                        l_strSearchCond,
                        l_strSortKey,
                        null,
                        l_objVars);
            
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        
        int l_intSize = 0;
        
        if (l_lisBankDepositNotifyRows != null || l_lisBankDepositNotifyRows.size() != 0)
        {
            l_intSize = l_lisBankDepositNotifyRows.size();
            log.debug("�����ʒm���� = " + l_intSize);
            
            for(int i = 0; i < l_lisBankDepositNotifyRows.size(); i++)
            {
                log.debug("�������ꂽ�����ʒm���R�[�h" + (BankDepositNotifyRow)l_lisBankDepositNotifyRows.get(i));
                
            }
        }
        
        List l_lisCashinNoticeUnit2 = new ArrayList();
        
        try
        {
            //1.8 �������ꂽ�����ʒm������LOOP
            for (int i = 0; i < l_intSize; i++)
            {
                BankDepositNotifyRow l_bankDepositNotifyRow =
                    (BankDepositNotifyRow)l_lisBankDepositNotifyRows.get(i);
                
                BankDepositNotifyParams l_bankDepositNotifyParams =
                    new BankDepositNotifyParams(l_bankDepositNotifyRow);
                
                //1.8.1 (���N�G�X�g.�����敪 == null)�@@OR
                //(���N�G�X�g.�����敪 != null AND ���N�G�X�g.�����敪 = �����ʒm���R�[�h.�����敪)
                //�̏ꍇ
                //create�����ʒm����()�̖߂�l��ArrayList�ɒǉ��B
                if (l_request.transactionDiv == null ||
                        (l_request.transactionDiv != null &&
                                l_request.transactionDiv.equals(
                                        l_bankDepositNotifyParams.getStatus())))
                {
                    WEB3AioCashinNoticeUnit2 l_cashinNoticeUnit2 = null;
                    WEB3GentradeMainAccount l_mainAccount = null;
                    
                    
                    if(l_bankDepositNotifyParams.getBranchCode() != null &&
                            l_bankDepositNotifyParams.getAccountCode() != null)
                    {
                        
                        try
                        {
                            //1.8.1.1 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
                            
                            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                            WEB3GentradeAccountManager l_accountManager =
                                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                            
                            l_mainAccount =
                                l_accountManager.getMainAccount(
                                        l_bankDepositNotifyParams.getInstitutionCode(),
                                        l_bankDepositNotifyParams.getBranchCode(),
                                        l_bankDepositNotifyParams.getAccountCode());
                        }
                        catch (WEB3BaseException l_ex)
                        {
                            //1.8.1.2 ��O������
                            //�ڋq�����݃G���[��O�������Ă�
                            //�ߑ����邾���łȂɂ����Ȃ��B
                            log.debug("Not found mainAccount " +
                                    "�،���ЃR�[�h = " + l_bankDepositNotifyParams.getInstitutionCode() +
                                    "���X�R�[�h = " + l_bankDepositNotifyParams.getBranchCode() +
                                    "�����R�[�h = " + l_bankDepositNotifyParams.getAccountCode());
                            
                            log.debug("�ڋq�����݃G���[��O����", l_ex);
                        }
                    }
                    
                    //1.8.1.3 create�����ʒm����(�����ʒmParams, �ڋq)
                    l_cashinNoticeUnit2 =
                        this.createCashinNoticeUnit(
                                l_bankDepositNotifyParams,
                                l_mainAccount);
                    
                    l_lisCashinNoticeUnit2.add(l_cashinNoticeUnit2);
                    
                }
            }
            
            WEB3AioCashinNoticeUnit2[] l_cashinNoticeUnits2 =
                new WEB3AioCashinNoticeUnit2[l_lisCashinNoticeUnit2.size()];
            l_lisCashinNoticeUnit2.toArray(l_cashinNoticeUnits2);
            
            //�y�[�W
            int l_intPageSize = Integer.parseInt(l_request.pageSize);
            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            
            WEB3PageIndexInfo l_pageIndexInfo =
                new WEB3PageIndexInfo(
                        l_cashinNoticeUnits2,
                        l_intPageIndex,
                        l_intPageSize);
            
            l_cashinNoticeUnits2 =
                (WEB3AioCashinNoticeUnit2[])l_pageIndexInfo.getArrayReturned(
                        WEB3AioCashinNoticeUnit2.class);
            
            // 1.9 ���X�|���X�f�[�^�𐶐�����B
            WEB3AdminAioCashinNoticeSearchResponse l_response =
                (WEB3AdminAioCashinNoticeSearchResponse) l_request.createResponse();
            
            //1.10�w�肵���،���Ђ��o�^���Ă���ʉ݂̒ʉ݃R�[�h�����ׂĎ擾����B 
            String[] l_strCurrencyCodes = WEB3GentradeCurrency.getCurrencyCodeList(
                l_administrator.getInstitutionCode());
            
            //1.11�T�}�����𐶐�����B 
            this.createSummaryInfo(l_strCurrencyCodes, 
                l_lisBankDepositNotifyRows,
                l_response);
            
            
            // 1.12 �v���p�e�B�Z�b�g
            //���X�|���X.�����ʒm���׈ꗗ = ArrayList.toArray()�̖߂�l
            l_response.cashinNoticeList = l_cashinNoticeUnits2;   
            
            //���X�|���X.�\���y�[�W�ԍ� = ���N�G�X�g.�\���y�[�W�ԍ�
            l_response.pageIndex = l_request.pageIndex;
            
            //���X�|���X.���y�[�W�� = ListPage(*).getTotalPages()
            l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
            
            //���X�|���X.�����R�[�h�� = ListPage(*).getTotalSize()
            l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
            
            //���X�|���X.�I��ʉ݃R�[�h = get�ʉ݃R�[�h�ꗗ�i�j�̖߂�l
            l_response.selectCurrencyCode = l_strCurrencyCodes;
            
            //(*)doFindAllQuery()�̖߂�l
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
            
        }
        catch(NumberFormatException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
    }
    
    /**
     * (get�����ʒm�������͉��)<BR>
     * �����ʒm������ʕ\���������s���B <BR>
     * <BR>
     * ���V�[�P���X�}�u�i�����ʒm�m�F�ď����jget�����ʒm�������͉�ʁv�Q��<BR>
     * =============================================== <BR>
     * �@@�@@��̈ʒu : �����ʒmParams.�����敪 != "9�F�G���["�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��O��throw����<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02839<BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AdminAioCashinNoticeChangeInputResponse
     * @@roseuid 4108BA730187
     */
    protected WEB3AdminAioCashinNoticeChangeInputResponse getCashinNoticeChangeInputScreen(
            WEB3AdminAioCashinNoticeChangeInputRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCashinNoticeChangeInputScreen(" +
        "WEB3AdminAioCashinNoticeChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h = �����ʒm�m�F�ď���
        //is�X�V = true
        
        l_administrator.validateAuthority(
                WEB3TransactionCategoryDef.BANK_COOPERATION,
                true);
        
        List l_lisBankDepositNotifyRows = null;
        List l_lisCashinNoticeUnit2 = new ArrayList();

        //�����ʒm�e�[�u������
        //���N�G�X�g.�����ʒmID(�z��)�̒l��
        //�ȉ��̂Ƃ���ɁAor �����ɓW�J���Č����B
        //[����]
        //(��s�����ʒm�e�[�u��ID = ���N�G�X�g.�����ʒmID[index]��2���ځ`�Ō�
        //�@@AND �،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h()
        //�@@AND �f�[�^�捞�敪 = ���N�G�X�g.�����ʒmID[index]��1����)
        //OR �c
        List l_lisBindVars = new ArrayList();
        StringBuffer l_sbWhere = new StringBuffer();
        int l_intLength = 0;
        if (l_request.cashinNoticeTableId != null)
        {
            l_intLength = l_request.cashinNoticeTableId.length;
        }
        int l_intLengthShort = l_intLength - 1;
        for (int i = l_intLengthShort; i >= 0; i--)
        {
            // �،���ЃR�[�h���擾����B
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            //(��s�����ʒm�e�[�u��ID = ���N�G�X�g.�����ʒmID[index]��2���ځ`�Ō�
            long l_lngBankDepositNotifyId = Long.parseLong(l_request.cashinNoticeTableId[i].substring(1));

            //�@@AND �f�[�^�捞�敪 = ���N�G�X�g.�����ʒmID[index]��1����)
            String l_strDataLoadDiv = l_request.cashinNoticeTableId[i].substring(0 ,1);

            l_sbWhere.append(" (bank_deposit_notify_id = ? ");
            l_lisBindVars.add(new Long(l_lngBankDepositNotifyId));
            log.debug(" (bank_deposit_notify_id = ? " + new Long(l_lngBankDepositNotifyId));

            l_sbWhere.append(" and institution_code = ? ");
            l_lisBindVars.add(l_strInstitutionCode);
            log.debug(" and institution_code = ? " + l_strInstitutionCode);

            l_sbWhere.append(" and data_load_div = ?) ");
            l_lisBindVars.add(l_strDataLoadDiv);
            log.debug(" and data_load_div = ?) " + l_strDataLoadDiv);

            if (i != 0)
            {
                l_sbWhere.append("or ");
                log.debug("or ");
            }
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBankDepositNotifyRows =
                l_queryProcessor.doFindAllQuery(
                    BankDepositNotifyRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_lisBindVars.toArray());
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        //1.5 �������ꂽ�����ʒm���R�[�h������LOOP
        for (Iterator l_iter = l_lisBankDepositNotifyRows.iterator(); l_iter.hasNext(); )
        {
            BankDepositNotifyRow l_bankDepositNotifyRow =
                (BankDepositNotifyRow)l_iter.next();
            BankDepositNotifyParams l_bankDepositNotifyParams =
                new BankDepositNotifyParams(l_bankDepositNotifyRow);

            //�����ʒmParams.�����敪 != "9�F�G���["�̏ꍇ�A��O��throw����
            if (!WEB3BankDepositNotifyStatusDef.ERROR.equals(l_bankDepositNotifyParams.getStatus()))
            {
                log.debug("�����ς݂̃��R�[�h�����݂��܂��B������x�������ĉ������B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02839,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����ς݂̃��R�[�h�����݂��܂��B������x�������ĉ������B");
            }

            WEB3GentradeMainAccount l_mainAccount = null;
            try
            {
                //1.5.1.1 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)

                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                if (l_bankDepositNotifyParams.getAccountCode() != null)
                {
                    l_mainAccount =
                        l_accountManager.getMainAccount(
                            l_bankDepositNotifyParams.getInstitutionCode(),
                            l_bankDepositNotifyParams.getBranchCode(),
                            l_bankDepositNotifyParams.getAccountCode());
                }
            }
            catch (WEB3BaseException l_ex)
            {
                //1.5.1.2 ��O������
                //�ڋq�����݃G���[��O�������Ă�
                //�ߑ����邾���łȂɂ����Ȃ��B
                log.debug("�ڋq�����݃G���[��O����", l_ex);
            }

            //1.5.1.3 create�����ʒm����(�����ʒmParams, �ڋq)
            WEB3AioCashinNoticeUnit2 l_cashinNoticeUnit2 =
                this.createCashinNoticeUnit(
                    l_bankDepositNotifyParams,
                    l_mainAccount);

            l_lisCashinNoticeUnit2.add(l_cashinNoticeUnit2);
        }

        WEB3AioCashinNoticeUnit2[] l_cashinNoticeUnits2 =
            new WEB3AioCashinNoticeUnit2[l_lisCashinNoticeUnit2.size()];
        l_lisCashinNoticeUnit2.toArray(l_cashinNoticeUnits2);

        // 1.6 ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminAioCashinNoticeChangeInputResponse l_response =
            (WEB3AdminAioCashinNoticeChangeInputResponse)l_request.createResponse();

        //1.7 (*)�v���p�e�B�Z�b�g
        //�����ʒm���׈ꗗ = ArrayList.toArray()
        l_response.cashinNoticeList = l_cashinNoticeUnits2;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�����ʒm����)<BR>
     * �����ʒm�����m�F�������s���B <BR>
     * <BR>
     * ���V�[�P���X�}�u�i�����ʒm�m�F�ď����jvalidate�����ʒm�����v�Q��<BR>
     * <BR>
     * =============================================== <BR>
     * �@@�@@��̈ʒu : �����ʒmParams.�����敪 != "9�F�G���["�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��O��throw����<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02839<BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3AdminAioCashinNoticeChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4109C8B50240
     */
    protected WEB3AdminAioCashinNoticeChangeConfirmResponse validateCashinNoticeChange(
            WEB3AdminAioCashinNoticeChangeConfirmRequest l_request) throws WEB3BaseException
            {
        final String STR_METHOD_NAME = "validateCashinNoticeChange(" +
        "WEB3AdminAioCashinNoticeChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h = "�����ʒm�ď���"
        //is�X�V = true
        
        l_administrator.validateAuthority(
                WEB3TransactionCategoryDef.BANK_COOPERATION,
                true);
        
        //1.4 StringBuffer( )
        //�G���[������p��
        //StringBuffer�𐶐�����B
        StringBuffer l_strErrorWhere = new StringBuffer();
        
        //1.5 ���N�G�X�g.���������ʒm���ׂ̌�����LOOP
        int l_intCcashinNoticeList = l_request.cashinNoticeList.length;
        for (int i = 0; i < l_intCcashinNoticeList; i++)
        {
            List l_lisSqlValues = new ArrayList();
            StringBuffer l_sbSql = new StringBuffer();

            String l_strCashinNoticeTableId = l_request.cashinNoticeList[i].cashinNoticeTableId;
            boolean l_blnIsEmpty = WEB3StringTypeUtility.isEmpty(l_strCashinNoticeTableId);
            long l_lngBankDepositNotifyId = 0;
            String l_strDataLoadDiv = null;
            if (!l_blnIsEmpty)
            {
                l_lngBankDepositNotifyId =
                    Long.parseLong(l_strCashinNoticeTableId.substring(1));
                l_strDataLoadDiv =
                    l_strCashinNoticeTableId.substring(0, 1);
            }
            l_sbSql.append(" bank_deposit_notify_id = ? ");
            l_lisSqlValues.add(new Long(l_lngBankDepositNotifyId));

            //�Ǘ���.get�،���ЃR�[�h()
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            l_sbSql.append(" and institution_code = ? ");
            l_lisSqlValues.add(l_strInstitutionCode);

            l_sbSql.append(" and data_load_div = ? ");
            l_lisSqlValues.add(l_strDataLoadDiv);

            List l_lisResults = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisResults =
                    l_queryProcessor.doFindAllQuery(
                        BankDepositNotifyRow.TYPE,
                        l_sbSql.toString(),
                        l_lisSqlValues.toArray());
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

            //�����ʒmParams.�����敪 != "9�F�G���["�̏ꍇ�A��O��throw����
            if (l_lisResults != null && !l_lisResults.isEmpty())
            {
                BankDepositNotifyRow l_bankDepositNotifyRow =
                    (BankDepositNotifyRow)l_lisResults.get(0);
                if (!WEB3BankDepositNotifyStatusDef.ERROR.equals(l_bankDepositNotifyRow.getStatus()))
                {
                    log.debug("�����ς݂̃��R�[�h�����݂��܂��B������x�������ĉ������B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02839,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�����ς݂̃��R�[�h�����݂��܂��B������x�������ĉ������B");
                }
            }

            //���N�G�X�g.���������ʒm����.���X�R�[�h
            String l_strBranchCode = l_request.cashinNoticeList[i].branchCode;
            //���N�G�X�g.���������ʒm����.�ڋq�R�[�h
            String l_strAccountCode = l_request.cashinNoticeList[i].accountCode;
            
            try
            {
                //1.5.1 validate���X����(���X�R�[�h : String[])
                //�����F
                //���N�G�X�g.���������ʒm����.���X�R�[�h
                l_administrator.validateBranchPermission(l_strBranchCode);
                
                //1.5.2 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
                //�ڋq���擾����B
                //�����F
                //�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h()
                //���X�R�[�h = ���N�G�X�g.���������ʒm����.���X�R�[�h
                //�����R�[�h = ���N�G�X�g.���������ʒm����.�ڋq�R�[�h
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                l_accountManager.getMainAccount(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
            }
            catch (WEB3BaseException l_ex)
            {
                //1.5.3 ��O������
                //�G���[�ڋq�������ǉ�����B
                //"["���N�G�X�g.���������ʒm����.���X�R�[�h, ���N�G�X�g.���������ʒm����.�ڋq�R�[�h"]�@@"
                //�f�[�^��
                //[307 111111][307 222222][308 111111]
                //1.5.3.1 append(arg0 : String)
                l_strErrorWhere.append("[");
                l_strErrorWhere.append(l_strBranchCode);
                l_strErrorWhere.append(" ");
                l_strErrorWhere.append(l_strAccountCode);
                l_strErrorWhere.append("]");
            }
        }
        
        //1.6 StringBuffer.length() > 0�̏ꍇ
        //�ڋq�����݃G���[��O
        //�𐶐����X���[����B
        //�����F
        //�G���[��� = �ڋq�����݃G���[
        //�G���[���\�b�h = "validate�����ʒm����"
        //�G���[���b�Z�[�W = �G���[�ڋq������
        if (l_strErrorWhere.length() > 0)
        {
            //1.6.1 WEB3BusinessLayerException(
            //  l_errorInfo : ErrorInfo, l_errorMethod : String, l_errorMessage : String)
            log.debug("�ڋq�����݃G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorWhere.toString());
        }
        
        //1.7 createResponse( )
        WEB3AdminAioCashinNoticeChangeConfirmResponse l_response =
            (WEB3AdminAioCashinNoticeChangeConfirmResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
            }
    
    /**
     * (submit�����ʒm����)<BR>
     * �����ʒm���������������s���B <BR>
     * <BR>
     * ���V�[�P���X�}�u�i�����ʒm�m�F�ď����jsubmit�����ʒm�����v �Q�� <BR>
     * <BR>
     * =============================================== <BR>
     * �@@�@@��̈ʒu : �����ʒmParams.�����敪 != "9�F�G���["�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��O��throw����<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02839<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3AdminAioCashinNoticeChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4109C8B50240
     */
    protected WEB3AdminAioCashinNoticeChangeCompleteResponse submitCashinNoticeChange(
            WEB3AdminAioCashinNoticeChangeCompleteRequest l_request) throws WEB3BaseException
            {
        final String STR_METHOD_NAME = "submitCashinNoticeChange(" +
        "WEB3AdminAioCashinNoticeChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h = "�����ʒm�ď���"
        //is�X�V = true
        l_administrator.validateAuthority(
                WEB3TransactionCategoryDef.BANK_COOPERATION,
                true);
        
        //1.4 ArrayList( )
        ArrayList l_lisCcashinNoticeList = new ArrayList();
        
        //1.5 HashTable( )
        Hashtable l_tblMainAccount = new Hashtable();
        
        //1.6 StringBuffer( )
        StringBuffer l_strErrorWhere = new StringBuffer();
        
        //1.7 ���N�G�X�g.���������ʒm���ׂ̌�����LOOP
        int l_intCcashinNoticeList = l_request.cashinNoticeList.length;
        
        MainAccount  l_mainAccount =  null;
        for (int i = 0; i < l_intCcashinNoticeList; i++)
        {
            List l_lisSqlValues = new ArrayList();
            StringBuffer l_sbSql = new StringBuffer();

            String l_strCashinNoticeTableId = l_request.cashinNoticeList[i].cashinNoticeTableId;
            boolean l_blnIsEmpty = WEB3StringTypeUtility.isEmpty(l_strCashinNoticeTableId);
            long l_lngBankDepositNotifyId = 0;
            String l_strDataLoadDiv = null;
            if (!l_blnIsEmpty)
            {
                l_lngBankDepositNotifyId =
                    Long.parseLong(l_strCashinNoticeTableId.substring(1));
                l_strDataLoadDiv =
                    l_strCashinNoticeTableId.substring(0, 1);
            }
            l_sbSql.append(" bank_deposit_notify_id = ? ");
            l_lisSqlValues.add(new Long(l_lngBankDepositNotifyId));

            //�Ǘ���.get�،���ЃR�[�h()
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            l_sbSql.append(" and institution_code = ? ");
            l_lisSqlValues.add(l_strInstitutionCode);

            l_sbSql.append(" and data_load_div = ? ");
            l_lisSqlValues.add(l_strDataLoadDiv);

            List l_lisResults = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisResults =
                    l_queryProcessor.doFindAllQuery(
                        BankDepositNotifyRow.TYPE,
                        l_sbSql.toString(),
                        l_lisSqlValues.toArray());
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

            //�����ʒmParams.�����敪 != "9�F�G���["�̏ꍇ�A��O��throw����
            if (l_lisResults != null && !l_lisResults.isEmpty())
            {
                BankDepositNotifyRow l_bankDepositNotifyRow =
                    (BankDepositNotifyRow)l_lisResults.get(0);
                if (!WEB3BankDepositNotifyStatusDef.ERROR.equals(l_bankDepositNotifyRow.getStatus()))
                {
                    log.debug("�����ς݂̃��R�[�h�����݂��܂��B������x�������ĉ������B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02839,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�����ς݂̃��R�[�h�����݂��܂��B������x�������ĉ������B");
                }
            }

            //���N�G�X�g.���������ʒm����.���X�R�[�h
            String l_strBranchCode = l_request.cashinNoticeList[i].branchCode;
            //���N�G�X�g.���������ʒm����.�ڋq�R�[�h
            String l_strAccountCode = l_request.cashinNoticeList[i].accountCode;
            try
            {
                //1.7.1 validate���X����(���X�R�[�h : String[])
                //�����F
                //���N�G�X�g.���������ʒm����.���X�R�[�h
                l_administrator.validateBranchPermission(l_strBranchCode);
                
                //1.7.2 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                l_mainAccount = l_accountManager.getMainAccount(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
                
                //1.7.3 add(arg0 : Object)
                //ArrayList��
                //���������ʒm���ׂ�add����B
                l_lisCcashinNoticeList.add(l_request.cashinNoticeList[i]);
                
                //1.7.4 put(arg0 : Object, arg1 : Object)
                l_tblMainAccount.put(l_request.cashinNoticeList[i].cashinNoticeTableId, l_mainAccount);
            }
            catch (WEB3BaseException l_ex)
            {
                //1.7.5 ��O������
                //�G���[�ڋq�������ǉ�����B
                //"["���N�G�X�g.���������ʒm����.���X�R�[�h,���N�G�X�g.���������ʒm����.�ڋq�R�[�h"]�@@"
                //�f�[�^��
                //[307 111111][307 222222][308 111111]
                //1.7.5.1 append(arg0 : String)
                l_strErrorWhere.append("[");
                l_strErrorWhere.append(l_strBranchCode);
                l_strErrorWhere.append(" ");
                l_strErrorWhere.append(l_strAccountCode);
                l_strErrorWhere.append("]");
            }
        }
        
        //1.7.5.2 StringBuffer.length() > 0�̏ꍇ
        //�ڋq�����݃G���[��O
        //�𐶐����X���[����B
        //�����F
        //�G���[��� = �ڋq�����݃G���[
        //�G���[���\�b�h = "submit�����ʒm����"
        //�G���[���b�Z�[�W = �G���[�ڋq������
        if (l_strErrorWhere.length() > 0)
        {
            //1.8.1 WEB3BusinessLayerException(
            //  l_errorInfo : ErrorInfo, l_errorMethod : String, l_errorMessage : String)
            log.debug("�ڋq�����݃G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorWhere.toString());
        }
        
        //1.8 validate����p�X���[�h(�p�X���[�h : String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //1.9 ArrayList�̌�����LOOP
        WEB3AioCashinNoticeUnit2[] l_cashinNoticeList =
            new WEB3AioCashinNoticeUnit2[l_lisCcashinNoticeList.size()];
        l_lisCcashinNoticeList.toArray(l_cashinNoticeList);
        int l_intSize = l_cashinNoticeList.length;
        for (int i = 0; i < l_intSize; i++)
        {
            //�Ǘ���.get�Ǘ��҃R�[�h()
            String l_strAdministratorCode = l_administrator.getAdministratorCode();
            //���������ʒm����.�����ʒmID
            String l_strCashinNoticeTableId = l_cashinNoticeList[i].cashinNoticeTableId;

            // �،���ЃR�[�h���擾����B
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            //(��s�����ʒm�e�[�u��ID = ���N�G�X�g.�����ʒmID[index]��2���ځ`�Ō�
            long l_lngBankDepositNotifyId = Long.parseLong(l_strCashinNoticeTableId.substring(1));

            //�@@AND �f�[�^�捞�敪 = ���N�G�X�g.�����ʒmID[index]��1����)
            String l_strDataLoadDiv = l_strCashinNoticeTableId.substring(0 ,1);

            //���N�G�X�g.���l
            String l_strRemark = l_cashinNoticeList[i].remark;
            
            //1.9.1 get(arg0 : Object)
            //Hashtable����ڋq�I�u�W�F�N�g���擾�B
            //���� ArrayList����擾�������������ʒm����.�����ʒmID
            l_mainAccount = (MainAccount)l_tblMainAccount.get(l_cashinNoticeList[i].cashinNoticeTableId);
            
            //1.9.2 doUpdateQuery(arg0 : String, arg1 : PrimaryKey, arg2 : Map)
            //�����ʒm�e�[�u����
            //ArrayList����擾����
            //���������ʒm����.�����ʒm�e�[�u��ID��
            //�ȉ��̂Ƃ���Ɏw�肵�X�V�B
            //[����]
            //��s�����ʒm�e�[�u��ID = ���������ʒm����.�����ʒm�e�[�u��ID��2���ځ`�Ō�
            //�،���ЃR�[�h = �Ǘ���.get�،���ЃR�[�h()
            //�f�[�^�捞�敪 = ���������ʒm����.�����ʒm�e�[�u��ID��1����
            //[�ύX���e]
            //���X�R�[�h = �ڋq.���X�R�[�h
            //�ڋq�R�[�h = �ڋq.�ڋq�R�[�h
            //���l = ���N�G�X�g.���l
            //�����敪 = ������
            //�X�V�� = �Ǘ���.get�Ǘ��҃R�[�h()
            //�X�V���� = �V�X�e�����t
            Map l_mapSpac = new HashMap();
            l_mapSpac.put("branch_code", l_mainAccount.getBranch().getBranchCode());
            l_mapSpac.put("account_code", l_mainAccount.getAccountCode());
            l_mapSpac.put("remark", l_strRemark);
            l_mapSpac.put("status", WEB3StatusDef.NOT_DEAL);
            l_mapSpac.put("update_person", l_strAdministratorCode);
            l_mapSpac.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                BankDepositNotifyPK l_bankDepositNotifypk =
                    new BankDepositNotifyPK(
                        l_lngBankDepositNotifyId,
                        l_strInstitutionCode,
                        l_strDataLoadDiv);
                l_queryProcessor.doUpdateQuery(
                    l_bankDepositNotifypk,
                    l_mapSpac);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        
        //1.10 createResponse( )
        WEB3AdminAioCashinNoticeChangeCompleteResponse l_response =
            (WEB3AdminAioCashinNoticeChangeCompleteResponse) l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
            }
    
    /**
     * (create��������)<BR>
     * �����ʒm�e�[�u���̌���������������쐬���ԋp����B<BR>
     * <BR>
     * �P�j�،���ЃR�[�h�������ɒǉ�����<BR>
     * <BR>
     * �Q�j�ȉ��𕔓X�R�[�h�̏����Ƃ���<BR>
     * �Q�|�P�j���N�G�X�g.���X�R�[�h<BR>
     * �Q�|�Q�j���X�R�[�h  is null���Q�|�P�j��or �����Œǉ�����B<BR>
     * <BR>
     * �R�j�ȉ��̏�����null�łȂ��ꍇ<BR>
     * ���N�G�X�g.�ڋq�R�[�hand�����ɂĒǉ�����B<BR>
     * ���N�G�X�g.�U���˗��l�R�[�h<BR>
     * ���N�G�X�g.�����<BR>
     * <BR>
     * �S�j��������(FROM)��null�łȂ�<BR>
     * �������� <= �X�V�����@@�ꍇ<BR>
     * ��and�����ɂĒǉ�����B<BR>
     * <BR>
     * �T�j��������(TO)��null�łȂ���<BR>
     * �X�V���� <= ��������(TO)��<BR>
     * ��and�����ɂĒǉ�����B<BR>
     * <BR>
     * �U�j�ʉ݃R�[�h�� �h�S�āh �ȊO�̏ꍇ�A<BR> 
     * ���N�G�X�g.�ʉ݃R�[�h��null�̏ꍇ�A�ʉ݃R�[�h is null <BR>
     * ���N�G�X�g.�ʉ݃R�[�h != null�̏ꍇ�A�ʉ݃R�[�h = ���N�G�X�g.�ʉ݃R�[�h <BR>
     * ��and�����ɂĒǉ�����B <BR>
     * <BR>
     * �V�j�쐬�����������ԋp����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4109C8B50240
     */
    protected String createQueryCondition(WEB3AdminAioCashinNoticeSearchRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryCondition(" +
            "WEB3AdminAioCashinNoticeSearchRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        StringBuffer l_strSearchCondition = new StringBuffer();
        
        //�P�j�،���ЃR�[�h�������ɒǉ�����
        l_strSearchCondition.append("institution_code = ? ");
        
        //�Q�j�ȉ��𕔓X�R�[�h�̏����Ƃ���
        //�Q�|�P�j���N�G�X�g.���X�R�[�h��z�񌏐���in�����ɓW�J����B
        //�Q�|�Q�j���X�R�[�h  is null��  �Q�|�P�j��or �����Œǉ�����B
        l_strSearchCondition.append( " and (branch_code is null or branch_code in ( ? ");
        
        for (int i = 1; i < l_request.branchCode.length; i++)
        {
            l_strSearchCondition.append(" , ? ");
        }
        
        l_strSearchCondition.append( " )) ");
        
        
        // �R�j�ȉ��̏�����null�łȂ��ꍇand�����ɂĒǉ�����B
        //���N�G�X�g.�ڋq�R�[�h
        //���N�G�X�g.�U���˗��l�R�[�h
        //���N�G�X�g.�����
        if (l_request.accountCode != null)
        {
            l_strSearchCondition.append(" and account_code like ? ");
        }
        if (l_request.clientCode != null)
        {
            l_strSearchCondition.append(" and deposit_data_trans_person_code = ? ");
        }
        if (l_request.settlementDate != null)
        {
            l_strSearchCondition.append(" and deposit_data_account_date = ? ");
        }
        
        // �S�j��������(FROM)��null�łȂ��ꍇ
        // �������� <= �X�V����
        // ��and�����ɂĒǉ�����B
        if (l_request.transactionDateFrom != null)
        {
            l_strSearchCondition.append(" and last_updated_timestamp >= ? ");
        }
        
        // �T�j��������(TO)��null�łȂ��ꍇ
        // �X�V���� <= ��������(TO)
        // ��and�����ɂĒǉ�����B
        if (l_request.transactionDateTo != null)
        {
            l_strSearchCondition.append(" and last_updated_timestamp <= ? ");
        }
        
        // �U�j�ʉ݃R�[�h�� �h�S�āh �ȊO�̏ꍇ
        // ���N�G�X�g.�ʉ݃R�[�h��null�̏ꍇ�A�ʉ݃R�[�h is null
        // ���N�G�X�g.�ʉ݃R�[�h != null�̏ꍇ�A�ʉ݃R�[�h = ���N�G�X�g.�ʉ݃R�[�h 
        if (!WEB3AioCurrencyCodeDef.ALL.equals(l_request.currencyCode))
        {
            //���N�G�X�g.�ʉ݃R�[�h��null�̏ꍇ�A�ʉ݃R�[�h is null
            if (l_request.currencyCode == null)
            {
                l_strSearchCondition.append(" and currency_code is null ");
                
            //���N�G�X�g.�ʉ݃R�[�h != null�̏ꍇ�A�ʉ݃R�[�h = ���N�G�X�g.�ʉ݃R�[�h 
            }
            else
            {
                l_strSearchCondition.append(" and currency_code = ? ");
            }
        }
        log.exiting(STR_METHOD_NAME);
        
        //�V�j�쐬�����������ԋp����B
        return l_strSearchCondition.toString();
    }
    
    /**
     * (create�\�[�g�L�[)<BR>
     * �����ʒm�e�[�u���̃\�[�g����������쐬���ԋp����B<BR>
     * <BR>
     * �P�j���N�G�X�g.�����ʒm�\�[�g�L�[�̔z�񌏐���LOOP����B<BR>
     * <BR>
     * �P�|�P�j�����ʒm�\�[�g�L�[.createSortKeySpec()��null�łȂ��ꍇ<BR>
     * <BR>
     * �\�[�g�L�[������ = �����ʒm���׃\�[�g.createSortKeySpec()�̖߂�l<BR>
     * <BR>
     * �P�|�Q�j������0���ȏ�̏ꍇ�@@<BR>
     * �\�[�g�L�[��������J���}�łȂ��B<BR>
     * <BR>
     * �Q�j�������ԋp����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4109C8B50240
     */
    protected String createSortKey(WEB3AdminAioCashinNoticeSearchRequest l_request)
    {
        final String STR_METHOD_NAME = "createSortKey(" +
        "WEB3AdminAioCashinNoticeSearchRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strSortKey = "";
        
        if (l_request.sortKeys.length > 0)
        {
            //�P�j���N�G�X�g.�����ʒm�\�[�g�L�[�̔z�񌏐���LOOP����B
            for (int i = 0; i < l_request.sortKeys.length; i++)
            {
                // �P�|�P�j�����ʒm�\�[�g�L�[.createSortKeySpec()��null�łȂ��ꍇ
                //�\�[�g�L�[������ = �����ʒm���׃\�[�g.createSortKeySpec()�̖߂�l
                if (l_request.sortKeys[i].createSortKeySpec() != null)
                {
                    l_strSortKey = l_strSortKey +
                    l_request.sortKeys[i].createSortKeySpec() + " ,";
                }
            }
            // �P�|�Q�j������0���ȏ�̏ꍇ
            //�@@�\�[�g�L�[�������" ,"�łȂ��B
            l_strSortKey = l_strSortKey.substring(0, l_strSortKey.length() - 1);
        }
        log.exiting(STR_METHOD_NAME);
        return l_strSortKey;
    }
    
    /**
     * (create�����ʒm����)<BR>
     * �����ʒm���ׂ��쐬��<BR>
     * �ȉ��̂悤�ɃZ�b�g����B<BR>
     * <BR>
     * �����ʒmID = �����ʒmParams.�f�[�^�捞�敪 + �����ʒmParams.�����ʒmID<BR>
     * <BR>
     * (*)����.�ڋq != null�̏ꍇ�̂݁A�ڋq��null�̏ꍇ��null<BR>
     * ���X�R�[�h = �ڋq.get���X�R�[�h()�̖߂�l(*)<BR>
     * �ڋq�R�[�h = �ڋq.get�\���ڋq�R�[�h()�̖߂�l(*)<BR>
     * ��s�� = ��p�U�������Params.��s��(*)<BR>
     * �x�X�� = ��p�U�������Params.�x�X��(*)<BR>
     * �ڋq�� = �ڋq.���O�i�c��1�j (*)<BR>
     * <BR>
     * �U���˗��l�R�[�h = �����ʒmParams.�U���˗��l�R�[�h<BR>
     * �U���˗��l�� = �����ʒmParams.�U���˗��l��<BR>
     * ���z = �����ʒmParams.���z<BR>
     * ����� = �����ʒmParams.������𐼗���t�ɕϊ������l<BR> 
     * (�@@�N��.getJapEraDiv(�����ʒmParams.�����)�ɂĔN�����擾����<BR>
     * �A�N��.toDate(�N��(=�擾�����N��)�C<BR>
     * �@@�@@�a�����(=�����ʒmParams.�����))�̖߂�l)<BR>
     * <BR>
     * �U����s�� = �����ʒmParams.�U����s��<BR>
     * �U���x�X�� = �����ʒmParams.�U���x�X��<BR>
     * <BR>
     * �������� = �����ʒmParams.�X�V����<BR>
     * �����敪 = �����ʒmParams.�����敪<BR>
     * <BR>
     * ���l = �����ʒmParams.���l(null�̏ꍇ,�����ʒmParams.�G���[�R�����g���Z�b�g�j<BR>
     * <BR>
     * �����敪 = �����ʒmParams.�����敪<BR>
     * ����敪 = �����ʒmParams.����敪<BR>
     * <BR>
     * �ʉ݃R�[�h. = �����ʒmParams.�ʉ݃R�[�h<BR>
     * @@param l_bankDepositNotifyParams - (�����ʒmParams)
     * @@param l_mainAccount -(�ڋq)
     * @@return WEB3AioCashinNoticeUnit2
     * @@roseuid 4109C8B50240
     */
    protected WEB3AioCashinNoticeUnit2 createCashinNoticeUnit(
        BankDepositNotifyParams l_bankDepositNotifyParams,
        WEB3GentradeMainAccount l_mainAccount)
    {
        final String STR_METHOD_NAME = "createCashinNoticeUnit(" +
        "BankDepositNotifyParams, WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bankDepositNotifyParams == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AioCashinNoticeUnit2 l_aioCashinNoticeUnit2 =
            new WEB3AioCashinNoticeUnit2();

        //�����ʒmID = �����ʒmParams.�f�[�^�捞�敪 + �����ʒmParams.�����ʒmID
        l_aioCashinNoticeUnit2.cashinNoticeTableId =
            l_bankDepositNotifyParams.getDataLoadDiv()
            + l_bankDepositNotifyParams.getBankDepositNotifyId() + "";

        //���X�R�[�h = �ڋq.get���X�R�[�h()�̖߂�l(*)
        //�ڋq�R�[�h = �ڋq.get�\���ڋq�R�[�h()�̖߂�l(*)
        //�ڋq�� = �ڋq.���O�i�c��1�j (*)
        //��s�� = ��p�U�������.��s�� (*)
        //�x�X�� = ��p�U�������.�x�X�� (*)
        // (*)����.�ڋq != null�̏ꍇ�̂݁A�ڋq��null�̏ꍇ��null
        if (l_mainAccount != null)
        {
            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_mainAccount.getDataSourceObject();
            //���X�R�[�h = �ڋq.get���X�R�[�h()�̖߂�l
            l_aioCashinNoticeUnit2.branchCode =  l_mainAccountRow.getBranchCode();
            // �ڋq�R�[�h = �ڋq.get�\���ڋq�R�[�h()�̖߂�l
            l_aioCashinNoticeUnit2.accountCode =  l_mainAccount.getDisplayAccountCode();
            // �ڋq�� = �ڋq.���O�i�c��1�j
            l_aioCashinNoticeUnit2.accountName =
                l_mainAccountRow.getFamilyNameAlt1();
            
            try
            {
                //��p�U��������e�[�u����茟���B
                ExclusiveUseAccountRow l_exUseAccountRow = ExclusiveUseAccountDao.findRowByPk(l_mainAccountRow.getAccountId());
                
                //��s�� = ��p�U�������.��s�� (*)
                l_aioCashinNoticeUnit2.financialInstitutionName =
                    l_exUseAccountRow.getFinInstitutionName();
                
                //�x�X�� = ��p�U�������.�x�X�� (*)
                l_aioCashinNoticeUnit2.financialBranchName =
                    l_exUseAccountRow.getFinBranchName();
            }   
            //��O��������catch���U��������s���A�U��������x�X���̒l��null�̂܂܂Ƃ���B
            catch (DataQueryException l_ex)
            {                
                log.debug("__DataQueryException__(��p�U������):::::�G���[�������������ʒm���R�[�h=" 
                        + l_bankDepositNotifyParams, l_ex);                
            }
            catch (DataNetworkException l_ex)
            {
                log.debug("__DataNetworkException__(��p�U������):::::�G���[�������������ʒm���R�[�h=" 
                        + l_bankDepositNotifyParams, l_ex);                
            }            
        }
        else
        {
            // ���X�R�[�h = �����ʒmParams.���X�R�[�h(null)
            l_aioCashinNoticeUnit2.branchCode =  l_bankDepositNotifyParams.getBranchCode();
            // �ڋq�R�[�h= �����ʒmParams.�ڋq�R�[�h(null)
            String l_strAccountCode = l_bankDepositNotifyParams.getAccountCode();
            if(l_strAccountCode != null)
            {
                l_aioCashinNoticeUnit2.accountCode = l_strAccountCode.substring(0,6);                
            }
            
        }
        
        try
        {
            // �U���˗��l�R�[�h = �����ʒmParams.�U���˗��l�R�[�h
            l_aioCashinNoticeUnit2.clientCode = l_bankDepositNotifyParams.getDepositDataTransPersonCode();
            
            // ���z =  �����ʒmParams.���z            
            double l_dblPrice = Double.parseDouble(l_bankDepositNotifyParams.getDepositDataDepositAmount());
            l_aioCashinNoticeUnit2.price = WEB3StringTypeUtility.formatNumber(l_dblPrice);
            
        }
        catch(NumberFormatException l_nfe)
        {
            log.debug("__NumberFormatException__(��p�U������):::::�G���[�������������ʒm���R�[�h="
                    + l_bankDepositNotifyParams, l_nfe);                                      
        }
        
        // �U���˗��l�� = �����ʒmParams.�U���˗��l��
        l_aioCashinNoticeUnit2.clientName =
            l_bankDepositNotifyParams.getDepositDataTransPersonName();
        
        //����� = �����ʒmParams.������𐼗���t�ɕϊ������l
        //(�@@�N��.getJapEraDiv(�����ʒmParams.�����)�ɂĔN�����擾����
        //�A�N��.toDate(�N��(=�擾�����N��)�C
        //  �a�����(=�����ʒmParams.�����))�̖߂�l)
        String l_strDepositDataAccountDate =
            l_bankDepositNotifyParams.getDepositDataAccountDate();
        l_aioCashinNoticeUnit2.settlementDate =
            WEB3GentradeEra.toDate(
                WEB3GentradeEra.getJapEraDiv(l_strDepositDataAccountDate),
                l_strDepositDataAccountDate);
                
        // ��s�R�[�h = �����ʒmParams.��s�R�[�h
        l_aioCashinNoticeUnit2.financialInstitutionCode =
            l_bankDepositNotifyParams.getBankCode();
        
        // �x�X�R�[�h = �����ʒmParams.�x�X�R�[�h
        l_aioCashinNoticeUnit2.financialBranchCode =
            l_bankDepositNotifyParams.getBankBranchCode();
        
        // �U����s�� = �����ʒmParams.�d����s��
        l_aioCashinNoticeUnit2.transferFinancialInstitutionName =
            l_bankDepositNotifyParams.getDeliveredBankName();
        
        // �U���x�X�� = �����ʒmParams.��s�x�X��
        l_aioCashinNoticeUnit2.transferFinancialBranchName =
            l_bankDepositNotifyParams.getDeliveredBankBranchName();
        
        // �������� = �����ʒmParams.�X�V����
        l_aioCashinNoticeUnit2.transactionDate =
            l_bankDepositNotifyParams.getLastUpdatedTimestamp();
        
        // �����敪 = �����ʒmParams.�����敪
        l_aioCashinNoticeUnit2.transactionDiv = l_bankDepositNotifyParams.getStatus();
        
        // ���l = �����ʒmParams.���l
        //���l��null�̏ꍇ�G���[�R�����g���l�߂�
        String l_strRemark = l_bankDepositNotifyParams.getRemark();
        if(l_strRemark == null)
        {
            l_aioCashinNoticeUnit2.remark = l_bankDepositNotifyParams.getDepositErrorComment();
        }
        else
        {
            l_aioCashinNoticeUnit2.remark = l_strRemark;
        }
        
        // �����敪 = �����ʒmParams�����敪
        l_aioCashinNoticeUnit2.cashinoutDiv = l_bankDepositNotifyParams.getCashTransferDiv();
        
        // ����敪 = �����ʒmParams����敪
        l_aioCashinNoticeUnit2.ioTradingType = l_bankDepositNotifyParams.getTradeDiv();
           
        // �ʉ݃R�[�h. = �����ʒmParams.�ʉ݃R�[�h 
        l_aioCashinNoticeUnit2.currencyCode = l_bankDepositNotifyParams.getCurrencyCode();
        
        log.exiting(STR_METHOD_NAME);
        return l_aioCashinNoticeUnit2;
        
    }
    
    /**
     * (create�����l)<BR>
     * ���������Œǉ������ɑΉ�����悤��ArrayList�ɏ��Ԃɒǉ����č쐬���Ă��������B�@@<BR>
     * <BR>
     * �P�j����:�،���ЃR�[�h <BR>
     * �Q�j���N�G�X�g.���X�R�[�h�̔z�񌏐����@@���N�G�X�g.���X�R�[�h[]�@@ <BR>
     * �R�j���N�G�X�g.�ڋq�R�[�h��null�o�Ȃ��ꍇ�A���N�G�X�g.�ڋq�R�[�h<BR>
     * �S�j���N�G�X�g.�U���˗��l�R�[�h��null�o�Ȃ��ꍇ�A���N�G�X�g.�U���˗��l�R�[�h<BR>
     * �T�j���N�G�X�g.�������null�łȂ��ꍇ�A<BR>
     * �@@�@@�N��.toJapDate(���N�G�X�g.�����)�ɂĕϊ������a����� <BR>
     * ��toJapDate()�̖߂�l��null�̏ꍇ�A��O�u������̎w�肪����������܂���v��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@: BUSINESS_ERROR_03153<BR>
     * �U�j���N�G�X�g.��������FROM��null�o�Ȃ��ꍇ�A���N�G�X�g.��������FROM<BR>
     * �V�j���N�G�X�g.��������TO��null�o�Ȃ��ꍇ�A���N�G�X�g.��������TO<BR>
     * �W�j���N�G�X�g.�ʉ݃R�[�h���h�S�āh�ȊO�̏ꍇ�A <BR>
     * ���N�G�X�g.�ʉ݃R�[�h��null�̏ꍇ�A�ʉ݃R�[�h is null <BR>
     * ���N�G�X�g.�ʉ݃R�[�h != null�̏ꍇ�A���N�G�X�g.�ʉ݃R�[�h <BR>
     * <BR>
     * ArrayList.toArray()�̖߂�l�������l�Ƃ��ĕԋp����B<BR>
     * @@param l_request - (���N�G�X�g)
     * @@param l_strInstitutionCode - (�،���ЃR�[�h) 
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 4109C8B50240
     */
    protected Object[] createQueryContainer(
        WEB3AdminAioCashinNoticeSearchRequest l_request, 
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryContainer( " +
            "WEB3AdminAioCashinNoticeSearchRequest )";
        log.entering(STR_METHOD_NAME);
        
        List l_lisValue = new ArrayList();
        
        //�P�j����:�،���ЃR�[�h
        l_lisValue.add(l_strInstitutionCode); 
        
        //�Q�j���N�G�X�g.���X�R�[�h�̔z�񌏐����@@���N�G�X�g.���X�R�[�h[]
        for (int i = 0; i < l_request.branchCode.length; i++)
        {
            l_lisValue.add(l_request.branchCode[i]);
        }
        //�R�j���N�G�X�g.�ڋq�R�[�h��null�o�Ȃ��ꍇ�A���N�G�X�g.�ڋq�R�[�h
        if (l_request.accountCode != null)
        {
            l_lisValue.add( l_request.accountCode + "%");
        }
        //�S�j���N�G�X�g.�U���˗��l�R�[�h��null�o�Ȃ��ꍇ�A���N�G�X�g.�U���˗��l�R�[�h
        if (l_request.clientCode != null)
        {
            l_lisValue.add(l_request.clientCode);
        }
        //�T�j���N�G�X�g.�������null�o�Ȃ��ꍇ�A���N�G�X�g.�����
        if (l_request.settlementDate != null)
        {
            //�N��.toJapDate(���N�G�X�g.�����)�ɂĕϊ������a�����
            String[] l_strSettlementDates =
                WEB3GentradeEra.toJapDate(l_request.settlementDate);
            //��toJapDate()�̖߂�l��null�̏ꍇ�A��O�u������̎w�肪����������܂���v��throw����B
            if (l_strSettlementDates == null)
            {
                log.debug("������̎w�肪����������܂���");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03153,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "������̎w�肪����������܂���");
            }

            l_lisValue.add(l_strSettlementDates[1]);
        }
        //�U�j���N�G�X�g.��������FROM��null�o�Ȃ��ꍇ�A���N�G�X�g.��������FROM
        if (l_request.transactionDateFrom != null)
        {
            l_lisValue.add( l_request.transactionDateFrom);
        }
        //�V�j���N�G�X�g.��������TO��null�o�Ȃ��ꍇ�A���N�G�X�g.��������TO
        if (l_request.transactionDateTo != null)
        {
            l_lisValue.add(l_request.transactionDateTo);
        }
        
        //�W�j���N�G�X�g.�ʉ݃R�[�h���h�S�āh�ȊO�̏ꍇ�A 
        // ���N�G�X�g.�ʉ݃R�[�h��null�̏ꍇ�A�ʉ݃R�[�h is null 
        // ���N�G�X�g.�ʉ݃R�[�h != null�̏ꍇ�A���N�G�X�g.�ʉ݃R�[�h 
        if (!WEB3AioCurrencyCodeDef.ALL.equals(l_request.currencyCode))
        {
            //���N�G�X�g.�ʉ݃R�[�h��null�̏ꍇ�A�ʉ݃R�[�h is null 
            //�N�G�X�g.�ʉ݃R�[�h != null�̏ꍇ�A���N�G�X�g.�ʉ݃R�[�h
            if (l_request.currencyCode != null)
            {
                l_lisValue.add(l_request.currencyCode);             
            } 
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisValue.toArray();
    }

    /**
     * (create�T�}�����)<BR>
     * �T�}�����𐶐�����B<BR>
     * <BR>
     * �ڍׂ́A�V�[�P���X�u�i�����ʒm�m�F�ď����jcreate�T�}�����v�Q��<BR>
     * @@param l_strCurrencyCodes - (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     * @@param l_lisCashinNoticeRecord - (�����ʒm���R�[�hList)<BR>
     * �����ʒm���R�[�hList<BR>
     * @@param l_response - (���X�|���X�f�[�^)<BR>
     * ���X�|���X�f�[�^<BR>
     * @@return WEB3AdminAioCashinNoticeSearchResponse
     * @@roseuid 44D6C275009B
     */
    protected WEB3AdminAioCashinNoticeSearchResponse createSummaryInfo(
        String[] l_strCurrencyCodes, 
        List l_lisCashinNoticeRecord, 
        WEB3AdminAioCashinNoticeSearchResponse l_response) 
    {
        final String STR_METHOD_NAME = "createSummaryInfo" +
            "(String[], List, WEB3AdminAioCashinNoticeSearchResponse)";
        log.entering(STR_METHOD_NAME);
        
        if (l_response == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 HashMap�I�u�W�F�N�g�̐���
        HashMap l_map = new HashMap();
        
        int l_intCurrencyCodeSize = 0;
        if (l_strCurrencyCodes != null)
        {
            l_intCurrencyCodeSize = l_strCurrencyCodes.length;
        }

        //1.2�ʉ݃R�[�h�z��̗v�f���ALoop�������s���B
        for (int i = 0; i < l_intCurrencyCodeSize; i++)
        {
            //1.2.1�O�݃T�}�����(String)(
            WEB3ForeignSummaryInfo l_foreignSummaryInfo = new WEB3ForeignSummaryInfo(
                l_strCurrencyCodes[i]);
            
            //1.2.2HashMap�I�u�W�F�N�g�ɒl���Z�b�g����B
            //[�Z�b�g������e]
            //key = �ʉ݃R�[�h�z��̗v�f
            //value = �O�݃T�}�����I�u�W�F�N�g
            l_map.put(l_strCurrencyCodes[i], l_foreignSummaryInfo);

        }

        int l_listSize = 0;
        if (l_lisCashinNoticeRecord != null)
        {
            l_listSize = l_lisCashinNoticeRecord.size();
        }
        //1.3�����ʒm���R�[�h�̗v�f���ALoop�������s���B
        for (int i = 0; i < l_listSize; i++)
        {   
            BankDepositNotifyRow l_bankDepositNotifyRow =
                (BankDepositNotifyRow)l_lisCashinNoticeRecord.get(i);

            BankDepositNotifyParams l_bankDepositNotifyParams =
                new BankDepositNotifyParams(l_bankDepositNotifyRow);
            //1.3.1�����ʒm���R�[�h�̗v�f.�ʉ݃R�[�h��null�̏ꍇ�A�ȉ��̏��������s
            if (l_bankDepositNotifyParams.getCurrencyCode() == null)
            {
                //�~�݂̃T�}���v�Z�������s���B
                //1.3.1.1[����] 
                //�����ʒmParams�F �����ʒm���R�[�h�̗v�f 
                //���X�|���X�f�[�^�F�@@�����ʒm�������X�|���X
                this.calcJapaneseCurrencyInfo(l_bankDepositNotifyParams,
                    l_response);

            //1.3.2�����ʒm���R�[�h�̗v�f.�ʉ݃R�[�h  != null�̏ꍇ�A�ȉ��̏��������s
            } 
            else
            {
                //1.3.2.1HashMap�I�u�W�F�N�g.get(�����ʒm���R�[�h�̗v�f.�ʉ݃R�[�h)�ŁA
                //�O�݃T�}�����I�u�W�F�N�g���擾
                String l_strCurrencyCode = l_bankDepositNotifyParams.getCurrencyCode();
                WEB3ForeignSummaryInfo l_forSumInfo = (WEB3ForeignSummaryInfo)
                    l_map.get(l_strCurrencyCode);
                
                //1.3.2.2�擾�����O�݃T�}�����I�u�W�F�N�g != null �̏ꍇ�A�ȉ��̏��������s
                if (l_forSumInfo != null)
                {
                    //1.3.2.2.1�O�݂̃T�}�������v�Z����B 
                    this.calcForeignCurrencyInfo(l_forSumInfo, l_bankDepositNotifyParams);
                }
                
            }
            
        }
        //1.4���X�|���X.�O�݃T�}�����ꗗ =
        //HashMap�I�u�W�F�N�g.values().toArray()
        WEB3ForeignSummaryInfo[] l_foreignSum = new WEB3ForeignSummaryInfo[l_map.size()];
        l_map.values().toArray(l_foreignSum);
        l_response.foreignSummaryList = l_foreignSum;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * (calc�~�ݏ��)<BR>
     * �~�݂̃T�}���v�Z�������s���B<BR>
     * <BR>
     * <BR>
     * �P�j�@@����:�����ʒmParams.�����敪 == "������"�̏ꍇ�A<BR>
     * �@@�@@�@@�E�@@���X�|���X�f�[�^.���햾�׌������J�E���g<BR>
     * �@@�@@�@@�E�@@���X�|���X�f�[�^.���햾�׍��v���z�Ɉ���:�����ʒmParams.������z�����Z<BR>
     * <BR>
     * �Q�j�@@����:�����ʒmParams.�����敪 == "�G���["�̏ꍇ�A<BR>
     * �@@�@@�@@�E�@@���X�|���X�f�[�^.�G���[���׌������J�E���g<BR>
     * �@@�@@�@@�E�@@���X�|���X�f�[�^.�G���[���׍��v���z�Ɉ���:�����ʒmParams.������z�����Z<BR>
     * <BR>
     * �R�j�@@����:�����ʒmParams.�����敪 == "������"�̏ꍇ�A<BR>
     * �@@�@@�@@�E�@@���X�|���X�f�[�^.���������׌������J�E���g<BR>
     * �@@�@@�@@�E�@@���X�|���X�f�[�^.���������׍��v���z�Ɉ���:�����ʒmParams.������z�����Z<BR>
     * <BR>
     * �S�j�@@����:�����ʒmParams.�����敪 == "����"�̏ꍇ�A<BR>
     * �@@�@@�@@�E�@@���X�|���X�f�[�^.�������׌������J�E���g<BR>
     * �@@�@@�@@�E���X�|���X�f�[�^.�������׍��v���z�Ɉ���:�����ʒmParams.������z�����Z<BR>
     * <BR>
     * �T�j�@@����:�����ʒmParams.�����敪 == "�o��"�̏ꍇ�A<BR>
     * �@@�@@�@@�E�@@���X�|���X�f�[�^.�o�����׌������J�E���g<BR>
     * �@@�@@�@@�E�@@���X�|���X�f�[�^.�o�����׍��v���z�Ɉ���:�����ʒmParams.������z�����Z<BR>
     * <BR>
     * �U�j�@@���X�|���X�f�[�^.�����׍��v���z�Ɉ���:�����ʒmParams.������z�����Z<BR>
     * <BR>
     * �V�j�@@���X�|���X�f�[�^.�����׌������J�E���g<BR>
     * @@param l_bankDepositNotifyParams - (�����ʒmParams)<BR>
     * �����ʒmParams�I�u�W�F�N�g<BR>
     * @@param l_response - (���X�|���X�f�[�^)<BR>
     * ���X�|���X�f�[�^<BR>
     * @@roseuid 44D6D2300280
     */
    public void calcJapaneseCurrencyInfo(BankDepositNotifyParams l_bankDepositNotifyParams, 
        WEB3AdminAioCashinNoticeSearchResponse l_response) 
    {
        final String STR_METHOD_NAME = "calcJapaneseCurrencyInfo" +
            "(BankDepositNotifyParams, WEB3AdminAioCashinNoticeSearchResponse)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bankDepositNotifyParams == null || l_response == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        double l_dblDepositAmount = 0;
        if (l_bankDepositNotifyParams.getDepositDataDepositAmount() != null)
        {
            l_dblDepositAmount = Double.parseDouble(
                l_bankDepositNotifyParams.getDepositDataDepositAmount()); //�����ʒmParams.������z�����Z
        }
            
        
        //�P�j�@@����:�����ʒmParams.�����敪 == "������"�̏ꍇ
        //���X�|���X�f�[�^.���햾�׌������J�E���g 
        //���X�|���X�f�[�^.���햾�׍��v���z�Ɉ���:�����ʒmParams.������z�����Z
        if (WEB3StatusDef.DEALT.equals(l_bankDepositNotifyParams.getStatus()))
        {
            //���X�|���X�f�[�^.���햾�׌������J�E���g 
            int l_intNormalNumber = 0;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.normalNumber))
            {
                l_intNormalNumber = Integer.parseInt(l_response.normalNumber);
            }
            
            l_intNormalNumber = l_intNormalNumber + 1;
            l_response.normalNumber = l_intNormalNumber + "";
      
            //���X�|���X�f�[�^.���햾�׍��v���z�Ɉ���:�����ʒmParams.������z�����Z
            double l_dblNormalTotalPrice = 0;
            if (l_response.normalTotalPrice != null)
            {
                l_dblNormalTotalPrice = Double.parseDouble(l_response.normalTotalPrice);
            }

            l_dblNormalTotalPrice = l_dblNormalTotalPrice + l_dblDepositAmount;
            l_response.normalTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblNormalTotalPrice);
          
        }
        
        // �Q�j����:�����ʒmParams.�����敪 == "�G���["�̏ꍇ
        // ���X�|���X�f�[�^.�G���[���׌������J�E���g 
        // ���X�|���X�f�[�^.�G���[���׍��v���z�Ɉ���:�����ʒmParams.������z�����Z 
        if (WEB3StatusDef.DATA_ERROR.equals(l_bankDepositNotifyParams.getStatus()))
        {
            //���X�|���X�f�[�^.�G���[���׌������J�E���g
            int l_intErrorNumber = 0;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.errorNumber))
            {
                l_intErrorNumber = Integer.parseInt(l_response.errorNumber);
            }
            l_intErrorNumber = l_intErrorNumber + 1;
            l_response.errorNumber = l_intErrorNumber + "";
      
            //���X�|���X�f�[�^.�G���[���׍��v���z�Ɉ���:�����ʒmParams.������z�����Z
            double l_dblErrorTotalPrice = 0;
            if (l_response.errorTotalPrice != null)
            {
                l_dblErrorTotalPrice = Double.parseDouble(l_response.errorTotalPrice); 
            }
     
            l_dblErrorTotalPrice = l_dblErrorTotalPrice + l_dblDepositAmount;
            l_response.errorTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblErrorTotalPrice);
          
        }
        
        //�R�j�@@����:�����ʒmParams.�����敪 == "������"�̏ꍇ
        //���X�|���X�f�[�^.���������׌������J�E���g 
        //���X�|���X�f�[�^.���������׍��v���z�Ɉ���:�����ʒmParams.������z�����Z
        if (WEB3StatusDef.NOT_DEAL.equals(l_bankDepositNotifyParams.getStatus()))
        {
            //���X�|���X�f�[�^.���������׌������J�E���g 
            int l_intNonTransactionNumber = 0;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.nonTransactionNumber))
            {
                l_intNonTransactionNumber = Integer.parseInt(l_response.nonTransactionNumber);
            }
            l_intNonTransactionNumber = l_intNonTransactionNumber + 1;
            l_response.nonTransactionNumber = l_intNonTransactionNumber + "";
      
            //���X�|���X�f�[�^.���������׍��v���z�Ɉ���:�����ʒmParams.������z�����Z
            double l_dblNonTransactionTotalPrice = 0;
            if (l_response.nonTransactionTotalPrice != null)
            {
                l_dblNonTransactionTotalPrice = Double.parseDouble(l_response.nonTransactionTotalPrice);
            }

            l_dblNonTransactionTotalPrice = l_dblNonTransactionTotalPrice + l_dblDepositAmount;
            l_response.nonTransactionTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblNonTransactionTotalPrice);
          
        }
        
        //�S�j����:�����ʒmParams.�����敪 == "����"�̏ꍇ
        //���X�|���X�f�[�^.�������׌������J�E���g 
        //���X�|���X�f�[�^.�������׍��v���z�Ɉ���:�����ʒmParams.������z�����Z
        if (WEB3AioBankDepositNotifyCashTransferDivDef.CASH_IN.equals(
            l_bankDepositNotifyParams.getCashTransferDiv()))
        {
            //���X�|���X�f�[�^.�������׌������J�E���g 
            int l_intCashinNumber = 0;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.cashinNumber))
            {
                l_intCashinNumber = Integer.parseInt(l_response.cashinNumber);
            }
            l_intCashinNumber = l_intCashinNumber + 1;
            l_response.cashinNumber = l_intCashinNumber + "";
      
            //���X�|���X�f�[�^.�������׍��v���z�Ɉ���:�����ʒmParams.������z�����Z
            double l_dblCashinTotalPrice = 0;
            if (l_response.cashinTotalPrice != null)
            {
                l_dblCashinTotalPrice = Double.parseDouble(l_response.cashinTotalPrice); 
            }
   
            l_dblCashinTotalPrice = l_dblCashinTotalPrice + l_dblDepositAmount;
            l_response.cashinTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblCashinTotalPrice);
          
        }
        
        // �T�j����:�����ʒmParams.�����敪 == "�o��"�̏ꍇ
        //���X�|���X�f�[�^.�o�����׌������J�E���g 
        //���X�|���X�f�[�^.�o�����׍��v���z�Ɉ���:�����ʒmParams.������z�����Z 
        if (WEB3AioBankDepositNotifyCashTransferDivDef.CASH_OUT.equals(
            l_bankDepositNotifyParams.getCashTransferDiv()))
        {
            //���X�|���X�f�[�^.�o�����׌������J�E���g 
            int l_intCashoutNumber = 0;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.cashoutNumber))
            {
                l_intCashoutNumber = Integer.parseInt(l_response.cashoutNumber);
            }
            l_intCashoutNumber = l_intCashoutNumber + 1;
            l_response.cashoutNumber = l_intCashoutNumber + "";
      
            //���X�|���X�f�[�^.�o�����׍��v���z�Ɉ���:�����ʒmParams.������z�����Z 
            double l_dblCashoutTotalPrice = 0;
            if (l_response.cashoutTotalPrice != null)
            {
                l_dblCashoutTotalPrice = Double.parseDouble(l_response.cashoutTotalPrice); 
            }
            l_dblCashoutTotalPrice = l_dblCashoutTotalPrice + l_dblDepositAmount;
            l_response.cashoutTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblCashoutTotalPrice);
          
        }
        
        //�U�j�@@���X�|���X�f�[�^.�����׍��v���z�Ɉ���:�����ʒmParams.������z�����Z 
        double l_dblTotalAmout = 0;
        if (l_response.totalPrice != null)
        {
            l_dblTotalAmout = Double.parseDouble(l_response.totalPrice);
        }
        l_dblTotalAmout = l_dblTotalAmout + l_dblDepositAmount;
        
        l_response.totalPrice = WEB3StringTypeUtility.formatNumber(l_dblTotalAmout);
        
        // �V�j�@@���X�|���X�f�[�^.�����׌������J�E���g 
        int l_intTotleNumber = 0;
        if (WEB3StringTypeUtility.isNotEmpty(l_response.totalNumber))
        {
            l_intTotleNumber = Integer.parseInt(l_response.totalNumber);
        }
        l_intTotleNumber = l_intTotleNumber + 1;
        l_response.totalNumber = l_intTotleNumber + "";
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc�O�ݏ��)<BR>
     * �O�݂̃T�}�������v�Z����B<BR>
     * <BR>
     * �P�j�@@����:�����ʒmParams.�����敪 = <BR>
     * "����"�̏ꍇ�A����:�O�݃T�}�����.�����������J�E���g�B<BR>
     * <BR>
     * �Q�j�@@����:�����ʒmParams.�����敪 = <BR>
     * "����"�̏ꍇ�A����:�O�݃T�}�����.�������v�z�ɓ����ʒmParams.������z�����Z<BR>
     * @@param l_foreignSummaryInfo - (�O�݃T�}�����)<BR>
     * �O�݃T�}�����I�u�W�F�N�g<BR>
     * @@param l_bankDepositNotifyParams - (�����ʒmParams)<BR>
     * �����ʒmParams�I�u�W�F�N�g<BR>
     * @@roseuid 44E447AE00EB
     */
    private void calcForeignCurrencyInfo(WEB3ForeignSummaryInfo l_foreignSummaryInfo, 
        BankDepositNotifyParams l_bankDepositNotifyParams) 
    {
        final String STR_METHOD_NAME = "calcForeignCurrencyInfo" +
            "(WEB3ForeignSummaryInfo, BankDepositNotifyParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bankDepositNotifyParams == null || l_foreignSummaryInfo == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        double l_dblDepositAmountTemp = 0 ;
        if (l_bankDepositNotifyParams.getDepositDataDepositAmount() != null)
        {
            l_dblDepositAmountTemp = Double.parseDouble
                (l_bankDepositNotifyParams.getDepositDataDepositAmount()); //�����ʒmParams.������z
        }        
        
        //�P�j����:�����ʒmParams.�����敪 = "����"�̏ꍇ
        //    ����:�O�݃T�}�����.�����������J�E���g�B
        //�Q�j����:�����ʒmParams.�����敪 = "����"�̏ꍇ
        //    ����:�O�݃T�}�����.�������v�z�ɉ��Z
        if (WEB3AioBankDepositNotifyCashTransferDivDef.CASH_IN.equals(
            l_bankDepositNotifyParams.getCashTransferDiv()))
        {
            //����:�O�݃T�}�����.�����������J�E���g�B
            int l_intCashinNumber = 0;
            if (WEB3StringTypeUtility.isNotEmpty(l_foreignSummaryInfo.cashinNumber))
            {
                l_intCashinNumber = Integer.parseInt(l_foreignSummaryInfo.cashinNumber);
            }
            l_intCashinNumber = l_intCashinNumber + 1;       
            l_foreignSummaryInfo.cashinNumber = l_intCashinNumber + "";

            //����:�O�݃T�}�����.�������v�z�ɓ����ʒmParams.������z�����Z
            double l_dblCashinTotalPrice = 0;
            if (l_foreignSummaryInfo.cashinTotalPrice != null)
            {
                l_dblCashinTotalPrice = Double.parseDouble(l_foreignSummaryInfo.cashinTotalPrice);
            }
            l_dblCashinTotalPrice = l_dblCashinTotalPrice + l_dblDepositAmountTemp;

            l_foreignSummaryInfo.cashinTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblCashinTotalPrice);

        }

        log.exiting(STR_METHOD_NAME);   
    }    
}
@
