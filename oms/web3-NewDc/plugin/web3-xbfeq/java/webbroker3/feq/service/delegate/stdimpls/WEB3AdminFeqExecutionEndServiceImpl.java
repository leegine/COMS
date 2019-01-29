head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionEndServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������o���I���T�[�r�XImpl(WEB3AdminFeqExecutionEndServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 �s�p (���u) �V�K�쐬
Revesion History : 2005/08/03 ����(���u) ���r���[
Revesion History : 2007/02/27 �ĉ� (���u) ����002 �{�Ԗ⍇No.Q00144
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.data.FeqOrderexecutionEndDao;
import webbroker3.feq.data.FeqOrderexecutionEndParams;
import webbroker3.feq.data.FeqOrderexecutionEndRow;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndInputRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionEndInputResponse;
import webbroker3.feq.message.WEB3FeqExecutionEndExecuteCondUnit;
import webbroker3.feq.message.WEB3FeqExecutionEndUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionEndService;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionEndUnitService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������o���I���T�[�r�XImpl)<BR>
 * �O�������o���I���T�[�r�X�����N���X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionEndServiceImpl implements WEB3AdminFeqExecutionEndService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionEndServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F002BF
     */
    public WEB3AdminFeqExecutionEndServiceImpl() 
    {
     
    }
    
    /**
     * �O�������o���I���T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[��<BR>����B
     * <BR>
     *    get���͉��()<BR>
     *    validate�o���I��()<BR>
     *    submit�o���I��()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 421A945400A5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqExecutionEndInputRequest)
        {
            //get���͉��()
            l_response = 
                this.getInputScreen((WEB3AdminFeqExecutionEndInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqExecutionEndConfirmRequest)
        {
            //validate�o���I��()
            l_response = 
                this.validateExecEnd((WEB3AdminFeqExecutionEndConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqExecutionEndCompleteRequest)
        {
            //submit�o���I��()
            l_response = 
                this.submitExecEnd((WEB3AdminFeqExecutionEndCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʎ擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�i�ǁj�o���I���jget���͉�ʁv �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionEndInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 421A96D303C2
     */
    protected WEB3AdminFeqExecutionEndInputResponse getInputScreen(
        WEB3AdminFeqExecutionEndInputRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqExecutionEndInputRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.debug("�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B");
        }
        
        //1.2:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.3:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.4:get�戵�\�s��(String, ProductTypeEnum)(
        String[] l_strHandlingPossMarkets = 
            WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode, 
                ProductTypeEnum.FOREIGN_EQUITY);
        
        //1.5: create�o���I���ꗗ(String, String[])
        WEB3FeqExecutionEndUnit[] l_ExecEndUnits = 
            this.createExecEndList(l_strInstitutionCode, l_strHandlingPossMarkets);
        
        //1.6:createResponse( )
        WEB3AdminFeqExecutionEndInputResponse l_response = 
            (WEB3AdminFeqExecutionEndInputResponse)l_request.createResponse();
        
        //1.7:(*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B
        //�o���I���ꗗ  ���@@create�o���I���ꗗ()�̖߂�l
        l_response.executionEndList = l_ExecEndUnits;
        
        log.exiting(STR_METHOD_NAME);
        
        //1.8
        return l_response;
    }
    
    /**
     * (validate�o���I��)<BR>
     * �o���I�������̊m�F���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�i�ǁj�o���I���jvalidate�o���I���v �Q��<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�i�ǁj�o���I���v(�i�ǁj�o���I���jvalidate�o���I��)<BR>
     * �@@�@@:  1.5.1 get�O���o���I��<BR> 
     * �@@�@@null���ԋp���ꂽ�ꍇ�́A<BR> 
     * �@@�@@�u�Y���f�[�^�Ȃ��v�̗�O<BR> 
     * <BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01037<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�i�ǁj�o���I���v(�i�ǁj�o���I���jvalidate�o���I��)<BR>
     * �@@�@@:  1.5.3 is�o���I���\<BR> 
     * �@@�@@false���ԋp���ꂽ�ꍇ�A<BR> 
     * �@@�@@�u�������G���[�v�̗�O���X���[����B<BR> 
     * <BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02160<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionEndConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 421A96D303E1
     */
    protected WEB3AdminFeqExecutionEndConfirmResponse validateExecEnd(
        WEB3AdminFeqExecutionEndConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateExecEnd(WEB3AdminFeqExecutionEndConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1: validate( )
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            log.debug("�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B");
        }
        
        //1.3:validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)(
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5:(*)���N�G�X�g�f�[�^.�o���I�����{�ꗗ�̗v�f����Loop����
        if (l_request.executionEndExecuteCondList != null && 
            l_request.executionEndExecuteCondList.length > 0)
        {            
            int l_intCnt = l_request.executionEndExecuteCondList.length;
            
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqExecutionEndExecuteCondUnit l_execEndCondUnit = 
                    l_request.executionEndExecuteCondList[i];
                
                if (l_execEndCondUnit != null)
                {
                    //1.5.1:get�O���o���I��(String, String[])
                    FeqOrderexecutionEndParams[] l_orderExecEndParamses = 
                        this.getFeqExecEnd(l_strInstitutionCode, 
                        new String[]{l_execEndCondUnit.marketCode});
                    
                    if (l_orderExecEndParamses == null || l_orderExecEndParamses.length == 0)
                    {
                        log.debug("�����ɊY������O���o���I�������݂��Ȃ��B");
                        log.exiting(STR_METHOD_NAME);                        
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                            this.getClass().getName() + STR_METHOD_NAME, 
                            "�����ɊY������O���o���I�������݂��Ȃ��B");
                    }
                    else if(l_orderExecEndParamses.length != 1)
                    {
                        log.debug("�O���o���I�����f�[�^�s�����G���[�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }
                    
                    //1.5.2:(*)�����敪�`�F�b�N
                    //get�O���o���I��()�̖߂�l.�����敪 == "�G���["�̏ꍇ ����
                    //�����Ώۂ̔����� == �O���o���I��.�O����{����YYYYMMDD�̏ꍇ�A
                    //���̗v�f�֏������ڍs����B(continue)
                    //���������\�Ƃ���ׁB
                    boolean l_blnFlag = WEB3CarryoverEndTypeDef.ERROR.equals(
                        l_orderExecEndParamses[0].getStatus());
                    Date l_datOrderBizDate = l_execEndCondUnit.orderBizDate;
                    Date l_datLastExec = l_orderExecEndParamses[0].getLastExecuteDate();
                    int l_intFlag = WEB3DateUtility.compareToDay(
                        l_datOrderBizDate,
                        l_datLastExec);   
                        
                    if (l_blnFlag && l_intFlag == 0)
                    {
                        continue;
                    }                    
                    
                    //1.5.3: is�o���I���\(Date, Date)
                    boolean l_blnIsExecEndPoss = this.isExecEndPossible(
                        l_datLastExec,
                        l_datOrderBizDate);
                        
                    //false���ԋp���ꂽ�ꍇ�A
                    //�u�������G���[�v�̗�O���X���[����B
                    if (!l_blnIsExecEndPoss)
                    {
                        log.debug("�������G���[�B");
                        log.exiting(STR_METHOD_NAME);                        
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02160,
                            this.getClass().getName() + STR_METHOD_NAME, 
                            "�������G���[�B");
                    }
                }
            }
        }
        
        //1.6:createResponse( )
        WEB3AdminFeqExecutionEndConfirmResponse l_response = 
            (WEB3AdminFeqExecutionEndConfirmResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        //1.7
        return l_response;
    }
    
    /**
     * (submit�o���I��)<BR>
     * �o���I���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�i�ǁj�o���I���jsubmit�o���I���v �Q��<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) / <BR>
     * �@@�i�ǁj�o���I���v(�i�ǁj�o���I���jsubmit�o���I��)<BR>
     * �@@�@@:  1.6.3 is�o���I���\<BR> 
     * �@@�@@false���ԋp���ꂽ�ꍇ�A<BR> 
     * �@@�@@�u�������G���[�v�̗�O���X���[����B<BR> 
     * <BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02160<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionEndCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 421A96D40009
     */
    protected WEB3AdminFeqExecutionEndCompleteResponse submitExecEnd(
        WEB3AdminFeqExecutionEndCompleteRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitExecEnd(WEB3AdminFeqExecutionEndCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1: validate( )
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        if (l_admin == null)
        {
            log.debug( "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                 "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B");
        }
        
        //1.3:validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)(
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4:validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6:(*)���N�G�X�g�f�[�^.�o���I�����{�ꗗ�̗v�f����Loop����
        if (l_request.executionEndExecuteCondList != null && 
            l_request.executionEndExecuteCondList.length > 0)
        {            
            int l_intCnt = l_request.executionEndExecuteCondList.length;
            
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqExecutionEndExecuteCondUnit l_execEndCondUnit = 
                    l_request.executionEndExecuteCondList[i];
                
                if (l_execEndCondUnit != null)
                {
                    //1.6.1:get�O���o���I��(String, String[])
                    
                    FeqOrderexecutionEndParams[] l_orderExecEndParamses = 
                        this.getFeqExecEnd(l_strInstitutionCode, 
                        new String[]{l_execEndCondUnit.marketCode});

                    //null���ԋp���ꂽ�ꍇ�́A
                    //���̗v�f�֏������ڍs����B(continue)
                    if (l_orderExecEndParamses == null || l_orderExecEndParamses.length == 0)
                    {                        
                        continue;
                    }
                    else if(l_orderExecEndParamses.length != 1)
                    {
                        log.debug("�O���o���I�����f�[�^�s�����G���[�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }
                    
                    //1.6.2:(*)�����敪�`�F�b�N
                    //get�O���o���I��()�̖߂�l.�����敪 == "�G���["�̏ꍇ ����
                    //�����Ώۂ̔����� == �O���o���I��.�O����{����YYYYMMDD�̏ꍇ�A
                    //���̗v�f�֏������ڍs����B(continue)
                    //���������\�Ƃ���ׁB
                    boolean l_blnFlag = WEB3CarryoverEndTypeDef.ERROR.equals(
                        l_orderExecEndParamses[0].getStatus());
                    Date l_datOrderBizDate = l_execEndCondUnit.orderBizDate;
                    Date l_datLastExec = l_orderExecEndParamses[0].getLastExecuteDate();
                    int l_intFlag = WEB3DateUtility.compareToDay(
                        l_datOrderBizDate,
                        l_datLastExec);
                        
                    if (l_blnFlag && l_intFlag == 0)
                    {
                        continue;
                    }  
                    
                    //1.6.3: is�o���I���\(Date, Date)
                    boolean l_blnIsExecEndPoss = this.isExecEndPossible(
                        l_datLastExec,
                        l_datOrderBizDate);
                    
                    //false���ԋp���ꂽ�ꍇ�A
                    //�u�������G���[�v�̗�O���X���[����B
                    if (!l_blnIsExecEndPoss)
                    {
                        log.debug("�������G���[�B");
                        log.exiting(STR_METHOD_NAME);                        
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02160,
                            this.getClass().getName() + STR_METHOD_NAME, 
                            "�������G���[�B");
                    }
                }
            }
        }
        
        //1.7:(*)���N�G�X�g�f�[�^.�o���I�����{�ꗗ�̗v�f����Loop����
        if (l_request.executionEndExecuteCondList != null && 
            l_request.executionEndExecuteCondList.length > 0)
        {                        
            int l_intCnt = l_request.executionEndExecuteCondList.length;
            
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqExecutionEndExecuteCondUnit l_execEndCondUnit = 
                    l_request.executionEndExecuteCondList[i];
        
                if (l_execEndCondUnit != null)
                {
                    String l_strMarketCode = l_execEndCondUnit.marketCode;
                    Date l_datOrderBizDate = l_execEndCondUnit.orderBizDate;
                    
                    //1.7.1:get�����Ώیڋq�ꗗ(String, String, Date)
                    WEB3GentradeMainAccount[] l_mainAccounts = this.getDealTargetAccountList(
                        l_strInstitutionCode, 
                        l_strMarketCode, 
                        l_datOrderBizDate);
                        
                    int l_intDealCnt = 0;
                        
                    //1.7.2: (*)get�����Ώیڋq�ꗗ()�̗v�f����Loop����
                    if (l_mainAccounts != null && l_mainAccounts.length > 0)
                    {
                        int l_intMainAccountCnt = l_mainAccounts.length;
                                                                        
                        for (int j = 0; j < l_intMainAccountCnt; j++)
                        {
                            WEB3GentradeMainAccount l_mainAccount = l_mainAccounts[j];
                            
                            //1.7.2.1:exec�o���I��(�ڋq, String, Date)
                            WEB3AdminFeqExecutionEndUnitService l_execEndUnitService = 
                                (WEB3AdminFeqExecutionEndUnitService)Services.getService(
                                    WEB3AdminFeqExecutionEndUnitService.class);
                            
                            if (l_execEndUnitService == null)
                            {
                                log.debug("�O�������o���I��UnitServiceImpl�����݂��Ȃ��B");
                                log.exiting(STR_METHOD_NAME);
                                throw new WEB3SystemLayerException(
                                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                                     this.getClass().getName() + STR_METHOD_NAME,
                                    "�O�������o���I��UnitServiceImpl�����݂��Ȃ��B");
                            }
                            
                            try
                            {
                                l_execEndUnitService.execExecEnd(
                                    l_mainAccount, 
                                    l_strMarketCode, 
                                    l_datOrderBizDate);
                            }
                            //��O���X���[���ꂽ�ꍇ�A���̌ڋq�֏������ڍs����B(continue)
                            catch (Exception l_ex)
                            {
                                log.error("exec�o���I��(�ڋq�R�[�h: " + l_mainAccount.getAccountCode()
                                    + " �s��R�[�h: " + l_strMarketCode
                                    + " ������: " + l_datOrderBizDate + ")",
                                    l_ex);

                                l_intDealCnt++;
                                continue;
                            }
                        }
                    }
                    //1.7.3:update�O���o���I��(String, String, Date, String)
                    String l_strStatus = null;
                    //[exec�o���I��()�ɂĈꌏ����O���X���[����Ȃ������ꍇ] 
                    if (l_intDealCnt == 0)
                    {
                        //"1�F������"���Z�b�g 
                        l_strStatus = WEB3CarryoverEndTypeDef.COMPLETE_PROCESS;
                    }
                    //[��L�ȊO] 
                    else
                    {
                        // "9�F�G���["���Z�b�g
                        l_strStatus = WEB3CarryoverEndTypeDef.ERROR;
                    }
                    this.updateFeqExecEnd(l_strInstitutionCode, 
                        l_strMarketCode,
                        l_datOrderBizDate,
                        l_strStatus);
                }
            }
        }
        
        //1.8: get�戵�\�s��(String, ProductTypeEnum)
        String[] l_strHandlingPossMarkets = 
            WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode, 
                ProductTypeEnum.FOREIGN_EQUITY);
        
        //1.9:get�O���o���I��(String, String[])
        FeqOrderexecutionEndParams[] l_paramses = this.getFeqExecEnd(
            l_strInstitutionCode, 
            l_strHandlingPossMarkets);
                    
        if (l_paramses == null || l_paramses.length == 0)
        {
            log.debug("�����ɊY������O���o���I�������݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);                        
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�����ɊY������O���o���I�������݂��Ȃ��B");
        }
       
        //1.10:(*)get�O���o���I��()�̑S�v�f.�����敪 == "������"�̏ꍇ
        boolean l_blnIsAll = false;
        if (l_paramses != null && l_paramses.length > 0)
        {
            int l_intParamsCnt = l_paramses.length;
            
            for (int i = 0; i < l_intParamsCnt; i++)
            {               
                FeqOrderexecutionEndParams l_params = l_paramses[i]; 
                if (l_params != null)
                {
                    if (WEB3CarryoverEndTypeDef.COMPLETE_PROCESS.equals(l_params.getStatus()))
                    {
                        l_blnIsAll = true;                
                    }
                    else 
                    {
                        l_blnIsAll = false;                        
                        break;            
                    } 
                }               
            }
        }        
        
        if (l_blnIsAll)
        {            
            //1.10.1: (*)�o���I��Params�𐶐�����B
            //(*)�o���I��Params�𐶐����A�v���p�e�B���Z�b�g����B
            //�Z�b�g����v���p�e�B�́A
            //�yxTrade�z�⑫����.DB�X�V\\20.(��)�o���I���u�O���o���I��_�o���I���e�[�u���d�l.xls�v�Q�ƁB
            OrderexecutionEndParams l_orderExceEndParams = new OrderexecutionEndParams();
            
            //institution_code:�Ǘ���.�،���ЃR�[�h
            l_orderExceEndParams.setInstitutionCode(l_strInstitutionCode);
            
            //product_type:4�F�O������
            l_orderExceEndParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            
            //future_option_div:0�FDEFAULT
            l_orderExceEndParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            
            //carryover_end_type:0�F������
            l_orderExceEndParams.setCarryoverEndType(WEB3CarryoverEndTypeDef.NOT_STARTED_PROCESS);
            
            //created_timestamp:���ݎ���
            Timestamp l_tsNowTime = GtlUtils.getSystemTimestamp();
            
            l_orderExceEndParams.setCreatedTimestamp(l_tsNowTime);
            
            //last_updated_timestamp:���ݎ���
            l_orderExceEndParams.setLastUpdatedTimestamp(l_tsNowTime);
            
            //1.10.2:insertRow(l_row : Row)
            try
            {
                WEB3DataAccessUtility.insertRow(
                    l_orderExceEndParams);//DataNetworkException, DataQueryException
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }  
        }

        
        //1.11:createResponse( )
        WEB3AdminFeqExecutionEndCompleteResponse l_response = 
            (WEB3AdminFeqExecutionEndCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        
        //1.12
        return l_response;
    }
    
    /**
     * (get�O���o���I��)<BR>
     * �����̏،���ЃR�[�h�A�s��R�[�h�ɊY������s��<BR>
     * �O���o���I���e�[�u�����擾����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����ɂĊO���o���I���e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h ����<BR>
     * �@@�@@�s��R�[�h in (�p�����[�^.�s��R�[�h)<BR>
     * <BR>
     * ���������ʂ́A�s��R�[�h�ŏ����\�[�g���A�擾���邱�ƁB<BR>
     * <BR> 
     * �@@�Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�擾�����������ʂ�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strMarketCodes - (�s��R�[�h)<BR>
     * �s��R�[�h�̈ꗗ<BR>
     * @@return FeqOrderexecutionEndParams[]
     * @@throws WEB3BaseException
     * @@roseuid 42AFD97900C3
     */
    protected FeqOrderexecutionEndParams[] getFeqExecEnd(
        String l_strInstitutionCode, 
        String[] l_strMarketCodes) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getFeqExecEnd(String, String[]) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_strMarketCodes == null || l_strMarketCodes.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�s��R�[�h�����w��ł��B");
        }
        
        try
        {
            //�P�j�@@DB����
            //�ȉ��̏����ɂĊO���o���I���e�[�u������������B
            //[����]
            //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h ����
            //�s��R�[�h in (�p�����[�^.�s��R�[�h)
            String l_strWhere = " institution_code = ? and market_code in (";
            
            int l_intCnt = l_strMarketCodes.length;
            
            Object[] l_objs = new Object[l_intCnt + 1];
            
            l_objs[0] = l_strInstitutionCode;
                
            for (int i = 0; i < l_intCnt; i++)
            {
                l_strWhere += "?, ";
                l_objs[i + 1] = l_strMarketCodes[i];
            }
            
            l_strWhere = l_strWhere.substring(0, l_strWhere.length() - 2) + ") ";
            
            //���������ʂ́A�s��R�[�h�ŏ����\�[�g���A�擾���邱�ƁB
            String l_strOrderby = "market_code";
            
            QueryProcessor l_queryProcessor = 
                Processors.getDefaultProcessor();//DataNetworkException,DataQueryException    
            List l_lisOrderexecEndRows = l_queryProcessor.doFindAllQuery(
                FeqOrderexecutionEndRow.TYPE, 
                l_strWhere,
                l_strOrderby,
                null,
                l_objs);//DataNetworkException, DataQueryException
            
            //�Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B
            //�Q�j�@@�擾�����������ʂ�ԋp����B
            FeqOrderexecutionEndParams[] l_params = null;
            if (l_lisOrderexecEndRows != null && !l_lisOrderexecEndRows.isEmpty())
            {
                int l_intRowsCnt = l_lisOrderexecEndRows.size();
                l_params = new FeqOrderexecutionEndParams[l_intRowsCnt];
                for (int i = 0; i < l_intRowsCnt; i++)
                {
                    l_params[i] = new FeqOrderexecutionEndParams(
                        (FeqOrderexecutionEndRow)l_lisOrderexecEndRows.get(i));
                }                
            }            
            return l_params;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                
    }
    
    /**
     * (create�o���I���ꗗ)<BR>
     * �����̏،���ЃR�[�h�A�s��R�[�h�ꗗ���A<BR>
     * �O�������o���I�����ׂ̈ꗗ���쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@this.get�O���o���I��()���R�[������B<BR>
     * <BR>
     * �@@�@@[get�O���o���I��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�p�����[�^.�s��R�[�h�ꗗ <BR>
     * <BR>
     * �R�j�@@�Q�j�̖߂�l�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
     *  �R�|�P�j�@@�O�������o���I�����׃C���X�^���X�𐶐�����B<BR>
     *  �R�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>           
     * �@@�@@�@@�s��R�[�h = �����Ώۂ̗v�f.�s��R�[�h<BR>
     * �@@�@@�@@�s�ꖼ = �����Ώۂ̗v�f.�s��R�[�h�ɊY������s��.�s�ꖼ<BR>
     * �@@�@@�@@�����敪 = �����Ώۂ̗v�f.�����敪<BR>
     * �@@�@@�@@�O�񔭒��� = �����Ώۂ̗v�f.�O����{�� <BR>
     * �@@�@@�@@�O��o���I������ = �����Ώۂ̗v�f.�X�V���t <BR>
     * <BR>
     * �@@�Q�|�T�j�@@ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strMarketCodeList - (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�ꗗ<BR>
     * @@return WEB3FeqExecutionEndUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42AFDEE703C0
     */
    protected WEB3FeqExecutionEndUnit[] createExecEndList(
        String l_strInstitutionCode, 
        String[] l_strMarketCodeList) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createExecEndList(String, String[]) ";
        log.entering(STR_METHOD_NAME);
        
        
            //�P�j�@@ArrayList�𐶐�����B
            List l_lisExecEndUnit = new ArrayList();
        
            //�Q�j this.get�O���o���I��()���R�[������B
            FeqOrderexecutionEndParams[] l_orderExecEndPraramses = 
                this.getFeqExecEnd(l_strInstitutionCode, l_strMarketCodeList); 
            if (l_orderExecEndPraramses == null)
            {
                log.error("�O���o���I���e�[�u�������݂��Ȃ�");

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�O���o���I���e�[�u�������݂��Ȃ�" );
            }
           
            //�R�j�@@�Q�j�̖߂�l�̗v�f�����ȉ��̏������J��Ԃ��B
            if (l_orderExecEndPraramses != null && l_orderExecEndPraramses.length > 0)
            {
                int l_intCnt = l_orderExecEndPraramses.length;
                
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                if (l_finApp == null)
                {
                    log.debug("FinApp�����݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "FinApp�����݂��Ȃ��B");
                }
    
                WEB3GentradeFinObjectManager l_finObjectMgr =
                    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                if (l_finObjectMgr == null)
                {
                    log.debug("FinObjectManager�����݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "FinObjectManager�����݂��Ȃ��B");
                }
    
                for (int i = 0; i < l_intCnt; i++)
                {
                    if (l_orderExecEndPraramses[i] != null)
                    {
                        FeqOrderexecutionEndParams l_orderExecEndPrarams = l_orderExecEndPraramses[i];
        
                        //�R�|�P�j�@@�O�������o���I�����׃C���X�^���X�𐶐�����B
                        WEB3FeqExecutionEndUnit l_executionEndUnit = new WEB3FeqExecutionEndUnit();
    
                        //�R�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                        //�s��R�[�h = �����Ώۂ̗v�f.�s��R�[�h
                        l_executionEndUnit.marketCode = l_orderExecEndPrarams.getMarketCode();
                        try
                        {
                        //�s�ꖼ = �����Ώۂ̗v�f.�s��R�[�h�ɊY������s��.�s�ꖼ
                        Market l_market = l_finObjectMgr.getMarket(
                            l_strInstitutionCode, 
                            l_orderExecEndPrarams.getMarketCode());//NotFoundException
                            
                            l_executionEndUnit.marketName = l_market.getMarketName();
                        } 
                        catch (NotFoundException l_ex)
                        {
                            log.error("�s��e�[�u���ɊY������f�[�^������܂���B", l_ex);

                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        }        

                        //�����敪 = �����Ώۂ̗v�f.�����敪
                        l_executionEndUnit.executionEndDiv = l_orderExecEndPrarams.getStatus();
    
                        //�O�񔭒��� = �����Ώۂ̗v�f.�O����{��
                        l_executionEndUnit.previousOrderBizDate = 
                            WEB3DateUtility.toDay(l_orderExecEndPrarams.getLastExecuteDate());
        
                        //�O��o���I������ = �����Ώۂ̗v�f.�X�V���t
                        l_executionEndUnit.previousExecutionEndTime = 
                            l_orderExecEndPrarams.getLastUpdatedTimestamp();
            
                        //�Q�|�T�j�@@ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
                        l_lisExecEndUnit.add(l_executionEndUnit);
                    }
                }
            }
    
            //�R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B 
            WEB3FeqExecutionEndUnit[] l_execEndUnits = null;
    
            if (!l_lisExecEndUnit.isEmpty())
            {
                l_execEndUnits = new WEB3FeqExecutionEndUnit[l_lisExecEndUnit.size()];
                l_lisExecEndUnit.toArray(l_execEndUnits);
            }
    
            log.exiting(STR_METHOD_NAME);
    
            return l_execEndUnits;
        
    }
    
    /**
     * (is�o���I���\)<BR>
     * �����̔�����(null�̏ꍇ�́A������ԊǗ�.get������()�̖߂�l)��<BR>
     * �o���I���������\���ǂ������ʂ���B<BR>
     * �o���I���\�ł����true���A�ȊOfalse��ԋp����B<BR>
     * <BR>
     * �P�j�@@�o���I���\�`�F�b�N<BR>
     * �@@�ȉ��̏����ɊY������ꍇ�́Atrue��ԋp����B<BR>
     * �@@�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * �@@�p�����[�^.�O����{�� < �p�����[�^.������ <= �Ɩ����t(*1)<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_datLastExecuteDate - (�O����{��)<BR>
     * �O����{��<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@return Boolean
     * @@throws WEB3BaseException
     * @@roseuid 42AFE1580314
     */
    protected boolean isExecEndPossible(Date l_datLastExecuteDate, Date l_datBizDate) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isExecEndPossible(Date, Date) ";
        log.entering(STR_METHOD_NAME);
        
        //�����̔�����(null�̏ꍇ�́A������ԊǗ�.get������()�̖߂�l)
        if (l_datBizDate == null)
        {                        
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();                        
        }
        
        //�P�j�@@�o���I���\�`�F�b�N
        //�ȉ��̏����ɊY������ꍇ�́Atrue��ԋp����B
        //�ȊO�Afalse��ԋp����B
        //�p�����[�^.�O����{�� < �p�����[�^.������ <= �Ɩ����t(*1)
        TradingSystem l_ts = GtlUtils.getTradingSystem();
        if (l_ts == null)
        {
            log.debug("TradingSystem�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "TradingSystem�����݂��Ȃ��B");
        }
              
        Date l_datBusDate = l_ts.getBizDate();
        int l_intLastCompareBiz = WEB3DateUtility.compareToDay(l_datLastExecuteDate, l_datBizDate);
        int l_intBizCompareBus = WEB3DateUtility.compareToDay(l_datBizDate, l_datBusDate);
        
        if (l_intLastCompareBiz < 0 && l_intBizCompareBus <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;                
    }
    
    /**
     * (get�����Ώیڋq�ꗗ)<BR>
     * �o���I�������ΏۂƂȂ钍����ێ�����<BR>
     * �ڋq�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����P�ʌ���<BR>
     * �@@�O�����������}�l�[�W��.get�o���I���Ώے����P��()���R�[������B<BR>
     * <BR>
     * �@@[get�o���I���Ώے����P��()�Ɏw�肷�����]<BR>
     * �@@�@@����ID�F�@@null<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�s��R�[�h�F�@@�p�����[�^.�s��R�[�h<BR>
     * �@@�@@�������F�@@�p�����[�^.������<BR>
     * <BR>
     * �@@null���ԋp���ꂽ�ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�I�u�W�F�N�g�쐬<BR>
     * �@@�Q�|�P�j�P�j�̌������ʂɂ��āA���j�[�N�Ȍ���ID�̈ꗗ���쐬����B<BR>
     * �@@�Q�|�Q�j�Q�|�P�j�ɂč쐬��������ID�̈ꗗ���A�ڋq�I�u�W�F�N�g���쐬���A<BR>
     * �@@�@@�z��Ƃ��ĕԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@return WEB3GentradeMainAccount[]
     * @@throws WEB3BaseException
     * @@roseuid 42B7E7A30301
     */
    protected WEB3GentradeMainAccount[] getDealTargetAccountList(
        String l_strInstitutionCode, 
        String l_strMarketCode, 
        Date l_datBizDate) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDealTargetAccountList(String, String, Date) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�P�j�@@�����P�ʌ���
            //�O�����������}�l�[�W��.get�o���I���Ώے����P��()���R�[������B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            if (l_finApp == null)
            {
                log.debug("FinApp�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinApp�����݂��Ȃ��B");
            }
            
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            if (l_tradingModule == null)
            {
                log.debug("TradingModule�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "TradingModule�����݂��Ȃ��B");
            }
            
            WEB3FeqOrderManager l_orderMgr = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            if (l_orderMgr == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�O�����������}�l�[�W�������݂��Ȃ��B");
            }
            WEB3FeqOrderUnit[] l_orderUnits = l_orderMgr.getOrderExecEndObjectOrderUnit(
                null,
                l_strInstitutionCode, 
                l_strMarketCode,
                l_datBizDate);        
            WEB3GentradeMainAccount[] l_mainAccounts= null;
            List l_lisMainAccounts = new ArrayList();
            
            //null���ԋp���ꂽ�ꍇ�Anull��ԋp����B        
            if (l_orderUnits != null && l_orderUnits.length > 0)
            {                
                //�Q�j�@@�ڋq�I�u�W�F�N�g�쐬
                //�Q�|�P�j�P�j�̌������ʂɂ��āA���j�[�N�Ȍ���ID�̈ꗗ���쐬����B            
                //�Q�|�Q�j�Q�|�P�j�ɂč쐬��������ID�̈ꗗ���A�ڋq�I�u�W�F�N�g���쐬���A
                //�z��Ƃ��ĕԋp����B                
                WEB3GentradeAccountManager l_accMgr = 
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                if (l_accMgr == null)
                {
                    log.debug("�g���A�J�E���g�}�l�[�W�������݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�g���A�J�E���g�}�l�[�W�������݂��Ȃ��B");
                }
                
                int l_intCnt = l_orderUnits.length;
                
                log.debug("�P�j�̌������� > 0," + l_intCnt);
                
                List l_lisAccountId = new ArrayList();
                
                for (int i = 0; i < l_intCnt; i++)
                {
                    WEB3FeqOrderUnit l_orderUnit = l_orderUnits[i];
    
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
                
                for (int i = 0; i < l_intAccountCnt; i++)
                {
                    Long l_accountId = (Long)l_lisAccountId.get(i);
                    
                    WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)
                        l_accMgr.getMainAccount(l_accountId.longValue());//NotFoundException
                       
                    l_lisMainAccounts.add(l_mainAccount);
                }
                
                if (!l_lisMainAccounts.isEmpty())
                {
                    l_mainAccounts = new WEB3GentradeMainAccount[l_lisMainAccounts.size()];
                    l_lisMainAccounts.toArray(l_mainAccounts);
                }
            }     
            
            log.exiting(STR_METHOD_NAME);   
            
            return l_mainAccounts;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
    }
    
    /**
     * (update�O���o���I��)<BR>
     * �����̏����ɊY������O���o���I���e�[�u����<BR>
     * udpate����B<BR>
     * <BR>
     * �p�����[�^.�،���ЃR�[�h�A�p�����[�^.�s��R�[�h��<BR>
     * �Y������O���o���I���e�[�u���̃f�[�^��update����B<BR>
     * <BR>
     * �X�V���e�́A<BR>
     * �yxTrade�z�⑫����.DB�X�V\\20.(��)�o���I��<BR>
     * �u�O���o���I��_�O���o���I���e�[�u���d�l.xls�v<BR>
     * �@@�Q�ƁB<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@param l_strStatus - (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�@@������<BR>
     * 1�F�@@������<BR>
     * 9�F�@@�G���[<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B7E7A3033F
     */
    protected void updateFeqExecEnd(
        String l_strInstitutionCode, 
        String l_strMarketCode, 
        Date l_datBizDate, 
        String l_strStatus) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateFeqExecEnd(String, String, Date, String) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�p�����[�^.�،���ЃR�[�h�A�p�����[�^.�s��R�[�h��
            //�Y������O���o���I���e�[�u���̃f�[�^��update����B
            FeqOrderexecutionEndRow l_orderexecEndRow = 
                FeqOrderexecutionEndDao.findRowByInstitutionCodeMarketCode(
                    l_strInstitutionCode, l_strMarketCode);//DataNetworkException,DataQueryException
            
            if (l_orderexecEndRow != null)
            {
                log.debug("�O���o���I���e�[�u���̃f�[�^ != null");
                
                FeqOrderexecutionEndParams l_orderexecEndParams = 
                    new FeqOrderexecutionEndParams(l_orderexecEndRow);
                
                //last_execute_date:������(* ��ʓ��͒l)
                l_orderexecEndParams.setLastExecuteDate(l_datBizDate);
                
                //�����敪:��O�����������ꍇ�́A9�F�G���[
                //�ȊO�A1�F������
                l_orderexecEndParams.setStatus(l_strStatus);

                //last_updater:�Z�b�V������胍�O�C���h�c���擾�A���O�C���h�c�ɊY������Ǘ���.�Ǘ��҃R�[�h�B
                //���O�C���h�c���擾�ł��Ȃ��ꍇ�́Anull�B
                OpLoginSecurityService l_opLoginSec =
                    (OpLoginSecurityService) Services.getService(
                        OpLoginSecurityService.class);
                if (l_opLoginSec == null)
                {
                    log.debug("���O�C���Z�L�����e�B�T�[�r�X�����݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���O�C���Z�L�����e�B�T�[�r�X�����݂��Ȃ��B");
                }        
                
                LoginInfo l_loginInfo = l_opLoginSec.getLoginInfo(); 
                if (l_loginInfo == null)
                {
                    log.debug("LoginInfo�����݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "LoginInfo�����݂��Ȃ��B");
                } 
                
                long l_lngLoginId = l_loginInfo.getLoginId();
                
                AdministratorRow l_administratorRow =
                    AdministratorDao.findRowByLoginId(l_lngLoginId);//DataNetworkException,DataQueryException
                                
                if (l_administratorRow != null)
                {
                    l_orderexecEndParams.setLastUpdater(l_administratorRow.getAdministratorCode());
                }
                else
                {
                    l_orderexecEndParams.setLastUpdater(null);
                }
                  
                //last_updated_timestamp:���ݎ���
                l_orderexecEndParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                QueryProcessor l_queryProcessor = 
                    Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
                l_queryProcessor.doUpdateQuery(
                    l_orderexecEndParams);//DataNetworkException,DataQueryException                    
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        log.exiting(STR_METHOD_NAME);
    }
}
@
