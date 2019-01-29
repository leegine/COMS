head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �N���C�A���g�E���N�G�X�g�E�T�[�r�X(WEB3ClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 �^�� (���u) �C��
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�N���C�A���g�E���N�G�X�g�E�T�[�r�X)<BR>
 * <BR> 
 * �ėp�N���C�A���g���N�G�X�g�T�[�r�X<BR> 
 * �N���C�A���g����̃��N�G�X�g����������T�[�r�X�̋��ʃX�[�p�[�N���X�B<BR>
 * <BR> 
 */
public abstract class WEB3ClientRequestService
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ClientRequestService.class);

    /**
     * ���O�C���`���l���̃^�O��
     * @@roseuid 403496D10132
     */
    public WEB3ClientRequestService()
    {

    }

    /**
     * (get����)<BR>
     * <BR> 
     * ���O�C���Z�L�����e�B�T�[�r�X���ڋq���擾����B<BR>
     * <BR> 
     * @@return WEB3GentradeMainAccount ����
     * @@throws WEB3SystemLayerException
     * @@roseuid 403496D10140
     */
    public MainAccount getMainAccount() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMainAccount()";
        log.entering(STR_METHOD_NAME);

        long l_accountId;
        MainAccount l_mainAccount;

        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        l_accountId = l_opLoginSec.getAccountId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        try
        {
            l_mainAccount = l_accMgr.getMainAccount(l_accountId);
        }
        catch (NotFoundException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_mainAccount;
    }

    /**
     * (get�⏕����)<BR>
     * <BR> 
     * ���O�C���Z�L�����e�B�T�[�r�X���⏕�ڋq���擾����B<BR>
     * @@param l_subAccountType
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount
     * @@throws webbroker3.common.WEB3SystemLayerException
     * @@roseuid 403496D101AE
     */
    public SubAccount getSubAccount(SubAccountTypeEnum l_subAccountType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSubAccount(SubAccountTypeEnum)";
        log.entering(STR_METHOD_NAME);

        long l_accountId;
        MainAccount l_mainAccount;
        SubAccount l_subAccount;

        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        l_accountId = l_opLoginSec.getAccountId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        
        try
        {
            l_mainAccount = l_accMgr.getMainAccount(l_accountId);
            l_subAccount = l_mainAccount.getSubAccount(l_subAccountType);
        }
        catch (NotFoundException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }

    /**
     * (get�㗝���͎�)<BR>
     * <BR> 
     * ���O�C���Z�L�����e�B�T�[�r�X���㗝���͎҂��擾����B<BR>
     * �ڋq���͂̏ꍇ��null��ԋp����B<BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Trader
     * @@throws webbroker3.common.WEB3SystemLayerException
     * @@roseuid 403496D1022C
     */
    public Trader getTrader() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTrader()";
        log.entering(STR_METHOD_NAME);

        long l_loginId;
        Trader l_trader = null;

        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        l_loginId = l_opLoginSec.getLoginInfo().getLoginId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();

        try
        {
            l_trader = l_finObjMgr.getTraderByLoginId(l_loginId);
        }
        catch (NotFoundException e)
        {
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_trader;
    }

    /**
     * (get���O�C���`���l��)<BR>
     * <BR> 
     * ���O�C���Z�L�����e�B�T�[�r�X��胍�O�C���`���l�����擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 403496D10298
     */
    public String getLoginChannel()
    {
        final String STR_METHOD_NAME = "getLoginChannel()";
        log.entering(STR_METHOD_NAME);
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strChannel =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);
        if (l_strChannel == null || l_strChannel.equals(""))
        {
            l_strChannel = WEB3ChannelDef.BRANCH;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strChannel;
    }

}
@
