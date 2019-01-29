head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverSkipServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������J�z�X�L�b�v�����ʒm�T�[�r�XImpl(WEB3EquityOrderCarryOverSkipServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 羐� (���u) �V�K�쐬
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.equity.data.HostEquityCarryoverSkipParams;
import webbroker3.equity.data.HostEquityCarryoverSkipRow;
import webbroker3.equity.message.WEB3EquityCarryOverSkipRequest;
import webbroker3.equity.message.WEB3EquityCarryOverSkipResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipUnitService;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������J�z�X�L�b�v�����ʒm�T�[�r�XImpl�j�B<BR>
 * <BR>
 * ���������J�z�X�L�b�v�����ʒm�T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverSkipServiceImpl
    implements WEB3EquityOrderCarryOverSkipService
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderCarryOverSkipServiceImpl.class);

    /**
     * @@roseuid 40B2D48D01DA
     */
    public WEB3EquityOrderCarryOverSkipServiceImpl()
    {

    }

    /**
     * ���������J�z�X�L�b�v�����ʒm�T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return webbroker3.common.message.WEB3BackResponse<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4056D74F000D
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        WEB3EquityCarryOverSkipRequest l_eqCarryOverSkipRequest = null;
        if (l_request instanceof WEB3EquityCarryOverSkipRequest)
        {
            l_eqCarryOverSkipRequest =
                (WEB3EquityCarryOverSkipRequest)l_request;
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);

        }
        WEB3EquityCarryOverSkipResponse l_eqCarryOverSkipResponse =
            (WEB3EquityCarryOverSkipResponse) 
                l_eqCarryOverSkipRequest.createResponse();

        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_qp.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                new ServiceTransactionCallback());
        }
        catch (DataCallbackException l_ex)
        {
            WEB3BaseException l_wbe = (WEB3BaseException)l_ex.getDetails();
            throw l_wbe;
        }
        catch (DataException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_eqCarryOverSkipResponse;
    }
    
    /**
     * (���������J�z�X�L�b�v�����ʒmTransactionCallback)<BR>
     * ���������J�z�X�L�b�v�����ʒm�������s���g�����U�N�V�����E�R�[���o�b�N�N���X�B<BR>
     */
    private class ServiceTransactionCallback
        implements TransactionCallback
    {

        /**
         * @@roseuid 40B2E6C000B7
         */
        public ServiceTransactionCallback()
        {

        }

        /**
         * (process)<BR>
         * �g�����U�N�V�������������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�����J�z�X�L�b�v�����ʒm�T�[�r�X�jprocess�v�Q�ƁB<BR>
         * @@return WEB3BaseException�i���������ɂ�WEB3BaseException��O�����������ꍇ�j<BR>
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4137CF9C0200
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME =
                "ServiceTransactionCallback.process()";
            log.entering(STR_METHOD_NAME);            
            try
            {
                //1.1get�o���I��( )
                List l_list = getOrderExecEnd();
                //1.2 get�X�L�b�v�����ʒm�L���[
                List l_lstParams = getHostEquityCarryoverSkipParams(l_list);
                int l_intSize = 0;
                if (l_lstParams != null)
                {
                    l_intSize = l_lstParams.size();
                }
                log.debug("l_intSize = " + l_intSize);
                QueryProcessor l_qp = Processors.getDefaultProcessor();
                Map l_mapChanges = new HashMap();
                //1.3. �X�L�b�v�����ʒm�L���[�̌������ALoop
                for (int i = 0; i < l_intSize; i++)
                {                        
                    HostEquityCarryoverSkipParams l_carryoverSkipParams =
                        (HostEquityCarryoverSkipParams)l_lstParams.get(i);
                    try
                    {                        
                        UnitServiceTransactionCallback l_callback =
                            new UnitServiceTransactionCallback(
                                l_carryoverSkipParams, l_list);
                        l_qp.doTransaction(
                            QueryProcessor.TX_CREATE_NEW,
                            l_callback);
                        l_mapChanges.put("status", WEB3StatusDef.DEALT);
                    }
                    catch (DataCallbackException l_dce)
                    {
                        log.debug(STR_METHOD_NAME, (WEB3BaseException)l_dce.getDetails());
                        l_mapChanges.put("status", WEB3StatusDef.DATA_ERROR);
                    }
                    catch (Exception l_ex)
                    {
                        log.debug(STR_METHOD_NAME, l_ex);
                        l_mapChanges.put("status", WEB3StatusDef.DATA_ERROR);
                    }
                    finally
                    {
                        l_qp.doUpdateQuery(
                            l_carryoverSkipParams.getPrimaryKey(),
                            l_mapChanges);
                    }
                }
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        /**
         * (get�o���I��)<BR>
         * �P�j�@@�y�o���I���e�[�u���z����ȉ��̏����ŁA�Y���f�[�^��S�Ď擾����B<BR>
         * <BR>
         * �@@�@@�@@�����o������<BR>
         * �@@�@@�@@�����^�C�v������<BR>
         * <BR>
         * �Q�j�@@�u�o���I��Params�v��List��Ԃ��B<BR>
         * @@return List
         * @@roseuid 4119D5250073
         */
        public List getOrderExecEnd() throws WEB3SystemLayerException  
        {
            final String STR_METHOD_NAME = "getOrderExecEnd";
            StringBuffer l_sbWhere = new StringBuffer();
            //�����^�C�v������
            l_sbWhere.append(" product_type = ? ");
            Object[] l_objWhere = { new Integer(ProductTypeEnum.EQUITY.intValue())};                           
            List l_lisRecords = null;
            QueryProcessor l_QueryProcessor = null;
            try 
            {
                l_QueryProcessor = Processors.getDefaultProcessor();
                l_lisRecords =l_QueryProcessor.doFindAllQuery(
                    OrderexecutionEndRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    null,
                    l_objWhere);
            }
            catch (DataException l_ex) 
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);                              
            } 
            return l_lisRecords;
        }
        
        /**
         *(get�X�L�b�v�����ʒm�L���[)  <BR>
         * �P�j�@@�y���������J�z�X�L�b�v�����ʒm�L���[�e�[�u���z����<BR>
         * �@@�@@�ȉ��̏����ɍ��v����S�f�[�^�� SELECT FOR UPDATE�i�s���b�N�j�Ŏ擾���A<BR>
         * �@@�@@�u�����J�z�X�L�b�v�����ʒm�L���[Params�v�̃��X�g���쐬����B<BR>
         * <BR>
         * �@@�@@�@@�@@�@@�f�[�^�R�[�h��"AI822"(�����J�z�X�L�b�v����)"<BR>
         * �@@�@@���@@�����敪��"������"<BR>
         * �@@�@@���،���ЃR�[�h�����w��B<BR>
         * <BR>
         * �Q�j�@@�P�j�ō쐬�����u�����J�z�X�L�b�v�����ʒm�L���[Params�v��List��Ԃ��B<BR>
         * �@@�@@�@@�Y���f�[�^�Ȃ��̏ꍇ��null��Ԃ��B<BR>
         * @@param l_orderExeEndList- (�o���I��List)<BR>
         * @@return List <BR>
         * @@throws WEB3SystemLayerException
         * @@roseuid 4137CF9C0099
         */
        public List getHostEquityCarryoverSkipParams(List l_orderExeEndList)
            throws WEB3SystemLayerException
        {
            final String STR_METHOD_NAME = "getHostEquityCarryoverSkipParams()";
            log.entering(STR_METHOD_NAME);

            String l_strWhere = "request_code=? and status=?";
            String l_strSort = "institution_code asc";
            String l_strCondition = "for update";
            Object[] l_objWhere = {
                //�f�[�^�R�[�h
                WEB3HostRequestCodeDef.EQUITY_TRANSFE_PRODUCT_NOTICE,
                    WEB3HostStatusDef.NOT_STARTED_PROCESS
            };

            List l_lisCarryoverSkipRows;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisCarryoverSkipRows =
                    l_queryProcessor.doFindAllQuery(
                        HostEquityCarryoverSkipRow.TYPE,
                        l_strWhere,
                        l_strSort,
                        l_strCondition,
                        l_objWhere);
            }
            catch (DataException l_de)
            {
                log.error(STR_METHOD_NAME, l_de);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_de.getMessage(),
                    l_de);
            }
            
            int l_intCnt = l_lisCarryoverSkipRows.size();
            List l_lisCarryoverSkipParams = new Vector();
            for (int i = 0; i < l_intCnt; i++)
            {
                l_lisCarryoverSkipParams.add(
                    new HostEquityCarryoverSkipParams(
                        (HostEquityCarryoverSkipRow)l_lisCarryoverSkipRows.get(i)));
            }
            log.exiting(STR_METHOD_NAME);
            return l_lisCarryoverSkipParams;
        }
    }
    
    /**
     * (���������J�z�X�L�b�v�����ʒm�ꌏ�T�[�r�XTransactionCallback)<BR>
     * ���������J�z�X�L�b�v�����ʈꌏ�m�������s���g�����U�N�V�����E�R�[���o�b�N�N���X�B<BR>
     */
    private class UnitServiceTransactionCallback
        implements TransactionCallback
    {
        /**
         * (���������J�z�X�L�b�v�����ʒm�L���[Params)<BR>
         */
        private HostEquityCarryoverSkipParams carryoverSkipParams;
        
        /**
         * (�o���I��List)<BR>
         */
        private List orderExecEnds;
        
        /**
         * (�R���X�g���N�^)<BR>
         * @@param l_carryoverSkipParams - (���������J�z�X�L�b�v�����ʒm�L���[Params)<BR>
         * @@param l_lisOrderExecEnd - (�o���I��List)<BR>
         */
        public UnitServiceTransactionCallback(
            HostEquityCarryoverSkipParams l_carryoverSkipParams,
            List l_lisOrderExecEnd)
        {
            carryoverSkipParams = l_carryoverSkipParams;
            orderExecEnds = l_lisOrderExecEnd;
        }
        
        /**
         * (process)<BR>
         * ���������J�z�X�L�b�v�����ʒm�ꌏ�T�[�r�X�̒����J�z���ʍX�V���R�[������B<BR>
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);
            
            WEB3EquityOrderCarryOverSkipUnitService l_unitService =
                (WEB3EquityOrderCarryOverSkipUnitService)Services.getService(WEB3EquityOrderCarryOverSkipUnitService.class);
            try
            {
                l_unitService.updateOrderCarryOverResult(carryoverSkipParams, orderExecEnds); 
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
