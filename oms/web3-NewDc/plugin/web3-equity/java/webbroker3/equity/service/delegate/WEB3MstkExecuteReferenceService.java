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
filename	WEB3MstkExecuteReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����������Ɖ�T�[�r�X(WEB3MstkExecuteReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12  �d��(���u) �V�K�쐬
                   2004/12/29 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;

/**
 * �i�����~�j�����������Ɖ�T�[�r�X�j�B<BR>
 * <BR>
 * �����~�j�����������Ɖ�T�[�r�X�C���^�t�F�C�X
 * @@author �d��
 * @@version 1.0
 */
public interface WEB3MstkExecuteReferenceService extends WEB3BusinessService 
{
    
    /**
     * �iexecute�j�B<BR>
     * �����~�j�����������Ɖ�������{����B
     * @@param l_request (���N�G�X�g)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
