head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAttentionInfoNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ӏ��ʒm�T�[�r�X(WEB3AdminEquityAttentionInfoNotifyService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 ���� (���u) �V�K�쐬 ���f��No.219
*/

package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (���ӏ��ʒm�T�[�r�X)<BR>
 * ���ӏ��ʒm�T�[�r�X�C���^�[�t�F�C�X<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_JOIN_EXISTING�j<BR>
 * <BR>
 * @@author ����
 * @@version 1.0
 */
public interface WEB3AdminEquityAttentionInfoNotifyService extends WEB3BackBusinessService
{

    /**
     * ���ӏ��ʒm�T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 49377C740007
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}@
