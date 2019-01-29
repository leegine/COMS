head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AysnIfoChangeCancelOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           :�敨OP���������t�T�[�r�X�����N���X(WEB3AysnIfoChangeCancelOrderAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : �����ŗ����F
                   2004/06/22   ���Ō� (Sinocom) �V�K�쐬
              001: 2004/08/14 ���Ō� �Ή� �y�����w���I�v�V�����z�\�[�X�R�[�h�`�F�b�N�w�E����(JP)200408
                   �񓯊��Ή��ŗ����F
                   2005/03/22 ���u���@@�񓯊����s�Ή��i�V�K�N���X�j
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.ifo.data.HostFotypeOrderAcceptRow;
import webbroker3.ifo.message.WEB3IfoChangeCancelAcceptRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�񓯊��Ή��敨OP���������t�T�[�r�X�����N���X�j�B
 * @@author  : ���u���i���{���u�j
 * @@version : 1.0
 */
public class WEB3AysnIfoChangeCancelOrderAcceptServiceImpl
    implements Runnable
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AysnIfoChangeCancelOrderAcceptServiceImpl.class);

    /**
     * �敨OP���������t���N�G�X�g
     */
    private WEB3IfoChangeCancelAcceptRequest request;


    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3AysnIfoChangeCancelOrderAcceptServiceImpl(WEB3IfoChangeCancelAcceptRequest
        l_request)
    {
        this.request = l_request;
    }

    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AysnIfoChangeCancelOrderAcceptServiceImpl�Frun()";
        log.entering(STR_METHOD_NAME);

        WEB3IfoChangeCancelAcceptRequest l_inRequest = (WEB3IfoChangeCancelAcceptRequest)request;

        try
        {
            try
            {
                //�N�G���v���Z�b�T�̃C���X�^���X���擾����
                //�X���[ DataNetworkException, DataFindException
                QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
                //�敨OP������tTransactionCallback�I�u�W�F�N�g�𐶐�
                WEB3IfoChangeCancelOrderAcceptTransactionCallback l_ifoOrderAcceptTransactionCallback =
                    new WEB3IfoChangeCancelOrderAcceptTransactionCallback();

                //set���ʃR�[�h�v���t�B�N�X�ꗗ()
                l_ifoOrderAcceptTransactionCallback.setOrderRequestNumberPrefixGroup(
                    l_inRequest.orderRequestNumberPrefixGroup);

                l_QueryProcessor.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    l_ifoOrderAcceptTransactionCallback);
            }
            catch (DataQueryException l_dqex)
            {
                //DB�A�N�Z�X�����s�̏ꍇ
                log.error(getClass().getName() + STR_METHOD_NAME, l_dqex);
                //��O���X���[����
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME);
            }
            catch (DataNetworkException l_dnex)
            {
                //DB�A�N�Z�X�����s�̏ꍇ
                log.error(getClass().getName() + STR_METHOD_NAME, l_dnex);
                //��O���X���[����
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }

        //�X���b�h�J��
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(
                request.threadNo.longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�敨OP���������tTransactionCallback)<BR>
     * �g�����U�N�V�������������{��������N���X<BR>
     */
    public class WEB3IfoChangeCancelOrderAcceptTransactionCallback
        implements TransactionCallback
    {

        /**
         * (�敨OP���������tTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^<BR>
         * @@return WEB3IfoChangeCancelOrderAcceptTransactionCallback
         * (�_���r���[WEB3IfoChangeCancelOrderAcceptServiceImpl.WEB3IfoChangeCancelOrderAcceptTransactionCallback
         * @@roseuid 408384C000D8
         */
        public WEB3IfoChangeCancelOrderAcceptTransactionCallback()
        {

        }

        /**
         * ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * (set�����̎��ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * �����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B<BR>
         * @@params String[] - ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
         */
        public void setOrderRequestNumberPrefixGroup(String[]
            l_orderRequestNumberPrefixGroup)
        {
            orderRequestNumberPrefixGroup = l_orderRequestNumberPrefixGroup;
        }

        /**
         * (get�����̎��ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * ���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B<BR>
         * @@return String[]<BR>
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return orderRequestNumberPrefixGroup;
        }


        /**
         * ����������t���������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�敨OP���������t�jprocess�v�Q�ƁB<BR>
         * <BR>
         * �P�j �����Ώۃ��R�[�h�擾<BR>
         * ������t�L���[�e�[�u�����A�s���b�N�iselect for update�j�ɂēǂݍ��ށB<BR>
         * <BR>
         * �@@[��������]<BR>
         * �@@�敨OP������t�L���[�e�[�u��.�f�[�^�R�[�h ==<BR>
         *    �h�I�v�V�������������t�h�iEI80B�j<BR>
         *   �܂��́A�h�敨���������t�h�iEI80D�j<BR>
         * �@@�敨OP������t�L���[�e�[�u��.�����敪 == �h�������h<BR>
         * <BR>
         * �ȍ~�̏����́A�������ʂ̐敨OP������t�L���[�e�[�u��<BR>
         * �̊e�s�ɑ΂��Ď��{����B<BR>
         * <BR>
         * �Q�j �C���^�Z�v�^�Z�b�g<BR>
         * �敨OP���������t�C���^�Z�v�^�I�u�W�F�N�g�𐶐����A<BR>
         * setThreadLocalPersistenceEventInterceptor()�ɂāA<BR>
         * �����}�l�[�W����ThreadLocal�ɃZ�b�g����B<BR>
         * <BR>
         * [�R���X�g���N�^�̈���]<BR>
         * �G���[�R�[�h�F�@@�敨OP������t�L���[�e�[�u��.�G���[���b�Z�[�W<BR>
         * <BR>
         * �R�j �L���[�ɊY�����钍���h�c���擾<BR>
         * �����}�l�[�W��.get�����P��()���R�[�����A<BR>
         * �����ΏۃL���[�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�،���ЃR�[�h�@@�F�敨OP������t�L���[�e�[�u��.�،���ЃR�[�h<BR>
         * �@@���X�R�[�h�@@�F�敨OP������t�L���[�e�[�u��.���X�R�[�h<BR>
         * �@@���i�^�C�v�@@�F�h�敨�I�v�V�����h<BR>
         * �@@���ʃR�[�h�@@�F�敨OP������t�L���[�e�[�u��.���ʃR�[�h<BR>
         * <BR>
         * �S�j �R�[���o�b�N�T�[�r�X�i�����X�V�����j���s<BR>
         * �����ΏۃL���[�̓��e�ƒ����P��.������Ԃ𔻒肵�A<BR>
         * �ȉ��̏��������{����B<BR>
         * <BR>
         * �E�i�敨OP������t�L���[�e�[�u��.������t���� == "������t����" &&<BR>
         * �@@�����P��.������� == ��t�ρi�ύX�����j<BR>
         * OrderStatusEnum.MODIFY_ACCEPTED�j�̏ꍇ<BR>
         * �@@DefaultChangeOrderSentMarketResponseMessage�𐶐�����B<BR>
         * <BR>
         * �@@MarketResponseReceiverCallbackService�N���X���擾���A<BR>
         * �@@process���\�b�h���R�[������B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�i���������p�����[�^�I�u�W�F�N�g�j<BR>
         * <BR>
         * �E�i�敨OP������t�L���[�e�[�u��.������t���� == "�G���[" &&<BR>
         * �@@�����P��.������� == ��t�ρi�ύX�����j<BR>
         * OrderStatusEnum.MODIFY_ACCEPTED�j�̏ꍇ<BR>
         * �@@DefaultChangeOrderRejectMarketResponseMessage�𐶐�����B<BR>
         * <BR>
         * �@@MarketResponseReceiverCallbackService�N���X���擾���A<BR>
         * �@@process���\�b�h���R�[������B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�i���������p�����[�^�I�u�W�F�N�g�j<BR>
         * <BR>
         * �E�i�敨OP������t�L���[�e�[�u��.������t���� == "������t����"<BR>
         * &&�@@�����P��.������� == ��t�ρi��������j<BR>
         * OrderStatusEnum.CANCEL_ACCEPTED�j�̏ꍇ<BR>
         * �@@DefaultCancelOrderSentMarketResponseMessage�𐶐�����B<BR>
         * <BR>
         * �@@MarketResponseReceiverCallbackService�N���X���擾���A<BR>
         * �@@process���\�b�h���R�[������B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�i���������p�����[�^�I�u�W�F�N�g�j<BR>
         * <BR>
         * �E�i�敨OP������t�L���[�e�[�u��.������t���� == "�G���[" <BR>
         *   && �����P��.������� == ��t�ρi��������j<BR>
         * OrderStatusEnum.CANCEL_ACCEPTED�j�̏ꍇ<BR>
         * �@@DefaultCancelOrderRejectMarketResponseMessage<BR>
         *   �𐶐�����B<BR>
         * <BR>
         * �@@MarketResponseReceiverCallbackService�N���X���擾���A<BR>
         * �@@process���\�b�h���R�[������B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�i���������p�����[�^�I�u�W�F�N�g�j<BR>
         * <BR>
         * �E�i��L�ȊO�j<BR>
         * �@@�������Ȃ��B<BR>
         * �@@���d���ǉz���̃P�[�X�Ȃ̂ŁA�����X�V���s��Ȃ��B<BR>
         * <BR>
         * �T�j �L���[�e�[�u���ɏ����ς��X�V<BR>
         * �ȉ��̒ʂ�A�����ΏۃL���[���R�[�h���X�V����B<BR>
         * <BR>
         * �@@[�X�V���e]<BR>
         * �@@�i�e�s���̏����ŗ�O�����������ꍇ�j<BR>
         * �@@�敨OP������t�L���[�e�[�u��.�����敪 == �h�G���[�h<BR>
         * <BR>
         * �@@�i�ȊO�̏ꍇ�j<BR>
         * �@@�敨OP������t�L���[�e�[�u��.�����敪 == "������"<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 408384C000D7
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "execute(l_request)";
            log.entering(STR_METHOD_NAME);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //[��������]
            //�敨OP������t�L���[�e�[�u��.�f�[�^�R�[�h == �h�I�v�V����������t�h�iEI80B�j�܂��́A�h�敨������t�h�iEI80D�j
            //�敨OP������t�L���[�e�[�u��.�����敪 == �h�������h
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" ( request_code = ? "); //�f�[�^�R�[�h == �h�I�v�V����������t�h
            l_sbWhere.append(" or request_code = ? )"); //�f�[�^�R�[�h ==�h�敨������t�h
            l_sbWhere.append(" and status = ? "); //�����敪 == �h�������h

            int l_intPrefixGroupLength = 0;
            if (orderRequestNumberPrefixGroup != null)
            {
                l_intPrefixGroupLength = orderRequestNumberPrefixGroup.length;
            }
            if (l_intPrefixGroupLength > 0)
            {
                l_sbWhere.append(" and (");
                for (int i = 0; i < l_intPrefixGroupLength; i++)
                {
                    if (i > 0)
                    {
                        l_sbWhere.append(" or ");
                    }
                    l_sbWhere.append("order_request_number like ?");
                }
                l_sbWhere.append(")");
            }

            Object[] l_objIfoOrderUnitWhere = new Object[l_intPrefixGroupLength + 3];
            l_objIfoOrderUnitWhere[0] = WEB3HostRequestCodeDef.OPTION_ORDER_CHANGE_CANCEL_ACCEPT;
            l_objIfoOrderUnitWhere[1] = WEB3HostRequestCodeDef.FUTURES_ORDER_CHANGE_CANCEL_ACCEPT;
            l_objIfoOrderUnitWhere[2] = WEB3StatusDef.NOT_DEAL;

            for (int i = 0; i < l_intPrefixGroupLength; i++)
            {
                l_objIfoOrderUnitWhere[i + 3] = orderRequestNumberPrefixGroup[i] + "%";
            }

            //�X���[ DataNetworkException, DataFindException
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            //������t�L���[�e�[�u���̌��ʃ��X�g
            List l_lisSearchResult = null;

            //�X���[ DataQueryExeption,DataNetworkException,
            //doFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[])
            l_lisSearchResult =
                l_QueryProcessor.doFindAllQuery(
                    HostFotypeOrderAcceptRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    null,
                    l_objIfoOrderUnitWhere);

            //�敨OP������t�L���[�e�[�u��ROW�I�u�W�F�N�g
            HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams = null;


            //OP�����}�l�[�W���I�u�W�F�N�g���擾����
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
                (WEB3OptionOrderManagerImpl)l_finApp
                    .getTradingModule(ProductTypeEnum.IFO)
                    .getOrderManager();

            MarketAdapter l_marketAdapter = l_finApp.getTradingModule(ProductTypeEnum.IFO).getMarketAdapter();
            IfoMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
                (IfoMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();

            //��t���ʁi������t�j�I�u�W�F�N�g�𐶐�����
            DefaultChangeOrderSentMarketResponseMessage l_changeOrderSentMarketResponseMessage =
                null;
            //��t���ʁi�����G���[�j�I�u�W�F�N�g�𐶐�����
            DefaultChangeOrderRejectedMarketResponseMessage l_changeOrderRejectedMarketResponseMessage =
                null;

            //��t���ʁi�����t�j�I�u�W�F�N�g�𐶐�����
            DefaultCancelOrderSentMarketResponseMessage l_cancelOrderSentMarketResponseMessage =
                null;
            //��t���ʁi����G���[�j�I�u�W�F�N�g�𐶐�����
            DefaultCancelOrderRejectedMarketResponseMessage l_cancelOrderRejectedMarketResponseMessage =
                null;

            //�����P�ʃI�u�W�F�N�g
            OrderUnit l_orderUnit = null;

            int i = 0;
            long l_lngLen = 0;
            if (l_lisSearchResult != null)
            {
                l_lngLen = l_lisSearchResult.size();
            }

            log.debug("l_intLen = " + l_lngLen);

            for (i = 0; i < l_lngLen; i++)
            {
                l_hostFotypeOrderAcceptParams =
                    new HostFotypeOrderAcceptParams((HostFotypeOrderAcceptRow)l_lisSearchResult.get(i));
                try
                {
                    // TransactionCallback����
                    WEB3IfoChangeCancelOrderAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3IfoChangeCancelOrderAcceptNormalTransactionCallback(
                        l_hostFotypeOrderAcceptParams);

                    // doTransaction()
                    l_QueryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

                }
                catch (Exception l_exp)
                {
                    //�����ΏۃL���[UPDATE�@@(�G���[��)
                    if (l_exp instanceof WEB3BaseRuntimeException)
                    {
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_exp;
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80076.equals(l_wre.getErrorInfo()))
                        {
                            log.debug("�������b�N���s�F");
                            continue;
                        }
                    }

                    //�G���[������ȊO�̏ꍇ�@@(=>�G���[)
                    log.debug("�ꌏ�����ɂăG���[�����F");
                    Map l_mapChanges = new HashMap();
                    l_mapChanges.put("status", WEB3StatusDef.DATA_ERROR);
                    l_mapChanges.put("last_updated_timestamp",GtlUtils.getSystemTimestamp());
                    l_QueryProcessor.doUpdateQuery(l_hostFotypeOrderAcceptParams.getPrimaryKey(), l_mapChanges);
                }
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

}
@
