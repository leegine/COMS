head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϘA�g���|�[�g�T�[�r�X�����N���X(WEB3AdminAioSettleReportServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 ��O�� (���u) �V�K�쐬     
                   2004/10/27 ���E(���u) ���r���[               
                   2006/04/14 ������ �d�l�ύX�E���f��530
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.WEB3AioSettleInstitution;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.define.WEB3AioSettleReportSortkeyDef;
import webbroker3.aio.define.WEB3AioTransactionStatusDef;
import webbroker3.aio.message.WEB3AdminAioSettleReportListRequest;
import webbroker3.aio.message.WEB3AdminAioSettleReportListResponse;
import webbroker3.aio.message.WEB3AioSettleReportUnit;
import webbroker3.aio.message.WEB3AioSortKeyUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioSettleReportService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (���ϘA�g���|�[�g�T�[�r�XImpl)<BR>
 * ���ϘA�g���|�[�g�T�[�r�X�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioSettleReportServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminAioSettleReportService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSettleReportServiceImpl.class);
        
    /**
     * ���ϘA�g���|�[�g�T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���ϘA�g���|�[�g�j���|�[�g��ʕ\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410101610138
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
        
        WEB3AdminAioSettleReportListRequest l_aioSettleReportListRequest = null;
        WEB3AdminAioSettleReportListResponse l_aioSettleReportListResponse = null;        
        
        if (l_request instanceof WEB3AdminAioSettleReportListRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u ���ϘA�g���|�[�g�ꗗ���N�G�X�g �v�̏ꍇ
            l_aioSettleReportListRequest = (WEB3AdminAioSettleReportListRequest) l_request;
        }
        else
        {
            log.debug("Error[���͒l���s���ł�]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1 ���͓��e�`�F�b�N 
        l_aioSettleReportListRequest.validate();
        
        //1.2 �Ǘ��҃C���X�^���X���擾����B         
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 �Y���̊Ǘ��҂����̋@@�\���g���邩�����`�F�b�N���s���B 
        //validate����(String, boolean)
        //[����] 
        //�@@�\�J�e�S���R�[�h�F �hB0201�h
        //is�X�V�F false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_SETTLE_REPORT, 
            false);
        
        //1.4 �Y���̊Ǘ��҂��w�肵�����X�ɑ΂��鏈�����������邩�`�F�b�N����B 
        //validate���X����(String)
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        l_web3Administrator.validateBranchPermission(
            l_aioSettleReportListRequest.branchCode);
        
        //1.5 �擾�����̕�����𐶐�����B
        String l_strWhereClause = null;
        l_strWhereClause = this.createGetCondCharacterString(l_aioSettleReportListRequest);
        
        //1.6 �擾�����ɐݒ肷��l�̔z��𐶐�����B
        Object l_bindVars[] = this.createGetCondDataContainer(l_aioSettleReportListRequest);
        
        //�y�[�W�T�C�Y�F
        int l_intPageSize = 0;
        l_intPageSize = Integer.parseInt(l_aioSettleReportListRequest.pageSize);
            
        //�y�[�W�ԍ��F
        int l_intPageIndex = 0;
        l_intPageIndex = Integer.parseInt(l_aioSettleReportListRequest.pageIndex);
        
        //1.7 create�\�[�g����������(AIO�\�[�g�L�[�Q[]) 
        //�\�[�g������𐶐�����B 
        //[����] 
        //�\�[�g�L�[�F ���N�G�X�g�f�[�^.�\�[�g�L�[ 
        String l_strSortCond = 
            this.createSortCondString(l_aioSettleReportListRequest.sortKeys);
        
        WEB3PageIndexInfo l_pageIndexInfo = null;
        //1.8, 1.9 ���Z�@@�֘A�g���o���󋵃e�[�u������A���R�[�h���擾����B
        List l_lisRows = null;
        try
        {
            //�m�����n 
            //Row�^�C�v�F ���Z�@@�֘A�g���o����Row.TYPE 
            //Where�F create�擾����������()�̖߂�l 
            //orderBy�F create�\�[�g����������()�̖߂�l  
            //condition�F null 
            //���X�g�F create�擾�����f�[�^�R���e�i()�̖߂�l 
            //�y�[�W�T�C�Y�F ���N�G�X�g�f�[�^.�y�[�W���\���s�� 
            //�y�[�W�ԍ��F ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� - 1 
            
            List l_lisBankCashTransferStatusRows = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    BankCashTransferStatusRow.TYPE,
                    l_strWhereClause,
                    l_strSortCond,
                    null,
                    l_bindVars,
                    l_intPageSize,
                    l_intPageIndex - 1);
            
            l_pageIndexInfo = new WEB3PageIndexInfo(
                    l_lisBankCashTransferStatusRows, 
                    l_intPageIndex, 
                    l_intPageSize);   
            
            l_lisRows = l_pageIndexInfo.getListReturned();
                
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.10 �،���ЃI�u�W�F�N�g���擾����B 
        Institution l_institution = l_web3Administrator.getInstitution();
        
        //1.11 ArrayList�C���X�^���X�𐶐�����B
        List l_lisAioSettleReportUnit = null;
        l_lisAioSettleReportUnit = new ArrayList();
                
        //1.12 �擾�����v�f����Loop����         
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_lisRows.get(i);
                
            //1.12.1 ���ϘA�g���|�[�g���׃C���X�^���X�𐶐�����B 
            //�m�����n 
            //�،���ЁF get�،����()�̖߂�l 
            //���Z�@@�֘A�g���o���󋵁F ���Z�@@�֘A�g���o����Row�I�u�W�F�N�g
            
            WEB3AioSettleReportUnit l_aioSettleReportUnit = null;
            l_aioSettleReportUnit = this.createSettleReportUnit(
                l_institution, l_bankCashTransferStatusRow);
                
            //1.12.2 ���ϘA�g���|�[�g���׃I�u�W�F�N�g�����X�g�ɒǉ�����B 

            l_lisAioSettleReportUnit.add(l_aioSettleReportUnit);                
        }
        
        WEB3AioSettleReportUnit[] l_aioSettleReportUnits = 
            new WEB3AioSettleReportUnit[l_lisAioSettleReportUnit.size()];
            
        //1.13 ���ϘA�g���|�[�g���ׂ̔z����擾����B
        l_lisAioSettleReportUnit.toArray(l_aioSettleReportUnits);
                
       
        //1.14 �\���y�[�W�ԍ����擾����B        
        l_intPageIndex = l_pageIndexInfo.getPageIndex();
        
        //1.15 ���y�[�W�����擾����B 
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();
        
        //1.16 �����R�[�h�����擾����B       

        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

        
        //1.17 ���ϋ@@�փC���X�^���X�𐶐�����B
        //�m�����n 
        //���ϋ@@��ID�F ���N�G�X�g�f�[�^.���ϋ@@��ID 
        
        WEB3AioSettleInstitution l_web3AioSettleInstitution = 
            new WEB3AioSettleInstitution(l_aioSettleReportListRequest.paySchemeId);
        
        //1.18 ���ϋ@@�ւ̖��̂��擾����B
        String l_strSettleName = l_web3AioSettleInstitution.getName();
        
        //1.19 ���X�|���X�f�[�^�𐶐�����B
        l_aioSettleReportListResponse = (WEB3AdminAioSettleReportListResponse) 
            l_request.createResponse();
        
        //1.20  �v���p�e�B�Z�b�g
        //���X�|���X.���X�R�[�h = ���N�G�X�g�f�[�^.���X�R�[�h
        l_aioSettleReportListResponse.branchCode = 
            l_aioSettleReportListRequest.branchCode;
        
        log.debug("���X�|���X.���X�R�[�h = " + l_aioSettleReportListResponse.branchCode);
        
        //���X�|���X.���ϋ@@��ID = ���N�G�X�g�f�[�^.���ϋ@@��ID
        l_aioSettleReportListResponse.paySchemeId = 
            l_aioSettleReportListRequest.paySchemeId;
        
        log.debug("���X�|���X.���ϋ@@��ID = " + l_aioSettleReportListResponse.paySchemeId);
        
        //���X�|���X.���ϋ@@�֖� = ��g���ϋ@@��.get����()�̖߂�l
        l_aioSettleReportListResponse.paySchemeName = l_strSettleName;
        
        log.debug("���X�|���X.���ϋ@@�֖� = " + l_aioSettleReportListResponse.paySchemeName);
        
        //���X�|���X.�ڋq�R�[�h = ���N�G�X�g�f�[�^.�ڋq�R�[�h
        l_aioSettleReportListResponse.accountCode = 
            l_aioSettleReportListRequest.accountCode;

        log.debug("���X�|���X.�ڋq�R�[�h = " + l_aioSettleReportListResponse.accountCode);
        
        //���X�|���X.��n�� = ���N�G�X�g�f�[�^.��n��
        l_aioSettleReportListResponse.deliveryDate = 
            l_aioSettleReportListRequest.deliveryDate;
        
        log.debug("���X�|���X.��n�� = " + l_aioSettleReportListResponse.deliveryDate);
        
        //���X�|���X.�������i���j = ���N�G�X�g�f�[�^.�������i���j
        l_aioSettleReportListResponse.minOrtderDate = 
            l_aioSettleReportListRequest.minOrtderDate;
        
        log.debug("���X�|���X.�������i���j = " + l_aioSettleReportListResponse.minOrtderDate);
        
        //���X�|���X.�������i���j = ���N�G�X�g�f�[�^.�������i���j
        l_aioSettleReportListResponse.maxOrtderDate = 
            l_aioSettleReportListRequest.maxOrtderDate;
            
        log.debug("���X�|���X.�������i���j = " + l_aioSettleReportListResponse.maxOrtderDate);

        //���X�|���X.������� = ���N�G�X�g�f�[�^.�������
        
        l_aioSettleReportListResponse.transactionStatus = 
            l_aioSettleReportListRequest.transactionStatus;
        
        log.debug("���X�|���X.������� = " + l_aioSettleReportListResponse.transactionStatus);
        
        //���X�|���X..com�f�r�b�g���ώ���ԍ��i���j = 
        //���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j
        l_aioSettleReportListResponse.minComDebitNumber = 
            l_aioSettleReportListRequest.minComDebitNumber;
        
        log.debug("���X�|���X..com�f�r�b�g���ώ���ԍ��i���j= " + 
                l_aioSettleReportListResponse.minComDebitNumber);
        
        //���X�|���X..com�f�r�b�g���ώ���ԍ��i���j = 
        //���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j
        l_aioSettleReportListResponse.maxComDebitNumber = 
            l_aioSettleReportListRequest.maxComDebitNumber;
            
        log.debug("���X�|���X..com�f�r�b�g���ώ���ԍ��i���j= " + 
                l_aioSettleReportListResponse.maxComDebitNumber);
        
        //���X�|���X.���ϘA�g���|�[�g���� = ���ϘA�g���|�[�g���ׂ̔z��
        
        l_aioSettleReportListResponse.settleReportUnits = l_aioSettleReportUnits;
        
        log.debug("���X�|���X.���ϘA�g���|�[�g����'s length= " + 
                l_aioSettleReportListResponse.settleReportUnits.length);
        
        //���X�|���X.�\���y�[�W�ԍ� = ListPage.pageNumber()�̖߂�l
        l_aioSettleReportListResponse.pageIndex = l_intPageIndex + "";
        
        log.debug("���X�|���X.���ϘA�g���|�[�g����= " + l_aioSettleReportListResponse.pageIndex);
                        
        //���X�|���X.���y�[�W�� = ListPage.totalPages()�̖߂�l
        l_aioSettleReportListResponse.totalPages = l_intTotalPages + "";
        
        log.debug("���X�|���X.���y�[�W��= " + l_aioSettleReportListResponse.totalPages);
        
        //���X�|���X.�����R�[�h�� = ListPage.totalSize()�̖߂�l
        l_aioSettleReportListResponse.totalRecords = l_intTotalRecords + "";
  
        log.debug("���X�|���X.�����R�[�h��= " + l_aioSettleReportListResponse.totalRecords);
        
        log.exiting(STR_METHOD_NAME);
        return l_aioSettleReportListResponse;
    }
    
    /**
     * (create�擾����������)<BR>
     * ���N�G�X�g�f�[�^����A�f�[�^�擾����������𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j���X�R�[�h��������<BR>
     * <BR>
     *   ����������F "branch_code=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �R�j���ϋ@@��ID��������<BR>
     * <BR>
     *   ����������F " and pay_scheme_id=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �S�j�ڋq�R�[�h��������<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�ڋq�R�[�h != null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and account_code=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �T�j��������������<BR>
     * <BR>
     * �T�|�P�j ����.���N�G�X�g�f�[�^.�������i���j != null and <BR>
     * ����.���N�G�X�g�f�[�^.�������i���j != null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and order_date_time>=? and order_date_time<=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �T�|�Q�j ����.���N�G�X�g�f�[�^.�������i���j != null and <BR>
     * ����.���N�G�X�g�f�[�^.�������i���j = null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and order_date_time>=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �T�|�R�j ����.���N�G�X�g�f�[�^.�������i���j = null and <BR>
     * ����.���N�G�X�g�f�[�^.�������i���j != null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and order_date_time<=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �U�j��n����������<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.��n�� != null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and delivery_scheduled_date=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �V�j.com�f�r�b�g���ώ���ԍ���������<BR>
     * <BR>
     * �V�|�P�j ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null <BR>
     * and ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and center_pay_id>=? and center_pay_id<=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �V�|�Q�j ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null <BR>
     * and ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j = null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and center_pay_id=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �W�j������ԏ�������<BR>
     * <BR>
     *   ����������F " and status=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �X�j���������������ԋp����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return String
     * @@roseuid 41182B5503A5
     */
    protected String createGetCondCharacterString(
        WEB3AdminAioSettleReportListRequest l_request) 
    {
        final String STR_METHOD_NAME = "createGetCondCharacterString(" +
            "WEB3AdminAioSettleReportListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P�j��̕�����𐶐�����B
        String l_strWhereCondition = "";
        
        //�Q�j���X�R�[�h�������� 
        l_strWhereCondition += "branch_code = ?";
        
        //�R�j���ϋ@@��ID�������� 
        l_strWhereCondition += " and pay_scheme_id = ?";
 
        //�S�j�ڋq�R�[�h�������� 
        //����.���N�G�X�g�f�[�^.�ڋq�R�[�h != null �̏ꍇ 
        if (l_request.accountCode != null)
        {        
            l_strWhereCondition += " and substr(account_code, 0, 6) = ?";
        }
        
        //�T�j��������������
        //�T�|�P�j ����.���N�G�X�g�f�[�^.�������i���j != null and 
        //        ����.���N�G�X�g�f�[�^.�������i���j != null �̏ꍇ 
        if (l_request.minOrtderDate != null &&
            l_request.maxOrtderDate != null)
        {            
            l_strWhereCondition += " and order_date_time >= ? and order_date_time <= ?";
        }
        //�T�|�Q�j ����.���N�G�X�g�f�[�^.�������i���j != null and 
        //        ����.���N�G�X�g�f�[�^.�������i���j = null �̏ꍇ 
        if (l_request.minOrtderDate != null &&
            l_request.maxOrtderDate == null)
        {
            l_strWhereCondition += " and order_date_time >= ?";
        }
       
        //�T�|�R�j ����.���N�G�X�g�f�[�^.�������i���j = null and 
        //        ����.���N�G�X�g�f�[�^.�������i���j != null �̏ꍇ
        if (l_request.minOrtderDate == null &&
            l_request.maxOrtderDate != null)
        {
            l_strWhereCondition += " and order_date_time<= ?" ;
        }

        //�U�j��n���������� 
        //����.���N�G�X�g�f�[�^.��n�� != null �̏ꍇ 
        if (l_request.deliveryDate != null)
        {
            l_strWhereCondition += " and delivery_scheduled_date = ?";
        }
        
        //�V�j.com�f�r�b�g���ώ���ԍ��������� 
        //�V�|�P�j ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null and 
        //        ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null �̏ꍇ 
        if (l_request.minComDebitNumber != null &&
            l_request.maxComDebitNumber != null)
        {
            l_strWhereCondition += " and center_pay_id >= ? and center_pay_id <= ?";
        }
        
        //�V�|�Q�j ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null and 
        //        ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j = null �̏ꍇ 
        if (l_request.minComDebitNumber != null &&
            l_request.maxComDebitNumber == null)
        {
            l_strWhereCondition += " and center_pay_id = ?";
        }
        
        //�W�j������ԏ������� 
        //����.���N�G�X�g�f�[�^.������� != 4�i���ׂāj �̏ꍇ 
        if (!WEB3AioTransactionStatusDef.ALL.equals(l_request.transactionStatus))
        {
            l_strWhereCondition += " and transaction_status = ?";
        }
        log.debug("��������������= " + l_strWhereCondition);
        
        //�X�j���������������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strWhereCondition;
    }
    
    /**
     * (create�擾�����f�[�^�R���e�i)<BR>
     * ���N�G�X�g�f�[�^����A�擾�����̃f�[�^���X�g�𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j���X�R�[�h<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.���X�R�[�h���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �R�j���ϋ@@��ID<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.���ϋ@@��ID���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �S�j�ڋq�R�[�h<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�ڋq�R�[�h != null �̏ꍇ<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�ڋq�R�[�h���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �T�j������<BR>
     * <BR>
     * �T�|�P�j ����.���N�G�X�g�f�[�^.�������i���j != null and <BR>
     * ����.���N�G�X�g�f�[�^.�������i���j != null �̏ꍇ<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�������i���j�A<BR>
     *  ����.���N�G�X�g�f�[�^.�������i���j���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �T�|�Q�j ����.���N�G�X�g�f�[�^.�������i���j != null and <BR>
     * ����.���N�G�X�g�f�[�^.�������i���j = null �̏ꍇ<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�������i���j���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �T�|�R�j ����.���N�G�X�g�f�[�^.�������i���j = null and <BR>
     * ����.���N�G�X�g�f�[�^.�������i���j != null �̏ꍇ<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�������i���j���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �U�j��n��<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.��n�� != null �̏ꍇ<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.��n�����P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �V�j.com�f�r�b�g���ώ���ԍ�<BR>
     * <BR>
     * �V�|�P�j ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null <BR>
     * and ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null �̏ꍇ<BR>
     * <BR>
     *   
     * ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j�A
     * ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j���P�j�̃�
     * �X�g�ɒǉ�����B<BR>
     * <BR>
     * �V�|�Q�j ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null <BR>
     * and ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j = null �̏ꍇ<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �W�j�������<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.������Ԃ��P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �X�j���X�g����z����擾���A�ԋp����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return Object[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41182B5503B4
     */
    protected Object[] createGetCondDataContainer(
        WEB3AdminAioSettleReportListRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createGetCondDataContainer(" +
            "WEB3AdminAioSettleReportListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j���ArrayList�𐶐�����B
        List l_lisAioSettleReportUnit = null;
        l_lisAioSettleReportUnit = new ArrayList();

        //�Q�j���X�R�[�h
        //����.���N�G�X�g�f�[�^.���X�R�[�h���P�j�̃��X�g�ɒǉ�����B
        l_lisAioSettleReportUnit.add(l_request.branchCode);
        
        //�R�j���ϋ@@��ID 
        //����.���N�G�X�g�f�[�^.���ϋ@@��ID���P�j�̃��X�g�ɒǉ�����B 
        l_lisAioSettleReportUnit.add(l_request.paySchemeId);
        
        //�S�j�ڋq�R�[�h 
        //����.���N�G�X�g�f�[�^.�ڋq�R�[�h != null �̏ꍇ
        //����.���N�G�X�g�f�[�^.�ڋq�R�[�h���P�j�̃��X�g�ɒǉ�����B        
        if (l_request.accountCode != null)
        {
            l_lisAioSettleReportUnit.add(l_request.accountCode);
        }

        //�T�j������ 
        //�T�|�P�j ����.���N�G�X�g�f�[�^.�������i���j != null and 
        //        ����.���N�G�X�g�f�[�^.�������i���j != null �̏ꍇ 
        //����.���N�G�X�g�f�[�^.�������i���j�A����.���N�G�X�g�f�[�^.�������i���j���P�j�̃��X�g�ɒǉ�����B
        
        if (l_request.minOrtderDate != null &&
            l_request.maxOrtderDate != null)
        {
            l_lisAioSettleReportUnit.add(l_request.minOrtderDate);
            l_lisAioSettleReportUnit.add(l_request.maxOrtderDate);
        }

        //�T�|�Q�j ����.���N�G�X�g�f�[�^.�������i���j != null and 
        //        ����.���N�G�X�g�f�[�^.�������i���j = null �̏ꍇ 
        //����.���N�G�X�g�f�[�^.�������i���j���P�j�̃��X�g�ɒǉ�����B 
        if (l_request.minOrtderDate != null &&
            l_request.maxOrtderDate == null)
        {
            l_lisAioSettleReportUnit.add(l_request.minOrtderDate);
        }
        
        //�T�|�R�j ����.���N�G�X�g�f�[�^.�������i���j = null and 
        //        ����.���N�G�X�g�f�[�^.�������i���j != null �̏ꍇ 
        //����.���N�G�X�g�f�[�^.�������i���j���P�j�̃��X�g�ɒǉ�����B
        if (l_request.minOrtderDate == null &&
            l_request.maxOrtderDate != null)
        {
            l_lisAioSettleReportUnit.add(l_request.maxOrtderDate);
        }
        
        //�U�j��n�� 
        //����.���N�G�X�g�f�[�^.��n�� != null �̏ꍇ 
        //����.���N�G�X�g�f�[�^.��n�����P�j�̃��X�g�ɒǉ�����B
        if (l_request.deliveryDate != null)
        {
            l_lisAioSettleReportUnit.add(l_request.deliveryDate);
        }
        
        //�V�j.com�f�r�b�g���ώ���ԍ� 
        //�V�|�P�j ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null and         
        //        ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null �̏ꍇ 
        if (l_request.minComDebitNumber != null &&
            l_request.maxComDebitNumber != null)
        {
            l_lisAioSettleReportUnit.add(l_request.minComDebitNumber);
            l_lisAioSettleReportUnit.add(l_request.maxComDebitNumber);
        }
        
        //�V�|�Q�j ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j != null and 
        //        ����.���N�G�X�g�f�[�^..com�f�r�b�g���ώ���ԍ��i���j = null �̏ꍇ 
        if (l_request.minComDebitNumber != null &&
            l_request.maxComDebitNumber == null)
        {
            l_lisAioSettleReportUnit.add(l_request.minComDebitNumber);            
        }
        
        //�W�j������� 
        //����.���N�G�X�g�f�[�^.������� != 4�i���ׂāj �̏ꍇ         
        if (!WEB3AioTransactionStatusDef.ALL.equals(l_request.transactionStatus))
        {
            l_lisAioSettleReportUnit.add(l_request.transactionStatus);
        }
        
        //�X�j���X�g����z����擾���A�ԋp����B 
        Object[] l_bindVars = 
            new Object[l_lisAioSettleReportUnit.size()];
            
        l_lisAioSettleReportUnit.toArray(l_bindVars);
        
        log.exiting(STR_METHOD_NAME);
        return l_bindVars;
    }
    
    /**
     * (create���ϘA�g���|�[�g����)<BR>
     * ���ϘA�g���׃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���ϘA�g���|�[�g�jcreate���ϘA�g���|�[�g���ׁv �Q��<BR>
     * @@param l_institution - (�،���ЃI�u�W�F�N�g)<BR>
     * @@param l_bankCashTransferStatusRow - (���Z�@@�֘A�g���o����Row�I�u�W�F�N�g)
     * @@return WEB3AioSettleReportUnit
     * @@throws WEB3BaseException
     * @@roseuid 41185B93038C
     */
    protected WEB3AioSettleReportUnit createSettleReportUnit(
        Institution l_institution, 
        BankCashTransferStatusRow l_bankCashTransferStatusRow) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleReportUnit()";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null || l_bankCashTransferStatusRow == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1 ���ϘA�g���|�[�g���׃C���X�^���X�𐶐�����B
        WEB3AioSettleReportUnit l_aioSettleReportUnit = new WEB3AioSettleReportUnit();
        WEB3GentradeMainAccount l_web3GentradeMainAcc = null;
        try
        {       
            //1.2 �ڋq�C���X�^���X�𐶐�����B 
            //�m�����n
            //�،����ID�F ����.�،����.getInstitutionId()�̖߂�l 
            //���X�R�[�h�F ����.���Z�@@�֘A�g���o����Params.���X�R�[�h 
            //�ڋq�R�[�h�F ����.���Z�@@�֘A�g���o����Params.�ڋq�R�[�h 
            AccountManager l_accountManager = GtlUtils.getAccountManager();  
            l_web3GentradeMainAcc = (WEB3GentradeMainAccount)
                l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(), 
                    l_bankCashTransferStatusRow.getBranchCode(), 
                    l_bankCashTransferStatusRow.getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }     
        //1.3 �ڋq�̖��̂��擾����B
        String l_strDisplayAccountName = l_web3GentradeMainAcc.getDisplayAccountName();
        
        //1.4 ���b�Z�[�W�R�[�h���擾����B 

        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                    WEB3AioMultiBankSettleControlService.class);

        //�m�����n 
        //�،���ЃR�[�h�F ����.���Z�@@�֘A�g���o����Params.�،���ЃR�[�h 
        //���X�R�[�h�F ����.���Z�@@�֘A�g���o����Params.���X�R�[�h 
        //�ڋq�R�[�h�F ����.���Z�@@�֘A�g���o����Params.�ڋq�R�[�h 
        //���ʃR�[�h�F ����.���Z�@@�֘A�g���o����Params.���ʃR�[�h 
        //.com�f�r�b�g���ώ���ԍ��F ����.���Z�@@�֘A�g���o����Params..com�f�r�b�g���ώ���ԍ� 
        //�����敪�F ����.���Z�@@�֘A�g���o����Params.�����敪 
        //FLAG�i�����j�F ����.���Z�@@�֘A�g���o����Params.����FLAG�i�����j 
        //FLAG�i���ϊJ�n�j�F ����.���Z�@@�֘A�g���o����Params.����FLAG�i���ϊJ�n�j 
        //FLAG�i���ό��ʁj�F ����.l_bankCashTransferStatusRow���Z�@@�֘A�g���o����Params.����FLAG�i���ό��ʁj 
               
        String l_strMessageCode = l_aioMultiBankSettleControlService.getMessageCode(
            l_bankCashTransferStatusRow.getInstitutionCode(), 
            l_bankCashTransferStatusRow.getBranchCode(), 
            l_bankCashTransferStatusRow.getAccountCode(), 
            l_bankCashTransferStatusRow.getOrderRequestNumber(), 
            l_bankCashTransferStatusRow.getCenterPayId(), 
            l_bankCashTransferStatusRow.getTransactionStatus(), 
            l_bankCashTransferStatusRow.getOrderStatusFlag(), 
            l_bankCashTransferStatusRow.getStartStatusFlag(), 
            l_bankCashTransferStatusRow.getResultStatusFlag());
        
        //1.5 �v���p�e�B�Z�b�g
        
        //���ϘA�g���|�[�g����.�ڋq�R�[�h = ���Z�@@�֘A�g���o����Params.�ڋq�R�[�h
        l_aioSettleReportUnit.accountCode = l_bankCashTransferStatusRow.getAccountCode().substring(0, 6);
        
        //���ϘA�g���|�[�g����.�ڋq�� = �ڋq.get�ڋq�\����()�̖߂�l
        l_aioSettleReportUnit.accountName = l_strDisplayAccountName;
        
        //���ϘA�g���|�[�g����..com�f�r�b�g���ώ���ԍ� = 
        //���Z�@@�֘A�g���o����Params..com�f�r�b�g���ώ���ԍ�
        l_aioSettleReportUnit.comDebitNumber = 
            l_bankCashTransferStatusRow.getCenterPayId();
        
        //���ϘA�g���|�[�g����.�����X�����ԍ� = �i�ȉ��̂Ƃ���j
        //    ���Z�@@�֘A�g���o����Params.�،���ЃR�[�h+
        //    ���Z�@@�֘A�g���o����Params.���X�R�[�h+
        //    ���Z�@@�֘A�g���o����Params.���ʃR�[�h
        
        //�i�R�̍��ڂ̕������A���������́j
        l_aioSettleReportUnit.shopOrderId = 
            l_bankCashTransferStatusRow.getInstitutionCode() + 
            l_bankCashTransferStatusRow.getBranchCode() + 
            l_bankCashTransferStatusRow.getOrderRequestNumber();
        
        //���ϘA�g���|�[�g����.��t���� = ���Z�@@�֘A�g���o����Params.��������
        l_aioSettleReportUnit.receptionDate = 
            l_bankCashTransferStatusRow.getOrderDateTime();
        
        log.debug("���Z�@@�֘A�g���o����Params.�������ԁi���ό��ʒʒm�j = " + 
                l_bankCashTransferStatusRow.getResultRequestTime());
        log.debug("���Z�@@�֘A�g���o����Params.�������ԁi���ϊJ�n�v���j = " + 
                l_bankCashTransferStatusRow.getStartRequestTime());        
        log.debug("���Z�@@�֘A�g���o����Params.�������ԁi�����v���j = " + 
                l_bankCashTransferStatusRow.getOrderRequestTime());  
        
        //���ϘA�g���|�[�g����.�߂���� = �i�ȉ��̂Ƃ���j
        
        //    ���Z�@@�֘A�g���o����Params.�������ԁi���ό��ʒʒm�j != null �̏ꍇ�A
        //            ���Z�@@�֘A�g���o����Params.�������ԁi���ό��ʒʒm�j        
        if (l_bankCashTransferStatusRow.getResultRequestTime() != null)
        {
            l_aioSettleReportUnit.returnDate = 
                l_bankCashTransferStatusRow.getResultRequestTime();
        }
        
        //    ���Z�@@�֘A�g���o����Params.�������ԁi���ϊJ�n�v���j != null �̏ꍇ�A
        //            ���Z�@@�֘A�g���o����Params.�������ԁi���ϊJ�n�v���j        
        else if (l_bankCashTransferStatusRow.getStartRequestTime() != null)            
        {
            l_aioSettleReportUnit.returnDate = 
                l_bankCashTransferStatusRow.getStartRequestTime();
        }
        
        //    ���Z�@@�֘A�g���o����Params.�������ԁi�����v���j != null �̏ꍇ�A
        //            ���Z�@@�֘A�g���o����Params.�������ԁi�����v���j                 
        else if (l_bankCashTransferStatusRow.getOrderRequestTime() != null)
        {
            l_aioSettleReportUnit.returnDate = 
                l_bankCashTransferStatusRow.getOrderRequestTime();
        }
        
        //����ȊO�̏ꍇ�Anull
        else
        {
            l_aioSettleReportUnit.returnDate = null;
        }
        
        log.debug("���ϘA�g���|�[�g����.�߂���� = " + l_aioSettleReportUnit.returnDate);
        
        //���ϘA�g���|�[�g����.��n�� = ���Z�@@�֘A�g���o����Params.��n�\���
        l_aioSettleReportUnit.deliveryDate = 
            l_bankCashTransferStatusRow.getDeliveryScheduledDate();
        
        log.debug("���ϘA�g���|�[�g����.��n�� = " + l_aioSettleReportUnit.deliveryDate);
        
        //���ϘA�g���|�[�g����.�U���\��� = ���Z�@@�֘A�g���o����Params.�U�������\���
        l_aioSettleReportUnit.transScheduledDate = 
            l_bankCashTransferStatusRow.getComondebiCaptureDate();
        
        log.debug("���ϘA�g���|�[�g����.�U���\��� = " + l_aioSettleReportUnit.transScheduledDate);
        
        //���ϘA�g���|�[�g����.���z = ���Z�@@�֘A�g���o����Params.���z
        l_aioSettleReportUnit.amount = l_bankCashTransferStatusRow.getAmount() + "";
        
        log.debug("���ϘA�g���|�[�g����.���z = " + l_aioSettleReportUnit.amount);
        
        //���ϘA�g���|�[�g����.������� = ���Z�@@�֘A�g���o����Params.�����敪
        l_aioSettleReportUnit.transactionStatus = 
            l_bankCashTransferStatusRow.getTransactionStatus();
        
        log.debug("���ϘA�g���|�[�g����.������� = " + l_aioSettleReportUnit.transactionStatus);
        
        //���ϘA�g���|�[�g����.���b�Z�[�W�R�[�h = 
        //�}���`�o���N���ϐ���T�[�r�XImpl.get���b�Z�[�W�R�[�h()�̖߂�l
        l_aioSettleReportUnit.messageCode = l_strMessageCode;
        
        log.debug("���ϘA�g���|�[�g����.���b�Z�[�W�R�[�h = " + l_aioSettleReportUnit.messageCode);
                
        //���ϘA�g���|�[�g����.���͋敪 = ���Z�@@�֘A�g���o����Params.���͋敪
        l_aioSettleReportUnit.inputDiv = l_bankCashTransferStatusRow.getInputDiv();
        
        log.debug("���ϘA�g���|�[�g����.���͋敪 = " + l_aioSettleReportUnit.inputDiv);
        
        log.exiting(STR_METHOD_NAME);
        return l_aioSettleReportUnit;        
    }
    
    /**
     * (create�\�[�g����������)<BR>
     * �\�[�g����������𐶐�����B <BR>
     * <BR>
     * �P�j��̕�����𐶐�����B <BR>
     * <BR>
     * �Q�j����.�\�[�g�L�[�̊e�v�f�ɂ��āA�ȉ��̏������s���B <BR>
     * <BR>
     * �Q�|�P�j�\�[�g�L�[.�L�[���� == �h�ڋq�R�[�h�h and �\�[�g�L�[.����/�~�� == �h�����h �̏ꍇ<BR> 
     * <BR>
     * �P�j�̕�����ɁA"account_code" ��ǉ�����B <BR>
     * <BR>
     * �Q�|�Q�j�\�[�g�L�[.�L�[���� == �h�ڋq�R�[�h�h and �\�[�g�L�[.����/�~�� == �h�~���h �̏ꍇ<BR>
     * <BR>
     * �P�j�̕�����ɁA"account_code desc" ��ǉ�����B <BR>
     * <BR>
     * �Q�|�R�j�\�[�g�L�[.�L�[���� == �h.com�f�r�b�g���ώ���ԍ��h and <BR>
     *       �\�[�g�L�[.����/�~�� == �h�����h �̏ꍇ <BR>
     * <BR>
     * �P�j�̕�����ɁA"center_pay_id" ��ǉ�����B <BR>
     * <BR>
     * �Q�|�S�j�\�[�g�L�[.�L�[���� == �h.com�f�r�b�g���ώ���ԍ��h and <BR>
     *       �\�[�g�L�[.����/�~�� == �h�~���h �̏ꍇ <BR>
     * <BR>
     * �P�j�̕�����ɁA"center_pay_id desc" ��ǉ�����B <BR>
     * <BR>
     * �Q�|�T�j�\�[�g�L�[.�L�[���� == �h�����X�����ԍ��h and <BR>
     *       �\�[�g�L�[.����/�~�� == �h�����h �̏ꍇ <BR>
     * <BR>
     * �P�j�̕�����ɁA"institution_code, branch_code, order_request_number" ��ǉ�����B <BR>
     * <BR>
     * �Q�|�U�j�\�[�g�L�[.�L�[���� == �h�����X�����ԍ��h and <BR>
     *       �\�[�g�L�[.����/�~�� == �h�~���h �̏ꍇ <BR>
     * <BR>
     * �P�j�̕�����ɁA"institution_code desc, branch_code desc, <BR>
     *      order_request_number desc" ��ǉ�����B<BR> 
     * <BR>
     * �Q�|�V�j�\�[�g�L�[.�L�[���� == �h��t�����h and �\�[�g�L�[.����/�~�� == �h�����h �̏ꍇ<BR> 
     * <BR>
     * �P�j�̕�����ɁA"order_date_time" ��ǉ�����B <BR>
     * <BR>
     * �Q�|�W�j�\�[�g�L�[.�L�[���� == �h��t�����h and �\�[�g�L�[.����/�~�� == �h�~���h �̏ꍇ <BR>
     * <BR>
     * �P�j�̕�����ɁA"order_date_time desc" ��ǉ�����B <BR>
     * <BR>
     * �Q�|�X�j�Y���̗v�f���ŏI�v�f�ł͂Ȃ��ꍇ <BR>
     * <BR>
     * �P�j�̕�����ɁA", " ��ǉ�����B <BR>
     * <BR>
     * �R�j�������ꂽ�������ԋp����B<BR>
     */
    protected String createSortCondString(WEB3AioSortKeyUnit[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortCondString(WEB3AioSortKeyUnit[] sortKeys)";
        log.entering(STR_METHOD_NAME);
        
        // �P�j��̕�����𐶐�����B
        StringBuffer l_strSortString = new StringBuffer();
        // �Q�j�\�[�g�L�[�z��̊e�v�f���Ɉȉ��̏������s���B
        int l_intLength = 0;
        if (l_sortKeys != null)
        {
            l_intLength = l_sortKeys.length;
        }
        //�Q�j����.�\�[�g�L�[�̊e�v�f�ɂ��āA�ȉ��̏������s���B 
        for (int i = 0; i < l_intLength; i++ )
        {
            //�Q�|�P�j�\�[�g�L�[.�L�[���� == �h�ڋq�R�[�h�h and 
            //       �\�[�g�L�[.����/�~�� == �h�����h �̏ꍇ 
            //  �P�j�̕�����ɁA"account_code" ��ǉ�����B
            //�Q�|�Q�j�\�[�g�L�[.�L�[���� == �h�ڋq�R�[�h�h and 
            //       �\�[�g�L�[.����/�~�� == �h�~���h �̏ꍇ 
            //  �P�j�̕�����ɁA"account_code desc" ��ǉ�����B
            if (WEB3AioSettleReportSortkeyDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("account_code");
                }
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("account_code desc");
                }
            }

            //�Q�|�R�j�\�[�g�L�[.�L�[���� == �h.com�f�r�b�g���ώ���ԍ��h and 
            //      �\�[�g�L�[.����/�~�� == �h�����h �̏ꍇ 
            //  �P�j�̕�����ɁA"center_pay_id" ��ǉ�����B 
            //�Q�|�S�j�\�[�g�L�[.�L�[���� == �h.com�f�r�b�g���ώ���ԍ��h and 
            //      �\�[�g�L�[.����/�~�� == �h�~���h �̏ꍇ 
            //  �P�j�̕�����ɁA"center_pay_id desc" ��ǉ�����B
            else if (WEB3AioSettleReportSortkeyDef.COMDEBIT_NUMBER.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("center_pay_id");
                }
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("center_pay_id desc");
                }
            }
            //�Q�|�T�j�\�[�g�L�[.�L�[���� == �h�����X�����ԍ��h and 
            //      �\�[�g�L�[.����/�~�� == �h�����h �̏ꍇ 
            //  �P�j�̕�����ɁA"institution_code, branch_code, order_request_number" ��ǉ�����B 
            //�Q�|�U�j�\�[�g�L�[.�L�[���� == �h�����X�����ԍ��h and 
            //      �\�[�g�L�[.����/�~�� == �h�~���h �̏ꍇ 
            //  �P�j�̕�����ɁA"institution_code desc, branch_code desc, order_request_number desc" ��ǉ�����B
            else if (WEB3AioSettleReportSortkeyDef.SHOP_ORDERID.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("institution_code, branch_code, order_request_number");
                }
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("institution_code desc, branch_code desc, order_request_number desc");
                }                
            }
            //�Q�|�V�j�\�[�g�L�[.�L�[���� == �h��t�����h and �\�[�g�L�[.����/�~�� == �h�����h �̏ꍇ 
            //  �P�j�̕�����ɁA"order_date_time" ��ǉ�����B 
            //�Q�|�W�j�\�[�g�L�[.�L�[���� == �h��t�����h and �\�[�g�L�[.����/�~�� == �h�~���h �̏ꍇ 
            //  �P�j�̕�����ɁA"order_date_time desc" ��ǉ�����B
            else if (WEB3AioSettleReportSortkeyDef.RECEPTION_DATE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("order_date_time");
                }
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("order_date_time desc");
                }                
            }
            //�Q�|�X�j�Y���̗v�f���ŏI�v�f�ł͂Ȃ��ꍇ 
            //  �P�j�̕�����ɁA", " ��ǉ�����B
            if (i < (l_intLength - 1))
            {
                l_strSortString.append(",");
            }
        }
        //�R�j�������ꂽ�������ԋp����B
        log.debug(l_strSortString.toString());
        log.exiting(STR_METHOD_NAME);
        
        return l_strSortString.toString();
    }
}
@
