head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.19.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSLRepayListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍψꗗ�T�[�r�X(WEB3AioSLRepayListService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/12 �����q (���u) �V�K�쐬 �d�l�ύX���f��757
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�،��S�ۃ��[���ԍψꗗ�T�[�r�X)<BR>
 * �،��S�ۃ��[���ԍψꗗ�T�[�r�X�C���^�[�t�F�C�X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public interface WEB3AioSLRepayListService extends WEB3BusinessService
{
    /**
     * �،��S�ۃ��[���ԍψꗗ�T�[�r�X���������{����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 46DE378E025A
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
