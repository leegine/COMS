head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ����������o�^�T�[�r�X(WEB3AdminBondDomesticProductRegistService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 �����q(���u) �V�K�쐬 �d�l�ύX�E���f��No.193
*/

package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��ҍ����������o�^�T�[�r�X)<BR>
 * �Ǘ��ҍ����������o�^�T�[�r�X<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public interface WEB3AdminBondDomesticProductRegistService extends WEB3BusinessService
{

    /**
     * �Ǘ��Ҍl�����������o�^�T�[�r�X���������{����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 466505F60186
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
