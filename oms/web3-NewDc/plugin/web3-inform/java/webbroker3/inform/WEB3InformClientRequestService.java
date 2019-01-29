head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���Ǘ��N���C�A���g���N�G�X�g�T�[�r�X(WEB3InformClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 䈋� (���u) �V�K�쐬
*/
package webbroker3.inform;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A���Ǘ��N���C�A���g���N�G�X�g�T�[�r�X)<BR>
 * �A���Ǘ��N���C�A���g���N�G�X�g�T�[�r�X�N���X
 */
public class WEB3InformClientRequestService extends WEB3ClientRequestService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformClientRequestService.class);

    /**
     * @@roseuid 41EE642D0138
     */
    public WEB3InformClientRequestService()
    {

    }

    /**
     * (get�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * super.get�⏕����()�ɂĕ⏕�����I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * [super.get�⏕����()�Ɏw�肷�����] 
     * SubAccountTypeEnum.EQUITY_SUB_ACCOUNT<BR>
     * @@return WEB3GentradeSubAccount
     * @@roseuid 418F43730108
     */
    public WEB3GentradeSubAccount getSubAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)
                super.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
}
@
