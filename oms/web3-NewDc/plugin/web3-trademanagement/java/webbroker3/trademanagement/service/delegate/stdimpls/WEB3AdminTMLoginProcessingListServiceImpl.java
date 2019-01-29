head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.19.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginProcessingListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���O�C�������ꗗ�T�[�r�XImpl (WEB3AdminTMLoginProcessingListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 ������ (���u) �V�K�쐬 ���f��No.005,007,009
Revision History : 2008/10/06 ������ (���u) �d�l�ύX ���f��No.012,No.014,No.015
Revision History : 2008/10/09 ������ (���u) �d�l�ύX ���f��No.016
Revision History : 2008/10/17 �юu�� (���u) �d�l�ύX ���f��018
*/

package webbroker3.trademanagement.service.delegate.stdimpls;
import java.util.ArrayList;
import java.util.List;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.LoginHistoryPastRow;
import webbroker3.gentrade.data.LoginHistoryRow;
import webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginProcessingListService;
import webbroker3.trademanagement.define.WEB3AdminTMKeyItemDef;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryReferenceUnit;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistorySortKey;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (���O�C�������ꗗ�T�[�r�XImpl)<BR>
 * �Ǘ��҃��O�C�������ꗗ�T�[�r�X�����N���X�B<BR>
 * <BR>
 * @@version 1.0
 * @@author ������
 */
public class WEB3AdminTMLoginProcessingListServiceImpl implements WEB3AdminTMLoginProcessingListService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginProcessingListServiceImpl.class);

    /**
     * @@roseuid 48D74D2502B3
     */
    public WEB3AdminTMLoginProcessingListServiceImpl()
    {

    }

    /**
     * �Ǘ��ҁE���O�C�������ꗗ���J�n����B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B <BR>
     * <BR>
     * ���Ǘ��ҁE���O�C�������ꗗ�������̓��N�G�X�g�̏ꍇ <BR>
     * �@@this.get�������͉��()���R�[������B <BR>
     * <BR>
     * ���Ǘ��ҁE���O�C�������ꗗ�������ʃ��N�G�X�g�̏ꍇ <BR>
     * �@@this.get�������ʉ��()���R�[������B <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C5C6D203C7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminTraderAdminLoginHistoryInputRequest)
        {
            //�Ǘ��ҁE���O�C�������ꗗ�������̓��N�G�X�g�̏ꍇ
            //this.get�������͉��()���R�[������B
            l_response = getSearchInputScreen(
                (WEB3AdminTraderAdminLoginHistoryInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminTraderAdminLoginHistoryListRequest)
        {
            //�Ǘ��ҁE���O�C�������ꗗ�������ʃ��N�G�X�g�̏ꍇ
            //this.get�������ʉ��()���R�[������B
            l_response = getSearchResultScreen(
                (WEB3AdminTraderAdminLoginHistoryListRequest)l_request);
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

    /**
     * (get�������͉��)<BR>
     * ���O�C�������ꗗ�̌������͉�ʂ̕\�����s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ��ҁE���O�C�������ꗗ)get�������́v�Q�ƁB <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * �Ǘ��ҁE���O�C�������ꗗ�\�����̓��N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminTraderAdminLoginHistoryInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE0DC0025E
     */
    protected WEB3AdminTraderAdminLoginHistoryInputResponse getSearchInputScreen(
        WEB3AdminTraderAdminLoginHistoryInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSearchInputScreen(WEB3AdminTraderAdminLoginHistoryInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTraderAdminLoginHistoryInputResponse l_response = null;

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, false);

        l_response = (WEB3AdminTraderAdminLoginHistoryInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�������ʉ��)<BR>
     * ���O�C�������ꗗ�̌������ʉ�ʂ̕\�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ��ҁE���O�C�������ꗗ)get�������ʁv�Q�ƁB <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * �ǊǗ��ҁE���O�C�������ꗗ�������ʃ��X�|���X�N���X�B<BR>
     * @@return WEB3AdminTraderAdminLoginHistoryListResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE0E6D02BF
     */
    protected WEB3AdminTraderAdminLoginHistoryListResponse getSearchResultScreen(
        WEB3AdminTraderAdminLoginHistoryListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSearchResultScreen(WEB3AdminTraderAdminLoginHistoryListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTraderAdminLoginHistoryListResponse l_response = null;

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, false);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administartor.getInstitutionCode();

        //IP�A�h���X
        String l_strIpAddress = l_request.ipAddress;
        //�ڋq�R�[�h
        String l_strAccountCode = l_request.accountCode;
        //���X�R�[�h
        String l_strBranchCode = l_request.branchCode;
        //���t
        String l_strSearchDate = l_request.searchDate;
        //����(��)
        String l_strStartTime = l_request.startTime;
        //����(��)�B
        String l_strEndTime = l_request.endTime;

        //create��������������()
        String l_strQueryString = this.createQueryString(l_strIpAddress,
           l_strAccountCode, l_strBranchCode);

        //create���������f�[�^�R���e�i()
        Object[] l_strQueryDataContainers =
            this.createQueryDataContainer(
                l_strInstitutionCode,
                l_strSearchDate,
                l_strIpAddress,
                l_strBranchCode,
                l_strAccountCode,
                l_strStartTime,
                l_strEndTime);

        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = l_request.sortKeys;
        //create�\�[�g�L�[()
        String l_strSortCond = this.createSortCond(l_sortKeys);

        //get���O�C�������ꗗ()
        List l_lisLoginProessInfoList = this.getLoginProessInfoList(
            l_strQueryString,
            l_strSortCond,
            l_strQueryDataContainers);

        //WEB3PageIndexInfo
        int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisLoginProessInfoList,
            l_intRequestPageIndex,
            l_intRequestPageSize);

        //getListReturned( )
        List l_lisReturn = l_pageIndexInfo.getListReturned();

        //create���O�C���������ꗗ()
        WEB3AdminTraderAdminLoginHistoryReferenceUnit[] l_adminTraderAdminLoginHistoryReferenceUnit =
            this.createLoginProessInfoList(l_lisReturn);

        //getTotalRecords( )
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();
        //getPageIndex( )
        int l_intPageIndex = l_pageIndexInfo.getPageIndex();
        //getTotalPages( )
        int l_intTotalPageSize = l_pageIndexInfo.getTotalPages();
        //createResponse( )
        l_response =
            (WEB3AdminTraderAdminLoginHistoryListResponse)l_request.createResponse();
        //�v���p�e�B�Z�b�g
        l_response.pageIndex = String.valueOf(l_intPageIndex);
        l_response.totalPages = String.valueOf(l_intTotalPageSize);
        l_response.totalRecords = String.valueOf(l_intTotalRecords);
        l_response.loginHistoryList = l_adminTraderAdminLoginHistoryReferenceUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������)<BR>
     * �f�[�^���擾����ׂ̏����𐶐�����B <BR>
     * <BR>
     * �P�j�������i�[�ϐ���p�ӂ���B <BR>
     * <BR>
     * �Q�j�������̍쐬 <BR>
     * <BR>
     * �@@�Q�|�P�j �P�j�̕ϐ��ɉ��L�̕������ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@"institution_code = ? and <BR>
     * �@@�@@�@@�@@�@@�@@�@@login_timestamp >= to_date(' ? || ? || 00 ', 'yyyymmddhh24miss') and<BR>
     * �@@�@@�@@�@@�@@�@@�@@login_timestamp <= to_date(' ? || ? || 59 ', 'yyyymmddhh24miss')<BR>
     * <BR>
     * �@@�Q�|�Q�j ����.IP�A�h���X != null �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�P�j�̕ϐ��ɉ��L�̕������ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@"and ip_address = ?"<BR>
     * <BR>
     * �@@�Q�|�R�j ����.���X�R�[�h != null �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�P�j�̕ϐ��ɉ��L�̕������ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@"and branch_code like ? || '%'"<BR>
     * <BR>
     * �@@�Q�|�S�j ����.�ڋq�R�[�h != null �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�P�j�̕ϐ��ɉ��L�̕������ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@"and account_code like ? || '%' "<BR>
     * <BR>
     * �R�j �������ԋp����B <BR>
     * @@param l_strIpAddress - (IP�A�h���X)<BR>
     * ��ʂ�����͂��ꂽIP�A�h���X�B<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h�B<BR>
     * @@return String
     * @@roseuid 48BF458E03D8
     */
    private String createQueryString(
        String l_strIpAddress,
        String l_strAccountCode,
        String l_strBranchCode)
    {
        final String STR_METHOD_NAME =
            "createQueryString(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�������i�[�ϐ���p�ӂ���B
        StringBuffer l_sbQueryString = new StringBuffer();
        //�������̍쐬
        String l_strQueryString = "institution_code = ? and " +
            "login_timestamp >= to_date( ? || ? ||'00', 'yyyymmddhh24miss') and " +
            "login_timestamp <= to_date( ? || ? ||'59', 'yyyymmddhh24miss') ";
        l_sbQueryString.append(l_strQueryString);
        if (l_strIpAddress != null)
        {
            l_sbQueryString.append(" and ip_address = ?");
        }
        if (l_strBranchCode != null)
        {
            l_sbQueryString.append(" and branch_code like ? || '%'");
        }
        if (l_strAccountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%' ");
        }
        log.exiting(STR_METHOD_NAME);

        //�������ԋp����B
        return l_sbQueryString.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * �擾�����f�[�^�R���e�i�쐬�B <BR>
     * <BR>
     * �P�j ���ArrayList�𐶐�����B <BR>
     * �@@�@@ List lst = new ArrayList();<BR>
     * <BR>
     * �Q�j �P�j��ArrayList�Ɉȉ��̏���ǉ�����B<BR>
     * �@@�@@ �@@lst.add((����)�،���ЃR�[�h);<BR>
     * �@@�@@ �@@lst.add((����)���t);<BR>
     * �@@�@@ �@@lst.add((����)����(��));<BR>
     * �@@�@@ �@@lst.add((����)���t);<BR>
     * �@@�@@ �@@lst.add((����)����(��));<BR>
     * <BR>
     * �R�j ���͈����`�F�b�N�B<BR>
     * <BR>
     * �@@�@@�R�|�P�j ����.IP�A�h���X != null �̏ꍇ�A���L�̏��������s�B<BR>
     * �@@�@@�@@�@@�@@�@@lst.add((����)IP�A�h���X);<BR>
     * <BR>
     * �@@�@@�R�|�Q�j ����.���X�R�[�h != null �̏ꍇ�A���L�̏��������s�B<BR>
     * �@@�@@�@@�@@�@@�@@lst.add((����)���X�R�[�h);<BR>
     * <BR>
     * �@@�@@�R�|�R�j ����.�ڋq�R�[�h != null �̏ꍇ�A���L�̏��������s�B<BR>
     * �@@�@@�@@�@@�@@�@@lst.add((����)�ڋq�R�[�h);<BR>
     * <BR>
     * �S�j �ǉ�����ArrayList�ɑ΂��āAtoArray()�����s�B <BR>
     * �@@�@@ �z����擾���A�ԋp����B <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_strSearchDate - (���t)<BR>
     * ��ʓ��͂��ꂽ���t�B'yyyymmdd' �`���B<BR>
     * @@param l_strIpAddress - (IP�A�h���X)<BR>
     * ��ʂ�����͂��ꂽIP�A�h���X�B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h�B<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�B(6��)<BR>
     * @@param l_strStartTime - (����(��))<BR>
     * ��ʓ��͂��ꂽ����(��)�B'hh24mi'�`���B<BR>
     * @@param l_strEndTime - (����(��))<BR>
     * ��ʓ��͂��ꂽ����(��)�B'hh24mi'�`���B<BR>
     * @@return Object[]
     * @@roseuid 48BF459B01C1
     */
    private Object[] createQueryDataContainer(
        String l_strInstitutionCode,
        String l_strSearchDate,
        String l_strIpAddress,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strStartTime,
        String l_strEndTime)
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer(String, String, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        Object[] l_queryDatas = null;
        //���ArrayList�𐶐�����B
        List l_lisQueryData = new ArrayList();

        //��ArrayList�ɏ���ǉ�����B
        //lst.add((����)�،���ЃR�[�h);
        l_lisQueryData.add(l_strInstitutionCode);

        //lst.add((����)���t);
        l_lisQueryData.add(l_strSearchDate);

        //lst.add((����)����(��));
        l_lisQueryData.add(l_strStartTime);

        //lst.add((����)���t)
        l_lisQueryData.add(l_strSearchDate);

        //lst.add((����)����(��));
        l_lisQueryData.add(l_strEndTime);

        //���͈����`�F�b�N�B
        if (l_strIpAddress != null)
        {
            l_lisQueryData.add(l_strIpAddress);
        }
        if (l_strBranchCode != null)
        {
            l_lisQueryData.add(l_strBranchCode);
        }
        if (l_strAccountCode != null)
        {
            l_lisQueryData.add(l_strAccountCode);
        }

        l_queryDatas = new Object[l_lisQueryData.size()];
        l_lisQueryData.toArray(l_queryDatas);
        log.exiting(STR_METHOD_NAME);

        return l_queryDatas;
    }

    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�����������ҏW����B<BR>
     * <BR>
     * �e�[�u���񕨗������g�p���A�ȉ��̒ʂ�A�\�[�g����������iorder by��j��ҏW����B<BR>
     * <BR>
     * �P�j (����)�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A <BR>
     * �@@�@@�@@�\�[�g������������쐬����B<BR>
     * <BR>
     * �@@�P�|�P�j (����)�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A�쐬�����\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�P�|�P�|�P�j �\�[�g�L�[.�L�[���ځ��uIP�A�h���X�v�̏ꍇ�A�ȉ��̕�������쐬�����\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@"NVL( LPAD( SUBSTR( IP_ADDRESS, 1, INSTR( IP_ADDRESS, '.', 1, 1) -1), 3, '0'), '000') || <BR>
     * �@@�@@�@@ NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 1) +1, <BR>
     * �@@�@@�@@ INSTR( IP_ADDRESS, '.', 1, 2) - INSTR( IP_ADDRESS, '.', 1, 1)-1), 3, '0'), '000') || <BR>
     * �@@�@@�@@ NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 2) +1, <BR>
     * �@@�@@�@@ INSTR( IP_ADDRESS, '.', 1, 3) - INSTR( IP_ADDRESS, '.', 1, 2)-1), 3, '0'), '000') || <BR>
     * �@@�@@�@@ NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 3) +1), 3, '0'), '000')" <BR>
     * <BR>
     * �@@�@@�P�|�P�|�Q�j�@@�P�|�P�|�P�j�ȊO�̏ꍇ�A�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     * �@@�@@�쐬�����\�[�g����������ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�@@�E�u�����v ���@@���O�C��(�ߋ�)�����e�[�u��.���� <BR>
     * �@@�@@�@@�E�u�ڋq�R�[�h�v ���@@���O�C��(�ߋ�)�����e�[�u��.�ڋq�R�[�h <BR>
     * <BR>
     * �@@�P�|�Q�j �\�[�g�����ɊY������\�[�g������ҏW����B<BR>
     * �@@�@@�@@�@@�@@ �����Fasc <BR>
     * �@@�@@�@@�@@ �@@�~���Fdesc <BR>
     * <BR>
     * �Q�j �쐬�����\�[�g�����������ԋp����B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[�ێ��B<BR>
     * @@return String
     * @@roseuid 48BF45AC029C
     */
    private String createSortCond(WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortCond(String, WEB3AdminTraderAdminLoginHistorySortKey[])";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbSortCond = new StringBuffer();
        String l_strSortCond = " NVL( LPAD( SUBSTR( IP_ADDRESS, 1, INSTR( IP_ADDRESS," +
            " '.', 1, 1) -1), 3, '0'), '000') ||" +
            "NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 1) +1," +
            " INSTR( IP_ADDRESS, '.', 1, 2) - INSTR" +
            "( IP_ADDRESS, '.', 1, 1)-1), 3, '0'), '000') || " +
            "NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 2) +1," +
            " INSTR( IP_ADDRESS, '.', 1, 3) - INSTR" +
            "( IP_ADDRESS, '.', 1, 2)-1), 3, '0'), '000') || " +
            "NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 3) +1), 3, '0'), '000') ";
        //(����)�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A
        //�\�[�g������������쐬����B
        int l_intLength = l_sortKeys.length;
        for (int i = 0; i < l_intLength; i++)
        {
            //�P�|�P�j (����)�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A�쐬�����\�[�g����������ɒǉ�����B
            //�P�|�P�|�P�j �\�[�g�L�[.�L�[���ځ��uIP�A�h���X�v�̏ꍇ�A�ȉ��̕�������쐬�����\�[�g����������ɒǉ�����B
            if (WEB3AdminTMKeyItemDef.IP_ADDRESS.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append(l_strSortCond);
            }
            //�P�|�P�|�Q�j�@@�P�|�P�|�P�j�ȊO�̏ꍇ�A�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A
            //�쐬�����\�[�g����������ɒǉ�����B
            //�u�����v ���@@���O�C��(�ߋ�)�����e�[�u��.����
            else if (WEB3AdminTMKeyItemDef.LOGIN_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append(" login_timestamp ");
            }
            //�u�ڋq�R�[�h�v ���@@���O�C��(�ߋ�)�����e�[�u��.�ڋq�R�[�h
            else if (WEB3AdminTMKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append(" account_code ");
            }
            //�\�[�g�����ɊY������\�[�g������ҏW����B
            //     �����Fasc
            //�@@ �@@�~���Fdesc
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCond.append("ASC");
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCond.append("DESC");
            }
            if (i < l_intLength - 1)
            {
                l_sbSortCond.append(", ");
            }
        }
        log.exiting(STR_METHOD_NAME);

        //�쐬�����\�[�g�����������ԋp����
        return l_sbSortCond.toString();
    }

    /**
     * (get���O�C���������ꗗ)<BR>
     * ���O�C������ ���� ���O�C���ߋ��������烌�R�[�h���擾����B<BR>
     * <BR>
     * �P�j ���O�C���e�[�u���f�[�^�}�l�[�W��.getRowType()���\�b�h���R�[������B<BR>
     * �@@[getRowType()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@���t       �F (����)�f�[�^�R���e�i�@@�ɃZ�b�g�������t<BR>
     * �@@�@@����(��) �F (����)�f�[�^�R���e�i�@@�ɃZ�b�g��������(��) <BR>
     * <BR>
     * �Q�j Processors.getDefaultProcessor()���\�b�h���R�[������B<BR>
     * <BR>
     * �R�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * [doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@ rowType   �F�@@�P�j �̖߂�l<BR>
     * �@@�@@ where      �F�@@(����)��������<BR>
     * �@@�@@ orderBy    �F�@@(����)�����\�[�g����<BR>
     * �@@�@@ conditions �F�@@null<BR>
     * �@@�@@ bindVars   �F�@@(����)�f�[�^�R���e�i<BR>
     * <BR>
     * ���������ʂ�0���̏ꍇ�A�G���[��ԋp����B<BR>
     * �G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v�iBUSINESS_ERROR_01037�j�j<BR>
     * <BR>
     * �Q�j �P�j �̖߂�l��ԋp����B<BR>
     * @@param l_strSearchCondition - (��������)<BR>
     * ���������B<BR>
     * @@param l_strSearchSortCondtion - (�����\�[�g����)<BR>
     * �����\�[�g�����B<BR>
     * @@param l_dataContainers - (�f�[�^�R���e�i)<BR>
     * �f�[�e�R���e�i�B<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 48BF45BA03B3
     */
    private List getLoginProessInfoList(
        String l_strSearchCondition,
        String l_strSearchSortCondtion,
        Object[] l_dataContainers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLoginProessInfoList(String, String, Object[])";
        log.entering(STR_METHOD_NAME);

        //���O�C���e�[�u���f�[�^�}�l�[�W��.getRowType()���\�b�h���R�[������B
        WEB3TradeManagementLoginTableDataManager l_tradeManagementLoginTableDataManager =
            new WEB3TradeManagementLoginTableDataManager();
        RowType l_rowType =
            l_tradeManagementLoginTableDataManager.getRowType((String)l_dataContainers[1], (String)l_dataContainers[2]);
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords  = l_queryProcessor.doFindAllQuery(
                l_rowType,
                l_strSearchCondition,
                l_strSearchSortCondtion,
                null,
                l_dataContainers);
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
        if (l_lisRecords.isEmpty())
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (create���O�C���������ꗗ)<BR>
     * ���O�C���������Params���A�ꗗ�����쐬����B <BR>
     * <BR>
     * �P�j�@@ArrayList�I�u�W�F�N�g�̐����B <BR>
     * <BR>
     * �Q�j�@@(����)�y�[�W�����O�C���������̗v�f���ALoop�������s���B <BR>
     * <BR>
     * �@@�Q�|�P�j�@@���O�C���������N���X�̃I�u�W�F�N�g�𐶐��B <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�y�[�W�����O�C��������񂩂烍�O�C��(�ߋ�)�����e�[�u��Row�����o���B <BR>
     * <BR>
     * �@@�Q�|�R�j�@@�Q�|�P�j�Ő��������I�u�W�F�N�g�Ɉȉ��̓��e���Z�b�g����B <BR>
     * <BR>
     * �@@�@@�E ���O�C���������I�u�W�F�N�g.���O�C������    = <BR>
     * �@@�@@�@@�@@�@@�@@�@@���O�C��(�ߋ�)�����e�[�u��Row.get���O�C������<BR>
     * �@@�@@�E ���O�C���������I�u�W�F�N�g.IP�A�h���X       = <BR>
     * �@@�@@�@@�@@�@@�@@�@@���O�C��(�ߋ�)�����e�[�u��Row.getIP�A�h���X<BR>
     * �@@�@@�E ���O�C���������I�u�W�F�N�g.�����o�H�敪 = <BR>
     * �@@�@@�@@�@@�@@�@@�@@���O�C��(�ߋ�)�����e�[�u��Row.get�����o�H�敪<BR>
     * �@@�@@�E ���O�C���������I�u�W�F�N�g.���O�C������    = <BR>
     * �@@�@@�@@�@@�@@�@@�@@���O�C��(�ߋ�)�����e�[�u��Row.get���O�C������<BR>
     * �@@�@@�E ���O�C���������I�u�W�F�N�g.���X�R�[�h      = <BR>
     * �@@�@@�@@�@@�@@�@@�@@���O�C��(�ߋ�)�����e�[�u��Row.get���X�R�[�h<BR>
     * �@@�@@�E ���O�C���������I�u�W�F�N�g.�ڋq�R�[�h       = <BR>
     * �@@�@@�@@�@@�@@�@@�@@���O�C��(�ߋ�)�����e�[�u��Row.get�ڋq�R�[�h<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�P�j�Ő�������ArrayList�I�u�W�F�N�g�Ƀ��O�C���������I�u�W�F�N�g��add()����B <BR>
     * <BR>
     * �R�j�@@�P�j�Ő�������ArrayList�I�u�W�F�N�g��z��ɕϊ����ĕԋp����B<BR>
     * <BR>
     * @@param l_lisPagePrevLoginProcessInfos - (�y�[�W�����O�C���������)<BR>
     * �y�[�W�����O�C���������B<BR>
     * @@return WEB3AdminTraderAdminLoginHistoryReferenceUnit[]
     * @@roseuid 48C0B719017B
     */
    private WEB3AdminTraderAdminLoginHistoryReferenceUnit[] createLoginProessInfoList(
        List l_lisPagePrevLoginProcessInfos)
    {
        final String STR_METHOD_NAME = "createLoginProessInfoList(List)";
        log.entering(STR_METHOD_NAME);

        //ArrayList�I�u�W�F�N�g�̐����B
        List l_lisAdminTraderAdminLoginHistoryReferenceUnits = new ArrayList();
        int l_intLength = l_lisPagePrevLoginProcessInfos.size();
        //(����)�y�[�W�����O�C���������̗v�f���ALoop�������s���B
        for (int i = 0; i < l_intLength; i++)
        {
            WEB3AdminTraderAdminLoginHistoryReferenceUnit
                l_adminTraderAdminLoginHistoryReferenceUnit =
                    new WEB3AdminTraderAdminLoginHistoryReferenceUnit();
            if (l_lisPagePrevLoginProcessInfos.get(i) instanceof LoginHistoryRow)
            {
                LoginHistoryRow l_loginHistoryRow =
                    (LoginHistoryRow)l_lisPagePrevLoginProcessInfos.get(i);
                //�@@�E ���O�C���������I�u�W�F�N�g.���O�C������ = ���O�C�������e�[�u��Row.get���O�C������
                l_adminTraderAdminLoginHistoryReferenceUnit.loginDate =
                    l_loginHistoryRow.getLoginTimestamp();
                //���O�C���������I�u�W�F�N�g.IP�A�h���X = ���O�C�������e�[�u��Row.getIP�A�h���X
                l_adminTraderAdminLoginHistoryReferenceUnit.ipAddress =
                    l_loginHistoryRow.getIpAddress();
                //���O�C���������I�u�W�F�N�g.�����o�H�敪 = ���O�C�������e�[�u��Row.get�����o�H�敪
                l_adminTraderAdminLoginHistoryReferenceUnit.orderRootDiv =
                    l_loginHistoryRow.getOrderRootDiv();
                //���O�C���������I�u�W�F�N�g.���O�C������ = ���O�C�������e�[�u��Row.get���O�C������
                l_adminTraderAdminLoginHistoryReferenceUnit.loginResult =
                    l_loginHistoryRow.getLoginFailure();
                //���O�C���������I�u�W�F�N�g.���X�R�[�h = ���O�C�������e�[�u��Row.get���X�R�[�h
                l_adminTraderAdminLoginHistoryReferenceUnit.branchCode =
                    l_loginHistoryRow.getBranchCode();
                //���O�C���������I�u�W�F�N�g.�ڋq�R�[�h =
                //�@@�@@�@@���O�C�������e�[�u��Row.get�ڋq�R�[�h
                l_adminTraderAdminLoginHistoryReferenceUnit.accountCode =
                    l_loginHistoryRow.getAccountCode();
            }
            else if (l_lisPagePrevLoginProcessInfos.get(i) instanceof LoginHistoryPastRow)
            {
                LoginHistoryPastRow l_loginHistoryPastRow =
                    (LoginHistoryPastRow)l_lisPagePrevLoginProcessInfos.get(i);
                //�@@�E ���O�C���������I�u�W�F�N�g.���O�C������ = ���O�C��(�ߋ�)�����e�[�u��Row.get���O�C������
                l_adminTraderAdminLoginHistoryReferenceUnit.loginDate =
                    l_loginHistoryPastRow.getLoginTimestamp();
                //���O�C���������I�u�W�F�N�g.IP�A�h���X = ���O�C��(�ߋ�)�����e�[�u��Row.getIP�A�h���X
                l_adminTraderAdminLoginHistoryReferenceUnit.ipAddress =
                    l_loginHistoryPastRow.getIpAddress();
                //���O�C���������I�u�W�F�N�g.�����o�H�敪 = ���O�C��(�ߋ�)�����e�[�u��Row.get�����o�H�敪
                l_adminTraderAdminLoginHistoryReferenceUnit.orderRootDiv =
                    l_loginHistoryPastRow.getOrderRootDiv();
                //���O�C���������I�u�W�F�N�g.���O�C������ = ���O�C��(�ߋ�)�����e�[�u��Row.get���O�C������
                l_adminTraderAdminLoginHistoryReferenceUnit.loginResult =
                    l_loginHistoryPastRow.getLoginFailure();
                //���O�C���������I�u�W�F�N�g.���X�R�[�h = ���O�C��(�ߋ�)�����e�[�u��Row.get���X�R�[�h
                l_adminTraderAdminLoginHistoryReferenceUnit.branchCode =
                    l_loginHistoryPastRow.getBranchCode();
                //���O�C���������I�u�W�F�N�g.�ڋq�R�[�h =
                //�@@�@@�@@���O�C��(�ߋ�)�����e�[�u��Row.get�ڋq�R�[�h
                l_adminTraderAdminLoginHistoryReferenceUnit.accountCode =
                    l_loginHistoryPastRow.getAccountCode();
            }
            //��������ArrayList�I�u�W�F�N�g�Ƀ��O�C���������I�u�W�F�N�g��add()����B
            l_lisAdminTraderAdminLoginHistoryReferenceUnits.add(l_adminTraderAdminLoginHistoryReferenceUnit);
        }

        //��������ArrayList�I�u�W�F�N�g��z��ɕϊ����ĕԋp����B
        int l_intUnitsLength = l_lisAdminTraderAdminLoginHistoryReferenceUnits.size();
        WEB3AdminTraderAdminLoginHistoryReferenceUnit[] l_adminTraderAdminLoginHistoryReferenceUnits =
            new WEB3AdminTraderAdminLoginHistoryReferenceUnit[l_intUnitsLength];
        for (int i = 0; i < l_intUnitsLength; i++)
        {
            l_adminTraderAdminLoginHistoryReferenceUnits[i] =
                (WEB3AdminTraderAdminLoginHistoryReferenceUnit)
                    l_lisAdminTraderAdminLoginHistoryReferenceUnits.get(i);
        }

        log.exiting(STR_METHOD_NAME);

        return l_adminTraderAdminLoginHistoryReferenceUnits;
    }
}
@
