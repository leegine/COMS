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
filename	WEB3AdminEquityManualExpireService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����蓮�����T�[�r�X(WEB3AdminEquityManualExpireService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30�@@�юu��(���u) �V�K�쐬
*/

package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��ҁE�����蓮�����T�[�r�X)<BR>
 * �Ǘ��ҁE�����蓮�����T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * @@author �юu��(���u)
 * @@version 1.0
 */

public interface WEB3AdminEquityManualExpireService extends WEB3BusinessService 
{
    /**
     * �����蓮�����������s���B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4469314A0018
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
