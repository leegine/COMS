head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.20.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AccTransChangeToIfoDepositService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����ւ̐U�փT�[�r�X(WEB3AccTransChangeToIfoDepositService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/26 ���E(���u) ���r���[
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�؋����ւ̐U�փT�[�r�X)<BR>
 * �؋����ւ̐U�փT�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public interface WEB3AccTransChangeToIfoDepositService
    extends WEB3BusinessService
{

    /**
     * �؋����ւ̐U�փT�[�r�X�������s���B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4135AEDC014E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
