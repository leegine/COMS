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
filename	WEB3AdminBondExecuteChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ��ύX�T�[�r�X(WEB3AdminBondExecuteChangeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����(���u) �V�K�쐬
*/

package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��Җ��ύX�T�[�r�X)<BR>
 * �Ǘ��Җ��ύX�T�[�r�X�C���^�[�t�F�[�X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */
public interface WEB3AdminBondExecuteChangeService extends WEB3BusinessService
{
    /**
     * �ǊǗ��Җ��ύX�������s��
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@roseuid 44D7227703CC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)throws WEB3BaseException;
}
@
