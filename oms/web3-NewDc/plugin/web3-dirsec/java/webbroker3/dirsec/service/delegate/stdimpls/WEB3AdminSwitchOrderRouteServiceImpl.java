head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminSwitchOrderRouteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҕ�����ؑփT�[�r�X�����N���X(WEB3AdminSwitchOrderRouteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ј��� (���u) �d�l�ύX���f��No.020
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
Revesion History : 2007/02/17  ���؎q (���u) �����̖��No.002-004
Revesion History : 2007/02/27  �Ӑ� (���u) ���f��No.024,026-028,031-039
Revesion History : 2007/06/21  �Ӑ� (���u) ���f��No.102
Revesion History : 2007/06/22  ���G�� (���u) ���f��No.104,107
Revesion History : 2007/06/22  ������ (���u) ���f��No.103,106
Revesion History : 2007/06/22  ���n�m (���u) ���f��No.105,108
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3FrontOrderSystemCodeDef;
import webbroker3.common.define.WEB3FrontOrderTradeCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.define.WEB3ValidFlag;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminOrderRouteSwitchingInfo;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteSelectRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteSelectResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.dirsec.service.delegate.WEB3AdminSwitchOrderRouteService;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҕ����o�H�ؑփT�[�r�X)<BR>
 * <BR>
 * �Ǘ��Ҕ�����ؑփT�[�r�X�����N���X<BR>
 * <BR>
 * WEB3AdminSwitchOrderRouteServiceImpl<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminSwitchOrderRouteServiceImpl implements WEB3AdminSwitchOrderRouteService
{

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSwitchOrderRouteServiceImpl.class);

    /**
     * @@roseuid 430C4986035B
     */
    public WEB3AdminSwitchOrderRouteServiceImpl()
    {

    }

    /**
     * �Ǘ��Ҕ�����ؑփT�[�r�X���s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^���A<BR>
     * �@@[�Ǘ��ҁE������֑ؑI�����N�G�X�g�̏ꍇ]<BR>
     * �@@�@@this.get�I�����()���R�[������B<BR>
     * <BR>
     * �@@[�Ǘ��ҁE������ؑ֊m�F���N�G�X�g�̏ꍇ]<BR>
     * �@@�@@this.validate������ؑ�()���R�[������B<BR>
     * <BR>
     * �@@[�Ǘ��ҁE������ؑ֊������N�G�X�g�̏ꍇ]<BR>
     * �@@�@@this.submit������ؑ�()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 42D23A3E016B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        try
        {
            //get�I�����
            if (l_request instanceof WEB3AdminSwitchOrderRouteSelectRequest)
            {
                l_response =
                    this.getSelectScreen((WEB3AdminSwitchOrderRouteSelectRequest) l_request);

            //validate������ؑ�()
            }
            else if (l_request instanceof WEB3AdminSwitchOrderRouteConfirmRequest)
            {
                l_response =
                    this.validateOrderRouteChange(
                        (WEB3AdminSwitchOrderRouteConfirmRequest) l_request);

            //submit������ؑ�()
            }
            else if (l_request instanceof WEB3AdminSwitchOrderRouteCompleteRequest)
            {
                l_response =
                    this.submitOrderRouteChange(
                        (WEB3AdminSwitchOrderRouteCompleteRequest) l_request);                
            }
            else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT ���N�G�X�g NOT �Ǘ��Ҕ�����ؑփT�[�r�X���N�G�X�g");
            }
        }
 
        catch (DataQueryException l_dqex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dqex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.toString(),
                l_dqex);

        } 
        catch (DataNetworkException l_dnex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dnex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.toString(),
                l_dnex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * �Ǘ��Ҕ�����֑ؑI����ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i������ؑցjget�I����ʁv�Q�ƁB<BR>
     * @@param ���N�G�X�g�f�[�^ - �Ǘ��ҁE������֑ؑI�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return �Ǘ��ҁE������֑ؑI�����X�|���X<BR>
     * @@roseuid 42D23A9C014C
     */
    protected WEB3AdminSwitchOrderRouteSelectResponse getSelectScreen(WEB3AdminSwitchOrderRouteSelectRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "getSelectScreen()";
        log.entering(STR_METHOD_NAME);
        
        // �t�����g�����Ǘ����ʃT�[�r�X�C���X�^���X����
        WEB3AdminDirSecFrontOrderCommonService l_adminFrontOrderCommonService =
            (WEB3AdminDirSecFrontOrderCommonService) Services.getService(
            WEB3AdminDirSecFrontOrderCommonService.class);

        //������ؑփe�[�u��Row�^�z��
        OrderSwitchingRow[] orderSwitchingRow = null;
        
        // ��������^�z��
        WEB3AdminOrderRouteSwitchingInfo[] switchInfos = null;
        
        // ���O�C�����C���X�^���X
        WEB3Administrator l_administrator = null;

        // 1.1.���N�G�X�g�p�����[�^��null�`�F�b�N
        l_request.validate();
        
        // 1.2.���O�C�����C���X�^���X�擾
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.3.validate�����`�F�b�N()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH,
            false);

        //1.4. isDIR�Ǘ���( )�`�F�b�N DIR�Ǘ��҂łȂ��ꍇ�A��O���X���[����B
        boolean l_blnDir = l_administrator.isDirAdministrator();
        if (!l_blnDir)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        // 1.2.������ؑփe�[�u�����烌�R�[�h���擾
        orderSwitchingRow = l_adminFrontOrderCommonService.getOrderRouteSwitchingRows(l_request.institutionCode);

        // 1.3.������ꗗ���𐶐�����
        switchInfos = l_adminFrontOrderCommonService.createSwitchRouteInfoList(orderSwitchingRow);

        // 1.4.���X�|���X�f�[�^�쐬    	
        WEB3AdminSwitchOrderRouteSelectResponse l_response =
            (WEB3AdminSwitchOrderRouteSelectResponse) l_request.createResponse();

        // 1.5.�v���p�e�B�Z�b�g
        l_response.infoList = switchInfos;

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * �Ǘ��Ҕ�����ؑ֊m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i������ؑցjvalidate������ؑցv�Q�ƁB<BR>
     * @@param ���N�G�X�g�f�[�^ - �Ǘ��ҁE������ؑ֊m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return �Ǘ��ҁE������ؑ֊m�F���X�|���X<BR>
     * @@roseuid 42D23A9C016B
     */
    protected WEB3AdminSwitchOrderRouteConfirmResponse validateOrderRouteChange(WEB3AdminSwitchOrderRouteConfirmRequest l_request)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validateOrderRouteChange()";
        log.entering(STR_METHOD_NAME);

        // ��������I�u�W�F�N�g�擾
        WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = l_request.infoUnit;

        // 1.1.���N�G�X�g�f�[�^validate�`�F�b�N
        l_request.validate();

        // �����o�H�敪���擾
        String l_sbumitRouteDiv = l_switchInfoObj.submitOrderRouteDiv;
        
        // �ύX��L���t���O���擾
        String l_changedValidFlg = l_switchInfoObj.changedValidFlag;

        //�t�����g�������n��L���ɂ���ꍇ�A�r�n�m�`�q�S��Q�`�F�b�N���s��
        if (l_sbumitRouteDiv.equals(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION) && 
                                                            l_changedValidFlg.equals(WEB3ValidFlag.ON))
        {
            // �t�����g�����Ǘ����ʃC���X�^���X����
        	WEB3AdminDirSecFrontOrderCommonService l_adminFrontOrderCommonService =
                (WEB3AdminDirSecFrontOrderCommonService) Services.getService(
                WEB3AdminDirSecFrontOrderCommonService.class);

            //�t�����g����������敪�R�[�h���擾����
            String frontOrderExchangeCode = l_adminFrontOrderCommonService.getFrontOrderExchangeCode(l_switchInfoObj.convertMarketCode);

            //�r�n�m�`�q�S��Q�`�F�b�N���s��
            //�r�n�m�`�q�S��Q���R�[�h���擾�ł����ꍇ�́A��O���X���[����
            l_adminFrontOrderCommonService.validateSonarCheck(
                                l_request.institutionCode,
                                frontOrderExchangeCode,
                                l_switchInfoObj.frontOrderSystemCode,
                                l_switchInfoObj.productType);

        }

        //���X�|���X�f�[�^�쐬    	
        WEB3AdminSwitchOrderRouteConfirmResponse l_response =
            (WEB3AdminSwitchOrderRouteConfirmResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * �Ǘ��Ҕ�����ؑ֊����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i������ؑցjsubmit������ؑցv�Q�ƁB<BR>
     * @@param ���N�G�X�g�f�[�^ - �Ǘ��ҁE������ؑ֊������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return �Ǘ��ҁE������ؑ֊������X�|���X<BR>
     * @@roseuid 42D23A9C018B
     */
    protected WEB3AdminSwitchOrderRouteCompleteResponse submitOrderRouteChange(WEB3AdminSwitchOrderRouteCompleteRequest l_request)
        throws WEB3BaseException, DataNetworkException, DataQueryException
    {

        final String STR_METHOD_NAME = "submitOrderRouteChange()";
        log.entering(STR_METHOD_NAME);

        // ���O�C�����C���X�^���X
        WEB3Administrator l_administrator = null;
        // �p�X���[�h
        String l_strPassword = null;
        // ��������C���X�^���X
        WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = l_request.infoUnit;
        // �t�����g�����Ǘ����ʃC���X�^���X����
        WEB3AdminDirSecFrontOrderCommonService l_adminFrontOrderCommonService =
            (WEB3AdminDirSecFrontOrderCommonService) Services.getService(
            WEB3AdminDirSecFrontOrderCommonService.class);
       
        // 1.1.���O�C�����擾
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.2.���N�G�X�g�f�[�^validate�`�F�b�N
        l_request.validate();
        
        // 1.3.validate�����`�F�b�N()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH,
            true);

        //1.4. isDIR�Ǘ���( )�`�F�b�N DIR�Ǘ��҂łȂ��ꍇ�A��O���X���[����B
        boolean l_blnDir = l_administrator.isDirAdministrator();
        if (!l_blnDir)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        //�p�X���[�h�`�F�b�N
        l_strPassword = l_request.password;
        l_administrator.validateTradingPassword(l_strPassword);

        //�،���ЃR�[�h�擾
        String institutionCode = l_administrator.getInstitutionCode();

        // �����o�H�敪���擾
        String l_sbumitRouteDiv = l_switchInfoObj.submitOrderRouteDiv;
    
        // �ύX��L���t���O���擾
        String l_changedValidFlg = l_switchInfoObj.changedValidFlag;

        // �t�����g����������敪�R�[�h
        String l_frontExCode = l_adminFrontOrderCommonService.getFrontOrderExchangeCode(l_switchInfoObj.convertMarketCode);

        //�t�����g�������n��L���ɂ���ꍇ�A�r�n�m�`�q�S��Q�`�F�b�N���s��
        if (l_sbumitRouteDiv.equals(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION) && 
                                                            l_changedValidFlg.equals(WEB3ValidFlag.ON))
        {
            //�r�n�m�`�q�S��Q�`�F�b�N���s��
            //�r�n�m�`�q�S��Q���R�[�h���擾�ł����ꍇ�́A��O���X���[����
            l_adminFrontOrderCommonService.validateSonarCheck(
                                l_request.institutionCode,
                                l_frontExCode,
                                l_switchInfoObj.frontOrderSystemCode,
                                l_switchInfoObj.productType);
        }

        //get�L�������o�H
        WEB3GentradeOrderSwitching l_orderSwitching =
            this.getExpirationOrderRoute(l_request.institutionCode, l_switchInfoObj);
        //������ؑփe�[�u���X�V����
        this.updateOrderSwitching(institutionCode, l_switchInfoObj);
        
        // �ڋq���̃L���[�e�[�u���X�V�����Ɠ�����}��ׁA1�b��Sleep����B
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

            log.error(" sleep�����̎��s");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        //���N�G�X�g�f�[�^.��������.�����^�C�v��1�F�����̏ꍇ
        if (Integer.toString(ProductTypeEnum.EQUITY.intValue()).equals(l_switchInfoObj.productType))
        {
            this.updateEqtypeOrder(l_request.institutionCode, l_frontExCode, l_switchInfoObj, l_orderSwitching);
        }

        //���N�G�X�g�f�[�^.��������.�����^�C�v��6�F�敨�I�v�V�����̏ꍇ
        if (Integer.toString(ProductTypeEnum.IFO.intValue()).equals(l_switchInfoObj.productType))
        {
            this.updateIfoOrder(l_request.institutionCode, l_frontExCode, l_switchInfoObj, l_orderSwitching);
        }

        //���X�|���X�f�[�^�쐬    	
        WEB3AdminSwitchOrderRouteCompleteResponse l_response =
            (WEB3AdminSwitchOrderRouteCompleteResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }

    /**
     * ������ؑփe�[�u���̃��R�[�h��L���^�����ɍX�V����B<BR>
     * <BR>
     * �P�j�p�����[�^.��������.�ύX��L���t���O == 1�i�L���j�̏ꍇ�A<BR>
     * �@@�����ɊY������������R�[�h��S�Ė����ɍX�V����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h And<BR>
     * �@@�@@�s��R�[�h = �p�����[�^.��������.�s��R�[�h And<BR>
     * �@@�@@�t�����g�����V�X�e���敪 = �p�����[�^.��������.�t�����g�����V�X�e���敪 <BR>
     * And<BR>
     * �@@�@@�����^�C�v = �p�����[�^.��������.�����^�C�v <BR>
     * <BR>
     * �@@[�X�V�l]<BR>
     * �@@�@@DB�X�V�d�l<BR>
     * �@@�@@�@@�u������ؑ�_������ؑփe�[�u��.xls<BR>
     * �@@�@@�@@��������ؑ�[�L�����R�[�h�ȊO]_������ؑփe�[�u��_DB�X�V�V�[�g�v�Q�ƁB<BR>
     * <BR>
     * �Q�j�����ɊY�����锭����ؑփe�[�u���̃��R�[�h.�L���t���O��<BR>
     * �@@�X�V����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h And<BR>
     * �@@�@@�s��R�[�h = �p�����[�^.��������.�s��R�[�h And<BR>
     * �@@�@@�t�����g�����V�X�e���敪 = �p�����[�^.��������.�t�����g�����V�X�e���敪 <BR>
     * And<BR>
     * �@@�@@�����^�C�v = �p�����[�^.��������.�����^�C�v And <BR>
     * �@@�@@�����o�H�敪 = �p�����[�^.��������.�����o�H<BR>
     * <BR>
     * �@@[�X�V�l]<BR>
     * �@@�@@DB�X�V�d�l<BR>
     * �@@�@@�@@�u������ؑ�_������ؑփe�[�u��.xls<BR>
     * �@@�@@�@@��������ؑ�[�L��or����]_������ؑփe�[�u��_DB�X�V�V�[�g�v�Q�ƁB<BR>
     * �R�jcommit���s���B <BR>
     *  * QueryProcessor.doQueries(QueryProcessor.TX_CREATE_NEW, BatchedQuery[]) <BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h<BR>
     * @@param �������� - ��������I�u�W�F�N�g<BR>
     * @@roseuid 42D38BAC037E
     */
    protected void updateOrderSwitching(
        String institutioncode,
        WEB3AdminOrderRouteSwitchingInfo infolist)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderSwitching()";
        log.entering(STR_METHOD_NAME);
        
        // �X�V����������̐���
        StringBuffer l_sbInValidWhere = new StringBuffer();
        l_sbInValidWhere.append(" institution_code = ? ");
        l_sbInValidWhere.append(" and market_code = ? ");
        l_sbInValidWhere.append(" and front_order_system_code = ? ");
        l_sbInValidWhere.append(" and product_type = ? ");
        
        // ���������R���e�i�̐���
        Object[] l_objInValidWhere =
            {
                institutioncode,
                infolist.marketCode,
                infolist.frontOrderSystemCode,
                infolist.productType
            };
            
        // �X�V����������̐���
        StringBuffer l_sbValidWhere = new StringBuffer();
        l_sbValidWhere.append(" institution_code = ? ");
        l_sbValidWhere.append(" and market_code = ? ");
        l_sbValidWhere.append(" and front_order_system_code = ? ");
        l_sbValidWhere.append(" and product_type = ? ");
        l_sbValidWhere.append(" and submit_order_route_div = ? ");
        
        // ���������R���e�i�̐���
        Object[] l_objValidWhere =
            {
                institutioncode,
                infolist.marketCode,
                infolist.frontOrderSystemCode,
                infolist.productType,
                infolist.submitOrderRouteDiv
            };            
        
        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();

            // �p�����[�^.��������.�ύX��L���t���O == 1�i�L���j�̏ꍇ�A
            if (infolist.changedValidFlag.equals(WEB3ValidFlag.ON))
            {
                //BatchedQuery[]�̍쐬�p
                ArrayList l_listQuery = new ArrayList();
                
                // �����ɊY������������R�[�h��S�Ė����ɍX�V����B
                BatchedQuery l_updQueryOff = BatchedQuery.createUpdateAllQuery(OrderSwitchingRow.TYPE,
                                                                                  l_sbInValidWhere.toString(),
                                                                                  l_objInValidWhere,
                                                                                  this.setColumValue("valid_flag", WEB3ValidFlag.OFF));
                                                                                  
                // List��add
                l_listQuery.add(l_updQueryOff);                                                                                  

                // �I�����ꂽ�����o�H��L���ɍX�V����B
                BatchedQuery l_updQueryOn = BatchedQuery.createUpdateAllQuery(OrderSwitchingRow.TYPE,
                                                                              l_sbValidWhere.toString(),
                                                                              l_objValidWhere,
                                                                              this.setColumValue("valid_flag", WEB3ValidFlag.ON));

                // List��add
                l_listQuery.add(l_updQueryOn);

                BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
                
                // �z��^�ɕϊ�
                l_listQuery.toArray(l_queries);   
                
                // �X�V�������s/�ʃg�����U�N�V�����ɂ��A�P�ƃR�~�b�g
                qp.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );

            }
            // �p�����[�^.��������.�ύX��L���t���O == 0�i�����j�̏ꍇ�A
            // �����ɊY������������R�[�h�𖳌��ɍX�V����B
            else if (infolist.changedValidFlag.equals(WEB3ValidFlag.OFF))
            {
                // �����ɊY������������R�[�h�𖳌��ɍX�V����B
                BatchedQuery l_updQueryOff = BatchedQuery.createUpdateAllQuery(OrderSwitchingRow.TYPE,
                                                                                l_sbValidWhere.toString(),
                                                                                l_objValidWhere,
                                                                                this.setColumValue("valid_flag", WEB3ValidFlag.OFF));
                                                                                
                BatchedQuery[] l_queries = {l_updQueryOff};
                
                // �X�V�������s/�ʃg�����U�N�V�����ɂ��A�P�ƃR�~�b�g
                qp.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );
            }
        }
        catch (DataFindException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *������������L���[�e�[�u���̃��R�[�h�������ɂ�蒊�o����B <BR>
     *<BR>
     *�P�j�����ɊY�����郌�R�[�h�𒊏o����B <BR>
     *�@@������ID�̏����Ń\�[�g����B <BR>
     *<BR>
     * ���������L���[�e�[�u������Astatus��"0":�������̃��R�[�h��I������B<BR>
     * <BR>
     * [����]<BR>
     * �،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h And<BR>
     *�@@�t�����g����������敪�R�[�h = �p�����[�^.�t�����g����������敪�R�[�h and <BR>
     *�@@�t�����g�����V�X�e���敪 = �p�����[�^.��������.�t�����g�����V�X�e���敪 and <BR>
     *�@@�t�����g��������敪�R�[�h = 1�F�������� and <BR>
     *�@@�����敪 = 0�F������ and <BR>
     *�@@�S���������敪 = 0�F�S�����ȊO and <BR>
     *�@@(����R�[�h�iSONAR�j in (11�F�ʏ����i�������j, 51�F�M�p��, 52�F�M�p��) 
     *�@@�@@or ����R�[�h�iSONAR�j is NULL) <BR>
     *<BR>
     *�Q�j�@@�������ʂ�ԋp����B <BR>
     *<BR>
     * @@param institutioncode -�،���ЃR�[�h
     * @@param frotExCode      -�t�����g����������敪�R�[�h
     * @@param infoUnit        -��������
     * @@return �L���[�e�[�u��List
     * @@throws WEB3BaseException
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     */
    protected List selectEqtypeOrderAll(String institutioncode, String frotExCode, 
                                        WEB3AdminOrderRouteSwitchingInfo infoUnit)throws WEB3BaseException, 
                                                                                          DataNetworkException, 
                                                                                          DataQueryException,
                                                                                          DataCallbackException
    {
        final String STR_METHOD_NAME = "selectEqtypeOrderAll()";
        log.entering(STR_METHOD_NAME);
       
        List EqOrderList = new ArrayList();       
        
        // ���o����������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and status = ? ");
        l_sbWhere.append(" and all_order_change_div = ? ");
        l_sbWhere.append(" and (sonar_traded_code in (?, ?, ?) ");
        l_sbWhere.append(" or sonar_traded_code is NULL) ");

        // ����ID�����w��
        String l_strSort = "account_id asc";
                
        // ���o�����R���e�i�̐���
        Object[] l_objWhere =
            {
                institutioncode,
                frotExCode,
                infoUnit.frontOrderSystemCode,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                WEB3StatusDef.NOT_DEAL, 
                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE, 
                WEB3TransactionTypeSONARDef.MARKET_TRADING, 
                WEB3TransactionTypeSONARDef.OPEN_CONTRACT, 
                WEB3TransactionTypeSONARDef.SETTLE_CONTRACT, 
            };
        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();

            // �L���[�e�[�u������������B 
            EqOrderList = qp.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return EqOrderList;
    }

    /**
     * ������������L���[�e�[�u���̃��R�[�h�̔����o�H�敪���X�V����B<BR>
     * <BR>
     * �P�j�����ɊY�����郌�R�[�h���X�V����B<BR>
     * <BR>
     * �@@[����]<BR>
     *�@@����ID = �p�����[�^.������������L���[Row.����ID and <BR>
     *�@@���ʃR�[�h = �p�����[�^.������������L���[Row.���ʃR�[�h and <BR>
     *�@@�����敪 = �p�����[�^.������������L���[Row.�����敪 <BR>
     * <BR>
     *�Q�j�@@�ύX��L���t���O�ɂ���āA���R�[�h�̔����o�H�敪���X�V����B <BR>
     *<BR>
     *�@@�Q�|�P�j�@@�p�����[�^.��������.�ύX��L���t���O == 1�i�L���j�̏ꍇ <BR>
     *�@@�@@�I�����������o�H�ɍX�V����B <BR>
     *<BR>
     *�@@�Q�|�Q�j�@@�p�����[�^.��������.�ύX��L���t���O == 0�i�����j�̏ꍇ <BR>
     *�@@�@@�����o�H��"������~"�ɍX�V����B <BR>
     *<BR>
     *�@@�y��Trade�z�⑫����.DB�X�V  <BR>
     *�@@�u������ؑ�_������������L���[�e�[�u��.xls�v���Q�ƁB <BR>
     *<BR>
     *�R�jcommit���s���B <BR>
     *�@@* QueryProcessor.doQueries(QueryProcessor.TX_CREATE_NEW, BatchedQuery[])<BR>
     * @@param ������������L���[Row - ������������L���[Row<BR>
     * @@param �������� - ��������I�u�W�F�N�g<BR>
     * @@roseuid 42D38F1701F8
     */
    protected void updateEqtypeOrderAll(
        HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow,
        WEB3AdminOrderRouteSwitchingInfo infoUnit)
        throws WEB3BaseException, DataNetworkException, DataQueryException, DataCallbackException
    {

        final String STR_METHOD_NAME = "updateEqtypeOrderAll(HostEqtypeOrderAllRow, WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);

        // �X�V����������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");
        l_sbWhere.append(" and order_request_number = ? ");
        l_sbWhere.append(" and status = ? ");
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                Long.toString(l_hostEqtypeOrderAllRow.getAccountId()),
                l_hostEqtypeOrderAllRow.getOrderRequestNumber(),
                l_hostEqtypeOrderAllRow.getStatus()
            };

        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();

            // �p�����[�^.��������.�ύX��L���t���O == 1�i�L���j�̏ꍇ�A
            if (infoUnit.changedValidFlag.equals(WEB3ValidFlag.ON))
            {                
                // BatchedQuery[]�̍쐬�p
                ArrayList l_listQuery = new ArrayList();
                
                // �L���[�e�[�u���̔����o�H���X�V����B 
                BatchedQuery l_updQueryDiv = BatchedQuery.createUpdateAllQuery(HostEqtypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere,
                    this.setColumValue("submit_order_route_div",infoUnit.submitOrderRouteDiv));
                
                // List��add
                l_listQuery.add(l_updQueryDiv);
                
                BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
                
                // �z��^�ɕϊ�
                l_listQuery.toArray(l_queries);   
                
                // �X�V�������s/�ʃg�����U�N�V�����ɂ��A�P�ƃR�~�b�g
                qp.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );                                    
            }
            // �p�����[�^.��������.�ύX��L���t���O == 0�i�����j�̏ꍇ�A
            else if (infoUnit.changedValidFlag.equals(WEB3ValidFlag.OFF))
            {
                // BatchedQuery[]�̍쐬�p
                ArrayList l_listQuery = new ArrayList();
                
                BatchedQuery l_updQueryStop = BatchedQuery.createUpdateAllQuery(HostEqtypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere,
                    this.setColumValue("submit_order_route_div",WEB3SubmitOrderRouteDivDef.ORDER_STOP));
                
                // List��add
                l_listQuery.add(l_updQueryStop);
                
                BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
                
                // �z��^�ɕϊ�
                l_listQuery.toArray(l_queries);     
                
                // �X�V�������s/�ʃg�����U�N�V�����ɂ��A�P�ƃR�~�b�g
                qp.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );
            }
        }
        catch (DataFindException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �e�[�u���J�����ƒl��Map�I�u�W�F�N�g�ɃZ�b�g����<BR>
     * <BR>
     * �P�j�p�����[�^���}�b�v�I�u�W�F�N�g�ɃZ�b�g���ĕԋp����<BR>
     * <BR>
     * @@param �e�[�u���J���� - �e�[�u���J����<BR>
     * @@param �J�����ɑ΂���l - <BR>
     * @@return Map<BR>
     * @@roseuid 
     */
    private Map setColumValue(String l_priColum, String l_priValue)
    {    
        Map objMap = new HashMap();
        
        objMap.put(l_priColum, l_priValue);
        objMap.put("last_updated_timestamp",GtlUtils.getSystemTimestamp());

        return objMap;
    }
    
    /**
     * ���������P�ʃe�[�u������A�֑ؑΏ۔����o�H�̃��R�[�h��I������B<BR>
     * <BR>
     *�P�j�g���A�J�E���g�}�l�[�W��.get�،���Ёi�j�ŁA�،���ЃI�u�W�F�N�g���擾����B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@�،���ЃR�[�h�F �p�����[�^.�،���ЃR�[�h <BR>
     *<BR>
     *�Q�j�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��i�j�ŁA�s��I�u�W�F�N�g���擾����B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@�،���ЁF �P�j�̏،���� <BR>
     *�@@�s��R�[�h�F �p�����[�^.��������.�s��R�[�h <BR>
     *<BR>
     *�R�j�����ɊY�����郌�R�[�h����������B <BR>
     *�@@������ID�̏����Ń\�[�g����B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@���XID�̐擪2�� = �P�j�̏،����.get�،����ID�i�j�̖߂�l and <BR>
     *�@@�����^�C�v = �p�����[�^.��������.�����^�C�v and <BR>
     *�@@�s��ID = �Q�j�̎s��.get�s��ID�i�j�̖߂�l and <BR>
     *�@@�����L����� = 1�F�I�[�v�� and <BR>
     *�@@����R�[�h�iSONAR�j in (11�F���ʊ���, 51�F�M�p��, 52�F�M�p�ԍ�) <BR>
     *<BR>
     *�S�j�������ʂ�ԋp����B<BR>
     * <BR> 
     * @@param institutioncode -�،���ЃR�[�h
     * @@param infoUnit        -��������
     * @@return �����P�ʃe�[�u��List
     * @@throws WEB3BaseException
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     */
    protected List selectEqtypeOrderUnit(String l_institutionCode, 
                                        WEB3AdminOrderRouteSwitchingInfo infoUnit)throws WEB3BaseException, 
                                                                                          DataNetworkException, 
                                                                                          DataQueryException,
                                                                                          DataCallbackException
    {
        final String STR_METHOD_NAME = "selectEqtypeOrderUnit(String, WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);
       
        List EqOrderUnitList = new ArrayList();       
        
        // ���o����������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" substr(branch_id,1,2) = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and market_id = ? ");
        l_sbWhere.append(" and order_open_status = ? ");
        l_sbWhere.append(" and sonar_traded_code in (?,?,?) ");

        try
        {
            //�s��ID�擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager
                = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

            // �P�j�g���A�J�E���g�}�l�[�W��.get�،���Ёi�j�ŁA�،���ЃI�u�W�F�N�g���擾����
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_institutionCode);

            // �Q�j�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��i�j�ŁA�s��I�u�W�F�N�g���擾����
            WEB3GentradeMarket l_market = null;        //�s��
            l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                l_institution, infoUnit.marketCode);
  
            // ���o�����R���e�i�̐���
            Object[] l_objWhere =
            {
                Long.toString(l_institution.getInstitutionId()),
                infoUnit.productType,
                Long.toString(l_market.getMarketId()),
                OrderOpenStatusEnum.OPEN,
                WEB3TransactionTypeSONARDef.MARKET_TRADING,
                WEB3TransactionTypeSONARDef.OPEN_CONTRACT,
                WEB3TransactionTypeSONARDef.SETTLE_CONTRACT,                
            };
            
            // ����ID�����w��
            String l_strSort = "account_id asc";

            QueryProcessor qp = Processors.getDefaultProcessor();

            // ���������P�ʃe�[�u������������B 
            EqOrderUnitList = qp.doFindAllQuery(EqtypeOrderUnitRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_nfe)
        {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00645,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return EqOrderUnitList;
    }
    
    /**
     * ���������P�ʃe�[�u�����X�V���邩�ۂ��𔻒肷��B<BR>
     *<BR>
     *�X�V����ꍇ�Atrue��ԋp����B <BR>
     *�X�V���Ȃ��ꍇ�Afalse��ԋp����B <BR>
     *<BR>
     *�P�j��������.�s��R�[�h == "JASDAQ"�̏ꍇ�A�I�[�N�V���� or �}�[�P�b�g���C�N���`�F�b�N����B <BR>
     * <BR>
     *  �P�|�P�j��������������擾����B<BR>
     *<BR>
     *�P�|�Q�j�����������.�X�����J�敪 == "�}�[�P�b�g���C�N"�̏ꍇ<BR>
     *�@@�P�|�Q�|�P�j��������.�t�����g�����V�X�e���敪 != "�}�[�P�b�g���C�N"�Ȃ�Afalse��ԋp����B<BR>
     *<BR>
     *�P�|�R�j�����������.�X�����J�敪 != "�}�[�P�b�g���C�N"�̏ꍇ<BR>
     *�@@�P�|�R�|�P�j��������.�t�����g�����V�X�e���敪 == "�}�[�P�b�g���C�N"�Ȃ�Afalse��ԋp����B<BR>
     *<BR>
     *�Q�jtrue��ԋp����B<BR>
     * @@param ���������P��Row - ���������P��Row <BR>
     * @@param ��������I�u�W�F�N�g - ��������I�u�W�F�N�g <BR>
     * @@throws WEB3BaseException 
     */
    private boolean isUpdateEqtypeOrderUnit(EqtypeOrderUnitRow l_oldOrderUnitRow, 
        WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".isUpdateEqtypeOrderUnit(EqtypeOrderUnitRow," +
                "WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);
        
        //�s�� == "JASDAQ"�̏ꍇ�A�I�[�N�V���� or �}�[�P�b�g���C�N���`�F�b�N����B                       
        if (WEB3MarketCodeDef.JASDAQ.equals(l_switchInfoObj.marketCode))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);       
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_tradingModule.
                getProductManager();

            //getTradedProduct(����ID, �s��ID)
            WEB3EquityTradedProduct l_tradedProduct;
            try 
            {
                l_tradedProduct = (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    l_oldOrderUnitRow.getProductId(), l_oldOrderUnitRow.getMarketId());
            } 
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME,
                    l_nfe.getMessage());
            }

            EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();

            //(�������.�X�����J�敪 == "�}�[�P�b�g���C�N"�̏ꍇ
            if(WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(l_tradedProductRow.getOpenOtcDiv()))
            {
                //�t�����g�����V�X�e���敪 != "�}�[�P�b�g���C�N"�Ȃ璍���P�ʃe�[�u�����X�V���Ȃ��B
                if(!WEB3FrontOrderSystemCodeDef.JASDAQ_MM.equals(l_switchInfoObj.frontOrderSystemCode))
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }   
            //(�������.�X�����J�敪 != "�}�[�P�b�g���C�N"�̏ꍇ
            else                       
            {
                //�t�����g�����V�X�e���敪 == "�}�[�P�b�g���C�N"�Ȃ璍���P�ʃe�[�u�����X�V���Ȃ��B
                if(WEB3FrontOrderSystemCodeDef.JASDAQ_MM.equals(l_switchInfoObj.frontOrderSystemCode))
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return true;
    }
    
   /**
    *���������P�ʃe�[�u���̔����o�H�敪���X�V����B <BR>
    * <BR>
     *�P�j���������P��Row�̔����o�H�敪��֑ؑΏۂ̔����o�H�敪�A <BR>
     *�@@�X�V���t�����ݓ����ɂ��ꂼ��X�V����B <BR>
     *<BR>
    * [����]<BR>
    *  �����P��ID = �p�����[�^.���������P��Row.�����P��ID <BR>
    * <BR>
    * [�X�V�l]<BR>
     *�@@�����o�H�敪 = �p�����[�^.��������.�����o�H�敪 <BR>
    *  �X�V���t = ���ݓ���<BR>
     *<BR>
     *�@@�y��Trade�z�⑫����.DB�X�V <BR>
     *�@@�u������ؑ�_���������P�ʃe�[�u���d�l.xls�v���Q�ƁB <BR>
    *  <BR>
    * �Q�jcommit���s���B<BR>
    * �@@* QueryProcessor.doQueries(QueryProcessor.TX_CREATE_NEW, <BR>
    * BatchedQuery[])<BR>
    * <BR>
    * @@param ���������P��Row - ���������P��Row <BR>
    * @@param ��������I�u�W�F�N�g - ��������I�u�W�F�N�g <BR>
    * @@throws WEB3BaseException 
    */
    private void updateEqtypeOrderUnit(EqtypeOrderUnitRow l_oldOrderUnitRow, 
        WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".updateEqtypeOrderUnit(EqtypeOrderUnitRow," +
        "WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);

        // �����o�H�敪���擾
        String l_sbumitRouteDiv = l_switchInfoObj.submitOrderRouteDiv;
        
        // �X�V����������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" order_unit_id = ? ");

        // �X�V�����R���e�i�̐���
        Object[] l_objWhere =
            {
                Long.toString(l_oldOrderUnitRow.getOrderUnitId())
            };

        //�����P�ʃ��R�[�h�̔����o�H�敪��֑ؑΏۂ̔����o�H�A�X�V���t�����ݓ����ɂ��ꂼ��X�V����B
        QueryProcessor qp;
        try
        {
            qp = Processors.getDefaultProcessor();
            //BatchedQuery[]�̍쐬�p
            ArrayList l_listQuery = new ArrayList();
            
            BatchedQuery l_updateQuery = BatchedQuery.createUpdateAllQuery(EqtypeOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere,
                this.setColumValue("submit_order_route_div",l_sbumitRouteDiv));
            
            l_listQuery.add(l_updateQuery);
            
            BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
            
            // �z��^�ɕϊ�
            l_listQuery.toArray(l_queries);  
            
            // �X�V�������s/�ʃg�����U�N�V�����ɂ��A�P�ƃR�~�b�g
            qp.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );
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
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���������̍X�V�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u �i������ؑցjupdate���������v�Q�ƁB<BR>
     * @@param l_strInstitutioncode - �،���ЃR�[�h<BR>
     * @@param l_strFrotExCode - �t�����g����������敪�R�[�h<BR>
     * @@param infoUnit - ��������<BR>
     * @@param l_orderSwitching - �֑ؑO������ؑ�<BR>
     * @@throws WEB3BaseException<BR>
     * @@throws DataNetworkException<BR>
     * @@throws DataQueryException<BR>
     * @@throws DataCallbackException<BR>
     */
    private void updateEqtypeOrder(String l_strInstitutioncode, 
        String l_strFrotExCode, 
        WEB3AdminOrderRouteSwitchingInfo l_infoUnit,
        WEB3GentradeOrderSwitching l_orderSwitching)
        throws WEB3BaseException, 
                    DataNetworkException, 
                    DataQueryException,
                    DataCallbackException
    {
        String STR_METHOD_NAME =
            ".updateEqtypeOrder(String, String, WEB3AdminOrderRouteSwitchingInfo, WEB3GentradeOrderSwitching)";
        log.entering(STR_METHOD_NAME);

        List l_lstEqtypeOrder = new ArrayList();
        //������������L���[�e�[�u���̃��R�[�h�������ɂ�蒊�o����B
        l_lstEqtypeOrder = this.selectEqtypeOrderAll(l_strInstitutioncode, l_strFrotExCode, l_infoUnit);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);           
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        HostEqtypeOrderAllRow l_hostEqtypeOrderAllRowLast = null;

        for (int i = 0; i < l_lstEqtypeOrder.size(); i++)
        {
            HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow = (HostEqtypeOrderAllRow)l_lstEqtypeOrder.get(i);
            

            //���[�v1��ځA�܂��́A������������L���[Row.����ID���O���R�[�h�ƈقȂ�ꍇ
            if (i == 0)
            {
                l_hostEqtypeOrderAllRowLast = l_hostEqtypeOrderAllRow;
                //�ڋq�����b�N����B 
                l_accountManager.lockAccount(l_strInstitutioncode, 
                    l_hostEqtypeOrderAllRow.getBranchCode(), 
                    l_hostEqtypeOrderAllRow.getAccountCode());
            } 
            else 
            {
                if (l_hostEqtypeOrderAllRow.getAccountId() != l_hostEqtypeOrderAllRowLast.getAccountId()) 
                {
                    //�ڋq�����b�N����B 
                    l_accountManager.lockAccount(l_strInstitutioncode, 
                        l_hostEqtypeOrderAllRow.getBranchCode(), 
                        l_hostEqtypeOrderAllRow.getAccountCode()); 
                }

                l_hostEqtypeOrderAllRowLast = l_hostEqtypeOrderAllRow;
            }

            //������������L���[�e�[�u���̔����o�H�敪���X�V����B 
            this.updateEqtypeOrderAll(l_hostEqtypeOrderAllRow, l_infoUnit);
        }

        //�֑ؑO������ؑւ��A�����^�C�v�ɑΉ����钍���P�ʃe�[�u���̍X�V�ΏۂƂȂ�
        //�����o�H���ۂ��𔻒肷��B
        //[����]
        //�֑ؑO������ؑցF ����.�֑ؑO������ؑ�
        boolean l_blnUpdate = this.isOrderUnitUpdateObjSwitchOrderRoute(l_orderSwitching);

        if (!l_blnUpdate)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        if (!WEB3ValidFlag.ON.equals(l_infoUnit.changedValidFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        try
        {
            EqtypeOrderUnitRow l_eqtypeOrderUnitRowlast = null;
            //���������P�ʃe�[�u������A�֑ؑΏ۔����o�H�̃��R�[�h����������B
            List l_lstEqtypeOrderUnit = this.selectEqtypeOrderUnit(l_strInstitutioncode, l_infoUnit);
            for (int i = 0; i < l_lstEqtypeOrderUnit.size(); i++)
            {
                EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_lstEqtypeOrderUnit.get(i);
                
                //���������P�ʃe�[�u�����X�V���邩�ۂ��𔻒肷��B 
                boolean l_blnUpdateOrderUnit = this.isUpdateEqtypeOrderUnit(l_eqtypeOrderUnitRow, l_infoUnit);
    
                //���������P�ʃe�[�u�����X�V���Ȃ��ꍇ
                if (!l_blnUpdateOrderUnit)
                {
                    continue;
                }
    
                    if (i == 0)
                    {
                        l_eqtypeOrderUnitRowlast = l_eqtypeOrderUnitRow;
                        MainAccount l_mainAccount = l_accountManager.getMainAccount(l_eqtypeOrderUnitRow.getAccountId());
                        //�ڋq�����b�N����B 
                        l_accountManager.lockAccount(l_strInstitutioncode, 
                            l_mainAccount.getBranch().getBranchCode(), 
                            l_mainAccount.getAccountCode());
                    }
                    else
                    {
                        if (l_eqtypeOrderUnitRow.getAccountId() != l_eqtypeOrderUnitRowlast.getAccountId())
                        {
                            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_eqtypeOrderUnitRow.getAccountId());
                            //�ڋq�����b�N����B 
                            l_accountManager.lockAccount(l_strInstitutioncode, 
                                l_mainAccount.getBranch().getBranchCode(), 
                                l_mainAccount.getAccountCode());
                        }

                        l_eqtypeOrderUnitRowlast = l_eqtypeOrderUnitRow;
                    }
    
                //���������P�ʃe�[�u���̔����o�H�敪���X�V����B 
                this.updateEqtypeOrderUnit(l_eqtypeOrderUnitRow, l_infoUnit);
            }
        }
        catch (NotFoundException l_notFoundException)
        {
            log.error(STR_METHOD_NAME, l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_notFoundException.getMessage());
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �敨OP�����̍X�V�������s���B <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i������ؑցjupdate�敨OP�����v�Q�ƁB<BR>
     * @@param l_strInstitutioncode - �،���ЃR�[�h<BR>
     * @@param l_strFrotExCode - �t�����g����������敪�R�[�h<BR>
     * @@param l_infoUnit - ��������<BR>
     * @@param l_orderSwitching - �֑ؑO������ؑ�<BR>
     * @@throws WEB3BaseException
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     */
    private void updateIfoOrder(String l_strInstitutioncode,
        String l_strFrotExCode,
        WEB3AdminOrderRouteSwitchingInfo l_infoUnit,
        WEB3GentradeOrderSwitching l_orderSwitching)
        throws WEB3BaseException,
                    DataNetworkException,
                    DataQueryException,
                    DataCallbackException
    {
        String STR_METHOD_NAME =
            ".updateIfoOrder(String, String, WEB3AdminOrderRouteSwitchingInfo, WEB3GentradeOrderSwitching)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstIfoOrderAll = new ArrayList();
        //�敨OP��������L���[�e�[�u���̃��R�[�h�������ɂ�蒊�o����B 
        l_lstIfoOrderAll = this.selectIfoOrderAll(l_strInstitutioncode, l_strFrotExCode, l_infoUnit);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);           
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        HostFotypeOrderAllRow l_hostFotypeOrderAllRowlast = null;
        for (int i = 0; i < l_lstIfoOrderAll.size(); i++)
        {
            HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstIfoOrderAll.get(i);
            
            if (i == 0)
            {
                l_hostFotypeOrderAllRowlast = l_hostFotypeOrderAllRow;
                //�ڋq�����b�N����B
                l_accountManager.lockAccount(l_strInstitutioncode, 
                    l_hostFotypeOrderAllRow.getBranchCode(), 
                    l_hostFotypeOrderAllRow.getAccountCode());
            }
            else
            {
                if (l_hostFotypeOrderAllRow.getAccountId() != l_hostFotypeOrderAllRowlast.getAccountId())
                {
                    //�ڋq�����b�N����B
                    l_accountManager.lockAccount(l_strInstitutioncode, 
                        l_hostFotypeOrderAllRow.getBranchCode(), 
                        l_hostFotypeOrderAllRow.getAccountCode());
                }
                
                l_hostFotypeOrderAllRowlast = l_hostFotypeOrderAllRow;
            }
            
            //�敨OP��������L���[�e�[�u���̔����o�H�敪���X�V����B
            this.updateIfoOrderAll(l_hostFotypeOrderAllRow, l_infoUnit);
        }
        
        // �֑ؑO������ؑւ��A�����^�C�v�ɑΉ����钍���P�ʃe�[�u���̍X�V�ΏۂƂȂ�
        // �����o�H���ۂ��𔻒肷��B
        // [����]
        // �֑ؑO������ؑցF ����.�֑ؑO������ؑ�
        boolean l_blnOrderUnitUpdateObjSwitchOrderRoute =
            this.isOrderUnitUpdateObjSwitchOrderRoute(l_orderSwitching);
        
        if (!l_blnOrderUnitUpdateObjSwitchOrderRoute)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        if (!WEB3ValidFlag.ON.equals(l_infoUnit.changedValidFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        List l_lstIfoOrderUnit = new ArrayList();
        //�敨OP�����P�ʃe�[�u������A�֑ؑΏ۔����o�H�̃��R�[�h����������B 
        l_lstIfoOrderUnit = this.selectIfoOrderUnit(l_strInstitutioncode, l_infoUnit);

        try
        {
            IfoOrderUnitRow l_ifoOrderUnitRowlast = null;
            for (int i = 0; i < l_lstIfoOrderUnit.size(); i++)
            {
                IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_lstIfoOrderUnit.get(i);
                
                if (i == 0)
                {
                    l_ifoOrderUnitRowlast = l_ifoOrderUnitRow;
                    MainAccount l_mainAccount = l_accountManager.getMainAccount(l_ifoOrderUnitRow.getAccountId());
                    //�ڋq�����b�N����B 
                    l_accountManager.lockAccount(l_strInstitutioncode, 
                        l_mainAccount.getBranch().getBranchCode(), 
                        l_mainAccount.getAccountCode());
                }
                else
                {
                    if (l_ifoOrderUnitRow.getAccountId() != l_ifoOrderUnitRowlast.getAccountId())
                    {
                        MainAccount l_mainAccount = l_accountManager.getMainAccount(l_ifoOrderUnitRow.getAccountId());
                        //�ڋq�����b�N����B 
                        l_accountManager.lockAccount(l_strInstitutioncode, 
                            l_mainAccount.getBranch().getBranchCode(), 
                            l_mainAccount.getAccountCode());
                    }
                    
                    l_ifoOrderUnitRowlast = l_ifoOrderUnitRow;
                }
                
                //�敨OP�����P�ʃe�[�u���̔����o�H�敪���X�V����B 
                this.updateIfoOrderUnit(l_ifoOrderUnitRow, l_infoUnit);
            }
        }
        catch (NotFoundException l_notFoundException)
        {
            log.error(STR_METHOD_NAME, l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_notFoundException.getMessage());
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *�敨OP��������L���[�e�[�u���̃��R�[�h�������ɂ�蒊�o����B <BR>
     *<BR>
     *�P�j�����ɊY�����郌�R�[�h�𒊏o����B <BR>
     *�@@������ID�̏����Ń\�[�g����B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h and <BR>
     *�@@�t�����g����������敪�R�[�h = �p�����[�^.�t�����g����������敪�R�[�h and <BR>
     *�@@�t�����g�����V�X�e���敪 = �p�����[�^.��������.�t�����g�����V�X�e���敪 and <BR>
     *�@@�t�����g��������敪�R�[�h = 1�F�������� and <BR>
     *�@@�����敪 = 0�F������ and <BR>
     *�@@�S���������敪 = 0�F�S�����ȊO <BR>
     *<BR>
     *�Q�j�@@�������ʂ�ԋp����B<BR>
     * <BR> 
     * @@param institutioncode -�،���ЃR�[�h
     * @@param frotExCode -�t�����g����������敪�R�[�h
     * @@param infoUnit -��������
     * @@return List
     */
    private List selectIfoOrderAll(String l_strInstitutioncode, 
        String l_strFrotExCode, 
        WEB3AdminOrderRouteSwitchingInfo l_infoUnit) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = ".selectIfoOrderAll(String, String, WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);

        // ���o����������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and status = ? ");
        l_sbWhere.append(" and all_order_change_div = ? ");
        
        // ����ID�����w��
        String l_strSort = " account_id asc ";

        // ���o�����R���e�i�̐���
        Object[] l_objWhere = 
        {
            l_strInstitutioncode,
            l_strFrotExCode,
            l_infoUnit.frontOrderSystemCode,
            WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
            WEB3StatusDef.NOT_DEAL,
            WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE
        };

        List l_lstIfoOrder = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
    
            // �敨OP��������L���[�e�[�u������������B 
            l_lstIfoOrder = l_queryProcessor.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        return l_lstIfoOrder;
    }
    
    /**
     *�敨OP�����P�ʃe�[�u������A�֑ؑΏ۔����o�H�̃��R�[�h����������B <BR>
     *<BR>
     *�P�j�g���A�J�E���g�}�l�[�W��.get�،���Ёi�j�ŁA�،���ЃI�u�W�F�N�g���擾����B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@�،���ЃR�[�h�F �p�����[�^.�،���ЃR�[�h <BR>
     *<BR>
     *�Q�j�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��i�j�ŁA�s��I�u�W�F�N�g���擾����B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@�،���ЁF �P�j�̏،���� <BR>
     *�@@�s��R�[�h�F �p�����[�^.��������.�s��R�[�h <BR>
     *<BR>
     *�R�j�����ɊY�����郌�R�[�h����������B <BR>
     *�@@������ID�̏����Ń\�[�g����B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@���XID�̐擪2�� = �P�j�̏،����.get�،����ID�i�j�̖߂�l and <BR>
     *�@@�����^�C�v = �p�����[�^.��������.�����^�C�v and <BR>
     *�@@�s��ID = �Q�j�̎s��.get�s��ID�i�j�̖߂�l and <BR>
     *�@@�����L����� = 1�F�I�[�v�� and <BR>
     *<BR>
     *�S�j�@@�������ʂ�ԋp����B<BR>
     * <BR> 
     * @@param l_strInstitutionCode -�،���ЃR�[�h
     * @@param l_infoUnit        -��������
     * @@return �����P�ʃe�[�u��List
     * @@throws WEB3BaseException
     */
    private List selectIfoOrderUnit(String l_strInstitutionCode, 
        WEB3AdminOrderRouteSwitchingInfo l_infoUnit)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = ".selectIfoOrderUnit(String, WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);
       
        List IfoOrderUnitList = new ArrayList();       
        
        // ���o����������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" substr(branch_id,1,2) = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and market_id = ? ");
        l_sbWhere.append(" and order_open_status = ? ");

        try
        {
            //�s��ID�擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager
                = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

            // �P�j�g���A�J�E���g�}�l�[�W��.get�،���Ёi�j�ŁA�،���ЃI�u�W�F�N�g���擾����
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);

            // �Q�j�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��i�j�ŁA�s��I�u�W�F�N�g���擾����
            WEB3GentradeMarket l_market = null;        //�s��
            l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                l_institution, l_infoUnit.marketCode);
  
            // ���o�����R���e�i�̐���
            Object[] l_objWhere =
            {
                Long.toString(l_institution.getInstitutionId()),
                l_infoUnit.productType,
                Long.toString(l_market.getMarketId()),
                OrderOpenStatusEnum.OPEN 
            };
            
            // ����ID�����w��
            String l_strSort = "account_id asc";

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // �敨OP�����P�ʃe�[�u������������B 
            IfoOrderUnitList = l_queryProcessor.doFindAllQuery(IfoOrderUnitRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_nfe)
        {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00645,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return IfoOrderUnitList;
    }

    /**
     * (is�����P�ʍX�V�Ώ۔����o�H)
     *�֑ؑO������ؑւ��A�����^�C�v�ɑΉ����钍���P�ʃe�[�u���̍X�V�ΏۂƂȂ�<BR>
�@@   *�����o�H���ۂ��𔻒肷��B <BR>
     *<BR>
     *�X�V�ΏۂƂȂ锭���o�H�̏ꍇ�Atrue��ԋp����B  <BR>
     *�X�V�ΏۂƂȂ锭���o�H�łȂ��ꍇ�Afalse��ԋp����B  <BR>
     *<BR>
     *�P�j�L���Ȑ֑ؑO������ؑւ��擾�ł��Ȃ��i����.�֑ؑO������ؑ� == null�j�A <BR>
     *�@@�@@�܂��́A�����G���W����SONAR�i����.�֑ؑO������ؑ�.isSONAR() == true�j�̏ꍇ <BR>
     *<BR>
     *�@@�P�|�P�jtrue��ԋp����B <BR>
     *<BR>
     *�Q�jfalse��ԋp����B<BR>
     *<BR>
     *@@param l_orderSwitching - �֑ؑO������ؑ�
     *@@return boolean
     */
    private boolean isOrderUnitUpdateObjSwitchOrderRoute(
        WEB3GentradeOrderSwitching l_orderSwitching) 
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = " isOrderUnitUpdateObjSwitchOrderRoute(WEB3GentradeOrderSwitching)";
        log.entering(STR_METHOD_NAME);

        //�P�j�L���Ȑ֑ؑO������ؑւ��擾�ł��Ȃ��i����.�֑ؑO������ؑ� == null�j�A
        //�@@�܂��́A�����G���W����SONAR�i����.�֑ؑO������ؑ�.isSONAR() == true�j�̏ꍇ
        //�P�|�P�jtrue��ԋp����B
        if (l_orderSwitching == null || l_orderSwitching.isSONAR())
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�Q�jfalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     *�敨OP��������L���[�e�[�u���̔����o�H�敪���X�V����B <BR>
     *<BR>
     *�P�j�����ɊY�����郌�R�[�h���X�V����B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@����ID = �p�����[�^.�敨OP��������L���[Row.����ID and <BR>
     *�@@���ʃR�[�h = �p�����[�^.�敨OP��������L���[Row.���ʃR�[�h and <BR>
     *�@@�����敪 = �p�����[�^.�敨OP��������L���[Row.�����敪 <BR>
     *<BR>
     *�Q�j�@@�ύX��L���t���O�ɂ���āA���R�[�h�̔����o�H�敪���X�V����B <BR>
     *<BR>
     *�@@�Q�|�P�j�@@�p�����[�^.��������.�ύX��L���t���O == 1�i�L���j�̏ꍇ <BR>
     *�@@�@@�I�����������o�H�ɍX�V����B <BR>
     *<BR>
     *�@@�Q�|�Q�j�@@�p�����[�^.��������.�ύX��L���t���O == 0�i�����j�̏ꍇ <BR>
     *�@@�@@�����o�H��"������~"�ɍX�V����B <BR>
     *<BR>
     *�@@�y��Trade�z�⑫����.DB�X�V  <BR>
     *�@@�u������ؑ�_�敨OP��������L���[�e�[�u��.xls�v���Q�ƁB <BR>
     *<BR>
     *�R�jcommit���s���B <BR>
     *�@@* QueryProcessor.doQueries(QueryProcessor.TX_CREATE_NEW, BatchedQuery[])<BR>
     *<BR>
     *@@param �敨OP��������L���[Row - �敨OP��������L���[Row <BR>
     *@@param �������� - �������� <BR>
     *@@throws WEB3BaseException <BR>
     */
    private void updateIfoOrderAll(HostFotypeOrderAllRow l_hostFotypeOrderAllRow, 
        WEB3AdminOrderRouteSwitchingInfo l_infoUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".updateIfoOrderAll(HostFotypeOrderAllRow, WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);

        // �X�V����������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");
        l_sbWhere.append(" and order_request_number = ? ");
        l_sbWhere.append(" and status = ? ");
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                Long.toString(l_hostFotypeOrderAllRow.getAccountId()),
                l_hostFotypeOrderAllRow.getOrderRequestNumber(),
                l_hostFotypeOrderAllRow.getStatus()
            };

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // �p�����[�^.��������.�ύX��L���t���O == 1�i�L���j�̏ꍇ�A
            if (WEB3ValidFlag.ON.equals(l_infoUnit.changedValidFlag))
            {                
                // BatchedQuery[]�̍쐬�p
                ArrayList l_listQuery = new ArrayList();
                
                // �L���[�e�[�u���̔����o�H���X�V����B 
                BatchedQuery l_updQueryDiv = BatchedQuery.createUpdateAllQuery(HostFotypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere,
                    this.setColumValue("submit_order_route_div",l_infoUnit.submitOrderRouteDiv));
                
                // List��add
                l_listQuery.add(l_updQueryDiv);
                
                BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
                
                // �z��^�ɕϊ�
                l_listQuery.toArray(l_queries);   
                
                // �X�V�������s/�ʃg�����U�N�V�����ɂ��A�P�ƃR�~�b�g
                l_queryProcessor.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );                                    
            }
            // �p�����[�^.��������.�ύX��L���t���O == 0�i�����j�̏ꍇ�A
            else if (WEB3ValidFlag.OFF.equals(l_infoUnit.changedValidFlag))
            {
                // BatchedQuery[]�̍쐬�p
                ArrayList l_listQuery = new ArrayList();
                
                BatchedQuery l_updQueryStop = BatchedQuery.createUpdateAllQuery(HostFotypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere,
                    this.setColumValue("submit_order_route_div",WEB3SubmitOrderRouteDivDef.ORDER_STOP));
                
                // List��add
                l_listQuery.add(l_updQueryStop);
                
                BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
                
                // �z��^�ɕϊ�
                l_listQuery.toArray(l_queries);     
                
                // �X�V�������s/�ʃg�����U�N�V�����ɂ��A�P�ƃR�~�b�g
                l_queryProcessor.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );
            }
        }
        catch (DataFindException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *�敨OP�����P�ʃe�[�u���̔����o�H�敪���X�V����B <BR>
     *<BR>
     *�P�j�敨OP�����P��Row�̔����o�H�敪��֑ؑΏۂ̔����o�H�敪�A <BR>
     *�@@�X�V���t�����ݓ����ɂ��ꂼ��X�V����B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@�����P��ID = �p�����[�^.�敨OP�����P��Row.�����P��ID <BR>
     *<BR>
     *�@@[�X�V�l] <BR>
     *�@@�����o�H�敪 = �p�����[�^.��������.�����o�H�敪 <BR>
     *�@@�X�V���t = ���ݓ��� <BR>
     *<BR>
     *�@@�y��Trade�z�⑫����.DB�X�V <BR>
     *�@@�u������ؑ�_�敨OP�����P�ʃe�[�u���d�l.xls�v���Q�ƁB <BR>
     *<BR>
     *�Q�jcommit���s���B <BR>
     *�@@* QueryProcessor.doQueries(QueryProcessor.TX_CREATE_NEW, BatchedQuery[])<BR>
     * <BR>
     * @@param �敨OP��������L���[Row - �敨OP��������L���[Row <BR>
     * @@param ��������I�u�W�F�N�g - ��������I�u�W�F�N�g <BR>
     * @@throws WEB3BaseException 
     */
     private void updateIfoOrderUnit(IfoOrderUnitRow l_ifoOrderUnitRow, 
         WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj) 
         throws WEB3BaseException
     {
         final String STR_METHOD_NAME = ".updateIfoOrderUnit(IfoOrderUnitRow," +
         "WEB3AdminOrderRouteSwitchingInfo)";
         log.entering(STR_METHOD_NAME);

         // �����o�H�敪���擾
         String l_sbumitRouteDiv = l_switchInfoObj.submitOrderRouteDiv;
         
         // �X�V����������̐���
         StringBuffer l_sbWhere = new StringBuffer();
         l_sbWhere.append(" order_unit_id = ? ");

         // �X�V�����R���e�i�̐���
         Object[] l_objWhere =
             {
                 Long.toString(l_ifoOrderUnitRow.getOrderUnitId())
             };

         //�����P�ʃ��R�[�h�̔����o�H�敪��֑ؑΏۂ̔����o�H�A�X�V���t�����ݓ����ɂ��ꂼ��X�V����B
         QueryProcessor l_queryProcessor;
         try
         {
             l_queryProcessor = Processors.getDefaultProcessor();
             //BatchedQuery[]�̍쐬�p
             ArrayList l_listQuery = new ArrayList();
             
             BatchedQuery l_updateQuery = BatchedQuery.createUpdateAllQuery(IfoOrderUnitRow.TYPE,
                 l_sbWhere.toString(),
                 l_objWhere,
                 this.setColumValue("submit_order_route_div",l_sbumitRouteDiv));
             
             l_listQuery.add(l_updateQuery);
             
             BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
             
             // �z��^�ɕϊ�
             l_listQuery.toArray(l_queries);  
             
             // �X�V�������s/�ʃg�����U�N�V�����ɂ��A�P�ƃR�~�b�g
             l_queryProcessor.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );
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
         log.exiting(STR_METHOD_NAME);
     }

    /**
     * (get�L�������o�H)<BR>
     * �L���Ȕ�����ؑփI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �P�j�L���Ȕ�����ؑփI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@* WEB3GentradeOrderSwitching.getOnOrderSwitching() <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h�F �p�����[�^.�،���ЃR�[�h <BR>
     * �@@�����^�C�v�F �p�����[�^.��������.�����^�C�v <BR>
     * �@@�s��R�[�h�F �p�����[�^.��������.�s��R�[�h <BR>
     * �@@�t�����g�����V�X�e���敪�F �p�����[�^.��������.�t�����g�����V�X�e���敪 <BR>
     * <BR>
     * �Q�j�߂�l��ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_orderRouteSwitchingInfo - ��������
     * @@return WEB3GentradeOrderSwitching
     * @@throws WEB3BaseException
     */
     private WEB3GentradeOrderSwitching getExpirationOrderRoute(
         String l_strInstitutionCode,
         WEB3AdminOrderRouteSwitchingInfo l_orderRouteSwitchingInfo) throws WEB3BaseException
     {
         final String STR_METHOD_NAME = " getExpirationOrderRoute(String, WEB3AdminOrderRouteSwitchingInfo)";
         log.entering(STR_METHOD_NAME);

         EnumeratedManager l_enumeratedManager = EnumeratedManager.getInstance();

         int l_intProductType = Integer.parseInt(l_orderRouteSwitchingInfo.productType);
         ProductTypeEnum l_productTypeEnum = (ProductTypeEnum)l_enumeratedManager.valueFromInt(
             ProductTypeEnum.class,
             l_intProductType);

         //�P�j�L���Ȕ�����ؑփI�u�W�F�N�g���擾����B
         WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
             WEB3GentradeOrderSwitching.getOnOrderSwitching(
                 l_strInstitutionCode,
                 l_productTypeEnum,
                 l_orderRouteSwitchingInfo.marketCode,
                 l_orderRouteSwitchingInfo.frontOrderSystemCode);

         //�Q�j�߂�l��ԋp����B
         log.exiting(STR_METHOD_NAME);
         return l_gentradeOrderSwitching;
     }
}
@
