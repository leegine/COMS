head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.13.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioSLRegistProductReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۓo�^�����Ɖ�T�[�r�X�N���X(WEB3AdminAioSLRegistProductReferenceService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����F (���u) �V�K�쐬 ���f��760
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�S�ۓo�^�����Ɖ�T�[�r�X)<BR>
 * �S�ۓo�^�����Ɖ�T�[�r�X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public interface WEB3AdminAioSLRegistProductReferenceService extends WEB3BusinessService
{
    /**
     * �S�ۓo�^�����Ɖ�������{����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
