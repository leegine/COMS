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
filename	WEB3AdminSwitchOrderRouteService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҕ�����ؑփT�[�r�X�C���^�t�F�C�X(WEB3AdminSwitchOrderRouteService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
*/
package webbroker3.dirsec.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��Ҕ�����ؑփT�[�r�X)<BR>
 * <BR>
 * �Ǘ��Ҕ�����ؑփT�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * WEB3AdminSwitchOrderRouteService interface<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public interface WEB3AdminSwitchOrderRouteService extends WEB3BusinessService
{
    
    
	/**
	 * �Ǘ��Ҕ�����ؑփT�[�r�X���s���B
	 * @@param l_request - ���N�G�X�g
	 * @@return WEB3GenResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 42D23A1902D3
	 */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException; 
    
}
@
