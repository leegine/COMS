head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginCancelService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p�������T�[�r�X(WEB3ToSuccMarginCancelService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 ���@@�_�O (���u) �V�K�쐬 
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�i�A���j�M�p�������T�[�r�X)<BR>
 * �i�A���j�M�p�������T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author �@@���@@�_�O(���u)
 * @@version 1.0 
 */
public interface WEB3ToSuccMarginCancelService extends WEB3BusinessService 
{
    
    /**
     * �i�A���j�M�p�������������s���B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 433A23C70290
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException;
}
@
