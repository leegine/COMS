head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageDownloadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X(WEB3AdminIfoDepShortageDownloadService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27�@@����(���u) �V�K�쐬 ���f��No.006
*/
package webbroker3.ifoadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X)<BR>
 * �Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X�C���^�[�t�F�C�X <BR>
 * <BR>
 * @@author ����
 * @@version 1.0
 */
public interface WEB3AdminIfoDepShortageDownloadService extends WEB3BusinessService
{
    /**
     * �Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X�������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 499B565C0136
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}@
