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
filename	WEB3BondSellService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����p�T�[�r�X(WEB3BondSellService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/06 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�����p�T�[�r�X)<BR>
 * �����p�T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public interface WEB3BondSellService extends WEB3BusinessService 
{
    
    /**
     * �����p���������{����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44E93D9F00D3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
