head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticRecruitLimitManageService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ��������X�ʉ���g�Ǘ��T�[�r�X(WEB3AdminBondDomesticRecruitLimitManageService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.214
*/
package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��ҍ��������X�ʉ���g�Ǘ��T�[�r�X)<BR>
 * �Ǘ��ҍ��������X�ʉ���g�Ǘ��T�[�r�X�C���^�[�t�F�C�X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public interface WEB3AdminBondDomesticRecruitLimitManageService extends WEB3BusinessService
{

    /**
     * �Ǘ��ҍ��������X�ʉ���g�Ǘ��T�[�r�X���������{����B<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
     * ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4695AE800130
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
