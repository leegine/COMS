head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.18.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݏ����w��ύX�T�[�r�X(WEB3AdminAccInfoCampaignAccOpenChangeService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 � (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �����J�ݏ����w��ύX�T�[�r�X<BR>
 * @@author  �
 * @@version 1.0
 */
public interface WEB3AdminAccInfoCampaignAccOpenChangeService extends WEB3BusinessService
{

    /**
     * �����J�ݏ����w��ύX���������{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException 
     * @@roseuid 45B044910268
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)throws WEB3BaseException;
}
@
