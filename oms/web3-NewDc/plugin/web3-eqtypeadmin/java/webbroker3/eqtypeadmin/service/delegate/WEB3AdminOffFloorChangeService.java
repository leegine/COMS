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
filename	WEB3AdminOffFloorChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������X�V�T�[�r�X�C���^�t�F�[�X(WEB3AdminOffFloorChangeService.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��җ���O���������X�V�T�[�r�X)<BR>
 * <BR>
 * �Ǘ��җ���O���������X�V�T�[�r�X�C���^�t�F�[�X<BR>
 * <BR>
 * WEB3AdminOffFloorChangeService interface<BR>
 * <BR>
 * @@author Wanishree
 * @@version 1.0
 */
public interface WEB3AdminOffFloorChangeService extends WEB3BusinessService
{
    /**
     * �Ǘ��җ���O���������X�V�T�[�r�X���������{����B<BR>
     * <BR>
     * Execute WEB3AdminOffFloorChangeService<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41BD8BE603B3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
