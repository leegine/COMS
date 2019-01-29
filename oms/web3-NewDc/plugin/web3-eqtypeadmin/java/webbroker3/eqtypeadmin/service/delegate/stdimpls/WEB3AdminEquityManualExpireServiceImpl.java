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
filename	WEB3AdminEquityManualExpireServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����蓮�����T�[�r�XImpl(WEB3AdminEquityManualExpireServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30�@@�юu��(���u) �V�K�쐬
Revesion History : 2007/12/17  ��іQ(���u) �d�l�ύX���f��No.168
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DaemonTriggerTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3ServerUrlAccessorDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityLapseTargetOrderCondition;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityManualExpireService;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;



/**
 * (�Ǘ��ҁE�����蓮�����T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�����蓮�����T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �юu��(���u)
 * @@version 1.0
 */

public class WEB3AdminEquityManualExpireServiceImpl extends WEB3ClientRequestService implements WEB3AdminEquityManualExpireService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminEquityManualExpireServiceImpl.class);
    
    /**
     * ServerAccessor�I�u�W�F�N�g <BR>
     * <BR>
     * �����������e�T�[�o�ɐU�蕪����B<BR>
     * ��������s���ɃZ�b�g�����B<BR>
     */
    private ServerAccessor accessor;
    
    /**
     * (�Ǘ��ҁE�����蓮�����T�[�r�XImpl)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityManualExpireServiceImpl
     * @@roseuid 446932950058
     */
    public WEB3AdminEquityManualExpireServiceImpl() 
    {
     
    }
    
    /**
     * �����蓮�����������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�����蓮�������̓��N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.get���͉��()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�����蓮�����m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.validate�蓮����()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�����蓮���������N�����N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.run�蓮����()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�����蓮���������X�e�[�^�X�m�F���N�G�X�g�̏ꍇ<BR>
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
        
        //�Ǘ��ҁE�����蓮�������̓��N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminEquityManualLapseInputRequest)
        {
            //this.get���͉��()���R�[������B
            l_response = this.getInputScreen((WEB3AdminEquityManualLapseInputRequest)l_request);
        }
        
        //�Ǘ��ҁE�����蓮�����m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminEquityManualLapseConfirmRequest)
        {
            //this.validate�蓮����()���R�[������B
            l_response = this.validateManualExpire((WEB3AdminEquityManualLapseConfirmRequest)l_request);
        }
        
        //�Ǘ��ҁE�����蓮���������N�����N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminEquityManualLapseRunRequest)
        {
            //this.run�蓮����()���R�[������B
            l_response = this.runManualExpire((WEB3AdminEquityManualLapseRunRequest)l_request);
        }
        
        //�Ǘ��ҁE�����蓮���������X�e�[�^�X�m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminEquityManualLapseStatusRequest)
        {
            //this.validate�����X�e�[�^�X()���R�[������B
            l_response = this.validateStatus((WEB3AdminEquityManualLapseStatusRequest)l_request);
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
     * �����蓮�������͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��Ҋ����蓮�����T�[�r�X�jget���͉�ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����蓮�������̓��N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321E03A6
     */
    protected WEB3AdminEquityManualLapseInputResponse getInputScreen(WEB3AdminEquityManualLapseInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminEquityManualLapseInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_MANUAL_EXPIRE, true);
        
        //1.4get�،����( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_admin.getInstitution();
        
        //1.5 validate�����J�z������(�،����)
        this.validateCarryOvering(l_institution);
        
        //1.6 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.7 get�戵�\�s��(�،���ЃR�[�h : String, �����^�C�v : ProductTypeEnum)
        String[] l_strMarkets = 
            WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(l_strInstitutionCode, ProductTypeEnum.EQUITY);

        //1.8 get�戵�\�s��(�،���ЃR�[�h : String, �����^�C�v : ProductTypeEnum)
        String[] l_strPTSMarkets = 
        	WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(l_strInstitutionCode, ProductTypeEnum.EQUITY);

        String[] l_strResults = mergeAndSort(l_strMarkets, l_strPTSMarkets);

        //1.9  createResponse( )
        WEB3AdminEquityManualLapseInputResponse l_response = 
            (WEB3AdminEquityManualLapseInputResponse)l_request.createResponse();
        
        //1.10  (*)�v���p�e�B�Z�b�g
        //�s��R�[�h�ꗗ�@@���@@(*1)�Ŏ擾�����s��R�[�h�̔z���(*2)�Ŏ擾�����s��R�[�h�̔z����}�[�W�����z��������ŃZ�b�g�B
        l_response.marketList = l_strResults;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�蓮����)<BR>
     * �����蓮�����m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��Ҋ����蓮�����T�[�r�X�jvalidate�蓮�����v�Q��<BR>
     * ===================================================<BR>
     * �@@�V�[�P���X�} :�i�Ǘ��Ҋ����蓮�����T�[�r�X�jvalidate�蓮����<BR>
     * �@@��̈ʒu    : 1.5 get�蓮�����Ώے����P�ʈꗗ()<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@null���ԋp���ꂽ�ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u�Y�����������݂��܂���B�v�̗�O���X���[����B<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag : BUSINESS_ERROR_02086<BR>
     * ===================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����蓮�����m�F���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321E03B6
     */
    protected WEB3AdminEquityManualLapseConfirmResponse validateManualExpire(WEB3AdminEquityManualLapseConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateManualExpire(WEB3AdminEquityManualLapseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
       
        //1.3 validate�蓮�����\(�Ǘ���, WEB3GenRequest)
        this.validateManualExpirePossibility(l_admin, l_request);
 
        //1.4  get�،����( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_admin.getInstitution();
        
        //1.5 get�蓮�����Ώے����P�ʈꗗ(�،����, String[], String, String[], String[], String[], String, Long, Long)
        EqtypeOrderUnitRow[] l_rows = 
            WEB3AdminPMEquityDataManager.getManualExpireOrderUnits(
                l_institution,  
                l_request.equityLapseTargetOrderCondition.branchCode, 
                l_request.equityLapseTargetOrderCondition.productCode,
                l_request.equityLapseTargetOrderCondition.marketList,
                l_request.equityLapseTargetOrderCondition.tradingTypeList,
                l_request.equityLapseTargetOrderCondition.repaymentList,
                l_request.equityLapseTargetOrderCondition.accountCode,
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
        
        //1.6 createResponse( )
        WEB3AdminEquityManualLapseConfirmResponse l_response =
            (WEB3AdminEquityManualLapseConfirmResponse)l_request.createResponse();
        
        //1.7  (*)�v���p�e�B�Z�b�g
        //�Ώے�������    ���@@get�蓮�����Ώے����P�ʈꗗ()�̖߂�l.length
        l_response.lapseTargetOrderNumber = String.valueOf(l_rows.length);
        
        //���ݎ���      ���@@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        //������       ���@@���N�G�X�g�f�[�^.�����Ώے�������.�����R�[�h != null�̏ꍇ�A
        //               �@@�����R�[�h�ɊY�����銔������.getDataSourceObject().���������Z�b�g�B
        String l_strProductCode = l_request.equityLapseTargetOrderCondition.productCode;
        if (l_strProductCode != null) 
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_equityProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
            EqTypeProduct l_eqTypeProduct = null;
            try 
            {
                l_eqTypeProduct = l_equityProductManager.getProduct(l_institution, l_strProductCode);
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
            
            EqtypeProductRow l_row = (EqtypeProductRow)l_eqTypeProduct.getDataSourceObject();
            l_response.productName = l_row.getStandardName();
       
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (run�蓮����)<BR>
     * �����蓮�����N�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��Ҋ����蓮�����T�[�r�X�jrun�蓮�����v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����蓮���������N�����N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321E03D5
     */
    protected WEB3AdminEquityManualLapseRunResponse runManualExpire(WEB3AdminEquityManualLapseRunRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "runManualExpire(WEB3AdminEquityManualLapseRunRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
      
        //1.3 validate�蓮�����\()
        this.validateManualExpirePossibility(l_admin, l_request);
        
        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 delete�I�����C�����s����(String)
        this.deleteOnlineRunStatus(l_strInstitutionCode);
        
        //1.6 get�f�[�����g���K�[�ꗗ( )
        List l_lisDaemonTriggerList = this.getDaemonTriggerList();
        
        //1.7  getServerAccessor( )
        ServerAccessor l_serverAccessor = this.getServerAccessor();
        
        //1.8 (*)get�f�[�����g���K�[�ꗗ()�̖߂�l�̗v�f�����A
        //Loop����
        try
        {
            for (int i = 0; i < l_lisDaemonTriggerList.size(); i++) 
            {
                DaemonTriggerRow l_row = (DaemonTriggerRow)l_lisDaemonTriggerList.get(i);
                
                //1.8.1 updateAP�ďo��(long)
                this.updateAPCalling(l_row.getThreadNo());
                
                //1.8.2 �Ǘ��ҁE�����蓮�������C�����N�G�X�g( )
                WEB3AdminEquityManualLapseMainRequest l_mainRequest = 
                    new WEB3AdminEquityManualLapseMainRequest();
                
                //�،���ЃR�[�h   ���@@get�،���ЃR�[�h()�̖߂�l
                l_mainRequest.institutionCode = l_strInstitutionCode;
                
                //�X���b�hNo        ���@@�����Ώۂ̗v�f.�X���b�h�ԍ�
                l_mainRequest.threadNo = new Long(l_row.getThreadNo());
                
                //From����ID  ���@@�����Ώۂ̗v�f.�ڋq�R�[�h�i���j
                l_mainRequest.accountIdFrom = new Long(l_row.getRangeFrom());
                
                //To����ID        ���@@�����Ώۂ̗v�f.�ڋq�R�[�h�i���j
                l_mainRequest.accountIdTo = new Long(l_row.getRangeTo());
                
                //�����Ώے�������  ���@@���N�G�X�g�̓�������
                l_mainRequest.equityLapseTargetOrderCondition = l_request.equityLapseTargetOrderCondition;
 
                //1.8.3 doRequest(arg0 : Request)
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
        
        //1.9 createResponse( )
        WEB3AdminEquityManualLapseRunResponse l_response = 
            (WEB3AdminEquityManualLapseRunResponse)l_request.createResponse();
        
        //1.10 (*)�v���p�e�B�Z�b�g
        //���ݎ���      ���@@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�����X�e�[�^�X)<BR>
     * �����蓮�����̏����X�e�[�^�X���m�F����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��Ҋ����蓮�����T�[�r�X�jvalidate�����X�e�[�^�X�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����蓮���������X�e�[�^�X�m�F���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4469321F000C
     */
    protected WEB3AdminEquityManualLapseStatusResponse validateStatus(WEB3AdminEquityManualLapseStatusRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateStatus(WEB3AdminEquityManualLapseStatusRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2  validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_MANUAL_EXPIRE, true);
        
        //1.3 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.4 get�f�[�����g���K�[�ꗗ( )
        List l_lisDaemonTriggerList = this.getDaemonTriggerList();
        
        //1.5 get�I�����C�����s���ʈꗗ(String)
        List l_lisOnlineExecResultList = this.getOnlineRunStatusList(l_strInstitutionCode);
        
        //1.6 createResponse( )
        WEB3AdminEquityManualLapseStatusResponse l_response =
            (WEB3AdminEquityManualLapseStatusResponse)l_request.createResponse();
        
        //1.7 (*)�v���p�e�B�Z�b�g
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
     * �����蓮�������������s�\���ǂ����`�F�b�N����B<BR>
     * 
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҁE�����蓮�����T�[�r�X�jvalidate�蓮�����\�v�Q�ƁB<BR>
     * =====================================================<BR>
     * �@@�V�[�P���X�} :�i�Ǘ��ҁE�����蓮�����T�[�r�X�jvalidate�蓮�����\<BR>
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
     * �@@�V�[�P���X�} : �i�Ǘ��ҁE�����蓮�����T�[�r�X�jvalidate�蓮�����\<BR>
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
        
        //1.1 (*)���N�G�X�g�̌^����
        WEB3AdminEquityManualLapseRunRequest l_runRequest = null;
        WEB3AdminEquityManualLapseConfirmRequest l_confirmRequest = null;
        WEB3AdminEquityLapseTargetOrderCondition l_condition = null;
        if (l_request instanceof WEB3AdminEquityManualLapseConfirmRequest)
        {
            l_confirmRequest = (WEB3AdminEquityManualLapseConfirmRequest)l_request;
            l_condition = l_confirmRequest.equityLapseTargetOrderCondition;

        }
        else if (l_request instanceof WEB3AdminEquityManualLapseRunRequest)
        {
            l_runRequest = (WEB3AdminEquityManualLapseRunRequest)l_request;
            l_condition = l_runRequest.equityLapseTargetOrderCondition;

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
        
        //���N�G�X�g.�����Ώے�������.�����R�[�h
        String l_strProductCode = l_condition.productCode;
        
        //���N�G�X�g.�����Ώے�������.�ڋq�R�[�h
        String l_strAccountCode = l_condition.accountCode;
        
        //1.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_MANUAL_EXPIRE, true);

        //1.3 validate���X����(���X�R�[�h : String[])
        l_administrator.validateBranchPermission(l_strBranchCodes);
        
        //1.4  (*)�����N�����N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminEquityManualLapseRunRequest)
        {
            //1.4.1 validate����p�X���[�h(String)
            l_administrator.validateTradingPassword(l_runRequest.password);
        }
        
        //1.5  get�،����( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_administrator.getInstitution();
        
        //1.6 validate�����J�z������(�،����)
        this.validateCarryOvering(l_institution);

        //1.7  get�f�[�����g���K�[�ꗗ( )
        List l_lisTriggerList = this.getDaemonTriggerList();
        
        //1.8 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.9 get�I�����C�����s���ʈꗗ(String)
        List l_lisOnlineExecResultList = this.getOnlineRunStatusList(l_strInstitutionCode);
        
        //1.10  (*)��d�N���`�F�b�N
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
        // 1.11 (*)�擾���������R�[�h != null�̏ꍇ
        if (l_strProductCode != null) 
        {
            
            //1.11.1 getProduct()
            try 
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_equityProductManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                l_equityProductManager.getProduct(l_institution, l_strProductCode);
            }
            catch (NotFoundException nf_ex)
            {
                log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����ɊY������f�[�^�����݂��Ȃ��B");
            }
        }
        
        //1.12 (*)�擾�����ڋq�R�[�h != null�̏ꍇ
        if (l_strAccountCode != null) 
        {
            //1.12.1 get�ڋq�ꗗ(String, String[], String)
            WEB3AdminPMEquityDataManager.getAccountList(l_strInstitutionCode,l_strBranchCodes, l_strAccountCode);    
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
     * �@@�@@�����^�C�v = "�蓮�����iEqtype�j"<BR>
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
        //�@@�@@�����^�C�v = "�蓮�����iEqtype�j" 
        Object[] l_bindValues = {WEB3DaemonTriggerTypeDef.EQTYPE_MANUAL_EXPIRE};

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
     * �@@�@@�����^�C�v = "����"<BR>
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
        //�@@�@@�����^�C�v = "����" 
        //�@@�@@�敨�^�I�v�V�����敪 = "DEFAULT" 
        //�@@�@@�I�����C���T�[�r�X�敪 = "�蓮����" 
        String l_strWhere = " institution_code = ? and product_type = ? " +
        "and future_option_div = ? and online_service_div = ?";
        
        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(ProductTypeEnum.EQUITY);
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
     * �@@�@@�����^�C�v = "����"<BR>
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
        //�@@�@@�����^�C�v = "����" 
        //�@@�@@�敨�^�I�v�V�����敪 = "DEFAULT" 
        //�@@�@@�I�����C���T�[�r�X�敪 = "�蓮����" 
        final String l_strWhere= " institution_code = ? and product_type = ? " +
        "and future_option_div = ? and online_service_div = ?";
        
        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(ProductTypeEnum.EQUITY);
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
     * �P�j�@@�p�����[�^.�،����.get�����J�z�����敪()���\�b�h��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �@@[get�����J�z�����敪()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�����^�C�v�F�@@"����"<BR>
     * �@@�@@�敨�^�I�v�V�����敪�F�@@"DEFAULT"<BR>
     * <BR>
     * �@@null���ԋp���ꂽ�ꍇ�́A�������I������B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l��"�����J�zAP�ďo��"�̏ꍇ�A<BR>
     * �@@�u�����J�z�������ׁ̈A�����s�v�̗�O���X���[����B<BR>
     * �@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_02446<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g
     * @@throws WEB3BaseException 
     * @@roseuid 447264600022
     */
    protected void validateCarryOvering(WEB3GentradeInstitution l_institution) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateCarryOvering(WEB3GentradeInstitution)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�p�����[�^.�،����.get�����J�z�����敪()���\�b�h�� 
        //�@@�R�[������B 
        String l_strCarryoverEndType = 
            l_institution.getCarryoverEndType(ProductTypeEnum.EQUITY, WEB3FuturesOptionDivDef.DEFAULT);

        //�@@null���ԋp���ꂽ�ꍇ�́A�������I������B 
        if (l_strCarryoverEndType == null) 
        {
            log.exiting(l_strCarryoverEndType);
            return;
        }
        //�Q�j�@@�P�j�̖߂�l��"�����J�zAP�ďo��"�̏ꍇ�A 
        //�@@�u�����J�z�������ׁ̈A�����s�v�̗�O���X���[����B 
        else if (WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP.equals(l_strCarryoverEndType))
        {
            log.debug("�����J�z�������ׁ̈A�����s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02446,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�z�������ׁ̈A�����s�B");     
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

    /**
     * ��̔z�����̔z��ɍ������āA�����\�[�g�ŕԋp����
     * @@param l_strArraySrc
     * @@param l_strDest
     * @@return String[]
     */
    private String[] mergeAndSort(String[] l_strArraySrc, String[] l_strDest)
     {
         Object[] l_objMergeArray = WEB3Toolkit.merge(l_strArraySrc, l_strDest);
         l_objMergeArray = WEB3Toolkit.toUnique(l_objMergeArray);
         int l_intMergeArrayCnt = l_objMergeArray.length;
         String[] l_strResults = new String[l_intMergeArrayCnt];
         for (int i = 0; i < l_intMergeArrayCnt; i++)
         {
             l_strResults[i] = (String) l_objMergeArray[i];
         }

         int l_intResultsCnt = l_strResults.length;
         String l_strTemp = null;
         for (int i = 0; i < l_intResultsCnt; i++)
         {
             for (int j = i + 1; j < l_intResultsCnt; j++)
             {
                 if (Integer.parseInt(l_strResults[i]) > Integer.parseInt(l_strResults[j]))
                 {
                     l_strTemp = l_strResults[j];
                     l_strResults[j] = l_strResults[i];
                     l_strResults[i] = l_strTemp;
                 }
             }
         }
         return l_strResults;
     }
}
@
