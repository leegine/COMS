head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeCancelNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨��������ʒm�T�[�r�X�C���^�t�F�C�X(WEB3FuturesChangeCancelNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/21 Ḗ@@�� (���u) �V�K�쐬
*/


package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (�敨��������ʒm�T�[�r�X)<BR>
 * �����w���敨��������ʒm�T�[�r�X�C���^�t�F�C�X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public interface WEB3FuturesChangeCancelNotifyService extends WEB3BackBusinessService 
{
    
    /**
     * �����w���敨��������ʒm�T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A89DDE02B8
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
