head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ؑցE�d�q��t�\���T�[�r�X�C���^�t�F�C�X (WEB3InformAccSwElecDeliApplyService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 �����F (���u) �V�K�쐬 �d�l�ύX���f��098
*/
package webbroker3.inform.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�����ؑցE�d�q��t�\���T�[�r�X)<BR>
 * �����ؑցE�d�q��t�\���T�[�r�X�C���^�t�F�C�X<BR>
 * 
 * @@author �����F
 * @@version 1.0
 */
public interface WEB3InformAccSwElecDeliApplyService extends WEB3BusinessService
{
    /**
     * �����ؑցE�d�q��t�\���T�[�r�X�������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
