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
filename	WEB3EquityOrderCarryOverSkipService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������J�z�X�L�b�v�����ʒm�T�[�r�X(WEB3EquityOrderCarryOverSkipService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 羐� (���u) �V�K�쐬
                   2004/12/13 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.service.delegate.WEB3BackBusinessService;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.WEB3BaseException;

/**
 * �i���������J�z�X�L�b�v�����ʒm�T�[�r�X�C���^�t�F�[�X�j�B<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@version 1.0
 */
public interface WEB3EquityOrderCarryOverSkipService
    extends WEB3BackBusinessService
{

    /**
     * ���������J�z�X�L�b�v�����ʒm�T�[�r�X���������{����<BR>
     * @@param l_request - ���N�G�X�g
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4056DE2802DC
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;

}
@
