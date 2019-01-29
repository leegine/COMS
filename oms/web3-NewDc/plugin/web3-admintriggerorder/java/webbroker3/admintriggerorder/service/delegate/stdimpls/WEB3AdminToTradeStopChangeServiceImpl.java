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
filename	WEB3AdminToTradeStopChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�XImpl(WEB3AdminToTradeStopChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/05 �]�V�q(���u) �V�K�쐬
                 : 2006/04/12 �]�V�q(���u) �d�l�ύX�E���f��055
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInfoUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdInputResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.triggerorder.base.data.TriggerOrderStopDao;
import webbroker3.triggerorder.base.data.TriggerOrderStopPK;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�XImpl)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�X�����N���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToTradeStopChangeServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminToTradeStopChangeService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopChangeServiceImpl.class);
    
    /**
     * @@roseuid 4430DDF40271
     */
    public WEB3AdminToTradeStopChangeServiceImpl() 
    {
     
    }
    
    /**
     * �戵��~�ύX�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���g���K�[�����Ǘ��ҁE�戵��~�ύX���̓��N�G�X�g�̏ꍇ<BR>
     * �@@this.get���͉��()���R�[������B<BR>
     * <BR>
     * ���g���K�[�����Ǘ��ҁE�戵��~�ύX�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�ύX()���R�[������B<BR>
     * <BR>
     * ���g���K�[�����Ǘ��ҁE�戵��~�ύX�������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�ύX()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410FB8102AD
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
        if (l_request instanceof WEB3AdminToTradeStopUpdInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminToTradeStopUpdInputRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToTradeStopUpdConfirmRequest)
        {
            l_response = this.validateChange((WEB3AdminToTradeStopUpdConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToTradeStopUpdCompleteRequest)
        {
            l_response = this.submitChange((WEB3AdminToTradeStopUpdCompleteRequest) l_request);
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
     * �戵��~�ύX���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�X�jget���͉�ʁv�Q�ƁB<BR>
     * ============================================================== <BR>
     *    �V�[�P���X�}�F�u�i�g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�X�jget���͉�ʁv<BR>
     *    ��̈ʒu�F1.5.1 (*)���ꎷ�s�����戵��~Row�̎擾<BR>
     *    ���N�G�X�g�f�[�^.���ꎷ�s�����戵��~ID��<BR>
     *    �Y��������ꎷ�s�����戵��~Row���擾����B<BR>
     *    ��Dao�N���X�̃��\�b�h���g�p�B<BR>
     *    �擾�ł��Ȃ������ꍇ�A�Y���f�[�^�Ȃ��̃V�X�e���G���[���X���[����B<BR>
     *    class : WEB3SystemLayerException<BR>
     *    tag : SYSTEM_ERROR_80005<BR>
     * ============================================================== <BR>
     * <BR>
     * ============================================================== <BR>
     *    �V�[�P���X�}�F�u�i�g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�X�jget���͉�ʁv<BR>
     *    ��̈ʒu�F1.6.2 get���ꎷ�s�����戵��~�ꗗ(String, String[], String, <BR>
     *    String, WEB3AdminToTradeStopSortKey[])<BR>
     *    null���ԋp���ꂽ�ꍇ�A<BR>
     *    �u�����ɊY������f�[�^�����݂��Ȃ��B�v��<BR>
     *    �Ɩ��G���[���X���[����B<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_01037<BR>
     * ============================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�ύX���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopUpdInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410FBDA00F8
     */
    protected WEB3AdminToTradeStopUpdInputResponse getInputScreen(WEB3AdminToTradeStopUpdInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToTradeStopUpdInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, false);
        
        //1.4.validate���X����(���X�R�[�h : String[])
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5.(*)�����ʎ戵��~�ύX�i���N�G�X�g�f�[�^.���ꎷ�s�����戵��~ID != null�j�̏ꍇ
        List l_lisRows = null;
        if (WEB3StringTypeUtility.isNotEmpty(l_request.triggerTradeStopId))
        {
            try
            {
                //1.5.1.(*)���ꎷ�s�����戵��~Row�̎擾
                //���N�G�X�g�f�[�^.���ꎷ�s�����戵��~ID��
                //�Y��������ꎷ�s�����戵��~Row���擾����B
                //��Dao�N���X�̃��\�b�h���g�p�B
                //�擾�ł��Ȃ������ꍇ�A�Y���f�[�^�Ȃ��̃V�X�e���G���[���X���[����B
                TriggerOrderStopRow l_triggerOrderStopRow = TriggerOrderStopDao.findRowByPk(
                    Long.parseLong(l_request.triggerTradeStopId));
                l_lisRows = new ArrayList();
                l_lisRows.add(l_triggerOrderStopRow);
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
        }
        //1.6.(*)��L�ȊO�̏ꍇ
        else
        {
            //1.6.1.get�،���ЃR�[�h( )
            String l_strInstitutionCode = l_admin.getInstitutionCode();
            
            //1.6.2.get���ꎷ�s�����戵��~�ꗗ(String, String[], String, 
            //String, WEB3AdminToTradeStopSortKey[])
            //null���ԋp���ꂽ�ꍇ�A�u�����ɊY������f�[�^�����݂��Ȃ��B�v��
            //�Ɩ��G���[���X���[����B
            l_lisRows = WEB3AdminToDataManager.getTriggerOrderStopList(
                l_strInstitutionCode, 
                l_request.branchCode, 
                l_request.tradeStopDiv, 
                null, 
                null);
            
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����ɊY������f�[�^�����݂��Ȃ��B");
            }
        }
        
        //1.7.get�،����( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.8.create�戵��~���ꗗ(�،����, ���ꎷ�s�����戵��~Row[])
        TriggerOrderStopRow[] l_rows = new TriggerOrderStopRow[l_lisRows.size()];
        l_lisRows.toArray(l_rows);
        WEB3AdminToTradeStopInfoUnit[] l_tradeStopInfoUnits = 
            WEB3AdminToDataManager.createTradeStopInfoList(
                l_institution, 
                l_rows);
        
        //1.9.createResponse( )
        WEB3AdminToTradeStopUpdInputResponse l_response = 
            (WEB3AdminToTradeStopUpdInputResponse) l_request.createResponse();

        //1.10.(*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B
        //���ݎ���      ���@@GtlUtils.getSystemTimestamp()
        //�戵��~���ꗗ  ���@@create�戵��~���ꗗ()�̖߂�l
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        l_response.tradeStopInfoList = l_tradeStopInfoUnits;
        
        //1.11
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�ύX)<BR>
     * �戵��~�ύX�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�X�jvalidate�ύX�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�ύX�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopUpdConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410FBDA0117
     */
    protected WEB3AdminToTradeStopUpdConfirmResponse validateChange(WEB3AdminToTradeStopUpdConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminToTradeStopUpdConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, true);
        
        //1.4.createResponse( )
        WEB3AdminToTradeStopUpdConfirmResponse l_response = 
            (WEB3AdminToTradeStopUpdConfirmResponse) l_request.createResponse();
        
        //���X�|���X������A���ݎ������Z�b�g����B
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        //1.5
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�ύX)<BR>
     * �戵��~�ύX�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�X�jsubmit�ύX�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�ύX�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopUpdCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410FBDA0136
     */
    protected WEB3AdminToTradeStopUpdCompleteResponse submitChange(WEB3AdminToTradeStopUpdCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminToTradeStopUpdCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3.validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, true);
        
        //1/4 validate����p�X���[�h(�p�X���[�h : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5.(*)���N�G�X�g�f�[�^.�戵��~���ꗗ�̗v�f�i=�戵��~���j�����ALoop����
        int l_intStopInfoLen = l_request.tradeStopInfoList.length;
        for (int i = 0; i < l_intStopInfoLen; i++)
        {
            //1.5.1.HashMap( )
            Map l_map = new HashMap();
            
            //1.5.2.(*)�ύX��X�����J�敪�`�F�b�N
            if (WEB3MarketCodeDef.JASDAQ.equals(l_request.tradeStopInfoList[i].marketCode))
            {
                if (!WEB3Toolkit.isEquals(l_request.tradeStopInfoList[i].otcOpenDiv, l_request.tradeStopInfoList[i].aftOtcOpenDiv))
                {
                    l_map.put("addition", l_request.tradeStopInfoList[i].aftOtcOpenDiv);
                }
            }
            
            //1.5.3.(*)�ύX���~���R�`�F�b�N
            if (!WEB3Toolkit.isEquals(l_request.tradeStopInfoList[i].stopReason, l_request.tradeStopInfoList[i].aftChangeStopReason))
            {
                l_map.put("stop_reason", l_request.tradeStopInfoList[i].aftChangeStopReason);
            }

            //1.5.4.(*)�ύX��L������To�`�F�b�N
            if (!l_request.tradeStopInfoList[i].expirationEndDate.equals(
                l_request.tradeStopInfoList[i].aftChangeExpirationEndDate))
            {
                l_map.put("valid_term_to", l_request.tradeStopInfoList[i].aftChangeExpirationEndDate);
            }
            
            //1.5.5.(*)�����Ώۂ̗v�f.������~�󋵈ꗗ�̗v�f�i=������~�󋵁j�����ALoop����
            int l_intStopStateLen = l_request.tradeStopInfoList[i].orderStopStateList.length;
            for (int j = 0; j < l_intStopStateLen; j++)
            {
                //1.5.5.1.(*)�ύX���~�t���O�`�F�b�N
                if (l_request.tradeStopInfoList[i].orderStopStateList[j].stopFlag != 
                    l_request.tradeStopInfoList[i].orderStopStateList[j].aftChangeStopFlag)
                {
                    String l_strColumn = null;
                    if (WEB3TriggerOrderTypeDef.SUCC.equals(
                        l_request.tradeStopInfoList[i].orderStopStateList[j].triggerOrderType))
                    {
                        l_strColumn = "succ_order_stop_flag";
                    }
                    else if (WEB3TriggerOrderTypeDef.OCO.equals(
                        l_request.tradeStopInfoList[i].orderStopStateList[j].triggerOrderType))
                    {
                        l_strColumn = "oco_order_stop_flag";
                    }
                    else if (WEB3TriggerOrderTypeDef.IFD.equals(
                        l_request.tradeStopInfoList[i].orderStopStateList[j].triggerOrderType))
                    {
                        l_strColumn = "ifd_order_stop_flag";
                    }
                    else if (WEB3TriggerOrderTypeDef.STOP.equals(
                        l_request.tradeStopInfoList[i].orderStopStateList[j].triggerOrderType))
                    {
                        l_strColumn = "stop_order_stop_flag";
                    }
                    else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(
                        l_request.tradeStopInfoList[i].orderStopStateList[j].triggerOrderType))
                    {
                        l_strColumn = "wlimit_order_stop_flag";
                    }
                    
                    if (l_request.tradeStopInfoList[i].orderStopStateList[j].aftChangeStopFlag)
                    {
                        l_map.put(l_strColumn, BooleanEnum.TRUE);
                    }
                    else
                    {
                        l_map.put(l_strColumn, BooleanEnum.FALSE);
                    }
                }
            }
            
            //1.5.6.(*)�ύX����iHashMap.size() != 0�j�̏ꍇ
            if (l_map.size() != 0)
            {
                //1.5.6.1.(*)��������HashMap�ɍX�V�l�F�X�V�҃R�[�h�A�X�V���t���Z�b�g����B
                l_map.put("last_updater", l_admin.getAdministratorCode());
                l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                
                try
                {
                    //1.5.6.2.doUpdateQuery(arg0 : PrimaryKey, arg1 : Map)
                    //(*)�����ݒ�d�l
                    //arg0�F�@@�����Ώۂ̗v�f.ID�������Ƃ��Đ����������ꎷ�s�����戵��~Pk
                    //arg1�F�@@��������HashMap
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    TriggerOrderStopPK l_triggerOrderStopPK = 
                        new TriggerOrderStopPK(Long.parseLong(l_request.tradeStopInfoList[i].id));
                    l_queryProcessor.doUpdateQuery(l_triggerOrderStopPK, l_map); 
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
            }
        }
        
        //1.6.createResponse( )
        //���X�|���X������A���ݎ������Z�b�g����B
        WEB3AdminToTradeStopUpdCompleteResponse l_response = 
            (WEB3AdminToTradeStopUpdCompleteResponse) l_request.createResponse();
        l_response.currentTime = GtlUtils.getSystemTimestamp();

        //1.7
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
