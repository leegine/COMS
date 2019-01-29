head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualExpireServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�蓮�����T�[�r�XImpl(WEB3AdminToManualExpireServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/17�@@�A����(���u) �V�K�쐬
                 : 2006/08/23�@@�юu��(���u) �d�l�ύX���f��No.068,089
                 : 2006/10/18�@@�ęԍg(���u) �d�l�ύX���f��No.093�C097                 
                 : 2006/11/28�@@����(���u) �d�l�ύX���f��No.115 
                 : 2006/11/20 ����(���u) ���f�� 112                
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.comm.client.CommunicationException;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.ServerConnector;
import com.fitechlabs.xtrade.kernel.comm.client.ServerException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.db.ServerConfigRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToLapseTargetOrderCondition;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseInputResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseRunRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseRunResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseStatusRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseStatusResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToManualExpireService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DaemonTriggerTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3ServerUrlAccessorDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�XImpl)<BR>
 * �iWEB3AdminToManualExpireServiceImpl�j<BR>
 * �g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�����N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminToManualExpireServiceImpl 
    extends WEB3ClientRequestService implements WEB3AdminToManualExpireService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToManualExpireServiceImpl.class);
    
    /**
     * ServerAccessor�I�u�W�F�N�g<BR>
     * <BR>
     * �����������e�T�[�o�ɐU�蕪����B<BR>
     */
    private ServerAccessor accessor;
    
    /**
     * (�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�XImpl)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �P�j�N���X�^�����O��T�[�o�[URL���擾����B<BR>
     * �@@�@@QueryProcessor.doFindAllQuery()���\�b�h��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@ServerConfigRow.TYPE<BR>
     * �@@�@@�@@arg1�F�@@"config_categ = ?"<BR>
     * �@@�@@�@@arg2�F�@@"cluster.urls"�݂̂�v�f�Ƃ���z��<BR>
     * <BR>
     * �@@�@@��"cluster.urls"�͒萔��`�N���X��common.define��<BR>
     * �@@�@@�@@�쐬���邱�ƁB<BR>
     * <BR>
     * �@@�@@�������ʂ̊e�v�f.config_value���擾���A������z���<BR>
     * �@@�@@�쐬����B<BR>
     * �@@�@@���������ʂ��擾�ł��Ȃ������ꍇ�A�u�Y���f�[�^�Ȃ��v��<BR>
     * �@@�@@�@@�V�X�e���G���[���X���[����B<BR>
     *       class        : WEB3SystemLayerException <BR>
     *       tag          : SYSTEM_ERROR_80005 <BR>
     * <BR>
     * �Q�j�@@ServerAccessor�̍쐬<BR>
     * �@@ServerConnector.createAccessor()���\�b�h���R�[�����A<BR>
     * �@@�߂�l��this.accessor�ɃZ�b�g����B<BR>
     * <BR>
     * �@@[craeteAccessor()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�iACCESSOR_KEY�j�F�@@"web3-static-cluster"<BR>
     * �@@�@@arg1�iURL�j�F�@@�P�j�ɂč쐬����������z��<BR>
     * <BR>
     * �@@�@@��"web3-static-cluster"�͒萔��`�N���X��common.define��<BR>
     * �@@�@@�@@�쐬���邱�ƁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 440BE6F103D1
     */
    public WEB3AdminToManualExpireServiceImpl() throws WEB3BaseException
    {

    }
    
    /**
     * �蓮�����������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���g���K�[�����Ǘ��ҁE�蓮�������̓��N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.get���͉��()���R�[������B<BR>
     * <BR>
     * ���g���K�[�����Ǘ��ҁE�蓮�����m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.validate�蓮����()���R�[������B<BR>
     * <BR>
     * ���g���K�[�����Ǘ��ҁE�蓮���������N�����N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.run�蓮����()���R�[������B<BR>
     * <BR>
     * ���g���K�[�����Ǘ��ҁE�蓮���������X�e�[�^�X�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�@@this.validate�����X�e�[�^�X()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44054D4D0212
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
        if (l_request instanceof WEB3AdminToManualLapseInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminToManualLapseInputRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToManualLapseConfirmRequest)
        {
            l_response = this.validateManualExpire((WEB3AdminToManualLapseConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToManualLapseRunRequest)
        {
            l_response = this.runManualExpire((WEB3AdminToManualLapseRunRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToManualLapseStatusRequest)
        {
            l_response = this.validateStatus((WEB3AdminToManualLapseStatusRequest) l_request);
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
     * �蓮�����������͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮�������̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToManualLapseInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 44054DD801D4
     */
    protected WEB3AdminToManualLapseInputResponse getInputScreen(WEB3AdminToManualLapseInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToManualLapseInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_MANUAL_EXPIRE, true);
        
        //1.5 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 get���i�敪�ꗗ(String, String[])
        String[] l_strProductTypeList = WEB3AdminToDataManager.getCommodityDivList(l_strInstitutionCode, l_request.branchCode);
        
        //1.7 get�戵�\�s��(�،���ЃR�[�h : String, �����^�C�v : ProductTypeEnum)
        String[] l_strMarkets = 
            WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(l_strInstitutionCode, ProductTypeEnum.EQUITY);
        
        //1.8 createResponse( )
        WEB3AdminToManualLapseInputResponse l_response = 
            (WEB3AdminToManualLapseInputResponse) l_request.createResponse();
        
        //1.9 (*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //����������ʈꗗ    ���@@null�i�Œ�j�@@�������I�ɑΉ��B
        l_response.triggerOrderTypeList = null;
        //���i�敪�ꗗ  ���@@get���i�敪�ꗗ()�̖߂�l
        l_response.productDivList = l_strProductTypeList;
        //�s��R�[�h�ꗗ ���@@get�戵�\�s��()�̖߂�l
        l_response.marketList = l_strMarkets;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�蓮����)<BR>
     * �蓮�����m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�jvalidate�蓮�����v�Q�ƁB<BR>
     *       =============================================== <BR>
     *       �V�[�P���X�} :�i�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�jvalidate�蓮����<BR>
     *       ��̈ʒu    : 1.7.2 get�蓮�����Ώے����P�ʈꗗ(Long, �،����, <BR>
     *                     String[], String[], String[], String[], String, <BR>
     *                     String, Long, Long)<BR>
     *                     null���ԋp���ꂽ�ꍇ�A<BR>
     *                    �u�����h�c�ɊY�����钍���P�ʂ����݂��܂���B�v�̗�O���X���[����B<BR>
     *       class       : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_02086 <BR>
     *       =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮�����m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToManualLapseConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44054DD801F3
     */
    protected WEB3AdminToManualLapseConfirmResponse validateManualExpire(WEB3AdminToManualLapseConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateManualExpire(WEB3AdminToManualLapseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate�蓮�����\(�Ǘ���, WEB3GenRequest)
        OrderUnit l_orderUnit = this.validateManualExpirePossibility(l_admin, l_request);
        
        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 createResponse( )
        WEB3AdminToManualLapseConfirmResponse l_response = 
            (WEB3AdminToManualLapseConfirmResponse) l_request.createResponse();
        
        //1.6 (*)ID���ڎw�莞
        //�i���N�G�X�g�f�[�^.�����Ώے�������.����ID != null�j�̏ꍇ
        String l_strlapseTargetOrderNumber = null;
        if (WEB3StringTypeUtility.isNotEmpty(l_request.lapseTargetOrderCondition.id))
        {
            //1.6.1 create����(String, OrderUnit, 
            //�g���K�[�����Ǘ��ҁE�蓮�����m�F���X�|���X)
            this.createUnit(l_strInstitutionCode, l_orderUnit, l_response);
            l_strlapseTargetOrderNumber = "1";
        }
        //1.7 (*)ID���ڎw�莞�łȂ��ꍇ
        else
        {
            //1.7.1 get�،����( )
            Institution l_institution = l_admin.getInstitution();
            
            //1.7.2 get�蓮�����Ώے����P�ʈꗗ(����ID : Long, 
            //�،���� : �،����, ���X�R�[�h : String[], 
            //����������ʈꗗ : String[], ���i�敪�ꗗ : String[], 
            //�s��R�[�h�ꗗ : String[], �����R�[�h : String, 
            //�ڋq�R�[�h : String, ����IDFrom : Long, ����IDTo : Long)
            WEB3AdminToLapseTargetOrderCondition l_orderCondition = l_request.lapseTargetOrderCondition;
            OrderUnit[] l_orderUnits = WEB3AdminToDataManager.getManualExpireOrderUnits(
                null,
                l_institution,
                l_orderCondition.branchCode,
                l_orderCondition.triggerOrderTypeList,
                l_orderCondition.productDivList,
                l_orderCondition.marketList,
                l_orderCondition.productCode,
                l_orderCondition.accountCode,
                null,
                null);

            if (l_orderUnits == null || l_orderUnits.length == 0)
            {
                log.error("�Y�����������݂��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y�����������݂��܂���B");
            }
            
            l_strlapseTargetOrderNumber = String.valueOf(l_orderUnits.length);
        }
        
        //1.8 (*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�Ώے�������  ���@@ID���ڎw��̏ꍇ�A1�B
        //�ȊO�Aget�蓮�����Ώے����P�ʈꗗ()�̖߂�l.length
        l_response.lapseTargetOrderNumber = l_strlapseTargetOrderNumber;
        //���ݎ��� �� GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        //������
        if (l_request.lapseTargetOrderCondition.productCode != null)
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
            EqTypeProduct l_eqtypeProduct = null;
            try
            {
                l_eqtypeProduct = l_productManager.getProduct(
                    l_admin.getInstitution(),
                    l_request.lapseTargetOrderCondition.productCode);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�����ɊY������f�[�^�����݂��Ȃ��B", l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_nfe.getMessage(), 
                    l_nfe);
            }
            l_response.productName =
                ((EqtypeProductRow)l_eqtypeProduct.getDataSourceObject()).getStandardName();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (run�蓮����)<BR>
     * �蓮���������N�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�jrun�蓮�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮���������N�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToManualLapseRunResponse
     * @@throws WEB3BaseException
     * @@roseuid 44054DD80212
     */
    protected WEB3AdminToManualLapseRunResponse runManualExpire(WEB3AdminToManualLapseRunRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " runManualExpire(WEB3AdminToManualLapseRunRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate�蓮�����\(�Ǘ���, WEB3GenRequest)
        this.validateManualExpirePossibility(l_admin, l_request);
 
        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 delete�I�����C�����s����(String)
        this.deleteOnlineRunStatus(l_strInstitutionCode);
        
        //1.6 get�f�[�����g���K�[�ꗗ( )
        List l_lisTriggerList = this.getDaemonTriggerList();
        
        //1.7 (*)get�f�[�����g���K�[�ꗗ()�̖߂�l�̗v�f�����ALoop����
        int l_intLength = l_lisTriggerList.size();
        
        // getServerAccessor()
        ServerAccessor l_serverAccessor = this.getServerAccessor();
        try
        {
            for(int i = 0; i < l_intLength; i++)
            {
                DaemonTriggerRow l_row = (DaemonTriggerRow) l_lisTriggerList.get(i);
                // updateAP�ďo��()
                this.updateAPCalling(l_row.getThreadNo());
                //1.7.1 �g���K�[�����Ǘ��ҁE�蓮�������C�����N�G�X�g( )
                WEB3AdminToManualLapseMainRequest l_mainRequest = new WEB3AdminToManualLapseMainRequest();
                //(*)�C���X�^���X������A�ȉ��̃v���p�e�B���Z�b�g����B
                //�،���ЃR�[�h ���@@get�،���ЃR�[�h()�̖߂�l
                l_mainRequest.institutionCode = l_strInstitutionCode;
                //�X���b�hNo      ���@@�����Ώۂ̗v�f.�X���b�h�ԍ�
                l_mainRequest.threadNo = new Long(l_row.getThreadNo());
                //From����ID    ���@@�����Ώۂ̗v�f.�ڋq�R�[�h�i���j
                l_mainRequest.rangeFrom = new Long(l_row.getRangeFrom());
                //To����ID      ���@@�����Ώۂ̗v�f.�ڋq�R�[�h�i���j
                l_mainRequest.rangeTo = new Long(l_row.getRangeTo());
                //�����Ώے�������    ���@@���N�G�X�g�f�[�^�̓�������
                l_mainRequest.lapseTargetOrderCondition = l_request.lapseTargetOrderCondition;
                
                //1.7.2 doRequest(arg0 : Request)
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
        
        //1.8 createResponse( )
        WEB3AdminToManualLapseRunResponse l_response = 
            (WEB3AdminToManualLapseRunResponse) l_request.createResponse();
        
        //1.9 (*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //���ݎ���        ���@@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�����X�e�[�^�X)<BR>
     * �蓮�����̏����X�e�[�^�X���m�F����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�jvalidate�����X�e�[�^�X�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮���������X�e�[�^�X�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToManualLapseStatusResponse
     * @@throws WEB3BaseException
     * @@roseuid 440E89B90065
     */
    protected WEB3AdminToManualLapseStatusResponse validateStatus(WEB3AdminToManualLapseStatusRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateStatus(WEB3AdminToManualLapseStatusRequest)";
        log.entering(STR_METHOD_NAME);
   
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_MANUAL_EXPIRE, true);
        
        //1.5 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
 
        //1.6 get�f�[�����g���K�[�ꗗ( )
        List l_lisTriggerList = this.getDaemonTriggerList();
        
        //1.7 get�I�����C�����s���ʈꗗ(String)
        List l_lisOnlineExecResultList = this.getOnlineRunStatusList(l_strInstitutionCode);
        
        //1.8 createResponse( )
        WEB3AdminToManualLapseStatusResponse l_response = 
            (WEB3AdminToManualLapseStatusResponse) l_request.createResponse();
        
        //1.9 (*)�v���p�e�B�Z�b�g
        //�����X�e�[�^�X   ���@@�ȉ��̕���ɂ��Z�b�g����B
        //�@@�@@"������"���Z�b�g�������
        //�@@�@@�E�I�����C�����s���ʃ��R�[�h���擾�ł��Ȃ������ꍇ
        //�@@�@@�E�擾�����f�[�����g���K�[���R�[�h�̌����ƁA�I�����C�����s���ʃ��R�[�h�̌������قȂ�ꍇ
        //�@@�@@�E�擾�����f�[�����g���K�[���R�[�h.������Ԃ�"������"��1���ł����݂���ꍇ
        //�@@�@@�E�擾�����I�����C�����s���ʃ��R�[�h.���s�X�e�[�^�X�敪��"������"��1���ł����݂���ꍇ
        if (l_lisOnlineExecResultList == null || l_lisTriggerList.size() != l_lisOnlineExecResultList.size())
        {
            l_response.lapseStatus = WEB3RunStatusDivDef.DEALING;
        }
        else 
        {
            int l_intSize = l_lisTriggerList.size();
            DaemonTriggerRow l_daemonTriggerRow = null;
            OnlineRunStatusRow l_onlineRunStatusRow = null;
            int l_intFlag = 0;
            for(int i = 0; i < l_intSize; i++)
            {
                l_daemonTriggerRow = (DaemonTriggerRow)l_lisTriggerList.get(i);
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
     * �蓮�������������s�\���ǂ����`�F�b�N����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�jvalidate�蓮�����\�v�Q�ƁB<BR>
     *       =============================================== <BR>
     *       �V�[�P���X�} :�i�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�jvalidate�蓮�����\<BR>
     *       ��̈ʒu     : 1.5.2 getProduct(arg0 : Institution, <BR>
     *                      arg1 : �_���r���[::java::lang::String)<BR>
     *                      �擾�ł��Ȃ������ꍇ�A�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
     *                      �̋Ɩ��G���[���X���[����B<BR>
     *       class        : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_01037 <BR>
     *       =============================================== <BR>   
     *       =============================================== <BR> 
     *       �V�[�P���X�} :�i�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�jvalidate�蓮�����\<BR>
     *       ��̈ʒu     : 1.6.2 get�ڋq�ꗗ(String, String[], String)<BR>
     *                      �擾�ł��Ȃ������ꍇ�A�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
     *                      �̋Ɩ��G���[���X���[����B<BR>
     *       class        : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_01037 <BR>
     *       =============================================== <BR>
     *       =============================================== <BR>
     *       �V�[�P���X�} :�i�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�jvalidate�蓮�����\<BR>
     *       ��̈ʒu     : 1.7.1.1 getOrderUnits(arg0 : long)<BR>
     *                      �擾�ł��Ȃ������ꍇ�A<BR>
     *                     �u�����h�c�ɊY�����钍���P�ʂ����݂��܂���B�v��<BR>
     *                      �Ɩ��G���[���X���[����B<BR>
     *       class        : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_02011 <BR>
     *       =============================================== <BR>
     *       =============================================== <BR>  
     *       �V�[�P���X�} :�i�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�jvalidate�蓮�����\<BR>
     *       ��̈ʒu     : 1.7.2.1 getOrderUnits(arg0 : long)<BR>
     *                      �擾�ł��Ȃ������ꍇ�A<BR>
     *                     �u�����h�c�ɊY�����钍���P�ʂ����݂��܂���B�v��<BR>
     *                      �Ɩ��G���[���X���[����B<BR>
     *       class        : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_02011 <BR>
     *       =============================================== <BR>
     *       =============================================== <BR> 
     *       �V�[�P���X�} :�i�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�jvalidate�蓮�����\<BR>
     *       ��̈ʒu     : 1.7.3 (*)�����������`�F�b�N<BR>
     *                      (*)�ȉ��̏����̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@                  �u�w�肳�ꂽ�����͎蓮���������ΏۊO�v�̗�O���X���[����B<BR>
     *       class        : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_02419 <BR>
     *       =============================================== <BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return OrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 440790DB03D5
     */
    protected OrderUnit validateManualExpirePossibility(WEB3Administrator l_administrator, WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateManualExpirePossibility(WEB3Administrator, WEB3GenRequest)";
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
        WEB3AdminToManualLapseRunRequest l_runRequest = null;
        WEB3AdminToLapseTargetOrderCondition l_condition = null;
        if (l_request instanceof WEB3AdminToManualLapseConfirmRequest)
        {
            WEB3AdminToManualLapseConfirmRequest l_confirmRequest = 
                (WEB3AdminToManualLapseConfirmRequest) l_request;
            l_condition = l_confirmRequest.lapseTargetOrderCondition;
        }
        else if (l_request instanceof WEB3AdminToManualLapseRunRequest)
        {
            l_runRequest = (WEB3AdminToManualLapseRunRequest) l_request;
            l_condition = l_runRequest.lapseTargetOrderCondition;
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
        
        //���N�G�X�g.�����Ώے�������.����ID
        String l_strOrderId = l_condition.id;
        //���N�G�X�g.�����Ώے�������.���X�R�[�h
        String[] l_strBranchCodes = l_condition.branchCode;
        //���N�G�X�g.�����Ώے�������.���i�敪�ꗗ
        String[] l_strProductList = l_condition.productDivList;
        //���N�G�X�g.�����Ώے�������.�����R�[�h
        String l_productCode = l_condition.productCode;
        //���N�G�X�g.�����Ώے�������.�ڋq�R�[�h
        String l_strAccountCode = l_condition.accountCode;
        
        //1.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_MANUAL_EXPIRE, true);

        //1.3 validate���X����(���X�R�[�h : String[])
        l_administrator.validateBranchPermission(l_strBranchCodes);
        
        //1.4 (*)�����N�����N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminToManualLapseRunRequest)
        {
            //1.4.1 validate����p�X���[�h(�p�X���[�h : String)
            l_administrator.validateTradingPassword(l_runRequest.password);
        }
        
        //1.7 get�I�����C�����s���ʈꗗ
        //1.8 ��d�N���`�F�b�N    
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        List l_lstOnlineRunStatus = this.getOnlineRunStatusList(l_strInstitutionCode);
        // �I�����C�����s���ʃ��R�[�h����
        if (l_lstOnlineRunStatus != null)
        {
            //1.5 get�f�[�����g���K�[�ꗗ()
            List l_lstDaemonTrigger = this.getDaemonTriggerList();
            // �������قȂ�ꍇ
            if (l_lstOnlineRunStatus.size() != l_lstDaemonTrigger.size())
            {
                log.debug("�����s�����i�I�����C�����s���ʂ̌��� != �f�[�����g���K�[�̌����j");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���̎蓮�����v���Z�X���N�����ł��B");
            }
            
            for (int i = 0; i < l_lstOnlineRunStatus.size(); i++)
            {
                OnlineRunStatusRow l_onlineRunStatusRow =
                    (OnlineRunStatusRow)l_lstOnlineRunStatus.get(i);
                // "������"�����݂���ꍇ
                if (WEB3RunStatusDivDef.DEALING.equals(l_onlineRunStatusRow.getRunStatusDiv()))
                {
                    log.debug("�I�����C�����s���ʂ�\"������\"�̃��R�[�h����");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���̎蓮�����v���Z�X���N�����ł��B");
                }
            }
            
            for (int i = 0; i < l_lstDaemonTrigger.size(); i++)
            {
                DaemonTriggerRow l_daemonTriggerRow =
                    (DaemonTriggerRow)l_lstDaemonTrigger.get(i);
                // "���ғ�"�łȂ��ꍇ
                if (!WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus()))
                {
                    log.debug("�I�����C�����s���ʂ�\"������\"�̃��R�[�h����");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���̎蓮�����v���Z�X���N�����ł��B");
                }
            }
        }
        
        //1.9 (*)�擾���������R�[�h != null�̏ꍇ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        if (l_productCode != null)
        {
            //1.9.1 get�،����( )
            Institution l_institution = l_administrator.getInstitution();
            WEB3EquityProductManager l_productMgr = (WEB3EquityProductManager) l_tradingModule.getProductManager();
            
            //1.9.2  getProduct(arg0 : Institution, arg1 : String)
            try
            {
                l_productMgr.getProduct(l_institution, l_productCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�����ɊY������f�[�^�����݂��Ȃ��B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }
        }
        
        //1.10 (*)�擾�����ڋq�R�[�h != null�̏ꍇ
        if (l_strAccountCode != null)
        {
            //1.10.1 get�ڋq�ꗗ(�،���ЃR�[�h : String, ���X�R�[�h : 
            //String[], �ڋq�R�[�h : String)
            WEB3AdminToDataManager.getAccountList(l_strInstitutionCode, l_strBranchCodes, l_strAccountCode);
        }
        
        //1.11 (*)ID���ڎw��i�擾��������ID != null�j�̏ꍇ
        OrderUnit[] l_orderUnits = null;
        if (l_strOrderId != null)
        {
            int l_intLength = l_strProductList.length;
            boolean l_blnIsEquity = false;
            for(int i = 0; i < l_intLength; i++)
            {
                //1.11.1 (*)�擾�������i�敪�ꗗ�ɁA"��������" or "�M�p���"���܂܂��ꍇ
                if (WEB3CommodityDivDef.EQUITY.equals(l_strProductList[i])
                    || WEB3CommodityDivDef.MARGIN.equals(l_strProductList[i]))
                {
                    l_blnIsEquity = true;
                    break;
                }
                
                //1.11.2 �擾�������i�敪�ꗗ�ɁA"�敨" or "�I�v�V����"���܂܂��ꍇ
                if (WEB3CommodityDivDef.FUTURE.equals(l_strProductList[i])
                    || WEB3CommodityDivDef.OPTION.equals(l_strProductList[i]))
                {
                    l_blnIsEquity = false;
                    break;
                }
            }
            
            String l_strOrderConditionType = null;
            OrderOpenStatusEnum l_orderOpenStatusEnum = null; 
            String l_strRequestType = null;
            
            //1.11.1.1 getOrderUnits(arg0 : long)
            if (l_blnIsEquity)
            {
                WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
                l_orderUnits = l_orderManager.getOrderUnits(Long.parseLong(l_strOrderId));
                
                if (l_orderUnits == null || l_orderUnits.length == 0)
                {
                        log.debug("�����h�c�ɊY�����钍���P�ʂ����݂��܂���B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02011,
                            this.getClass().getName() + "." + STR_METHOD_NAME, 
                            "�����h�c�ɊY�����钍���P�ʂ����݂��܂���B");
                }
                
                EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow) l_orderUnits[0].getDataSourceObject();
                //�����P��.�����L�����
                l_orderOpenStatusEnum = l_orderUnits[0].getOrderOpenStatus();
                //�����P��.��������
                l_strOrderConditionType = l_row.getOrderConditionType();
                //�����P��.���N�G�X�g�^�C�v
                l_strRequestType = l_row.getRequestType();
            }
            
            //1.11.2.1 getOrderUnits(arg0 : long)
            if (l_blnIsEquity == false)
            {
                l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3OptionOrderManagerImpl l_orderManagerImpl = 
                    (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
                l_orderUnits = l_orderManagerImpl.getOrderUnits(Long.parseLong(l_strOrderId));
                
                if (l_orderUnits == null || l_orderUnits.length == 0)
                {
                        log.debug("�����h�c�ɊY�����钍���P�ʂ����݂��܂���B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02011,
                            this.getClass().getName() + "." + STR_METHOD_NAME, 
                            "�����h�c�ɊY�����钍���P�ʂ����݂��܂���B");
                }
                
                IfoOrderUnitRow l_row = (IfoOrderUnitRow) l_orderUnits[0].getDataSourceObject();
                //�����P��.�����L�����
                l_orderOpenStatusEnum = l_orderUnits[0].getOrderOpenStatus();
                //�����P��.��������
                l_strOrderConditionType = l_row.getOrderConditionType();
                //�����P��.���N�G�X�g�^�C�v
                l_strRequestType = l_row.getRequestType();
            }
            
            //1.11.3 (*)�����������`�F�b�N
            //(*)�ȉ��̏����̂����ꂩ�ɊY������ꍇ�́A
            //�u�w�肳�ꂽ�����͎蓮���������ΏۊO�v�̗�O���X���[����B
            //�@@�����P��.�������� == "DEFAULT"�@@�̏ꍇ
            //�A�����P��.�����L����� == "�N���[�Y"�̏ꍇ
            //�B�����P��.�������� == "W�w�l" ���� �����P��.���N�G�X�g�^�C�v == "�����T�[�o"�̏ꍇ
            //���`�F�b�N�ɕK�v�Ȓ����P�ʂ̊e���ڒl�́A��L������ɂ�
            //�擾���Ă������ƁB
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType)
                || OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatusEnum)
                || (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType)
                    && WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType)))
            {
                log.debug("�w�肳�ꂽ�����͎蓮���������ΏۊO�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02419,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w�肳�ꂽ�����͎蓮���������ΏۊO�ł��B");
            }
        }
        
        //1.11.4 �擾���������P�ʂ�ԋp����
        log.exiting(STR_METHOD_NAME);
        if (l_orderUnits != null && l_orderUnits.length > 0)
        {
            return l_orderUnits[0];
        }
        //1.12 null��ԋp����
        return null;
    }
    
    /**
     * (create����)<BR>
     * �����̒����P�ʂ̒������ׂ��쐬���A<BR>
     * ���X�|���X�ɃZ�b�g����B<BR>
     * �����ϒ����̏ꍇ�͌����^���ʖ��ׂ��쐬����B<BR>
     * <BR>
     * �P�j�@@����������ʂ𔻕ʂ���B<BR>
     * �@@�P�|�P�j�L���X�g���������P��(*1).�������� == "�t�w�l"�̏ꍇ<BR>
     * �@@�@@����������� == "�t�w�l"<BR>
     * <BR>
     * �@@�P�|�Q�j�L���X�g���������P��(*1).�������� == "W�w�l"�̏ꍇ <BR>
     * �@@�@@����������� == "W�w�l" <BR>
     * <BR>
     * �@@�P�|�R�j��L�ȊO�́A�u�p�����[�^�s���v�̗�O���X���[����B<BR>
     *       class        : WEB3SystemLayerException <BR>
     *       tag          : SYSTEM_ERROR_80017<BR>
     * <BR>
     * �@@���L���X�g���������P�ʂ��画�ʂɕK�v�ȍ��ڂ��擾����悤�ɂ��A<BR>
     * �@@�@@��L�������͏d�������Ȃ����ƁB<BR>
     * <BR>
     * �Q�j�@@�������ׂ��쐬����B <BR>
     * �@@�Q�|�P�j���������P�ʂ̏ꍇ <BR>
     * �@@�i�p�����[�^.�����P�ʂ̌^��EqtypeOrderUnit�j <BR>
     * �@@�@@�Q�|�P�|�P�j�@@����J�����_�R���e�L�X�g�̒l���Z�b�g�������B <BR>
     * �@@�@@�@@�g���K�[�����Ǘ��҃f�[�^�}�l�[�W��.reset����J�����_�R���e�L�X�g()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[reset����J�����_�R���e�L�X�g()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h <BR>
     * �@@�@@�@@�@@�����^�C�v�F�@@�L���X�g���������P��.�����^�C�v <BR>
     * �@@�@@�@@�@@���XID�F�@@�L���X�g���������P��.���XID <BR>
     * �@@�@@�@@�@@�s��ID�F�@@�L���X�g���������P��.�s��ID <BR>
     * �@@�@@�@@�@@����ID�F�@@�L���X�g���������P��.����ID <BR>
     * �@@�@@�@@�@@��t���ԋ敪�F�@@"�����E�M�p" <BR>
     * <BR>
     * �@@�@@�Q�|�P�|�Q�j�@@�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�XImpl. <BR>
     * �@@�@@�@@create���������Ɖ�Unit�ꗗ()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[create���������Ɖ�Unit�ꗗ()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�@@�����P�ʈꗗ�F�@@�L���X�g���������P�ʂ݂̂�v�f�Ƃ���z�� <BR>
     * �@@�@@�@@�@@���N�G�X�g�f�[�^�F�@@�쐬�������N�G�X�g�f�[�^(*2) <BR>
     * <BR>
     * �@@�@@�Q�|�P�|�R�j�@@���ϒ����̏ꍇ�A�������ׂ��쐬����B <BR>
     * �@@�@@�i�L���X�g���������P��.�����J�e�S�� == "�M�p�ԍϒ���"�j <BR>
     * �@@�@@�g�����������}�l�[�W��.create��������ByOrder()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[create��������ByOrder()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�@@�����P��ID�F�@@�L���X�g���������P��.�����P��ID <BR>
     * <BR>
     * �@@�@@�Q�|�P�|�S�j�@@�p�����[�^.���X�|���X�Ɉȉ��̃v���p�e�B���Z�b�g����B <BR>
     * �@@�@@�@@������������ = �Q�|�P�|�Q�j�̖߂�l <BR>
     * �@@�@@�@@������������ = �Q�|�P�|�R�j�̖߂�l <BR>
     * �@@�@@�@@�@@���R�[�����Ă��Ȃ��ꍇ��null���Z�b�g�B <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�敨OP�����P�ʂ̏ꍇ <BR>
     * �@@�i�p�����[�^.�����P�ʂ̌^��IfoOrderUnit�j <BR>
     * �@@�@@�Q�|�Q�|�P�j�@@����J�����_�R���e�L�X�g�̒l���Z�b�g�������B <BR>
     * �@@�@@�@@�g���K�[�����Ǘ��҃f�[�^�}�l�[�W��.reset����J�����_�R���e�L�X�g()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[reset����J�����_�R���e�L�X�g()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h <BR>
     * �@@�@@�@@�@@�����^�C�v�F�@@�L���X�g���������P��.�����^�C�v <BR>
     * �@@�@@�@@�@@���XID�F�@@�L���X�g���������P��.���XID <BR>
     * �@@�@@�@@�@@�s��ID�F�@@�L���X�g���������P��.�s��ID <BR>
     * �@@�@@�@@�@@����ID�F�@@�L���X�g���������P��.����ID <BR>
     * �@@�@@�@@�@@��t���ԋ敪�F�@@"�����w���敨OP" <BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j�@@�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�XImpl. <BR>
     * �@@�@@�@@create�敨OP�����Ɖ�Unit�ꗗ()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[create�敨OP�����Ɖ�Unit�ꗗ()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�@@�����P�ʈꗗ�F�@@�L���X�g���������P�ʂ݂̂�v�f�Ƃ���z�� <BR>
     * �@@�@@�@@�@@���N�G�X�g�f�[�^�F�@@�쐬�������N�G�X�g�f�[�^(*2) <BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�R�j�@@���ϒ����̏ꍇ�A���ʖ��ׂ��쐬����B <BR>
     * �@@�@@�i�L���X�g���������P��.�����J�e�S�� == "�敨�ԍϒ���" or "OP�ԍϒ���"�j <BR>
     * �@@�@@OP�����}�l�[�W��.create���ʖ���ByOrder()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[create���ʖ���ByOrder()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�@@����ID�F�@@�L���X�g���������P��.����ID <BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�S�j�@@�p�����[�^.���X�|���X�Ɉȉ��̃v���p�e�B���Z�b�g����B <BR>
     * �@@�@@�@@�敨OP�������� = �Q�|�Q�|�Q�j�̖߂�l <BR>
     * �@@�@@�@@�敨OP���ʖ��� = �Q�|�Q�|�R�j�̖߂�l <BR>
     * �@@�@@�@@�@@���R�[�����Ă��Ȃ��ꍇ��null���Z�b�g�B <BR>
     * <BR>
     * (*1)�L���X�g���������P�� <BR>
     * �@@�p�����[�^.�����P�ʂ�instanceof�ɂĔ��ʂ��A <BR>
     * �@@�ȉ��̂����ꂩ�ɃL���X�g����B <BR>
     * �@@�EEqtypeOrderUnit <BR>
     * �@@�EIfoOrderUnit <BR>
     * <BR>
     * (*2)�쐬�������N�G�X�g�f�[�^ <BR>
     * �@@[���������P�ʂ̏ꍇ] <BR>
     * �@@�@@�g���K�[�����Ǘ��ҁE���������Ɖ�N�G�X�g�I�u�W�F�N�g�𐶐� <BR>
     * �@@[�敨OP�����P�ʂ̏ꍇ] <BR>
     * �@@�@@�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�N�G�X�g�I�u�W�F�N�g�𐶐� <BR>
     * <BR>
     * �@@���������I�u�W�F�N�g�Ɉȉ��̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^.����������� = �P�j�ɂĔ��ʂ��������������<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_response - (���X�|���X)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮�����m�F���X�|���X�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4407A834017B
     */
    protected void createUnit(
        String l_strInstitutionCode,
        OrderUnit l_orderUnit,
        WEB3AdminToManualLapseConfirmResponse l_response) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createUnit(String, OrderUnit, WEB3AdminToManualLapseConfirmResponse)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
        // �P�j�@@����������ʂ𔻕ʂ���B
        String l_orderConditionType = null;
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = null;
        IfoOrderUnitRow l_ifoOrderUnitRow = null;
        if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            l_orderConditionType = l_eqtypeOrderUnitRow.getOrderConditionType();
        }
        else if (l_orderUnit instanceof IfoOrderUnit)
        {
            l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
            l_orderConditionType = l_ifoOrderUnitRow.getOrderConditionType();
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
        
        String l_strOrderingConditionType = null;
        //�@@�P�|�P�j�L���X�g���������P��(*1).�������� == "�t�w�l"�̏ꍇ
        // �@@�@@����������� == "�t�w�l"
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderConditionType)) 
        {
            l_strOrderingConditionType = WEB3TriggerOrderTypeDef.STOP;
        }
        //�P�|�Q�j�L���X�g���������P��(*1).�������� == "W�w�l"�̏ꍇ
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderConditionType))
        {
            l_strOrderingConditionType = WEB3TriggerOrderTypeDef.W_LlIMIT;
        }
        //�P�|�R�j��L�ȊO�́A�u�p�����[�^�s���v�̗�O���X���[����B
        else
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
        // �Q�j�@@�������ׂ��쐬����B
        //�@@�Q�|�P�j���������P�ʂ̏ꍇ
        // �@@�i�p�����[�^.�����P�ʂ̌^��EqtypeOrderUnit�j
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            // �Q�|�P�|�P�j�@@����J�����_�R���e�L�X�g�̒l���Z�b�g�������B
            // �g���K�[�����Ǘ��҃f�[�^�}�l�[�W��.reset����J�����_�R���e�L�X�g()
            //���R�[������B
            WEB3AdminToDataManager.resetTradingCalContext(
                l_strInstitutionCode,
                l_eqtypeOrderUnitRow.getProductType(),
                new Long(l_eqtypeOrderUnitRow.getBranchId()),
                new Long(l_eqtypeOrderUnitRow.getMarketId()),
                new Long(l_eqtypeOrderUnitRow.getProductId()),
                WEB3TradingTimeTypeDef.EQUITY);
            
            // �Q�|�P�|�Q�j�@@�g���K�[�����Ǘ��ҁE���������Ɖ�T�[�r�XImpl.
            // �@@create���������Ɖ�Unit�ꗗ()���R�[������B
            WEB3AdminToEquityOrderReferenceServiceImpl l_serviceImpl =  new WEB3AdminToEquityOrderReferenceServiceImpl();
            EqTypeOrderUnit[] l_eqTypeOrderUnits = new EqTypeOrderUnit[1];
            l_eqTypeOrderUnits[0] = (EqTypeOrderUnit) l_orderUnit;
            WEB3AdminToEquityOrderRefRefRequest l_request = new WEB3AdminToEquityOrderRefRefRequest();
            l_request.triggerOrderType = l_strOrderingConditionType;
            WEB3AdminToEquityOrderRefUnit[] l_orderRefUnits = 
                l_serviceImpl.createEquityOrderRefUnitList(l_eqTypeOrderUnits, l_request);
            
            // �Q�|�P�|�R�j�@@���ϒ����̏ꍇ�A�������ׂ��쐬����B
            //�@@�@@�i�L���X�g���������P��.�����J�e�S�� == "�M�p�ԍϒ���"�j
            WEB3MarginContractUnit[] l_contractUnits = null;
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_eqTypeOrderUnits[0].getOrderCateg()))
            {      
                //�g�����������}�l�[�W��.create��������ByOrder()
                // �@@�@@���R�[������B
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
                l_contractUnits = l_orderManager.createContractUnitByOrder(l_eqTypeOrderUnits[0].getOrderUnitId());
            }
            
            // �Q�|�P�|�S�j�@@�p�����[�^.���X�|���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
            //�@@�@@������������ = �Q�|�P�|�Q�j�̖߂�l
            //   ���R�[�����Ă��Ȃ��ꍇ��null���Z�b�g�B
            if (l_orderRefUnits != null && l_orderRefUnits.length != 0)
            {
                l_response.equityOrderUnit = l_orderRefUnits[0];
            }
            //�@@�@@������������ = �Q�|�P�|�R�j�̖߂�l
            if (l_contractUnits != null && l_contractUnits.length != 0)
            {
                l_response.equityContractUnits = l_contractUnits;
            }
        }       
        //�@@�Q�|�Q�j�@@�敨OP�����P�ʂ̏ꍇ
        //�@@�i�p�����[�^.�����P�ʂ̌^��IfoOrderUnit�j
        else
        {
            //�Q�|�Q�|�P�j�@@����J�����_�R���e�L�X�g�̒l���Z�b�g�������B
            // �g���K�[�����Ǘ��҃f�[�^�}�l�[�W��.reset����J�����_�R���e�L�X�g()
            // ���R�[������B
            WEB3AdminToDataManager.resetTradingCalContext(
                l_strInstitutionCode,
                l_ifoOrderUnitRow.getProductType(),
                new Long(l_ifoOrderUnitRow.getBranchId()),
                new Long(l_ifoOrderUnitRow.getMarketId()),
                new Long(l_ifoOrderUnitRow.getProductId()),
                WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            
            //�Q�|�Q�|�Q�j�@@�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�XImpl.
            //create�敨OP�����Ɖ�Unit�ꗗ()���R�[������B
            WEB3AdminToIfoOrderReferenceServiceImpl l_serviceImpl = new WEB3AdminToIfoOrderReferenceServiceImpl();
            WEB3AdminToIfoOrderRefRefRequest l_request = new WEB3AdminToIfoOrderRefRefRequest();
            l_request.triggerOrderType = l_strOrderingConditionType;
            IfoOrderUnit[] l_ifoOrderUnits = new IfoOrderUnit[1];
            l_ifoOrderUnits[0] = (IfoOrderUnit) l_orderUnit;
            WEB3AdminToIfoOrderRefUnit[] l_refUnits = 
                l_serviceImpl.createIfoOrderRefUnitList(l_ifoOrderUnits, l_request);
            
            //�Q�|�Q�|�R�j�@@���ϒ����̏ꍇ�A���ʖ��ׂ��쐬����B
            // �i�L���X�g���������P��.�����J�e�S�� == "�敨�ԍϒ���" or "OP�ԍϒ���"�j
            WEB3FuturesOptionsContractUnit[] l_contractUnits = null;
            if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_ifoOrderUnits[0].getOrderCateg())
                || OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_ifoOrderUnits[0].getOrderCateg()))
            {
                //�@@OP�����}�l�[�W��.create���ʖ���ByOrder()���R�[������B
                //�@@�@@�@@[create���ʖ���ByOrder()�ɃZ�b�g����p�����[�^]
                //�@@�@@�@@�@@����ID�F�@@�L���X�g���������P��.����ID
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3OptionOrderManagerImpl l_orderManagerImpl =
                    (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
                l_contractUnits = l_orderManagerImpl.createContractUnitByOrder(l_ifoOrderUnits[0].getOrderId());
            }
            
            // �Q�|�Q�|�S�j�@@�p�����[�^.���X�|���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
            //�@@�敨OP�������� = �Q�|�Q�|�Q�j�̖߂�l
            //���R�[�����Ă��Ȃ��ꍇ��null���Z�b�g
            if (l_refUnits != null && l_refUnits.length != 0)
            {
                l_response.ifoOrderUnit = l_refUnits[0];
            }
            //�@@ �敨OP���ʖ��� = �Q�|�Q�|�R�j�̖߂�l
            if (l_contractUnits != null && l_contractUnits.length != 0)
            {
                l_response.ifoContractUnits = l_contractUnits;
            }
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
     * �@@�@@�����^�C�v = "�蓮�����iEqtype�^Ifo�j"<BR>
     * <BR>
     * �@@[�\�[�g����]<BR>
     * �@@�@@�X���b�h�ԍ��@@����<BR>
     * <BR>
     * �@@�Y���f�[�^�Ȃ��̏ꍇ�A�u�Y���f�[�^�Ȃ��v��<BR>
     * �@@�V�X�e���G���[���X���[����B<BR>
     *       class        : WEB3SystemLayerException <BR>
     *       tag          : SYSTEM_ERROR_80005<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 440BFEFC024A
     */
    protected List getDaemonTriggerList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDaemonTriggerList()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@DB����
        // �@@�ȉ��̏����Ńf�[�����g���K�[�e�[�u������������B
        // �@@[����]
        // �@@�@@�����^�C�v = "�蓮�����iEqtype�^Ifo�j"
        String l_strCondition = "order by thread_no asc";
        String[] l_strBindValues = {WEB3DaemonTriggerTypeDef.EQTYPE_IFO_TRIGGER_MANUAL_EXPIRE};
        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                DaemonTriggerRow.TYPE,
                " trigger_type = ?",
                l_strCondition,
                l_strBindValues);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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
     * �@@�@@�����^�C�v = "���̑�"<BR>
     * �@@�@@�敨�^�I�v�V�����敪 = "DEFAULT"<BR>
     * �@@�@@�I�����C���T�[�r�X�敪 = "�蓮����"<BR>
     * <BR>
     * �@@�Y���f�[�^�Ȃ��̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 440E8B73019E
     */
    protected List getOnlineRunStatusList(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOnlineRunStatusList(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@DB����
        // �@@�ȉ��̏����ŃI�����C�����s���ʃe�[�u������������B
        // �@@[����]
        // �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
        // �@@�@@�����^�C�v = "���̑�"
        // �@@�@@�敨�^�I�v�V�����敪 = "DEFAULT"
        // �@@�@@�I�����C���T�[�r�X�敪 = "�蓮����"
        String l_strWhere= " institution_code = ? and product_type = ? " +
            "and future_option_div = ? and online_service_div = ?";
        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(String.valueOf(ProductTypeEnum.OTHER.intValue()));
        l_lisBindValues.add(WEB3FuturesOptionDivDef.DEFAULT);
        l_lisBindValues.add(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        String[] l_strBindValues = new String[l_lisBindValues.size()];
        l_lisBindValues.toArray(l_strBindValues);
        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                OnlineRunStatusRow.TYPE,
                l_strWhere,
                l_strBindValues);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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
     * �@@�@@�����^�C�v = "���̑�"<BR>
     * �@@�@@�敨�^�I�v�V�����敪 = "DEFAULT"<BR>
     * �@@�@@�I�����C���T�[�r�X�敪 = "�蓮����"<BR>
     * <BR>
     * �@@���폜�Ώۂ̃��R�[�h���Ȃ��Ă���O�Ƃ��Ȃ����ƁB<BR>
     * �@@���{�����͐V�K�g�����U�N�V�����ŏ������s���A<BR>
     * �@@�@@�����������ɍX�V�����f�����悤�ɂ��邱�ƁB<BR>
     * �@@�@@�i�Q�l�FWEB3GentradeDaemonTriggerManager.startThread()�j<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 441627100082
     */
    protected void deleteOnlineRunStatus(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteOnlineRunStatus(String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�ȉ��̏����ɊY������I�����C�����s���ʃe�[�u����
        // �@@���R�[�h��delete����B
        // �@@[����]
        // �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
        // �@@�@@�����^�C�v = "���̑�"
        // �@@�@@�敨�^�I�v�V�����敪 = "DEFAULT"
        // �@@�@@�I�����C���T�[�r�X�敪 = "�蓮����"
        final String l_strWhere= " institution_code = ? and product_type = ? " +
            "and future_option_div = ? and online_service_div = ?";
        ArrayList l_lisBindValues = new ArrayList();
        l_lisBindValues.add(l_strInstitutionCode);
        l_lisBindValues.add(String.valueOf(ProductTypeEnum.OTHER.intValue()));
        l_lisBindValues.add(WEB3FuturesOptionDivDef.DEFAULT);
        l_lisBindValues.add(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        final String[] l_strBindValues = new String[l_lisBindValues.size()];
        l_lisBindValues.toArray(l_strBindValues);
        
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
                                l_strBindValues);
                            int l_intSize =  l_lisRows.size();
                            for (int i = 0; i < l_intSize; i++)
                            {
                                OnlineRunStatusRow l_row = (OnlineRunStatusRow) l_lisRows.get(i);
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
     * (updateAP�ďo��)
     *
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

     * @@param l_lngThreadNo - (�X���b�hNo)
     * �X���b�hNo
     * @@throws WEB3BaseException
     */
    protected void updateAPCalling(long l_lngThreadNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateApCalling(l_lngThreadNo)";
        log.entering(STR_METHOD_NAME);

        final String l_strWhere= " thread_no = ?";
        final String l_strCondition = "for update";
        final Object[] l_bindValues = {new Long(l_lngThreadNo)};

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
                                DaemonTriggerRow.TYPE,
                                l_strWhere,
                                l_strCondition,
                                l_bindValues);
                            int l_intSize =  l_lisRows.size();
                            for (int i = 0; i < l_intSize; i++)
                            {
                                DaemonTriggerParams l_params = new DaemonTriggerParams();
                                GtlUtils.copyRow2Params((DaemonTriggerRow)l_lisRows.get(i), l_params);
                                l_params.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_API_CALL);
                                WEB3DataAccessUtility.updateRow(l_params);
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
     * (getServerAccessor)
     *
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
     *
     * @@return ServerAccessor
     * @@throws WEB3BaseException
     */
    protected ServerAccessor getServerAccessor() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getServerAccessor()";
        log.entering(STR_METHOD_NAME);
        
        if (this.accessor != null)
        {
            log.exiting(STR_METHOD_NAME);
            return this.accessor;
        }

        // �P�j�N���X�^�����O��T�[�o�[URL���擾����B
        // �@@�@@QueryProcessor.doFindAllQuery()���\�b�h��
        // �@@�@@�R�[������B
        String[] l_strBindValues = {WEB3ServerUrlAccessorDef.CLUSTER_URLS};
        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                ServerConfigRow.TYPE,
                " config_categ = ?",
                l_strBindValues);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
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
        
        // �Q�j�@@ServerAccessor�̍쐬
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
        ServerAccessor l_serverAccessor = null;
        try
        {
            l_serverAccessor = ServerConnector.createAccessor(
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
        return l_serverAccessor;
    }
}
@
