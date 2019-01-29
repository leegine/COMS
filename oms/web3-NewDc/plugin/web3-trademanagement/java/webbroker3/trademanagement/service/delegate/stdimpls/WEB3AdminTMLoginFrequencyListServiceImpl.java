head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.18.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginFrequencyListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IP�ʃ��O�C���񐔈ꗗ�T�[�r�XImpl(WEB3AdminTMLoginFrequencyListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 ����(���u) �V�K�쐬 ���f�� 005,007,008,009,011
Revision History : 2008/10/17 �юu�� (���u) �d�l�ύX ���f��018
*/

package webbroker3.trademanagement.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.LoginHistoryPastRow;
import webbroker3.gentrade.data.LoginHistoryRow;
import webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountReferenceUnit;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginFrequencyListService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (IP�ʃ��O�C���񐔈ꗗ�T�[�r�XImpl)<BR>
 * �Ǘ���IP�ʃ��O�C���񐔈ꗗ�����N���X�B<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminTMLoginFrequencyListServiceImpl implements WEB3AdminTMLoginFrequencyListService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminTMLoginFrequencyListServiceImpl.class);

    /**
     * (IP�A�h���X�ʃ��O�C��������)<BR>
     * IP�A�h���X�ʖ��̃��O�C�������񐔂�ێ��B<BR>
     * <BR>
     * key   �F ���O�C��������<BR>
     * value �F IP�A�h���X<BR>
     * <BR>
     * ���l�̃Z�b�g���@@�ɂ��ẮusetIP�ʃ��O�C��������()�v���Q�ƁB<BR>
     */
    private HashMap ipAddressLoginProcessingFrequency;

    /**
     * (���O�C�������񐔈ꗗ)<BR>
     * ���O�C�������񐔂̈ꗗ��ێ��B<BR>
     */
    private ArrayList loginProcessingFrequencyList;

    /**
     * (�d���J�E���^�[)<BR>
     * �d���J�E���^�[�B<BR>
     */
    private double duplicateCounter;

    /**
     * @@roseuid 48D75CD80127
     */
    public WEB3AdminTMLoginFrequencyListServiceImpl()
    {

    }

    /**
     * �Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ���J�n����B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A <BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B <BR>
     * <BR>
     * ���Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�������̓��N�G�X�g�̏ꍇ <BR>
     * �@@this.get�������͉��()���R�[������B <BR>
     * <BR>
     * ���Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�������ʃ��N�G�X�g�̏ꍇ <BR>
     * �@@this.get�������ʉ��()���R�[������B <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C5C5C002AC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //���N�G�X�g�f�[�^�̌^�ɂ��A
        //�ȉ��̃��\�b�h���Ăѕ�����B
        WEB3GenResponse l_response = null;

        //���Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�������̓��N�G�X�g�̏ꍇ
        //this.get�������͉��()���R�[������B
        if (l_request instanceof WEB3AdminTraderAdminLoginCountInputRequest)
        {
            l_response = this.getSearchInputScreen((WEB3AdminTraderAdminLoginCountInputRequest)l_request);
        }
        //���Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�������ʃ��N�G�X�g�̏ꍇ
        //this.get�������ʉ��()���R�[������B
        else if (l_request instanceof WEB3AdminTraderAdminLoginCountListRequest)
        {
            l_response = this.getSearchResultScreen((WEB3AdminTraderAdminLoginCountListRequest)l_request);
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
     * IP�ʃ��O�C���񐔈ꗗ�̌������͉�ʂ̕\�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ)get�������́v�Q�ƁB <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * �Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ���̓��N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminTraderAdminLoginCountInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C0EFB201C1
     */
    protected WEB3AdminTraderAdminLoginCountInputResponse getSearchInputScreen(
        WEB3AdminTraderAdminLoginCountInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchInputScreen(WEB3AdminTraderAdminLoginCountInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����()
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F �hC1301�h
        //is�X�V�F false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST,
            false);

        //createResponse()
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTraderAdminLoginCountInputResponse l_response =
            (WEB3AdminTraderAdminLoginCountInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�������ʉ��)<BR>
     * IP�ʃ��O�C���񐔈ꗗ�̌������ʉ�ʂ̕\�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ)get�������ʁv�Q�ƁB <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * �Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�m�F���N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminTraderAdminLoginCountListResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C0EEF000A1
     */
    protected WEB3AdminTraderAdminLoginCountListResponse getSearchResultScreen(
        WEB3AdminTraderAdminLoginCountListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchResultScreen(WEB3AdminTraderAdminLoginCountListRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        //�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        l_request.validate();

        //getInstanceFrom���O�C�����()
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F �hC1301�h
        //is�X�V�F false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST,
            false);

        //get�،���ЃR�[�h()
        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get�Ώ�IP�A�h���X�擾����(String, String, String, String)
        //[get�Ώ�IP�A�h���X�擾����()�ɃZ�b�g����p�����[�^]
        //�،���ЃR�[�h �F get�،���ЃR�[�h() �̖߂�l
        //���t �F ���N�G�X�g�f�[�^.���t
        //����(��) �F ���N�G�X�g�f�[�^.����(��)
        //����(��) �F ���N�G�X�g�f�[�^.����(��)
        HashSet l_hsObjectIPAddressProcesses =
            this.getObjectIPAddressProcess(
                l_strInstitutionCode,
                l_request.searchDate,
                l_request.startTime,
                l_request.endTime);

        //declaration�t�B�[���h�ϐ�(HashSet)
        //[declaration�t�B�[���h�ϐ�()�ɃZ�b�g����p�����[�^]
        //IP�A�h���X�ꗗ �F get�Ώ�IP�A�h���X�擾����() �̖߂�l
        this.declarationFieldVariable(l_hsObjectIPAddressProcesses);

        //createIP�ʃ��O�C��������(String, String, String, String, HashSet)
        //[createIP�ʃ��O�C��������()�ɃZ�b�g����p�����[�^]
        //�،���ЃR�[�h �F get�،���ЃR�[�h() �̖߂�l
        //���t �F ���N�G�X�g�f�[�^.���t
        //����(��) �F ���N�G�X�g�f�[�^.����(��)
        //����(��) �F ���N�G�X�g�f�[�^.����(��)
        //IP�A�h���X�ꗗ �F get�Ώ�IP�A�h���X�擾����() �̖߂�l
        this.createIPLoginProcessingFrequency(
            l_strInstitutionCode,
            l_request.searchDate,
            l_request.startTime,
            l_request.endTime,
            l_hsObjectIPAddressProcesses);

        //getIP�ʃ��O�C��������()
        //IP�A�h���X�Ƒ΂ɂȂ郍�O�C�������񐔂��Z�b�g�ŕۑ�����HashMap���擾����B
        HashMap l_hmIPLoginProcessingFrequencys = this.getIPLoginProcessingFrequency();

        //sort���O�C��������(Set)
        //[sort���O�C��������()�ɃZ�b�g����p�����[�^]
        //IP�A�h���X�ʃ��O�C�������񐔃R���N�V���� �F getIP�ʃ��O�C�������̖߂�l.keySet();
        double[] l_dblLoginProcessingFrequencys =
            this.sortLoginProcessingFrequency(l_hmIPLoginProcessingFrequencys.keySet());

        //getIP�ʃ��O�C���񐔖��׈ꗗ(double[], String)
        //[getIP�ʃ��O�C���񐔖��׈ꗗ()�ɃZ�b�g����p�����[�^]
        //����IP�ʃ��O�C�������� �F sort���O�C��������()�̖߂�l
        //�����N �F ���N�G�X�g�f�[�^.�����N
        List l_lisIPLoginFrequencyUnitList =
            this.getIPLoginFrequencyUnitList(
                l_dblLoginProcessingFrequencys,
                l_request.rank);

        //WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //[WEB3PageIndexInfo�i�j�Ɏw�肷�����]
        //l_list �F getIP�ʃ��O�C���񐔖��׈ꗗ()�̖߂�l
        //l_intRequestPageIndex �F�@@���N�G�X�g�f�[�^.�\���y�[�W�ԍ���int�^�ɕϊ������l
        //l_intRequestPageSize �F�@@���N�G�X�g�f�[�^.�y�[�W���\���s����int�^�ɕϊ������l
        WEB3PageIndexInfo l_lisViewPageIndexInfos = new WEB3PageIndexInfo(
            l_lisIPLoginFrequencyUnitList,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        //getListReturned()
        //���׃f�[�^�ꗗ�̃��X�g���擾����B
        List l_lisReturneds = l_lisViewPageIndexInfos.getListReturned();

        //getTotalRecords()
        //�����R�[�h�����擾�B
        int l_intTotalRecords = l_lisViewPageIndexInfos.getTotalRecords();

        //getPageIndex()
        //�\���y�[�W�ԍ����擾����B
        int l_intPageIndex = l_lisViewPageIndexInfos.getPageIndex();

        //getTotalPages()
        //�y�[�W���\���s�����擾����B
        int l_intTotalPages = l_lisViewPageIndexInfos.getTotalPages();

        //createResponse()
        WEB3AdminTraderAdminLoginCountListResponse l_response =
            (WEB3AdminTraderAdminLoginCountListResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�Ɉȉ��̒ʂ�v���p�e�B���Z�b�g����B
        //���X�|���X�f�[�^.�\���y�[�W�ԍ�    ���@@getPageIndex()�̖߂�l
        l_response.pageIndex = String.valueOf(l_intPageIndex);

        //���X�|���X�f�[�^.���y�[�W��   ���@@getPageSize()�̖߂�l
        l_response.totalPages = String.valueOf(l_intTotalPages);

        //���X�|���X�f�[�^.�����R�[�h��     ���@@getTotalRecords()�̖߂�l
        l_response.totalRecords = String.valueOf(l_intTotalRecords);

        //���X�|���X�f�[�^.IP�ʃ��O�C���񐔏��    ���@@getListReturned()�̖߂�l.toArray()
        WEB3AdminTraderAdminLoginCountReferenceUnit[] l_loginCountReferenceUnits =
            new WEB3AdminTraderAdminLoginCountReferenceUnit[l_lisReturneds.size()];
        l_lisReturneds.toArray(l_loginCountReferenceUnits);
        l_response.loginCountList = l_loginCountReferenceUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�Ώ�IP�A�h���X�擾����)<BR>
     * ���͂������t�A����(��)�A����(��)�͈̔͂ŃA�N�Z�X����IP�A�h���X���擾�B<BR>
     * <BR>
     * �P)��������������̍쐬�B<BR>
     * �@@�@@String�^�ϐ���p�ӂ��A���L�̕������i�[����B<BR>
     * �@@�@@"institution_code = ? and <BR>
     * �@@�@@�@@login_timestamp >= to_date(' ? || ? || 00 ', 'yyyymmddhh24miss') and<BR>
     * �@@�@@�@@login_timestamp <= to_date(' ? || ? || 59 ', 'yyyymmddhh24miss')<BR>
     * <BR>
     * �Q)�f�[�^�R���e�i�̍쐬�B<BR>
     * <BR>
     * �@@�Q�|�P) ���ArrayList�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@List lst = new ArrayList();<BR>
     * <BR>
     * �@@�Q�|�Q) �Q�|�P)��ArrayList�Ɉȉ��̏���ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@lst.add((����)�،���ЃR�[�h);<BR>
     * �@@�@@�@@�@@�@@�@@lst.add((����)���t);<BR>
     * �@@�@@�@@�@@�@@�@@lst.add((����)����(��));<BR>
     * �@@�@@�@@�@@�@@�@@lst.add((����)���t);<BR>
     * �@@�@@�@@�@@�@@�@@lst.add((����)����(��));<BR>
     * <BR>
     * �@@�Q�|�R) Object�^�̔z���p�ӂ��A<BR>
     * �@@�@@�@@�@@�@@�@@�Q�|�Q)��ArrayList�ɑ΂��āAtoArray() �̖߂�l���i�[����B<BR>
     * <BR>
     * �R)���O�C���e�[�u���f�[�^�}�l�[�W��.getRowType()���\�b�h���R�[������B<BR>
     * �@@[getRowType()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@���t       �F (����)���t<BR>
     * �@@�@@����(��) �F (����)����(��) <BR>
     * <BR>
     * �S�j Processors.getDefaultProcessor()���\�b�h���R�[������B<BR>
     * <BR>
     * �T�j QueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * [doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@ rowType �F  �R) �̖߂�l<BR>
     * �@@�@@ where     �F �P)�ō쐬����String<BR>
     * �@@�@@ bindVars �F �Q�|�R) ��Object�z��<BR>
     * <BR>
     * ���������ʂ�0���̏ꍇ�A�G���[��ԋp����B<BR>
     * �G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v�iBUSINESS_ERROR_01037�j<BR>
     * <BR>
     * �U�j IP�A�h���X�̎擾�B<BR>
     * <BR>
     * �@@�@@�U�|�P) HashSet�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@HashSet set = new HashSet();<BR>
     * <BR>
     * �@@�@@�U�|�Q) �T)�Ŏ擾����List�̗v�f�������A���L�̏�����Loop����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�U�|�Q�|�P) List����A�R�j �̖߂�l�ɑΉ�����Row�����o���B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�U�|�Q�|�Q) �U�|�P) ��HashSet�Ɉȉ��̓��e���Z�b�g����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@set.add(���O�C��(�ߋ�)�����e�[�u��Row.getIP�A�h���X)<BR>
     * <BR>
     * �@@�@@�U�|�R) �U�|�P)��HashSet�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_strSearchDate - (���t)<BR>
     * ���t�B(yyyymmdd �`��)<BR>
     * @@param l_strStartTime - (����(��))<BR>
     * ����(��)�B(hh24mi �`��)<BR>
     * @@param l_strEndTime - (����(��))<BR>
     * ����(��)�B(hh24mi �`��)<BR>
     * @@return HashSet
     * @@throws WEB3BaseException
     * @@roseuid 48C4AF2600B8
     */
    private HashSet getObjectIPAddressProcess(
        String l_strInstitutionCode,
        String l_strSearchDate,
        String l_strStartTime,
        String l_strEndTime) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getObjectIPAddressProcess(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P)��������������̍쐬�B
        //String�^�ϐ���p�ӂ��A���L�̕������i�[����B
        //"institution_code = ? and
        //login_timestamp >= to_date(' ? || ? || 00 ', 'yyyymmddhh24miss') and
        //login_timestamp <= to_date(' ? || ? || 59 ', 'yyyymmddhh24miss')
        String l_strWhere = "institution_code = ? "
            + "and login_timestamp >= to_date( ? || ? || '00', 'yyyymmddhh24miss') "
            + "and login_timestamp <= to_date( ? || ? || '59', 'yyyymmddhh24miss') ";

        //�Q)�f�[�^�R���e�i�̍쐬�B
        //�Q�|�P)���ArrayList�𐶐�����B
        List l_lisBindVars = new ArrayList();

        //�Q�|�Q) �Q�|�P)��ArrayList�Ɉȉ��̏���ǉ�����B
        //lst.add((����)�،���ЃR�[�h);
        l_lisBindVars.add(l_strInstitutionCode);

        //lst.add((����)���t);
        l_lisBindVars.add(l_strSearchDate);

        //lst.add((����)����(��));
        l_lisBindVars.add(l_strStartTime);

        //lst.add((����)���t);
        l_lisBindVars.add(l_strSearchDate);

        //lst.add((����)����(��));
        l_lisBindVars.add(l_strEndTime);

        //�Q�|�R) Object�^�̔z���p�ӂ��A
        //�Q�|�Q)��ArrayList�ɑ΂��āAtoArray() �̖߂�l���i�[����B
        Object[] l_objBindVars = l_lisBindVars.toArray();

        //�R)���O�C���e�[�u���f�[�^�}�l�[�W��.getRowType()���\�b�h���R�[������B
        //[getRowType()�ɃZ�b�g����p�����[�^]
        //���t �F (����)���t
        WEB3TradeManagementLoginTableDataManager l_loginTableDataManager =
            new WEB3TradeManagementLoginTableDataManager();
        RowType l_rowType = l_loginTableDataManager.getRowType(l_strSearchDate, l_strStartTime);

        List l_lisRows = null;
        try
        {
            //�S�jProcessors.getDefaultProcessor()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�T�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
            //rowType �F �R) �̖߂�l
            //where �F �P)�ō쐬����String
            //bindVars �F �Q�|�R) ��Object�z��
            l_lisRows = l_queryProcessor.doFindAllQuery(
                l_rowType,
                l_strWhere,
                l_objBindVars);
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

        //���������ʂ�0���̏ꍇ�A�G���[��ԋp����B
        //�G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v�iBUSINESS_ERROR_01037�j
        if (l_lisRows.isEmpty())
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        //�U�jIP�A�h���X�̎擾�B
        //�U�|�P) HashSet�I�u�W�F�N�g�𐶐�����B
        HashSet l_hsIpAddress = new HashSet();

        //�U�|�Q) �T)�Ŏ擾����List�̗v�f�������A���L�̏�����Loop����B
        int l_intSize = l_lisRows.size();
        for (int i = 0; i < l_intSize; i++)
        {
            //�U�|�Q�|�P) List����A�R�j �̖߂�l�ɑΉ�����Row�����o���B
            //�U�|�Q�|�Q) �U�|�P) ��HashSet�Ɉȉ��̓��e���Z�b�g����B
            Object l_objectRow = l_lisRows.get(i);
            if (l_objectRow instanceof LoginHistoryRow)
            {
                //set.add(���O�C�������e�[�u��Row.getIP�A�h���X)
                l_hsIpAddress.add(((LoginHistoryRow)l_objectRow).getIpAddress());
            }
            else if (l_objectRow instanceof LoginHistoryPastRow)
            {
                //set.add(���O�C���ߋ������e�[�u��Row.getIP�A�h���X)
                l_hsIpAddress.add(((LoginHistoryPastRow)l_objectRow).getIpAddress());
            }
        }

        log.exiting(STR_METHOD_NAME);

        //�U�|�R) �U�|�P)��HashSet�I�u�W�F�N�g��ԋp����B
        return l_hsIpAddress;
    }

    /**
     * (declaration�t�B�[���h�ϐ�)<BR>
     * IP�ʃ��O�C���񐔈ꗗ�T�[�r�XImpl�̃t�B�[���h�ϐ��̒�`�B���L�̏��������{����B<BR>
     * <BR>
     * �@@���ϐ��錾�́uIP�ʃ��O�C���񐔈ꗗ�T�[�r�XImpl�v��<BR>
     * �@@�@@�@@�n�܂�������ɂ��Ă������B<BR>
     * <BR>
     * �@@�P) IP�A�h���X�Ƒ΂ɂȂ郍�O�C�������񐔂��Z�b�g�ŕۑ�����HashMap<BR>
     *   �@@�@@HashMap IP�A�h���X�ʃ��O�C�������� = new HashMap();<BR>
     * <BR>
     * �@@�Q) ���O�C�������񐔂͕ʓr,ArrayList�Ɋi�[����B<BR>
     *   �@@�@@List ���O�C�������񐔈ꗗ = new ArrayList( (����)IP�A�h���X�ꗗ.size() );<BR>
     * <BR>
     * �@@�R) �d���񐔃J�E���g�ϐ����`�B<BR>
     * �@@�@@�@@double   �d���J�E���^�[  = 0.000001;<BR>
     * �@@�@@�@@�csetIP�ʃ��O�C��������()���\�b�h�ɂĎg�p<BR>
     * @@param l_hsIPAddressList - (IP�A�h���X�ꗗ)<BR>
     * IP�A�h���X�ꗗ�B<BR>
     * @@roseuid 48C4F05D0231
     */
    private void declarationFieldVariable(HashSet l_hsIPAddressList)
    {
        final String STR_METHOD_NAME = "declarationFieldVariable(HashSet)";
        log.entering(STR_METHOD_NAME);

        //�@@�P) IP�A�h���X�Ƒ΂ɂȂ郍�O�C�������񐔂��Z�b�g�ŕۑ�����HashMap
        //�@@�@@HashMap IP�A�h���X�ʃ��O�C�������� = new HashMap();
        ipAddressLoginProcessingFrequency = new HashMap();

        //�@@�Q) ���O�C�������񐔂͕ʓr,ArrayList�Ɋi�[����B
        //�@@�@@List ���O�C�������񐔈ꗗ = new ArrayList( (����)IP�A�h���X�ꗗ.size() );
        loginProcessingFrequencyList = new ArrayList(l_hsIPAddressList.size());

        //�@@�R) �d���񐔃J�E���g�ϐ����`�B
        //�@@�@@�@@double �d���J�E���^�[ = 0.000001;
        //�@@�@@�@@�csetIP�ʃ��O�C��������()���\�b�h�ɂĎg�p
        duplicateCounter = 0.000001;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createIP�ʃ��O�C��������)<BR>
     * IP�A�h���X���̃��O�C�������񐔂̍쐬�AIP�A�h���X���ɕR�t�����s���B<BR>
     * <BR>
     * �P) ��������������̍쐬�B<BR>
     *  �@@�@@String�^�ϐ���p�ӂ��A���L�̕������i�[����B<BR>
     * �@@�@@"institution_code = ? and <BR>
     * �@@�@@�@@ip_address = ? and <BR>
     * �@@�@@�@@login_timestamp >= to_date(' ? || ? || 00 ', 'yyyymmddhh24miss') and<BR>
     * �@@�@@�@@login_timestamp <= to_date(' ? || ? || 59 ', 'yyyymmddhh24miss')<BR>
     * <BR>
     * �Q)�f�[�^�R���e�i�̍쐬�O�����B<BR>
     * <BR>
     * �@@�Q�|�P) ���ArrayList�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@List lst = new ArrayList();<BR>
     * <BR>
     * �@@�Q�|�Q) �Q�|�P)��ArrayList�Ɉȉ��̏���ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@lst.add((����)�،���ЃR�[�h);<BR>
     * �@@�@@�@@�@@�@@�@@lst.add(null);<BR>
     * �@@�@@�@@�@@�@@�@@lst.add((����)���t);<BR>
     * �@@�@@�@@�@@�@@�@@lst.add((����)����(��));<BR>
     * �@@�@@�@@�@@�@@�@@lst.add((����)���t);<BR>
     * �@@�@@�@@�@@�@@�@@lst.add((����)����(��));<BR>
     * <BR>
     * �R)���O�C���e�[�u���f�[�^�}�l�[�W��.getRowType()���\�b�h���R�[������B<BR>
     * �@@[getRowType()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@���t       �F (����)���t<BR>
     * �@@�@@����(��) �F (����)����(��) <BR>
     * <BR>
     * �S�j Processors.getDefaultProcessor()���\�b�h���R�[������B<BR>
     * <BR>
     * �T) IP�A�h���X���̃��O�C�������񐔎擾�̑O���������B<BR>
     * �@@�@@Iterator it = (����)IP�A�h���X�ꗗ.Iterator();<BR>
     * <BR>
     * �U) �T)�Ő��������I�u�W�F�N�g�̗v�f�����݂������A���L�̏��������{�B <BR>
     * �@@�@@(IP�A�h���X�Ƒ΂ɂȂ郍�O�C�������񐔂��擾����܂�)<BR>
     * <BR>
     * �@@�@@�U�|�P) �Q�|�P)�ō쐬����ArrayList�ɑ΂��ĉ��L�̏��������{�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�܂��AString�^�ϐ���p�ӂ��A������ɂ������l(IP�A�h���X)���i�[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@String s = it.next().toString();<BR>
     * �@@�@@�@@�@@�@@�@@�@@lst.set(1, s);<BR>
     * <BR>
     * �@@�@@�U�|�Q) QueryProcessor.doGetCountQuery()���\�b�h���R�[������B<BR>
     * �@@�@@�@@[doGetCountQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@ rowType  �F  �R) �̖߂�l<BR>
     * �@@�@@�@@�@@�@@ where     �F  �P)�ō쐬����String<BR>
     * �@@�@@ �@@�@@�@@bindVars �F  �U�|�P) �������ArrayList�I�u�W�F�N�g.toArray(); �̖߂�l<BR>
     * <BR>
     * �@@�@@�U�|�R) setIP�ʃ��O�C��������()���\�b�h���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�� �U�|�Q) �̏����́A��ʓ��͂����w�莞�ԓ���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�A�N�Z�X�̂�����IP�A�h���X�̃��R�[�h������DB����擾�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����̒l���u���O�C�������񐔁v�ƂȂ�B<BR>
     * <BR>
     * �@@�@@�@@[setIP�ʃ��O�C��������()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@ IP�A�h���X�����O�C�������񐔁F�@@�U�|�Q) �̖߂�l<BR>
     * �@@�@@�@@�@@�@@ IP�A�h���X�F�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@  �U�|�P) �Œ�`����String<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_strSearchDate - (���t)<BR>
     * ���t�B(yyyymmdd �`��)<BR>
     * @@param l_strStartTime - (����(��))<BR>
     * ����(��)�B(hh24mi �`��)<BR>
     * @@param l_strEndTime - (����(��))<BR>
     * ����(��)�B(hh24mi �`��)<BR>
     * @@param l_hsIPAddressList - (IP�A�h���X�ꗗ)<BR>
     * IP�A�h���X�ꗗ�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 48C4EF680384
     */
    private void createIPLoginProcessingFrequency(
        String l_strInstitutionCode,
        String l_strSearchDate,
        String l_strStartTime,
        String l_strEndTime,
        HashSet l_hsIPAddressList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIPLoginProcessingFrequency(String, String, String, String, HashSet)";
        log.entering(STR_METHOD_NAME);

        //�P) ��������������̍쐬�B
        //String�^�ϐ���p�ӂ��A���L�̕������i�[����B
        //"institution_code = ? and
        //ip_address = ? and
        //login_timestamp >= to_date(' ? || ? || 00 ', 'yyyymmddhh24miss') and
        //login_timestamp <= to_date(' ? || ? || 59 ', 'yyyymmddhh24miss')
        String l_strWhere = "institution_code = ? and ip_address = ? "
            + "and login_timestamp >= to_date( ? || ? || '00', 'yyyymmddhh24miss') "
            + "and login_timestamp <= to_date( ? || ? || '59', 'yyyymmddhh24miss') ";

        //�Q)�f�[�^�R���e�i�̍쐬�O�����B
        //�Q�|�P) ���ArrayList�𐶐�����B
        List l_lisBindVars = new ArrayList();

        //�Q�|�Q) �Q�|�P)��ArrayList�Ɉȉ��̏���ǉ�����B
        //lst.add((����)�،���ЃR�[�h);
        l_lisBindVars.add(l_strInstitutionCode);

        //lst.add(null);
        l_lisBindVars.add(null);

        //lst.add((����)���t);
        l_lisBindVars.add(l_strSearchDate);

        //lst.add((����)����(��));
        l_lisBindVars.add(l_strStartTime);

        //lst.add((����)���t);
        l_lisBindVars.add(l_strSearchDate);

        //lst.add((����)����(��));
        l_lisBindVars.add(l_strEndTime);

        //�R)���O�C���e�[�u���f�[�^�}�l�[�W��.getRowType()���\�b�h���R�[������B
        //[getRowType()�ɃZ�b�g����p�����[�^]
        //���t �F (����)���t
        WEB3TradeManagementLoginTableDataManager l_loginTableDataManager =
            new WEB3TradeManagementLoginTableDataManager();
        RowType l_rowType = l_loginTableDataManager.getRowType(l_strSearchDate, l_strStartTime);

        int l_intCount = 0;
        try
        {
            //�S�jProcessors.getDefaultProcessor()���\�b�h���R�[������B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�T) IP�A�h���X���̃��O�C�������񐔎擾�̑O���������B
            //Iterator it = (����)IP�A�h���X�ꗗ.Iterator();
            Iterator l_ipAddressIterator = l_hsIPAddressList.iterator();

            //�U) �T)�Ő��������I�u�W�F�N�g�̗v�f�����݂������A���L�̏��������{�B
            //(IP�A�h���X�Ƒ΂ɂȂ郍�O�C�������񐔂��擾����܂�)
            while (l_ipAddressIterator.hasNext())
            {
                //�U�|�P) �Q�|�P)�ō쐬����ArrayList�ɑ΂��ĉ��L�̏��������{�B
                //�܂��AString�^�ϐ���p�ӂ��A������ɂ������l(IP�A�h���X)���i�[����B
                String l_strIPAddress = l_ipAddressIterator.next().toString();
                l_lisBindVars.set(1, l_strIPAddress);

                //�U�|�Q) QueryProcessor.doGetCountQuery()���\�b�h���R�[������B
                //[doGetCountQuery()�ɃZ�b�g����p�����[�^]
                //rowType �F �R) �̖߂�l
                //where �F �P)�ō쐬����String
                //bindVars �F �U�|�P) �������ArrayList�I�u�W�F�N�g.toArray(); �̖߂�l
                l_intCount = l_queryProcessor.doGetCountQuery(
                    l_rowType,
                    l_strWhere,
                    l_lisBindVars.toArray());

                //�U�|�R) setIP�ʃ��O�C��������()���\�b�h���R�[������B
                //�� �U�|�Q) �̏����́A��ʓ��͂����w�莞�ԓ���
                //�A�N�Z�X�̂�����IP�A�h���X�̃��R�[�h������DB����擾�B
                //�����̒l���u���O�C�������񐔁v�ƂȂ�B
                //[setIP�ʃ��O�C��������()�ɃZ�b�g����p�����[�^]
                //IP�A�h���X�����O�C�������񐔁F�@@�U�|�Q) �̖߂�l
                //IP�A�h���X�F�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ �U�|�P) �Œ�`����String
                this.setIPLoginProcessingFrequency(l_intCount, l_strIPAddress);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setIP�ʃ��O�C��������)<BR>
     * create���O�C�������񐔕ێ��ϐ�() �Œ�`����HashMap��<BR>
     * �u���O�C�������񐔁v�ƁuIP�A�h���X�v���΂ɂȂ�悤�ɃZ�b�g���鏈���B<BR>
     * <BR>
     * �P) ���L�̓����ϐ����`�B<BR>
     * �@@�@@//�d��key�ێ��ϐ�(Double�^)<BR>
     * �@@�@@Double   dbl        = null;<BR>
     * �@@�@@//�d��key�ێ��ϐ�+�d���J�E���^�[(�v���~�e�B�u�^double)<BR>
     * �@@�@@double   dblkey    = 0.0;<BR>
     * �@@�@@//�d���t���O(�d������/�d���Ȃ��Ftrue/false)<BR>
     * �@@�@@boolean  overlaps = false;<BR>
     * <BR>
     * �Q) (����)IP�A�h���X�����O�C�������� ��String�ɕϊ����Ă����B<BR>
     * �@@�@@String strCnt = new Integer(IP�A�h���X�����O�C��������).toString();<BR>
     * <BR>
     * �R) ���O�C�������񐔏d���`�F�b�N�B<BR>
     * �@@�@@HashMap�N���X��key�ɂ����镔���ɏd�������݂����ꍇ��<BR>
     * �@@�@@�����_��t�^���A�d��������s�������B<BR>
     * <BR>
     * �@@�R�|�P) declaration�t�B�[���h�ϐ�() �Œ�`����<BR>
     * �@@�@@�@@�@@�@@�@@�u���O�C�������񐔈ꗗ�vArrayList��size�ɂ���<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�EArrayList > 0 �̏ꍇ, �R�|�P�|�P) �����{�B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�E��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@key  �F new Double( �Q) �Œ�`����String�ϐ� )<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@value�F (����)IP�A�h���X<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ƁA�uIP�A�h���X�ʃ��O�C�������񐔁vHashMap��put����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@ ����ɁA���O�C�������񐔈ꗗ.add( �Q)�Œ�`�����ϐ� )�@@�����{����B<BR>
     * <BR>
     * �@@�@@�R�|�P�|�P) �u���O�C�������񐔈ꗗ�vArrayList��size����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���L���������[�v����B<BR>
     * <BR>
     * �@@�@@�@@�R�|�P�|�P�|�P) ���O�C�������񐔈ꗗ�̗v�f�� �Q) �̕ϐ����r��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�l������������(�d������)�ꍇ�A���L�̏��������{�B<BR>
     * <BR>
     * �@@�@@�@@�@@�R�|�P�|�P�|�P�|�P) (����)IP�A�h���X�����O�C�������񐔂�Double�^�ɕϊ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�R�|�P�|�P�|�P�|�Q) �R�|�P�|�P�|�P�|�P)�ŕϊ�����Double�^�ϐ���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@declaration�t�B�[���h�ϐ�() ���\�b�h��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��`�����u�d���J�E���^�[�v�𑫂��B<BR>
     * <BR>
     * �@@�@@�@@�@@�R�|�P�|�P�|�P�|�R) �uIP�A�h���X�ʃ��O�C�������񐔁vHashMap��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@key  �F new Double(�R�|�P�|�P�|�P�|�Q) �̒l)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@value�F (����)IP�A�h���X<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��put����B<BR>
     * <BR>
     * �@@�@@�@@�@@�R�|�P�|�P�|�P�|�S) �u���O�C�������񐔈ꗗ�vArrayList��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@new Double(�R�|�P�|�P�|�P�|�Q) �̒l).toString()<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��add����B<BR>
     * <BR>
     * �@@�@@�@@�@@�R�|�P�|�P�|�P�|�T) �d���J�E���^�[�̃C���N�������g���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�R�|�P�|�P�|�P�|�U) �d���t���O = true(�d������) �Ƃ���B<BR>
     * <BR>
     * �@@�@@�@@�@@�R�|�P�|�P�|�P�|�V) �R�|�P�|�P)�̏����𔲂���B<BR>
     * <BR>
     * <BR>
     * �@@�@@�R�|�P�|�Q) �R�|�P�|�P) �̃��[�v�����I����ɏd���t���O�`�F�b�N����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@false(�d���Ȃ�)�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@key  �F new Double( �Q) �Œ�`����String�ϐ� )<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@value�F (����)IP�A�h���X<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�ƁA�uIP�A�h���X�ʃ��O�C�������񐔁vHashMap��put����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@����ɁA���O�C�������񐔈ꗗ.add( �Q)�Œ�`�����ϐ� )�@@�����{����B<BR>
     * <BR>
     * @@param l_intIPAddressEveryLoginProcessingFrequency - <BR>
     * (IP�A�h���X�����O�C��������)<BR>
     * IP�A�h���X�����O�C�������񐔁B<BR>
     * @@param l_strIPAddress - (IP�A�h���X)<BR>
     * IP�A�h���X�B<BR>
     * @@roseuid 48C50A0A01CF
     */
    private void setIPLoginProcessingFrequency(
        int l_intIPAddressEveryLoginProcessingFrequency,
        String l_strIPAddress)
    {
        final String STR_METHOD_NAME = "setIPLoginProcessingFrequency(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P) ���L�̓����ϐ����`�B
        //�d��key�ێ��ϐ�(Double�^)
        Double l_key = null;
        //�d��key�ێ��ϐ�+�d���J�E���^�[(�v���~�e�B�u�^double)
        double l_dblkey = 0.0;
        //�d���t���O(�d������/�d���Ȃ��Ftrue/false)
        boolean l_blnIsOverlaps = false;
        //�Q) (����)IP�A�h���X�����O�C�������� ��String�ɕϊ����Ă����B
        //String strCnt = new Integer(IP�A�h���X�����O�C��������).toString();
        String l_strCnt = new Integer(l_intIPAddressEveryLoginProcessingFrequency).toString();

        //�R) ���O�C�������񐔏d���`�F�b�N�B
        //HashMap�N���X��key�ɂ����镔���ɏd�������݂����ꍇ��
        //�����_��t�^���A�d��������s�������B
        //�R�|�P) declaration�t�B�[���h�ϐ�() �Œ�`����
        //�u���O�C�������񐔈ꗗ�vArrayList��size�ɂ���
        //�EArrayList > 0 �̏ꍇ, �R�|�P�|�P) �����{�B
        int l_intSize = loginProcessingFrequencyList.size();
        if (l_intSize > 0)
        {
            //�R�|�P�|�P) �u���O�C�������񐔈ꗗ�vArrayList��size�������L���������[�v����B
            for (int i = 0; i < l_intSize; i++)
            {
                //�R�|�P�|�P�|�P) ���O�C�������񐔈ꗗ�̗v�f�� �Q) �̕ϐ����r��
                //�l������������(�d������)�ꍇ�A���L�̏��������{�B
                if (l_strCnt.equals(loginProcessingFrequencyList.get(i)))
                {
                    //�R�|�P�|�P�|�P�|�P) (����)IP�A�h���X�����O�C�������񐔂�Double�^�ɕϊ��B
                    l_key = Double.valueOf(l_strCnt);
                    //�R�|�P�|�P�|�P�|�Q) �R�|�P�|�P�|�P�|�P)�ŕϊ�����Double�^�ϐ���
                    //declaration�t�B�[���h�ϐ�() ���\�b�h��
                    //��`�����u�d���J�E���^�[�v�𑫂��B
                    l_dblkey = l_key.doubleValue() + duplicateCounter;
                    //�R�|�P�|�P�|�P�|�R) �uIP�A�h���X�ʃ��O�C�������񐔁vHashMap��
                    //key �F new Double(�R�|�P�|�P�|�P�|�Q) �̒l)
                    //value�F (����)IP�A�h���X ��put����B
                    ipAddressLoginProcessingFrequency.put(new Double(l_dblkey), l_strIPAddress);
                    //�R�|�P�|�P�|�P�|�R) �uIP�A�h���X�ʃ��O�C�������񐔁vHashMap��
                    //new Double(�R�|�P�|�P�|�P�|�Q) �̒l).toString()��add����B
                    loginProcessingFrequencyList.add(new Double(l_dblkey).toString());
                    //�R�|�P�|�P�|�P�|�T) �d���J�E���^�[�̃C���N�������g���s���B
                    duplicateCounter = new BigDecimal(
                        duplicateCounter + "").add(
                        new BigDecimal("0.000001")).doubleValue();
                    //�R�|�P�|�P�|�P�|�U) �d���t���O = true(�d������) �Ƃ���B
                    l_blnIsOverlaps = true;
                    //�R�|�P�|�P�|�P�|�V) �R�|�P�|�P)�̏����𔲂���B
                    break;
                }
            }

            //�R�|�P�|�Q) �R�|�P�|�P) �̃��[�v�����I����ɏd���t���O�`�F�b�N����B
            //false(�d���Ȃ�)�̏ꍇ
            //key �F new Double( �Q) �Œ�`����String�ϐ� )
            //value�F (����)IP�A�h���X
            //�ƁA�uIP�A�h���X�ʃ��O�C�������񐔁vHashMap��put����B
            //����ɁA���O�C�������񐔈ꗗ.add( �Q)�Œ�`�����ϐ� )�@@�����{����B
            if (!l_blnIsOverlaps)
            {
                ipAddressLoginProcessingFrequency.put(new Double(l_strCnt), l_strIPAddress);
                loginProcessingFrequencyList.add(l_strCnt);
            }
        }
        else
        {
            //�E��L�ȊO�̏ꍇ�A
            //key  �F new Double( �Q) �Œ�`����String�ϐ� )
            //value�F (����)IP�A�h���X
            //�ƁA�uIP�A�h���X�ʃ��O�C�������񐔁vHashMap��put����B
            //����ɁA���O�C�������񐔈ꗗ.add( �Q)�Œ�`�����ϐ� )�@@�����{����B
            ipAddressLoginProcessingFrequency.put(new Double(l_strCnt), l_strIPAddress);
            loginProcessingFrequencyList.add(l_strCnt);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getIP�ʃ��O�C��������)<BR>
     * declaration�t�B�[���h�ϐ�() �Œ�`����<BR>
     * �uIP�A�h���X�ʃ��O�C�������񐔁vHashMap��ԋp����B<BR>
     * @@return HashMap
     * @@roseuid 48C6088D0329
     */
    private HashMap getIPLoginProcessingFrequency()
    {
        //declaration�t�B�[���h�ϐ�() �Œ�`����
        //�uIP�A�h���X�ʃ��O�C�������񐔁vHashMap��ԋp����B
        return this.ipAddressLoginProcessingFrequency;
    }

    /**
     * (sort���O�C��������)<BR>
     * ���O�C�������񐔂��\�[�g(����)�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P) ���[�J���ϐ��̒�`<BR>
     * �@@�@@�E�J�Ԃ������p<BR>
     * �@@�@@Iterator ite = (����)IP�A�h���X�ʃ��O�C�������񐔃R���N�V����.iterator();<BR>
     * <BR>
     * �@@�@@�E�����\�[�g���ꂽ���O�C�������񐔂�ێ�����ϐ�<BR>
     * �@@�@@double[] priAryDbl = <BR>
     * �@@�@@�@@new double[(����)IP�A�h���X�ʃ��O�C�������񐔃R���N�V����.size()];<BR>
     * <BR>
     * �@@�@@�E���O�C�������񐔂��i�[����ϐ�<BR>
     * �@@�@@Object   objValue  = null;<BR>
     * <BR>
     * �@@�@@�E�J�E���^�[�ϐ�<BR>
     * �@@�@@int i = 0;<BR>
     * <BR>
     * �Q) �P)�Œ�`����Iterator�I�u�W�F�N�g�̒��ɗv�f�����݂����<BR>
     * �@@�@@���L�̏��������[�v����B<BR>
     * <BR>
     * �@@�Q�|�P) �P)�Œ�`����Object�ϐ���<BR>
     * �@@�@@�@@�@@�@@�@@Iterator�I�u�W�F�N�g�̒��̗v�f���i�[����<BR>
     * <BR>
     * �@@�Q�|�Q) �P)�Œ�`����double�^�z��[k]��<BR>
     * �@@�@@�@@�@@�@@�@@�Q�|�P) �Œ�`�����ϐ���double�^�ɕϊ����Ċi�[����B<BR>
     * <BR>
     * �@@�Q�|�R) int�^�ϐ� i�̃C���N�������g���s��(+1)<BR>
     * <BR>
     * �R) �\�[�g����(����)�����{����<BR>
     * �@@�@@Arrays.sort(priAryDbl); <BR>
     * <BR>
     * �S) double�^�z���ԋp����B<BR>
     * <BR>
     * @@param l_ipAddressLoginProcessingFrequencyCollection - <BR>
     * (IP�A�h���X�ʃ��O�C�������񐔃R���N�V����)<BR>
     * IP�A�h���X�ʃ��O�C�������񐔃R���N�V�����B<BR>
     * @@return double[]
     * @@roseuid 48C60DCE0120
     */
    private double[] sortLoginProcessingFrequency(Set l_ipAddressLoginProcessingFrequencyCollection)
    {
        final String STR_METHOD_NAME = "sortLoginProcessingFrequency(Set)";
        log.entering(STR_METHOD_NAME);

        //�P) ���[�J���ϐ��̒�`
        //�E�J�Ԃ������p
        Iterator l_ipFrequencyIterator = l_ipAddressLoginProcessingFrequencyCollection.iterator();

        //�E�����\�[�g���ꂽ���O�C�������񐔂�ێ�����ϐ�
        double[] l_dblPriAryDbls = new double[l_ipAddressLoginProcessingFrequencyCollection.size()];

        //�E���O�C�������񐔂��i�[����ϐ�
        Object l_objValue = null;

        //�E�J�E���^�[�ϐ�
        int i = 0;
        //�Q) �P)�Œ�`����Iterator�I�u�W�F�N�g�̒��ɗv�f�����݂����
        //���L�̏��������[�v����B
        while (l_ipFrequencyIterator.hasNext())
        {
            //�Q�|�P) �P)�Œ�`����Object�ϐ���
            //Iterator�I�u�W�F�N�g�̒��̗v�f���i�[����
            l_objValue = l_ipFrequencyIterator.next();            

            //�Q�|�Q) �P)�Œ�`����double�^�z��[k]��
            //�Q�|�P) �Œ�`�����ϐ���double�^�ɕϊ����Ċi�[����B
            Double l_priAryDbl = new Double(l_objValue.toString());
            l_dblPriAryDbls[i] = l_priAryDbl.doubleValue();

            //�Q�|�R) int�^�ϐ� i�̃C���N�������g���s��(+1)
            i++;
        }

        //�R) �\�[�g����(����)�����{����
        Arrays.sort(l_dblPriAryDbls);

        log.exiting(STR_METHOD_NAME);

        //�S) double�^�z���ԋp����B
        return l_dblPriAryDbls;
    }

    /**
     * (getIP�ʃ��O�C���񐔖��׈ꗗ)<BR>
     * ���X�|���X�f�[�^�Ƃ��ēn��IP�ʃ��O�C���񐔂̈ꗗ���擾���鏈���B<BR>
     * <BR>
     * �P) ���L�̓����ϐ����`<BR>
     * <BR>
     * �@@�E����                           (int�^ �����l�F1)<BR>
     * �@@�E�y���O�C��������(�~��)�z (double�^ �����l�F0.0)<BR>
     * �@@�E�O�񃍃O�C��������      (int�^ �����l�F0)<BR>
     * �@@�E�����J�E���^�[                (int�^ �����l�F0)<BR>
     * �@@�E���O�C�����׈ꗗ���X�g      (List ���O�C�����׈ꗗ���X�g = new ArrayList();)<BR>
     * <BR>
     * �Q) (����)����IP�ʃ��O�C��������.length �񂾂����L�̏��������[�v�B<BR>
     * �@@�@@�J�Ԃ��񐔂� i (�����l�F0) �Ƃ��A i, ���� ��+1���Z���Ȃ��烋�[�v����B<BR>
     * <BR>
     * �@@�Q�|�P) �y���O�C��������(�~��)�z��<BR>
     * �@@�@@�@@�@@�@@(����)����IP�ʃ��O�C��������[( <BR>
     * �@@�@@�@@�@@�@@�@@(����)����IP�ʃ��O�C��������.length - i - 1)] ����<BR>
     * <BR>
     * �@@�Q�|�Q) i != 0��ڈȊO�̏ꍇ�́A���L���������{�B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P) �������ʔ��菈���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@(�����_�ȉ���؎̂Ă�)�y���O�C��������(�~��)�z<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�� �O�񃍃O�C�������� �������������ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�����J�E���^�[�� +1�@@�ǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�A(�����_�ȉ���؎̂Ă�)�y���O�C��������(�~��)�z<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�� �O�񃍃O�C�������� ���������Ȃ������ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�����J�E���^�[�� 0 ��������B(�l�̃��Z�b�g)<BR>
     * <BR>
     * �@@�Q�|�R) ���O�C�����׈ꗗ �����N�͈͂ł̎擾�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@( ���� - �����J�E���^�[ ) > (����)�����N �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�Q�j �̃��[�v��break����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�A !( ( ���� - �����J�E���^�[ ) > (����)�����N) �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���L�̏��������{����B<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�P) IP�ʃ��O�C���񐔏��I�u�W�F�N�g �𐶐�����B<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�Q) IP�A�h���X�A���O�C�������񐔁A�����N�̎擾�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����J�E���^�[ != 0 �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�EIP�ʃ��O�C���񐔏��I�u�W�F�N�g.IP�A�h���X = <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@IP�A�h���X�ʃ��O�C��������.get(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@new Double(�y���O�C��������(�~��)�z));<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�EIP�ʃ��O�C���񐔏��I�u�W�F�N�g.�����N = <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@new Integer(���� - �����J�E���^�[ ).toString();<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�EIP�ʃ��O�C���񐔏��I�u�W�F�N�g.���O�C�������� = <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(�����_�ȉ���؎̂Ă�)�y���O�C��������(�~��)�z<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�A�����J�E���^�[ == 0 �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�EIP�ʃ��O�C���񐔏��I�u�W�F�N�g.IP�A�h���X = <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@IP�A�h���X�ʃ��O�C��������.get(<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@new Double(�y���O�C��������(�~��)�z));<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�EIP�ʃ��O�C���񐔏��I�u�W�F�N�g.�����N = <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@new Integer(����).toString();<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�EIP�ʃ��O�C���񐔏��I�u�W�F�N�g.���O�C�������� = <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(�����_�ȉ���؎̂Ă�)�y���O�C��������(�~��)�z<BR>
     * <BR>
     * �@@�@@�@@�Q�|�R�|�R) ���O�C�����׈ꗗ���X�g�ɁAIP�ʃ��O�C���񐔏��I�u�W�F�N�g��ǉ��B<BR>
     * <BR>
     * �@@�Q�|�S) <BR>
     * �O�񃍃O�C�������񐔂�(�����_�ȉ���؎̂Ă�)�y���O�C��������(�~��)�z���i�[����B<BR>
     * <BR>
     * �R) ���O�C�����׈ꗗ���X�g��ԋp����B<BR>
     * @@param l_dblAscIPLoginProcessingFrequencys - (����IP�ʃ��O�C��������)<BR>
     * �����\�[�g���ꂽIP�ʃ��O�C�������񐔁B<BR>
     * @@param l_strRank - (�����N)<BR>
     * @@return List
     * @@roseuid 48C62ACE02B3
     */
    private List getIPLoginFrequencyUnitList(double[] l_dblAscIPLoginProcessingFrequencys, String l_strRank)
    {
        final String STR_METHOD_NAME = "getIPLoginFrequencyUnitList(double[], String)";
        log.entering(STR_METHOD_NAME);

        //�P) ���L�̓����ϐ����`
        //�E���� (int�^ �����l�F1)
        int l_intNumber = 1;

        //�E�y���O�C��������(�~��)�z (double�^ �����l�F0.0)
        double l_dblLoginProcessingFrequency = 0.0;

        //�E�O�񃍃O�C�������� (int�^ �����l�F0)
        int l_intPastLoginProcessingFrequency = 0;

        //�E�����J�E���^�[ (int�^ �����l�F0)
        int l_intCounter = 0;

        //�E���O�C�����׈ꗗ���X�g (List ���O�C�����׈ꗗ���X�g = new ArrayList();)
        List l_lisLoginUnitList = new ArrayList();

        //�Q) (����)����IP�ʃ��O�C��������.length �񂾂����L�̏��������[�v�B
        //�J�Ԃ��񐔂� i (�����l�F0) �Ƃ��A i, ���� ��+1���Z���Ȃ��烋�[�v����B
        int l_intSize = l_dblAscIPLoginProcessingFrequencys.length;
        for (int i = 0; i < l_intSize; i++, l_intNumber++)
        {
            //�Q�|�P) �y���O�C��������(�~��)�z��
            //(����)����IP�ʃ��O�C��������[( (����)����IP�ʃ��O�C��������.length - i - 1)] ����
            l_dblLoginProcessingFrequency = l_dblAscIPLoginProcessingFrequencys[l_intSize - i - 1];

            //�Q�|�Q) i != 0��ڈȊO�̏ꍇ�́A���L���������{�B
            if (i != 0)
            {
                //�Q�|�Q�|�P) �������ʔ��菈���B
                //�@@(�����_�ȉ���؎̂Ă�)�y���O�C��������(�~��)�z
                //�� �O�񃍃O�C�������� �������������ꍇ
                if ((int)l_dblLoginProcessingFrequency == l_intPastLoginProcessingFrequency)
                {
                    //�E�����J�E���^�[�� +1�@@�ǉ�����B
                    l_intCounter++;
                }
                //�A(�����_�ȉ���؎̂Ă�)�y���O�C��������(�~��)�z
                //�� �O�񃍃O�C�������� ���������Ȃ������ꍇ
                else
                {
                    //�E�����J�E���^�[�� 0 ��������B(�l�̃��Z�b�g)
                    l_intCounter = 0;
                }
            }

            //�Q�|�R) ���O�C�����׈ꗗ �����N�͈͂ł̎擾�����B
            //�@@( ���� - �����J�E���^�[ ) > (����)�����N �̏ꍇ
            if ((l_intNumber - l_intCounter) > Integer.parseInt(l_strRank))
            {
                //�Q�j �̃��[�v��break����B
                break;
            }
            //�A !( ( ���� - �����J�E���^�[ ) > (����)�����N) �̏ꍇ
            //���L�̏��������{����B
            else
            {
                //�Q�|�R�|�P) IP�ʃ��O�C���񐔏��I�u�W�F�N�g �𐶐�����B
                WEB3AdminTraderAdminLoginCountReferenceUnit l_loginCountReferenceUnits =
                    new WEB3AdminTraderAdminLoginCountReferenceUnit();

                //�Q�|�R�|�Q) IP�A�h���X�A���O�C�������񐔁A�����N�̎擾�B
                //�@@�����J�E���^�[ != 0 �̏ꍇ
                if (l_intCounter != 0)
                {
                    //�EIP�ʃ��O�C���񐔏��I�u�W�F�N�g.IP�A�h���X =
                    //IP�A�h���X�ʃ��O�C��������.get(new Double(�y���O�C��������(�~��)�z));
                    l_loginCountReferenceUnits.ipAddress =
                        ipAddressLoginProcessingFrequency.get(new Double(l_dblLoginProcessingFrequency)).toString();

                    //�EIP�ʃ��O�C���񐔏��I�u�W�F�N�g.�����N =
                    //new Integer(���� - �����J�E���^�[ ).toString();
                    l_loginCountReferenceUnits.rank = new Integer(l_intNumber - l_intCounter).toString();

                    //�EIP�ʃ��O�C���񐔏��I�u�W�F�N�g.���O�C�������� =
                    //(�����_�ȉ���؎̂Ă�)�y���O�C��������(�~��)�z
                    l_loginCountReferenceUnits.loginCount = String.valueOf((int)l_dblLoginProcessingFrequency);
                }
                //�A�����J�E���^�[ == 0 �̏ꍇ
                else
                {
                    //�EIP�ʃ��O�C���񐔏��I�u�W�F�N�g.IP�A�h���X =
                    //IP�A�h���X�ʃ��O�C��������.get(new Double(�y���O�C��������(�~��)�z));
                    l_loginCountReferenceUnits.ipAddress =
                        ipAddressLoginProcessingFrequency.get(new Double(l_dblLoginProcessingFrequency)).toString();

                    //�EIP�ʃ��O�C���񐔏��I�u�W�F�N�g.�����N = new Integer(����).toString();
                    l_loginCountReferenceUnits.rank = new Integer(l_intNumber).toString();

                    //�EIP�ʃ��O�C���񐔏��I�u�W�F�N�g.���O�C�������� =
                    //(�����_�ȉ���؎̂Ă�)�y���O�C��������(�~��)�z
                    l_loginCountReferenceUnits.loginCount = String.valueOf((int)l_dblLoginProcessingFrequency);
                }

                //�Q�|�R�|�R) ���O�C�����׈ꗗ���X�g�ɁAIP�ʃ��O�C���񐔏��I�u�W�F�N�g��ǉ��B
                l_lisLoginUnitList.add(l_loginCountReferenceUnits);
            }

            //�Q�|�S) �O�񃍃O�C�������񐔂�(�����_�ȉ���؎̂Ă�)�y���O�C��������(�~��)�z���i�[����B
            l_intPastLoginProcessingFrequency = (int)l_dblLoginProcessingFrequency;
        }

        log.exiting(STR_METHOD_NAME);

        //�R) ���O�C�����׈ꗗ���X�g��ԋp����B
        return l_lisLoginUnitList;
    }
}@
