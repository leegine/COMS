head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����������ʒm�T�[�r�X�C���^�[�t�F�C�X(WEB3MutualTradeOrderNotifyService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ����  (���u) �V�K�쐬
                   2004/08/24 ����� (���u) ���r���[
*/
package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (���M���������ʒm�T�[�r�X)
 * �����M�����������ʒm�T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public interface WEB3MutualTradeOrderNotifyService 
    extends WEB3BackBusinessService 
{
    /**
     * �����M�����������ʒm�T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40567C3B02A9
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) 
        throws WEB3BaseException;
}
@
