head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.17.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransFromFXService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : FX����U�փT�[�r�X(WEB3FXTransFromFXService)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/21 ����(���u) �V�K�쐬
 */

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (FX����U�փT�[�r�X) <BR>
 * FX����U�փT�[�r�X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public interface WEB3FXTransFromFXService extends WEB3BusinessService
{
    /**
     * (execute) FX����U�փT�[�r�X�������s���B
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BE51420342
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}@
