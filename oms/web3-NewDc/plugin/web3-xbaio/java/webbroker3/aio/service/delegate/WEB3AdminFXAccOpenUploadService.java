head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.13.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXAccOpenUploadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X(WEB3AdminFXAccOpenUploadService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 �A����(���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X)<BR>
 * �Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public interface WEB3AdminFXAccOpenUploadService extends WEB3BusinessService 
{
    
    /**
     * �Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X�������s���B
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E8704900CB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
