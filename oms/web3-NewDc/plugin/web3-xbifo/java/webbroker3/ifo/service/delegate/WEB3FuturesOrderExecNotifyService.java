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
filename	WEB3FuturesOrderExecNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�o���ʒm�T�[�r�X�C���^�t�F�C�X(WEB3FuturesOrderExecNotifyService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/23 䈋� (���u) �V�K�쐬
*/
package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (�敨�o���ʒm�T�[�r�X)<BR>
 * �����w���敨�o���ʒm�T�[�r�X�C���^�t�F�C�X<BR>
 */
public interface WEB3FuturesOrderExecNotifyService
    extends WEB3BackBusinessService
{

    /**
     * �����w���敨�o���ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�T�[�r�X�j�敨�o���ʒm�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.ifo.message.WEB3FuturesOrderExecNotifyResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A83F5200DD
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;
}
@
