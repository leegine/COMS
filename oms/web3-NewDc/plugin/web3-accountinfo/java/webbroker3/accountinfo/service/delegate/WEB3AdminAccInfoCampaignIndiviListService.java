head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.19.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ʌڋq�w��ꗗ�T�[�r�X (WEB3AdminAccInfoCampaignIndiviListService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 ���؎q (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�ʌڋq�w��ꗗ�T�[�r�X)<BR>
 * �ʌڋq�w��ꗗ�T�[�r�X<BR>
 * <BR>
 * @@author ���؎q<BR>
 * @@version 1.0<BR>
 */
public interface WEB3AdminAccInfoCampaignIndiviListService extends WEB3BusinessService 
{
    
    /**
     * �ʌڋq�w��w��ꗗ�\�����������{����B<BR>
     * <BR>
     * �@@�|get�ꗗ���()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B0705A0158
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
