head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.16.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FEqConTransferService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ւ̐U�փT�[�r�X�N���X(WEB3FEqConTransferService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ��O�� (���u) �V�K�쐬       
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�O�������ւ̐U�փT�[�r�X)<BR>
 * �O�������ւ̐U�փT�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public interface WEB3FEqConTransferService extends WEB3BusinessService 
{
    
    /**
     * �O�������ւ̐U�֏������s���B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E38627025C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
