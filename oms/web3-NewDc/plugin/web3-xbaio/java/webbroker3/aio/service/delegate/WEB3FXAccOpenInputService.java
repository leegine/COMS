head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.12.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXAccOpenInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�����J�ݓ��̓T�[�r�X(WEB3FXAccOpenInputService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/26 ���E (���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (FX�����J�ݓ��̓T�[�r�X) <BR>
 * FX�����J�ݓ��̓T�[�r�X�C���^�[�t�F�C�X
 * @@author ���E(���u)
 * @@version 1.0 
 */
public interface WEB3FXAccOpenInputService extends WEB3BusinessService
{
    /**
     * FX�����J�ݓ��̓T�[�r�X�������s���B
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41C653E20307
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}@
