head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioSLProductChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����ύX�T�[�r�X(WEB3AdminAioSLProductChangeService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �g�E�N�|(���u) �V�K�쐬 �d�l�ύX���f��760
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�S�ۖ����ύX�T�[�r�X)<BR>
 * �S�ۖ����ύX�T�[�r�X�C���^�t�F�[�X<BR>
 * <BR>
 * @@author �g�E�N�|
 * @@version 1.0
 */
public interface WEB3AdminAioSLProductChangeService extends WEB3BusinessService
{

    /**
     * �S�ۖ����ύX���������{����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DF80BD0276
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
