head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���o�^�T�[�r�X(WEB3AdminFPTRegistService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 �đo�g (���u) �V�K�쐬
*/

package webbroker3.docadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��ҋ����@@��t�{���o�^�T�[�r�X)<BR>
 * �Ǘ��ҋ����@@��t�{���o�^�T�[�r�X�C���^�t�F�[�X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public interface WEB3AdminFPTRegistService extends WEB3BusinessService
{

   /**
    * �����@@��t�{���o�^���������{����B<BR>
    * <BR>
    * @@param l_request - (���N�G�X�g)<BR>
    * ���N�G�X�g<BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}@
