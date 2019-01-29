head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ėp�N���C�A���g���N�G�X�g�T�[�r�X�N���X(WEB3RuitoClientRequestService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 ���u�� (���u) �V�K�쐬
*/

package webbroker3.xbruito;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;

/**
 * �ėp�N���C�A���g���N�G�X�g�T�[�r�X�i�ݐϓ����p�j<BR>
 * <BR>
 * �N���C�A���g����̃��N�G�X�g����������T�[�r�X�̋��ʃX�[�p�[�N���X�B<BR>
 */
public class WEB3RuitoClientRequestService extends WEB3ClientRequestService
{
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3RuitoClientRequestService.class);
		
    /**
     * �igetSubAccount�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * ���O�C���Z�L�����e�B�T�[�r�X���⏕�ڋq���擾����B<BR>
     * <BR>
     * �P�j�@@super.get�⏕����( )�ɂāA�Y���ڋq�̗ݐϓ�������p<BR>
     *       �⏕����<BR>
     *       �I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[get�⏕�����ɓn���p�����^]<BR>
     * �@@�@@SubAccountTypeEnum.�����������<BR>
     * @@return webbroker3.gentrade.WEB3GentradeSubAccount
     * @@roseuid 406133B20377
     */
    public WEB3GentradeSubAccount getSubAccount() throws WEB3BaseException
    {
		final String STR_METHOD_NAME = "getSubAccount()";
		log.entering(STR_METHOD_NAME);
		log.exiting(STR_METHOD_NAME);
        return (WEB3GentradeSubAccount)super.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
    }

    /**
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40C4688A00BB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        return l_request.createResponse();
    }
}
@
