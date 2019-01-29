head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferManagementUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�U�֊Ǘ�UnitService�����N���X(WEB3AdminFXTransferManagementUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 ������ (���u) �V�K�쐬
                 : 2006/08/17 ��� (SCS) ���f��No.610�Ή�
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferManagementUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FxTransStatusOperationDivDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX�U�֊Ǘ�UnitServiceImpl) <BR>
 * FX�U�֊Ǘ�UnitService�����N���X <BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor( <BR>
 * TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AdminFXTransferManagementUnitServiceImpl implements
    WEB3AdminFXTransferManagementUnitService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferManagementUnitServiceImpl.class);

    /**
     * (submit���) <BR>
     * DB�ɑ΂��ĐU�֒����̎���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iFX�U�֊Ǘ��jsubmit����iUnit�j�v�Q��
     * 
     * @@param l_gftTransferStatusParams - GFT�U�֏�Params�I�u�W�F�N�g
     * @@param l_administrator - �Ǘ��҃I�u�W�F�N�g
     * @@param l_strPassword - �p�X���[�h
     * @@throws WEB3BaseException
     * @@roseuid 41C68F630238
     */
    public void submitCancel(GftTransferStatusParams l_gftTransferStatusParams,
        WEB3Administrator l_administrator, String l_strPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancel()";
        log.entering(STR_METHOD_NAME);
        
        if (l_gftTransferStatusParams == null || l_administrator == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // FX�f�[�^����T�[�r�X���擾
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
    
        //1.1 GFT�U�֏󋵃e�[�u�����X�V����B 
        // [����] 
        // GFT�U�֏�Params�F GFT�U�֏󋵍s�I�u�W�F�N�g 
        // �X�V��U�֏󋵋敪�F 3�i����j 
        // �X�V�҃R�[�h�F �Ǘ���.�Ǘ��҃R�[�h 
        l_fxDataControlService.updateGFTTransferStatus(
            l_gftTransferStatusParams,
            WEB3TransferStatusDivDef.CANCEL,
            l_administrator.getAdministratorCode());
        
        //1.2 GFT�U�֏�Params.�����敪��01�F�،���������ב֕ۏ؋���(FX�o��)�̏ꍇ�A
        //�@@�@@�܂��́@@03�F�،��������犔��؋�����(��OP�o��)�̏ꍇ�A���{
        if(WEB3FxTransStatusOperationDivDef.TO_FX.equals(l_gftTransferStatusParams.getOperationDiv())
            || WEB3FxTransStatusOperationDivDef.TO_FUOP.equals(l_gftTransferStatusParams.getOperationDiv()))
        {
            // AIO�����}�l�[�W�����擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_web3AioOrderMgr =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            
            //�g���A�J�E���g�}�l�[�W���擾����    
            WEB3GentradeAccountManager l_genAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            //1.2.1 �����̎�����s���B 
            // [����] 
            // �،���ЃR�[�h�F GFT�U�֏�Params.�،���ЃR�[�h 
            // ���X�R�[�h�F GFT�U�֏�Params.���X�R�[�h 
            // �ڋq�R�[�h�F GFT�U�֏�Params.�ڋq�R�[�h 
            // ���ʃR�[�h�F GFT�U�֏�Params.���ʃR�[�h 
            // �M�p�U�֗p���ʃR�[�h�F GFT�U�֏�Params.�M�p�U�֗p���ʃR�[�h 
            // �p�X���[�h�F 
            l_web3AioOrderMgr.transferOrderCancel(
                l_gftTransferStatusParams.getInstitutionCode(),
                l_gftTransferStatusParams.getBranchCode(),
                l_gftTransferStatusParams.getAccountCode(),
                l_gftTransferStatusParams.getOrderRequestNumber(),
                l_gftTransferStatusParams.getMrgTrnOrderRequestNumber(),
				l_strPassword
                );
            
            //1.2.2 �ڋq�̃C���X�^���X���擾����B 
            // [����] 
            // �،���ЃR�[�h�F GFT�U�֏�Params.�،���ЃR�[�h 
            // ���X�R�[�h�F GFT�U�֏�Params.���X�R�[�h 
            // �����R�[�h�F GFT�U�֏�Params.�ڋq�R�[�h 
            WEB3GentradeMainAccount l_genMainAccount = 
                l_genAccountManager.getMainAccount(
                    l_gftTransferStatusParams.getInstitutionCode(),
                    l_gftTransferStatusParams.getBranchCode(),
                    l_gftTransferStatusParams.getAccountCode()
                    );
            
            //1.2.3 �⏕�������擾����B 
            // [����] 
            // �⏕�����^�C�v�F 1�i�a��������j
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = 
                    (WEB3GentradeSubAccount) l_genMainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
            catch(NotFoundException l_ex)
            {
                log.error("__NotFoundException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //1.2.4 �]�͂̍Čv�Z���s���B 
            // [����] 
            // �⏕�����F �⏕�����I�u�W�F�N�g
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
        }       
        log.exiting(STR_METHOD_NAME);
    }
}@
