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
filename	WEB3MutualOrderAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M������t�T�[�r�X(WEB3MutualOrderAcceptService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ����  (���u) �V�K�쐬
                   2004/08/23 ����� (���u) ���r���[
*/
package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (���M������t�T�[�r�X)
 * �����M��������t�T�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public interface WEB3MutualOrderAcceptService 
    extends WEB3BackBusinessService 
{
    
    /**
     * �����M��������t�T�[�r�X���������{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056643202C1
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) 
        throws WEB3BaseException;
}
@
