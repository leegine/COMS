head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.24.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginStopStartChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��O�C����~�ĊJ�ύX�T�[�r�X(WEB3AdminTMLoginStopStartChangeService.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��҃��O�C����~�ĊJ�ύX�T�[�r�X)<BR>
 * <BR>
 * �Ǘ��҃��O�C����~�ĊJ�ύX�T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * -----<English>---------------<BR>
 * <BR>
 * WEB3AdminTMLoginStopStartChangeService<BR>
 * <BR>
 * WEB3AdminTMLoginStopStartChangeService interface<BR>
 * <BR>
 * @@author Babu Prasad
 * @@version 1.0
 */
public interface WEB3AdminTMLoginStopStartChangeService extends WEB3BusinessService
{

   /**
    * ���O�C����~�ĊJ�ύX�������s���B<BR>
    * <BR>
    * ----<English>----------------<BR>
    * <BR>
    * Execute WEB3AdminTMLoginStopStartChangeService.<BR>
    * <BR>
    * @@param l_request - ���N�G�X�g<BR>
    * <BR>
    * l_request<BR>
    * <BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException WEB3BaseException
    * @@roseuid 41774524011E
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
