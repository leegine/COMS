head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoDeclineService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO���ރT�[�r�X�C���^�t�F�C�X(WEB3IpoDeclineService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �d�� (���u) �V�K�쐬
*/

package webbroker3.ipo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * IPO���ރT�[�r�X�C���^�t�F�C�X
 * 
 * @@author �d��
 * @@version 1.0
 */
public interface WEB3IpoDeclineService extends WEB3BusinessService 
{
    
    /**
     * IPO���ޏ��������{����B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40DA5B580302
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
