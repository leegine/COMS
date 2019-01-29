head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAutoExecuteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������T�[�r�XImpl (WEB3BondAutoExecuteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 ꎉ� (���u) �V�K�쐬
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.message.WEB3BondAutoExecRequest;
import webbroker3.bd.message.WEB3BondAutoExecResponse;
import webbroker3.bd.service.delegate.WEB3BondAutoExecuteService;
import webbroker3.bd.service.delegate.WEB3BondAutoExecuteUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.util.WEB3LogUtility;

/**
 * (���������T�[�r�XImpl)<BR>
 * ���������T�[�r�X�����N���X<BR>
 *
 * @@author ꎉ�(���u)
 * @@version 1.0
 */
public class WEB3BondAutoExecuteServiceImpl implements WEB3BondAutoExecuteService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondAutoExecuteServiceImpl.class);

    /**
     * ��������菈�������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���������execute�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        if (!(l_request instanceof WEB3BondAutoExecRequest))
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        Object l_objDoTransaction = null;

        //1.1 validate()
        WEB3BondAutoExecRequest l_bondAutoExecRequest =
            (WEB3BondAutoExecRequest)l_request;
        l_bondAutoExecRequest.validate();

        WEB3BondAutoExecResponse l_response = null;
        try
        {
            //1.2 getDefaultProcessor()
            QueryProcessor l_queryProcessors = Processors.getDefaultProcessor();

            //1.3 �I�����C�����s����TransactionCallback(String, long, long)
            //[����]
            //  �،���ЃR�[�h�F���N�G�X�g�f�[�^.�،���ЃR�[�h
            //  from����ID�F���N�G�X�g�f�[�^.from����ID
            //  to����ID�F���N�G�X�g�f�[�^.to����ID
            WEB3GentradeOnlineRunStatusTransactionCallback l_onlineRunStatusTransactionCallback =
                new WEB3GentradeOnlineRunStatusTransactionCallback(
                    l_bondAutoExecRequest.institutionCode,
                    l_bondAutoExecRequest.fromAccountId,
                    l_bondAutoExecRequest.toAccountId);

            //1.4 doTransaction(arg0 : int, arg1 : TransactionCallback)
            //[����]
            //  Tx�FQueryProcessor.TX_CREATE_NEW
            //  TransactionCallback�F�I�����C�����s����TransactionCallback
            l_objDoTransaction =
                l_queryProcessors.doTransaction(QueryProcessor.TX_CREATE_NEW,
                    l_onlineRunStatusTransactionCallback);
        }
        catch (DataCallbackException l_dataCallbackException)
        {
            //1.5 ��d�N���̗�O��throw���ꂽ�ꍇ
            Object l_exception = l_dataCallbackException.getDetails();
            if (l_exception instanceof WEB3BaseException)
            {
                WEB3BaseException l_baseException = (WEB3BaseException)l_exception;
                if (WEB3ErrorCatalog.BUSINESS_ERROR_01992.equals(l_baseException.getErrorInfo()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw l_baseException;
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


        //1.6 get�����Ώیڋq�ꗗ(String, long, long)
        MainAccount[] l_mainAccount = this.getAccountList(
            l_bondAutoExecRequest.institutionCode,
            l_bondAutoExecRequest.fromAccountId,
            l_bondAutoExecRequest.toAccountId);

        //1.7 getService(arg0 : Class)
        //�T�[�r�X�F���������T�[�r�X
        WEB3BondAutoExecuteService l_service =
            (WEB3BondAutoExecuteService)Services.getService(
                WEB3BondAutoExecuteService.class);

        //1.8 �擾�����ڋq�I�u�W�F�N�g����Loop
        boolean l_blnIsError = false;
        if (l_mainAccount != null)
        {
            for(int i = 0; i < l_mainAccount.length; i++)
            {
                try
                {
                    //1.8.1 exec�������For�ڋq(�ڋq)
                    l_service.execAutoExecuteForAccount(l_mainAccount[i]);
                }
                catch(WEB3BaseException l_ex)
                {
                    log.debug("__an WEB3BaseException ", l_ex);
                    l_blnIsError = true;
                }
            }
        }

        //1.9 update���s�X�e�[�^�X�敪(���s�X�e�[�^�X�敪 : String)
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

        l_response =
            (WEB3BondAutoExecResponse)l_bondAutoExecRequest.createResponse();


        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�����Ώیڋq�ꗗ)<BR>
     * get�����Ώیڋq�ꗗ <BR>
     * <BR>
     * �P�j�@@�����P�ʌ���  <BR>
     * �@@�g���������}�l�[�W��.get�������Ώے���()���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F����.�،���ЃR�[�h  <BR>
     * �@@�@@�@@from����ID�F�@@�@@����.from����ID  <BR>
     * �@@�@@�@@to����ID�F�@@�@@�@@����.to����ID  <BR>
     * <BR>
     * �Q�j�ڋq�I�u�W�F�N�g�쐬  <BR>
     * �@@�Q�|�P�j�P�j�̌������ʂɂ��āA���j�[�N�Ȍ���ID�̈ꗗ���쐬����B <BR>
     * �@@�Q�|�Q�j�Q�|�P�j�ɂč쐬��������ID�̈ꗗ���A�ڋq�I�u�W�F�N�g���쐬���A<BR>
     * �@@�@@�z��Ƃ��ĕԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param  l_lngFromAccountId - (from����ID)<BR>
     * from����ID<BR>
     * @@param l_lngToAccountId - (to����ID)<BR>
     * to����ID<BR>
     * @@return MainAccount[]
     * @@throws WEB3BaseException
     */
    protected MainAccount[] getAccountList(
        String l_strInstitutionCode,
        long l_lngFromAccountId,
        long l_lngToAccountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAccountList(String, long, long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����P�ʌ���
        //�g�������������}�l�[�W��.get�������Ώے���()���R�[������B
        //�@@[����]
        //�@@�@@�،���ЃR�[�h�F����.�،���ЃR�[�h
        //�@@�@@from����ID�F�@@�@@����.from����ID
        //�@@�@@to����ID�F�@@�@@�@@����.to����ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        WEB3BondOrderUnit[] l_bondOrderUnit =
            l_bondOrderManager.getAutoExecuteOrder(
                l_strInstitutionCode,
                l_lngFromAccountId,
                l_lngToAccountId);

        //�Q�j�@@�ڋq�I�u�W�F�N�g�쐬
        AccountManager l_accountManager = l_finApp.getAccountManager();
        int l_intSize = 0;
        if(l_bondOrderUnit == null)
        {
            return null;
        }
        else
        {
            l_intSize = l_bondOrderUnit.length;
        }
        List l_lisMainAccount = new ArrayList();

        HashSet l_hsAccountId = new HashSet();
        for(int i = 0; i < l_intSize; i++)
        {
            //�Q�|�P�j�P�j�̌������ʂɂ��āA���j�[�N�Ȍ���ID�̈ꗗ���쐬����B
            Long l_lngAccountId = new Long(l_bondOrderUnit[i].getAccountId());

            l_hsAccountId.add(l_lngAccountId);
        }
        try
        {
            Iterator l_iterator = l_hsAccountId.iterator();
            while(l_iterator.hasNext())
            {
                MainAccount l_mainAccount = l_accountManager.getMainAccount(
                    Long.parseLong(l_iterator.next().toString()));
                l_lisMainAccount.add(l_mainAccount);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //  �z��Ƃ��ĕԋp����B
        MainAccount[] l_mainAccounts = new MainAccount[l_lisMainAccount.size()];
        l_lisMainAccount.toArray(l_mainAccounts);
        log.exiting(STR_METHOD_NAME);
        return l_mainAccounts;
    }

    /**
     * (exec�������For�ڋq)<BR>
     * exec�������For�ڋq <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uexec�������For�ڋq�v�Q�ƁB<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@throws WEB3BaseException
     */
    public void execAutoExecuteForAccount(MainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execAutoExecuteForAccount(MainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBrachCode = l_mainAccount.getBranch().getBranchCode();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        // 1.1 lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        // �،���ЃR�[�h�F�ڋq.get�،����.get�،���ЃR�[�h
        // ���X�R�[�h�F�ڋq.get���X.get���X�R�[�h
        // �����R�[�h�F�ڋq.get�ڋq�R�[�h
        l_accountManager.lockAccount(
            l_strInstitutionCode,
            l_strBrachCode,
            l_mainAccount.getAccountCode());

        // 1.2 get�������Ώے���(String, long, long)
        // �،���ЃR�[�h�F�ڋq.get�،����.get�،���ЃR�[�h
        // from����ID�F�ڋq.get����ID
        // to����ID�F�ڋq.get����ID
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        WEB3BondOrderUnit[] l_bondOrderUnit =
            l_bondOrderManager.getAutoExecuteOrder(
                l_strInstitutionCode,
                l_mainAccount.getAccountId(),
                l_mainAccount.getAccountId());

        // 1.3 �擾���������I�u�W�F�N�g����Loop
        // �������P�ʁFLoop���̒����I�u�W�F�N�g
        if (l_bondOrderUnit != null)
        {
            for(int i = 0; i < l_bondOrderUnit.length; i++)
            {
                // 1.3.1 notify�������(�g���������P��)
                WEB3BondAutoExecuteUnitService l_autoExecuteUnitService =
                    (WEB3BondAutoExecuteUnitService) Services.getService(
                        WEB3BondAutoExecuteUnitService.class);
                l_autoExecuteUnitService.notifyAutoExecute(l_bondOrderUnit[i]);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�I�����C�����s����TransactionCallback)<BR>
     * �I�����C�����s����TransactionCallback�N���X<BR>
     */
    public class WEB3GentradeOnlineRunStatusTransactionCallback implements TransactionCallback
    {
        /**
         *(�،���ЃR�[�h)<BR>
         *�،���ЃR�[�h<BR>
         */
        private String institutionCode;

        /**
         * (from����ID)<BR>
         * from����ID<BR>
         */
        private long fromAccountId;

        /**
         * (to����ID)<BR>
         * to����ID<BR>
         */
        private long toAccountId;

        /**
         * (�I�����C�����s����TransactionCallback) <BR>
         * �R���X�g���N�^<BR>
         * <BR>
         * �،���ЃR�[�h������.�،���ЃR�[�h <BR>
         * from����ID������.from����ID<BR>
         * to����ID������.to����ID <BR>
         * <BR>
         * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h<BR>
         * @@param l_lngFromAccountId - (from����ID)<BR>
         * from����ID<BR>
         * @@param l_lngToAccountId - (to����ID)<BR>
         * to����ID
         */
        public WEB3GentradeOnlineRunStatusTransactionCallback(
            String l_strInstitutionCode,
            long l_lngFromAccountId,
            long l_lngToAccountId)
        {
            final String STR_METHOD_NAME =
                "WEB3GentradeOnlineRunStatusTransactionCallback(Sting, long, long)";
            log.entering(STR_METHOD_NAME);

            //�،���ЃR�[�h������.�،���ЃR�[�h
            this.institutionCode = l_strInstitutionCode;
            //from����ID������.from����ID
            this.fromAccountId = l_lngFromAccountId;
            //to����ID������.to����ID
            this.toAccountId = l_lngToAccountId;

            log.exiting(STR_METHOD_NAME);
        }

        /**
         * (process���\�b�h�̃I�[�o�[���C�h) <BR>
         * <BR>
         * �P�j�I�����C�����s����.set������(�،���ЃR�[�h�A<BR>
         * �@@�����^�C�v�A�敨�^�I�v�V�����敪�A�I�����C���T�[�r�X<BR>
         * �@@�敪�AFrom����ID�ATo����ID)���ĂԁB <BR>
         * <BR>
         * �@@[����] <BR>
         * �@@�@@�،���ЃR�[�h�@@�@@�@@���،���ЃR�[�h <BR>
         * �@@�@@�����^�C�v�@@�@@�@@�@@�@@�@@��'��' <BR>
         * �@@�@@�敨�^�I�v�V�����敪��'DEFAULT'<BR>
         * �@@�@@�I�����C���T�[�r�X�敪��'�������'<BR>
         * �@@�@@From����ID�@@�@@�@@�@@�@@��from����ID<BR>
         * �@@�@@To����ID�@@�@@�@@�@@�@@�@@�@@��to����ID<BR>
         * <BR>
         * �Q�j�P�j�̖߂�l��Ԃ��B<BR>
         * @@return Object
         * @@throws DataCallbackException
         */
        public Object process() throws DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);

            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                //�P�j�I�����C�����s����.set������(�،���ЃR�[�h�A�����^�C�v�A�敨�^�I�v�V�����敪�A
                // �I�����C���T�[�r�X�敪�AFrom����ID�ATo����ID)���ĂԁB
                // [����]
                //�@@ �،���ЃR�[�h�@@�@@�@@���،���ЃR�[�h
                //�@@ �����^�C�v�@@�@@�@@�@@�@@�@@��'��'
                //�@@ �敨�^�I�v�V�����敪��'DEFAULT'
                //�@@ �I�����C���T�[�r�X�敪��'�������'
                //�@@ From����ID�@@�@@�@@�@@�@@��from����ID
                //�@@ To����ID�@@�@@�@@�@@�@@�@@�@@��to����ID
                l_onlineRunStatus =
                    WEB3GentradeOnlineRunStatus.setDealing(
                        this.institutionCode,
                        ProductTypeEnum.BOND,
                        WEB3FuturesOptionDivDef.DEFAULT,
                        WEB3OnlineServiceDiv.AUTO_EXECUTE,
                        this.fromAccountId,
                        this.toAccountId);
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
    }
}@
