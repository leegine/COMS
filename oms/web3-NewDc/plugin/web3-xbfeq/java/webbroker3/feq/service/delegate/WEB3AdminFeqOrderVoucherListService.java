head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderVoucherListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO���������������`�[�ꗗ�T�[�r�X(WEB3AdminFeqOrderVoucherListService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 �s�p (���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[     
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��ҊO���������������`�[�ꗗ�T�[�r�X)<BR>
 * �Ǘ��ҊO���������������`�[�ꗗ�T�[�r�X�C���^�[�t�F�C�X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public interface WEB3AdminFeqOrderVoucherListService extends WEB3BusinessService 
{
    
    /**
     * �O���������������`�[�ꗗ�T�[�r�X�������s���B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FC1D900CE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
