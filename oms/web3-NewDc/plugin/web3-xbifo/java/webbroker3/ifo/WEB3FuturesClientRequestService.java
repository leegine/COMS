head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           :�敨�N���C�A���g���N�G�X�g�T�[�r�X(WEB3FuturesClientRequestService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20   ���Ō� (Sinocom) �V�K�쐬
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;


/**
 * (�敨�N���C�A���g���N�G�X�g�T�[�r�X)<BR>
 */
public class WEB3FuturesClientRequestService extends WEB3ClientRequestService
{
    
    /**
     * @@roseuid 40F7A03800AB
     */
    public WEB3FuturesClientRequestService() 
    {
     
    }
    
    /**
     * (get�⏕����)<BR>
     * �igetSubAccount�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ���O�C���Z�L�����e�B�T�[�r�X���⏕�������擾����B<BR>
     * <BR>
     * �P�j�@@���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾����B<BR>
     * <BR>
     * �Q�j�@@�A�J�E���g�}�l�[�W��.getSubAccount( )�ɂāA<BR>
     * �Y���ڋq�̎w���敨����p�⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[getSubAccount����]<BR>
     * �@@SubAccountTypeEnum.�����I�v�V�����������(�敨�؋���)(=7)<BR>
     * <BR>
     * ���敨�̎���ł́A��L��Enum���ݒ肳���⏕�������g�p����B<BR>
     * <BR>
     * �R�j�@@�Q�j�ŗ�O���X���[���ꂽ�ꍇ�A�u�敨�������J�݃G���[�v���X���[����B<BR>
     * @@return SubAccount
     * @@roseuid 404EE5420094
     */
    public SubAccount getSubAccount() throws WEB3BaseException
    {
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            long l_accountId = l_opLoginSec.getAccountId();

        if (l_accountId == 0L)
        {
            Long l_longAccountId = (Long)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.ACCOUNT_ID);
            if (l_longAccountId != null)
            {
                l_accountId = l_longAccountId.longValue();
            }
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            try
            {
                return (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_accountId,
                    SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "getSubAccount()",
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        else
        {
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount = super.getSubAccount(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            }
            catch(WEB3SystemLayerException l_ex)
            {
                //�敨�������J�݃G���[���X���[����
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00284,
                    this.getClass().getName());
            }

            return l_subAccount;
        }

    }
}
@
