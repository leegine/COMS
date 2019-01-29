head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�N���C�A���g���N�G�X�g�T�[�r�X(WEB3OptionClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ����� (���u) �V�K�쐬
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
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP�N���C�A���g���N�G�X�g�T�[�r�X)<BR>
 * OP�N���C�A���g���N�G�X�g�T�[�r�X�N���X<BR>
 * �ėp�N���C�A���g���N�G�X�g�T�[�r�X�i�I�v�V�����p�j<BR>
 * �N���C�A���g����̃��N�G�X�g����������T�[�r�X�̋��ʃX�[�p�[�N���X�B<BR>
 * @@author  �����
 * @@version 1.0
 */
public class WEB3OptionClientRequestService extends WEB3ClientRequestService
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionClientRequestService.class);
            
    /**
     * �iget�⏕�����̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ���O�C���Z�L�����e�B�T�[�r�X���⏕�ڋq���擾����B<BR>
     * <BR>
     * �P�j���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾����B<BR>
     * <BR>
     * �Q�j����ID����A�ڋq�I�u�W�F�N�g�i�����I�u�W�F�N�g�j���擾����B<BR>
     * 
     * �R�j�ڋq.getOP��������^�C�v()�ɂ��Y���ڋq�̕⏕�����^�C�v���擾����B<BR>
     * 
     * �S�j�A�J�E���g�}�l�[�W��.getSubAccount(�⏕�����^�C�v)�ɂāA�Y���ڋq�̎w���I�v�V��������p�⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[getSubAccount����]<BR>
     * �@@SubAccountTypeEnum.�w���I�v�V�����������<BR>
     * 
     * @@return �w���I�v�V��������p�⏕�����I�u�W�F�N�g���擾����B
     * @@roseuid 404EE42800C3
     */
    public SubAccount getSubAccount() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        //�P�j���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾����B
        OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        //�P�j���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾����B        
        long l_lngAccountId = l_opLoginSec.getAccountId();
        if (l_lngAccountId == 0L)
        {
            Long l_longaccountId =
                (Long)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.ACCOUNT_ID);
            if (l_longaccountId != null)
            {
                l_lngAccountId = l_longaccountId.longValue();
            }
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        SubAccount  l_subAccount = null;
        try
        {
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
            //�R�j�ڋq.getOP��������^�C�v()�ɂ��Y���ڋq�̕⏕�����^�C�v���擾����B
            SubAccountTypeEnum l_subAccountType = l_mainAccount.getOpSubAccountType();
            //�S�j�A�J�E���g�}�l�[�W��.getSubAccount(�⏕�����^�C�v)�ɂāA�Y���ڋq�̎w���I�v�V��������p�⏕�����I�u�W�F�N�g���擾����B
            l_subAccount = l_accountManager.getSubAccount(l_lngAccountId,l_subAccountType);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);        
        return l_subAccount;
    }

    /**
     * �iexecute�j<BR>
     * <BR> 
     * @@param l_request 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        return l_request.createResponse();
    }
}
@
