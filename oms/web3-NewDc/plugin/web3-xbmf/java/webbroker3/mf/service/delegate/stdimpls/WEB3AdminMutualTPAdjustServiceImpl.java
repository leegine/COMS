head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPAdjustServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��җ]�͒����T�[�r�XImpl(WEB3AdminMutualTPAdjustServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 ��O�� (���u) �V�K�쐬                   
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.mf.WEB3AdminMutualTPAdjustConfirmInterceptor;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundNewOrderSpec;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPAdjustService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * ���M�Ǘ��җ]�͒����T�[�r�XImpl
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminMutualTPAdjustServiceImpl extends 
    WEB3MutualClientRequestService implements WEB3AdminMutualTPAdjustService 
{    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualTPAdjustServiceImpl.class);
        
    /**
     * �����M���]�͒������������{����B 
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A �ȉ��̂����ꂩ�̃��\�b�h���R�[������B <BR>
     * <BR>
     * �@@�����M�Ǘ��җ]�͒����m�F���N�G�X�g�̏ꍇ <BR>
     * �@@�@@this.validate�]�͒���( ) <BR>
     * �@@�����M�Ǘ��җ]�͒����������N�G�X�g�̏ꍇ <BR>
     * �@@�@@this.submit�]�͒���( )<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B2ED11023F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }       
        //���N�G�X�g�f�[�^�̌^�ɂ��A �ȉ��̂����ꂩ�̃��\�b�h���R�[������B 

        //�����M�Ǘ��җ]�͒����m�F���N�G�X�g�̏ꍇ 
        //�@@this.validate�]�͒���( ) 
        //�����M�Ǘ��җ]�͒����������N�G�X�g�̏ꍇ 
        //�@@this.submit�]�͒���( )

        if (l_request instanceof WEB3AdminMutualTPAdjustConfirmRequest)
        {
            WEB3AdminMutualTPAdjustConfirmResponse l_adminMutualTPAdjustConfirmResponse =
                this.validateTPAdjust((WEB3AdminMutualTPAdjustConfirmRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_adminMutualTPAdjustConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminMutualTPAdjustCompleteRequest)
        {
            WEB3AdminMutualTPAdjustCompleteResponse l_adminMutualTPAdjustCompleteResponse =
                this.submitTPAdjust((WEB3AdminMutualTPAdjustCompleteRequest) l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_adminMutualTPAdjustCompleteResponse;
        }
        else
        {
            // �p�����[�^�l���s��
            log.debug(STR_METHOD_NAME + " �p�����[�^�l���s������I");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
    }
    
    /**
     * (validate�]�͒���)
     * �����M���]�͒����m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j�]�͒����m�F�v�Q��<BR>
     * ==========================================================<BR>
     *   1.4 �ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A��O���X���[�B<BR>
     *      �w�Y������ڋq�����݂��܂���B�x<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.5 �g�����M�������擾�ł��Ȃ����ꍇ�A��O���X���[�B<BR>
     *      �w�Y��������������݂��܂���B�v<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00391<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.6 �������`�F�b�N<BR>
     *       get�c�Ɠ��敪()==�h��c�Ɠ��h�̏ꍇ�A��O���X���[�B<BR>
     *      �w����������c�Ɠ��ł��B�x<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02019<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.7 �����`�F�b�N<BR>
     *       get�c�Ɠ��敪()==�h��c�Ɠ��h�̏ꍇ�A��O���X���[�B<BR>
     *      �w��������c�Ɠ��ł��B�x<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02149<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.8 ��n���`�F�b�N<BR>
     *       get�c�Ɠ��敪()==�h��c�Ɠ��h�̏ꍇ�A��O���X���[�B<BR>
     *      �w��n������c�Ɠ��ł��B�x<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02336<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.9 ��n�������͓��`�F�b�N<BR>
     *       ��n�������ݓ��t�i�V�X�e���^�C���X�^���v�j�̏ꍇ�A��O���X���[�B<BR>
     *      �w��n�������͓����O�̓��t�ł��B�x<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02347<BR>
     *==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminMutualTPAdjustConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40BDAA350378
     */
    protected WEB3AdminMutualTPAdjustConfirmResponse validateTPAdjust(
        WEB3AdminMutualTPAdjustConfirmRequest l_request) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateTPAdjust(" +
            "WEB3AdminMutualTPAdjustConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B 
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��҂̌����`�F�b�N���s���B 
        //[����]  
        //�@@�@@�\�J�e�S���R�[�h�F �@@�\�J�e�S���R�[�h.���M�i�]�͒����j 
        //�@@is�X�V�F true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_MUTUAL_TRADING_POWER_ADJUST,
            true);
        
        //1.4 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //�ڋq�I�u�W�F�N�g���擾����B 
        //[����] 
        //�@@�،���ЃR�[�h�F �Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h() 
        //�@@���X�R�[�h�F �Ǘ��҃I�u�W�F�N�g.get���X�R�[�h() 
        //�@@�����R�[�h�F ���N�G�X�g�f�[�^.�ڋq�R�[�h
        
        //�g���A�J�E���g�}�l�[�W���̎擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {        
            l_accMgr.getMainAccount(
                l_admin.getInstitutionCode(), 
                l_admin.getBranchCode(), 
                l_request.accountCode);
        }
        catch(WEB3BaseException l_ex)
        {
            //�ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A��O���X���[�B
            //�w�Y������ڋq�����݂��܂���B�x            
            log.error("�Y������ڋq�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.5 get���M����(Institution, String)
        //�g�����M�������擾����B  
        //�m�����n  
        //�@@�،���ЁF �Ǘ��҃I�u�W�F�N�g.get�،����() 
        //�@@�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager(); 
      
        try
        {
           l_mutualFundProductManager.getMutualFundProduct(
                l_admin.getInstitution(), 
                l_request.mutualProductCode);
        }
        catch (NotFoundException l_ex)
        {
            //�g�����M�������擾�ł��Ȃ����ꍇ�A��O���X���[�B
            //�w�Y��������������݂��܂���B�v
            log.error("�Y��������������݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       

        //1.6 get�c�Ɠ��敪(���t : Timestamp)
        //[����] 
        //�@@���t�F ���N�G�X�g�f�[�^.������
        String l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_request.orderBizDate.getTime()));
        
        //�������`�F�b�N
        //get�c�Ɠ��敪()==�h��c�Ɠ��h�̏ꍇ�A��O���X���[�B
        //�w����������c�Ɠ��ł��B�x
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.debug("����������c�Ɠ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02019,
                this.getClass().getName() + STR_METHOD_NAME,
                "����������c�Ɠ��ł��B");
        }        
        
        //1.7 get�c�Ɠ��敪(���t : Timestamp)
        //[����] 
        //�@@�����F ���N�G�X�g�f�[�^.����
        l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_request.executionTimestamp.getTime()));
        
        //�����`�F�b�N
        //get�c�Ɠ��敪()==�h��c�Ɠ��h�̏ꍇ�A��O���X���[�B
        //�w��������c�Ɠ��ł��B�x
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.debug("��������c�Ɠ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02149,
                this.getClass().getName() + STR_METHOD_NAME,
                "��������c�Ɠ��ł��B");
        }
        //1.8 get�c�Ɠ��敪(���t : Timestamp)
        //[����] 
        //�@@���t�F ���N�G�X�g�f�[�^.��n��
        l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_request.deliveryDate.getTime()));
        
        //��n���`�F�b�N
        //get�c�Ɠ��敪()==�h��c�Ɠ��h�̏ꍇ�A��O���X���[�B
        //�w��n������c�Ɠ��ł��B�x
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.debug("��n������c�Ɠ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02336,
                this.getClass().getName() + STR_METHOD_NAME,
                "��n������c�Ɠ��ł��B");
        }
        //1.9 ��n�������͓��`�F�b�N
        if (WEB3DateUtility
            .compare(
                l_request.deliveryDate,
                GtlUtils.clearTimeFields(GtlUtils.getSystemTimestamp()))
            < 0)
        {
            log.debug("��n�������͓����O�̓��t�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02347,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��n�������͓����O�̓��t�ł��B");
        }
        
        //1.10 createResponse( )
        //���X�|���X�f�[�^�̐���
        WEB3AdminMutualTPAdjustConfirmResponse l_response = 
            (WEB3AdminMutualTPAdjustConfirmResponse) l_request.createResponse();       
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�]�͒���)
     * �����M���]�͒��������������s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j�]�͒��������v�Q��<BR>
     * ==========================================================<BR>
     *   1.5 �ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A��O���X���[�B<BR>
     *      �w�Y������ڋq�����݂��܂���B�x<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01035<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.9 �g�����M�������擾�ł��Ȃ����ꍇ�A��O���X���[�B<BR>
     *      �w�Y��������������݂��܂���B�v<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00391<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.10 �������`�F�b�N<BR>
     *       get�c�Ɠ��敪()==�h��c�Ɠ��h�̏ꍇ�A��O���X���[�B<BR>
     *      �w����������c�Ɠ��ł��B�x<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02019<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.11 �����`�F�b�N<BR>
     *       get�c�Ɠ��敪()==�h��c�Ɠ��h�̏ꍇ�A��O���X���[�B<BR>
     *      �w��������c�Ɠ��ł��B�x<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02149<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.12 ��n���`�F�b�N<BR>
     *       get�c�Ɠ��敪()==�h��c�Ɠ��h�̏ꍇ�A��O���X���[�B<BR>
     *      �w��n������c�Ɠ��ł��B�x<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02336<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     *   1.13 ��n�������͓��`�F�b�N<BR>
     *       ��n�������ݓ��t�i�V�X�e���^�C���X�^���v�j�̏ꍇ�A��O���X���[�B<BR>
     *      �w��n�������͓����O�̓��t�ł��B�x<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02347<BR>
     *==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminMutualTPAdjustCompleteResponse
     * @@roseuid 40BDAA350378
     */
    protected WEB3AdminMutualTPAdjustCompleteResponse submitTPAdjust(
        WEB3AdminMutualTPAdjustCompleteRequest l_request)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitTPAdjust(" +
            "WEB3AdminMutualTPAdjustCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B 
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��҂̌����`�F�b�N���s���B 
        //[����]  
        //�@@�@@�\�J�e�S���R�[�h�F �@@�\�J�e�S���R�[�h.���M�i�]�͒����j 
        //�@@is�X�V�F true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_MUTUAL_TRADING_POWER_ADJUST,
            true);
        
        //1.4 validate����p�X���[�h(�p�X���[�h : String)
        //�Ïؔԍ��̃`�F�b�N���s���B
        //[����]  
        //�@@�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);
         
        //1.5 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //�ڋq�I�u�W�F�N�g���擾����B 

        //[����] 
        //�@@�،���ЃR�[�h�F �Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h() 
        //�@@���X�R�[�h�F �Ǘ��҃I�u�W�F�N�g.get���X�R�[�h() 
        //�@@�����R�[�h�F ���N�G�X�g�f�[�^.�ڋq�R�[�h
        //�g���A�J�E���g�}�l�[�W���̎擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {        
            l_mainAccount = (WEB3GentradeMainAccount)
                l_accMgr.getMainAccount(
                    l_admin.getInstitutionCode(), 
                    l_admin.getBranchCode(), 
                    l_request.accountCode);
        }
        catch(WEB3BaseException l_ex)
        {
            //�ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A��O���X���[�B
            //�w�Y������ڋq�����݂��܂���B�x
            log.error("�Y������ڋq�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.6 lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //���������b�N����B  
        //[����]  
        //�@@�،���ЃR�[�h�F �Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h() 
        //�@@���X�R�[�h�F �Ǘ��҃I�u�W�F�N�g.get���X�R�[�h() 
        //�@@�����R�[�h�F �ڋq�I�u�W�F�N�g.get�����R�[�h()
        l_accMgr.lockAccount(
            l_admin.getInstitutionCode(),
            l_admin.getBranchCode(),
            l_mainAccount.getAccountCode());
        
        //1.7 is�M�p�����J��(�ٍϋ敪 : String)
        //[����]  
        //�@@�ٍϋ敪�F 0�i�w��Ȃ��j
        boolean l_blnisMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(
                WEB3GentradeRepaymentDivDef.DEFAULT);

        //1.8 getSubAccount(arg0 : SubAccountTypeEnum)
        //�⏕�����I�u�W�F�N�g���擾����B
        //[����]  
        //�@@�⏕�����^�C�v�F   
        //�@@is�M�p�����J��=true �̏ꍇ�ASubAccountTypeEnum.�����M�p��������i�ۏ؋��j   
        //�@@is�M�p�����J��=false �̏ꍇ�ASubAccountTypeEnum.������������i�a����j  
        SubAccount l_subAccount = null; 
        try
        {            
            if (l_blnisMarginAccountEstablished)
            {
                l_subAccount = 
                    l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            else
            {
                l_subAccount = 
                    l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
            
        //1.9 get���M����(Institution, String)
        //�g�����M�������擾����B  
        //�m�����n  
        //�@@�،���ЁF �Ǘ��҃I�u�W�F�N�g.get�،����() 
        //�@@�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager(); 
        
        WEB3MutualFundProduct l_mfProduct = null;
        
        try
        {
            l_mfProduct = (WEB3MutualFundProduct) 
                l_mutualFundProductManager.getMutualFundProduct(
                    l_admin.getInstitution(), 
                    l_request.mutualProductCode);
        }
        //�g�����M�������擾�ł��Ȃ����ꍇ�A��O���X���[�B
        //�w�Y��������������݂��܂���B�v
        catch (NotFoundException l_ex)
        {
            log.error("�Y��������������݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       

        //1.10 get�c�Ɠ��敪(���t : Timestamp)
        //[����] 
        //�@@���t�F ���N�G�X�g�f�[�^.������
        String l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_request.orderBizDate.getTime()));
        
        //�������`�F�b�N
        //get�c�Ɠ��敪()==�h��c�Ɠ��h�̏ꍇ�A��O���X���[�B
        //�w����������c�Ɠ��ł��B�x
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.debug("����������c�Ɠ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02019,
                this.getClass().getName() + STR_METHOD_NAME,
                "����������c�Ɠ��ł��B");
        }        
        
        //1.11 get�c�Ɠ��敪(���t : Timestamp)
        //[����] 
        //�@@�����F ���N�G�X�g�f�[�^.����
        l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_request.executionTimestamp.getTime()));
        
        //�����`�F�b�N
        //get�c�Ɠ��敪()==�h��c�Ɠ��h�̏ꍇ�A��O���X���[�B
        //�w��������c�Ɠ��ł��B�x
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.debug("��������c�Ɠ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02149,
                this.getClass().getName() + STR_METHOD_NAME,
                "��������c�Ɠ��ł��B");
        }
        //1.12 get�c�Ɠ��敪(���t : Timestamp)
        //[����] 
        //�@@���t�F ���N�G�X�g�f�[�^.��n��
        l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_request.deliveryDate.getTime()));
        
        //��n���`�F�b�N
        //get�c�Ɠ��敪()==�h��c�Ɠ��h�̏ꍇ�A��O���X���[�B
        //�w��n������c�Ɠ��ł��B�x
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.debug("��n������c�Ɠ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02336,
                this.getClass().getName() + STR_METHOD_NAME,
                "��n������c�Ɠ��ł��B");
        }
        //1.13 ��n�������͓��`�F�b�N
        if (WEB3DateUtility
            .compare(
                l_request.deliveryDate,
                GtlUtils.clearTimeFields(GtlUtils.getSystemTimestamp()))
            < 0)
        {
            log.debug("��n�������͓����O�̓��t�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02347,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��n�������͓����O�̓��t�ł��B");
        }
        
        //1.14 ���M�Ǘ��җ]�͒����m��C���^�Z�v�^�I�u�W�F�N�g�𐶐�����B
        WEB3AdminMutualTPAdjustConfirmInterceptor l_tpAdjustConfirmInterceptor = 
            new WEB3AdminMutualTPAdjustConfirmInterceptor();
        
        //1.15 ���v���p�e�B�E�Z�b�g��
        //�����������M�Ǘ��җ]�͒����m��C���^�Z�v�^�Ɉȉ��̃v���p�e�B��ݒ肷��B  

        //�P�j�@@��������ݒ肷��B 
        //�@@[set�������ɓn���p�����^]   
        //�@@�@@�������F ���N�G�X�g�f�[�^.������ 
        l_tpAdjustConfirmInterceptor.setOrderBizDate(
            new Timestamp(l_request.orderBizDate.getTime()));

        //�Q�j�@@������ݒ肷��B 
        //�@@[set�����ɓn���p�����^]   
        //�@@�@@�����F ���N�G�X�g�f�[�^.���� 
        l_tpAdjustConfirmInterceptor.setExecutionDate(
            new Timestamp(l_request.executionTimestamp.getTime()));

        //�R�j�@@��n����ݒ肷��B   
        //�@@[set��n���ɓn���p�����^]   
        //�@@�@@��n���F ���N�G�X�g�f�[�^.��n�� 
        l_tpAdjustConfirmInterceptor.setDeliveryDate(
            new Timestamp(l_request.deliveryDate.getTime()));

        //�S�j�@@�T�Z��n�����ݒ肷��B 
        //�@@[set�T�Z��n����ɓn���p�����^] 
        //�@@�@@�T�Z��n����F ���N�G�X�g�f�[�^.���Z���z 
        l_tpAdjustConfirmInterceptor.setEstimatedPrice(
            Double.parseDouble(l_request.settlePrice));
        
        //�T�j�@@�v�Z����z��ݒ肷��B 
        //�@@[set�v�Z����z�ɓn���p�����^] 
        //�@@�@@�v�Z����z�F �擾�����g�����M�����I�u�W�F�N�g.get���t����z() 
        double l_dblConstantValue = l_mfProduct.getConstantValue();
        l_tpAdjustConfirmInterceptor.setConstantValue(l_dblConstantValue);
        
        //�U�j�@@����z�K�p����ݒ肷��B 
        //�@@[set����z�K�p���ɓn���p�����^] 
        //�@@�@@����z�K�p���F �擾�����g�����M�����I�u�W�F�N�g.get����z�K�p��()
        Timestamp l_tsConstantValueAppDate = new Timestamp(
            l_mfProduct.getConstantValueAppDate().getTime());
        l_tpAdjustConfirmInterceptor.setConstantValueAppDate(l_tsConstantValueAppDate);
          
        //1.16 setThreadLocalPersistenceEventInterceptor(arg0 : MutualFundOrderManagerPersistenceEventInterceptor)
        //���M�Ǘ��җ]�͒����m��C���^�Z�v�^���A�g�����M�����}�l�[�W���ɐݒ肷��B 
        //�m�����n 
        //���M�����m��C���^�Z�v�^�F ���M�Ǘ��җ]�͒����m��C���^�Z�v�^�I�u�W�F�N�g
        
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();
        
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_tpAdjustConfirmInterceptor);

        //1.17 �g�����M�V�K�������e(Trader, boolean, String, double, QuantityTypeEnum, TaxTypeEnum)
        // �g�����M�V�K�������e�𐶐�����B  

        // [�g�����M�V�K�������e�̃R���X�g���N�^�ɓn���p�����^]  
        // �@@�㗝���͎ҁF null  
        // �@@is���t�F true 
        // �@@�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h 
        // �@@�������ʁF ���N�G�X�g�f�[�^.���Z���z 
        // �@@�������ʃ^�C�v�F QuantityTypeEnum.���z 
        // �@@�ŋ敪�F  
        // �@@�@@�|�ڋq�I�u�W�F�N�g.is��������J��()==true�̏ꍇ�ATaxTypeEnum.SPECIAL   
        // �@@�@@�|�ڋq�I�u�W�F�N�g.is��������J��()==false�̏ꍇ�ATaxTypeEnum.NORMAL  
        // �@@�@@�mis��������J�݂ɓn���p�����^�n  
        // �@@�@@�@@��n���F ���N�G�X�g�f�[�^.��n��  
        // �@@�@@�@@�⏕�����F �擾�����⏕�����I�u�W�F�N�g  
        
        boolean l_blnIsSpecialAccount =
            l_mainAccount.isSpecialAccountEstablished(
                l_request.deliveryDate, 
                l_subAccount);
        
        log.debug("�ڋq�I�u�W�F�N�g.is��������J��()== " + l_blnIsSpecialAccount);
        
        TaxTypeEnum l_taxTypeEnum = null;
        
        if (l_blnIsSpecialAccount)
        {
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        else
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        
        WEB3MutualFundNewOrderSpec l_mfNewOrderSpec = 
            new WEB3MutualFundNewOrderSpec(
                null,
                true,
                l_request.mutualProductCode,
                Double.parseDouble(l_request.settlePrice),
                QuantityTypeEnum.AMOUNT,
                l_taxTypeEnum);
           
        //1.18 submitNewOrder()
        //�������e��DB�֓o�^����B 
        //�m�����n 
        //�@@�⏕�����F �擾�����⏕�����I�u�W�F�N�g  
        //�@@�����^�C�v�F ProductTypeEnum.�����M��  
        //�@@�V�K�������e�F �g�����M�V�K�������e  
        //�@@����ID�F �g�����M�����}�l�[�W��.createNewOrderId()�̖߂�l 
        //�@@����p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ� 
        //�@@is�����R���ȗ��F true
        long l_lngNewOrderId = l_orderManager.createNewOrderId();
        OrderSubmissionResult l_orderSubmissionResult = 
            l_orderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.MUTUAL_FUND,
                l_mfNewOrderSpec,
                l_lngNewOrderId,
                l_request.password,
                true);
        
        if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
        {
            log.debug("�Y���g�����M�����}�l�[�W��.submitNewOrder()�̖߂�l" +
                ".getProcessingResult().isSuccessfulResult()" +
                "==false�̏ꍇ�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME,
               "�V�K�������s");
        }
         
        //1.19 �]�͍Čv�Z(�⏕���� : �⏕����)
        //�]�͎c�������X�V����B 
        //[����] 
        //�@@�⏕�����F �擾�����⏕�����I�u�W�F�N�g
        
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        l_tpTradingPowerService.reCalcTradingPower(
            (WEB3GentradeSubAccount)l_subAccount); 
         
        //1.20 createResponse( )
        //���X�|���X�I�u�W�F�N�g�𐶐�����B
        WEB3AdminMutualTPAdjustCompleteResponse l_response = 
            (WEB3AdminMutualTPAdjustCompleteResponse) l_request.createResponse();       
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
