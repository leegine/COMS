head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�XImpl(WEB3AdminInformPTSAccountListServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 �Ӑ�(���u) �V�K�쐬 ���f��No.130
Revision History    : 2008/03/26 ���u��(���u) �V�K�쐬 ���f��No.133,�c�a�X�V�d�lNo.022
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.define.WEB3InformKeyItemDef;
import webbroker3.inform.message.WEB3AdminInformPTSAccountInfoUnit;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInqCondition;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInqSortKey;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformPTSAccountListService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�XImpl)<BR>
 * �Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListServiceImpl implements WEB3AdminInformPTSAccountListService
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListServiceImpl.class);

    /**
     * @@roseuid 47C6665002B9
     */
    public WEB3AdminInformPTSAccountListServiceImpl()
    {

    }

    /**
     * �Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X�������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B537740302
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        if (l_request instanceof WEB3AdminInformPTSAccountListInquiryRequest)
        {
            //get�������
            l_response = this.getSearchScreen((WEB3AdminInformPTSAccountListInquiryRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformPTSAccountListResultRequest)
        {
            //get�������ʉ��
            l_response = this.getSearchResultScreen((WEB3AdminInformPTSAccountListResultRequest)l_request);
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
     * (get�������)<BR>
     * �Ǘ���PTS�\���q�ꗗ�⍇��������ʂ̎擾���s���B <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��ҁiPTS�\���q�ꗗ�⍇���jget������ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformPTSAccountListInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B537740304
     */
    protected WEB3AdminInformPTSAccountListInquiryResponse getSearchScreen(
        WEB3AdminInformPTSAccountListInquiryRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchScreen(WEB3AdminInformPTSAccountListInquiryRequest)";
        log.entering(STR_METHOD_NAME);

        // getInstanceFrom���O�C�����()
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.PTS_ACCOUNT_MANAGE,
            false);

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminInformPTSAccountListInquiryResponse l_response =
            (WEB3AdminInformPTSAccountListInquiryResponse)l_request.createResponse();

        //���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        //�@@�\�������i���j�F�@@���ݓ����̑O�c�Ɠ�
        Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();
        l_response.applyDateFrom =
            WEB3DateUtility.toDay(WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1));
        //�@@�\�������i���j�F�@@���ݓ����̑O��
        l_response.applyDateTo =
            WEB3DateUtility.toDay(WEB3DateUtility.addDay(l_tsCurrentDate, -1));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�������ʉ��)<BR>
     * �Ǘ���PTS�\���q�ꗗ�⍇���������ʉ�ʂ̎擾���s���B <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�Ǘ��ҁiPTS�\���q�ꗗ�⍇���jget�������ʉ�ʁv �Q�ƁB<BR>
     * ==================================================
     * �@@�@@��̈ʒu :���R�[�h���擾�ł��Ȃ��ꍇ�A��O�́uBUSINESS_ERROR_00398<BR>
     * �@@�@@�@@�@@�@@�@@�@@�i�Y������f�[�^�����݂��܂���B�j�v��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00398<BR>
     * ==================================================
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformPTSAccountListResultResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B537740306
     */
    protected WEB3AdminInformPTSAccountListResultResponse getSearchResultScreen(
        WEB3AdminInformPTSAccountListResultRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchResultScreen(WEB3AdminInformPTSAccountListResultRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h�F "A0501"
        //is�X�V�Ffalse
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.PTS_ACCOUNT_MANAGE,
            false);

        //create�擾����������(�Ǘ��ҁEPTS�\���q�ꗗ�⍇����������)
        String l_strQueryString = this.createQueryString(l_request.searchCondition);

        //create�擾�����f�[�^�R���e�i(String, �Ǘ��ҁEPTS�\���q�ꗗ�⍇����������)
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()
        //���������F ���N�G�X�g�f�[�^.��������
        Object[] l_queryDataContainers =
            this.createQueryDataContainer(l_administrator.getInstitutionCode(), l_request.searchCondition);

        //create�\�[�g����������(PTS�\���q�ꗗ�⍇���\�[�g�L�[[])
        //�\�[�g�L�[�F ���N�G�X�g�f�[�^.�\�[�g�L�[
        String l_strSortCondString = this.createSortCondString(l_request.sortKeys);

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminInformPTSAccountListResultResponse l_response =
            (WEB3AdminInformPTSAccountListResultResponse)l_request.createResponse();
        try
        {
            //�N�G���v���Z�b�T���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //doFindAllQuery
            //rowType�F �e��A��Row.TYPE
            //where�F create�擾����������()�̖߂�l
            //orderBy�F create�\�[�g����������()�̖߂�l
            //condition�F null
            //���X�g�F create�擾�����f�[�^�R���e�i()�̖߂�l
            //�y�[�W�T�C�Y�F ���N�G�X�g�f�[�^.�y�[�W���\���s��
            //�y�[�W�ԍ��F ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� - 1
            ListPage l_lisVariousInformRows =
                l_queryProcessor.doFindAllQuery(
                    VariousInformRow.TYPE,
                    l_strQueryString,
                    l_strSortCondString,
                    null,
                    l_queryDataContainers,
                    Integer.parseInt(l_request.pageSize),
                    Integer.parseInt(l_request.pageIndex) - 1);

            //���R�[�h���擾�ł��Ȃ��ꍇ�A
            //��O�́uBUSINESS_ERROR_00398�i�Y������f�[�^�����݂��܂���B�j�v��throw����B
            if (l_lisVariousInformRows.isEmpty())
            {
                log.debug("�Y������f�[�^�����݂��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y������f�[�^�����݂��܂���B");
            }

            WEB3PageIndexInfo l_lisViewPageIndexInfo =
                new WEB3PageIndexInfo(
                    l_lisVariousInformRows,
                    Integer.parseInt(l_request.pageIndex),
                    Integer.parseInt(l_request.pageSize));

            int l_intVariousInformRowCnt = l_lisVariousInformRows.size();
            List l_lisAdminInformPTSAccountInfoUnits = new ArrayList();
            for (int i = 0; i < l_intVariousInformRowCnt; i++)
            {
                VariousInformRow l_variousInformRow = (VariousInformRow)l_lisVariousInformRows.get(i);
                WEB3AdminInformPTSAccountInfoUnit l_adminInformPTSAccountInfoUnit =
                    new WEB3AdminInformPTSAccountInfoUnit();

                //���X�R�[�h�F�@@�e��A��Params.���X�R�[�h
                l_adminInformPTSAccountInfoUnit.branchCode = l_variousInformRow.getBranchCode();
                //�ڋq�R�[�h�F�@@�e��A��Params.�ڋq�R�[�h�̓�6��
                if (l_variousInformRow.getAccountCode() != null)
                {
                    l_adminInformPTSAccountInfoUnit.accountCode =
                        l_variousInformRow.getAccountCode().substring(0, 6);
                }
                //�ڋq���F�@@�e��A��Params.�ڋq��
                l_adminInformPTSAccountInfoUnit.accountName = l_variousInformRow.getAccountName();
                //�\���敪�F�@@�e��A��Params.�敪�Q
                l_adminInformPTSAccountInfoUnit.ptsAccOpenDiv = l_variousInformRow.getExtDiv2();
                //��ԁF�@@�e��A��Params.�敪�P
                l_adminInformPTSAccountInfoUnit.status = l_variousInformRow.getExtDiv1();
                //�\�������F�@@�e��A��Params.�쐬����
                l_adminInformPTSAccountInfoUnit.applyDate = l_variousInformRow.getCreatedTimestamp();
                //�X�V�ҁF�@@�e��A��Params.�X�V�҃R�[�h
                l_adminInformPTSAccountInfoUnit.lastUpdater = l_variousInformRow.getLastUpdater();

                //add(arg0 : Object)
                l_lisAdminInformPTSAccountInfoUnits.add(l_adminInformPTSAccountInfoUnit);
            }

            //toArray
            WEB3AdminInformPTSAccountInfoUnit[] l_adminInformPTSAccountInfoUnits =
                new WEB3AdminInformPTSAccountInfoUnit[l_lisAdminInformPTSAccountInfoUnits.size()];
            l_lisAdminInformPTSAccountInfoUnits.toArray(l_adminInformPTSAccountInfoUnits);

            //�\���y�[�W�ԍ����擾����B
            int l_intPageIndex = l_lisViewPageIndexInfo.getPageIndex();

            //���y�[�W�����擾����B
            int l_intTotalPage = l_lisViewPageIndexInfo.getTotalPages();

            //�����R�[�h�����擾����B
            int l_intTotalRecord = l_lisViewPageIndexInfo.getTotalRecords();

            //�\���y�[�W�ԍ��F�@@pageNumber()�̖߂�l
            l_response.pageIndex = String.valueOf(l_intPageIndex);
            //���y�[�W���F�@@totalPages()�̖߂�l
            l_response.totalPages = String.valueOf(l_intTotalPage);
            //�����R�[�h���F�@@totalSize()�̖߂�l
            l_response.totalRecords = String.valueOf(l_intTotalRecord);
            //PTS�\���q���ꗗ�F�@@PTS�\���q���̔z��
            l_response.ptsAccountInfoList = l_adminInformPTSAccountInfoUnits;
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

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�擾����������)<BR>
     * �擾�����̕�����𐶐�����B<BR>
     * <BR>
     * �P�j�@@��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�A����ʁA�،���ЃR�[�h<BR>
     * <BR>
     * �@@�@@"inform_div=? and institution_code=?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@���X�R�[�h<BR>
     * <BR>
     * �R�|�P�j�@@����.��������.���X�R�[�h.length() == 1 �̏ꍇ<BR>
     * <BR>
     * �@@�@@" and branch_code=?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�|�Q�j�@@����.��������.���X�R�[�h.length() > 1 �̏ꍇ<BR>
     * <BR>
     * �@@�@@" and (branch_code=? or branch_code=? or ... or branch_code=?)" <BR>
     * �@@�@@�@@�@@���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@��"branch_code=?"�̐��́A����.��������.���X�R�[�h�̗v�f���Ɠ����ɂȂ�B<BR>
     * <BR>
     * �S�j�@@�ڋq�R�[�h<BR>
     * <BR>
     * �@@�@@����.��������.�ڋq�R�[�h != null �̏ꍇ<BR>
     * <BR>
     * �@@�@@" and account_code like '?%'" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �T�j�@@�L���t���O�A�\���敪<BR>
     * <BR>
     * �@@�@@����.��������.�\���敪 != null �̏ꍇ<BR>
     * <BR>
     * �@@�@@" and ext_div1='1' and ext_div2 =?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �U�j�@@�\������<BR>
     * <BR>
     * �U�|�P�j�@@����.��������.�\�������i���j != null �̏ꍇ<BR>
     * <BR>
     * �@@�@@" and created_timestamp>=?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �U�|�Q�j�@@����.��������.�\�������i���j != null �̏ꍇ<BR>
     * <BR>
     * �@@�@@" and created_timestamp<=?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �V�j�@@�������ꂽ�������ԋp����B<BR>
     * @@param l_queryCondition - (��������)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 47B9446F00D9
     */
    private String createQueryString(
        WEB3AdminInformPTSAccountListInqCondition l_queryCondition) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryString(WEB3AdminInformPTSAccountListInqCondition)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@��̕�����𐶐�����B
        StringBuffer l_sbQueryString = new StringBuffer();

        //�Q�j�@@�A����ʁA�،���ЃR�[�h
        l_sbQueryString.append(" inform_div = ? and institution_code = ? ");

        //�R�j�@@���X�R�[�h
        if (l_queryCondition.branchCode.length == 1)
        {
            //�R�|�P�j�@@����.��������.���X�R�[�h.length() == 1 �̏ꍇ
            //" and branch_code=?" ���P�j�̕�����ɒǉ�����B
            l_sbQueryString.append(" and branch_code = ? ");
        }
        else if (l_queryCondition.branchCode.length > 1)
        {
            //�R�|�Q�j�@@����.��������.���X�R�[�h.length() > 1 �̏ꍇ
            //" and (branch_code=? or branch_code=? or ... or branch_code=?)" ���P�j�̕�����ɒǉ�����B
            //��"branch_code=?"�̐��́A����.��������.���X�R�[�h�̗v�f���Ɠ����ɂȂ�B
            l_sbQueryString.append(" and (branch_code = ? ");
            for (int i = 0; i < (l_queryCondition.branchCode.length - 1); i++)
            {
                l_sbQueryString.append(" or branch_code = ? ");
            }
            l_sbQueryString.append(")");
        }

        //�S�j�@@�ڋq�R�[�h
        //����.��������.�ڋq�R�[�h != null �̏ꍇ
        //" and account_code like '?%'" ���P�j�̕�����ɒǉ�����B
        if (l_queryCondition.accountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%' ");
        }

        //�T�j�@@�L���t���O�A�\���敪
        //   ����.��������.�\���敪 != null �̏ꍇ
        //   " and ext_div1='1' and ext_div2 =?" ���P�j�̕�����ɒǉ�����B
        if (l_queryCondition.ptsAccOpenDiv != null)
        {
            l_sbQueryString.append(" and ext_div1 = '1' and ext_div2 = ? ");
        }

        //�U�j�@@�\������
        //�U�|�P�j�@@����.��������.�\�������i���j != null �̏ꍇ
        //   " and created_timestamp>=?" ���P�j�̕�����ɒǉ�����B
        if (l_queryCondition.applyDateFrom != null)
        {
            l_sbQueryString.append(" and created_timestamp >= ? ");
        }

        //�U�|�Q�j�@@����.��������.�\�������i���j != null �̏ꍇ
        //  " and created_timestamp<=?" ���P�j�̕�����ɒǉ�����B
        if (l_queryCondition.applyDateTo != null)
        {
            l_sbQueryString.append(" and created_timestamp <= ? ");
        }

        //�V�j�@@�������ꂽ�������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create�擾�����f�[�^�R���e�i)<BR>
     * �擾�����ɃZ�b�g����l�̔z��𐶐�����B<BR>
     * <BR>
     * �P�j�@@���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�A�����<BR>
     * <BR>
     * �@@�@@����.��������.�A����� ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@�،���ЃR�[�h<BR>
     * <BR>
     * �@@�@@����.�،���ЃR�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �S�j�@@���X�R�[�h<BR>
     * <BR>
     * �@@�@@����.��������.���X�R�[�h�̊e�v�f ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �T�j�@@�ڋq�R�[�h<BR>
     * <BR>
     * �@@�@@����.��������.�ڋq�R�[�h != null�̏ꍇ<BR>
     * <BR>
     * �@@�@@����.��������.�ڋq�R�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �U�j�@@�\���敪<BR>
     * <BR>
     * �@@�@@����.��������.�\���敪 != null�̏ꍇ<BR>
     * <BR>
     * �@@�@@����.��������.�\���敪 ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �W�j�@@�\������<BR>
     * <BR>
     * �W�|�P�j�@@<BR>
     * �@@�@@����.��������.�\�������i���j != null�̏ꍇ<BR>
     * <BR>
     * �@@�@@����.��������.�\�������i���j ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �W�|�Q�j�@@<BR>
     * �@@�@@����.��������.�\�������i���j != null�̏ꍇ<BR>
     * <BR>
     * �@@�@@����.��������.�\�������i���j ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �X�j�@@�������ꂽList����z����擾���A�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_queryCondition - (��������)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 47B9446F00E9
     */
    private Object[] createQueryDataContainer(
        String l_strInstitutionCode,
        WEB3AdminInformPTSAccountListInqCondition l_queryCondition) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String, WEB3AdminInformPTSAccountListInqCondition)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���ArrayList�𐶐�����B
        List l_lisQueryDataContainers = new ArrayList();

        //�Q�j�@@�A�����
        //   ����.��������.�A����� ���P�j��List�ɒǉ�����B
        l_lisQueryDataContainers.add(l_queryCondition.informType);

        //�R�j�@@�،���ЃR�[�h
        //  ����.�،���ЃR�[�h ���P�j��List�ɒǉ�����B
        l_lisQueryDataContainers.add(l_strInstitutionCode);

        //�S�j�@@���X�R�[�h
        //   ����.��������.���X�R�[�h�̊e�v�f ���P�j��List�ɒǉ�����B
        for (int i = 0; i < l_queryCondition.branchCode.length; i++)
        {
            l_lisQueryDataContainers.add(l_queryCondition.branchCode[i]);
        }

        //�T�j�@@�ڋq�R�[�h
        //   ����.��������.�ڋq�R�[�h != null�̏ꍇ
        //  ����.��������.�ڋq�R�[�h ���P�j��List�ɒǉ�����B
        if (l_queryCondition.accountCode != null)
        {
            l_lisQueryDataContainers.add(l_queryCondition.accountCode);
        }

        //�U�j�@@�\���敪
        //   ����.��������.�\���敪 != null�̏ꍇ
        //   ����.��������.�\���敪 ���P�j��List�ɒǉ�����B
        if (l_queryCondition.ptsAccOpenDiv != null)
        {
            l_lisQueryDataContainers.add(l_queryCondition.ptsAccOpenDiv);
        }

        //�W�j�@@�\������
        //�W�|�P�j   ����.��������.�\�������i���j != null�̏ꍇ
        //  ����.��������.�\�������i���j ���P�j��List�ɒǉ�����B
        if (l_queryCondition.applyDateFrom != null)
        {
            l_lisQueryDataContainers.add(l_queryCondition.applyDateFrom);
        }

        //�W�|�Q�j   ����.��������.�\�������i���j != null�̏ꍇ
        //  ����.��������.�\�������i���j ���P�j��List�ɒǉ�����B
        if (l_queryCondition.applyDateTo != null)
        {
            l_lisQueryDataContainers.add(l_queryCondition.applyDateTo);
        }

        //�X�j�@@�������ꂽList����z����擾���A�ԋp����B
        Object[] l_queryDataContainers = new Object[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_queryDataContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryDataContainers;
    }

    /**
     * (create�\�[�g����������)<BR>
     * �擾�f�[�^�̃\�[�g�����̕�����𐶐�����B<BR>
     * <BR>
     * �P�j�@@��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�\�[�g�L�[�z��̊e�v�f���Ɉȉ��̏������s���B�iLoop�����j<BR>
     * <BR>
     * �Q�|�P�j�@@�L�[���� == �h���X�R�[�h�h �̏ꍇ<BR>
     * <BR>
     * �@@�@@�E����/�~�� == "A"�i�����j �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@"branch_code"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�E����/�~�� == "D"�i�~���j �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@"branch_code desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �Q�|�Q�j�@@�L�[���� == �h�ڋq�R�[�h�h �̏ꍇ<BR>
     * <BR>
     * �@@�@@�E����/�~�� == "A"�i�����j �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@"account_code"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�E����/�~�� == "D"�i�~���j �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@"account_code desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �Q�|�R�j�@@�L�[���� == �h�\�������h �̏ꍇ<BR>
     * <BR>
     * �@@�@@�E����/�~�� == "A"�i�����j �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@"created_timestamp"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�E����/�~�� == "D"�i�~���j �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@"created_timestamp desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �Q�|�S�j�@@�Y���̗v�f���z����̍Ō�̗v�f�ł͂Ȃ��ꍇ<BR>
     * <BR>
     * �@@�@@", "���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@�������ꂽ�������ԋp����B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 47B9446F00EC
     */
    private String createSortCondString(
        WEB3AdminInformPTSAccountListInqSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSortCondString(WEB3AdminInformPTSAccountListInqSortKey[])";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@��̕�����𐶐�����B
        StringBuffer l_sbSortCondString = new StringBuffer();

        int l_intAddCnt = 0;
        //�Q�j�@@�\�[�g�L�[�z��̊e�v�f���Ɉȉ��̏������s���B�iLoop�����j
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            if (WEB3InformKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                //�Q�|�P�j�@@�L�[���� == �h���X�R�[�h�h �̏ꍇ
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    //�E����/�~�� == "A"�i�����j �̏ꍇ
                    l_sbSortCondString.append(" branch_code, ");
                }
                else
                {
                    //�E����/�~�� == "D"�i�~���j �̏ꍇ
                    l_sbSortCondString.append(" branch_code desc, ");
                }

                l_intAddCnt = l_intAddCnt + 1;
            }
            else if (WEB3InformKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //�Q�|�Q�j�@@�L�[���� == �h�ڋq�R�[�h�h �̏ꍇ
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    //�E����/�~�� == "A"�i�����j �̏ꍇ
                    l_sbSortCondString.append(" account_code, ");
                }
                else
                {
                    //�E����/�~�� == "D"�i�~���j �̏ꍇ
                    l_sbSortCondString.append(" account_code desc, ");
                }

                l_intAddCnt = l_intAddCnt + 1;
            }
            else if (WEB3InformKeyItemDef.APPLY_DATE.equals(l_sortKeys[i].keyItem))
            {
                //�Q�|�R�j�@@�L�[���� == �h�\�������h �̏ꍇ
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    //�E����/�~�� == "A"�i�����j �̏ꍇ
                    l_sbSortCondString.append(" created_timestamp, ");
                }
                else
                {
                    //�E����/�~�� == "D"�i�~���j �̏ꍇ
                    l_sbSortCondString.append(" created_timestamp desc, ");
                }

                l_intAddCnt = l_intAddCnt + 1;
            }
        }

        String l_strSortCondString = l_sbSortCondString.toString();
        if (l_intAddCnt > 0)
        {
            l_strSortCondString = l_strSortCondString.substring(0, (l_strSortCondString.length() - 2));
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSortCondString;
    }
}
@
