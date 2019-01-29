head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ւ̐U�֎���T�[�r�XImpl�N���X(WEB3FEqConTransferCancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/23 ���E (���u) �V�K�쐬       
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;
import java.util.Vector;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FEqConTransferDataControlService;
import webbroker3.aio.data.FeqAccountParams;
import webbroker3.aio.data.UwgTransferStatusParams;
import webbroker3.aio.define.WEB3AioFeqConOperationDivDef;
import webbroker3.aio.message.WEB3FEqConTransferCancelCompleteRequest;
import webbroker3.aio.message.WEB3FEqConTransferCancelCompleteResponse;
import webbroker3.aio.message.WEB3FEqConTransferCancelConfirmRequest;
import webbroker3.aio.message.WEB3FEqConTransferCancelConfirmResponse;
import webbroker3.aio.message.WEB3FEqConTransferCancelSelectRequest;
import webbroker3.aio.message.WEB3FEqConTransferCancelSelectResponse;
import webbroker3.aio.message.WEB3FEqConTransferUnit;
import webbroker3.aio.service.delegate.WEB3FEqConTransferCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FeqFirstTransferFlagDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������ւ̐U�֎���T�[�r�XImpl)<BR>
 * �O�������ւ̐U�֎���T�[�r�X�����N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FEqConTransferCancelServiceImpl extends WEB3ClientRequestService 
    implements WEB3FEqConTransferCancelService 
{
    
    /**
     * @@roseuid 42355E710000
     */
    public WEB3FEqConTransferCancelServiceImpl() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferCancelServiceImpl.class);

    
    /**
     * �O�������ւ̐U�֎���������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *   get�I�����()<BR>
     *   validate���()<BR>
     *   submit���()
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3955B02C9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3FEqConTransferCancelSelectRequest)
        {
            l_response = 
                this.getSelectScreen((WEB3FEqConTransferCancelSelectRequest) l_request);

        }
        else if(l_request instanceof WEB3FEqConTransferCancelConfirmRequest)
        {
            l_response = 
                this.validateCancel((WEB3FEqConTransferCancelConfirmRequest) l_request);
            
        }
        else if(l_request instanceof WEB3FEqConTransferCancelCompleteRequest)
        {
            l_response = 
                this.submitCancel((WEB3FEqConTransferCancelCompleteRequest) l_request);
            
        }
        else
        {
            log.debug(
                    "���N�G�X�g�f�[�^��"
                    + " WEB3FEqConTransferCancelSelectRequest "
                    + " �� WEB3FEqConTransferCancelConfirmRequest"
                    + " �� WEB3FEqConTransferCancelCompleteRequest�ȊO�ł���, but is "
                    + l_request.getClass().getName());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�I�����)<BR>
     * �I����ʎ擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������ւ̐U�֎���jget�I����ʁv �Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConTransferCancelSelectResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E395970087
     */
    protected WEB3FEqConTransferCancelSelectResponse getSelectScreen(
        WEB3FEqConTransferCancelSelectRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getSelectScreen(WEB3FEqConTransferCancelSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) get�⏕����(SubAccountTypeEnum)
        //�A�C�e���̒�`
        //�⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j 
        SubAccount l_subAcccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        log.debug("AccountId = " + l_subAcccount.getAccountId());
        log.debug("SubAccountId = " + l_subAcccount.getSubAccountId());
        
        //1.2) validate����(SubAccount)
        //�A�C�e���̒�`
        //�ȉ��̃`�F�b�N���s���B 
        //�@@�|��t���ԃ`�F�b�N 
        //�@@�|�V�X�e����~���`�F�b�N 
        //�@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        
        l_aioOrderManager.validateOrder(l_subAcccount);
        
        //1.3) validate�O���U�։\(SubAccount)
        //�A�C�e���̒�`
        //�O���̐U�֎�����\�ł��邩���`�F�b�N����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        l_aioOrderManager.validateFeqConTransferPossible(l_subAcccount);
        
        WEB3FEqConTransferCancelSelectResponse l_response = null;
        
        try
        {
            //1.4) getDefaultProcessor( )
            //1.5) doFindAllQuery(RowType, String, String, String, Object[], int, int)
            //�A�C�e���̒�`
            //�����P�ʃe�[�u������A����\�Ȓ����P�ʂ��擾����B
            //[����] 
            //������� = 1013�i�O�������U�֒����i�a�������O�����������ցj�j 
            //��n�� > ���ݓ��t 
            //[����] 
            //Row�^�C�v�F �����P��Row.TYPE 
            //Where�F "account_id=? and sub_account_id=? and order_type=? and delivery_date>?" 
            //orderBy�F "received_date_time desc" 
            //condition�F null 
            //���X�g�F �ȉ��̗v�f�̔z�� 
            // �⏕����.getAccountId()�̖߂�l 
            //�⏕����.getSubAccountId()�̖߂�l 
            //1013�i�O�������U�֒����i�a�������O�����������ցj�j 
            //���ݓ��t
            Object[] l_objValue = 
                {
                    l_subAcccount.getAccountId() + "", 
                    l_subAcccount.getSubAccountId() + "", 
                    OrderTypeEnum.TRANSFER_TO_FEQ, 
                    GtlUtils.getSystemTimestamp()
                };
            
            List l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    "account_id = ? and sub_account_id = ? and order_type = ? and delivery_date > ? ",
                    "received_date_time desc",
                    null,
                    l_objValue);
            log.debug("l_lisRows.size() = " + l_lisRows.size());
            
            //1.6)  ArrayList( )
            List l_lisFEqConTransferUnits = new Vector();
            
            //1.7) �擾���������P�ʖ���Loop����
            for(int i = 0; i < l_lisRows.size(); i++)
            {
                AioOrderUnitParams l_aioOrderUnitParams = (AioOrderUnitParams) l_lisRows.get(i);
                
                // test log
                log.debug("�擾���������P��: l_aioOrderUnitParams[" + i + "] = " + l_aioOrderUnitParams);
                
                
                //1.7.1) get�����敪(�����P��Params)
                //�A�C�e���̒�`
                //�����敪���擾����B
                //[����] 
                //�����P��Params�F �����P��Params�I�u�W�F�N�g 
                String l_strOperationDiv = this.getOperationDiv(l_aioOrderUnitParams);
                log.debug("l_strOperationDiv = " + l_strOperationDiv);
                
                //1.7.2) get�����敪()�̖߂�l == �h�U�֒��h or
                //get�����敪()�̖߂�l == �hUWG���ϒ��h �̏ꍇ�A���{
                if(WEB3AioFeqConOperationDivDef.TRANSFERING.equals(l_strOperationDiv)
                    || WEB3AioFeqConOperationDivDef.UWG_TRANSFERING.equals(l_strOperationDiv))
                {
                    //1.7.2.1) �O���U�֖���( )
                    WEB3FEqConTransferUnit l_fEqConTransferUnit = new WEB3FEqConTransferUnit();
                    
                    //1.7.2.2) �v���p�e�B�Z�b�g
                    //(*2)�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
                    //�O���U�֖���.����ID = �����P��.����ID
                    l_fEqConTransferUnit.orderId = l_aioOrderUnitParams.getOrderId() + "";
                        
                    //�O���U�֖���.��t���� = �����P��.�󒍓���
                    l_fEqConTransferUnit.receptionDate = l_aioOrderUnitParams.getReceivedDateTime();
                    
                    //�O���U�֖���.�U�֋��z = �����P��.��������
                    l_fEqConTransferUnit.changeAmt = WEB3StringTypeUtility.formatNumber(l_aioOrderUnitParams.getQuantity());
                    
                    //�O���U�֖���.��n�\��� = �����P��.��n��
                    l_fEqConTransferUnit.deliveryScheduledDate = l_aioOrderUnitParams.getDeliveryDate();
                    
                    //�O���U�֖���.������ = get�����敪()�̖߂�l
                    l_fEqConTransferUnit.transactionStateType = l_strOperationDiv;
                    
                    //�O���U�֖���.����\�t���O = �i�ȉ��̂Ƃ���j
                    //   ������ == �h�U�֒��h �̏ꍇ�Atrue
                    //   ������ != �h�U�֒��h �́Afalse
                    if(WEB3AioFeqConOperationDivDef.TRANSFERING.equals(l_strOperationDiv))
                    {
                        l_fEqConTransferUnit.cancelFlag = true;
                    }
                    else
                    {
                        l_fEqConTransferUnit.cancelFlag = false;
                    }
                    
                    //1.7.2.3) add(arg0 : Object)
                    //[����] 
                    //arg0�F �O�������U�֖��׃I�u�W�F�N�g 
                    l_lisFEqConTransferUnits.add(l_fEqConTransferUnit);

                }
            }
            
            //1.8) toArray( )
            //�A�C�e���̒�`
            //�z����擾����B
            WEB3FEqConTransferUnit[] l_fEqConTransferUnit = 
                new WEB3FEqConTransferUnit[l_lisFEqConTransferUnits.size()];
            
            l_lisFEqConTransferUnits.toArray(l_fEqConTransferUnit);
            
            //1.9) createResponse( )
            //�A�C�e���̒�`
            //���X�|���X�f�[�^�𐶐�����B
            l_response = (WEB3FEqConTransferCancelSelectResponse)l_request.createResponse();
            
            //1.10)�v���p�e�B�Z�b�g
            //(*3)�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
            //���X�|���X.�O�������U�ֈꗗ = �O�������U
            l_response.fstkTransferList = l_fEqConTransferUnit;
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
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate���)<BR>
     * �U�ւ̎���m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������ւ̐U�֎���jvalidate����v �Q��
     * ------------------------------------------------
     * 1.6 get�����敪(�����P��Params)
     *  get�����敪�̖߂�l != �h�U�֒��h�̏ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01955<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConTransferCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3959700A6
     */
    protected WEB3FEqConTransferCancelConfirmResponse validateCancel(
        WEB3FEqConTransferCancelConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "validateCancel(WEB3FEqConTransferCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) get�⏕����(SubAccountTypeEnum)
        //�A�C�e���̒�`
        //�⏕�����I�u�W�F�N�g���擾����B
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3) validate����(SubAccount)
        //�A�C�e���̒�`
        //�ȉ��̃`�F�b�N���s���B 
        //�@@�|��t���ԃ`�F�b�N 
        //�@@�|�V�X�e����~���`�F�b�N 
        //�@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.4) validate�O���U�։\(SubAccount)
        //�A�C�e���̒�`
        //�O���̐U�֎�����\�ł��邩���`�F�b�N����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        l_aioOrderManager.validateFeqConTransferPossible(l_subAccount);

        //1.5) getOrderUnits(SubAccount, FilterQueryParamsSpec, PaginationQueryParamsSpec, SortKeySpec)
        //�A�C�e���̒�`
        //����ΏۂƂȂ钍���P�ʂ��擾����B
        //���z��̐擪�̗v�f���擾����B 
        //[����] 
        //����ID�F ���N�G�X�g�f�[�^.����ID 
        OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        
        if (l_orderUnits.length <= 0)
        {
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80005,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        OrderUnit l_orderUnit = l_orderUnits[0];
        
        //1.6 get�����敪(�����P��Params)
        //�A�C�e���̒�`
        //�����敪���擾����B
        //[����] 
        //�����P��Params�F �����P��.getDataSourceObject()�̖߂�l 
        AioOrderUnitParams l_aioOrderUnitParams = 
            (AioOrderUnitParams)l_orderUnit.getDataSourceObject();
        
        String l_strOperationDiv = this.getOperationDiv(l_aioOrderUnitParams);
        
        // get�����敪�̖߂�l != �h�U�֒��h
        //�̏ꍇ�A��O���X���[����B
        if(!WEB3AioFeqConOperationDivDef.TRANSFERING.equals(l_strOperationDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01955,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪[" + l_strOperationDiv + "] != �U�֒�");
        }
        
        //����U�֒�����
        //����`�F�b�N
        //1.7)  validate�O���U�֎��(�⏕����, String)
        //�A�C�e���̒�`
        //�Y������������\�����`�F�b�N����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //���ʃR�[�h�F �����P��.���ʃR�[�h 
        l_aioOrderManager.validateFeqConTransferCancel(
            l_subAccount,
            l_aioOrderUnitParams.getOrderRequestNumber());

        //1.8) get�O�������ڋq(String, String, String)
        //�A�C�e���̒�`
        //�O�������ڋq�I�u�W�F�N�g���擾����B
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F �⏕����.get����X.getBranchCode() 
        //�ڋq�R�[�h�F �⏕����.getMainAccount().getAccountCode() 
         
        WEB3FEqConTransferDataControlService l_fEqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);

        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = l_fEqConTransferDataControlService.getFeqAccountByAccountCode(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,      
                l_ex.getMessage(),
                l_ex);
        }
        //1.9) createResponse( )
        WEB3FEqConTransferCancelConfirmResponse l_response = 
            (WEB3FEqConTransferCancelConfirmResponse)l_request.createResponse();
        
        //1.10) �v���p�e�B�Z�b�g
        //(*)�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //���X�|���X.��������ԍ� = �O�������ڋq.�O�����������ԍ�
        l_response.tradeAccountCode = l_feqAccountParams.getFeqAccountCode();
        
        //���X�|���X.��t���� = �����P��.�󒍓���
        l_response.receptionDate = l_aioOrderUnitParams.getReceivedDateTime();
        
        //���X�|���X.�U�֋��z = �����P��.��������
        l_response.changeAmt = WEB3StringTypeUtility.formatNumber(l_aioOrderUnitParams.getQuantity());
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit���)<BR>
     * �U�ւ̎�������������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O�������ւ̐U�֎���jsubmit����v �Q��
     * ------------------------------------------------
     * 1.6 get�����敪(�����P��Params)
     *  get�����敪�̖߂�l != �h�U�֒��h�̏ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01955<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3FEqConTransferCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3959700C6
     */
    protected WEB3FEqConTransferCancelCompleteResponse submitCancel(
        WEB3FEqConTransferCancelCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "submitCancel(WEB3FEqConTransferCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1)  validate( )
        //�A�C�e���̒�`
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();
        
        //1.2) get�⏕����(SubAccountTypeEnum)
        //�A�C�e���̒�`
        //�⏕�����I�u�W�F�N�g���擾����B
        //[����] 
        //�⏕�����^�C�v�F 1�i������������i�a����j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3) validate����(SubAccount)
        //�A�C�e���̒�`
        //�ȉ��̃`�F�b�N���s���B 
        //�@@�|��t���ԃ`�F�b�N 
        //�@@�|�V�X�e����~���`�F�b�N 
        //�@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.4) validate�O���U�։\(SubAccount)
        //�A�C�e���̒�`
        //�O���̐U�֎�����\�ł��邩���`�F�b�N����B
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l
        l_aioOrderManager.validateFeqConTransferPossible(l_subAccount);

        //1.5) getOrderUnits(long)
        //�A�C�e���̒�`
        //�������钍���P�ʂ��擾����B
        //���z��̐擪�̗v�f���擾����B
        //[����] 
        //����ID�F ���N�G�X�g�f�[�^.����ID 
        OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        
        if (l_orderUnits.length <= 0)
        {
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80005,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        OrderUnit l_orderUnit = l_orderUnits[0];
        
        //1.6 get�����敪(�����P��Params)
        //�A�C�e���̒�`
        //�����敪���擾����B
        //[����] 
        //�����P��Params�F �����P��.getDataSourceObject()�̖߂�l 
        AioOrderUnitParams l_aioOrderUnitParams = (AioOrderUnitParams)l_orderUnit.getDataSourceObject();
        
        log.debug("submitCancel :: l_aioOrderUnitParams = " + l_aioOrderUnitParams);
        
        String l_strOperationDiv = this.getOperationDiv(l_aioOrderUnitParams);
        
        // get�����敪�̖߂�l != �h�U�֒��h
        //�̏ꍇ�A��O���X���[����B
        if(!WEB3AioFeqConOperationDivDef.TRANSFERING.equals(l_strOperationDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01955,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪[" + l_strOperationDiv + "] != �U�֒�");
        }
        
        //����U�֒�����
        //����`�F�b�N
        //1.7)  validate�O���U�֎��(�⏕����, String)
        //�A�C�e���̒�`
        //�Y������������\�����`�F�b�N����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //���ʃR�[�h�F �����P��.���ʃR�[�h 
        l_aioOrderManager.validateFeqConTransferCancel(
            l_subAccount,
            l_aioOrderUnitParams.getOrderRequestNumber());

        //1.8)  get�㗝���͎�( )
        //�A�C�e���̒�`
        //�㗝���͎҂��擾����B
        Trader l_trader = this.getTrader();

        //1.9) validate����p�X���[�h(Trader, SubAccount, String)
        //�A�C�e���̒�`
        //�Ïؔԍ��̃`�F�b�N���s���B
        //[����] 
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� 
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        OrderValidationResult l_orderValidationResult = 
            l_gentradeOrderValidator.validateTradingPassword(
                l_trader, 
                l_subAccount, 
                l_request.password);
        
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�`�F�b�N�G���[�̏ꍇ�͂��O���X���[����" + 
                l_orderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.10) getUWG�U�֏�(String, String, String)
        //�A�C�e���̒�`
        //������ꂽ�����Ɋ֘A����UWG�U�֏󋵂̃��R�[�h���擾����B
        //[����] 
        //�،���ЃR�[�h�F �⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F �⏕����.get����X.getBranchCode() 
        //���ʃR�[�h�F �����P��.���ʃR�[�h 
        WEB3FEqConTransferDataControlService l_fEqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgTransferStatusParams l_uwgTransferStatusParams = 
            l_fEqConTransferDataControlService.getUwgTransferStatus(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_aioOrderUnitParams.getOrderRequestNumber());
        log.debug("l_uwgTransferStatusParams.getInstitutionCode() = " + l_uwgTransferStatusParams.getInstitutionCode());
        log.debug("l_uwgTransferStatusParams.getBranchCode() = " + l_uwgTransferStatusParams.getBranchCode());
        log.debug("l_uwgTransferStatusParams.getAccountCode() = " + l_uwgTransferStatusParams.getAccountCode());
        log.debug("l_uwgTransferStatusParams.getOrderRequestNumber() = " + l_uwgTransferStatusParams.getOrderRequestNumber());
        log.debug("l_uwgTransferStatusParams.getMrgTrnOrderRequestNumber() = " + l_uwgTransferStatusParams.getMrgTrnOrderRequestNumber());
        
        //1.11) �U�֒������(String, String, String, String, String)
        //�A�C�e���̒�`
        //�U�֒����̎�����s���B
        //[����] 
        //�،���ЃR�[�h�F UWG�U�֏�Params.�،���ЃR�[�h 
        //���X�R�[�h�F UWG�U�֏�Params.���X�R�[�h 
        //�ڋq�R�[�h�F UWG�U�֏�Params.�ڋq�R�[�h 
        //���ʃR�[�h�F UWG�U�֏�Params.���ʃR�[�h 
        //�M�p�U�֗p���ʃR�[�h�F UWG�U�֏�Params.�M�p�U�֗p���ʃR�[�h 
        l_aioOrderManager.transferOrderCancel(
            l_uwgTransferStatusParams.getInstitutionCode(),
            l_uwgTransferStatusParams.getBranchCode(),
            l_uwgTransferStatusParams.getAccountCode(),
            l_uwgTransferStatusParams.getOrderRequestNumber(),
            l_uwgTransferStatusParams.getMrgTrnOrderRequestNumber());
        
        //1.12) updateUWG�U�֏�(UWG�U�֏�Params, String)
        //�A�C�e���̒�`
        //UWG�U�֏󋵂��X�V����B
        //[����] 
        //UWG�U�֏�Params�F getUWG�U�֏�()�̖߂�l 
        //�X�V��U�֏󋵋敪�F 3�i����j
        log.debug("submitCancel :: l_uwgTransferStatusParams = " + l_uwgTransferStatusParams);
        l_fEqConTransferDataControlService.updateUwgTransferStatus(
            l_uwgTransferStatusParams,
            WEB3TransferStatusDivDef.CANCEL);
       
        //1.13) �]�͍Čv�Z(�⏕���� : �⏕����)
        //�A�C�e���̒�`
        //�]�͂̍Čv�Z���s���B
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3GentradeSubAccount l_gentradeSubAccount = 
            (WEB3GentradeSubAccount)l_subAccount;
        
        l_service.reCalcTradingPower(l_gentradeSubAccount);
        
        //1.14) getOrderUnits(SubAccount, FilterQueryParamsSpec, PaginationQueryParamsSpec, SortKeySpec)
        //�A�C�e���̒�`
        //������ꂽ�����P�ʂ��擾����B
        //���z��̐擪�̗v�f���擾����B
        //[����] 
        //����ID�F ���N�G�X�g�f�[�^.����ID 
        OrderUnit[] l_updatedOrderUnits = l_aioOrderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        OrderUnit l_updatedOrderUnit = l_updatedOrderUnits[0];
        
        AioOrderUnitParams l_updateAioOrderUnitParams = (AioOrderUnitParams)l_updatedOrderUnit.getDataSourceObject();
        log.debug("submitCancel :: l_updateAioOrderUnitParams = " + l_updateAioOrderUnitParams);
        
        //1.15) createResponse( )
        WEB3FEqConTransferCancelCompleteResponse l_response =
            (WEB3FEqConTransferCancelCompleteResponse)l_request.createResponse();
        
        //1.16) �v���p�e�B�Z�b�g
        //(*)�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
        //���X�|���X.������� = �����P��.�X�V���t
        l_response.cancelDate = l_updateAioOrderUnitParams.getLastUpdatedTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����敪)<BR>
     * �U�֖��ׂɃZ�b�g���鏈���敪���擾����B<BR>
     * <BR>
     * �P�j�����P�ʂɑΉ�����UWG�U�֏󋵂��擾����B<BR>
     * <BR>
     *    �O���U�֘A�g�f�[�^����T�[�r�XImpl.getUWG�U�֏�()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    �،���ЃR�[�h�F ����.�����P��Params.���XID����擾�����،���ЃR�[�h<BR>
     *    ���X�R�[�h�F ����.�����P��Params.���XID����擾�������X�R�[�h<BR>
     *    ���ʃR�[�h�F ����.�����P��Params.���ʃR�[�h<BR>
     * <BR>
     * �Q�j�����敪�𔻒肷��B<BR>
     * <BR>
     * �Q�|�P�jUWG�U�֏�.�U�֏󋵋敪 == 0�i���ϒ��j and<BR>
     *          UWG�U�֏�.����M�敪 == 0�i�����M�j �̏ꍇ<BR>
     * <BR>
     *    0�i�U�֒��j ��ԋp����B<BR>
     * <BR>
     * �Q�|�Q�jUWG�U�֏�.�U�֏󋵋敪 == 0�i���ϒ��j and<BR>
     *          UWG�U�֏�.����M�敪 == 1�i���M�ρj �̏ꍇ<BR>
     * <BR>
     *    1�iUWG���ϒ��j ��ԋp����B<BR>
     * <BR>
     * �Q�|�R�jUWG�U�֏�.�U�֏󋵋敪 == 1�i���ϊ����j �̏ꍇ<BR>
     * <BR>
     *    2�iUWG���ϊ����j ��ԋp����B<BR>
     * <BR>
     * �Q�|�S�jUWG�U�֏�.�U�֏󋵋敪 == 2�i���σG���[�j �̏ꍇ<BR>
     * <BR>
     *    3�iUWG���σG���[�j ��ԋp����B<BR>
     * <BR>
     * �Q�|�T�jUWG�U�֏�.�U�֏󋵋敪 == 3�i����j �̏ꍇ<BR>
     * <BR>
     *    4�i����ρj ��ԋp����B
     * @@param l_orderUnitParams - �����P��Params�I�u�W�F�N�g
     * 
     * @@return String
     * @@roseuid 41ECE2D900AB
     */
    protected String getOperationDiv(AioOrderUnitParams l_orderUnitParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getOperationDiv(AioOrderUnitParams l_orderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�����P�ʂɑΉ�����UWG�U�֏󋵂��擾����B
        //�O���U�֘A�g�f�[�^����T�[�r�XImpl.getUWG�U�֏�()���R�[������B
        //[����] 
        //�،���ЃR�[�h�F ����.�����P��Params.���XID����擾�����،���ЃR�[�h 
        //���X�R�[�h�F ����.�����P��Params.���XID����擾�������X�R�[�h 
        //���ʃR�[�h�F ����.�����P��Params.���ʃR�[�h 
        WEB3FEqConTransferDataControlService l_fEqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Branch l_branch = null;
        UwgTransferStatusParams l_uwgTransferStatusParams = null;
        try
        {
            l_branch = l_finApp.getAccountManager().getBranch(l_orderUnitParams.getBranchId());
            
            log.debug("getOperationDiv :: l_orderUnitParams = " + l_orderUnitParams);
            l_uwgTransferStatusParams = 
                l_fEqConTransferDataControlService.getUwgTransferStatus(
                    l_branch.getInstitution().getInstitutionCode(),
                    l_branch.getBranchCode(),
                    l_orderUnitParams.getOrderRequestNumber());
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,      
                l_ex.getMessage(),
                l_ex);
        }
        log.debug("l_uwgTransferStatusParams = " + l_uwgTransferStatusParams);
        
        //�Q�j�����敪�𔻒肷��B 
        //�Q�|�P�jUWG�U�֏�.�U�֏󋵋敪 == 0�i���ϒ��j and 
        //UWG�U�֏�.����M�敪 == 0�i�����M�j �̏ꍇ
        //0�i�U�֒��j ��ԋp����B 
        if(WEB3TransferStatusDivDef.PROCESSING.equals(l_uwgTransferStatusParams.getTransferStatusDiv())
            && WEB3SendRcvDivDef.NOT_SEND.equals(l_uwgTransferStatusParams.getSendRcvDiv()))
        {
            log.debug("************entry 2-1)");
            return WEB3AioFeqConOperationDivDef.TRANSFERING;
        }
        
        //�Q�|�Q�jUWG�U�֏�.�U�֏󋵋敪 == 0�i���ϒ��j and 
        //UWG�U�֏�.����M�敪 == 1�i���M�ρj �̏ꍇ
        //1�iUWG���ϒ��j ��ԋp����B 
        if(WEB3TransferStatusDivDef.PROCESSING.equals(l_uwgTransferStatusParams.getTransferStatusDiv())
            && WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_uwgTransferStatusParams.getSendRcvDiv()))
        {
            log.debug("************entry 2-2)");
            return WEB3AioFeqConOperationDivDef.UWG_TRANSFERING;
        }
        
        //�Q�|�R�jUWG�U�֏�.�U�֏󋵋敪 == 1�i���ϊ����j �̏ꍇ
        //2�iUWG���ϊ����j ��ԋp����B 
        if(WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(l_uwgTransferStatusParams.getTransferStatusDiv()))
        {
            return WEB3AioFeqConOperationDivDef.UWG_TRANSFER_COMPLETE;
        }

        //�Q�|�S�jUWG�U�֏�.�U�֏󋵋敪 == 2�i���σG���[�j �̏ꍇ
        //3�iUWG���σG���[�j ��ԋp����B 
        if(WEB3TransferStatusDivDef.PROCESS_ERROR.equals(l_uwgTransferStatusParams.getTransferStatusDiv()))
        {
            return WEB3AioFeqConOperationDivDef.UWG_TRANSFER_ERROE;
        }

        
        //�Q�|�T�jUWG�U�֏�.�U�֏󋵋敪 == 3�i����j �̏ꍇ 
        //4�i����ρj ��ԋp����B 
        if(WEB3TransferStatusDivDef.CANCEL.equals(l_uwgTransferStatusParams.getTransferStatusDiv()))
        {
            return WEB3AioFeqConOperationDivDef.CANCELED;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
