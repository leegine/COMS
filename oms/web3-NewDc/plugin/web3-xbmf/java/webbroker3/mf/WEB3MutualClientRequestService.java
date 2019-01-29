head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�N���C�A���g���N�G�X�g�T�[�r�X(WEB3MutualClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �����(���u) �V�K�쐬
                   2004/12/10 ����(���u) �c�Ή�
*/

package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M�N���C�A���g���N�G�X�g�T�[�r�X)<BR>
 * �ėp�N���C�A���g���N�G�X�g�T�[�r�X�i�����M���p�j<BR>
 * �N���C�A���g����̃��N�G�X�g����������T�[�r�X�̋��ʃX�[�p�[�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3MutualClientRequestService extends WEB3ClientRequestService 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualClientRequestService.class);
    
    /**
     * (get�⏕����)<BR>
     * (getSubAccount�̃I�[�o�[���C�h)<BR>
     * <BR>
     * ���O�C���Z�L�����e�B�T�[�r�X���⏕�ڋq���擾����B <BR>
     * <BR>
     * �P�j�@@super.get�⏕����( )�ɂāA<BR>
     * �Y���ڋq�̓����M������p�⏕�����I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@[get�⏕�����ɓn���p�����^] <BR>
     *   (*)�ȉ��̎菇�Őݒ�B  <BR>
     *   �|���O�C���Z�L�����e�B�T�[�r�X������ID���擾�B  <BR>
     *   �|�擾��������ID�������ɁA�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[���B <BR> 
     *   �|�擾�����ڋq�I�u�W�F�N�g.is�M�p�����J��()���R�[���B  <BR>
     *   �Eis�M�p�����J��=true �̏ꍇ�A�ȉ����w��B  <BR>
     *   �@@�@@SubAccountTypeEnum.�����M�p��������i�ۏ؋��j  <BR>
     *   �Eis�M�p�����J��=false �̏ꍇ�A�ȉ����w��B  <BR>
     *   �@@�@@SubAccountTypeEnum.������������i�a����j  <BR>
     *    <BR>
     *   ���jis�M�p�����J�݂ɓn������  <BR>
     *   �@@�ٍϋ敪���h0�F�w��Ȃ��h <BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount
     * @@roseuid 40554CAF000E
     */
    public SubAccount getSubAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccount()";
        log.entering(STR_METHOD_NAME);
        
        //�P�jsuper.get�⏕����( )�ɂāA�Y���ڋq�̓����M������p�⏕�����I�u�W�F�N�g���擾����B 
        //  [get�⏕�����ɓn���p�����^] 
        //�@@�⏕�����^�C�v��(*) 
        //  (*)�ȉ��̎菇�Őݒ�B 
        //  �|���O�C���Z�L�����e�B�T�[�r�X������ID���擾�B
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        long l_accountId = l_opLoginSec.getAccountId();
         
        //  �|�擾��������ID�������ɁA�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[���B 
        //�g���A�J�E���g�}�l�[�W���擾
        FinApp l_finApp = 
            (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManaer = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = 
                (WEB3GentradeMainAccount) l_gentradeAccountManaer.getMainAccount(l_accountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(" When �擾�����ڋq�I�u�W�F�N�g..");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //  �|�擾�����ڋq�I�u�W�F�N�g.is�M�p�����J��()���R�[���B
        //  ���jis�M�p�����J�݂ɓn������ 
        //�@@�ٍϋ敪���h0�F�w��Ȃ� 
        boolean l_blnisMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        // �Eis�M�p�����J��=true �̏ꍇ�A�ȉ����w��B 
        // SubAccountTypeEnum.�����M�p��������i�ۏ؋��j 
        if (l_blnisMarginAccountEstablished == true)
        {
            log.exiting(STR_METHOD_NAME);
            return super.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
        }
        else
        {
            // is�M�p�����J��=false �̏ꍇ�A�ȉ����w��B 
            //�@@SubAccountTypeEnum.������������i�a����j
            log.exiting(STR_METHOD_NAME);
            return super.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        }
    }
    
}
@
