head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.19.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoBaseInfoReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l����{���Ɖ�T�[�r�X�C���^�t�F�C�X(WEB3AccInfoBaseInfoReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (���q�l����{���Ɖ�T�[�r�X)<BR>
 * ���q�l����{���Ɖ�T�[�r�X�C���^�t�F�C�X<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public interface WEB3AccInfoBaseInfoReferenceService extends WEB3BusinessService 
{
    
    /**
     * �ڋq��{���Ɖ�����s���B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163B65802A2
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
