head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.55.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformSwElecDeliApplyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�C���^�[�t�F�C�X�iWEB3AdminInformSwElecDeliApplyService.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 �����i���u�j�V�K�쐬 ���f��No.099
*/
package webbroker3.inform.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X) <BR>
 * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�C���^�[�t�F�C�X <BR>
 * @@author ����
 * @@version 1.0
 */
public interface WEB3AdminInformSwElecDeliApplyService extends WEB3BusinessService
{
    /**
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�������s���B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
