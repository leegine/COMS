head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.20.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransToFXService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�փT�[�r�X(WEB3FXTransToFXService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/1/20 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (FX�ւ̐U�փT�[�r�X) <BR>
 * FX�ւ̐U�փT�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public interface WEB3FXTransToFXService extends WEB3BusinessService
{
    /**
     * FX�ւ̐U�փT�[�r�X�������s���B
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41BCF2CC0279
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}@
