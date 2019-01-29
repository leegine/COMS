head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeConfirmServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���m�F�T�[�r�X�����N���X(WEB3AdminAioCashinNoticeConfirmServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ��O�� (���u) �V�K�쐬   
                   2004/10/27 ����(���u) ���r���[                                
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.data.DepositInformRow;
import webbroker3.aio.define.WEB3OutPutDivDef;
import webbroker3.aio.message.WEB3AdminAioCashinConfirmListRequest;
import webbroker3.aio.message.WEB3AdminAioCashinConfirmListResponse;
import webbroker3.aio.message.WEB3AioCashinNoticeUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinNoticeConfirmService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�����A���m�F�T�[�r�XImpl)<BR>
 * �����A���m�F�T�[�r�X�����N���X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioCashinNoticeConfirmServiceImpl extends WEB3ClientRequestService implements WEB3AdminAioCashinNoticeConfirmService 
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinNoticeConfirmServiceImpl.class);
       
    /**
     * �����A���m�F�T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����A���m�F�j�ꗗ��ʕ\���f�[�^�擾�v �Q��<BR>
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
        WEB3AdminAioCashinConfirmListRequest l_cashinConfirmListRequest = null;
        WEB3AdminAioCashinConfirmListResponse l_cashinConfirmListResponse = null;        
        
        if (l_request instanceof WEB3AdminAioCashinConfirmListRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u �����A���m�F���̓��N�G�X�g �v�̏ꍇ
            l_cashinConfirmListRequest = 
                (WEB3AdminAioCashinConfirmListRequest) l_request;
        }
        else
        {
            log.debug("Error[���͒l���s���ł�]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 ���͓��e�`�F�b�N 
        l_cashinConfirmListRequest.validate();
        
        //1.2 �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
               
        //1.3 �Y���̊Ǘ��҂����̋@@�\���g���邩�����`�F�b�N���s���B 
        //validate����(String, boolean)
        //[����] 
        //�@@�\�J�e�S���R�[�h�F �hB0301�h
        //is�X�V�F false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_IN_NOTICE,
            false);
        
        //1.4 �Y���̊Ǘ��҂��w�肵�����X�ɑ΂��鏈�����������邩�`�F�b�N����B 
        //validate���X����(String)
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        l_web3Administrator.validateBranchPermission(
            l_cashinConfirmListRequest.branchCode);
        
        //1.5 �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = null;
        l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        //1.6 �f�[�^�擾�����̕�����𐶐�����B
        //�m�����n 
        //���N�G�X�g�f�[�^�F �����A���m�F�ꗗ���N�G�X�g 

        String l_strWhereClause = this.createGetCondCharacterString(
            l_cashinConfirmListRequest);
        
        String l_strOrderBy = "worked_timestamp desc";
        
        //1.7 �f�[�^�擾�����̃f�[�^�R���e�i�𐶐�����B 
        //�m�����n 
        //���N�G�X�g�f�[�^�F ���N�G�X�g�f�[�^ 
        //�،���ЃR�[�h�F get�،���ЃR�[�h()�̖߂�l 
    
        Object l_bindVars[] = this.createGetCondDataContainer(
            l_cashinConfirmListRequest, 
            l_strInstitutionCode);
            
        int l_intPageSize = 
            Integer.parseInt(l_cashinConfirmListRequest.pageSize);
            
        int l_intPageIndex = 
            Integer.parseInt(l_cashinConfirmListRequest.pageIndex);        
        
        List l_lisRows = null;
        
        log.debug("���N�G�X�g�f�[�^.�o�͋敪 = " + 
                l_cashinConfirmListRequest.outputDiv);
        
        WEB3PageIndexInfo l_listViewPageIndexInfo = null;
        //���N�G�X�g�f�[�^.�o�͋敪 = '0'�i�ꗗ�j �̏ꍇ�A���{        
        if (WEB3OutPutDivDef.LIST_VIEW.equals(
                l_cashinConfirmListRequest.outputDiv))
        {        
            //1.8 1.9 �����A���e�[�u������A���R�[�h���擾����B 
            //�i�y�[�W���O��������j 
    
            //�m�����n 
            //Row�^�C�v�F �����A��Row.TYPE 
            //Where�F create�擾����������()�̖߂�l 
            //orderBy�F "worked_timestamp desc" 
            //condition�F null 
            //���X�g�F create�擾�����f�[�^�R���e�i()�̖߂�l 
            //�y�[�W�T�C�Y�F ���N�G�X�g�f�[�^.�y�[�W���\���s�� 
            //�y�[�W�ԍ��F ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� - 1        

            try
            {
                List l_lisDepositInformRows = 
                    Processors.getDefaultProcessor().doFindAllQuery(
                        DepositInformRow.TYPE,
                        l_strWhereClause,  
                        l_strOrderBy,                   
                        null,
                        l_bindVars);
//                        l_intPageSize, 
//                        l_intPageIndex - 1);
                        
                DepositInformRow[] l_depositInformRows = new DepositInformRow[l_lisDepositInformRows.size()];
                l_lisDepositInformRows.toArray(l_depositInformRows);
                
                l_listViewPageIndexInfo = new WEB3PageIndexInfo(
                        l_depositInformRows, 
                        l_intPageIndex, 
                        l_intPageSize);   
                
                l_lisRows = l_listViewPageIndexInfo.getListReturned();
                    
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
        }
        //���N�G�X�g�f�[�^.�o�͋敪 = '1'�iCSV�j �̏ꍇ�A���{
        else if (WEB3OutPutDivDef.CSV_VIEW.equals(
                    l_cashinConfirmListRequest.outputDiv))
        {
            //1.10 �����A���e�[�u������A���R�[�h���擾����B 
            //�i�y�[�W���O�����Ȃ��j 

            //�m�����n 
            //Row�^�C�v�F �����A��Row.TYPE 
            //Where�F create�擾����������()�̖߂�l 
            //orderBy�F "worked_timestamp desc" 
            //condition�F null 
            //���X�g�F create�擾�����f�[�^�R���e�i()�̖߂�l 

            try
            {
                l_lisRows = 
                    Processors.getDefaultProcessor().doFindAllQuery(
                        DepositInformRow.TYPE, 
                        l_strWhereClause,  
                        l_strOrderBy, 
                        null,
                        l_bindVars);
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
        }
                
        log.debug("search �����A���e�[�u��.size = " + l_lisRows.size());
        //1.11 ArrayList�C���X�^���X�𐶐�����B
        List l_lisAioCashinNoticeUnit = new ArrayList();
        
        //1.12 �擾�����v�f����Loop����

        for (int i = 0; i < l_lisRows.size(); i++)
        {
            DepositInformRow l_depositInformRow = (DepositInformRow) l_lisRows.get(i);
            
            log.debug("�����A���e�[�u��Row [" + i + "] = " + l_depositInformRow);
            
            //1.12.1 �����A�����׃C���X�^���X�𐶐�����B
            //�m�����n 
            //�،���ЁF �Ǘ���.get�،����()�̖߂�l 
            //�����A��Params�F �����A��Params�I�u�W�F�N�g 
            
            WEB3AioCashinNoticeUnit l_web3AioCashinNoticeUnit = 
                this.createCashinNoticeUnits(
                    l_web3Administrator.getInstitution(), l_depositInformRow);
            
            //1.12.2 �����A�����ׂ����X�g�ɒǉ�����B
            //�m�����n 
            //arg0�F �����A�����׃I�u�W�F�N�g 

            l_lisAioCashinNoticeUnit.add(l_web3AioCashinNoticeUnit); 
        }
        
        WEB3AioCashinNoticeUnit[] l_web3AioCashinNoticeUnits =  
            new WEB3AioCashinNoticeUnit[l_lisAioCashinNoticeUnit.size()];

        //1.13 �����A�����ׂ̔z����擾����B
        l_lisAioCashinNoticeUnit.toArray(l_web3AioCashinNoticeUnits);
        
        int l_intTotalPage = 0;
        int l_intTotalRecords = 0;
        
        String l_strFinInsNameDanji = null;
        
        //1.14 ���N�G�X�g�f�[�^.�o�͋敪 = '0'�i�ꗗ�j �̏ꍇ�A���{        
        if (WEB3OutPutDivDef.LIST_VIEW.equals(
                l_cashinConfirmListRequest.outputDiv))
        {
            //1.14.1 �\���y�[�W�ԍ����擾����B
            l_intPageIndex = l_listViewPageIndexInfo.getPageIndex();                    

            //1.14.2 ���y�[�W�����擾����B 
            l_intTotalPage = l_listViewPageIndexInfo.getTotalPages();
            
            //1.14.3 �����R�[�h�����擾����B
            l_intTotalRecords = l_listViewPageIndexInfo.getTotalRecords();
            
            log.debug("�\���y�[�W�ԍ� = " + l_intPageIndex);
            log.debug("���y�[�W�� = " + l_intTotalPage);
            log.debug("�����R�[�h�� = " + l_intTotalRecords);            
        }
        
        log.debug("���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h" + 
                l_cashinConfirmListRequest.finInstitutionCode);
        
        //1.15 ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h != null �̏ꍇ�A���{        
        if (l_cashinConfirmListRequest.finInstitutionCode != null)
        {
            //1.15.1 ���Z�@@�փC���X�^���X�𐶐�����B 

            //�m�����n 
            //�،���ЃR�[�h�F get�،���ЃR�[�h()�̖߂�l 
            //���Z�@@�փR�[�h�F ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h 
            
            WEB3GentradeFinInstitution l_web3GentradeFinInstitution = 
                new WEB3GentradeFinInstitution(
                    l_strInstitutionCode, 
                    l_cashinConfirmListRequest.finInstitutionCode);
            
            //1.15.2 ���Z�@@�֖��i�����j���擾����B
            l_strFinInsNameDanji = l_web3GentradeFinInstitution.getFinInstitutionNameKanji();
            log.debug("���Z�@@�֖��i�����j = " + l_strFinInsNameDanji);
        }
        
        //1.16 ���X�|���X�f�[�^�𐶐�����B
        l_cashinConfirmListResponse = (WEB3AdminAioCashinConfirmListResponse) 
            l_request.createResponse(); 
        
        //1.17  �v���p�e�B�Z�b�g
        
        //���X�|���X.���X�R�[�h = ���N�G�X�g�f�[�^.���X�R�[�h
        l_cashinConfirmListResponse.branchCode = 
            l_cashinConfirmListRequest.branchCode;       
            
        log.debug("���X�|���X.���X�R�[�h =" + l_cashinConfirmListResponse.branchCode);
        
        //���X�|���X.�ڋq�R�[�h�i���j = ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j
        l_cashinConfirmListResponse.minAccountCode = 
            l_cashinConfirmListRequest.minAccountCode;

        log.debug("���X�|���X.�ڋq�R�[�h�i���j=" + l_cashinConfirmListResponse.minAccountCode);
        
        //���X�|���X.�ڋq�R�[�h�i���j = ���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j
        l_cashinConfirmListResponse.maxAccountCode = 
            l_cashinConfirmListRequest.maxAccountCode;
        
        log.debug("���X�|���X.�ڋq�R�[�h�i���j=" + l_cashinConfirmListResponse.maxAccountCode);
        
        //���X�|���X.�A�������i���j = ���N�G�X�g�f�[�^.�A�������i���j
        l_cashinConfirmListResponse.minNoticeDate = 
            l_cashinConfirmListRequest.minNoticeDate;
            
        log.debug("���X�|���X.�A�������i���j=" + l_cashinConfirmListResponse.minNoticeDate);
        
        //���X�|���X.�A�������i���j = ���N�G�X�g�f�[�^.�A�������i���j
        l_cashinConfirmListResponse.maxNoticeDate = 
            l_cashinConfirmListRequest.maxNoticeDate;
            
        log.debug("���X�|���X.�A�������i���j=" + l_cashinConfirmListResponse.maxNoticeDate);
        
        //���X�|���X.�U�����i���j = ���N�G�X�g�f�[�^.�U�����i���j
        l_cashinConfirmListResponse.minTransferDate = 
            l_cashinConfirmListRequest.minTransferDate;
            
        log.debug("���X�|���X.�U�����i���j=" + l_cashinConfirmListResponse.minTransferDate);
        
        //���X�|���X.�U�����i���j = ���N�G�X�g�f�[�^.�U�����i���j
        l_cashinConfirmListResponse.maxTransferDate = 
            l_cashinConfirmListRequest.maxTransferDate;
            
        log.debug("���X�|���X.�U�����i���j=" + l_cashinConfirmListResponse.maxTransferDate);
        
        //���X�|���X.�U������Z�@@�փR�[�h = ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h
        l_cashinConfirmListResponse.finInstitutionCode = 
            l_cashinConfirmListRequest.finInstitutionCode;
            
        log.debug("���X�|���X.�U������Z�@@�փR�[�h =" + l_cashinConfirmListResponse.finInstitutionCode);
        
        //���X�|���X.�U������Z�@@�֖� = �i�ȉ��̂Ƃ���j
        //���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h = null �̏ꍇ�Anull

        if (l_cashinConfirmListRequest.finInstitutionCode == null)
        {
            l_cashinConfirmListResponse.finInstitutionName = null; 
        }
        //���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h != null �̏ꍇ�A
        //    ���Z�@@��.get���Z�@@�֖��i�����j()�̖߂�l
        else
        {
            l_cashinConfirmListResponse.finInstitutionName = l_strFinInsNameDanji;
        }
        log.debug("���X�|���X.�U������Z�@@�֖� =" + l_cashinConfirmListResponse.finInstitutionName);
        
        //���X�|���X.���� = ListPage.totalSize()�̖߂�l
        if (WEB3OutPutDivDef.LIST_VIEW.equals(
                l_cashinConfirmListRequest.outputDiv))
        {
            l_cashinConfirmListResponse.outputNumber = l_listViewPageIndexInfo.getTotalRecords() + "";        
        }
        else
        {
            l_cashinConfirmListResponse.outputNumber = l_lisRows.size() + "";
        }
        log.debug("���X�|���X.���� =" + l_cashinConfirmListResponse.outputNumber);
        
        //���X�|���X.�����A�����׈ꗗ = �����A�����ׁm�n
        l_cashinConfirmListResponse.cashinNoticeUnits = l_web3AioCashinNoticeUnits;
                    
        //���X�|���X.�o�͋敪 = ���N�G�X�g�f�[�^.�o�͋敪
        l_cashinConfirmListResponse.outputDiv = 
            l_cashinConfirmListRequest.outputDiv;
            
        //�����X�|���X.�o�͋敪 = '0'�i�ꗗ�j�̏ꍇ
        if (WEB3OutPutDivDef.LIST_VIEW.equals(
                l_cashinConfirmListRequest.outputDiv))
        {
            log.debug("���X�|���X.�o�͋敪 = '0'�i�ꗗ�j�̏ꍇ");
            l_cashinConfirmListResponse.pageIndex = l_intPageIndex + "";
                
            l_cashinConfirmListResponse.totalPages = l_intTotalPage + "";
            
            l_cashinConfirmListResponse.totalRecords = l_intTotalRecords + "";
        }
        //�����X�|���X.�o�͋敪 = '1'�iCSV�j�̏ꍇ
        //���X�|���X.�\���y�[�W�ԍ� = null
        //���X�|���X.���y�[�W�� = null
        //���X�|���X.�����R�[�h�� = null
        else 
        {
            log.debug("���X�|���X.�o�͋敪 = '1'�iCSV�j�̏ꍇ");
            l_cashinConfirmListResponse.pageIndex = null;
                
            l_cashinConfirmListResponse.totalPages = null;
            
            l_cashinConfirmListResponse.totalRecords = null;
        }
        
        log.debug("���X�|���X.�\���y�[�W�ԍ� =" + l_cashinConfirmListResponse.pageIndex);
        log.debug("���X�|���X.���y�[�W�� =" + l_cashinConfirmListResponse.totalPages);
        log.debug("���X�|���X.�����R�[�h�� =" + l_cashinConfirmListResponse.totalRecords);
        
        log.exiting(STR_METHOD_NAME);
        return l_cashinConfirmListResponse;
    }
    
    /**
     * (create�擾����������)<BR>
     * ���N�G�X�g�f�[�^����A�f�[�^�擾����������𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h�����𐶐�����B<BR>
     * <BR>
     *   ����������F "institution_code=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h��������<BR>
     * <BR>
     *   ����������F " and branch_code=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �S�j�ڋq�R�[�h��������<BR>
     * <BR>
     * �S�|�P�j����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != <BR>
     * null and ����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and account_code>=? and account_code<=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �S�|�Q�j����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j !=<BR>
     *  null and ����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j = null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and account_code=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �T�j�A��������������<BR>
     * <BR>
     * �T�|�P�j����.���N�G�X�g�f�[�^.�A�������i���j !=<BR>
     *  null and ����.���N�G�X�g�f�[�^.�A�������i���j != null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and worked_timestamp>=? and<BR> worked_timestamp<=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �T�|�Q�j����.���N�G�X�g�f�[�^.�A�������i���j = null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and worked_timestamp>=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �U�j�U������������<BR>
     * <BR>
     * �U�|�P�j����.���N�G�X�g�f�[�^.�U�����i���j != <BR>
     * null and ����.���N�G�X�g�f�[�^.�U�����i���j != null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and transfer_date>=? and transfer_date<=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �U�|�Q�j����.���N�G�X�g�f�[�^.�U�����i���j !=<BR>
     *  null and ����.���N�G�X�g�f�[�^.�U�����i���j = null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and transfer_date>=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �V�j�U������Z�@@�֏�������<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h != null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and fin_institution_code=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �W�j�������ꂽ�������ԋp����B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4108B52800FA
     */
    protected String createGetCondCharacterString(
        WEB3AdminAioCashinConfirmListRequest l_request) 
    {
        final String STR_METHOD_NAME = "createGetCondCharacterString(" + 
            "WEB3AdminAioCashinConfirmListRequest l_request)";
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
        
        //�Q�j�،���ЃR�[�h�����𐶐�����B
        l_strWhereCondition += "institution_code = ?";
        
        //�R�j���X�R�[�h��������
        l_strWhereCondition += " and branch_code = ?";
        
        //�S�j�ڋq�R�[�h�������� 
        //�S�|�P�j����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and 
        //       ����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null �̏ꍇ 
        //����������F " and account_code>=? and account_code<=?" 

        if (l_request.minAccountCode != null &&
            l_request.maxAccountCode != null)
        {        
            l_strWhereCondition += " and substr(account_code, 0, 6) >= ? and substr(account_code, 0, 6) <= ? ";
        }
        //�S�|�Q�j����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and 
        //       ����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j = null �̏ꍇ 
        //����������F " and account_code=?" 

        if (l_request.minAccountCode != null &&
            l_request.maxAccountCode == null)
        {
            l_strWhereCondition += " and substr(account_code, 0, 6) = ?";
        }

        //�T�j�A�������������� 
        
        //�T�|�P�j����.���N�G�X�g�f�[�^.�A�������i���j != null and 
        //       ����.���N�G�X�g�f�[�^.�A�������i���j != null �̏ꍇ 
        //����������F " and worked_timestamp>=? and worked_timestamp<=?" 
        
        if (l_request.minNoticeDate != null &&
            l_request.maxNoticeDate != null)
        {        
            l_strWhereCondition += " and worked_timestamp >= ? and worked_timestamp <= ?";
        }

        //�T�|�Q�j����.���N�G�X�g�f�[�^.�A�������i���j = null �̏ꍇ 
        //����������F " and worked_timestamp>=?" 
        
        if (l_request.maxNoticeDate == null)
        {
            l_strWhereCondition += " and worked_timestamp >= ?";
        }
        
        //�U�j�U������������ 

        //�U�|�P�j����.���N�G�X�g�f�[�^.�U�����i���j != null and 
        //       ����.���N�G�X�g�f�[�^.�U�����i���j != null �̏ꍇ 
        //����������F " and transfer_date>=? and transfer_date<=?" 
        
        if (l_request.minTransferDate != null &&
            l_request.maxTransferDate != null)
        {        
            l_strWhereCondition += 
                " and to_char(transfer_date, 'yyyyMMdd') >= to_char(?, 'yyyyMMdd')" +
                " and to_char(transfer_date, 'yyyyMMdd') <= to_char(?, 'yyyyMMdd')";
        }
        
        //�U�|�Q�j����.���N�G�X�g�f�[�^.�U�����i���j != null and 
        //       ����.���N�G�X�g�f�[�^.�U�����i���j = null �̏ꍇ 
        //����������F " and transfer_date>=?" 
        
        if (l_request.minTransferDate != null &&
            l_request.maxTransferDate == null)
        {        
            l_strWhereCondition += " and to_char(transfer_date, 'yyyyMMdd') >= to_char(?, 'yyyyMMdd')";
        }

        //�V�j�U������Z�@@�֏������� 

        //����.���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h != null �̏ꍇ 
        //����������F " and fin_institution_code=?" 
        
        if (l_request.finInstitutionCode != null)
        {
            l_strWhereCondition += " and fin_institution_code = ?";
        }
        
        log.debug("���������� = " + l_strWhereCondition);
        
        //�W�j�������ꂽ�������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strWhereCondition;
    }
    
    /**
     * (create�擾�����f�[�^�R���e�i)<BR>
     * ���N�G�X�g�f�[�^����A�擾�����̃f�[�^�R���e�i�𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * <BR>
     *   ����.�،���ЃR�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.���X�R�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j�ڋq�R�[�h<BR>
     * <BR>
     * �S�|�P�j����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != <BR>
     * null and ����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null �̏ꍇ<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j�A<BR>
     * ����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�|�Q�j����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != <BR>
     * null and ����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j = null �̏ꍇ<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�j�A��������������<BR>
     * <BR>
     * �T�|�P�j����.���N�G�X�g�f�[�^.�A�������i���j !=<BR>
     *  null and ����.���N�G�X�g�f�[�^.�A�������i���j != null �̏ꍇ<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�A�������i���j�A<BR>
     * ����.���N�G�X�g�f�[�^.�A�������i���j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�|�Q�j����.���N�G�X�g�f�[�^.�A�������i���j != <BR>
     * null and ����.���N�G�X�g�f�[�^.�A�������i���j = null �̏ꍇ<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�A�������i���j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�|�R�j����.���N�G�X�g�f�[�^.�A�������i���j = <BR>
     * null and ����.���N�G�X�g�f�[�^.�A�������i���j = null �̏ꍇ<BR>
     * <BR>
     * �T�|�R�|�P�j�O���c�Ɠ����Z�o����B<BR>
     * <BR>
     *    �c�Ɠ��v�Z.calc�c�Ɠ�()<BR>
     * <BR>
     *    �m�����n<BR>
     *    ����F �V�X�e���^�C���X�^���v<BR>
     *    ���Z�^���Z�����F -1<BR>
     * <BR>
     * �T�|�R�|�Q�j������t���؎������擾����B<BR>
     * <BR>
     *    ������ԊǗ�.get�s��ǎ���()<BR>
     * <BR>
     *    �m�����n<BR>
     *    �s��R�[�h�F 0�iDEFAULT)<BR>
     *    ���i�R�[�h�F 0�iDEFAULT)<BR>
     * <BR>
     * �T�|�R�|�R�j�O�c�Ɠ����t�ƒ��؎�����ҏW����������ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �U�j�U������������<BR>
     * <BR>
     * �U�|�P�j����.���N�G�X�g�f�[�^.�U�����i���j !=<BR>
     *  null and ����.���N�G�X�g�f�[�^.�U�����i���j != null �̏ꍇ<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�U�����i���j�A<BR>
     * ����.���N�G�X�g�f�[�^.�U�����i���j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �U�|�Q�j����.���N�G�X�g�f�[�^.�U�����i���j != <BR>
     * null and ����.���N�G�X�g�f�[�^.�U�����i���j = null �̏ꍇ<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�U�����i���j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �V�j�U������Z�@@�֏�������<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h != null �̏ꍇ<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �W�jArrayLis��..toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 4108BA730187
     */
    protected Object[] createGetCondDataContainer(
        WEB3AdminAioCashinConfirmListRequest l_request, 
        String l_strInstitutionCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createGetCondDataContainer(" + 
            "WEB3AdminAioCashinConfirmListRequest l_request, String l_strInstitutionCode";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j���ArrayList�𐶐�����B
        List l_lisDepositInform = null;
        l_lisDepositInform = new ArrayList();

        //�Q�j�،���ЃR�[�h 
        //����.�،���ЃR�[�h��ArrayList�ɒǉ�����B 
        l_lisDepositInform.add(l_strInstitutionCode);
        
        //�R�j���X�R�[�h 
        //����.���N�G�X�g�f�[�^.���X�R�[�h��ArrayList�ɒǉ�����B 
        l_lisDepositInform.add(l_request.branchCode);
        
        //�S�j�ڋq�R�[�h 

        //�S�|�P�j����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and 
        //       ����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null �̏ꍇ
        //����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j�A
        //����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j��ArrayList�ɒǉ�����B
        
        if (l_request.minAccountCode != null &&
            l_request.maxAccountCode != null)
        {
            l_lisDepositInform.add(l_request.minAccountCode);
            l_lisDepositInform.add(l_request.maxAccountCode);
        }
        
        //�S�|�Q�j����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null and 
        //       ����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j = null �̏ꍇ
        //       ����.���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j��ArrayList�ɒǉ�����B 

        if (l_request.minAccountCode != null &&
            l_request.maxAccountCode == null)
        {
            l_lisDepositInform.add(l_request.minAccountCode);
        }
    
        //�T�j�A�������������� 

        //�T�|�P�j����.���N�G�X�g�f�[�^.�A�������i���j != null and 
        //       ����.���N�G�X�g�f�[�^.�A�������i���j != null �̏ꍇ
        //����.���N�G�X�g�f�[�^.�A�������i���j�A
        //����.���N�G�X�g�f�[�^.�A�������i���j��ArrayList�ɒǉ�����B 

        if (l_request.minNoticeDate != null &&
            l_request.maxNoticeDate != null)
        {
            l_lisDepositInform.add(l_request.minNoticeDate);
            l_lisDepositInform.add(l_request.maxNoticeDate);
        }
        
        //�T�|�Q�j����.���N�G�X�g�f�[�^.�A�������i���j != null and 
        //       ����.���N�G�X�g�f�[�^.�A�������i���j = null �̏ꍇ 
        //����.���N�G�X�g�f�[�^.�A�������i���j��ArrayList�ɒǉ�����B 

        if (l_request.minNoticeDate != null &&
            l_request.maxNoticeDate == null)
        {
            l_lisDepositInform.add(l_request.minNoticeDate);
        }
        
        //�T�|�R�j����.���N�G�X�g�f�[�^.�A�������i���j = null and 
        //       ����.���N�G�X�g�f�[�^.�A�������i���j = null �̏ꍇ 
        //�T�|�R�|�P�j�O���c�Ɠ����Z�o����B
        //�c�Ɠ��v�Z.calc�c�Ɠ�() 
        //�m�����n 
        //����F �V�X�e���^�C���X�^���v 
        //���Z�^���Z�����F -1 
        
        if (l_request.minNoticeDate == null &&
            l_request.maxNoticeDate == null)
        {
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(
                    GtlUtils.getTradingSystem().getSystemTimestamp());
        
            Timestamp l_datBizDate = l_gentradeBizDate.roll(-1); 
            log.debug("�O���c�Ɠ� = " + l_datBizDate);
            
            //�T�|�R�|�Q�j������t���؎������擾����B 
    
            //������ԊǗ�.get�s��ǎ���()
            //�m�����n 
            //�s��R�[�h�F 0�iDEFAULT) 
            //���i�R�[�h�F 0�iDEFAULT) 
            
            String l_strTradeCloseTime =
                WEB3GentradeTradingTimeManagement.getTradeCloseTime(
                    WEB3MarketCodeDef.DEFAULT, WEB3ProductCodeDef.DEFAULT);
                                
            log.debug("������t���؎��� = " + l_strTradeCloseTime);
            
            //�T�|�R�|�R�j�O�c�Ɠ����t�ƒ��؎�����ҏW����������ArrayList�ɒǉ�����B

            String l_strDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd") +  
                l_strTradeCloseTime;
            
            log.debug("�O�c�Ɠ����t�ƒ��؎�����ҏW�������� = " + l_strDate);
            
            Date l_datWorkedTimestamp = WEB3DateUtility.getDate(l_strDate, "yyyyMMddHHmmss");
            
            l_lisDepositInform.add(l_datWorkedTimestamp);
                    
        }
        
        //�U�j�U������������ 

        //�U�|�P�j����.���N�G�X�g�f�[�^.�U�����i���j != null and 
        //       ����.���N�G�X�g�f�[�^.�U�����i���j != null �̏ꍇ
        //����.���N�G�X�g�f�[�^.�U�����i���j�A
        //����.���N�G�X�g�f�[�^.�U�����i���j��ArrayList�ɒǉ�����B
        
        if (l_request.minTransferDate != null &&
            l_request.maxTransferDate != null)
        {
            l_lisDepositInform.add(l_request.minTransferDate);
            l_lisDepositInform.add(l_request.maxTransferDate);   
        }
        
        //�U�|�Q�j����.���N�G�X�g�f�[�^.�U�����i���j != null and 
        //       ����.���N�G�X�g�f�[�^.�U�����i���j = null �̏ꍇ 
        //����.���N�G�X�g�f�[�^.�U�����i���j��ArrayList�ɒǉ�����B
        
        if (l_request.minTransferDate != null &&
            l_request.maxTransferDate == null)
        {
            l_lisDepositInform.add(l_request.minTransferDate);           
        }

        //�V�j�U������Z�@@�֏������� 

        //����.���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h != null �̏ꍇ 
        //����.���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h��ArrayList�ɒǉ�����B 
        
        if (l_request.finInstitutionCode != null)
        {
            l_lisDepositInform.add(l_request.finInstitutionCode);           
        }
        
        //�W�jArrayList.toArray()�̖߂�l��ԋp����B 
        Object[] l_bindVars = 
            new Object[l_lisDepositInform.size()];
            
        l_lisDepositInform.toArray(l_bindVars);
        
        //debug =====================
        if (l_bindVars != null)
        {
            for (int i = 0; i < l_bindVars.length; i++)
            {
                log.debug("l_bindVars[" + i + "] = " + l_bindVars[i]);
            }
        }
        //============================
        
        log.exiting(STR_METHOD_NAME);
        return l_bindVars;
    }
    
    /**
     * (create�����A������)<BR>
     * �����A�����׃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����A���m�F�jcreate�����A�����ׁv �Q��<BR>
     * @@param l_institution - (�،���ЃI�u�W�F�N�g)<BR>
     * @@param l_depositInformRow - (�����A��Row)<BR>
     * �����A��Params�I�u�W�F�N�g<BR>
     * @@return WEB3AioCashinNoticeUnit
     * @@throws WEB3BaseException
     * @@roseuid 4109C8B50240
     */
    protected WEB3AioCashinNoticeUnit createCashinNoticeUnits(
        Institution l_institution, 
        DepositInformRow l_depositInformRow) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCashinNoticeUnits(" + 
            "Institution l_institution, DepositInformRow l_depositInformRow)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null || l_depositInformRow == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 �����A�����׃C���X�^���X�𐶐�����B
        WEB3AioCashinNoticeUnit l_web3AioCashinNoticeUnit = 
            new WEB3AioCashinNoticeUnit();
        
        String l_strFamilyNameAlt1 = null;
        
        try
        {
            //1.2 �ڋq�C���X�^���X�𐶐�����B
            //�m�����n 
            //�،����ID�F ����.�،����.getInstitutionId()�̖߂�l 
            //���X�R�[�h�F ����.�����A��Params.���X�R�[�h 
            //�ڋq�R�[�h�F ����.�����A��Params.�ڋq�R�[�h 
            AccountManager l_accountManager = GtlUtils.getAccountManager();  
            WEB3GentradeMainAccount l_web3MainAcc = (WEB3GentradeMainAccount)
                l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(), 
                    l_depositInformRow.getBranchCode(), 
                    l_depositInformRow.getAccountCode()); 
            
            MainAccountRow l_mainAccountRow = (MainAccountRow)
                l_web3MainAcc.getDataSourceObject();
            
            //���O�i�c��1�j���擾����B
            l_strFamilyNameAlt1 = l_mainAccountRow.getFamilyNameAlt1();
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("�ڋq�C���X�^���X�𐶐�����:", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }    
        //1.3 ���Z�@@�փC���X�^���X�𐶐�����B 
        //�m�����n 
        //�،���ЃR�[�h�F ����.�،����.getInstitutionCode()�̖߂�l 
        //���Z�@@�փR�[�h�F ����.�����A��Params.���Z�@@�փR�[�h 
        WEB3GentradeFinInstitution l_web3GentradeFinInstitution = 
            new WEB3GentradeFinInstitution(
                l_institution.getInstitutionCode(), 
                l_depositInformRow.getFinInstitutionCode());
            
        //1.4 ���Z�@@�֖��i�����j���擾����B
        String l_strFinInsNameDanji = 
            l_web3GentradeFinInstitution.getFinInstitutionNameKanji();
        
        log.debug("���Z�@@�֖��i�����j = " + l_strFinInsNameDanji);
        
        //1.5 �v���p�e�B�Z�b�g
        //(*) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        
        //�����A������.���X�R�[�h = ����.�����A��Params.���X�R�[�h
        l_web3AioCashinNoticeUnit.branchCode = l_depositInformRow.getBranchCode();
        
        //�����A������.�ڋq�R�[�h = ����.�����A��Params.�ڋq�R�[�h
        l_web3AioCashinNoticeUnit.accountCode = l_depositInformRow.getAccountCode().substring(0,6);
        
        //�����A������.�ڋq�� = �ڋqParams.���O�i�c��1�j        
        l_web3AioCashinNoticeUnit.accountName = l_strFamilyNameAlt1;
        
        //�����A������.�����z = ����.�����A��Params.�����z
        l_web3AioCashinNoticeUnit.cashinAmt = l_depositInformRow.getAmount() + "";
        
        //�����A������.�U������Z�@@�֖� = ���Z�@@��.get���Z�@@�֖��i�����j()�̖߂�l
        l_web3AioCashinNoticeUnit.finInstitutionName = l_strFinInsNameDanji;
        
        //�����A������.�U���� = ����.�����A��Params.�U����
        l_web3AioCashinNoticeUnit.transferDate = l_depositInformRow.getTransferDate();
        
        //�����A������.�A������ = ����.�����A��Params.��Ɠ���
        l_web3AioCashinNoticeUnit.noticeDate = l_depositInformRow.getWorkedTimestamp();
        
        //�����A������.���ʃR�[�h = ����.�����A��Params.���ʃR�[�h
        l_web3AioCashinNoticeUnit.orderRequestNumber = 
            l_depositInformRow.getOrderRequestNumber();
        
        log.exiting(STR_METHOD_NAME);
        return l_web3AioCashinNoticeUnit;
    }
}
@
