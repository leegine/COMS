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
filename	WEB3EquityOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������T�[�r�X�̃G���g���[�|�C���g(WEB3EquityOrderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 ���� ���D(SRA) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
 */
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �i���������T�[�r�X�̃G���g���[�|�C���g�j�B<BR>
 * <BR> 
 * @@see webbroker3.gentrade.WEB3GentradeClientRequestService
 * @@version 1.0
 */
public interface WEB3EquityOrderService extends WEB3BusinessService
{

    /**
     * (�T�[�r�X�G���g���[�|�C���g)<BR>
     *<BR> 
     * @@param l_request
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 400E3ED800CA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
