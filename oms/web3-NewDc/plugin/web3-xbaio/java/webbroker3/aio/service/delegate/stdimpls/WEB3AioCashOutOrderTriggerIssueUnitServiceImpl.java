head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashOutOrderTriggerIssueUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�������g���K�[���sUnitServiceImpl(WEB3AioCashOutOrderTriggerIssueUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/09 �����q (���u) �V�K�쐬 �d�l�ύX���f��No.720
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl;
import webbroker3.aio.service.delegate.WEB3AioCashOutOrderTriggerIssueUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o�������g���K�[���sUnitServiceImpl)<BR>
 * �o���g���K�[���sUnitService�����N���X<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 * <BR>
 * @@author �����q(���u)
 * @@version 1.0
 */
public class WEB3AioCashOutOrderTriggerIssueUnitServiceImpl implements WEB3AioCashOutOrderTriggerIssueUnitService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashOutOrderTriggerIssueUnitServiceImpl.class);

    /**
     * �o�������̃g���K�[���s�������s���B<BR>
     * <BR>
     * �P�j�@@���o���E���o�Ƀ��N�G�X�g���M�T�[�r�X�I�u�W�F�N�g�𐶐�<BR>
     * <BR>
     * �Q�j�@@���o���E���o�Ƀ��N�G�X�g���M�T�[�r�X�I�u�W�F�N�g . �g���K���s()�Ń��b�Z�[�W���M<BR>
     * [�w�肷�����]<BR>
     * �E�،���ЃR�[�h�F����:�،���ЃR�[�h<BR>
     * �E�f�[�^�R�[�h�F"GI801T"<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@throws WEB3BaseException
     */
    public void execute(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);

        //���o���E���o�Ƀ��N�G�X�g���M�T�[�r�X
        WEB3AioMarketRequestSenderServiceImpl l_marketRequestSenderServiceImpl =
            (WEB3AioMarketRequestSenderServiceImpl) GtlUtils.getTradingModule(
                ProductTypeEnum.AIO).getMarketAdapter().getMarketRequestSenderServce();

        String l_strRequestCode = WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER + "T";
        // ���o���E���o�Ƀ��N�G�X�g���M�T�[�r�X�I�u�W�F�N�g . �g���K���s()�Ń��b�Z�[�W���M
        l_marketRequestSenderServiceImpl.submitTrigger(l_strInstitutionCode, l_strRequestCode);

        log.exiting(STR_METHOD_NAME);
    }
}
@
