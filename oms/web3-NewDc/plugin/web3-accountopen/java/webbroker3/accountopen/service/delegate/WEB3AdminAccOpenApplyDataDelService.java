head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�X(WEB3AdminAccOpenApplyDataDelService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 ���m�a(���u) �V�K�쐬 ���f��No.159
*/

package webbroker3.accountopen.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�X)<BR>
 * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * @@author ���m�a
 * @@version 1.0
 */
public interface WEB3AdminAccOpenApplyDataDelService extends WEB3BusinessService
{

    /**
     * �����J�ݎ��������f�[�^�폜���������{����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 49337EA3038B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
