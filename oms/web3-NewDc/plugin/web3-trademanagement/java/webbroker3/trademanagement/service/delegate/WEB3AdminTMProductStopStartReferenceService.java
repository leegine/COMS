head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.24.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMProductStopStartReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�T�[�r�X)(WEB3AdminTMProductStopStartReferenceServicel.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trademanagement.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�T�[�r�X)<BR>
 * <BR>
 * �Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * WEB3AdminTMProductStopStartReferenceService<BR>
 * <BR>
 * WEB3AdminTMProductStopStartReferenceService interface<BR>
 * <BR>
 * @@author Rajesh Sharma
 * @@version 1.0
 */
public interface WEB3AdminTMProductStopStartReferenceService extends WEB3BusinessService
{

   /**
    * ���i�ʎ戵��~�ĊJ�Ɖ�����s���B<BR>
    * <BR>
    * Execute WEB3AdminTMProductStopStartReferenceService process. <BR>
    * <BR>
    * @@param l_request - ���N�G�X�g<BR>
    * <BR>
    * l_request<BR>
    * <BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException WEB3BaseException
    * @@roseuid 417482E10080
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
