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
filename	WEB3MarginClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����N���C�A���g���N�G�X�g�T�[�r�X(WEB3MarginClientRequestService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/27 ���Ō� (Sinocom) �V�K�쐬
                   2005/01/06 �����a�� (SRA) JavaDoc�C��
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;

import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;

/**
 * �i�M�p����N���C�A���g���N�G�X�g�T�[�r�X�j�B<BR>
 * <BR>
 * �ėp�N���C�A���g���N�G�X�g�T�[�r�X(�M�p����p)<BR>
 * <BR>
 * �N���C�A���g����̃��N�G�X�g����������T�[�r�X�̋��ʃX�[�p�[�N���X�B
 * @@version 1.0
 */
public class WEB3MarginClientRequestService extends WEB3ClientRequestService
{
    
    /**
     * @@roseuid 4142BDBE0081
     */
    public WEB3MarginClientRequestService() 
    {
     
    }
    
    /**
     * (get�⏕����)<BR>
     * �igetSubAccount�̃I�[�o�[���[�h �j <BR>
     * <BR>
     * ���O�C���Z�L�����e�B�T�[�r�X���⏕�������擾����B <BR>
     * <BR>
     * �P�j�@@���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾���A<BR>
     * �Y������ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�A�J�E���g�}�l�[�W��.getSubAccount( )�ɂāA<BR>
     * �Y���ڋq�̊����p�⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[getSubAccount����]<BR>
     * �@@SubAccountTypeEnum.�����������<BR>
     * @@return WEB3GentradeSubAccount<BR>
     * @@throws WEB3BaseException
     * @@roseuid 410F2C880053
     */
    public WEB3GentradeSubAccount getSubAccount() 
        throws WEB3BusinessLayerException, WEB3SystemLayerException
    {
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
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
            try
            {
                return (WEB3GentradeSubAccount)l_accountManager.getSubAccount(l_accountId, SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            catch (NotFoundException l_ne)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                    getClass().getName() + ".getSubAccount()",
                    l_ne.getMessage(),
                    l_ne);
            }
        }
        else
        {
            try
            {
                return (WEB3GentradeSubAccount)super.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            catch (WEB3SystemLayerException l_e)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                    getClass().getName() + ".getSubAccount()",
                    l_e.getMessage(),
                    l_e);
            }
        }
    }
}
@
