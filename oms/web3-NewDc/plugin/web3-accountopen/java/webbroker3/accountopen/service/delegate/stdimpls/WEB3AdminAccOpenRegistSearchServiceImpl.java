head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenRegistSearchServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐ\�������T�[�r�XImpl(WEB3AdminAccOpenRegistSearchServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AdminAccOpenRegistCsv;
import webbroker3.accountopen.define.WEB3AccOpenAccountOpenDivDef;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDownloadRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDownloadResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplySearchInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplySearchInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenRegistSearchService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ����J�ݐ\�������T�[�r�XImpl)<BR>
 * �Ǘ��Ҍ����J�ݐ\�������T�[�r�X�����N���X<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminAccOpenRegistSearchServiceImpl implements WEB3AdminAccOpenRegistSearchService 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenRegistSearchServiceImpl.class);
    
    /**
     * @@roseuid 41B45E720271
     */
    public WEB3AdminAccOpenRegistSearchServiceImpl() 
    {
     
    }
    
    /**
     * �����J�ݐ\���������������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\���������̓��N�G�X�g�̏ꍇ<BR> 
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g�̏ꍇ<BR> 
     * �@@�|get�_�E�����[�h�t�@@�C��()���R�[������B <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A155810029
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request instanceof WEB3AdminAccOpenApplySearchInputRequest)
        {
            log.debug("�Ǘ��Ҍ����J�ݐ\���������̓��N�G�X�g");
            WEB3AdminAccOpenApplySearchInputResponse l_response = 
                getInputScreen((WEB3AdminAccOpenApplySearchInputRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminAccOpenApplyDownloadRequest)
        {
            log.debug("�Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g");
            WEB3AdminAccOpenApplyDownloadResponse l_response = 
                getDownloadFile((WEB3AdminAccOpenApplyDownloadRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get���͉��)<BR>
     * �����J�ݐ\��������ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�\�������jget���͉�ʁv�Q�ƁB <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݐ\���������̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplySearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A1550101DF
     */
    protected WEB3AdminAccOpenApplySearchInputResponse getInputScreen(WEB3AdminAccOpenApplySearchInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccOpenApplySearchInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate����(�@@�\�J�e�S���R�[�h�i=�����J�݁j : String, is�X�V�i=false�j : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, false);
        
        //1.3 createResponse( )
        WEB3AdminAccOpenApplySearchInputResponse l_response = 
            (WEB3AdminAccOpenApplySearchInputResponse)l_request.createResponse();
            
        Timestamp l_tsCurrentDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        //1.4 (*) �v���p�e�B�Z�b�g
        //�O�c�Ɠ�
        l_response.previousBizDate = WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1);
        
        //�O��
        l_response.previousDate =  WEB3DateUtility.toDay(WEB3DateUtility.addDay(l_tsCurrentDate, -1));
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �����J�ݐ\���_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�\�������jget�_�E�����[�h�t�@@�C���v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplyDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A1550101FE
     */
    protected WEB3AdminAccOpenApplyDownloadResponse getDownloadFile(WEB3AdminAccOpenApplyDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminAccOpenApplyDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        log.debug("1.1 validate( )");
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        log.debug("1.2 getInstanceFrom���O�C�����( )");
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h�i=�����J�݁j : String, is�X�V�i=false�j : boolean)
        log.debug("1.3 validate����(�@@�\�J�e�S���R�[�h�i=�����J�݁j : String, is�X�V�i=false�j : boolean)");
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN, false);
        
        //1.4 validate���X����(String[])
        log.debug("1.4 validate���X����(String[])");
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 create��������������(�Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g)
        log.debug("1.6 create��������������(�Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g)");
        String l_strQueryString = this.createQueryString(l_request);
        
        log.debug("1.7 create���������f�[�^�R���e�i(�Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g, String)");
        //1.7 create���������f�[�^�R���e�i(�Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g, String)
        String[] l_queryContainers = this.createQueryContainer(l_request, l_strInstitutionCode);
        
        //1.8 get�����J�݌����q(String, String[], String, Date, Date, String)
        log.debug("1.8 get�����J�݌����q(String, String[], String, Date, Date, String)");
        List l_lisExpAccountOpens = WEB3AccOpenExpAccountOpen.getExpAccountOpen(
            l_strQueryString, 
            l_queryContainers,
            "branch_code, infomation_claim_datetime asc",
            null,
            null,
            null);
       
        //1.9 �����J�ݐ\��CSV(String, String)
        log.debug("1.9 �����J�ݐ\��CSV(String, String)");
        WEB3AdminAccOpenRegistCsv l_csv = new WEB3AdminAccOpenRegistCsv(l_strInstitutionCode, l_request.accountType);
        
        //1.10 get�����J�݌����q()�߂�l�̊e�v�f����LOOP����
        //(*) get�����J�݌����q()�߂�l�̊e�v�f����LOOP�����B
        //  �A���Aget�����J�݌����q()�߂�l�̌�����
        //  ���N�G�X�g�f�[�^.�_�E�����[�h������葽���ꍇ�A
        //  �_�E�����[�h�����܂łƂ���B
        log.debug("1.10 get�����J�݌����q()�߂�l�̊e�v�f����LOOP����");
        int l_intExpAccountOpenCount = l_lisExpAccountOpens.size();
        int l_intDownloadNumber = 0;
        int l_intLoop = 0;
        if (l_request.downloadNumber != null)
        {
            l_intDownloadNumber = Integer.parseInt(l_request.downloadNumber);
        }
        
        if (l_intExpAccountOpenCount > l_intDownloadNumber)
        {
            l_intLoop = l_intDownloadNumber;
        }
        else
        {
            l_intLoop = l_intExpAccountOpenCount;
        }
        
        for (int i = 0; i < l_intLoop; i++)
        {
            //1.10.1 add���׍s( )
            int l_int = l_csv.addRow();
            
            //1.10.2 set���ڒl(int, �����J�݌����q)
            log.debug("1.10.1 set���ڒl(int, �����J�݌����q)");
            l_csv.setValue(l_int, (WEB3AccOpenExpAccountOpen)l_lisExpAccountOpens.get(i));
        }
        
        //1.11 getCSV�t�@@�C���s( )
        log.debug("1.11 getCSV�t�@@�C���s( )");
        String[] l_strLines = l_csv.getCsvFileLines();
        
        //1.12 createResponse( )
        WEB3AdminAccOpenApplyDownloadResponse l_response = (WEB3AdminAccOpenApplyDownloadResponse)l_request.createResponse();
        
        //1.13 �v���p�e�B�Z�b�g
        //�_�E�����[�h�t�@@�C��
        log.debug("l_intExpAccountOpenCount = " + l_intExpAccountOpenCount);
        log.debug("l_strLines.length = " + l_strLines.length);
        l_response.downloadFile = l_strLines;
        
        //�@@�����R�[�h���F�@@get�����J�݌����q()�߂�l�̗v�f��
        l_response.totalRecords = Integer.toString(l_intExpAccountOpenCount);
        
        //���ݓ���
        l_response.currentDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (create��������������)<BR>
     * ���������������ҏW����B <BR>
     * <BR>
     * �P�j�@@�߂�l���� <BR>
     * �@@�߂�l�̌�������������C���X�^���X�i�FString�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@�،���Џ����ǉ�<BR>
     * �@@�،���ЃR�[�h������ǉ�����B<BR>
     * <BR>
     * �@@" institution_code = ? "<BR>
     * <BR>
     * �R�j�@@���X�����ǉ�<BR>
     * �@@���X�R�[�h�z��̗v�f�����A���X�R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and �ibranch_code = ? or branch_code = ? ����j" <BR>
     * <BR>
     * �S�j�@@�����敪�����ǉ�<BR>
     * �@@�����敪������ǉ�����B <BR>
     * <BR>
     * �@@" and account_div = ? " <BR>
     * <BR>
     * �T�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null�j�̏ꍇ�A�����R�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and account_code >= ? " <BR>
     * <BR>
     * �U�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null�j�̏ꍇ�A�����R�[�h������<BR>
     * �ǉ�����B <BR>
     * <BR>
     * �@@" and account_code <= ? " <BR>
     * <BR>
     * �V�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����������i���j != null�j�̏ꍇ�A������������������<BR>
     * �ǉ�����B <BR>
     * <BR>
     * �@@" and to_char(infomation_claim_datetime, 'YYYYMMDD') >= ? " <BR>
     * <BR>
     * �W�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����������i���j != null�j�̏ꍇ�A������������������<BR>
     * �ǉ�����B <BR>
     * <BR>
     * �@@" and to_char(infomation_claim_datetime, 'YYYYMMDD') <= ? " <BR>
     * <BR>
     * �X�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����J�ݓ��i���j != null�j�̏ꍇ�A�����J�ݓ�������<BR>
     * �ǉ�����B <BR>
     * <BR>
     * �@@" and to_char(account_open_date, 'YYYYMMDD') >= ? " <BR>
     * <BR>
     * �P�O�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����J�ݓ��i���j != null�j�̏ꍇ�A�����J�ݓ�������<BR>
     * �ǉ�����B <BR>
     * <BR>
     * �@@" and to_char(account_open_date, 'YYYYMMDD') <= ? " <BR>
     * <BR>
     * �P�P�j�@@���ʃR�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.���ʃR�[�h�i���j != null�j�̏ꍇ�A���ʃR�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and acc_open_request_number >= ? " <BR>
     * <BR>
     * �P�Q�j�@@���ʃR�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.���ʃR�[�h�i���j != null�j�̏ꍇ�A���ʃR�[�h������ǉ�����B <BR>
     * <BR>
     * �@@" and acc_open_request_number <= ? " <BR>
     * <BR>
     * �P�R�j�@@�����J�݋敪�����ǉ����w�肪����ꍇ�̂� <BR>
�@@   *   �i���N�G�X�g�f�[�^.�����J�݋敪 != null�j�̏ꍇ�A�敪�l�ɂ��A������ǉ�����B <BR>
     * <BR>
�@@   *     [���N�G�X�g�f�[�^.�����J�݋敪�� "�����J�ݍς�"�̏ꍇ�n<BR> 
     * <BR> 
�@@   *     " and account_open_date is not null " <BR>
     * <BR> 
     *�@@  �m���N�G�X�g�f�[�^.�����J�݋敪�� "�������J��"�̏ꍇ�n<BR>  
     * <BR>
     *�@@   " and account_open_date is null " <BR>
     * <BR>
     * �P�S�j�@@�����I�v�V�����敪�����ǉ����w�肪����ꍇ�̂� <BR>
�@@   *   �i���N�G�X�g�f�[�^.�����I�v�V�����敪 != null�j�̏ꍇ�A<BR>
     *    �����I�v�V�����敪������ǉ�����B<BR> 
     * <BR> 
�@@   *    " and id_confirm_flag = ? " <BR>
     * <BR> 
     * �P�T�j�@@������C���X�^���X��ԋp <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return String
     * @@roseuid 41A1665F0070
     */
    protected String createQueryString(WEB3AdminAccOpenApplyDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminAccOpenApplyDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�߂�l����
        String l_strQueryString = "";
        
        //�Q�j�@@�،���Џ����ǉ�
        l_strQueryString += " institution_code = ? ";
        
        //�R�j�@@���X�����ǉ�
        String l_strBranchCodeCondition = "";
        int l_intBranchCodeCount = 0;
        if (l_request.branchCode != null)
        {
            l_intBranchCodeCount = l_request.branchCode.length;
        }
        
        if (l_intBranchCodeCount != 0)
        {
            l_strBranchCodeCondition += " and ( ";
            
            for (int i = 0; i < l_intBranchCodeCount; i++)
            {
                log.debug("�R�j�@@���X�����ǉ�");
                if ( i == 0)
                {
                    l_strBranchCodeCondition += " branch_code = ? ";
                }
                else
                {
                    l_strBranchCodeCondition += " or branch_code = ? ";
                }
            }
            
            l_strBranchCodeCondition += " ) ";
        }
        l_strQueryString += l_strBranchCodeCondition;
        
        //�S�j�@@�����敪�����ǉ�
        l_strQueryString += " and account_div = ? ";
        
        //�T�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.accountCodeFrom != null)
        {
            log.debug("�T�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_strQueryString += " and account_code >= ? ";
        }
        
        //�U�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.accountCodeTo != null)
        {
            log.debug("�U�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_strQueryString += " and account_code <= ? ";
        }
        
        //�V�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.infoClaimDateFrom != null)
        {
            log.debug("�V�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_strQueryString += " and to_char(infomation_claim_datetime, 'YYYYMMDD') >= ? ";
        }

        //�W�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.infoClaimDateTo != null)
        {
            log.debug("�W�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_strQueryString += " and to_char(infomation_claim_datetime, 'YYYYMMDD') <= ? ";
        }

        //�X�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.accountOpenDateFrom != null)
        {
            log.debug("�X�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_strQueryString += " and to_char(account_open_date, 'YYYYMMDD') >= ? ";
        }

        //�P�O�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.accountOpenDateTo != null)
        {
            log.debug("�P�O�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_strQueryString += " and to_char(account_open_date, 'YYYYMMDD') <= ? ";
        }

        //�P�P�j�@@���ʃR�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.requestNumberFrom != null)
        {
            log.debug("�P�P�j�@@���ʃR�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_strQueryString += " and acc_open_request_number >= ? ";
        }

        //�P�Q�j�@@���ʃR�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.requestNumberTo != null)
        {
            log.debug("�P�Q�j�@@���ʃR�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_strQueryString += " and acc_open_request_number <= ? ";
        }

        //�P�R�j �����J�݋敪�����ǉ����w�肪����ꍇ�̂�
        if (l_request.accountOpenDiv != null)
        {
            // [���N�G�X�g�f�[�^.�����J�݋敪�� "�����J�ݍς�"�̏ꍇ�n 
            if(WEB3AccOpenAccountOpenDivDef.OPEN_COMPLETE.equals(l_request.accountOpenDiv))
            {
                log.debug("�P�R�j �����J�݋敪�����ǉ����w�肪����ꍇ�̂�");
                log.debug("[���N�G�X�g�f�[�^.�����J�݋敪�� \"�����J�ݍς�\"�̏ꍇ�n");
                l_strQueryString += " and account_open_date is not null ";
            }
            
            //�m���N�G�X�g�f�[�^.�����J�݋敪�� "�������J��"�̏ꍇ�n
            if(WEB3AccOpenAccountOpenDivDef.NOT_OPEN.equals(l_request.accountOpenDiv))
            {
                log.debug("�P�R�j �����J�݋敪�����ǉ����w�肪����ꍇ�̂�");
                log.debug("[���N�G�X�g�f�[�^.�����J�݋敪�� \"�������J��\"�̏ꍇ�n"); 
                l_strQueryString += " and account_open_date is null ";                               
            }
        }


        //�P�S�j�@@�����I�v�V�����敪�����ǉ����w�肪����ꍇ�̂� 
        if (l_request.searchOptionDiv != null)
        {
            log.debug("�P�S�j�@@�����I�v�V�����敪�����ǉ����w�肪����ꍇ�̂�");  
            l_strQueryString += " and id_confirm_flag = ? ";                
        }

        log.exiting(STR_METHOD_NAME);

        //�P�T�j�@@������C���X�^���X��ԋp
        return l_strQueryString;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i��ҏW����B <BR>
     * <BR>
     * �P�j�@@�߂�l���� <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐� <BR>
     * <BR>
     * �Q�j�@@�،���Џ����ǉ�<BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�ɁA�،���ЃR�[�h��ǉ�����B<BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@�،���ЃR�[�h<BR>
     * <BR>
     * �R�j�@@���X�����ǉ�<BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�ɁA���X�R�[�h�z��̗v�f�������X�R�[�h��<BR>
     * �ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.���X�R�[�h[index]<BR>
     * <BR>
     * �S�j�@@�����敪�����ǉ�<BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�ɁA�����敪��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�����敪<BR>
     * <BR>
     * �T�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɍ����R�[�h��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j<BR>
     * <BR>
     * �U�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɍ����R�[�h��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�ڋq�R�[�h�i���j<BR>
     * <BR>
     * �V�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����������i���j != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɏ�������������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�����������i���j��YYYYMMDD�ɕҏW����������<BR>
     * <BR>
     * �W�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����������i���j != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɏ�������������ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�����������i���j��YYYYMMDD�ɕҏW����������<BR>
     * <BR>
     * �X�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����J�ݓ��i���j != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɍ����J�ݓ���ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�����J�ݓ��i���j��YYYYMMDD�ɕҏW����������<BR>
     * <BR>
     * �P�O�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.�����J�ݓ��i���j != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɍ����J�ݓ���ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.�����J�ݓ��i���j��YYYYMMDD�ɕҏW����������<BR>
     * <BR>
     * �P�P�j�@@���ʃR�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.���ʃR�[�h�i���j != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɏ��ʃR�[�h��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.���ʃR�[�h�i���j<BR>
     * <BR>
     * �P�Q�j�@@���ʃR�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�<BR>
     * �@@�i���N�G�X�g�f�[�^.���ʃR�[�h�i���j != null�j�̏ꍇ�A�߂�l�ҏW�p<BR>
     * �C���X�^���X�Ɏ��ʃR�[�h��ǉ�����B <BR>
     * <BR>
     * �@@[add()�Ɏw�肷�����] <BR>
     * �@@���N�G�X�g�f�[�^.���ʃR�[�h�i���j<BR>
     * <BR>
     * �P�R�j�@@�����I�v�V�����敪�����ǉ����w�肪����ꍇ�̂� <BR>
�@@   *   �i���N�G�X�g�f�[�^.�����I�v�V�����敪 != null�j�̏ꍇ�A<BR>
     *    �߂�l�ҏW�p�C���X�^���X�ɒl��ǉ�����B<BR> 
     * <BR>
�@@   *    [add()�Ɏw�肷�����] <BR>  
�@@   *    ���N�G�X�g�f�[�^.�����I�v�V�����敪 <BR>
     * <BR> 
     * �P�S�j�@@�z���ԋp <BR>
     * �@@�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B <BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@return String[]
     * @@roseuid 41A1665F007F
     */
    protected String[] createQueryContainer(WEB3AdminAccOpenApplyDownloadRequest l_request, String l_strInstitutionCode) 
    {
        final String STR_METHOD_NAME = " createQueryContainer(WEB3AdminAccOpenApplyDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�߂�l���� 
        ArrayList l_arrayList = new ArrayList();
        
        //�Q�j�@@�،���Џ����ǉ�
        l_arrayList.add(l_strInstitutionCode);
        
        //�R�j�@@���X�����ǉ�
        int l_intBranchCodeCount = 0;
        if (l_request.branchCode != null)
        {
            l_intBranchCodeCount = l_request.branchCode.length;
        }
        
        for (int i = 0; i < l_intBranchCodeCount; i++)
        {
            log.debug("�R�j�@@���X�����ǉ�");
            l_arrayList.add(l_request.branchCode[i]);
        }
        
        //�S�j�@@�����敪�����ǉ�
        l_arrayList.add(l_request.accountType);
        
        //�T�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.accountCodeFrom != null)
        {
            log.debug("�T�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_arrayList.add(l_request.accountCodeFrom);
        }
        
        //�U�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.accountCodeTo != null)
        {
            log.debug("//�U�j�@@�ڋq�R�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_arrayList.add(l_request.accountCodeTo);
        }
        
        //�V�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.infoClaimDateFrom != null)
        {
            log.debug("�V�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_arrayList.add(WEB3DateUtility.formatDate(l_request.infoClaimDateFrom, "yyyyMMdd"));
        }

        //�W�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.infoClaimDateTo != null)
        {
            log.debug("�W�j�@@�����������i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_arrayList.add(WEB3DateUtility.formatDate(l_request.infoClaimDateTo, "yyyyMMdd"));
        }

        //�X�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.accountOpenDateFrom != null)
        {
            log.debug("�X�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_arrayList.add(WEB3DateUtility.formatDate(l_request.accountOpenDateFrom, "yyyyMMdd"));
        }

        //�P�O�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.accountOpenDateTo != null)
        {
            log.debug("�P�O�j�@@�����J�ݓ��i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_arrayList.add(WEB3DateUtility.formatDate(l_request.accountOpenDateTo, "yyyyMMdd"));
        }

        //�P�P�j�@@���ʃR�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.requestNumberFrom != null)
        {
            log.debug("�P�P�j�@@���ʃR�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_arrayList.add(l_request.requestNumberFrom);
        }

        //�P�Q�j�@@���ʃR�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�
        if (l_request.requestNumberTo != null)
        {
            log.debug("�P�Q�j�@@���ʃR�[�h�i���j�����ǉ� ���w�肪����ꍇ�̂�");
            l_arrayList.add(l_request.requestNumberTo);
        }

        //�P�R�j�@@�����I�v�V�����敪�����ǉ����w�肪����ꍇ�̂�
        if (l_request.searchOptionDiv != null)
        {
            log.debug("�P�R�j�@@�����I�v�V�����敪�����ǉ����w�肪����ꍇ�̂�");  
            l_arrayList.add(l_request.searchOptionDiv);          
        }    
        
        //�P�S�j�@@�z���ԋp
        String[] l_queryContainers = new String[l_arrayList.size()];
        l_arrayList.toArray(l_queryContainers); 
        
        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }
}
@
