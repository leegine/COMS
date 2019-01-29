head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������޽Impl(WEB3AdminAccInfoAccEstablishSearchServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09 �����q (���u) �V�K�쐬
                 : 2006/11/20 ��іQ (���u) ���f�� No.143
                 : 2006/11/24 ���� (���u) ���f�� No.147�A149
                 : 2006/12/06 ���� (���u) ���f�� No.151
                 : 2006/10/09 �����q (���u) ���f�� No.152
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoAccEstablishDownLoadCSV;
import webbroker3.accountinfo.define.WEB3AccInfoDataContentDef;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.define.WEB3AccInfoLoginLockDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoAccEstablishSearchInfo;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchDLRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchDLResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoAccEstablishSearchService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3LoginDisabledFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.ExclusiveUseAccountRow;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������޽Impl)<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */

public class WEB3AdminAccInfoAccEstablishSearchServiceImpl
    extends WEB3AccInfoClientRequestService
    implements WEB3AdminAccInfoAccEstablishSearchService

{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAccInfoAccEstablishSearchServiceImpl.class);

    /**
     * �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������������{����B<BR>
     * <BR>
     * �P�j���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     *    �� �����̃��N�G�X�g�f�[�^���A<BR>
     * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq����<BR>
     * ����ظ��Ă̏ꍇ<BR>
     *    �@@�E get���͉��()���R�[������B<BR>
     * <BR>
     *    �� �����̃��N�G�X�g�f�[�^���A<BR>
     * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq����<BR>
     * �ꗗظ��Ă̏ꍇ<BR>
     *    �@@�E create�ꗗ()���R�[������B<BR>
     * <BR>
     *    �� �����̃��N�G�X�g�f�[�^���A<BR>
     * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq����<BR>
     * �޳�۰��ظ��Ă̏ꍇ<BR>
     *    �@@�E get�_�E�����[�h�t�@@�C��()���R�[������B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

        //�P�j���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B
        // �����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���V�K�����J�݁E
        //�����ڊǁE���O�C�����b�N�ڋq��������ظ��Ă̏ꍇ
        //      �@@�E get���͉��()���R�[������B
        if (l_request instanceof WEB3AdminAccInfoAccEstablishSearchInputRequest)
        {
            l_response =
                this.getInputScreen(
                    (WEB3AdminAccInfoAccEstablishSearchInputRequest)l_request);
        }

        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE
        //���O�C�����b�N�ڋq�����ꗗظ��Ă̏ꍇ
        //      �@@�E create�ꗗ()���R�[������B
        else if (l_request instanceof WEB3AdminAccInfoAccEstablishSearchListRequest)
        {
            l_response =
                this.getListScreen(
                    (WEB3AdminAccInfoAccEstablishSearchListRequest)l_request);
        }

        //�����̃��N�G�X�g�f�[�^���A�Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE
        //���O�C�����b�N�ڋq�����޳�۰��ظ��Ă̏ꍇ
        //      �@@�E get�_�E�����[�h�t�@@�C��()���R�[������B
        else if (l_request instanceof WEB3AdminAccInfoAccEstablishSearchDLRequest)
        {
            l_response =
                this.getDownloadFile(
                    (WEB3AdminAccInfoAccEstablishSearchDLRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���q�l���i�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����jget���͉�ʁv<BR>
     * �Q��<BR>
     * @@param l_request -�i���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������̓��N�G�X�g
     * @@return WEB3AdminAccInfoAccEstablishSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected  WEB3AdminAccInfoAccEstablishSearchInputResponse getInputScreen(
        WEB3AdminAccInfoAccEstablishSearchInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoAccEstablishSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.2) validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);

        //1.3) createResponse()
        WEB3AdminAccInfoAccEstablishSearchInputResponse l_response =
            (WEB3AdminAccInfoAccEstablishSearchInputResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g

        Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();
        
        //�����J�ݓ��i���j:GtlUtils.getSystemTimestamp()�̑O�c�Ɠ�
        l_response.accountOpenDateFrom = WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1);

        //�����J�ݓ��i���j:GtlUtils.getSystemTimestamp()�̑O��
        String l_strDate = WEB3DateUtility.formatDate(WEB3DateUtility.addDay(l_tsCurrentDate, -1), "yyyyMMdd");
        l_response.accountOpenDateTo = WEB3DateUtility.getDate(l_strDate , "yyyyMMdd");

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ꗗ���)<BR>
     * �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����ꗗ��ʕ\���������s���B<BR>
     * <BR>
     *  �V�[�P���X�} <BR>
     *  �u���q�l���i�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����jget�ꗗ��ʁv<BR>
     *  �Q��<BR>
     *  ========================================================<BR>
     *  1.10.10�i*5�j�w���X�g�x�̗v�f����MAX���������𒴂����ꍇ�A<BR>
     *  ��O�iBUSINESS_ERROR_02418�j��throw����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02418<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *  1.11�i*6�j���X�g�̗v�f����0���̏ꍇ�A<BR>
     *  ��O�iBUSINESS_ERROR_01037�j��throw����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����ꗗ���N�G�X�g
     * @@return WEB3AdminAccInfoAccEstablishSearchListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected  WEB3AdminAccInfoAccEstablishSearchListResponse getListScreen(
        WEB3AdminAccInfoAccEstablishSearchListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminAccInfoAccEstablishSearchListRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1  validate( )
        l_request.validate();

        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3) validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);

        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //1.5 create��������������(String, String[], String, String, String, Date, Date, String)
        String l_strQueryString =
            this.createQueryString(
                l_request.branchCode,
                l_request.traderCode,
                l_request.accountCode,
                l_request.accountTypeCode,
                l_request.accountOpenDateFrom,
                l_request.accountOpenDateTo,
                l_request.accountNameKana);

        //1.6 create���������f�[�^�R���e�i(String, String[], String, String, String, Date, Date, String)
        String[] l_strQueryContainers =
            this.createQueryContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.traderCode,
                l_request.accountCode,
                l_request.accountTypeCode,
                l_request.accountOpenDateFrom,
                l_request.accountOpenDateTo,
                l_request.accountNameKana);

        //1.7 create�\�[�g����(���q�l���\�[�g�L�[[])
        String l_strSortCond = this.createSortCond(l_request.sortKeys);

        //1.8 get�ڋq(String, String[], String)
        //[����]
        // ��������������F�@@create��������������()�̖߂�l
        // ���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l
        // �\�[�g�����F�@@create�\�[�g����()�̖߂�l
        List l_lisMainAccount =
            WEB3GentradeMainAccount.getMainAccount(
                l_strQueryString,
                l_strQueryContainers,
                l_strSortCond);

        //1.9 ArrayList( )
        List l_lisAccInfoOpenSearchInfo = new ArrayList();

        //1.10  (*1)get�ڋq()�̖߂�l�̓��A�\���Ώۍs(fromIndex �` toIndex)�̊�Loop����
        int l_intpageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intpageSize = Integer.parseInt(l_request.pageSize);

        int l_intMainAccount = 0;
        if (l_lisMainAccount != null && l_lisMainAccount.size() != 0)
        {
            l_intMainAccount = l_lisMainAccount.size();
        }
        for (int i = 0; i < l_intMainAccount; i++)
        {
            //1.10.1  is�f�[�^���e(String, Date, String, String)
            WEB3GentradeMainAccount l_row = (WEB3GentradeMainAccount)l_lisMainAccount.get(i);           
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_row.getDataSourceObject();
            
            boolean l_blnDataCountent =
                this.isDataContent(
                    l_request.dataContentDiv,
                    l_mainAccountRow.getAccountOpenDate(),
                    l_strInstitutionCode,
                    l_mainAccountRow.getBranchCode(),
                    l_mainAccountRow.getAccountCode());

            //1.10.2 (*2) is�f�[�^���e�i�j�̖߂�l = false�̏ꍇ�A���Y�v�f�̏����𒆒f�icontinue�j
            if (!l_blnDataCountent)
            {
                continue;
            }

            //1.10.3 get���O�C�����R�[�h(long)
            long l_lngAccountId = l_mainAccountRow.getAccountId();
            List l_lisLoginRecord = this.getLoginRecord(l_lngAccountId);

            // (*3) get���O�C�����R�[�h()�̖߂�l�̗v�f����0���̏ꍇ�A���Y�v�f�̏����𒆒f�icontinue�j
            if (l_lisLoginRecord.isEmpty())
            {
                log.debug("get���O�C�����R�[�h()�̖߂�l�̗v�f����0���̏ꍇ�A���Y�v�f�̏����𒆒f");
                continue;
            }
            log.debug("get���O�C�����R�[�h()�̖߂�l�̗v�f��!=0���̏ꍇ");
            LoginRow l_logintRow = (LoginRow)l_lisLoginRecord.get(0);

            //1.10.4 is���O�C�����b�N(String, long, long)
            //[����]
            // ���O�C�����b�N�敪�F�@@���N�G�X�g�f�[�^.���O�C�����b�N�敪
            // ���O�C���L�����F�@@get���O�C�����R�[�h().get(0).���O�C���L����
            // ���O�C���G���[�񐔁F�@@get���O�C�����R�[�h().get(0).���O�C���G���[��

            boolean l_blnIsLoginLock =
                this.isLoginLock(l_request.loginLockDiv,
                    l_logintRow.getDisabled(),
                    l_logintRow.getFailureCount());

            //1.10.5 (*3) is���O�C�����b�N�i�j�̖߂�l = false�̏ꍇ�A���Y�v�f�̏����𒆒f�icontinue�j
            if (!l_blnIsLoginLock)
            {
                continue;
            }

            //1.10.6 get��p�U����������R�[�h(long)
            List l_lisExclusiveUseAccountRecord =
                this.getExclusiveUseAccountRecord(l_lngAccountId);

            int l_intExclusiveUseAccountRecord = l_lisExclusiveUseAccountRecord.size();

            //1.10.7 �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq���i�j
            WEB3AccInfoAccEstablishSearchInfo l_accInfoOpenSearchInfo =
                new WEB3AccInfoAccEstablishSearchInfo();

            //1.10.8 (*4)�v���p�e�B�Z�b�g
            l_accInfoOpenSearchInfo.branchCode = l_mainAccountRow.getBranchCode();
            l_accInfoOpenSearchInfo.traderCode = l_mainAccountRow.getSonarTraderCode();
            l_accInfoOpenSearchInfo.accountCode = l_mainAccountRow.getAccountCode().substring(0, 6);

            if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
            {
                l_accInfoOpenSearchInfo.accountTypeCode =
                    MainAccountTypeEnum.JOINT_OWNERSHIP.intValue() + "";
            }
            else
            {
                l_accInfoOpenSearchInfo.accountTypeCode =
                    MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.intValue() + "";
            }

            l_accInfoOpenSearchInfo.accountName = l_mainAccountRow.getFamilyName();

            l_accInfoOpenSearchInfo.accountNameKana = l_mainAccountRow.getFamilyNameAlt1();

            l_accInfoOpenSearchInfo.accountOpenDate = l_mainAccountRow.getAccountOpenDate();

            if (l_intExclusiveUseAccountRecord == 0)
            {
                l_accInfoOpenSearchInfo.payFinancialInstitution = null;
                l_accInfoOpenSearchInfo.financialBranchName = null;
                l_accInfoOpenSearchInfo.item = null;
                l_accInfoOpenSearchInfo.financialInstitutionNumber = null;
                l_accInfoOpenSearchInfo.financialBranchCode = null;
                l_accInfoOpenSearchInfo.financialAccountCode = null;
            }
            else
            {
                ExclusiveUseAccountRow l_exclusiveUseAccountRow =
                    (ExclusiveUseAccountRow)l_lisExclusiveUseAccountRecord.get(0);

                l_accInfoOpenSearchInfo.payFinancialInstitution =
                    l_exclusiveUseAccountRow.getFinInstitutionName();

                l_accInfoOpenSearchInfo.financialBranchName =
                    l_exclusiveUseAccountRow.getFinBranchName();

                l_accInfoOpenSearchInfo.item =
                    l_exclusiveUseAccountRow.getFinAccountTypeName();

                l_accInfoOpenSearchInfo.financialInstitutionNumber =
                    l_exclusiveUseAccountRow.getFinInstitutionCode();

                l_accInfoOpenSearchInfo.financialBranchCode =
                    l_exclusiveUseAccountRow.getFinBranchCode();

                l_accInfoOpenSearchInfo.financialAccountCode =
                    l_exclusiveUseAccountRow.getFinAccountNo();
            }

            l_accInfoOpenSearchInfo.zipCode = l_mainAccountRow.getZipCode();
            l_accInfoOpenSearchInfo.address1 = l_mainAccountRow.getAddressLine1();
            l_accInfoOpenSearchInfo.address2 = l_mainAccountRow.getAddressLine2();
            l_accInfoOpenSearchInfo.address3 = l_mainAccountRow.getAddressLine3();

            String l_strDisabled = l_logintRow.getDisabled() + "";
            if (WEB3LoginDisabledFlagDef.ACCINFO_DISABLED.equals(l_strDisabled) && l_logintRow.getFailureCount() >= 1)
            {
                l_accInfoOpenSearchInfo.loginLockFlag = true;
            }
            else
            {
                l_accInfoOpenSearchInfo.loginLockFlag = false;
            }

            l_accInfoOpenSearchInfo.loginErrorCount =
                l_logintRow.getFailureCount() + "";

            //1.10.9 add(Object)
            l_lisAccInfoOpenSearchInfo.add(l_accInfoOpenSearchInfo);

            //1.10.10 �i*5�j�w���X�g�x�̗v�f����MAX���������𒴂����ꍇ�A
            //��O�iBUSINESS_ERROR_02418�j��throw����B
            int l_intMaxCount = Integer.parseInt(l_request.maxCount);

            // �w���X�g�x.size > ���N�G�X�g�f�[�^.MAX��������
            // ���L�����̏ꍇ�A��L��MAX���������`�G�c�N�͍s��Ȃ��B
            // ���N�G�X�g�f�[�^. ���O�C�����b�N�敪 = 1�F�@@���O�C�����b�N�q &&
            // ���N�G�X�g�f�[�^. �f�[�^���e�敪 = 00�F�@@�f�[�^���e���I��
            if (!(WEB3AccInfoLoginLockDivDef.LOGIN_LOCK_ACCOUNT.equals(l_request.loginLockDiv)
                && WEB3AccInfoDataContentDef.DATA_CONTENT_NOT_SELECT.equals(l_request.dataContentDiv)))
            {
                if (l_lisAccInfoOpenSearchInfo.size() > l_intMaxCount)
                {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02418,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���R�[�h�������������E�l�𒴂��Ă��܂��B");
                }
            }
        }
        

        //1.11  �i*6�j���X�g�̗v�f����0���̏ꍇ�A��O�iBUSINESS_ERROR_01037�j��throw����B
        if (l_lisAccInfoOpenSearchInfo.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        //1.12 toArray( )
        WEB3AccInfoAccEstablishSearchInfo[] l_accOpenLockSearchList =
            new WEB3AccInfoAccEstablishSearchInfo[l_lisAccInfoOpenSearchInfo.size()];
        l_lisAccInfoOpenSearchInfo.toArray(l_accOpenLockSearchList);

        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_accOpenLockSearchList, l_intpageIndex, l_intpageSize);

        //1.13 createResponse( )
        WEB3AdminAccInfoAccEstablishSearchListResponse l_response =
            (WEB3AdminAccInfoAccEstablishSearchListResponse)l_request.createResponse();

        //1.14 �i*7�j�v���p�e�B�Z�b�g
        l_response.branchCode = l_request.branchCode;
        l_response.traderCode = l_request.traderCode;
        l_response.accountCode = l_request.accountCode;
        l_response.accountNameKana = l_request.accountNameKana;
        l_response.accountTypeCode = l_request.accountTypeCode;
        l_response.dataContentDiv = l_request.dataContentDiv;
        l_response.accountOpenDateFrom = l_request.accountOpenDateFrom;
        l_response.accountOpenDateTo = l_request.accountOpenDateTo;
        l_response.loginLockDiv = l_request.loginLockDiv;

        WEB3AccInfoAccEstablishSearchInfo[] l_lstEstablishSearchInfo = 
            new WEB3AccInfoAccEstablishSearchInfo[l_pageIndexInfo.getListReturned().size()];
        l_pageIndexInfo.getListReturned().toArray(l_lstEstablishSearchInfo);
        l_response.accOpenLockSearchList = l_lstEstablishSearchInfo;
        
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����_�E�����[�h�t�@@�C���f�[�^�擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���q�l���i�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����jget�_�E�����[�h�v<BR>
     * �Q��<BR>
     *  ========================================================<BR>
     *  1.10.27�i*4�j���׍s����MAX���������𒴂����ꍇ�A<BR>
     *  ��O�iBUSINESS_ERROR_01871�j��throw����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01871<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�h���N�G�X�g
     * @@return WEB3AdminAccInfoAccEstablishSearchDLResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected  WEB3AdminAccInfoAccEstablishSearchDLResponse getDownloadFile(
        WEB3AdminAccInfoAccEstablishSearchDLRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getDownloadFile(WEB3AdminAccInfoAccEstablishSearchDLRequest) ";
        log.entering(STR_METHOD_NAME);

        //1.1  validate( )
        l_request.validate();

        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3) validate����(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);

        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //1.5 create��������������(String, String[], String, String, String, Date, Date, String)
        String l_strQueryString =
            this.createQueryString(
                l_request.branchCode,
                l_request.traderCode,
                l_request.accountCode,
                l_request.accountTypeCode,
                l_request.accountOpenDateFrom,
                l_request.accountOpenDateTo,
                l_request.accountNameKana);

        //1.6 create���������f�[�^�R���e�i(String, String[], String, String, String, Date, Date , String)
        String[] l_strQueryContainers =
            this.createQueryContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.traderCode,
                l_request.accountCode,
                l_request.accountTypeCode,
                l_request.accountOpenDateFrom,
                l_request.accountOpenDateTo,
                l_request.accountNameKana);

        //1.7 create�\�[�g����(���q�l���\�[�g�L�[[])
        String l_strSortCond = this.createSortCond(l_request.sortKeys);

        //1.8 get�ڋq(String, String[], String)
        //[����]
        // ��������������F�@@create��������������()�̖߂�l
        // ���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l
        // �\�[�g�����F�@@create�\�[�g����()�̖߂�l
        List l_lisMainAccount =
            WEB3GentradeMainAccount.getMainAccount(
                l_strQueryString,
                l_strQueryContainers,
                l_strSortCond);

        //1.9 �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV( )
        WEB3AdminAccInfoAccEstablishDownLoadCSV l_accInfoAccEstablishDownLoadCSV =
            new WEB3AdminAccInfoAccEstablishDownLoadCSV();

        //1.10  (*1) �擾�����_�E�����[�h�f�[�^(get�ڋq()�̖߂�l)�̊e�v�f����LOOP
        int l_intMainAccount = 0;
        if (l_lisMainAccount != null && l_lisMainAccount.size() != 0)
        {
            l_intMainAccount = l_lisMainAccount.size();
        }

        for (int i = 0; i < l_intMainAccount; i++)
        {
            //1.10.1 is�f�[�^���e(String, Date, String, String)
            //[����]
            // �f�[�^���e�ԍ��F�@@���N�G�X�g�f�[�^.�f�[�^���e�ԍ�
            // �����o�^���F�@@get�ڋq()[index].�����o�^��
        	// �،���ЃR�[�h�F�@@get�،����()�̖߂�l 
            // ���X�R�[�h�F�@@get�ڋq()[index].���X�R�[�h
            // �ڋq�R�[�h�F�@@get�ڋq()[index].�����R�[�h
            WEB3GentradeMainAccount l_row = (WEB3GentradeMainAccount)l_lisMainAccount.get(i);           
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_row.getDataSourceObject();

            boolean l_blnDataCountent =
                this.isDataContent(
                    l_request.dataContentDiv,
                    l_mainAccountRow.getAccountOpenDate(),
                    l_strInstitutionCode,
                    l_mainAccountRow.getBranchCode(),
                    l_mainAccountRow.getAccountCode());

            //1.10.2 (*2) is�f�[�^���e�i�j�̖߂�l = false�̏ꍇ�A���Y�v�f�̏����𒆒f�icontinue�j
            if (!l_blnDataCountent)
            {
                continue;
            }

            //1.10.3 get���O�C�����R�[�h(long)
            long l_lngAccountId = l_mainAccountRow.getAccountId();
            List l_lisLoginRecord = this.getLoginRecord(l_lngAccountId);

            // get���O�C�����R�[�h()�̖߂�l�̗v�f����0���̏ꍇ�A���Y�v�f�̏����𒆒f�icontinue�j
            if (l_lisLoginRecord.isEmpty())
            {
                log.debug("get���O�C�����R�[�h()�̖߂�l�̗v�f����0���̏ꍇ�A���Y�v�f�̏����𒆒f");
                continue;
            }
            log.debug("get���O�C�����R�[�h()�̖߂�l�̗v�f�� != 0��");
            LoginRow l_logintRow = null;
            if (l_lisLoginRecord != null)
            {
                l_logintRow = (LoginRow)l_lisLoginRecord.get(0);
            }

            //1.10.4 is���O�C�����b�N(String, long, long)
            //[����]
            // ���O�C�����b�N�敪�F�@@���N�G�X�g�f�[�^.���O�C�����b�N�敪
            // ���O�C���L�����F�@@get���O�C�����R�[�h().get(0).���O�C���L����
            // ���O�C���G���[�񐔁F�@@get���O�C�����R�[�h().get(0).���O�C���G���[��
            boolean l_blnIsLoginLock =
                this.isLoginLock(l_request.loginLockDiv,
                    l_logintRow.getDisabled(),
                    l_logintRow.getFailureCount());

            //1.10.5 (*3) is���O�C�����b�N�i�j�̖߂�l = false�̏ꍇ�A���Y�v�f�̏����𒆒f�icontinue�j
            if (!l_blnIsLoginLock)
            {
                continue;
            }

            //1.10.6 get��p�U����������R�[�h(long)
            List l_lisExclusiveUseAccountRecord =
                this.getExclusiveUseAccountRecord(l_lngAccountId);
            
            //get���X���R�[�h(long)
            List l_lisBranchRecords = this.getBranchRecord(l_mainAccountRow.getBranchId());

            int l_intExclusiveUseAccountRecord = l_lisExclusiveUseAccountRecord.size();

            //1.10.7 add���׍s( )
            //[����]
            // �s�ԍ��F�@@add���׍s()�̖߂�l
            // �f�[�^���e�敪�F�@@���N�G�X�g�f�[�^.�f�[�^���e�ԍ�
            int l_intAddRow = l_accInfoAccEstablishDownLoadCSV.addRow();

            //1.10.8 set�f�[�^���e�ԍ�(int, String)
            l_accInfoAccEstablishDownLoadCSV.setDataContentDiv(
                l_intAddRow, l_request.dataContentDiv);

            //1.10.9 set��ЃR�[�h(int, String)
            l_accInfoAccEstablishDownLoadCSV.setInstitutionCode(
                l_intAddRow, l_strInstitutionCode);

            //1.10.10 set���X�R�[�h(int, String)
            l_accInfoAccEstablishDownLoadCSV.setBranchCode(
                l_intAddRow, l_mainAccountRow.getBranchCode());

            //1.10.11 set���҃R�[�h(int, String)
            l_accInfoAccEstablishDownLoadCSV.setTraderCode(
                l_intAddRow, l_mainAccountRow.getSonarTraderCode());

            //1.10.12 set�ڋq�R�[�h(int, String)
            l_accInfoAccEstablishDownLoadCSV.setAccountCode(
                l_intAddRow, l_mainAccountRow.getAccountCode());

            //1.10.13 set�������(int, String)
            String l_strAccountType = l_mainAccountRow.getAccountType().intValue() + "";
            l_accInfoAccEstablishDownLoadCSV.setAccountTypeCode(
                l_intAddRow, l_strAccountType);

            //1.10.14 set�ڋq���i�����j(int, String)
            l_accInfoAccEstablishDownLoadCSV.setAccountName(
                l_intAddRow, l_mainAccountRow.getFamilyName());

            //1.10.15 set�ڋq���i�J�i�j(int, String)
            l_accInfoAccEstablishDownLoadCSV.setAccountNameKana(
                l_intAddRow, l_mainAccountRow.getFamilyNameAlt1());

            //1.10.16 set�����J�ݓ�(int, String)
            String l_strAccountOpenDate =
                WEB3DateUtility.formatDate(
                    l_mainAccountRow.getAccountOpenDate(), "yyyy/MM/dd");
            l_accInfoAccEstablishDownLoadCSV.setAccountOpenDate(
                l_intAddRow, l_strAccountOpenDate);

            if (l_intExclusiveUseAccountRecord == 0)
            {
                //1.10.17 set�������s(int, String)
                l_accInfoAccEstablishDownLoadCSV.setPayFinancialInstitution(
                    l_intAddRow, null);

                //1.10.18 set��s�x�X��(int, String)
                l_accInfoAccEstablishDownLoadCSV.setFinancialBranchName(
                    l_intAddRow, null);

                //1.10.19 set�Ȗ�(int, String)
                l_accInfoAccEstablishDownLoadCSV.setItem(
                    l_intAddRow, null);

                //1.10.20 set��s�ԍ�(int, String)
                l_accInfoAccEstablishDownLoadCSV.setFinancialInstitutionNumber(
                    l_intAddRow, null);

                //1.10.21 set��s�x�X�ԍ�(int, String)
                l_accInfoAccEstablishDownLoadCSV.setFinancialBranchCode(
                    l_intAddRow, null);

                //1.10.22 set��s�����ԍ�(int, String)
                l_accInfoAccEstablishDownLoadCSV.setFinancialAccountCode(
                    l_intAddRow, null);
            }
            else
            {
                ExclusiveUseAccountRow l_exclusiveUseAccountRow =
                    (ExclusiveUseAccountRow)l_lisExclusiveUseAccountRecord.get(0);

                l_accInfoAccEstablishDownLoadCSV.setPayFinancialInstitution(
                    l_intAddRow, l_exclusiveUseAccountRow.getFinInstitutionName());

                l_accInfoAccEstablishDownLoadCSV.setFinancialBranchName(
                    l_intAddRow, l_exclusiveUseAccountRow.getFinBranchName());

                l_accInfoAccEstablishDownLoadCSV.setItem(
                    l_intAddRow, l_exclusiveUseAccountRow.getFinAccountTypeName());

                l_accInfoAccEstablishDownLoadCSV.setFinancialInstitutionNumber(
                    l_intAddRow, l_exclusiveUseAccountRow.getFinInstitutionCode());

                l_accInfoAccEstablishDownLoadCSV.setFinancialBranchCode(
                    l_intAddRow, l_exclusiveUseAccountRow.getFinBranchCode());

                l_accInfoAccEstablishDownLoadCSV.setFinancialAccountCode(
                    l_intAddRow, l_exclusiveUseAccountRow.getFinAccountNo());
            }

            //1.10.23 set�ڋq�Z���i�X�֔ԍ��j(int, String)
            l_accInfoAccEstablishDownLoadCSV.setZipCode(
                l_intAddRow, l_mainAccountRow.getZipCode());

            //1.10.24 setset�ڋq�Z���i�Z��1�j((int, String)
            l_accInfoAccEstablishDownLoadCSV.setAddress1(
                l_intAddRow, l_mainAccountRow.getAddressLine1());

            //1.10.25 set�ڋq�Z���i�Z��2�j((int, String)
            l_accInfoAccEstablishDownLoadCSV.setAddress2(
                l_intAddRow, l_mainAccountRow.getAddressLine2());

            //1.10.26 set�ڋq�Z���i�Z��3�j((int, String)
            l_accInfoAccEstablishDownLoadCSV.setAddress3(
                l_intAddRow, l_mainAccountRow.getAddressLine3());
            
            //set�d�b�ԍ�(int, String)
            //[����]  
            // �s�ԍ��F�@@add���׍s()�̖߂�l  
            // �d�b�ԍ��F�@@get�ڋq()[index].�d�b�ԍ�
            l_accInfoAccEstablishDownLoadCSV.setTelephone(
                l_intAddRow, l_mainAccountRow.getTelephone());
            
            BranchRow l_branchRow = (BranchRow)l_lisBranchRecords.get(0);
            //set���X��(int, String)
            //[����]  
            // �s�ԍ��F�@@add���׍s()�̖߂�l  
            // ���X���F�@@get���X���R�[�h().get(0).get���X��(branch_name)
            l_accInfoAccEstablishDownLoadCSV.setBranchName(
                l_intAddRow, l_branchRow.getBranchName());

            //1.10.27 �i*4�j���׍s����MAX���������𒴂����ꍇ�A��O�iBUSINESS_ERROR_01871�j��throw����B
            int l_intMaxCount = Integer.parseInt(l_request.maxCount);
            int l_rowCount = l_accInfoAccEstablishDownLoadCSV.getRowCount();
            
            if (l_rowCount > l_intMaxCount)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01871,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "CSV�t�@@�C���s�͍ő又���������z���Ă��܂��B");
            }
        }

        //1.11 getCSV�t�@@�C���s( )
        String[] l_strCsvFileLines = l_accInfoAccEstablishDownLoadCSV.getCsvFileLines();

        //1.12 createResponse( )
        WEB3AdminAccInfoAccEstablishSearchDLResponse l_response =
            (WEB3AdminAccInfoAccEstablishSearchDLResponse)l_request.createResponse();

        //1.13�i*5�j�v���p�e�B�Z�b�g
        l_response.downloadFile = l_strCsvFileLines;
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        l_response.dataContentNumber = l_request.dataContentDiv;

       log.exiting(STR_METHOD_NAME);
       return l_response;
    }

    /**
     * (create��������������)<BR>
     * ���������������ҏW����B<BR>
     * <BR>
     * �P�j�@@�߂�l����<BR>
     *    �E �߂�l�̌�������������C���X�^���X�i�FString�j�𐶐�<BR>
     * <BR>
     * �Q�j�@@�u�،���ЃR�[�h�v�����ǉ�<BR>
     *    �E �،���ЃR�[�h������ǉ�����B<BR>
     * <BR>
     *    �@@�@@" institution_code =  ? "<BR>
     * <BR>
     * �R�j�@@�u���X�R�[�h�v�����ǉ�<BR>
     *    �E ���X������ǉ�����B���X�R�[�h[]�̗v�f����"?"��ǉ�����B<BR>
     * <BR>
     *    �@@�@@" and branch_code in (?, ?,,,) " <BR>
     * <BR>
     * �S�j�@@�u���҃R�[�h�v�����ǉ�<BR>
     *    �E ����.���҃R�[�h != null�̏ꍇ�A���҃R�[�h��ǉ�����B<BR>
     * <BR>
     *    �@@�@@" and sonar_trader_code = ? " <BR>
     * <BR>
     * �T�j�@@�u�ڋq�R�[�h�v�����ǉ�<BR>
     *    �E ����.�ڋq�R�[�h != null�̏ꍇ�A�ڋq�R�[�h������ǉ�����B<BR>
     * <BR>
     *    �@@�@@" and account_code like ? " <BR>
     * <BR>
     * �U�j�@@�u�ڋq���i�J�i�j�v�����ǉ� <BR>
     *    �E ����.�ڋq�R�[�h != null�̏ꍇ�A�ڋq�R�[�h������ǉ�����B<BR>
     *    �@@�i���O����v�����j<BR>
     * <BR>
     *    �@@�@@" and account_code like ? " <BR>
     * <BR>
     * �V�j�@@�u������ʁv�����ǉ�<BR>
     *    �E ����.������� != null �̏ꍇ�A������ʏ�����ǉ�����B<BR>
     * <BR>
     *    -�@@������� = 0(�S��)�̏ꍇ�A�@@�����ǉ������B<BR>
     *    -�@@������� = 1(�l�q)�̏ꍇ�A�@@" and account_type <> ? "<BR>
     *    -�@@������� = 2(�@@�l�q)�̏ꍇ�A�@@" and account_type = ? "<BR>
     *    �@@�i���l�q�̏ꍇ�A�ڋq.�����^�C�v��"3�F�@@�l"�ȊO�̃��R�[�h�j<BR>
     *    �@@�i���@@�l�q�̏ꍇ�A�ڋq.�����^�C�v��"3�F�@@�l"�̃��R�[�h�j<BR>
     * <BR>
     * �W�j�@@�u�����J�ݓ��i���j�v�u�����J�ݓ��i���j�v�����ǉ�<BR>
     *    �E ����.�����J�ݓ��i���j != null �A����.�����J�ݓ��i���j != null �̏ꍇ�A<BR>
     *    �@@�@@�����J�ݓ�������ǉ�����B<BR>
     * <BR>
     *    -�@@����.�����J�ݓ��i���j != null && ����.�����J�ݓ��i���j != null �̏ꍇ�A<BR>
     * <BR>
     *     �@@�@@" and account_open_date >= ? " <BR>
     *     �@@�@@" and account_open_date <= ? " <BR>
     * <BR>
     *     -�@@����.�����J�ݓ��i���j != null && ����.�����J�ݓ��i���j = null �̏ꍇ�A<BR>
     *     �@@�i�������J�ݓ��i���j�݂̂����͂���Ă����ꍇ�j<BR>
     * <BR>
     *     �@@�@@" and account_open_date >= ? "<BR>
     * <BR>
     *     -�@@����.�����J�ݓ��i���j = null && ����.�����J�ݓ��i���j != null �̏ꍇ�A<BR>
     *     �@@�i�������J�ݓ��i���j�݂̂����͂���Ă����ꍇ�j<BR>
     * <BR>
     *     �@@�@@" and account_open_date <= ? "<BR>
     * <BR>
     *  �X�j�@@������C���X�^���X��ԋp<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strTraderCode - (���҃R�[�h)<BR>
     * ���҃R�[�h
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     * @@param l_strAccountType - (�������)<BR>
     * �������
     * @@param l_datAccountOpenDateFrom - (�����J�ݓ��i��)�j<BR>
     * �����J�ݓ��i��)
     * @@param l_datAccountOpenDateTo - (�����J�ݓ��i���j)<BR>
     * �����J�ݓ��i���j
     * @@param l_strAccountNameKana - (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j
     * @@return String
     * @@roseuid 4163B2130031
     */
    protected String createQueryString(
        String[] l_strBranchCodes,
        String l_strTraderCode,
        String l_strAccountCode,
        String l_strAccountType,
        Date l_datAccountOpenDateFrom,
        Date l_datAccountOpenDateTo,
        String l_strAccountNameKana)
    {
        final String STR_METHOD_NAME =
            " createQueryString(String, String[], String, String, Date, Date, String) ";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�߂�l����
        //�߂�l�̌�������������C���X�^���X�i�FString�j�𐶐�
        StringBuffer l_sbQueryString = new StringBuffer();

        //�Q�j�@@�u�،���ЃR�[�h�v�����ǉ�
        //�،���ЃR�[�h������ǉ�����B
        //" institution_code =  ? "
        l_sbQueryString.append(" institution_code = ? ");

        //�R�j�@@�u���X�R�[�h�v�����ǉ�
        //���X������ǉ�����B���X�R�[�h[]�̗v�f����"?"��ǉ�����B
        //" and branch_code in (?, ?,,,) "
        if (l_strBranchCodes != null && l_strBranchCodes.length != 0)
        {
        	l_sbQueryString.append(" and branch_code in ( ");

            int l_intBranchCodeSize = l_strBranchCodes.length;
            for (int i = 0; i < l_intBranchCodeSize; i++)
            {
            	l_sbQueryString.append(" ? , ");
            }

            int l_intQueryStringLength = l_sbQueryString.length();
            String l_strQueryString = l_sbQueryString.substring(0, l_intQueryStringLength - 2);
            l_sbQueryString = new StringBuffer(l_strQueryString);
            l_sbQueryString.append(" ) ");
        }

        //�S�j�@@�u���҃R�[�h�v�����ǉ�
        //����.���҃R�[�h != null�̏ꍇ�A���҃R�[�h��ǉ�����B
        //" and sonar_trader_code = ? "
        if (l_strTraderCode != null)
        {
        	l_sbQueryString.append(" and sonar_trader_code = ? ");
        }

        //�T�j�@@�u�ڋq�R�[�h�v�����ǉ�
        //����.�ڋq�R�[�h != null�̏ꍇ�A�ڋq�R�[�h������ǉ�����
        //�i���O����v�����j
        //" and account_code like ? "
        if (l_strAccountCode != null)
        {
        	l_sbQueryString.append(" and account_code like ? ");
        }

        //�U�j�@@�u�ڋq���i�J�i�j�v�����ǉ�
        //����.�ڋq���i�J�i�j != null�̏ꍇ�A�ڋq���i�J�i�j������ǉ�����B
        //�i�������܂������j
        //" and family_name_alt1 like ? "
        if (l_strAccountNameKana != null)
        {
        	l_sbQueryString.append(" and family_name_alt1 like ? ");
        }

        //�V�j�@@�u������ʁv�����ǉ�
        //����.������� != null �̏ꍇ�A������ʏ�����ǉ�����B
        if (l_strAccountType != null)
        {
            //������� = 0(�S��)�̏ꍇ�A�@@�����ǉ������B
            //������� = 1(�l�q)�̏ꍇ�A�@@" and account_type <> ? "
            //������� = 2(�@@�l�q)�̏ꍇ�A�@@" and account_type = ? "
            //�i���l�q�̏ꍇ�A�ڋq.�����^�C�v��"3�F�@@�l"�ȊO�̃��R�[�h�j
            //�i���@@�l�q�̏ꍇ�A�ڋq.�����^�C�v��"3�F�@@�l"�̃��R�[�h�j
            if ((MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.intValue() + "").equals(l_strAccountType))
            {
            	l_sbQueryString.append(" and account_type <> ? ");
            }
            else if ((MainAccountTypeEnum.JOINT_OWNERSHIP.intValue() + "").equals(l_strAccountType))
            {
            	l_sbQueryString.append(" and account_type = ? ");
            }
        }

        //�W�j�@@�u�����J�ݓ��i���j�v�u�����J�ݓ��i���j�v�����ǉ�
        // ����.�����J�ݓ��i���j != null �A����.�����J�ݓ��i���j != null �̏ꍇ�A�����J�ݓ�������ǉ�����B
        if (l_datAccountOpenDateFrom != null || l_datAccountOpenDateTo != null)
        {
            //����.�����J�ݓ��i���j != null && ����.�����J�ݓ��i���j != null �̏ꍇ�A
            //" and account_open_date >= ? "
            //" and account_open_date <= ? "
            if (l_datAccountOpenDateFrom != null && l_datAccountOpenDateTo != null)
            {
            	l_sbQueryString.append(" and account_open_date >= ? ");
            	l_sbQueryString.append(" and account_open_date <= ? ");
            }

            //����.�����J�ݓ��i���j = null && ����.�����J�ݓ��i���j != null �̏ꍇ�A
            //�i�������J�ݓ��i���j�݂̂����͂���Ă����ꍇ�j
            //" and account_open_date <= ? "
            else if (l_datAccountOpenDateFrom != null)
            {
            	l_sbQueryString.append(" and account_open_date >= ? ");
            }
            else if (l_datAccountOpenDateTo != null)
            {
            	l_sbQueryString.append(" and account_open_date <= ? ");
            }
        }

        //�X�j�@@������C���X�^���X��ԋp
        String l_strQueryString = l_sbQueryString.toString();
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i��ҏW����B<BR>
     * <BR>
     * �P�j�@@�߂�l����<BR>
     *    �E �߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�<BR>
     * <BR>
     * �Q�j�@@�u�،���ЃR�[�h�v�����ǉ�<BR>
     *    �E �،���ЃR�[�h����������X�g�ɒǉ�����B<BR>
     * <BR>
     *    �@@[add()�Ɏw�肷�����]<BR>
     *    �@@����.�،���ЃR�[�h<BR>
     * <BR>
     * �R�j�@@�u���X�R�[�h�v�����ǉ�<BR>
     *    �E ����.���X�R�[�h[]���̕��X�R�[�h�����ׂă��X�g�ɒǉ�����B<BR>
     * <BR>
     *    �@@[add()�Ɏw�肷�����]<BR>
     *    �@@���X�R�[�h<BR>
     * <BR>
     * �S�j�@@�u���҃R�[�h�v�����ǉ�<BR>
     *    �E ���҃R�[�h�w��̏ꍇ�i����.���҃R�[�h != null�j�A<BR>
     *�@@�@@�@@���҃R�[�h����������X�g�ɒǉ�����B<BR>
     * <BR>
     *    �@@[add()�Ɏw�肷�����]<BR>
     *    �@@����.���҃R�[�h<BR>
     * <BR>
     * �T�j�@@�u�ڋq�R�[�h�v�����ǉ�<BR>
     *    �E �ڋq�R�[�h�w��̏ꍇ�i����.�ڋq�R�[�h != null�j�A<BR>
     *    �@@�@@�ڋq�R�[�h����������X�g�ɒǉ�����B<BR>
     *    �@@�i���O����v�����j<BR>
     * <BR>
     *    �@@[add()�Ɏw�肷�����]<BR>
     *    �@@����.�ڋq�R�[�h + "%"<BR>
     * <BR>
     * �U    �j�@@�u�ڋq���i�J�i�j�v�����ǉ� <BR>
     *    �E �ڋq���i�J�i�j�w��̏ꍇ�i����.�ڋq���i�J�i�j != null�j�A<BR>
     *    �@@�@@�ڋq���i�J�i�j����������X�g�ɒǉ�����B]<BR>
     *    �@@�i�������܂������j<BR>
     * <BR>
     *    �@@[add()�Ɏw�肷�����]<BR>
     *    �@@ "%" + ����.�ڋq���i�J�i�j + "%"<BR>
     * <BR>
     * �V�j�@@�u������ʁv�����ǉ�<BR>
     *    �E ������ʎw��̏ꍇ�i����.������� != null�j�A<BR>
     *    �@@�@@������ʕ���������X�g�ɒǉ�����B<BR>
     * <BR>
     *    -�@@������� = 0(�S��)�̏ꍇ�A�����ǉ������B<BR>�@@
     * <BR>
     *    -�@@������� = 1(�l�q)�̏ꍇ�A�܂��́A������� = 2(�@@�l�q)�̏ꍇ�A<BR>
     * <BR>
     *    �@@[add()�Ɏw�肷�����] <BR>
     *    �@@MainAccountTypeEnum.3�F�@@�l�A�J�E���g<BR>
     * <BR>
     * �W�j�@@�u�����J�ݓ��i���j�v�u�����J�ݓ��i���j�v�����ǉ� <BR>
     *    �E �����J�ݓ��w��̏ꍇ�i����.�����J�ݓ��i���j != null �A<BR>
     *    �@@�@@����.�����J�ݓ��i���j != null�j�A<BR>
     *    �@@�����J�ݓ�����������X�g�ɒǉ�����B<BR>
     * <BR>
     *    -�@@����.�����J�ݓ��i���j != null && ����.�����J�ݓ��i���j != null �̏ꍇ�A<BR>
     * <BR>
     *    �@@[add()�Ɏw�肷�����]<BR>
     *    �@@����.�����J�ݓ��i���j�𕶎���iyyyymmdd�j�ɕϊ������l<BR>
     *    �@@����.�����J�ݓ��i���j�𕶎���iyyyymmdd�j�ɕϊ������l<BR>
     * <BR>
     *    -�@@����.�����J�ݓ��i���j != null && ����.�����J�ݓ��i���j = null �̏ꍇ�A<BR>
     *    �@@�i�������J�ݓ��i���j�݂̂����͂���Ă����ꍇ�j<BR>
     * <BR>
     *    �@@[add()�Ɏw�肷�����]<BR>
     *    �@@����.�����J�ݓ��i���j�𕶎���iyyyymmdd�j�ɕϊ������l <BR>
     * <BR>
     *    -�@@����.�����J�ݓ��i���j = null && ����.�����J�ݓ��i���j != null �̏ꍇ�A<BR>
     *    �@@�i�������J�ݓ��i���j�݂̂����͂���Ă����ꍇ�j<BR>
     * <BR>
     *    �@@[add()�Ɏw�肷�����]<BR>
     *    �@@����.�����J�ݓ��i���j�𕶎���iyyyymmdd�j�ɕϊ������l <BR>
     * <BR>
     * <BR>
     * �X�j�@@�z���ԋp<BR>
     *    �߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strTraderCode - (���҃R�[�h)<BR>
     * ���҃R�[�h
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     * @@param l_strAccountType - (�������)<BR>
     * �������
     * @@param l_datAccountOpenDateFrom - (�����J�ݓ��i��)�j<BR>
     * �����J�ݓ��i��)
     * @@param l_datAccountOpenDateTo - (�����J�ݓ��i���j)<BR>
     * �����J�ݓ��i���j
     * @@param l_strAccountNameKana - (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j
     * @@return String[]
     * @@roseuid 4163B2130031
     */
    protected String[] createQueryContainer(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strTraderCode,
        String l_strAccountCode,
        String l_strAccountType,
        Date l_datAccountOpenDateFrom,
        Date l_datAccountOpenDateTo,
        String l_strAccountNameKana)
    {
        final String STR_METHOD_NAME =
            " createQueryContainer(String, String[], String, String, String, Date, Date, String) ";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�߂�l����
        //�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�
        List l_lisQueryContainer = new ArrayList();

        //�Q�j�@@�u�،���ЃR�[�h�v�����ǉ�
        //�،���ЃR�[�h����������X�g�ɒǉ�����B
        //[add()�Ɏw�肷�����]
        //����.�،���ЃR�[�h
        l_lisQueryContainer.add(l_strInstitutionCode);

        //�R�j�@@�u���X�R�[�h�v�����ǉ�
        //����.���X�R�[�h[]���̕��X�R�[�h�����ׂă��X�g�ɒǉ�����B
        //[add()�Ɏw�肷�����]
        //���X�R�[�h
        int l_intBranchCodeSize = 0;
        if (l_strBranchCodes != null)
        {
            l_intBranchCodeSize = l_strBranchCodes.length;
        }

        for (int i = 0; i < l_intBranchCodeSize; i++)
        {
            l_lisQueryContainer.add(l_strBranchCodes[i]);
        }

        //�S�j�@@�u���҃R�[�h�v�����ǉ�
        //���҃R�[�h�w��̏ꍇ�i����.���҃R�[�h != null�j�A���҃R�[�h����������X�g�ɒǉ�����B
        //[add()�Ɏw�肷�����]
        //����.���҃R�[�h
        if (l_strTraderCode != null)
        {
            l_lisQueryContainer.add(l_strTraderCode);
        }

        //�T�j�@@�u�ڋq�R�[�h�v�����ǉ�
        //�ڋq�R�[�h�w��̏ꍇ�i����.�ڋq�R�[�h != null�j�A�ڋq�R�[�h����������X�g�ɒǉ�����B
        //�i���O����v�����j
        //[add()�Ɏw�肷�����]
        //����.�ڋq�R�[�h + "%"
        if (l_strAccountCode != null)
        {
            l_lisQueryContainer.add(l_strAccountCode + "%" );
        }

        //�U�j�@@�u�ڋq���i�J�i�j�v�����ǉ�
        //�ڋq���i�J�i�j�w��̏ꍇ�i����.�ڋq���i�J�i�j != null�j�A�ڋq���i�J�i�j����������X�g�ɒǉ�����B
        //�i�������܂������j
        //[add()�Ɏw�肷�����]
        //"%" + ����.�ڋq���i�J�i�j + "%"
        if (l_strAccountNameKana != null)
        {
            l_lisQueryContainer.add("%" + l_strAccountNameKana + "%" );
        }

        //�V�j�@@�u������ʁv�����ǉ�
        //������ʎw��̏ꍇ�i����.������� != null�j�A������ʕ���������X�g�ɒǉ�����B
        if (l_strAccountType != null)
        {
            //-�@@������� = 0(�S��)�̏ꍇ�A�����ǉ������B
            //-�@@������� = 1(�l�q)�̏ꍇ�A�܂��́A������� = 2(�@@�l�q)�̏ꍇ�A
            //[add()�Ɏw�肷�����]
            //MainAccountTypeEnum.3�F�@@�l�A�J�E���g

            if ((MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.intValue() + "").equals(l_strAccountType)
                || (MainAccountTypeEnum.JOINT_OWNERSHIP.intValue() + "").equals(l_strAccountType))
            {
                l_lisQueryContainer.add(MainAccountTypeEnum.CORPORATE_ACCOUNT.intValue() + "");
            }
        }

        //�W�j�@@�u�����J�ݓ��i���j�v�u�����J�ݓ��i���j�v�����ǉ�
        //�����J�ݓ��w��̏ꍇ�i����.�����J�ݓ��i���j != null �A����.�����J�ݓ��i���j != null�j�A
        //�����J�ݓ�����������X�g�ɒǉ�����B
        if (l_datAccountOpenDateFrom != null || l_datAccountOpenDateTo != null)
        {
            //-�@@����.�����J�ݓ��i���j != null && ����.�����J�ݓ��i���j != null �̏ꍇ�A
            //[add()�Ɏw�肷�����]
            if (l_datAccountOpenDateFrom != null && l_datAccountOpenDateTo != null)
            {
                //����.�����J�ݓ��i���j�𕶎���iyyyymmdd�j�ɕϊ������l
                String l_strAccountOpenDateFrom =
                    WEB3DateUtility.formatDate(
                        l_datAccountOpenDateFrom, "yyyyMMdd");

                //����.�����J�ݓ��i���j�𕶎���iyyyymmdd�j�ɕϊ������l
                String l_strAccountOpenDateTo =
                    WEB3DateUtility.formatDate(
                        l_datAccountOpenDateTo, "yyyyMMdd");
                l_lisQueryContainer.add(l_strAccountOpenDateFrom);
                l_lisQueryContainer.add(l_strAccountOpenDateTo);
            }

            //-�@@����.�����J�ݓ��i���j = null && ����.�����J�ݓ��i���j != null �̏ꍇ�A
            //�i�������J�ݓ��i���j�݂̂����͂���Ă����ꍇ�j
            else if (l_datAccountOpenDateFrom != null)
            {
                //[add()�Ɏw�肷�����]
                //����.�����J�ݓ��i���j�𕶎���iyyyymmdd�j�ɕϊ������l
                String l_strAccountOpenDateFrom =
                    WEB3DateUtility.formatDate(
                        l_datAccountOpenDateFrom, "yyyyMMdd");
                l_lisQueryContainer.add(l_strAccountOpenDateFrom);
            }
            else if (l_datAccountOpenDateTo != null)
            {
                //[add()�Ɏw�肷�����]
                //����.�����J�ݓ��i���j�𕶎���iyyyymmdd�j�ɕϊ������l
                String l_strAccountOpenDateTo =
                    WEB3DateUtility.formatDate(
                        l_datAccountOpenDateTo, "yyyyMMdd");
                l_lisQueryContainer.add(l_strAccountOpenDateTo);
            }
        }

        //�X�j�@@�z���ԋp
        //�߂�l�ҏW�p�C���X�^���X�i�FArrayList�j.toArray()��ԋp����B
        String[] l_strQueryContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
    }

    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�����������ҏW����B<BR>
     * <BR>
     * �e�[�u���񕨗������g�p���A<BR>
     * �ȉ��̒ʂ�A�\�[�g����������iorder by��j��ҏW����B<BR>
     * <BR>
     * �P�j ����.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A<BR>
     * �\�[�g������������쐬����B<BR>
     * <BR>
     *    �P�|�P�j �\�[�g�L�[��ҏW����B<BR>
     * <BR>
     *    �@@�P�|�P�|�P�j �\�[�g�L�[ = �u���X�R�[�h�v�̏ꍇ<BR>
     *    �@@�@@�@@�@@�@@�@@�@@�ڋq�}�X�^�e�[�u��.���X�R�[�h<BR>
     * <BR>
     *    �@@�P�|�P�|�Q�j �\�[�g���� = �u���҃R�[�h�v�̏ꍇ <BR>
     *    �@@�@@�@@�@@�@@�@@�@@�ڋq�}�X�^�e�[�u��.���҃R�[�h�iSONAR�j <BR>
     * <BR>
     *    �@@�P�|�P�|�R�j �\�[�g���� = �u�ڋq�R�[�h�v�̏ꍇ <BR>
     *    �@@�@@�@@�@@�@@�@@�@@�ڋq�}�X�^�e�[�u��.�����R�[�h<BR>
     * <BR>
     *    �@@�P�|�P�|�S�j �\�[�g���� = �u�����J�ݓ��v�̏ꍇ<BR>
     *    �@@�@@�@@�@@�@@�@@�@@�ڋq�}�X�^�e�[�u��.�����o�^��<BR>
     * <BR>
     *     �P�|�Q�j �\�[�g�����ɊY������\�[�g������ҏW����B<BR>
     *     �@@�@@�@@�@@���� �F asc <BR>
     *     �@@�@@�@@�@@�~�� �F desc <BR>
     * <BR>
     *  �Q�j �쐬�����\�[�g�����������ԋp����B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected String createSortCond(WEB3AccInfoSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createSortCond(WEB3AccInfoSortKey[]) ";
        log.entering(STR_METHOD_NAME);

        if (l_sortKeys == null || l_sortKeys.length == 0)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j ����.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A�\�[�g������������쐬����B
        String l_strSortCond = "";
        int l_intSortKeysLength = l_sortKeys.length;

        //�P�|�P�j �\�[�g�L�[��ҏW����B
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            String l_strKeyItem = l_sortKeys[i].keyItem;
            String l_strAscDesc = l_sortKeys[i].ascDesc;
            if (i != 0)
            {
                l_strSortCond += ","; 
            }
            //�P�|�P�|�P�j �\�[�g�L�[ = �u���X�R�[�h�v�̏ꍇ
            //�ڋq�}�X�^�e�[�u��.���X�R�[�h
            if (WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_strSortCond += " branch_code ASC ";
                }
                else
                {
                    l_strSortCond += " branch_code DESC ";
                }
            }

            //�P�|�P�|�Q�j �\�[�g���� = �u���҃R�[�h�v�̏ꍇ
            //�ڋq�}�X�^�e�[�u��.���҃R�[�h�iSONAR�j
            else if (WEB3AccInfoKeyItemDef.TRADER_CODE.equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_strSortCond += " sonar_trader_code ASC ";
                }
                else
                {
                    l_strSortCond += " sonar_trader_code DESC ";
                }
            }

            //�P�|�P�|�R�j �\�[�g���� = �u�ڋq�R�[�h�v�̏ꍇ
            //�ڋq�}�X�^�e�[�u��.�����R�[�h
            else if (WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_strSortCond += " account_code ASC ";
                }
                else
                {
                    l_strSortCond += " account_code DESC ";
                }
            }

            //�P�|�P�|�S�j �\�[�g���� = �u�����J�ݓ��v�̏ꍇ
            //�ڋq�}�X�^�e�[�u��.�����o�^��
            else if (WEB3AccInfoKeyItemDef.ACCOUNTOPENDATE.equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_strSortCond += " account_open_date ASC ";
                }
                else
                {
                    l_strSortCond += " account_open_date DESC ";
                }
            }
        }

        //�P�|�Q�j �\�[�g�����ɊY������\�[�g������ҏW����B
        //���� �F asc
        //�~�� �F desc
        //�Q�j �쐬�����\�[�g�����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }

    /**
     * (is�f�[�^���e)<BR>
     * ����.�f�[�^���e�ԍ��ɂ��A�����Ώۃ��R�[�h���𔻒肷��B<BR>
     * <BR>
     * �E�����Ώۂ̏ꍇ�Atrue���A�����ΏۊO�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �P�j ����.�f�[�^���e�ԍ� = 00�F�f�[�^���e���I�� �̏ꍇ <BR>
     *    �P�|�P�j true��ԋp����B<BR>
     * <BR>
     * �Q�j ����.�f�[�^���e�ԍ� = 01�F�V�K�����J�݈ē��p�f�[�^ �̏ꍇ<BR>
     *    �Q�|�P�j ����.�����o�^�� != null �̎��Atrue��ԋp����B<BR>
     * <BR>
     * �R�j ����.�f�[�^���e�ԍ� = 02�F�U���݃J�[�h�p�f�[�^ �̏ꍇ<BR>
     *    �R�|�P�j ����.�����o�^�� != null �̎��Atrue��ԋp����B<BR>
     * <BR>
     * �S�j ����.�f�[�^���e�ԍ� = 03�F�����ڊǈē��p�f�[�^ �̏ꍇ<BR>
     *    �S�|�P�j �u�ڋq�}�X�^�[�i�S���X�j�e�[�u���v�����L�����Ō�������B<BR>
     *    �@@�@@�@@�@@�@@�@@�@@�i�����R�[�h���擾�o���Ȃ������ꍇ��false��ԋp����j<BR>
     * <BR>
     *    �@@ [����]�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     *    �@@�@@�@@�@@�@@�@@���X�R�[�h = ����.���X�R�[�h<BR>
     *    �@@�@@�@@�@@�@@�@@�ڋq�R�[�h = ����.�ڋq�R�[�h��1���`6����<BR>
     *    �@@�@@�@@�@@�@@�@@�ڋq�R�[�hCD = ����.�ڋq�R�[�h��7����<BR>
     * <BR>
     *    �S�|�Q�j �������ʂɂ�蔻�肷��B<BR>
     *    �@@�@@�@@�@@�@@�ڋq�i�S���X���j.[�����ڊǑO]���X�R�[�h != null &&<BR>
     *    �@@�@@�@@�@@�@@�ڋq�i�S���X���j.[�����ڊǑO]�ڋq�R�[�h != null �̎��A<BR>
     *    �@@�@@�@@�@@�@@true��ԋp����B<BR>
     * <BR>
     * �T�j�@@false��ԋp����B<BR>
     * @@param l_strDataContentDiv - (�f�[�^���e�ԍ�)<BR>
     * �f�[�^���e�ԍ�
     * @@param l_datAccountOpenDate - (�����o�^��)<BR>
     * �����o�^��
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     * @@return false
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected boolean isDataContent(
        String l_strDataContentDiv,
        Date l_datAccountOpenDate,
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " isDataContent(String, Date, String, String) ";
        log.entering(STR_METHOD_NAME);

        //�E�����Ώۂ̏ꍇ�Atrue���A�����ΏۊO�̏ꍇ�Afalse��ԋp����B
        //�P�j ����.�f�[�^���e�ԍ� = 00�F�f�[�^���e���I�� �̏ꍇ
        //�@@�P�|�P�j true��ԋp����B
        if (WEB3AccInfoDataContentDef.DATA_CONTENT_NOT_SELECT.equals(l_strDataContentDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�Q�j ����.�f�[�^���e�ԍ� = 01�F�V�K�����J�݈ē��p�f�[�^ �̏ꍇ
        //�@@�Q�|�P�j ����.�����o�^�� != null �̎��Atrue��ԋp����B
        else if (WEB3AccInfoDataContentDef.NEW_ACC_OPEN_GUIDANCE_DATA.equals(l_strDataContentDiv))
        {
            if (l_datAccountOpenDate != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //�R�j ����.�f�[�^���e�ԍ� = 02�F�U���݃J�[�h�p�f�[�^ �̏ꍇ
        //�@@�R�|�P�j ����.�����o�^�� != null �̎��Atrue��ԋp����B
        else if (WEB3AccInfoDataContentDef.TRANSFER_CARD_DATA.equals(l_strDataContentDiv))
        {
            if (l_datAccountOpenDate != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //�S�j ����.�f�[�^���e�ԍ� = 03�F�����ڊǈē��p�f�[�^ �̏ꍇ
        else if (WEB3AccInfoDataContentDef.ACCOUNT_TRANSFER_GUIDANCE_DATA.equals(l_strDataContentDiv))
        {
            //�@@�S�|�P�j �u�ڋq�}�X�^�[�i�S���X�j�e�[�u���v�����L�����Ō�������B
            List l_lisMainAccountAll = null;
            try
            {
                //[����]�@@�،���ЃR�[�h = ����.�،���ЃR�[�h
                //���X�R�[�h = ����.���X�R�[�h
                //�ڋq�R�[�h = ����.�ڋq�R�[�h��1���`6����
                //�ڋq�R�[�hCD = ����.�ڋq�R�[�h��7����
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                String l_strWhere =
                    " comp_code = ? and  br_code = ?  and cust_code = ?  and cust_code_cd = ? ";

                Object[] l_objQueryContainer = {
                	l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode.substring(0, 6),
                    l_strAccountCode.substring(6)};

                l_lisMainAccountAll = l_queryProcessor.doFindAllQuery(
                    MainAccountAllRow.TYPE,
                    l_strWhere,
                    l_objQueryContainer);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //���R�[�h���擾�o���Ȃ������ꍇ��false��ԋp����
            if (l_lisMainAccountAll == null
                || l_lisMainAccountAll.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            //�S�|�Q�j �������ʂɂ�蔻�肷��B
            //�ڋq�i�S���X���j.[�����ڊǑO]���X�R�[�h != null &&
            //�ڋq�i�S���X���j.[�����ڊǑO]�ڋq�R�[�h != null �̎��Atrue��ԋp����B
            MainAccountAllRow l_mainAccountAllRow =
                (MainAccountAllRow)l_lisMainAccountAll.get(0);
            if (l_mainAccountAllRow.getBeforeAccTransBrCode() != null
                && l_mainAccountAllRow.getBeforeAccTransCustCode() != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //�T�j�@@false��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is���O�C�����b�N)<BR>
     * ����.���O�C�����b�N�敪�ɂ��A�����Ώۃ��R�[�h���𔻒肷��B<BR>
     * <BR>
     * �E �����Ώۂ̏ꍇ�Atrue���A�����ΏۊO�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �P�j ����.���O�C�����b�N�敪 = 1�F���O�C�����b�N�q �̏ꍇ<BR>
     *    �P�|�P�j ����.���O�C���L���� = 1�Fenabled && ����.���O�C���G���[�� >= 1 �̎��A<BR>
     *    true��ԋp����B<BR>
     * <BR>
     * �Q�j ����.���O�C�����b�N�敪 = 0�F�S�� �̏ꍇ<BR>
     *    �Q�|�P�j true��ԋp����B<BR>
     * <BR>
     * �R�j false��ԋp����B<BR>
     * @@param l_strLoginLockDiv - (���O�C�����b�N�敪�j<BR>
     * ���O�C�����b�N�敪
     * @@param l_lngLoginDisabledFlag - �i���O�C���L�����j<BR>
     * ���O�C���L����
     * @@param l_lngLoginErrorCount - �i���O�C���G���[�񐔁j<BR>
     * ���O�C���G���[��
     * @@return false
     * @@roseuid 4163B2130031
     */
    protected boolean isLoginLock(
        String l_strLoginLockDiv,
        long l_lngLoginDisabledFlag,
        long l_lngLoginErrorCount)
    {
        final String STR_METHOD_NAME = " isLoginLock(String, long, long)";
        log.entering(STR_METHOD_NAME);

        //�����Ώۂ̏ꍇ�Atrue���A�����ΏۊO�̏ꍇ�Afalse��ԋp����B

        //�P�j ����.���O�C�����b�N�敪 = 1�F���O�C�����b�N�q �̏ꍇ
        //�@@�P�|�P�j ����.���O�C���L���� = 1�Fdisabled && ����.���O�C���G���[�� >= 1 �̎��A
        //         true��ԋp����B
        if (WEB3AccInfoLoginLockDivDef.LOGIN_LOCK_ACCOUNT.equals(l_strLoginLockDiv))
        {
            if (l_lngLoginDisabledFlag == 1 && l_lngLoginErrorCount >= 1)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //�Q�j ����.���O�C�����b�N�敪 = 0�F�S�� �̏ꍇ
        //�@@�Q�|�P�j true��ԋp����B
        else if (WEB3AccInfoLoginLockDivDef.ALL.equals(l_strLoginLockDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�R�j false��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get��p�U����������R�[�h)<BR>
     * ������������擾�����u��p�U����������R�[�h�v��List�^�ŕԋp����B<BR>
     * <BR>
     * �P�j doFindAllQuery()���g�p���āu��p�U��������e�[�u���v���ȉ��̏����Ō����B<BR>
     *    �@@�@@�i�����R�[�h���擾�o���Ȃ��ꍇ�͗�O�Ƃ��Ȃ��j<BR>
     * <BR>
     *    [����]<BR>
     *    ��p�U��������e�[�u��.����ID = ����.����ID<BR>
     * <BR>
     * �Q�j �������ʂ�ԋp����B<BR>
     * @@param l_lngAccountId - �i����ID�j<BR>
     * ����ID
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected List getExclusiveUseAccountRecord(long l_lngAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExclusiveUseAccountRecord(long)";
        log.entering(STR_METHOD_NAME);

        //�P�j doFindAllQuery()���g�p���āu��p�U��������e�[�u���v���ȉ��̏����Ō����B
        //[����]
        //��p�U��������e�[�u��.����ID = ����.����ID
        List l_lisExclusiveUseAccount = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strWhere = " account_id = ? ";

            Object[] l_objQueryContainer = {new Long(l_lngAccountId)};

            l_lisExclusiveUseAccount = l_queryProcessor.doFindAllQuery(
                ExclusiveUseAccountRow.TYPE,
                l_strWhere,
                l_objQueryContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j �������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisExclusiveUseAccount;
    }

    /**
     * (get���O�C�����R�[�h)<BR>
     * ������������擾�����u���O�C�����R�[�h�v��List�^�ŕԋp����B<BR>
     * <BR>
     * �P�j doFindAllQuery()���g�p���āu���O�C���e�[�u���v���ȉ��̏����Ō����B<BR>
     * �@@�@@�@@ �i�����R�[�h���擾�o���Ȃ��ꍇ�͗�O�Ƃ��Ȃ��j<BR>
     * <BR>
     *    [����]<BR>
     *    ���O�C���e�[�u��.���O�C��ID = ����.����ID<BR>
     * <BR>
     * �Q�j �������ʂ�ԋp����B<BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID�@@
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected List getLoginRecord(long l_lngAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLoginRecord(long)";
        log.entering(STR_METHOD_NAME);

        //�P�j doFindAllQuery()���g�p���āu���O�C���e�[�u���v���ȉ��̏����Ō����B
        //[����]
        //���O�C���e�[�u��.���O�C��ID = ����.����ID
        List l_lisLoginRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strWhere = " login_id = ? ";

            Object[] l_objQueryContainer = {new Long(l_lngAccountId)};

            l_lisLoginRecords = l_queryProcessor.doFindAllQuery(
                LoginRow.TYPE,
                l_strWhere,
                l_objQueryContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j �������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisLoginRecords;
    }
    
    /**
     * (get���X���R�[�h)<BR>
     * ������������擾�����u���X���R�[�h�v��List�^�ŕԋp����B<BR>
     * <BR>
     * �P�j doFindAllQuery()���g�p���āu���X�e�[�u���v���ȉ��̏����Ō����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@���X�e�[�u��.���XID = ����.���XID<BR>
     * <BR>
     * �Q�j �������ʂ�ԋp����B<BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID
     * @@return List
     * @@throws WEB3BaseException 
     */
    protected List getBranchRecord(long l_lngBranchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchRecord(long)";
        log.entering(STR_METHOD_NAME);

        //�P�j doFindAllQuery()���g�p���āu���X�e�[�u���v���ȉ��̏����Ō����B
        //[����]
        //���X�e�[�u��.���XID = ����.���XID
        List l_lisBranchRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strWhere = " branch_id = ? ";

            Object[] l_objQueryContainer = {new Long(l_lngBranchId)};

            l_lisBranchRecords = l_queryProcessor.doFindAllQuery(
                BranchRow.TYPE,
                l_strWhere,
                l_objQueryContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j �������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisBranchRecords;
    }
}
@
