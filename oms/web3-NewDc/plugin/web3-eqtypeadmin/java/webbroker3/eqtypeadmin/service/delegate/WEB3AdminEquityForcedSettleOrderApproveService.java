head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderApproveService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X(WEB3AdminEquityForcedSettleOrderApproveService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����F (���u) �V�K�쐬 �d�l�ύX���f��No.129
*/
package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X�C���^�[�t�F�C�X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public interface WEB3AdminEquityForcedSettleOrderApproveService extends WEB3BusinessService
{

    /**
     * �������ω��������F�^�񏳔F�������s���B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4603204903BB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
