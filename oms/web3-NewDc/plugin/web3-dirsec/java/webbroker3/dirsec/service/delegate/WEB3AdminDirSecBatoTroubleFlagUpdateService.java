head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecBatoTroubleFlagUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғd�q����Q�t���O�X�V(WEB3AdminDirSecBatoTroubleFlagUpdateService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/25 ���O(���u) �V�K�쐬 ���f��No.117
*/
package webbroker3.dirsec.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��ғd�q����Q�t���O�X�V)<BR>
 * �Ǘ��ғd�q����Q�t���O�X�V�T�[�r�X�C���^�[�t�F�C�X�B<BR>
 * <BR>
 * @@author ���O
 * @@version 1.0
 */
public interface WEB3AdminDirSecBatoTroubleFlagUpdateService extends WEB3BusinessService
{

    /**
     * �Ǘ��ғd�q����Q�t���O�X�V���J�n����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47D7582A03C3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
