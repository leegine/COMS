head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderLockStatusUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������b�N�敪�X�V�T�[�r�XImpl(WEB3AdminBondOrderLockStatusUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 ����(���u) �V�K�쐬         
*/

package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.message.WEB3AdminBondOrderLockUnlockRequest;
import webbroker3.bd.message.WEB3AdminBondOrderLockUnlockResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderLockStatusUpdateService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (���Ǘ��Ғ������b�N�敪�X�V�T�[�r�XImpl)<BR>
 * ���Ǘ��Ғ������b�N�敪�X�V�T�[�r�X�����N���X
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminBondOrderLockStatusUpdateServiceImpl 
    implements WEB3AdminBondOrderLockStatusUpdateService 
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondOrderLockStatusUpdateServiceImpl.class);
    
    /**
     * @@roseuid 44E3362D02AF
     */
    public WEB3AdminBondOrderLockStatusUpdateServiceImpl() 
    {
     
    }
    
    /**
     * ���Ǘ��Ғ������b�N�敪�X�V�������s��<BR>
     * <BR>
     * �V�[�P���X�}�u�i���j�Ǘ��Ғ������b�N�敪�X�V�v�Q��
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C42DA703D7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 ���N�G�X�g���Ǘ��ҍ��������b�N�敪�X�V���N�G�X�g�ɃL���X�g����
        WEB3AdminBondOrderLockUnlockRequest l_bondOrderLockUnlockRequest = 
            (WEB3AdminBondOrderLockUnlockRequest)l_request;
        
        //1.2 validate( )
        l_bondOrderLockUnlockRequest.validate();
        
        //1.3 getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 validate����(String, boolean)
        //[����]  
        // �@@�\�J�e�S���R�[�h�F �@@�\�J�e�S���R�[�h.���i���ύX�A������j
        // is�X�V�F true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL,
            true);
        
        //1.5 get�������P��By����ID(long)
        //[����] 
        // ����ID�F ���N�G�X�g�̒���ID��long�ɕϊ���������
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_orderManager = 
            (WEB3BondOrderManager ) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager(); 
        WEB3BondOrderUnit l_bondOrderUnit = 
            l_orderManager.getBondOrderUnitByOrderId(
                Long.parseLong(l_bondOrderLockUnlockRequest.id));

        //1.6 validate���X����(String)
        AccountManager l_accountManager = l_finApp.getAccountManager();

        try
        {
            Branch l_branch = 
                l_accountManager.getBranch(l_bondOrderUnit.getBranchId());
            String l_strBranchCode = l_branch.getBranchCode();
            l_administrator.validateBranchPermission(l_strBranchCode);
            
            //1.7 validate�������b�N�敪�X�V�\���(�g���������P��)
            l_orderManager.validateOrderLockDivUpdatePossibleStatus(l_bondOrderUnit);
            
            //1.8 update���������b�N�敪(�������b�N�敪 : String, �g���������P�� : �g���������P��)
            //[����] 
            // ���b�N�敪�F ���N�G�X�g�̒������b�N�敪 
            // �g���������P�ʁF �擾�����g���������P��
            l_orderManager.updateBondOrderLockStatus(
                l_bondOrderLockUnlockRequest.orderLockDiv, 
                l_bondOrderUnit);
            
            //1.9 get�������P��(long)
            //[����] 
            // �����P��ID�F �擾�����g���������P��.�����P��ID
            OrderUnit l_orderUnit = 
                l_orderManager.getOrderUnit(l_bondOrderUnit.getOrderUnitId());
            l_bondOrderUnit = (WEB3BondOrderUnit)l_orderUnit;
            
            //1.10 get�������b�N�����{�^���敪(�g���������P��)
            //[����] 
            // �g���������P�ʁF �擾�����g���������P��
            WEB3AdminBondHelperService l_bondHelperService = 
                (WEB3AdminBondHelperService)Services.getService(WEB3AdminBondHelperService.class);
            String l_strOrderLockCancelButtonDiv = 
                l_bondHelperService.getOrderLockButtonDiv(l_bondOrderUnit);
            
            //1.11 get���ύX�{�^���敪(�g���������P��)
            //[����] 
            // �g���������P�ʁF �擾�����g���������P��
            String l_strExecuteChangButtonDiv = 
                l_bondHelperService.getExecuteChangButtonDiv(l_bondOrderUnit);
            
            //1.12 get����{�^���敪(�g���������P��)
            //[����] 
            // �g���������P�ʁF �擾�����g���������P��
            String l_strCancelButtonDiv = 
                l_bondHelperService.getCancelButtonDiv(l_bondOrderUnit);
            
            //1.13 create���X�|���X( )
            WEB3AdminBondOrderLockUnlockResponse l_response = 
                (WEB3AdminBondOrderLockUnlockResponse)l_request.createResponse();
            
            //1.14  �v���p�e�B�Z�b�g
            //get�������P��()����擾���������P�ʂɂ��A���X�|���X�����l��ݒ肷��B
            //�����X�|���X.���b�N�����{�^���敪���@@get�������b�N�����{�^���敪()�̖߂�l
            l_response.lockDiv = l_strOrderLockCancelButtonDiv;
            
            //�����X�|���X.���ύX�{�^���敪�@@���@@get���ύX�{�^���敪()�̖߂�l
            l_response.execChgDiv = l_strExecuteChangButtonDiv;
            
            //�����X�|���X.����{�^���敪�@@�@@�@@���@@get����{�^���敪()�̖߂�l
            l_response.cancelDiv = l_strCancelButtonDiv;
            
            //�����X�|���X.�X�V���ԁ@@�@@�@@�@@�@@�@@���@@���ݓ���
            l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in l_accountManager.getBranch", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
}
@
