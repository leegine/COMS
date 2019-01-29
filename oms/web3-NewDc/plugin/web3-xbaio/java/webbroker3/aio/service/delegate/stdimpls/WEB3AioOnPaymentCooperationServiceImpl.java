head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOnPaymentCooperationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���A�g�T�[�r�XImpl(WEB3AioOnPaymentCooperationServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AioOnPaymentCooperationRequest;
import webbroker3.aio.service.delegate.WEB3AioOnPaymentCooperationService;
import webbroker3.aio.service.delegate.WEB3AioOnPaymentCooperationUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���A�g�T�[�r�XImpl) <BR>
 * �o���A�g�T�[�r�X�����N���X <BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationServiceImpl implements WEB3AioOnPaymentCooperationService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOnPaymentCooperationServiceImpl.class);  
    
    /**
     * @@roseuid 41E77F4A01D4
     */
    public WEB3AioOnPaymentCooperationServiceImpl()
    {
    }

    /**
     * �o���A�g�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�o���A�g�jexecute�v �Q�� <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@roseuid 41C7B2080071
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AioOnPaymentCooperationRequest l_onPaymentCooperationRequest = 
            (WEB3AioOnPaymentCooperationRequest)l_request;        
        
        //1.1 validate( )(�o���A�g���N�G�X�g::validate)
        //���N�G�X�g�̃`�F�b�N���s���B
        l_onPaymentCooperationRequest.validate();
        
        //1.2 InstitutionImpl(�،���ЃR�[�h : long)
        //�،���ЃI�u�W�F�N�g���擾����B 
        //[����] 
        //�،���ЃR�[�h �F ���N�G�X�g.�،���ЃR�[�h
        AccountManager l_accountManager = GtlUtils.getAccountManager();
       
        Institution l_institution = null;
        try
        {                
            l_institution = 
                l_accountManager.getInstitution(
                    l_onPaymentCooperationRequest.institutionCode);
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
        
        //1.3 getBranches( )
        //�،���Ђɏ������邷�ׂĂ̕��X��Branch�I�u�W�F�N�g�̔z��Ƃ��Ď擾����B
        Branch[] l_branchs = l_institution.getBranches();
        int l_intLength = 0;
        
        if (l_branchs != null || l_branchs.length != 0)
        {
            l_intLength = l_branchs.length;
            log.debug("Branch�I�u�W�F�N�g�̔z��.length := " + l_intLength);
        }
        
        //1.4 ArrayList( )
        //�����P�ʃI�u�W�F�N�g�p��ArrayList( )���쐬����B
        List l_lisAioOrderUnit = new ArrayList();
        
        //1.5 getBranches( )�̗v�f����Loop�������s���B
        for (int i = 0; i < l_intLength; i++)
        {
            //1.5.1 is���M��񎞏o����������( )
            //���X���o���A�g���s�����ǂ����̔��ʂ��s���B 

            //�߂�l���htrue�h�̏ꍇ�́A�o���A�g�̏������{�B 
            //�߂�l���hfalse�h�̏ꍇ�́A�o���A�g�̏����ΏۊO�Ƃ���B
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_branchs[i];
            
            boolean l_blnIsPaymentOrderCreate = l_branch.isPaymentOrderCreate();
            
            //�߂�l���hfalse�h�̏ꍇ�́A�ȍ~�̏������X�L�b�v����B
            if (!l_blnIsPaymentOrderCreate)
            {
                log.debug("is���M��񎞏o����������( )�̖߂�l���hfalse�h�̏ꍇ");
                continue;
            }
            
            //1.5.2 get�o���A�g������(String)(�o���A�g�T�[�r�XImpl::get�o���A�g������)
            //���������擾����B 

            log.debug("l_branch.getBranchCode() = " + l_branch.getBranchCode());
            //[����] 
            //���X�R�[�h �F ���X[index].getBranchCode
            String l_strOnPaymentCoopBizDate = 
                this.getOnPaymentCooperationBizDate(l_branch.getBranchCode());            
            
            //1.5.3 �i*1�j�����P�ʃe�[�u���̓Ǎ���
            //�i*1�j
            //�ȉ��̏����Œ����P�ʃe�[�u�����f�[�^���擾����B

            //[��������]
            // ���XID �F ���X[index].getBranchId( )
            // ������� �F 1001�i�o���j
            // �����J�e�S�� �F 9�i���o���j
            // ������� �F1�i��t�ρj
            // �����^�C�v �F 5�i�����j
            // �����L����� �F 1�i�I�[�v���j
            // �����敪 �F 1�i�I�[�v���j
            // ������ �F get�o���A�g������( )�̖߂�l
            // �o���\���敪 �Fnull or �hmf�i�����M���j�h

            //[�\�[�g����]
            // ����ID�@@����
            try
            {                
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();         
                StringBuffer l_strWhere = new StringBuffer();
                l_strWhere.append(" branch_id = ?");     
                l_strWhere.append(" and order_type = ?");
                l_strWhere.append(" and order_categ = ?");
                l_strWhere.append(" and order_status = ?");    
                l_strWhere.append(" and product_type = ?");    
                l_strWhere.append(" and order_open_status = ?");   
                l_strWhere.append(" and expiration_status = ?");   
                l_strWhere.append(" and biz_date = ?");   
                l_strWhere.append(" and (payment_application_div is null");   
                l_strWhere.append(" or payment_application_div = ? )");
                
                String l_strOrderBy = " account_id "; 
                Object[] l_objMutualFrgncalWhere =
                {
                    new Long(l_branch.getBranchId()), 
                    OrderTypeEnum.CASH_OUT,
                    OrderCategEnum.CASH_IN_OUT,
                    OrderStatusEnum.ACCEPTED,
                    ProductTypeEnum.CASH,
                    OrderOpenStatusEnum.OPEN,
                    OrderExpirationStatusEnum.OPEN,
                    l_strOnPaymentCoopBizDate, 
                    WEB3AioPaymentApplicationDivDef.MF
                };
                List l_lisAioOrderUnitRows = 
                    l_queryProcessor.doFindAllQuery(
                        AioOrderUnitRow.TYPE,
                        l_strWhere.toString(),
                        l_strOrderBy,
                        null,
                        l_objMutualFrgncalWhere);
                
                int l_intSize = 0;
                if (l_lisAioOrderUnitRows != null && !l_lisAioOrderUnitRows.isEmpty())
                {
                    l_intSize = l_lisAioOrderUnitRows.size();
                    log.debug("l_lisAioOrderUnitRows.size() = " + l_intSize);
                }
                else
                {
                    log.debug("�Y�����钍���f�[�^�͂���܂���B�y���X�R�[�h�z�F" + l_branch.getBranchCode());
                    continue;
                }
                
                //1.5.4 �ϐ�.����ID = null ���Z�b�g
                String l_strAccountId = null;
                String l_strPaymentApplicationDiv = null;
                
                //1.5.5 �擾���������P�ʂ��Ƃ̃��[�v����
                //���[�v�����́A�����P�ʗv�f���Ɂh1�h����������
                for (int j = 0; j < l_intSize + 1; j++)
                {
                    String l_strAccountIdLoop = null;
                    String l_strPaymentApplicationDivLoop = null;
                    AioOrderUnit l_aioOrderUnit = null;
                    
                    if (l_intSize != j)
                    {
                        AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisAioOrderUnitRows.get(j);
                        l_strAccountIdLoop = l_aioOrderUnitRow.getAccountId() + "";
                        l_strPaymentApplicationDivLoop = l_aioOrderUnitRow.getPaymentApplicationDiv();
                        
                        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                        WEB3AioOrderManager l_aioOrderManager = 
                            (WEB3AioOrderManager) l_finApp.getTradingModule(
                                ProductTypeEnum.AIO).getOrderManager();                    
                    
                        OrderUnit[] l_orderUnits = 
                            l_aioOrderManager.getOrderUnits(l_aioOrderUnitRow.getOrderId());
                        l_aioOrderUnit = (AioOrderUnit) l_orderUnits[0];  
                    }
                    
                    //1.5.5.1 �i*2-1�j����t���[
                    //�i*2-1�j �ϐ�.����ID = null�̏ꍇ
                    if (l_strAccountId == null)
                    {
                        log.debug("�ϐ�.����ID = null�̏ꍇ");
                        //1.5.5.1.1 �����P��[index].����ID��ϐ�.����ID�ɃZ�b�g
                        l_strAccountId = l_strAccountIdLoop;
                        l_strPaymentApplicationDiv = l_strPaymentApplicationDivLoop;
                        
                        //1.5.5.1.2 add(�����P�ʃI�u�W�F�N�g[index] : Object)
                        //�����P�ʃ��X�g�ɓ����ڋqID�̒����P�ʃI�u�W�F�N�g��ǉ�����B 
      
                        //[����] 
                        //�����P�ʃI�u�W�F�N�g[index]                        
                        l_lisAioOrderUnit.add(l_aioOrderUnit);
                        
                        //1.5.5.1.3 �ȍ~�̏������X�L�b�v����B
                        continue;
                    }
                    
                    //1.5.5.2 �i*2-2�j����t���[
                    //�i*2-2�j�ϐ�.����ID �� �����P��[index].����ID�̏ꍇ
                    else if (!l_strAccountId.equals(l_strAccountIdLoop))
                    {
                        log.debug("�ϐ�.����ID �� �����P��[index].����ID�̏ꍇ");
                        //1.5.5.2.1 ArrayList( )�̗v�f�� > �h1�h�̏ꍇ
                        //  or
                        //ArrayList( )�̗v�f�� == �h1�h�̏ꍇ����
                        //�����P��.�o���\���敪 == �hmf�i���M�j�h�̏ꍇ
                        if (l_lisAioOrderUnit.size() > 1 || 
                            l_lisAioOrderUnit.size() == 1 && 
                            WEB3AioPaymentApplicationDivDef.MF.equals(l_strPaymentApplicationDiv))
                        {
                            log.debug("ArrayList( )�̗v�f�� > �h1�h�̏ꍇ " +
                                "or ArrayList( )�̗v�f�� == �h1�h�̏ꍇ���� " +
                                "�����P��.�o���\���敪 == �hmf�i���M�j�h�̏ꍇ");
                            
                            //1.5.5.2.1.1 toArray( )
                            //�����P�ʃI�u�W�F�N�g�̔z���ԋp����B
                            AioOrderUnit[] l_aioOrderUnits = new AioOrderUnit[l_lisAioOrderUnit.size()];
                            l_lisAioOrderUnit.toArray(l_aioOrderUnits);
                            
                            //1.5.5.2.1.2 execute(AioOrderUnit[])
                            //�o���A�g�������s���B 
                            //[����] 
                            //�����P�ʃI�u�W�F�N�g[ ] �F �����P�ʃI�u�W�F�N�g�̔z�� 

                            //�V�[�P���X�} 
                            //�u(�o���A�g)�o���A�g�v�Q��
                            WEB3AioOnPaymentCooperationUnitService l_onPaymentCoopUnitService =
                                (WEB3AioOnPaymentCooperationUnitService) Services.getService(
                                    WEB3AioOnPaymentCooperationUnitService.class);
                            
                            l_onPaymentCoopUnitService.execute(l_aioOrderUnits);                            
                        }
                        
                        //1.5.5.2.2  �����P��[index].����ID��ϐ�.����ID�ɃZ�b�g
                        l_strAccountId = l_strAccountIdLoop;
                        l_strPaymentApplicationDiv = l_strPaymentApplicationDivLoop;
                        
                        //1.5.5.2.3 clear( )
                        //���̒����P�ʃI�u�W�F�N�g�p�i�قȂ�ڋqID�̒����p�j�ɃN���A����B
                        l_lisAioOrderUnit.clear();
                        
                        //1.5.5.2.4 add(�����P�ʃI�u�W�F�N�g[index] : Object)
                        //�����P�ʃ��X�g�ɐV�����ڋqID�̒����P�ʂ�ǉ�����B 

                        //[����] 
                        //�����P�ʃI�u�W�F�N�g[index]
                        l_lisAioOrderUnit.add(l_aioOrderUnit);
                    }
                    
                    //1.5.5.3 �i*2-3�j����t���[
                    //�i*2-3�j�ϐ�.����ID == �����P��[index].����ID�̏ꍇ
                    else if (l_strAccountId.equals(l_strAccountIdLoop))
                    {
                        log.debug("�ϐ�.����ID == �����P��[index].����ID�̏ꍇ");
                        
                        //1.5.5.3.1 add(�����P�ʃI�u�W�F�N�g[index] : Object)
                        //�����P�ʃ��X�g�ɐV�����ڋqID�̒����P�ʂ�ǉ�����B 

                        //[����] 
                        //�����P�ʃI�u�W�F�N�g[index]
                        l_lisAioOrderUnit.add(l_aioOrderUnit);
                    }
                }
                
                //1.5.6 clear( )
                //���̒����P�ʃI�u�W�F�N�g�p�i�قȂ镔�XID�̒����p�j�ɃN���A����B
                l_lisAioOrderUnit.clear();                
            }        
            catch (DataException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //1.6 createResponse( )
        WEB3BackResponse l_response = l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }

    /**
     * (get�o���A�g������) <BR>
     * ���������擾����B <BR>
     * <BR>
     * �P�j ����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|�����̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR> 
     * <BR>
     *  �@@����J�����_�R���e�L�X�g.���X�R�[�h = �����̕��X�R�[�h  <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ� <BR>
     *      ������ԃR���e�L�X�g���Z�b�g����B<BR>  
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B  <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �R�j ������ԊǗ�.get������()���R�[�� <BR>
     * <BR>
     * �S�j ������ԊǗ�.get������()�̖߂�l�̓��t�������hyyyyMMdd�h�`���� <BR>
     *          ������ŕԋp����B <BR>
     * <BR>
     * @@param l_strBranchCode - ���X�R�[�h
     * @@return WEB3FXTransToFXConfirmResponse
     * @@roseuid 41C7B2080090
     */
    protected String getOnPaymentCooperationBizDate(String l_strBranchCode)       
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "getOnPaymentCooperationBizDate(String l_strBranchCode)";
        log.entering(l_strMethodName);
        
        //�P�j ����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B 
        //�@@�|�����̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B  

        //�@@����J�����_�R���e�L�X�g.���X�R�[�h = �����̕��X�R�[�h         
       
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        log.debug("l_context = " + l_context);

        l_context.setBranchCode(l_strBranchCode);        
        
        //�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B  
        //  �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
        //�Q�j�@@��t�����A���t���[�����Z�b�g����B  
        //�@@�|������ԊǗ�.setTimestamp()���R�[������B        
        WEB3GentradeTradingTimeManagement.setTimestamp();
       
        //�R�j ������ԊǗ�.get������()���R�[�� 
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //�S�j ������ԊǗ�.get������()�̖߂�l�̓��t�������hyyyyMMdd�h�`���̕�����ŕԋp����B
        String l_strOnPaymentBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        
        log.debug("�o���A�g������ = " + l_strOnPaymentBizDate);
        log.exiting(l_strMethodName);
        return l_strOnPaymentBizDate;
    }
}@
