head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultOfferDownloadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʍw���\�����޳�۰�޻��޽�C���^�t�F�C�X(WEB3AdminIpoLotResultOfferDownloadService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * �Ǘ���IPO���I���ʍw���\�����޳�۰�޻��޽�C���^�t�F�C�X
 */
public interface WEB3AdminIpoLotResultOfferDownloadService extends WEB3BusinessService 
{
    
    /**
     * �Ǘ���IPO���I���ʍw���\���󋵃_�E�����[�h���������{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40E143F20066
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
