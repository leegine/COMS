head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSExecEndNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (PTS)�����o���I���ʒm�T�[�r�XImpl(WEB3EquityPTSExecEndNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 ��іQ(���u) �V�K�쐬 ���f��No.1285�C1295
Revision History : 2008/02/18 ��іQ(���u) ���f��No.1302
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3MarginUpdateEventInterceptor;
import webbroker3.equity.message.WEB3EquityPTSExecEndNotifyRequest;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSExecEndNotifyService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ((PTS)�����o���I���ʒm�T�[�r�XImpl)<BR>
 * (PTS)�����o���I���ʒm�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3EquityPTSExecEndNotifyServiceImpl
    implements WEB3EquityPTSExecEndNotifyService
{
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSExecEndNotifyServiceImpl.class);

    /**
     * @@roseuid 40B1BACD02E9
     */
    public WEB3EquityPTSExecEndNotifyServiceImpl()
    {

    }

    /**
     * (PTS)�����o���I���ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�iPTS�j�o���I���ʒm�T�[�r�X�j�o���I���ʒm�v�Q��<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
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

        WEB3EquityPTSExecEndNotifyRequest l_ptsExecEndNotifyRequest =
            (WEB3EquityPTSExecEndNotifyRequest)l_request;

        //vaidate( )
        l_ptsExecEndNotifyRequest.validate();

        try
        {
            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //(PTS)�I�����C�����s����TransactionCallback�I�u�W�F�N�g�𐶐�����B
            //�����̐ݒ�d�l�͈ȉ��̒ʂ�
            //�،���ЃR�[�h : ���N�G�X�g.�،���ЃR�[�h
            //From����ID : ���N�G�X�g.From����ID
            //To����ID : ���N�G�X�g.To����ID
            WEB3EquityPTSOnlineRunStatusTransactionCallback l_onlineRunStatusTransactionCallback =
                new WEB3EquityPTSOnlineRunStatusTransactionCallback(
                    l_ptsExecEndNotifyRequest.institutionCode,
                    l_ptsExecEndNotifyRequest.rangeFrom,
                    l_ptsExecEndNotifyRequest.rangeTo);

            // doTransaction(�g�����U�N�V�������� : int, TransactionCallback  : TransactionCallback)
            //�g�����U�N�V�������� : TX_CREATE_NEW
            //TransactionCallback : ��������(PTS)�I�����C�����s����TransactionCallback�I�u�W�F�N�g
            WEB3GentradeOnlineRunStatus l_onlineRunStatus =
                (WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    l_onlineRunStatusTransactionCallback);

            //�iPTS�j�o���I���ʒmTransactionCallback(String, long, long, �I�����C�����s����, String)
            //�،���ЃR�[�h : ���N�G�X�g.�،���ЃR�[�h
            //From����ID : ���N�G�X�g.From����ID
            //To����ID : ���N�G�X�g.To����ID
            //�I�����C�����s���� :
            //�@@(PTS)�I�����C�����s����TransactionCallback.process( )�̖߂�l�ł���I�����C�����s���ʃI�u�W�F�N�g
            //�s��R�[�h : ���N�G�X�g.�s��R�[�h
            WEB3EquityPTSExecEndNotifyTransactionCallback l_execEndNotifyTransactionCallback =
                new WEB3EquityPTSExecEndNotifyTransactionCallback(
                    l_ptsExecEndNotifyRequest.institutionCode,
                    l_ptsExecEndNotifyRequest.rangeFrom,
                    l_ptsExecEndNotifyRequest.rangeTo,
                    l_onlineRunStatus,
                    l_ptsExecEndNotifyRequest.marketCode);

            //doTransaction(�g�����U�N�V�������� : int, TransactionCallback : TransactionCallback)
            //�g�����U�N�V�������� : TX_CREATE_NEW
            //TransactionCallback : ��������(PTS)�o���I���ʒmTransactionCallback�I�u�W�F�N�g
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_execEndNotifyTransactionCallback);
        }
        catch (DataCallbackException l_dataCallbackException)
        {
            //process( )�ŗ�O��throw���ꂽ�ꍇ
            //throw���ꂽ��O�I�u�W�F�N�g���A�G���[�����擾���č�throw����B
            WEB3BaseException l_ex = (WEB3BaseException)l_dataCallbackException.getDetails();

            log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw l_ex;
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

        log.exiting(STR_METHOD_NAME);
        return l_ptsExecEndNotifyRequest.createResponse();
    }

    /**
     * ((PTS)�o���I���ʒmTransactionCallback)<BR>
     * �iPTS�j�o���I���ʒmTransactionCallback�N���X<BR>
     */
    public class WEB3EquityPTSExecEndNotifyTransactionCallback
        implements TransactionCallback
    {
        /**
         * (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h<BR>
         */
        private String institutionCode;

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
         * (�I�����C�����s����)<BR>
         * �I�����C�����s����<BR>
         */
        private WEB3GentradeOnlineRunStatus onlineRunStatus;

        /**
         * (�s��R�[�h)<BR>
         * �s��R�[�h<BR>
         */
        private String marketCode;

        /**
         * (�iPTS�j�o���I���ʒmTransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * ���N���X�̃v���p�e�B�ɁA�����̓����ڂ��Z�b�g����B<BR>
         * <BR>
         * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h�B<BR>
         * @@param l_lngRangeFrom - (From����ID)<BR>
         * From����ID�B<BR>
         * @@param l_lngRangeTo - (To����ID)<BR>
         * To����ID�B<BR>
         * @@param l_onlineRunStatus - (�I�����C�����s����)<BR>
         * �I�����C�����s���ʁB<BR>
         * @@param l_strMarketCode - (�s��R�[�h)<BR>
         * �s��R�[�h<BR>
         */
        public WEB3EquityPTSExecEndNotifyTransactionCallback(
            String l_strInstitutionCode,
            long l_lngRangeFrom,
            long l_lngRangeTo,
            WEB3GentradeOnlineRunStatus l_onlineRunStatus,
            String l_strMarketCode)
        {
            institutionCode = l_strInstitutionCode;
            rangeFrom = l_lngRangeFrom;
            rangeTo = l_lngRangeTo;
            onlineRunStatus = l_onlineRunStatus;
            marketCode = l_strMarketCode;
        }

        /**
         * (get�����Ώیڋq�ꗗ)<BR>
         * �L�����������ڋq�̈ꗗ���쐬���Ԃ��B<BR>
         * <BR>
         * �P�j�@@PTS�s��̎s��ID���擾����B<BR>
         * �@@�@@�@@�s��ID�́A�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��( )<BR>
         * �@@�@@�@@�Ŏ擾�����s��I�u�W�F�N�g����擾����B<BR>
         * �@@�@@�@@�@@[get�s��( )�̈����ݒ�]<BR>
         * �@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@this.�،���ЃR�[�h<BR>
         * �@@�@@�@@�@@�@@�s��R�[�h�F�@@this.�s��R�[�h<BR>
         * <BR>
         * �Q�j�@@�L�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
         * <BR>
         * �@@�@@�@@�N�G���v���Z�b�T���g�p���A<BR>
         * �@@�@@�@@�ȉ��̏����ɍ��v���钍���P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
         * �@@�@@�@@�i����ID�����w��j<BR>
         * <BR>
         * �@@�@@�@@----------------------------------------------------------<BR>
         * �@@�@@�@@�����o������<BR>
         * <BR>
         * �@@�@@�@@�@@�@@�@@���XID in this.�،���ЃR�[�h<BR>
         * �@@�@@�@@�@@�@@�@@�ɊY������،����.getBranches( )�̖߂�l�̂����ꂩ<BR>
         * �@@�@@�@@���@@�����^�C�v == "����"<BR>
         * �@@�@@�@@���@@������� != �i"�����~�j��������"�A"�����~�j��������"�j<BR>
         * �@@�@@�@@���@@�����L����� == "�I�[�v��"<BR>
         * �@@�@@�@@���@@this.From����ID <= ����ID<BR>
         * �@@�@@�@@���@@����ID <= this.To����ID<BR>
         * �@@�@@�@@���@@������ < PTS������ԊǗ�.getPTS������( )(*1)<BR>
         * �@@�@@�@@���@@�s��ID == �P�j�Ŏ擾�����s��ID<BR>
         * <BR>
         * �@@�@@�@@��(*1)������PTS�s��J�ǎ��ԑтɔ������������݂̂��擾���邽�߂̏����B<BR>
         * �@@�@@�@@----------------------------------------------------------<BR>
         * <BR>
         * �R�j�@@�ڋq�I�u�W�F�N�g�̈ꗗ���쐬����B<BR>
         * <BR>
         * �@@�@@�@@�܂��A�Q�j�Ŏ擾���������P�ʃI�u�W�F�N�g.����ID�̈�ӂȈꗗ���쐬����B<BR>
         * �@@�@@�@@�e����ID���ɁA�Y������ڋq�I�u�W�F�N�g���擾���A<BR>
         * �@@�@@�@@�ԋp�p�̌ڋq�I�u�W�F�N�g�̈ꗗ�ɒǉ����Ă����B<BR>
         * <BR>
         * �S�j�@@�쐬�����ڋq�I�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
         * �@@�@@�@@���Y���f�[�^�Ȃ�����null��ԋp����B<BR>
         * @@return WEB3GentradeMainAccount[]
         * @@throws WEB3BaseException
         */
        protected WEB3GentradeMainAccount[] getMainAccounts() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getMainAccounts()";
            log.entering(STR_METHOD_NAME);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            Institution l_institution = null;
            WEB3GentradeMarket l_market = null;
            try
            {
                //�P�j�@@PTS�s��̎s��ID���擾����B
                //�s��ID�́A�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��( )�Ŏ擾�����s��I�u�W�F�N�g����擾����B
                //�،���ЃR�[�h�F�@@this.�،���ЃR�[�h
                //�s��R�[�h�F�@@this.�s��R�[�h
                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                    this.institutionCode,
                    this.marketCode);

                //this.�،���ЃR�[�h�ɊY������،����
                l_institution = l_accountManager.getInstitution(this.institutionCode);
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

            //�Q�j�@@�L�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B
            StringBuffer l_sbWhere = new StringBuffer();

            //���XID in this.�،���ЃR�[�h�ɊY������،����.getBranches( )�̖߂�l�̂����ꂩ
            Branch[] l_branchs = l_institution.getBranches();
            if (l_branchs != null && l_branchs.length > 0)
            {
                l_sbWhere.append("branch_id in (" + l_branchs[0].getBranchId());
                for (int i = 1; i < l_branchs.length; i++)
                {
                    l_sbWhere.append("," + l_branchs[i].getBranchId());
                }
                l_sbWhere.append(") and ");
            }

            //�����^�C�v == "����"
            //���@@������� != �i"�����~�j��������"�A"�����~�j��������"�j
            //���@@�����L����� == "�I�[�v��"
            //���@@this.From����ID <= ����ID
            //���@@����ID <= this.To����ID
            //���@@������ < PTS������ԊǗ�.getPTS������( )(*1)
            //���@@�s��ID == �P�j�Ŏ擾�����s��ID
            l_sbWhere.append(" product_type = ?");
            l_sbWhere.append(" and order_type not in (?,?)");
            l_sbWhere.append(" and order_open_status = ?");
            l_sbWhere.append(" and ? <= account_id");
            l_sbWhere.append(" and account_id <= ?");
            l_sbWhere.append(" and biz_date < ?");
            l_sbWhere.append(" and market_id = ?");

            String l_strBizDate = WEB3DateUtility.formatDate(
                WEB3EquityPTSTradingTimeManagement.getPTSOrderBizDate(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            Object[] l_objWheres =
            {
                ProductTypeEnum.EQUITY,
                OrderTypeEnum.MINI_STOCK_BUY,
                OrderTypeEnum.MINI_STOCK_SELL,
                OrderOpenStatusEnum.OPEN,
                new Long(rangeFrom),
                new Long(rangeTo),
                l_strBizDate,
                new Long(l_market.getMarketId())
            };

            //�L�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����
            List l_lisRecords = new ArrayList();
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                String l_strSort = "account_id asc";

                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    l_strSort,
                    null,
                    l_objWheres);
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

            //�R�j�@@�ڋq�I�u�W�F�N�g�̈ꗗ���쐬����B
            WEB3GentradeMainAccount[] l_mainAccounts = null;
            if (l_lisRecords != null && !l_lisRecords.isEmpty())
            {
                long l_accountId = 0L;
                int l_intSize = l_lisRecords.size();
                List l_lisAccounts = new ArrayList();
                for (int i = 0; i < l_intSize; i++)
                {
                    //�Q�j�Ŏ擾���������P�ʃI�u�W�F�N�g.����ID�̈�ӂȈꗗ���쐬����B
                    EqtypeOrderUnitRow l_orderUnitRow =
                        (EqtypeOrderUnitRow)l_lisRecords.get(i);

                    if (l_accountId != l_orderUnitRow.getAccountId())
                    {
                        l_accountId = l_orderUnitRow.getAccountId();

                        WEB3GentradeMainAccount l_mainAccount = null;
                        try
                        {
                            //�e����ID���ɁA�Y������ڋq�I�u�W�F�N�g���擾���A
                            l_mainAccount =
                                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_accountId);
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

                        l_lisAccounts.add(l_mainAccount);
                    }
                }

                //�ԋp�p�̌ڋq�I�u�W�F�N�g�̈ꗗ�ɒǉ����Ă����B
                l_mainAccounts = new WEB3GentradeMainAccount[l_lisAccounts.size()];
                l_lisAccounts.toArray(l_mainAccounts);
            }

            //�S�j�@@�쐬�����ڋq�I�u�W�F�N�g�̈ꗗ��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_mainAccounts;
        }

        /**
         * (set����J�����_�R���e�L�X�g)<BR>
         * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
         * <BR>
         * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
         * <BR>
         * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = this.�،���ЃR�[�h<BR>
         * �@@����J�����_�R���e�L�X�g.���X�R�[�h =<BR>
         * �@@�@@�i���X�s��ʁEPTS�j�戵����.get�i���X�s��ʁEPTS�j�戵����( )���R�[�����A<BR>
         * �@@�@@�߂�l���X�g��0�Ԗڂ̗v�f�́i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g���<BR>
         * �@@�@@���X�R�[�h���擾����B<BR>
         * �@@�@@[get�i���X�s��ʁEPTS�j�戵����( )�̈����ݒ�]<BR>
         * �@@�@@�@@�،���ЃR�[�h�F�@@this.�،���ЃR�[�h<BR>
         * �@@����J�����_�R���e�L�X�g.�s��R�[�h = this.�s��R�[�h<BR>
         * �@@������ԃR���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h<BR>
         * �@@������ԃR���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
         * <BR>
         * �@@��������t���i�A������t�g�����U�N�V�����͐ݒ�s�v�B<BR>
         * <BR>
         * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
         * �@@�@@�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
         * �ݒ�L�[�F�@@PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
         * <BR>
         * �Q�j�@@��t�����A���t���[�����Z�b�g����B<BR>
         * �@@�|PTS������ԊǗ�.setTimestamp()���R�[������B<BR>
         * @@throws WEB3BaseException
         */
        protected void setTradingClendarContext() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "setTradingClendarContext()";
            log.entering(STR_METHOD_NAME);

            //����J�����_�����p����R���e�L�X�g�𐶐�����B
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();

            //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = this.�،���ЃR�[�h
            l_context.setInstitutionCode(this.institutionCode);

            //����J�����_�R���e�L�X�g.���X�R�[�h =
            //�i���X�s��ʁEPTS�j�戵����.get�i���X�s��ʁEPTS�j�戵����( )���R�[�����A
            //�߂�l���X�g��0�Ԗڂ̗v�f�́i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g���
            //���X�R�[�h���擾����B
            WEB3GentradeBranchMarketPTSDealtCond[] l_BranchMarketPTSDealtConds =
                WEB3GentradeBranchMarketPTSDealtCond.getBranchMarketPTSDealtCond(this.institutionCode);

            BranchMarketPtsDealtCondRow l_branchMarketPtsDealtCondRow =
                (BranchMarketPtsDealtCondRow)l_BranchMarketPTSDealtConds[0].getDataSourceObject();
            l_context.setBranchCode(l_branchMarketPtsDealtCondRow.getBranchCode());

            //����J�����_�R���e�L�X�g.�s��R�[�h = this.�s��R�[�h
            l_context.setMarketCode(this.marketCode);

            //������ԃR���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

            //������ԃR���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
            //�ݒ�L�[�F�@@PTS������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);

            //�Q�j�@@��t�����A���t���[�����Z�b�g����B
            //�@@�|PTS������ԊǗ�.setTimestamp()���R�[������B
            WEB3EquityPTSTradingTimeManagement.setTimestamp();
            log.exiting(STR_METHOD_NAME);
        }

        /**
         * �g�����U�N�V�������������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�iPTS�j�o���I���ʒm�T�[�r�X�jprocess�v�Q�ƁB<BR>
         * <BR>
         * @@return Object
         * @@throws DataCallbackException
         */
        public Object process() throws DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            WEB3GentradeMainAccount[] l_mainAccounts = null;
            try
            {
                //set����J�����_�R���e�L�X�g( )
                this.setTradingClendarContext();

                //get�����Ώیڋq�ꗗ( )
                l_mainAccounts = this.getMainAccounts();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }

            //�擾�����ڋq�I�u�W�F�N�g�����ALoop
            int l_intSize = 0;
            if (l_mainAccounts != null)
            {
                l_intSize = l_mainAccounts.length;
            }

            //�ȉ��̌ڋq�I�u�W�F�N�g������Loop���ŁA�������ɃG���[�i�V�X�e���G���[���j�����������ꍇ�́A
            //���̌ڋq�̏o���I�����ʂ�rollback���A���̌ڋq�́u�o���I�����s�v���s���B
            String l_strStatus = WEB3RunStatusDivDef.DEALED;
            for (int i = 0; i < l_intSize; i++)
            {
                //�iPTS�j�ڋq�P�ʏo���I��TransactionCallback(�ڋq, String)
                //�ڋq : �擾�����ڋq�I�u�W�F�N�g�z��̗v�f
                //�s��R�[�h : this.�s��R�[�h
                WEB3EquityPTSAccountExecEndTransactionCallback l_accountExecEndTransactionCallback =
                    new WEB3EquityPTSAccountExecEndTransactionCallback(
                        l_mainAccounts[i],
                        this.marketCode);

                //doTransaction(�g�����U�N�V�������� : int, TransactionCallback : TransactionCallback)
                //�g�����U�N�V�������� : TX_CREATE_NEW
                //TransactionCallback : ��������(PTS)�ڋq�P�ʏo���I��TransactionCallback�I�u�W�F�N�g
                try
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_accountExecEndTransactionCallback);
                }
                catch (Exception l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    //�ꕔ�̌ڋq�ł��ُ�I�������ꍇ�A"�G���["
                    l_strStatus = WEB3RunStatusDivDef.ERROR;
                }
            }

            //update���s�X�e�[�^�X�敪(���s�X�e�[�^�X�敪 : String)
            try
            {
                this.onlineRunStatus.updateRunStatusDiv(l_strStatus);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * ((PTS)�ڋq�P�ʏo���I��TransactionCallback)<BR>
         * �iPTS�j�ڋq�P�ʏo���I��TransactionCallback�N���X<BR>
         */
        public class WEB3EquityPTSAccountExecEndTransactionCallback
            implements TransactionCallback
        {
            /**
             * (�ڋq)�B<BR>
             * �ڋq�I�u�W�F�N�g�B<BR>
             */
            private WEB3GentradeMainAccount mainAccount;

            /**
             * (�s��R�[�h)<BR>
             * �s��R�[�h<BR>
             */
            private String marketCode;

            /**
             * (�iPTS�j�ڋq�P�ʏo���I��TransactionCallback)<BR>
             * �R���X�g���N�^�B<BR>
             * <BR>
             * ���N���X�̃v���p�e�B�ɁA�����̓����ڂ��Z�b�g����B<BR>
             * <BR>
             * @@param l_mainAccount - (�ڋq)<BR>
             * �ڋq�I�u�W�F�N�g�B<BR>
             * @@param l_strMarketCode - (�s��R�[�h)<BR>
             * �s��R�[�h<BR>
             */
            public WEB3EquityPTSAccountExecEndTransactionCallback(
                WEB3GentradeMainAccount l_mainAccount,
                String l_strMarketCode)
            {
                mainAccount = l_mainAccount;
                marketCode = l_strMarketCode;
            }

            /**
             * (is�o���I�������P��)<BR>
             * �w��̒����P�ʃI�u�W�F�N�g���o���I���Ώۂ��ǂ����𔻒肷��B<BR>
             * <BR>
             * �����̒����P��.���������� < PTS������ԊǗ�.getPTS������( )(*1)�̏ꍇ�́A<BR>
             * �o���I���Ώۂł���Ɣ��肵true��Ԃ��B<BR>
             * ��L�ȊO�̏ꍇ�́Afalse��Ԃ��B<BR>
             * <BR>
             * (*1)�����L�������������܂ł̒���<BR>
             * <BR>
             * @@param l_orderUnit - (�����P��)<BR>
             * �����P�ʃI�u�W�F�N�g�B<BR>
             * @@return boolean
             */
            protected boolean isOrderExecEnd(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
            {
                final String STR_METHOD_NAME = "isOrderExecEnd(EqTypeOrderUnit)";
                log.entering(STR_METHOD_NAME);

                //PTS������ԊǗ�.getPTS������( )
                Date l_datBizDate = WEB3EquityPTSTradingTimeManagement.getPTSOrderBizDate();
                EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

                //�����P��.����������
                Date l_datExpirationDate = l_eqtypeOrderUnitRow.getExpirationDate();

                //�����̒����P��.���������� < PTS������ԊǗ�.getPTS������( )�̏ꍇ
                if (WEB3DateUtility.compareToDay(l_datExpirationDate, l_datBizDate) < 0)
                {
                    //�o���I���Ώۂł���Ɣ��肵true��Ԃ��B
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }

                //��L�ȊO�̏ꍇ�́Afalse��Ԃ��B
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            /**
             * �g�����U�N�V�������������{����B<BR>
             * <BR>
             * �V�[�P���X�}<BR>
             * �u�i�iPTS�j�o���I���ʒm�j�ڋq�P�ʏo���I�����s�v�Q�ƁB<BR>
             * @@throws DataCallbackException
             * @@return Object
             */
            public Object process() throws DataCallbackException
            {
                final String STR_METHOD_NAME = "process()";
                log.entering(STR_METHOD_NAME);

                //lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
                //�،���ЃR�[�h : this.�ڋq.�،���ЃR�[�h
                //���X�R�[�h : this.�ڋq.���X�R�[�h
                //�����R�[�h : this.�ڋq.�����R�[�h
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                MainAccountRow l_mainAccountRow = (MainAccountRow)mainAccount.getDataSourceObject();
                try
                {
                    l_accountManager.lockAccount(
                        l_mainAccountRow.getInstitutionCode(),
                        l_mainAccountRow.getBranchCode(),
                        l_mainAccountRow.getAccountCode());
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new DataCallbackException(
                        l_ex.getMessage(),
                        l_ex);
                }

                //is�M�p�����J��()
                boolean l_blnIsMarginAccountEstablished =
                    mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

                // getSubAccount(�⏕�����^�C�v : SubAccountTypeEnum)
                WEB3GentradeSubAccount l_subAccount = null;
                WEB3GentradeMarket l_market = null;

                try
                {
                    if (l_blnIsMarginAccountEstablished)
                    {
                        //�M�p�����J�݌ڋq�ithis.�ڋq.is�M�p�����J��( )��true�j�̏ꍇ�A"�����M�p��������i�ۏ؋��j"
                        l_subAccount =
                            (WEB3GentradeSubAccount)mainAccount.getSubAccount(
                                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                    }
                    else
                    {
                        //�M�p�������J�݌ڋq�ithis.�ڋq.is�M�p�����J��( )��false�j�̏ꍇ�A"������������i�a����j"
                        l_subAccount =
                            (WEB3GentradeSubAccount)mainAccount.getSubAccount(
                                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    }

                    // get�s��(�،���ЃR�[�h : String, �s��R�[�h : String)
                    //�،���ЃR�[�h : this.�ڋq.�،���ЃR�[�h
                    //�s��R�[�h : this.�s��R�[�h
                    WEB3GentradeFinObjectManager l_finObjectManager =
                        (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

                    l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_mainAccountRow.getInstitutionCode(),
                        this.marketCode);
                }
                catch (NotFoundException l_nfe)
                {
                    WEB3SystemLayerException l_ex =
                        new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_nfe.getMessage(),
                            l_nfe);

                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new DataCallbackException(
                        l_ex.getMessage(),
                        l_ex);
                }

                try
                {
                    //[���o����]
                    // ������� != �i"�����~�j��������"�A"�����~�j��������"�j
                    // �����L����� �� "�I�[�v��"
                    // ������ ���@@PTS������ԊǗ�.getPTS������( )
                    // �s��ID �� �擾�����s��I�u�W�F�N�g.�s��ID
                    String l_strWhere = "order_type not in (?,?) and order_open_status = ?"
                        + " and biz_date < ? and market_id = ?";

                    String l_strBizDate = WEB3DateUtility.formatDate(
                        WEB3EquityPTSTradingTimeManagement.getPTSOrderBizDate(),
                        WEB3GentradeTimeDef.DATE_FORMAT_YMD);

                    Object[] l_objWhere =
                    {
                        OrderTypeEnum.MINI_STOCK_BUY,
                        OrderTypeEnum.MINI_STOCK_SELL,
                        OrderOpenStatusEnum.OPEN,
                        l_strBizDate,
                        new Long(l_market.getMarketId()),
                    };

                    TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                    WEB3EquityPTSOrderManager l_orderManager =
                        (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
                    //get�����P�ʈꗗ(�⏕����, ProductTypeEnum, String, String[], String)
                    //�⏕���� : �擾�����⏕�����I�u�W�F�N�g
                    //�����^�C�v : "����"
                    //�������������� : ���o�����̓V�[�P���X�̃m�[�g�A���J�[�Q��
                    //���������f�[�^�R���e�i : ���o�����̓V�[�P���X�̃m�[�g�A���J�[�Q��
                    //�\�[�g���� : null�i�\�[�g�w��Ȃ��j
                    List l_lisOrderUnits = l_orderManager.getOrderUnits(
                        l_subAccount,
                        ProductTypeEnum.EQUITY,
                        l_strWhere,
                        l_objWhere,
                        null);

                    //�擾���������P�ʃI�u�W�F�N�g����Loop����
                    int l_intOrderUnitsSize = 0;
                    if (l_lisOrderUnits != null)
                    {
                        l_intOrderUnitsSize = l_lisOrderUnits.size();
                    }
                    for (int i = 0; i < l_intOrderUnitsSize; i++)
                    {
                        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_lisOrderUnits.get(i);

                        //�����������A�܂��͈ꕔ���̏ꍇ�̂ݎ��{
                        if (l_orderUnit.isUnexecuted() || l_orderUnit.isPartiallyExecuted())
                        {
                            WEB3EquityExecutedMailSenderService l_execMailSenderService =
                                (WEB3EquityExecutedMailSenderService)Services.getService(
                                    WEB3EquityExecutedMailSenderService.class);

                            //sendMailProcess()
                            l_execMailSenderService.sendMailProcess(l_orderUnit, null, true);

                        }

                        //is�o���I�������P��(�����P��)
                        //�����P�� : �擾���������P�ʃI�u�W�F�N�g�z��̗v�f
                        boolean l_blnIsOrderExecEnd = this.isOrderExecEnd(l_orderUnit);

                        //is�o���I�������P��( )��false�̏ꍇ�A���̒����P�ʂ���������icontinue�j
                        if (!l_blnIsOrderExecEnd)
                        {
                            continue;
                        }

                        //�X�V�C�x���g�C���^�Z�v�^
                        WEB3MarginUpdateEventInterceptor l_interceptor = new WEB3MarginUpdateEventInterceptor();

                        //setThreadLocalPersistenceEventInterceptor(�X�V�C�x���g�C���^�Z�v�^
                        //: EqTypeOrderManagerPersistenceEventInterceptor)
                        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

                        // expireOrder(����ID : long)
                        //����ID :  �擾���������P�ʃI�u�W�F�N�g�z��̗v�f.����ID
                        ProcessingResult l_processingResult =
                            l_orderManager.expireOrder(l_orderUnit.getOrderId());

                        if (l_processingResult.isFailedResult())
                        {
                            log.debug(l_processingResult.getErrorInfo().getErrorMessage());
                            throw new WEB3BaseException(
                                l_processingResult.getErrorInfo(),
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                l_processingResult.getErrorInfo().getErrorMessage());
                        }
                    }

                    //�]�͍Čv�Z()
                    //�⏕���� : �擾�����⏕�����I�u�W�F�N�g
                    WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                        (WEB3TPTradingPowerReCalcService)Services.getService(
                            WEB3TPTradingPowerReCalcService.class);

                    l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new DataCallbackException(
                        l_ex.getMessage(),
                        l_ex);
                }

                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
    }

    /**
     * ((PTS)�I�����C�����s����TransactionCallback)<BR>
     * (PTS)�I�����C�����s����TransactionCallback�N���X�B<BR>
     */
    public class WEB3EquityPTSOnlineRunStatusTransactionCallback
        implements TransactionCallback
    {
        /**
         * (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h<BR>
         */
        private String institutionCode;

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
         * (�iPTS�j�I�����C�����s����TransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * ���N���X�̃v���p�e�B�ɁA�����̓����ڂ��Z�b�g����B<BR>
         * <BR>
         * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h�B<BR>
         * @@param l_lngRangeFrom - (From����ID)<BR>
         * From����ID�B<BR>
         * @@param l_lngRangeTo - (To����ID)<BR>
         * To����ID�B<BR>
         */
        public WEB3EquityPTSOnlineRunStatusTransactionCallback(
            String l_strInstitutionCode,
            long l_lngRangeFrom,
            long l_lngRangeTo)
        {
            institutionCode = l_strInstitutionCode;
            rangeFrom = l_lngRangeFrom;
            rangeTo = l_lngRangeTo;
        }

        /**
         * �g�����U�N�V�������������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�iPTS�j�o���I���ʒm�T�[�r�X�j�o���I���ʒm�v�Q�ƁB<BR>
         * @@throws DataCallbackException
         * @@return Object
         */
        public Object process() throws DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //set������
            // �،���ЃR�[�h�F�@@this.�،���ЃR�[�h
            // �����^�C�v : "����"
            // �敨�^�I�v�V�����敪 : "DEFAULT"  
            // �I�����C���T�[�r�X�敪 : "(PTS)�o���I���ʒm"
            // From����ID�F�@@this.From����ID
            // To����ID�F�@@this.To����ID
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                l_onlineRunStatus = WEB3GentradeOnlineRunStatus.setDealing(
                    this.institutionCode,
                    ProductTypeEnum.EQUITY,
                    WEB3FuturesOptionDivDef.DEFAULT,
                    WEB3OnlineServiceDiv.PTS_ORDER_EXEC_END,
                    this.rangeFrom,
                    this.rangeTo);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }

            //set������()�̖߂�l��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }
    }
}
@
