head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���N���C�A���g���N�G�X�g�T�[�r�X(WEB3BondClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/22 ����� (���u) �V�K�쐬
*/

package webbroker3.bd;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;



import webbroker3.gentrade.WEB3ClientRequestService;


/**
 * (���N���C�A���g���N�G�X�g�T�[�r�X )<BR>
 * ���N���C�A���g���N�G�X�g�T�[�r�X
 * 
 * @@author  �����
 * @@version 1.0
 */
public class WEB3BondClientRequestService extends WEB3ClientRequestService
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3BondClientRequestService.class);
            
    /**
     * @@roseuid 424CC4BE01B5
     */
    public WEB3BondClientRequestService() 
    {
     
    }
    
    /**
     * (get�⏕����)<BR>
     * �igetSubAccount�̃I�[�o�[���[�h�j  <BR>
     * <BR>
     * ���O�C���Z�L�����e�B�T�[�r�X���⏕�������擾����B  <BR>
     * <BR>
     * �P�j�@@���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾���A�Y������ڋq�I�u�W�F�N�g
     * ���擾����B <BR>
     * <BR>
     * �Q�j�@@�g���A�J�E���g�}�l�[�W��.getSubAccount()�ɂāA�Y���ڋq�̕⏕�����I�u�W�F�N
     * �g���擾����B <BR>
     * <BR>
     * �@@[getSubAccount()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�⏕�����^�C�v�F�@@<BR>
     * �@@�@@�@@[�ڋq�I�u�W�F�N�g.is�M�p�����J��(�h�w��Ȃ��h) == true�̏ꍇ]<BR>
     * �@@�@@�@@�@@SubAccountTypeEnum.�����M�p�������<BR>
     * �@@�@@�@@[��L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@SubAccountTypeEnum.�����������<BR>
     * @@return �⏕����
     * @@roseuid 421947A80030
     */
    public SubAccount getSubAccount() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        //�P�j���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾����B
        OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        //�j�@@���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾���A�Y������ڋq�I�u�W�F�N�g���擾����B      
        long l_lngAccountId = l_opLoginSec.getAccountId();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount  l_subAccount = null;
        try
        {
            SubAccountTypeEnum l_subAccountType = null;
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
            //[�ڋq�I�u�W�F�N�g.is�M�p�����J��(�h�w��Ȃ��h) == true�̏ꍇ]SubAccountTypeEnum.�����M�p�������
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                l_subAccountType = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
            //��L�ȊO�̏ꍇSubAccountTypeEnum.�����������
            else
            {
                l_subAccountType =SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
            //�Q�j�@@�g���A�J�E���g�}�l�[�W��.getSubAccount()�ɂāA�Y���ڋq�̕⏕�����I�u�W�F�N�g���擾����B
            l_subAccount = l_accountManager.getSubAccount(l_lngAccountId,l_subAccountType);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);        
        return l_subAccount;
    }
}
@
