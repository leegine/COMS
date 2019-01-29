head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����N���C�A���g���N�G�X�g�T�[�r�X(WEB3EquityClientRequestService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/27   ���Ō� (Sinocom) �V�K�쐬 
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;

/**
 * �i�����N���C�A���g���N�G�X�g�T�[�r�X�j�B<BR>
 * <BR>
 * �N���C�A���g����̃��N�G�X�g����������T�[�r�X�̋��ʃX�[�p�[�N���X�B
 * @@version 1.0
 */
public class WEB3EquityClientRequestService extends WEB3ClientRequestService
{
    
    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3EquityClientRequestService.class);
        
    /**
     * @@roseuid 4142BDBE0013
     */
    public WEB3EquityClientRequestService() 
    {
     
    }
    
    /**
     * (get�⏕����)<BR>
     * <BR>
     * �igetSubAccount�̃I�[�o�[���[�h�j <BR>
     * <BR>
     * ���O�C���Z�L�����e�B�T�[�r�X���⏕�������擾����B <BR>
     * <BR>
     * �P�j�@@���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾���A�Y������ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@���Y�q���M�p�q���ǂ������肷��B<BR>
     * <BR>
     * �@@�@@�@@�ڋq.is�M�p�����J��("0�F�w��Ȃ�")==true�̏ꍇ�͐M�p�q�ł���B<BR>
     * �@@�@@�@@�ȊO�A��M�p�q�ł���B<BR>
     * <BR>
     * �R�j�@@�g���A�J�E���g�}�l�[�W��.getSubAccount( )�ɂāA�Y���ڋq�̕⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[getSubAccount����]<BR>
     * �@@�M�p�q�̏ꍇ�F�@@SubAccountTypeEnum.�M�p��������iEQUITY_MARGIN_SUB_ACCOUNT�j<BR>
     * �@@��M�p�q�̏ꍇ�F�@@SubAccountTypeEnum.������������iEQUITY_SUB_ACCOUNT�j<BR>
     * <BR>
     * @@return WEB3GentradeSubAccount<BR>
     * @@throws WEB3BaseException
     * @@roseuid 410F2C880053
     */
    public WEB3GentradeSubAccount getSubAccount() 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeSubAccount l_subAccount = null;

        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        long l_accountId = l_opLoginSec.getAccountId();
        if (l_accountId == 0L)
        {
            Long l_longAccountId = (Long)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3MarginAttributeNameDef.ACCOUNT_ID);
            if (l_longAccountId != null)
            {
	            l_accountId = l_longAccountId.longValue();
            }
        }
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        try
        {
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_accountId);
            SubAccountTypeEnum l_subAccountType;
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                l_subAccountType = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
            else
            {
                l_subAccountType = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(l_accountId, l_subAccountType);
        }
        catch (NotFoundException l_ne)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }

        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
}
@
