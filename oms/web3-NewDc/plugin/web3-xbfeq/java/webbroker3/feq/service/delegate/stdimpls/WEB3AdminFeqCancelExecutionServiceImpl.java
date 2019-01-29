head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCancelExecutionServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������o��������T�[�r�XImpl(WEB3AdminFeqCancelExecutionServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �A�C��(���u) �V�K�쐬
                 : 2005/08/01 ��O��(���u) ���r���[       
Revesion History : 2008/10/02 ����(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.469
Revesion History : 2009/08/03 ���g(���u �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.508
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqFinTransactionManager;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionInputRequest;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionInputResponse;
import webbroker3.feq.message.WEB3FeqOrderAndExecutionUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqCancelExecutionService;
import webbroker3.feq.service.delegate.WEB3FeqCommonMessageCreatedService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAndExecutionUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO�������o��������T�[�r�XImpl)<BR>
 * �Ǘ��ҊO�������o��������T�[�r�X�����N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqCancelExecutionServiceImpl 
    implements WEB3AdminFeqCancelExecutionService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqCancelExecutionServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F10222
     */
    public WEB3AdminFeqCancelExecutionServiceImpl() 
    {
     
    }
    
    /**
     * �O�������o����������������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɑΉ����郁�\�b�h���R�[������B<BR>
     * <BR>
     * �|get���͉��()<BR>
     * �|validate���()<BR>
     * �|submit���()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42942ECB03C1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqCancelExecutionInputRequest)
        {
            //get���͉��()
            l_response = 
                this.getInputScreen((WEB3AdminFeqCancelExecutionInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqCancelExecutionConfirmRequest)
        {
            //validate���()
            l_response = 
                this.validateCancel((WEB3AdminFeqCancelExecutionConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqCancelExecutionCompleteRequest)
        {
            //submit���()
            l_response =
                this.submitCancel((WEB3AdminFeqCancelExecutionCompleteRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�o��������jget���͉�ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCancelExecutionInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42942EBE0334
     */
    public WEB3AdminFeqCancelExecutionInputResponse getInputScreen(
        WEB3AdminFeqCancelExecutionInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminFeqCancelExecutionInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException
        
        //1.3 createResponse( )
        WEB3AdminFeqCancelExecutionInputResponse l_response = 
            (WEB3AdminFeqCancelExecutionInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate���)<BR>
     * �o��������m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�o��������jvalidate����v �Q�ƁB<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR> 
     * �o��������v(�o��������jvalidate���)<BR>
     * �@@�@@:  1.6 �o���I�������ς݂̏ꍇ�iis�o���I��() == true�j�A<BR> 
     * ��O���X���[����B<BR> 
     * �@@�@@�o���I�������ς݂̏ꍇ�iis�o���I��() == true�j�A<BR> 
     * ��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02164<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCancelExecutionConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 42942EBE0336
     */
    public WEB3AdminFeqCancelExecutionConfirmResponse validateCancel(
        WEB3AdminFeqCancelExecutionConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateCancel(WEB3AdminFeqCancelExecutionConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException

        //get�،���ЃR�[�h
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get�^�p�R�[�h(�،���ЃR�[�h : String, �^�p�R�[�h : String)
        //�،���ЃR�[�h�F get�،���ЃR�[�h�i�j�̖߂�l
        //�^�p�R�[�h�F���N�G�X�g�f�[�^.�^�p�R�[�h
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService)Services.getService(
                WEB3FeqOrderEmpCodeGettingService.class);
        String l_strEmpCode =
            l_feqOrderEmpCodeGettingService.getEmpCode(l_strInstitutionCode, l_request.managementCode);
        //1.4 get�����P��By�^�p�R�[�h(Date, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinApp�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModule�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "�O�����������}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        Date l_datBizDate = l_request.orderBizDate;
        if (l_datBizDate == null)
        {
            TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
            l_datBizDate = WEB3DateUtility.toDay(l_tradingSystem.getSystemTimestamp());
        }
        WEB3FeqOrderUnit l_orderUnit = 
            (WEB3FeqOrderUnit)l_orderManager.getOrderUnitByOrderEmpCode(
                l_datBizDate, 
                l_strEmpCode);//WEB3BaseException
        if (l_orderUnit == null)
        {
            String l_strMessage = "get�����P��By�^�p�R�[�h(" 
                + l_datBizDate 
                + ", " 
                + l_request.managementCode 
                + ")��null�B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.5 is�o���I��( )
        boolean l_blnExecEnd = l_orderUnit.isExecEnd();
        
        //1.6 �o���I�������ς݂̏ꍇ�iis�o���I��() == true�j�A��O���X���[����
        if (l_blnExecEnd)
        {
            //1.6.1 �i��O�j
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02164,
                this.getClass().getName() + STR_METHOD_NAME,
                "�o���I�������ς݂Ȃ̂ŁA�o��������s�ł��B");
        }
        
        // ��菈�����̏ꍇ�A��O���X���[����B
        // �i�����P��.get����ԋ敪( ) == "3�F��菈�����i�ꕔ�����j" or �h4�F��菈�����i�S�������j�h�j
        if (l_orderUnit.getExecStatusDiv().equals(WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE) 
            || l_orderUnit.getExecStatusDiv().equals(WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03134,
                this.getClass().getName() + STR_METHOD_NAME,
                "��菈�����̏ꍇ�A�o��������s�ł��B");
        }
        
        //1.7 get�L�����By���ԍ�(long, int)
        int l_intExecNo = Integer.parseInt(l_request.execNo);
        WEB3FeqOrderExecution l_orderExec = 
            (WEB3FeqOrderExecution)l_orderManager.getValidExecByExecNo(
                l_orderUnit.getOrderUnitId(), 
                l_intExecNo);//WEB3BaseException
        
        //1.8 get�g�����U�N�V����(FeqOrderExecution)
        WEB3FeqFinTransactionManager l_finTransactionManager = 
            (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
        if (l_finTransactionManager == null)
        {
            String l_strMessage = "�O������TransactionManager�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        FeqFinTransactionParams l_feqFinTranParam = l_finTransactionManager.getTransaction(l_orderExec);//WEB3BaseException
            
        //1.9 �O�����������͏��( )
        WEB3FeqOrderAndExecutionUnit l_feqOrderAndExecUnit = new WEB3FeqOrderAndExecutionUnit();

        //1.10 create�O�����������͏��(...)
        WEB3FeqCommonMessageCreatedService l_service = 
            new WEB3FeqCommonMessageCreatedServiceImpl();
        l_service.createFeqOrderAndExecutionUnit(
            l_feqOrderAndExecUnit,
            l_orderUnit,
            l_orderExec,
            l_feqFinTranParam);
        
        //1.11 createResponse()
        WEB3AdminFeqCancelExecutionConfirmResponse l_response = 
            (WEB3AdminFeqCancelExecutionConfirmResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���X�|���X��null�ł��B");
        }    

        //1.12 �v���p�e�B�Z�b�g
        l_response.orderAndExecutionUnit = l_feqOrderAndExecUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit���)<BR>
     * �o������������������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�o��������jsubmit����v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCancelExecutionCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 42942EBE0338
     */
    public WEB3AdminFeqCancelExecutionCompleteResponse submitCancel(
        WEB3AdminFeqCancelExecutionCompleteRequest l_request)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitCancel(WEB3AdminFeqCancelExecutionCompleteRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException
        
        //1.4 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException

        //1.5 update�����(�O���o���ʒm�L���[Params, long)
        WEB3FeqOrderAndExecutionUpdateService l_feqOrderAndExecutionUpdateService = 
            (WEB3FeqOrderAndExecutionUpdateService )Services.getService(WEB3FeqOrderAndExecutionUpdateService.class);
        long l_lngExecId = Long.parseLong(l_request.execId); 
        l_feqOrderAndExecutionUpdateService.updateExecuteCancel(null, l_lngExecId); 
        
        //1.6 getOrderExecution(arg0 : long)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinApp�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "�O������TradingModule�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "�O�����������}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderExecution l_feqOrderExecution = null;
        try
        {
            l_feqOrderExecution = 
                (WEB3FeqOrderExecution)l_orderManager.getOrderExecution(l_lngExecId);//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�O��������肪���݂��Ȃ��B[���ID = " + l_lngExecId + " ]";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
        if (l_feqOrderExecution == null)
        {
            String l_strMessage = "�O��������肪���݂��Ȃ��B[���ID = " + l_lngExecId + " ]";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.7 update�g�����U�N�V����(long, boolean)
        WEB3FeqPositionManager l_feqPositionManager = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
        if (l_feqPositionManager == null)
        {
            String l_strMessage = "�O�������|�W�V�����}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        l_feqPositionManager.updateTransaction(l_feqOrderExecution.getOrderUnitId(), true);
        
        //1.8 update�T�Z��n���(�O�����������P��, Date)
        WEB3FeqOrderUnit l_feqOrderUnit = null;
        try
        {
            l_feqOrderUnit = (WEB3FeqOrderUnit) l_orderManager.getOrderUnit(l_feqOrderExecution.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�O�����������P�ʂ����݂��Ȃ��B[�����P��ID = " 
                + l_feqOrderExecution.getOrderUnitId() 
                + " ]";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
        if (l_feqOrderUnit == null)
        {
            String l_strMessage = "�O�����������P�ʂ����݂��Ȃ��B[�����P��ID = " 
                + l_feqOrderExecution.getOrderUnitId() 
                + " ]";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        Date l_datExecDate = WEB3DateUtility.toDay(l_feqOrderExecution.getExecutionTimestamp());
        l_orderManager.updateEstimatedPrice(l_feqOrderUnit, l_datExecDate);
         
        //1.9 �]�͍Čv�Z(�⏕���� : �⏕����)
        WEB3TPTradingPowerReCalcService l_web3TPTradingPowerService =
            (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
        l_web3TPTradingPowerService.reCalcTradingPower(l_feqOrderUnit.getSubAccount());//WEB3SystemLayerException

        //1.10 createResponse
        WEB3AdminFeqCancelExecutionCompleteResponse l_response = 
            (WEB3AdminFeqCancelExecutionCompleteResponse)l_request.createResponse();
        
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���X�|���X��null�ł��B");
        }    

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
