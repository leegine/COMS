head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityManualExpireMainService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����蓮�������C���T�[�r�X(WEB3AdminEquityManualExpireMainService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/5/31 ������ (���u) �V�K�쐬
*/

package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (�Ǘ��ҁE�����蓮�������C���T�[�r�X)<BR>
 * �Ǘ��ҁE�����蓮�������C���T�[�r�X�C���^�[�t�F�C�X <BR>
 *�i�񓯊��������s���ׂ̃G���g���[�N���X�j <BR>
 *�i�g�����U�N�V���������FTX_CREATE_NEW�j<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */

public interface WEB3AdminEquityManualExpireMainService extends WEB3BackBusinessService 
{
    /**
     * �i�񓯊��j�����蓮�����������N������B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 44693554034E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
