head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.51.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�޻��޽(WEB3AdminSrvRegiOtherOrgIdDownloadService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/03/10 �đo�g(���u) �V�K�쐬 ���f��No.336
*/

package webbroker3.srvregi.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�޻��޽)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�޻��޽�C���^�t�F�C�X�N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public interface WEB3AdminSrvRegiOtherOrgIdDownloadService extends WEB3BusinessService
{

    /**
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�ޏ������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B9483D013E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@