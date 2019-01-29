head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.45.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistSellTransSrcListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����E���z���E���p����U����ꗗ�T�[�r�X�����N���X(WEB3AdminInformProfDistSellTransSrcListServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/24 �Ӑ�(���u) �V�K�쐬 ���f��No.046
Revision History    : 2007/06/12 �Ӑ�(���u) �d�l�ύX ���f��No.080
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.DirectDebitRow;
import webbroker3.inform.define.WEB3InformKeyItemDef;
import webbroker3.inform.define.WEB3InformProductFirstDef;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcCondition;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcSortKey;
import webbroker3.inform.message.WEB3AdminInformProfDistTransferInfo;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistSellTransSrcListService;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����E���z���E���p����U����ꗗ�T�[�r�X�����N���X)<BR>
 * �����E���z���E���p����U����ꗗ�T�[�r�X�����N���X<BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcListServiceImpl
    implements WEB3AdminInformProfDistSellTransSrcListService
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistSellTransSrcListServiceImpl.class);

    /**
     * @@roseuid 4655937403D6
     */
    public WEB3AdminInformProfDistSellTransSrcListServiceImpl()
    {

    }

    /**
     * �����E���z���E���p����U����ꗗ�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     * �Eget���͉��()<BR>
     * �Eget�ꗗ���()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 461F4E530134
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminInformProfDistSellTransSrcInpRequest)
        {
            //���͉��
            l_response = getInputScreen((WEB3AdminInformProfDistSellTransSrcInpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistSellTransSrcListRequest)
        {
            //�ꗗ���
            l_response = getListScreen((WEB3AdminInformProfDistSellTransSrcListRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����E���z���E���p����U����ꗗ�jget���͉�ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminInformProfDistSellTransSrcInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 461F4ED60214
     */
    protected WEB3AdminInformProfDistSellTransSrcInpResponse getInputScreen(
        WEB3AdminInformProfDistSellTransSrcInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminInformProfDistSellTransSrcInpRequest)";
        log.entering(STR_METHOD_NAME);

        //���͉�ʂ̎擾���s���B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //�����`�F�b�N���s���B
        //[����]
        //  �@@�\�J�e�S���R�[�h�F A0105�F�ڋq�o�^�Ǘ����j���[
        //  is�X�V�F false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU, false);

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminInformProfDistSellTransSrcInpResponse l_response =
            (WEB3AdminInformProfDistSellTransSrcInpResponse)l_request.createResponse();

        Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();

        //���X�|���X�f�[�^.�o�^���i���j = ���ݓ����̑O�c�Ɠ�
        l_response.registDateFrom = WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1);

        //���X�|���X�f�[�^.�o�^���i���j = ���ݓ����̑O��
        l_response.registDateTo = WEB3DateUtility.toDay(WEB3DateUtility.addDay(l_tsCurrentDate, -1));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ꗗ���)<BR>
     * �ꗗ��ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����E���z���E���p����U����ꗗ�jget�ꗗ��ʁv�Q��<BR>
     * ===============================================<BR>
     * �@@�V�[�P���X�}�@@:�@@�Ǘ��҂��q�l���i�����ҏ��ύX�jvalidate�ύX<BR>
     * �@@��̈ʒu�@@�@@�@@:�@@1.5.2  �S���X����舵���������Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     * �@@class�@@�@@�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@�@@�@@�@@�@@:�@@BUSINESS_ERROR_01380<BR>
     * ===============================================<BR>
     * �@@�V�[�P���X�}�@@:�@@�Ǘ��҂��q�l���i�����ҏ��ύX�jvalidate�ύX<BR>
     * �@@��̈ʒu�@@�@@�@@:�@@1.11  ���R�[�h���擾�ł��Ȃ��ꍇ�A��O��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u�Y���Ȃ��G���[�v��throw����B<BR>
     * �@@class�@@�@@�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@�@@�@@�@@�@@:�@@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminInformProfDistSellTransSrcListResponse
     * @@throws WEB3BaseException
     * @@roseuid 461F4EE1027D
     */
    protected WEB3AdminInformProfDistSellTransSrcListResponse getListScreen(
        WEB3AdminInformProfDistSellTransSrcListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminInformProfDistSellTransSrcListRequest)";
        log.entering(STR_METHOD_NAME);

        //�������`�F�b�N���s���B
        l_request.validate();

        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //�����`�F�b�N���s���B
        //[����]
        //  �@@�\�J�e�S���R�[�h�F A0105�F�ڋq�o�^�Ǘ����j���[
        //  is�X�V�F false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU, false);

        //���N�G�X�g�f�[�^.��������.���X�R�[�h���擾����B
        String l_branchCode = l_request.searchCondition.branchCode;
        //���X�R�[�h��null�łȂ��ꍇ
        if (l_branchCode != null)
        {
            //���Y�Ǘ��҂��A�w��̕��X����舵���邩���`�F�b�N����B
            l_administrator.validateBranchPermission(l_branchCode);
        }
        //���N�G�X�g�f�[�^.��������.���X�R�[�h��null�̏ꍇ
        else
        {
            //�Y���Ǘ��҂ɑS���X�f�[�^���������������邩�𔻒肷��B
            //�S���X����舵���������Ȃ��ꍇ�A��O��throw����B
            if (!l_administrator.isAllBranchsPermission())
            {
                log.debug("�S���X���̊Ǘ��҂łȂ��ꍇ�́A����s�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01380,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        //create��������������(�����E���z���E���p����U���挟������)
        //[����]
        //  ���������F ���N�G�X�g�f�[�^.��������
        String l_strQueryString = createQueryString(l_request.searchCondition);

        //�擾�����ɃZ�b�g����l�̔z��𐶐�����B
        //[����]
        //  �،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()
        //  ���������F ���N�G�X�g�f�[�^.��������
        Object[] l_queryContainer = createQueryDataContainer(
            l_administrator.getInstitutionCode(),
            l_request.searchCondition);

        //�\�[�g�����̕�����𐶐�����B
        //[����]
        //  �\�[�g�L�[�F ���N�G�X�g�f�[�^.�\�[�g�L�[
        String l_strSortCond = createSortCondString(l_request.sortKeys);

        //���N�G�X�g�f�[�^.�y�[�W���\���s��
        int l_intPageSize = Integer.parseInt(l_request.pageSize);

        //���N�G�X�g�f�[�^.�v���y�[�W�ԍ� - 1
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex) - 1;
        ListPage l_lisRecords = null;
        try
        {
            //�N�G���v���Z�b�T���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�����U�֓o�^�}�X�^�e�[�u�����烌�R�[�h���擾����B
            //[����]
            //  rowType�F �����U�֓o�^�}�X�^Row.TYPE
            //  where�F create�擾����������()�̖߂�l
            //  orderBy�F create�\�[�g����������()�̖߂�l
            //  condition�F null
            //  ���X�g�F create�擾�����f�[�^�R���e�i()�̖߂�l
            //  �y�[�W�T�C�Y�F ���N�G�X�g�f�[�^.�y�[�W���\���s��
            //  �y�[�W�ԍ��F ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� - 1
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                DirectDebitRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_queryContainer,
                l_intPageSize,
                l_intPageIndex);

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

        //���R�[�h���擾�ł��Ȃ��ꍇ�A��O�́u�Y���Ȃ��G���[�v��throw����B
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("�Y������f�[�^�����݂��܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
                l_lisRecords,
                Integer.parseInt(l_request.pageIndex), 
                l_intPageSize);

        //���ArrayList�𐶐�����B
        List l_lisInfoUnit = new ArrayList();

        //�擾�������R�[�h�����[�v
        int l_lisRecordsSize = l_lisRecords.size();
        for (int i = 0; i < l_lisRecordsSize; i++)
        {
            //�U������𐶐�����B
            //[����]
            //  �����U�֓o�^�}�X�^Row�F �������U�֓o�^�}�X�^Row
            //���X�g�ɐU�������ǉ�����B
            //[����]
            //  arg0�F create�U������()�̖߂�l
            l_lisInfoUnit.add(createTransferInfo((DirectDebitRow)l_lisRecords.get(i)));
        }

        //�z����擾����B
        WEB3AdminInformProfDistTransferInfo[] l_informInfoUnits =
            new WEB3AdminInformProfDistTransferInfo[l_lisRecordsSize];
        l_lisInfoUnit.toArray(l_informInfoUnits);

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminInformProfDistSellTransSrcListResponse l_response =
            (WEB3AdminInformProfDistSellTransSrcListResponse)l_request.createResponse();

        //���X�|���X�f�[�^.�U������ = �U������̔z��
        l_response.transferInfo = l_informInfoUnits;

        //���X�|���X.�\���y�[�W�ԍ� = pageNumber()�̖߂�l
        l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();
        log.debug("�\���y�[�W�ԍ�:" + l_pageIndexInfo.getPageIndex());

        //���X�|���X.���y�[�W�� = totalPages()�̖߂�l
        l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();
        log.debug("���y�[�W��:" + l_pageIndexInfo.getTotalPages());

        //���X�|���X.�����R�[�h�� = totalSize()�̖߂�l
        l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
        log.debug("�����R�[�h��:" + l_pageIndexInfo.getTotalRecords());


        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������)<BR>
     * �擾�����̕�����𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * <BR>
     * �@@�@@"institution_code=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h<BR>
     * <BR>
     * �@@�R�|�P�j����.��������.���X�R�[�h��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@" and branch_code=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �S�j���҃R�[�h<BR>
     * <BR>
     * �@@�S�|�P�j����.��������.���҃R�[�h��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@" and trader_code=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �T�j�ڋq�R�[�h<BR>
     * <BR>
     * �@@�T�|�P�j����.��������.�ڋq�R�[�h��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@" and account_code like '?%'"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �U�j�w��敪<BR>
     * <BR>
     * �@@�U�|�P�j����.��������.�w��敪��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@" and designate_div=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �V�j���i<BR>
     * <BR>
     * �@@�V�|�P�j����.��������.���i��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@" and comodity like '?%'"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �W�j�U�֋敪<BR>
     * <BR>
     * �@@�W�|�P�j����.��������.�U�֋敪��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@" and transfer_div=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �X�j�����R�[�h<BR>
     * <BR>
     * �@@�X�|�P�j����.��������.�����R�[�h��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@" and fund_code=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �P�O�j�o�^���i���j<BR>
     * <BR>
     * �@@�P�O�|�P�j����.��������.�o�^���i���j��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@" and sonar_created_timestamp>=?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �P�P�j�o�^���i���j<BR>
     * <BR>
     * �@@�P�P�|�P�j����.��������.�o�^���i���j��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@" and sonar_created_timestamp<?"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �P�Q�j�������ꂽ�������ԋp����B<BR>
     * @@param l_profDistSellTransSrcCondition - (��������)<BR>
     * ��������
     * @@return String
     * @@roseuid 461F4F24032B
     */
    private String createQueryString(
        WEB3AdminInformProfDistSellTransSrcCondition l_profDistSellTransSrcCondition)
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminInformProfDistSellTransSrcCondition)";
        log.entering(STR_METHOD_NAME);

        //�P�j��̕�����𐶐�����B
        StringBuffer l_sbQueryString = new StringBuffer();

        //�Q�j�،���ЃR�[�h
        //"institution_code=?"���P�j�̕�����ɒǉ�����B
        l_sbQueryString.append("institution_code=?");

        //�R�j���X�R�[�h
        //�R�|�P�j����.��������.���X�R�[�h��null�łȂ��ꍇ
        //" and branch_code=?"���P�j�̕�����ɒǉ�����B
        if (l_profDistSellTransSrcCondition.branchCode != null)
        {
            l_sbQueryString.append(" and branch_code=?");
        }

        //�S�j���҃R�[�h
        //�S�|�P�j����.��������.���҃R�[�h��null�łȂ��ꍇ
        //" and trader_code=?"���P�j�̕�����ɒǉ�����B
        if (l_profDistSellTransSrcCondition.traderCode != null)
        {
            l_sbQueryString.append(" and trader_code=?");
        }

        //�T�j�ڋq�R�[�h
        //�T�|�P�j����.��������.�ڋq�R�[�h��null�łȂ��ꍇ
        //" and account_code like '?%'"���P�j�̕�����ɒǉ�����B
        if (l_profDistSellTransSrcCondition.accountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%'");
        }

        //�U�j�w��敪
        //�U�|�P�j����.��������.�w��敪��null�łȂ��ꍇ
        //" and designate_div=?"���P�j�̕�����ɒǉ�����B
        if (l_profDistSellTransSrcCondition.specifyDiv != null)
        {
            l_sbQueryString.append(" and designate_div=?");
        }

        //�V�j���i
        //�V�|�P�j����.��������.���i��null�łȂ��ꍇ
        //" and comodity like '?%'"���P�j�̕�����ɒǉ�����B
        if (l_profDistSellTransSrcCondition.product != null)
        {
            l_sbQueryString.append(" and comodity like ? || '%'");
        }

        //�W�j�U�֋敪
        //�W�|�P�j����.��������.�U�֋敪��null�łȂ��ꍇ
        //" and transfer_div=?"���P�j�̕�����ɒǉ�����B
        if (l_profDistSellTransSrcCondition.transferDiv != null)
        {
            l_sbQueryString.append(" and transfer_div=?");
        }

        //�X�j�����R�[�h
        //�X�|�P�j����.��������.�����R�[�h��null�łȂ��ꍇ
        //" and fund_code=?"���P�j�̕�����ɒǉ�����B
        if (l_profDistSellTransSrcCondition.productCode != null)
        {
            l_sbQueryString.append(" and fund_code=?");
        }

        //�P�O�j�o�^���i���j
        //�P�O�|�P�j����.��������.�o�^���i���j��null�łȂ��ꍇ
        //" and sonar_created_timestamp>=?"���P�j�̕�����ɒǉ�����B
        if (l_profDistSellTransSrcCondition.registDateFrom != null)
        {
            l_sbQueryString.append(" and sonar_created_timestamp>=?");
        }

        //�P�P�j�o�^���i���j
        //�P�P�|�P�j����.��������.�o�^���i���j��null�łȂ��ꍇ
        //" and sonar_created_timestamp<=?"���P�j�̕�����ɒǉ�����B
        if (l_profDistSellTransSrcCondition.registDateTo != null)
        {
            l_sbQueryString.append(" and sonar_created_timestamp<?");
        }

        //�P�Q�j�������ꂽ�������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * �擾�����ɃZ�b�g����l�̔z��𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * <BR>
     * �@@����.�،���ЃR�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h<BR>
     * <BR>
     * �@@�R�|�P�j����.��������.���X�R�[�h��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@����.��������.���X�R�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �S�j���҃R�[�h<BR>
     * <BR>
     * �@@�S�|�P�j����.��������.���҃R�[�h��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@����.��������.���҃R�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �T�j�ڋq�R�[�h<BR>
     * <BR>
     * �@@�T�|�P�j����.��������.�ڋq�R�[�h��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@����.��������.�ڋq�R�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �U�j�w��敪<BR>
     * <BR>
     * �@@�U�|�P�j����.��������.�w��敪��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@����.��������.�w��敪���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �V�j���i<BR>
     * <BR>
     * �@@�V�|�P�j����.��������.���i��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@����.��������.���i�̓�1�����P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �W�j�U�֋敪<BR>
     * <BR>
     * �@@�W�|�P�j����.��������.�U�֋敪��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@����.��������.�U�֋敪���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �X�j�����R�[�h<BR>
     * <BR>
     * �@@�X�|�P�j����.��������.�����R�[�h��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@����.��������.�����R�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �P�O�j�o�^���i���j<BR>
     * <BR>
     * �@@�P�O�|�P�j����.��������.�o�^���i���j��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@����.��������.�o�^���i���j���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �P�P�j�o�^���i���j<BR>
     * <BR>
     * �@@�P�P�|�P�j����.��������.�o�^���i���j��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@����.��������.�o�^���i���j�̗������P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �P�Q�j�������ꂽList����z����擾���A�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_profDistSellTransSrcCondition - (��������)<BR>
     * ��������
     * @@return Object[]
     * @@roseuid 461F4F27005F
     */
    private Object[] createQueryDataContainer(
        String l_strInstitutionCode,
        WEB3AdminInformProfDistSellTransSrcCondition l_profDistSellTransSrcCondition)
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(String, WEB3AdminInformProfDistSellTransSrcCondition)";
        log.entering(STR_METHOD_NAME);

        //�P�j���ArrayList�𐶐�����B
        List l_lstQueryContainer = new ArrayList();

        //�Q�j�،���ЃR�[�h
        //����.�،���ЃR�[�h���P�j��List�ɒǉ�����B
        l_lstQueryContainer.add(l_strInstitutionCode);

        //�R�j���X�R�[�h
        //�R�|�P�j����.��������.���X�R�[�h��null�łȂ��ꍇ
        //����.��������.���X�R�[�h���P�j��List�ɒǉ�����B
        if (l_profDistSellTransSrcCondition.branchCode != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.branchCode);
        }

        //�S�j���҃R�[�h
        //�S�|�P�j����.��������.���҃R�[�h��null�łȂ��ꍇ
        //����.��������.���҃R�[�h���P�j��List�ɒǉ�����B
        if (l_profDistSellTransSrcCondition.traderCode != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.traderCode);
        }

        //�T�j�ڋq�R�[�h
        //�T�|�P�j����.��������.�ڋq�R�[�h��null�łȂ��ꍇ
        //����.��������.�ڋq�R�[�h���P�j��List�ɒǉ�����B
        if (l_profDistSellTransSrcCondition.accountCode != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.accountCode);
        }

        //�U�j�w��敪
        //�U�|�P�j����.��������.�w��敪��null�łȂ��ꍇ
        //����.��������.�w��敪���P�j��List�ɒǉ�����B
        if (l_profDistSellTransSrcCondition.specifyDiv != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.specifyDiv);
        }

        //�V�j���i
        //�V�|�P�j����.��������.���i��null�łȂ��ꍇ
        //����.��������.���i�̓�1�����P�j��List�ɒǉ�����B
        if (!WEB3StringTypeUtility.isEmpty(l_profDistSellTransSrcCondition.product))
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.product.substring(0, 1));
        }

        //�W�j�U�֋敪
        //�W�|�P�j����.��������.�U�֋敪��null�łȂ��ꍇ
        //����.��������.�U�֋敪���P�j��List�ɒǉ�����B
        if (l_profDistSellTransSrcCondition.transferDiv != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.transferDiv);
        }

        //�X�j�����R�[�h
        //�X�|�P�j����.��������.�����R�[�h��null�łȂ��ꍇ
        //����.��������.�����R�[�h���P�j��List�ɒǉ�����B
        if (l_profDistSellTransSrcCondition.productCode != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.productCode);
        }

        //�P�O�j�o�^���i���j
        //�P�O�|�P�j����.��������.�o�^���i���j��null�łȂ��ꍇ
        //����.��������.�o�^���i���j���P�j��List�ɒǉ�����B
        if (l_profDistSellTransSrcCondition.registDateFrom != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.registDateFrom);
        }

        //�P�P�j�o�^���i���j
        //�P�P�|�P�j����.��������.�o�^���i���j��null�łȂ��ꍇ
        //����.��������.�o�^���i���j�̗������P�j��List�ɒǉ�����B
        if (l_profDistSellTransSrcCondition.registDateTo != null)
        {
            l_lstQueryContainer.add(
                WEB3DateUtility.addDay(l_profDistSellTransSrcCondition.registDateTo, 1));
        }

        //�P�Q�j�������ꂽList����z����擾���A�ԋp����B
        Object[] l_queryContainer = new Object[l_lstQueryContainer.size()];
        l_lstQueryContainer.toArray(l_queryContainer);

        log.exiting(STR_METHOD_NAME);
        return l_queryContainer;
    }

    /**
     * (create�\�[�g����������)<BR>
     * �\�[�g�����̕�����𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�\�[�g�L�[�̊e�v�f���A�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ�"���X�R�[�h"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�P�j�\�[�g�L�[.����/�~����A�F�����̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@"branch_code"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�Q�j�\�[�g�L�[.����/�~����D�F�~���̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@"branch_code desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�\�[�g�L�[.�L�[���ڂ�"���҃R�[�h"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P�j�\�[�g�L�[.����/�~����A�F�����̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@"trader_code"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j�\�[�g�L�[.����/�~����D�F�~���̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@"trader_code desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�Q�|�R�j�\�[�g�L�[.�L�[���ڂ�"�ڋq�R�[�h"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�P�j�\�[�g�L�[.����/�~����A�F�����̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@"account_code"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�Q�j�\�[�g�L�[.����/�~����D�F�~���̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@"account_code desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�Q�|�S�j�\�[�g�L�[.�L�[���ڂ�"�����R�[�h"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�Q�|�S�|�P�j�\�[�g�L�[.����/�~����A�F�����̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@"fund_code"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�Q�|�S�|�Q�j�\�[�g�L�[.����/�~����D�F�~���̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@"fund_code desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�Q�|�T�j�\�[�g�L�[.�L�[���ڂ�"�o�^��"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�Q�|�T�|�P�j�\�[�g�L�[.����/�~����A�F�����̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@"sonar_created_timestamp"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�Q�|�T�|�Q�j�\�[�g�L�[.����/�~����D�F�~���̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@"sonar_created_timestamp desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�Q�|�U�j�Ώۂ̃\�[�g�L�[���z��̍Ō�̗v�f�łȂ��ꍇ<BR>
     * <BR>
     * �@@�@@", "���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j�������ꂽ�������ԋp����B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[�̔z��
     * @@return String
     * @@roseuid 461F4F290320
     */
    private String createSortCondString(WEB3AdminInformProfDistSellTransSrcSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = " createSortCondString(WEB3AdminInformProfDistSellTransSrcSortKey[])";
        log.entering(STR_METHOD_NAME);

        //�P�j��̕�����𐶐�����B
        StringBuffer l_sbSortCond = new StringBuffer();

        //�Q�j�\�[�g�L�[�̊e�v�f���A�ȉ��̏������J��Ԃ��B
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            String l_strSubCond;
            //�Q�|�P�j�\�[�g�L�[.�L�[���ڂ�"���X�R�[�h"�̏ꍇ
            if (WEB3InformKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strSubCond = "branch_code";
            }
            //�Q�|�Q�j�\�[�g�L�[.�L�[���ڂ�"���҃R�[�h"�̏ꍇ
            else if (WEB3InformKeyItemDef.TRADER_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strSubCond = "trader_code";
            }
            //�Q�|�R�j�\�[�g�L�[.�L�[���ڂ�"�ڋq�R�[�h"�̏ꍇ
            else if (WEB3InformKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strSubCond = "account_code";
            }
            //�Q�|�S�j�\�[�g�L�[.�L�[���ڂ�"�����R�[�h"�̏ꍇ
            else if (WEB3InformKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strSubCond = "fund_code";
            }
            //�Q�|�T�j�\�[�g�L�[.�L�[���ڂ�"�o�^��"�̏ꍇ
            else if (WEB3InformKeyItemDef.REGIST_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strSubCond = "sonar_created_timestamp";
            }
            else
            {
                continue;
            }
            //�Q�|�U�j�Ώۂ̃\�[�g�L�[���z��̍Ō�̗v�f�łȂ��ꍇ
            //", "���P�j�̕�����ɒǉ�����B
            if (l_sbSortCond.length() != 0)
            {
                l_sbSortCond.append(", ");
            }
            l_sbSortCond.append(l_strSubCond);

            if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCond.append(" desc");
            }
        }

        //�R�j�������ꂽ�������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbSortCond.toString();
    }

    /**
     * (create�U������)<BR>
     * �U������𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�����E���z���E���p����U����ꗗ�jcreate�U������v�Q��<BR>
     * @@param l_directDebitRow - (�����U�֓o�^�}�X�^Row)<BR>
     * �����U�֓o�^�}�X�^Row
     * @@return WEB3InformProfDistTransferInfo
     * @@throws WEB3BaseException
     * @@roseuid 4640298E029B
     */
    private WEB3AdminInformProfDistTransferInfo createTransferInfo(DirectDebitRow l_directDebitRow)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createTransferInfo(DirectDebitRow)";
        log.entering(STR_METHOD_NAME);

        //�����E���z���E���p����U������(�����U�֓o�^�}�X�^Row)
        //(�����E���z���E���p����U������::�����E���z���E���p����U������)
        WEB3AdminInformProfDistTransferInfo l_adminInformProfDistSellTransSrcInfo =
            new WEB3AdminInformProfDistTransferInfo(l_directDebitRow);

        //set�ڋq���(String, String, String, �����E���z���E���p����U������)
        //(�����E���z���E���p����U����ꗗ�T�[�r�XImpl::set�ڋq���)
        this.setAccountInfo(l_directDebitRow.getInstitutionCode(),
            l_adminInformProfDistSellTransSrcInfo.branchCode,
            l_adminInformProfDistSellTransSrcInfo.accountCode,
            l_adminInformProfDistSellTransSrcInfo);

        //set�������(String, String, String, �����E���z���E���p����U������)
        //(�����E���z���E���p����U����ꗗ�T�[�r�XImpl::set�������)
        this.setProductInfo(l_directDebitRow.getInstitutionCode(),
            l_adminInformProfDistSellTransSrcInfo.product,
            l_adminInformProfDistSellTransSrcInfo.productCode,
            l_adminInformProfDistSellTransSrcInfo);

        log.exiting(STR_METHOD_NAME);
        return l_adminInformProfDistSellTransSrcInfo;
    }

    /**
     * (set�ڋq���)<BR>
     * �ڋq�����擾���A�U������ɐݒ肷��B<BR>
     * <BR>
     * �P�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A<BR>
     * �@@�@@�@@�@@�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�F ����.���X�R�[�h<BR>
     * �@@�����R�[�h�F ����.�����R�[�h<BR>
     * <BR>
     * �@@���ڋq�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�A���^�[������B<BR>
     * <BR>
     * �Q�j�ڋq.getDataSourceObject()���R�[�����A�ڋqRow���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�Ȃ�<BR>
     * <BR>
     * �R�j�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����.�U������.�ڋq���i�����j = �ڋqRow.���O�i�c���j<BR>
     * �@@����.�U������.�ڋq���i�J�i�j = �ڋqRow.���O�i�c���P�j<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h
     * @@param l_transferInfo - (�U������)<BR>
     * �U������
     * @@throws WEB3BaseException
     * @@roseuid 464A5BB90322
     */
    private void setAccountInfo(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        WEB3AdminInformProfDistTransferInfo l_transferInfo)
    {
        String STR_METHOD_NAME = "setAccountInfo(String, String, String, WEB3AdminInformProfDistSellTransSrcInfo)";
        log.entering(STR_METHOD_NAME);

        //�P�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾����B
        //�@@[����]
        //�@@�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //�@@���X�R�[�h�F ����.���X�R�[�h
        //�@@�����R�[�h�F ����.�����R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�Q�j�ڋq.getDataSourceObject()���R�[�����A�ڋqRow���擾����B
        MainAccountRow l_mainAcountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //�R�j�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //�@@����.�U������.�ڋq���i�����j = �ڋqRow.���O�i�c���j
        //�@@����.�U������.�ڋq���i�J�i�j = �ڋqRow.���O�i�c���P�j
        l_transferInfo.accountName = l_mainAcountRow.getFamilyName();
        l_transferInfo.accountNameKana = l_mainAcountRow.getFamilyNameAlt1();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�������)<BR>
     * ���������擾���A�U������ɐݒ肷��B<BR>
     * <BR>
     * �P�j����.�����R�[�h��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�P�|�P�j�g���A�J�E���g�}�l�[�W��.getInstitution()���R�[�����A�،���ЃI�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،���ЃR�[�h�F ����.�،���ЃR�[�h<BR> 
     * <BR>
     * �@@�P�|�Q�jthis.is���M����()���R�[�����A���i�����M�������ۂ��𔻒肷��B<BR> 
     * <BR>
     * �@@�@@[����]<BR> 
     * �@@�@@���i�F ����.���i
     * <BR>
     * �@@�P�|�R�j���M�����̏ꍇ�iis���M����()==true�j<BR>
     * <BR>
     * �@@�@@�P�|�R�|�P�j�g�����M�����}�l�[�W��.get���M����()���R�[�����A<BR>
     * �@@�@@�@@�@@�g�����M�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F �،����<BR>
     * �@@�@@�@@�����R�[�h�F ����.�����R�[�h<BR>
     * <BR>
     * �@@�@@�@@���g�����M�����I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�A���^�[������B<BR>
     * <BR>
     * �@@�@@�P�|�R�|�Q�j�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@����.�U������.������ = �g�����M����.get������()<BR>
     * <BR>
     * �@@�P�|�S�j���̑��̏ꍇ<BR>
     * <BR>
     * �@@�@@�P�|�S�|�P�j�������}�l�[�W��.get������()���R�[�����A<BR>
     * �@@�@@�@@�@@�������I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F �،����<BR>
     * �@@�@@�@@�����R�[�h�F ����.�����R�[�h<BR>
     * <BR>
     * �@@�@@�@@���g�����M�����I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�A���^�[������B<BR>
     * <BR>
     * �@@�@@�P�|�S�|�Q�j�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@����.�U������.������ = ������.get������()<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strProduct - (���i)<BR>
     * ���i
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h
     * @@param l_transferInfo - (�U������)<BR>
     * �U������
     * @@throws WEB3BaseException
     * @@roseuid 464A5BBC0082
     */
    private void setProductInfo(
        String l_strInstitutionCode,
        String l_strProduct,
        String l_strProductCode,
        WEB3AdminInformProfDistTransferInfo l_transferInfo) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " setProductInfo(String, String, String, WEB3AdminInformProfDistSellTransSrcInfo)";
        log.entering(STR_METHOD_NAME);

        boolean l_blnIsMutualProduct = false;
        //�P�j����.�����R�[�h��null�łȂ��ꍇ
        if (l_strProductCode != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //�P�|�P�j�g���A�J�E���g�}�l�[�W��.getInstitution()���R�[�����A�،���ЃI�u�W�F�N�g���擾����B
            Institution l_institution = null;
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            try
            {
                l_institution = (Institution)l_accountManager.getInstitution(l_strInstitutionCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�P�|�Q�jthis.is���M����()���R�[�����A���i�����M�������ۂ��𔻒肷��B
            l_blnIsMutualProduct = this.isMutualProduct(l_strProduct);

            //�P�|�R�j���M�����̏ꍇ�iis���M����()==true�j
            if (l_blnIsMutualProduct)
            {
                //�P�|�R�|�P�j�g�����M�����}�l�[�W��.get���M����()���R�[�����A�g�����M�����I�u�W�F�N�g���擾����B
                WEB3MutualFundProduct l_mutualFundProduct = null;

                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
                WEB3MutualFundProductManager l_mutualFundProductManager =
                    (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
                try
                {
                    //�g�����M�����I�u�W�F�N�g���擾
                    l_mutualFundProduct =
                        (WEB3MutualFundProduct)l_mutualFundProductManager.getMutualFundProduct(
                            l_institution,
                            l_strProductCode);

                    //�P�|�R�|�Q�j�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
                    l_transferInfo.productName = l_mutualFundProduct.getMutualProductName();
                }
                catch (NotFoundException l_ex)
                {
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
            }
            else
            {
                //�P�|�S�j���̑��̏ꍇ
                //�P�|�S�|�P�j�������}�l�[�W��.get������()���R�[�����A�������I�u�W�F�N�g���擾����B
                WEB3BondProduct l_bondProduct = null;

                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
                WEB3BondProductManager l_bondProductManager =
                    (WEB3BondProductManager)l_tradingModule.getProductManager();
                try
                {
                    //�������I�u�W�F�N�g���擾����
                    l_bondProduct =
                        (WEB3BondProduct)l_bondProductManager.getBondProduct(l_institution, l_strProductCode);

                    //�P�|�S�|�Q�j�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
                    l_transferInfo.productName = l_bondProduct.getProductName();
                }
                catch (NotFoundException l_ex)
                {
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is���M����)<BR>
     * ���i�����M�������ۂ��𔻒肷��B<BR>
     * <BR>
     * true�F���M�����̏ꍇ<BR>
     * false�F���̑��̏ꍇ<BR>
     * <BR>
     * �P�j����.���i��null�łȂ��ꍇ<BR>
     * <BR>
     * �@@�P�|�P�j����.���i�̓�1����2�F�����M���A�܂��́A<BR>
     * �@@�@@�@@�@@�@@R�F�I�[�v�������R�[�X�̏ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �Q�jfalse��ԋp����B<BR>
     * @@param l_strProduct - (���i)<BR>
     * ���i
     * @@return boolean
     * @@roseuid 465270F70183
     */
    private boolean isMutualProduct(String l_strProduct)
    {
        String STR_METHOD_NAME = "isMutualProduct(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j����.���i��null�łȂ��ꍇ
        if (l_strProduct != null && l_strProduct.length() > 0)
        {
            //����.���i�̓�1�����擾
            String l_strFont = l_strProduct.substring(0, 1);
            if (WEB3InformProductFirstDef.MUTUAL_FUND.equals(l_strFont) ||
                WEB3InformProductFirstDef.OPEN_KABUTOU_COURSE.equals(l_strFont))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
