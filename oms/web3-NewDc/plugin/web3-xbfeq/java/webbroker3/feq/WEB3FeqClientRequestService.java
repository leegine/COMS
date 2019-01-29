head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������N���C�A���g���N�G�X�g�T�[�r�X(WEB3FeqClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ������(���u) �V�K�쐬
                   2005/07/26 �����F(���u) ���r���[
*/
package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������N���C�A���g���N�G�X�g�T�[�r�X)<BR>
 * �O�������N���C�A���g���N�G�X�g�T�[�r�X�N���X<BR>
 * <BR>
 * @@ author ������ <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqClientRequestService extends WEB3ClientRequestService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FeqClientRequestService.class);
    
    /**
     * @@roseuid 42CE39ED0000
     */
    public WEB3FeqClientRequestService() 
    {
     
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���擾����B<BR>
     * <BR>
     * this.get����().getInstitution().getInstitutionCode()��ԋp����B<BR>
     * @@return String
     * @@roseuid 42971A8601F4
     */
    public String getInstitutionCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInstitutionCode()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.getMainAccount().getInstitution().getInstitutionCode();
        
    }
    
    /**
     * (get�⏕����)<BR>
     * �igetSubAccount�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * ���O�C���Z�L�����e�B�T�[�r�X���⏕�������擾����B<BR>
     * <BR>
     * �P�j�@@���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾���A�Y������ڋq<BR>
     * �I�u�W�F�N�g���擾����B<BR>
     *     ���擾��������ID == 0�̏ꍇ�A<BR>
     * ThreadLocalSystemAttributeRegistry.getAttribute()���<BR>
     *        ����ID���擾����B<BR>
     *        �ݒ�L�[�F ACCOUNT_ID<BR>
     * <BR>
     * �Q�j�@@�O�������N���C�A���g���N�G�X�g�T�[�r�X.get�⏕����()��<BR>
     * �Ϗ��ideligate�j����B<BR>
     * <BR>
     * �@@[get�⏕����()�Ɏw�肷�����]<BR>
     * �@@�ڋq�F�@@�擾�����ڋq�I�u�W�F�N�g<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount
     * @@throws WEB3BaseException 
     * @@roseuid 428AF2AA0018
     */
    public SubAccount getSubAccount() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        //���O�C���Z�L�����e�B�T�[�r�X���⏕�������擾����
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
            OpLoginSecurityService.class);
        //�P�j�@@���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾���A�Y������ڋq�I�u�W�F�N�g���擾����B
        long l_lngAccountId = l_opLoginSec.getAccountId();
        if (l_lngAccountId == 0L)
        {
            Long l_longaccountId =
                (Long)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3FeqLocalSystemAttributesDef.ACCOUNT_ID);
            if (l_longaccountId != null)
            {
                l_lngAccountId = l_longaccountId.longValue();
            }
        }
        //�Q�j�@@�O�������N���C�A���g���N�G�X�g�T�[�r�X.get�⏕����()�ɈϏ��ideligate�j����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        SubAccount l_subAccount = WEB3FeqClientRequestService.getSubAccount(l_mainAccount);
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
    
    /**
     * (get�⏕����)<BR>
     * �istatic���\�b�h�j<BR>
     * <BR>
     * �P�j�@@���Y�q���M�p�q���ǂ������肷��B<BR>
     * <BR>
     *    �ڋq.is�M�p�����J��("0�F�w��Ȃ�")==true�̏ꍇ�́A�M�p�q�ł���B<BR>
     *    �ȊO�A��M�p�q�ł���B<BR>
     * <BR>
     * �Q�j�@@�A�J�E���g�}�l�[�W��.getSubAccount( )�ɂāA�Y���ڋq�̕⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *   [getSubAccount()�Ɏw�肷�����]<BR>
     * �@@accountId�F�@@�ڋq.getAccountId()<BR>
     * �@@subAccountType�F�@@<BR>
     *    �|�M�p�q�̏ꍇ�� SubAccountTypeEnum.�M�p�������<BR>
     * �@@�@@�@@�iEQUITY_MARGIN_SUB_ACCOUNT�j<BR>
     *    �|��M�p�q�̏ꍇ�� SubAccountTypeEnum.�����������<BR>
     * �@@�@@�@@�iEQUITY_SUB_ACCOUNT�j<BR>
     * @@param l_account - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@return WEB3GentradeSubAccount
     * @@throws WEB3BaseException 
     * @@roseuid 42B6744001C5
     */
    public static WEB3GentradeSubAccount getSubAccount(WEB3GentradeMainAccount l_account) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getSubAccount(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_account == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3FeqClientRequestService" + "." + STR_METHOD_NAME);
        }
        
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_account;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeSubAccount  l_subAccount = null;
        try
        {
            //�ڋq.is�M�p�����J��("0�F�w��Ȃ�")==true�̏ꍇ�́A�M�p�q�ł���B
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                //�Q�j�@@�A�J�E���g�}�l�[�W��.getSubAccount( )�ɂāA�Y���ڋq�̕⏕�����I�u�W�F�N�g���擾����B
                l_subAccount = 
                    (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                        l_mainAccount.getAccountId(), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            //�ȊO�A��M�p�q�ł���B
            else
            {
                l_subAccount = 
                    (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                        l_mainAccount.getAccountId(), SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                "WEB3FeqClientRequestService" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
}
@
