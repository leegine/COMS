head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailDistributionService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�܂����ē����[���z�M�w���T�[�r�X(WEB3AdminAccInfoMailDistributionService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��҂��q�܂����ē����[���z�M�w���T�[�r�X)<BR>
 * �Ǘ��҂��q�܂����ē����[���z�M�w���T�[�r�X�C���^�t�F�C�X<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */

public interface WEB3AdminAccInfoMailDistributionService extends WEB3BusinessService
{
    /**
     * �ē����[���z�M�w�����������{����B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1F42007A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
