head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontOrderRouteChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҕ����o�H�ؑփT�[�r�X�C���^�t�F�C�X(WEB3AdminFrontOrderRouteChangeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.115
*/
package webbroker3.dirsec.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��Ҕ����o�H�ؑփT�[�r�X)<BR>
 * <BR>
 * �Ǘ��Ҕ����o�H�ؑփT�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * WEB3AdminFrontOrderRouteChangeService interface<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public interface WEB3AdminFrontOrderRouteChangeService extends WEB3BusinessService {


    /**
     * �Ǘ��Ҕ����o�H�ؑփT�[�r�X���������{����B<BR>
     * <BR>
     * Execute WEB3AdminFrontOrderRouteChangeService<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4301842C0199
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
