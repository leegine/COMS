head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.33.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultUploadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʱ���۰�޻��޽�C���^�t�F�C�X(WEB3AdminIpoLotResultUploadService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ipo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * �Ǘ���IPO���I���ʱ���۰�޻��޽�C���^�t�F�C�X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public interface WEB3AdminIpoLotResultUploadService extends WEB3BusinessService 
{
    
    /**
     * �Ǘ���IPO���I���ʃA�b�v���[�h���������{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40E1474B0344
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
