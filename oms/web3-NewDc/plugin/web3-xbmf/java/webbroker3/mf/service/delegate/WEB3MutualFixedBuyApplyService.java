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
filename	WEB3MutualFixedBuyApplyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t�V�K�\���T�[�r�X(WEB3MutualFixedBuyApplyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/23 �юu�� (���u) �V�K�쐬
*/
package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * ���M�莞��z���t�V�K�\���T�[�r�X
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */
public interface WEB3MutualFixedBuyApplyService extends WEB3BusinessService 
{
    /**
     * �����莞��z���t�V�K�\���T�[�r�X���������{����B
     * @@param l_request - (���N�G�X�g�f�[�^) <BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
