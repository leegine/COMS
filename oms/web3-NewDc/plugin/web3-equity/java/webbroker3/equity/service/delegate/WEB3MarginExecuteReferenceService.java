head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginExecuteReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������Ɖ�T�[�r�X�C���^�t�F�[�X(WEB3MarginExecuteReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/21 䈋�(���u) �V�K�쐬
                   2005/01/05 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �i�M�p����������Ɖ�T�[�r�X�j�B<BR>
 * <BR>
 * �M�p����������Ɖ�T�[�r�X�C���^�t�F�[�X
 * @@author 䈋�
 * @@version 1.0
 */
public interface WEB3MarginExecuteReferenceService extends WEB3BusinessService 
{
    
    /**
     * �M�p����������Ɖ�T�[�r�X���������{����<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40582F6E01B3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@