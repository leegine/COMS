head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualExpireService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X(WEB3AdminToManualExpireService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20�@@�A����(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X)<BR>
 * �iWEB3AdminToManualExpireService�j<BR>
 * �g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public interface WEB3AdminToManualExpireService extends WEB3BusinessService 
{
    
    /**
     * �蓮�����������s���B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44054D14028F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
