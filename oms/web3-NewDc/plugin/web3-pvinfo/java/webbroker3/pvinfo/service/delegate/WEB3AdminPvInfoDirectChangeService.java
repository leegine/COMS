head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.04.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoDirectChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃_�C���N�g�w��ύX�T�[�r�X(WEB3AdminPvInfoDirectChangeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/26 ���O�B(���u) �쐬
*/
package webbroker3.pvinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��҃_�C���N�g�w��ύX�T�[�r�X)<BR>
 * �Ǘ��҃_�C���N�g�w��ύX�T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.00
 */
public interface WEB3AdminPvInfoDirectChangeService extends WEB3BusinessService 
{
    
    /**
     * �_�C���N�g�w��ύX�������s���B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 416103730367
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
