head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.33.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoOfferStopResumeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO��W��~�ĊJ�T�[�r�X�C���^�t�F�C�X(WEB3AdminIpoOfferStopResumeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/26 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ipo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * �Ǘ���IPO��W��~�ĊJ�T�[�r�X�C���^�t�F�C�X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public interface WEB3AdminIpoOfferStopResumeService extends WEB3BusinessService 
{
    
    /**
     * �Ǘ���IPO��W��~�ĊJ���������{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40D0164C0145
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
