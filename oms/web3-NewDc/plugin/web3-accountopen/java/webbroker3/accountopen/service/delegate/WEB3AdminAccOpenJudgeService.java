head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenJudgeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐR���T�[�r�X (WEB3AdminAccOpenJudgeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 ����(���u) �V�K�쐬
*/

package webbroker3.accountopen.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��Ҍ����J�ݐR���T�[�r�X)<BR>
 * �Ǘ��Ҍ����J�ݐR���T�[�r�X�C���^�t�F�C�X<BR>
 *   
 * @@author ����
 * @@version 1.0
 */
public interface WEB3AdminAccOpenJudgeService extends WEB3BusinessService 
{
    
    /**
     * �����J�ݐR�����������{����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473AC3A005B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
