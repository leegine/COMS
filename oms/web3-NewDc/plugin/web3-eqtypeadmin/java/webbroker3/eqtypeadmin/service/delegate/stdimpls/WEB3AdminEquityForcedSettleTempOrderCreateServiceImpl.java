head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ω������쐬�T�[�r�XImpl(WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����q(���u) �V�K�쐬 �d�l�ύX���f��No.131�A139�A140
Revision History : 2007/06/04�@@�đo�g(���u) �d�l�ύX ���f��No.156
Revision History : 2007/08/21 �đo�g(���u) �d�l�ύX���f��No.162
Revision History : 2008/01/17 �И���(���u) �d�l�ύX���f��No.179 �c�a�X�V�d�lNo.017
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ForcedSettleReasonType;
import webbroker3.common.define.WEB3ForcedsettleorderDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.eqtypeadmin.define.WEB3ForcedSettleTypeDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleTempOrderCreateService;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;
import webbroker3.tradingpower.define.WEB3TPForcedSettleReasonDef;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (�������ω������쐬�T�[�r�XImpl)<BR>
 * �������ω������쐬�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl implements
    WEB3AdminEquityForcedSettleTempOrderCreateService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl.class);

    /**
     * @@roseuid 462CA4210321
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl()
    {

    }

    /**
     * �������ω������쐬�T�[�r�X�����{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�������ω������쐬�j�������쐬�v�Q��<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@roseuid 46036ACC016F
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        // validate
        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g�����w��(null)�ł��B");
        }

        if (!(l_request instanceof WEB3AdminEquityForcedSettleTempOrderCreateRequest))
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        WEB3AdminEquityForcedSettleTempOrderCreateRequest l_adminEquityForcedSettleTempOrderCreateRequest =
            (WEB3AdminEquityForcedSettleTempOrderCreateRequest)l_request;

        l_adminEquityForcedSettleTempOrderCreateRequest.validate();

        Object l_objDoTransaction = null;
        boolean l_blnIsError = false;

        try
        {
            // getDefaultProcessor()
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // �I�����C�����s����transactionCallback
            String l_strForcedSettleStatus = "";

            if (WEB3ForcedSettleTypeDef.BEFORE_ONLINE.equals(
                l_adminEquityForcedSettleTempOrderCreateRequest.forcedSettleType))
            {
                l_strForcedSettleStatus = WEB3OnlineServiceDiv.FORCED_SETTLE_BEFORE_ONLINE;
            }
            else if (WEB3ForcedSettleTypeDef.REST_TIMEZONE.equals(
                l_adminEquityForcedSettleTempOrderCreateRequest.forcedSettleType))
            {
                l_strForcedSettleStatus = WEB3OnlineServiceDiv.FORCED_SETTLE_MARKET;
            }

            WEB3GentradeOnlineRunStatusTransactionCallback l_onlineRunStatusTransactionCallback =
                new WEB3GentradeOnlineRunStatusTransactionCallback(
                    l_adminEquityForcedSettleTempOrderCreateRequest.institutionCode,
                    l_adminEquityForcedSettleTempOrderCreateRequest.rangeFrom,
                    l_adminEquityForcedSettleTempOrderCreateRequest.rangeTo,
                    l_strForcedSettleStatus);

            // doTransaction(TX_CREATE_NEW : int, �I�����C�����s����TransactionCallback : TransactionCallback) 
            l_objDoTransaction = l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_onlineRunStatusTransactionCallback);
        }
        catch (DataCallbackException l_dataCallbackException)
        {
            // set������()�����d�N���̗�O��throw���ꂽ�ꍇ
            Object l_exception = l_dataCallbackException.getDetails();
            if (l_exception instanceof WEB3BaseException) 
            {
                WEB3BaseException l_baseException = (WEB3BaseException)l_exception;
                if (WEB3ErrorCatalog.BUSINESS_ERROR_01992.equals(l_baseException.getErrorInfo()))
                {
                    log.error("�w��AP�N�����i��d�N���G���[�j�B", l_baseException);
                    log.exiting(STR_METHOD_NAME);

                    WEB3AdminEquityForcedSettleTempOrderCreateResponse l_response =
                        (WEB3AdminEquityForcedSettleTempOrderCreateResponse)
                            l_adminEquityForcedSettleTempOrderCreateRequest.createResponse();

                    log.exiting(STR_METHOD_NAME);
                    return l_response;
                }
            }
        }
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataNetworkException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataQueryException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }

        WEB3GentradeOnlineRunStatus l_onlineRunStatus =
            (WEB3GentradeOnlineRunStatus)l_objDoTransaction;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_genAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        // getInstitution
        Institution l_institution = null;
        try
        {
            l_institution = l_genAccountManager.getInstitution(
                l_adminEquityForcedSettleTempOrderCreateRequest.institutionCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(" �e�[�u���ɊY������f�[�^������܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �������ϑΏۂ̉�Ђ��A�������戵�\�����肷��B 
        WEB3ToSuccOrderManagerImpl l_toOrderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //[����]
        //�،���ЃR�[�h�F�@@���N�G�X�g.�،���ЃR�[�h
        //�����^�C�v�F�@@"����"
        //�敨�^�I�v�V�����敪�F�@@"DEFAULT"
        boolean l_blnIsSuccOrderHandling = true;
        try
        {
            l_toOrderManager.validateSuccOrderHandling(
                l_adminEquityForcedSettleTempOrderCreateRequest.institutionCode,
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT);
        }
        catch (WEB3BaseException l_ex)
        {
            l_blnIsSuccOrderHandling = false;
        }

        WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService l_service =
            (WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService)Services.getService
                (WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService.class);

        String l_strForcedSettleType =
            l_adminEquityForcedSettleTempOrderCreateRequest.forcedSettleType;

        InstitutionRow l_insRow = (InstitutionRow) l_institution.getDataSourceObject();
        String l_strForcedSettleOrderDiv = l_insRow.getForcedsettleorderDiv();

        // ����t���[
        if (WEB3ForcedSettleTypeDef.BEFORE_ONLINE.equals(l_strForcedSettleType)
            && (WEB3ForcedsettleorderDivDef.SETTLEDAY_COME.equals(
                l_strForcedSettleOrderDiv)
                || WEB3ForcedsettleorderDivDef.SETTLEDAY_COME_AND_GUARANTEE_MAINTENANCE_BREAK.equals(
                    l_strForcedSettleOrderDiv)))
        {
            // get�������ϊ������������ꗗ
            //[����] 
            //�،���� : getInstitution()�̖߂�l 
            //From����ID�F�@@���N�G�X�g.From����ID 
            //To����ID�F�@@���N�G�X�g.To����ID
            List l_lisContractList = WEB3EquityPositionManager.getForcedSettleCloseDateContractList(
                l_institution,
                l_adminEquityForcedSettleTempOrderCreateRequest.rangeFrom,
                l_adminEquityForcedSettleTempOrderCreateRequest.rangeTo
                );

            int l_intListSize = l_lisContractList.size();
            // get�������ϊ������������ꗗ()�Ŏ擾����List�̗v�f����Loop����
            for (int i = 0; i < l_intListSize; i++)
            {
                // exec�������쐬(EqtypeContractRow, String, ���ʋ������ό���, boolean)
                try
                {
                    l_service.execTempOrderCreation(
                        (EqtypeContractRow)l_lisContractList.get(i),
                        WEB3ForcedSettleReasonType.FIXED_DATE_COMING,
                        null,
                        l_blnIsSuccOrderHandling
                        );
                }
                catch (Exception l_ex)
                {
                    log.error("__an WEB3BaseException of exec�������쐬", l_ex);
                    l_blnIsError = true;
                }
            }
        }

        // String( )
        String l_strAccIdKey = "";

        // ����t���[
        if (WEB3ForcedSettleTypeDef.BEFORE_ONLINE.equals(l_strForcedSettleType)
            && (WEB3ForcedsettleorderDivDef.GUARANTEE_MAINTENANCE_BREAK.equals(
                l_strForcedSettleOrderDiv)
                || WEB3ForcedsettleorderDivDef.SETTLEDAY_COME_AND_GUARANTEE_MAINTENANCE_BREAK.equals(
                    l_strForcedSettleOrderDiv)))
        {
            // get�����ꗗ(From����ID : long, To����ID : long)
            List l_lisContracts = WEB3EquityPositionManager.getContracts(
                l_adminEquityForcedSettleTempOrderCreateRequest.rangeFrom,
                l_adminEquityForcedSettleTempOrderCreateRequest.rangeTo
                );

            WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService l_createUnitService =
                (WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService)Services.getService(
                    WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.class);
            int l_intListSize = l_lisContracts.size();
            // get�������ϊ������������ꗗ()�Ŏ擾����List�̗v�f����Loop����
            WEB3TPContractForcedSettleResult l_tpResult = null;
            for (int i = 0; i < l_intListSize; i ++)
            {
                EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow)l_lisContracts.get(i);
                String l_strAccId = l_eqtypeContractRow.getAccountId() + "";

                // �����v�f�̌���ID �� �u����ID�L�[�v�̏ꍇ
                if (!l_strAccId.equals(l_strAccIdKey))
                {
                    //validate���ʋ�������(EqtypeContractRow)
                    try
                    {
                        l_tpResult = l_createUnitService.validateContractForcedSettle(l_eqtypeContractRow);
                    }
                    catch (Exception l_ex)
                    {
                        log.error("__an WEB3BaseException of validate���ʋ�������", l_ex);
                        l_blnIsError = true;
                        continue;
                    }

                    // �����v�f�̌���ID���u����ID�L�[�v�ɑ������B
                    l_strAccIdKey = l_eqtypeContractRow.getAccountId() + "";
                }

                // validate���ʋ�������()�̖߂�l.����t���O �� true�̏ꍇ�A�ꌏ�������s���B
                if (l_tpResult.resultFlg)
                {
                    String l_strForcedSettleResonType = null;

                    if (WEB3TPForcedSettleReasonDef.BEFORE_ONLINE_SLIGHTNESS.equals(
                        l_tpResult.forcedSettleReason))
                    {
                        l_strForcedSettleResonType =
                            WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS;
                    }
                    if (WEB3TPForcedSettleReasonDef.BEFORE_ONLINE_SERIOUSNESS.equals(
                        l_tpResult.forcedSettleReason))
                    {
                        l_strForcedSettleResonType =
                            WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS;
                    }
                    //[�h�I�����C���J�n�O�i�@@��j�h�̏ꍇ]
                    //�h�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j�h
                    if (WEB3TPForcedSettleReasonDef.BEFORE_ONLINE_LEGAL.equals(
                        l_tpResult.forcedSettleReason))
                    {
                        l_strForcedSettleResonType =
                            WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL;
                    }

                    // exec�������쐬(EqtypeContractRow, String, ���ʋ������ό���, boolean)
                    try
                    {
                        l_createUnitService.execTempOrderCreation(
                            l_eqtypeContractRow,
                            l_strForcedSettleResonType,
                            l_tpResult,
                            l_blnIsSuccOrderHandling
                            );
                    }
                    catch (Exception l_ex)
                    {
                        log.error("__an WEB3BaseException of exec�������쐬", l_ex);
                        l_blnIsError = true;
                    }
                }
            }
        }

        //����t���[
        //���N�G�X�g�f�[�^.�������Ϗ����敪���A"�x�e���ԑ�"���A
        //�i�،����.�M�p�������ώ��{�敪 �� "�ۏ؋��ێ�������"�܂��́A
        // "���ϊ����������{�ۏ؋��ێ�������"�j�̏ꍇ���{����B
        if (WEB3ForcedSettleTypeDef.REST_TIMEZONE.equals(l_strForcedSettleType)
            && (WEB3ForcedsettleorderDivDef.GUARANTEE_MAINTENANCE_BREAK.equals(l_strForcedSettleOrderDiv)
                || WEB3ForcedsettleorderDivDef.SETTLEDAY_COME_AND_GUARANTEE_MAINTENANCE_BREAK.equals(
                    l_strForcedSettleOrderDiv)))
        {
            // get�����ꗗ(From����ID : long, To����ID : long)
            List l_lisContracts = WEB3EquityPositionManager.getContracts(
                l_adminEquityForcedSettleTempOrderCreateRequest.rangeFrom,
                l_adminEquityForcedSettleTempOrderCreateRequest.rangeTo);

            //�������ϕۏ؋��ێ�������i��ԁj�������쐬�ꌏ�T�[�r�X
            WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService l_createUnitService =
                (WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService)Services.getService(
                    WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.class);
            int l_intListSize = l_lisContracts.size();
            // get�����ꗗ()�Ŏ擾����List�̗v�f����Loop����
            WEB3TPContractForcedSettleResult l_tpResult = null;
            for (int i = 0; i < l_intListSize; i ++)
            {
                EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow)l_lisContracts.get(i);
                String l_strAccId = l_eqtypeContractRow.getAccountId() + "";

                // �����v�f�̌���ID �� �u����ID�L�[�v�̏ꍇ
                if (!l_strAccId.equals(l_strAccIdKey))
                {
                    //validate���ʋ�������(EqtypeContractRow)
                    try
                    {
                        l_tpResult = l_createUnitService.validateContractForcedSettle(l_eqtypeContractRow);
                    }
                    catch (Exception l_ex)
                    {
                        log.error("__an WEB3BaseException of validate���ʋ�������", l_ex);
                        l_blnIsError = true;
                        continue;
                    }

                    // �����v�f�̌���ID���u����ID�L�[�v�ɑ������B
                    l_strAccIdKey = l_eqtypeContractRow.getAccountId() + "";
                }

                // validate���ʋ�������()�̖߂�l.����t���O �� true�̏ꍇ�A�ꌏ�������s���B
                if (l_tpResult.resultFlg)
                {
                    // exec�������쐬(EqtypeContractRow, String, ���ʋ������ό���, boolean)
                    try
                    {
                        l_createUnitService.execTempOrderCreation(
                            l_eqtypeContractRow,
                            WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET,
                            l_tpResult,
                            l_blnIsSuccOrderHandling);
                    }
                    catch (Exception l_ex)
                    {
                        log.error("__an WEB3BaseException of exec�������쐬", l_ex);
                        l_blnIsError = true;
                    }
                }
            }
        }
        // update���s�X�e�[�^�X�敪(���s�X�e�[�^�X�敪 : String)
        //�����敪�F�@@��L�������ɃG���[�����������ꍇ�A"�G���["
        //�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�A"������"
        String l_strRunStatusDiv = null;
        if(l_blnIsError)
        {
            l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
        }
        else
        {
            l_strRunStatusDiv = WEB3RunStatusDivDef.DEALED;
        }

        l_onlineRunStatus.updateRunStatusDiv(l_strRunStatusDiv);

        WEB3AdminEquityForcedSettleTempOrderCreateResponse l_response =
            (WEB3AdminEquityForcedSettleTempOrderCreateResponse)
                l_adminEquityForcedSettleTempOrderCreateRequest.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�I�����C�����s����TransactionCallback)<BR>
     * �I�����C�����s����TransactionCallback�N���X�B<BR>
     */
    public class WEB3GentradeOnlineRunStatusTransactionCallback implements TransactionCallback
    {
        /**
         * (From����ID)<BR>
         * From����ID<BR>
         */
        private long rangeFrom;

        /**
         * (To����ID)<BR>
         * To����ID<BR>
         */
        private long rangeTo;

        /**
         * (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h<BR>
         */
        private String institutionCode;

        /**
         * (�I�����C���T�[�r�X�敪)<BR>
         * �I�����C���T�[�r�X�敪<BR>
         */
        private String onlineServiceDiv;

        /**
         * �g�����U�N�V�������������{����B<BR>
         * <BR>
         * �I�����C�����s���ʃe�[�u����"������"�ݒ���s���B<BR>
         * <BR>
         * ���߂�l�ɃI�����C�����s���ʃI�u�W�F�N�g��ԋp����B<BR>
         * @@return Object
         * @@roseuid 460380BC0363
         */
        public Object process() throws DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                //�I�����C�����s���ʃe�[�u���ɏ����J�n���R�[�h�𔽉f����B  
                // �����͈ȉ��̒ʂ�ɃZ�b�g����B  
                // [����]
                //�@@ �،���ЃR�[�h�F�@@�v���p�e�B.�،���ЃR�[�h  
                //�@@ �����^�C�v�F�@@"����"  
                //�@@ �敨�^�I�v�V�����敪�F�@@"DEFAULT" 
                //�@@ �I�����C���T�[�r�X�敪�F�@@�v���p�e�B.�I�����C���T�[�r�X�敪
                //�@@ From����ID�F�@@�v���p�e�B.From����ID
                //�@@ To����ID�F�@@�v���p�e�B.To����ID
                l_onlineRunStatus =
                    WEB3GentradeOnlineRunStatus.setDealing(
                        this.institutionCode,
                        ProductTypeEnum.EQUITY,
                        WEB3FuturesOptionDivDef.DEFAULT,
                        this.onlineServiceDiv,
                        this.rangeFrom,
                        this.rangeTo);
            }
            catch (WEB3BaseException l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }

            //�Q�j�P�j�̖߂�l��Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }

        /**
         * (�I�����C�����s����transactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * @@param l_strInstitionCode - (�،���ЃR�[�h�B)<BR>
         * �،���ЃR�[�h�B<BR>
         * @@param l_lngRangeFrom - (From����ID)<BR>
         * From�����B<BR>
         * @@param l_lngRangeTo - (To����ID)<BR>
         * To����ID�B<BR>
         * @@param l_strOnlineServiceDiv - (�I�����C���T�[�r�X�敪)<BR>
         * �I�����C���T�[�r�X�敪<BR>
         * @@roseuid 460380C2016F
         */
        public WEB3GentradeOnlineRunStatusTransactionCallback(
            String l_strInstitionCode,
            long l_lngRangeFrom,
            long l_lngRangeTo,
            String l_strOnlineServiceDiv)
        {
            final String STR_METHOD_NAME = " WEB3GentradeOnlineRunStatusTransactionCallback("
                + "String, long, long, String)";
            log.entering(STR_METHOD_NAME);

            this.institutionCode = l_strInstitionCode;
            this.rangeFrom = l_lngRangeFrom;
            this.rangeTo = l_lngRangeTo;
            this.onlineServiceDiv = l_strOnlineServiceDiv;

            log.exiting(STR_METHOD_NAME);
        }
    }
}
@
