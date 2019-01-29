head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorAccountListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CC�I�y���[�^�Ώیڋq�ꗗ�T�[�r�XImpl(WEB3CCOperatorAccountListServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 ���n�m (���u) �V�K�쐬�E���f��No.039�ANo.042
*/

package webbroker3.login.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.data.TraderAccountInfoParams;
import webbroker3.gentrade.data.TraderAccountInfoRow;
import webbroker3.login.define.WEB3TraderAccountInfosSortKeyDef;
import webbroker3.login.message.WEB3CCOperatorAccountListRequest;
import webbroker3.login.message.WEB3CCOperatorAccountListResponse;
import webbroker3.login.message.WEB3TraderAccountInfo;
import webbroker3.login.message.WEB3TraderAccountInfosSortKey;
import webbroker3.login.service.delegate.WEB3CCOperatorAccountListService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (CC�I�y���[�^�Ώیڋq�ꗗ�T�[�r�XImpl)<BR>
 * CC�I�y���[�^�Ώیڋq�ꗗ�T�[�r�XImpl<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3CCOperatorAccountListServiceImpl extends WEB3LoginServiceBaseImpl
    implements WEB3CCOperatorAccountListService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3CCOperatorAccountListServiceImpl.class);

    /**
     * (CC�I�y���[�^�Ώیڋq�ꗗ�T�[�r�XImpl)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 46A45A2100CB
     */
    public WEB3CCOperatorAccountListServiceImpl()
    {

    }

    /**
     * �����ŗ^����ꂽ���N�G�X�g����ɋƖ��������s���A�������ʂ����X�|���X<BR>
     * �ɐݒ肵�ĕԂ��B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^���A<BR>
     * �@@[CC�I�y���[�^�Ώیڋq�ꗗ���N�G�X�g�̏ꍇ]<BR>
     * �@@�@@this.get�Ώیڋq�ꗗ()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g)<BR>   
     * ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4694957A036E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        // CC�I�y���[�^�Ώیڋq�ꗗ���N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3CCOperatorAccountListRequest)
        {
            // this.get�Ώیڋq�ꗗ()���\�b�h���R�[������B
            l_response =
                this.getAccountList(
                    (WEB3CCOperatorAccountListRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
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
     * (get�Ώیڋq�ꗗ)<BR>
     * �Ώیڋq�ꗗ���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�Ώیڋq�ꗗ�jget�Ώیڋq�ꗗ�����v �Q�ƁB<BR>
     * <BR>
     * ========================================================<BR>
     * �@@�V�[�P���X�}(�u�i�Ώیڋq�ꗗ�jget�Ώیڋq�ꗗ�����v)<BR>
     * �@@�@@�擾�ł��Ȃ��̏ꍇ�A�\�����Ȃ��V�X�e���G���[���������܂����B<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class:�@@WEB3SystemLayerException<BR>
     * �@@�@@�@@tag�@@:�@@SYSTEM_ERROR_80002<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * CC�I�y���[�^�Ώیڋq�ꗗ���N�G�X�g<BR>
     * @@return WEB3CCOperatorAccountListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4694993E0032
     */
    protected WEB3CCOperatorAccountListResponse getAccountList(
        WEB3CCOperatorAccountListRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " getAccountList(WEB3CCOperatorAccountListRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // getSessionProperty(arg0 : String)
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strCCOperatorID =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.CCOPERATOR_ID);
        if (l_strCCOperatorID == null)
        {
            log.debug("�\�����Ȃ��V�X�e���G���[���������܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + STR_METHOD_NAME,
                "�\�����Ȃ��V�X�e���G���[���������܂����B");
        }

        // create����������(String, String, String)
        // �ڋq�R�[�h�F���N�G�X�g.�ڋq�R�[�h
        // �ڋq���i�����j�F���N�G�X�g.�����O�i�����j
        // �ڋq���i�J�i�j�F���N�G�X�g.�����O�i�J�i�j
        String l_strCreateQueryString = this.createQueryString(
            l_request.acceptCode,
            l_request.nameKanji,
            l_request.nameKana);

        // create�����R���e�i(long, String, String, String)
        // �ڋq�R�[�h�F���N�G�X�g.�ڋq�R�[�h
        // �ڋq���i�����j�F���N�G�X�g.�����O�i�����j
        // �ڋq���i�J�i�j�F���N�G�X�g.�����O�i�J�i�j
        Object[] l_createQueryDataContainers = this.createQueryDataContainer(
            Long.parseLong(l_strCCOperatorID),
            l_request.acceptCode,
            l_request.nameKanji,
            l_request.nameKana);

        // create�\�[�g����(�Ώیڋq�\�[�g�L�[[])
        // �\�[�g�L�[�F���N�G�X�g.�\�[�g�L�[
        String l_strCreateSortCond = this.createSortCond(l_request.sortKeys);

        // doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : String, arg3 : String, arg4 : Object[])
        // �s�^�C�v�F���ҕʌڋq���e�[�u��Row.TYPE
        // ��������������F ����������������������
        // �\�[�g�����F ���������\�[�g����
        // �R���f�B�V�����F null
        // ���������f�[�^�R���e�i�F �����������������f�[�^�R���e�i
        List l_lisResults = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults = l_queryProcessor.doFindAllQuery(
                TraderAccountInfoRow.TYPE,
                l_strCreateQueryString,
                l_strCreateSortCond,
                null,
                l_createQueryDataContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // createResponse( )
        // ���X�|���X�f�[�^�𐶐�����B
        WEB3CCOperatorAccountListResponse l_operatorAccountListResponse =
            (WEB3CCOperatorAccountListResponse)l_request.createResponse();

        int l_intTotalPages = 0;
        int l_intTotalRecords = 0;
        int l_intPageIndex = 0;
        // �i����t���[�F�f�[�^�擾�ł����ꍇ�j
        if (!l_lisResults.isEmpty())
        {
            // WEB3PageIndexInfo(List, int, int)
            // ���׃��X�g�F �擾�������ҕʌڋq���e�[�u���̃��X�g
            // �v���y�[�W�ԍ��F ���N�G�X�g.�v���y�[�W�ԍ�
            // �v���y�[�W���\���s���F ���N�G�X�g.�y�[�W���\���s��
            int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
            int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
            WEB3PageIndexInfo l_pageIndexInfo =
                new WEB3PageIndexInfo(
                    l_lisResults,
                    l_intRequestPageIndex,
                    l_intRequestPageSize);

            // getArrayReturned(Class)
            // class�F���ҕʌڋq���e�[�u��Params.class
            TraderAccountInfoParams[] l_traderAccountInfoParams =
                (TraderAccountInfoParams[])l_pageIndexInfo.getArrayReturned(
                    TraderAccountInfoParams.class);

            // �擾�����\���Ώۂ̈��ҕʌڋq���e�[�u��Params�̔z��̌������A�������J��Ԃ��B
            int l_intLength = l_traderAccountInfoParams.length;
            List l_lisContainers = new ArrayList();

            for (int i = 0; i < l_intLength; i++)
            {
                // �R�t�ڋq���( )
                // �R�t�ڋq���I�u�W�F�N�g�𐶐�����B
                WEB3TraderAccountInfo l_traderAccountInfo = new WEB3TraderAccountInfo();

                // �P�D�R�t�ڋq�����ȉ��̒ʂ�ҏW����
                // �ڋq�R�[�h�F���ҕʌڋq���e�[�u��Params.�����R�[�h
                l_traderAccountInfo.acceptCode = l_traderAccountInfoParams[i].getAccountCode();
                // ���O�i�����j�F���ҕʌڋq���e�[�u��Params.���O�i�c���j
                l_traderAccountInfo.nameKanji = l_traderAccountInfoParams[i].getFamilyName();
                // ���O�i�J�i�j�F���ҕʌڋq���e�[�u��Params.���O�i�c��1�j
                l_traderAccountInfo.nameKana = l_traderAccountInfoParams[i].getFamilyNameAlt1();
                // ��L�ȊO�͈��ҕʌڋq���e�[�u��Params.������
                l_traderAccountInfo.accountID = l_traderAccountInfoParams[i].getAccountId();
                l_traderAccountInfo.branchCode = l_traderAccountInfoParams[i].getBranchCode();
                // �Q�D���X�g�𐶐����A�ҏW�����R�t�ڋq���������ǉ�
                l_lisContainers.add(l_traderAccountInfo);
            }

            // getTotalPages( )
            l_intTotalPages = l_pageIndexInfo.getTotalPages();

            // getTotalRecords( )
            l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

            // getPageIndex( )
            l_intPageIndex = l_pageIndexInfo.getPageIndex();

            // �v���p�e�B�Z�b�g
            // �Ώیڋq�ꗗ�F�R�t�ڋq���̃��X�g
            WEB3TraderAccountInfo[] l_traderAccountInfos =
                new WEB3TraderAccountInfo[l_lisContainers.size()];
            l_lisContainers.toArray(l_traderAccountInfos);
            l_operatorAccountListResponse.traderAccoutInfos = l_traderAccountInfos;
            // ���y�[�W���FgetTotalPages()�̖߂�l
            l_operatorAccountListResponse.totalPages = l_intTotalPages + "";
            // �����R�[�h���FgetTotalRecords()�̖߂�l
            l_operatorAccountListResponse.totalRecords = l_intTotalRecords + "";
            // �\���y�[�W�ԍ��FgetPageIndex()�̖߂�l
            l_operatorAccountListResponse.pageIndex = l_intPageIndex + "";
        }
        else
        {
            // �Ώیڋq�ꗗ�Fnull
            l_operatorAccountListResponse.traderAccoutInfos = null;
            // ���y�[�W���F0
            l_operatorAccountListResponse.totalPages = l_intTotalPages + "";
            // �����R�[�h���F0
            l_operatorAccountListResponse.totalRecords = l_intTotalRecords + "";
            // �\���y�[�W�ԍ��F0
            l_operatorAccountListResponse.pageIndex = l_intPageIndex + "";
        }

        log.exiting(STR_METHOD_NAME);
        return l_operatorAccountListResponse;
    }

    /**
     * (create����������)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j��������������C���X�^���X(�FString)�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����^.����ID����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "trader_id = ? "<BR>
     * <BR>
     * �R�j�p�����^.�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * �@@�����[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and account_code = ? "<BR>
     * <BR>
     * �S�j�p�����^.�ڋq���i�����j != null�̏ꍇ�A<BR>
     * �@@���O�i�c���j����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and family_name like ? "<BR>
     * <BR>
     * �T�j�p�����^.�ڋq���ڋq���i�J�i�j != null�̏ꍇ�A<BR>
     * �@@���O�i�c��1�j����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and family_name_alt1 like ? "<BR>
     * <BR>
     * �U�j�쐬�������������������ԋp����B<BR>
     * <BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * ���͂��ꂽ�U���̌ڋq�R�[�h<BR>
     * @@param l_strFamilyName - (�ڋq���i�����j)<BR>
     * ���͂��ꂽ�ڋq���i�����j<BR>
     * @@param l_strFamilyNameAlt1 - (�ڋq���i�J�i�j)<BR>
     * ���͂��ꂽ�ڋq���i�J�i�j<BR>
     * @@return String
     * @@roseuid 4695E61D0044
     */
    protected String createQueryString(
        String l_strAccountCode,
        String l_strFamilyName,
        String l_strFamilyNameAlt1)
    {

        final String STR_METHOD_NAME = " createQueryString(String, String, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j��������������C���X�^���X(�FString)�𐶐�����B
        StringBuffer l_sbQueryString = new StringBuffer();

        // �Q�j�p�����^.����ID����������������ɒǉ�����B
        // �@@�������������� += "trader_id = ? "
        l_sbQueryString.append("trader_id = ? ");

        // �R�j�p�����^.�ڋq�R�[�h != null�̏ꍇ�A
        // �@@�����[�h����������������ɒǉ�����B
        // �@@�������������� += "and account_code = ? "
        if (l_strAccountCode != null)
        {
            l_sbQueryString.append("and account_code = ? ");
        }

        // �S�j�p�����^.�ڋq���i�����j != null�̏ꍇ�A
        // �@@���O�i�c���j����������������ɒǉ�����B
        // �@@�������������� += "and family_name like ? "
        if (l_strFamilyName != null)
        {
            l_sbQueryString.append("and family_name like ? ");
        }

        // �T�j�p�����^.�ڋq���ڋq���i�J�i�j != null�̏ꍇ�A
        // �@@���O�i�c��1�j����������������ɒǉ�����B
        // �@@�������������� += "and family_name_alt1 like ? "
        if (l_strFamilyNameAlt1 != null)
        {
            l_sbQueryString.append("and family_name_alt1 like ? ");
        }

        // �U�j�쐬�������������������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create�����R���e�i)<BR>
     * ���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����^.����ID�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�p�����^.�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * �@@�ڋq�R�[�h�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j�p�����^.�ڋq���i�����j != null�̏ꍇ�A<BR>
     * �@@'%' + �ڋq���i�����j + '%'�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�j�p�����^.�ڋq���i�J�i�j != null�̏ꍇ�A<BR>
     * �@@'%' + �ڋq���i�J�i�j + '%'�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �U�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_lngTradeId - (����ID)<BR>
     * �Z�b�V��������擾��������ID<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * ���͂��ꂽ�U���̌ڋq�R�[�h<BR>
     * @@param l_strFamilyName - (�ڋq���i�����j)<BR>
     * ���͂��ꂽ�ڋq���i�����j<BR>
     * @@param l_strFamilyNameAlt1 - (�ڋq���i�J�i�j)<BR>
     * ���͂��ꂽ�ڋq���i�J�i�j<BR>
     * @@return Object[]
     * @@roseuid 4695E89C0286
     */
    protected Object[] createQueryDataContainer(
        long l_lngTradeId,
        String l_strAccountCode,
        String l_strFamilyName,
        String l_strFamilyNameAlt1)
    {

        final String STR_METHOD_NAME = " createQueryDataContainer(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        // �P�jArrayList�𐶐�����B
        List l_lisQuerys = new ArrayList();

        // �Q�j�p�����^.����ID�𐶐�����ArrayList�ɒǉ�����B
        l_lisQuerys.add(new Long(l_lngTradeId));

        // �R�j�p�����^.�ڋq�R�[�h != null�̏ꍇ�A
        // �@@�ڋq�R�[�h�𐶐�����ArrayList�ɒǉ�����B
        if (l_strAccountCode != null)
        {
            l_lisQuerys.add(l_strAccountCode);
        }

        // �S�j�p�����^.�ڋq���i�����j != null�̏ꍇ�A
        // �@@'%' + �ڋq���i�����j + '%'�𐶐�����ArrayList�ɒǉ�����B
        if (l_strFamilyName != null)
        {
            l_lisQuerys.add("%" + l_strFamilyName + "%");
        }

        // �T�j�p�����^.�ڋq���i�J�i�j != null�̏ꍇ�A
        // �@@'%' + �ڋq���i�J�i�j + '%'�𐶐�����ArrayList�ɒǉ�����B
        if (l_strFamilyNameAlt1 != null)
        {
            l_lisQuerys.add("%" + l_strFamilyNameAlt1 + "%");
        }

        // �U�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        Object[] l_results = new Object[l_lisQuerys.size()];
        l_lisQuerys.toArray(l_results);

        log.exiting(STR_METHOD_NAME);
        return l_results;
    }

    /**
     * (create�\�[�g����)<BR>
     * �\�[�g������������쐬����B<BR>
     * <BR>
     * �P�j�\�[�g����������(�FString)���쐬����B<BR>
     * <BR>
     * �Q�j�p�����^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�p�����^.�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     * �@@�@@�쐬�����\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�E�u�ڋq�R�[�h�v�@@���@@���ҕʌڋq���e�[�u��.�����R�[�h<BR>
     * �@@�@@�E�u���O�v�@@���@@���ҕʌڋq���e�[�u��.���O�i�c��1�j<BR>
     * <BR>
     * �@@�Q�|�Q�j�p�����^.�\�[�g�L�[.�����^�~���ɑΉ�����擾����<BR>
     * �@@�@@(asc or desc)���\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �R�j�쐬�����\�[�g�����������ԋp����B<BR>
     * <BR>
     * @@param l_sortKeys - �Ώیڋq�\�[�g�L�[�̔z��
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4696E6E302A4
     */
    protected String createSortCond(WEB3TraderAccountInfosSortKey[] l_sortKeys)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " createSortCond(WEB3TraderAccountInfosSortKey[])";
        log.entering(STR_METHOD_NAME);

        if (l_sortKeys == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �P�j�\�[�g����������(�FString)���쐬����B
        StringBuffer l_sbSortCond = new StringBuffer();

        // �Q�j�p�����^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            // �Q�|�P�j�p�����^.�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A
            // �쐬�����\�[�g����������ɒǉ�����B
            // �u�ڋq�R�[�h�v�@@���@@���ҕʌڋq���e�[�u��.�����R�[�h
            if (WEB3TraderAccountInfosSortKeyDef.ACCEPT_CODE.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("account_code");
            }
            // �u���O�v�@@���@@���ҕʌڋq���e�[�u��.���O�i�c��1�j
            else if (WEB3TraderAccountInfosSortKeyDef.NAME_KANA.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("family_name_alt1");
            }

            // �Q�|�Q�j�p�����^.�\�[�g�L�[.�����^�~���ɑΉ�����擾����
            // (asc or desc)���\�[�g����������ɒǉ�����B
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                if (i != l_sortKeys.length - 1)
                {
                    l_sbSortCond.append(" ASC , ");
                }
                else
                {
                    l_sbSortCond.append(" ASC ");
                }
            }
            else
            {
                if (i != l_sortKeys.length - 1)
                {
                    l_sbSortCond.append(" DESC , ");
                }
                else
                {
                    l_sbSortCond.append(" DESC ");
                }
            }
        }

        // �R�j�쐬�����\�[�g�����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbSortCond.toString();
    }

}
@
