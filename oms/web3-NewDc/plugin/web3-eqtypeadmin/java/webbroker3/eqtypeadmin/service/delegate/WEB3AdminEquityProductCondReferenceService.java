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
filename	WEB3AdminEquityProductCondReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�Ǘ��Ҋ������������Ɖ�T�[�r�X�C���^�t�F�C�X)
                       (WEB3AdminEquityProductCondReferenceService.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * �i�Ǘ��Ҋ������������Ɖ�T�[�r�X�C���^�t�F�C�X�j<BR>
 * <BR>
 * �Ǘ��Ҋ������������Ɖ�T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminEquityProductCondReferenceService interface<BR>
 * <BR>
 * @@author Saravanan Krishnamoorthy
 * @@version 1.0
 */
public interface WEB3AdminEquityProductCondReferenceService extends WEB3BusinessService
{

   /**
    * �������������Ɖ�����s���B<BR>
    * <BR>
    * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
    * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
    * <BR>
    * ���Ǘ��ҁE�������������Ɖ�������̓��N�G�X�g�̏ꍇ<BR>
    * �@@this.get�������͉��()���R�[������B<BR>
    * <BR>
    * ���Ǘ��ҁE�������������Ɖ�N�G�X�g�̏ꍇ<BR>
    * �@@this.get�Ɖ���()���R�[������B<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * Execute WEB3AdminEquityProductCondReferenceService process<BR>
    * <BR>
    * Call one of the following methods based on the type of the argument,
    * l_request.<BR>
    * <BR>
    * ��If WEB3AdminPMProductCondRefInputRequest<BR>
    * �@@Call this.getProductInputScreen()<BR>
    * <BR>
    * ��If WEB3AdminPMProductCondRefReferenceRequest<BR>
    * �@@Call this.getReferenceScreen()<BR>
    * <BR>
    * @@param l_request - �i���N�G�X�g�j<BR>
    * <BR>
    * l_request<BR>
    * <BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException WEB3BaseException
    * @@roseuid 4190C77C0258
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
