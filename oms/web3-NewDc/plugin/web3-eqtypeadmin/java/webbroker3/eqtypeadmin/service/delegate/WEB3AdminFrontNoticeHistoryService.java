head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontNoticeHistoryService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ғʒm�����Q�ƃT�[�r�X(WEB3AdminFrontNoticeHistoryService.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * �Ǘ��Ғʒm�����Q�ƃT�[�r�X�C���^�t�F�C�X<BR>
 */
public interface WEB3AdminFrontNoticeHistoryService extends WEB3BusinessService 
{
   
   /**
    * �Ǘ��Ғʒm�����Q�ƃT�[�r�X���s���B<BR>
    * @@param l_request - ���N�G�X�g<BR>
    * @@return WEB3GenResponse<BR>
    * @@throws WEB3BaseException<BR>
    * @@roseuid 42D2155F00FE
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
