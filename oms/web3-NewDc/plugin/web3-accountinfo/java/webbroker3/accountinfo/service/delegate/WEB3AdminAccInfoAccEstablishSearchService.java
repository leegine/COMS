head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.19.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �V�K�����J�݌������޽(WEB3AdminAccInfoAccEstablishSearchService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09 �����q (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������޽)<BR>
 * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������޽�C���^�t�F�C�X<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */

public interface WEB3AdminAccInfoAccEstablishSearchService extends WEB3BusinessService
{
    /**
     * �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�������������{����B<BR>
     * @@param l_request
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
  
}
@
