head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.25.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginFrequencyListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IP�ʃ��O�C���񐔈ꗗ�T�[�r�X(WEB3AdminTMLoginFrequencyListService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 ����(���u) �V�K�쐬 ���f�� 005
*/

package webbroker3.trademanagement.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (IP�ʃ��O�C���񐔈ꗗ�T�[�r�X)<BR>
 * �Ǘ���IP�ʃ��O�C���񐔈ꗗ�T�[�r�X�C���^�t�F�C�X�B<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public interface WEB3AdminTMLoginFrequencyListService extends WEB3BusinessService
{
    /**
     * �Ǘ���IP�ʃ��O�C���񐔈ꗗ�T�[�r�X�������s���B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}@
