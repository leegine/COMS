head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLCashOutStopReleaseServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۃ��[���o���S���������T�[�r�X�����N���X(WEB3AdminAioSLCashOutStopReleaseServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����i���u�j�V�K�쐬 ���f��No.764
Revision History : 2007/09/18 �����i���u�j���f��No.768
Revision History : 2007/09/18 �����i���u�j���f��No.772
Revision History : 2007/09/20 �����i���u�j���f��No.779
Revision History : 2007/10/30 �����i���u�j�c�a�X�V�d�lNo.159
Revision History : 2007/10/31 �����F (���u) �d�l�ύX ���f��No.818
Revision History : 2007/11/08 ��іQ (���u) �d�l�ύX ���f��No.820
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.aio.define.WEB3SLSortKeyDef;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLRestraintMoneyListRequest;
import webbroker3.aio.message.WEB3AdminSLRestraintMoneyListResponse;
import webbroker3.aio.message.WEB3SLCashOutStopInfoUnit;
import webbroker3.aio.message.WEB3SLSortKey;
import webbroker3.aio.service.delegate.WEB3AdminAioSLCashOutStopReleaseService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AmountLockFlgDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.SecurityCashoutRestraintDao;
import webbroker3.gentrade.data.SecurityCashoutRestraintParams;
import webbroker3.gentrade.data.SecurityCashoutRestraintRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�S�ۃ��[���o���S���������T�[�r�XImpl)<BR>
 * �S�ۃ��[���o���S���������T�[�r�X�����N���X<BR>
 *
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3AdminAioSLCashOutStopReleaseServiceImpl implements WEB3AdminAioSLCashOutStopReleaseService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLCashOutStopReleaseServiceImpl.class);

    /**
     * �،��S�ۃ��[���o����~�����������J�n����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���،��S�ۃ��[���o����~�����m�F���N�G�X�g�̏ꍇ<BR>
     * this.validate�o����~�����m�F���()���R�[������B<BR>
     * <BR>
     * ���،��S�ۃ��[���o����~�����������N�G�X�g�̏ꍇ <BR>
     * this.submit�o����~�����������()���R�[������B<BR>
     * <BR>
     * ���،��S�ۃ��[���o���S�����ꗗ���N�G�X�g�̏ꍇ<BR>
     * this.get�S�ۃ��[���o���S�����ꗗ���()���R�[������B<BR>
     *
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
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

        // �،��S�ۃ��[���o����~�����m�F���N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminSLCashOutStopReleaseConfirmRequest)
        {
            // this.get�o���S���������m�F���()���R�[������
            l_response = this.validateSLCashOutStopReleaseConfirmScreen(
                (WEB3AdminSLCashOutStopReleaseConfirmRequest)l_request);
        }

        // �،��S�ۃ��[���o����~�����������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminSLCashOutStopReleaseCompleteRequest)
        {
            // this.get�o���S���������������()���R�[������
            l_response = this.submitSLCashOutStopCompleteScreen(
                (WEB3AdminSLCashOutStopReleaseCompleteRequest)l_request);
        }

        // �،��S�ۃ��[���o���S�����ꗗ���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminSLRestraintMoneyListRequest)
        {
            // this.get�S�ۃ��[���o���S�����ꗗ���()���R�[������
            l_response = this.getSLRestraintMoneyListScreen(
                (WEB3AdminSLRestraintMoneyListRequest)l_request);
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
     * (get�S�ۃ��[���o���S�����ꗗ���)<BR>
     * �S�ۃ��[���o���S�����ꗗ��ʕ\������<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     *�uget�S�ۃ��[���o���S�����ꗗ�v�Q�ƁB<BR>
     * ========================================================== <BR>
     * �V�[�P���X�} �F(�S�ۃ��[���o���S�������� / get�S�ۃ��[���o���S�����ꗗ) <BR>
     * ��̈ʒu�F(�����Ώۃ��R�[�h�����݂��Ȃ��ꍇ�A��O���X���[����B)<BR>
     * class: WEB3BusinessLayerException<BR>
     * �@@tag: BUSINESS_ERROR_00398 <BR>
     * ========================================================== <BR>
     * @@param l_request - (�،��S�ۃ��[���o���S�����ꗗ���N�G�X�g�f�[�^)<BR>
     * �،��S�ۃ��[���o���S�����ꗗ���N�G�X�g�f�[�^
     * @@return WEB3AdminSLRestraintMoneyListResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLRestraintMoneyListResponse getSLRestraintMoneyListScreen(
        WEB3AdminSLRestraintMoneyListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSLRestraintMoneyListScreen(WEB3AdminSLRestraintMoneyListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLRestraintMoneyListResponse l_response =
            (WEB3AdminSLRestraintMoneyListResponse)l_request.createResponse();

        // ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        // �Ǘ��҃I�u�W�F�N�g���擾����B
        // [validate����()�Ɏw�肷�����]
        // �@@�\�J�e�S���R�[�h�F"B0603"(�V�X�e���Ǘ� �،��S�ۃ��[��(�o���\�z����Ǘ�))

        // is�X�V�FFALSE
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PAYMENT_TRADING_POWER_CONTROL_MANAGE, false);

        // create�擾����������
        // ���X�R�[�h�F���N�G�X�g�f�[�^.���X�R�[�h
        // �ڋq�R�[�h�F���N�G�X�g�f�[�^.�ڋq�R�[�h
        // �o����~�敪�F���N�G�X�g�f�[�^.�o����~�敪
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        String l_strGetCondCharacter = this.createGetCondCharacterString(l_request.branchCode,
            l_request.accountCode, l_request.cashOutStopDiv);

        // create�擾�����f�[�^�R���e�i
        // �S�ۃ��[���o���S���e�[�u���擾�����f�[�^�R���e�i�쐬�B
        // [this.create�擾�����f�[�^�R���e�i()�Ɏw�肷�����]
        //�@@�،���ЃR�[�h�F�Ǘ���.get�،���ЃR�[�h()�̖߂�l
        //  ���X�R�[�h�F���N�G�X�g�f�[�^.���X�R�[�h 
        //�@@�ڋq�R�[�h�F���N�G�X�g�f�[�^.�ڋq�R�[�h
        //�@@�o����~�敪�F���N�G�X�g�f�[�^.�o����~�敪

        Object[] l_bindVars = this.createQueryDataContainer(l_strInstitutionCode, l_request.branchCode,
            l_request.accountCode, l_request.cashOutStopDiv);

        // �\�[�g�����������ҏW����B
        // [this.create�\�[�g�L�[()�Ɏw�肷�����]
        //�@@�\�[�g�L�[�F����:���N�G�X�g�f�[�^.���[���\�[�g�L�[
        String l_strSortKey = this.createSortKey(l_request.sortKeys);

        List l_lisSearchResults = null;

        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        try
        {
            // �S�ۃ��[���o���S�����e�[�u�����烌�R�[�h���擾����B
            // Row�^�C�v�F�@@�S�ۃ��[���o���S����Row.TYPE
            // Where�F�@@create�擾����������()�̖߂�l
            // orderBy�F�@@create�\�[�g�L�[()�̖߂�l
            // condition�F�@@null
            // ���X�g�F�@@create���������f�[�^�R���e�i()�̖߂�l
            // �y�[�W�T�C�Y�F�@@���N�G�X�g�f�[�^.�y�[�W���\���� ��int�^�ɕϊ������l
            // �y�[�W�ԍ��F�@@���N�G�X�g�f�[�^.�v���y�[�W�ԍ� - 1 ��int�^�ɕϊ������l

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisSearchResults = l_queryProcessor.doFindAllQuery(
                SecurityCashoutRestraintRow.TYPE,
                l_strGetCondCharacter,
                l_strSortKey,
                null,
                l_bindVars,
                l_intPageSize,
                l_intPageIndex - 1);
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

        // �����Ώۃ��R�[�h�����݂��Ȃ��ꍇ�A��O���X���[����
        if (l_lisSearchResults == null || l_lisSearchResults.isEmpty())
        {
            log.debug("�Y������f�[�^�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������f�[�^�����݂��܂���B");
        }

        // l_list : doFindAllQuery�i�j�̖߂�l
        // l_intRequestPageIndex :�@@���N�G�X�g�f�[�^.�v���y�[�W�ԍ���int�^�ɕϊ������l
        // l_intRequestPageSize :�@@���N�G�X�g�f�[�^.�y�[�W���\���s����int�^�ɕϊ������l
        WEB3PageIndexInfo l_web3PageIndexInfo =
            new WEB3PageIndexInfo(l_lisSearchResults, l_intPageIndex, l_intPageSize);

        List l_lisReturneds = l_web3PageIndexInfo.getListReturned();

        List l_lisCashOutStopInfos = new ArrayList();
        int l_intCntReturned = l_lisReturneds.size();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeMainAccount l_mainAccount = null;

        for (int i = 0; i < l_intCntReturned; i++)
        {
            SecurityCashoutRestraintRow l_securityCashoutRestraintRow =
                (SecurityCashoutRestraintRow)l_lisReturneds.get(i);

            // �ڋq���擾����
            // [get�ڋq�i�j�Ɏw�肷�����]
            //�@@�،���ЃR�[�h�F�S�ۃ��[���o���S����Row.get�،���ЃR�[�h()�̖߂�l
            //�@@���X�R�[�h�F�S�ۃ��[���o���S����Row.get���X�R�[�h()�̖߂�l
            //�@@�����R�[�h�F�S�ۃ��[���o���S����Row.get�ڋq�R�[�h()�̖߂�l

            l_mainAccount = l_accountManager.getMainAccount(
                l_securityCashoutRestraintRow.getInstitutionCode(),
                l_securityCashoutRestraintRow.getBranchCode(),
                l_securityCashoutRestraintRow.getAccountCode());

            // create�o����~���
            WEB3SLCashOutStopInfoUnit l_slCashOutStopInfoUnit =
                this.createCashOutStopInfoUnit(l_securityCashoutRestraintRow, l_mainAccount.getDisplayAccountName());

            l_lisCashOutStopInfos.add(l_slCashOutStopInfoUnit);
        }

        // �o����~���ꗗ�F
        l_response.cashOutStopInfoList = (WEB3SLCashOutStopInfoUnit[])l_lisCashOutStopInfos.toArray(
            new WEB3SLCashOutStopInfoUnit[l_lisCashOutStopInfos.size()]);

        // ���y�[�W
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_web3PageIndexInfo.getTotalPages());

        // �����R�[
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_web3PageIndexInfo.getTotalRecords());

        // �\���y�[�W�ԍ��F���N�G�X�g.�v���y�[�W�ԍ�
        l_response.pageIndex = l_request.pageIndex;

        log.exiting(STR_METHOD_NAME);

        return l_response;

    }

    /**
     * (validate�o����~�����m�F���)<BR>
     * �o����~�����m�F��ʕ\������<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidate�S�ۃ��[���o���S���������v�Q�ƁB<BR>
     * =============================================== <BR>
     * ��̈ʒu�@@�@@:�S�ۃ��[���o���S�����e�[�u��Row�ɂĊY���f�[�^���擾�ł��Ȃ��ꍇ�A<BR>
     * ��O��throw����B<BR>
     * class�@@:�@@WEB3BusinessLayerException  <BR>
     * tag�@@�@@:�@@BUSINESS_ERROR_01037<BR>
     * =============================================== <BR>
     *
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminSLCashOutStopReleaseConfirmResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLCashOutStopReleaseConfirmResponse validateSLCashOutStopReleaseConfirmScreen(
        WEB3AdminSLCashOutStopReleaseConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSLCashOutStopReleaseConfirmScreen(WEB3AdminSLCashOutStopReleaseConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // �Ǘ��҃I�u�W�F�N�g���擾����B
        // [validate����()�Ɏw�肷�����]
        // �@@�\�J�e�S���R�[�h�F"B0603"(�V�X�e���Ǘ� �،��S�ۃ��[��(�o���\�z����Ǘ�))

        // is�X�V�FTRUE
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PAYMENT_TRADING_POWER_CONTROL_MANAGE, true);

        // �S�ۃ��[���o���S�����e�[�u��Row���擾����
        SecurityCashoutRestraintRow l_securityCashoutRestraintRow =
            this.getSecurityCashoutRestraintRow(l_request.accountId);

        if (l_securityCashoutRestraintRow == null)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        // �ڋq���擾����B
        // [get�ڋq�i�j�Ɏw�肷�����]
        //�@@�،���ЃR�[�h�F�S�ۃ��[���o���S����Row.get�،���ЃR�[�h()�̖߂�l
        //�@@���X�R�[�h�F�S�ۃ��[���o���S����Row.get���X�R�[�h()�̖߂�l
        //�@@�����R�[�h�F�S�ۃ��[���o���S����Row.get�ڋq�R�[�h()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeMainAccount l_mainAccount = null;

        l_mainAccount = l_accountManager.getMainAccount(
            l_securityCashoutRestraintRow.getInstitutionCode(),
            l_securityCashoutRestraintRow.getBranchCode(),
            l_securityCashoutRestraintRow.getAccountCode());

        // create�o����~���
        WEB3SLCashOutStopInfoUnit l_slCashOutStopInfoUnit =
            this.createCashOutStopInfoUnit(l_securityCashoutRestraintRow, l_mainAccount.getDisplayAccountName());

        WEB3AdminSLCashOutStopReleaseConfirmResponse l_response =
            (WEB3AdminSLCashOutStopReleaseConfirmResponse)l_request.createResponse();

        l_response.cashOutStopInfo = l_slCashOutStopInfoUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�o����~�����������)<BR>
     * �o����~����������ʕ\������<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     *�usubmit�S�ۃ��[���o���S���������v�Q�ƁB<BR>
     *
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminSLCashOutStopReleaseCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLCashOutStopReleaseCompleteResponse submitSLCashOutStopCompleteScreen(
        WEB3AdminSLCashOutStopReleaseCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitSLCashOutStopCompleteScreen(WEB3AdminSLCashOutStopReleaseCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        // �Ǘ��҃I�u�W�F�N�g���擾����B
        // [validate����()�Ɏw�肷�����]
        // �@@�\�J�e�S���R�[�h�F"B0603"(�V�X�e���Ǘ� �،��S�ۃ��[��(�o���\�z����Ǘ�))

        // is�X�V�FTRUE
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PAYMENT_TRADING_POWER_CONTROL_MANAGE, true);

        // �Ïؔԍ��̃`�F�b�N���s��
        // [validate����p�X���[�h()�Ɏw�肷�����]
        // �p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        // �Ǘ��҃R�[�h���擾����
        String l_strAdminCode = l_administrator.getAdministratorCode();

        // �S�ۃ��[���o���S�����e�[�u�����X�V����
        // [update�S�ۃ��[���o���S�����e�[�u���i�j�Ɏw�肷�����]
        //�@@����ID�F���N�G�X�g�f�[�^.����ID
        //�@@�Ǘ��҃R�[�h�F�Ǘ���.get�Ǘ��҃R�[�h()�̖߂�l
        //�@@���ݎ����FTradingSystem.getSystemTimestamp()�̖߂�l

        Timestamp l_tsNowTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        this.updateSecurityCashoutRestraintRow(
            l_request.accountId,
            l_strAdminCode,
            l_tsNowTime);

        WEB3AdminSLCashOutStopReleaseCompleteResponse l_response =
            (WEB3AdminSLCashOutStopReleaseCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get�S�ۃ��[���o���S�����e�[�u�����R�[�h)<BR>
     * �S�ۃ��[���o���S����Row�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �S�ۃ��[���o���S�����e�[�u������A�i�����j����ID����L�[�Ƃ��Č������s���A<BR>
     * �擾����Row�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * ��)�擾�o���Ȃ��ꍇ��null��ԋp����B<BR>
     *
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID
     * @@return SecurityCashoutRestraintRow
     * @@throws WEB3BaseException
     */
      private SecurityCashoutRestraintRow getSecurityCashoutRestraintRow(long l_lngAccountId)
            throws WEB3BaseException
      {
        final String STR_METHOD_NAME =
            "getSecurityCashoutRestraintRow(long)";
        log.entering(STR_METHOD_NAME);

        SecurityCashoutRestraintRow l_securityCashoutRestraintRow = null;
        // �S�ۃ��[���o���S�����e�[�u������A�i�����j����ID����L�[�Ƃ��Č������s���A
        // �擾����Row�I�u�W�F�N�g��ԋp����B
        try
        {
            l_securityCashoutRestraintRow = SecurityCashoutRestraintDao.findRowByPk(l_lngAccountId);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

          log.exiting(STR_METHOD_NAME);
          return l_securityCashoutRestraintRow;
      }

    /**
     * (update�S�ۃ��[���o���S�����e�[�u��)<BR>
     * ��L�[�ŒS�ۃ��[���o���S�����e�[�u���̍X�V���s���B<BR>
     * <BR>
     * �i����)����ID����L�[�Ƃ��ĒS�ۃ��[���o���S�����e�[�u���� <BR>
     * ���z���b�N�t���O(�o����~��)���o����~��Ԃ��������Ԃ�UPDATE���s���B <BR>
     * <BR>
     * �P�j�X�V����<BR>
     * ���z���b�N�t���O�F 0 (�ʏ�)<BR>
     * �X�V�҃R�[�h�F (����)�Ǘ��҃R�[�h [�Ǘ���.get�Ǘ��҃R�[�h()�̖߂�l] <BR>
     * �X�V�����F (����)���ݎ��� [TradingSystem.getSystemTimestamp()�̖߂�l]<BR>
     * <BR>
     * ���j�uDB�X�V�d�l�v���Q�ƁB<BR>
     *
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID
     * @@param l_strAdminCode - (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h
     * @@param l_datNowTime - (���ݎ���)<BR>
     * ���ݎ���
     * @@throws WEB3BaseException
     */
    private void updateSecurityCashoutRestraintRow(
        long l_lngAccountId, String l_strAdminCode, Date l_datNowTime) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "updateSecurityCashoutRestraintRow(long, String, Date)";
        log.entering(STR_METHOD_NAME);
        // �P�j�X�V����
        // ���z���b�N�t���O�F�@@0 (�ʏ�)
        // �X�V�҃R�[�h�F�@@  (����)�Ǘ��҃R�[�h�@@[�Ǘ���.get�Ǘ��҃R�[�h()�̖߂�l]
        // �X�V�����F�@@      (����)���ݎ����@@[TradingSystem.getSystemTimestamp()�̖߂�l]

        try
        {
            SecurityCashoutRestraintRow l_securityCashoutRestraintRow =
                this.getSecurityCashoutRestraintRow(l_lngAccountId);
            if (l_securityCashoutRestraintRow == null)
            {
                log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����ɊY������f�[�^�����݂��Ȃ��B");
            }

            SecurityCashoutRestraintParams l_securityCashoutRestraintParams =
                new SecurityCashoutRestraintParams(l_securityCashoutRestraintRow);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_securityCashoutRestraintParams.setAmountLockFlg(WEB3AmountLockFlgDef.NORMAL);
            l_securityCashoutRestraintParams.setLastUpdater(l_strAdminCode);
            l_securityCashoutRestraintParams.setLastUpdatedTimestamp(l_datNowTime);

            l_queryProcessor.doUpdateQuery(l_securityCashoutRestraintParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�o����~���)<BR>
     * �o����~�����쐬����B<BR>
     * <BR>
     * �o����~���I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g���A�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�o����~���I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�o����~���I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B<BR>
     * ����ID�F�@@�@@�@@�@@�@@�@@(����)�S�ۃ��[���o���S����Row.����ID<BR>
     * ���X�R�[�h�F�@@�@@�@@�@@(����)�S�ۃ��[���o���S����Row.���X�R�[�h <BR>
     * �ڋq�R�[�h�F�@@�@@�@@�@@(����)�S�ۃ��[���o���S����Row.�ڋq�R�[�h <BR>
     * �ڋq���F�@@�@@�@@�@@�@@�@@(����)�ڋq�� <BR>
     * ���p�\�g�F�@@�@@�@@  (����)�S�ۃ��[���o���S����Row.���p�\�g <BR>
     * �o���S�����F�@@�@@�@@  (����)�S�ۃ��[���o���S����Row.�o���S���� <BR>
     * �o���\�z�F�@@�@@�@@  (����)�S�ۃ��[���o���S����Row.�o���\�z <BR>
     * �o����~�敪�F�@@    (����)�S�ۃ��[���o���S����Row.���z���b�N�t���O <BR>
     * <BR>
     * �R�j�o����~���I�u�W�F�N�g��ԋp����B<BR>
     *
     * @@param l_securityCashoutRestraintRow - (�S�ۃ��[���o���S����Row)<BR>
     * �S�ۃ��[���o���S����Row
     * @@param l_strAccountName - (�ڋq��)<BR>
     * �ڋq��
     * @@return WEB3SLCashOutStopInfoUnit
     */
    private WEB3SLCashOutStopInfoUnit createCashOutStopInfoUnit(
        SecurityCashoutRestraintRow l_securityCashoutRestraintRow, String l_strAccountName)
    {
        final String STR_METHOD_NAME =
            "createCashOutStopInfoUnit(SecurityCashoutRestraintRow, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�o����~���I�u�W�F�N�g�𐶐�����B
        WEB3SLCashOutStopInfoUnit l_slCashOutStopInfoUnit = new WEB3SLCashOutStopInfoUnit();

        // �Q�j�o����~���I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����

        // (����)�S�ۃ��[���o���S����Row.����ID
        l_slCashOutStopInfoUnit.accountId = l_securityCashoutRestraintRow.getAccountId();

        // (����)�S�ۃ��[���o���S����Row.���X�R�[�h
        l_slCashOutStopInfoUnit.branchCode = l_securityCashoutRestraintRow.getBranchCode();

        // (����)�S�ۃ��[���o���S����Row.�ڋq�R�[�h
        l_slCashOutStopInfoUnit.accountCode = l_securityCashoutRestraintRow.getAccountCode();

        // (����)�ڋq��
        l_slCashOutStopInfoUnit.accountName = l_strAccountName;

        // (����)�S�ۃ��[���o���S����Row.���p�\�g
        l_slCashOutStopInfoUnit.cashoutLimit = String.valueOf(l_securityCashoutRestraintRow.getUseEnableLimit());

        // (����)�S�ۃ��[���o���S����Row.�o���S����
        l_slCashOutStopInfoUnit.cashoutRestraint = String.valueOf(l_securityCashoutRestraintRow.getCashoutRestraint());

        // (����)�S�ۃ��[���o���S����Row.�o���\�z
        l_slCashOutStopInfoUnit.cashoutPossAmt = String.valueOf(l_securityCashoutRestraintRow.getCashoutEnablieAmount());

        // (����)�S�ۃ��[���o���S����Row.���z���b�N�t���O
        l_slCashOutStopInfoUnit.cashoutStopDiv = l_securityCashoutRestraintRow.getAmountLockFlg();

        log.exiting(STR_METHOD_NAME);
        return l_slCashOutStopInfoUnit;
    }

    /**
     * (create�擾����������)<BR>
     * �S�ۃ��[���o���S�����e�[�u������f�[�^���擾����ۂ̏����𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h <BR>
     * �@@�Q�|�P�j "institution_code=?"�@@���P�j�̕�����ɒǉ�����B<BR>
     *  <BR>
     * �R�j���X�R�[�h  <BR>
     * �@@�R�|�P�j(����)���X�R�[�h != null�@@�̏ꍇ  <BR>
     * �@@�@@" and branch_code=?"�@@���P�j�̕�����ɒǉ�����B<BR>
     *  <BR>
     * �S�j�ڋq�R�[�h<BR>
     * �@@�S�|�P�j(����)�ڋq�R�[�h != null�@@�̏ꍇ<BR>
     * �@@�@@" and account_code like ? %"�@@���P�j�̕�����ɒǉ�����B<BR>
     *  <BR>
     * �T�j���z���b�N�t���O<BR>
     * �@@�T�|�P�j(����)�o����~�敪 !=null�@@�̏ꍇ <BR>
     * �@@�@@" and amount_lock_flg=?"�@@���P�j�̕�����ɒǉ�����B<BR>
     *  <BR>
     * �U�j�������ԋp����B<BR>
     *
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���N�G�X�g�f�[�^.���X�R�[�h
     * @@param l_strAccountCode - (�ڋq�R�[�h) <BR>
     * ���N�G�X�g�f�[�^.�ڋq�R�[�h
     * @@param l_strCashOutStopDiv - (�o����~�敪) <BR>
     * ���N�G�X�g�f�[�^.�o����~�敪
     * @@return String
     */
    private String createGetCondCharacterString(String l_strBranchCode,
        String l_strAccountCode, String l_strCashOutStopDiv)
    {
        final String STR_METHOD_NAME =
            "createGetCondCharacterString(String, String, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j��̕�����𐶐�����B
        StringBuffer l_sbGetCondCharacter = new StringBuffer();

        //�Q�j�،���ЃR�[�h
        //�@@�Q�|�P�j "institution_code=?"�@@���P�j�̕�����ɒǉ�����B

        l_sbGetCondCharacter.append("institution_code=? ");

       //�R�j���X�R�[�h
       //�@@�R�|�P�j(����)���X�R�[�h != null�@@�̏ꍇ
       //�@@�@@" and branch_code=?"�@@���P�j�̕�����ɒǉ�����B
        if (l_strBranchCode != null)
        {
            l_sbGetCondCharacter.append(" and branch_code=? ");
        }

       // �S�j�ڋq�R�[�h
       //�@@�S�|�P�j(����)�ڋq�R�[�h != null�@@�̏ꍇ
       //    " and account_code like ? %"�@@���P�j�̕�����ɒǉ�����B
        if (l_strAccountCode != null)
        {
            l_sbGetCondCharacter.append(" and account_code like ? || '%'");
        }

        // �T�j���z���b�N�t���O
       // �@@�T�|�P�j(����)�o����~�敪 !=null�@@�̏ꍇ
       //�@@�@@" and amount_lock_flg=?"�@@���P�j�̕�����ɒǉ�����B
        if (l_strCashOutStopDiv != null)
        {
            l_sbGetCondCharacter.append(" and amount_lock_flg=?");
        }

        log.exiting(STR_METHOD_NAME);
        // �U�j�������ԋp����B
        return l_sbGetCondCharacter.toString();
    }

    /**
     * (create�擾�����f�[�^�R���e�i)<BR>
     * �S�ۃ��[���o���S�����e�[�u������f�[�^���擾����ۂ̏����̃f�[�^�R���e�i�𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * �@@�Q�|�P�j(����)�،���ЃR�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h <BR>
     * �@@�R�|�P�j(����)���X�R�[�h != null�@@�̏ꍇ<BR>
     *   (����)���X�R�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �S�j�ڋq�R�[�h<BR>
     * �@@�S�|�P�j(����)�ڋq�R�[�h != null�@@�̏ꍇ<BR>
     *   (����)�ڋq�R�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �T�j�o����~�敪<BR>
     * �@@�T�|�P�j(����)�o����~�敪 != null�@@�̏ꍇ<BR>
     * �@@(����)�o����~�敪���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �U�jList����z����擾���āA�ԋp����B<BR>
     *
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �Ǘ���.get�،���ЃR�[�h()�̖߂�l
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���N�G�X�g�f�[�^.���X�R�[�h
     * @@param l_strAccountCode - (�ڋq�R�[�h) <BR>
     * ���N�G�X�g�f�[�^.�ڋq�R�[�h
     * @@param l_strCashOutStopDiv - (�o����~�敪) <BR>
     * ���N�G�X�g�f�[�^.�o����~�敪
     * @@return Object[]
     */
    private Object[] createQueryDataContainer(String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountCode, String l_strCashOutStopDiv)
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j���ArrayList�𐶐�����B
        List l_lisQueryDataContainers = new ArrayList();

        // �Q�j�،���ЃR�[�h
         // �Q�|�P�j(����)�،���ЃR�[�h���P�j��List�ɒǉ�����B
        l_lisQueryDataContainers.add(l_strInstitutionCode);

        // �R�j���X�R�[�h
        // �R�|�P�j(����)���X�R�[�h != null�@@�̏ꍇ
        //�@@(����)���X�R�[�h���P�j��List�ɒǉ�����B
        if (l_strBranchCode != null)
        {
            l_lisQueryDataContainers.add(l_strBranchCode);
        }

        // �S�j�ڋq�R�[�h
        //  �S�|�P�j(����)�ڋq�R�[�h != null�@@�̏ꍇ
        //   (����)�ڋq�R�[�h���P�j��List�ɒǉ�����B
        if (l_strAccountCode != null)
        {
            l_lisQueryDataContainers.add(l_strAccountCode);
        }

        //�T�j�o����~�敪
        // �T�|�P�j(����)�o����~�敪 != null�@@�̏ꍇ
        //  (����)�o����~�敪���P�j��List�ɒǉ�����B
        if (l_strCashOutStopDiv != null)
        {
            l_lisQueryDataContainers.add(l_strCashOutStopDiv);
        }

        // �U�jList����z����擾���āA�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisQueryDataContainers.toArray(new Object[l_lisQueryDataContainers.size()]);
    }

    /**
     * (create�\�[�g�L�[)<BR>
     * �\�[�g�����������ҏW����B<BR>
     * <BR>
     * �e�[�u���񕨗������g�p���A�ȉ��̒ʂ�A�\�[�g����������iorder by��j��ҏW����B<BR>
     * <BR>
     * �P�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A<BR>
     * �@@�@@�@@�\�[�g������������쐬����B<BR>
     * <BR>
     * �@@�P�|�P�j�\�[�g�L�[.�L�[���ڂɑΉ�����e�[�u���񕨗������\�[�g�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@[�\�[�g�L�[.�L�[���� = ���X�R�[�h �̏ꍇ] <BR>
     * �@@�@@�@@�@@�@@�S�ۃ��[���o���S�����e�[�u��.branch_code<BR>
     * <BR>
     * �@@�@@[�\�[�g�L�[.�L�[���� = �ڋq�R�[�h �̏ꍇ] <BR>
     * �@@�@@�@@�@@�@@�S�ۃ��[���o���S�����e�[�u��.account_code<BR>
     * <BR>
     * �@@�P�|�Q�j�\�[�g�����ɊY������\�[�g������ҏW����B<BR>
     * �@@�@@�@@�@@�����Fasc <BR>
     * �@@�@@�@@�@@�~���Fdesc <BR>
     * <BR>
     * �Q�j �쐬�����\�[�g�����������ԋp����B<BR>
     *
     * @@param  l_sortKeys - (�\�[�g�L�[) <BR>
     * �S�ۃ��[���\�[�g�L�[�I�u�W�F�N�g
     * @@return String
     */
    private String createSortKey(WEB3SLSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME =
            "createSortKey(WEB3SLSortKey[])";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbSortKey = new StringBuffer();
        // �P�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A
        int l_intSortKeyCnt = l_sortKeys.length;

        for (int i = 0; i < l_intSortKeyCnt; i++)
        {
            // �P�|�P�j�\�[�g�L�[��ҏW
           // �\�[�g�L�[.�L�[���� = �ڋq�R�[�h �̏ꍇ
            if (WEB3SLSortKeyDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortKey.append(" account_code ");
            }
            else
            {
                l_sbSortKey.append(" branch_code ");
            }

            // �P�|�Q�j�\�[�g�����ɊY������\�[�g������ҏW����B
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortKey.append(" asc ,");
            }
            else
            {
                l_sbSortKey.append(" desc ,");
            }

        }

        // �Q�j �쐬�����\�[�g�����������ԋp����B
        l_sbSortKey = l_sbSortKey.deleteCharAt(l_sbSortKey.length()-1);
        log.exiting(STR_METHOD_NAME);
        return l_sbSortKey.toString();
    }

}
@
