head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccOpenStateChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ���PTS�����J�ݏ󋵕ύX�T�[�r�X(WEB3AdminInformPTSAccOpenStateChangeService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/27 �đo�g(���u) �V�K�쐬 ���f��No.129
*/

package webbroker3.inform.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �Ǘ���PTS�����J�ݏ󋵕ύX�T�[�r�X<BR>
 * �Ǘ���PTS�����J�ݏ󋵕ύX�T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public interface WEB3AdminInformPTSAccOpenStateChangeService extends WEB3BusinessService
{
    /**
     * �Ǘ���PTS�����J�ݏ󋵕ύX�T�[�r�X�������s���B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A00E3601CB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
