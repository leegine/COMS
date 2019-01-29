head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNettingExchangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �O�������בփl�b�e�B���O�T�[�r�X�����N���X(WEB3FeqNettingExchangeServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/09/08 �Ԑi (���u) �V�K�쐬 ���f��No.549
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ProcessIdDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqPositionManagerHelper;
import webbroker3.feq.data.FeqOrderexecutionEndRow;
import webbroker3.feq.message.WEB3FeqNettingExchangeRequest;
import webbroker3.feq.message.WEB3FeqNettingExchangeResponse;
import webbroker3.feq.service.delegate.WEB3FeqNettingExchangeService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������בփl�b�e�B���O�T�[�r�XImpl)<BR>
 * �O�������בփl�b�e�B���O�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �Ԑi
 * @@version 1.0 
 */
public class WEB3FeqNettingExchangeServiceImpl implements WEB3FeqNettingExchangeService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log  =
        WEB3LogUtility.getInstance(WEB3FeqNettingExchangeServiceImpl.class);

    /**
     * @@roseuid 42CE39F5007D
     */
    public WEB3FeqNettingExchangeServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * �בփl�b�e�B���O�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������בփl�b�e�B���O�T�[�r�X�j�בփl�b�e�B���O�����v�Q�ƁB<BR>
     * @@param l_web3BackRequest - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 42B8A39B0394
     */
    public WEB3BackResponse  execute(WEB3BackRequest  l_web3BackRequest)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_web3BackRequest == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        else
        {
            if (l_web3BackRequest instanceof WEB3FeqNettingExchangeRequest)
            {
                WEB3FeqNettingExchangeRequest l_feqNettingExchangeRequest =
                    (WEB3FeqNettingExchangeRequest)l_web3BackRequest;

                //�،���ЃR�[�h
                String l_strInstitutionCode = l_feqNettingExchangeRequest.institutionCode;

                //���������擾����B
                //�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h
                Date l_datOrderBizDate = this.getOrderBizDate(l_strInstitutionCode);

                //�l�b�e�B�������Ώیڋq�ꗗ���擾����B
                //�،���ЃR�[�h�F ���N�G�X�g.�،���ЃR�[�h
                //�������F�@@get������()�̖߂�l
                MainAccount[] l_mainAccounts = this.getNettingMainAccountList(
                    l_strInstitutionCode,
                    l_datOrderBizDate);

                //�����敪
                String l_strStatus = WEB3StatusDef.DEALT;

                QueryProcessor l_queryProcessor = null;
                try
                {
                    l_queryProcessor = Processors.getDefaultProcessor();
                }
                catch (DataFindException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                         this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                          WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                          this.getClass().getName() + "." + STR_METHOD_NAME);
                }

                //get�����Ώیڋq�ꗗ()�̗v�f����Loop����
                if (l_mainAccounts != null)
                {
                    for (int i = 0; i < l_mainAccounts.length; i++)
                    {
                        try
                        {
                            //update�g�����U�N�V����TransactionCallback
                            WEB3FEQNettingUpdateTransactionCallback l_callBack =
                                new WEB3FEQNettingUpdateTransactionCallback();

                            l_callBack.setMainAccount(l_mainAccounts[i]);
                            l_callBack.setOrderBizDate(l_datOrderBizDate);

                            l_queryProcessor.doTransaction(TransactionalInterceptor.TX_CREATE_NEW, l_callBack);
                        }
                        catch(Exception l_ex)
                        {
                            l_strStatus = WEB3StatusDef.DATA_ERROR;
                            continue;
                        }
                    }
                }

                //�،���Ђ��擾����B
                l_strInstitutionCode = l_feqNettingExchangeRequest.institutionCode;
                WEB3GentradeInstitution l_institution = null;
                try
                {
                    l_institution = new WEB3GentradeInstitution(l_strInstitutionCode);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���" , l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���" , l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //�،���ЃR�[�h�̑Ή��̑S�Ă̕��X���擾����B
                Branch[] l_branches = l_institution.getBranches();
                //�擾�����������ʐ���LOOP����
                //�v���Z�X�Ǘ�Params�𐶐�����B
                ProcessManagementParams l_processManagementParams =
                    new ProcessManagementParams();

                //�v���Z�X�h�c:'0014'
                l_processManagementParams.setProcessId(WEB3ProcessIdDef.FEQ_NETTING_EXCHANGE_COMPLETE);

                //���͂̏،���ЃR�[�h���Z�b�g�B
                l_processManagementParams.setInstitutionCode(
                    l_strInstitutionCode);

                //�����敪:
                l_processManagementParams.setStatus(l_strStatus);

                //�ŏI�X�V��:null
                l_processManagementParams.setLastUpdater(null);

                for (int i = 0; i < l_branches.length; i++)
                {
                    //���X�R�[�h:�،����.���X�R�[�h[i]
                    l_processManagementParams.setBranchCode(l_branches[i].getBranchCode());

                    //�ŏI�X�V����:"���ݓ���(GtlUtils.getSystemTimestamp())"
                    l_processManagementParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                    try
                    {
                        l_queryProcessor.doInsertQuery(l_processManagementParams);
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
                }

                WEB3FeqNettingExchangeResponse l_feqNettingExchangeResponse =
                    (WEB3FeqNettingExchangeResponse)l_web3BackRequest.createResponse();

                log.exiting(STR_METHOD_NAME);
                return l_feqNettingExchangeResponse;
            }
            else
            {
                log.debug("�p�����[�^�^�C�v�s��");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�p�����[�^�^�C�v�s��");
            }
        }
    }

    /**
     * (get�l�b�e�B�������Ώیڋq�ꗗ)<BR>
     * �l�b�e�B�������ΏۂƂȂ钍����ێ�����B<BR>
     * �ڋq�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����P�ʌ���<BR>
     * �O�����������}�l�[�W��.get�l�b�e�B���Ώے����P��()���R�[������B<BR>
     * <BR>
     * [get�l�b�e�B���Ώے����P��()�Ɏw�肷�����]<BR>
     * ����ID�F�@@null<BR>
     * �،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �������F�@@get������()�̖߂�l<BR>
     * ��null���ԋp���ꂽ�ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�I�u�W�F�N�g�쐬<BR>
     * �Q�|�P�j�P�j�̌������ʂɂ��āA���j�[�N�Ȍ���ID�̈ꗗ���쐬����B<BR>
     * �Q�|�Q�j�Q�|�P�j�ɂč쐬��������ID�̈ꗗ���A�ڋq�I�u�W�F�N�g���쐬���A<BR>
     * �z��Ƃ��ĕԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�I�u�W�F�N�g<BR>
     * @@param l_datOrderBizDate - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@return MainAccount[]
     * @@throws WEB3BaseException
     */
    private MainAccount[] getNettingMainAccountList(String l_strInstitutionCode,
        Date l_datOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNettingMainAccountList(String, Date)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����P�ʌ���
        //�O�����������}�l�[�W��.get�l�b�e�B���Ώے����P��()���R�[������B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        WEB3FeqOrderManager l_feqOrderManager =
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

        //[get�l�b�e�B���Ώے����P��()�Ɏw�肷�����]
        //����ID�F�@@null
        //�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h
        //�������F�@@get������()�̖߂�l
        WEB3FeqOrderUnit[] l_feqOrderUnits = l_feqOrderManager.getNettingOrderUnit(
            null,
            l_strInstitutionCode,
            l_datOrderBizDate);

        //��null���ԋp���ꂽ�ꍇ�Anull��ԋp����B
        if (l_feqOrderUnits == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�Q�j�@@�ڋq�I�u�W�F�N�g�쐬
        else
        {
            //�Q�|�P�j�P�j�̌������ʂɂ��āA���j�[�N�Ȍ���ID�̈ꗗ���쐬����B
            //�Q�|�Q�j�Q�|�P�j�ɂč쐬��������ID�̈ꗗ���A�ڋq�I�u�W�F�N�g���쐬���A�z��Ƃ��ĕԋp����B
            WEB3GentradeMainAccount[] l_mainAccounts= null;
            List l_lisMainAccounts = new ArrayList();
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            int l_intCnt = l_feqOrderUnits.length;

            List l_lisAccountId = new ArrayList();

            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqOrderUnit l_orderUnit = l_feqOrderUnits[i];

                if (l_orderUnit != null)
                {
                    Long l_accountId = new Long(l_orderUnit.getAccountId());

                    if (!l_lisAccountId.contains(l_accountId))
                    {
                        l_lisAccountId.add(l_accountId);
                    }
                }
            }

            int l_intAccountCnt = l_lisAccountId.size();

            try
            {
                for (int i = 0; i < l_intAccountCnt; i++)
                {
                    Long l_accountId = (Long)l_lisAccountId.get(i);

                    WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)
                        l_accMgr.getMainAccount(l_accountId.longValue());//NotFoundException
                    l_lisMainAccounts.add(l_mainAccount);
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

            if (!l_lisMainAccounts.isEmpty())
            {
                l_mainAccounts = new WEB3GentradeMainAccount[l_lisMainAccounts.size()];
                l_lisMainAccounts.toArray(l_mainAccounts);
            }
            log.exiting(STR_METHOD_NAME);   
            return l_mainAccounts;
        }
    }

    /**
     * (get�������j<BR>
     * <BR>
     * �P�j�@@�����P�ʌ���<BR>
     * �ȉ��̏����ɂĊO���o���I���e�[�u������������B<BR>
     * �@@[����]<BR>
     * �،���ЃR�[�h�@@=�@@�p�����[�^.�،���ЃR�[�h<BR>
     * <BR>
     * �������ʂ́A�ȉ��̍��ڂō~���\�[�g���A�擾���邱�ƁB<BR>
     * �@@�@@�@@�O����{��<BR>
     * <BR>
     * �@@���O���o���I���e�[�u�����擾�ł��Ȃ��ꍇ�́A�V�X�e���G���[�h�Y���f�[�^�Ȃ��h��ԋp����B<BR>
     * <BR>
     * �Q�j�������擾<BR>
     * �@@�O���o���I��List(0)�D�O����{����ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�I�u�W�F�N�g<BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    private Date getOrderBizDate(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDate(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����P�ʌ���
        //[����]
        //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
        // �������ʂ́A�ȉ��̍��ڂō~���\�[�g���A�擾���邱�ƁB
         //�@@�O����{��
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        // �\�[�g����
        String l_strOrderBy = " last_execute_date desc  ";

        List l_lisRecords = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderexecutionEndRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                new Object[]{l_strInstitutionCode}
                );
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

        //���O���o���I���e�[�u�����擾�ł��Ȃ��ꍇ�́A�V�X�e���G���[�h�Y���f�[�^�Ȃ��h��ԋp����B
        if (l_lisRecords.size() == 0)
        {
            log.debug("�Y���f�[�^�Ȃ�");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "�e�[�u���ɊY������f�[�^������܂���B");
        }
        //�O���o���I��List(0)�D�O����{����ԋp����B
        else
        {
            FeqOrderexecutionEndRow l_feqOrderexecutionEnd = (FeqOrderexecutionEndRow)l_lisRecords.get(0);

            log.exiting(STR_METHOD_NAME);
            return l_feqOrderexecutionEnd.getLastExecuteDate();
        }
    }

    /**
     * update�g�����U�N�V����TransactionCallback<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     */
    public class WEB3FEQNettingUpdateTransactionCallback implements TransactionCallback
    {
        /**
         *�����P�ʂh�c�ꗗ<BR>
         */
        private long[] l_lngOrderUnitIdLists;

        /**
         *���<BR>
         */
        private Date l_datOrderBizDate;

        /**
         * �ڋq
         */
        private MainAccount l_mainAccount;

        /**
         * (set�����P�ʂh�c�ꗗ)<BR>
         * <BR>
         * �����̒����P�ʂh�c�ꗗ���v���p�e�B�ɃZ�b�g����B<BR>
         * @@params long[] - �����P�ʂh�c�ꗗ<BR>
         */
        public void setOrderUnitIdList(long[] l_lngOrderUnitIdLists)
        {
            this.l_lngOrderUnitIdLists = l_lngOrderUnitIdLists;
        }

        /**
         * (set���)<BR>
         * <BR>
         * �����̊�����v���p�e�B�ɃZ�b�g����B<BR>
         * @@params Date - ���<BR>
         */
        public void setOrderBizDate(Date l_datOrderBizDate)
        {
            this.l_datOrderBizDate = l_datOrderBizDate;
        }

        /**
         * (set�ڋq)<BR>
         * <BR>
         * �����̌ڋq���v���p�e�B�ɃZ�b�g����B<BR>
         * @@params Date - ���<BR>
         */
        public void setMainAccount(MainAccount l_mainAccount)
        {
            this.l_mainAccount = l_mainAccount;
        }

        /**
         * �f�t�H���g�R���X�g���N�^
         * @@return WEB3FeqNettingExchangeServiceImpl.WEB3FEQNettingUpdateTransactionCallback
         * @@roseuid 4088F56A0131
         */
        public WEB3FEQNettingUpdateTransactionCallback()
        {

        }

        /**
         * �l�b�e�B���O�בւƂȂ�g�����U�N�V�����̋��z���Čv�Z����B<BR>
         * <BR>
         * <BR>
         * @@return Object
         * @@roseuid 4088F56A0122
         */
        public Object process()
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //�O�������|�W�V�����w���p�[���擾����B
            WEB3FeqPositionManagerHelper l_feqPositionManagerHelper =
                new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            //�g���A�J�E���g�}�l�[�W�����擾����B
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //�O�����������}�l�[�W�����擾����B
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_feqOrderManager =
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

            //���������b�N����B
            //�،���ЃR�[�h�F�@@�ڋq.�،���ЃR�[�h
            //���X�R�[�h�F�@@�ڋq.���X�R�[�h
            //�����R�[�h�F�@@�ڋq.�����R�[�h
            String l_strInstitutionCode =  this.l_mainAccount.getInstitution().getInstitutionCode();
            String l_strBranchCode = this.l_mainAccount.getBranch().getBranchCode();
            String l_strAccountCode =  this.l_mainAccount.getAccountCode();


            //�l�b�e�B���ΏۂƂȂ钍���P�ʂ��擾����B
            //����ID�F�@@�����Ώۂ̗v�f.�ڋq.����ID
            //�،���ЃR�[�h�F�@@�p�����[�^.�ڋq.�،���ЃR�[�h
            //�������F�@@get������()�̖߂�l
            long l_lngAccountId = this.l_mainAccount.getAccountId();

            try
            {
                WEB3FeqOrderUnit[] l_orderUnits = l_feqOrderManager.getNettingOrderUnit(
                    new Long(l_lngAccountId),
                    l_strInstitutionCode,
                    l_datOrderBizDate);

                //�����P��ID�ꗗ�Fget�l�b�e�B���Ώے����P��()�̖߂�l��蒊�o���������P��ID�̈ꗗ
                long[] l_lngOrderUnitIdList = null;
    
                int l_intCnt = 0;
    
                if (l_orderUnits != null && l_orderUnits.length > 0)
                {
                    l_intCnt = l_orderUnits.length;
                    int l_intCnt2 = 0;
    
                    for (int j = 0; j < l_intCnt; j++)
                    {
                        WEB3FeqOrderUnit l_orderUnit = l_orderUnits[j];
    
                        if (l_orderUnit != null)
                        {
                            l_intCnt2++;
                        }
                    }
                    if (l_intCnt2 > 0)
                    {
                        l_lngOrderUnitIdList = new long[l_intCnt2];
                        int l_intNo = 0;
    
                        for (int k = 0; k < l_intCnt; k++)
                        {
                            WEB3FeqOrderUnit l_orderUnit = l_orderUnits[k];
    
                            if (l_orderUnit != null)
                            {
                                l_lngOrderUnitIdList[l_intNo] = l_orderUnit.getOrderUnitId(); 
                                l_intNo++;
                            }
                        }
                    }
                }
                l_accountManager.lockAccount(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);

                this.setOrderUnitIdList(l_lngOrderUnitIdList);
                //update�g�����U�N�V�����i�l�b�e�B���O�̗p�j(�����P�ʂh�c�ꗗ : long[], ��� : Date)
                l_feqPositionManagerHelper.updateTransactionNettingAdoption(l_lngOrderUnitIdLists, l_datOrderBizDate);
            }
            catch(WEB3BaseException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
