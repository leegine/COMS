head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.20.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioSLProductCancelService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ�������T�[�r�X�C���^�t�F�[�X(WEB3AdminAioSLProductCancelService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 ���^�] (���u) �V�K�쐬 �d�l�ύX���f��760
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�S�ۖ�������T�[�r�X�C���^�t�F�[�X)<BR>
 * �S�ۖ�������T�[�r�X�C���^�t�F�[�X<BR>
 *
 * @@author ���^�]
 * @@version 1.0
 */
public interface WEB3AdminAioSLProductCancelService extends WEB3BusinessService
{
    /**
     * �S�ۖ���������������{����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
