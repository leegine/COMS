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
filename	WEB3AdminToTradeStopChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�X(WEB3AdminToTradeStopChangeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/05�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�X)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�ύX�T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public interface WEB3AdminToTradeStopChangeService extends WEB3BusinessService 
{
    
    /**
     * �戵��~�ύX�������s���B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410FB7402FB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
