head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeCancelNotifyMainService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������ʒm���C���T�[�r�X(WEB3EquityChangeCancelNotifyMainService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 �������F�iSRA�j �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2006/11/02 ������@@(���u)���f��No.1046
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * �i������������ʒm���C���T�[�r�X�C���^�t�F�[�X�j�B
 * @@author �������F
 * @@version 1.0
 */
public interface WEB3EquityChangeCancelNotifyMainService extends WEB3BackBusinessService 
{
    /**
     * (execute)<BR>
     * ������������ʒm���C���T�[�r�X�����{����B <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;
}
@
