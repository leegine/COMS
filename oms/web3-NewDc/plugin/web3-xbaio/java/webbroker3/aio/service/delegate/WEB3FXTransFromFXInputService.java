head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.16.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransFromFXInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : FX����U�֓��̓T�[�r�X(WEB3FXTransFromFXInputService)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/24 ����(���u) �V�K�쐬
 */

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (FX����U�֓��̓T�[�r�X) <BR>
 * FX����U�֓��̓T�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public interface WEB3FXTransFromFXInputService extends WEB3BusinessService
{
    /**
     * FX����U�֓��̓T�[�r�X�������s���B
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41BCFA4D02E7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}@
