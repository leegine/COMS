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
filename	WEB3FeqOrderAcceptExecutionNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������t�o���ʒm�T�[�r�X(WEB3FeqOrderAcceptExecutionNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ꎉ� (���u) �V�K�쐬
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (�O������������t�o���ʒm�T�[�r�X)<BR>
 * �O������������t�o���ʒm�T�[�r�X�C���^�t�F�[�X<BR>
 * <BR>
 * �i�g�����U�N�V���������F�ݒ�s�v�j<BR>
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public interface WEB3FeqOrderAcceptExecutionNotifyService extends WEB3BackBusinessService 
{
    /**
     * �O������������t�o���ʒm�T�[�r�X���������{����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
