head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.33.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I�����T�[�r�X(WEB3AdminIpoLotService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 �A���� (���u) �V�K�쐬
*/

package webbroker3.ipo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ���IPO���I�����T�[�r�X)<BR>
 * �Ǘ���IPO���I�����T�[�r�X�C���^�t�F�C�X<BR>
 *
 * @@author �A����
 * @@version 1.0
 */
public interface WEB3AdminIpoLotService extends WEB3BusinessService 
{
    
    /**
     * �Ǘ���IPO���I�������������{����B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40C6666E00D7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
