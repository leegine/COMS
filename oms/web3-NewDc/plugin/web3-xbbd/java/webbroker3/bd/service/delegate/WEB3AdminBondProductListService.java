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
filename	WEB3AdminBondProductListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ������ꗗ�T�[�r�X(WEB3AdminBondProductListService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q (���u) �V�K�쐬
*/

package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��ҍ������ꗗ�T�[�r�X)<BR>
 * ���Ǘ��Җ����ꗗ�T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0 
 */
public interface WEB3AdminBondProductListService extends WEB3BusinessService
{
    
    /**
     * ���Ǘ��Җ����ꗗ�����{����B<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B5FDFC0090
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
