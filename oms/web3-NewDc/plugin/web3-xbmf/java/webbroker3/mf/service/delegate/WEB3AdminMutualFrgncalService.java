head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncalService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���C�O�s��J�����_�[�o�^�T�[�r�X�@@�C���^�[�t�F�C�X(WEB3MutualFrgncalService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �����(���u) �V�K�쐬
*/
package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * �����M���C�O�s��J�����_�[�o�^�T�[�r�X�@@�C���^�[�t�F�C�X�N���X
 */
public interface WEB3AdminMutualFrgncalService extends WEB3BusinessService 
{
    
    /**
     * �����M���C�O�s��J�����_�[�o�^�����{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D80BFB002D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
