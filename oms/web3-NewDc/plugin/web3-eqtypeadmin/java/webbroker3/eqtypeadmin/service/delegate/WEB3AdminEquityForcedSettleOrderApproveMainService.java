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
filename	WEB3AdminEquityForcedSettleOrderApproveMainService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F���C���T�[�r�X(WEB3AdminEquityForcedSettleOrderApproveMainService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����F (���u) �V�K�쐬 �d�l�ύX���f��No.129
*/
package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F���C���T�[�r�X)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F���C���T�[�r�X�C���^�[�t�F�C�X<BR>
 * <BR>
 * �i�񓯊��������s���ׂ̃G���g���[�N���X�j <BR>
 * �i�g�����U�N�V���������FTX_CREATE_NEW�j<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public interface WEB3AdminEquityForcedSettleOrderApproveMainService extends WEB3BackBusinessService
{

    /**
     * �i�񓯊��j�������ω��������F�^�񏳔F�������N������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 460327F902DE
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;
}
@
