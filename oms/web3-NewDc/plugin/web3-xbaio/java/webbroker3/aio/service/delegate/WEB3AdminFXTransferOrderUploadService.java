head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.13.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXTransferOrderUploadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�U�֒����A�b�v���[�h�T�[�r�X(WEB3AdminFXTransferOrderUploadService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/24 �A����(���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (FX�U�֒����A�b�v���[�h�T�[�r�X)<BR>
 * FX�U�֒����A�b�v���[�h�T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public interface WEB3AdminFXTransferOrderUploadService extends WEB3BusinessService 
{
    
    /**
     * FX�U�֒����A�b�v���[�h�T�[�r�X�������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43C5FA660246
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
