head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioSLCashOutStopReleaseService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۃ��[���o���S���������T�[�r�X�C���^�[�t�F�[�X(WEB3AdminAioSLCashOutStopReleaseService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����i���u�j�V�K�쐬 ���f��No.764
*/
package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�S�ۃ��[���o���S���������T�[�r�X)<BR>
 * �S�ۃ��[���o���S���������T�[�r�X�C���^�[�t�F�[�X<BR>
 *
 * @@author ����(���u)
 * @@version 1.0
 */
public interface WEB3AdminAioSLCashOutStopReleaseService extends WEB3BusinessService
{
    /**
     * �S�ۃ��[���o���S���������T�[�r�X���s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     *
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;

}
@
