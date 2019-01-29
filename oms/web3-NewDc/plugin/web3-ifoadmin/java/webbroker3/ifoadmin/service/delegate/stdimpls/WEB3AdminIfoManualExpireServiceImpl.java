head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoManualExpireServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl(WEB3AdminIfoManualExpireServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30�@@�Ӑ�(���u) �V�K�쐬
Revision History : 2007/07/09�@@�Ј���(���u) �d�l�ύX���f��No.002
*/

package webbroker3.ifoadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.comm.client.CommunicationException;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.ServerConnector;
import com.fitechlabs.xtrade.kernel.comm.client.ServerException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.db.ServerConfigRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DaemonTriggerTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3ServerUrlAccessorDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifoadmin.WEB3AdminIfoDataManager;
import webbroker3.ifoadmin.message.WEB3AdminIfoLapseTargetOrderCondition;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusResponse;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoManualExpireService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;



/**
 * (�Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�敨OP�蓮�����T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �Ӑ�(���u)
 * @@version 1.0
 */

public class WEB3AdminIfoManualExpireServiceImpl extends WEB3ClientRequestService implements WEB3AdminIfoManualExpireService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminIfoManualExpireServiceImpl.class);
    
    /**
     * ServerAccessor�I�u�W�F�N�g <BR>
     * <BR>
     * �����������e�T�[�o�ɐU�蕪����B<BR>
     * ��������s���ɃZ�b�g�����B<BR>
     */
    private ServerAccessor accessor;
    
    /**
     * (�Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoManualExpireServiceImpl
     * @@roseuid 446932950058
     */
    public WEB3AdminIfoManualExpireServiceImpl() 
    {
     
    }
    
    /**
     * �敨OP�蓮�����������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�敨OP�蓮�������̓��N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.get���͉��()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�敨OP�蓮�����m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.validate�蓮����()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�敨OP�蓮���������N�����N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.run�蓮����()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�敨OP�蓮���������X�e�[�^�X�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.validate�����X�e�[�^�X()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4469317D0107
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
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

        WEB3GenResponse l_response = null;
        
        //�Ǘ��ҁE�敨OP�蓮�������̓��N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminIfoManualLapseInputRequest)
        {
            //this.get���͉��()���R�[������B
            l_response = this.getInputScreen((WEB3AdminIfoManualLapseInputRequest)l_request);
        }
        
        //�Ǘ��ҁE�敨OP�蓮�����m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminIfoManualLapseConfirmRequest)
        {
            //this.validate�蓮����()���R�[������B
            l_response = this.validateManualExpire((WEB3AdminIfoManualLapseConfirmRequest)l_request);
        }
        
        //�Ǘ��ҁE�敨OP�蓮���������N�����N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminIfoManualLapseRunRequest)
        {
            //this.run�蓮����()���R�[������B
            l_response = this.runManualExpire((WEB3AdminIfoManualLapseRunRequest)l_request);
        }
        
        //�Ǘ��ҁE�敨OP�蓮���������X�e�[�^�X�m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminIfoManualLapseStatusRequest)
        {
            //this.validate�����X�e�[�^�X()���R�[������B
            l_response = this.validateStatus((WEB3AdminIfoManualLapseStatusRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * �敨OP�蓮�������͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��Ґ敨OP�蓮�����T�[�r�X�jget���͉�ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�敨OP�蓮�������̓��N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321E03A6
     */
    protected WEB3AdminIfoManualLapseInputResponse getInputScreen(WEB3AdminIfoManualLapseInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminIfoManualLapseInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        // validate( )
        l_request.validate();
        
        // getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.MANUAL_EXPIRE, true);
        
        // get�،����( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_admin.getInstitution();
        
        // validate�����J�z������(�،����,String[])
        //����敪�ꗗ
        String[] l_strTradingTypeList = null;
        this.validateCarryOvering(l_institution,l_strTradingTypeList);
        
        // get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        // get�i���X�w���ʁj�戵����(�،���ЃR�[�h : String)
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexDealtConds = 
            WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(l_strInstitutionCode);
        
        // createResponse( )
        WEB3AdminIfoManualLapseInputResponse l_response = 
            (WEB3AdminIfoManualLapseInputResponse)l_request.createResponse();
        
        // (*)�v���p�e�B�Z�b�g
        //�w����ʈꗗ = get�i���X�w���ʁj�戵�����̖߂�l 
        //unique�Ȏw���i=�����Y�����R�[�h�j�݂̂̔z����Z�b�g
        Vector l_vecTargetProductCode = new Vector();
        for (int i = 0; i < l_branchIndexDealtConds.length; i++) 
        {
            WEB3GentradeBranchIndexDealtCond l_branchIndexDealtCond = l_branchIndexDealtConds[i];
            if (!l_vecTargetProductCode.contains(l_branchIndexDealtCond.getUnderlyingProductCode())) 
            {
                l_vecTargetProductCode.add(l_branchIndexDealtCond.getUnderlyingProductCode());
            }
        }
        
        String[] l_strTargetProductCodeList = new String[l_vecTargetProductCode.size()];
        for (int i = 0; i < l_vecTargetProductCode.size(); i++) 
        {
            l_strTargetProductCodeList[i] = (String)l_vecTargetProductCode.get(i);
        }
        
        l_response.targetProductList = l_strTargetProductCodeList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�蓮����)<BR>
     * �敨OP�蓮�����m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��Ґ敨OP�蓮�����T�[�r�X�jvalidate�蓮�����v�Q��<BR>
     * ===================================================<BR>
     * �@@�V�[�P���X�} :�i�Ǘ��Ґ敨OP�蓮�����T�[�r�X�jvalidate�蓮����<BR>
     * �@@��̈ʒu    : 1.5 get�蓮�����Ώے����P�ʈꗗ()<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@null���ԋp���ꂽ�ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u�Y�����������݂��܂���B�v�̗�O���X���[����B<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag : BUSINESS_ERROR_02086<BR>
     * ===================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�敨OP�蓮�����m�F���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321E03B6
     */
    protected WEB3AdminIfoManualLapseConfirmResponse validateManualExpire(WEB3AdminIfoManualLapseConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateManualExpire(WEB3AdminIfoManualLapseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        // validate( )
        l_request.validate();
        
        // getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
       
        // validate�蓮�����\(�Ǘ���, WEB3GenRequest)
        this.validateManualExpirePossibility(l_admin, l_request);
 
        // get�،����( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_admin.getInstitution();

        // get����(�،����, String, String, String, String, String)
        WEB3IfoProductImpl l_ifoProductImpl = WEB3AdminIfoDataManager.getProduct(l_institution ,
            l_request.ifoLapseTargetOrderCondition.fuOpDiv ,
            l_request.ifoLapseTargetOrderCondition.targetProductCode ,
            l_request.ifoLapseTargetOrderCondition.delivaryMonth ,
            l_request.ifoLapseTargetOrderCondition.strikePrice ,
            l_request.ifoLapseTargetOrderCondition.opProductType
            );
        //get����(�،����, String, String, String, String, String)
        
        String l_strProductId = null;
        String l_productCode = null;
        if (l_ifoProductImpl != null) 
        {
            l_strProductId = Long.toString(l_ifoProductImpl.getProductId());
            l_productCode = l_ifoProductImpl.getProductCode();
        }
        
        // get�蓮�����Ώے����P�ʈꗗ(�،����, String[], String, String[], String[], String[], String, Long, Long)
        IfoOrderUnitRow[] l_rows = 
            WEB3AdminIfoDataManager.getManualExpireOrderUnits(
                l_institution,  
                l_request.ifoLapseTargetOrderCondition.branchCode, 
                l_strProductId,
                l_request.ifoLapseTargetOrderCondition.tradingTypeList,
                l_request.ifoLapseTargetOrderCondition.accountCode,
                null,
                null);
        
        //�@@null���ԋp���ꂽ�ꍇ�A
        //�u�Y�����������݂��܂���B�v�̗�O���X���[����B
        if (l_rows == null) 
        {
            log.debug("�Y�����������݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02086,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y�����������݂��܂���B");          
        }
        
        // createResponse( )
        WEB3AdminIfoManualLapseConfirmResponse l_response =
            (WEB3AdminIfoManualLapseConfirmResponse)l_request.createResponse();
        
        // (*)�v���p�e�B�Z�b�g
        //�Ώے�������    ���@@get�蓮�����Ώے����P�ʈꗗ()�̖߂�l.length
        l_response.lapseTargetOrderNumber = String.valueOf(l_rows.length);
        
        //���ݎ���      ���@@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        //������       ���@@get����()�����̖߂�l != null�̏ꍇ�A
        //                get����()�̖߂�l�������B
        String l_strProductCode = l_productCode;
        if (l_strProductCode != null) 
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_ifoProductManagerImpl =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
            IfoProduct l_ifoProduct = null;
            try 
            {
                l_ifoProduct = l_ifoProductManagerImpl.getIfoProduct(l_institution, l_strProductCode);
            }
            catch (NotFoundException l_ex) 
            {
                log.error("�����ɊY������f�[�^�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����ɊY������f�[�^�����݂��Ȃ��B");
            }
            
            IfoProductRow l_row = (IfoProductRow)l_ifoProduct.getDataSourceObject();
            l_response.productName = l_row.getStandardName();
       
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (run�蓮����)<BR>
     * �敨OP�蓮�����N�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��Ґ敨OP�蓮�����T�[�r�X�jrun�蓮�����v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�敨OP�蓮���������N�����N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321E03D5
     */
    protected WEB3AdminIfoManualLapseRunResponse runManualExpire(WEB3AdminIfoManualLapseRunRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "runManualExpire(WEB3AdminIfoManualLapseRunRequest)";
        log.entering(STR_METHOD_NAME);
        
        // validate( )
        l_request.validate();
        
        // getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
      
        // validate�蓮�����\()
        this.validateManualExpirePossibility(l_admin, l_request);
        
        // get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        // delete�I�����C�����s����(String)
        this.deleteOnlineRunStatus(l_strInstitutionCode);
        
        // get�f�[�����g���K�[�ꗗ( )
        List l_lisDaemonTriggerList = this.getDaemonTriggerList();
        
        // getServerAccessor( )
        ServerAccessor l_serverAccessor = this.getServerAccessor();
        
        // (*)get�f�[�����g���K�[�ꗗ()�̖߂�l�̗v�f�����A
        //Loop����
        try
        {
            for (int i = 0; i < l_lisDaemonTriggerList.size(); i++) 
            {
                DaemonTriggerRow l_row = (DaemonTriggerRow)l_lisDaemonTriggerList.get(i);
                
                // updateAP�ďo��(long)
                this.updateAPCalling(l_row.getThreadNo());
                
                // �Ǘ��ҁE�敨OP�蓮�������C�����N�G�X�g( )
                WEB3AdminIfoManualLapseMainRequest l_mainRequest = 
                    new WEB3AdminIfoManualLapseMainRequest();
                
                //�،���ЃR�[�h   ���@@get�،���ЃR�[�h()�̖߂�l
                l_mainRequest.institutionCode = l_strInstitutionCode;
                
                //�X���b�hNo        ���@@�����Ώۂ̗v�f.�X���b�h�ԍ�
                l_mainRequest.threadNo = new Long(l_row.getThreadNo());
                
                //From����ID  ���@@�����Ώۂ̗v�f.�ڋq�R�[�h�i���j
                l_mainRequest.accountIdFrom = new Long(l_row.getRangeFrom());
                
                //To����ID        ���@@�����Ώۂ̗v�f.�ڋq�R�[�h�i���j
                l_mainRequest.accountIdTo = new Long(l_row.getRangeTo());
                
                //�����Ώے�������  ���@@���N�G�X�g�̓�������
                l_mainRequest.ifoLapseTargetOrderCondition = l_request.ifoLapseTargetOrderCondition;
 
                // doRequest(arg0 : Request)
                l_serverAccessor.doRequest(l_mainRequest); 
    
            }
        }
        catch (CommunicationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // createResponse( )
        WEB3AdminIfoManualLapseRunResponse l_response = 
            (WEB3AdminIfoManualLapseRunResponse)l_request.createResponse();
        
        // (*)�v���p�e�B�Z�b�g
        //���ݎ���      ���@@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�����X�e�[�^�X)<BR>
     * �敨OP�蓮�����̏����X�e�[�^�X���m�F����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��Ґ敨OP�蓮�����T�[�r�X�jvalidate�����X�e�[�^�X�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�敨OP�蓮���������X�e�[�^�X�m�F���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321F000C
     */
    protected WEB3AdminIfoManualLapseStatusResponse validateStatus(WEB3AdminIfoManualLapseStatusRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateStatus(WEB3AdminIfoManualLapseStatusRequest)";
        log.entering(STR_METHOD_NAME);
        
        // getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.MANUAL_EXPIRE, true);
        
        // get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        // get�f�[�����g���K�[�ꗗ( )
        List l_lisDaemonTriggerList = this.getDaemonTriggerList();
        
        // get�I�����C�����s���ʈꗗ(String)
        List l_lisOnlineExecResultList = this.getOnlineRunStatusList(l_strInstitutionCode);
        
        // createResponse( )
        WEB3AdminIfoManualLapseStatusResponse l_response =
            (WEB3AdminIfoManualLapseStatusResponse)l_request.createResponse();
        
        // (*)�v���p�e�B�Z�b�g
        //�����X�e�[�^�X   ���@@�ȉ��̕���ɂ��Z�b�g����B
        //�@@�@@"������"���Z�b�g�������
        //�@@�@@�E�I�����C�����s���ʃ��R�[�h���擾�ł��Ȃ������ꍇ
        //�@@�@@�E�擾�����f�[�����g���K�[���R�[�h�̌����ƁA�I�����C�����s���ʃ��R�[�h�̌������قȂ�ꍇ
        //�@@�@@�E�擾�����f�[�����g���K�[���R�[�h.������Ԃ�"������"��1���ł����݂���ꍇ
        //�@@�@@�E�擾�����I�����C�����s���ʃ��R�[�h.���s�X�e�[�^�X�敪��"������"��1���ł����݂���ꍇ
        if (l_lisOnlineExecResultList == null || l_lisDaemonTriggerList.size() != l_lisOnlineExecResultList.size())
        {
            l_response.lapseStatus = WEB3RunStatusDivDef.DEALING;
        }
        else 
        {
            int l_intSize = l_lisDaemonTriggerList.size();
            DaemonTriggerRow l_daemonTriggerRow = null;
            OnlineRunStatusRow l_onlineRunStatusRow = null;
            int l_intFlag = 0;
            for(int i = 0; i < l_intSize; i++)
            {
                l_daemonTriggerRow = (DaemonTriggerRow)l_lisDaemonTriggerList.get(i);
                l_onlineRunStatusRow = (OnlineRunStatusRow)l_lisOnlineExecResultList.get(i);
                //�@@�E�擾�����f�[�����g���K�[���R�[�h.������Ԃ�"������"��1���ł����݂���ꍇ
                //�@@�E�擾�����I�����C�����s���ʃ��R�[�h.���s�X�e�[�^�X�敪��"������"��1���ł����݂���ꍇ
                if (WEB3DaemonTriggerStatusDef.THREAD_PROCESSING.equals(l_daemonTriggerRow.getTriggerStatus()) 
                    || WEB3RunStatusDivDef.DEALING.equals(l_onlineRunStatusRow.getRunStatusDiv()))
                {
                    l_intFlag = 1;
                    break;
                }
                else if (!WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus())
                    || !WEB3RunStatusDivDef.DEALED.equals(l_onlineRunStatusRow.getRunStatusDiv()))
                {
                    l_intFlag = 2;                 
                }
            }
            //�@@"������"���Z�b�g�������
            if (l_intFlag == 1)
            {
                l_response.lapseStatus = WEB3RunStatusDivDef.DEALING;
            }
            //�@@�A"������"���Z�b�g�������
            //�@@�E�擾�����S�Ẵf�[�����g���K�[���R�[�h.������� == "���ғ�"�@@����
            //�@@�擾�����S�ẴI�����C�����s���ʃ��R�[�h.���s�X�e�[�^�X�敪 == "������"
            else if (l_intFlag == 0)
            {
                l_response.lapseStatus = WEB3RunStatusDivDef.DEALED;
            }
            //"�G���["���Z�b�g�������
            //�ȊO�̏ꍇ
            else if (l_intFlag == 2)
            {
                l_response.lapseStatus = WEB3RunStatusDivDef.ERROR;
            }
        }
        
        //�@@���ݎ���  ���@@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�蓮�����\)<BR>
     * �敨OP�蓮�������������s�\���ǂ����`�F�b�N����B<BR>
     * 
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҁE�敨OP�蓮�����T�[�r�X�jvalidate�蓮�����\�v�Q�ƁB<BR>
     * =====================================================<BR>
     * �@@�V�[�P���X�} :�i�Ǘ��ҁE�敨OP�蓮�����T�[�r�X�jvalidate�蓮�����\<BR>
     * �@@ ��̈ʒu   : 1.10  (*)��d�N���`�F�b�N<BR>
     *             �ȉ��̏����ɊY�����Ȃ��ꍇ�́A<BR>
     *             �u�w��AP�N�����i��d�N���G���[�j�B�v�̗�O���X���[����B<BR>
     *             <BR>
     *             �@@get�I�����C�����s���ʈꗗ()�̖߂�l == null<BR>
     *             �Aget�I�����C�����s���ʈꗗ()�̖߂�l�̌��� ==<BR>
     *             �@@get�f�[�����g���K�[�ꗗ()�̖߂�l�̌��� ����<BR>
     *             �@@get�I�����C�����s���ʈꗗ()�̖߂�l�̊e�v�f��<BR>
     *             �@@���s�X�e�[�^�X�敪��"������"�����݂��Ȃ� ����<BR>
     *             �@@get�f�[�����g���K�[�ꗗ()�̖߂�l�̊e�v�f��<BR>
     *             �@@������Ԃ�"���ғ�<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag : BUSINESS_ERROR_01992<BR>
     * =====================================================<BR>
     * =====================================================<BR>
     * �@@�V�[�P���X�} : �i�Ǘ��ҁE�敨OP�蓮�����T�[�r�X�jvalidate�蓮�����\<BR>
     * �@@ ��̈ʒu   : 1.11.1 getProduct()<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@1.12.1get�ڋq�ꗗ(String, String[], String)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�擾�ł��Ȃ������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u�����ɊY������f�[�^�����݂��Ȃ��B�v��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Ɩ��G���[���X���[����B<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag : BUSINESS_ERROR_01037<BR>
     * =====================================================<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@throws WEB3BaseException 
     * @@roseuid 4469564F03D3
     */
    protected void validateManualExpirePossibility(
        WEB3Administrator l_administrator, 
        WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "validateManualExpirePossibility(WEB3Administrator, WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_administrator == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
        // (*)���N�G�X�g�̌^����
        WEB3AdminIfoManualLapseRunRequest l_runRequest = null;
        WEB3AdminIfoManualLapseConfirmRequest l_confirmRequest = null;

        WEB3AdminIfoLapseTargetOrderCondition l_condition = null;
        if (l_request instanceof WEB3AdminIfoManualLapseConfirmRequest)
        {
            l_confirmRequest = (WEB3AdminIfoManualLapseConfirmRequest)l_request;
            l_condition = l_confirmRequest.ifoLapseTargetOrderCondition;

        }
        else if (l_request instanceof WEB3AdminIfoManualLapseRunRequest)
        {
            l_runRequest = (WEB3AdminIfoManualLapseRunRequest)l_request;
            l_condition = l_runRequest.ifoLapseTargetOrderCondition;

        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        //���N�G�X�g.�����Ώے�������.���X�R�[�h
        String[] l_strBranchCodes = l_condition.branchCode;
        
        //���N�G�X�g.�����Ώے�������.�敨�^�I�v�V�����敪
        String l_strFuturesOptionDivision = l_condition.fuOpDiv;
        
        //���N�G�X�g.�����Ώے�������.�w�����
        String l_strTargetProductCode = l_condition.targetProductCode;
        
        //���N�G�X�g.�����Ώے�������.����
        String l_strDelivaryMonth = l_condition.delivaryMonth;
        
        //���N�G�X�g.�����Ώے�������.�s�g���i
        String l_strStrikePrice = l_condition.strikePrice;
        
        //���N�G�X�g.�����Ώے�������.�I�v�V�������i�敪
        String l_strOpProductType = l_condition.opProductType;
        
        //���N�G�X�g.�����Ώے�������.����敪�ꗗ
        String[] l_strTradingTypeList = l_condition.tradingTypeList;
        
        //���N�G�X�g.�����Ώے�������.�ڋq�R�[�h
        String l_strAccountCode = l_condition.accountCode;
        
        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.MANUAL_EXPIRE, true);

        // validate���X����(���X�R�[�h : String[])
        l_administrator.validateBranchPermission(l_strBranchCodes);
        
        // (*)�����N�����N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminIfoManualLapseRunRequest)
        {
            // validate����p�X���[�h(String)
            l_administrator.validateTradingPassword(l_runRequest.password);
        }
        
        // get�،����( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_administrator.getInstitution();
        
        // validate�����J�z������(�،����,String[])
        this.validateCarryOvering(l_institution,l_strTradingTypeList);

        // get�f�[�����g���K�[�ꗗ( )
        List l_lisTriggerList = this.getDaemonTriggerList();
        
        // get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        // get�I�����C�����s���ʈꗗ(String)
        List l_lisOnlineExecResultList = this.getOnlineRunStatusList(l_strInstitutionCode);
        
        // (*)��d�N���`�F�b�N
        if (l_lisOnlineExecResultList != null) 
        {
            if (l_lisOnlineExecResultList.size() != l_lisTriggerList.size()) 
            {
                log.debug("�w��AP�N�����i��d�N���G���[�j�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�w��AP�N�����i��d�N���G���[�j�B");
            }
            else 
            {
                OnlineRunStatusRow l_onlineRunStatusRow = null;
                DaemonTriggerRow l_daemonTriggerRow = null;
                for (int i = 0; i < l_lisOnlineExecResultList.size(); i++)
                {
                    l_onlineRunStatusRow = (OnlineRunStatusRow)l_lisOnlineExecResultList.get(i);
                    l_daemonTriggerRow = (DaemonTriggerRow)l_lisTriggerList.get(i);
                    if (WEB3RunStatusDivDef.DEALING.equals(l_onlineRunStatusRow.getRunStatusDiv())
                        || !WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus())) 
                    {
                        log.debug("�w��AP�N�����i��d�N���G���[�j�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                            this.getClass().getName() + "." + STR_METHOD_NAME, 
                            "�w��AP�N�����i��d�N���G���[�j�B");
                    }
                }
            }
        }
        // (*)�擾�����敨�^�I�v�V�����敪?!=?null�̏ꍇ
        if (l_strFuturesOptionDivision != null) 
        {
            
            // get����(�،����, String, String, String, String, String)
                
                WEB3IfoProductImpl l_ifoProductImpl = WEB3AdminIfoDataManager.getProduct(
                    l_institution ,
                    l_strFuturesOptionDivision ,
                    l_strTargetProductCode ,
                    l_strDelivaryMonth ,
                    l_strStrikePrice ,
                    l_strOpProductType);
                
                if (l_ifoProductImpl == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�����ɊY������f�[�^�����݂��Ȃ��B"
                    );
                    
                }

        }
        
        // (*)�擾�����ڋq�R�[�h != null�̏ꍇ
        if (l_strAccountCode != null) 
        {
            // get�ڋq�ꗗ(String, String[], String)
            WEB3AdminIfoDataManager.getAccountList(l_strInstitutionCode,l_strBranchCodes, l_strAccountCode);    
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�f�[�����g���K�[�ꗗ)<BR>
     * �����̏����ɊY������f�[�����g���K�[�e�[�u����<BR>
     * ���R�[�h��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����Ńf�[�����g���K�[�e�[�u������������B<BR>
     * �@@[����]<BR>
     * �@@�@@�����^�C�v = "�蓮�����iifo�j"<BR>
     * <BR>
     * �@@[�\�[�g����]<BR>
     * �@@�@@�X���b�h�ԍ��@@����<BR>
     * <BR>
     * �@@�Y���f�[�^�Ȃ��̏ꍇ�A�u�Y���f�[�^�Ȃ��v��<BR>
     * �@@�V�X�e���G���[���X���[����B<BR>
     *  class: WEB3SystemLayerException<BR>
     *  tag:SYSTEM_ERROR_80005<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * @@return java.util.List
     * @@throws WEB3BaseException 
     * @@roseuid 44695650026C
     */
    protected List getDaemonTriggerList() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getDaemonTriggerList()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@DB���� 
        //�@@�ȉ��̏����Ńf�[�����g���K�[�e�[�u������������B 
        //�@@[����] 
        //�@@�@@�����^�C�v = "�蓮�����iifo�j" 
        Object[] l_bindValues = {WEB3DaemonTriggerTypeDef.IFO_MANUAL_EXPIRE};

        //�@@[�\�[�g����] 
        //�@@�@@�X���b�h�ԍ��@@���� 
        String l_strCondition = "thread_no asc";
        
        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                DaemonTriggerRow.TYPE,
                " trigger_type = ?",
                l_strCondition,
                null,
                l_bindValues);
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
        
        //�@@�Y���f�[�^�Ȃ��̏ꍇ�A�u�Y���f�[�^�Ȃ��v�� 
        //�@@�V�X�e���G���[���X���[����B 
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        
        //�Q�j�@@�������ʂ�ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }
    
    /**
     * (get�I�����C�����s���ʈꗗ)<BR>
     * �����̏����ɊY������I�����C�����s���ʃe�[�u����<BR>
     * ���R�[�h��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����ŃI�����C�����s���ʃe�[�u������������B<BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�����^�C�v = "�敨OP"<BR>
     * �@@�@@�敨�^�I�v�V�����敪 = "DEFAULT"<BR>
     * �@@�@@�I�����C���T�[�r�X�敪 = "�蓮����"<BR>
     * <BR>
     * �@@�Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@return java.util.List
     * @@throws WEB3BaseException 
     * @@roseuid 446956500369
     */
    protected List getOnlineRunStatusList(String l_strInstitutionCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getOnlineRunStatusList(String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@DB���� 
        //�@@�ȉ��̏����ŃI�����C�����s���ʃe�[�u������������B 
        //�@@[����] 
        //�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h 
        //�@@�@@�����^�C�v = "�敨OP" 
        //�@@�@@�敨�^�I�v�V�����敪 = "DEFAULT" 
        //�@@�@@�I�����C���T�[�r�X�敪 = "�蓮����" 
        String l_strWhere = " institution_code = ? and product_type = ? " +
        "and future_option_div = ? and online_service_div = ?";
        
        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(ProductTypeEnum.IFO);
        l_lisBindValues.add(WEB3FuturesOptionDivDef.DEFAULT);
        l_lisBindValues.add(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        Object[] l_bindValues = new Object[l_lisBindValues.size()];
        l_lisBindValues.toArray(l_bindValues);
        
        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                OnlineRunStatusRow.TYPE,
                l_strWhere,
                l_bindValues);
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
        
        // �Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�������ʂ�ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }
    
    /**
     * (delete�I�����C�����s����)<BR>
     * �����ɊY������I�����C�����s���ʃe�[�u����<BR>
     * ���R�[�h�𕨗��폜����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ɊY������I�����C�����s���ʃe�[�u����<BR>
     * �@@���R�[�h��delete����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�����^�C�v = "�敨OP"<BR>
     * �@@�@@�敨�^�I�v�V�����敪 = "DEFAULT"<BR>
     * �@@�@@�I�����C���T�[�r�X�敪 = "�蓮����"<BR>
     * <BR>
     * �@@���폜�Ώۂ̃��R�[�h���Ȃ��Ă���O�Ƃ��Ȃ����ƁB<BR>
     * �@@���{�����͐V�K�g�����U�N�V�����ŏ������s���A<BR>
     * �@@�@@�����������ɍX�V�����f�����悤�ɂ��邱�ƁB<BR>
     * �@@�@@�i�Q�l�FWEB3GentradeDaemonTriggerManager.startThread()�j<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@throws WEB3BaseException 
     * @@roseuid 446956510034
     */
    protected void deleteOnlineRunStatus(String l_strInstitutionCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "deleteOnlineRunStatus(String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�ȉ��̏����ɊY������I�����C�����s���ʃe�[�u���� 
        //�@@���R�[�h��delete����B 
        //�@@[����] 
        //�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h 
        //�@@�@@�����^�C�v = "�敨OP" 
        //�@@�@@�敨�^�I�v�V�����敪 = "DEFAULT" 
        //�@@�@@�I�����C���T�[�r�X�敪 = "�蓮����" 
        final String l_strWhere= " institution_code = ? and product_type = ? " +
        "and future_option_div = ? and online_service_div = ?";
        
        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(ProductTypeEnum.IFO);
        l_lisBindValues.add(WEB3FuturesOptionDivDef.DEFAULT);
        l_lisBindValues.add(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        
        final Object[] l_bindValues = new Object[l_lisBindValues.size()];
        l_lisBindValues.toArray(l_bindValues);
        
        try
        {
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                new TransactionCallback()
                    {
                        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
                        {
                            List l_lisRows = l_queryProcesser.doFindAllQuery(
                                OnlineRunStatusRow.TYPE,
                                l_strWhere,
                                "for update",
                                l_bindValues);
                            int l_intSize =  l_lisRows.size();
                            for (int i = 0; i < l_intSize; i++)
                            {
                                OnlineRunStatusRow l_row = (OnlineRunStatusRow)l_lisRows.get(i);
                                WEB3DataAccessUtility.deleteRow(l_row);
                            }
                            
                            log.exiting(STR_METHOD_NAME);
                            return null;
                        }
                    }
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
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (updateAP�ďo��)<BR>
     * �����̃X���b�hNo�ɊY������f�[�����g���K�[��<BR>
     * ���R�[�h���A"AP�ďo��"��update����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ɊY������f�[�����g���K�[�e�[�u����<BR>
     * �@@���R�[�h��update����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�X���b�h�ԍ� = �p�����[�^.�X���b�hNo<BR>
     * <BR>
     * �@@[�X�V�l]<BR>
     * �@@�@@������� = "�g���K�[�iAP�ďo���j"<BR>
     * <BR>
     * �@@���{�����͐V�K�g�����U�N�V�����ŏ������s���A<BR>
     * �@@�@@�����������ɍX�V�����f�����悤�ɂ��邱�ƁB<BR>
     * �@@�@@�i�Q�l�FWEB3GentradeDaemonTriggerManager.startThread()�j<BR>
     * @@param l_lngThreadNo - (�X���b�hNo)<BR>
     * �X���b�hNo
     * @@throws WEB3BaseException 
     * @@roseuid 446956510131
     */
    protected void updateAPCalling(long l_lngThreadNo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "updateAPCalling(long)";
        log.entering(STR_METHOD_NAME);
        
        final int UPDATE_SUCCESS = 0; // �X�V����������I�������l
        final int UPDATE_FAIL = -1; // �X�V�ΏۃX���b�h���̍X�V�Ɏ��s�����ꍇ�̒l
        final int NO_ROWS = -2; // �X�V�ΏۃX���b�h�����擾�ł��Ȃ������ꍇ�̒l
        
        //�P�j�@@�ȉ��̏����ɊY������f�[�����g���K�[�e�[�u���� 
        //�@@���R�[�h��update����B 
        //
        //�@@[����] 
        //�@@�@@�X���b�h�ԍ� = �p�����[�^.�X���b�hNo 
        //
        //�@@[�X�V�l] 
        //�@@�@@������� = "�g���K�[�iAP�ďo���j" 
        //
        //�@@���{�����͐V�K�g�����U�N�V�����ŏ������s���A 
        //�@@�@@�����������ɍX�V�����f�����悤�ɂ��邱�ƁB 
        //�@@�@@�i�Q�l�FWEB3GentradeDaemonTriggerManager.startThread()�j
        try 
        {
            final HashMap l_changes = new HashMap();
            l_changes.put("trigger_status",WEB3DaemonTriggerStatusDef.THREAD_API_CALL);
            
            final String l_strWhere = "thread_no = ?";
            final String l_strCondition = "for update";
            final Object l_bindVars[] = {new Long(l_lngThreadNo)};
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            Integer l_intResult = (Integer)
                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW,
                                               new TransactionCallback()
            {
                public Object process() throws DataNetworkException,
                    DataQueryException,
                    DataCallbackException
                {
                    Integer l_intResult = null;
                    List l_lisRows = l_queryProcesser.doFindAllQuery(
                        DaemonTriggerRow.TYPE,
                        l_strWhere, l_strCondition, l_bindVars);
                    if (l_lisRows != null && l_lisRows.size() > 0)
                    {
                        DaemonTriggerRow l_row =
                            (DaemonTriggerRow) l_lisRows.get(0);
                        WEB3DataAccessUtility.updateRow(l_row, l_changes);
                        l_intResult = new Integer(UPDATE_SUCCESS);
                    }
                    else
                    {
                        l_intResult = new Integer(NO_ROWS);
                    }
                    return l_intResult;
                }
            }
            );

            if (l_intResult.intValue() == UPDATE_FAIL)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            }
            else if (l_intResult.intValue() == NO_ROWS)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
        }
        catch (DataException de)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", de);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�����J�z������)<BR>
     * �����J�z���������ǂ����`�F�b�N����B<BR>
     * <BR> 
     * �P�j�@@get�����J�z�����敪()�̈���.�o���I���敪�ɃZ�b�g����l��ݒ肷��B <BR>
     * <BR>
     * �@@�P�|�P�j�@@������ԊǗ�.is�[�ꎞ�ԑ�()�̖߂�l��false�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@"DEFAULT"��ݒ肷��B <BR>
     * �@@�P�|�Q�j�@@������ԊǗ�.is�[�ꎞ�ԑ�()�̖߂�l��true�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@"�[��O�o���I��"��ݒ肷��B <BR>
     * <BR>
     * �Q�j  �敨�������J�z���������ǂ����̃`�F�b�N <BR>
     *   �p�����[�^.�،����.get�����J�z�����敪()���\�b�h���R�[������B<BR>
     * <BR>
     *   [get�����J�z�����敪()�ɃZ�b�g����p�����[�^]<BR>
     *    �����^�C�v�F�@@"�敨OP" <BR>
     *   �敨�^�I�v�V�����敪�F�@@"�敨" <BR>
     *   �o���I���敪�F�@@�P�j�̐ݒ�l<BR>
     * <BR>
     * �R�j�@@�I�v�V�����������J�z���������ǂ����̃`�F�b�N <BR>
     *   �p�����[�^.�،����.get�����J�z�����敪()���\�b�h���R�[������B <BR>
     * <BR>
     *   [get�����J�z�����敪()�ɃZ�b�g����p�����[�^] <BR>
     *   �����^�C�v�F�@@"�敨OP" <BR>
     *   �敨�^�I�v�V�����敪�F�@@"�I�v�V����" <BR>
     *   �o���I���敪�F�@@�P�j�̐ݒ�l <BR>
     * <BR>
     * �S�j  �p�����[�^.����敪�ꗗ�ɂ�菈�����e�𕪊򂷂�B<BR>
     *   [�p�����[�^.����敪�ꗗ == null�̏ꍇ] <BR>
     *  �i���͉�ʕ\����������R�[�����ꂽ�ꍇ�j<BR>
     *   �Q�j�A�R�j�̖߂�l�������Ƃ�"�����J�zAP�ďo��"�ł���΁A<BR>
     *  �u�����J�z�������ׁ̈A�����s�v�̗�O���X���[����B <BR>
     * <BR>
     *   [�p�����[�^.����敪�ꗗ�ɐ敨���(*1)���܂܂��ꍇ] <BR>
     *   �Q�j�̖߂�l == "�����J�zAP�ďo��"�ł���΁A<BR>
     *  �u�����J�z�������ׁ̈A�����s�v�̗�O���X���[����B <BR>
     * <BR>
     *   [�p�����[�^.����敪�ꗗ�ɃI�v�V�������(*2)���܂܂��ꍇ] <BR>
     *   �R�j�̖߂�l == "�����J�zAP�ďo��"�ł���΁A<BR>
     *   �u�����J�z�������ׁ̈A�����s�v�̗�O���X���[����B <BR>
     *   <BR>
     * @@param l_institution - (�،����)<BR>   
     * �،���ЃI�u�W�F�N�g <BR>  
     * @@param l_strTradingTypeList - (����敪�ꗗ)<BR>  
     * <BR> 
     * @@throws WEB3BaseException   
     */
    protected void validateCarryOvering(WEB3GentradeInstitution l_institution,String[] l_strTradingTypeList) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCarryOvering(WEB3GentradeInstitution,String[])";
        log.entering(STR_METHOD_NAME);

        //�P�jget�����J�z�����敪()�̈���.�o���I���敪�ɃZ�b�g����l��ݒ肷��B
        //������ԊǗ�.is�[�ꎞ�ԑ�()
        boolean l_blnIsEveningSessionTimeZone = WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone();

        String l_strEndType = null;

        if (l_blnIsEveningSessionTimeZone)
        {
            //�o���I���敪
            l_strEndType = WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END;
        }
        else
        {
            l_strEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
        }

        //�Q�j  �敨�������J�z���������ǂ����̃`�F�b�N
        // �p�����[�^.�،����.get�����J�z�����敪()���\�b�h���R�[������B
        String l_strCarryoverEndTypeFutures = 
            l_institution.getCarryoverEndType(ProductTypeEnum.IFO, WEB3FuturesOptionDivDef.FUTURES, l_strEndType);
        
        //�R�j  �I�v�V�����������J�z���������ǂ����̃`�F�b�N
        // �p�����[�^.�،����.get�����J�z�����敪()���\�b�h���R�[������B
        String l_strCarryoverEndTypeOption = 
            l_institution.getCarryoverEndType(ProductTypeEnum.IFO, WEB3FuturesOptionDivDef.OPTION, l_strEndType);
        
        //�p�����[�^.����敪�ꗗ == null�̏ꍇ
        //�Q�j�A�R�j�̖߂�l�������Ƃ�"�����J�zAP�ďo��"�ł����
        if (l_strTradingTypeList == null)
        {
            if (WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP.equals(l_strCarryoverEndTypeFutures)&&
                WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP.equals(l_strCarryoverEndTypeOption))
            {
                log.debug("�����J�z�������ׁ̈A�����s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02446,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����J�z�������ׁ̈A�����s�B");
                          
            }
        }
        
        //[�p�����[�^.����敪�ꗗ�ɐ敨���(*1)���܂܂��ꍇ]�Q�j�̖߂�l == "�����J�zAP�ďo��"�ł���΁A
        //�u�����J�z�������ׁ̈A�����s�v�̗�O���X���[����B
        if (l_strTradingTypeList != null && 
            WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP.equals(l_strCarryoverEndTypeFutures))
        {
            for (int i = 0; i < l_strTradingTypeList.length; i++) 
            {
                if (String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.intValue()).equals(l_strTradingTypeList[i])) 
                {
                    log.debug("�����J�z�������ׁ̈A�����s�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02446,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�����J�z�������ׁ̈A�����s�B");                
                }
            }
        }
        
        //[�p�����[�^.����敪�ꗗ�ɃI�v�V�������(*2)���܂܂��ꍇ]�R�j�̖߂�l == "�����J�zAP�ďo��"�ł���΁A
        //�u�����J�z�������ׁ̈A�����s�v�̗�O���X���[����B
        if (l_strTradingTypeList != null && 
            WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP.equals(l_strCarryoverEndTypeOption))
        {
            for (int i = 0; i < l_strTradingTypeList.length; i++)
            {
                if ( String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.intValue()).equals(l_strTradingTypeList[i])
                    || String.valueOf(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.intValue()).equals(l_strTradingTypeList[i]))
                {
                    log.debug("�����J�z�������ׁ̈A�����s�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02446,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�����J�z�������ׁ̈A�����s�B");                
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���ו��U�������s���ׂ�ServerAccessor�I�u�W�F�N�g��<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�j�@@this.accessor != null�̏ꍇ�Athis.accessor��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ�A�ȍ~�̎菇�ɂ�ServerAccessor<BR>
     * �@@�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �R�j�@@�N���X�^�����O��T�[�o�[URL���擾����B<BR>
     * �@@�@@QueryProcessor.doFindAllQuery()���\�b�h��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@ServerConfigRow.TYPE<BR>
     * �@@�@@�@@arg1�F�@@"config_categ = ?"<BR>
     * �@@�@@�@@arg2�F�@@"cluster.urls"�݂̂�v�f�Ƃ���z��<BR>
     * <BR>
     * �@@�@@��"cluster.urls"�͒萔��`�N���X�Q�Ƃ��邱�ƁB<BR>
     * <BR>
     * �@@�@@�������ʂ̊e�v�f.config_value���擾���A������z���<BR>
     * �@@�@@�쐬����B<BR>
     * �@@�@@���������ʂ��擾�ł��Ȃ������ꍇ�A�u�Y���f�[�^�Ȃ��v��<BR>
     * �@@�@@�@@�V�X�e���G���[���X���[����B<BR>
     * �@@�@@class�@@:�@@WEB3SystemLayerException<BR>
     * �@@�@@tag�@@:�@@SYSTEM_ERROR_80005<BR>
     * <BR>
     * �S�j�@@ServerAccessor�̍쐬<BR>
     * �@@ServerConnector.createAccessor()���\�b�h���R�[�����A<BR>
     * �@@�߂�l��this.accessor�ɃZ�b�g������A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[craeteAccessor()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�iACCESSOR_KEY�j�F�@@"web3-static-cluster"<BR>
     * �@@�@@arg1�iURL�j�F�@@�R�j�ɂč쐬����������z��<BR>
     * <BR>
     * �@@�@@��"web3-static-cluster"�͒萔��`�N���X�Q�Ƃ��邱�ƁB<BR>
     * @@return ServerAccessor
     * @@throws WEB3BaseException 
     * @@roseuid 4472EC71004E
     */
    protected ServerAccessor getServerAccessor() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getServerAccessor()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@this.accessor != null�̏ꍇ�Athis.accessor��ԋp����B 
        if (this.accessor != null)
        {
            return this.accessor;
        }
        
        //�Q�j�@@�P�j�ȊO�̏ꍇ�A�ȍ~�̎菇�ɂ�ServerAccessor 
        //�@@�I�u�W�F�N�g���擾����B 
        //
        //�R�j�@@�N���X�^�����O��T�[�o�[URL���擾����B 
        //�@@�@@QueryProcessor.doFindAllQuery()���\�b�h�� 
        //�@@�@@�R�[������B 
        //
        //�@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] 
        //�@@�@@�@@arg0�F�@@ServerConfigRow.TYPE 
        //�@@�@@�@@arg1�F�@@"config_categ = ?" 
        //�@@�@@�@@arg2�F�@@"cluster.urls"�݂̂�v�f�Ƃ���z�� 
        //
        //�@@�@@��"cluster.urls"�͒萔��`�N���X�Q�Ƃ��邱�ƁB 
        //
        //�@@�@@�������ʂ̊e�v�f.config_value���擾���A������z��� 
        //�@@�@@�쐬����B 
        //�@@�@@���������ʂ��擾�ł��Ȃ������ꍇ�A�u�Y���f�[�^�Ȃ��v�� 
        //�@@�@@�@@�V�X�e���G���[���X���[����B 
        String l_strQueryWhere = "config_categ = ?";
        Object[] l_bindValues = {WEB3ServerUrlAccessorDef.CLUSTER_URLS};
        List l_lisRecords = null;
        try 
        {         
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                ServerConfigRow.TYPE,
                l_strQueryWhere,
                l_bindValues);
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
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        
        //�S�j�@@ServerAccessor�̍쐬 
        // �@@ServerConnector.createAccessor()���\�b�h���R�[�����A
        // �@@�߂�l��this.accessor�ɃZ�b�g����B
        int l_intLength = l_lisRecords.size();
        ArrayList l_lisConfigValues = new ArrayList();
        for (int i = 0; i < l_intLength; i++)
        {
            ServerConfigRow l_serverConfigRow = (ServerConfigRow) l_lisRecords.get(i);
            l_lisConfigValues.add(l_serverConfigRow.getConfigValue());
        }
        
        String[] l_strConfigValues = new String[l_lisConfigValues.size()];
        l_lisConfigValues.toArray(l_strConfigValues);
        try
        {
            ServerAccessor l_serverAccessor = ServerConnector.createAccessor(
                WEB3ServerUrlAccessorDef.WEB3_STATIC_CLUSTER,
                l_strConfigValues);
            this.accessor = l_serverAccessor;
        }
        catch (CommunicationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return this.accessor;
    }
}
@
