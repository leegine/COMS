head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityProductCondScheduleService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҋ������������\��ꗗ�T�[�r�X(WEB3AdminEquityProductCondScheduleService.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * �i�Ǘ��Ҋ������������\��ꗗ�T�[�r�X�j<BR>
 * <BR>
 * �Ǘ��Ҋ������������\��ꗗ�T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * WEB3AdminEquityProductCondScheduleService interface<BR>
 * <BR>
 * @@author Ambha
 * @@version 1.0
 */
public interface WEB3AdminEquityProductCondScheduleService extends WEB3BusinessService
{

   /**
    * �������������\��ꗗ�������s���B<BR>
    * <BR>
    * Execute WEB3AdminEquityProductCondScheduleService process<BR>
    * <BR>
    * @@param l_request - �i���N�G�X�g�j<BR>
    * <BR>
    * l_request<BR>
    * <BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException WEB3BaseException
    * @@roseuid 4191C06A0386
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@