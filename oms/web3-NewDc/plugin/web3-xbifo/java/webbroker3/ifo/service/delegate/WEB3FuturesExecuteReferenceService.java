head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesExecuteReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�������Ɖ�T�[�r�X�X(WEB3FuturesExecuteReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 ���Q (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�敨�������Ɖ�T�[�r�X)<BR>
 * �����w���敨�������Ɖ�T�[�r�X�C���^�[�t�F�C�X<BR>
 */
public interface WEB3FuturesExecuteReferenceService extends WEB3BusinessService 
{
    
    /**
     * �����w���敨�������Ɖ�T�[�r�X���������{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057FB43034E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
