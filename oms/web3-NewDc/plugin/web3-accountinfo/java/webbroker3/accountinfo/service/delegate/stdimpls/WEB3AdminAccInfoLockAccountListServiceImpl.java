head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ���޽�����N���X (WEB3AdminAccInfoLockAccountListImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.WEB3AccInfoLockAccYAccRegisterRelease;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.define.WEB3AccInfoSearchCondTypeDef;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AccInfoStopInfoUnit;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLockAccountListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3MngLockDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3YellowCustomerDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ���޽�����N���X )<BR>
 * �Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ���޽�����N���X <BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoLockAccountListServiceImpl extends WEB3AccInfoClientRequestService
    implements WEB3AdminAccInfoLockAccountListService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLockAccountListServiceImpl.class);
    
    /**
     * ���b�N�ڋq�o�^�⍇���ꗗ�������s���B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���̓��N�G�X�g�̏ꍇ <BR>
     * �|get���͉��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���N�G�X�g�̏ꍇ <BR>
     * �|get�ꗗ���()���R�[������B <BR>
     * @@param l_request<BR>
     * @@throws WEB3BaseException<BR>
     * @@return WEB3GenResponse<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminAccInfoLockAccountSearchInputRequest)
        {
            l_response = this.getInputScreenDisplay((WEB3AdminAccInfoLockAccountSearchInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoLockAccountSearchListRequest)
        {
            l_response = this.getLockAccountRegistList((WEB3AdminAccInfoLockAccountSearchListRequest)l_request);
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
     * (get���͉��)<BR>
     * ���b�N�ڋq�o�^�⍇���ꗗ���͉�ʕ\���������s���B<BR> 
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i���b�N�ڋq�o�^�⍇���ꗗ�jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request<BR>
     * @@throws WEB3BaseException<BR>
     * @@return WEB3AdminAccInfoLockAccountSearchInputResponse  <BR>
     */
    protected WEB3AdminAccInfoLockAccountSearchInputResponse getInputScreenDisplay(WEB3AdminAccInfoLockAccountSearchInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreenDisplay(WEB3AdminAccInfoLockAccountSearchInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���O�C�������Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�ڋq��{���i��{�j
        //is�X�V�F�@@false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminAccInfoLockAccountSearchInputResponse l_response = 
            (WEB3AdminAccInfoLockAccountSearchInputResponse)l_request.createResponse();
        
        //���X�|���X�f�[�^�v���p�e�B�ɒl���Z�b�g����B
        //(*)�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�A�l���Z�b�g����B
        //�o�^���i���j�F�@@TradingSystem.getSystemTimestamp()�̂P�����O�i*1)
        //�@@�o�^���i���j�F�@@TradingSystem.getSystemTimestamp()
        Timestamp l_TimSystem = GtlUtils.getSystemTimestamp();
        
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_TimSystem);
        l_calendar.add(Calendar.MONTH, -1);
        Date l_datSystemFrom = l_calendar.getTime();
        
        l_response.registDateFrom = l_datSystemFrom ;        
        l_response.registDateTo = l_TimSystem;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���b�N�ڋq�o�^�⍇���ꗗ)<BR>
     * ���b�N�ڋq�o�^�⍇���ꗗ�\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�Ǘ��҂��q�l���i���b�N�ڋq�o�^�⍇���ꗗ�jget���b�N�ڋq�o�^�⍇���ꗗ�v�Q�ƁB<BR>
     * @@param l_request<BR>
     * @@throws WEB3BaseException<BR>
     * @@return WEB3AdminAccInfoLockAccountSearchListResponse  <BR>
     */
    protected WEB3AdminAccInfoLockAccountSearchListResponse getLockAccountRegistList(WEB3AdminAccInfoLockAccountSearchListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLockAccountRegistList(WEB3AdminAccInfoLockAccountSearchListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1���N�G�X�g�f�[�^�̐��������`�F�b�N����B         
        l_request.validate();
        
        //1.2���O�C�������Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3�Ǘ��҂̌����`�F�b�N���s���B
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        //1.4���X�������`�F�b�N����B
        l_administrator.validateBranchPermission(l_request.branchCode);
        //1.5�،���ЃR�[�h���擾����B 
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        //1.6
        /*
         * ���������������ҏW����B
         * [create��������������()�Ɏw�肷�����] 
         * ���X�R�[�h[]�F�@@���N�G�X�g�f�[�^.���X�R�[�h[] 
         * �o�^���i���j�F�@@���N�G�X�g�f�[�^.�o�^���i���j 
         * �o�^���i���j�F�@@���N�G�X�g�f�[�^.�o�^���i���j
         * ���������敪�F�@@���N�G�X�g�f�[�^.���������敪
         */
        String l_strQueryString = this.createQueryString(l_request.branchCode, 
            l_request.registDateFrom, 
            l_request.registDateTo,
            l_request.searchCondType);
        
        //1.7
        /*
         * ���������f�[�^�R���e�i��ҏW����B 
         * [create���������f�[�^�R���e�i()�Ɏw�肷�����] 
         * �،���ЃR�[�h�F�@@get�،���ЃR�[�h() 
         * ���X�R�[�h[]�F�@@���N�G�X�g�f�[�^.���X�R�[�h[] 
         * �J�n���F�@@���N�G�X�g�f�[�^.�J�n�� 
         * �I�����F�@@���N�G�X�g�f�[�^.�I���� 
         * ���������敪�F�@@���N�G�X�g�f�[�^.���������敪
         */
        List l_lisQueryContainer = this.createQueryContainer(l_strInstitutionCode, 
            l_request.branchCode, 
            l_request.registDateFrom, 
            l_request.registDateTo,
            l_request.searchCondType);
        
        //1.8
        /*
         * �\�[�g������ҏW����B
         * [create�\�[�g����()�Ɏw�肷�����]
         * �\�[�g�L�[�F���N�G�X�g�f�[�^.�\�[�g�L�[
         */
        String l_strSortCond = this.createSortCond(l_request.sortKeys);
        
        //1.9���b�N�qY�q�o�^�����ꗗList�i�FArrayList�j����
        List l_lisLockAccount = new ArrayList();
        
        /*1.10���b�N�ڋq�o�^�ꗗ���擾����B
         *[get���b�N�o�^�ڋq�ꗗ()�Ɏw�肷�����] 
         *    ��������������F�@@create��������������()�̖߂�l 
         *    ���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l 
         *    ���������\�[�g�����F�@@create�\�[�g����()�̖߂�l
         * 
         */     
        String[] l_strQueryContainer = new String [l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryContainer);
        List l_lisRegistAcc = 
            this.getLockRegistAccList(l_strQueryString, l_strQueryContainer, l_strSortCond);
        
        if (l_lisRegistAcc != null)
        {
            //1.11get���b�N�o�^�ڋq�ꗗ( )�̖߂�l�̊e�v�f����LOOP
            for (int i = 0; i < l_lisRegistAcc.size(); i++)
            {
                MainAccountRow l_mainAccountRow = (MainAccountRow)l_lisRegistAcc.get(i);
                WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountRow);
                
                /*1.11.1
                 * ��~���ꗗUnit���b�Z�[�W�f�[�^���쐬����B
                 * 
                 * [create��~���ꗗUnit()�Ɏw�肷�����] 
                 * �ڋq�F�@@get���b�N�o�^�ڋq�ꗗ�i�j�̖߂�l�̊e�v�f
                 */
                WEB3AccInfoStopInfoUnit l_stopInfoUnit = this.createStopInfoListUnit(l_mainAccount);
                //1.11.2
                l_lisLockAccount.add(l_stopInfoUnit);
            }
            
        }
        
        //1.12���X�|���X�f�[�^�𐶐�����B
        WEB3AdminAccInfoLockAccountSearchListResponse l_response = 
            (WEB3AdminAccInfoLockAccountSearchListResponse)l_request.createResponse();
        //1.13���b�Z�[�W (*)�v���p�e�B�Z�b�g
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_lisLockAccount,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));
                
        l_response.stopInfoList =
            (WEB3AccInfoStopInfoUnit[]) l_pageIndexInfo.getArrayReturned(
                WEB3AccInfoStopInfoUnit.class);
        
        //���X�|���X.���y�[�W��
        l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();
        //���X�|���X.�����R�[�h��
        l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
        //���X�|���X.�\���y�[�W�ԍ� 
        l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();  
        
        //���X�|���X�f�[�^�𐶐�����B
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create��������������)<BR>
     * ���������������ҏW����B <BR> 
     * <BR>
     * �P�j�@@�߂�l����  <BR>
     * �@@�߂�l�̌�������������C���X�^���X�i�FString�j�𐶐�  <BR>
     * <BR>
     * �Q�j�@@�،���Џ����ǉ� <BR>
     * �@@�،���ЃR�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" institution_code =  ? " <BR>
     * <BR>
     * �R�j�@@���X�����ǉ� <BR>
     * �@@���X������ǉ�����B���X�R�[�h[]�̗v�f����"?"��ҏW����B <BR>
     * <BR>
     * �@@" and branch_code in (?, ?,,,) " <BR>
     * <BR>
     * �S�j�@@�o�^���i���j�C�o�^���i���j�����ǉ�  <BR>
     * �@@�J�n���C�I�������w�肳��Ă���ꍇ�i�J�n�� != null && �I���� != null�j�A <BR>
     * �@@�\�����͈͎̔w��𕶎���C���X�^���X�ɒǉ�����B <BR>
     * <BR>
     * �@@" and to_char(enable_order_updated_timestamp,'YYYYMMDD') " + <BR>
     * �@@" between ? and ? " 
     * <BR>
     * �T�j�@@���������敪�ǉ� <BR>
     * �@@���������敪���w�肳��Ă���ꍇ�i�������� != null�j�A<BR> 
     * �@@���������敪�w��𕶎���C���X�^���X�ɒǉ�����B <BR>
     * <BR>
     * �@@�|���������敪 == �h1�FY�q�h�̏ꍇ <BR>
     * �@@�@@�@@" and yellow_customer = ? "<BR> 
     * <BR>
     * �@@�|���������敪 == �h2�F�Ǘ����b�N�h�̏ꍇ <BR>
     * �@@�@@�@@" and mng_lock_flag = ? " <BR>
     * <BR>
     * �@@�|���������敪 == �h3�F�x�X���b�N�h�̏ꍇ <BR>
     * �@@�@@�@@" and branch_lock = ? " <BR>
     * <BR>
     * �@@�|���������敪 == �h0�F�S�āh�̏ꍇ <BR>
     * �@@�@@�@@" and (yellow_customer = ? " + <BR>
     *       " or mng_lock_flag = ? " + <BR>
     *       " or  branch_lock = ? ) "  <BR>
     * <BR>
     * �U�j�@@������C���X�^���X��ԋp <BR>
     * <BR>
     * @@param l_strBranchCodes - ���X�R�[�h�z��<BR>
     * @@param l_datRegistDateFrom - �o�^���i���j<BR>
     * @@param l_datRegistDateTo - �o�^���i���j<BR>
     * @@param l_strSearchCondType - ���������敪<BR>
     * @@return String
     */
    protected String createQueryString(String[] l_strBranchCodes,
        Date l_datRegistDateFrom, Date l_datRegistDateTo, String l_strSearchCondType)
    {
        
        final String STR_METHOD_NAME = " createQueryString(String[] l_strBranchCodes, Date l_datRegistDateFrom, Date l_datRegistDateTo, String l_strSearchCondType)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�߂�l����  <BR>
        //* �@@�߂�l�̌�������������C���X�^���X�i�FString�j�𐶐�
        String l_strSearchCond;
        
       /*
        * �Q�j�@@�،���Џ����ǉ�<BR>
        * �@@�،���ЃR�[�h������ǉ�����B
        * <BR>
        * �@@" institution_code =  ? "
        */
        l_strSearchCond = " institution_code = ? ";
        
        //���X������ǉ�����B���X�R�[�h[]�̗v�f����"?"��ҏW����B
        
        if (l_strBranchCodes != null)
        {
            int l_intBranchCodesCnt = l_strBranchCodes.length;
            
            if (l_intBranchCodesCnt > 0)
            {
                StringBuffer l_sbQueryBranchCodes = new StringBuffer();
                                                
                for (int i = 0; i < l_intBranchCodesCnt; i++)
                {

                    if (i != 0)
                    {
                        l_sbQueryBranchCodes.append(", ");
                    }
                    l_sbQueryBranchCodes.append("?");
                    
                }                
                l_strSearchCond += " and branch_code in (" + l_sbQueryBranchCodes.toString() + ")";          
            }
        }

        
        /*
         * �S�j�@@�o�^���i���j�C�o�^���i���j�����ǉ�  
         *      * �@@�J�n���C�I�������w�肳��Ă���ꍇ�i�J�n�� != null && �I���� != null�j�A 
         *      * �@@�\�����͈͎̔w��𕶎���C���X�^���X�ɒǉ�����B 
         */
        if (l_datRegistDateFrom != null && l_datRegistDateTo != null)
        {
            l_strSearchCond += " and to_char(enable_order_updated_timestamp,'YYYYMMDD') " + " between ? and ? " ;
        }
        
        /*�T�j�@@���������敪�ǉ� 
         * �@@���������敪���w�肳��Ă���ꍇ�i�������� != null�j�A 
         * �@@���������敪�w��𕶎���C���X�^���X�ɒǉ�����B 
         */
        if (l_strSearchCondType != null)
        {
            /*
             *�|���������敪 == �h1�FY�q�h�̏ꍇ 
             * " and yellow_customer = ? " 
             *         
             */
            if (WEB3AccInfoSearchCondTypeDef.YACCOUNT.equals(l_strSearchCondType))
            {
                l_strSearchCond +=" and yellow_customer = ?";
            }
            
            /*
             * �|���������敪 == �h2�F�Ǘ����b�N�h�̏ꍇ 
             *" and mng_lock_flag = ? " 
             */
            else if (WEB3AccInfoSearchCondTypeDef.ADMINLOCK.equals(l_strSearchCondType))
            {
                l_strSearchCond +=" and mng_lock_flag = ?";
            }
            
            /*
             * �|���������敪 == �h3�F�x�X���b�N�h�̏ꍇ 
             * " and branch_lock = ? " 
             */
            else if (WEB3AccInfoSearchCondTypeDef.BRANCHLOCK.equals(l_strSearchCondType))
            {
                l_strSearchCond +=" and branch_lock = ?";
            }
            
           /* �|���������敪 == �h0�F�S�āh�̏ꍇ 
            * �@@�@@�@@" and (yellow_customer = ? " + 
            *       " or mng_lock_flag = ? " + 
            *       " or  branch_lock = ? ) " 
            */
            else if (WEB3AccInfoSearchCondTypeDef.ALL.equals(l_strSearchCondType))
            {
                l_strSearchCond +=" and (yellow_customer = ?" + " or mng_lock_flag = ?" + " or  branch_lock = ?" + ")";
            }
        }
                
        log.exiting(STR_METHOD_NAME);
        return l_strSearchCond;
    }
    
    /**
     * (create�����f�[�^�R���e�i )<BR>
     * ���������f�[�^�R���e�i��ҏW����B  <BR>
     * <BR>
     * �P�j�@@�߂�l����  <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�  <BR>
     * <BR>
     * �Q�j�@@�،���Џ����ǉ�  <BR>
     * �@@�،���ЃR�[�h�������ǉ�����B  <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR> 
     * �@@�،���ЃR�[�h<BR>  
     * <BR>
     * �R�j�@@���X�����ǉ�  <BR>
     * �@@���X�R�[�h[]�����ׂĒǉ�����B  <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]�@@ <BR> 
     * �@@���X�R�[�h <BR>
     * <BR>
     * �S�j�@@�o�^���i���j�C�o�^���i���j�����ǉ�  <BR>
     * �@@�o�^���i���j�C�o�^���i���j���w�肳��Ă���ꍇ�i�o�^���i���j != null && �o�^���i���j != null�j�A <BR> 
     * �@@�o�^���i���j�C�o�^���i���j�����X�g�ɒǉ�����B <BR> 
     * <BR>
     * �@@[add()�Ɏw�肷�����]  <BR>
     * �@@�o�^���i���j�𕶎���iyyyyMMdd�j�ɕϊ������l  <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����]  <BR>
     * �@@�o�^���i���j�𕶎���iyyyyMMdd�j�ɕϊ������l  <BR>
     * <BR>
     * �T�j�@@���������敪�ǉ� <BR>
     * �@@���������敪���w�肳��Ă���ꍇ�i���������敪 != null�j�A <BR>
     * �@@���������敪�w������X�g�ɒǉ�����B <BR>
     * <BR>
     * �@@�|���������敪 == �h1�FY�q�h�̏ꍇ <BR>
     * �@@�h1:Y�q�h�����X�g�ɒǉ�����B<BR> 
     * <BR>
     * �@@�|���������敪 == �h2�F�Ǘ����b�N�h�̏ꍇ <BR>
     * �@@�h1:���b�N�h�����X�g�ɒǉ�����B <BR>
     * <BR>
     * �@@�|���������敪 == �h3�F�x�X���b�N�h�̏ꍇ <BR>
     * �@@�h1:���b�N"�����X�g�ɒǉ�����B<BR> 
     * <BR>
     * �@@�|���������敪 == �h0�F�S�āh�̏ꍇ <BR>
     * �@@�h1:Y�q�h�A�h1:���b�N�h�A�h1:���b�N"�����X�g�ɒǉ�����B<BR> 
     * <BR>
     * �U�j�@@�z���ԋp  <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B  <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strBranchCodes - ���X�R�[�h�z��<BR>
     * @@param l_datRegistDateFrom - �o�^���i���j<BR>
     * @@param l_datRegistDateTo - �o�^���i���j<BR>
     * @@param l_strSearchCondType - ���������敪
     * @@throws WEB3BaseException
     * @@return List
     * @@roseuid 415103500340
     */
    protected List createQueryContainer(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes, 
        Date l_datRegistDateFrom, 
        Date l_datRegistDateTo,
        String l_strSearchCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createQueryContainer()";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_strInstitutionCode] = " + l_strInstitutionCode
                );
        }
        
        //�P�j�@@�߂�l����  <BR>
        //* �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐��߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�
        List l_lisQueryContainer = new ArrayList();
        
        //�Q�j�@@�،���Џ����ǉ�
        l_lisQueryContainer.add(l_strInstitutionCode);
        
        //�R�j�@@���X�����ǉ�             
        for (int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisQueryContainer.add(l_strBranchCodes[i]);
        }
        /*
         * �S�j�@@�o�^���i���j�C�o�^���i���j�����ǉ�  
         *      * �@@�o�^���i���j�C�o�^���i���j���w�肳��Ă���ꍇ�i�o�^���i���j != null && �o�^���i���j != null�j�A 
         *      * �@@�o�^���i���j�C�o�^���i���j�����X�g�ɒǉ�����B 
         *      * 
         *      * �@@[add()�Ɏw�肷�����]  
         *      * �@@�o�^���i���j�𕶎���iyyyyMMdd�j�ɕϊ������l  
         */
        if (l_datRegistDateFrom != null && l_datRegistDateTo != null)
        {
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datRegistDateFrom, "yyyyMMdd"));
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datRegistDateTo, "yyyyMMdd"));
        }
        
        if (l_strSearchCondType != null)
        {
            /*
             *�|���������敪 == �h1�FY�q�h�̏ꍇ 
             * " and yellow_customer = 1:Y�q " 
             *         
             */
            if (WEB3AccInfoSearchCondTypeDef.YACCOUNT.equals(l_strSearchCondType))
            {
                l_lisQueryContainer.add(WEB3YellowCustomerDef.YELLOW_CUSTOMER);
            }
            
            /*
             * �|���������敪 == �h2�F�Ǘ����b�N�h�̏ꍇ 
             *" and mng_lock_flag = 1:���b�N " 
             */
            else if (WEB3AccInfoSearchCondTypeDef.ADMINLOCK.equals(l_strSearchCondType))
            {
                l_lisQueryContainer.add(WEB3MngLockDef.LOCK);
            }
            
            /*
             * �|���������敪 == �h3�F�x�X���b�N�h�̏ꍇ 
             * " and branch_lock = 1:���b�N " 
             */
            else if (WEB3AccInfoSearchCondTypeDef.BRANCHLOCK.equals(l_strSearchCondType))
            {
                l_lisQueryContainer.add(WEB3MngLockDef.LOCK);
            }
            
           /* �|���������敪 == �h0�F�S�āh�̏ꍇ <BR>
            * �@@�@@�@@" and (yellow_customer = 1:Y�q " + 
            *       " or mng_lock_flag = 1:���b�N " + 
            *       " or  branch_lock = 1:���b�N ) " 
            */
            else if (WEB3AccInfoSearchCondTypeDef.ALL.equals(l_strSearchCondType))
            {
                l_lisQueryContainer.add(WEB3YellowCustomerDef.YELLOW_CUSTOMER);
                l_lisQueryContainer.add(WEB3MngLockDef.LOCK);
                l_lisQueryContainer.add(WEB3MngLockDef.LOCK);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisQueryContainer;     
    }
    
    /**
     * (create��~���ꗗUnit)<BR>
     * �����̃��b�N�ڋq�o�^�I�u�W�F�N�g���A��~���ꗗUnit���b�Z�[�W�f�[�^���쐬����B<BR> 
     * <BR>
     * �P�j�@@��~���ꗗUnit���� <BR>
     * �@@��~���ꗗUnit�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@�v���p�e�B�Z�b�g <BR>
     * �@@�P�j�ɂĐ���������~���ꗗUnit�Ɉȉ��̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@��~���ꗗUnit.���X�R�[�h = �ڋq.���X�R�[�h <BR>
     * �@@��~���ꗗUnit.�ڋq�R�[�h = �ڋq.�ڋq�R�[�h <BR>
     * �@@��~���ꗗUnit.�ڋq�� = �ڋq.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p����B<BR> 
     * �@@��~���ꗗUnit.�Ǘ���ID = �Ǘ���.get�Ǘ��҃R�[�h(*1)���擾�ł��Ȃ������ꍇ�Anull���Z�b�g����B <BR>
     * �@@��~���ꗗUnit.�o�^���� = �ڋq.��~�󋵍X�V���� <BR>
     * �@@��~���ꗗUnit.Y�q�敪 = �ڋq.Y�q�敪 <BR>
     * �@@��~���ꗗUnit.�Ǘ����b�N�敪 = �ڋq.�Ǘ����b�N <BR>
     * �@@��~���ꗗUnit.�Ǘ����b�N���R�t���O�i���֋��j = �ڋq.�Ǘ����b�N���R�t���O�i���֋��j <BR>
     * �@@��~���ꗗUnit.�Ǘ����b�N���R�t���O�i�ۏ؋������j = �ڋq.�Ǘ����b�N���R�t���O�i�ۏ؋������j <BR>
     * �@@��~���ꗗUnit.�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j =�@@�ڋq.�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j <BR>
     * �@@��~���ꗗUnit.�Ǘ����b�N���R�t���O�i�a�蒷�������ցj =�@@�ڋq.�Ǘ����b�N���R�t���O�i�a�蒷�������ցj <BR>
     * �@@��~���ꗗUnit.�Ǘ����b�N�����J�n�� = �ڋq.�Ǘ����b�N�����J�n��<BR> 
     * �@@��~���ꗗUnit.�Ǘ����b�N�����I���� = �ڋq.�Ǘ����b�N�����I���� <BR>
     * �@@��~���ꗗUnit.�x�X���b�N�敪 = �ڋq.�x�X���b�N <BR>
     * �@@��~���ꗗUnit.�����F�敪 = �ڋq.�����F�� <BR>
     * �@@��~���ꗗUnit.��~�󋵓o�^���R = �ڋq.��~�󋵓o�^���R <BR>
     * �@@��~���ꗗUnit.���b�N�q�o�^����SONAR��t�� = <BR>
     * �@@�@@�|�i���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*2) == null�j�̏ꍇ�Anull�B<BR> 
     * �@@�@@�|�ȊO�A���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*2).get�����敪()�B<BR> 
     * �@@��~���ꗗUnit.�x�q�o�^����SONAR��t�� = <BR>
     * �@@�@@�|�i���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*3) == null�j�̏ꍇ�Anull�B<BR> 
     * �@@�@@�|�ȊO�A���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*3).get�����敪()�B<BR> 
     * <BR>
     * �@@�@@�i*1�jAdministratorDao.findRowByInstitutionCodeAdministratorCode�i�،���ЃR�[�h�C�Ǘ��҃R�[�h�j�ɂĎ擾 <BR>
     * �@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@�ڋq.�،���ЃR�[�h <BR>
     * �@@�@@�@@�@@�@@�Ǘ��҃R�[�h�F�@@�ڋq.��~�󋵍X�V�҃R�[�h �@@<BR>�@@�@@�@@�@@ 
     * <BR>
     * �@@�@@�i*2)�i*3�j[get���b�N�q�x�q�o�^����()�Ɏw�肷�����]  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ڋq�F�@@�ڋq <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�f�[�^�R�[�h�F�@@(*2) �hGI846�h(WEB3HostRequestCodeDef) �^ (*3) �hGI847�h(WEB3HostRequestCodeDef) <BR>
     * <BR>
     * �R�j�v���p�e�B�Z�b�g������~���ꗗUnit��ԋp����B<BR>
     *
     *@@param �ڋq - �ڋq
     *@@throws WEB3BaseException
     *@@return WEB3AccInfoInsiderInfoUnit
     */
    protected WEB3AccInfoStopInfoUnit createStopInfoListUnit(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createInsiderInfoListUnit(WEB3GentradeMainAccount l_mainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }
        
        //�P�j�@@��~���ꗗUnit���� 
        //* �@@��~���ꗗUnit�𐶐�����B 
        WEB3AccInfoStopInfoUnit l_stopInfoUnit = new WEB3AccInfoStopInfoUnit();

        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        //�Q�j�@@�v���p�e�B�Z�b�g 
        //* �@@�P�j�ɂĐ���������~���ꗗUnit�Ɉȉ��̃v���p�e�B���Z�b�g����B
        
        //��~���ꗗUnit.���X�R�[�h = �ڋq.���X�R�[�h
        l_stopInfoUnit.branchCode = l_mainAccount.getBranch().getBranchCode();
        //��~���ꗗUnit.�ڋq�R�[�h = �ڋq.�ڋq�R�[�h
        l_stopInfoUnit.accountCode = l_mainAccount.getDisplayAccountCode();
        //��~���ꗗUnit.�ڋq�� = �ڋq.���O�i�c���j���ڋq���i�����j�Ƃ��Ďg�p����B
        l_stopInfoUnit.accountName = l_mainAccountRow.getFamilyName();
        
        //��~���ꗗUnit.�Ǘ���ID = �Ǘ���.get�Ǘ��҃R�[�h(*1)���擾�ł��Ȃ������ꍇ�Anull���Z�b�g����B
        //�i*1�jAdministratorDao.findRowByInstitutionCodeAdministratorCode�i�،���ЃR�[�h�C�Ǘ��҃R�[�h�j�ɂĎ擾 
        //* �@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@�ڋq.�،���ЃR�[�h 
        //* �@@�@@�@@�@@�@@�Ǘ��҃R�[�h�F�@@�ڋq.��~�󋵍X�V�҃R�[�h �@@
        AdministratorRow l_row = null;
        try
        {
            l_row = AdministratorDao.findRowByInstitutionCodeAdministratorCode(
                l_mainAccountRow.getInstitutionCode(), l_mainAccountRow.getEnableOrderLastUpdater());
        }
        catch (DataNetworkException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        if (l_row == null)
        {
            l_stopInfoUnit.managerID = null;
        }
        else
        {
            l_stopInfoUnit.managerID = l_row.getAdministratorCode();
        }

        //��~���ꗗUnit.�o�^���� = �ڋq.��~�󋵍X�V���� 
        l_stopInfoUnit.registDate = 
            l_mainAccountRow.getEnableOrderUpdatedTimestamp();
        
        //��~���ꗗUnit.Y�q�敪 = �ڋq.Y�q�敪 
        l_stopInfoUnit.yellowAccountDiv = l_mainAccountRow.getYellowCustomer();
        //��~���ꗗUnit.�Ǘ����b�N�敪 = �ڋq.�Ǘ����b�N
        l_stopInfoUnit.mngLockDiv = l_mainAccountRow.getMngLockFlag();
        //��~���ꗗUnit.�Ǘ����b�N���R�t���O�i���֋��j = �ڋq.�Ǘ����b�N���R�t���O�i���֋��j
        if (BooleanEnum.FALSE.equals(l_mainAccountRow.getMngLockFlagAdvance()))
        {
            l_stopInfoUnit.mngExpenseLockReasonFlag = false;
        }
        else
        {
            l_stopInfoUnit.mngExpenseLockReasonFlag = true;
        }
        //��~���ꗗUnit.�Ǘ����b�N���R�t���O�i�ۏ؋������j = �ڋq.�Ǘ����b�N���R�t���O�i�ۏ؋������j
        if (BooleanEnum.FALSE.equals(l_mainAccountRow.getMngLockFlagUnpayMargin()))
        {
            l_stopInfoUnit.mngDepositLockReasonFlag = false;
        }
        else
        {
            l_stopInfoUnit.mngDepositLockReasonFlag = true;
        }
        //��~���ꗗUnit.�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j =�@@�ڋq.�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j
        if (BooleanEnum.FALSE.equals(l_mainAccountRow.getMngLockFlagShortSecurity()))
        {
            l_stopInfoUnit.mngCollateralLockReasonFlag = false;
        }
        else
        {
            l_stopInfoUnit.mngCollateralLockReasonFlag = true;
        }
        //��~���ꗗUnit.�Ǘ����b�N���R�t���O�i�a�蒷�������ցj =�@@�ڋq.�Ǘ����b�N���R�t���O�i�a�蒷�������ցj
        if (BooleanEnum.FALSE.equals(l_mainAccountRow.getMngLockFlagUnsubstitDepo()))
        {
            l_stopInfoUnit.mngReceiptLockReasonFlag = false;
        }
        else
        {
            l_stopInfoUnit.mngReceiptLockReasonFlag = true;
        }
        //��~���ꗗUnit.�Ǘ����b�N�����J�n�� = �ڋq.�Ǘ����b�N�����J�n��
        l_stopInfoUnit.mngLockCancelStartDate = l_mainAccountRow.getMngLockOffStartDate();
        //��~���ꗗUnit.�Ǘ����b�N�����I���� = �ڋq.�Ǘ����b�N�����I����
        l_stopInfoUnit.mngLockCancelEndDate = l_mainAccountRow.getMngLockOffEndDate();
        //��~���ꗗUnit.�x�X���b�N�敪 = �ڋq.�x�X���b�N
        l_stopInfoUnit.branchLockDiv = l_mainAccountRow.getBranchLock();
        //��~���ꗗUnit.�����F�敪 = �ڋq.�����F�� 
        l_stopInfoUnit.orderPermitDiv = l_mainAccountRow.getOrderPermission();
        //��~���ꗗUnit.��~�󋵓o�^���R = �ڋq.��~�󋵓o�^���R
        l_stopInfoUnit.stopStateRegistReason = l_mainAccountRow.getLockRegistrationReason();
        /*
         * ��~���ꗗUnit.���b�N�q�o�^����SONAR��t�� = 
         *      �|�i���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*2) == null�j�̏ꍇ�Anull�B
         * �@@�@@ �|�ȊO�A���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*2).get�����敪()�B
         */

        WEB3AccInfoLockAccYAccRegisterRelease l_registerRelease = 
            WEB3AccInfoLockAccYAccRegisterRelease.getWEB3AccLockAccYAccRecordRelease(l_mainAccount, WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL);
        if (l_registerRelease == null)
        {
            l_stopInfoUnit.lockAccountSonarState = null;

        }
        else
        {
            l_stopInfoUnit.lockAccountSonarState = l_registerRelease.getStatus();

        }
        
        /*
         * ��~���ꗗUnit.�x�q�o�^����SONAR��t�� = 
         *      �|�i���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*3) == null�j�̏ꍇ�Anull�B
         *      �|�ȊO�A���b�N�q�x�q�o�^����.get���b�N�q�x�q�o�^����()(*3).get�����敪()�B
         */
        WEB3AccInfoLockAccYAccRegisterRelease l_release = 
            WEB3AccInfoLockAccYAccRegisterRelease.getWEB3AccLockAccYAccRecordRelease(l_mainAccount, WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL);
        if (l_release == null)
        {
            l_stopInfoUnit.yAccountSonarState = null;
        }
        else
        {
            l_stopInfoUnit.yAccountSonarState = l_release.getStatus();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_stopInfoUnit;
    }

    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�����������ҏW����B<BR>  
     * <BR>
     * �e�[�u���񕨗������g�p���A�ȉ��̒ʂ�A�\�[�g����������iorder by��j��ҏW����B<BR>  
     * <BR>
     * �P�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A<BR>  
     * �@@�@@�@@�\�[�g������������쐬����B <BR> 
     * �@@�P�|�P�j�\�[�g�L�[��ҏW  <BR>
     * �@@�@@�P�|�P�|�P�j�\�[�g�L�[ = �o�^�����̏ꍇ <BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�ڋq�}�X�^�e�[�u��.��~�󋵍X�V���� <BR>
     * <BR>
     * �@@�@@�P�|�P�|�Q�j�\�[�g���� = �ڋq�R�[�h�̏ꍇ  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ڋq�}�X�^�e�[�u��.�ڋq�R�[�h <BR> 
     * <BR>
     * �@@�@@�P�|�P�|�S�j�\�[�g���� = �Ǘ���ID  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ڋq�}�X�^.��~�󋵍X�V�҃R�[�h <BR>
     * <BR>
     * �@@�P�|�Q�j�\�[�g�����ɊY������\�[�g������ҏW����B  <BR>
     * �@@�@@�@@�@@�@@�����Fasc  <BR>
     * �@@�@@�@@�@@�@@�~���Fdesc  <BR>
     * <BR>
     * �Q�j �쐬�����\�[�g�����������ԋp����B<BR>  
     * @@param  l_sortKeys - �\�[�g�L�[<BR>
     * @@return String<BR>
     * @@roseuid 4164C934006A
     */
    protected String createSortCond(WEB3AccInfoSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3AccInfoSortKey[] l_sortKeys)";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKeys == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_sortKeys] = " + l_sortKeys
                );
        }
        
        StringBuffer l_sbQuery = new StringBuffer();
        
        int l_intLength = l_sortKeys.length;

        for (int i = 0; i < l_intLength; i++ )
        {
            
            if(WEB3AccInfoKeyItemDef.REGIST_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" enable_order_updated_timestamp ");
            }
            else if(WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" account_code ");
            }
            else if(WEB3AccInfoKeyItemDef.MANAGER_ID.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" enable_order_last_updater ");
            }
            else
            {
                continue;
            }
            String l_sort = null;
            if(WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sort = "Asc";
            }
            else
            {
                l_sort = "Desc";
            }
            l_sbQuery.append(l_sort);
            
            if(i < l_intLength - 1)
            {
                l_sbQuery.append(", ");
            }
                        
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_sbQuery.toString();

    }
    
    /**
     * (get���b�N�o�^�ڋq�ꗗ)<BR> 
     * �����̌��������ɊY������ڋqParams�̈ꗗ��ԋp����B <BR> 
     * <BR> 
     * �P�jArrayList�𐶐�����B  <BR> 
     * <BR> 
     * �Q�j�ڋq�s�I�u�W�F�N�g��List���擾����B <BR>  
     * �@@�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B <BR>  
     * <BR> 
     * �@@�@@[doFindAllQuery()�Ɏw�肷�����]  <BR> 
     * �@@�@@rowType�F�@@�ڋqRow.TYPE  <BR> 
     * �@@�@@where�F�@@��������������  <BR> 
     * �@@�@@orderBy�F�@@���������\�[�g���� <BR> 
     * �@@�@@conditions�F�@@null  <BR> 
     * �@@�@@bindVars�F�@@���������f�[�^�R���e�i  <BR> 
     * <BR> 
     * �@@�@@�������ʂ��擾�ł����ꍇ�A�������ʂ��  <BR> 
     * �@@�@@�ڋq�C���X�^���X�𐶐����AArrayList�ɒǉ�����B<BR>   
     * �@@�@@���������ʂ̐����C���X�^���X�𐶐����A�ǉ�����B<BR>   
     * <BR> 
     * �S�jArrayList��ԋp����B<BR>   
     * �@@��ArrayList�̗v�f����0�̏ꍇ�Anull��ԋp����B<BR> 
     * <BR> 
     * @@param  String - ��������������<BR> 
     * @@param  String[] - ���������f�[�^�R���e�i<BR> 
     * @@param  String - ���������\�[�g����<BR> 
     * @@throws WEB3BaseException <BR> 
     * return List
     */
    public List getLockRegistAccList(String l_strSearchCond, String[] l_strBindVars, String l_strOrderBy) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLockRegistAccList(String l_strSearchCond, String[] l_strBindVars, String l_strOrderBy)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jArrayList�𐶐�����B 
        List l_lisRecords = null;

        try
        {

            //�Q�j�ڋq�s�I�u�W�F�N�g��List���擾����B

            /*
             * QueryProcessor.doFindAllQuery()���\�b�h���R�[������B 
             *      [doFindAllQuery()�Ɏw�肷�����]  
             *      rowType�F�@@�ڋqRow.TYPE   
             *      where�F�@@��������������  
             *      orderBy�F�@@���������\�[�g���� 
             *      conditions�F�@@null  
             *      bindVars�F�@@���������f�[�^�R���e�i  
             */
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                MainAccountRow.TYPE,
                l_strSearchCond,
                l_strOrderBy,
                null,
                l_strBindVars);
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�s���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B 
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;

    }
}
@
