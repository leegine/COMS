head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoManualExpireMainService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�敨OP�蓮�������C���T�[�r�X (WEB3AdminIfoManualExpireMainService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30�@@�Ӑ�(���u) �V�K�쐬
*/

package webbroker3.ifoadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (�Ǘ��ҁE�敨OP�蓮�������C���T�[�r�X )<BR>
 * �Ǘ��ҁE�敨OP�蓮�������C���T�[�r�X �C���^�[�t�F�C�X <BR>
 *�i�񓯊��������s���ׂ̃G���g���[�N���X�j <BR>
 *�i�g�����U�N�V���������FTX_CREATE_NEW�j<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */

public interface WEB3AdminIfoManualExpireMainService extends WEB3BackBusinessService 
{
    /**
     * �i�񓯊��j�敨OP�蓮�����������N������B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 44693554034E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
