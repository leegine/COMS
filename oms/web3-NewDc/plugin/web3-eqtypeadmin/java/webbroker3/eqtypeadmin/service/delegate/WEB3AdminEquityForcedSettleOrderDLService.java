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
filename	WEB3AdminEquityForcedSettleOrderDLService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ϒ���DL�T�[�r�X�iWEB3AdminEquityForcedSettleOrderDLService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/21 ���n(���u) �V�K�쐬���f��171
*/

package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��ҁE�������ϒ���DL�T�[�r�X)<BR>
 * �Ǘ��ҁE�������ϒ���DL�T�[�r�X�C���^�[�t�F�C�X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public interface WEB3AdminEquityForcedSettleOrderDLService extends WEB3BusinessService
{

    /**
     * �Ǘ��ҁE�������ϒ���DL�T�[�r�X�������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47662FBC024F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
