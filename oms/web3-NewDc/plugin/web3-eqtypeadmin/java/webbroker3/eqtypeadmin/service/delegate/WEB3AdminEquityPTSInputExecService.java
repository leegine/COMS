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
filename	WEB3AdminEquityPTSInputExecService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�iWEB3AdminEquityPTSInputExecService.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/22 ���� (���u) �V�K�쐬���f��177
*/
package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X)<BR>
 * �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�C���^�[�t�F�C�X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public interface WEB3AdminEquityPTSInputExecService extends WEB3BusinessService
{

    /**
     * �����iPTS�j�o�����͏������s���B<BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4790433D0396
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
