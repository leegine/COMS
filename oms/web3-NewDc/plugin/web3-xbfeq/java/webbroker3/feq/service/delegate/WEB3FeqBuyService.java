head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������t�T�[�r�X(WEB3FeqBuyService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�O���������t�T�[�r�X)<BR>
 * �O���������t�T�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public interface WEB3FeqBuyService extends WEB3BusinessService 
{    
    /**
     * �O���������t�T�[�r�X���������{����B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 428AF0460121
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException;
}
@